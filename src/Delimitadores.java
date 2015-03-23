import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lucasamaral on 19/03/15.
 */
public class Delimitadores {
    public static String DOIS_PONTOS = ":";
    public static String ABRE_CONCHETE = "[";
    public static String FECHA_CONCHETE = "]";
    public static String PONTO = ".";

    public static Collection<String> delimitadores = new ArrayList<String>();

    static {
        delimitadores.add(DOIS_PONTOS);
        delimitadores.add(ABRE_CONCHETE);
        delimitadores.add(FECHA_CONCHETE);
        delimitadores.add(PONTO);
    }

    public Boolean getDelimitador(String token) {
        return delimitadores.contains(token);
    }
}
