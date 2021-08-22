-- auto Generated on 2021-08-02
-- DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	username VARCHAR (20) UNIQUE NOT NULL COMMENT 'username',
	`password` VARCHAR (32) NOT NULL COMMENT 'password',
	halt VARCHAR (10) NOT NULL COMMENT 'halt',
	display_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'displayName',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'user';
