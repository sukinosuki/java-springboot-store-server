CREATE TABLE `goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `covers` varchar(1000) NOT NULL COMMENT '封面',
  `detail` varchar(1000) NOT NULL COMMENT '详情',
  `on_shelf` tinyint NOT NULL COMMENT '是否上架',
  `stock` int NOT NULL COMMENT '库存',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `sort` int NOT NULL,
  `real_sales` int NOT NULL COMMENT '真实销售量',
  `fake_base_sales` int NOT NULL COMMENT '虚假的基础销售量(展示销售量=真实销售量+虚假基础销售)',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `goods_2_goods_category`  (
  `goods_id` bigint NOT NULL,
  `goods_category_id` bigint NOT NULL,
  PRIMARY KEY (`goods_id`, `goods_category_id`)
);

CREATE TABLE `goods_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sort` int NOT NULL,
  `pid` bigint NOT NULL,
  `enabled` tinyint NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX(`name`)
);

CREATE TABLE `nav_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL,
  `cover` varchar(255) NOT NULL COMMENT '封面(有的菜单需要封面有的不需要)',
  `action_type` tinyint NOT NULL COMMENT '0无操作1打开页面2打开url',
  `position` varchar(50) NULL COMMENT '菜单位置(对应nav_menu_position表的value)',
  `sort` int NOT NULL,
  `description` varchar(255) NOT NULL,
  `open_page` varchar(50) NULL COMMENT '打开页面名称(唯一)',
  `open_page_param` varchar(255) NOT NULL COMMENT '打开页面携带参数',
  `open_url` varchar(255) NOT NULL COMMENT 'url链接',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `nav_menu_position`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称(唯一)',
  `value` varbinary(50) NOT NULL COMMENT '值(唯一)',
  `description` varchar(255) NOT NULL,
  `sort` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX(`value`),
  UNIQUE INDEX(`name`)
);

CREATE TABLE `role_2_permission_node`  (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`)
);

CREATE TABLE `sys_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `enabled` tinyint NOT NULL COMMENT '是否启用',
  `method` varchar(10) NULL COMMENT 'GET\\POST\\PUT\\DELETE',
  `url` varchar(255) NULL,
  `pid` bigint NOT NULL,
  `type` tinyint NOT NULL COMMENT '1菜单2按钮',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX(`method`, `url`)
);

CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX(`name`)
);

CREATE TABLE `sys_role_2_sys_permission`  (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`)
);

CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(12) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(12) NOT NULL,
  `avatar` varchar(255) NOT NULL,
  `enabled` tinyint NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX(`username`)
);

CREATE TABLE `sys_user_2_sys_role`  (
  `uid` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`uid`, `role_id`)
);

