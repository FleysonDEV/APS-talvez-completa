package Visão;
import Modelo.*;
import javax.swing.*;
import java.awt.*;

public class ResultadoPagina extends JFrame implements Exibivel {
    private double meusPontos;

    public ResultadoPagina(double pontuacao) {
        this.meusPontos = pontuacao;
        configurarLayout();
    }

    @Override
    public void configurarLayout() {
        setTitle("SeraVerdade - Resultado Final");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Fim de Jogo!", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new GridBagLayout());
        JLabel Texto = new JLabel("Sua pontuação total:");
        JLabel Pontos = new JLabel(meusPontos + " / 10.0");
        Pontos.setFont(new Font("Arial", Font.BOLD, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        painelCentral.add(Texto, gbc);
        gbc.gridy = 1;
        painelCentral.add(Pontos, gbc);
        add(painelCentral, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btInicio = new JButton("Início");
        JButton btSair = new JButton("Sair do Jogo");
        painelBotoes.add(btInicio);
        painelBotoes.add(btSair);
        add(painelBotoes, BorderLayout.SOUTH);

        btInicio.addActionListener(v -> {
            new SeraVerdadePrincipal().setVisible(true);
            this.dispose();
        });
        btSair.addActionListener(e -> System.exit(0));

        SwingUtilities.invokeLater(() -> {
            String msg;
            if (meusPontos <= 3) msg = "Tome cuidado com o que vê na internet.";
            else if (meusPontos <= 7) msg = "Muito bom, mas sempre se mantenha informado.";
            else msg = "Excelente, parabéns por atingir a pontuação máxima.";

            JOptionPane.showMessageDialog(this, msg);
        });
    }
}