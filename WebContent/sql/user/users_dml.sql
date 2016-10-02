Update users set grade=‘B’ where u_id = ‘park’
;

Insert into users values(‘park’,’1234’,’박연아’,’W’,’24’,’01067873131’,’5910342@naver.com’,’null’,’/images/my.jpg’,’G’);
;

Select name, grade from users where u_id =‘park’ and u_pw=‘1234’
;

Update users set name=‘박연아’ , mobile=‘01012341234’, email=‘0000@naver.com’,’/images/mymy.jpg’) where u_id = ‘park’
;

Update users set u_pw=‘123’ where u_id=‘park’ and u_pw=‘1234’
;

Select * from users where u_id=‘park’
;

Update users set grade =‘X’ where u_id =‘park’ and u_pw=‘1234’
;

Select * from users
;

Select * from users where grade=‘B’
;

Select * from users where u_id = ‘park’
;

Update users set grade=‘G’ from users
;

update users set name=‘박연아’, email=‘5910342@naver.com’ , gender=‘W’, age=‘24’, mobile=‘01067873131’, grade=‘G’ where u_id=‘park’
;

Delete from users where u_id=‘park’ and u_pw = ‘1234’
;

Update users set grade=‘B’ where u_id=‘park’
;

Update users set grade=‘G’ where u_id = ‘park’
;
