package test.metier;

import modele.metier.Etape;
import modele.metier.MiniExcursion;
import modele.metier.MiniExcursionPayante;

/**
 *
 * @author nb
 */
public class TestMiniExcursion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\nTest n°1 : instanciation mini excursion");
        MiniExcursion me = new MiniExcursion("E01", "Excursion dans l'île", 8);
        System.out.println(me.toString());
        
        System.out.println("\nTest n°2 : ajout d'étapes");
        me.ajouterEtape(new Etape(1, "traversée aller", 30));
        me.ajouterEtape(new Etape(2, "promenade dans l’île", 60));
        me.ajouterEtape(new Etape(3, "visite de la chapelle", 20));
        me.ajouterEtape(new Etape(4, "visite du phare", 30 ));
        me.ajouterEtape(new Etape(5, "promenade sur une petite crique", 15));
        me.ajouterEtape(new Etape(6, "visite du jardin exotique", 45));
        me.ajouterEtape(new Etape(7, "traversée retour",  30));
        System.out.println(me.toString());
        
        System.out.println("\nTest n°3 : instanciation mini excursion payante");
        MiniExcursionPayante mep = new MiniExcursionPayante("E02", "Randonnée pédestre au \"Cap des vents\"", 35, 25.0f);
        System.out.println(mep.toString());
        
        System.out.println("\nTest n°4 : ajout d'étapes");
        mep.ajouterEtape(new Etape(1, "Trajet aller en car", 45));
        mep.ajouterEtape(new Etape(2, "Randonnée : aller au cap par les bois", 140));
        mep.ajouterEtape(new Etape(3, "Pique-nique", 60));
        mep.ajouterEtape(new Etape(4, "Randonnée : retour du cap par la lande", 120 ));
        mep.ajouterEtape(new Etape(5, "Trajet retour en car", 45));
        System.out.println(mep.toString());
     }
    
}
