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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>


/*td 조절*/
td{
padding:10px;
}

td:nth-of-type(1){
	width:200px;
	background-color: #404040;
	color:white;
	font-weight: bold;
	border-bottom-color: white;
	text-align: center;
}
td:nth-of-type(2) {
	width:600px;
}
input {width:100%;}
td:last-of-type{
background-color: white;
border-bottom-color: black;
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
</style>
</head>

<body>
<main>
<h2>사용자 등록</h2>
<form action="/Users/Write" method="POST">
 <table>
   <tr>
     <td><span class="red">*</span>사용자 아이디</td>
     <td id=" tduserid">
     <input type ="text" name="userid" id="userid"/>
     <input type ="button" name="dupCheck" value="중복확인" id="dupCheck"/>
     <input type ="button" name="dupCheck2" value="중복확인(새창)" id="dupCheck2"/>
     <span id="dupResult"></span>
     </td>
   <tr>
     <td><span class="red">*</span>비밀번호</td>
     <td><input type ="password" name="passwd" id="passwd1"/></td>
   </tr>
   <tr>
     <td><span class="red">*</span>비밀번호 확인</td>
     <td><input type ="password" id="passwd2"/></td>
   </tr>
   <tr>
     <td><span class="red">*</span>사용자 이름</td>
     <td><input type ="text" name="username" id ="username"/></td>
   </tr>
   <tr>
     <td>이메일</td>
     <td><input type ="email" name="email"/></td>
   </tr>
   <tr id="last">
     <td colspan="2">
     <input type ="submit" value="회원가입" id="goSum"/>
     <input type ="button" value="목록" id="goList"/>
     </td>
   </tr>
 </table>


</form>
</main>
<script>
    // 리스트 버튼 이동
      const goList = document.getElementById('goList');
      goList.onclick= function() {
	location.href = '/Users/List'; }
	
      
   //변수 정리
 	const dupCheckEl = document.getElementById('dupCheck');
 	const dupChec2kEl = document.getElementById('dupCheck2');
	const formEl = document.querySelector('form');
 	const useridEL =  document.getElementById('userid');
	const passwd1EL =  document.getElementById('passwd1');
 	const passwd2EL =  document.getElementById('passwd2');
 	const usernameEl =  document.getElementById('username');
 	 let dupCheckClicked = false; 
 	

  
  
 	//중복 - 새창 열기
 	dupChec2kEl.onclick =function() {
 		//새창을 연다
 		let html = '/Users/DupCheck?userid=' + useridEL.value; //새창에 보여줄 html
 		let name = 'DupCheck'; // 이름을 부여해 클릭을 여러번해도 창이 한번만 나오도록 한다
 		let features = 'height=500,width=500'
 		window.open(html,name,features)
 		
 	}		
 	
 	


   
 	// 홈페이지에서 거르기
	formEl.onsubmit =function() {
 		
 		let pwd1 = passwd1EL.value;
 		let pwd2 = passwd2EL.value;
 				
 		//ID 비밀번호 사용자 이름 입력 필수
 		if(useridEL.value.trim() == '') {
 			alert('아이디를 입력하세요')
 			useridEL.focus()
 			return false;
 		}else if(!pwd1) {
 			alert('비밀번호를 입력하세요')
 			passwd1EL.focus()
 			return false;
 		}else if(!pwd2) {
 			alert('비밀번호확인을 입력하세요')
 			passwd2EL.focus()
 			return false;
 		}else if(pwd1 !== pwd2){
 			alert('비밀번호가 일치하지 않습니다')
 			return false; 			
 		}else if(usernameEl.value.trim().length < 2) {
 			usernameEl.focus()
 			alert('이름을 2자 이상입력하세요')
 			return false;
 		}else if(dupCheckClicked == false ){
 			alert('중복확인이 필요합니다')
 			console.log(dupCheckClicked)
 			return false;
 		}else{
 			
 			return true;
 		}

		
	}        
      
</script>
<script>
$(function(){
     // 중복 - 홈페이지에서나타나기
	$('#dupCheck').on('click',function(){

		$.ajax({
			url:'/Users/IdDupCheck',
			data:{userid: $('[name=userid]').val()}
		}).done(function(data){
			//alert(data)
			//console.log(data.userid)
			/*
			if(data == '') {
				alert('사용가능한 아이디입니다')
			}else {
				alert('사용할 수 없는 아이디입니다')
			}
			*/
			/*XHL(XMLTttpReauest) $ajax fetch */
			if(data == ''){
				let html = '<br>사용가능한 아이디 입니다'
				$('#dupResult').html(html).removeClass('red').addClass('green');
				dupCheckClicked = true;  
				
			
				console.log(dupCheckClicked)
			}else {
				let html = '<br>사용 불가능한 아이디 입니다'
					$('#dupResult').html(html).removeClass('green').addClass('red');
				dupCheckClicked = false;  
		
				
				
			}
			
			
		}).fail(function(err){
			console.log(err)
			dupCheckClicked = false; 
		})
		
	})

	
})


</script>
<script>


</script>

</body>
</html>