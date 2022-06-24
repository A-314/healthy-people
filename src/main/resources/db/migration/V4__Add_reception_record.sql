create table reception_record(
    id serial not null primary key,
    date date not null,
    interval_id integer  not null,
    patient_id integer ,
    name_new_patient varchar,
    description varchar
);
alter table if exists record add constraint reception_record_interval_fk foreign key (interval_id) references interval;
alter table if exists record add constraint reception_record_patient_fk foreign key (patient_id) references patient;