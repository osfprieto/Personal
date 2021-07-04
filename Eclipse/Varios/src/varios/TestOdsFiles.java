/*package varios;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.openoffice.odf.doc.OdfDocument;
import org.openoffice.odf.doc.OdfFileDom;
import org.w3c.dom.Node;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;


public class TestOdsFiles {


	

	// Load the ODF document from the path
	OdfDocument odfDoc = OdfDocument.loadDocument("C:\\example.ods");

	// Get the content as DOM tree
	OdfFileDom odfContent = odfDoc.getContentDom();
	// System.out.println( "odfContent = " + odfContent.toString());

	// Initialize XPath
	XPath xpath = odfDoc.getXPath();

	// Issue XPath query to select first cell in each row
	DTMNodeList nodeList = (DTMNodeList) xpath.evaluate(
	"//table:table-row/table:table-cell[1]", odfContent, XPathConstants.NODESET);

	// Print textual content of each cell in the nodeset
	for (int i = 0; i < nodeList.getLength(); i++) {
	Node cell = nodeList.item(i);
	System.out.println(cell.getTextContent());
	} 
	
}
*/