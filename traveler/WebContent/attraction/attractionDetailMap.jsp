<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/map.css" />

<title>위치 정보</title>
</head>
<body>
	<%@ include file="/include/header.jsp"%>
	<main>

		<h2 class="map_title">위치 조회</h2>
		
        ${locationInfo.title}
        
		<div id="map" style="width: 500px; height: 400px;"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2886c9bce8ecfb5fab6295f850bee53"></script>
		<section>
			<script>
				var container = document.getElementById('map');
				var options = {
					center : new kakao.maps.LatLng("${locationInfo.latitude}", "${locationInfo.longitude}"),
					level : 3
				};

				var map = new kakao.maps.Map(container, options);
				
				var markerPosition  = new kakao.maps.LatLng("${locationInfo.latitude}", "${locationInfo.longitude}"); 

				var marker = new kakao.maps.Marker({
				    position: markerPosition
				});

				marker.setMap(map);

			</script>
		</section>


	</main>
	<%@ include file="/include/footer.jsp"%>
</body>
</html>