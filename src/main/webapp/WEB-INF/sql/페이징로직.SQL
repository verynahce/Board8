 [1] 로직 
 총 갯수 269 개 
 TOTALPAGECOUNT 총 페이지 수 27
 
PAGECOUNT : 페이지 당 10개 씩
 NOWPAGE  : 현재 3 페이지
 
 1페이지 1- 10
 2페이지 11- 20
 3페이지 21- 30
 
 STARTNUM := 21; ( NOWPAGE -1) *PAGECOUNT +1
 ENDNUM := 30; NOUWPAGE * PAGECOUNT

[2] 화면에 뿌릴때 

           12345678910          다음 마지막 (1-10)
처음 이전 11121314151617181920 다음 마지막  (11-20)
처음 이전 21222324252627                     (21-27)
PPC PAGEPAERCOUNT = 하나의 뷰페이지에 페이지 10개 씩 뿌린다

/// 이거 확실한거 아님
PAGESTRARTNUM = (NP-1) /PPC *PTC+1 
PAGEENDNUM   = ((NP-1)/PPC+1) *PTC

[3] 수 구하기
SELECT * FROM (
SELECT ROW_NUMBER() OVER (ORDER BY SEQ ASC) AS 줄번호, ZIPCODE, SEQ
FROM POST)T
WHERE 줄번호 BETWEEN STARTNUM AND ENDNUM; 
  
SELECT * FROM POST;