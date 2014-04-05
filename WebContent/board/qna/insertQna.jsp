<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<form mrthod="post" enctype="multipart/form-data" action="InsertQnaAction.action">
<table width="700" height="1" border="2" cellspacing="10"	cellpadding="1">
	<tr>
		<td align="center" bgcolor="#FAED7D"><h1>QnA 게시판</h1></td>
	</tr>
</table>
<table width="700" height="2" border="2" cellspacing="3" cellpadding="2">
	<tr>
		<td align="center" width="150" bgcolor="#B7F0B1"></font>작성자</td>
		<td width="500" bgcolor="#5CD1E5"><input size="40" type="text"
			name="qna_id" value=""></input></td>
	</tr>
	<tr>
		<td align="center" width="150" bgcolor="#B7F0B1"></font>글제목</td>
		<td width="500" bgcolor="#5CD1E5"><input size="90" type="text"
			name="qna_subject" value=""></input></td>
	</tr>
	<tr>
		<td align="center" width="150" bgcolor="#B7F0B1"></font>공개여부
			<td><input size="20" type="radio" name="qna_isprivate" value="yes"
				checked />공개 <input size="20" type="radio" name="qna_isprivate" value="no" />비공개</td>
		</td>
	</tr>
	<tr>
		<td align="center" width="150" bgcolor="#B7F0B1"></font>문의 사항
		<td>
			<select name="qna_category">
				<option value="error">오류 문의</option>
				<option value="business">업소정보 문의</option>
				<option value="event">이벤트 문의</option>
				<option value="alliance">제휴 문의</option>
				<option value="review">리뷰 문의</option>
				<option value="member">회원정보 문의</option>
				<option value="others">기타 문의</option>
			</select>
		</td>
		</td>
	</tr>
	<tr>
		<td align="center" width="500" bgcolor="#B7F0B1">글내용</td>
		<td><textarea name="qna_content" rows="15" cols="70"></textarea></td>
	</tr>

	<tr>
		<td align="right" colspan="2"bgcolor="#FAED7D"><input type="submit" value="글등록" ></input>
	  	<input type="button" value="글목록보기" OnClick="window.location='/qna/ListQna.action'"></input>
	  	</td>
	</tr>	
</table>
</form>
</head>
</html>