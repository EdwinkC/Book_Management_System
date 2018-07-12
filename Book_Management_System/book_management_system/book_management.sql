/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : book_management

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/07/2018 16:13:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `Bno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Bname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Bautor` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Btype` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Bcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Bcount` smallint(6) NULL DEFAULT NULL,
  PRIMARY KEY (`Bno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1001010', '《史记》人生艺术', '张学成', '人文社科类', '本书讲析了司马迁的人生，里面有凶险的政治，复杂的商场战场，微妙的人际关系，还有事业的成败，有洋洋大观的官场文化。', 10);
INSERT INTO `book` VALUES ('1001011', '西游记', '吴承恩', '人文社科类', '《西游记》为明代小说家吴承恩所著。取材于《大唐西域记》和民间传说、元杂剧。宋代《大唐三藏取经诗话》（本名《大唐三藏取经记》）是西游记故事见于说话文字的最早雏形，其中，唐僧就是以玄奘法师为原型的。', 9);
INSERT INTO `book` VALUES ('1001012', '红楼梦', '曹雪芹', '人文社科类', '《红楼梦》，中国古典四大名著之首，清代作家曹雪芹创作的章回体长篇小说，又名《石头记》《金玉缘》。此书分为120回“程本”和80回“脂本”两种版本系统。', 9);
INSERT INTO `book` VALUES ('3001010', '园艺百科全书', '	克里斯托夫.布里克尔', '农林类', '园艺百科全书是一本享有极高国际声誉的园艺百科全书，共分为两部分：“营建花园”和“养护花园”，第一部分主要是园艺规划和种植栽培；第二部分教你如何选择种植设备和材料，把植物置于合适的环境中，在进行花园维护时所需要的工具，以及如何快速判定疾病征兆并控制上百种昆虫。', 9);
INSERT INTO `book` VALUES ('4001010', '梦的解析', '	西格蒙德·弗洛伊德', '心理学类', '该书开创了弗洛伊德的“梦的解析”理论，被作者本人描述为“理解潜意识心理过程的捷径。”该书引入了本我概念，描述了弗洛伊德的潜意识理论，用于解释梦。弗洛伊德在此首次讨论了后来发展的恋母情结理论。', 10);
INSERT INTO `book` VALUES ('5001010', '数据结构', '严蔚敏', '计算机类', '数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。通常情况下，精心选择的数据结构可以带来更高的运行或者存储效率。数据结构往往同高效的检索算法和索引技术有关。', 10);
INSERT INTO `book` VALUES ('5001011', 'JavaEE开发', '郭克华', '计算机类', 'JavaEE企业级开发，轻量级框架开发MVC和Struts。', 10);
INSERT INTO `book` VALUES ('5001012', '计算机组成原理', '唐朔飞', '计算机类', '介绍计算机的基本组成以及计算机的发展应用和展望，使读者对计算机的总体概貌有进一步的了解。', 10);
INSERT INTO `book` VALUES ('5001013', '需求分析', '骆斌', '计算机类', '需求分析也称为软件需求分析、系统需求分析或需求分析工程等，是开发人员经过深入细致的调研和分析，准确理解用户和项目的功能、性能、可靠性等具体要求，将用户非形式的需求表述转化为完整的需求定义，从而确定系统必须做什么的过程。', 10);
INSERT INTO `book` VALUES ('5001014', '操作系统', '张尧学', '计算机类', '操作系统（Operating System，简称OS）是管理和控制计算机硬件与软件资源的计算机程序，是直接运行在“裸机”上的最基本的系统软件，任何其他软件都必须在操作系统的支持下才能运行。', 10);
INSERT INTO `book` VALUES ('5001015', '设计模式', '刘伟', '计算机类', '设计模式（Design Pattern）是一套被反复使用、多数人知晓的、经过分类的、代码设计经验的总结。使用设计模式的目的：为了代码可重用性、让代码更容易被他人理解、保证代码可靠性。 设计模式使代码编写真正工程化；设计模式是软件工程的基石脉络，如同大厦的结构一样。', 10);
INSERT INTO `book` VALUES ('5001016', 'UML建模', '谢星星', '计算机类', 'UML是面向对象软件的标准化建模语言。UML因其简单、统一的特点，而且能表达软件设计中的动态和静态信息，目前已成为可视化建模语言的工业标准。在软件无线电系统的开发过程中，统一建模语言可以在整个设计周期中使用，帮助设计者缩短设计时间，减少改进的成本，使软硬件分割最优。', 10);
INSERT INTO `book` VALUES ('5001017', 'Java程序设计', '辛运帏', '计算机类', '通过对Java编程语言的全面介绍，引导读者快速地掌握Java编程语言的核心内容并学会灵活运用所学的语言知识及面向对象的编程思想。《Java程序设计》共分9章，内容包括Java语言概述、面向对象编程初步、Java的基本语法、类库与数组、面向对象编程深入、Applet程序、图形用户界面编程、异常处理和输入输出，以及多线程编程。', 10);
INSERT INTO `book` VALUES ('bc001', '豌豆笑传', '匿名', '漫画类', '父与子类型的漫画，笑料爆棚。', 10);
INSERT INTO `book` VALUES ('bc002', '阿衰', '猫小乐', '漫画类', '幽默风趣，笑料不断。', 10);
INSERT INTO `book` VALUES ('cs003', '斗罗大陆', '唐家三少', '玄幻类', '唐门一日，暴雨梨花针。', 10);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `Uno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Bno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BorrowDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Uno`, `Bno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('20181001', '1001011', '2018-07-11 18:15:09');
INSERT INTO `borrow` VALUES ('20181001', '1001012', '2018-07-11 18:38:10');
INSERT INTO `borrow` VALUES ('20181001', '3001010', '2018-07-11 18:38:21');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `Uno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Bname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `OrderDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Uno`, `Bname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('20181001', '爱的教育', '2018-07-12 11:58:15');
INSERT INTO `t_order` VALUES ('20181001', '课程设计指导书', '2018-07-08 18:07:02');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `Uno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Upwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Uname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Nborrow` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`Uno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('20181001', '111111', '小明', 3);
INSERT INTO `user` VALUES ('admin', '123456', '管理员', 0);

-- ----------------------------
-- View structure for v_borrow
-- ----------------------------
DROP VIEW IF EXISTS `v_borrow`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_borrow` AS select `borrow`.`Uno` AS `Uno`,`borrow`.`Bno` AS `Bno`,`borrow`.`BorrowDate` AS `BorrowDate`,`book`.`Bname` AS `Bname`,`user`.`Uname` AS `Uname` from ((`book` join `borrow`) join `user`) where ((`book`.`Bno` = `borrow`.`Bno`) and (`borrow`.`Uno` = `user`.`Uno`));

-- ----------------------------
-- View structure for v_order
-- ----------------------------
DROP VIEW IF EXISTS `v_order`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_order` AS select `t_order`.`Uno` AS `Uno`,`t_order`.`Bname` AS `Bname`,`t_order`.`OrderDate` AS `OrderDate`,`user`.`Uname` AS `Uname` from (`t_order` join `user`) where (`user`.`Uno` = `t_order`.`Uno`);

SET FOREIGN_KEY_CHECKS = 1;
