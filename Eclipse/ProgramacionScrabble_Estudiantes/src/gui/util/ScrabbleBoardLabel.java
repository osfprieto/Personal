package gui.util;

import javax.swing.JLabel;

public class ScrabbleBoardLabel extends JLabel{
	private int row;
	private int column;
	public ScrabbleBoardLabel(int nRow, int nColumn)
	{
		super();
		this.row= nRow;
		this.column = nColumn;
	}
	public int getRow()
	{
		return this.row;			
	}
	public int getColumn()
	{
		return this.column;
	}
	public void setRow(int nRow)
	{
		this.row = nRow;
	}
	public void setColumn(int nColumn)
	{
		this.column= nColumn;
	}
	
}
