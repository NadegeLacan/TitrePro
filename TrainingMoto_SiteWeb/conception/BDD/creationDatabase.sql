drop database trainingMotocross;
create database training_motocross;
use training_motocross;

create table users (
id_user bigint not null primary key auto_increment,
mail_user varchar(50) not null,
pwd_user varchar(200) not null
);

create table villes (
id_ville bigint not null primary key auto_increment,
code_postal varchar(50),
ville varchar(100)
);

create table pilotes (
id_pilote bigint not null primary key auto_increment,
numero_licence varchar(50) not null,
civilite_pilote varchar(50),
nom_pilote varchar(50),
prenom_pilote varchar(50),
date_naissance date,
adresse_pilote varchar(200),
tel_pilote varchar(50),
personne_contact varchar(100),
tel_personne_contact varchar(50),
responsable_legal varchar(100),
id_user bigint,
id_ville bigint,
foreign key (id_user) references users(id_user),
foreign key (id_ville) references villes(id_ville)
); 

create table clubs (
id_club bigint not null primary key auto_increment,
nom_club varchar(50) not null,
adresse_club varchar(200),
tel_club varchar(50),
longueur_piste int,
pilote_maxi int,
kid_autorise boolean,
civilite_responsable varchar(50),
nom_responsable varchar(50),
prenom_responsable varchar(50),
id_user bigint,
id_ville bigint,
foreign key (id_user) references users(id_user),
foreign key (id_ville) references villes(id_ville)
); 

create table entrainements (
id_entrainement bigint not null primary key auto_increment,
date_entrainement date,
heure_ouverture time,
heure_fermeture time,
prix_entrainement int,
kid_autorise boolean,
id_club bigint,
foreign key (id_club) references clubs(id_club)
);

create table inscrire (
id_pilote bigint,
id_entrainement bigint,
primary key (id_pilote,id_entrainement),
foreign key (id_pilote) references pilotes(id_pilote),
foreign key (id_entrainement) references entrainements(id_entrainement)
);

alter table inscrire
add pilote_valide boolean;

alter table villes
add region varchar(100);

