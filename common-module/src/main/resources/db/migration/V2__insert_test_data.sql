insert into organization(name, description) values ('Google',null);
insert into organization(name, description) values ('Modsen',null);
insert into organization(name, description) values ('EPAM',null);

insert into person(firstname, lastname, age, organization_id) values ('Artyom','Khotsin',19,1);
insert into person(firstname, lastname, age, organization_id) values ('Peter','Petrov',36,1);
insert into person(firstname, lastname, age, organization_id) values ('Igor','Petrov',36,1);
insert into person(firstname, lastname, age, organization_id) values ('Ivan','Ivanov',52,1);

insert into place(country,additionaldescription) values ('Belarus','Река 10 км от Минска, пикничок');
insert into place(country,city,street,buildingnumber) values ('Belarus','Minsk','Centralnaya',9);


insert into event(theme,description,date) values ('NewYear',null,'2022-12-31');
insert into event(theme,description,date) values ('Birthday Party',null,'2023-04-28');
insert into event(theme,description,date) values ('Easter',null,'2023-04-26');

insert into party(event_id, person_id, time, place_id) values (1,3,'2022-12-31 19:00:00.000000',2);
insert into party(event_id, person_id, time, place_id) values (2,1,'2023-04-28 12:00:00.000000',1);
insert into party(event_id, person_id, time, place_id) values (3,4,'2023-04-26 12:00:00.000000',1);
insert into party(event_id, person_id, time, place_id) values (1,2,'2023-12-31 19:00:00.000000',2);