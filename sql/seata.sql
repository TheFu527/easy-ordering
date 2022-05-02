-- t_account: table
DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `amount` double(14,2) DEFAULT '0.00',
  `password` varchar (255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `t_account`
VALUES ('1', '1', '4000.00', '1');

-- t_order: table
DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `commodity_code` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `amount` double(14,2) DEFAULT '0.00',
  `description` varchar(2083) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- t_stock: table
DROP TABLE IF EXISTS `t_stock`;

CREATE TABLE `t_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `price` double(14,2) DEFAULT '0.00',
  `image` varchar(2083) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `t_stock`
VALUES ('1', 'KOI12345678', 'iPhone', '1000', '450.00', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-13-pro-family-hero?wid=940&hei=1112&fmt=png-alpha&.v=1644969385433');
INSERT INTO `t_stock`
VALUES ('2', 'KIP22345679', 'iPad', '1000', '350.00', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/ipad-2021-hero-landing-wifi?wid=900&hei=1046&fmt=jpeg&qlt=90&.v=1630973863000');
INSERT INTO `t_stock`
VALUES ('3', 'KAI23455678', 'Airpods', '1000', '150.00', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MME73?wid=1144&hei=1144&fmt=jpeg&qlt=95&.v=1632861342000');
INSERT INTO `t_stock`
VALUES ('4', 'KMA34567891', 'MacBook', '1000', '800.00', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mbp-spacegray-select-202011_GEO_US?wid=904&hei=840&fmt=jpeg&qlt=90&.v=1632948875000');
INSERT INTO `t_stock`
VALUES ('5', 'KIW32345679', 'iWatch', '1000', '250.00', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MN1L3ref_VW_34FR+watch-45-alum-starlight-nc-7s_VW_34FR_WF_CO?wid=750&hei=712&trim=1%2C0&fmt=p-jpg&qlt=95&.v=1645128544617%2C1631661833000');

-- undo_log: table
DROP TABLE IF EXISTS `undo_log`;

CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


