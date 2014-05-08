<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- category  -->			
	<div class="col-md-12 row" name="category" id="categoryForm" method="post" style="margin-bottom: 15px" >
	
		<label class="sr-only">상품 대분류</label> <!-- label은 클래스를 이용하여 숨김처리-->
		<select style="width: 200px;" class="form-control input-sm"
			name="good_ctgy" id="good_ctgy_Depth01" >
			
			<option selected="selected">판매식품 전체</option>
			<option value="01" <c:if test="${good_ctgy eq '01'}">selected</c:if>>쌀/과일/농수축산물</option>
			<option value="02" <c:if test="${good_ctgy eq '02'}">selected</c:if>>차/음료</option>
			<option value="03" <c:if test="${good_ctgy eq '03'}">selected</c:if>>과자</option>
			<option value="04" <c:if test="${good_ctgy eq '04'}">selected</c:if>>가공식품</option>
			<option value="05" <c:if test="${good_ctgy eq '05'}">selected</c:if>>건강식품</option>
			<option value="06" <c:if test="${good_ctgy eq '06'}">selected</c:if>>다이어트식품</option>
			<option value="07" <c:if test="${good_ctgy eq '07'}">selected</c:if>>유아식</option>
	
		</select>
				
		<label class="sr-only">상품 중분류</label>		
		<select style="width: 200px;" class="form-control input-sm"
			name="good_ctgy" id="good_ctgy_Depth02">
			<option selected="selected">쌀/과일/농수축산물</option>
	        <option>쌀/찹쌀/현미</option>
	        <option>혼합곡/잡곡류</option>
	        <option>국내산과일</option>
	        <option>수입과일</option>
	        <option>냉동/말린과일</option>
	        <option>고구마/감자/옥수수외</option>
	        <option>배추/엽채/쌈채소</option>
	        <option>양념/버섯/나물</option>
	        <option>견과류</option>
	        <option>생선류</option>
	        <option>해산물/어패류</option>
	        <option>건어물</option>
	        <option>김/해초류</option>
	        <option>한우</option>
	        <option>국내산 육우</option>
	        <option>수입소고기</option>
	        <option>국내산 돼지고기</option>
	        <option>수입돼지고기</option>
	        <option>닭고기/오리/기타</option>
	        <option>축산가공식품</option>
	        <option>김치</option>
	        <option>반찬/젓갈류</option>
	        <option>업소용/식자재코너</option>
	        <option>친환경브랜드식품관</option>
		</select>		
				
		<label class="sr-only">상품 소분류</label>
		<select style="width: 200px;" class="form-control input-sm"
			name="good_ctgy" id="good_ctgy_Depth03" >
			<option selected="selected">쌀/찹쌀/현미</option>
	        <option>일반쌀 20kg</option>
	        <option>일반쌀 10kg</option>
	        <option>소포장 쌀</option>
	        <option>현미/발아현미</option>
	        <option>흑미/보리</option>
	        <option>찹쌀</option>
	        <option>기능성 쌀</option>
	        <option>농협쌀</option>
	        <option>이천쌀</option>
		</select>
		
		</div>
		<!-- /.분류설정 -->	


</body>
</html>