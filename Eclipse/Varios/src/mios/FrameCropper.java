package mios;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.io.FileFilter;
import java.io.IOException;

@SuppressWarnings("serial")
public class FrameCropper extends JFrame implements KeyListener {

	private static final int TOP_SPACING = 0;
	
	private Container cont;
	private File workingDirectory = null;
	private String destinationDirectory = null;
	private int workingHeight;
	private int workingWidth;
	private File[] frames;
	private int currentFrameIndex;
	private FrameCropperPanel frameCropperPanel;
	
	public FrameCropper(){
		super("FrameCropper");
		
		if(recieveInput()){
			initFrame();
		}
	}
	
	public static void main(String[] args) {
		FrameCropper cropper = new FrameCropper();
		cropper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initFrame(){
		System.out.println("Working Directory: " + workingDirectory.getAbsolutePath());
		System.out.println("Destination Directory: " + destinationDirectory);
		System.out.println("Working Width: " + workingWidth);
		System.out.println("Working Height: " + workingHeight);
		
		frames = workingDirectory.listFiles(new FileFilter(){
			@Override
			public boolean accept(File pathname) {
				String name = pathname.getName().toLowerCase();
				return name.endsWith(".jpg") ||
						name.endsWith(".jpeg");
			}});
		
		System.out.println("Frames found: "+ frames.length);
		
		if(frames.length > 0){
			currentFrameIndex = 0;
			
			frameCropperPanel = new FrameCropperPanel(workingWidth, workingHeight);
			frameCropperPanel.setCurrentWorkingFrame(loadFrame());
			frameCropperPanel.addKeyListener(this);
			
			cont = getContentPane();
			cont.add(frameCropperPanel);
			this.setVisible(true);
			this.setSize(workingWidth, workingHeight + TOP_SPACING);
			this.addKeyListener(this);
		}
	}
	
	private void saveLastProcessedImage(){
		File outputFile = new File(destinationDirectory + File.separator + frames[currentFrameIndex].getName());
		outputFile.mkdirs();
		try {
			ImageIO.write(frameCropperPanel.getProcessedImage(), "jpg", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"Retry image: " + outputFile.getName(),
					"Retry image: " + currentFrameIndex,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void nextFrame(){
		saveLastProcessedImage();
		
		currentFrameIndex++;
		if(currentFrameIndex < frames.length){
			frameCropperPanel.setCurrentWorkingFrame(loadFrame());
		} else {
			JOptionPane.showMessageDialog(this, "We're done here.");
			System.exit(0);
		}
	}
	
	private BufferedImage loadFrame(){
		System.out.println("Loading frame: " + currentFrameIndex);
		if(frames[currentFrameIndex] != null){
			try {
				return ImageIO.read(frames[currentFrameIndex]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private boolean recieveInput(){
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		JButton button = new JButton("Select folder");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser directoryChooser = new JFileChooser();
				directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = directoryChooser.showOpenDialog(panel);
				if(result == JFileChooser.APPROVE_OPTION){
					workingDirectory = directoryChooser.getSelectedFile();
					String path = workingDirectory.getParent();
					String directoryName = workingDirectory.getName();
					destinationDirectory = path + File.separator + directoryName + " - reframed";
				}
			}
			
		});
		JSpinner heightSpinner = new JSpinner();
		heightSpinner.setValue(1080);
		JSpinner widthSpinner = new JSpinner();
		widthSpinner.setValue(1920);
		
		
		panel.add(new JLabel("Directory:"));
		panel.add(button);
		panel.add(new JLabel("Width:"));
		panel.add(widthSpinner);
		panel.add(new JLabel("Height:"));
		panel.add(heightSpinner);
		
		JOptionPane.showMessageDialog(this, panel, "Input", JOptionPane.QUESTION_MESSAGE);
		
		try{
			if(workingDirectory != null && destinationDirectory != null){
				workingWidth = (Integer) widthSpinner.getValue();
				workingHeight = (Integer) heightSpinner.getValue();
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_LEFT) {frameCropperPanel.moveLeft();}
		else if(c == KeyEvent.VK_UP) {frameCropperPanel.moveUp();}
		else if(c == KeyEvent.VK_RIGHT) {frameCropperPanel.moveRight();}
		else if(c == KeyEvent.VK_DOWN) {frameCropperPanel.moveDown();}
		else if(c == KeyEvent.VK_ENTER) {nextFrame();}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		repaint();
	}
	
	private class FrameCropperPanel extends JPanel{
		
		private static final int DELTA = 15;
		
		private BufferedImage currentWorkingFrame;
		private int currentX = 0;
		private int currentY = 0;
		
		private int workingWidth;
		private int workingHeight;
		
		public void moveLeft(){ currentX-=DELTA; }
		public void moveRight() { currentX+=DELTA; }
		public void moveUp() { currentY-=DELTA; }
		public void moveDown() { currentY+=DELTA; }
		
		public FrameCropperPanel(int workingWidth, int workingHeight){
			this.workingHeight = workingHeight;
			this.workingWidth = workingWidth;
			this.setSize(workingWidth, workingHeight + TOP_SPACING);
		}
		
		public void setCurrentWorkingFrame(BufferedImage currentWorkingFrame){
			this.currentWorkingFrame = currentWorkingFrame;
		}
		
		public BufferedImage getProcessedImage(){
			BufferedImage dest = currentWorkingFrame.getSubimage(currentX,  currentY,  workingWidth, workingHeight);
			return dest;
		}
		
		@Override
		public void paint(Graphics g){
			g.drawImage(getProcessedImage(), 0, TOP_SPACING, this);
		}
		
	}
}
