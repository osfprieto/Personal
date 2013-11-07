package practicaDosIngeSoftII;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;




public class ControlPrint {

	public static void main(String[] args) {
		//Calendar calendar = new GregorianCalendar();
		//System.out.println(PrintableObject.format(calendar));
		
		List<Forecast> forecasts = new ArrayList<Forecast>();
		
		for(int i=0;i<10;i++)
			forecasts.add(new Forecast("25", new GregorianCalendar()));
		
		Image image = new ImageIcon("test.png").getImage();
		
		print(20, forecasts, image);
		
	}
	
	
	public static void print(double dataDispersion, List<Forecast> forecasts, Image image){
		PrintableObject po = new PrintableObject(dataDispersion, forecasts, image);
		PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(po);
        if (job.printDialog()) {
            try {
				job.print();
			} catch (PrinterException e) {
				System.out.println("Error while printing forecast.");
			}
        } else {
            System.out.println("Forecast printing has been cancelled.");
        }
	}

	public static class PrintableObject implements Printable{
		
        private static final Font FUENTE_SUBTITULO = new Font("Times New Roman", Font.BOLD, 16);
        private static final Font FUENTE_CUERPO = new Font("Times New Roman", Font.PLAIN, 12);
		
		private double dataDispersion;
		private List<Forecast> forecasts;
		private Image image;
		
		
		public PrintableObject(double dataDispersion, List<Forecast> forecasts,
				Image image){
			this.dataDispersion = dataDispersion;
			this.forecasts = forecasts;
			this.image = image;
		}
		
		public int print(Graphics g, PageFormat pageFormat, int pageNum)
				throws PrinterException{	
			
	        
			if(pageNum==0){
				
				int width = (int) pageFormat.getPaper().getWidth();
				int height =  (int) pageFormat.getPaper().getHeight();
				
				pageFormat.getPaper().setImageableArea(0, 0,
                        width, height);
				
				g.setFont(FUENTE_SUBTITULO);
				g.drawString("Forecast trends (ºK)", 100, 100);
				
				g.drawImage(image, 100, 120, width-200>0?width-200:100, 300, null);
				
				g.drawString("Data dispersion: ", 100, 430);
				
				g.drawString("Date (DD/MM/YYYY)", width/4+-25, 470);
				g.drawString("Forecast (ºK)", 3*width/4+-25, 470);
				
				g.setFont(FUENTE_CUERPO);
				
				g.drawString(""+dataDispersion, 250, 430);
			
				Iterator<Forecast> iterator = forecasts.iterator();
				int y = 490;
				while(iterator.hasNext()){
					Forecast forecast = iterator.next();
					
					g.drawString(format(forecast.getFecha()), 150, y);
					g.drawString(""+forecast.getTemperatura(), 3*width/4, y);
					
					y += 15;
				}
				
				return PAGE_EXISTS;
			} else {
				return NO_SUCH_PAGE;
			}
		}
		
		public static String format(Calendar calendar){
			
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH);
			int year = calendar.get(Calendar.YEAR);
			
			return (day<10?"0"+day:""+day)+"/"
					+(month<10?"0"+month:""+month)+"/"
					+year;
		}
		
	}
	
}
