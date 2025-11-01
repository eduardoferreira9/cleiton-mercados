package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Point2D;

public class GestaoDeProduto extends JFrame {

    public GestaoDeProduto(String usuario) {

        setTitle("Sistema do Supermercado Cleitin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tamanho mínimo para evitar que a tela fique muito pequena
        setMinimumSize(new Dimension(800, 600));

        // --- Painel de Fundo com Degrade ---
        JPanel painelDegrade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Cores utilizadas (tons de cinza para o degrade)
                Color cor1 = new Color(215, 215, 215);
                Color cor2 = new Color(150, 150, 150);
                Color cor3 = new Color(96, 96, 96);

                // Local de inicio do degrade (Vertical)
                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, getHeight());
                float[] fractions = {0.0f, 0.5f, 1.0f};

                // Array das cores
                Color[] colors = {cor1, cor2, cor3};

                // Definindo as cores e preenchendo o fundo
                LinearGradientPaint lgp = new LinearGradientPaint(start, end, fractions, colors);
                g2d.setPaint(lgp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        painelDegrade.setLayout(new GridBagLayout()); // Usando GridBagLayout para centralizar e organizar
        add(painelDegrade, BorderLayout.CENTER);


        // --- Painel principal central para os componentes (fundo branco com bordas arredondadas simuladas) ---
        JPanel painelConteudo = new JPanel(new BorderLayout(20, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                // Simula bordas arredondadas e o fundo branco da área principal
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                // Desenha o retângulo com cantos arredondados (ajuste os valores de 20 e 20 para o raio)
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2d.dispose();
            }
        };
        painelConteudo.setPreferredSize(new Dimension(1000, 600)); // Tamanho ideal para o painel de conteúdo
        painelConteudo.setOpaque(false); // Torna o painel transparente para ver o fundo arredondado
        painelConteudo.setBorder(new EmptyBorder(20, 30, 30, 30)); // Padding interno

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(50, 50, 50, 50); // Margem para o painel de conteúdo
        painelDegrade.add(painelConteudo, gbc);


        // --- Componentes do Cabeçalho (Topo) ---
        JPanel painelCabecalho = new JPanel(new BorderLayout(10, 0));
        painelCabecalho.setOpaque(false); // Transparente

        // 1. Lado Esquerdo: Ícone e Título
        JPanel painelTituloGeral = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelTituloGeral.setOpaque(false);

        // Ícone (Simulação de um gráfico azul)
        JLabel iconeLabel = new JLabel();
        try {
            // Usa o método auxiliar para simular o ícone de estatística/gráfico
            iconeLabel.setIcon(new ImageIcon(getIconeGrafico(40, 40)));

        } catch (Exception e) {
            // Tratamento de erro
        }

        // Título
        JLabel tituloLabel = new JLabel("Supermercado Cleitin");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 28));
        tituloLabel.setForeground(new Color(60, 60, 60)); // Cor escura para o título

        painelTituloGeral.add(iconeLabel);
        painelTituloGeral.add(tituloLabel);


        // 2. Lado Direito: Botão SAIR
        JButton btnSair = new JButton("SAIR");
        btnSair.setBackground(new Color(220, 38, 38)); // Vermelho
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Arial", Font.BOLD, 16));
        btnSair.setFocusPainted(false);
        btnSair.setPreferredSize(new Dimension(80, 35));
        btnSair.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
        btnSair.addActionListener(e -> dispose()); // Fecha a janela ao clicar

        // Monta o cabeçalho
        painelCabecalho.add(painelTituloGeral, BorderLayout.WEST);
        painelCabecalho.add(btnSair, BorderLayout.EAST);

        painelConteudo.add(painelCabecalho, BorderLayout.NORTH);


        // --- Painel Central para Busca, Botões, Título e Tabela ---
        JPanel painelCentral = new JPanel();
        painelCentral.setOpaque(false);
        painelCentral.setLayout(new GridBagLayout()); // Usa GridBagLayout para controle preciso

        GridBagConstraints gbcCentral = new GridBagConstraints();
        gbcCentral.fill = GridBagConstraints.HORIZONTAL;
        gbcCentral.insets = new Insets(0, 0, 15, 0); // Espaçamento entre as linhas


        // 1. Linha superior: Campo de Busca/Botões (Esquerda) e Título da Tela (Direita)

        // --- Título da Tela ("Gestão de Produtos") ---
        JLabel tituloProdutos = new JLabel("Gestão de Produtos");
        tituloProdutos.setFont(new Font("Arial", Font.BOLD, 28));
        tituloProdutos.setForeground(new Color(60, 60, 60)); // Cor escura

        gbcCentral.gridx = 1;
        gbcCentral.gridy = 0;
        gbcCentral.weightx = 0.5; // Ocupa a metade direita
        gbcCentral.anchor = GridBagConstraints.EAST;
        painelCentral.add(tituloProdutos, gbcCentral);

        // --- Campo de Busca e Botões ---
        JPanel painelBuscaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelBuscaAcoes.setOpaque(false);

