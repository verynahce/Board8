<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
<style>
.green{color:green;}
.red{color:red;}
</style>
</head>
<body>
<main>
 <form action="/Users/DupCheck" method="POST">
   <h2>ID 중복확인</h2>
   <input type="text" name="userid" id="userid" value="${param.userid}" />
   <input type="submit" value="검색"/>
 </form>
  <div style="height:50px;"></div>
  <c:choose >
   <c:when test ="${userVo eq null }">
    <p class="green">${param.userid}는 사용가능한 아이디입니다
    <input type="button" value="아이디 사용하기" id ="btnClose"/> 
    </p>
   </c:when>
   <c:otherwise>
    <p class="red">${param.userid}는 사용불가한 아이디입니다</p>
   </c:otherwise>
  </c:choose>
</main>

<script>
	const btnCloseEl =document.getElementById('btnClose')
	btnCloseEl.onclick = function() {
		alert('ID 중복확인완료')
		//넘겨줄 창의 userid에 조회된 결과를 보낸다
	    //넘겨줄 창(내창을 open한 widnow) :window.opener ->mf
	    //내창 :window
	    const mfUseridEl = window.opener.document.querySelector('[name=userid]');
		const cfUseridEl = window.document.querySelector('#userid')
	   mfUseridEl.value =  cfUseridEl.value

	    window.close();
	}


</script>



</body>
</html>