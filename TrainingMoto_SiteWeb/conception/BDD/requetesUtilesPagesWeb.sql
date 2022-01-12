use trainingmotocross;
/* liste des terrains ouverts dans les  jours à venir sur toutes les pages*/
select clubs.nomClub, clubs.adresseClub, villes.codePostal, villes.ville, clubs.telClub, 
            entrainements.dateEntrainement, entrainements.heureOuverture, entrainements.heureFermeture,
            entrainements.prixEntrainement, entrainements.kidAutorise
from clubs
inner join villes on villes.idVille=clubs.idVille
inner join entrainements on entrainements.idClub=clubs.idClub
where entrainements.dateEntrainement<=adddate(date(now()),15)
and entrainements.dateEntrainement>=date(now())
order by entrainements.dateEntrainement;

/*ChangeTrainingClub.html récupération des infos clubs */
select * 
from clubs 
inner join villes on villes.idVille=clubs.idVille
where idUser="";

/*détail de l'entrainement sélectionné */
select * 
from entrainements
where entrainements.dateEntrainement= ""
and idClub= "";

/*modification de l'entrainement*/
update entrainements 
set entrainements.dateEntrainement="", entrainements.heureOuverture="",entrainements.heureFermeture="",entrainements.prixEntrainement=x
where entrainements.dateEntrainement="" 
and idClub="";

/*deleteRider.html affichage info du pilotes*/
select * 
from pilotes 
inner join villes on villes.idVille=pilotes.idVille 
where idUser="";

/*suppression infos pilotes */
delete from pilotes 
where idUser="";

/*deleteTrainingRider.html AFFICHER LA LISTE DES TRAINING SUR LESQUELS LE PILOTE EST INSCRIT */
select * 
from entrainements
inner join inscrire on inscrire.idEntrainement=entrainements.idEntrainement
inner join pilotes on pilotes.idPilote=inscrire.idPilote
where pilotes.idUser="";

/* suppression du training selectionné*/
delete from inscrire
where idPilote=""
and idEntrainement="";

/*registerClub.html enregsitrement du club */
select idVille from villes where codePostal="" and ville="";
insert into clubs (nomClub,adresseClub,telClub,longueurPiste,piloteMaxi,kidAutorise,civiliteResponsable,nomResponsable,prenomResponsable,idUser,idVille) 
values ();

/*registerClubChange.html recupération données club */
select * 
from clubs 
inner join villes on villes.idVille=clubs.IdVille
inner join users on users.idUser=clubs.idUser
where clubs.idUser=""; 

/*modification données club */
select idVille from villes where codePostal="" and ville="";
update clubs 
set adresseClub="",telClub="",longueurPiste=x,piloteMaxi=x,kidAutorise=choix,civiliteResponsable="",nomResponsable="",prenomResponsable="",idVille=x
where idUser="";

/*registerRider.html enregsitrement du pilote */
select idVille from villes where codePostal="" and ville="";
insert into pilotes (numeroLicence,civilitePilote,nomPilote,prenomPilote,dateNaissance,adressePilote,telPilote,personneContact,telPersonneContact,responsableLegal,idUser,idVille) 
values ();

/*registerRiderChange.html recupération données pilotes */
select * 
from pilotes 
inner join villes on villes.idVille=clubs.IdVille
inner join users on users.idUser=clubs.idUser
where pilotes.idUser=""; 

/*modification données pilotes */
select idVille from villes where codePostal="" and ville="";
update pilotes 
set dateNaissance="",adressePilote="",telPilote="",personneContact="",telPersonneContact="",responsableLegal="",idVille=x
where idUser="";

/*new_training.php recupération des infos clubs */
select *
from clubs
inner join users on users.idUser=clubs.idUser
inner join villes on villes.idVille=clubs.idVilles;

/*enregistrement du training en BDD*/
insert into entrainements(dateEntrainement,heureOuverture,heureFermeture,prixEntrainement,kidAutorise,idClub)
values ();

/*registerTrainingValidRider.html */
select *
from pilotes 
inner join inscrire on inscrire.idPilote=pilotes.idPilote
inner join entrainements on entrainements.idEntrainement=inscrire.idEntrainement
inner join clubs on clubs.idClub=entrainements.idClub
where inscrire.piloteValide=false
and clubs.idUser="";

/*valider l'inscription du pilote en BDD */
update inscrire
set piloteValide=true
where idPilote=""
and idEntrainement="";

/*annuler inscription pilote en BDD*/
delete from inscrire
where idPilote=""
and idEntrainement="";

/*selectionTrainingClub.html liste des entrainements à venir pour un club*/
select * 
from entrainements
inner join clubs on clubs.idClub=entrainements.idClub
where dateEntrainement>=date(now());

/*suppression de l'entrainement selectionné*/
delete from entrainements
where idEntrainement="";

/* trainingList.html liste des entrainements de la région sélectionnée*/
select * 
from entrainements
inner join clubs on clubs.idClub=entrainements.idClub
inner join villes on clubs.idVille=villes.idVille
where villes.region=""
and entrainements.dateEntrainement>=date(now())
order by entrainements.dateEntrainement;

