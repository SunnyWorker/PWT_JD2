create table event(
    id bigint primary key,
    theme varchar(255) not null,
    description varchar(1024),
    person_id bigint not null,
    time timestamp not null,
    place_id bigint not null
);

create table person(
    id bigint primary key,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    age tinyint not null
);

create table place(
    id bigint primary key,
    country varchar(255) not null,
    city varchar(255),
    street varchar(255),
    buildingNumber int,
    additionalBuildingNumber int,
    additionalDescription varchar(1024)
);

ALTER table event add constraint FK_Organizer FOREIGN KEY (person_id) references person(id);
ALTER table event add constraint FK_Place FOREIGN KEY (place_id) references place(id);