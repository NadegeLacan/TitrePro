<?php
include "connectBDD.php";
try {
$sql = "SELECT * FROM entrainements E INNER JOIN clubs C ON E.id_club=C.id_club INNER JOIN villes V ON V.id_ville=C.id_ville ";
$sql = $sql . " WHERE date_entrainement>=DATE(NOW()) AND date_entrainement<=ADDDATE(DATE(NOW()),15) ORDER BY date_entrainement";
$resultat = $pdo->prepare($sql);
$resultat->execute();

//On récupère la liste des entrainements des 15 derniers jours pour affichage
$date =null;
while ($donnees = $resultat->fetch()) {
    if ($date!=$donnees['date_entrainement'] && $date!=null){
        echo '</p>';
    }
    if ($date!=$donnees['date_entrainement']){
       echo '<p class="dateTraining">' . $donnees['date_entrainement'];
       $date=$donnees['date_entrainement'];
    }
    // on affiche le logo du club enregistré dans photos/logoClub/numero_id.jpg
    echo '<div class="cardClub">
            <img class="logoClub" src="photos/logoClub/'.$donnees['id_club'].'.png" alt="logo du cub">';
    echo '<div class="infoClub"> ';
    echo '<p class="listTraining">'.$donnees['region'].'</p>';
    echo '<p class="listTraining">'.$donnees['nom_club'].'</p>';
    echo '</div> </div>';
}
echo '</p>';
}
catch (PDOException $exception) {
exit('Erreur execution requete liste des entrainements');
}
?>