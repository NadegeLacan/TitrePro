const express = require("express");
const app = express();
const bcrypt = require("bcrypt");
const path = require("path");
const mysql = require("mysql");
const http = require("http");
const server = http.createServer(app);

/*app
    .set("view engine", "ejs")
    .use(express.json())
    .use(express.static(public));
app.use(express.urlencoded({ extended: true }));*/

//connect db
const connection = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "Samirnadege81",
  database: "trainingmotocross",
});

connection.connect((err) => {
  if (err) throw err;
  console.log("db connected");
});

// ##### HTTP ROUTES ###

// index
app.get(`/`, (req, res) => {
  res.render("index.ejs", { info: "" });
});

// CONNEXION
app.post("/connection", (req, res) => {
  //si utilisateur veut créer un compte
  if (req.body.creationCompte) {
    const query = `SELECT * FROM user WHERE pseudo = "${req.body.pseudoConnexion}";`;

    //check si pseudo exist deja
    connection.query(query, async function (error, results, fields) {
      if (error) throw error;

      if (results.length != 0) {
        res.status(401).render("index.ejs", { info: "User in use" });
      } else {

        //encrypt
        const salt = await bcrypt.genSalt();
        const hashed = await bcrypt.hash(req.body.pwdConnexion, salt);

        const insert = `INSERT INTO user(pseudo,password, connect)
        VALUES ("${req.body.pseudoConnexion}", "${hashed}", ${false} );`;

        // add to database sinon
        connection.query(insert, function (error, results, fields) {
          if (error) throw error;
          res.status(201).render("index.ejs", { info: "User created" });
        });
      }
    });
  } else {

    //s'il veut se connecter
    const query = `SELECT * FROM user WHERE pseudo = "${req.body.pseudoConnexion}";`;

    //check s'il existe
    connection.query(query, (err, result, fields) => {
      if (err) throw err;

      if (result.length === 0) {

        res.status(401).render("index.ejs", { info: "Wrong credentials " });
      } else {
        //check si la password est correct
        bcrypt.compare(
            req.body.pwdConnexion,
            result[0].password,
            (err, compare) => {
              if (err) throw err;

              if (compare) {

                // réponds avec la page de chat si correct
                res.status(200).render("chat.ejs", { id: result[0].id });
              } else {

                res
                    .status(401)
                    .render("index.ejs", { info: "Wrong credentials" });
              }
            }
        );
      }
    });
  }
});
