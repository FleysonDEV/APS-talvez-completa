package Modelo;
import java.util.ArrayList;
/**
 * CONCEITO DE CLASSE DE MODELO (Entity/Domain Class):
 * Esta classe funciona como o "molde" ou "planta baixa" para representar uma questão do Quiz na memória do sistema.
 * Ela não possui lógica visual (regras do Swing) nem executa transições; sua única responsabilidade é
 * armazenar e fornecer os dados estruturados de cada pergunta.
 */
public class QuestaoQuiz {
    private boolean eVerdadeira;
    private String caminhoImagem;
    private ArrayList<String> justificativas;
    private ArrayList<String> justificativasCorretas;
    private String explicacaoFinal;

    /**
     * CONSTRUTOR COMPLETO (Inicializador do Objeto):
     * Método especial que define as regras de nascimento de um objeto 'QuestaoQuiz'.
     */
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