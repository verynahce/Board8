CREATE  FUNCTION   GET_FILENUM RETURN  NUMBER
AS  
  V_NUM  NUMBER(10);
BEGIN
  SELECT
     ( SELECT NVL(MAX(FILE_NUM), 0)+1 FROM FILES)
   INTO 
     V_NUM
  FROM DUAL;
  
  RETURN  V_NUM; 
END;
/

DROP FUNCTION GET_FILENUM;



