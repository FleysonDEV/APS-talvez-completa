package Visão;
import Modelo.*;
import javax.swing.*;
import java.awt.*;

/**
 * CONCEITO DE HERANÇA: 'ResultadoPagina' herda (extends) as características de 'JFrame', tornando-se uma janela gráfica do Swing com capacidade de renderizar componentes.
 * CONCEITO DE INTERFACE E POLIMORFISMO: Ao implementar (implements) a interface 'Exibivel',
 * a classe é obrigada a cumprir o contrato do método 'configurarLayout()'.
 */
public class ResultadoPagina extends JFrame implements Exibivel {
    /** ENCAPSULAMENTO: Declaração de atributos privados ('private'). O estado interno do objeto
     * é protegido contra acessos externos diretos, controlando como os dados são manipulados.
     */
    private double meusPontos;
    // CONSTRUTOR: Método responsável por inicializar o objeto da tela recebendo os dados necessários
    public ResultadoPagina(double pontuacao) {
        this.meusPontos = pontuacao;
        configurarLayout();
    }
    /**
     * SOBRESCRITA DE MÉTODO (@Override): Redefine o comportamento do método abstrato
     * declarado originalmente na interface 'Exibivel' para construir o visual específico desta tela.
     */
    @Override
    public void configurarLayout() {
        setTitle("SeraVerdade - Resultado Final");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        /**
         * POLIMORFISMO: O método 'setLayout' aceita uma referência genérica da interface 'LayoutManager'.
         * Passar uma instância de 'BorderLayout' para ele é um exemplo de polimorfismo (classe específica agindo como sua interface).
         */
        setLayout(new BorderLayout(10, 10));
        //POLIMORFISMO: O método 'setLayout' espera uma referência abstrata da interface 'LayoutManager'.

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
        /**
         * EXPRESSÃO LAMBDA / INTERFACE FUNCIONAL: O método 'addActionListener' recebe uma
         * interface funcional (ActionListener). A sintaxe 'v -> { ... }' implementa o método
         * 'actionPerformed' de forma compacta e anônima em tempo de execução.
         */
        btInicio.addActionListener(v -> {
            new SeraVerdadePrincipal().setVisible(true);
            this.dispose();
        });
        btSair.addActionListener(e -> System.exit(0));
        /**
         * EXPRESSÃO LAMBDA: Garante que a caixa de mensagem seja empurrada
         * para a fila de eventos do Swing (Event Dispatch Thread), executando de forma segura
         * logo após a renderização gráfica total da tela.
         */
        SwingUtilities.invokeLater(() -> {
            String msg;
            if (meusPontos <= 3) msg = "Tome cuidado com o que vê na internet.";
            else if (meusPontos <= 7) msg = "Muito bom, mas sempre se mantenha informado.";
            else msg = "Excelente, parabéns por atingir a pontuação máxima.";

            JOptionPane.showMessageDialog(this, msg);
        });
    }
}