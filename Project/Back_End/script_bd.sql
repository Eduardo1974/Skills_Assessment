drop database SkillsAssessment;
create database SkillsAssessment;
use SkillsAssessment;

create table institution(
	ist_code int auto_increment,
    ist_company varchar(250),
    ist_cnpj varchar(50),
    ist_city varchar(250),
    constraint pk_ist primary key (ist_code)
)engine=innodb;


create table user (
	usr_code int auto_increment,
    usr_userName varchar(250) ,
    usr_password varchar(100) ,
    usr_ra varchar(30),
    usr_situation smallint default 0,
    usr_verified smallint default 0,
    usr_type varchar (50) ,
    usr_token varchar(500),
    usr_name varchar(100),
    usr_register Date,
    ist_code int ,
    constraint fk_ist foreign key (ist_code) references institution (ist_code),
    constraint pk_usr primary key (usr_code)
)engine=innodb;



create table course (
	crs_code int auto_increment,
    crs_name varchar (250),
    crs_situation smallint,
    crs_registration_date timestamp,
    constraint crs_pk primary key (crs_code)
)engine=innodb;

create table enrolls (
	ern_code int auto_increment,
	ern_year int ,
	ern_period int,
    crs_code int,
    usr_code int,
	constraint pk_prd primary key (ern_code),
    constraint fk_crs foreign key (crs_code) references course (crs_code),
    constraint fk_usr_enrolls foreign key (usr_code) references user (usr_code)
)engine=innodb;

create table question(
	qst_code int auto_increment,
    qst_question varchar(250) ,
    qst_introduction varchar(250) ,
    qst_situation int,
    constraint pk_qst primary key (qst_code)
)engine=innodb;

create table competence (
	com_code int auto_increment,
    com_type  varchar(250) ,
    com_registration_date timestamp,
    com_situation smallint default 0,
    constraint pk_com primary key (com_code)
)engine=innodb;

create table alternatives(
	alt_code int auto_increment,
    alt_description varchar(250) ,
    qst_code int ,
    constraint pk_alt primary key (alt_code),
    constraint fk_qst foreign key (qst_code) references question (qst_code)
)engine=innodb;

create table alt_com (
	rsc_code int auto_increment,
    alt_code int,
    com_code int,
    rsc_weight int,
    constraint pf_rsc primary key (rsc_code),
    constraint fk_alt foreign key (alt_code) references alternatives(alt_code),
    constraint fk_cmp foreign key (com_code) references competence (com_code)
);

create table quiz(
	quz_code int auto_increment,
    usr_code int ,
    qst_code int ,
    alt_code int ,
    quz_date timestamp,
    quz_duration time,
    constraint pk_pro primary key (quz_code),
    constraint fk_usr_quiz foreign key (usr_code) references user (usr_code),
    constraint fk_pgt foreign key (qst_code) references question (qst_code),
    constraint fk_rsp foreign key (alt_code) references alternatives (alt_code)
)engine=innodb;

create table result(
	rst_code int auto_increment,
    usr_code int ,
    rst_date_final timestamp,
    rst_completed smallint default 0,
    rst_measured smallint default 0,
    constraint pk_rlt primary key (rst_code),
    constraint fk_usr foreign key (usr_code) references user (usr_code)
)engine=innodb;

create table average(
	avr_code int auto_increment,
    rst_code int,
    com_code int,
    avr_final int,
    constraint pk_avr primary key (avr_code),
    constraint fk_rst foreign key (rst_code) references result (rst_code),
    constraint fk_cpt foreign key (com_code) references competence (com_code)
)engine=innodb;

create table ist_crs(
	itc_code int auto_increment,
    crs_code int,
    ist_code int,
    constraint pk_ist_crs primary key (itc_code,crs_code,ist_code) ,
    constraint fk_course foreign key (crs_code) references course (crs_code),
    constraint fk_institution foreign key (ist_code) references institution (ist_code)
)engine=innodb;

