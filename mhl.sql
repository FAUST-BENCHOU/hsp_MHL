CREATE DATABASE mhl;
CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	empId VARCHAR(50) NOT NULL DEFAULT '',
	pwd CHAR(32) NOT NULL DEFAULT '',
	`name` VARCHAR(50) NOT NULL DEFAULT '',
	job VARCHAR(50) NOT NULL DEFAULT ''
)CHARSET=utf8;

INSERT INTO employee VALUES(NULL,'661',MD5('123456'),'sarry','manager');
INSERT INTO employee VALUES(NULL,'662',MD5('123456'),'ben','server');
INSERT INTO employee VALUES(NULL,'663',MD5('123456'),'john','cashier');
INSERT INTO employee VALUES(NULL,'664',MD5('123456'),'hans','manager');

CREATE TABLE dinningtable(
	id INT PRIMARY KEY AUTO_INCREMENT,
	state VARCHAR(20) NOT NULL DEFAULT '', # 餐桌的状态
	orderName VARCHAR(50) NOT NULL DEFAULT '', # 预定人的名字
	orderTel VARCHAR(20) NOT NULL DEFAULT '' # 预定人的手机号
)CHARSET=utf8;
INSERT INTO dinningtable VALUES(NULL,'empty','','');
INSERT INTO dinningtable VALUES(NULL,'empty','','');
INSERT INTO dinningtable VALUES(NULL,'empty','','');
DELETE FROM dinningtable WHERE id IN (1,2,3);
SELECT * FROM dinningtable
DROP TABLE dinningtable
UPDATE dinningtable SET state='reserved',orderName='22',orderTel='22' WHERE id = 2
UPDATE dinningtable SET state = 'empty',orderName='',orderTel='' WHERE id = 1


CREATE TABLE menu(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL DEFAULT '', 
	`type` VARCHAR(50) NOT NULL DEFAULT '', 
	price DOUBLE  
)CHARSET=utf8;
INSERT INTO menu VALUES(NULL,'fish','staple',10.0);
INSERT INTO menu VALUES(NULL,'beef','staple',20.0);
INSERT INTO menu VALUES(NULL,'rice','staple',2.0);
INSERT INTO menu VALUES(NULL,'pork','soup',5.0);
INSERT INTO menu VALUES(NULL,'cake','dessert',40.0);
INSERT INTO menu VALUES(NULL,'pudding','dessert',30.0);
SELECT * FROM menu
DROP TABLE menu
SELECT price FROM menu WHERE id=1
CREATE TABLE bill(
		id INT PRIMARY KEY AUTO_INCREMENT, # 自增主键
		billId VARCHAR(50) NOT NULL DEFAULT '', # 账单号可以按自己的规则生成 uuid
		menuId INT NOT NULL DEFAULT 0, # 菜品的编号，也可以使用外键
		nums INT NOT NULL DEFAULT 0, # 份数
		money DOUBLE NOT NULL DEFAULT 0, # 金额
		diningTableId INT NOT NULL DEFAULT 0, # 餐桌
		billDate DATETIME NOT NULL, # 订单日期
		state VARCHAR(24) NOT NULL DEFAULT '' # 状态 '未结账' '已经结账' '挂单' '现金' '支付宝' '坏账'
)CHARSET=utf8;

SELECT bill.*,menu.`name`,menu.`price` FROM bill,menu 
		WHERE bill.`id`=menu.`id`AND
		bill.`diningTableId`=1
SELECT * FROM bill WHERE diningTableId =2
