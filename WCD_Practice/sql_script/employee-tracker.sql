
DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

LOCK TABLES `employee` WRITE;

INSERT INTO `employee` VALUES (1,'Nguyen Trung Anh','31/08/1998','Ha Noi', 'Giam doc', 'Dieu hanh'),
                             (2,'Pham Quoc Phuong','28/02/1998','Ha Noi', 'Truong phong', 'Nhan su'),
                             (3,'Tran Dang Hai','25/05/1998','Thai Binh', 'Nhan vien', 'Marketing');

UNLOCK TABLES;

