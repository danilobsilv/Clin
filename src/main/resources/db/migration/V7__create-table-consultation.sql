create table consultation(

    id bigint not null auto_increment,
    doctor_id bigint not null,
    patient_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultation_doctor_id foreign key(doctor_id) references medicos(id),
    constraint fk_consultation_patient_id foreign key(patient_id) references pacientes(id)

);