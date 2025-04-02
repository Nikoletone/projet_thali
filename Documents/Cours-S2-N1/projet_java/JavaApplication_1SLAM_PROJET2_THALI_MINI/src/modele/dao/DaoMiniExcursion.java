  
 package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Etape;
import modele.metier.MiniExcursion;
import modele.metier.MiniExcursionPayante;

/**
 *
 * @author nb
 */
public class DaoMiniExcursion {


    /**
     * Extraire l'ensemble des enregistrements de la table EXCURSION
     *
     * @return liste d'objets de type MiniExcursion
     * @throws SQLException
     */
    public static ArrayList<MiniExcursion> getAll() throws SQLException {
        ArrayList<MiniExcursion> lesExcursionsTrouvees = new ArrayList<>();
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Excursion");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            MiniExcursion e;
            float tarif = rs.getFloat("Tarif");
            if (tarif == 0.0f) {
                e = new MiniExcursion(
                        rs.getString("Code"),
                        rs.getString("Libelle"),
                        rs.getInt("NbPlaces")
                );
            } else {
                e = new MiniExcursionPayante(
                        rs.getString("Code"),
                        rs.getString("Libelle"),
                        rs.getInt("NbPlaces"),
                        rs.getFloat("Tarif")
                );
            }
            // ajouter les étapes
            ArrayList<Etape> lesEtapes = DaoEtape.getAllByExcursion(rs.getString("Code"));
            e.setLesEtapes(lesEtapes);
            lesExcursionsTrouvees.add(e);

        }
        return lesExcursionsTrouvees;
    } 
/**
 * Récupère une MiniExcursion à partir de son code unique.
 *
 * @param codeExcursion Le code de l'excursion à rechercher.
 * @return Une instance de MiniExcursion si elle est trouvée, sinon null.
 * @throws SQLException En cas d'erreur lors de l'exécution de la requête SQL.
 */
public static MiniExcursion getOneById(String codeExcursion) throws SQLException {
    // Initialisation de l'objet MiniExcursion qui contiendra le résultat
    MiniExcursion miniexcurtionTrouvee = null;

    // Définition de la requête SQL avec un paramètre (le code de l'excursion)
    String query = "SELECT * FROM excursion WHERE Code = ?"; 

    // Utilisation d'un bloc try-with-resources pour gérer automatiquement la fermeture des ressources
    try (Connection cnx = ConnexionBDD.getConnexion(); // Connexion à la base de données
         PreparedStatement pstmt = cnx.prepareStatement(query)) { // Préparation de la requête SQL
        
        // Affectation du paramètre à la requête (remplacement du ? par la valeur du codeExcursion)
        pstmt.setString(1, codeExcursion);

        // Exécution de la requête et récupération des résultats dans un ResultSet
        try (ResultSet rs = pstmt.executeQuery()) {
            // Vérification si un résultat a été trouvé
            if (rs.next()) {
                // Création de l'objet MiniExcursion avec les valeurs récupérées depuis la base
                miniexcurtionTrouvee = new MiniExcursion(
                    rs.getString("Code"), // Récupération du code de l'excursion
                    rs.getString("libelle"), // Récupération du libellé de l'excursion
                    rs.getInt("NbPlaces")  // Récupération du nombre de places disponibles
                );
            }
        }
    }
    // Retourne l'excursion trouvée (ou null si aucune excursion ne correspond au code donné)
    return miniexcurtionTrouvee; 
}

}


   