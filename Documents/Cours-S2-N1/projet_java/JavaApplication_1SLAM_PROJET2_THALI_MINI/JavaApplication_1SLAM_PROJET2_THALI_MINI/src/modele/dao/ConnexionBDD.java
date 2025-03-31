package modele.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton de connexion à la BDD
 *
 * @author nb
 * @version 2023
 */
public class ConnexionBDD {

    /**
     * Instance de la connexion
     */
    private static Connection cnx;


    private ConnexionBDD() {

    }


    /**
     * Créer une connexion verss le SGBD paramétré dans le fichier de properties
     * @return connexion JDBC obtenue
     * @throws SQLException 
     * @throws java.io.IOException 
     */
    public static Connection getConnexion() throws SQLException {
        if (cnx == null) {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thali?serverTimezone=Europe/Paris", "thali_util", "secret");
            cnx.setAutoCommit(true);
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.INFO, "Connexion réussie à la BDD : "+cnx.getCatalog());
        }
        return cnx;
    }

}
