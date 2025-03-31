package modele.metier;

/**
 * Décrit une mini-excursion payante
 * @author nb
 * @version  2025
 */
public class MiniExcursionPayante extends MiniExcursion {
    
    private float tarif;
    
    public MiniExcursionPayante(String code, String libelle, int nbPlaces, float tarif) {
        super(code, libelle, nbPlaces);
        this.tarif= tarif;
    }

    @Override
    public String toStringEtat() {
        return "MiniExcursionPayante{" + super.toStringEtat() + "\n\ttarif=" + tarif + '}';
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }    
    
}
