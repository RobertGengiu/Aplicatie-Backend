import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
Clasa care se ocupa cu citirea fisierlor - Singleton
 */
public class Reader {
    private static Reader instance = null;

    /*
    Constructor privat fara parametrii
     */
    private Reader() {
    }

    /*
    Implementare thread safe
     */
    public static Reader getInstance() {
        if (instance == null) {
            synchronized (DataBase.class) {
                if (instance == null) {
                    instance = new Reader();
                }
            }
        }
        return instance;
    }

    /**
     * Citeste date de intrare
     *
     * @param dataBase locul unde sunt stocate informatiile
     * @param filename numele fisierul de intrare
     * @throws IOException
     * @throws ParseException
     */
    void read_data(DataBase dataBase, String filename) throws IOException,
            ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        StringTokenizer stringTokenizer;
        /*
        Datele pentru fiecare loc se gasesc pe o linie individuala
        <nume>.<tara>.<judet>-<oras>.<pret>.<nr_activ> ...... <dat1>.<date2>
         */
        while (line != null) {
            stringTokenizer = new StringTokenizer(line, ".");
            ArrayList<String> activitati = new ArrayList<>();
            LinkedList<String> ierarhie = new LinkedList<>();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            String nume = stringTokenizer.nextToken();
            String tara = stringTokenizer.nextToken();
            String judet = stringTokenizer.nextToken();
            String oras = stringTokenizer.nextToken();
            double pret_mediu = Double.parseDouble(stringTokenizer.nextToken());
            int numarul_de_activitati = Integer.parseInt(stringTokenizer.
                    nextToken());

            while (numarul_de_activitati > 0) {
                activitati.add(stringTokenizer.nextToken());
                numarul_de_activitati--;
            }

            Date date1 = dateFormat.parse(stringTokenizer.nextToken());
            Date date2 = dateFormat.parse(stringTokenizer.nextToken());
            ierarhie.add(tara);
            ierarhie.add(judet);
            ierarhie.add(oras);
            Loc continut = new Loc(nume, ierarhie, pret_mediu, activitati, date1,
                    date2);
            dataBase.adauga_loc(tara, judet, oras, nume, continut);

            /*
            Citim urmatoarea linie
             */
            line = reader.readLine();
        }
    }
}
