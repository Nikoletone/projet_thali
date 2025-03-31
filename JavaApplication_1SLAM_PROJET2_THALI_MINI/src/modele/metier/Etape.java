package modele.metier;

/**
 * Décrit une étape d'une mini-excursion
 * @author nb
 * @version 2023
 */
public class Etape {
    
    private int numEtape;           // n° d'ordre de l'étape dans son excursion
    private String description;     // présentation courte de l'étape
    private int dureePrevue;        // durée prévue de l'étape en minutes 

    public Etape(int num, String description, int dureePrevue) {
        this.numEtape = num;
        this.description = description;
        this.dureePrevue = dureePrevue;
    }

    @Override
    public String toString() {
        return "Etape{" + "numEtape=" + numEtape + ", description=" + description + ", dureePrevue=" + dureePrevue + '}';
    }

    public int getNumEtape() {
        return numEtape;
    }

    public void setNumEtape(int numEtape) {
        this.numEtape = numEtape;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDureePrevue() {
        return dureePrevue;
    }

    public void setDureePrevue(int dureePrevue) {
        this.dureePrevue = dureePrevue;
    }
    
    
    
}
