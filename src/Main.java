import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        URL url = getClass().getResource(caminho);
        try {
            setArquivoEntrada(new File(url.toURI()));
        } catch (URISyntaxException e) {
            System.out.println("[ERRO] Problema na sintaxe do arquivo de entrada");
        }
    }

    public void getSeparaLinha(String chave, Integer linha) {
        if (!(chave.startsWith("/*") && chave.endsWith("*/")) && !chave.equals("")) {
            Integer coluna = 1;
            for (String token: chave.split(" ")) {
                getSeparaToken(token, linha, coluna);
                coluna += token.length();
            }
        }
    }

    public void getSeparaToken(String chave, Integer linha, Integer coluna) {
        if (chave.endsWith(":") && chave.split(":").length == 1) {
            getGravaToken(chave.split(":")[0], linha, coluna);
            getGravaToken(":", linha, coluna);
        } else if (chave.matches("\\[+[a-zA-z]([a-zA-Z]|[0-9]|_)*+(,|\\])")) {
            for (String token : chave.split("\\[+[a-zA-z]([a-zA-Z]|[0-9]|_)*+(,|\\])")) {
                getGravaToken(token, linha, coluna);
            }
        } else {
            getGravaToken(chave, linha, coluna);
        }
    }


    public void getGravaToken(String chave, Integer linha, Integer coluna) {
        if (PalavrasReservadas.palavrasReservadas.contains(chave)) {
            System.out.println(chave + " - palavra reservada");
        } else if (Delimitadores.delimitadores.contains(chave)) {
            System.out.println(chave + " - delimitador");
        } else if (chave.matches("[a-zA-z]([a-zA-z]|[0-9]|_)*")) {
            System.out.println(chave + " - identificador");
        } else {
            System.out.println("[ERRO] Token não reconhecido linha " + linha + ", coluna " + coluna);
        }
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
                getSeparaLinha(scanner.nextLine(), linha);
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
