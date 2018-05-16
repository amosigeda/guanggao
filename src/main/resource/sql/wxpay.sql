/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/10/13 17:07:40                          */
/*==============================================================*/


drop table if exists city;

drop table if exists configure;

drop index init_index_timei on init_log;

drop index init_index_mercId on init_log;

drop index init_index_imsi on init_log;

drop table if exists init_log;

drop table if exists merc_account;

drop index Index_product_key on merc_product;

drop table if exists merc_product;

drop index Index_public_key on merchant;

drop index Index_private_key on merchant;

drop table if exists merchant;

drop table if exists province;

drop index api_index_tradeNo on trade_api_log;

drop table if exists trade_api_log;

drop index request_index_appId on trade_request_log;

drop index request_index_orderId on trade_request_log;

drop index request_index_time on trade_request_log;

drop index request_index_mercId on trade_request_log;

drop index request_index_imsi on trade_request_log;

drop table if exists trade_request_log;

drop index result_index_transTime on trade_result_log;

drop index result_index_channelNo on trade_result_log;

drop index result_index_tradeNo on trade_result_log;

drop table if exists trade_result_log;

drop index status_index_transTime on trade_status_log;

drop index status_index_channelNo on trade_status_log;

drop index status_index_tradeNo on trade_status_log;

drop table if exists trade_status_log;

/*==============================================================*/
/* Table: city                                                  */
/*==============================================================*/
create table city
(
   city_id              int not null auto_increment comment '主键id',
   province_id          int comment '省份id',
   city_code            varchar(20) comment '城市代码',
   city_name            varchar(20) comment '城市名称',
   primary key (city_id)
);

alter table city comment '城市表';

/*==============================================================*/
/* Table: configure                                             */
/*==============================================================*/
create table configure
(
   id                   int not null auto_increment,
   tradeType            varchar(32) comment '交易类型:cs.pay.submit',
   version              varchar(8) comment '版本号，1.3',
   insNo                varchar(32) comment '机构号',
   channel              varchar(10) comment '支付渠道',
   mchId                varchar(32) comment '由cs分配的商户号',
   notifyUrl            varchar(128) comment '结果通知地址',
   url                  varchar(128) comment '请求地址',
   timeout              int comment '连接超时时间',
   create_time          timestamp comment '创建时间',
   update_time          timestamp comment '更新时间',
   create_name          varchar(20) comment '创建者',
   update_name          varchar(50) comment '更新人',
   remarks              varchar(50) comment '备注',
   primary key (id)
);

alter table configure comment '对接配置';

/*==============================================================*/
/* Table: init_log                                              */
/*==============================================================*/
create table init_log
(
   id                   int not null auto_increment,
   mobile_model         varchar(128) comment '手机型号',
   os_version           varchar(8) comment '手机操作系统版本',
   net_mode             varchar(24) comment '网络模式',
   productPackage       varchar(32) comment '包名',
   appname              varchar(32) comment '应用名',
   imsi                 varchar(16) comment '卡串号',
   imei                 varchar(16) comment '国际移动设备标识',
   screen               varchar(32) comment '屏幕分辨率',
   lac                  varchar(32) comment '基站信息',
   mip                  varchar(16) comment 'IP地址',
   mercId               varchar(24),
   errorCode            varchar(5) comment '错误代码;1表示成功',
   errorCodeDes         varchar(128) comment '错误描述',
   time                 varchar(14),
   sign                 varchar(32),
   primary key (id)
);

alter table init_log comment '初始化日志';

/*==============================================================*/
/* Index: init_index_imsi                                       */
/*==============================================================*/
create index init_index_imsi on init_log
(
   imsi
);

/*==============================================================*/
/* Index: init_index_mercId                                     */
/*==============================================================*/
create index init_index_mercId on init_log
(
   mercId
);

/*==============================================================*/
/* Index: init_index_timei                                      */
/*==============================================================*/
create index init_index_timei on init_log
(
   time
);

/*==============================================================*/
/* Table: merc_account                                          */
/*==============================================================*/
create table merc_account
(
   account_id           int not null auto_increment comment '主键id',
   username             varchar(50) comment '用户名',
   password             varchar(50) comment '密码',
   login_time           timestamp comment '最后登录时间',
   login_ip             varchar(20) comment '最后登录ip',
   status               int,
   create_time          timestamp comment '创建时间',
   update_time          timestamp comment '更新时间',
   create_name          varchar(20) comment '创建者',
   update_name          varchar(50) comment '更新人',
   remarks              varchar(50) comment '备注',
   primary key (account_id)
);

alter table merc_account comment '商户账号表';

/*==============================================================*/
/* Table: merc_product                                          */
/*==============================================================*/
create table merc_product
(
   product_id           int not null auto_increment comment '主键id',
   merc_id              int comment '商户id',
   product_name         varchar(20) comment '产品名称',
   product_key          varchar(32) comment '产品标识',
   status               smallint comment '状态：0待上传应用，1待审核，2审核通过，3审核不通过，4启用，5禁用',
   status_message       varchar(50) comment '状态信息',
   product_path         varchar(100) comment '产品路径',
   product_size         float comment '产品大小',
   create_time          timestamp comment '创建时间',
   create_name          varchar(10) comment '创建者',
   update_name          varchar(20) comment '更新者',
   update_time          timestamp comment '更新时间',
   remarks              varchar(100) comment '备注',
   primary key (product_id)
);

