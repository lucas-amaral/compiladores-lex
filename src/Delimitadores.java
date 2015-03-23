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
    public static String DOIS_PONTOS_TRACO = ":-";
    public static String ATRIBUICAO = "===>";
    public static String VIRGULA = ",";

    public static Collection<String> delimitadores = new ArrayList<String>();

    static {
        delimitadores.add(DOIS_PONTOS);
        delimitadores.add(ABRE_CONCHETE);
        delimitadores.add(FECHA_CONCHETE);
        delimitadores.add(PONTO);
        delimitadores.add(DOIS_PONTOS_TRACO);
        delimitadores.add(ATRIBUICAO);
        delimitadores.add(VIRGULA);
    }

    public Boolean getDelimitador(String token) {
        return delimitadores.contains(token);
    }
}