        JTextField campoBusca = new JTextField(25);
        campoBusca.setText("Buscar por Nome, Código de Barras");
        campoBusca.setForeground(Color.GRAY);
        campoBusca.setFont(new Font("Arial", Font.PLAIN, 14));
        campoBusca.setPreferredSize(new Dimension(300, 30));

        // Listener para simular o placeholder
        campoBusca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campoBusca.getText().equals("Buscar por Nome, Código de Barras")) {
                    campoBusca.setText("");
                    campoBusca.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campoBusca.getText().isEmpty()) {
                    campoBusca.setForeground(Color.GRAY);
                    campoBusca.setText("Buscar por Nome, Código de Barras");
                }
            }
        });

        JButton btnNovoProduto = criarBotaoAcao("Novo Produto", new Color(96, 96, 96));
        JButton btnBuscar = criarBotaoAcao("Buscar", new Color(96, 96, 96));

        painelBuscaAcoes.add(campoBusca);
        painelBuscaAcoes.add(btnNovoProduto);
        painelBuscaAcoes.add(btnBuscar);

        gbcCentral.gridx = 0;
        gbcCentral.gridy = 0;
        gbcCentral.weightx = 0.5; // Ocupa a metade esquerda
        gbcCentral.anchor = GridBagConstraints.WEST;
        painelCentral.add(painelBuscaAcoes, gbcCentral);


        // 2. Tabela de Produtos (Linha 2)
        String[] colunas = {"Código", "Nome", "Quantidade", "Preço", "Descrição"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0); // 0 linhas iniciais

        // Adicionando algumas linhas de exemplo
        for (int i = 0; i < 15; i++) {
            model.addRow(new Object[]{"", "", "", "", ""});
            model.addRow(new Object[]{"", "", "", "", ""});


        }


        JTable tabelaProdutos = new JTable(model);
        tabelaProdutos.setRowHeight(35); // Altura da linha

        // Customização do cabeçalho
        tabelaProdutos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabelaProdutos.getTableHeader().setBackground(Color.WHITE); // Fundo branco
        tabelaProdutos.getTableHeader().setForeground(new Color(60, 60, 60)); // Cor do texto
        tabelaProdutos.getTableHeader().setReorderingAllowed(false); // Impede reordenamento de colunas
        tabelaProdutos.getTableHeader().setPreferredSize(new Dimension(1, 40)); // Altura do cabeçalho

        // Configuração da tabela
        tabelaProdutos.setShowGrid(true);
        tabelaProdutos.setGridColor(new Color(200, 200, 200)); // Linhas de grade claras
        tabelaProdutos.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borda ao redor da tabela

        gbcCentral.gridx = 0;
        gbcCentral.gridy = 1;
        gbcCentral.gridwidth = 2; // Ocupa as duas colunas (busca e título)
        gbcCentral.weighty = 1.0; // MUITO IMPORTANTE: Faz a tabela crescer verticalmente
        gbcCentral.fill = GridBagConstraints.BOTH; // Faz a tabela crescer em ambas as direções
        gbcCentral.insets = new Insets(20, 0, 0, 0); // Espaço acima da tabela
        painelCentral.add(scrollPane, gbcCentral);

        painelConteudo.add(painelCentral, BorderLayout.CENTER);
    }

    // --- Método auxiliar para criar botões de ação ---
    private JButton criarBotaoAcao(String texto, Color corFundo) {
        JButton btn = new JButton(texto);
        btn.setBackground(corFundo);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 30));
        // Remove a borda padrão do Swing e adiciona um padding
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return btn;
    }

    // --- Método auxiliar para simular o ícone de gráfico/estatística ---
    // Cria um ImageIcon a partir de uma imagem desenhada em runtime.
    private Image getIconeGrafico(int width, int height) {
        Image image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        // Cor azul clara do ícone na imagem
        Color corIcone = new Color(66, 133, 244);
        g2d.setColor(corIcone);
        g2d.setStroke(new BasicStroke(2)); // Linha mais grossa para o contorno
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 5;
        int y = 5;
        int w = width - 10;
        int h = height - 10;

        // Barra vertical principal (base do gráfico)
        g2d.drawLine(x, y + h, x, y + 2);
        // Barra horizontal (eixo X)
        g2d.drawLine(x, y + h, x + w, y + h);

        // Barras do gráfico
        int barWidth = (w - 20) / 4;

        // 1ª barra
        g2d.fillRect(x + 5, y + h - h / 3, barWidth, h / 3);
        // 2ª barra
        g2d.fillRect(x + 5 + barWidth + 5, y + h - h / 2, barWidth, h / 2);
        // 3ª barra
        g2d.fillRect(x + 5 + 2 * (barWidth + 5), y + h - h * 2 / 3, barWidth, h * 2 / 3);
        // 4ª barra
        g2d.fillRect(x + 5 + 3 * (barWidth + 5), y + h - h / 4, barWidth, h / 4);

        g2d.dispose();
        return image;
    }


}