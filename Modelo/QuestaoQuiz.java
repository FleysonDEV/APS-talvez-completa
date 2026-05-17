package Modelo;
import java.util.ArrayList;


public class QuestaoQuiz {
    private boolean eVerdadeira;
    private String caminhoImagem;
    private ArrayList<String> justificativas;
    private String explicacaoFinal;


    public QuestaoQuiz( boolean eVerdadeira, String caminhoImagem, ArrayList<String> justificativas, String explicacaoFinal) {

        this.eVerdadeira = eVerdadeira;
        this.caminhoImagem = caminhoImagem;
        this.justificativas = justificativas;
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
    public String getExplicacaoFinal() {
        return explicacaoFinal;
    }
}