alter table merc_product comment '商户产品';

/*==============================================================*/
/* Index: Index_product_key                                     */
/*==============================================================*/
create unique index Index_product_key on merc_product
(
   product_key
);

/*==============================================================*/
/* Table: merchant                                              */
/*==============================================================*/
create table merchant
(
   merc_id              int not null auto_increment comment '主键id',
   city_id              int comment '城市id',
   account_id           int comment '账号id',
   name                 varchar(20) not null comment '商户名称',
   official_number      varchar(20) not null comment '商户营业执照号',
   contacts             varchar(20) not null comment '商户负责人',
   email                varchar(50) comment '商户电子邮件',
   mobile               varchar(15) not null comment '手机号码',
   phone                varchar(15) not null comment '商户固定电话',
   public_key           varchar(50) comment '商户公钥',
   private_key          varchar(50) comment '商户秘钥',
   mr_url               varchar(100) comment '同步地址',
   status               smallint comment '商户状态：0默认（无状态），1待审核，2审核通过，3审核不通过，4启用，5禁用',
   status_message       varchar(100) comment '状态信息',
   create_time          timestamp comment '创建时间',
   update_time          timestamp comment '更新时间',
   create_name          varchar(10) comment '创建者：新注册的默认填写为：系统注册',
   update_name          varchar(20) comment '更新用户名',
   remarks              varchar(100) comment '备注',
   primary key (merc_id)
);

alter table merchant comment '商户';

/*==============================================================*/
/* Index: Index_private_key                                     */
/*==============================================================*/
create unique index Index_private_key on merchant
(
   private_key
);

/*==============================================================*/
/* Index: Index_public_key                                      */
/*==============================================================*/
create unique index Index_public_key on merchant
(
   public_key
);

/*==============================================================*/
/* Table: province                                              */
/*==============================================================*/
create table province
(
   province_id          int not null auto_increment comment '主键id',
   province_code        varchar(20) comment '省份代码',
   province_name        varchar(20) comment '省份名称',
   primary key (province_id)
);

alter table province comment '省份表';

/*==============================================================*/
/* Table: trade_api_log                                         */
/*==============================================================*/
create table trade_api_log
(
   id                   int not null auto_increment,
   tradeType            varchar(32) comment '交易类型',
   version              varchar(8) comment '版本',
   channel              varchar(24) comment '支付渠道',
   terminalType         varchar(20) comment '终端类型',
   cashierNo            varchar(20) comment '操作员',
   insNo                varchar(32),
   mchid                varchar(32) comment '商户号',
   sign                 varchar(32) comment '签名',
   body                 varchar(128) comment '商品描述',
   outTradeNo           varchar(32) comment '商户订单号',
   amount               float(5,2) comment '交易金额',
   description          varchar(128) comment '附加数据',
   currency             varchar(16) comment '货币类型',
   timePaid             varchar(14) comment '订单支付时间',
   timeExpire           varchar(14) comment '订单失效时间',
   subject              varchar(16) comment '商品标题',
   limitPay             varchar(8) comment '为指定支付方式，指定不能使用信用卡支付可设置为no_credit',
   notifyUrl            varchar(128) comment '支付完成后结果通知url；参数参考交易详细查询;',
   errorCode            varchar(5) comment '错误代码,1表示成功',
   errorCodeDes         varchar(128) comment '错误描述',
   primary key (id)
);

alter table trade_api_log comment '下单接口日志';

/*==============================================================*/
/* Index: api_index_tradeNo                                     */
/*==============================================================*/
create index api_index_tradeNo on trade_api_log
(
   outTradeNo
);

/*==============================================================*/
/* Table: trade_request_log                                     */
/*==============================================================*/
create table trade_request_log
(
   id                   int not null auto_increment,
   mobile_model         varchar(128) comment '手机型号',
   os_version           varchar(8) comment '手机操作系统版本',
   net_mode             varchar(24) comment '网络模式',
   productPackage       varchar(32) comment '包名',
   appname              varchar(32) comment '应用名',
   imsi                 varchar(16) comment '卡串号',
   imei                 varchar(16) comment '国际移动设备标识',
   screen               varchar(32) comment '屏幕分辨率',
   lac                  varchar(32) comment '基站信息',
   mip                  varchar(16) comment 'IP地址',
   merc_id              varchar(32) comment '商户号',
   app_id               varchar(32) comment '应用编号',
   out_order_id         varchar(32) comment '商户订单号',
   user_id              varchar(24) comment '商户用户名',
   subject              varchar(128) comment '计费描述',
   amount               float(5,2) comment '计费金额',
   time                 varchar(14) comment '时间戳',
   ret_url              varchar(128) comment '回调地址',
   sign                 varchar(32) comment 'MD5验证',
   errorCode            varchar(5) comment '错误代码：1代表成功',
   errorCodeDes         varchar(128) comment '错误描述',
   primary key (id)
);

