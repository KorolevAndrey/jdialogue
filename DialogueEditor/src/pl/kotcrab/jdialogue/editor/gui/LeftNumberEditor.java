/*******************************************************************************
 * DialogueEditor
 * Copyright (C) 2013-2014 Pawel Pastuszak
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package pl.kotcrab.jdialogue.editor.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

@SuppressWarnings("rawtypes")
public class LeftNumberEditor extends DefaultCellEditor // modifed copy of JTable.GenericEditor
{
	private static final long serialVersionUID = 1L;
	Class[] argTypes = new Class[]{String.class};
	java.lang.reflect.Constructor constructor;
	Object value;

	public LeftNumberEditor () {
		super(new JTextField());
		getComponent().setName("Table.editor");

		final JTextField textField = (JTextField) getComponent(); // changed aligment and added autoselect when edited
		textField.setHorizontalAlignment(JTextField.LEFT);
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost (FocusEvent e) {
			}

			@Override
			public void focusGained (FocusEvent e) {
				textField.selectAll();
			}
		});

	}

	public boolean stopCellEditing () {
		String s = (String) super.getCellEditorValue();
		// Here we are dealing with the case where a user
		// has deleted the string value in a cell, possibly
		// after a failed validation. Return null, so that
		// they have the option to replace the value with
		// null or use escape to restore the original.
		// For Strings, return "" for backward compatibility.
		if ("".equals(s)) {
			((JComponent) getComponent()).setBorder(new LineBorder(Color.red));
			return false;
		}

		try {
			value = constructor.newInstance(new Object[]{s});
		} catch (Exception e) {
			((JComponent) getComponent()).setBorder(new LineBorder(Color.red));
			return false;
		}

		int a = new Integer(s); // added value checking

		if (a <= 0 || a > 999) {
			((JComponent) getComponent()).setBorder(new LineBorder(Color.red));
			return false;
		}

		return super.stopCellEditing();
	}

	public Component getTableCellEditorComponent (JTable table, Object value, boolean isSelected, int row, int column) {
		this.value = null;
		((JComponent) getComponent()).setBorder(new LineBorder(Color.black));
		try {
			Class<?> type = table.getColumnClass(column);
			// Since our obligation is to produce a value which is
			// assignable for the required type it is OK to use the
			// String constructor for columns which are declared
			// to contain Objects. A String is an Object.
			if (type == Object.class) {
				type = String.class;
			}
			constructor = type.getConstructor(argTypes);
		} catch (Exception e) {
			return null;
		}
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

	public Object getCellEditorValue () {
		return value;
	}
}
