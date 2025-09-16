DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
 employee_id INT PRIMARY KEY AUTO_INCREMENT,
 employee_name VARCHAR(50)NOT NULL,
 age INT NOT NULL,
 passwords VARCHAR(50)NULL,
 start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
 end_date TIMESTAMP DEFAULT NULL
);

INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1001','佐藤 健','34','Ab12Cd34','2015-04-01',NULL);
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1002','鈴木 美咲','29','Xy89Zt56','2018-07-15','2024-12-31');
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1003','高橋 翔','41','Mn56Ab12','2010-10-01',NULL);
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1004','田中 花子','26','Qw12Er34','2022-03-01',NULL);
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1005','伊藤 大輔','38','Rt67Yp90','2012-06-20','2020-09-30');
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1006','渡辺 真由','31','Gt45Ui78','2017-01-10',NULL);
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1007','山本 拓海','45','Jk98Op76','2005-09-01','2019-04-15');
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1008','中村 優子','33','Zx34Qw12','2016-12-01',NULL);
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1009','小林 陽斗','27','Bn65Rt43','2021-05-25',NULL);
INSERT INTO employee 
(employee_id,employee_name,age,passwords,start_date,end_date)VALUES
('1010','加藤 綾','30','Lm09Yt34','2019-11-11','2023-03-31');
