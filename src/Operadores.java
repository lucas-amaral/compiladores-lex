import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lucasamaral on 23/03/15.
 */
public class Operadores {
    public static String MAIS = "+";
    public static String MENOS = "-";
    public static String VEZES = "*";
    public static String DIVISAO = "/";

    public static String MENOR = "<";
    public static String MAIOR = ">";

    public static Collection<String> operadores = new ArrayList<String>();

    static {
        operadores.add(MAIS);
        operadores.add(MENOS);
        operadores.add(VEZES);
        operadores.add(DIVISAO);
        operadores.add(MENOR);
        operadores.add(MAIOR);
    }

    public static Boolean getOperador(String token) {
        return operadores.contains(token);
    }
}
