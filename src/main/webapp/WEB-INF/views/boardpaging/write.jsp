<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>

#table {
/*td 조절*/

 border-collapse: separate;
 border-color: white;
  td{
  padding:10px;
 
 border-radius: 18px;
}

td:nth-of-type(1){
	width:200px;
	background-color: #47CBF2;
	color:white;
	font-weight: bold;

	text-align: center;
}
td:nth-of-type(2) {
	width:600px;
}
input {width:100%;}
td:last-of-type{
background-color: white;

}
input[name="userid"] {width:30%;}


/*버튼 크기 조절(세부조절)*/
input[type=submit] {width:100px;}
input[type=button] {width:100px;}
#dupCheck{width:70px;}
#dupCheck2{width:102px;}

/*색추가*/
.red {color:red;}
.green{color:green;}

}
textarea {
width:100%;
height: 300px;

}
a.btn.btn-info:hover {
text-decoration: none;
}
</style>
</head>

<body>
<main>
<%@include file="/WEB-INF/include/menus2.jsp" %>
<h2> ${menu_name} 게시글 등록</h2>
<form action="/BoardPaging/Write" method="POST">
     <input type="hidden" name="menu_id" value="${menu_id}" id="menu_id"/>
     <input type="hidden" name="nowpage" value="${nowpage}" id="nowpage"/>
 <table id=table>
   <tr>
     <td><span class="red">*</span>제목</td>
     <td><input type ="text" name="title" id="title"/></td>
   <tr>
   <tr>
     <td><span class="red">*</span>작성자 이름</td>
     <td><input type ="text" name="writer" id ="writer" value="${login.userid}"/></td>
   </tr>
   <tr>
     <td>내용</td>
     <td><textarea name="content" maxlength="1300"></textarea></td>
     </tr>

   <tr>
     <td colspan="2">
     <input type ="submit" class="btn btn-info" value="글저장" id="goSum"/>
     <input type ="button" class="btn btn-info" value="목록" id="goList"/>
     </td>
   </tr>
 </table>


</form>
</main>
<script>
    // 리스트 버튼 이동
      const goList = document.getElementById('goList');
      const titleEl = document.getElementById('title');
      const writerEl = document.getElementById('writer');
      const menu_idEl = document.getElementById('menu_id');

      const formEl = document.getElementsByTagName('form')[0];
      let nowpage =${nowpage};
      
      goList.onclick= function() {
	location.href = '/BoardPaging/List?menu_id='+menu_idEl.value+'&nowpage='+nowpage;
			}
	
      formEl.onsubmit = function() {
    	  if(titleEl.value.length < 4) {
    		  alert('제목은 4자이상 입력하세요')
    		  titleEl.focus()
    		  return false;
    	  }else  if(writerEl.value.trim() == '') {
    		  alert('작성자를 입력하시오')
    		  writerEl.focus()
    		  return false;
    	  } 
    		  
    	  return true;
    	  
      }
      



</script>

</body>
</html>