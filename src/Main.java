import view.TelaLogin;

public class Main {
    public static void main(String[] args) {
        // Deixa o visual mais moderno e nativo
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicia a aplicação na tela de login
        javax.swing.SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin("admin");
            telaLogin.setVisible(true);
        });
    }
}
