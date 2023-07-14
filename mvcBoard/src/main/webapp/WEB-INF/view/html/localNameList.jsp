<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

<script
src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
</script>
<script>
	$(document).ready(function(){
		
		const x = [];
		const y = [];
		// const barColors = ["red", "green","blue","orange","brown"]; 디폴트 색상 사용
		
		// 동기호출로 x,y값을 세팅
		$.ajax({
			async : false, // true(비동기:기본값) - 자바에서 다른 스레드에서 동작하는 개념, 페이지가 띄워진 후 이벤트 동작시킬때 유용, 
			url : '/rest/localNameList', // false(동기) - 그래프보다 먼저 X, Y 값을 받아온다.
			type : 'get',
			success : function(model) {
				// Backend Model -> Frontend Model 변경
				
				/* model -> {'model':[{localName:'부산', cnt:10},
					{localName:'부산', cnt:10}, 반복]}
				*/
				model.forEach(function(item, index) {
					
					$('#target').append('<tr>');
					$('#target').append('<td>'+item.localName+'</td>');
					$('#target').append('<td>'+item.cnt+'</td>');
					$('#target').append('</tr>');
					
					// chart모델 생성
					x.push(item.localName);
					y.push(item.cnt);
					
				});
			}
		});
		

		new Chart("target2", {
		  type: "bar",
		  data: {
		    labels: x,
		    datasets: [{
		      // backgroundColor: barColors,
		      data: y
		    }]
		  },
		  // options: {...} 생략
		});
		
	});
</script>
</head>
<body>
	<h1>AJax API사용으로 localNameList 호출</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>지역이름</th>
				<th>게시글수</th>
			</tr>
		</thead>
		<tbody id="target">
		
		</tbody>
	</table>
	
	<canvas id="target2" style="width:100%;max-width:700px"></canvas>
	
</body>
</html>