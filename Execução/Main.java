package Execução;

import Visão.SeraVerdadePrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { //garante que a sua interface gráfica seja criada e modificada na EDT (Event Dispatch Thread), que é a linha de execução (thread) segura do Java dedicada apenas para cuidar das telas.
            new SeraVerdadePrincipal().setVisible(true);
        });
    }
}