//Class validate XML
package validate;

//DomParser
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class Validate {
	private Document doc;
	private boolean state;
	
	public Validate(Document doc){
		int clases, atributos, servicios;
		clases = atributos = servicios = 0;
		
		this.state = true;
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		if(!doc.getDocumentElement().getNodeName().equals("Herramienta")){
			message(1);
			return;
		}
		
		NodeList nList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());
		
		 for (int a = 0; a < nList.getLength() ; a++) {
			Node bNode = nList.item(a);
			
			System.out.println("\n" + bNode.getNodeName());
			
			if (bNode.getNodeType() == Node.ELEMENT_NODE) {
				
				for(int b=0; b < bNode.getChildNodes().getLength(); b++){
						 if(bNode.getChildNodes().item(b).getNodeType() == Node.ELEMENT_NODE){
						 	System.out.println("\t" + bNode.getChildNodes().item(b).getNodeName() );
						 	
						 	if(bNode.getChildNodes().getLength()>0){
						 		Node cNode = bNode.getChildNodes().item(b);
						 	
						 		for(int c=0; c < cNode.getChildNodes().getLength(); c++){
						 			if(cNode.getChildNodes().item(c).getNodeType() == Node.ELEMENT_NODE){
						 				System.out.println("\t\t" + cNode.getChildNodes().item(c).getNodeName());
						 				
						 				if(!cNode.getChildNodes().item(c).getNodeName().equals("ProyectoM")){
						 					message(6);
						 					return;
						 				}
						 				
						 				if(cNode.getChildNodes().getLength()>0){
									 		Node dNode = cNode.getChildNodes().item(c);
									 		
									 		for(int d=0; d < dNode.getChildNodes().getLength(); d++){
									 			if(dNode.getChildNodes().item(d).getNodeType() == Node.ELEMENT_NODE){
									 				System.out.println("\t\t\t" + dNode.getChildNodes().item(d).getNodeName());
									 			
									 				if(dNode.getChildNodes().getLength()>0){
												 		Node eNode = dNode.getChildNodes().item(d);
												 		
												 		for(int e=0; e < eNode.getChildNodes().getLength(); e++){
												 			if(eNode.getChildNodes().item(e).getNodeType() == Node.ELEMENT_NODE){
												 				System.out.println("\t\t\t\t" + eNode.getChildNodes().item(e).getNodeName());
												 				
												 				if(eNode.getChildNodes().item(e).getNodeName().equals("ClaseM")){
												 					clases++;	
												 				}
												 				
												 				if(eNode.getChildNodes().getLength()>0){
															 		Node fNode = eNode.getChildNodes().item(e);
															 		
															 		for(int f=0; f < fNode.getChildNodes().getLength(); f++){
															 			if(fNode.getChildNodes().item(f).getNodeType() == Node.ELEMENT_NODE){
															 				System.out.println("\t\t\t\t\t" + fNode.getChildNodes().item(f).getNodeName());
															 		
															 				
															 				
															 				if(fNode.getChildNodes().getLength()>0){
																		 		Node gNode = fNode.getChildNodes().item(f);
																		 		
																		 		for(int g=0; g < gNode.getChildNodes().getLength(); g++){
																		 			if(gNode.getChildNodes().item(g).getNodeType() == Node.ELEMENT_NODE){
																		 				System.out.println("\t\t\t\t\t" + gNode.getChildNodes().item(g).getNodeName());
																		 				
																		 				if(gNode.getChildNodes().item(g).getNodeName().equals("AtributoM")){
																		 					atributos++;
																		 				}
																		 				
																		 				if(gNode.getChildNodes().item(g).getNodeName().equals("ServicioM")){
																		 					servicios++;
																		 				}
																		 				
																		 				
																		 				
																		 			}
																		 		}
															 				}
															 			}
															 		}
															 	}
												 			}
												 		}
									 				}
									 				
									 			
									 			}
									 		}
									 		
						 				}
						 			}
						 		}
						 	}
						 	
						 }
					 }

				 }
		 }
		
		System.out.println("Cantidad de clases "+clases+" de atributos "+atributos+" de servicios "+servicios);
		System.out.println("END VALIDATE");
		
		
	}
	
	public void message(int error){
		final JPanel msgvalidate = new JPanel();
		JOptionPane.showMessageDialog(msgvalidate, "El XML "+
				"no corresponde al DTD de Integranova.", 
			    		"Error", JOptionPane.ERROR_MESSAGE);
		
		switch(error){
			case 1:
				System.out.println("No se encuentra el tag Herramienta.");
				break;
			
			case 2:				
				System.out.println("No se encuentra el tag NumeroSerie.");
				break;
			
			case 3:				
				System.out.println("No se encuentra el tag Version_DTD.");
				break;
				
			case 4:				
				System.out.println("No se encuentra el tag Version_ONM.");
				break;
				
			case 5:				
				System.out.println("No se encuentra el tag Timestamp.");
				break;
				
			case 6:				
				System.out.println("No se encuentra el Proyecto.");
				break;
				
			case 7:				
				System.out.println("Error en Clase.");
				break;
				
			case 8:				
				System.out.println("Error en Atributo.");
				break;
				
			case 9:				
				System.out.println("Error en Servicio.");
				break;
				
			case 10:				
				System.out.println("Error en.");
				break;
		}
		
		this.state=false;		
	}
	
	public void setstate(boolean state){
		this.state = state;
	}
	
	public boolean getstate(){
		return this.state;
	}
	
}