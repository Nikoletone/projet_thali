package modele.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.metier.Etape;

/**
 * Liason BDD - classe métier Etape
 * @author nb
 * @version 2023
 */
public class DaoEtape {

    /**
     * Rechercher un enregistrement dans la table ETAPE d'après son identifiant
     * conceptuel (code excursion, n° d'étape) et en faire un objet de type
     * Etape
     *
     * @param codeExcursion code de l'excursion à laquelle l'étape recherchée
     * est rattachée
     * @param numEtape numéro d'ordre de l'étape dans cette excursion
     * @return objet de type Etape si trouvé dans la BDD, null sinon
     * @throws SQLException
     */
    public static Etape getOneById(String codeExcursion, int numEtape) throws SQLException {
        Etape etapeTrouvee = null;
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Etape WHERE CodeExcursion = ? AND NumETape = ?");
        pstmt.setString(1, codeExcursion);
        pstmt.setInt(2, numEtape);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            etapeTrouvee = new Etape(
                    rs.getInt("NumEtape"),
                    rs.getString("Description"),
                    rs.getInt("DureePrevue")
            );
        }
        return etapeTrouvee;
    }

    /**
     * Extraire l'ensemble des enregistrements de la table SERVICE
     *
     * @param codeExcursion code de l'excursion à laquelle les étapes
     * recherchées sont rattachées
     * @return liste d'objets de type Etape
     * @throws SQLException
     */
    public static ArrayList<Etape> getAllByExcursion(String codeExcursion) throws SQLException {
        ArrayList<Etape> lesEtapesTrouvees = new ArrayList<>();
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Etape WHERE CodeExcursion = ? ORDER BY NumEtape");
        pstmt.setString(1, codeExcursion);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Etape unEtape = new Etape(
                    rs.getInt("NumEtape"),
                    rs.getString("Description"),
                    rs.getInt("DureePrevue")
            );

            lesEtapesTrouvees.add(unEtape);
        }
        return lesEtapesTrouvees;
    }
    
    /**
     * n° de la dernière étape d'une excursion
     * @param codeExcursion
     * @return nombre d'étapes de l'excursion d'id codeExcursion
     * @throws java.sql.SQLException
     */
    public static int getNbEtapesByExcursion(String codeExcursion) throws SQLException {
        int nb;
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("SELECT COUNT(*) AS Nb FROM Etape WHERE CodeExcursion = ?");
        pstmt.setString(1, codeExcursion);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            nb = rs.getInt(1);
        } else {
            nb = -1;
        }
        return nb;
    }
    

// 
    /**
     * Ajouter une étape à une excursion en tant que dernière étape
     * @param codeExcursion
     * @param uneEtape
     * @return numéro de l'étape ajoutée ou 0 si échec
     * @throws SQLException 
     */
    public static int insertFin(String codeExcursion, Etape uneEtape) throws SQLException {
        int retour;
        int nbEtapes;
        int nbEnreg;
        // déterminer le nombre d'étapes de l'excursion
        nbEtapes = getNbEtapesByExcursion(codeExcursion);
        // ajouter comme dernière étape
        Connection cnx = ConnexionBDD.getConnexion();
        PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO Etape VALUES (null, ?, ?, ?, ?)");
        pstmt.setString(1, codeExcursion);
        pstmt.setInt(2, nbEtapes + 1);
        pstmt.setString(3, uneEtape.getDescription());
        pstmt.setInt(4, uneEtape.getDureePrevue());
        nbEnreg = pstmt.executeUpdate();
        if (nbEnreg > 0){
            retour = nbEtapes+1;
        }else{
            retour = 0;
        }
        return retour;
    }



}
