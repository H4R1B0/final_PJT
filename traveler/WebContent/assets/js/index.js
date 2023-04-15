(function () {
  // logo 클릭 시 index.html로 이동!
  const logo = document.querySelector(".logo");
  logo.addEventListener("click", () => {
    document.location.href = "index.html";
  });

  // areas 클릭 시 각 area 에 맞는 관광지 소개 페이지로 이동
  const areas = document.querySelectorAll(".areas");
  areas.forEach((area) => {
    area.addEventListener("click", (e) => {
      if (localStorage.getItem("isLogin")) {
        document.location.href = `travel.html?${e.target.alt}`;
      } else {
        alert("로그인이 필요한 서비스입니다.");
      }
    });
  });
})();
