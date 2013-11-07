package uno.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import uno.model.UNOModelo;

public class CardLabelFactory
{

	public static final int CARTA_BOCA_ABAJO = 1000;
	public static final int CARTA_BOCA_ABAJO_CUB = 2000;
//	public static final int CARD_W = 70;
//	public static final int CARD_H = 117;
	
	public static final int CARD_W = 90;
	public static final int CARD_H = 137;
	

	public CardLabel getCardLabel(int code, int color)
	{

		CardLabel card = new CardLabel();
		card.setColor(color);
		card.setNumero(code);

		String colorName;

		if (code == CARTA_BOCA_ABAJO_CUB)
		{
			card.setIcon(new ImageIcon("data/dorso.jpg"));
			card.setPreferredSize(new Dimension(15, 137));
		}
		else
		{
			if (code == CARTA_BOCA_ABAJO)
			{
				card.setIcon(new ImageIcon("data/dorso.jpg"));
			}
			// si esta entre 0 y 18 es carta de n'umero
			else if (0 <= code && code <= 18)
			{
				colorName = sacarNombreColor(color);
				int number = UNOModelo.translateCardNumberCode(code);

				card.setIcon(new ImageIcon("data/" + number + "-" + colorName + ".jpg"));
			}
			else
			{
				colorName = sacarNombreColor(color);
				if (code == UNOModelo.POSICION_CARTA_ROBA2 || code == UNOModelo.POSICION_CARTA_ROBA2 + 1)
				{
					card.setIcon(new ImageIcon("data/+2-" + colorName + ".jpg"));
				}
				else if (code == UNOModelo.POSICION_CARTA_INVIERTE || code == UNOModelo.POSICION_CARTA_INVIERTE + 1)
				{
					card.setIcon(new ImageIcon("data/inv-" + colorName + ".jpg"));
				}
				else if (code == UNOModelo.POSICION_CARTA_SALTO || code == UNOModelo.POSICION_CARTA_SALTO + 1)
				{
					card.setIcon(new ImageIcon("data/pas-" + colorName + ".jpg"));
				}
				else if (code == UNOModelo.POSICION_CARTA_ROBA4)
				{
					card.setIcon(new ImageIcon("data/+4.jpg"));
				}
				else if (code == UNOModelo.POSICION_CARTA_COMODIN)
				{
					card.setIcon(new ImageIcon("data/jok.jpg"));
				}
			}
			card.setPreferredSize(new Dimension(CARD_W, CARD_H));
		}
		return card;

	}

	

	private String sacarNombreColor(int color)
	{
		String colorName = "";
		switch (color)
		{
		case UNOModelo.COLOR_ROJO:
			colorName = "red";
			break;
		case UNOModelo.COLOR_AMARILLO:
			colorName = "yellow";
			break;
		case UNOModelo.COLOR_VERDE:
			colorName = "green";
			break;
		case UNOModelo.COLOR_AZUL:
			colorName = "blue";
			break;
		}
		return colorName;
	}

}
