(function () {
  if (localStorage.getItem("isLogin")) {
    // 카테고리 변경 -> 로그아웃만 나오도록

    const category = document.querySelector(".category");
    category.style.display = "none";

    const categoryLogout = document.querySelector(".category_logout");
    categoryLogout.style.display = "flex";
  } else {
    // 카테고리 변경 -> 로그인 & 회원가입
    const category = document.querySelector(".category");
    category.style.display = "flex";
    const categoryLogout = document.querySelector(".category_logout");
    categoryLogout.style.display = "none";
  }
})();
