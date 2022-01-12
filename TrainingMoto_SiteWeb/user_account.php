<?php
session_start();
if (isset($_SESSION['LAST_ACTIVITY'])){
    if ((time() - $_SESSION['LAST_ACTIVITY']) > (5*60)) {
           session_unset();
           session_destroy();
        }
    $_SESSION['LAST_ACTIVITY'] = time();
}

include "connectBDD.php";

if (isset($_SESSION['login'])){
    $user = $_SESSION['login'];
    // on teste si l'utilisateur connecté est un club ou un pilote
    try {
        $sql = "SELECT * FROM clubs C INNER JOIN users U ON U.id_user=C.id_user INNER JOIN villes V ON V.id_ville=C.id_ville WHERE mail_user=?";
        $resultat = $pdo->prepare($sql);
        $resultat->execute(array($user));
        while ($donnees = $resultat->fetch()) {
            $id_club[] = $donnees['id_club'];
            $nom_club[] = $donnees['nom_club'];
            $civilite_resp_club[] = $donnees['civilite_responsable'];
            $nom_resp_club[] = $donnees['nom_responsable'];
            $prenom_resp_club[] = $donnees['prenom_responsable'];
            $adresse[] = $donnees['adresse_club'];
            $cp[] = $donnees['code_postal'];
            $ville[] = $donnees['ville'];
            $tel[] = $donnees['tel_club'];
            $pilote_maxi[] = $donnees['pilote_maxi'];
            $longueur_piste[] = $donnees['longueur_piste'];
        }
        if (isset($id_club)){
            $type_user="club";
        }
        else {
            $type_user="pilote";
            $sql = "SELECT * FROM pilotes P INNER JOIN users U ON U.id_user=P.id_user INNER JOIN villes V ON V.id_ville=P.id_ville WHERE mail_user=?";
            $resultat = $pdo->prepare($sql);
            $resultat->execute(array($user));
            while ($donnees = $resultat->fetch()) {
                $id_pilote[] = $donnees['id_pilote'];
                $civilite_pilote[] = $donnees['civilite_pilote'];
                $nom_pilote[] = $donnees['nom_pilote'];
                $prenom_pilote[] = $donnees['prenom_pilote'];
                $adresse[] = $donnees['adresse_pilote'];
                $cp[] = $donnees['code_postal'];
                $ville[] = $donnees['ville'];
                $tel[] = $donnees['tel_pilote'];
                $nom_contact[] = $donnees['personne_contact'];
                $tel_contact[] = $donnees['tel_personne_contact'];
                $resp_legal[] = $donnees['responsable_legal'];
            }
        }
    } catch (PDOException $exception) {
        exit('Erreur execution requete');
    }
}

?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Création de compte</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/4d78d9851b.js" crossorigin="anonymous"></script>
</head>
<body>
    <!-- HEADER du site web LOGO MENU COMPTE_USER -->
    <header>
        <img src="photos/logoFFM.jpg" alt="Logo FFM" />
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

