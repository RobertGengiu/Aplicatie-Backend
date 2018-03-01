import java.util.LinkedHashMap;
import java.util.Map;

public class Judet {
    private Map<String, Oras> orase;    // map cu orasele

    /**
     * Constructor cu parametrii
     */
    public Judet() {
        orase = new LinkedHashMap<>();
    }

    /**
     * @return Intoarce referinta la map-ul cu orase din judet
     */
    public Map<String, Oras> getOrase() {
        return orase;
    }
}
