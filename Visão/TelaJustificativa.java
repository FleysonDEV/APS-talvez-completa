package Visão;
import java.util.Collections;
import Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * CONCEITO DE HERANÇA: 'TelaJustificativa' herda (extends) as características de 'JFrame'.
 * CONCEITO DE INTERFACE E POLIMORFISMO: Implementa 'Exibivel', cumprindo o contrato do método 'configurarLayout()'.
 */
public class TelaJustificativa extends JFrame implements Exibivel {
    // ENCAPSULAMENTO: Atributos privados para proteção do estado interno do objeto.
    private QuestaoQuiz questaoAtual;
    private boolean escolhaUsuario;
    private int rodada;
    private double pontuacao;
    private ArrayList<QuestaoQuiz> listaDoRound;
    private String explicacao;
    private ArrayList<JRadioButton> listaRadios = new ArrayList<>();
    private ButtonGroup grupoJustificativas = new ButtonGroup();

    // CONSTRUTOR: Inicializa a tela com os dados herdados da rodada atual.
    public TelaJustificativa(QuestaoQuiz questao, boolean escolha, int rodada, double pontuacao, ArrayList<QuestaoQuiz> lista) {
        this.questaoAtual = questao;
        this.escolhaUsuario = escolha;
        this.rodada = rodada;
        this.pontuacao = pontuacao;
        this.listaDoRound = lista;
        //Pula a justificativa caso errar a resposta da notícia
        if (this.escolhaUsuario != questaoAtual.isEVerdadeira()){
            FimDeRodada(false);
            return;
        }
        configurarLayout();
    }
    /**
     * SOBRESCRITA DE MÉTODO (@Override): Redefine o comportamento do método abstrato declarado
     * originalmente na interface 'Exibivel', customizando a construção visual desta tela específica.
     */
    @Override
    public void configurarLayout() {
        setTitle("SeraVerdade - Justificativa - Rodada " + rodada);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //POLIMORFISMO: O método 'setLayout' espera uma referência abstrata da interface 'LayoutManager'.
        setLayout(new BorderLayout(10, 5));
        // OPERADOR TERNÁRIO: Avalia de forma compacta e condicional a String baseada no booleano 'escolhaUsuario'
        JLabel lblRodada = new JLabel("Por que você acha que é " + (escolhaUsuario ? "Verdadeiro" : "Falso") + "?", SwingConstants.CENTER);
        lblRodada.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblRodada, BorderLayout.NORTH);

        JPanel painelCentralGeral = new JPanel();
        painelCentralGeral.setLayout(new BoxLayout(painelCentralGeral, BoxLayout.Y_AXIS));
        painelCentralGeral.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        JPanel painelImagem = new JPanel(new FlowLayout(FlowLayout.CENTER));
        java.net.URL imgUrl = getClass().getResource("/imagem/" + questaoAtual.getCaminhoImagem());

