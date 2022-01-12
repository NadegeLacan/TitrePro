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
// Requête pour insérer les données dans la table inscrire
$sql_insert = "INSERT INTO inscrire (id_pilote,id_entrainement,pilote_valide) ";
$sql_insert = $sql_insert. " VALUES(".$_GET['idPilote'].",".$_GET['idEntrainement'].",0);";
$resultat = $pdo->prepare($sql_insert);
$resultat->execute();
//Redirirge vers la page de confirmation
echo "<script type='text/javascript'>document.location.replace('page_confirmation.php');</script>";
}
catch (PDOException $exception) {
exit('Erreur execution enregistrement du training');
}
?>