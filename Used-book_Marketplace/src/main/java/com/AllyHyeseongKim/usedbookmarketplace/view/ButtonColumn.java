package com.AllyHyeseongKim.usedbookmarketplace.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;


public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {
    private JTable jTable;
    private Action action;
    private Border originalBorder;
    private Border focusBorder;

    private JButton renderButton;
    private JButton editButton;
    private Object editValue;
    private boolean isButtonColumnEditor;

    public ButtonColumn(JTable jTable, Action action, int column) {
        this.jTable = jTable;
        this.action = action;

        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );
        originalBorder = editButton.getBorder();
        setFocusBorder( new LineBorder(Color.BLUE) );

        TableColumnModel columnModel = jTable.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
        jTable.addMouseListener( this );
    }

    public void setFocusBorder(Border focusBorder) {
        this.focusBorder = focusBorder;
        editButton.setBorder( focusBorder );
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) {
            editButton.setText( "" );
            editButton.setIcon( null );
        } else if (value instanceof Icon) {
            editButton.setText( "" );
            editButton.setIcon( (Icon)value );
        } else {
            editButton.setText( value.toString() );
            editButton.setIcon( null );
        }

        this.editValue = value;
        return editButton;
    }

    @Override
    public Object getCellEditorValue()
    {
        return editValue;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        if (hasFocus) {
            renderButton.setBorder( focusBorder );
        } else {
            renderButton.setBorder( originalBorder );
        }

        if (value == null) {
            renderButton.setText( "" );
            renderButton.setIcon( null );
        } else if (value instanceof Icon) {
            renderButton.setText( "" );
            renderButton.setIcon( (Icon)value );
        } else {
            renderButton.setText( value.toString() );
            renderButton.setIcon( null );
        }

        return renderButton;
    }

    public void actionPerformed(ActionEvent e) {
        int row = jTable.convertRowIndexToModel( jTable.getEditingRow() );
        fireEditingStopped();

        ActionEvent event = new ActionEvent(
                jTable,
                ActionEvent.ACTION_PERFORMED,
                "" + row);
        action.actionPerformed(event);
    }

    public void mousePressed(MouseEvent e) {
        if (jTable.isEditing()
                &&  jTable.getCellEditor() == this)
            isButtonColumnEditor = true;
    }

    public void mouseReleased(MouseEvent e) {
        if (isButtonColumnEditor
                &&  jTable.isEditing())
            jTable.getCellEditor().stopCellEditing();

        isButtonColumnEditor = false;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}