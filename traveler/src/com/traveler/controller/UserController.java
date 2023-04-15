package com.traveler.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.traveler.dto.User;
import com.traveler.model.service.UserService;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = UserService.getInstance();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		String action = request.getParameter("action");
		System.out.println("action : "+ action);

		if(action == null) {
			response.sendRedirect(request.getContextPath()+"/error/error.jsp");
		}
		else if(action.equals("login")) {
			login(request, response);
			
			
		}
		else if(action.equals("signin")) {
			signin(request, response);
		}
		else if(action.equals("logout")) {
			logout(request, response);
		}
		else if(action.equals("update")) {
			update(request, response);
		}
		else if(action.equals("delete")) {
			delete(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath()+"/error/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 에서 get 호출");
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("로그인메소드");
		//값 받아오기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String remainLogin = request.getParameter("remainLogin");
		String result;
		//아이디저장 쿠키 설정하기
		Cookie cookie = new Cookie("id", id); 
		if(remainLogin != null) {
			cookie.setMaxAge(60*60*3); 
		}
		else {
			cookie.setMaxAge(0); 
		}
		response.addCookie(cookie);
		//객체만들기
		User user = new User();
		user.setId(id);
		user.setPwd(pwd);
		System.out.println("login : " + user);
		//결과 받아오기
		try {
			 result = userService.login(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/error/error.jsp");
			return;
		}
		//결과 처리하기
		System.out.println("로그인결과:"+result);
		if(result == null) {
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
			System.out.println(" 정보가 없습니다.");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath());
			HttpSession session = request.getSession();
			try {
				user = userService.check(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("userInfo", user);
		}
		//response.sendRedirect(request.getContextPath()+"/user/login.jsp");
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("로그아웃메소드");
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		
		response.sendRedirect(request.getContextPath());
		
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("회원가입메소드");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String advertisement = request.getParameter("adv");
		boolean adv;
		if(advertisement.equals("true")){
			adv = true;
		}
		else {
			adv=false;
		}
		User user = new User(id, pwd, email, adv);
		
		try {
			userService.signin(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("회원정보갱신메소드");
		HttpSession session = request.getSession();
		// TODO Auto-generated method stub
				System.out.println("회원가입메소드");
				
				String id = request.getParameter("id");
				String email = request.getParameter("email");
				String pwd = request.getParameter("pwd");
				String advertisement = request.getParameter("adv");
				boolean adv;
				if(advertisement.equals("true")){
					adv = true;
				}
				else {
					adv=false;
				}
				if(pwd=="") {
					User oldInfo = (User) session.getAttribute("userInfo");
					pwd=oldInfo.getPwd();
				}
				User user = new User(id, pwd, email, adv);
				
				try {
					userService.update(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					user = userService.check(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("userInfo", user);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("회원삭제 메소드");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		session.removeAttribute("userInfo");
		
		try {
			userService.delete(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


