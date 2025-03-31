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
}
