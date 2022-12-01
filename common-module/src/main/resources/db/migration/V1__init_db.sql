create table Event
(
    id          bigserial       not null,
    date        date         not null,
    description varchar(1024),
    theme       varchar(255) not null,
    primary key (id)
);

create table Organization
(
    id          bigserial       not null,
    description varchar(1024),
    name        varchar(255) not null,
    primary key (id)
);

create table Party
(
    id        bigserial       not null,
    time      timestamp(6) not null,
    event_id  bigint,
    person_id bigint       not null,
    place_id  bigint       not null,
    primary key (id)
);

create table Person
(
    id              bigserial    not null,
    age             smallint     not null,
    firstName       varchar(255) not null,
    lastName        varchar(255) not null,
    organization_id bigint,
    primary key (id)
);

create table Place
(
    id                       bigserial       not null,
    additionalBuildingNumber integer,
    additionalDescription    varchar(1024),
    buildingNumber           integer,
    city                     varchar(255),
    country                  varchar(255) not null,
    street                   varchar(255),
    primary key (id)
);

create table Invitation
(
    person_id bigint not null,
    party_id  bigint not null,
    primary key (person_id,party_id)
);

alter table if exists Party add constraint FK_party_event foreign key (event_id) references Event;
alter table if exists Party add constraint FK_party_person foreign key (person_id) references Person;
alter table if exists Invitation add constraint FK_invitation_person foreign key (person_id) references Person;
alter table if exists Invitation add constraint FK_invitation_party foreign key (party_id) references Party;
alter table if exists Party add constraint FK_party_place foreign key (place_id) references Place;
alter table if exists Person add constraint FK_person_organization foreign key (organization_id) references Organization;