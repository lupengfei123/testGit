
CREATE TABLE IF NOT EXISTS `cust_modules` (
  `module_code` varchar(20) NOT NULL,
  `app_code` varchar(20) DEFAULT NULL,
  `module_name` varchar(50) NOT NULL,
  `module_uri` varchar(100) NOT NULL,
  `module_leaf` int(11) NOT NULL DEFAULT '0' COMMENT '0: 否,1:是 ',
  `module_parent` varchar(50) DEFAULT '‘’',
  `module_order` int(11) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `module_display` int(11) DEFAULT '0' COMMENT '是否显示(0:不显示 1:显示)',
  PRIMARY KEY (`module_code`),
  KEY `module_parent` (`module_parent`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


