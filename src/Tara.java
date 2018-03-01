import java.util.LinkedHashMap;
import java.util.Map;

public class Tara {
    private Map<String, Judet> judete;  // map cu judetele

    /**
     * Constructor fara parametrii
     */
    public Tara() {
        judete = new LinkedHashMap<>();
    }

    /**
     * @return referinta catre map-ul de judete dintr-o tara
     */
    public Map<String, Judet> getJudete() {
        return judete;
    }
}
