package Execução;

import Visão.SeraVerdadePrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Executa a interface na Thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            new SeraVerdadePrincipal().setVisible(true);
        });
    }
}