
create sequence hibernate_sequence start 1 increment 1;

create table doctor (
    id int8 not null,
    person_id int4,
    primary key (id)
);
create table patient (
    id int8 not null,
    person_id int4,
    primary key (id)
);
create table person (
    id int4 not null,
    birth_date date,
    email varchar(255),
    fact_address varchar(255),
    name varchar(255),
    patronymic varchar(255),
    phone varchar(255),
    second_phone varchar(255),
    surname varchar(255),
    primary key (id)
    );
create table therapy (
    id int8 not null,
    description varchar(255),
    diagnosis varchar(255),
    doctor_id int8,
    patient_id int8,
    primary key (id)
    );
create table treatment (id int8 not null,
    date date,
    description varchar(255),
    new_patient boolean,
    patient_name varchar(255),
    patient_id int8,
    primary key (id)
    );

alter table if exists doctor add constraint doctor_person_fk foreign key (person_id) references person;
alter table if exists patient add constraint patient_person_fk foreign key (person_id) references person;
alter table if exists therapy add constraint therapy_doctor_fk foreign key (doctor_id) references doctor;
alter table if exists therapy add constraint therapy_patient_fk foreign key (patient_id) references patient;
alter table if exists treatment add constraint therapy_patient_fk foreign key (patient_id) references patient;
