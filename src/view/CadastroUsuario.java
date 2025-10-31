package view;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class CadastroUsuario extends JFrame {

    // Definição de cores e estilos
    private static final Color COR_FUNDO_FORMULARIO = new Color(230, 230, 230, 255);
    private static final Color COR_BORDA_FORMULARIO = new Color(180, 180, 180);
    private static final Color COR_CAMPO_TEXTO = Color.WHITE;
    private static final Color COR_BORDA_CAMPO = new Color(200, 200, 200);
    private static final Color COR_BOTAO_VOLTAR = new Color(204, 0, 0);
    private static final Color COR_BOTAO_ENVIAR = new Color(102, 102, 102);

    // Variáveis finais de dimensão (Podem ser acessadas pelos métodos)
    private final int PADDING_CAMPO = 10;
    private final int ALTURA_CAMPO = 40;

    public CadastroUsuario(String usuario){

        setTitle("Cadastrar Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(600, 400));

        // Painel principal com degradê (Fundo)
        JPanel painelDegrade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                Color cor1 = new Color(192, 192, 192);
                Color cor2 = new Color(140, 140, 140);
                Color cor3 = new Color(96, 96, 96);

                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, getHeight());
                float[] fractions = {0.0f, 0.4f, 1.0f};

                Color[] colors = {cor1, cor2, cor3};

                LinearGradientPaint lgp = new LinearGradientPaint(start, end, fractions, colors);
                g2d.setPaint(lgp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        painelDegrade.setLayout(new GridBagLayout());

        // ---

        // Painel do Formulário com cantos arredondados
        JPanel painelFormulario = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = 25;
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

                g2d.setColor(COR_FUNDO_FORMULARIO);
                g2d.fill(roundedRectangle);

                g2d.setColor(COR_BORDA_FORMULARIO);
                g2d.draw(roundedRectangle);

                g2d.dispose();
                super.paintComponent(g);
            }
        };

        painelFormulario.setOpaque(false);
        painelFormulario.setPreferredSize(new Dimension(400, 500));
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Título da Tela
        JLabel titulo = new JLabel("Cadastrar Usuário");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Adiciona o título ao painel principal
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.weighty = 0.1;
        gbcTitulo.anchor = GridBagConstraints.NORTH;
        gbcTitulo.insets= new Insets(110, 0, 0, 0);
        painelDegrade.add(titulo, gbcTitulo);

        // --- Componentes do Formulário ---
        // O método 'criarPainelCampo' agora é chamado aqui, fora de sua definição.
        painelFormulario.add(criarPainelCampo("Usuário:", "Digite seu usuário..", false));
        painelFormulario.add(criarPainelCampo("Email:", "Digite seu email..", false));
        painelFormulario.add(criarPainelCampo("Confirmar email:", "Digite seu email novamente..", false));
        painelFormulario.add(criarPainelCampo("Senha:", "Digite a senha..", true));
        painelFormulario.add(criarPainelCampo("Confirmar senha:", "Digite a senha novamente..", true));

        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        painelBotoes.setOpaque(false);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(COR_BOTAO_VOLTAR);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setPreferredSize(new Dimension(120, 40));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de cadastro
                new TelaPrincipal(usuario).setVisible(true); // Retorna à tela principal
            }
        });

        // Botão Enviar
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBackground(COR_BOTAO_ENVIAR);
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEnviar.setPreferredSize(new Dimension(120, 40));
        btnEnviar.setBorderPainted(false);
        btnEnviar.setFocusPainted(false);

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnEnviar);

        // Adiciona os botões
        painelFormulario.add(painelBotoes);

        // Adiciona o painel do formulário ao painel degradê
        GridBagConstraints gbcFormulario = new GridBagConstraints();
        gbcFormulario.gridx = 0;
        gbcFormulario.gridy = 1;
        gbcFormulario.weighty = 0.9;
        gbcFormulario.anchor = GridBagConstraints.CENTER;
        painelDegrade.add(painelFormulario, gbcFormulario);

        // Adiciona o painel degradê à janela
        add(painelDegrade, BorderLayout.CENTER);
    }



    private JPanel criarPainelCampo(String labelText, final String placeholder, boolean isPassword) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(PADDING_CAMPO, 0, PADDING_CAMPO, 0));

        // Rótulo
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JTextComponent campo;

        if (isPassword) {
            JPasswordField passField = new JPasswordField();
            passField.setEchoChar((char) 0);
            campo = passField;
        } else {
            campo = new JTextField();
        }

        // Configuração do campo
        campo.setText(placeholder);
        campo.setForeground(Color.GRAY);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, ALTURA_CAMPO));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA_CAMPO, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campo.setBackground(COR_CAMPO_TEXTO);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));


        // Adicionar focus listener para gerenciar o placeholder
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                // Necessário cast para acessar métodos de JTextField/JPasswordField
                JTextComponent currentField = campo;
                if (currentField.getText().equals(placeholder)) {
                    currentField.setText("");
                    currentField.setForeground(Color.BLACK);
                    if (currentField instanceof JPasswordField) {
                        ((JPasswordField) currentField).setEchoChar('*');
                    }
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTextComponent currentField = campo;
                if (currentField.getText().isEmpty()) {
                    currentField.setText(placeholder);
                    currentField.setForeground(Color.GRAY);
                    if (currentField instanceof JPasswordField) {
                        ((JPasswordField) currentField).setEchoChar((char) 0);
                    }
                }
            }
        });

        p.add(label);
        p.add(Box.createRigidArea(new Dimension(0, 5)));
        p.add(campo);
        return p;
    }

}