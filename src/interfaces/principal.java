package interfaces;
import clases.*;
import validate.*;
import mapping.*;
import scripts.*;
import website.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import java.io.File;
import java.awt.EventQueue;

import static java.nio.file.StandardCopyOption.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Window.Type;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Toolkit;



//DomParser
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



//Arrays
import java.util.ArrayList;
import java.util.List;


public class principal {
	
	private static final CopyOption REPLACE_EXISTING = null;
	private JFrame frmCMode;
	private JTextField archivo_xml;
	private JTextField directorio;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal window = new principal();
					window.frmCMode.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		/* ******************
		 * FRAME COMPILERMODE
		 * ******************
		 */
		
		frmCMode = new JFrame();
		frmCMode.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Camilo\\workspace\\compilerMode\\src\\img\\LOGO.png"));
		frmCMode.setResizable(false);
		frmCMode.setTitle("CompilerMode");
		frmCMode.setSize(1024, 768);
		frmCMode.setBounds(100, 100, 450, 300);
		frmCMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCMode.getContentPane().setLayout(null);

		
		/* ************************
		 * DESCRIPCION COMPILERMODE
		 * ************************
		 */		
		
		JLabel lblDescripcinDeCompilermode = new JLabel("CompilerMode");
		lblDescripcinDeCompilermode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcinDeCompilermode.setBounds(10, 11, 424, 26);
		frmCMode.getContentPane().add(lblDescripcinDeCompilermode);
		
		
		/* ******************************
		 * BUTTON SELECCIONAR ARCHIVO XML
		 * ******************************
		 */
		
		JLabel lblIngresarArchivo = new JLabel("Seleccionar modelo:");
		lblIngresarArchivo.setBounds(10, 158, 130, 14);
		frmCMode.getContentPane().add(lblIngresarArchivo);
		
		archivo_xml = new JTextField();
		archivo_xml.setEditable(false);
		archivo_xml.setBounds(150, 155, 210, 20);
		frmCMode.getContentPane().add(archivo_xml);
		archivo_xml.setColumns(10);
		
