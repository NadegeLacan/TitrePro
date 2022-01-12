<?php
session_start();
if (!isset($_SESSION['login'])){
   echo "<script type='text/javascript'>alert('Merci de vous connecter'); document.location.replace('index.php');</script>";
   exit();
}
$user = $_SESSION['login'];
if (isset($_SESSION['LAST_ACTIVITY'])){
    if ((time() - $_SESSION['LAST_ACTIVITY']) > (5*60)) {
           session_unset();
           session_destroy();
        }
    $_SESSION['LAST_ACTIVITY'] = time();
}

include "connectBDD.php";

try {
    // requete pour tester si l'utilisateur est bien un pilote
    $sql = "SELECT * FROM pilotes P INNER JOIN users U ON U.id_user=P.id_user WHERE mail_user=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($user));
    $id_pilote = array();
    while ($donnees = $resultat->fetch()) {
            $id_pilote[] = $donnees['id_pilote'];
    }

    // si l'utilisateur n'est pas un pilote on quitte la page sinon on charge la page
    if (sizeof($id_pilote)<1){
       echo "<script type='text/javascript'>alert('Espace resérvé aux Pilotes'); document.location.replace('index.php');</script>";
       exit();
    }
    else {
        // requete pour récupérer la liste des entrainements à proposer
        $sql = "SELECT * FROM entrainements E INNER JOIN clubs C ON E.id_club=C.id_club INNER JOIN villes V ON V.id_ville=C.id_ville ";
        $sql = $sql . " WHERE date_entrainement>=DATE(NOW()) ";
        // on teste si la recherche est filtrée
        if (isset($_POST['date_debut']) && $_POST['date_debut']!=''){
            $sql = $sql . " AND date_entrainement>='".$_POST['date_debut']."' ";
        }
        if (isset($_POST['date_fin']) && $_POST['date_fin']!=''){
            $sql = $sql . " AND date_entrainement<='".$_POST['date_fin']."' ";
        }
        if (isset($_POST['region']) && $_POST['region']!=''){
            $sql = $sql . " AND region='".$_POST['region']."' ";
        }
        if (isset($_POST['departement']) && $_POST['departement']!=''){
            $sql = $sql . " AND departement='".$_POST['departement']."' ";
        }
        $sql = $sql . "ORDER BY date_entrainement";
        $resultat = $pdo->prepare($sql);
        $resultat->execute();
    }
}
catch (PDOException $exception) {
exit('Erreur execution requete liste des entrainements');
}
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Training Moto Cross</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/4d78d9851b.js" crossorigin="anonymous"></script>
</head>
<body>
    <!-- HEADER du site web LOGO MENU COMPTE_USER -->
    <header>
        <img src="photos/logoFFM.jpg" alt="Logo FFM" />
        <?php if (isset($user)){
                    echo '<div class="userConnect">
                           <p>Utilisateur connecté : '.$user.'</p>
                          </div>';
                }
        ?>
        <!-- zone de menu haut de page -->
        <nav class="dropdown_menu">
            <a href="index.php"><i class="fas fa-home fa-2x"></i></a>
            <div class="dropdown_menu_club">
            <p>CLUBS</p>
                 <div class="dropdown_content_menu_club">
                    <p><a href="new_training.php">Nouvel entrainement</a></p>
                    <p><a href="update_training.php">Modifier supprimer un entrainement</a></p>
                    <p><a href="valid_rider.php">Valider des pilotes</a></p>
                 </div>
            </div>
            <div class="dropdown_menu_pilotes">
            <p>PILOTES</p>
                 <div class="dropdown_content_menu_pilotes">
                    <p><a href="list_training.php">Consulter les entrainements</a></p>
                    <p><a href="list_training_dispo.php">S'inscrire à un entrainement</a></p>
                    <p><a href="delete_training_rider.php">Annuler une inscription</a></p>
                 </div>
            </div>
            <a href="list_training.php"><p>ENTRAINEMENTS</p></a>
            <a href="search.php"><i class="fas fa-search fa-2x"></i></a>
        </nav>
        <div class="user_account">
        <a href="user_account.php"><i class="fas fa-user fa-3x"></i></a>
        <a href="logout.php"><i class="fas fa-user-alt-slash"></i></a>
        </div>
    </header>

     <!-- FORMULAIRE DE FILTRE -->
    <div class="frameLeft">
      <h3>Filtrer</h3>
      <form class="filtre" method="POST" action="list_training_dispo.php">
      <div class="champFiltre">
        <label for="date_debut">Date </label></br>
        <span>du </span>
        <input type="date" name="date_debut" id="date_debut" />
        <span>au </span>
        <input type="date" name="date_fin" id="date_fin" />
      </div>
      <div class="champFiltre">
        <label for="region">Région </label>
        <input type="text" name="region" id="region" />
      </div>
      <div class="champFiltre">
        <label for="departement">Département </label>
        <input type="text" name="departement" id="departement" />
      </div>
        <input type="submit" name="validFiltre" id="validFiltre" value="OK">
      </form>
    </div>

    <!-- LISTE DES ENTRAINEMENTS -->
    <div class="frameCenter">
    <h1>Liste des entrainements </h1>
        <?php
        //On affiche la liste des entrainements à partir du résultat de la requête
        while ($donnees = $resultat->fetch()) {
            // on affiche le logo du club enregistré dans photos/logoClub/numero_id.png
            echo '<p>
                    <div class="cardClub">
                        <img class="logoClub" src="photos/logoClub/'.$donnees['id_club'].'.png" alt="logo du cub">';
            echo '<div class="infoClub"> ';
            echo '<p class="listTraining">'.$donnees['date_entrainement'].' '.$donnees['region'].'</p>';
            echo '<p class="listTraining">'.$donnees['nom_club'].' '.$donnees['adresse_club'].' '.$donnees['code_postal'].' '.$donnees['ville'].'</p>';
            if ($donnees['kid_autorise'==1]){
                echo '<p class="listTraining">'.$donnees['pilote_maxi'].' pilotes maximum . Pilotes Kids acceptés </p>';
            }
            else {
                echo '<p class="listTraining">'.$donnees['pilote_maxi'].' pilotes maximum . Pilotes Kids refusés </p>';
            }
            echo '</div>
                  <a href="saveEntryTraining.php?idEntrainement='. $donnees['id_entrainement'] .'&idPilote='. $id_pilote[0] .'">S\'inscrire</a>
                  </div> </p>';
        }
        ?>
    </div>
     <!-- FOOTER-->
    <footer>
        <ul>
            <li><a href="conditionsGenerales.html">Conditions Générales</a></li>
            <li><a href="mentionsLegales.html">Mentions Légales</a></li>
            <li><a href="contactSupport.html">Contact</a></li>
            <li><a href="https://www.ffmoto.org/" target="_blank">Site FFM</a></li>
            <li><a href="https://ffm.engage-sports.com/" target="_blank">Site Engage Sport</a></li>
        </ul>
    </footer>
   </body>
   </html>