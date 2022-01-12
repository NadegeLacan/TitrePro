<?php
session_start();
if (isset($_SESSION['login'])){
    $user = $_SESSION['login'];
}
if (isset($_SESSION['LAST_ACTIVITY'])){
    if ((time() - $_SESSION['LAST_ACTIVITY']) > (5*60)) {
           session_unset();
           session_destroy();
        }
    $_SESSION['LAST_ACTIVITY'] = time();
}

include "connectBDD.php";

try {
//on teste si l'utilisateur est un pilote ou un club et on fait un update en fonction
if ($_POST['typeUser']=="pilote"){
    $mail=htmlspecialchars($_POST['emailPilote'], ENT_QUOTES);

    $licence=htmlspecialchars($_POST['licencePilote'], ENT_QUOTES);
    $adresse=htmlspecialchars($_POST['adressePilote'], ENT_QUOTES);
    $cp=htmlspecialchars($_POST['cpPilote'], ENT_QUOTES);
    $ville=htmlspecialchars($_POST['villePilote'], ENT_QUOTES);
    $tel=htmlspecialchars($_POST['telPilote'], ENT_QUOTES);
    $naissance=htmlspecialchars($_POST['dateNaissPilote'], ENT_QUOTES);
    if (isset($_POST['contactPilote'])){
        $contact=htmlspecialchars($_POST['contactPilote'], ENT_QUOTES);
    }
    else {
        $contact='';
    }
    if (isset($_POST['telContactPilote'])){
        $tel_contact=htmlspecialchars($_POST['telContactPilote'], ENT_QUOTES);
    }
    else {
        $tel_contact='';
    }
    if (isset($_POST['respLegalPilote'])){
        $resp=htmlspecialchars($_POST['respLegalPilote'], ENT_QUOTES);
    }
    else {
        $resp='';
    }
    // requete insert utilisateur
    $sql = "INSERT INTO users (mail_user,pwd_user) VALUES ('".$mail."','".password_hash($pass, PASSWORD_BCRYPT )."');";
    $resultat = $pdo->prepare($sql);
    $resultat->execute();
    $sql = "SELECT id_user FROM users WHERE mail_user=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($mail));
    while ($donnees = $resultat->fetch()){
            $id_user=$donnees['id_user'];
    }

    // requete ville insert si inexistante
    $sql = "SELECT id_ville FROM villes WHERE code_postal=? AND ville=?;";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($cp,$ville));
    while ($donnees = $resultat->fetch()){
        $id_ville=$donnees['id_ville'];
    }
    if (!isset($id_ville)){
        // si la ville n'existe pas on la cré et on récupère l'id ensuite
        $sql = "INSERT INTO villes (code_postal,ville) VALUES ('".$cp."','".$ville."');";
        $resultat = $pdo->prepare($sql);
        $resultat->execute();
        $sql = "SELECT id_ville FROM villes WHERE code_postal=? AND ville=?;";
        $resultat = $pdo->prepare($sql);
        $resultat->execute(array($cp,$ville));
        while ($donnees = $resultat->fetch()){
            $id_ville=$donnees['id_ville'];
        }
    }

    //insert pilotes
    $sql = "INSERT INTO pilotes (numero_licence,civilite_pilote,nom_pilote,prenom_pilote,date_naissance,";
    $sql = $sql . "adresse_pilote,tel_pilote,personne_contact,tel_personne_contact,responsable_legal,id_user,id_ville) ";
    $sql = $sql . "VALUES ('".$licence."','".$civilite."','".$nom."','".$prenom."','".$naissance."','".$adresse."','".$tel."','".$contact."','".$tel_contact."','".$resp."',".$id_user.",".$id_ville.");";
    $resultat = $pdo->prepare($sql);
    $resultat->execute();
}
else if ($_POST['typeUser']=="club"){
    $mail=htmlspecialchars($_POST['emailClub'], ENT_QUOTES);
    $pass=htmlspecialchars($_POST['pwdClub'], ENT_QUOTES);
    $civilite=htmlspecialchars($_POST['civilite'], ENT_QUOTES);
    $nom=htmlspecialchars($_POST['nomRespClub'], ENT_QUOTES);
    $prenom=htmlspecialchars($_POST['prenomRespClub'], ENT_QUOTES);
    $nom_club=htmlspecialchars($_POST['nomClub'], ENT_QUOTES);
    $adresse=htmlspecialchars($_POST['adresseClub'], ENT_QUOTES);
    $cp=htmlspecialchars($_POST['cpClub'], ENT_QUOTES);
    $ville=htmlspecialchars($_POST['villeClub'], ENT_QUOTES);
    $tel=htmlspecialchars($_POST['telClub'], ENT_QUOTES);
    $longueur_piste=htmlspecialchars($_POST['longueurPiste'], ENT_QUOTES);
    $pilote_max=htmlspecialchars($_POST['piloteMaxi'], ENT_QUOTES);

    // requete insert utilisateur
    $sql = "INSERT INTO users (mail_user,pwd_user) VALUES ('".$mail."','".password_hash($pass, PASSWORD_BCRYPT )."');";
    $resultat = $pdo->prepare($sql);
    $resultat->execute();
    $sql = "SELECT id_user FROM users WHERE mail_user=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($mail));
    while ($donnees = $resultat->fetch()){
            $id_user=$donnees['id_user'];
    }

    // requete ville insert si inexistante
    $sql = "SELECT id_ville FROM villes WHERE code_postal=? AND ville=?;";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($cp,$ville));
    while ($donnees = $resultat->fetch()){
        $id_ville=$donnees['id_ville'];
    }
    if (!isset($id_ville)){
        // si la ville n'existe pas on la cré et on récupère l'id ensuite
        $sql = "INSERT INTO villes (code_postal,ville) VALUES ('".$cp."','".$ville."');";
        $resultat = $pdo->prepare($sql);
        $resultat->execute();
        $sql = "SELECT id_ville FROM villes WHERE code_postal=? AND ville=?;";
        $resultat = $pdo->prepare($sql);
        $resultat->execute(array($cp,$ville));
        while ($donnees = $resultat->fetch()){
            $id_ville=$donnees['id_ville'];
        }
    }

    //insert clubs
    $sql = "INSERT INTO clubs (nom_club,adresse_club,tel_club,longueur_piste,pilote_maxi,civilite_responsable,nom_responsable,prenom_responsable,id_user,id_ville) ";
    $sql = $sql . "VALUES ('".$nom_club."','".$adresse."','".$tel."',".$longueur_piste.",".$pilote_max.",'".$civilite."','".$nom."','".$prenom."',".$id_user.",".$id_ville.");";
    $resultat = $pdo->prepare($sql);
    $resultat->execute();
}
else {
    exit('Une erreur est survenue');
}
//Redirirge vers la page de confirmation
echo "<script type='text/javascript'>document.location.replace('page_confirmation.php');</script>";
}
catch (PDOException $exception) {
exit('Erreur execution enregistrement du training');
}
?>