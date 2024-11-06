<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 테스트</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>

<style>
#table {
       td{
          padding: 10px;
          text-align: center;
          }
       td:nth-of-type(1) { width:100px;}
       td:nth-of-type(2) { width:380px;}
       td:nth-of-type(3) { width:100px;}
       td:nth-of-type(4) { width:100px;}
       td:nth-of-type(5) { width:120px;}
       td:nth-of-type(6) { width:100px;}
       tr:first-child {
                         background-color: #333;
                         color:white;
                         font-weight:700;
                         td{
                             border-color: silver;
                          } 
                        }
     tr:nth-child(2)>td{
                      text-align: right;
                     }

}
   
</style>

</head>
<body>
	<main>  
	<%@include file="/WEB-INF/include/pdsmenus.jsp" %>
	  <h2>${menu_name} 자료실 목록 </h2>
	  <table id="table">
	    <tr>
	      <td>번호</td>
	      <td>제목</td>
	      <td>작성자</td>
	      <td>파일수</td>
	      <td>작성일</td>
	      <td>조회수</td>

	    </tr>
	    <tr>
	      <td colspan="6">
	        [<a href="/Pds/WriteForm?menu_id=${map.menu_id}&nowpage=${map.nowpage}">새 글 추가</a>]&nbsp;&nbsp;&nbsp;	      	      
	        [<a href="/">HOME</a>]&nbsp;		      	      
	      </td>
	    </tr>

	    <c:forEach var="pds" items="${ response.list }">
	     <tr>
	    <td>${pds.idx}</td>
	    <td><a href="/Pds/View?idx=${pds.idx}&nowpage=${map.nowpage}&menu_id=${map.menu_id}">${pds.title}</a></td>
	    <td>${pds.writer}</td>
	    <td>${pds.filescount}</td>
	    <td>${pds.regdate}</td>
	    <td>${pds.hit}</td>
             </tr>
	    </c:forEach>	    	    	     
	    
	  </table>
	<%@include file="/WEB-INF/include/pdspaging.jsp" %>
	<div id="searchbox">
	<form action="/Pds/List" method="POST">
	 <input type="hidden" name="nowpage" value="${map.nowpage}">
	 <input type="hidden" name="menu_id" value="${map.menu_id}">
	 <select name="search">
	   <option value="title">제목</option> 
	   <option value="writer">작성자</option> 
	   <option value="content">내용</option> 
	 </select>
	 <input type="text" name="searchtext" />
	 <input type="submit" value="검색"/>
	 </form>
	</div>
	
	</main>
</body>
</html>

