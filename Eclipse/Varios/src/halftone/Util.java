package halftone;

import java.io.File;

import javax.swing.JFileChooser;

public class Util {
	
	public static File selectFile() {
		JFileChooser fileChooser = new JFileChooser();
		int ret = fileChooser.showOpenDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	public static File selectFolder() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		int ret = fileChooser.showOpenDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	public static File createSibling(File folder, String name) {
		File sibling = new File(folder.getParentFile()+File.separator+name);
		sibling.mkdirs();
		return sibling;
	}
}
