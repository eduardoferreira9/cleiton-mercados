package view;

import javax.swing.border.AbstractBorder;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class Utils {
    public JButton createModernButton(String text, Color bgColor, Color fgColor, int fontSize) {
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

class PlaceHolder implements java.awt.event.FocusListener {

    private final JTextField field;
    private final String placeholder;

    public PlaceHolder(JTextField field, String placeholder) {
        this.field = field;
        this.placeholder = placeholder;
        setPlaceholder(); // initialize with placeholder
    }

    private void setPlaceholder() {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (field.getText().equals(placeholder)) {
            field.setText("");
            field.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (field.getText().isEmpty()) {
            field.setText(placeholder);
            field.setForeground(Color.GRAY);
        }
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