import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lucasamaral on 19/03/15.
 */
public class PalavrasReservadas {
    public static final String MODULE = "module";
    public static final String INITIALLY = "initially";

    public static final String S = "s";
    public static final String TICK = "tick";

    /**Sinais*/
    public static final String INPUT = "input";
    public static final String OUTPUT = "output";
    public static final String T_SIGNAL = "t_signal";
    public static final String P_SIGNAL = "p_signal";

    /**Variável*/
    public static final String VAR = "var";

    /**Caixa de regras*/
    public static final String ACTIVATE = "activate";
    public static final String DEACTIVATE = "deactivate";
    public static final String EXIT_TO = "exit_to";

    public static final String UP = "up";
    public static final String EMIT = "emit";

    /**Exceções*/
    public static final String ON_EXCEPTION = "on_exception"; //usado para definir as condições e ações no caso de uma exceção
    public static final String RAISE = "raise"; //usado para sinalizar uma condição de exceção durante uma reação.
    public static final String RESET = "reset"; //usada para propagar uma condição de exceção

    public static final String ENVIRONMENT = "environment";
    public static final String USER_TERMINAL = "user_terminal";

    public static final String CASE = "case";
    public static final String ELSE = "else";
    public static final String IS = "is";
    public static final String BOX = "box";
    public static final String WRITE = "write";

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
        palavrasReservadas.add(BOX);
        palavrasReservadas.add(UP);
        palavrasReservadas.add(EMIT);
        palavrasReservadas.add(WRITE);
    }

    public static Boolean getPalavraReservada(String token) {
        return palavrasReservadas.contains(token);
    }

}
