import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Loc {
    private String nume;                    // numele locului
    private double pret_mediu;              // numar cu zecimale
    private ArrayList<String> activitati;   // lista de activitati
    private Date prima_zi;                  // au formatul dd-mm-yy
    private Date ultima_zi;
    private LinkedList<String> ierahie;     // ierarhia Tara => Judet => Oras

    /**
     * Constructorul unui loc
     * @param nume numele locatiei
     * @param ierarhie   ierarhia in care se afla locul
     * @param pret_mediu pretul mediu cerut
     * @param activitati lista de activitati ce se pot desfasura in acel loc
     * @param prima_zi   ziua deschiderii
     * @param ultima_zi  ziua inchiderii
     */
    public Loc(String nume, LinkedList<String> ierarhie, double pret_mediu,
               ArrayList<String> activitati, Date prima_zi, Date ultima_zi) {
        this.nume = nume;
        this.ierahie = ierarhie;
        this.pret_mediu = pret_mediu;
        this.activitati = activitati;
        this.prima_zi = prima_zi;
        this.ultima_zi = ultima_zi;
    }

    public ArrayList<String> getActivitati() {
        return activitati;
    }

    public double getPret_mediu() {
        return pret_mediu;
    }

    public Date getPrima_zi() {
        return prima_zi;
    }

    public Date getUltima_zi() {
        return ultima_zi;
    }

    public LinkedList<String> getIerahie() {
        return ierahie;
    }

    public String getNume() {
        return nume;
    }
}
