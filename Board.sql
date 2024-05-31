drop table if exists board;

create table if not exists board (
num int primary key auto_increment,
author varchar(100) not null,
email varchar(200) not null,
title varchar(500) not null,
content varchar(4000),
passwd varchar(12),
writeday date default current_date(), 	-- 기본 입력 값이 현재 날짜
readcnt int default 0,					-- 기본 입력값이 0
rep_root int default 0,
rep_step int default 0,
rep_indent int default 0
);

-- author,email,title,content,passwd 외에 
-- default로 설정 된 필드는 기본값으로 사용
INSERT INTO BOARD(author,email,title,content,passwd)
VALUES('test','test@test.com','test title','test content','12345');

INSERT INTO BOARD(author,email,title,content,passwd)
VALUES('test2','test2@test.com','test title2','test content2','11111');

UPDATE BOARD SET readcnt=(SELECT readcnt from BOARD WHERE num=2)+1 WHERE num=2;

UPDATE BOARD SET author='HONG', email='hong@test.com', title='홍길동이 쓴 글'  WHERE num=2;

SELECT * FROM BOARD;

SELECT * FROM BOARD ORDER BY NUM DESC;

SELECT * FROM BOARD WHERE NUM=2;

commit
