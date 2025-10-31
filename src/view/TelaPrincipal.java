package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(String usuario) {
        setTitle("Sistema do Supermercado Cleitin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tamanho mínimo para evitar que a tela fique muito pequena
        setMinimumSize(new Dimension(600, 400));

        JPanel painelDegrade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                //Cores utilizadas
                Color cor1 = new Color(215, 215, 215);
                Color cor2 = new Color(150, 150, 150);
                Color cor3 = new Color(96, 96, 96);

                //Local de inicio do degrade
                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, getHeight());
                float[] fractions = {0.0f, 0.5f, 1.0f};

                //Array das cores
                Color[] colors = {cor1, cor2, cor3};

                //Definindo as cores
                LinearGradientPaint lgp = new LinearGradientPaint(start, end, fractions, colors);
                g2d.setPaint(lgp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelDegrade.setLayout(new BorderLayout());
        add(painelDegrade, BorderLayout.CENTER);

        // Painel lateral (Menu)
        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));

        // Mantém a largura fixa do menu lateral, enquanto a altura é flexível
        painelMenu.setPreferredSize(new Dimension(560, 90));
        painelMenu.setMaximumSize(new Dimension(1060, Integer.MAX_VALUE));

        painelMenu.setOpaque(false);
        painelMenu.setBorder(new EmptyBorder(100, 0, 30, 0));

        Color corBotaoEscuro = new Color(99, 99, 99);

        // Criação dos botões (JLabel's)
        JLabel lblCadastrarUsuario = criarBotaoMenu(
                "<html><center>Cadastrar<br>Usuário</center></html>",
                corBotaoEscuro,
                Color.WHITE
        );

        JLabel lblGestaoClientes = criarBotaoMenu(
                "<html><center>Gestão de<br>Clientes</center></html>",
                corBotaoEscuro,
                Color.WHITE
        );

        JLabel lblGestaoProdutos = criarBotaoMenu(
                "<html><center>Gestão de<br>Produtos</center></html>",
                corBotaoEscuro,
                Color.WHITE
        );

        JLabel lblSistemaVenda = criarBotaoMenu(
                "<html><center>Sistema<br>de Venda</center></html>",
                corBotaoEscuro,
                Color.WHITE
        );

        JLabel lblDeslogar = criarBotaoMenu("Deslogar", new Color(200, 0, 0), Color.WHITE);

        // Alinhamento central para os componentes no BoxLayout
        lblCadastrarUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblGestaoClientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblGestaoProdutos.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSistemaVenda.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDeslogar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona os componentes no painelMenu
        painelMenu.add(Box.createVerticalStrut(15));
        painelMenu.add(lblCadastrarUsuario);
        painelMenu.add(Box.createVerticalStrut(15));
        painelMenu.add(lblGestaoClientes);
        painelMenu.add(Box.createVerticalStrut(15));
        painelMenu.add(lblGestaoProdutos);
        painelMenu.add(Box.createVerticalStrut(15));
        painelMenu.add(lblSistemaVenda);
        painelMenu.add(Box.createVerticalGlue()); // Empurra "Deslogar" para baixo
        painelMenu.add(lblDeslogar);


        painelDegrade.add(painelMenu, BorderLayout.WEST);


        // Painel de Conteúdo Principal (Ocupa o restante da tela de forma responsiva)
        JPanel painelBemVindo = new JPanel();
        painelBemVindo.setBackground(new Color(99,99,99));
        painelBemVindo.setLayout(new BorderLayout());

        // Texto de Bem-vindo
        JLabel lblBemVindo = new JLabel();
        String textoHTML = "<html><div style='text-align: center;'>"
                + "Bem-vindo " + usuario + "<br>"
                + "ao Sistema do<br>"
                + "Supermercado Cleitin</div></html>";

        lblBemVindo.setText(textoHTML);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 35));
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
        lblBemVindo.setVerticalAlignment(SwingConstants.CENTER);


        // Adiciona o texto ao painel principal, garantindo a centralização
        painelBemVindo.add(lblBemVindo, BorderLayout.CENTER);

        // Adiciona o painel principal no CENTER, que ocupa todo o espaço restante
        painelDegrade.add(painelBemVindo, BorderLayout.CENTER);


        // 💡 IMPORTANTE: 'setVisible(true)' DEVE SER A ÚLTIMA CHAMADA
        // após configurar o estado maximizado.
        setVisible(true);

    }


    // Método que cria os botoes (Mantido)
    private JLabel criarBotaoMenu(String texto, Color corFundo, Color corTexto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setOpaque(true);
        label.setBackground(corFundo);
        label.setForeground(corTexto);

        label.setPreferredSize(new Dimension(200, 100));
        label.setMaximumSize(new Dimension(1060, 200));

        label.setBorder(BorderFactory.createEmptyBorder(16, 8, 16, 0));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito de hover com animação suave
        Color corOriginal = corFundo;
        Color corHover = escurecerCor(corFundo, 0.15f);
        Timer[] animacao = new Timer[1];

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                animarCor(label, corOriginal, corHover, 200, animacao);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                animarCor(label, label.getBackground(), corOriginal, 200, animacao);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                String textoLimpo = texto.replaceAll("\\<.*?\\>", "");
                JOptionPane.showMessageDialog(label, "Você clicou em: " + textoLimpo);
            }
        });

        return label;
    }

    // Função para animar transição de cor (Mantida)
    private void animarCor(JLabel label, Color corInicial, Color corFinal, int duracao, Timer[] refTimer) {
        if (refTimer[0] != null && refTimer[0].isRunning()) refTimer[0].stop();

        int frames = 20;
        int intervalo = duracao / frames;
        float[] startRGB = corInicial.getRGBComponents(null);
        float[] endRGB = corFinal.getRGBComponents(null);

        refTimer[0] = new Timer(intervalo, new ActionListener() {
            int step = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                float ratio = (float) step / (float) frames;
                float r = startRGB[0] + (endRGB[0] - startRGB[0]) * ratio;
                float g = startRGB[1] + (endRGB[1] - startRGB[1]) * ratio;
                float b = startRGB[2] + (endRGB[2] - startRGB[2]) * ratio;
                label.setBackground(new Color(r, g, b));
                step++;
                if (step > frames) ((Timer) e.getSource()).stop();
            }
        });
        refTimer[0].start();
    }

    // Escurece uma cor em percentual (Mantida)
    private Color escurecerCor(Color cor, float fator) {
        int r = (int) (cor.getRed() * (1 - fator));
        int g = (int) (cor.getGreen() * (1 - fator));
        int b = (int) (cor.getBlue() * (1 - fator));
        return new Color(r, g, b);
    }


}