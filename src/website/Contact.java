package website;

import clases.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Contact {

	public Header header = new Header();
	
	public Contact(ProyectoM Proyecto){
		
		System.out.println("Contact.php\n");
		
		String contact = header.getHeader() + header.getMenu() + header.getBegin_main() + 
				header.getEnd_main() + header.getEnd_html();
		
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\contact.php");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(contact);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
		
	}
}
