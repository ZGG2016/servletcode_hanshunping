use userdb;

create table users(
	userId int primary key not null auto_increment,
	username varchar(20),
	password varchar(20),
	email varchar(20),
	grade int
);


insert into users (username,password,email,grade)
    values
	("admin","admin","admin@163.com",1),
	("test01","test01","test01@163.com",2),
	("test02","test02","test02@163.com",2),
	("test03","test03","test03@163.com",2),
	("test04","test04","test04@163.com",2),
	("test05","test05","test05@163.com",2),
	("test06","test06","test06@163.com",2),
	("test07","test07","test07@163.com",2),
	("test08","test08","test08@163.com",2),
	("user01","user01","user01@163.com",3),
	("user02","user02","user02@163.com",3),
	("user03","user03","user03@163.com",3),
	("user04","user04","user04@163.com",3),
	("user05","user05","user05@163.com",3),
	("user06","user06","user06@163.com",3),
	("user07","user07","user07@163.com",3),
	("user08","user08","user08@163.com",3);
