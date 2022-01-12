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
    // requete pour tester si l'utilisateur est bien un club
    $sql = "SELECT * FROM clubs C INNER JOIN users U ON U.id_user=C.id_user WHERE mail_user=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($user));
    $id_club = array();
    $nom_club=array();
    while ($donnees = $resultat->fetch()) {
            $id_club[] = $donnees['id_club'];
            $nom_club[] = $donnees['nom_club'];
    }

    // si l'utilisateur n'est pas un club on quitte la page sinon on charge la page
    if (sizeof($id_club)<1){
       echo "<script type='text/javascript'>alert('Espace resérvé aux Clubs'); document.location.replace('index.php');</script>";
       exit();
    }
    else
    {
        $id_entrainement_select = null;
        if (isset($_POST['idTraining'])){
            $id_entrainement_select = $_POST['idTraining'];
        }
        if ($id_entrainement_select==null){
            // requete pour récuperer la liste des entrainements de ce club pour alimenter la liste deroulante
            $sql = "SELECT * FROM entrainements WHERE id_club=? AND date_entrainement>=DATE(NOW()) ORDER BY date_entrainement";
            $resultat = $pdo->prepare($sql);
            $resultat->execute(array($id_club[0]));
        }
        else {
            // requete pour récuperer les infos de l'entrainement selectionné pour alimenter le formulaire
            $sql = "SELECT * FROM entrainements WHERE id_club=? AND id_entrainement=?";
            $resultat = $pdo->prepare($sql);
            $resultat->execute(array($id_club[0],$id_entrainement_select));
        }
        $id_entrainement = array();
        $date_entrainement = array();
        $heure_ouverture = array();
        $heure_fermeture = array();
        $prix_entrainement = array();
        $kid_ok = array();
        while ($donnees = $resultat->fetch()) {
           $id_entrainement[] = $donnees['id_entrainement'];
           $date_entrainement[] = $donnees['date_entrainement'];
           $heure_ouverture[] = $donnees['heure_ouverture'];
           $heure_fermeture[] = $donnees['heure_fermeture'];
           $prix_entrainement[] = $donnees['prix_entrainement'];
           $kid_ok[] = $donnees['kid_autorise'];
        }
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
        <?php
            include "training_15jours.php";
        ?>
    </div>

    <!-- FORMULAIRE DE MODIFICATION D'UN TRAINING -->
    <div class="formTraining">
        <h1>Modifier / Supprimer un entrainement</h1>
        <!-- Affichage liste déroulante des entrainements existant pour ce club -->
            <form class="selectTraining" method="POST" action="update_training.php">
                <label for="training">Entrainements </label>
                     <select name="idTraining" id="idTraining">
                          <?php
                              $i=0;
                              while(isset($id_entrainement[$i]) )
                                  {
                                      if ($id_entrainement[$i]==$id_entrainement_select)
                                      {
                                            echo '<option selected value="'.$id_entrainement[$i].'">'.$date_entrainement[$i].' / '.$heure_ouverture[$i].' / '.$heure_fermeture[$i].' </option>';
                                      }
                                      else{
                                            echo '<option value="'.$id_entrainement[$i].'">'.$date_entrainement[$i].' / '.$heure_ouverture[$i].' / '.$heure_fermeture[$i].' </option>';
                                      }
                                      $i++;
                                  }
                          ?>
                     </select>
                     <input type="submit" name="selectTraining" id="selectTraining" value="OK">
            </form>

        <!-- Affichage formulaire rempli si un entrainement a été selectionné et passé en parametre vide sinon -->
        <form method="POST" action="majTraining.php">
            <?php
                echo '<h2>' . $nom_club[0];
                echo '<input type="hidden" name="idEntrainement" id="idEntrainement" value="'.$id_entrainement_select.'"/> </h2>';
                if ($id_entrainement_select!=null){
                    // on alimente le formulaire
                    echo '<fieldset>
                            <div class="champForm">
                                <label for="dateTraining">Date de l\'ouverture :</label>
                                <input type="date" name="dateTraining" id="dateTraining" required value="'.$date_entrainement[0].'"/>
                            </div>
                            <div class="champForm">
                                 <label for="openingHour">Heure d\'ouverture de la piste :</label>
                                 <input type="time" name="openingHour" id="openingHour" required value="'.$heure_ouverture[0].'"/>
                            </div>
                            <div class="champForm">
                                 <label for="closingHour">Heure de fermeture de la piste :</label>
                                 <input type="time" name="closingHour" id="closingHour" required value="'.$heure_fermeture[0].'"/>
                            </div>
                            <div class="champForm">
                                  <label for="priceTraining">Prix de l\'entrainement :</label>
                                  <input type="number" name="priceTraining" id="priceTraining" size="10" required value="'.$prix_entrainement[0].'"/>
                            </div>
                            <fieldset class="zoneRadio">
                                   <legend>Pilotes Kids acceptés</legend>';
                    if ($kid_ok[0]==1){
                        echo '<label for="kidAccept">Oui</label>
                              <input type="radio" name="kidAccept" id="kidAcceptOK" value="1" checked/>
                              <label for="kidAccept">Non</label>
                              <input type="radio" name="kidAccept" id="kidAcceptKO" value="0"/>';
                    }
                    else {
                        echo '<label for="kidAccept">Oui</label>
                              <input type="radio" name="kidAccept" id="kidAcceptOK" value="1" />
                              <label for="kidAccept">Non</label>
                              <input type="radio" name="kidAccept" id="kidAcceptKO" value="0" checked/>';
                    }
                    echo '</fieldset>
                          </fieldset>';
                }
                else {
                    // on affiche le formulaire vide
                    echo '<fieldset>
                            <div class="champForm">
                                <label for="dateTraining">Date de l\'ouverture :</label>
                                <input type="date" name="dateTraining" id="dateTraining" required/>
                            </div>
                            <div class="champForm">
                                 <label for="openingHour">Heure d\'ouverture de la piste :</label>
                                 <input type="time" name="openingHour" id="openingHour" required/>
                            </div>
                            <div class="champForm">
                                 <label for="closingHour">Heure de fermeture de la piste :</label>
                                 <input type="time" name="closingHour" id="closingHour" required/>
                            </div>
                            <div class="champForm">
                                  <label for="priceTraining">Prix de l\'entrainement :</label>
                                  <input type="number" name="priceTraining" id="priceTraining" size="10" required/>
                            </div>
                            <fieldset class="zoneRadio">
                                   <legend>Pilotes Kids acceptés</legend>
                                   <label for="kidAccept">Oui</label>
                                   <input type="radio" name="kidAccept" id="kidAcceptOK" value="1" checked/>
                                   <label for="kidAccept">Non</label>
                                   <input type="radio" name="kidAccept" id="kidAcceptKO" value="0"/>
                            </fieldset>
                          </fieldset>';
                }
            ?>
            <input type="submit" name="updateTraining" id="updateTraining" value="MODIFIER"/>
            <input type="submit" name="updateTraining" id="updateTraining" value="SUPPRIMER"/>
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