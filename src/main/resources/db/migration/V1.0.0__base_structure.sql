CREATE TABLE IF NOT EXISTS `cust_app` (
  `app_code` varchar(20) NOT NULL,
  `app_public_key` varchar(1000) NOT NULL COMMENT '公钥',
  `app_private_key` varchar(1000) DEFAULT NULL COMMENT '私钥',
  `app_name` varchar(50) NOT NULL COMMENT 'APP名称',
  `app_type` varchar(50) DEFAULT NULL COMMENT 'web, ios, android,app(包含ios和android)',
  `cust_code` varchar(4) DEFAULT NULL COMMENT '企业客户编码',
  `app_desc` varchar(100) DEFAULT NULL COMMENT 'APP描述',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`app_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
