package halftone;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlackAndWhite {

	public static void main(String[] args) {
		File folder = Util.selectFolder();
		if(folder != null && folder.isDirectory()) {
			File bwFolder = Util.createSibling(folder, folder.getName()+"bw");
			for(File imgFile : folder.listFiles()) 
				processImage(imgFile, bwFolder);
		}
	}

	private static void processImage(File imgFile, File bwFolder) {
		try {
			BufferedImage img = ImageIO.read(imgFile);
			ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	        op.filter(img, img);
	        File bwFile = new File(bwFolder.getAbsolutePath()+File.separator+imgFile.getName().replaceAll("\\.(.)*", ".jpg"));
			bwFile.createNewFile();
	        ImageIO.write(img, "jpg", bwFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
