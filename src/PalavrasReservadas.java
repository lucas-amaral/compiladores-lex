import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lucasamaral on 19/03/15.
 */
public class PalavrasReservadas {
    public static String MODULE = "module";
    public static String INITIALLY = "initially";

    public static String S = "s";
    public static String TICK = "tick";

    /**Sinais*/
    public static String INPUT = "input";
    public static String OUTPUT = "output";
    public static String T_SIGNAL = "t_signal";
    public static String P_SIGNAL = "p_signal";

    /**Variável*/
    public static String VAR = "var";

    /**Caixa de regras*/
    public static String ACTIVATE = "activate";
    public static String DEACTIVATE = "deactivate";
    public static String EXIT_TO = "exit_to";

    /**Exceções*/
    public static String ON_EXCEPTION = "on_exception"; //usado para definir as condições e ações no caso de uma exceção
    public static String RAISE = "raise"; //usado para sinalizar uma condição de exceção durante uma reação.
    public static String RESET = "reset"; //usada para propagar uma condição de exceção

    public static String ENVIRONMENT = "environment";
    public static String USER_TERMINAL = "user_terminal";

    public static String CASE = "case";
    public static String ELSE = "else";
    public static String IS = "is";

    public static Collection<String> palavrasReservadas = new ArrayList<String>();

    static {
        palavrasReservadas.add(MODULE);
        palavrasReservadas.add(INITIALLY);
        palavrasReservadas.add(S);
        palavrasReservadas.add(TICK);
        palavrasReservadas.add(INPUT);
        palavrasReservadas.add(OUTPUT);
        palavrasReservadas.add(T_SIGNAL);
        palavrasReservadas.add(P_SIGNAL);
        palavrasReservadas.add(VAR);
        palavrasReservadas.add(ACTIVATE);
        palavrasReservadas.add(DEACTIVATE);
        palavrasReservadas.add(EXIT_TO);
        palavrasReservadas.add(ON_EXCEPTION);
        palavrasReservadas.add(RAISE);
        palavrasReservadas.add(RESET);
        palavrasReservadas.add(ENVIRONMENT);
        palavrasReservadas.add(USER_TERMINAL);
        palavrasReservadas.add(CASE);
        palavrasReservadas.add(ELSE);
        palavrasReservadas.add(IS);
    }

    public static Boolean getPalavraReservada(String token) {
        return palavrasReservadas.contains(token);
    }

}
