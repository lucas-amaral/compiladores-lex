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

    public static String MAIOR_IGUAL = ">=";
    public static String MENOR_IGUAL = "<=";
    public static String MENOR = "<";
    public static String MAIOR = ">";
    public static String IGUAL = "=";

    public static String E = "&";


    public static Collection<String> operadores = new ArrayList<String>();
    public static Collection<String> caracteresReservadosExpressaoRegular = new ArrayList<String>();

    static {
        operadores.add(MAIS);
        operadores.add(MENOS);
        operadores.add(VEZES);
        operadores.add(DIVISAO);
        operadores.add(MENOR);
        operadores.add(MAIOR);
        operadores.add(MAIOR_IGUAL);
        operadores.add(MENOR_IGUAL);
        operadores.add(IGUAL);
        operadores.add(E);
        caracteresReservadosExpressaoRegular.add(MAIS);
        caracteresReservadosExpressaoRegular.add(VEZES);
    }

    public static Boolean getOperador(String token) {
        return operadores.contains(token);
    }

    public static Boolean getCaracterReservadoExpressaoRegular(String token) {
        return caracteresReservadosExpressaoRegular.contains(token);
    }
}
