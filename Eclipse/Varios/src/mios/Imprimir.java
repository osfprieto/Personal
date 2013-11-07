package mios;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.*;

import javax.imageio.ImageIO;

public class Imprimir {

	public static void main(String[] args) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new ObjetoDeImpresion());
		if(job.printDialog()){
			try{
				job.print();
			}catch(PrinterException e){
				System.out.println(e);
			}
		}
	}

	public static class ObjetoDeImpresion implements Printable{
		public int print(Graphics g, PageFormat f, int pageIndex){
			if(pageIndex<5){
				try {
					BufferedImage bf = ImageIO.read(new File("test.png"));
					g.drawImage(bf, 100, 500, null);
					g.setColor(Color.RED);
					g.setFont(new Font("Ubuntu Light", Font.ITALIC, 20));
					g.drawString("Texto a imprimir, página "+pageIndex, 100, 200);
					g.drawOval(100, 100, (pageIndex+1)*20, 100);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return PAGE_EXISTS;
			}
			else{
				return NO_SUCH_PAGE;
			}
		}
	}
}
