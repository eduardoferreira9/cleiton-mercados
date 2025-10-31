import view.TelaPrincipal;
import view.SistemaVenda;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal("Caixa 01");
            tela.setVisible(true);
        });
    }
}
