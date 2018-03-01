import java.util.*;


/*
Clasa parinte care contine toate informatiile si care trebuie o singura
data initializata
 */
public class DataBase {
    private static volatile DataBase instance = null;
    private Map<String, Tara> tari;     // map cu tarile

    /*
    Constructor private fara parametrii
    */
    private DataBase() {
        tari = new LinkedHashMap<>();
    }

    /*
    Implementare thread safe
     */
    public static DataBase getInstance() {
        if (instance == null) {
            synchronized (DataBase.class) {
                if (instance == null) {
                    instance = new DataBase();
                }
            }
        }
        return instance;
    }

    /**
     * Introduce un Loc in ierahie
     *
     * @param tara     numele tarii
     * @param judet    numele judetului
     * @param oras     numele orasului
     * @param loc      numele locului
     * @param continut datele unei locatii
     */
    public void adauga_loc(String tara, String judet, String oras, String loc,
                           Loc continut) {
        /*
        Adaugam tara in ierarhie, in cazul in care nu exista
         */
        if (!tari.containsKey(tara)) {
            tari.put(tara, new Tara());
        }
        /*
        Adaugam judetul in ierarhie, in cazul in care nu exista
         */
        if (!tari.get(tara).getJudete().containsKey(judet)) {
            tari.get(tara).getJudete().put(judet, new Judet());
        }
        /*
        Adaugam orasul in ierarhie, in cazul in care nu exista
         */
        if (!tari.get(tara).getJudete().get(judet).getOrase().containsKey(oras)) {
            tari.get(tara).getJudete().get(judet).getOrase().
                    put(oras, new Oras());
        }
        /*
        Adaugam noua locatie, pe care am considerat-o unica
         */
        tari.get(tara).getJudete().get(judet).getOrase().get(oras).getLocuri().
                put(loc, continut);
    }

    /**
     * Intoarce locatia de cautat
     *
     * @param tara  numele tarii
     * @param judet numele judetului
     * @param oras  numele orasului
     * @param loc   numele locatie
     * @return
     */
    public Loc cauta_loc(String tara, String judet, String oras,
                         String loc) {
        return tari.get(tara).getJudete().get(judet).getOrase().get(oras).
                getLocuri().get(loc);
    }

    /**
     * Afiseaza toate informatiile despre o locatie
     *
     * @param loc referinta catre o locatie
     */
    public void afiseaza_loc(Loc loc) {
        System.out.println("Nume:" + loc.getNume());
        LinkedList<String> ierahie = loc.getIerahie();
        System.out.println("Adresa: " + ierahie.get(0) + "/" +
                ierahie.get(1) + "/" + ierahie.get(2));
        System.out.println("Pret mediu: " + loc.getPret_mediu());
        System.out.println("Lista de activitati: ");

        for (int i = 0; i < loc.getActivitati().size() - 1; i++) {
            System.out.println(loc.getActivitati().get(i));
        }

        System.out.println("Intervalul in care se poate vizita: " +
                loc.getPrima_zi().toString() + " " + loc.getUltima_zi().
                toString());

    }

    /**
     * Afiseaza topul oraselor ce pot fi vizitate in in intervalul a-b
     *
     * @param tara  numele tarii
     * @param judet numele judetului
     * @param oras  numele orasului
     */

    /**
     * Clasa comparator
     */
    private static class LocComparator implements Comparator<Loc> {

        @Override
        public int compare(Loc loc1, Loc loc2) {
            if (loc1.getPret_mediu() > loc2.getPret_mediu()) {
                return 1;
            }
            return -1;

        }
    }

    /**
     * Afiseaza topul celor mai ieftine locatii in intervalul [a-b]
     * Sortarea se efectueaza cu un priority queue
     *
     * @param tara  numele tarii
     * @param judet numele judetului
     * @param oras  numele orasului
     * @param a     data check-in-ului
     * @param b     data check-out-ului
     */
    public void cauta_top(String tara, String judet, String oras, Date a, Date b) {
        Oras cautat = tari.get(tara).getJudete().get(judet).getOrase().get(oras);
        PriorityQueue<Loc> locatii = new PriorityQueue<>(new LocComparator());
        LinkedList<Loc> locatii_cautate = new LinkedList<>();
        /*
        Iteram pe colectia care contine locurile
         */
        for (String keys : cautat.getLocuri().keySet()) {
            Loc aux = cautat.getLocuri().get(keys);
            Date first = aux.getPrima_zi();
            Date last = aux.getUltima_zi();
            /*
            Daca a >= prima zi si b <= ultima zi
             */
            if (first.before(a) || first.equals(a) && last.after(b) ||
                    last.equals(b)) {
                locatii.add(aux);
            }
        }
        /**
         * Afisam top5
         */
        for (int i = 0; i < 5; i++) {
            Loc element = locatii.peek();
            afiseaza_loc(element);
            locatii.remove(element);
        }

    }

    /**
     * @param activitate_dorita activitatea dupa care cautam cel mai ieftin pret
     */
    public void cauta_dupa_activitate(String activitate_dorita) {
        PriorityQueue<Loc> locatii = new PriorityQueue<>(new LocComparator());
        /*
        Iteram prin tari, judete si orase, locatii, in cautarea de activitati
         */

        for (String keys1 : tari.keySet()) {
            for (String keys2 : tari.get(keys1).getJudete().keySet()) {
                for (String keys3 : tari.get(keys1).getJudete().get(keys2).
                        getOrase().keySet()) {
                    for (String keys4 : tari.get(keys1).getJudete().get(keys2).
                            getOrase().get(keys3).getLocuri().keySet()) {
                        Loc loc = tari.get(keys1).getJudete().get(keys2).
                                getOrase().get(keys3).getLocuri().get(keys4);
                        if (loc.getUltima_zi().compareTo(loc.getPrima_zi())
                                >= 10) {
                            for (String activitati : loc.getActivitati()) {
                                if (activitati.equals(activitate_dorita)) {
                                    locatii.add(loc);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }
        afiseaza_loc(locatii.peek());
    }
}

