package halftone;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Halftone {
	
	private static final int HALFTONE_SIZE = 4;
	private static Color colorBuffer[][] = new Color[HALFTONE_SIZE][HALFTONE_SIZE];
	private static Random random = new Random();
	
	public static void main(String[] args) {
		File folder = Util.selectFolder();
		if(folder != null && folder.isDirectory()) {
			File bwFolder = Util.createSibling(folder, folder.getName()+"ht");
			for(File imgFile : folder.listFiles()) 
				processImage(imgFile, bwFolder);
		}
	}

	private static void processImage(File imgFile, File bwFolder) {
		try {
			BufferedImage img = ImageIO.read(imgFile);
			BufferedImage halftoneImg = new BufferedImage(HALFTONE_SIZE*img.getWidth(), HALFTONE_SIZE*img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);			

			// Go over the pixels and halftone them.
			for(int i=0; i<img.getWidth();i++) {
				for(int j=0; j<img.getHeight();j++) {
					Color color = new Color(img.getRGB(i, j));
					generateHalftonePixel(color.getRed()/(HALFTONE_SIZE*HALFTONE_SIZE));
					copyPixel(halftoneImg, i*HALFTONE_SIZE, j*HALFTONE_SIZE);
				}
				System.out.println(i);
			}
			
	        File halftoneFile = new File(bwFolder.getAbsolutePath()+File.separator+imgFile.getName().replaceAll("\\.(.)*", ".png"));
			halftoneFile.createNewFile();
	        ImageIO.write(halftoneImg, "png", halftoneFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void generateHalftonePixel(int color) {
		cleanBuffer();
		int counter = 0;
		while(counter < color) {
			int i = random.nextInt(HALFTONE_SIZE);
			int j = random.nextInt(HALFTONE_SIZE);
			if(colorBuffer[i][j].equals(Color.BLACK)) {
				colorBuffer[i][j] = Color.WHITE;
				counter++;
			}
		}
		//System.out.println(counter);
	}
	
	private static void copyPixel(BufferedImage img, int I, int J) {
		for (int i=0; i<HALFTONE_SIZE; i++)
			for (int j=0; j<HALFTONE_SIZE; j++)
				img.setRGB(i+I, j+J, colorBuffer[i][j].getRGB());
	}
	
	private static void cleanBuffer() {
		for(int i=0;i<HALFTONE_SIZE;i++)
			for(int j=0;j<HALFTONE_SIZE;j++)
				colorBuffer[i][j] = Color.BLACK;
	}
}
