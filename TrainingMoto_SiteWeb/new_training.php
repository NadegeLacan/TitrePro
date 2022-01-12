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
    $sql = "SELECT * FROM clubs C INNER JOIN users U ON U.id_user=C.id_user WHERE mail_user=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($user));
    while ($donnees = $resultat->fetch()) {
        $id_club[] = $donnees['id_club'];
        $nom_club[] = $donnees['nom_club'];
        $civilite_resp[] = $donnees['civilite_responsable'];
        $nom_resp[] = $donnees['nom_responsable'];
        $prenom_resp[] = $donnees['nom_responsable'];
    }
    if (sizeof($id_club)<1){
        echo "<script type='text/javascript'>alert('Espace resérvé aux Clubs'); document.location.replace('index.php');</script>";
        exit();
    }
} catch (PDOException $exception) {
    exit('Erreur execution requete sur CLUBS et USERS');
}
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nouvel Entrainement</title>
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
                <?php
                    include "training_15jours.php";
                ?>
    </div>

    <!-- FORMULAIRE DE CREATION D'UN TRAINING -->
    <div class="formTraining">
        <h1>Nouvel entrainement</h1>
        <form method="POST" action="saveTraining.php">
                <h2>
                    <?php
                    echo $nom_club[0].' : '.$civilite_resp[0].' '.$nom_resp[0] = $donnees['nom_responsable'].' '.$prenom_resp[0];
                    echo '<input type="hidden" name="idClub" id="idClub" value="'.$id_club[0].'"/>';
                    ?>
                </h2>
                <fieldset>
                    <div class="champForm">
                    <label for="dateTraining">Date de l'ouverture :</label>
                    <input type="date" name="dateTraining" id="dateTraining" requiered/>
                    </div>
                    <div class="champForm">
                    <label for="openingHour">Heure d'ouverture de la piste :</label>
                    <input type="time" name="openingHour" id="openingHour" required/>
                    </div>
                    <div class="champForm">
                    <label for="closingHour">Heure de fermeture de la piste :</label>
                    <input type="time" name="closingHour" id="closingHour" required/>
                    </div>
                    <div class="champForm">
                    <label for="priceTraining">Prix de l'entrainement :</label>
                    <input type="number" name="priceTraining" id="priceTraining" size="10" required/>
                    </div>
                    <fieldset class="zoneRadio">
                        <legend>Pilotes Kids acceptés</legend>                        
                        <label for="kidAccept">Oui</label>
                        <input type="radio" name="kidAccept" id="kidAcceptOK" value="1" checked/>
                        <label for="kidAccept">Non</label>
                        <input type="radio" name="kidAccept" id="kidAcceptKO" value="0"/>
                    </fieldset>
                </fieldset>
                <input type="reset" name="resetTraining" id="resetTraining" value="REINITIALISER"/>
                <input type="submit" name="validationTraining" id="validationTraining" value="VALIDER"/>
        </form>
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