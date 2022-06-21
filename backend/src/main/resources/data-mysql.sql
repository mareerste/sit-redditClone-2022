
-- insert into user (dtype,username, avatar, description, email, password, registration_date, deleted) values ('User','marko123', null,'Proba 123', 'marko123@gmail.com', 'marko123','2022-04-01',false);
-- insert into user (dtype,username, avatar, description, email, password, registration_date, deleted) values ('Moderator','mirko123', null,'Proba 123', 'mirko123@gmail.com', 'mirko123','2022-04-01',false);
-- insert into user (dtype,username, avatar, description, email, password, registration_date, deleted) values ('Moderator','pera123', null,'opis profila 123', 'pera123@gmail.com', 'pera123','2022-04-02',false);
-- insert into user (dtype,username, avatar, description, email, password, registration_date, deleted) values ('Administrator','zarko123', null,'Proba 123', 'zarko123@gmail.com', 'zarko123','2022-04-01',false);
-- insert into user (dtype,username, avatar, description, email, password, registration_date, deleted) values ('Administrator','kosta123', null,'Proba 123', 'zarko123@gmail.com', 'kosta123','2022-04-01',false);

insert into user (username, avatar, description, email, password, registration_date, deleted) values ('marko123', null,'Proba 123', 'marko123@gmail.com', '$2a$04$w7h4Twv.ZStR9eoZuQRqHejSgydmzTrAV6Un0dUM5GOqVBCCw6IRC','2022-04-01',false);
insert into moderator (username, avatar, description, email, password, registration_date, deleted) values ('mirko123', null,'Proba 123', 'mirko123@gmail.com', '$2a$04$Ylp9koY3.5pxYTqeCxEzBeWj07jc0kp3821.e8uq7LB1bQF0VZd9S','2022-04-01',false);
insert into moderator (username, avatar, description, email, password, registration_date, deleted) values ('pera123', null,'opis profila 123', 'pera123@gmail.com', '$2a$04$VcEN0V/ociwRHnVNvAUC8e4dyG/lUYO6RevjmoWMJclGFxzODdtwq','2022-04-02',false);
insert into administrator (username, avatar, description, email, password, registration_date, deleted) values ('zarko123', null,'Proba 123', 'zarko123@gmail.com', '$2a$04$V2M/DkHpiIbM34SMjoo23uD94DdY2IRnF.p1.vTstdLDWuvBRXOZO','2022-04-01',false);
insert into user (username, avatar, description, email, password, registration_date, deleted) values ('kosta123', null,'Proba 123', 'kostao123@gmail.com', '$2a$04$BOrJt24UNCm4jCCmqC.Mpupa4SxnY/J.olflqUAiaQeRhwAwo9dgy','2022-04-01',false);


insert into community(creation_date,description,suspended,name) values ('2022-04-01',"Opis prvog communitya",false,"Fun");
insert into community(creation_date,description,suspended,name) values ('2022-04-02',"Opis drugog communitya",false,"School");

insert into community_rules (community_id,rules) values (1,'Good luck');
insert into community_rules (community_id,rules) values (1,'Have fun');
insert into community_rules (community_id,rules) values (2,'Lep pozdrav');

insert into moderators (community_id, moderator_id) values (1,'mirko123');
insert into moderators (community_id, moderator_id) values (1,'pera123');
insert into moderators (community_id, moderator_id) values (2,'pera123');

insert into flair (name) values ('FeelFree');
insert into flair (name) values ('FunAndFit');
insert into flair (name) values ('Something');
insert into flair (name) values ('Dodjavola');

insert into community_flairs(community_id, flair_id) values (1,1);
insert into community_flairs(community_id, flair_id) values (1,2);
insert into community_flairs(community_id, flair_id) values (2,1);
insert into community_flairs(community_id, flair_id) values (2,2);
insert into community_flairs(community_id, flair_id) values (2,3);
insert into community_flairs(community_id, flair_id) values (2,4);

insert into post (date,deleted, text,title,user_id,flair_id) values ('2022-06-19',false,'Tekst prvog posta','Random stvar','marko123',1);
insert into post (date,deleted, text,title,user_id,flair_id) values ('2022-04-03',false,'Tekst drugog posta','Random stvar part 2','marko123',1);
insert into post (date,deleted, text,title,user_id,flair_id) values ('2022-04-03',false,'Tekst treceg posta','Random title za drugi Community','marko123',2);
insert into post (date,deleted, text,title,user_id,flair_id) values ('2022-04-03',false,'Tekst cetvrtog posta za kostu','Random title za drugi Community 2','kosta123',2);

