package website;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Menu{
	
	public Menu(ProyectoM Proyecto){
		
		System.out.println("\nMenu.php\n");
		String php="";
		String publico="";
		String page = "";
		String Alias = "";
		String Dominio = "";
		
		php += "<?php \n\n ?>\n\n";
		php += "<div id='cssmenu'>\n\t<ul>\n\t\t<li><a href='index.php'><span>Inicio</span></a></li>\n";
		
		publico=php;
		
		for(int b=0; b< Proyecto.Vista.size(); b++){			
			
			for(int b2=0; b2< ((VistaM) Proyecto.getVistaM(b)).NodoJerarquia.size(); b2++){
				
				for(int b3=0; b3< ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2))).NodoJerarquia.size(); b3++){					
					Alias = ((AliasM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).Alias).getAlias();
					
					Dominio = Alias;
					
					php += "\n\t\t<li class='has-sub'><a href='"+ "#" + "'><span>" + Alias + "</span></a>\n";
					
					php += "\t\t\t<ul>\n";
					
					System.out.println(Dominio);
					
					//php += "\t\t\t\t<li class='last'><a href='"+ Alias.substring(0, 1) + Alias.substring(1, Alias.length()).toLowerCase() + ".php'><span>" + Alias + "</span></a></li>\n";
					
					for(int b4=0; b4< ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2))).getNodoJerarquia(b3)).NodoJerarquia.size(); b4++){
						Alias = ((AliasM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getNodoJerarquia(b4)).Alias).getAlias();
						
						if(!Dominio.equals(Alias))
							page = (Dominio + "_" + Alias).replace(" ", "_") + ".php";
						else
							page = Alias.replace(" ", "_") + ".php"; 
						
						
						System.out.println("\t" + page);
						
						php += "\t\t\t\t<li class='last'><a href='"+ page + "'><span>" + Alias + "</span></a></li>\n";
						
					}
					php += "\t\t\t</ul>\n\t\t</li>\n";
				}				
			}
		}

		php += "\t\t<li><a href='about.php'><span>Acerca de</span></a></li>\n";
		publico += "\t\t<li><a href='about.php'><span>Acerca de</span></a></li>\n";
		
		php += "\t\t<li class='last'><a href='contact.php'><span>Contacto</span></a></li>\n<?php if($_SESSION['log']) echo \"<li class='last'><a href='logout.php'><span>Salir</span></a></li>\";?>\t</ul>\n</div>\n\n";
		publico += "\t\t<li class='last'><a href='contact.php'><span>Contacto</span></a></li>\n<?php if($_SESSION['log']) echo \"<li class='last'><a href='logout.php'><span>Salir</span></a></li>\";?>\t</ul>\n</div>\n\n";
		
		for(int i=0; i<Proyecto.Clase.size(); i++){
			
			String menu = "<div id='cssmenu'>\n\t<ul>\n\t\t<li><a href='index.php'><span>Inicio</span></a></li>\n";
			
			if( ((ClaseM) Proyecto.getClaseM(i)).getAgent().equals("1") ){
				
				//System.out.println("Clase: " + ((ClaseM) Proyecto.getClaseM(i)).getNombre());
				
				
				
				for(int j=0; j<((ClaseM) Proyecto.getClaseM(i)).Interfaz.size(); j++){
					
					menu += getClase(((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).getId_clase(), Proyecto);
					
					
					for(int k=0; k < ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).id_servicio.size(); k++ ){
						//System.out.println("\t\tServicio: " + ((String) ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).getServicio(k)) + " dominio: " + getClase(((String) ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).getId_clase()), Proyecto) + " nombre: " + getServicio(((String) ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).getServicio(k)), Proyecto));
						
						
						
						menu += getServicio(((String) ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).getServicio(k)), Proyecto);						
					}
					
					
					/*for(int l=0; l < ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).atributos.size(); l++ ){
						//System.out.println("\t\tAtributos: " + ((String) ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).getAtributos(l)) );
						System.out.println("\t\tAtributos: " + getAtributo(((String) ((Interfaz) ((ClaseM) Proyecto.getClaseM(i)).getInterfazM(j)).getAtributos(l)), Proyecto) );
					}*/
					
					menu += "\t\t\t</ul>\n\t\t</li>\n"; 
				}			
				
				
				menu += "\t\t<li><a href='about.php'><span>Acerca de</span></a></li>\n";
				
				menu += "\t\t<li class='last'><a href='contact.php'><span>Contacto</span></a></li>\n";
				menu += "<?php if($_SESSION['log']) echo \"<li class='last'><a href='logout.php'><span>Salir</span></a></li>\";?>";
				
				menu += "\t</ul>\n</div>\n\n";
				
				try {
					  File file = new File(Proyecto.getDirectorio()+"\\lib\\menu_" + ((ClaseM) Proyecto.getClaseM(i)).getNombre().replace(" ", "_") + ".php");
			          BufferedWriter output = new BufferedWriter(new FileWriter(file));
			          output.write(menu);
			          output.close();
			        } catch ( IOException e ) {
			           e.printStackTrace();
			        }
				
			}
		}
		
		
		
		//WRITE MENU.PHP
		try {
			  File file = new File(Proyecto.getDirectorio()+"\\lib\\menu.php");
			  File file1 = new File(Proyecto.getDirectorio()+"\\lib\\menu_publico.php");
			  File file2 = new File(Proyecto.getDirectorio()+"\\lib\\menu_administrador.php");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          BufferedWriter output1 = new BufferedWriter(new FileWriter(file1));
	          BufferedWriter output2 = new BufferedWriter(new FileWriter(file2));
	          output.write(php);
	          output1.write(publico);
	          output2.write(php);
	          output.close();
	          output1.close();
	          output2.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
	}
	
	public String getClase(String id, ProyectoM Proyecto){
		String nombre="";
		String menu="";
		
		for(int i=0; i<Proyecto.Clase.size(); i++){
			if( ((ClaseM) Proyecto.getClaseM(i)).getId().equals(id) ){
				nombre = ((ClaseM) Proyecto.getClaseM(i)).getNombre();
				
				menu += "\n\t\t<li class='has-sub'><a href='"+ "#" + "'><span>" + ((ClaseM) Proyecto.getClaseM(i)).Alias.getAlias() + "</span></a>\n";
				
				menu += "\t\t\t<ul>\n";
				
				return menu;
			}
		}
		
		return nombre;
	}
	
	public String getServicio(String id, ProyectoM Proyecto){
		String nombre="";
		String menu="";
		
		for(int i=0; i<Proyecto.Clase.size(); i++){
			for(int j=0; j< ((ClaseM) Proyecto.getClaseM(i)).Servicios.size(); j++ ){
				if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(j)).getId().equals(id)  ){
					nombre = ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(j)).getNombre();
					
					menu += "\t\t\t\t<li class='last'><a href='"+ ((ClaseM) Proyecto.getClaseM(i)).Alias.getAlias().replace(" ", "_") + "_" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(j)).Alias.getAlias().replace(" ", "_")  + ".php'><span>" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(i)).getServicioM(j)).Alias.getAlias() + "</span></a></li>\n";
					
					return menu;
				}
			}		
		}
		
		return nombre;
	}
	
	public String getAtributo(String id, ProyectoM Proyecto){
		String nombre="";
		
		for(int i=0; i<Proyecto.Clase.size(); i++){
			for(int j=0; j< ((ClaseM) Proyecto.getClaseM(i)).Atributos.size(); j++ ){
				if( ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getId().equals(id) ){
					nombre = ((AtributoM) ((ClaseM) Proyecto.getClaseM(i)).getAtributoM(j)).getNombre();
					return nombre;
				}
			}		
		}
		
		return nombre;
	}
	
} 