package test.dao;

import modele.dao.ConnexionBDD;
import modele.dao.DaoMiniExcursion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.metier.MiniExcursion;

/**
 *
 * @author nb
 */
public class TestDaoMiniExcursion {

        
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // Test 1 getAll
        System.out.println("\n Test 1 : TestDaoMiniExcursion.getAll");
        try {
            ArrayList<MiniExcursion> lesExcursions = DaoMiniExcursion.getAll();
            for (MiniExcursion exc : lesExcursions) {
                System.out.println(exc.toStringEtat());
            }
            System.out.println(lesExcursions.size()+" excursions trouvées");
        } catch (SQLException ex) {
            Logger.getLogger(TestDaoMiniExcursion.class.getName()).log(Level.SEVERE, "TestDaoMiniExcursion - échec getAll : ", ex);
        }
        
        
       // Test de getOneById
System.out.println("\n Test 2 : TestDaoMiniExcursion.getOneById");
String codeTest = "E02"; 
try {
    MiniExcursion excursion = DaoMiniExcursion.getOneById(codeTest);
    if (excursion != null) {
        System.out.println("Excursion trouvée : " + excursion);
    } else {
        System.out.println("Aucune excursion trouvée pour le code " + codeTest);
    }
} catch (SQLException ex) {
    System.err.println("Erreur SQL lors du test getOneById : " + ex.getMessage());
}//     Fermeture de la connexion
        try {
            ConnexionBDD.getConnexion().close();
            System.out.println("\nConnexion à la BDD fermée");
        } catch (SQLException ex) {
            Logger.getLogger(TestDaoMiniExcursion.class.getName()).log(Level.SEVERE, "TestDaoMiniExcursion - échec de la fermeture de la connexion : ", ex);
        }

}

} 
