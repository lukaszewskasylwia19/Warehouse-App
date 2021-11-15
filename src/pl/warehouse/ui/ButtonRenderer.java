package pl.warehouse.ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Reprezentuje model przycisku tworzonego w tablicy (JTable)
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer(String label, Color color) {
        setOpaque(true);
        setText(label);
        setBackground(color);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setForeground(Color.white);
        return this;
    }
}
