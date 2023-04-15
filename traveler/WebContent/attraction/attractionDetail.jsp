<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/detail.css" />
<script src="https://kit.fontawesome.com/108b003e84.js" crossorigin="anonymous"></script> <!-- 폰트어썸 아이콘 import -->
<!-- <script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=02d1d86d8ff0c5647ae58e81d9999c86&libraries=services,clusterer,drawing"></script>
-->

<title>관광지 정보 조회</title>
</head>
<body>
	<%@ include file="/include/header.jsp"%>
	<main>

		<h1 class="detail_title">${title}&nbsp;관광&nbsp;정보</h1>

		<!-- 검색으로 살리거나 nav에서 살릴거임 -->
		<!--
		<form class="selection">
			<label for="city">도시</label> <select id="city" class="drop_box">
				<option value="" disabled selected>도시를 선택하시오</option>
				<option value="제주">제주도</option>
				<option value="서울">서울</option>
				<option value="강원">강원도</option>
				<option value="부산">부산</option>
				<option value="대전">대전</option>
				<option value="대구">대구</option>
				<option value="광주">광주</option>
				<option value="경주">경주</option>
			</select> <label for="sight_type">관광타입</label> <select id="sight_type"
				class="drop_box">
				<option value="" disabled selected>관광타입을 선택하시오</option>
				<option value="12">관광지</option>
				<option value="14">문화시설</option>
				<option value="15">축제공연행사</option>
				<option value="25">여행코스</option>
				<option value="28">레포츠</option>
				<option value="32">숙박</option>
				<option value="38">쇼핑</option>
				<option value="39">음식점</option>
			</select>

			<button id="fetch_btn" class="submit">요청보내기</button>
		</form>
		-->

		<!-- 망하면 이대로 제출 -->
		<!--  <table>
			<tr>
				<th>제목</th>
				<th>주소</th>
				<th>이미지1</th>
			</tr>

			<c:forEach items="${attractionList}" var="attraction">
				<tr>
					<td>${attraction.title}</td>
					<td>${attraction.addr}</td>
					<td><img class="detail_img" src="${attraction.img1}"
						onerror="this.src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpizjtvgskfw6Wuu2sLTi2_1vW1gJgFPFtMw&usqp=CAU';" /></td>
				</tr>
			</c:forEach>
		</table> -->
		<section class="detailMain">
			<ul class="detail_list">
				<c:forEach items="${attractionList}" var="attraction">
					<li class="detail_list_item">
						<img class="detail_img"
						src="${attraction.img1}"
						onerror="this.src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpizjtvgskfw6Wuu2sLTi2_1vW1gJgFPFtMw&usqp=CAU';" />
						<ul>
							<li class="detail_title">관광지</li>
							<li class="detail_content">${attraction.title}</li>
							<li class="detail_title">주소</li>
							<li class="detail_content">${attraction.addr}&nbsp;<a href="${pageContext.request.contextPath}/attraction?param=location&content_id=${attraction.content_id}"><i class="fa-sharp fa-solid fa-map-location-dot"></i></a></li>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</section>

		<!-- <div id="map" style="width: 100%; height: 400px;"></div>
		<section>
			<script>
				var container = document.getElementById('map');
				var options = {
					center : new kakao.maps.LatLng(33.450701, 126.570667),
					level : 3
				};

				var map = new kakao.maps.Map(container, options);
			</script>
		</section>  -->


	</main>
	<%@ include file="/include/footer.jsp"%>
</body>
</html>