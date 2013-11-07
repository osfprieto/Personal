package poligran.gui;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BuscaminasTable extends JTable
{
	private TableCellRenderer renderer;

	public BuscaminasTable(BuscaminasCellRenderer renderer)
	{
		this.renderer = renderer;
	}

	public TableCellRenderer getCellRenderer(int row, int column)
	{
		return renderer;
	}
}
