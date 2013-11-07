package gui;

public class CompradorYVendedorTest {


	public static void main(String[] args) {
		CompradorGUI frame=new CompradorGUI();
		VendedorGUI frame2=new VendedorGUI();
		frame2.setBounds(600, 200, 350, 100);
		frame.setBounds(250, 200, 350, 100);
		frame.setVisible(true);
		frame2.setVisible(true);
	}

}
