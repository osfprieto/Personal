package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classes.*;


public class IngresarTest {
	
	public static final String FILE_NAME = "Empresa.emp";
	public static File fileEmpresa = new File(FILE_NAME);
	public static FileInputStream fileInputStream;
	public static FileOutputStream fileOutputStream;
	public static ObjectInputStream objectInputStream;
	public static ObjectOutputStream objectOutputStream;
	
	public static Empresa empresa = new Empresa();
	public static IngresarFrame frameIngreso= new IngresarFrame();
	public static AdministradorGUI frameAdministrador = new AdministradorGUI();
	public static VerClientesGUI verClientesGUI = new VerClientesGUI();
	public static VerEmpleadosGUI verEmpleadosGUI = new VerEmpleadosGUI();
	public static VerProveedorGUI verProveedorGUI = new VerProveedorGUI();
	public static CompradorGUI frameComprador = new CompradorGUI();
	public static VendedorGUI frameVendedor = new VendedorGUI();
	public static ModificarFrame frameModificar = new ModificarFrame();
	public static VerProductosGUI verProductosGUI = new VerProductosGUI();
	public static FacturaVentaGUI facturaVentaGUI = new FacturaVentaGUI();
	public static FacturaCompraGUI facturaCompraGUI = new FacturaCompraGUI();
	public static VerFacturaGUI frameVerFactura = new VerFacturaGUI();
	public static GenerarRegistroGUI frameGenerarRegistro = new GenerarRegistroGUI();
	public static NewEmpresaGUI newEmpresaGUI = new NewEmpresaGUI();
	public static Empleados empleadoAtual;
	
	public static void main(String [] args) throws IOException, ClassNotFoundException{
		try{
			fileInputStream = new FileInputStream(FILE_NAME);
			objectInputStream = new ObjectInputStream(fileInputStream);
			
			/*Object objetoEmpresa = objectInputStream.readObject();
			IngresarTest.empresa = (Empresa) objetoEmpresa;*/
			empresa = (Empresa) objectInputStream.readObject();
			
			objectInputStream.close();
			
			frameIngreso.setVisible(true);
			frameIngreso.setBounds(350, 200, 350, 150);
			frameIngreso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Los datos de la Empresa no fueron encontrados."
					+"\nAhora iniciaremos una nueva empresa.\nPara esto es necesario un administrador.",
					"Ingresar Administrador", JOptionPane.ERROR_MESSAGE);
			newEmpresaGUI.setVisible(true);
		}
	}
	public static void guardar() throws IOException{
		fileOutputStream = new FileOutputStream(FILE_NAME);
		objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(empresa);
		
		objectOutputStream.close();
		
		System.exit(1);
	}
}
