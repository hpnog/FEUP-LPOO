package lpoo2015;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ContactsTableModel extends AbstractTableModel {

	private ArrayList<Contact> contacts = new ArrayList<Contact>();
	private static final String[] columnNames = { "Nome", "Telefone" };

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getColumnCount() {
		return 2;
	}

	public boolean isCellEditable(int row, int column) {
		return true;
	}

	public int getRowCount() {
		return contacts.size();
	}

	public Object getValueAt(int row, int column) {
		if (column == 0)
			return contacts.get(row).getName();
		else // 1
			return contacts.get(row).getPhoneNumber();
	}

	public void setValueAt(Object value, int row, int column) {
		if (column == 0)
			contacts.get(row).setName((String) value);
		else // 1
			contacts.get(row).setPhoneNumber((String) value);
		fireTableCellUpdated(row, column);
	}

	public void addRow() {
		contacts.add(new Contact("", ""));
		fireTableRowsInserted(contacts.size() - 1, contacts.size() - 1);
	}

	public void removeRow(int row) {
		contacts.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
