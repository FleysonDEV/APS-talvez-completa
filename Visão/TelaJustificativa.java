package Visão;
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

        JLabel Instrucao = new JLabel("Selecione uma ou mais justificativas de suporte:");
        Instrucao.setFont(new Font("Arial", Font.BOLD, 18));
        Instrucao.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentral.add(Instrucao);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));

        ArrayList<String> opcoes = questaoAtual.getJustificativas();

        String txtA = opcoes.size() > 0 ? opcoes.get(0) : "Opção A";
        String txtB = opcoes.size() > 1 ? opcoes.get(1) : "Opção B";
        String txtC = opcoes.size() > 2 ? opcoes.get(2) : "Opção C";
        String txtD = opcoes.size() > 3 ? opcoes.get(3) : "Opção D";

        Font fonteChecks = new Font("Arial", Font.PLAIN, 16);

        JCheckBox check1 = new JCheckBox(txtA);
        JCheckBox check2 = new JCheckBox(txtB);
        JCheckBox check3 = new JCheckBox(txtC);
        JCheckBox check4 = new JCheckBox(txtD);

        check1.setFont(fonteChecks);
        check2.setFont(fonteChecks);
        check3.setFont(fonteChecks);
        check4.setFont(fonteChecks);

        check1.setAlignmentX(Component.LEFT_ALIGNMENT);
        check2.setAlignmentX(Component.LEFT_ALIGNMENT);
        check3.setAlignmentX(Component.LEFT_ALIGNMENT);
        check4.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelCentral.add(check1);
        painelCentral.add(check2);
        painelCentral.add(check3);
        painelCentral.add(check4);

        add(painelCentral, BorderLayout.CENTER);

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
            boolean justificou = (check1.isSelected() || check2.isSelected() || check3.isSelected() || check4.isSelected());
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
        double pontosGanhos = 0;
        boolean acertou = (escolhaUsuario == questaoAtual.isEVerdadeira());

        if (acertou && justificou) {
            pontosGanhos = 2.0;
        } else if (!acertou && justificou) {
            pontosGanhos = 1.5;
        } else if (acertou && !justificou) {
            pontosGanhos = 1.0;
        } else {
            pontosGanhos = 0.0;
        }

        double somaResultado = this.pontuacao + pontosGanhos;

        String statusAcerto;
        if (acertou) {
            statusAcerto = "ACERTOU!!";
        } else {
            statusAcerto = "ERROU!!";
        }

        String respostaCerta = questaoAtual.isEVerdadeira() ? "VERDADEIRA" : "FALSA";

        String explicacaoFormatada = quebrarTexto(questaoAtual.getExplicacaoFinal(), 50);


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