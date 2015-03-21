import java.io.*;
import java.util.Scanner;

public class Main {
    private static Main m;
    private static FileOutputStream arquivoSaida;
    private File arquivoEntrada;

    public File getArquivoEntrada() {
        return arquivoEntrada;
    }

    public void setArquivoEntrada(File arquivoEntrada) {
        this.arquivoEntrada = arquivoEntrada;
    }

    public void getBuscaArquivo(String caminho) {
        setArquivoEntrada(new File(caminho));
    }

    public Boolean getGravaToken(String chave) {
        if (PalavrasReservadas.palavrasReservadas.contains(chave)) {
            System.out.println(chave + " - palavra reservada");
        } else if (Delimitadores.delimitadores.contains(chave)) {
            System.out.println(chave + " - delimitador");
        } else if (chave.startsWith("[a-A][z-Z]")) {
            System.out.println(chave + " - identificador");
        } else {
            return false;
        }
        return true;
    }

    public void inicia() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(getArquivoEntrada()).useDelimiter("\\n");
        } catch (FileNotFoundException e) {
            System.out.println("[ERRO] Arquivo de entrada não encontrado " + e);
        }
        if (scanner != null) {
            for (int linha = 1; scanner.hasNext() ; linha++) {
                System.out.println(getGravaToken(scanner.toString()));
            }

        }
    }

    public static void main(String []arg) throws IOException {
        try {
            arquivoSaida = new FileOutputStream("tokens.txt", true);
            System.setOut(new PrintStream(arquivoSaida));
        } catch (Exception e) {/**/}
        m = new Main();
        m.getBuscaArquivo(arg[0]);
        m.inicia();
        if (m.arquivoSaida != null) {
            m.arquivoSaida.close();
        }
    }
}
