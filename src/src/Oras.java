import java.util.LinkedHashMap;
import java.util.Map;

public class Oras {
    private Map<String, Loc> locuri;    // map cu locatile

    /**
     * Constructor cu parametrii
     */
    public Oras() {
        locuri = new LinkedHashMap<>();
    }

    /**
     *@return Intoarce referinta la map-ul cu locuri din oras
     */
    public Map<String, Loc> getLocuri() {
        return locuri;
    }
}