<!-- Si un utilisateur est connecté on affiche le formulaire en modification SINON on affiche le formulaire de création compte et connexion -->
    <?php
        if (isset($user)){
            // FORMULAIRE MODIFICATION DE COMPTE
            if ($type_user=="club"){
                // on affiche modification CLUB
                echo '<div class="formAccount">
                      <h1>Modification Compte</h1>
                      <form method="POST" action="updateUser.php?type=club">
                         <input type="hidden" name="typeUser" value="'.$type_user.'"/>
                         <p> ' . $nom_club[0] .'</p>
                         <div class="champForm">
                              <label for="emailClub">Email</label>
                              <input type="mail" name="emailClub" value="'.$user.'" requiered/>
                         </div>
                         <fieldset class="zoneRadio">
                             <legend>Civilité responsable </legend>';
                             if ($civilite_resp_club[0]=="Mr") {
                                echo '<label for="civilite">Mr </label>
                                      <input type="radio" name="civilite" id="mr" value="Mr" checked/>';
                             }
                             else{
                                echo '<label for="civilite">Mr </label>
                                      <input type="radio" name="civilite" id="mr" value="Mr"/>';
                             }
                             if ($civilite_resp_club[0]=="Mme"){
                                echo '<label for="civilite">Mme </label>
                                      <input type="radio" name="civilite" id="mme" value="Mme" checked/>';
                             }
                             else{
                                echo '<label for="civilite">Mme </label>
                                      <input type="radio" name="civilite" id="mme" value="Mme"/>';
                             }
                             if ($civilite_resp_club[0]=="Mlle"){
                                echo '<label for="civilite">Mlle </label>
                                      <input type="radio" name="civilite" id="mlle" value="Mlle" checked/>';
                             }
                             else{
                                echo '<label for="civilite">Mlle </label>
                                      <input type="radio" name="civilite" id="mlle" value="Mlle"/>';
                             }
                echo '</fieldset>
                      <div class="champForm">
                           <label for="nomRespClub">Nom responsable </label>
                           <input type="text" name="nomRespClub" value="'.$nom_resp_club[0].'" requiered/>
                      </div>
                      <div class="champForm">
                            <label for="prenomRespClub">Prénom responsable </label>
                            <input type="text" name="prenomRespClub" value="'.$prenom_resp_club[0].'" requiered/>
                      </div>
                      <div class="champForm">
                            <label for="adresseClub">Adresse </label>
                            <input type="text" name="adresseClub" value="'.$adresse[0].'" requiered/>
                      </div>
                      <div class="champForm">
                             <label for="cpClub">Code postal</label>
                             <input type="text" name="cpClub" value="'.$cp[0].'" requiered/>
                      </div>
                      <div class="champForm">
                             <label for="villeClub">Ville</label>
                             <input type="text" name="villeClub" value="'.$ville[0].'" requiered/>
                      </div>
                      <div class="champForm">
                             <label for="telClub">Téléphone</label>
                             <input type="tel" name="telClub" value="'.$tel[0].'" requiered/>
                      </div>
                      <div class="champForm">
                             <label for="longueurPiste">Longueur de la piste </label>
                             <input type="number" name="longueurPiste" value="'.$longueur_piste[0].'" requiered/>
                      </div>
                      <div class="champForm">
                              <label for="piloteMaxi">Nombre de pilotes maximum </label>
                              <input type="number" name="piloteMaxi" value="'.$pilote_maxi[0].'" requiered/>
                      </div>
                      <input type="reset" name="resetForm" id="resetForm" value="REINITIALISER"/>
                      <input type="submit" name="validForm" id="validForm" value="VALIDER"/>
                </form>
                </div>';
            }
            else if ($type_user=="pilote"){
                //on affiche le formulaire modification PILOTE
                echo '<div class="formAccount">
                      <h1>Modification Compte</h1>
                      <form method="POST" action="updateUser.php?type=pilote">
                         <input type="hidden" name="typeUser" value="'.$type_user.'"/>
                         <p> ' . $civilite_pilote[0] .' '. $nom_pilote[0] .' '. $prenom_pilote[0] .'</p>
                         <div class="champForm">
                               <label for="emailPilote">Email</label>
                               <input type="mail" name="emailPilote" value="'.$user.'" requiered/>
                         </div>
                         <div class="champForm">
                               <label for="adressePilote">Adresse </label>
                               <input type="text" name="adressePilote" value="'.$adresse[0].'" requiered/>
                         </div>
                         <div class="champForm">
                                <label for="cpPilote">Code postal</label>
                                <input type="text" name="cpPilote" value="'.$cp[0].'" requiered/>
                         </div>
                         <div class="champForm">
                                <label for="villePilote">Ville</label>
                                <input type="text" name="villePilote" value="'.$ville[0].'" requiered/>
                         </div>
                         <div class="champForm">
                                <label for="telPilote">Téléphone</label>
                                <input type="tel" name="telPilote" value="'.$tel[0].'" requiered/>
                         </div>
                         <div class="champForm">
                                <label for="contactPilote">Personne à contacter</label>
                                <input type="text" name="contactPilote" value="'.$nom_contact[0].'" />
                         </div>
                         <div class="champForm">
                                <label for="telContactPilote">Téléphone personne à contacter </label>
                                <input type="tel" name="telContactPilote" value="'.$tel_contact[0].'" />
                         </div>
                         <div class="champForm">
                                <label for="respLegalPilote">Responsable légal </label>
                                <input type="text" name="respLegalPilote" value="'.$resp_legal[0].'"/>
                         </div>
                      <input type="reset" name="resetForm" id="resetForm" value="REINITIALISER"/>
                      <input type="submit" name="validForm" id="validForm" value="VALIDER"/>
                </form>
                </div>';
            }
        }
        else {
                // FORMULAIRE DE CREATION D'UN COMPTE
                echo '<div class="formAccount">
                    <h1>Nouveau Compte</h1>
                    <form method="POST" action="user_account.php">
                        <fieldset class="zoneRadio">
                            <legend>Club ou Pilote ?</legend>
                            <label for="typeUser">Pilote</label>
                            <input type="radio" name="typeUser" id="piloteUser" value="pilote" checked/>
                            <label for="typeUser">Club</label>
                            <input type="radio" name="typeUser" id="clubUser" value="club"/>
                        </fieldset>
                        <input type=submit name="typeUserOK" id="typeUserOK" value="OK"/>
                    </form>';

                    if (isset($_POST['typeUser'])){
                        if ($_POST['typeUser']=="pilote"){
                            // on affiche le formulaire PILOTE
                            echo '<form method="POST" action="saveUser.php?type=pilote">
                                 <input type="hidden" name="typeUser" value="'.$_POST['typeUser'].'"/>
                                 <div class="champForm">
                                    <label for="emailPilote">Email</label>
                                    <input type="mail" name="emailPilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="pwdPilote">Mot de passe </label>
                                    <input type="password" name="pwdPilote" minlenght=8 requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="pwdPilote">Confirmation mot de passe </label>
                                    <input type="password" name="pwdPilote" minlenght=8 requiered/>
                                 </div>
                                 <fieldset class="zoneRadio">
                                     <legend>Civilité </legend>
                                     <label for="civilite">Mr </label>
                                     <input type="radio" name="civilite" id="mr" value="Mr" checked/>
                                     <label for="civilite">Mme </label>
                                     <input type="radio" name="civilite" id="mme" value="Mme"/>
                                     <label for="civilite">Mlle </label>
                                     <input type="radio" name="civilite" id="mlle" value="Mlle"/>
                                 </fieldset>
                                 <div class="champForm">
                                    <label for="nomPilote">Nom</label>
                                    <input type="text" name="nomPilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="prenomPilote">Prénom</label>
                                    <input type="text" name="prenomPilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="licencePilote">Numéro de licence</label>
                                    <input type="text" name="licencePilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="adressePilote">Adresse </label>
                                    <input type="text" name="adressePilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="cpPilote">Code postal</label>
                                    <input type="text" name="cpPilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="villePilote">Ville</label>
                                    <input type="text" name="villePilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="telPilote">Téléphone</label>
                                    <input type="tel" name="telPilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="dateNaissPilote">Date de naissance </label>
                                    <input type="date" name="dateNaissPilote" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="contactPilote">Personne à contacter</label>
                                    <input type="text" name="contactPilote"/>
                                 </div>
                                 <div class="champForm">
                                    <label for="telContactPilote">Téléphone personne à contacter </label>
                                    <input type="tel" name="telContactPilote"/>
                                 </div>
                                 <div class="champForm">
                                    <label for="respLegalPilote">Responsable légal </label>
                                    <input type="text" name="respLegalPilote"/>
                                 </div>
                                <input type="reset" name="resetForm" id="resetForm" value="REINITIALISER"/>
                                <input type="submit" name="validForm" id="validForm" value="VALIDER"/>
                            </form>';
                        }
                        else if ($_POST['typeUser']=="club"){
                            // on affiche le formulaire CLUB
                            echo '<form method="POST" action="saveUser.php?type=club">
                                 <input type="hidden" name="typeUser" value="'.$_POST['typeUser'].'"/>
                                 <div class="champForm">
                                    <label for="emailClub">Email</label>
                                    <input type="mail" name="emailClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="pwdClub">Mot de passe </label>
                                    <input type="password" name="pwdClub" minlenght=8 requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="pwdClub">Confirmation mot de passe </label>
                                    <input type="password" name="pwdClub" minlenght=8 requiered/>
                                 </div>
                                 <fieldset class="zoneRadio">
                                     <legend>Civilité responsable </legend>
                                     <label for="civilite">Mr </label>
                                     <input type="radio" name="civilite" id="mr" value="Mr" checked/>
                                     <label for="civilite">Mme </label>
                                     <input type="radio" name="civilite" id="mme" value="Mme"/>
                                     <label for="civilite">Mlle </label>
                                     <input type="radio" name="civilite" id="mlle" value="Mlle"/>
                                 </fieldset>
                                 <div class="champForm">
                                    <label for="nomRespClub">Nom responsable </label>
                                    <input type="text" name="nomRespClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="prenomRespClub">Prénom responsable </label>
                                    <input type="text" name="prenomRespClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="nomClub">Nom du club </label>
                                    <input type="text" name="nomClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="adresseClub">Adresse </label>
                                    <input type="text" name="adresseClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="cpClub">Code postal</label>
                                    <input type="text" name="cpClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="villeClub">Ville</label>
                                    <input type="text" name="villeClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="telClub">Téléphone</label>
                                    <input type="tel" name="telClub" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="longueurPiste">Longueur de la piste </label>
                                    <input type="number" name="longueurPiste" requiered/>
                                 </div>
                                 <div class="champForm">
                                    <label for="piloteMaxi">Nombre de pilotes maximum </label>
                                    <input type="number" name="piloteMaxi" requiered/>
                                 </div>
                                <input type="reset" name="resetForm" id="resetForm" value="REINITIALISER"/>
                                <input type="submit" name="validForm" id="validForm" value="VALIDER"/>
                            </form>';
                        }
                }
            }
        ?>
    </div>

     <!-- ZONE DE CONNEXION -->
     <?php
        if (!isset($user)){
            echo '<div class="frameConnectAccount">
                    <form method="POST" action="connectUser.php">
                           <legend>CONNEXION</legend>
                           <div class="frameConnectInput">
                                  <i class="fas fa-user"></i>
                                  <input type="email" name="emailConnexion" id="emailConnexion" requiered placeholder="identifiant"/>
                           </div>
                           <div class="frameConnectInput">
                                  <i class="fas fa-key"></i>
                                  <input type="password" name="pwdConnexion" id="pwdConnexion" minlenght=8 requiered placeholder="mot de passe"/>
                           </div>
                           <input type="submit" name="validationConnexion" id="validationConnexion" value="OK"/>
                    </form>
                   </div>';
        }
     ?>
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