package lpoo2015;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ContactManager  {
	private JFrame frame;
	private ContactsTableModel model;
	private JTable table;
	
	public ContactManager() {
		frame = new JFrame("Manutenção de Lista de Contactos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setPreferredSize(new Dimension(500,500));	
		Container c = frame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		// Construir e adicionar tabela
		model = new ContactsTableModel(); 
		table = new JTable(model);
		table.setRowSelectionAllowed(false);
		c.add(new JScrollPane(table));
		
		// Construir e adicionar barra de botões
		JPanel tools = new JPanel();
		tools.setLayout(new BoxLayout(tools, BoxLayout.X_AXIS));
		tools.add(createInsertButton());
		tools.add(createRemoveButton());
		c.add(tools);		

        frame.pack();
        frame.setVisible(true);
	}

	private JButton createInsertButton() {
		JButton insert = new JButton("Adicionar ");
		
		insert.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Adiciona a linha no modelo
				model.addRow();
				// Coloca corrente a 1ª célula da nova linha na vista
				int newRow = model.getRowCount()-1;
				table.editCellAt(newRow, 0);
				table.setRowSelectionInterval(newRow, newRow);
			}	
		});
		
		return insert;
	}

	private JButton createRemoveButton() {
		JButton remove = new JButton("Eliminar");
		
		remove.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					// Garante que o que estava a ser editado é salvo no modelo
					table.editCellAt(-1, -1);					
					// Remove a linha corrente
					model.removeRow(row);
					// Coloca corrente a mesma linha se possível
					if (model.getRowCount() > 0) {
						int nextRow = Math.min(row,model.getRowCount()-1);
						table.editCellAt(nextRow, 0);
						table.setRowSelectionInterval(nextRow, nextRow);
					}
				}
			}	
		});
		
		return remove;
	}
	
	public static void main(String[] args) {
		new ContactManager();
	}	
}