        // Tratamento de exceção
        try {
            if (imgUrl == null) {
                // LANÇAMENTO DE EXCEÇÃO (throw): Dispara manualmente uma instância da sua classe de exceção personalizada
                throw new TratamentoImagens("Imagem não encontrada na justificativa: " + questaoAtual.getCaminhoImagem());
            }

            ImageIcon imgIcon = new ImageIcon(imgUrl);
            Image img = imgIcon.getImage();
            Image imagemRedimensionada = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            JLabel lblImagem = new JLabel(new ImageIcon(imagemRedimensionada));
            lblImagem.setPreferredSize(new Dimension(800, 600));
            painelImagem.add(lblImagem);

        } catch (TratamentoImagens ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage() + "\nEsta rodada será pulada.", "Erro de Recurso", JOptionPane.ERROR_MESSAGE);
            pularRodadaSemPontuar();
            return;
        }

        painelCentralGeral.add(painelImagem);
        painelCentralGeral.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painelInstrucao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel Instrucao = new JLabel("Selecione uma justificativa de suporte:");
        Instrucao.setFont(new Font("Arial", Font.BOLD, 18));
        painelInstrucao.add(Instrucao);
        painelCentralGeral.add(painelInstrucao);
        painelCentralGeral.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painelOpcoesGrade = new JPanel(new GridLayout(3, 2, 40, 10));
        JPanel painelOpcoesCentralizado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelOpcoesGrade.setPreferredSize(new Dimension(900, 100));

        ArrayList<String> opcoes = questaoAtual.getJustificativas();
        Collections.shuffle(opcoes);
        // OPERADORES TERNÁRIOS DE SEGURANÇA: Evitam erros de índice (IndexOutOfBoundsException) checando o tamanho da coleção
        String txtA = opcoes.size() > 0 ? opcoes.get(0) : "Opção A";
        String txtB = opcoes.size() > 1 ? opcoes.get(1) : "Opção B";
        String txtC = opcoes.size() > 2 ? opcoes.get(2) : "Opção C";
        String txtD = opcoes.size() > 3 ? opcoes.get(3) : "Opção D";
        String txtE = opcoes.size() > 4 ? opcoes.get(4) : "Opção E";
        String txtF = opcoes.size() > 5 ? opcoes.get(5) : "Opção F";

        Font fonteChecks = new Font("Arial", Font.PLAIN, 16);

        JRadioButton radio1 = new JRadioButton(txtA);
        JRadioButton radio2 = new JRadioButton(txtB);
        JRadioButton radio3 = new JRadioButton(txtC);
        JRadioButton radio4 = new JRadioButton(txtD);
        JRadioButton radio5 = new JRadioButton(txtE);
        JRadioButton radio6 = new JRadioButton(txtF);

        JRadioButton[] todosRadios = {radio1, radio2, radio3, radio4, radio5, radio6};
        /**
         * LAÇO (For-Each): Iteração limpa sobre coleções ou arrays do Java,
         * adicionando os componentes simultaneamente às regras de exclusão visual e à lista dinâmica para posterior validação.
         */
        for (JRadioButton radio : todosRadios) {
            radio.setFont(fonteChecks);
            grupoJustificativas.add(radio);
            listaRadios.add(radio);
        }

        painelOpcoesGrade.add(radio1);
        painelOpcoesGrade.add(radio2);
        painelOpcoesGrade.add(radio3);
        painelOpcoesGrade.add(radio4);
        painelOpcoesGrade.add(radio5);
        painelOpcoesGrade.add(radio6);

        painelOpcoesCentralizado.add(painelOpcoesGrade);
        painelCentralGeral.add(painelOpcoesCentralizado);

        add(painelCentralGeral, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 10, 15, 10));

        JButton botaoConfirmar = new JButton("Confirmar Justificativa");
        JButton botaoPular = new JButton("Pular Justificativa");

        botaoConfirmar.setPreferredSize(new Dimension(200, 40));
        botaoPular.setPreferredSize(new Dimension(200, 40));

        painelBotoes.add(botaoConfirmar);
        painelBotoes.add(botaoPular);

        add(painelBotoes, BorderLayout.SOUTH);
        /**
         * EXPRESSÃO LAMBDA / INTERFACE FUNCIONAL: O método 'addActionListener' recebe uma
         * implementação da interface funcional 'ActionListener'. A sintaxe enxuta 'e -> { ... }'
         * substitui a verbosidade de uma classe interna anônima clássica.
         */
        botaoConfirmar.addActionListener(e -> {
            boolean justificou = (radio1.isSelected() || radio2.isSelected() || radio3.isSelected() || radio4.isSelected() || radio5.isSelected() || radio6.isSelected());
            if (!justificou) {
                JOptionPane.showMessageDialog(this, "Nenhuma justificativa selecionada. Caso não saiba, clique em 'Pular Justificativa'.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                FimDeRodada(true);
            }
        });

        botaoPular.addActionListener(e -> {
            FimDeRodada(false);
        });
    }
    //Lógica para calcular a pontuação ao termino da rodada
    private void FimDeRodada(boolean justificou) {
        boolean acertouNoticia = (escolhaUsuario == questaoAtual.isEVerdadeira());
        boolean justificaCorreta = false;

        if (justificou) {
            String textoSelecionado = "";
            for (JRadioButton rad : listaRadios) {
                if (rad.isSelected()) {
                    textoSelecionado = rad.getText();
                    break;
                }
            }

            ArrayList<String> corretas = questaoAtual.getJustificativasCorretas();
            if (corretas != null) {
                for (String correta : corretas) {
                    if (textoSelecionado.trim().equalsIgnoreCase(correta.trim())) {
                        justificaCorreta = true;
                        break;
                    }
                }
            }
        } else {
            justificaCorreta = false;
        }

        double pontosGanhos = 0.0;
        if (acertouNoticia && justificaCorreta) {
            pontosGanhos = 2.0;
        } else if (acertouNoticia && !justificaCorreta) {
            pontosGanhos = 1.5;
        } else {
            pontosGanhos = 0.0;
        }

        double somaResultado = this.pontuacao + pontosGanhos;

        String statusAcerto = "";
        if (acertouNoticia) {
            if (justificaCorreta) {
                statusAcerto = "Você ACERTOU a resposta e a JUSTIFICATIVA!";
            } else {
                statusAcerto = "Você ACERTOU a notícia, mas ERROU a justificativa!";
            }
        } else {
            statusAcerto = " Você ERROU a resposta principal da notícia! ";
        }

        String respostaCerta = questaoAtual.isEVerdadeira() ? "VERDADEIRA" : "FALSA";
        String explicacaoFormatada = quebrarTexto(questaoAtual.getExplicacaoFinal(), 50);

        String justificativaExibida = "Nenhuma cadastrada";
        if (questaoAtual.getJustificativasCorretas() != null && !questaoAtual.getJustificativasCorretas().isEmpty()) {
            justificativaExibida = questaoAtual.getJustificativasCorretas().get(0);
        }

        String mensagem = statusAcerto + "\n\n" +
                "A notícia é: " + respostaCerta + "\n\n" +
                "Justificativa Correta: " + justificativaExibida + "\n\n" +
                "Explicação:\n" + explicacaoFormatada + "\n\n" +
                "Pontos ganho: " + pontosGanhos;

        JOptionPane.showMessageDialog(this, mensagem, "Resultado da Rodada", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();

        if (this.rodada < listaDoRound.size()) {
            new TelaNoticia(this.rodada + 1, somaResultado, this.listaDoRound).setVisible(true);
        } else {
            new ResultadoPagina(somaResultado).setVisible(true);
        }
    }

    /**
     * MÉTODO DE RECUPERAÇÃO DE FLUXO: Método local adicionado para ser chamado pelo bloco
     * 'catch' em caso de falha crítica na imagem. Permite desviar o fluxo do jogo de forma limpa sem que o software trave.
     */
    private void pularRodadaSemPontuar() {
        this.dispose();
        if (this.rodada < listaDoRound.size()) {
            new TelaNoticia(this.rodada + 1, this.pontuacao, this.listaDoRound).setVisible(true);
        } else {
            new ResultadoPagina(this.pontuacao).setVisible(true);
        }
    }
    /**
     * MANIPULAÇÃO DE MÉTODOS DE STRING: Exemplo prático de processamento estruturado de texto usando sub-recursos
     * nativos da classe String, tais como '.length()', '.lastIndexOf()' e '.substring()'.
     */
    private String quebrarTexto(String texto, int limite) {
        if (texto == null || texto.length() <= limite) {
            return texto;
        }

        String resultado = "";
        while (texto.length() > limite) {
            int pontoQuebra = texto.lastIndexOf(" ", limite);
            if (pontoQuebra == -1) {
                pontoQuebra = limite;
            }

            resultado += texto.substring(0, pontoQuebra) + "\n";
            texto = texto.substring(pontoQuebra).trim();
        }
        resultado += texto;

        return resultado;
    }
}