package poligran.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class BuscaminasCellRenderer extends DefaultTableCellRenderer
{

	private ImageIcon mina = new ImageIcon("./img/mina.jpg");
	
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) 
	{
		JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		renderedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		if (value.equals("M"))
		{
			renderedLabel.setIcon(mina);
			renderedLabel.setBackground(Color.YELLOW);
		}
		else if (value.equals(" "))
		{
			renderedLabel.setBackground(Color.GRAY);
		}
		else if (value.equals("0"))
		{
			renderedLabel.setBackground(Color.WHITE);
		}
		else
		{
			renderedLabel.setBackground(Color.WHITE);
		}
		return renderedLabel;
	}
}
