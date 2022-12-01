insert into person(firstname, lastname, age) values ('Artyom','Khotsin',19);
insert into person(firstname, lastname, age) values ('Peter','Petrov',36);
insert into person(firstname, lastname, age) values ('Igor','Petrov',36);
insert into person(firstname, lastname, age) values ('Ivan','Ivanov',52);

insert into place(country,additionaldescription) values ('Belarus','Река 10 км от Минска, пикничок');
insert into place(country,city,street,buildingnumber) values ('Belarus','Minsk','Centralnaya',9);

insert into event(theme, description, person_id, time, place_id) values ('NewYear',null,3,'2022-12-31 19:00:00.000000',2);
insert into event(theme, description, person_id, time, place_id) values ('Birthday Party',null,1,'2023-04-28 12:00:00.000000',1);
insert into event(theme, description, person_id, time, place_id) values ('Easter',null,4,'2023-04-26 12:00:00.000000',1);
insert into event(theme, description, person_id, time, place_id) values ('NewYear',null,2,'2023-12-31 19:00:00.000000',2);