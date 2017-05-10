drop database if exists wcrs;
create database wcrs;
use wcrs;

###############################################################
##1.  start from mysql 5.5, innodb is the default storage engine
##2.  use unsigned
##3.  use NOT NULL if possible
##4.  use ENUM
##5.  table name in SINGULAR
## http://www.devshed.com/c/a/mysql/designing-a-mysql-database-tips-and-techniques/
###############################################################
###############################################################
# wechat_user (union_id uniquely identify one user)
###############################################################
CREATE TABLE if not exists w_c_user (
    union_id       VARCHAR(36)       primary key NOT NULL,
    parent         VARCHAR(36)       DEFAULT NULL,        #   must be a valid scene_id
    nick_name      varchar(40)       NOT NULL,	                #	姓   
    phone          varchar(16)       DEFAULT NULL,
    gender         smallint          NOT NULL DEFAULT 0,	    #	性别  //user info.
    language       varchar(6)        NOT NULL DEFAULT 'cn',
    city           varchar(30)       NOT NULL DEFAULT '',
    province       varchar(30)       NOT NULL DEFAULT '',	
    country        varchar(64)       NOT NULL DEFAULT '',	
    head_img_url   varchar(128)      NOT NULL DEFAULT '',	 
    create_t       datetime          DEFAULT NULL,	            #	记录时间
    modify_t       datetime          DEFAULT NULL,	            #	记录更新时间
    status         smallint          NOT NULL DEFAULT 0,
    CONSTRAINT     phone_unique      UNIQUE (phone),
    FOREIGN KEY    (parent)          REFERENCES w_c_user(union_id) 
)  DEFAULT CHARSET=utf8;

###############################################################
# public_account, public account with wechat
###############################################################
CREATE TABLE if not exists public_account (
    p_a_id          varchar(30)        primary key NOT NULL,
    app_id          varchar(20)        NOT NULL,
    app_secret      varchar(36)        NOT NULL,
    description     varchar(60)        NOT NULL DEFAULT '',
    create_t        datetime           DEFAULT NULL	            #	记录时间
)  DEFAULT CHARSET=utf8;

###############################################################
# public_account_user (open_id uniquely identify one user belongs to a public account)
###############################################################
CREATE TABLE if not exists p_a_user (
    open_id        VARCHAR(36)       primary key NOT NULL,
    p_a_id         varchar(30)       NOT NULL,
    scene_id       int               DEFAULT NULL,
    union_id       VARCHAR(36)       DEFAULT NULL,                  #   usage?
    create_t       datetime          DEFAULT NULL,	                #	记录时间
    modify_t       datetime          DEFAULT NULL,	                #	记录更新时间
    ticket         varchar(100)      DEFAULT NULL,	                #
    ###constraint     union_id_unique   UNIQUE (union_id),              #limit one union_id to one public account
    foreign key    (union_id)        references w_c_user(union_id),
    foreign key    (p_a_id)          references public_account (p_a_id)
)  DEFAULT CHARSET=utf8;

###############################################################
# public_account_event, event from a public account with wechat
# relationship to public account
###############################################################
CREATE TABLE if not exists p_a_event (
    id              int unsigned       NOT NULL auto_increment primary key,
    to_user_name    varchar(30)        NOT NULL DEFAULT '',
    from_user_name  varchar(30)        NOT NULL DEFAULT '',
    msg_type        varchar(20)        NOT NULL DEFAULT '',
    event           varchar(20)        NOT NULL DEFAULT '',
    event_key       varchar(100)       NOT NULL DEFAULT '',
    from_ip         varchar(15)        NOT NULL DEFAULT '',
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
# affiliate, aff_node --> affilication node
###############################################################
CREATE TABLE if not exists affiliate (
    id              int unsigned       NOT NULL auto_increment primary key,
    product_id      int unsigned       NOT NULL,
    first           smallint           NOT NULL DEFAULT 0,
    second          smallint           NOT NULL DEFAULT 0,
    third           smallint           NOT NULL DEFAULT 0,
    aff_node_p      smallint           NOT NULL DEFAULT 10,
    aff_node_id     varchar(36)        NOT NULL,
    aff_node_name   varchar(32)        DEFAULT NULL,
    description     varchar(100)       NOT NULL DEFAULT '',
    create_t        datetime           DEFAULT NULL,	            #	记录时间
    foreign key    (product_id)        references product (id),
    foreign key    (aff_node_id)       references w_c_user (union_id),
    UNIQUE KEY     id_aff_product     (product_id, aff_node_id)
)  DEFAULT CHARSET=utf8;

###############################################################
# product_rule ( product_id --> rule_id, 1 --> M, multiple affiliation_nodes.
###############################################################

###############################################################
# wcuser_product
###############################################################
CREATE TABLE if not exists wcuser_product (
    id             int unsigned        NOT NULL auto_increment primary key,
    union_id       VARCHAR(36)         NOT NULL,
    product_id     int unsigned        NOT NULL,
    amount         numeric(15,2)       NOT NULL,
    create_t       datetime            DEFAULT NULL,
    foreign key    (union_id)          references w_c_user (union_id),
    foreign key    (product_id)        references product (id)
)  DEFAULT CHARSET=utf8;

###############################################################
# billing:
# in user_product table, each record generates
# at most 4 trans: first, second, third, and affiliate root
###############################################################
CREATE TABLE if not exists billing (
    id             int unsigned        NOT NULL auto_increment primary key,
    union_id       VARCHAR(36)         NOT NULL,
    u_p_id         int unsigned        NOT NULL,
    percent        smallint            NOT NULL,
    amount         numeric(15,2)       NOT NULL,
    create_t       datetime            DEFAULT NULL,
    foreign key    (union_id)          references w_c_user (union_id),
    foreign key    (u_p_id)            references user_product (id)
)  DEFAULT CHARSET=utf8;

###############################################################
# account:
# user account to manage wcrs
###############################################################
create table account (
    id int unsigned not null auto_increment primary key,
    username varchar(50) not null,
    password varchar(72) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    phone varchar(50) not null,
    enabled boolean not null,
    unique index account_idx1 (username)
) engine = InnoDb;

###############################################################
# role:
# user role to manage access control
###############################################################
create table role (
    id smallint unsigned not null auto_increment primary key,
    name varchar(50) not null
) engine = InnoDb;

###############################################################
# account_role:
# user role to manage access control
###############################################################
create table account_role (
    id int unsigned not null auto_increment primary key,
    account_id int unsigned not null,
    role_id smallint unsigned not null,
    foreign key (account_id) references account (id),
    foreign key (role_id) references role (id),
    unique index account_role_idx1 (account_id, role_id)
) engine = InnoDb;