		JButton file_xml = new JButton("...");
		file_xml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				JFileChooser filechooser = new JFileChooser();
				filechooser.setAcceptAllFileFilterUsed(false);
		        filechooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivo XML", "xml"));
				int opcion = filechooser.showOpenDialog(frmCMode);				
				if(opcion == JFileChooser.APPROVE_OPTION){
					archivo_xml.setText(filechooser.getSelectedFile().getPath());
					
					//Validar extension XML
					if( !filechooser.getSelectedFile().getName().endsWith(".xml") ){						
						//ERROR DE EXTENSION
						final JPanel panel = new JPanel();
					    JOptionPane.showMessageDialog(panel, "El archivo "
					    		+ "ingresado no tiene la extensión XML, "
					    		+ "por favor asegúrese que el archivo "
					    		+ "corresponda a este tipo de documento.", 
					    		"Error", JOptionPane.ERROR_MESSAGE);
						
						System.out.println("No se ha seleccionado un archivo válido!.");
						archivo_xml.setText("");						
					}
				}
			}
		});
		file_xml.setBounds(374, 154, 50, 20);
		frmCMode.getContentPane().add(file_xml);
		
		
		/* ******************************
		 * BUTTON SELECCIONAR DIRECTORIO
		 * ******************************
		 */		
		
		directorio = new JTextField();
		directorio.setEditable(false);
		directorio.setBounds(150, 186, 210, 20);
		frmCMode.getContentPane().add(directorio);
		directorio.setColumns(10);
		
		JLabel lblSeleccionarDirectorio = new JLabel("Seleccionar directorio:");
		lblSeleccionarDirectorio.setBounds(10, 189, 130, 14);
		frmCMode.getContentPane().add(lblSeleccionarDirectorio);
				
		JButton directory = new JButton("...");
		directory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    	//System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
			    	directorio.setText(chooser.getSelectedFile().getPath());
			    				    	
			    	if( chooser.getSelectedFile().getName().length() == 0 ){						
						//ERROR DE EXTENSION
						final JPanel panel = new JPanel();
					    JOptionPane.showMessageDialog(panel, "Debe seleccionar "
					    		+ "un directorio para almacenar el proyecto"
					    		+ "compilado.", 
					    		"Error", JOptionPane.ERROR_MESSAGE);
						
						System.out.println("No se ha seleccionado un directorio!.");
						directorio.setText("");
						
					}
			    }				
				
			}
		});
		directory.setBounds(374, 188, 50, 20);
		frmCMode.getContentPane().add(directory);
		
		
		
		/* ****************
		 * BUTTON COMPILAR
		 * ****************
		 */
		
		JButton btnCompilar = new JButton("Compilar");
		btnCompilar.setEnabled(true);	
				
		btnCompilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verificar si se ingresa un archivo válido y un directorio
				if(directorio.getText().length() == 0 || archivo_xml.getText().length() == 0){
					final JPanel panel = new JPanel();
				    JOptionPane.showMessageDialog(panel, "Debe seleccionar "
				    		+ "un directorio y un archivo en xml para "
				    		+ "compilar el proyecto.", 
				    		"Error", JOptionPane.ERROR_MESSAGE);
					
					System.out.println("Falta archivo xml o directorio.");
				}else{					
					//El directorio y el archivo son válidos
					try{					 
						 	
						 //Lectura de archivo	
						 /*FileReader fr = new FileReader(archivo_xml.getText());
						 BufferedReader br = new BufferedReader(fr);
						 String texto;
						 						
						 while((texto=br.readLine()) != null){
							 System.out.println(texto);							 
						 }*/
						 
						 String filexml = archivo_xml.getText().replace("\\", "\\\\");
						 File fXmlFile = new File(filexml);
							
						 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						 Document doc = dBuilder.parse(fXmlFile);					 
						 						 
						 //NORMALIZE
						 doc.getDocumentElement().normalize();
						 
						 /************
						  * VALIDATE
						  ************/
						 System.out.println("TEST VALIDATE");
						 
						 Validate validate = new Validate(doc);
						 
						 
						 if(validate.getstate()){
					
							 System.out.println("----------------------------");
							 
							 System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
							 
							 NodeList pList = doc.getElementsByTagName("ProyectoM");
							 
							 
							 /*******************
							  * CREATE DIRECTORY
							  *******************/
							 
							 ProyectoM Proyecto = new ProyectoM();
							 create_dir(directorio.getText(), pList, Proyecto);
							 
							 System.out.println("Proyecto con nombre "+Proyecto.getNombre());
							 
							 /************
							  * READXML
							  ************/
							 
							 readxml(doc, Proyecto);
							 
							 
								 
							 System.out.println("END");
							 System.exit(0); //CERRAR LUPITA
						 }				 
						 		
					} catch(Exception ex){}
				} //end else
			}//end button compilar

			private Object Atributos(String string, String string2) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnCompilar.setBounds(335, 227, 89, 23);
		frmCMode.getContentPane().add(btnCompilar);	
		
		JLabel lblDescripcion = new JLabel("<html><p>Esta herramienta permite compilar un modelo conceptual de Integranova en formato XML a lenguaje PHP.</p></html>");
		lblDescripcion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcion.setBounds(10, 43, 424, 101);
		frmCMode.getContentPane().add(lblDescripcion);
		
	}//end void initialize
	

	/* ****************
	 * Read
	 * ****************/
	public static void readxml(Document doc, ProyectoM Proyecto){
		
		 System.out.println("----------------------------");
		 System.out.println("Lectura XML");
		 System.out.println("----------------------------");
		 
		 NodeList nList = doc.getElementsByTagName("ClaseM");
		 NodeList aList = doc.getElementsByTagName("ProyectoM");		 						 
		 
		 System.out.println("----------------------------");
		 
		 /*************
		  * CONTADORES
		  *************/
		 int idevaluacion = 0;
		 int idservicio = 0;
		 int idargumento = 0;
		 int idatributo = 0;
		 int idppresentacion = 0;
		 int idelemagrup = 0;
		 int idvista = 0;
		 int idConjVisualM = 0;
		 int idElemConjVisual = 0;
		 int idnodo = 0;
		 int idnodo2 = 0;
		 int idnodo3 = 0;
		 int id_derivaciones = 0;
		 int id_interfaz = 0;
		 
		 boolean EvaluacionM = false;
		 boolean AtributoM = false;
		 boolean ServicioM = false;
		 boolean ArgumentoM = false;
		 boolean ConjVisualM = false;
		 boolean ElemConjVisual = false;
		 boolean PPresentacion = false;
		 boolean derivaciones = false;
		 boolean interfaz = false;
		 		
		 String PPresentacionId = "";
		 
		 /*********
		  * Level1
		  *********/
		 //DEBUG BUFFER LIMIT CONSOLE
		 
		 /************
		  * PROYECTOM
		  ************/
		 
		 for(int a1=0; a1 < aList.getLength(); a1++){
			 Node aNode = aList.item(a1);
			 System.out.println("\nCurrent Element :" + aNode.getNodeName());
			 
			 if (aNode.getNodeType() == Node.ELEMENT_NODE) {
				 
				 for(int a2=0; a2 < aNode.getChildNodes().getLength(); a2++){					 
					 Node a2Node = aNode.getChildNodes().item(a2);
					 if(a2Node.getNodeType() == Node.ELEMENT_NODE){						 
						System.out.println("----------------------------");										 
					 	System.out.println("\nCurrent Child Name: " + a2Node.getNodeName() );
					 	
					 	if (a2Node.getNodeType() == Node.ELEMENT_NODE) {
							 
							 for(int a3=0; a3 < a2Node.getChildNodes().getLength(); a3++){					 
								 Node a3Node = a2Node.getChildNodes().item(a3);
								 
								 if(a3Node.getNodeType() == Node.ELEMENT_NODE){
									Element a3Element = (Element) a2Node.getChildNodes().item(a3);
									System.out.println("----------------------------");										 
								 	System.out.println("\nCurrent Child-Child Name: " + a3Node.getNodeName() );
								 	
								 	if(a3Node.getNodeName().equals("AgregacionM")){
								 		AgregacionM(a3Element, Proyecto);
								 	}
								 	
								 	if(a3Node.getNodeName().equals("VistaM")){
								 		VistaM(a3Element, Proyecto);
								 	}
								 	
								 									 	
								 	if (a3Node.getNodeType() == Node.ELEMENT_NODE && !a3Node.getNodeName().equals("ClaseM")) {
										 
										 for(int a4=0; a4 < a3Node.getChildNodes().getLength(); a4++){					 
											 Node a4Node = a3Node.getChildNodes().item(a4);
											 
											 if(a4Node.getNodeType() == Node.ELEMENT_NODE){
												Element a4Element = (Element) a3Node.getChildNodes().item(a4);
												System.out.println("----------------------------");										 
											 	System.out.println("\nCurrent Child-Child-Child Name: " + a4Node.getNodeName() );
											 	
											 	if (a4Node.getNodeType() == Node.ELEMENT_NODE) {
													 
													 for(int a5=0; a5 < a4Node.getChildNodes().getLength(); a5++){					 
														 Node a5Node = a4Node.getChildNodes().item(a5);
														 
														 if(a5Node.getNodeType() == Node.ELEMENT_NODE){
															Element a5Element = (Element) a4Node.getChildNodes().item(a5);
															System.out.println("----------------------------");										 
														 	System.out.println("\nCurrent Child-Child-Child-Child Name: " + a5Node.getNodeName() );
														 	
														 	if(a5Node.getNodeName().equals("NodoJerarquiaM")){
														 		idvista = Proyecto.Vista.size()-1;
														 		if(idvista < 0)
														 			idvista = 0;
														 		NodoJerarquiaM(a5Element, ((VistaM) Proyecto.getVistaM(idvista)));
														 	}
														 	
														 	if(a5Node.getNodeName().equals("Ref.ClaseM") && a4Node.getNodeName().equals("ListaDe.Ref.Rol.ClaseActora")){
														 		ClaseActora(a5Element, Proyecto);
														 	}
														 		
														 	
														 	if (a5Node.getNodeType() == Node.ELEMENT_NODE) {
																 
																 for(int a6=0; a6 < a5Node.getChildNodes().getLength(); a6++){					 
																	 Node a6Node = a5Node.getChildNodes().item(a6);
																	 
																	 if(a6Node.getNodeType() == Node.ELEMENT_NODE){
																		Element a6Element = (Element) a5Node.getChildNodes().item(a6);
																		System.out.println("----------------------------");										 
																	 	System.out.println("\nCurrent Child-Child-Child-Child-Child Name: " + a6Node.getNodeName() );
																	 	
																	 	if(a6Node.getNodeName().equals("Alias")){
																	 		idnodo = ((VistaM) Proyecto.getVistaM(idvista)).NodoJerarquia.size()-1;
																	 		if(idnodo < 0)
																	 			idnodo = 0;
																	 		Alias(a6Element, a5Element, ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)).Alias);
																	 	}
																	 	
																	 	
																	 	if (a6Node.getNodeType() == Node.ELEMENT_NODE) {
																			 
																			 for(int a7=0; a7 < a6Node.getChildNodes().getLength(); a7++){					 
																				 Node a7Node = a6Node.getChildNodes().item(a7);
																				 
																				 if(a7Node.getNodeType() == Node.ELEMENT_NODE){
																					Element a7Element = (Element) a6Node.getChildNodes().item(a7);
																					System.out.println("----------------------------");										 
																				 	System.out.println("\nCurrent Child-Child-Child-Child-Child-Child Name: " + a7Node.getNodeName() );
																				 	
																				 	if(a7Node.getNodeName().equals("NodoJerarquiaM")){																				 		
																				 		NodoJerarquiaN(a7Element, ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)) );
																				 	}
																				 	
																				 	
																				 	if (a7Node.getNodeType() == Node.ELEMENT_NODE) {
																						 
																						 for(int a8=0; a8 < a7Node.getChildNodes().getLength(); a8++){					 
																							 Node a8Node = a7Node.getChildNodes().item(a8);
																							 
																							 if(a8Node.getNodeType() == Node.ELEMENT_NODE){
																								Element a8Element = (Element) a7Node.getChildNodes().item(a8);
																								System.out.println("----------------------------");										 
																							 	System.out.println("\nCurrent Child-Child-Child-Child-Child-Child-Child Name: " + a8Node.getNodeName() );
																							 	
																							 	if(a8Node.getNodeName().equals("Alias")){
																							 		idnodo2= ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)).NodoJerarquia.size()-1;
																							 		if(idnodo2 < 0)
																							 			idnodo2 = 0;
																							 		//System.out.println("DEBUG " + idvista + " " + idnodo + " " +idnodo2 + " " + idnodo3);
																							 		Alias(a8Element, a7Element, ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)).getNodoJerarquia(idnodo2)).Alias );																							 		
																							 	}
																							 	
																							 	
																							 	
																							 	if (a8Node.getNodeType() == Node.ELEMENT_NODE) {
																									 
																									 for(int a9=0; a9 < a8Node.getChildNodes().getLength(); a9++){					 
																										 Node a9Node = a8Node.getChildNodes().item(a9);
																										 
																										 if(a9Node.getNodeType() == Node.ELEMENT_NODE){
																											Element a9Element = (Element) a8Node.getChildNodes().item(a9);
																											System.out.println("----------------------------");										 
																										 	System.out.println("\nCurrent Child-Child-Child-Child-Child-Child-Child Name: " + a9Node.getNodeName() );
																										 	
																										 	if(a9Node.getNodeName().equals("NodoJerarquiaM")){
																										 		idnodo2= ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)).NodoJerarquia.size()-1;
																										 		if(idnodo2 < 0)
																										 			idnodo2 = 0;
																										 		System.out.println("DEBUG " + idvista + " " + idnodo + " " +idnodo2 + " " + idnodo3);
																										 		NodoJerarquiaN(a7Element, ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)).getNodoJerarquia(idnodo2))) );
																										 	}
																										 	
																										 	if (a9Node.getNodeType() == Node.ELEMENT_NODE) {
																												 
																												 for(int a10=0; a10 < a9Node.getChildNodes().getLength(); a10++){					 
																													 Node a10Node = a9Node.getChildNodes().item(a10);
																													 
																													 if(a10Node.getNodeType() == Node.ELEMENT_NODE){
																														Element a10Element = (Element) a9Node.getChildNodes().item(a10);
																														System.out.println("----------------------------");										 
																													 	System.out.println("\nCurrent Child-Child-Child-Child-Child-Child-Child Name: " + a10Node.getNodeName() );
																													 	
																													 	if(a10Node.getNodeName().equals("Alias")){	
																													 		idnodo3= ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)).getNodoJerarquia(idnodo2)).NodoJerarquia.size()-1;
																													 		if(idnodo3 < 0)
																													 			idnodo3 = 0;
																													 		System.out.println("DEBUG " + idvista + " " + idnodo + " " +idnodo2 + " " + idnodo3);
																													 		Alias(a10Element, a9Element, ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(idvista)).getNodoJerarquia(idnodo)).getNodoJerarquia(idnodo2)).getNodoJerarquia(idnodo3)).Alias );
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
										 }
								 	}
								 	
								 }
							 }
						 }			 

					 	
					 }
				 }
			 }			 
		 }
		 
		 
		/***********
		 * CLASEM
		 ***********/
		
		 for (int temp = 0; temp < nList.getLength() ; temp++){
			 Node nNode = nList.item(temp);							 
			 
			 System.out.println("\nCurrent Element :" + nNode.getNodeName());
			 
			 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				 
				 Element eElement = (Element) nNode;
				 
				 if(nNode.getNodeName().equals("ClaseM")){
					 ClaseM(eElement, Proyecto);									 
			 	 }
				 
				 /*********
				  * Level2
				  *********/								 
				 for(int tmp=0; tmp < nNode.getChildNodes().getLength(); tmp++){
					 if(nNode.getChildNodes().item(tmp).getNodeType() == Node.ELEMENT_NODE){
						Element nnElement = (Element) nNode.getChildNodes().item(tmp);
						 
						System.out.println("----------------------------");										 
					 	System.out.println("\nCurrent Child Name: " + nNode.getChildNodes().item(tmp).getNodeName() );
					 	
					 	if(nNode.getChildNodes().item(tmp).getNodeName().equals("Alias")){
					 		Alias(nnElement, eElement, ((ClaseM) Proyecto.getClaseM(temp)).Alias);					 		
				        }
					 	
					 	if(nNode.getChildNodes().item(tmp).getNodeName().equals("Observacions")){
					 		System.out.println("----------------------------");
					 		System.out.println("Observacions");
					 		System.out.println("----------------------------\n");
					 									 		
				            System.out.println(eElement.getElementsByTagName("Observacions").item(0).getTextContent());
				            ((ClaseM) Proyecto.getClaseM(temp)).setObservacions(Observacions(eElement));
				        }
					 	
					 	
					 	if(nNode.getChildNodes().getLength()>0){
					 		Node cNode = nNode.getChildNodes().item(tmp);
					 	
					 		/*********
							  * Level3
							  *********/
					 		for(int i=0; i < cNode.getChildNodes().getLength(); i++){
					 			if(cNode.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE){									 				
					 				Element cElement = (Element) cNode.getChildNodes().item(i);
					 				System.out.println("----------------------------");
					 				System.out.println("Current Child-Child Name: " + cNode.getChildNodes().item(i).getNodeName() + " Nombre: " + cElement.getAttribute("Nombre"));
					 				
					 				/*ATRIBUTOS*/
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("AtributoM")){
					 					 AtributoM = true;
					 					 AtributoM(cElement, ((ClaseM) Proyecto.getClaseM(temp)));
					 				}
					 				
					 				/* Derivaciones*/
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("DerivacionM")){
					 					 DerivacionM(cElement, ((ClaseM) Proyecto.getClaseM(temp)));
					 					 derivaciones = true;
					 				}
					 				
					 				/*FIdentificacionM*/
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("FIdentificacionM")){
					 					 FIdentificacionM(cElement);
					 				}
					 				
					 				/*EvaluacionM*/
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("EvaluacionM")){
					 					 EvaluacionM(cElement, ((ClaseM) Proyecto.getClaseM(temp)));
					 					 idevaluacion = ((ClaseM) Proyecto.getClaseM(temp)).Evaluaciones.size()-1;
					 					 EvaluacionM = true;
					 				}
					 				
					 				/*ServicioM*/
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("ServicioM")){
					 					 ServicioM = true;
					 					 ServicioM(cElement, ((ClaseM) Proyecto.getClaseM(temp)));
					 				}
					 				
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("ConjVisualM")){
								 		ConjVisualM = true;
					 					ConjVisualM(cElement, ((ClaseM) Proyecto.getClaseM(temp)));
								 	}
					 				
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("PPresentacionM")){
								 		PPresentacion = true;
					 					PPresentacionC(cElement, ((ClaseM) Proyecto.getClaseM(temp)));					 					
								 	}
					 				
					 				
					 				if(cNode.getChildNodes().item(i).getNodeName().equals("InterfazM")){
					 					((ClaseM) Proyecto.getClaseM(temp)).setAgent("1");	
					 					
					 					((ClaseM) Proyecto.getClaseM(temp)).addInterfazM();
					 					
					 					interfaz=true;
					 					
					 					id_interfaz = ((ClaseM) Proyecto.getClaseM(temp)).Interfaz.size() - 1;
					 					if(id_interfaz<0)
					 						id_interfaz = 0;
								 	}
					 				
					 				if(cNode.getChildNodes().getLength()>0){
					 					Node oNode = cNode.getChildNodes().item(i);
					 					
					 					/*********
										  * Level4
										  *********/
					 					for(int x=0; x < oNode.getChildNodes().getLength(); x++){
					 						if(oNode.getChildNodes().item(x).getNodeType() == Node.ELEMENT_NODE){
						 							
					 							idatributo = ((ClaseM) Proyecto.getClaseM(temp)).Atributos.size()-1;
					 							
					 							Element oElement = (Element) oNode.getChildNodes().item(x);
						 						System.out.println("----------------------------");
						 						System.out.println("\nCurrent Child-Child-Child Name: " + oNode.getChildNodes().item(x).getNodeName());
					 						
						 						if(oNode.getChildNodes().item(x).getNodeName().equals("Observacions")){
						 							System.out.println("----------------------------");
											 		System.out.println("Observacions:");
											 									 		
										            System.out.println(cElement.getElementsByTagName("Observacions").item(0).getTextContent());
										            
										            if(EvaluacionM)
										            	((EvaluacionM) ((ClaseM) Proyecto.getClaseM(temp)).getEvaluacionM(idevaluacion)).setObservacions( Observacions(cElement) );
										            if(AtributoM)
										            	((AtributoM) ((ClaseM) Proyecto.getClaseM(temp)).getAtributoM(idatributo)).setObservacions(Observacions(cElement));
										            if(ServicioM)
										            	((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).setObservacions(Observacions(cElement));
										            if(ConjVisualM)
										            	((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).setObservacions(Observacions(cElement));
										            if(PPresentacion)
										            	((PPresentacionM) ((ClaseM) Proyecto.getClaseM(temp)).getPPresentacionM(idppresentacion)).setObservacions(Observacions(cElement));
										        }
						 						
						 						if(oNode.getChildNodes().item(x).getNodeName().equals("Alias")){
											 		//Alias(oElement, cElement);
						 							
						 							idservicio = ((ClaseM) Proyecto.getClaseM(temp)).Servicios.size()-1;
						 							idConjVisualM = ((ClaseM) Proyecto.getClaseM(temp)).ConjVisual.size()-1;
						 							idppresentacion = ((ClaseM) Proyecto.getClaseM(temp)).PPresentacion.size()-1;
						 							
						 							if(AtributoM)
						 								Alias(oElement, cElement, ((AtributoM) ((ClaseM) Proyecto.getClaseM(temp)).getAtributoM(idatributo)).Alias);
						 							if(ServicioM)
						 								Alias(oElement, cElement, ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).Alias);
						 							if(ConjVisualM)
						 								Alias(oElement, cElement, ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).Alias);
						 							if(PPresentacion)
						 								Alias(oElement, cElement,  ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(temp)).getPPresentacionM(idppresentacion)).Alias );
											 	}
						 						
						 						if(oNode.getChildNodes().item(x).getNodeName().equals("MsgAyuda")){
						 							if(AtributoM)
										            	((AtributoM) ((ClaseM) Proyecto.getClaseM(temp)).getAtributoM(idatributo)).setMsgAyuda(	MsgAyuda(cElement) );
						 							
											 	}
						 						
						 						if(oNode.getChildNodes().item(x).getNodeName().equals("Her.EventoM")){
											 		HerEventoM(oElement);
											 	}
						 						
						 						if(oNode.getChildNodes().item(x).getNodeName().equals("PPresentacionM")){
						 							idservicio = ((ClaseM) Proyecto.getClaseM(temp)).Servicios.size()-1;						 							
						 							PPresentacionM(oElement, ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)));
						 							idppresentacion = ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).PPresentacion.size()-1;
						 							PPresentacionId = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).getId();
						 							//PPresentacion = true;
											 	}
						 						
						 						
						 						
						 						if(oNode.getChildNodes().getLength()>0){
						 							Node pNode = oNode.getChildNodes().item(x);
						 							
						 							/*if(PPresentacion){
							 							for(int pp = 0; pp < ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).PPresentacion.size(); pp++){
							 								if( ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(pp)).getId().equals(PPresentacion) ){
							 									idppresentacion = pp;
							 									break;
							 								}
							 							}
							 							
						 							}*/
						 							
						 							/*********
													  * Level5
													  *********/
						 							for(int p=0; p<pNode.getChildNodes().getLength(); p++){
						 								if(pNode.getChildNodes().item(p).getNodeType() == Node.ELEMENT_NODE){
						 									Element pElement = (Element) pNode.getChildNodes().item(p);
						 									System.out.println("----------------------------");
						 									System.out.println("Current Child-Child-Child-Child Name: " + pNode.getChildNodes().item(p).getNodeName());
						 																	
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Alias")){
						 										//idppresentacion = ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).PPresentacion.size()-1;
						 										Alias(pElement, oElement, ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).Alias);
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Observacions")){
						 										((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).setObservacions(Observacions(oElement));
						 									}
						 															 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("MsgAyuda")){
						 										MsgAyuda(oElement);
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Ref.EvaluacionM")){
						 										//RefEvaluacionM(pElement);
						 										System.out.println("----------------------------");
						 										System.out.println("Ref.EvaluacionM:");
						 										System.out.println(RefEvaluacionM(pElement));
						 																 										
						 										if(AtributoM)
						 											((AtributoM) ((ClaseM) Proyecto.getClaseM(temp)).getAtributoM(idatributo)).addRefEvaluacion( RefEvaluacionM(pElement));
						 										
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Ref.AtributoFIM")){
						 										RefAtributoFIM(pElement, ((ClaseM) Proyecto.getClaseM(temp)));
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("FormulaM")){
						 										if(EvaluacionM)
						 											FormulaM(pElement, ((EvaluacionM) ((ClaseM) Proyecto.getClaseM(temp)).getEvaluacionM(idevaluacion)).Formula );
						 										if(derivaciones){
						 											id_derivaciones = ((ClaseM) Proyecto.getClaseM(temp)).Derivaciones.size()-1;
						 											FormulaM(pElement, ((DerivacionM) ((ClaseM) Proyecto.getClaseM(temp)).getDerivacionM(id_derivaciones)).Formula);
						 										}
						 										
						 										if(AtributoM){
						 											FormulaM(pElement, ((AtributoM) ((ClaseM) Proyecto.getClaseM(temp)).getAtributoM(idatributo)).FormValorDef.Formula);
						 										}
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Ref.EventoM")){
						 										RefEventoM(pElement);
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Ref.DerivacionM")){
						 										((AtributoM) ((ClaseM) Proyecto.getClaseM(temp)).getAtributoM(idatributo)).Derivaciones.add( RefDerivacionM(pElement) );
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("ArgumentoM")){
						 										ArgumentoM = true;
						 										idservicio = ((ClaseM) Proyecto.getClaseM(temp)).Servicios.size()-1;										 										
						 										ArgumentoM(pElement, ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)) );
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("ElemConjVisual")){
						 										ElemConjVisual = true;
						 										idConjVisualM = ((ClaseM) Proyecto.getClaseM(temp)).ConjVisual.size()-1;
						 										ElemConjVisual(pElement, ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)) );
						 									}
						 									
						 									
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Ref.ClaseM")){
						 										//RefAtributoFIM(pElement, ((ClaseM) Proyecto.getClaseM(temp)));
						 										RefClaseM(pElement, ((Interfaz) ((ClaseM) Proyecto.getClaseM(temp)).getInterfazM(id_interfaz)));
						 									}
						 									
						 									if(pNode.getChildNodes().item(p).getNodeName().equals("Ref.AtributoM")){
						 										//RefAtributoFIM(pElement, ((ClaseM) Proyecto.getClaseM(temp)));
						 										RefAtributoM(pElement, ((Interfaz) ((ClaseM) Proyecto.getClaseM(temp)).getInterfazM(id_interfaz)));
						 									}
						 									
						 									if(oNode.getChildNodes().getLength()>0){
									 							Node qNode = pNode.getChildNodes().item(p);
									 							
									 							for(int q=0; q<qNode.getChildNodes().getLength(); q++){
									 								if(qNode.getChildNodes().item(q).getNodeType() == Node.ELEMENT_NODE){
									 									Element qElement = (Element) qNode.getChildNodes().item(q);
									 									System.out.println("----------------------------");
									 									System.out.println("Current Child-Child-Child-Child-Child Name: " + qNode.getChildNodes().item(q).getNodeName());
									 								
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Alias")){
									 										//Alias(qElement, pElement);
									 										idargumento = ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).Argumento.size()-1;
									 										
									 										if(ArgumentoM)
									 											Alias(qElement, pElement, ((AliasM) ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getArgumentoM(idargumento)).Alias) );
									 										if(ElemConjVisual){
									 											idElemConjVisual = ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).ElemConjVisual.size()-1;
									 											Alias(qElement, pElement, ((AliasM) ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).getElemConjVisual(idElemConjVisual)).Alias) );									 											
									 										}
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Observacions")){
									 										//Observacions(pElement);
									 										if(ArgumentoM)
									 											((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getArgumentoM(idargumento)).setObservacions(Observacions(pElement));
									 										if(ElemConjVisual)
									 											((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).getElemConjVisual(idElemConjVisual)).setObservacions(Observacions(pElement));
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("MsgAyuda")){
									 										MsgAyuda(pElement);
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Ref.EvaluacionM")){
										 									System.out.println("----------------------------");
									 										System.out.println("Ref.EvaluacionM:");
									 										System.out.println(RefEvaluacionM(qElement));
									 																 										
									 										if(ServicioM)
									 											((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).addRefEvaluacion( RefEvaluacionM(qElement));
									 										
									 																			 										
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("FormulaM")){
									 										if(ServicioM){
									 											FormulaM(pElement, ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).Formula);
									 										}
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("FormulaString")){
									 										//FormulaString(pElement);
									 										
									 										if(EvaluacionM)
									 											((EvaluacionM) ((ClaseM) Proyecto.getClaseM(temp)).getEvaluacionM(idevaluacion)).Formula.setFormulaString( FormulaString(pElement) );
									 										if(AtributoM){
									 											((AtributoM) ((ClaseM) Proyecto.getClaseM(temp)).getAtributoM(idatributo)).FormValorDef.Formula.setFormulaString( FormulaString(pElement));
									 										}
									 										if(derivaciones){
									 											((DerivacionM) ((ClaseM) Proyecto.getClaseM(temp)).getDerivacionM(id_derivaciones)).Formula.setFormulaString( FormulaString(pElement) );
									 											derivaciones = false;
									 										}
									 										else{
									 										
									 										int idEvaluacion = 0; 
									 										if ( ((ClaseM) Proyecto.getClaseM(temp)).Evaluaciones.size()-1 >= 0)
									 											idEvaluacion = ((ClaseM) Proyecto.getClaseM(temp)).Evaluaciones.size()-1;
									 										System.out.println(idEvaluacion + FormulaString(pElement));
									 										//((FormulaM) ((EvaluacionM) ((ClaseM) Proyecto.getClaseM(temp)).Evaluaciones.get(idEvaluacion)).Formula).setFormulaString(FormulaString(pElement));
									 										}
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Formula.Leaf")){
									 										FormulaLeaf(qElement);
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Her.ArgDatoValM")){
									 										if(ArgumentoM)
									 											HerArgDatoValM(qElement, ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getArgumentoM(idargumento)).HerArgDatoValM);
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Ref.AccionesOfertaM")){
									 										if(PPresentacion)
									 											((PPresentacionM) ((ClaseM) Proyecto.getClaseM(temp)).getPPresentacionM(idppresentacion)).setRefAccionesOfertaM(RefAccionesOfertaM(qElement));
									 										else
									 											((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).setRefAccionesOfertaM(RefAccionesOfertaM(qElement));									 										
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Ref.NavegaOfertadaM")){
									 										if(PPresentacion)
									 											((PPresentacionM) ((ClaseM) Proyecto.getClaseM(temp)).getPPresentacionM(idppresentacion)).setRefNavegaOfertadaM(RefNavegaOfertadaM(qElement));
									 										else
									 											((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).setRefNavegaOfertadaM(RefNavegaOfertadaM(qElement));									 										
									 									}
									 									
									 									if(qNode.getChildNodes().item(q).getNodeName().equals("Ref.ConjVisualM")){
									 										if(PPresentacion)
									 											((PPresentacionM) ((ClaseM) Proyecto.getClaseM(temp)).getPPresentacionM(idppresentacion)).setRefConjVisualM(RefConjVisualM(qElement));
									 										else
									 											((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).setRefConjVisualM(RefConjVisualM(qElement));									 										
									 									}
									 									
									 									if(qNode.getChildNodes().getLength()>0){
												 							Node rNode = qNode.getChildNodes().item(q);
												 							
												 							for(int r=0; r<rNode.getChildNodes().getLength(); r++){
												 								if(rNode.getChildNodes().item(r).getNodeType() == Node.ELEMENT_NODE){
												 									Element rElement = (Element) rNode.getChildNodes().item(r);
												 									System.out.println("----------------------------");
												 									System.out.println("Current Child-Child-Child-Child-Child-Child Name: " + rNode.getChildNodes().item(r).getNodeName());
												 									
												 									if(rNode.getChildNodes().item(r).getNodeName().equals("m_Valor")){
												 										System.out.println( mValor(qElement) );																 										
												 									}
												 									
												 									if(rNode.getChildNodes().item(r).getNodeName().equals("FormulaM")){
												 										if(ElemConjVisual)
												 											FormulaM(rElement, ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).getElemConjVisual(idElemConjVisual)).Formula );
												 										//mValor(qElement);																 										
												 									}
												 									
												 									if(rNode.getChildNodes().item(r).getNodeName().equals("FormulaString")){
												 										if(ServicioM){
												 											((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).Formula.setFormulaString( FormulaString(qElement) );
												 										}
												 									}
												 									
												 									if(rNode.getChildNodes().item(r).getNodeName().equals("ElemAgrupM")){												 										
												 										ElemAgrupM(rElement, ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).HerPPresentacion);
												 									}
												 									
												 									if(rNode.getChildNodes().item(r).getNodeName().equals("Ref.ServicioM")){
												 										if( ((ClaseM) Proyecto.getClaseM(temp)).getAgent().equals("1") ){
												 											id_interfaz = ((ClaseM) Proyecto.getClaseM(temp)).Interfaz.size()-1;
												 											
												 											RefServicioM(rElement, ((Interfaz) ((ClaseM) Proyecto.getClaseM(temp)).getInterfazM(id_interfaz)));
												 											
												 										}
												 									}
												 									
												 									if(rNode.getChildNodes().getLength()>0){
															 							Node sNode = rNode.getChildNodes().item(r);
															 							
															 							for(int s=0; s < sNode.getChildNodes().getLength(); s++){
															 								if(sNode.getChildNodes().item(s).getNodeType() == Node.ELEMENT_NODE){
															 									Element sElement = (Element) sNode.getChildNodes().item(s);
															 									System.out.println("----------------------------");
															 									System.out.println("Current Child-Child-Child-Child-Child-Child-Child Name: " + sNode.getChildNodes().item(s).getNodeName());
															 									
															 									if(sNode.getChildNodes().item(s).getNodeName().equals("Alias")){
															 										idelemagrup = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).HerPPresentacion.ElemAgrup.size()-1;
															 										Alias(sElement, rElement, ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).HerPPresentacion.getElemAgrup(idelemagrup)).Alias);
															 									}
															 									
															 									if(sNode.getChildNodes().item(s).getNodeName().equals("Observacions")){															 										
															 										//((ElemAgrupM) ((HerPPresentacionM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).HerPPresentacion).getElemAgrup(idelemagrup)).setObservacions(Observacions(rElement));
															 										Observacions(rElement);
															 									}
															 									
															 									if(sNode.getChildNodes().item(s).getNodeName().equals("MsgAyuda")){
															 										MsgAyuda(rElement);
															 									}
															 									
															 									if(sNode.getChildNodes().item(s).getNodeName().equals("FormulaString")){
															 										if(ElemConjVisual)
															 											((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).getElemConjVisual(idElemConjVisual)).Formula.setFormulaString(FormulaString(rElement));
															 										System.out.println(FormulaString(rElement));
															 									}
															 									
															 									if(sNode.getChildNodes().item(s).getNodeName().equals("Formula.Leaf")){
															 										if(ElemConjVisual)
															 											FormulaLeaf(sElement, ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(temp)).getConjVisualM(idConjVisualM)).getElemConjVisual(idElemConjVisual)).Formula );															 										
															 									}
															 									
															 									if(sNode.getChildNodes().item(s).getNodeName().equals("Ref.ClaseM")){															 										
															 										RefClaseM(sElement, ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getArgumentoM(idargumento)));
															 										//System.out.println("SIZE ARGUMENTO: " + idargumento + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getArgumentoM(idargumento)).getNombre());															 										
															 									}
															 									
															 									if(sNode.getChildNodes().item(s).getNodeName().equals("Ref.PPPobClaseM")){															 										
															 										((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getArgumentoM(idargumento)).HerArgObjValM.setRefPPPobClaseM( RefPPPobClaseM(sElement) );
															 										//System.out.println("SIZE ARGUMENTO: " + idargumento + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getArgumentoM(idargumento)).getNombre());															 										
															 									}
															 									
															 									
															 									if(sNode.getChildNodes().getLength()>0){
																		 							Node tNode = sNode.getChildNodes().item(s);
																		 							
																		 							for(int t=0; t < tNode.getChildNodes().getLength(); t++){
																		 								if(tNode.getChildNodes().item(t).getNodeType() == Node.ELEMENT_NODE){
																		 									Element tElement = (Element) tNode.getChildNodes().item(t);
																		 									System.out.println("----------------------------");
																		 									System.out.println("Current Child-Child-Child-Child-Child-Child-Child-Child Name: " + tNode.getChildNodes().item(t).getNodeName());
																		 								
																		 									if(tNode.getChildNodes().item(t).getNodeName().equals("Ref.ArgumentoM")){
																		 										RefArgumentoM(tElement, ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(temp)).getServicioM(idservicio)).getPPresentacionM(idppresentacion)).HerPPresentacion.getElemAgrup(idelemagrup)).RefRolArgumento);
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
									 							}	ArgumentoM = false; ElemConjVisual = false;									 									
						 									}
						 								}
						 							}
						 						}									 															 						
					 						}
					 					}
					 				} AtributoM = false; ServicioM = false; ConjVisualM = false; PPresentacion = false;
					 			} EvaluacionM = false;
					 		}
					 	}									 
					 }
				 }			 		
		 	}
			
		 } //End read
	

		/************************
		 * LISTAR PPRESENTACIONM	 		
		 ************************/
		 
		 /*System.out.println("\nLISTAR PPRESENTACIONM");
		 
		 for(int x1=0; x1< Proyecto.Clase.size(); x1++){
			 System.out.println("\nClase: " + ((ClaseM) Proyecto.getClaseM(x1)).getNombre() );
			 for(int x2=0; x2 < ((ClaseM) Proyecto.getClaseM(x1)).Servicios.size(); x2++ ){
				 System.out.println("\tServicio: " + ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre() );
				 System.out.println("\tAlias: " + ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Alias.getAlias().replace(" ", "_") );
				 for(int x3=0; x3 < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).PPresentacion.size(); x3++){
					 System.out.println("\t\t" +  ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId() );
					 System.out.println("\t\t" +  ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getNombre() );
					 System.out.println("\t\t" +  ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias() );
					 
					 for(int x4=0; x4 < ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.ElemAgrup.size(); x4++ ){
					 	System.out.println( "\t\t\tId: " + ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).getId() );
					 	System.out.println( "\t\t\tAlias: " + ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getAlias() );
					 	System.out.println( "\t\t\tRefRolArgumento: " + ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).RefRolArgumento.getRefRolArgumento() );
					 }
					 
				 }			 
			 }
		 }
		 */
		 
		 /**************
		  * ConjVisual
		  **************/
		 
		 for(int x1=0; x1 < Proyecto.Clase.size(); x1++){
			 for(int x2=0; x2 < ((ClaseM) Proyecto.getClaseM(x1)).ConjVisual.size(); x2++){
				 System.out.println("ConjVisual: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getId());
				 System.out.println("ConjVisual: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getNombre());
				 
				 for(int x3=0; x3 < ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).ElemConjVisual.size(); x3++){
					 System.out.println("\tElemConjVisual: " + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).getId() );
					 System.out.println("\t\tAlias: " + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Alias.getAlias() );
					 System.out.println("\t\tFormulaString: " + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString() );
				 }
			 }
		 }
		 
		 
		 /**************
		  * Scripts
		  **************/
		 
		 System.out.println("----------------------------");
		 System.out.println("Scripts");
		 
		 ForeignKeys foreignkeys = new ForeignKeys(Proyecto);
		 
		 DropForeignKeys dropforeignkey = new DropForeignKeys(Proyecto);
		 
		 Createtable createtable = new Createtable(Proyecto);
		 
		 Droptable droptable = new Droptable(Proyecto);
		 
		 PrimaryKeys primarykeys = new PrimaryKeys(Proyecto);
		 
		 DropPrimaryKeys dropprimarykeys = new DropPrimaryKeys(Proyecto);
		 
		 Indexes indexes = new Indexes(Proyecto);
		 
		 DropIndexes dropindexes = new DropIndexes(Proyecto);
		 
		 CreateAgents createagents = new CreateAgents(Proyecto);
		 
		 DeleteAgents deleteagents = new DeleteAgents(Proyecto);
		 
		 System.out.println("----------------------------");
		 
		 
		 /**************
		  * Mapping
		  **************/
		 
		 System.out.println("----------------------------");
		 System.out.println("Mapping");
		 Mapping mapping = new Mapping(Proyecto);
		 System.out.println("----------------------------");
		 
		 
		 
		 /**************
		  * website
		  **************/
		 
		 System.out.println("----------------------------");
		 System.out.println("website");
		 
		 Menu menu = new Menu(Proyecto);
		 
		 Contact contact = new Contact(Proyecto);
		 
		 Vistas vistas = new Vistas(Proyecto);		 
		 
		 System.out.println("----------------------------");
		 
		
	}	
	
	
	/* ****************
	 * METODOS
	 * ****************
	 */
	//Crear directorio del proyecto
	public static void create_dir(String newfolder, NodeList pList, ProyectoM Proyecto){
		
		System.out.println("----------------------------");
		System.out.println("Creando directorio");
		System.out.println("----------------------------\n");
		
		Node pNode = pList.item(0);
		Element pElement = (Element) pNode;
		
		String _Id = pElement.getAttribute("_Id");
		String Nombre = pElement.getAttribute("Nombre");
		
		Proyecto.set(_Id, Nombre);
		
		newfolder = newfolder.replace("\\", "\\\\")+"\\"+Proyecto.getNombre()+"\\";

		Proyecto.setDirectorio(newfolder);
		
		File dir = new File(Proyecto.getDirectorio());				 
		 if (!dir.exists()) {					    
			    boolean result = false;

			    try{
			        dir.mkdir();
			        result = true;
			        System.out.println("El directorio "+Proyecto.getNombre()+" ha sido creado.");
			     } catch(SecurityException se){
			        //handle it
			    	 
			     }
		 } else{
			 		System.out.println("El directorio "+Proyecto.getNombre()+" ya existe.");
		 }
		 
		 
		 copy_files(Proyecto);
		 
		 
	}//end void create_dir
	
	public static void copy_files(ProyectoM Proyecto){
		//listar archivos
		String src;
		Path current = Paths.get("");
		src = current.toAbsolutePath().toString()+"\\Project\\";
		
		File f = new File(src);
		
		if (f.exists()){ 
			File[] ficheros = f.listFiles(); 
			for (int x=0;x<ficheros.length;x++){
			  //System.out.println(ficheros[x].getName());
			  
			  File srcFolder = new File(src+ficheros[x].getName());
			  File destFolder = new File(Proyecto.getDirectorio()+ficheros[x].getName());
			  
			  if(!srcFolder.exists()){
				  
		           System.out.println("Directorio no existe.");
		           //just exit
		           System.exit(0);
		 
		        }else{
		 
		           try{
		        	copyFolder(srcFolder,destFolder);
		           }catch(IOException e){
		        	e.printStackTrace();
		        	//error, just exit
		                System.exit(0);
		           }
		        }
			  
			  	
			} 
		}else { 
			System.out.println("El directorio no existe"); 
		}
	}//end copy_files
	
	
	public static void copyFolder(File src, File dest)
	    	throws IOException{
					
	    	if(src.isDirectory()){
	 
	    		//if directory not exists, create it
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		   System.out.println("Copiando directorio  " 
	                              + src + "  hacia " + dest);
	    		}
	 
	    		//list all the directory contents
	    		String files[] = src.list();
	 
	    		for (String file : files) {
	    		   //construct the src and dest file structure
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   //recursive copy
	    		   copyFolder(srcFile,destFile);
	    		}
	 
	    	}else{
	    		//if file, then copy it
	    		//Use bytes stream to support all file types
	    		InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest); 
	 
	    	        byte[] buffer = new byte[1024];
	 
	    	        int length;
	    	        //copy the file content in bytes 
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }
	 
	    	        in.close();
	    	        out.close();
	    	        System.out.println("Copiando archivo " + src + " hacia " + dest);
	    	}
	 } //end copyfolder	
	
	/*******************
	 ClaseM
	 *******************/
	public static void ClaseM(Element eElement, ProyectoM Proyecto){
		 boolean EsTemporal, EsLegada, DTEPorDefecto, EsAgenteAnonimo;
		 EsTemporal = EsLegada = DTEPorDefecto = EsAgenteAnonimo = false;
		 
		 System.out.println("----------------------------");
		 System.out.println("ClaseM:");
		 //System.out.println("----------------------------\n");
		 									 
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 System.out.println(eElement.getAttribute("NombreEnBD"));
		 System.out.println(eElement.getAttribute("EsTemporal"));
		 System.out.println(eElement.getAttribute("EsLegada"));
		 System.out.println(eElement.getAttribute("DTEPorDefecto"));
		 System.out.println(eElement.getAttribute("EsAgenteAnonimo"));
		 
		 if (eElement.getAttribute("EsTemporal").equals("1"))
			 EsTemporal = true;
		 		 
		 if(eElement.getAttribute("EsLegada").equals("1"))
			 EsLegada = true;
		  
		 if(eElement.getAttribute("DTEPorDefecto").equals("1"))
			 DTEPorDefecto = true;
		  
		 if(eElement.getAttribute("EsAgenteAnonimo").equals("1"))
			 EsAgenteAnonimo = true;
		 
		 Proyecto.addClase(eElement.getAttribute("_Id"), eElement.getAttribute("Nombre"), eElement.getAttribute("NombreEnBD"), EsTemporal, EsLegada, DTEPorDefecto, EsAgenteAnonimo);
	}
	
	/*******************
	 Alias
	 *******************/
	 public static void Alias(Element eElement, Element e2Element, AliasM Alias){
		 System.out.println("----------------------------");
	 	 System.out.println("Alias:");
	 	 //System.out.println("----------------------------\n");
	 		
	 	 System.out.println(eElement.getAttribute("_RefInferencia"));
	 	 System.out.println(eElement.getAttribute("AliasInf"));
	 	 System.out.println(e2Element.getElementsByTagName("Alias").item(0).getTextContent());	 	 
	 	 Alias.setRefInferencia(eElement.getAttribute("_RefInferencia")); 
	 	 Alias.setAlias(e2Element.getElementsByTagName("Alias").item(0).getTextContent());
	 }
	 
	  
	 /******************
	  MsgAyuda
	  ******************/
	 public static String MsgAyuda(Element eElement){
		 /*System.out.println("----------------------------");
	 	 System.out.println("MsgAyuda");
	 	 System.out.println("----------------------------\n");
	 	 
	 	System.out.println(eElement.getElementsByTagName("MsgAyuda").item(0).getTextContent());*/
		return eElement.getElementsByTagName("MsgAyuda").item(0).getTextContent();
	 }
	 
	 /******************
	  Observacions
	  ******************/
	 public static String Observacions(Element eElement){
		 /*System.out.println("----------------------------");
	 	 System.out.println("Observacions");
	 	 System.out.println("----------------------------\n");
	 							 		
         System.out.println(eElement.getElementsByTagName("Observacions").item(0).getTextContent());*/
         return eElement.getElementsByTagName("Observacions").item(0).getTextContent();
	 }
	 
	 /*****************
	  AtibutoM
	  *****************/
	 public static void AtributoM(Element eElement, ClaseM Clase){
		 boolean EsTemporal, AdmiteNulos, PedirAlCrear;
		 EsTemporal = AdmiteNulos = PedirAlCrear = false;
		 
		 System.out.println("----------------------------");
		 System.out.println("AtributoM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("IdAtributo"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 System.out.println(eElement.getAttribute("Tipo"));
		 System.out.println(eElement.getAttribute("TipoDato"));
		 System.out.println(eElement.getAttribute("Tamano"));
		 System.out.println(eElement.getAttribute("EsTemporal"));
		 System.out.println(eElement.getAttribute("AdmiteNulos"));
		 System.out.println(eElement.getAttribute("PedirAlCrear"));
		 
		 Clase.addAtributo(eElement.getAttribute("_Id"), Integer.parseInt(eElement.getAttribute("IdAtributo")), eElement.getAttribute("Nombre"), eElement.getAttribute("Tipo"), eElement.getAttribute("TipoDato"), eElement.getAttribute("Tamano"), EsTemporal, AdmiteNulos, PedirAlCrear);
	 }
	 
	 /*****************
	  Ref.EvaluacionM
	  *****************/
	 public static String RefEvaluacionM(Element eElement){
		 //System.out.println("----------------------------");
		 //System.out.println("Ref.EvaluacionM:");
		 //System.out.println("----------------------------\n");
			
		 //System.out.println(eElement.getAttribute("_Ref"));
		 return eElement.getAttribute("_Ref");
	 }
	 
	 /*****************
	  FIdentificacionM
	  *****************/
	 public static void FIdentificacionM(Element eElement){
		 System.out.println("----------------------------");
		 System.out.println("FIdentificacionM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
	 }
	 
	 /*****************
	  Ref.AtributoFIM
	  *****************/
	 public static void RefAtributoFIM(Element eElement, ClaseM Clase){
		 System.out.println("----------------------------");
		 System.out.println("Ref.AtributoFIM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Ref"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 System.out.println(eElement.getAttribute("TipoDato"));
		 System.out.println(eElement.getAttribute("PathDeRoles"));
		 
		 Clase.setRefAtributoFIM(eElement.getAttribute("_Ref"));
	 }
	 
	 /*****************
	  EvaluacionM
	  *****************/
	 public static void EvaluacionM(Element eElement, ClaseM Clase){
		 System.out.println("----------------------------");
		 System.out.println("EvaluacionM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("id_EvaluacionM"));
		 System.out.println(eElement.getAttribute("Categoria"));
		 System.out.println(eElement.getAttribute("TipoCardinalidad"));
		 
		 Clase.addEvaluacion(eElement.getAttribute("_Id"), eElement.getAttribute("id_EvaluacionM"), eElement.getAttribute("Categoria"), eElement.getAttribute("TipoCardinalidad"));
	 }

	 /*****************
	  FormulaM
	  *****************/
	 public static void FormulaM(Element eElement, FormulaM Formula){
		 System.out.println("----------------------------");
		 System.out.println("FormulaM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("id_Formula"));
		 System.out.println(eElement.getAttribute("EsValida"));
		 System.out.println(eElement.getAttribute("TipoFormula"));
		 
		 Formula.setFormula(eElement.getAttribute("_Id"), eElement.getAttribute("id_Formula"), eElement.getAttribute("EsValida"), eElement.getAttribute("TipoFormula"));
		 
	 }	 
	 
	 /*****************
	  FormulaString
	  *****************/
	 public static String FormulaString(Element eElement){
		 //System.out.println("----------------------------");
		 //System.out.println("FormulaString:");
		 //System.out.println("----------------------------\n");
			
		 return eElement.getElementsByTagName("FormulaString").item(0).getTextContent();
	 }
	 
	 /*****************
	  Formula.Leaf
	  *****************/
	 public static void FormulaLeaf(Element eElement){
		 System.out.println("----------------------------");
		 System.out.println("Formula.Leaf:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("m_TipoFormula"));
		 System.out.println(eElement.getAttribute("m_Clase"));
		 System.out.println(eElement.getAttribute("m_Tipo"));
		 System.out.println(eElement.getAttribute("m_Dominio"));
		 System.out.println(eElement.getAttribute("m_TipoArgum"));
		 System.out.println(eElement.getAttribute("m_Cte"));
		 System.out.println(eElement.getAttribute("m_ServicioParametro"));
		 System.out.println(eElement.getAttribute("m_ClaseParametroTG"));
		 System.out.println(eElement.getAttribute("m_ClaseFormula"));
	 }
	 
	 /*****************
	  m_Valor
	  *****************/
	 public static String mValor(Element eElement){
		 //System.out.println("----------------------------");
		 //System.out.println("m_Valor:");
		 //System.out.println("----------------------------\n");
			
		 //System.out.println(eElement.getElementsByTagName("m_Valor").item(0).getTextContent());
		 return eElement.getElementsByTagName("m_Valor").item(0).getTextContent();
	 }
	 
	 /*****************
	  Ref.EventoM
	  *****************/
	 public static void RefEventoM(Element eElement){
		 System.out.println("----------------------------");
		 System.out.println("Ref.EventoM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Ref"));		 
	 }
	 
	 /*****************
	  ServicioM
	  *****************/
	 public static void ServicioM(Element eElement, ClaseM Clase){
		 boolean UsoInterno = false;
		 
		 System.out.println("----------------------------");
		 System.out.println("ServicioM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("idServicio"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 System.out.println(eElement.getAttribute("Tipo"));
		 System.out.println(eElement.getAttribute("DTipoEvento"));
		 System.out.println(eElement.getAttribute("UsoInterno"));
		 
		 if(eElement.getAttribute("UsoInterno").equals("1"))
			 UsoInterno = true;
		 
		 Clase.addServicio(eElement.getAttribute("_Id"), eElement.getAttribute("Nombre"), eElement.getAttribute("Tipo"), eElement.getAttribute("DTipoEvento"), UsoInterno);
	 }
	 
	 /*****************
	  ArgumentoM
	  *****************/
	 public static void ArgumentoM(Element eElement, ServicioM Servicio){
		 System.out.println("----------------------------");
		 System.out.println("ArgumentoM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("IdArgumento"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 System.out.println(eElement.getAttribute("AdmiteNulos"));
		 System.out.println(eElement.getAttribute("TipoArgumento"));
		 System.out.println(eElement.getAttribute("TipoColeccion"));
		 System.out.println(eElement.getAttribute("EsMultivaluado"));
		 System.out.println(eElement.getAttribute("OrdenFormal"));
		 System.out.println(eElement.getAttribute("OrdenVisual"));
		 System.out.println(eElement.getAttribute("TipoImplicito"));
		 System.out.println(eElement.getAttribute("EsDeEntrada"));
		 System.out.println(eElement.getAttribute("SelecMultiple"));	
		 
		 Servicio.addArgumento(eElement.getAttribute("_Id"), eElement.getAttribute("Nombre"), eElement.getAttribute("AdmiteNulos"), eElement.getAttribute("TipoArgumento"), eElement.getAttribute("TipoColeccion"), eElement.getAttribute("EsMultivaluado"), eElement.getAttribute("OrdenFormal"), eElement.getAttribute("OrdenVisual"), eElement.getAttribute("TipoImplicito"), eElement.getAttribute("EsDeEntrada"), eElement.getAttribute("SelecMultiple"));
	 }
	 
	 /*****************
	  Her.ArgDatoValM
	  *****************/
	 public static void HerArgDatoValM(Element eElement, HerArgDatoValM HerArgDatoVal){
		 System.out.println("----------------------------");
		 System.out.println("Her.ArgDatoValM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("TipoDato"));
		 System.out.println(eElement.getAttribute("Tamanyo"));
		 
		 HerArgDatoVal.setTipoDato(eElement.getAttribute("TipoDato"));
		 HerArgDatoVal.setTamanyo(eElement.getAttribute("Tamanyo"));
	 }
		 
	 /*****************
	  Her.EventoM
	  *****************/
	 public static void HerEventoM(Element eElement){
		 System.out.println("----------------------------");
		 System.out.println("Her.EventoM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("TipoEvento"));
	 }
	 
	 /*****************
	  PPresentacionM
	  Servicio.PPresentacion
	  *****************/
	 public static void PPresentacionM(Element eElement, ServicioM Servicio){
		 System.out.println("----------------------------");
		 System.out.println("PPresentacionM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 //System.out.println(eElement.getAttribute("IdPresentacion"));
		 System.out.println(eElement.getAttribute("TipoPP"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 
		 Servicio.addPPresentacion(eElement.getAttribute("_Id"), eElement.getAttribute("Nombre"), eElement.getAttribute("TipoPP"));
	 }
	 
	 /*****************
	  PPresentacionM
	  ClaseM.PPresentacion
	  *****************/
	 public static void PPresentacionC(Element eElement, ClaseM Clase){
		 System.out.println("----------------------------");
		 System.out.println("PPresentacionM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 //System.out.println(eElement.getAttribute("IdPresentacion"));
		 System.out.println(eElement.getAttribute("TipoPP"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 
		 Clase.addPPresentacion(eElement.getAttribute("_Id"), eElement.getAttribute("Nombre"), eElement.getAttribute("TipoPP"));
	 }

	 /*****************
	  ElemAgrupM
	  *****************/
	 public static void ElemAgrupM(Element eElement, HerPPresentacionM HerPPresentacion){
		 System.out.println("----------------------------");
		 System.out.println("ElemAgrupM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 //System.out.println(eElement.getAttribute("id_PAgrupArg"));
		 //System.out.println(eElement.getAttribute("Orden"));
		 //System.out.println(eElement.getAttribute("EsGrupo"));
		 
		 HerPPresentacion.addElemAgrup(eElement.getAttribute("_Id"));
		 
	 }
	 
	 /*****************
	  Ref.ArgumentoM
	  *****************/
	 public static void RefArgumentoM(Element eElement, RefRolArgumentoM RefRolArgumento){
		 System.out.println("----------------------------");
		 System.out.println("Ref.ArgumentoM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Ref"));
		 
		 RefRolArgumento.setRefRolArgumento(eElement.getAttribute("_Ref"));
		 
	 }
	 
	 /*****************
	  Her.ArgObjValM
	  *****************/
	 public static void HerArgObjValM(Element eElement){
		 System.out.println("----------------------------");
		 System.out.println("Her.ArgObjValM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("EsTHIS"));
		 System.out.println(eElement.getAttribute("Inicializa"));
	 }
	 
	 	 
	 /*****************
	  Ref.ClaseM
	  *****************/
	 public static void RefClaseM(Element eElement, ArgumentoM Argumento){
		 System.out.println("----------------------------");
		 System.out.println("Ref.ClaseM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Ref"));
		 ((HerArgObjValM) Argumento.HerArgObjValM).setRefClaseM(eElement.getAttribute("_Ref"));
	 }
	 
	 /*****************
	  Ref.PPPobClaseM
	  *****************/
	 public static String RefPPPobClaseM(Element eElement){
		 System.out.println("----------------------------");
		 System.out.println("Ref.PPPobClaseM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Ref"));
		 
		 return eElement.getAttribute("_Ref");
	 }
	 
	 /*****************
	  AgregacionM
	  *****************/
	 public static void AgregacionM(Element eElement, ProyectoM Proyecto){
		 System.out.println("----------------------------");
		 System.out.println("AgregacionM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("RolCompuesta"));
		 System.out.println(eElement.getAttribute("RolComponente"));
		 
		 Proyecto.addAgregacion(eElement.getAttribute("RolCompuesta"), eElement.getAttribute("RolComponente"), 
				 eElement.getAttribute("minCompuesta"), eElement.getAttribute("MaxCompuesta"), 
				 eElement.getAttribute("minComponente"), eElement.getAttribute("MaxComponente"),
				 eElement.getAttribute("EsDinamica"));
	 }
	 
	 /*****************
	  VistaM
	  *****************/
	 public static void VistaM(Element eElement, ProyectoM Proyecto){
		 System.out.println("----------------------------");
		 System.out.println("VistaM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("IdVista"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 
		 Proyecto.addVista(eElement.getAttribute("_Id"), eElement.getAttribute("IdVista"), eElement.getAttribute("Nombre"));
	 }
	 
	 /*****************
	  NodoJerarquiaM
	  Para Vista
	  *****************/
	 public static void NodoJerarquiaM(Element eElement, VistaM Vista){
		 System.out.println("----------------------------");
		 System.out.println("NodoJerarquiaM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("idNodoJerarquia"));
		 
		 Vista.addNodoJerarquia(eElement.getAttribute("_Id"), eElement.getAttribute("idNodoJerarquia"));
	 }
	 
	 /*****************
	  NodoJerarquiaM
	  Para NodoJerarquia
	  *****************/
	 public static void NodoJerarquiaN(Element eElement, NodoJerarquiaM NodoJerarquia){
		 System.out.println("----------------------------");
		 System.out.println("NodoJerarquiaM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("idNodoJerarquia"));
		 
		 NodoJerarquia.addNodoJerarquia(eElement.getAttribute("_Id"), eElement.getAttribute("idNodoJerarquia"));
	 }

	 
	 /*****************
	  ClaseActora
	  *****************/
	 public static void ClaseActora(Element eElement, ProyectoM Proyecto){
		 System.out.println("----------------------------");
		 System.out.println("ClaseActora:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Ref"));
		 
		 Proyecto.ClaseActora.add(eElement.getAttribute("_Ref"));		 
	 }


	 /*****************
	  ConjVisualM
	  *****************/
	 public static void ConjVisualM(Element eElement, ClaseM Clase){
		 System.out.println("----------------------------");
		 System.out.println("ConjVisualM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("id_ConjVisualM"));
		 System.out.println(eElement.getAttribute("Nombre"));
		 
		 Clase.addConjVisual(eElement.getAttribute("_Id"), eElement.getAttribute("id_ConjVisualM"), eElement.getAttribute("Nombre"));
		 
	 }


	 /*****************
	  ElemConjVisual
	  *****************/
	 public static void ElemConjVisual(Element eElement, ConjVisualM ConjVisual){
		 System.out.println("----------------------------");
		 System.out.println("ElemConjVisual:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("id_Elementos"));
		 System.out.println(eElement.getAttribute("Orden"));
		 
		 ConjVisual.addElemConjVisual(eElement.getAttribute("_Id"), eElement.getAttribute("id_Elementos"), eElement.getAttribute("Orden"));
		 
	 }

	 /*****************
	  DerivacionM
	  *****************/
	 public static void DerivacionM(Element eElement, ClaseM Clase){
		 System.out.println("----------------------------");
		 System.out.println("DerivacionM:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("_Id"));
		 System.out.println(eElement.getAttribute("id_DerivacionM"));
		 
		 Clase.addDerivacionM(eElement.getAttribute("_Id"), eElement.getAttribute("id_DerivacionM")); 
		 
	 }
	 
	 /*****************
	  Formula.Leaf
	  *****************/
	 public static void FormulaLeaf(Element eElement, FormulaM Formula){
		 System.out.println("----------------------------");
		 System.out.println("Formula.Leaf:");
		 //System.out.println("----------------------------\n");
			
		 System.out.println(eElement.getAttribute("m_TipoFormula"));
		 System.out.println(eElement.getAttribute("m_Clase"));
		 System.out.println(eElement.getAttribute("m_Tipo"));
		 System.out.println(eElement.getAttribute("m_Dominio"));
		 System.out.println(eElement.getAttribute("m_TipoArgum"));
		 System.out.println(eElement.getAttribute("m_Cte"));
		 System.out.println(eElement.getAttribute("m_ServicioParametro"));
		 System.out.println(eElement.getAttribute("m_ClaseParametroTG"));
		 System.out.println(eElement.getAttribute("m_ClaseFormula"));
		 
		 Formula.setM_Clase(eElement.getAttribute("m_Clase"));
		 Formula.setM_ClaseFormula(eElement.getAttribute("m_ClaseFormula"));
		 
	 }
	 
	 /*****************
	  RefAccionesOfertaM
	  *****************/
	 public static String RefAccionesOfertaM(Element eElement){
		 return eElement.getAttribute("_Ref");
	 }
	 
	 /*****************
	  RefNavegaOfertadaM
	  *****************/
	 public static String RefNavegaOfertadaM(Element eElement){
		 return eElement.getAttribute("_Ref");
	 }	 
	 
	 /*****************
	  RefConjVisualM
	  *****************/
	 public static String RefConjVisualM(Element eElement){
		 return eElement.getAttribute("_Ref");
	 }
	 
	 /*****************
	  RefDerivacionM
	  *****************/
	 public static String RefDerivacionM(Element eElement){
		 System.out.println( eElement.getAttribute("_Ref"));
		 return eElement.getAttribute("_Ref");
	 }
	 
	 /*****************
	  Ref.ServicioM
	  *****************/
	 public static void RefServicioM(Element eElement, Interfaz Interfaz){
		 System.out.println("----------------------------");
		 System.out.println("Ref.ServicioM");
		 System.out.println( eElement.getAttribute("_Ref"));
		 Interfaz.addServicio(eElement.getAttribute("_Ref"));
	 }
	 
	 /*****************
	  RefClaseM
	  *****************/
	 public static void RefClaseM(Element eElement, Interfaz Interfaz){
		 System.out.println("----------------------------");
		 System.out.println("Ref.ClaseM");
		 System.out.println( eElement.getAttribute("_Ref"));
		 Interfaz.setId_clase(eElement.getAttribute("_Ref"));
	 }
	 
	 /*****************
	  RefAtributoM
	  *****************/
	 public static void RefAtributoM(Element eElement, Interfaz Interfaz){
		 System.out.println("----------------------------");
		 System.out.println("Ref.AtributoM");
		 System.out.println( eElement.getAttribute("_Ref"));
		 Interfaz.addatributos(eElement.getAttribute("_Ref"));
	 }
}
