(function () {
  /* 로그인 MODAL */
  const categoryLoginBtn = document.getElementById("category_login_btn");
  const modal = document.querySelector(".login_modal");
  const overlay = document.querySelector(".login_modal .modal_overlay");
  const closeBtn = document.querySelector(".login_modal .closeBtn");

  /* MODAL 생성 및 제거 */
  // modal open
  const openModal = () => {
    modal.classList.remove("hidden");
  };
  // modal close
  const closeModal = () => {
    modal.classList.add("hidden");
  };

  categoryLoginBtn.addEventListener("click", (e) => {
    e.preventDefault();
    openModal();
  });

  closeBtn.addEventListener("click", closeModal);

  // 모달의 바깥부분 누르면 모달 없어지게
  overlay.addEventListener("click", closeModal);

  // MODAL의 로그인 버튼 클릭
  const loginBtn = document.querySelector(".login_btn");
  loginBtn.addEventListener("click", () => {
    closeModal();
    localStorage.setItem("isLogin", true);

    alert("로그인 되었습니다");

    // 카테고리 변경 -> 로그아웃만 나오도록
    const category = document.querySelector(".category");
    category.style.display = "none";

    const categoryLogout = document.querySelector(".category_logout");
    categoryLogout.style.display = "flex";
  });

  // 회원가입 페이지로 가기 버튼 클릭
  const goSignupBtn = document.querySelector(".go_signup_btn");
  const signupModal = document.querySelector(".signup_modal");

  goSignupBtn.addEventListener("click", () => {
    closeModal();
    signupModal.classList.remove("hidden");
  });
})();
