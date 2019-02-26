USE yutao;
/*
drop table CAR;
drop table SALESPERSON;
drop table DEALER;
drop view  CUSTOMERVIEW;
drop procedure MYPRO;*/


CREATE TABLE DEALER
	(Dname		varchar(20) NOT NULL,
     Address    varchar(30) NOT NULL,
     Dnum       char(2)     NOT NULL,
     PRIMARY KEY(Dnum)
     );
INSERT INTO DEALER VALUES('Apple','Windsor street','01'); 
INSERT INTO DEALER VALUES('Banana','Alan street','02'); 
INSERT INTO DEALER VALUES('Cherry','Coburg street','03'); 
INSERT INTO DEALER VALUES('Dust','South street','04'); 
INSERT INTO DEALER VALUES('Eel','Walnut street','05'); 
INSERT INTO DEALER VALUES('Fear','Larch street','06'); 
INSERT INTO DEALER VALUES('Goog','Summer street','07'); 
INSERT INTO DEALER VALUES('Happy','Clyde street','08'); 
INSERT INTO DEALER VALUES('Institution','Market street','09'); 
INSERT INTO DEALER VALUES('Journey','Barrington street','10'); 

CREATE TABLE SALESPERSON
	(Emp_ID		char(6)		NOT NULL,
     Sex		varchar(6)  NOT NULL,
     Fname		varchar(15)  NOT NULL,
     Lname      varchar(15)  NOT NULL,
     Dnum       char(2)      NOT NULL,
     PRIMARY KEY(Emp_ID),
     FOREIGN KEY(Dnum) REFERENCES DEALER(Dnum)
     );
INSERT INTO SALESPERSON VALUES('000001','MALE','Billy','Wang','01');
INSERT INTO SALESPERSON VALUES('000002','MALE','YUTAO','Xu','02');
INSERT INTO SALESPERSON VALUES('000003','MALE','Richard','Mike','03');
INSERT INTO SALESPERSON VALUES('000004','MALE','Billy','Tom','04');
INSERT INTO SALESPERSON VALUES('000005','FEMALE','Amy','Wang','05');
INSERT INTO SALESPERSON VALUES('000006','MALE','Aron','Li','06');
INSERT INTO SALESPERSON VALUES('000007','MALE','Abel','Li','07');
INSERT INTO SALESPERSON VALUES('000008','MALE','James','Lebron','08');
INSERT INTO SALESPERSON VALUES('000009','FEMALE','Abby','Zhang','09');
INSERT INTO SALESPERSON VALUES('000010','FEMALE','Angela','Book','10');
INSERT INTO SALESPERSON VALUES('000011','FEMALE','Sara','Good','05');


    
     
CREATE TABLE CAR
     (Cid       char(8)      NOT NULL,
      Make      varchar(8)   NOT NULL,
      Colour    varchar(9)   NOT NULL,
      Dnum      char(2)      	 NOT NULL,
      Emp_ID    char(6),
      PRIMARY KEY(Cid),
      FOREIGN KEY(Dnum) REFERENCES DEALER(Dnum),
      FOREIGN KEY(Emp_ID) REFERENCES SALESPERSON(Emp_ID)
      
      );
INSERT INTO CAR VALUES('00000001','Ford','white','01','000001');     
INSERT INTO CAR VALUES('00000002','Ford','blue','02','000001'); 
INSERT INTO CAR VALUES('00000003','Ford','yellow','03',NULL); 
INSERT INTO CAR VALUES('00000004','Ford','blue','04',NULL); 
INSERT INTO CAR VALUES('00000005','Lincoln','black','05','000001'); 
INSERT INTO CAR VALUES('00000006','Lincoln','blue','06',NULL); 
INSERT INTO CAR VALUES('00000007','Ford','white','07','000004'); 
INSERT INTO CAR VALUES('00000008','Lincoln','black','08','000006'); 
INSERT INTO CAR VALUES('00000009','Lincoln','gray','09',NULL); 
INSERT INTO CAR VALUES('00000010','Ford','black','10','000003'); 
INSERT INTO CAR VALUES('00000011','Lincoln','blue','01',NULL); 
INSERT INTO CAR VALUES('00000012','Ford','white','02','000004');  

/*DELETE Query 
delete a car according an accurate id*/
DELETE FROM CAR WHERE Cid='00000005';

/*update query
update a car's infomation according to an accruate id*/
UPDATE CAR
SET Emp_ID='000001'
WHERE Cid='0000006';

/*
Display all the cars in stock*/

SELECT 	Cid, Make, Colour,Address, Dname, CAR.Emp_ID
FROM DEALER 
INNER JOIN CAR on CAR.Dnum=DEALER.Dnum

;



/*USE LEFT JOIN COUNT, GROUP BY*/
/*Display all the employee who works for different dealer, and display the num of cars which a employee has been sold*/
SELECT Fname, Lname, Sex, COUNT(CAR.Emp_ID) AS numOfcars
FROM SALESPERSON
LEFT JOIN CAR 
ON SALESPERSON.Emp_ID=CAR.Emp_ID
GROUP BY SALESPERSON.Emp_ID
ORDER BY  COUNT(CAR.Emp_ID) DESC;


/*
Display a employee and display the car he(she) sold*/


SELECT  Fname,Lname,SALESPERSON.Emp_ID,Cid,Make,Colour
FROM SALESPERSON
INNER JOIN CAR ON SALESPERSON.Emp_ID=CAR.Emp_ID
WHERE SALESPERSON.Emp_ID='000001'
;

/*view query
This view can only display selected infomation which hide some inmportant infomation*/
CREATE VIEW CUSTOMERVIEW AS 
	SELECT Make,Colour,Address
    FROM CAR,DEALER
    WHERE CAR.Dnum=DEALER.Dnum;
/*print the view table*/
SELECT *
FROM CUSTOMERVIEW;
/*CREATE A TRIGGER*/
DELIMITER //
CREATE TRIGGER MagicTRI AFTER UPDATE ON CAR
FOR EACH ROW
BEGIN
UPDATE CAR
	SET Colour="red"
    WHERE (Colour="White");
END;//
/*CREATE A PROCEDURE*/
CREATE PROCEDURE MYPRO()
BEGIN
SELECT *
FROM DEALER;
END;
CALL MYPRO();