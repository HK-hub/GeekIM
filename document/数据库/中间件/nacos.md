# nacos 安装配置
```sql
create user nacos with password 'nacos';
grant all privileges on database db_nacos to nacos;

create database db_nacos owner nacos;

/******************************************/
/*   表名称 = config_info                  */
/******************************************/
CREATE TABLE config_info (
    id bigserial NOT NULL ,
    data_id varchar(255) NOT NULL,
    group_id varchar(128) DEFAULT NULL,
    content text NOT NULL,
    md5 varchar(32) DEFAULT NULL ,
    gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    src_user text ,
    src_ip varchar(50) DEFAULT NULL ,
    app_name varchar(128) DEFAULT NULL ,
    tenant_id varchar(128) DEFAULT '' ,
    c_desc varchar(256) DEFAULT NULL,
    c_use varchar(64) DEFAULT NULL ,
    effect varchar(64) DEFAULT NULL ,
    type varchar(64) DEFAULT NULL ,
    c_schema text ,
    encrypted_data_key text NOT NULL ,
    PRIMARY KEY (id),
    constraint uk_configinfo_datagrouptenant unique (data_id,group_id,tenant_id)
    ) ;



/******************************************/
/*   表名称 = config_info_aggr             */
/******************************************/
CREATE TABLE config_info_aggr (
    id bigserial NOT NULL ,
    data_id varchar(255) NOT NULL,
    group_id varchar(128) NOT NULL ,
    datum_id varchar(255) NOT NULL ,
    content text NOT NULL ,
    gmt_modified timestamp NOT NULL ,
    app_name varchar(128) DEFAULT NULL ,
    tenant_id varchar(128) DEFAULT '' ,
    PRIMARY KEY (id),
    constraint uk_configinfoaggr_datagrouptenantdatum unique (data_id,group_id,tenant_id,datum_id)
    ) ;


/******************************************/
/*   表名称 = config_info_beta             */
/******************************************/
CREATE TABLE config_info_beta (
    id bigserial NOT NULL  ,
    data_id varchar(255) NOT NULL ,
    group_id varchar(128) NOT NULL ,
    app_name varchar(128) DEFAULT NULL ,
    content text NOT NULL ,
    beta_ips varchar(1024) DEFAULT NULL ,
    md5 varchar(32) DEFAULT NULL ,
    gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    src_user text ,
    src_ip varchar(50) DEFAULT NULL ,
    tenant_id varchar(128) DEFAULT '' ,
    encrypted_data_key text NOT NULL ,
    PRIMARY KEY (id),
    constraint uk_configinfobeta_datagrouptenant unique (data_id,group_id,tenant_id)
    ) ;

/******************************************/
/*   表名称 = config_info_tag              */
/******************************************/
CREATE TABLE config_info_tag (
    id bigserial NOT NULL  ,
    data_id varchar(255) NOT NULL ,
    group_id varchar(128) NOT NULL ,
    tenant_id varchar(128) DEFAULT '' ,
    tag_id varchar(128) NOT NULL ,
    app_name varchar(128) DEFAULT NULL ,
    content text NOT NULL ,
    md5 varchar(32) DEFAULT NULL ,
    gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    src_user text ,
    src_ip varchar(50) DEFAULT NULL ,
    PRIMARY KEY (id),
    constraint uk_configinfotag_datagrouptenanttag unique (data_id,group_id,tenant_id,tag_id)
    ) ;

/******************************************/
/*   表名称 = config_tags_relation         */
/******************************************/
CREATE TABLE config_tags_relation (
    id bigint NOT NULL ,
    tag_name varchar(128) NOT NULL ,
    tag_type varchar(64) DEFAULT NULL ,
    data_id varchar(255) NOT NULL ,
    group_id varchar(128) NOT NULL ,
    tenant_id varchar(128) DEFAULT '' ,
    nid bigserial NOT NULL ,
    PRIMARY KEY (nid),
    constraint uk_configtagrelation_configidtag unique (id,tag_name,tag_type)
    ) ;
CREATE INDEX idx_tenant_id ON config_tags_relation (tenant_id);


/******************************************/
/*   表名称 = group_capacity               */
/******************************************/
CREATE TABLE group_capacity (
    id bigserial  NOT NULL  ,
    group_id varchar(128) NOT NULL DEFAULT '' ,
    quota int check ( quota >= 0 ) NOT NULL DEFAULT '0' ,
    usage int check ( usage >= 0 ) NOT NULL DEFAULT '0' ,
    max_size int check ( max_size >= 0 ) NOT NULL DEFAULT '0' ,
    max_aggr_count int check ( max_aggr_count >= 0 ) NOT NULL DEFAULT '0' ,
    max_aggr_size int check ( max_aggr_size >= 0 ) NOT NULL DEFAULT '0' ,
    max_history_count int check ( max_history_count >= 0 ) NOT NULL DEFAULT '0' ,
    gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (id),
    constraint positive_value_constraint  check ( id >= 0 ),
    constraint uk_group_id unique (group_id)
    ) ;


/******************************************/
/*   表名称 = his_config_info              */
/******************************************/
CREATE TABLE his_config_info (
    id bigint  NOT NULL ,
    nid bigserial NOT NULL ,
    data_id varchar(255) NOT NULL ,
    group_id varchar(128) NOT NULL ,
    app_name varchar(128) DEFAULT NULL ,
    content text NOT NULL ,
    md5 varchar(32) DEFAULT NULL ,
    gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    src_user text ,
    src_ip varchar(50) DEFAULT NULL ,
    op_type char(10) DEFAULT NULL ,
    tenant_id varchar(128) DEFAULT '' ,
    encrypted_data_key text NOT NULL ,
    PRIMARY KEY (nid)
) ;

create index idx_did on his_config_info(data_id);
create index idx_gmt_create on his_config_info (gmt_create);
create index idx_gmt_modified on his_config_info (gmt_modified);


/******************************************/
/*   表名称 = tenant_capacity              */
/******************************************/
CREATE TABLE tenant_capacity (
    id bigserial NOT NULL,
    tenant_id varchar(128) NOT NULL DEFAULT '' ,
    quota int  NOT NULL DEFAULT '0' ,
    usage int  NOT NULL DEFAULT '0' ,
    max_size int  NOT NULL DEFAULT '0' ,
    max_aggr_count int  NOT NULL DEFAULT '0' ,
    max_aggr_size int  NOT NULL DEFAULT '0' ,
    max_history_count int  NOT NULL DEFAULT '0' ,
    gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (id),
    constraint uk_tenant_id unique (tenant_id)
    ) ;


CREATE TABLE tenant_info (
    id bigserial not null ,
    kp varchar(128) NOT NULL ,
    tenant_id varchar(128) default '' ,
    tenant_name varchar(128) default '' ,
    tenant_desc varchar(256) DEFAULT NULL ,
    create_source varchar(32) DEFAULT NULL ,
    gmt_create bigint NOT NULL ,
    gmt_modified bigint NOT NULL ,
    PRIMARY KEY (id),
    constraint uk_tenant_info_kptenantid unique (kp,tenant_id)
    ) ;
create index idx_tenant_id on tenant_info (tenant_id);



CREATE TABLE users (
    username varchar(50) NOT NULL PRIMARY KEY ,
    password varchar(500) NOT NULL ,
    enabled boolean NOT NULL 
    );

CREATE TABLE roles (
    username varchar(50) NOT NULL ,
    role varchar(50) NOT NULL
    );
create unique INDEX idx_user_role on roles (username ASC, role ASC);

CREATE TABLE permissions (
    role varchar(50) NOT NULL ,
    resource varchar(255) NOT NULL ,
    action varchar(8) NOT NULL
    );
create UNIQUE INDEX uk_role_permission on permissions (role,resource,action);


INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');

```