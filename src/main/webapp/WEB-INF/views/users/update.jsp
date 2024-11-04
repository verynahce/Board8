<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
<style>

td{
padding:10px;
text-align: center;

}


td:nth-of-type(1){
	width:200px;
	background-color: black;
	color:white;
	font-weight: bold;
	border-bottom-color: white;
}
td:nth-of-type(2) {
	width:600px;
}
input {
	width:100%;}
td:last-of-type{
background-color: white;
border-bottom-color: black;
}
input[type=submit] {width:100px;}
input[type=button] {width:100px;}
#passwd1 {color:gray;}

</style>
</head>

<body>
<main>
<h2>사용자 수정</h2>
<form action="/Users/Update" method="POST">
 <table>
   <tr>
     <td>사용자 아이디</td>
     <td><input type ="text" name="userid" id ="userid" value="${uservo.userid}" readonly/></td>
   <tr>
     <td>기존 비밀번호</td>
     <td><input type ="password"id="passwd1" value="${uservo.passwd}" readonly/></td>
   </tr>
   <tr>
     <td>기존 비밀번호 확인</td>
     <td><input type ="password" id="passwd2"/></td>
   </tr>
      <tr>
     <td>새 비밀번호</td>
     <td><input type ="password"  name="passwd"  id="passwd3"/></td>
   </tr>
   <tr>
     <td>사용자 이름</td>
     <td><input type ="text" name="username" id ="username" value="${uservo.username}"/></td>
   </tr>
   <tr>
     <td>이메일</td>
     <td><input type ="email" name="email" value="${uservo.email}"/></td>
   </tr>
   <tr id="last">
     <td colspan="2">
     <input type ="submit" value="추가" id="gosum"/>
     <input type ="button" value="목록" id="goList"/>
     </td>
   </tr>
 </table>


</form>
</main>
<script>

  const goList = document.getElementById('goList');
      goList.onclick= function() {
	location.href = '/Users/List';

      }
      // 입력값 확인 기능
      
      
      
     //변수 지정
      const formEl = document.querySelector('form');
      const passwd1EL =  document.getElementById('passwd1');
	   const passwd2EL =  document.getElementById('passwd2');
	   const passwd3EL =  document.getElementById('passwd3');
	   const useridEL =  document.getElementById('userid');
	   const usernameEL =  document.getElementById('username');
      
      
     
	   formEl.onsubmit = function(){
    	let pwd1 = passwd1EL.value;
    	let pwd2 = passwd2EL.value;
    	let pwd3 = passwd3EL.value;
    	  
    	
    //ID 비밀번호 사용자 이름 입력 필수	
    	if(useridEL.value.trim() == ''){
    		alert('아이디를 입력하시오')
    		useridEL.focus()
    		return false;
    	}else if(!pwd2) {
    		alert('비밀번호확인을 입력하시오')
    		passwd2EL.focus()
    		return false;
    	}else if(!pwd3) {
    		alert('새비밀번호를 입력하시오')
    		passwd1EL.focus()
    		return false;
    	}else if(pwd1 !== pwd2) {
    		alert('비밀번호가 일치하지 않습니다')
    		passwd2EL.focus()
    		return false;
    	}else if(usernameEL.value.trim().length < 2) {
    		alert('이름은 두자이상 입력하세요')
    		usernameEL.focus()
    		return false;
    	}else {
    	    return true;
    	  
      }
      
      }   
	
	

      
      



</script>

</body>
</html>