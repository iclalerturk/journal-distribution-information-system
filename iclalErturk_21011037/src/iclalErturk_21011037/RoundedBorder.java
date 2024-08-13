package iclalErturk_21011037;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

public class RoundedBorder implements Border {

    private int radius;
    private Color borderColor;
    private Color backgroundColor;

    public RoundedBorder(int radius, Color borderColor, Color backgroundColor) {
        this.radius = radius;
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Kenarlıkları çizmeden önce arka planı yuvarlatılmış olarak doldurun
        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);

        // Kenarlığı çiz
        g2.setColor(borderColor);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

        g2.dispose();
    }
}
