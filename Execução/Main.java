package Execução;

import Visão.SeraVerdadePrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SeraVerdadePrincipal().setVisible(true);
        });
    }
}