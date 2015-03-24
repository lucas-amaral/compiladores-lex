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

    //Método que realiza a busca do arquivo no caminho informado como parâmetro do programa.
    public void getBuscaArquivo(String caminho) {
        URL url = getClass().getResource(caminho);
        try {
            setArquivoEntrada(new File(url.toURI()));
        } catch (URISyntaxException e) {
            System.out.println("[ERRO] Problema na sintaxe do arquivo de entrada");
        }
    }

    //Método que ignora os comentários da arquivo de entreda, e depois quebra os tokens pelos espaços em branco da linha.
    public void getSeparaLinha(String chave, Integer linha) {
        if (!chave.equals("")) {
            Integer coluna = 1;
            for (String token : chave.split("/\\*(\\S| )*\\*/")) {
                if (token.contains(PalavrasReservadas.WRITE+Delimitadores.ABRE_PARENTESES)
                        && token.contains("'") && token.contains(Delimitadores.FECHA_PARENTESES)) { //Texto dentro do write()
                    getQuebraToken(token, Delimitadores.ABRE_PARENTESES, linha, coluna);
                } else {
                    getQuebraToken(token, " ", linha, coluna);
                }
            }
        }
    }

    //Método para realizar a separação de uma sequência de caracteres, separando-as pelos delimitador(quebra) passado por parâmetro.
    public void getQuebraToken(String chave, String quebra, Integer linha, Integer coluna) {
        boolean gravaQuebra = false;
        if (chave.equals(quebra)) {
            getGravaToken(quebra, linha, coluna);
        } else {
            String separador = quebra;
            if (Operadores.getCaracterReservadoExpressaoRegular(quebra)) {
                separador = "[" + quebra + "]";
            } else if (Delimitadores.getCaracterReservadoExpressaoRegular(quebra)) {
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
            if (chave.endsWith(quebra)) {
                getGravaToken(quebra, linha, coluna);
            }
        }
    }

    //Método responsável por escolher qual delimitador/operador será utilizado para separar uma sequência de caracteres.
    public void getSeparaToken(String chave, Integer linha, Integer coluna) {
        if (chave.contains(Delimitadores.VIRGULA)) {
            getQuebraToken(chave, Delimitadores.VIRGULA, linha, coluna);
        } else if (chave.contains(Delimitadores.PONTO)) {
            getQuebraToken(chave, Delimitadores.PONTO, linha, coluna);
        } else if (chave.contains(Delimitadores.FECHA_CONCHETE)) {
            getQuebraToken(chave, Delimitadores.FECHA_CONCHETE, linha, coluna);
        } else if (chave.contains(Delimitadores.FECHA_PARENTESES)) {
            getQuebraToken(chave, Delimitadores.FECHA_PARENTESES, linha, coluna);
        } else if (chave.contains(Delimitadores.SETA)) {
            getQuebraToken(chave, Delimitadores.SETA, linha, coluna);
        } else if (chave.contains(Delimitadores.DOIS_PONTOS_IGUAL)) {
            getQuebraToken(chave, Delimitadores.DOIS_PONTOS_IGUAL, linha, coluna);
        } else if (chave.contains(Delimitadores.DOIS_PONTOS_TRACO)) {
            getQuebraToken(chave, Delimitadores.DOIS_PONTOS_TRACO, linha, coluna);
        } else if (chave.contains(Delimitadores.DOIS_PONTOS)) {
            getQuebraToken(chave, Delimitadores.DOIS_PONTOS, linha, coluna);
        } else if (chave.contains(Delimitadores.ATRIBUICAO)) {
            getQuebraToken(chave, Delimitadores.ATRIBUICAO, linha, coluna);
        } else if (chave.contains(Delimitadores.ABRE_CONCHETE)) {
            getQuebraToken(chave, Delimitadores.ABRE_CONCHETE, linha, coluna);
        } else if (chave.contains(Delimitadores.ABRE_PARENTESES)) {
            getQuebraToken(chave, Delimitadores.ABRE_PARENTESES, linha, coluna);
        } else if (chave.contains(Delimitadores.HASH)) {
            getQuebraToken(chave, Delimitadores.HASH, linha, coluna);
        } else if (chave.contains(Operadores.MAIOR_IGUAL)) {
            getQuebraToken(chave, Operadores.MAIOR_IGUAL, linha, coluna);
        } else if (chave.contains(Operadores.MENOR_IGUAL)) {
            getQuebraToken(chave, Operadores.MENOR_IGUAL, linha, coluna);
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
        } else if (chave.contains(Operadores.IGUAL)) {
            getQuebraToken(chave, Operadores.IGUAL, linha, coluna);
        } else if (chave.contains(Operadores.E)) {
            getQuebraToken(chave, Operadores.E, linha, coluna);
        } else {
            getGravaToken(chave, linha, coluna);
        }
    }


    //Método responsável por gravar o token no arquivo de saída.
    public void getGravaToken(String chave, Integer linha, Integer coluna) {
        chave = chave.trim();
        if (!chave.equals("")) {
            if (PalavrasReservadas.getPalavraReservada(chave)) {
                System.out.println(chave + " - palavra reservada");
            } else if (Delimitadores.getDelimitador(chave)) {
                System.out.println(chave + " - delimitador");
            } else if (Operadores.getOperador(chave)) {
                System.out.println(chave + " - operador");
            } else if (chave.matches("[0-9]([0-9])*")) {
                System.out.println(chave + " - constante");
            } else if (chave.matches("[a-zA-Z](\\w)*")) {
                System.out.println(chave + " - identificador");
            } else if (chave.matches("'(\\w|\\W)*'")) {
                System.out.println(chave + " - texto");
            } else {
                System.out.println("[ERRO] Token não reconhecido linha " + linha + ", coluna " + coluna);
            }
        }
    }


    //Método responsável por iniciar a leitura do arquivo de entrada, separando-o por linhas
    public void inicia() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(getArquivoEntrada()).useDelimiter("\\n");
        } catch (FileNotFoundException e) { /**/ }
        if (scanner != null) {
            for (int linha = 1; scanner.hasNext() ; linha++) {
                getSeparaLinha(scanner.nextLine(), linha);
            }

        }
    }

    /**
     * Inicia um arquivo de saída chamado "tokens.txt" onde será guardado os tokens lidos do arquivo de entrada
     * Recebe como argumento o caminho do arquivo de entrada e inicia a geração dos tokens
     */
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
