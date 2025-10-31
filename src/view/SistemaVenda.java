package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaVenda extends JFrame {

    public SistemaVenda(String usuario) {
        // Configurações basicas da janela
        setTitle("Sistema do Supermercado Cleitin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 600));

        // Painel com o fundo em Degradê
        JPanel painelDegrade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Cores utilizadas (Degradê Cinza)
                Color cor1 = new Color(215, 215, 215);
                Color cor2 = new Color(150, 150, 150);
                Color cor3 = new Color(96, 96, 96);

                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, getHeight());
                float[] fractions = {0.0f, 0.5f, 1.0f};
                Color[] colors = {cor1, cor2, cor3};

                LinearGradientPaint lgp = new LinearGradientPaint(start, end, fractions, colors);
                g2d.setPaint(lgp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelDegrade.setLayout(new BorderLayout());
        add(painelDegrade, BorderLayout.CENTER);

        //Painel Superior
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.weightx = 1.0;
        gbcHeader.anchor = GridBagConstraints.WEST;

        // Logo e Título
        JLabel logoLabel = new JLabel("🛒 Supermercado Cleitin");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        logoLabel.setForeground(new Color(50, 50, 50));
        headerPanel.add(logoLabel, gbcHeader);

        // Botão sair
        JButton sairButton = createModernButton("SAIR", new Color(215, 50, 50), Color.WHITE, 18);
        sairButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ação: Sair do Sistema!", "Ação de Botão", JOptionPane.INFORMATION_MESSAGE);
            // System.exit(0); // Código real para sair
        });

        gbcHeader.weightx = 0.0;
        gbcHeader.anchor = GridBagConstraints.EAST;
        headerPanel.add(sairButton, gbcHeader);

        painelDegrade.add(headerPanel, BorderLayout.NORTH);

        //Painel do centro
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        painelDegrade.add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //Tabela de Itens
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        contentPanel.add(leftPanel, gbc);

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5, 0, 5, 0);
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        gbcLeft.gridwidth = GridBagConstraints.REMAINDER;
        gbcLeft.weightx = 1.0;

        // Campos de texto
        JTextField scanField = new JTextField("Escanear ou Digitar Codigos de Barras/Nome");
        scanField.setForeground(Color.GRAY);
        scanField.setPreferredSize(new Dimension(300, 40));
        scanField.setFont(new Font("Arial", Font.PLAIN, 16));
        scanField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbcLeft.gridy = 0;
        leftPanel.add(scanField, gbcLeft);

        JLabel registroLabel = new JLabel("Registro de Itens");
        registroLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbcLeft.gridy = 1;
        leftPanel.add(registroLabel, gbcLeft);

        JTextField produtoField = new JTextField("Produto/Itens");
        produtoField.setForeground(Color.GRAY);
        produtoField.setPreferredSize(new Dimension(300, 40));
        produtoField.setFont(new Font("Arial", Font.PLAIN, 16));
        produtoField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbcLeft.gridy = 2;
        leftPanel.add(produtoField, gbcLeft);

        // Tabela de Itens
        String[] colunas = {"Nome do Produto", "Quantidade", "Valor Unit.", "Subtotal"};
        Object[][] dados = new Object[15][4];
        JTable tabela = new JTable(dados, colunas);
        tabela.setRowHeight(30);
        tabela.setEnabled(false);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(new Color(230, 230, 230));

        JScrollPane scrollPane = new JScrollPane(tabela);
        gbcLeft.weighty = 1.0;
        gbcLeft.gridy = 3;
        leftPanel.add(scrollPane, gbcLeft);

        //Coluna Venda, Cliente
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        contentPanel.add(rightPanel, gbc);

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5, 10, 5, 0);
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        // Títulos
        JLabel vendaLabel = new JLabel("Venda");
        vendaLabel.setFont(new Font("Arial", Font.BOLD, 30));
        vendaLabel.setForeground(new Color(50, 50, 50));
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.gridwidth = GridBagConstraints.REMAINDER;
        gbcRight.anchor = GridBagConstraints.EAST;
        rightPanel.add(vendaLabel, gbcRight);

        JLabel cpfLabel = new JLabel("CPF na Nota/ Cliente Fidelidade");
        cpfLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcRight.gridx = 0;
        gbcRight.gridy = 1;
        gbcRight.gridwidth = GridBagConstraints.REMAINDER;
        gbcRight.anchor = GridBagConstraints.WEST;
        rightPanel.add(cpfLabel, gbcRight);

        // Campo Buscar Cliente
        JTextField buscarClienteField = new JTextField("Buscar Cliente");
        buscarClienteField.setForeground(Color.GRAY);
        buscarClienteField.setPreferredSize(new Dimension(150, 40));
        buscarClienteField.setFont(new Font("Arial", Font.PLAIN, 16));
        buscarClienteField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        gbcRight.weightx = 1.0;
        gbcRight.gridwidth = 1;
        rightPanel.add(buscarClienteField, gbcRight);

        // Butao Buscar
        JButton buscarButton = createModernButton("Buscar", new Color(0, 120, 215), Color.WHITE, 16);
        buscarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ação: Buscar Cliente!", "Ação de Botão", JOptionPane.INFORMATION_MESSAGE);
        });
        gbcRight.gridx = 1;
        gbcRight.gridy = 2;
        gbcRight.weightx = 0.0;
        rightPanel.add(buscarButton, gbcRight);

        // Botões de Ação
        gbcRight.gridx = 0;
        gbcRight.gridwidth = GridBagConstraints.REMAINDER;
        gbcRight.weightx = 1.0;

        // Aplicar Desconto
        JButton descontoButton = createModernButton("Aplicar Desconto", new Color(220, 220, 220), Color.DARK_GRAY, 16);
        descontoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ação: Aplicar Desconto!", "Ação de Botão", JOptionPane.INFORMATION_MESSAGE);
        });
        gbcRight.gridy = 3;
        rightPanel.add(descontoButton, gbcRight);

        // Cancelar Venda
        JButton cancelarButton = createModernButton("Cancelar Venda", new Color(220, 220, 220), Color.DARK_GRAY, 16);
        cancelarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ação: Cancelar Venda!", "Ação de Botão", JOptionPane.INFORMATION_MESSAGE);
        });
        gbcRight.gridy = 4;
        rightPanel.add(cancelarButton, gbcRight);

        // Consultar Preço
        JButton precoButton1 = createModernButton("Consultar Preço", new Color(220, 220, 220), Color.DARK_GRAY, 16);
        precoButton1.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ação: Consultar Preço (Claro)!", "Ação de Botão", JOptionPane.INFORMATION_MESSAGE);
        });
        gbcRight.gridy = 5;
        rightPanel.add(precoButton1, gbcRight);

        // Consultar Preço 2
        JButton precoButton2 = createModernButton("Consultar Preço", new Color(80, 80, 80), Color.WHITE, 16);
        precoButton2.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ação: Consultar Preço (Escuro)!", "Ação de Botão", JOptionPane.INFORMATION_MESSAGE);
        });
        gbcRight.gridy = 6;
        rightPanel.add(precoButton2, gbcRight);

        // Filler
        JLabel filler = new JLabel();
        gbcRight.gridy = 7;
        gbcRight.weighty = 1.0;
        rightPanel.add(filler, gbcRight);

        setVisible(true);
    }

    private JButton createModernButton(String text, Color bgColor, Color fgColor, int fontSize) {
        JButton button = new JButton(text);
        button.setOpaque(true);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setPreferredSize(new Dimension(150, 45));

        // Remove a borda padrão do JButton para aplicar a customizada
        button.setBorderPainted(false);
        button.setFocusPainted(false); // Remove o quadrado de foco

        // Aplicação do estilo moderno: cantos arredondados
        button.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(bgColor.darker(), 15), // Borda arredondada (raio 15)
                BorderFactory.createEmptyBorder(10, 15, 10, 15) // Padding interno
        ));

        return button;
    }

}


//Classe para criar as bordas arredondadas
class RoundedBorder extends AbstractBorder {
    private Color color;
    private int radius;

    public RoundedBorder(Color c, int r) {
        this.color = c;
        this.radius = r;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Preenche o fundo do componente (importante para JButtons/JLabels)
        g2d.setColor(c.getBackground());
        g2d.fill(new RoundRectangle2D.Double(x, y, width, height, radius, radius));

        // Desenha o contorno
        g2d.setColor(color);
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        // Define o padding para que o conteúdo não toque na borda
        return new Insets(radius/2, radius, radius/2, radius);
    }
}