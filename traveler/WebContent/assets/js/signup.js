(function () {
    /* 회원가입 MODAL */
    const categorySignupBtn = document.getElementById("category_signup_btn");
    const modal = document.querySelector(".signup_modal");
    const overlay = document.querySelector(".signup_modal .modal_overlay");
    const closeBtn = document.querySelector(".signup_modal .closeBtn");

    /* MODAL 생성 및 제거 */
    // modal open
    const openModal = () => {
        modal.classList.remove("hidden");
    };
    // modal close
    const closeModal = () => {
        modal.classList.add("hidden");
    };

    categorySignupBtn.addEventListener("click", (e) => {
        e.preventDefault();
        openModal();
    });

    closeBtn.addEventListener("click", closeModal);

    // 모달의 바깥부분 누르면 모달 없어지게
    overlay.addEventListener("click", closeModal);

    // MODAL의 회원가입 버튼 클릭
    const signupBtn = document.querySelector(".signup_btn");
    signupBtn.addEventListener("click", () => {
        closeModal();
        alert("회원가입 되었습니다");
    });
})();
