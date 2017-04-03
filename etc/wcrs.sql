drop database if exists wcrs;
create database wcrs;
use wcrs;

###############################################################
##1.  start from mysql 5.5, innodb is the default storage enginer
##2.  use unsigned
##3.  use NOT NULL if possible
##4.  use ENUM
##5.  table name in SINGULAR
## http://www.devshed.com/c/a/mysql/designing-a-mysql-database-tips-and-techniques/
###############################################################

###############################################################
# user_info
###############################################################
CREATE TABLE if not exists user_info (
    openid         VARCHAR(36)       primary key NOT NULL,
    scene_id       int               NOT NULL DEFAULT 0,
    union_id       VARCHAR(36)       NOT NULL DEFAULT '',       #   usage?
    parent         int               NOT NULL DEFAULT 0,        #   must be a valid scene_id
    nick_name      varchar(40)       NOT NULL,	                #	姓   
    gender         varchar(6)        NOT NULL DEFAULT 'MALE',	#	性别  //user info.
    language       varchar(6)        NOT NULL DEFAULT 'cn',
    city           varchar(30)       NOT NULL DEFAULT '',
    province       varchar(30)       NOT NULL DEFAULT '',	
    country        varchar(64)       NOT NULL DEFAULT '',	
    head_img_url   varchar(128)      NOT NULL DEFAULT '',	 
    create_t       datetime          DEFAULT NULL,	            #	记录时间
    modify_t       datetime          DEFAULT NULL,	            #	记录更新时间
    status         smallint          NOT NULL DEFAULT 0,
    ticket         varchar(100)      DEFAULT NULL	            #
)  DEFAULT CHARSET=utf8;

###############################################################
# wc_event
###############################################################
CREATE TABLE if not exists wc_event (
    id              int unsigned       NOT NULL auto_increment primary key,
    to_user_name    varchar(30)        NOT NULL DEFAULT '',
    from_user_name  varchar(30)        NOT NULL DEFAULT '',
    msg_type        varchar(20)        NOT NULL DEFAULT '',
    event           varchar(20)        NOT NULL DEFAULT '',
    event_key       varchar(100)       NOT NULL DEFAULT '',
    create_t        datetime           DEFAULT NULL	            #	记录时间
)  DEFAULT CHARSET=utf8;

###############################################################
# product
###############################################################
CREATE TABLE if not exists product (
    id              int unsigned         NOT NULL auto_increment primary key,
    description     varchar(30)          NOT NULL DEFAULT '',
    p_type          varchar(30)          NOT NULL DEFAULT '',
    expired         tinyint(1) unsigned  NOT NULL DEFAULT '1',
    create_t        datetime             DEFAULT NULL	            #	记录时间
)  DEFAULT CHARSET=utf8;

###############################################################
# rule
###############################################################
CREATE TABLE if not exists rule (
    id              int unsigned       NOT NULL auto_increment primary key,
    first           smallint           NOT NULL DEFAULT 0,
    second          smallint           NOT NULL DEFAULT 0,
    third           smallint           NOT NULL DEFAULT 0,
    root_p          smallint           NOT NULL DEFAULT 10,
    root_id         varchar(32)        NOT NULL,
    root_name       varchar(32)      DEFAULT NULL,
    description     varchar(100)       NOT NULL DEFAULT '',
    create_t        datetime           DEFAULT NULL	            #	记录时间
)  DEFAULT CHARSET=utf8;

###############################################################
# product_rule ( product_id --> rule_id, 1 --> M, multiple affiliation_nodes.
###############################################################
CREATE TABLE if not exists product_rule (
    id             int unsigned        NOT NULL auto_increment primary key,
    product_id     int unsigned        NOT NULL,
    rule_id        int unsigned        NOT NULL,
    create_t       datetime            DEFAULT NULL,
    foreign key    (product_id)        references product (id),
    foreign key    (rule_id)           references rule (id),
    unique index   product_rule_idx1   (product_id, rule_id)
)  DEFAULT CHARSET=utf8;

###############################################################
# user_product (? root id), open_id is unique across all public accounts?
###############################################################
CREATE TABLE if not exists user_product (
    id             int unsigned        NOT NULL auto_increment primary key,
    user_id        VARCHAR(36)         NOT NULL,
    product_id     int unsigned        NOT NULL,
    amount         numeric(15,2)       NOT NULL,
    create_t       datetime            DEFAULT NULL,
    foreign key    (user_id)           references user_info (openid),
    foreign key    (product_id)        references product (id),
    unique index   user_product_idx1   (user_id, product_id)
)  DEFAULT CHARSET=utf8;



