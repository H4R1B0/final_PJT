package com.traveler.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.traveler.dto.AttractionInfo;
import com.traveler.model.service.AttractionInfoService;

@WebServlet("/attraction")
public class AttractionInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttractionInfoService attractionInfoService = AttractionInfoService.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		
		try {
			if(param.equals("listByTitle")) {
				getListByTitle(request, response);
			} else if(param.equals("location")) {
				findLocation(request, response);
			} 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	

	private void findLocation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

		System.out.println("위치 조회 요청");
		
		String content_id = request.getParameter("content_id");
		System.out.println(content_id);
		
		AttractionInfo attractionInfo = attractionInfoService.selectlocationById(content_id);
		System.out.println(attractionInfo);
		
		request.setAttribute("locationInfo", attractionInfo);
		
		request.getRequestDispatcher("attraction/attractionDetailMap.jsp").forward(request, response);
	}



	private void getListByTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		System.out.println("제목 포함 조회 요청");
		
		String title = request.getParameter("title");
		
		List<AttractionInfo> attractionList = attractionInfoService.selectByTitle(title);
		request.setAttribute("title", title);
		request.setAttribute("attractionList", attractionList);
		
		request.getRequestDispatcher("attraction/attractionDetail.jsp").forward(request, response);
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
