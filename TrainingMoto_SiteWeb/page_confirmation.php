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

     <!-- ZONE INFO LISTE DES TERRAINS OUVERTS CHARGEE AVEC LES RESULTATS DE LA REQUETE-->
    <div class="frameLeft">
        <h3>Terrains ouverts</h3>
        <?php include "training_15jours.php"; ?>
    </div>

    <div>
        <h1>Enregistrement effectué </h1>
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