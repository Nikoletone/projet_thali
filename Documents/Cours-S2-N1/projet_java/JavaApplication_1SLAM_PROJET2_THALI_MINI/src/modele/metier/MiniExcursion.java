package modele.metier;

import java.util.ArrayList;

/**
 * Décrit les excursions mises en places par THALI
 *
 * @author nb
 * @version 2025
 */
public class MiniExcursion {

    private String code;    // code de l'excursion
    private String libelle; // intitulé de l'excursion
    private int nbPlaces;   // nombre de places au maximum
    private ArrayList<Etape> lesEtapes; // liste ordonnée des étapes de l'excursion

    public MiniExcursion(String code, String libelle, int nbPlaces) {
        this.code = code;
        this.libelle = libelle;
        this.nbPlaces = nbPlaces;
        this.lesEtapes = new ArrayList<>();
    }


    public void ajouterEtape(Etape nouvelleEtape) {
        lesEtapes.add(nouvelleEtape);
    } 

    @Override
    public String toString() {
        String etat = "";
        etat += code + " - " + libelle + " - "+ nbPlaces + " pl.";
        return etat;
    }

    public String toStringEtat() {
        String etat = "";
        etat += "MiniExcursion{" + "code=" + code + ", libelle=" + libelle + ", nbPlaces=" + nbPlaces;
        etat += "\n\tles étapes= ";
        for (int i = 0; i < lesEtapes.size(); i++) {
            Etape e = lesEtapes.get(i);
            etat += "\n\t" + (i + 1) + " : " + e.toString();
        }
        etat += " }";
        return etat;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public ArrayList<Etape> getLesEtapes() {
        return lesEtapes;
    }

    public void setLesEtapes(ArrayList<Etape> lesEtapes) {
        this.lesEtapes = lesEtapes;
    }
    
    

}
