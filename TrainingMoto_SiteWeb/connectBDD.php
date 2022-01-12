<?php

// Création du DSN

$dsn = 'mysql:host=localhost;dbname=training_motocross;port=3306;charset=utf8';

// Création et test de la connexion

try {
	$pdo = new PDO($dsn,'root','Samirnadege81');
}
catch (PDOException $exception) {
	exit('Erreur de connexion à la base de données');
}
?>