insert into user (
    usr_userName ,usr_password ,usr_situation ,usr_verified ,usr_type,  usr_name) 
    values ('joao','1234',1,1,'employee','joao da silva');  
    
insert into user (
    usr_userName ,usr_password ,usr_situation ,usr_verified ,usr_type, usr_name) 
    values ('edu','1234',1,1,'student','eduardo alves');
    
insert into user (
    usr_userName ,usr_password ,usr_situation ,usr_verified ,usr_type, usr_name) 
    values ('pedro','1234',1,1,'student','pedro da silva'); 
    

  
insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quando terminou a 2º guerra?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quem criou o computdor?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ( "imagem.jpeg","2 + 2 ?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ( "imagem.jpeg","5 + 4 ?", 0);

insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quando acaba?", 1);

insert into question (qst_introduction,qst_question,qst_situation) 
values ("imagem.jpeg","Quando vou tirar carta?", 1);

insert into alternatives (alt_description,qst_code) values ("A",1);
insert into alternatives (alt_description,qst_code) values ("B",2);
insert into alternatives (alt_description,qst_code) values ("C",3);
insert into alternatives (alt_description,qst_code) values ("D",4);
insert into alternatives (alt_description,qst_code) values ("E",5);

insert into alternatives (alt_description,qst_code) values ("F",1);
insert into alternatives (alt_description,qst_code) values ("G",2);
insert into alternatives (alt_description,qst_code) values ("H",3);
insert into alternatives (alt_description,qst_code) values ("I",4);
insert into alternatives (alt_description,qst_code) values ("J",5);
insert into alternatives (alt_description,qst_code) values ("K",6);
insert into alternatives (alt_description,qst_code) values ("L",6);

insert into competence (com_type,com_registration_date) values ('Determinação','2008-01-01 00:00:01');
insert into competence (com_type,com_registration_date) values ('Individualismo','2008-01-01 00:00:02');
insert into competence (com_type,com_registration_date) values ('Persuasão','2008-01-01 00:00:03');
insert into competence (com_type,com_registration_date) values ('Persistência','2008-01-01 00:00:04');
insert into competence (com_type,com_registration_date) values ('Obediência','2008-01-01 00:00:05');

insert into alt_com (alt_code,com_code,rsc_weight) values (1,1,1);
insert into alt_com (alt_code,com_code,rsc_weight) values (1,2,7);
insert into alt_com (alt_code,com_code,rsc_weight) values (2,4,8);
insert into alt_com (alt_code,com_code,rsc_weight) values (2,3,5);
insert into alt_com (alt_code,com_code,rsc_weight) values (3,1,8);
insert into alt_com (alt_code,com_code,rsc_weight) values (3,5,5);

-- insert into quiz (usr_code,qst_code,alt_code,quz_date,quz_duration) values (1,1,1,current_date(),'00:39:38');
-- insert into quiz (usr_code,qst_code,alt_code,quz_date,quz_duration) values (2,1,1,current_date(),'00:40:38');
-- insert into quiz (usr_code,qst_code,alt_code,quz_date,quz_duration) values (1,2,2,current_date(),'00:41:38');
-- insert into quiz (usr_code,qst_code,alt_code,quz_date,quz_duration) values (2,2,2,current_date(),'00:45:38');
-- insert into quiz (usr_code,qst_code,alt_code,quz_date,quz_duration) values (1,3,3,current_date(),'00:41:38');
-- truncate quiz;

insert into course values (1,'Banco de Dados',1,'2008-01-01 00:00:01');
insert into course values (2,'Estrutura Leves',1,'2008-01-01 00:00:01');
insert into course values (3,'Logistica',1,'2008-01-01 00:00:01');
insert into course values (4,'Manutenção de Aeronaves',1,'2008-01-01 00:00:01');
insert into course values (5,'Gestão de Produção Industrial',1,'2008-01-01 00:00:01');
insert into course values (6,'Gestão de Produção Industrial',1,sysdate());


