import java.util.ArrayList;
import java.util.Collection;

/**
 * 19/03/15.
 * Classe responsável por guardar todas os delimitadores da linguagem RS
 */
public class Delimitadores {
    public static String DOIS_PONTOS = ":";
    public static String ABRE_CONCHETE = "[";
    public static String FECHA_CONCHETE = "]";
    public static String PONTO = ".";
    public static String DOIS_PONTOS_TRACO = ":-";
    public static String DOIS_PONTOS_IGUAL = ":=";
    public static String SETA = "===>";
    public static String ATRIBUICAO = "--->";
    public static String VIRGULA = ",";
    public static String ABRE_PARENTESES = "(";
    public static String FECHA_PARENTESES = ")";
    public static String HASH = "#";

    public static Collection<String> delimitadores = new ArrayList<String>();
    public static Collection<String> caracteresReservadosExpressaoRegular = new ArrayList<String>();

    static {
        delimitadores.add(DOIS_PONTOS);
        delimitadores.add(ABRE_CONCHETE);
        delimitadores.add(FECHA_CONCHETE);
        delimitadores.add(PONTO);
        delimitadores.add(DOIS_PONTOS_TRACO);
        delimitadores.add(DOIS_PONTOS_IGUAL);
        delimitadores.add(SETA);
        delimitadores.add(VIRGULA);
        delimitadores.add(ATRIBUICAO);
        delimitadores.add(ABRE_PARENTESES);
        delimitadores.add(FECHA_PARENTESES);
        delimitadores.add(HASH);
    }

    static  {
        caracteresReservadosExpressaoRegular.add(ABRE_PARENTESES);
        caracteresReservadosExpressaoRegular.add(ABRE_CONCHETE);
        caracteresReservadosExpressaoRegular.add(FECHA_PARENTESES);
        caracteresReservadosExpressaoRegular.add(FECHA_CONCHETE);
        caracteresReservadosExpressaoRegular.add(PONTO);
    }

    //Método para verificar se token é um delimitador da linguagem
    public static Boolean getDelimitador(String token) {
        return delimitadores.contains(token);
    }

    //Método que verifica se o delimitador é algum símbulo reservado da linguagem das expressões regulares em java
    public static Boolean getCaracterReservadoExpressaoRegular(String token) {
        return caracteresReservadosExpressaoRegular.contains(token);
    }
}
