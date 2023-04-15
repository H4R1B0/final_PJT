const show_count = 4;
const slide_show = document.getElementById("slide_show");
const slides = document.querySelector(".slides");
const slide_imgs = document.querySelectorAll(".slides li");
let current_idx = 0;
const slide_count = slide_imgs.length;
const prev = document.querySelector(".prev");
const next = document.querySelector(".next");
const slide_width = 18;
const slide_margin = 4;

slide_show.style.width =
  -2 + slide_width * show_count + slide_margin * (show_count + 1) + "em";
slides.style.width = (slide_width + slide_margin) * slide_count + "em";

/* 일정 시간마다 슬라이드 */
let slide_interval = setInterval(function () {
  move_slide((current_idx + 1) % (slide_count / show_count));
}, 4000);

function move_slide(num) {
  slides.style.left = -num * (slide_width + slide_margin) * show_count + "em";
  current_idx = num;
}
prev.addEventListener("click", function () {
  move_slide((current_idx + slide_count - 1) % (slide_count / show_count));
});
next.addEventListener("click", function () {
  move_slide((current_idx + 1) % (slide_count / show_count));
});
/* 슬라이드 제거 */
function remove_interval() {
  clearInterval(slide_interval);
}
/* 슬라이드 시작 */
function start_interval() {
  slide_interval = setInterval(function () {
    move_slide((current_idx + 1) % (slide_count / show_count));
  }, 4000);
}
