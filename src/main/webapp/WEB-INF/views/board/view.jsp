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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

#table {
margin-bottom:150px;

/*td 조절*/
  td{
      border: 1px solid gray;
     padding:10px;
     text-align:center;
      
     &:nth-of-type(1){
                    width: 150px;
                    background-color: #47CBF2;
                    color:white;
                     }
     &:nth-of-type(2) {
                        width: 250px;
                       }
     &:nth-of-type(3){
                    width: 150px;
                    background-color: #47CBF2;
                    color:white;
                   } 
    &:nth-of-type(4) {
                        width: 250px;
                       }                 
                                  
     }
   tr:nth-of-type(3) td:nth-of-type(2) {
   text-align:left;
   }  
   tr:nth-of-type(4) td:nth-of-type(2) {
   text-align: left;
   width:600px;
   height:250px;
   vertical-align:baseline;
   }  

   tr:last-of-type td{
   background-color: white;
   color:black;
   }

a.btn.btn-info:hover {
text-decoration: none;
}

}


</style>
</head>

<body>
<main>
<%@include file="/WEB-INF/include/menus.jsp" %>
<h2> ${menu_name} 게시글 내용보기</h2>

 <table id=table>
   <tr>
     <td>글 번호</td>
     <td>${vo.idx}</td>
     <td>조회수</td>
     <td>${vo.hit}</td>
   </tr>
   <tr>
     <td>작성자</td>
     <td>${vo.writer}</td>
     <td>작성일</td>
     <td>${vo.regdate}</td>
   </tr>
   <tr>
     <td>제목</td>
     <td colspan="3">${vo.title}</td>
   </tr>
   <tr>
     <td>내용</td>
     <td colspan="3">${vo.content}</td>
   </tr>
   <tr>
     <td colspan="4">
     <a class="btn btn-info" href="/Board/WriteForm?menu_id=${vo.menu_id}" role="button">새 글 쓰기</a>
     
     <c:if test="${login.userid eq vo.writer}">
     <a class="btn btn-info" href="/Board/UpdateForm?idx=${vo.idx}&menu_id=${vo.menu_id}" role="button">수정</a>
     <a class="btn btn-info"  href="/Board/Delete?idx=${vo.idx}&menu_id=${vo.menu_id}" role="button">삭제</a>
     </c:if>
     
     <a class="btn btn-info" href="/Board/List?menu_id=${vo.menu_id}" role="button">목록</a>
     <a class="btn btn-info"  href="/" role="button">HOME</a>
 
     </td>
   </tr>
 </table>




</main>
<script>

	



</script>

</body>
</html>