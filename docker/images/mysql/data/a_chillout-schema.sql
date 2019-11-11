DROP DATABASE IF EXISTS chillout;

CREATE DATABASE IF NOT EXISTS chillout;


CREATE TABLE `chillout`.`Client` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci UNIQUE NOT NULL,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `isAdmin` boolean
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `chillout`.`Product` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci UNIQUE NOT NULL,
  `unitPrice` decimal(10,2) not null,
  `description` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `chillout`.`OrderItem` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `idProduct` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
    foreign key (idProduct) references Product(id)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `chillout`.`Order` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `idClient` int(11) NOT NULL,
  `idOrderItem` int(11) NOT NULL,
    foreign key (idClient) references Client(id),
    foreign key (idOrderItem) references OrderItem(id)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
