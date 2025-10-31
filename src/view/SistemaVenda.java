package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.*;

public class SistemaVenda extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public SistemaVenda(String usuario) {
        setTitle("Sistema do Supermercado Cleitin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(900, 600));

        // Painel com fundo degradê
        JPanel painelDegrade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color cor1 = new Color(215, 215, 215);
                Color cor2 = new Color(150, 150, 150);
                Color cor3 = new Color(96, 96, 96);

                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, getHeight());
                float[] fractions = {0.0f, 0.5f, 1.0f};
                Color[] colors = {cor1, cor2, cor3};
                g2d.setPaint(new LinearGradientPaint(start, end, fractions, colors));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelDegrade.setLayout(new BorderLayout());
        add(painelDegrade, BorderLayout.CENTER);

        // Painel Superior (Header)
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.weightx = 1.0;

        JLabel logoLabel = new JLabel("🛒 Supermercado Cleitin");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        logoLabel.setForeground(Color.WHITE);
        headerPanel.add(logoLabel, gbcHeader);

        JLabel sairButton = criarBotaoModernizado("SAIR", new Color(200, 50, 50), Color.WHITE);
        sairButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new TelaPrincipal(usuario);
            }
        });

        gbcHeader.weightx = 0.0;
        gbcHeader.anchor = GridBagConstraints.EAST;
        headerPanel.add(sairButton, gbcHeader);

        painelDegrade.add(headerPanel, BorderLayout.NORTH);

        // Painel central
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        painelDegrade.add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Painel esquerdo
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(leftPanel, gbc);

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5, 0, 5, 0);
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        gbcLeft.gridwidth = GridBagConstraints.REMAINDER;
        gbcLeft.weightx = 1.0;

        JTextField scanField = criarCampoComPlaceholder("Escanear ou Digitar Código de Barras/Nome");
        gbcLeft.gridy = 0;
        leftPanel.add(scanField, gbcLeft);

        JLabel registroLabel = new JLabel("Registro de Itens");
        registroLabel.setFont(new Font("Arial", Font.BOLD, 14));
        registroLabel.setForeground(Color.WHITE);
        gbcLeft.gridy = 1;
        leftPanel.add(registroLabel, gbcLeft);

        JTextField produtoField = criarCampoComPlaceholder("Produto/Itens");
        gbcLeft.gridy = 2;
        leftPanel.add(produtoField, gbcLeft);

        // TABELA DE VENDAS
        String[] colunas = {"Nome do Produto", "Quantidade", "Valor Unit.", "Subtotal"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Apenas a coluna de Quantidade é editável
                return column == 1;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(28);
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));

        //header
        JTableHeader header = tabela.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(new Font("Arial", Font.BOLD, 15));

        // Definindo uma renderer personalizado para o header
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            private final Color bg = new Color(96, 96, 96);
            private final Color fg = Color.WHITE;

            {
                setHorizontalAlignment(SwingConstants.CENTER);
                setOpaque(true); // ESSENCIAL
                setBackground(bg);
                setForeground(fg);
                setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int col) {
                // Usa o texto da coluna (value) e aplica as cores definidas acima
                setText(value == null ? "" : value.toString());
                setBackground(bg);
                setForeground(fg);
                return this;
            }
        });

        header.repaint();

        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : new Color(225, 225, 225));
                }
                setHorizontalAlignment(column == 1 || column == 2 || column == 3 ? CENTER : LEFT);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tabela);
        gbcLeft.weighty = 1.0;
        gbcLeft.gridy = 3;
        leftPanel.add(scrollPane, gbcLeft);

        // Painel direito
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(rightPanel, gbc);

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5, 10, 5, 0);
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        JLabel vendaLabel = new JLabel("Venda");
        vendaLabel.setFont(new Font("Arial", Font.BOLD, 30));
        vendaLabel.setForeground(Color.WHITE);
        gbcRight.gridy = 0;
        rightPanel.add(vendaLabel, gbcRight);

        JTextField buscarClienteField = criarCampoComPlaceholder("Buscar Cliente / CPF");
        gbcRight.gridy = 1;
        rightPanel.add(buscarClienteField, gbcRight);

        JLabel aplicarDesconto = criarBotaoModernizado("Aplicar Desconto", new Color(99, 99, 99), Color.WHITE);
        gbcRight.gridy = 2;
        rightPanel.add(aplicarDesconto, gbcRight);

        JLabel cancelarVenda = criarBotaoModernizado("Cancelar Venda", new Color(180, 60, 60), Color.WHITE);
        gbcRight.gridy = 3;
        rightPanel.add(cancelarVenda, gbcRight);

        JLabel consultarPreco1 = criarBotaoModernizado("Consultar Preço", new Color(80, 80, 80), Color.WHITE);
        gbcRight.gridy = 4;
        rightPanel.add(consultarPreco1, gbcRight);

        // Listener para atualizar subtotal automaticamente
        modeloTabela.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int col = e.getColumn();
            if (col == 1 && row >= 0) { // Se editou a quantidade
                try {
                    double qtd = Double.parseDouble(modeloTabela.getValueAt(row, 1).toString());
                    double valor = Double.parseDouble(modeloTabela.getValueAt(row, 2).toString());
                    modeloTabela.setValueAt(String.format("%.2f", qtd * valor), row, 3);
                } catch (Exception ignored) {}
            }
        });

        setVisible(true);
    }

    // Campo com placeholder funcional
    private JTextField criarCampoComPlaceholder(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setForeground(Color.GRAY);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });

        return campo;
    }

    // 🔹 Botão moderno
    private JLabel criarBotaoModernizado(String texto, Color corFundo, Color corTexto) {
        JLabel botao = new JLabel(texto, SwingConstants.CENTER);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setOpaque(true);
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color corHover = escurecerCor(corFundo, 0.15f);
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { botao.setBackground(corHover); }
            @Override
            public void mouseExited(MouseEvent e) { botao.setBackground(corFundo); }
        });

        return botao;
    }

    private Color escurecerCor(Color cor, float fator) {
        int r = (int) (cor.getRed() * (1 - fator));
        int g = (int) (cor.getGreen() * (1 - fator));
        int b = (int) (cor.getBlue() * (1 - fator));
        return new Color(r, g, b);
    }
}
