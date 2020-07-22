DROP DATABASE IF EXISTS shiro;
CREATE DATABASE shiro DEFAULT CHARACTER SET utf8;
USE shiro;

drop table if exists user;
drop table if exists role;
drop table if exists permission;
drop table if exists user_role;
drop table if exists role_permission;

create table user (
                      id bigint auto_increment,
                      name varchar(100),
                      password varchar(100),
                      constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table role (
                      id bigint auto_increment,
                      name varchar(100),
                      constraint pk_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table permission (
                            id bigint auto_increment,
                            name varchar(100),
                            constraint pk_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table user_role (
                           uid bigint,
                           rid bigint,
                           constraint pk_users_roles primary key(uid, rid)
) charset=utf8 ENGINE=InnoDB;

create table role_permission (
                                 rid bigint,
                                 pid bigint,
                                 constraint pk_roles_permissions primary key(rid, pid)
) charset=utf8 ENGINE=InnoDB;


INSERT INTO `permission` VALUES (1,'addProduct');
INSERT INTO `permission` VALUES (2,'deleteProduct');
INSERT INTO `permission` VALUES (3,'editProduct');
INSERT INTO `permission` VALUES (4,'updateProduct');
INSERT INTO `permission` VALUES (5,'listProduct');
INSERT INTO `permission` VALUES (6,'addOrder');
INSERT INTO `permission` VALUES (7,'deleteOrder');
INSERT INTO `permission` VALUES (8,'editOrder');
INSERT INTO `permission` VALUES (9,'updateOrder');
INSERT INTO `permission` VALUES (10,'listOrder');
INSERT INTO `role` VALUES (1,'admin');
INSERT INTO `role` VALUES (2,'productManager');
INSERT INTO `role` VALUES (3,'orderManager');
INSERT INTO `role_permission` VALUES (1,1);
INSERT INTO `role_permission` VALUES (1,2);
INSERT INTO `role_permission` VALUES (1,3);
INSERT INTO `role_permission` VALUES (1,4);
INSERT INTO `role_permission` VALUES (1,5);
INSERT INTO `role_permission` VALUES (1,6);
INSERT INTO `role_permission` VALUES (1,7);
INSERT INTO `role_permission` VALUES (1,8);
INSERT INTO `role_permission` VALUES (1,9);
INSERT INTO `role_permission` VALUES (1,10);
INSERT INTO `role_permission` VALUES (2,1);
INSERT INTO `role_permission` VALUES (2,2);
INSERT INTO `role_permission` VALUES (2,3);
INSERT INTO `role_permission` VALUES (2,4);
INSERT INTO `role_permission` VALUES (2,5);
INSERT INTO `role_permission` VALUES (3,6);
INSERT INTO `role_permission` VALUES (3,7);
INSERT INTO `role_permission` VALUES (3,8);
INSERT INTO `role_permission` VALUES (3,9);
INSERT INTO `role_permission` VALUES (3,10);
INSERT INTO `user` VALUES (1,'zhang3','12345');
INSERT INTO `user` VALUES (2,'li4','abcde');
INSERT INTO `user_role` VALUES (1,1);
INSERT INTO `user_role` VALUES (2,2);