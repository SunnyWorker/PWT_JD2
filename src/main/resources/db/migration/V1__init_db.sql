create table event(
    id int8 primary key,
    theme varchar(255) not null,
    description varchar(1024),
    person_id int8 not null,
    time timestamp not null,
    place_id int8 not null
);

create table person(
    id int8 primary key,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    age int not null
);

create table place(
    id int8 primary key,
    country varchar(255) not null,
    city varchar(255),
    street varchar(255),
    buildingNumber int4,
    additionalBuildingNumber int4,
    additionalDescription varchar(1024)
);

ALTER table event add constraint FK_Organizer FOREIGN KEY (person_id) references person(id);
ALTER table event add constraint FK_Place FOREIGN KEY (place_id) references place(id);