alter table trade_request_log comment '请求微信支付日志';

/*==============================================================*/
/* Index: request_index_imsi                                    */
/*==============================================================*/
create index request_index_imsi on trade_request_log
(
   imsi
);

/*==============================================================*/
/* Index: request_index_mercId                                  */
/*==============================================================*/
create index request_index_mercId on trade_request_log
(
   merc_id
);

/*==============================================================*/
/* Index: request_index_time                                    */
/*==============================================================*/
create index request_index_time on trade_request_log
(
   time
);

/*==============================================================*/
/* Index: request_index_orderId                                 */
/*==============================================================*/
create index request_index_orderId on trade_request_log
(
   out_order_id
);

/*==============================================================*/
/* Index: request_index_appId                                   */
/*==============================================================*/
create index request_index_appId on trade_request_log
(
   app_id
);

/*==============================================================*/
/* Table: trade_result_log                                      */
/*==============================================================*/
create table trade_result_log
(
   id                   int not null auto_increment,
   returnCode           varchar(1) comment '返回状态码：0成功，1失败',
   returnMsg            varchar(128) comment '返回信息',
   resultCode           varchar(16) comment '业务结果：0成功，1失败',
   errCode              varchar(32) comment '错误代码',
   errCodeDes           varchar(128) comment '错误代码描述',
   channel              varchar(24) comment '支付渠道',
   payChannelType       varchar(50) comment '通道类型',
   terminalType         varchar(20) comment '终端类型',
   cashierName          varchar(20) comment '操作员',
   outTradeNo           varchar(32) comment '商户订单号',
   outChannelNo         varchar(32) comment '支付渠道订单号',
   body                 varchar(128) comment '商品描述',
   currency             varchar(16) comment '货币类型',
   amount               float(5,2) comment '交易金额',
   transTime            varchar(14) comment '交易时间',
   postscript           varchar(200) comment '附言',
   subject              varchar(16) comment '商品标题',
   status               varchar(2) comment '订单状态
            01：未支付
            02：已支付
            03：已冲正
            04：已关闭
            05：转入退款
            09：支付失败
            10：订单超时',
   outRefundNo          varchar(32) comment '商户退款订单号',
   channelRefundNo      varchar(32) comment '支付渠道退款单号',
   refundTime           varchar(14) comment '退款时间',
   refundStatus         varchar(2) comment '退款状态
            01：退款申请
            02：退款成功
            03：退款失败',
   sign                 varchar(32) comment '签名',
   primary key (id)
);

alter table trade_result_log comment '返回结果通知日志';

/*==============================================================*/
/* Index: result_index_tradeNo                                  */
/*==============================================================*/
create index result_index_tradeNo on trade_result_log
(
   outTradeNo
);

/*==============================================================*/
/* Index: result_index_channelNo                                */
/*==============================================================*/
create index result_index_channelNo on trade_result_log
(
   outChannelNo
);

/*==============================================================*/
/* Index: result_index_transTime                                */
/*==============================================================*/
create index result_index_transTime on trade_result_log
(
   transTime
);

/*==============================================================*/
/* Table: trade_status_log                                      */
/*==============================================================*/
create table trade_status_log
(
   id                   int not null auto_increment,
   returnCode           varchar(1) comment '返回状态码：0成功，1失败',
   returnMsg            varchar(128) comment '返回信息',
   codeUrl              varchar(32) comment '二维码串',
   payCode              varchar(1000) comment '支付码信息
            以下交易类型时有返回
            1.wxApp
            2.jdPay
            3.jdPayGate
            4.jdMicro',
   sign                 varchar(32) comment '签名',
   resultCode           varchar(16) comment '业务结果状态 ：0成功，1失败',
   outTradeNo           varchar(32) comment '商户订单号',
   outChannelNo         varchar(32) comment '支付渠道订单号',
   amount               float(5,2) comment '交易金额',
   transTime            varchar(14) comment '交易时间',
   errCode              varchar(32) comment '错误代码',
   errCodeDes           varchar(128) comment '错误代码描述',
   primary key (id)
);

alter table trade_status_log comment '下单状态日志';

/*==============================================================*/
/* Index: status_index_tradeNo                                  */
/*==============================================================*/
create index status_index_tradeNo on trade_status_log
(
   outTradeNo
);

/*==============================================================*/
/* Index: status_index_channelNo                                */
/*==============================================================*/
create index status_index_channelNo on trade_status_log
(
   outChannelNo
);

/*==============================================================*/
/* Index: status_index_transTime                                */
/*==============================================================*/
create index status_index_transTime on trade_status_log
(
   transTime
);

alter table city add constraint FK_Reference_2 foreign key (province_id)
      references province (province_id) on delete restrict on update restrict;

alter table merc_product add constraint FK_Reference_3 foreign key (merc_id)
      references merchant (merc_id) on delete restrict on update restrict;

alter table merchant add constraint FK_Reference_4 foreign key (city_id)
      references city (city_id) on delete restrict on update restrict;

alter table merchant add constraint FK_Reference_5 foreign key (account_id)
      references merc_account (account_id) on delete restrict on update restrict;

