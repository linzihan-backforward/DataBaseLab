# Host: 127.0.0.1  (Version 5.7.22-log)
# Date: 2018-06-29 14:57:45
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "bookborrow"
#

DROP TABLE IF EXISTS `bookborrow`;
CREATE TABLE `bookborrow` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书借阅ID',
  `userID` varchar(20) NOT NULL DEFAULT '' COMMENT '用户ID',
  `bookID` int(11) NOT NULL COMMENT '图书ID',
  `borrowtime` datetime DEFAULT NULL COMMENT '借书时间',
  `overtime` datetime DEFAULT NULL COMMENT '约定最晚归还时间',
  `returnedtime` datetime DEFAULT NULL COMMENT '实际归还时间',
  `ifreturned` tinyint(1) DEFAULT '0' COMMENT '是否归还',
  `renewtime` int(11) DEFAULT '0' COMMENT '续借次数',
  `borrowstate` int(11) DEFAULT NULL COMMENT '借阅状态',
  PRIMARY KEY (`ID`),
  KEY `userID` (`userID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `bookborrow_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`),
  CONSTRAINT `bookborrow_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

#
# Data for table "bookborrow"
#

INSERT INTO `bookborrow` VALUES (1,'A0101',1001,'2018-05-01 00:00:00','2018-08-01 00:00:00','2018-06-01 00:00:00',1,0,3),(2,'A0101',1011,'2018-05-01 00:00:00','2018-06-01 00:00:00',NULL,0,0,1),(3,'A0101',1017,'2018-03-01 00:00:00','2018-07-30 08:12:30','2018-06-19 08:35:05',1,2,3),(4,'A0101',1002,'2018-03-01 00:00:00','2018-05-02 00:00:00','2018-06-04 00:00:00',1,0,3),(5,'B0101',1003,'2018-05-08 00:00:00','2018-06-23 00:00:00','2018-06-01 00:00:00',1,0,3),(8,'A0101',1004,'2018-05-31 00:00:00','2018-06-30 00:00:00','2018-06-01 00:00:00',1,0,3),(9,'A0101',1005,'2018-05-31 00:00:00','2018-06-30 00:00:00','2018-06-01 00:00:00',1,0,3),(15,'A0101',1006,'2018-05-31 00:00:00','2018-06-30 00:00:00','2018-06-01 00:00:00',1,0,3),(16,'A0101',1007,'2018-05-31 00:00:00','2018-06-30 00:00:00','2018-06-01 00:00:00',1,0,3),(17,'A0101',1008,'2018-05-31 00:00:00','2018-06-30 00:00:00','2018-06-01 00:00:00',1,0,3),(18,'A0101',1009,'2018-05-31 00:00:00','2018-06-30 00:00:00','2018-06-01 00:00:00',1,0,3),(20,'B0101',1011,'2018-06-01 00:00:00','2018-07-31 00:00:00','2018-06-01 00:00:00',1,0,3),(21,'A0101',1010,'2018-06-01 00:00:00','2018-06-04 12:11:00','2018-06-04 12:37:30',1,0,3),(22,'A0101',1002,'2018-06-01 00:00:00','2018-07-01 00:00:00','2018-06-04 00:00:00',1,0,3),(27,'B0101',1001,'2018-06-03 00:00:00','2018-08-02 00:00:00','2018-06-04 00:00:00',1,0,3),(28,'B0101',1013,'2018-06-03 00:00:00','2018-08-02 00:00:00',NULL,0,0,0),(29,'C0101',3000,'2018-04-18 19:00:38','2018-06-15 19:00:38','2018-06-19 08:34:14',1,0,3),(30,'A0101',1014,'2018-06-19 08:33:44','2018-07-19 08:33:44',NULL,0,0,0);

#
# Structure for table "bookcategory"
#

DROP TABLE IF EXISTS `bookcategory`;
CREATE TABLE `bookcategory` (
  `ID` int(11) NOT NULL COMMENT '图书类别ID',
  `title` varchar(72) DEFAULT NULL COMMENT '图书类别名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "bookcategory"
#

INSERT INTO `bookcategory` VALUES (2,'古诗书'),(3,'学习辅导书'),(5,'程序设计'),(7,'金融经济学'),(8,'政治'),(9,'心灵鸡汤');

#
# Structure for table "booklist"
#

DROP TABLE IF EXISTS `booklist`;
CREATE TABLE `booklist` (
  `isbn` varchar(20) NOT NULL COMMENT '图书ISBN',
  `title` varchar(72) DEFAULT NULL COMMENT '图书名称',
  `category_ID` int(11) NOT NULL COMMENT '图书类型',
  `author` varchar(72) DEFAULT NULL COMMENT '作者',
  `press` varchar(72) DEFAULT NULL COMMENT '出版社',
  `publishday` date DEFAULT NULL COMMENT '出版时间',
  `pages` int(11) DEFAULT NULL COMMENT '页数',
  `price` decimal(18,2) DEFAULT NULL COMMENT '单价',
  `intro` varchar(72) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`isbn`),
  KEY `catagory_ID` (`category_ID`),
  CONSTRAINT `booklist_ibfk_1` FOREIGN KEY (`category_ID`) REFERENCES `bookcategory` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "booklist"
#

INSERT INTO `booklist` VALUES ('7-101-02858-6','红楼梦诗词曲赋鉴赏 ',2,'\r\n蔡义江','中华书局出版社 ','2001-10-01',558,38.00,NULL),('7-102-03453-8','世界政治',8,'小王','中华出版社','2018-04-30',230,18.00,NULL),('7-110-05742-2','微观经济学',7,'小芳','清华大学出版社','2010-04-05',338,35.30,NULL),('7-111-07886-1','数据库系统导论',5,'孟小峰 王珊','机械工业出版社','2001-06-01',670,68.00,''),('7-560-05856-6','高考英语总复习',3,'\r\n陈琳','\r\n外语教学与研究出版社','2007-02-01',320,25.90,NULL),('7-560-05866-6','高考数学总复习',3,'马山','外语教学出版社','2017-02-01',412,32.80,NULL);

#
# Structure for table "bookstate"
#

DROP TABLE IF EXISTS `bookstate`;
CREATE TABLE `bookstate` (
  `ID` int(11) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "bookstate"
#

INSERT INTO `bookstate` VALUES (1,'可借阅'),(2,'已借出'),(3,'已下架');

#
# Structure for table "book"
#

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `ID` int(11) NOT NULL COMMENT '图书ID',
  `isbn` varchar(20) DEFAULT NULL COMMENT '图书ISBN',
  `title` varchar(72) DEFAULT NULL COMMENT '图书名称',
  `state_ID` int(11) NOT NULL DEFAULT '2' COMMENT '状态',
  PRIMARY KEY (`ID`),
  KEY `state_ID` (`state_ID`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`state_ID`) REFERENCES `bookstate` (`ID`),
  CONSTRAINT `book_ibfk_3` FOREIGN KEY (`isbn`) REFERENCES `booklist` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每一本图书的信息表';

#
# Data for table "book"
#

INSERT INTO `book` VALUES (1001,'7-111-07886-1','数据库系统导论',1),(1002,'7-111-07886-1','数据库系统导论',1),(1003,'7-111-07886-1','数据库系统导论',1),(1004,'7-111-07886-1','数据库系统导论',1),(1005,'7-111-07886-1','数据库系统导论',1),(1006,'7-111-07886-1','数据库系统导论',1),(1007,'7-111-07886-1','数据库系统导论',1),(1008,'7-111-07886-1','数据库系统导论',1),(1009,'7-111-07886-1','数据库系统导论',1),(1010,'7-111-07886-1','数据库系统导论',1),(1011,'7-560-05856-6','高考英语总复习',2),(1012,'7-560-05856-6','高考英语总复习',1),(1013,'7-101-02858-6','红楼梦诗词曲赋鉴赏 ',2),(1014,'7-101-02858-6','红楼梦诗词曲赋鉴赏 ',2),(1015,'7-101-02858-6','红楼梦诗词曲赋鉴赏 ',1),(1016,'7-101-02858-6','红楼梦诗词曲赋鉴赏 ',1),(1017,'7-101-02858-6','红楼梦诗词曲赋鉴赏 ',1),(2000,'7-101-02858-6','红楼梦诗词曲赋鉴赏 ',1),(2001,'7-111-07886-1','数据库系统导论',1),(2002,'7-111-07886-1','数据库系统导论',3),(3000,'7-110-05742-2','微观经济学',1),(3002,'7-110-05742-2','微观经济学',1),(4000,'7-102-03453-8','世界政治',3);

#
# Structure for table "library"
#

DROP TABLE IF EXISTS `library`;
CREATE TABLE `library` (
  `ID` int(11) NOT NULL COMMENT '馆区ID',
  `name` varchar(72) DEFAULT NULL COMMENT '馆区名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "library"
#

INSERT INTO `library` VALUES (1,'A馆'),(2,'B馆'),(4,'C馆');

#
# Structure for table "bookstock"
#

DROP TABLE IF EXISTS `bookstock`;
CREATE TABLE `bookstock` (
  `isbn` varchar(20) NOT NULL COMMENT '图书ISBN',
  `title` varchar(72) DEFAULT NULL COMMENT '图书名称',
  `location_ID` int(11) DEFAULT NULL COMMENT '图书馆',
  `amount` int(11) DEFAULT NULL COMMENT '图书总数',
  `entryday` date DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`isbn`),
  KEY `location_ID` (`location_ID`),
  CONSTRAINT `bookstock_ibfk_1` FOREIGN KEY (`location_ID`) REFERENCES `library` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "bookstock"
#

INSERT INTO `bookstock` VALUES ('7-101-02858-6','红楼梦诗词曲赋鉴赏 ',1,5,'2018-03-20'),('7-111-07886-1','数据库系统导论',2,10,'2018-05-21'),('7-560-05856-6','高考英语总复习',4,2,'2018-05-22');

#
# Structure for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `rolename` varchar(72) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(72) DEFAULT NULL COMMENT '备注',
  `ifmanager` tinyint(1) NOT NULL COMMENT '是否管理员',
  `checkoutperiod` int(11) DEFAULT NULL COMMENT '借阅时长',
  `checkoutamount` int(11) DEFAULT NULL COMMENT '借阅数量',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,'ROLE_ADMIN','超级用户',1,60,9999),(2,'ROLE_Reader','普通读者',0,30,10),(3,'ROLE_Reader_VIP','特权读者',0,30,100);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(20) NOT NULL COMMENT '用户ID',
  `name` varchar(72) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(72) DEFAULT NULL COMMENT '密码',
  `readerID` varchar(20) DEFAULT NULL COMMENT '借书证号',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别',
  `Email` varchar(72) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(72) DEFAULT NULL COMMENT '手机号',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态，包括可借阅、有逾期、有欠款等',
  `checkoutamount` int(11) DEFAULT NULL COMMENT '借阅数量',
  `roleID` int(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`ID`),
  KEY `roleID` (`roleID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "user"
#

INSERT INTO `user` VALUES ('A0101','jack','{noop}1','A0101',1,'ABC@sina.com','13913988888',0,10,2),('B0101','宋江','{bcrypt}$2a$10$Eluta9UIZfukX/0p9Kx4hucevWNNqpW4GQTeymIB1LG6/5ggq8r/S','B1000',0,'CBC@QQ.com','18989888888',0,100,1),('C0101','李明','{noop}123456','C0101',0,'xyz@gmail.com','15812313414',0,4,2);

#
# Structure for table "fine"
#

DROP TABLE IF EXISTS `fine`;
CREATE TABLE `fine` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '罚款ID',
  `userID` varchar(20) NOT NULL COMMENT '用户ID',
  `amount` decimal(18,2) DEFAULT NULL COMMENT '罚款金额',
  `finetype` int(11) DEFAULT NULL COMMENT '罚款类型',
  `ispayed` tinyint(1) DEFAULT NULL COMMENT '罚款是否已经缴纳完成',
  `payeddate` date DEFAULT NULL COMMENT '罚款缴纳时间',
  PRIMARY KEY (`ID`),
  KEY `userID` (`userID`),
  CONSTRAINT `fine_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='罚款主表';

#
# Data for table "fine"
#

INSERT INTO `fine` VALUES (100,'A0101',30.00,2,1,'2018-05-23'),(101,'C0101',20.00,1,0,NULL),(102,'C0101',3.00,1,1,'2018-06-19'),(103,'A0101',20.00,1,0,NULL);

#
# Structure for table "fineitem"
#

DROP TABLE IF EXISTS `fineitem`;
CREATE TABLE `fineitem` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '罚款明细ID',
  `fineID` int(11) NOT NULL COMMENT '罚款ID',
  `finetype` int(11) NOT NULL COMMENT '罚款类型',
  `borrowovertimeID` int(11) DEFAULT '0' COMMENT '图书超期ID',
  `booklostID` int(11) DEFAULT '0' COMMENT '图书丢失ID',
  `bookdamagedID` int(11) DEFAULT '0' COMMENT '图书损坏ID',
  PRIMARY KEY (`ID`),
  KEY `fineID` (`fineID`),
  CONSTRAINT `fineitem_ibfk_1` FOREIGN KEY (`fineID`) REFERENCES `fine` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='罚款明细表';

#
# Data for table "fineitem"
#

INSERT INTO `fineitem` VALUES (1,100,2,0,2,0),(2,100,2,0,1,0),(3,101,1,29,0,0),(4,102,1,29,0,0),(5,103,1,2,0,0);

#
# Structure for table "borrowovertime"
#

DROP TABLE IF EXISTS `borrowovertime`;
CREATE TABLE `borrowovertime` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书超期ID',
  `bookID` int(11) NOT NULL COMMENT '图书ID',
  `userID` varchar(20) NOT NULL COMMENT '用户ID',
  `overdays` int(11) DEFAULT NULL COMMENT '超期天数',
  `borrowID` int(11) NOT NULL COMMENT '借阅记录的ID',
  PRIMARY KEY (`ID`),
  KEY `userID` (`userID`),
  KEY `borrowID` (`borrowID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `borrowovertime_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`),
  CONSTRAINT `borrowovertime_ibfk_3` FOREIGN KEY (`borrowID`) REFERENCES `bookborrow` (`ID`),
  CONSTRAINT `borrowovertime_ibfk_4` FOREIGN KEY (`bookID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书借阅超期';

#
# Data for table "borrowovertime"
#


#
# Structure for table "booklost"
#

DROP TABLE IF EXISTS `booklost`;
CREATE TABLE `booklost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '遗失记录ID',
  `userID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `bookID` int(11) DEFAULT NULL COMMENT '图书ID',
  `lostday` date DEFAULT NULL COMMENT '遗失时间',
  `borrowID` int(11) DEFAULT NULL COMMENT '借阅记录的ID',
  `type` int(11) DEFAULT NULL COMMENT '遗失类型',
  PRIMARY KEY (`id`),
  KEY `userID` (`userID`),
  KEY `bookID` (`bookID`),
  KEY `borrowID` (`borrowID`),
  CONSTRAINT `booklost_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`),
  CONSTRAINT `booklost_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`ID`),
  CONSTRAINT `booklost_ibfk_3` FOREIGN KEY (`borrowID`) REFERENCES `bookborrow` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "booklost"
#

INSERT INTO `booklost` VALUES (1,'A0101',1011,'2018-05-22',2,0),(2,'A0101',1001,'2018-05-21',1,0);
