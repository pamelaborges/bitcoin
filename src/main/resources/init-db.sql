USE bitcoin;
CREATE TABLE IF NOT EXISTS `User` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`cpf` VARCHAR(14) NOT NULL,
	`username` VARCHAR(50) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	PRIMARY KEY(`id`)) ENGINE=InnoDB;
	
CREATE TABLE IF NOT EXISTS `BitcoinOrder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` decimal(6, 2) NOT NULL,
  `type_order` VARCHAR(20) NOT NULL,
  `created` datetime NOT NULL,
  `status` VARCHAR(30) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE = InnoDB;

ALTER TABLE User ADD COLUMN role VARCHAR(55);
