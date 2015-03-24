import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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
        URL url = getClass().getResource(caminho);
        try {
            setArquivoEntrada(new File(url.toURI()));
        } catch (URISyntaxException e) {
            System.out.println("[ERRO] Problema na sintaxe do arquivo de entrada");
        }
    }

    public void getSeparaLinha(String chave, Integer linha) {
        if (!chave.equals("")) {
            Integer coluna = 1;
            for (String token : chave.split("/\\*(\\S| )*\\*/")) {
                getQuebraToken(token, " ", linha, coluna);
            }
        }
    }

    public void getQuebraToken(String chave, String quebra, Integer linha, Integer coluna) {
        boolean gravaQuebra = false;
        if (chave.equals(quebra)) {
            getGravaToken(quebra, linha, coluna);
        } else {
            String separador = quebra;
            if (quebra.equals(Operadores.MAIS) || quebra.equals(Operadores.VEZES)) {
                separador = "[" + quebra + "]";
            } else if (quebra.equals(Delimitadores.ABRE_PARENTESES)) {
                separador = "\\" + quebra;
            }
            for (String token : chave.split(separador)) {
                if (gravaQuebra) {
                    getGravaToken(quebra, linha, coluna);
                }
                getSeparaToken(token, linha, coluna);
                gravaQuebra = true;
                coluna += token.length();
            }
        }
    }

    public void getSeparaToken(String chave, Integer linha, Integer coluna) {
//        if (chave.endsWith(Delimitadores.DOIS_PONTOS) && chave.split(Delimitadores.DOIS_PONTOS).length == 1) {
//            getGravaToken(chave.split(Delimitadores.DOIS_PONTOS)[0], linha, coluna);
//            getGravaToken(Delimitadores.DOIS_PONTOS, linha, coluna);
//        } else if (chave.endsWith(Delimitadores.VIRGULA)) {
//            getSeparaToken(chave.substring(0, chave.length() - 1), linha, coluna);
//            getGravaToken(Delimitadores.VIRGULA, linha, coluna);
//        } else
        if (chave.endsWith(Delimitadores.PONTO)) {
            getSeparaToken(chave.substring(0, chave.length() - 1), linha, coluna);
            getGravaToken(Delimitadores.PONTO, linha, coluna);
        } else if (chave.endsWith(Delimitadores.FECHA_CONCHETE)
                && !chave.matches("([a-zA-Z]([a-zA-Z]|[0-9]|_)*)*#\\[[a-zA-Z]([a-zA-Z]|[0-9]|_)*\\]")) {
            getSeparaToken(chave.substring(0, chave.length() - 1), linha, coluna);
            getGravaToken(Delimitadores.FECHA_CONCHETE, linha, coluna);
        } else if (chave.startsWith(Delimitadores.ABRE_CONCHETE)
                && !chave.matches("([a-zA-Z]([a-zA-Z]|[0-9]|_)*)*#\\[[a-zA-Z]([a-zA-Z]|[0-9]|_)*\\]")) {
            getGravaToken(Delimitadores.ABRE_CONCHETE, linha, coluna);
            getSeparaToken(chave.substring(1, chave.length()), linha, coluna);
        } else if (chave.endsWith(Delimitadores.FECHA_PARENTESES)) {
            getSeparaToken(chave.substring(0, chave.length() - 1), linha, coluna);
            getGravaToken(Delimitadores.FECHA_PARENTESES, linha, coluna);
        } else if (chave.contains(Delimitadores.SETA)) {
            getQuebraToken(chave, Delimitadores.SETA, linha, coluna);
        } else if (chave.contains(Delimitadores.DOIS_PONTOS_IGUAL)) {
            getQuebraToken(chave, Delimitadores.DOIS_PONTOS_IGUAL, linha, coluna);
        } else if (chave.contains(Delimitadores.DOIS_PONTOS_TRACO)) {
            getQuebraToken(chave, Delimitadores.DOIS_PONTOS_TRACO, linha, coluna);
        } else if (chave.contains(Delimitadores.DOIS_PONTOS)) {
            getQuebraToken(chave, Delimitadores.DOIS_PONTOS, linha, coluna);
        } else if (chave.contains(Delimitadores.VIRGULA)) {
            getQuebraToken(chave, Delimitadores.VIRGULA, linha, coluna);
        } else if (chave.contains(Delimitadores.ATRIBUICAO)) {
            getQuebraToken(chave, Delimitadores.ATRIBUICAO, linha, coluna);
        } else if (chave.contains(Delimitadores.ABRE_PARENTESES)) {
            getQuebraToken(chave, Delimitadores.ABRE_PARENTESES, linha, coluna);
        } else if (chave.contains(Operadores.MAIS)) {
            getQuebraToken(chave, Operadores.MAIS, linha, coluna);
        } else if (chave.contains(Operadores.MENOS)) {
            getQuebraToken(chave, Operadores.MENOS, linha, coluna);
        } else if (chave.contains(Operadores.VEZES)) {
            getQuebraToken(chave, Operadores.VEZES, linha, coluna);
        } else if (chave.contains(Operadores.DIVISAO)) {
            getQuebraToken(chave, Operadores.DIVISAO, linha, coluna);
        } else if (chave.contains(Operadores.MAIOR)) {
            getQuebraToken(chave, Operadores.MAIOR, linha, coluna);
        } else if (chave.contains(Operadores.MENOR)) {
            getQuebraToken(chave, Operadores.MENOR, linha, coluna);
        } else {
            getGravaToken(chave, linha, coluna);
        }
    }


    public void getGravaToken(String chave, Integer linha, Integer coluna) {
        if (!chave.equals("") && !chave.equals(" ")) {
            if (PalavrasReservadas.getPalavraReservada(chave)) {
                System.out.println(chave + " - palavra reservada");
            } else if (Delimitadores.getDelimitador(chave)) {
                System.out.println(chave + " - delimitador");
            } else if (Operadores.getOperador(chave)) {
                System.out.println(chave + " - operador");
            } else if (chave.matches("[0-9]([0-9])*")) {
                System.out.println(chave + " - constante");
            } else if (chave.matches("[a-zA-Z](\\w)*")
                    || chave.matches("([a-zA-Z]([a-zA-Z]|[\\d]|_)*)*#\\[[a-zA-Z](\\w)*\\]")) {
                System.out.println(chave + " - identificador");
            } else {
                System.out.println("[ERRO] Token não reconhecido linha " + linha + ", coluna " + coluna);
            }
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
