package test;

import kernel.Kernel;
import junit.framework.TestCase;

public class KernelTest extends TestCase{
	  public void testAdd(){ 
	        int num1 = 3; 
	        int num2 = 2; 
	        int total = 5;
	        Kernel.play(num1, num2); 
	        assertEquals(num2, total);
	  } 
}
