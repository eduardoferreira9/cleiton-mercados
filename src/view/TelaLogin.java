package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class TelaLogin extends JFrame {
    // Cores
    private static final Color COR_FUNDO_DEGRADE_TOPO = new Color(215, 215, 215);
    private static final Color COR_FUNDO_DEGRADE_MEIO = new Color(150, 150, 150);
    private static final Color COR_FUNDO_DEGRADE_BASE = new Color(96, 96, 96);
    private static final Color COR_FUNDO_PAINEL_CENTRAL = new Color(230, 230, 230);
    private static final Color COR_TEXTO_TITULO = Color.WHITE;
    private static final Color COR_FUNDO_CAMPO_TEXTO = Color.WHITE;
    private static final Color COR_TEXTO_PADRAO = new Color(50, 50, 50);
    private static final Color COR_FUNDO_BOTAO = new Color(96, 96, 96);
    private static final Color COR_TEXTO_BOTAO = Color.WHITE;

    private static final int LARGURA_PAINEL_LOGIN = 400;
    private static final int ALTURA_PAINEL_LOGIN = 320;
    private static final int RAIO_BORDA = 30;

    public TelaLogin(String usuario) {
        setTitle("Sistema de Supermercado Cleitin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // <<< Abre tela cheia
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Fundo degradê
        JPanel painelDegrade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color[] cores = {COR_FUNDO_DEGRADE_TOPO, COR_FUNDO_DEGRADE_MEIO, COR_FUNDO_DEGRADE_BASE};
                float[] pos = {0.0f, 0.5f, 1.0f};
                LinearGradientPaint gradiente = new LinearGradientPaint(new Point2D.Float(0, 0),
                        new Point2D.Float(0, getHeight()), pos, cores);
                g2d.setPaint(gradiente);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelDegrade.setLayout(new BorderLayout());

        // Título
        JLabel labelTitulo = new JLabel("Sistema de Supermercado Cleitin", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        labelTitulo.setForeground(COR_TEXTO_TITULO);

        JPanel painelTitulo = new JPanel();
        painelTitulo.setOpaque(false);
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(100, 0, 40, 0));
        painelTitulo.add(labelTitulo);
        painelDegrade.add(painelTitulo, BorderLayout.NORTH);

        // Painel central de login
        JPanel painelLoginCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(COR_FUNDO_PAINEL_CENTRAL);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), RAIO_BORDA, RAIO_BORDA);
                g2d.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        painelLoginCentral.setLayout(new BoxLayout(painelLoginCentral, BoxLayout.Y_AXIS));
        painelLoginCentral.setPreferredSize(new Dimension(LARGURA_PAINEL_LOGIN, ALTURA_PAINEL_LOGIN));
        painelLoginCentral.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Campos
        Font fontRotulo = new Font("Arial", Font.BOLD, 18);
        Font fontCampo = new Font("Arial", Font.PLAIN, 16);

        JLabel labelUsuario = new JLabel("Usuário");
        labelUsuario.setFont(fontRotulo);
        labelUsuario.setForeground(COR_TEXTO_PADRAO);
        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoUsuario = new JTextField("Digite seu usuário..");
        campoUsuario.setFont(fontCampo);
        campoUsuario.setForeground(new Color(150, 150, 150));
        campoUsuario.setMaximumSize(new Dimension(LARGURA_PAINEL_LOGIN, 40));
        campoUsuario.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (campoUsuario.getText().equals("Digite seu usuário..")) {
                    campoUsuario.setText("");
                    campoUsuario.setForeground(COR_TEXTO_PADRAO);
                }
            }

            public void focusLost(FocusEvent e) {
                if (campoUsuario.getText().isEmpty()) {
                    campoUsuario.setText("Digite seu usuário..");
                    campoUsuario.setForeground(new Color(150, 150, 150));
                }
            }
        });

        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setFont(fontRotulo);
        labelSenha.setForeground(COR_TEXTO_PADRAO);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField campoSenha = new JPasswordField("Digite sua senha...");
        campoSenha.setFont(fontCampo);
        campoSenha.setForeground(new Color(150, 150, 150));
        campoSenha.setEchoChar((char) 0);
        campoSenha.setMaximumSize(new Dimension(LARGURA_PAINEL_LOGIN, 40));
        campoSenha.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(campoSenha.getPassword()).equals("Digite sua senha...")) {
                    campoSenha.setText("");
                    campoSenha.setForeground(COR_TEXTO_PADRAO);
                    campoSenha.setEchoChar('\u2022');
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(campoSenha.getPassword()).isEmpty()) {
                    campoSenha.setText("Digite sua senha...");
                    campoSenha.setForeground(new Color(150, 150, 150));
                    campoSenha.setEchoChar((char) 0);
                }
            }
        });

        JButton botaoEntrar = new RoundedButton("ENTRAR");
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoEntrar.setBackground(COR_FUNDO_BOTAO);
        botaoEntrar.setForeground(COR_TEXTO_BOTAO);
        botaoEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login padrão admin / 123
        botaoEntrar.addActionListener(e -> {
            String usuarioInput = campoUsuario.getText();
            String senhaInput = new String(campoSenha.getPassword());

            if (usuarioInput.equals("admin") && senhaInput.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                dispose();
                new TelaPrincipal(usuarioInput);
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        painelLoginCentral.add(labelUsuario);
        painelLoginCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        painelLoginCentral.add(campoUsuario);
        painelLoginCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        painelLoginCentral.add(labelSenha);
        painelLoginCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        painelLoginCentral.add(campoSenha);
        painelLoginCentral.add(Box.createRigidArea(new Dimension(0, 30)));
        painelLoginCentral.add(botaoEntrar);

        JPanel painelCentralWrapper = new JPanel(new GridBagLayout());
        painelCentralWrapper.setOpaque(false);
        painelCentralWrapper.add(painelLoginCentral);

        painelDegrade.add(painelCentralWrapper, BorderLayout.CENTER);
        add(painelDegrade, BorderLayout.CENTER);
    }
}

// Botão arredondado
class RoundedButton extends JButton {
    private static final int CORNER_RADIUS = 10;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
