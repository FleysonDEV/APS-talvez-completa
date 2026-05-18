package Visão;
import java.util.Collections;
import Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaJustificativa extends JFrame implements Exibivel {
    private QuestaoQuiz questaoAtual;
    private boolean escolhaUsuario;
    private int rodada;
    private double pontuacao;
    private ArrayList<QuestaoQuiz> listaDoRound;
    private String explicacao;

    private ArrayList<JRadioButton> listaRadios = new ArrayList<>();
    private ButtonGroup grupoJustificativas = new ButtonGroup();

    public TelaJustificativa(QuestaoQuiz questao, boolean escolha, int rodada, double pontuacao, ArrayList<QuestaoQuiz> lista) {
        this.questaoAtual = questao;
        this.escolhaUsuario = escolha;
        this.rodada = rodada;
        this.pontuacao = pontuacao;
        this.listaDoRound = lista;
        configurarLayout();
    }

    @Override
    public void configurarLayout() {
        // Inicialização das propriedades básicas da Janela (JFrame)
        setTitle("SeraVerdade - Justificativa - Rodada " + rodada);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JLabel lblRodada = new JLabel("Por que você acha que é " + (escolhaUsuario ? "Verdadeiro" : "Falso") + "?", SwingConstants.CENTER);
        lblRodada.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblRodada, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(70, 80, 20, 20));

        JLabel Instrucao = new JLabel("Selecione uma justificativa de suporte:");
        Instrucao.setFont(new Font("Arial", Font.BOLD, 18));
        Instrucao.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentral.add(Instrucao);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));


        ArrayList<String> opcoes = questaoAtual.getJustificativas();
        Collections.shuffle(opcoes);

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


        radio1.setFont(fonteChecks);
        radio2.setFont(fonteChecks);
        radio3.setFont(fonteChecks);
        radio4.setFont(fonteChecks);
        radio5.setFont(fonteChecks);
        radio6.setFont(fonteChecks);


        radio1.setAlignmentX(Component.LEFT_ALIGNMENT);
        radio2.setAlignmentX(Component.LEFT_ALIGNMENT);
        radio3.setAlignmentX(Component.LEFT_ALIGNMENT);
        radio4.setAlignmentX(Component.LEFT_ALIGNMENT);
        radio5.setAlignmentX(Component.LEFT_ALIGNMENT);
        radio6.setAlignmentX(Component.LEFT_ALIGNMENT);


        painelCentral.add(radio1);
        painelCentral.add(radio2);
        painelCentral.add(radio3);
        painelCentral.add(radio4);
        painelCentral.add(radio5);
        painelCentral.add(radio6);

        add(painelCentral, BorderLayout.CENTER);


        grupoJustificativas.add(radio1);
        grupoJustificativas.add(radio2);
        grupoJustificativas.add(radio3);
        grupoJustificativas.add(radio4);
        grupoJustificativas.add(radio5);
        grupoJustificativas.add(radio6);


        listaRadios.add(radio1);
        listaRadios.add(radio2);
        listaRadios.add(radio3);
        listaRadios.add(radio4);
        listaRadios.add(radio5);
        listaRadios.add(radio6);


        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton botaoConfirmar = new JButton("Confirmar Justificativa");
        JButton botaoPular = new JButton("Pular Justificativa");

        botaoConfirmar.setPreferredSize(new Dimension(120, 45));
        botaoPular.setPreferredSize(new Dimension(120, 45));

        painelBotoes.add(botaoConfirmar);
        painelBotoes.add(botaoPular);

        add(painelBotoes, BorderLayout.SOUTH);

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

    private void FimDeRodada(boolean justificou) {
        boolean acertouNoticia = (escolhaUsuario == questaoAtual.isEVerdadeira());
        boolean justificaCorreta = true;

        if (justificou) {
            for (JRadioButton rad : listaRadios) {
                String textoOpcao = rad.getText();
                boolean usuarioMarcou = rad.isSelected();
                boolean ehVerdadeira = questaoAtual.getJustificativasCorretas().contains(textoOpcao);

                if (usuarioMarcou != ehVerdadeira) {
                    justificaCorreta = false;
                    break;
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

        // Concatenação para o corpo da caixa de diálogo
        String mensagem = statusAcerto + "\n\n" +
                "A notícia é: " + respostaCerta + "\n\n" +
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