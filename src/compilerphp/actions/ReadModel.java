package compilerphp.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;         // |
//import org.jdom2.Element;          // |\ Librerías
//import org.jdom2.JDOMException;    // |/ JDOM
//import org.jdom2.input.SAXBuilder; // |
import org.jdom2.input.SAXBuilder;

//import compilerphp.actions.WriteSQL;

public class ReadModel{
	
	public static void test(){
		WriteSQL.put("SQL");
	}
	
	
	public static void cargarXml() throws IOException
	{
		
			FileReader fr = new FileReader("/home/leo/default.metawebdesign");
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s = br.readLine()) != null) {
				System.out.println(s);
			}
			fr.close();
			
	}
	
	public static void main(String[] args) throws IOException {
	    cargarXml();
	}
}