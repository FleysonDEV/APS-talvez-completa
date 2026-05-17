package Visão;

import Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaNoticia extends JFrame implements Exibivel {
    private int rodadaAtual;
    private double pontuacaoAcumulada;
    private ArrayList<QuestaoQuiz> listaDoRound;

    public TelaNoticia(int rodada, double pontuacao, ArrayList<QuestaoQuiz> lista) {
        this.rodadaAtual = rodada;
        this.pontuacaoAcumulada = pontuacao;
        this.listaDoRound = lista;
        configurarLayout();
    }

    @Override
    public void configurarLayout() {
        QuestaoQuiz questaoAtual = listaDoRound.get(rodadaAtual - 1);

        setTitle("SeraVerdade - Rodada " + rodadaAtual + " de 5");


        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblInstrucao = new JLabel("A notícia abaixo é verdadeira ou falsa?", SwingConstants.CENTER);
        lblInstrucao.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblInstrucao, BorderLayout.NORTH);


        JPanel painelCentral = new JPanel(new BorderLayout(5, 5));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        java.net.URL imgUrl = getClass().getResource("/imagem/" + questaoAtual.getCaminhoImagem());

        try {
            if (imgUrl == null) {
                throw new TratamentoImagens("Imagem não encontrada: " + questaoAtual.getCaminhoImagem());
            }

            ImageIcon imgIcon = new ImageIcon(imgUrl);
            Image img = imgIcon.getImage();


            Image imagemRedimensionada = img.getScaledInstance(1000, 540, Image.SCALE_SMOOTH);
            ImageIcon iconeFormatado = new ImageIcon(imagemRedimensionada);

            JLabel lblImagem = new JLabel(iconeFormatado);

            lblImagem.setPreferredSize(new Dimension(1000, 540));

            painelCentral.add(lblImagem, BorderLayout.CENTER);

        } catch (TratamentoImagens ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage() + "\nEsta rodada será pulada.", "Erro de Recurso", JOptionPane.ERROR_MESSAGE);
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
        painelEscolha.add(btVerdadeiro);
        painelEscolha.add(btFalso);
        add(painelEscolha, BorderLayout.SOUTH);

        btVerdadeiro.addActionListener(e -> {
            new TelaJustificativa(questaoAtual, true, rodadaAtual, pontuacaoAcumulada, listaDoRound).setVisible(true);
            this.dispose();
        });

        btFalso.addActionListener(e -> {
            new TelaJustificativa(questaoAtual, false, rodadaAtual, pontuacaoAcumulada, listaDoRound).setVisible(true);
            this.dispose();
        });
    }

    private void pularRodadaSemPontuar() {
        this.dispose();
        if (this.rodadaAtual < listaDoRound.size()) {
            new TelaNoticia(this.rodadaAtual + 1, this.pontuacaoAcumulada, listaDoRound).setVisible(true);
        } else {
            new ResultadoPagina(this.pontuacaoAcumulada).setVisible(true);
        }
    }
}