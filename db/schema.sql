-- drop table j_model;
-- drop table j_brand;
-- drop table j_model_j_brand;
create table j_model (
    id serial primary key,
    name varchar(2000)
);

create table j_brand (
    id serial primary key,
    name varchar(2000)
);

create table j_model_j_brand(
	model_id int not null references j_model(id),
    brands_id int not null references j_brand(id)
);