insert into community_posts (community_id,post_id) values (1,1);
insert into community_posts (community_id,post_id) values (1,2);
insert into community_posts (community_id,post_id) values (2,3);
insert into community_posts (community_id,post_id) values (2,4);

-- insert into comment (deleted,text,timestamp,post_id,user_id) values (false,'dobar post!','2022-04-04',1,"marko123");
insert into comment (deleted,text,timestamp,user_id) values (false,'dobar post!','2022-04-04',"marko123");
-- insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Pa i nije nestoo','2022-04-04','mirko123',1);
insert into comment (deleted,text,timestamp,user_id) values (false,'Pa i nije nestoo','2022-04-04','mirko123');
-- insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Zasto?','2022-04-04','marko123',2);
insert into comment (deleted,text,timestamp,user_id) values (false,'Zasto?','2022-04-04','marko123');
-- insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Bukvalno nema veze sa zivotom?','2022-04-05','pera123',1);
insert into comment (deleted,text,timestamp,user_id) values (false,'Bukvalno nema veze sa zivotom?','2022-04-05','pera123');
-- insert into comment (deleted,text,timestamp,post_id,user_id) values (false,'Glupost...','2022-04-04',1,"pera123");
insert into comment (deleted,text,timestamp,user_id) values (false,'Glupost...','2022-04-04',"pera123");
-- insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Bas je dobro','2022-04-04','zarko123',5);
insert into comment (deleted,text,timestamp,user_id) values (false,'Bas je dobro','2022-04-04','zarko123');
-- insert into comment (deleted,text,timestamp,post_id,user_id) values (false,'Komentar za drugi post!','2022-04-06',2,"marko123");
insert into comment (deleted,text,timestamp,user_id) values (false,'Komentar za drugi post!','2022-04-06',"marko123");
-- insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Pod komentar za drugi post prvi','2022-04-07','kosta123',7);
insert into comment (deleted,text,timestamp,user_id) values (false,'Pod komentar za drugi post blablabla','2022-04-07','mirko123');
-- insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Pod komentar za drugi post drugi','2022-04-08','marko123',7);
insert into comment (deleted,text,timestamp,user_id) values (false,'Pod komentar za drugi post blabla','2022-04-08','kosta123');

insert into post_comments (post_id,comment_id) values (1,1);
insert into post_comments (post_id,comment_id) values (1,5);
insert into post_comments (post_id,comment_id) values (2,7);

insert into child_comments (comment_id,child_id) values (1,2);
insert into child_comments (comment_id,child_id) values (2,3);
insert into child_comments (comment_id,child_id) values (1,4);
insert into child_comments (comment_id,child_id) values (5,6);
insert into child_comments (comment_id,child_id) values (7,8);
insert into child_comments (comment_id,child_id) values (7,9);

insert into reaction (date, type, comment_id,user_id) values ('2022-04-05',0,1,'marko123');
insert into reaction (date, type, post_id,user_id) values ('2022-04-05',0,1,'marko123');
insert into reaction (date, type, post_id,user_id) values ('2022-04-05',0,1,'mirko123');
insert into reaction (date, type, post_id,user_id) values ('2022-04-05',1,1,'pera123');

insert into report (reason,date,post_id,user_id) values (3,'2022-05-15',1,'marko123');
insert into report (reason,date,post_id,user_id) values (4,'2022-04-17',1,'pera123');
insert into report (reason,date,post_id,user_id) values (5,'2022-05-22',1,'kosta123');
insert into report (reason,date,post_id,user_id) values (5,'2022-05-28',2,'kosta123');
insert into report (reason,date,post_id,user_id) values (5,'2022-06-09',4,'kosta123');
insert into report (reason,date,post_id,user_id) values (5,'2022-06-08',3,'kosta123');
insert into report (reason,date,comment_id,user_id) values (0,'2022-06-11',1,'marko123');
insert into report (reason,date,comment_id,user_id) values (7,'2022-05-22',1,'mirko123');
insert into report (reason,date,comment_id,user_id) values (1,'2022-06-13',2,'marko123');

insert into banned (date,community_id, moderator_id, user_id) values ('2022-05-12',1,'mirko123','kosta123');