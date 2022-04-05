
insert into user (dtype,username, avatar, description, email, password, registration_date) values ('User','marko123', null,'Proba 123', 'marko123@gmail.com', 'marko123','2022-04-01');
insert into user (dtype,username, avatar, description, email, password, registration_date) values ('Moderator','mirko123', null,'Proba 123', 'mirko123@gmail.com', 'mirko123','2022-04-01');
insert into user (dtype,username, avatar, description, email, password, registration_date) values ('Moderator','pera123', null,'opis profila 123', 'pera123@gmail.com', 'pera123','2022-04-02');
insert into user (dtype,username, avatar, description, email, password, registration_date) values ('Administrator','zarko123', null,'Proba 123', 'zarko123@gmail.com', 'zarko123','2022-04-01');

insert into community(creation_date,description,suspended,name) values ('2022-04-01',"Opis prvog communitya",false,"Fun");
insert into community(creation_date,description,suspended,name) values ('2022-04-02',"Opis drugog communitya",false,"School");

insert into community_rules (community_id,rules) values (1,'Good luck');
insert into community_rules (community_id,rules) values (1,'Have fun');
insert into community_rules (community_id,rules) values (2,'Lep pozdrav');

insert into moderators (community_id, moderator_id) values (1,'mirko123');
insert into moderators (community_id, moderator_id) values (1,'pera123');
insert into moderators (community_id, moderator_id) values (2,'pera123');

insert into flair (name,community_id) values ('FeelFree',1);
insert into flair (name,community_id) values ('FunAndFit',1);
insert into flair (name,community_id) values ('Something',1);
insert into flair (name,community_id) values ('Dodjavola',2);

insert into post (date,deleted, text,title,community_id,user_id,flair_id) values ('2022-04-03',false,'Kakav brutalan dan!','Random stvar',1,'marko123',1);
insert into post (date,deleted, text,title,community_id,user_id,flair_id) values ('2022-04-03',false,'Gori ne moze..','Random stvar part 2',1,'marko123',1);
insert into post (date,deleted, text,title,community_id,user_id,flair_id) values ('2022-04-03',false,'Kakav debilan dan!','Random stvar za drugi Community',2,'marko123',2);

insert into comment (deleted,text,timestamp,post_id,user_id) values (false,'dobar post!','2022-04-04',1,"marko123");
insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Pa i nije nestoo','2022-04-04','mirko123',2);
insert into comment (deleted,text,timestamp,user_id,parent_comment_id) values (false,'Zasto?','2022-04-04','marko123',1);

insert into reaction (date, type, comment_id,user_id) values ('2022-04-05',0,1,'marko123');
insert into reaction (date, type, post_id,user_id) values ('2022-04-05',1,1,'marko123');
insert into reaction (date, type, post_id,user_id) values ('2022-04-05',1,1,'mirko123');
insert into reaction (date, type, post_id,user_id) values ('2022-04-05',1,1,'pera123');