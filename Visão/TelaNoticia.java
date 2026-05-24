package Visão;

import Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * CONCEITO DE HERANÇA: 'TelaNoticia' estende (extends) a classe 'JFrame', herdando
 * toda a infraestrutura necessária para criar e gerenciar uma janela gráfica no Swing.
 * * CONCEITO DE INTERFACE E POLIMORFISMO: A classe implementa (implements) a interface 'Exibivel'.
 * Isso estabelece um contrato que obriga a implementação do método 'configurarLayout()'.
 */
public class TelaNoticia extends JFrame implements Exibivel {
    /** ENCAPSULAMENTO: Declaração de atributos privados ('private'). O estado interno do objeto
    * é protegido contra acessos externos diretos, controlando como os dados são manipulados.
     */
    private int rodadaAtual;
    private double pontuacaoAcumulada;
    private ArrayList<QuestaoQuiz> listaDoRound;
    // CONSTRUTOR: Método especial usado para inicializar o objeto da classe com os dados da rodada.
    public TelaNoticia(int rodada, double pontuacao, ArrayList<QuestaoQuiz> lista) {
        this.rodadaAtual = rodada;
        this.pontuacaoAcumulada = pontuacao;
        this.listaDoRound = lista;
        configurarLayout();
    }
    /**
     * SOBRESCRITA DE MÉTODO (@Override): Redefine o comportamento do método abstrato
     * declarado na interface 'Exibivel', adaptando-o para construir a interface específica desta tela.
     */
    @Override
    public void configurarLayout() {
        QuestaoQuiz questaoAtual = listaDoRound.get(rodadaAtual - 1);
        setTitle("SeraVerdade - Rodada " + rodadaAtual + " de 5");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        /**
         * POLIMORFISMO: O método 'setLayout' aceita uma referência genérica da interface 'LayoutManager'.
         * Passar uma instância de 'BorderLayout' para ele é um exemplo de polimorfismo (classe específica agindo como sua interface).
         */
        setLayout(new BorderLayout(10, 10));

        JLabel lblInstrucao = new JLabel("A notícia abaixo é verdadeira ou falsa?", SwingConstants.CENTER);
        lblInstrucao.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblInstrucao, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout(5, 5));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        // Obtém o endereço do recurso baseado no caminho encapsulado dentro do modelo
        java.net.URL imgUrl = getClass().getResource("/imagem/" + questaoAtual.getCaminhoImagem());
        //tratamento de exceções
        try {
            if (imgUrl == null) {
                // LANÇAMENTO DE EXCEÇÃO (throw): Dispara manualmente uma instância da sua classe de exceção personalizada
                throw new TratamentoImagens("Imagem não encontrada: " + questaoAtual.getCaminhoImagem());
            }

            ImageIcon imgIcon = new ImageIcon(imgUrl);
            Image img = imgIcon.getImage();


            Image imagemRedimensionada = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            ImageIcon iconeFormatado = new ImageIcon(imagemRedimensionada);

            JLabel lblImagem = new JLabel(iconeFormatado);
            lblImagem.setPreferredSize(new Dimension(800, 600));


            painelCentral.add(lblImagem, BorderLayout.CENTER);

        } catch (TratamentoImagens ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage() + "\nEsta rodada será pulada.", "Erro de Recurso", JOptionPane.ERROR_MESSAGE);
            // FLUXO ALTERNATIVO: Método auxiliar chamado para desviar e recuperar o fluxo do programa com seguran
            pularRodadaSemPontuar();
            return;
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(0, 0));
        painelCentral.add(scrollPane, BorderLayout.NORTH);

        add(painelCentral, BorderLayout.CENTER);

        JPanel painelEscolha = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        JButton btVerdadeiro = new JButton("Verdadeiro");
        JButton btFalso = new JButton("Falso");
        btVerdadeiro.setPreferredSize(new Dimension(200, 40));
        btFalso.setPreferredSize(new Dimension(200, 40));

        btVerdadeiro.setBackground(new Color(144, 238, 144));
        btVerdadeiro.setOpaque(true);
        btVerdadeiro.setBorderPainted(false);

        btFalso.setBackground(new Color(255, 127, 127));
        btFalso.setOpaque(true);
        btFalso.setBorderPainted(false);

        painelEscolha.add(btVerdadeiro);
        painelEscolha.add(btFalso);
        add(painelEscolha, BorderLayout.SOUTH);
        /**
         * EXPRESSÃO LAMBDA: O método 'addActionListener' recebe uma
         * interface funcional (ActionListener). A sintaxe 'e -> { ... }' implementa de forma anônima e
         * direta o método 'actionPerformed', otimizando o tratamento do clique do botão.
         */
        btVerdadeiro.addActionListener(e -> {
            new TelaJustificativa(questaoAtual, true, rodadaAtual, pontuacaoAcumulada, listaDoRound).setVisible(true);
            this.dispose();
        });

        btFalso.addActionListener(e -> {
            new TelaJustificativa(questaoAtual, false, rodadaAtual, pontuacaoAcumulada, listaDoRound).setVisible(true);
            this.dispose();
        });
    }
    /**
     * COESÃO DE MÉTODOS: Método utilitário privado responsável especificamente pela
     * regra de transição de rodadas caso ocorra alguma falha crítica.
     */
    private void pularRodadaSemPontuar() {
        this.dispose();
        if (this.rodadaAtual < listaDoRound.size()) {
            new TelaNoticia(this.rodadaAtual + 1, this.pontuacaoAcumulada, listaDoRound).setVisible(true);
        } else {
            new ResultadoPagina(this.pontuacaoAcumulada).setVisible(true);
        }
    }
}