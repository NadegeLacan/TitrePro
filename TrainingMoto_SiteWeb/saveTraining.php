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
// Requête pour insérer les données dans la table entrainements
$sql_insert = "INSERT INTO entrainements (date_entrainement,heure_ouverture,heure_fermeture,prix_entrainement,kid_autorise,id_club) ";
$sql_insert = $sql_insert. " VALUES('".$_POST['dateTraining']."','".$_POST['openingHour']."','".$_POST['closingHour']."','".$_POST['priceTraining']."',".$_POST['kidAccept'].",".$_POST['idClub'].");";
$resultat = $pdo->prepare($sql_insert);
$resultat->execute();
//Redirirge vers la page de confirmation
echo "<script type='text/javascript'>document.location.replace('page_confirmation.php');</script>";
}
catch (PDOException $exception) {
exit('Erreur execution enregistrement du training');
}
?>