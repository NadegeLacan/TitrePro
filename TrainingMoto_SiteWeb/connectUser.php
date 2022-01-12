<?php
session_start();
$_SESSION['login']=$_POST['emailConnexion'];
$_SESSION['LAST_ACTIVITY'] = time();

include 'connectBDD.php';

// Requête pour tester la connexion de l'utilisateur
$mail=$_POST['emailConnexion'];
$pass=$_POST['pwdConnexion'];

try {
    $sql="SELECT * FROM users WHERE mail_user=?";
    $resultat = $pdo->prepare($sql);
    $resultat->execute(array($mail));

//Afficher l'accueil si OK ou msg erreur si KO
while ($donnees = $resultat->fetch()){
    if (password_verify($pass, $donnees['pwd_user'])){
        echo "<script type='text/javascript'>document.location.replace('index.php');</script>";
        exit();
    }
}

$_SESSION['login']=null;
echo "<script type='text/javascript'>alert('Utilisateur inexistant'); document.location.replace('index.php');</script>";
exit();
}
 catch (PDOException $exception) {
 exit('Erreur de connexion à la base de données');

 }
?>