import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lucasamaral on 19/03/15.
 */
public class Delimitadores {
    public static String DOIS_PONTOS = ":";

    public static Collection<String> delimitadores = new ArrayList<String>();

    static {
        delimitadores.add(DOIS_PONTOS);
    }

    public Boolean getDelimitador(String token) {
        return delimitadores.contains(token);
    }
}
