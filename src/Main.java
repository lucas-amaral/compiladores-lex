import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
    private static Main m;
    private static FileOutputStream arquivoSaida;
    private File arquivoEntrada;



    public static void main(String []arg) throws IOException {
        try {
            arquivoSaida = new FileOutputStream("tokens.txt", true);
            System.setOut(new PrintStream(arquivoSaida));
        } catch (Exception e) {/**/}
        m = new Main();
//        m.inicia();
//        if (m.getFout() != null) {
//            m.getFout().close();
//        }
    }
}
