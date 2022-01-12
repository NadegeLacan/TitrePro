<?php
session_start();
$user = $_SESSION['login'];
if ($user==null){
   echo "<script type='text/javascript'>alert('Merci de vous connecter'); document.location.replace('index.php');</script>";
   exit();
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
// on teste si c'est un update ou un delete qui a été demandé
if ($_POST['updateTraining']=='MODIFIER'){
    $sql = "UPDATE entrainements SET date_entrainement='".$_POST['dateTraining']."', heure_ouverture='".$_POST['openingHour']."',";
    $sql = $sql . "heure_fermeture='".$_POST['closingHour']."',prix_entrainement=".$_POST['priceTraining'].",kid_autorise=".$_POST['kidAccept'];
    $sql = $sql . " WHERE id_entrainement=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($_POST['idEntrainement']));
}
else {
    // si c'est un delete, on supprime les pilotes inscrits à cet entrainement dans la table inscrire
    // envoi mail d'information aux pilotes à prévoir
    $sql = "DELETE FROM inscrire WHERE id_entrainement=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($_POST['idEntrainement']));
    // suppression dans la table entrainements
    $sql = "DELETE FROM entrainements WHERE id_entrainement=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($_POST['idEntrainement']));
}

//Redirirge vers la page de confirmation
echo "<script type='text/javascript'>document.location.replace('page_confirmation.php');</script>";
}
catch (PDOException $exception) {
exit('Erreur execution mise à jour de l\'entrainement');
}
?>