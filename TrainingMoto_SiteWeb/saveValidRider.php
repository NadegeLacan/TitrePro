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
// Boucle sur la liste des pilotes inscrits
$i=0;
while(isset($_POST['idPilote'.$i])){
    // Requête pour mettre à jour le top pilote_valide dans la  table inscrire
    $sql = "UPDATE inscrire SET pilote_valide=? WHERE id_pilote=? AND id_entrainement=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($_POST['piloteValide'.$i],$_POST['idPilote'.$i],$_POST['idEntrainement'.$i]));
    $i++;
}

//Redirirge vers la page de confirmation
echo "<script type='text/javascript'>document.location.replace('page_confirmation.php');</script>";
}
catch (PDOException $exception) {
exit('Erreur execution enregistrement du training');
}
?>