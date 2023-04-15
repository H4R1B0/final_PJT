/* 로그아웃 버튼 클릭 */

(function () {
  const categoryLogout = document.querySelector(".category_logout");
  categoryLogout.addEventListener("click", () => {
    localStorage.removeItem("isLogin");
    alert("로그아웃 되었습니다.");

    // 카테고리 변경 -> 로그인 & 회원가입
    const category = document.querySelector(".category");
    category.style.display = "flex";
    const categoryLogout = document.querySelector(".category_logout");
    categoryLogout.style.display = "none";
  });
})();
