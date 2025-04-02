package lanceur;

import modele.dao.DaoMiniExcursion;
import gui.JFrameLesExcursions;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.metier.MiniExcursion;

/**
 *
 * @author nicolas
 */
public class LanceurThaliMini {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Variables destinées à initialiser les données affichées au démarrage
        ArrayList<MiniExcursion> lesExcursions;
        try {
            // récupérer la liste des excursions
            lesExcursions = DaoMiniExcursion.getAll();
            
            // Instancier la fenêtre initiale et lui fournir les données à afficher
            JFrameLesExcursions jf = new JFrameLesExcursions();
            jf.remplirJComBoxExcursions(lesExcursions);
            jf.raffraichirJTableEtapes(0);
            jf.setLocation(300, 300);
            jf.setVisible(true);
        } catch (SQLException | IOException ex) {
            String message = String.format("%s %n %s %n","Echec de lecture initiale des données dans la BDD",ex.getMessage());
            Logger.getLogger(LanceurThaliMini.class.getName()).log(Level.SEVERE, message, ex);
            JOptionPane.showMessageDialog(null, message, "Lanceur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        

    }

}
