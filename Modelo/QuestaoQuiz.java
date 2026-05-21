package Modelo;
import java.util.ArrayList;


public class QuestaoQuiz {
    private boolean eVerdadeira;
    private String caminhoImagem;
    private ArrayList<String> justificativas;
    private ArrayList<String> justificativasCorretas;
    private String explicacaoFinal;


    public QuestaoQuiz(boolean eVerdadeira, String caminhoImagem, ArrayList<String> justificativas, ArrayList<String> justificativasCorretas, String explicacaoFinal) {

        this.eVerdadeira = eVerdadeira;
        this.caminhoImagem = caminhoImagem;
        this.justificativas = justificativas;
        this.justificativasCorretas = justificativasCorretas;
        this.explicacaoFinal = explicacaoFinal;
    }


    public boolean isEVerdadeira() {
        return eVerdadeira;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public ArrayList<String> getJustificativas() {
        return justificativas;
    }

    public ArrayList<String> getJustificativasCorretas() {
        return justificativasCorretas;
    }

    public String getExplicacaoFinal() {
        return explicacaoFinal;
    }
}