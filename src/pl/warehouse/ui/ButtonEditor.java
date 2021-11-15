package pl.warehouse.ui;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reprezentuje model przycisku znajdujacego sie w tablicy (JTable)
 * pozwala na obsluzenie zdarzen oraz dostarcza informacji pozwalajacych na
 * zidentyfikowanie kliknietego wiersza (selectedRow).
 */
public class ButtonEditor extends DefaultCellEditor implements TableCellEditor {
    public int selectedRow;
    public JButton button;

    public ButtonEditor() {
        super(new JTextField());

        button = new JButton();
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addActionListener(clickHandler);
    }

    ActionListener clickHandler = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
        }
    };

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return selectedRow;
    }

    @Override
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}