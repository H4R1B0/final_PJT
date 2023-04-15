import { API_KEY } from "../../../api.js";
(function () {
  // index.html 에서 보냈던 클릭한 지역명
  const encodeArea = location.href.split("?")[1];
  const area = decodeURI(encodeArea);

  // 지역명 받아온 지역으로 변환
  const title = document.querySelector(".title");
  title.innerText = area;

  // 해당 지역의 관광지 불러오기
  const url = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${API_KEY}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&keyword=${encodeArea}&contentTypeId=12`;

  fetch(url)
    .then((res) => res.json())
    .then((data) => renderData(data));

  const renderData = (data) => {
    // const tours = document.querySelector(".tours");
    const tour_table = document.querySelector(".tour_table");
    const datas = data.response.body.items.item;
    const keys = ["title", "tel", "addr1"];
    for (const data of datas) {
      const tr = document.createElement("tr");
      for (const key of keys) {
        const td = document.createElement("td");
        td.innerText = data[key];
        tr.appendChild(td);
      }
      tour_table.appendChild(tr);
    }
  };
})();
