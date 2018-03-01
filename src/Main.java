import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
Clasa principala
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        DataBase dataBase = DataBase.getInstance();
        Reader reader = Reader.getInstance();
        reader.read_data(dataBase, "file.in");

        /*
        Afiseaza toate informatiile despre o locatie
         */
        System.out.println("\nPrima cautare\n");
        Loc arc = dataBase.cauta_loc("Romania", "Bucuresti", "Bucuresti",
                "Arcul de Triumf");
        dataBase.afiseaza_loc(arc);

        /*
        Afiseaza cel mai ieftin loc unde poti face shopping
         */
        System.out.println("\nA doua cautare\n");
        dataBase.cauta_dupa_activitate("shopping");

        /*
        Afiseaza top5 cele mai ieftine locatii ce pot fi vizitate in intervalul [a-b]
         */
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\nA treia cautare\n");
        dataBase.cauta_top("Romania", "Bucuresti", "Bucuresti",
                dateFormat.parse("05/02/2018"), dateFormat.parse("25/10/2018"));
    }
}
