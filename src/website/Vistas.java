package website;

import clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vistas {

	public Header header = new Header();
	
	public Vistas(ProyectoM Proyecto){
		
		System.out.println("\nVistas\n");
		
		String vista = "";
		String Dominio = "";
		String Alias = "";
		String RefInferencia = "";
		
		String page = "";
		
		
		for(int b=0; b< Proyecto.Vista.size(); b++){			
			
			for(int b2=0; b2< ((VistaM) Proyecto.getVistaM(b)).NodoJerarquia.size(); b2++){
				
				for(int b3=0; b3< ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2))).NodoJerarquia.size(); b3++){					
					Dominio = ((AliasM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).Alias).getAlias();
															
					System.out.println(Dominio);
					
					for(int b4=0; b4< ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2))).getNodoJerarquia(b3)).NodoJerarquia.size(); b4++){
						Alias = ((AliasM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getNodoJerarquia(b4)).Alias).getAlias();
						RefInferencia = ((AliasM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((NodoJerarquiaM) ((VistaM) Proyecto.getVistaM(b)).getNodoJerarquia(b2)).getNodoJerarquia(b3)).getNodoJerarquia(b4)).Alias).getRefInferencia();
						
						if(!Dominio.equals(Alias))
							vista = (Dominio + "_" + Alias).replace(" ", "_") + ".php";
						else
							vista = Alias.replace(" ", "_") + ".php"; 
						
						
						System.out.println("\t" + vista);
						System.out.println("\t" + RefInferencia);
		
						//Begin website
						page = header.getHeader() + header.getMenu() + header.getBegin_main();
						
						page = serviciopresentacion(RefInferencia, Proyecto, page);
						
						//End website
						page += header.getEnd_main() + header.getEnd_html();
						
						
						try {
							  File file = new File(Proyecto.getDirectorio()+"\\"+vista);
					          BufferedWriter output = new BufferedWriter(new FileWriter(file));
					          output.write(page);
					          output.close();
					        } catch ( IOException e ) {
					           e.printStackTrace();
					        } 
						
					}
					
				}				
			}
		}

	}
	
	
	
	public String serviciopresentacion(String RefInferencia, ProyectoM Proyecto, String page){
		
		String ElemRefInferencia = "";
		String Alias = "";
		String Atributo = "";
		String function = "";
		String Clase = "";
		String qs = "\"SELECT ";
		String id_attribute = "";
		String service = "";
		String back = ""; 
		String argumentos = "";
		String required = "";
		String tipodato = "";
		String tamanyo = "";
		List Atributos = new ArrayList<String>();
		List AliasAtributos = new ArrayList<String>();
		List FormulaAtributos = new ArrayList<String>();
		boolean find = false;
		
		int countAND = 0;
		String strfrom = "";
		String strwhere = "";
		String dominio = "";
		
		for(int x1=0; x1< Proyecto.Clase.size(); x1++){ //Clase.Servicio.PPresentacion
			 Clase = ((ClaseM) Proyecto.getClaseM(x1)).getNombreEnBD();
			 for(int x2=0; x2 < ((ClaseM) Proyecto.getClaseM(x1)).Servicios.size(); x2++ ){				 
				 for(int x3=0; x3 < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).PPresentacion.size(); x3++){
					 
					 //DESTROY
					 if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getDTipoEvento().equals("D") && ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId().equals(RefInferencia)){
						 
						 find = true;
						 id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getNombre();
						 tipodato = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getTipoDato();
						 service = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre();
						 Alias = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
						 
						 if(tipodato.equals("Autonumerico"))
							 tipodato = "number min='0'";
						 
						 page += "\n\n\t\t\t\t\t<?php\n";
						 
						 page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + Alias + "</span></div>\";\n\n";
						 
						 page += "\t\t\t\t\t\tif(!isset($_POST['" + service + "']) && !isset($_POST['buscar'])){\n"
						 		+ "\t\t\t\t\t\t\techo \"<form action='' method='POST'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"Id <input type=" + tipodato + " name='" + id_attribute + "' "
						 		+ "placeholder='" + id_attribute + "' required>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='" + Alias + "' value='" + service + "'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='buscar' value='buscar'></form>\";}\n";
						 
						 System.out.println("Pob: " + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(0)).HerArgObjValM.getRefPPPobClaseM() );
						 
						 
						 
						 //RELACIONAR POBLACION de Clase.Servicio.Argumento.HerArgObjVal.PPPobClase con id de CLASE.PPRESENTACION
						 //Buscar id y formar el grupo poblacion a mostrar de la tabla
						 page += "\t\t\t\t\t\telse if(isset($_POST['buscar'])){\n\t\t\t\t\t\t$conn = new postgresql;\n\t\t\t\t\t\t$conn->connect();\n";
						 
						 for(int c1=0; c1 < Proyecto.Clase.size(); c1++){
						 
							 for(int c2=0; c2 < ((ClaseM) Proyecto.getClaseM(c1)).PPresentacion.size(); c2++){	
							 
							 	 if( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getId().equals( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(0)).HerArgObjValM.getRefPPPobClaseM() ) ){
								 	 
								 	 System.out.println("\t_Id: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getId());
 									 System.out.println("\tNombre: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getNombre());
 									 System.out.println("\n\tRef.NavegaOfertadaM: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefNavegaOfertadaM());
									 System.out.println("\n\tRef.AccionesOfertaM: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefAccionesOfertaM());
									 System.out.println("\n\tRef.ConjVisualM: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefConjVisualM());
								 	 
									 for(int cv=0; cv < ((ClaseM) Proyecto.getClaseM(c1)).ConjVisual.size(); cv++ ){										 
										 if( ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getId().equals( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefConjVisualM() ) ){
											 System.out.println("\t\tConjVisual: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getId());
											 System.out.println("\t\tNombre: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getNombre());
											 
											 dominio = ((ClaseM) Proyecto.getClaseM(c1)).getNombreEnBD();
											 strfrom = dominio;
											 
											 for(int cv2=0; cv2 < ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).ElemConjVisual.size(); cv2++){
												 Atributos.add( ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getElemConjVisual(cv2)).Formula.getFormulaString() );
												 
												 
												 String M_Clase = ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getElemConjVisual(cv2)).Formula.getM_Clase();
												 String fk = "";
												 
												 if ( M_Clase.length() > 0 && !strfrom.contains(M_Clase) ){
													 strfrom += ", " + M_Clase;
													 
													 if(countAND > 0)
														 strwhere += " AND ";
													 
													 countAND ++;
													 
													 for(int f1=0; f1< Proyecto.Clase.size(); f1++){
														 if( ((ClaseM) Proyecto.getClaseM(f1)).getNombreEnBD().equals(M_Clase) ){
															 
															 for(int f2=0; f2< ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.size(); f2++ ){
																 if( ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).contains(dominio) ){
																	 fk = ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).replace(" INT NULL", "");
																 }
															 }
															 
															 
															 
														 }
													 }
													 													 
													 //IF THE RELATIONSHIP IS IN RELATIONDB
													 //GET THE ID EQUIAL IN FK OR ID 
													 for(int sqlw=0; sqlw< Proyecto.RelationDB.size(); sqlw++){
														 if( ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(dominio+M_Clase) || ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(M_Clase+dominio)){
														 	if( !strwhere.contains(((RelationDB) Proyecto.getRelation(sqlw)).getRelation()) )	
															 	strwhere += ((RelationDB) Proyecto.getRelation(sqlw)).getRelation();
														 }
													 }
												 }
											 }
											 
											 //BUG END "AND"
											 if(countAND > 0)
											 	strwhere += "\"";
											 
											 //RECORRER ATRIBUTOS
											 String tablerow = "\t\t\t\t\t\techo \"<table><tr>";
											 String row = "\t\t\t\t\t\twhile ($row = pg_fetch_array($result)){\n\t\t\t\t\t\t\t echo \"<form action='' method='POST'>\";\n\t\t\t\t\t\t\t";
											 for(int a1=0; a1< Atributos.size(); a1++){
												tablerow += "<td>" + Atributos.get(a1) + "</td>";
												
												if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
													row += "echo \"<td>\".$row[\""+ ((String) Atributos.get(a1)).substring(((String) Atributos.get(a1)).indexOf(".")+1, ((String) Atributos.get(a1)).length()) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												}
												else{
													row += "echo \"<td>\".$row[\""+ Atributos.get(a1) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												}
											 	
												//dominio.atributo
												if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
													qs += Atributos.get(a1) + ", ";
												}
												else{
													qs += dominio + "." + Atributos.get(a1) + ", ";	
												}	
											 }
											 
											 row += "echo \"<input type='hidden' name='" + id_attribute + "' value=\".$row[\"" + id_attribute + "\"].\">\";";
											 row += "echo \"<td><input type='submit' name='" + service + "' value='" + service + "'></td></form></tr>\";\n\t\t\t\t\t\t }\n \t\t\t\t\t\techo \"</tr></table>\";\n";
											 tablerow += "<td></td></tr><tr>\";\n";
											 
											 String search = id_attribute + "=\".$_POST[\"" + id_attribute + "\"].\" ";
											 
											 if(countAND == 0)
												 strwhere = "";
											 else
												 strwhere = " AND " + strwhere;
											 												 
											 
											 qs = "\t\t\t\t\t\t$qs = " + qs.substring(0, qs.length()-2) + " FROM " + strfrom + " WHERE " 
													 				   + search + strwhere + "\";\n\t\t\t\t\t\t$result = pg_query($conn->DB, $qs);\n\n";
											 
											 String backpage = ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).Alias.getAlias() + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
											 
											 backpage = backpage.replace(" ", "_");
											 
											 back = "\n\t\t\t\t\t\techo \"<br><br><br><br><a href='" + backpage  + ".php'>Volver</a>\";";
											 
											 page += qs + tablerow + row + back + "\n\t\t\t\t\t\t$conn->close();\n\t\t\t\t\t}\n";
											 
										 }										 
									 }
									 
								 	
								}
							
							}
						}
									
						  
						 
						 //DESTROY ELEMENT
						 function = "";
						 System.out.println("\t" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre());
						 
						 function = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre() + "(";
						 for(int x4=0; x4 < ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.ElemAgrup.size(); x4++ ){	 
						 	//System.out.println( "\t\t\t" + ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia() );
							 ElemRefInferencia = ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia();
							 
							 for(int s1=0; s1 < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Argumento.size(); s1++){								 
									
								 if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getId().equals(ElemRefInferencia) ){
										Alias = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).Alias.getAlias();
										Atributo = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getNombre();
										
										//System.out.println("\t\t\t" + Atributo + "   " + Alias);
										function += Atributo + ", ";
									}								 
							 } 
						 }		
						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['" + service + "'])){\n";
						 
						 page += "\n\t\t\t\t\t\t$" + Clase + " = new " + Clase + ";\n\t\t\t\t\t\t$" + Clase + "->connect();\n";
						 
						 function = service + "($_POST['" + id_attribute + "']);";
						 
						 page += "\t\t\t\t\t\t$" + Clase + "->" + function + "\n";
						 page += "\t\t\t\t\t\t$" + Clase + "->close();\n\t\t\t\t\t" + back + "\n\t\t\t\t\t}\n\t\t\t\t\t?>\n";
						 
						 return page;
							 
							 
						 
					 }
					 
					 //CREATE
					 if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getDTipoEvento().equals("C") && ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId().equals(RefInferencia)){
					 
						 find = true;
						 function = "";
						 System.out.println("\t" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre());
						 
						 function = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre() + "(";
						 		
					 
						 						 
						 find = true;
						 id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getNombre();
						 service = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre();
						 Alias = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
						 page += "\n\n\t\t\t\t\t<?php\n";
						 
						 page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + Alias + "</span></div>\";\n\n";
						 
						 page += "\t\t\t\t\t\tif(!isset($_POST['" + service + "'])){\n"
						 		+ "\t\t\t\t\t\t\techo \"<form action='' method='POST'>\";\n";
						 
						 
						 for(int x4=0; x4 < ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.ElemAgrup.size(); x4++ ){	 
							 	//System.out.println( "\t\t\t" + ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia() );
								 ElemRefInferencia = ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia();
								 
								 for(int s1=1; s1 < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Argumento.size(); s1++){								 
										
									 if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getId().equals(ElemRefInferencia) ){
											Alias = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).Alias.getAlias();
											Atributo = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getNombre();
											tipodato = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).HerArgDatoValM.getTipoDato();
											tamanyo = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).HerArgDatoValM.getTamanyo();
											String m_valor = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).FormValorDef.getm_valor();
											
											
											
											boolean objval = false;
											
											//type
											if(tipodato.equals("String"))
												tipodato = "text";
											else if(tipodato.equals("Autonumerico") || tipodato.equals("Nat"))
												tipodato = "number min='0'";
											else if(tipodato.equals("Date"))
												tipodato = "date";
											else if(((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getTipoImplicito().equals("P_PASSWORD"))
												tipodato = "password";
											else{
												tipodato = "number min='0'";
												objval = true;
											}
											
											//maxsize
											if(!tamanyo.equals("0") && !objval){
												if(tamanyo.equals("1")) tamanyo = "200"; //DEFAULT SIZE 200 PASSWORD, IN MODEL PASSWORD SIZE 1
												tamanyo = " maxlength=" + tamanyo;
											}
											else
												tamanyo = "";
											
											if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getAdmiteNulos().equals("0") ){
												required = "required";
											}
											else{
												required = "";
											}
											
											if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).HerArgDatoValM.getTipoDato().equals("Bool") ){
												String value = "";
												String msg = "";
												
												for(int a=0; a < ((ClaseM) Proyecto.getClaseM(x1)).Atributos.size(); a++){
													if( ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(a)).getId().equals(((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).Alias.getRefInferencia())){
														value = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(a)).FormValorDef.Formula.getFormulaString();
														//System.out.println("Value: " + value);
														
														msg = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(a)).getMsgAyuda();
														System.out.println(msg);
														
														if(value.equals("FALSE"))
															value="FALSE";
														else if(value.equals("TRUE"))
															value="TRUE";
														
													}
												}
												
												page += "\t\t\t\t\t\t\techo \"<input type=hidden name='" + Atributo + "' value=" + value + " " + required + "> \";\n";
											}
											else{
												String msg = "";
												
												for(int a=0; a < ((ClaseM) Proyecto.getClaseM(x1)).Atributos.size(); a++){
													if( ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(a)).getId().equals(((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).Alias.getRefInferencia())){
														msg = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(a)).getMsgAyuda();
													}
												}												
												
												page += "\t\t\t\t\t\t\techo \"<br><span title='" + msg + "'>" + Alias + " <input type=" + tipodato + tamanyo + " name='" + Atributo + "' placeholder='" + Alias + "' " + required + "></span><br>\";\n";
											}
											
											argumentos += "$_POST['" + Atributo + "'], ";
										}								 
								 } 
						 }
						 
						 argumentos = argumentos.substring(0, argumentos.length()-2);
						 
						 page += "\t\t\t\t\t\t\techo \"<br><br><input type='submit' name='" + service + "' value='" + service + "'></form>\";\n\t\t\t\t\t\t}\n\n\n";
						 
						 
						 back = "\n\t\t\t\t\t\techo \"<br><a href='" + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(x1)).getPPresentacionM(x2)).Alias.getAlias().replace(" ", "_") + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias().replace(" ", "_") + ".php'>Volver</a>\";";
						
						 						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['" + service + "'])){\n";
						 
						 page += "\n\t\t\t\t\t\t$" + Clase + " = new " + Clase + ";\n\t\t\t\t\t\t$" + Clase + "->connect();\n";
						 
						 function = service + "(" + argumentos + ");";
						 
						 page += "\t\t\t\t\t\t$" + Clase + "->" + function + "\n";
						 page += "\t\t\t\t\t\t$" + Clase + "->close();\n\t\t\t\t\t" + back + "\n\t\t\t\t\t}\n\t\t\t\t\t?>\n";
						 
						 return page;						 
						 
					 }
					 
					//Set Password
					 if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getDTipoEvento().equals("P") && ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId().equals(RefInferencia)){
						 find = true;
						 
						 id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getNombre();
						 tipodato = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getTipoDato();
						 service = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre();
						 Alias = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
						 
						 strfrom = ((ClaseM) Proyecto.getClaseM(x1)).getNombreEnBD();
						 
						 if(tipodato.equals("Autonumerico"))
							 tipodato = "number min='0'";
						 
						 page += "\n\n\t\t\t\t\t<?php\n";
						 
						 page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + Alias + "</span></div>\";\n\n";
						 
						 page += "\t\t\t\t\t\tif(!isset($_POST['" + service + "']) && !isset($_POST['buscar'])){\n"
						 		+ "\t\t\t\t\t\t\techo \"<form action='' method='POST'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"Id <input type=" + tipodato + " name='" + id_attribute + "' "
						 		+ "placeholder='" + id_attribute + "' required>\";\n"						 		
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='buscar' value='buscar'></form>\";}\n";
						 
						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['buscar'])){\n\t\t\t\t\t\t$conn = new postgresql;\n\t\t\t\t\t\t$conn->connect();\n";
						 
						 
						 
								 
								 //RECORRER ATRIBUTOS
						
						 String row = "\t\t\t\t\t\twhile ($row = pg_fetch_array($result)){\n\t\t\t\t\t\t\t echo \"<form action='' method='POST'>\";\n\t\t\t\t\t\t\t";
						 for(int a1=0; a1< Atributos.size(); a1++){
														
							if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
								row += "echo \"<td>\".$row[\""+ ((String) Atributos.get(a1)).substring(((String) Atributos.get(a1)).indexOf(".")+1, ((String) Atributos.get(a1)).length()) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
							}
							else{
								row += "echo \"<td>\".$row[\""+ Atributos.get(a1) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
							}
						 	
							//dominio.atributo
							if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
								qs += Atributos.get(a1) + ", ";
							}
							else{
								qs += dominio + "." + Atributos.get(a1) + ", ";	
							}	
						 }
						 
						 row += "echo \"<input type='hidden' name='" + id_attribute + "' value=\".$row[\"" + id_attribute + "\"].\">\";\n";
						 row += "\t\t\t\t\t\techo \"<br>New Password <input type=password maxlength=1 name='p_NewPassword' placeholder='New Password' required><br>\";\n";
						 row += "\t\t\t\t\t\techo \"<td><input type='submit' name='" + service + "' value='" + service + "'></td></form></tr>\";\n\t\t\t\t\t\t }\n \n";
						 
						 
						 String search = id_attribute + "=\".$_POST[\"" + id_attribute + "\"].\" ";
						 
						 if(countAND == 0)
							 strwhere = "";
						 else
							 strwhere = " AND " + strwhere;
						 												 
						 
						 qs = "\t\t\t\t\t\t$qs = " + "\"SELECT " + id_attribute + " FROM " + strfrom + " WHERE " 
								 				   + search + strwhere + "\";\n\t\t\t\t\t\t$result = pg_query($conn->DB, $qs);\n\n";
						 
						 						 
						 back = "\n\t\t\t\t\t\techo \"<br><a href='" + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(x1)).getPPresentacionM(0)).Alias.getAlias().replace(" ", "_") + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias().replace(" ", "_") + ".php'>Volver</a>\";";
						 
						 page += qs + row + back + "\n\t\t\t\t\t\t$conn->close();\n\t\t\t\t\t}\n";
						 
						 
						 
						 //page += "\t\t\t\t\t\t\techo \"<input type='hidden' name='" + id_attribute + "' value=\".$row[\"" + id_attribute + "\"].\">\";";
						 
						 
						 
						 //page += "\t\t\t\t\t\t\techo \"<br><br><input type='submit' name='" + service + "' value='" + service + "'></form>\";\n\t\t\t\t\t\t}\n\n\n";
						 
						 
						 //back = "\n\t\t\t\t\t\techo \"<br><a href='" + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(x1)).getPPresentacionM(0)).Alias.getAlias().replace(" ", "_") + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias().replace(" ", "_") + ".php'>Volver</a>\";";
						 
						 						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['" + service + "'])){\n";
						 
						 page += "\n\t\t\t\t\t\t$" + Clase + " = new " + Clase + ";\n\t\t\t\t\t\t$" + Clase + "->connect();\n";
						 
						 function = service + "($_POST['p_NewPassword'], $_POST['" + id_attribute + "']);";
						 
						 page += "\t\t\t\t\t\t$" + Clase + "->" + function + "\n";
						 page += "\t\t\t\t\t\t$" + Clase + "->close();\n\t\t\t\t\t" + back + "\n\t\t\t\t\t}\n\t\t\t\t\t?>\n";
						 
						 return page;	
						 
						 
						
					 }
					 
					 //EDIT
					 else if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre().contains("edit") && ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId().equals(RefInferencia)){
						 String form = "";
						 
						 List arguments = new ArrayList<String>();
						 
						 find = true;
						 
						 id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getNombre();
						 tipodato = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getTipoDato();
						 service = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre();
						 Alias = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
						 
						 strfrom = ((ClaseM) Proyecto.getClaseM(x1)).getNombreEnBD();
						 
						 if(tipodato.equals("Autonumerico"))
							 tipodato = "number min='0'";
						
						 page += "\n\n\t\t\t\t\t<?php\n";
						 
						 page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + Alias + "</span></div>\";\n\n";
						 
						 page += "\t\t\t\t\t\tif(!isset($_POST['" + service + "']) && !isset($_POST['buscar'])){\n"
						 		+ "\t\t\t\t\t\t\techo \"<form action='' method='POST'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"Id <input type=" + tipodato + " name='" + id_attribute + "' "
						 		+ "placeholder='" + id_attribute + "' required>\";\n"						 		
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='buscar' value='buscar'></form>\";}\n";
						 
						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['buscar'])){\n\t\t\t\t\t\t$conn = new postgresql;\n\t\t\t\t\t\t$conn->connect();\n";
						 
						 						 
						 //RECORRER ATRIBUTOS
						 
						 qs = "\t\t\t\t\t\t$qs = \"SELECT " + id_attribute + ", ";
						 form = "\t\t\t\t\t\twhile ($row = pg_fetch_array($result)){\n"
						 		+ "\t\t\t\t\t\techo \"<form action='' method='POST'>\";\n";
						 
						 //id
						 form +="\t\t\t\t\t\techo \"<br><input type=hidden name='" + id_attribute + "' value=\".$row['" + id_attribute + "'].\" ><br>\";\n";
						 
						 for(int a=0; a < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Argumento.size(); a++){
							 if( !((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getTipoArgumento().equals("O") ){
								 qs += ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getNombre().replace("p_", "") + ", ";
								 
								 arguments.add(((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getNombre());
								 
								 tipodato = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).HerArgDatoValM.getTipoDato();
								 
								 if(tipodato.equals("String"))
										tipodato = "text";
									else if(tipodato.equals("Autonumerico") || tipodato.equals("Nat"))
										tipodato = "number min='0'";
									else if(tipodato.equals("Date"))
										tipodato = "date";
								 
								 
								 
								 form += "\t\t\t\t\t\techo \"<br>" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).Alias.getAlias() 
										 + " <input type=" + tipodato 
										 + " maxlength=" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).HerArgDatoValM.getTamanyo() 
										 + " name='" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getNombre() 
										 + "' value=\".$row['" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getNombre().replace("p_", "") 
										 + "'].\" required><br>\";\n"; 
							 }							 
						 }
						 
						 form += "\t\t\t\t\t\techo \"<br><br><input type='submit' name='" + service + "' value='" + service + "'></form>\";}\n";
						 
						 qs = qs.substring(0, qs.length()-2) + " FROM " + strfrom + " WHERE " + id_attribute + "=\".$_POST['" + id_attribute + "'];\n";						 
						 
						 qs += "\t\t\t\t\t\t$result = pg_query($conn->DB, $qs);\n\n";
						 
						 qs += form;
						 
						 back = "\n\t\t\t\t\t\techo \"<br><a href='" + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(x1)).getPPresentacionM(0)).Alias.getAlias().replace(" ", "_") + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias().replace(" ", "_") + ".php'>Volver</a>\";";
						 
						 page += qs + back + "\n\t\t\t\t\t\t$conn->close();\n\t\t\t\t\t}\n";
						 
						 						 						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['" + service + "'])){\n";
						 
						 page += "\n\t\t\t\t\t\t$" + Clase + " = new " + Clase + ";\n\t\t\t\t\t\t$" + Clase + "->connect();\n";
						 
						 function = service + "(";
						 
						 for(int a1=0; a1 < arguments.size(); a1++){
							 function += "$_POST['" + ((String) arguments.get(a1)) + "'], ";
						 }
						 
						 //id
						 function +="$_POST['" + id_attribute + "']);";
						 
						 page += "\t\t\t\t\t\t$" + Clase + "->" + function + "\n";
						 page += "\t\t\t\t\t\t$" + Clase + "->close();\n\t\t\t\t\t" + back + "\n\t\t\t\t\t}\n\t\t\t\t\t?>\n";
						 
						 return page;	
						
				 
					 }
					 
					 //INS
					 else if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre().contains("Ins") && ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId().equals(RefInferencia)){
						if( !((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getDont().equals("1") ){
						 	String form = "";
							
						 	
						 	//javascript search open new windows
						 	page += "\t\t\t\t\t<script language='javascript'>\n\t\t\t\t\t\tfunction post_value(){\n"
						 			+ "\t\t\t\t\t\topener.document.f1.p_thislocal.value = document.frm.c_name.value;\n"
						 			+ "\t\t\t\t\t\tself.close();}\n\t\t\t\t\t</script>\n\n";
						 	
						 							 	
							 List arguments = new ArrayList<String>();
							 
							 find = true;
							 
							 id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getNombre();
							 tipodato = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getTipoDato();
							 service = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre();
							 Alias = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
							 
							 strfrom = ((ClaseM) Proyecto.getClaseM(x1)).getNombreEnBD();
							 
							 if(tipodato.equals("Autonumerico"))
								 tipodato = "number min='0'";
							
							 page += "\n\n\t\t\t\t\t<?php\n";
							 
							 page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + Alias + "</span></div>\";\n\n";
							 
							 page += "\t\t\t\t\t\tif(!isset($_POST['" + service + "']) ){\n";						 
							 						 
							 //RECORRER ATRIBUTOS
							 
							 form = "\t\t\t\t\t\techo \"<form action='' method='POST' name='f1'>\";\n";
							 
							 
							 for(int a=0; a < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Argumento.size(); a++){
														 
								 arguments.add(((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getNombre());
									 
								 tipodato = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).HerArgDatoValM.getTipoDato();
									 
								 
								 form += "\t\t\t\t\t\techo \"<br>" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).Alias.getAlias() 
										 + " <input type=number	min='0' placeholder='" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).Alias.getAlias() + "' " + " name='" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getNombre()										  
										 + "' required><a href='javascript:void(0);' NAME='' title='' onClick=window.open('buscar" + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)).getNombre() + ".php','Ratting','width=720,height=300,left=150,top=200,toolbar=1,status=1,');>buscar</a> <br><br>\";\n"; 
								 						 
							 
								//CREATE SEARCHALIAS.php 
								create_search(((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(a)), Proyecto);
							 }
							 
							 form += "\t\t\t\t\t\techo \"<br><br><input type='submit' name='" + service + "' value='" + service + "'></form>\";}\n";
							 
							 						 
							 back = "\n\t\t\t\t\t\techo \"<br><a href='" + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(x1)).getPPresentacionM(0)).Alias.getAlias().replace(" ", "_") + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias().replace(" ", "_") + ".php'>Volver</a>\";";
							 
							 page += form + "\n\n";
							 
							 						 						 
							 page += "\t\t\t\t\t\telse if(isset($_POST['" + service + "'])){\n";
							 
							 page += "\n\t\t\t\t\t\t$" + Clase + " = new " + Clase + ";\n\t\t\t\t\t\t$" + Clase + "->connect();\n";
							 
							 function = service + "(";
							 
							 for(int a1=0; a1 < arguments.size(); a1++){
								 function += "$_POST['" + ((String) arguments.get(a1)) + "'], ";
							 }
							 
							 //id
							 
							 function = function.substring(0, function.length()-2) + ");";
							 
							 page += "\t\t\t\t\t\t$" + Clase + "->" + function + "\n";
							 page += "\t\t\t\t\t\t$" + Clase + "->close();\n\t\t\t\t\t" + back + "\n\t\t\t\t\t}\n\t\t\t\t\t?>\n";
							 
							 return page;
						}
						 
					 }
					 
					 
					 //EVALUACIONES
					 else if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getTipo().equals("E") && ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getDTipoEvento().equals("N") && ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId().equals(RefInferencia)){

						 find = true;
						 id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getNombre();
						 tipodato = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getTipoDato();
						 service = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre();
						 Alias = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
						 
						 if(tipodato.equals("Autonumerico"))
							 tipodato = "number min='0'";
						 
						 page += "\n\n\t\t\t\t\t<?php\n";
						 
						 page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + Alias + "</span></div>\";\n\n";
						 
						 page += "\t\t\t\t\t\tif(!isset($_POST['" + service + "']) && !isset($_POST['buscar'])){\n"
						 		+ "\t\t\t\t\t\t\techo \"<form action='' method='POST'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"Id <input type=" + tipodato + " name='" + id_attribute + "' "
						 		+ "placeholder='" + id_attribute + "' required>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='" + Alias + "' value='" + service + "'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='buscar' value='buscar'></form>\";}\n";
						 
						 System.out.println("Pob: " + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(0)).HerArgObjValM.getRefPPPobClaseM() );
						 
						 
						 
						 //RELACIONAR POBLACION de Clase.Servicio.Argumento.HerArgObjVal.PPPobClase con id de CLASE.PPRESENTACION
						 //Buscar id y formar el grupo poblacion a mostrar de la tabla
						 page += "\t\t\t\t\t\telse if(isset($_POST['buscar'])){\n\t\t\t\t\t\t$conn = new postgresql;\n\t\t\t\t\t\t$conn->connect();\n";
						 
						 for(int c1=0; c1 < Proyecto.Clase.size(); c1++){
						 
							 for(int c2=0; c2 < ((ClaseM) Proyecto.getClaseM(c1)).PPresentacion.size(); c2++){	
							 
							 	 if( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getId().equals( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(0)).HerArgObjValM.getRefPPPobClaseM() ) ){
								 	 
									 for(int cv=0; cv < ((ClaseM) Proyecto.getClaseM(c1)).ConjVisual.size(); cv++ ){										 
										 if( ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getId().equals( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefConjVisualM() ) ){
											 System.out.println("\t\tConjVisual: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getId());
											 System.out.println("\t\tNombre: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getNombre());
											 
											 dominio = ((ClaseM) Proyecto.getClaseM(c1)).getNombreEnBD();
											 strfrom = dominio;
											 
											 for(int cv2=0; cv2 < ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).ElemConjVisual.size(); cv2++){
												 Atributos.add( ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getElemConjVisual(cv2)).Formula.getFormulaString() );
												 
												 
												 String M_Clase = ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getElemConjVisual(cv2)).Formula.getM_Clase();
												 String fk = "";
												 
												 if ( M_Clase.length() > 0 && !strfrom.contains(M_Clase) ){
													 strfrom += ", " + M_Clase;
													 
													 if(countAND > 0)
														 strwhere += " AND ";
													 
													 countAND ++;
													 
													 for(int f1=0; f1< Proyecto.Clase.size(); f1++){
														 if( ((ClaseM) Proyecto.getClaseM(f1)).getNombreEnBD().equals(M_Clase) ){
															 
															 for(int f2=0; f2< ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.size(); f2++ ){
																 if( ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).contains(dominio) ){
																	 fk = ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).replace(" INT NULL", "");
																 }
															 }
															 
															 
															 
														 }
													 }
													 													 
													 //IF THE RELATIONSHIP IS IN RELATIONDB
													 //GET THE ID EQUIAL IN FK OR ID 
													 for(int sqlw=0; sqlw< Proyecto.RelationDB.size(); sqlw++){
														 if( ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(dominio+M_Clase) || ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(M_Clase+dominio)){
														 	if( !strwhere.contains(((RelationDB) Proyecto.getRelation(sqlw)).getRelation()) )	
															 	strwhere += ((RelationDB) Proyecto.getRelation(sqlw)).getRelation();
														 }
													 }
												 }
											 }
											 
											 //BUG END "AND"
											 if(countAND > 0)
											 	strwhere += "\"";
											 
											 //RECORRER ATRIBUTOS
											 String tablerow = "\t\t\t\t\t\techo \"<table><tr>";
											 String row = "\t\t\t\t\t\twhile ($row = pg_fetch_array($result)){\n\t\t\t\t\t\t\t echo \"<form action='' method='POST'>\";\n\t\t\t\t\t\t\t";
											 for(int a1=0; a1< Atributos.size(); a1++){
												tablerow += "<td>" + Atributos.get(a1) + "</td>";
												
												if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
													row += "echo \"<td>\".$row[\""+ ((String) Atributos.get(a1)).substring(((String) Atributos.get(a1)).indexOf(".")+1, ((String) Atributos.get(a1)).length()) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												}
												else{
													row += "echo \"<td>\".$row[\""+ Atributos.get(a1) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												}
											 	
												//dominio.atributo
												if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
													qs += Atributos.get(a1) + ", ";
												}
												else{
													qs += dominio + "." + Atributos.get(a1) + ", ";	
												}	
											 }
											 
											 row += "echo \"<input type='hidden' name='" + id_attribute + "' value=\".$row[\"" + id_attribute + "\"].\"></tr>\";\n\t\t\t\t\t}\n";
											 row += "\t\t\t\t\t\techo \"</tr></table><br><br><br><br><br>\";\n";
											 
											 
											 row += "\t\t\t\t\t\techo \"<br><br><input type='submit' name='" + service + "' value='" + service + "'></form><br><br>\";\n\n ";
											 
											 
											 
											 tablerow += "</tr><tr>\";\n";
											 
											 String search = id_attribute + "=\".$_POST[\"" + id_attribute + "\"].\" ";
											 
											 if(countAND == 0)
												 strwhere = "";
											 else
												 strwhere = " AND " + strwhere;
											 												 
											 
											 qs = "\t\t\t\t\t\t$qs = " + qs.substring(0, qs.length()-2) + " FROM " + strfrom + " WHERE " 
													 				   + search + strwhere + ";\n\t\t\t\t\t\t$result = pg_query($conn->DB, $qs);\n\n";
											 
											 String backpage = ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).Alias.getAlias() + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
											 
											 backpage = backpage.replace(" ", "_");
											 
											 back = "\n\t\t\t\t\t\techo \"<br><br><br><br><a href='" + backpage  + ".php'>Volver</a>\";";
											 
											 page += qs + tablerow + row + back + "\n\t\t\t\t\t\t$conn->close();\n\t\t\t\t\t}\n";
											 
										 }										 
									 }
									 
								 	
								}
							
							}
						}
									
						  
						 
						 //
						 function = "";
						 System.out.println("\t" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre());
						 
						 function = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre() + "(";
						 for(int x4=0; x4 < ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.ElemAgrup.size(); x4++ ){	 
						 	//System.out.println( "\t\t\t" + ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia() );
							 ElemRefInferencia = ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia();
							 
							 for(int s1=0; s1 < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Argumento.size(); s1++){								 
									
								 if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getId().equals(ElemRefInferencia) ){
										Alias = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).Alias.getAlias();
										Atributo = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getNombre();
										
										//System.out.println("\t\t\t" + Atributo + "   " + Alias);
										function += Atributo + ", ";
									}								 
							 } 
						 }		
						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['" + service + "'])){\n";
						 
						 page += "\n\t\t\t\t\t\t$" + Clase + " = new " + Clase + ";\n\t\t\t\t\t\t$" + Clase + "->connect();\n";
						 
						 function = service + "($_POST['" + id_attribute + "']);";
						 
						 page += "\t\t\t\t\t\t$" + Clase + "->" + function + "\n";
						 page += "\t\t\t\t\t\t$" + Clase + "->close();\n\t\t\t\t\t" + back + "\n\t\t\t\t\t}\n\t\t\t\t\t?>\n";
						 
						 
						 
						 return page;
						 
					 }
					 
					 //TRANSACCIONES
					 else if( ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getTipo().equals("T") && ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getTipo().equals("T") && ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).getId().equals(RefInferencia)){
						 find = true;
						 id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getNombre();
						 tipodato = ((AtributoM) ((ClaseM) Proyecto.getClaseM(x1)).getAtributoM(0)).getTipoDato();
						 service = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre();
						 Alias = ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
						 
						 if(tipodato.equals("Autonumerico"))
							 tipodato = "number min='0'";
						 
						 page += "\n\n\t\t\t\t\t<?php\n";
						 
						 page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + Alias + "</span></div>\";\n\n";
						 
						 page += "\t\t\t\t\t\tif(!isset($_POST['" + service + "']) && !isset($_POST['buscar'])){\n"
						 		+ "\t\t\t\t\t\t\techo \"<form action='' method='POST'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"Id <input type=" + tipodato + " name='" + id_attribute + "' "
						 		+ "placeholder='" + id_attribute + "' required>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='" + Alias + "' value='" + service + "'>\";\n"
						 		+ "\t\t\t\t\t\t\techo \"&nbsp; <input type='submit' name='buscar' value='buscar'></form>\";}\n";
						 
						 System.out.println("Pob: " + ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(0)).HerArgObjValM.getRefPPPobClaseM() );
						 
						 
						 
						 //RELACIONAR POBLACION de Clase.Servicio.Argumento.HerArgObjVal.PPPobClase con id de CLASE.PPRESENTACION
						 //Buscar id y formar el grupo poblacion a mostrar de la tabla
						 page += "\t\t\t\t\t\telse if(isset($_POST['buscar'])){\n\t\t\t\t\t\t$conn = new postgresql;\n\t\t\t\t\t\t$conn->connect();\n";
						 
						 for(int c1=0; c1 < Proyecto.Clase.size(); c1++){
						 
							 for(int c2=0; c2 < ((ClaseM) Proyecto.getClaseM(c1)).PPresentacion.size(); c2++){	
							 
							 	 if( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getId().equals( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(0)).HerArgObjValM.getRefPPPobClaseM() ) ){
								 	 
									 for(int cv=0; cv < ((ClaseM) Proyecto.getClaseM(c1)).ConjVisual.size(); cv++ ){										 
										 if( ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getId().equals( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefConjVisualM() ) ){
											 System.out.println("\t\tConjVisual: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getId());
											 System.out.println("\t\tNombre: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getNombre());
											 
											 dominio = ((ClaseM) Proyecto.getClaseM(c1)).getNombreEnBD();
											 strfrom = dominio;
											 
											 for(int cv2=0; cv2 < ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).ElemConjVisual.size(); cv2++){
												 Atributos.add( ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getElemConjVisual(cv2)).Formula.getFormulaString() );
												 
												 
												 String M_Clase = ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(c1)).getConjVisualM(cv)).getElemConjVisual(cv2)).Formula.getM_Clase();
												 String fk = "";
												 
												 if ( M_Clase.length() > 0 && !strfrom.contains(M_Clase) ){
													 strfrom += ", " + M_Clase;
													 
													 if(countAND > 0)
														 strwhere += " AND ";
													 
													 countAND ++;
													 
													 for(int f1=0; f1< Proyecto.Clase.size(); f1++){
														 if( ((ClaseM) Proyecto.getClaseM(f1)).getNombreEnBD().equals(M_Clase) ){
															 
															 for(int f2=0; f2< ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.size(); f2++ ){
																 if( ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).contains(dominio) ){
																	 fk = ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).replace(" INT NULL", "");
																 }
															 }
															 
															 
															 
														 }
													 }
													 													 
													 //IF THE RELATIONSHIP IS IN RELATIONDB
													 //GET THE ID EQUIAL IN FK OR ID 
													 for(int sqlw=0; sqlw< Proyecto.RelationDB.size(); sqlw++){
														 if( ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(dominio+M_Clase) || ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(M_Clase+dominio)){
														 	if( !strwhere.contains(((RelationDB) Proyecto.getRelation(sqlw)).getRelation()) )	
															 	strwhere += ((RelationDB) Proyecto.getRelation(sqlw)).getRelation();
														 }
													 }
												 }
											 }
											 
											 //BUG END "AND"
											 if(countAND > 0)
											 	strwhere += "\"";
											 
											 //RECORRER ATRIBUTOS
											 String tablerow = "\t\t\t\t\t\techo \"<table><tr>";
											 String row = "\t\t\t\t\t\twhile ($row = pg_fetch_array($result)){\n\t\t\t\t\t\t\t echo \"<form action='' method='POST'>\";\n\t\t\t\t\t\t\t";
											 for(int a1=0; a1< Atributos.size(); a1++){
												tablerow += "<td>" + Atributos.get(a1) + "</td>";
												
												if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
													row += "echo \"<td>\".$row[\""+ ((String) Atributos.get(a1)).substring(((String) Atributos.get(a1)).indexOf(".")+1, ((String) Atributos.get(a1)).length()) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												}
												else{
													row += "echo \"<td>\".$row[\""+ Atributos.get(a1) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												}
											 	
												//dominio.atributo
												if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
													qs += Atributos.get(a1) + ", ";
												}
												else{
													qs += dominio + "." + Atributos.get(a1) + ", ";	
												}	
											 }
											 
											 row += "echo \"<input type='hidden' name='" + id_attribute + "' value=\".$row[\"" + id_attribute + "\"].\"></tr>\";\n\t\t\t\t\t}\n";
											 row += "\t\t\t\t\t\techo \"</tr></table><br><br><br><br><br>\";\n";
											 
											 //add info
											 for(int ar=0; ar < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Argumento.size(); ar++){
												 if (((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(ar)).getTipoArgumento().equals("D") ){
													 String name_tmp = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(ar)).getNombre();
													 row += "\t\t\t\t\t\techo\"" + name_tmp + " <input type='text' name='" + name_tmp + "' placeholder='" + name_tmp + "' required><br><br>  \";\n";
												 }
											 }
											 
											 row += "\t\t\t\t\t\techo \"<br><br><input type='submit' name='" + service + "' value='" + service + "'></form><br><br>\";\n\n ";
											 
											 
											 
											 tablerow += "</tr><tr>\";\n";
											 
											 String search = id_attribute + "=\".$_POST[\"" + id_attribute + "\"].\" ";
											 
											 if(countAND == 0)
												 strwhere = "";
											 else
												 strwhere = " AND " + strwhere;
											 												 
											 
											 qs = "\t\t\t\t\t\t$qs = " + qs.substring(0, qs.length()-2) + " FROM " + strfrom + " WHERE " 
													 				   + search + strwhere + "\";\n\t\t\t\t\t\t$result = pg_query($conn->DB, $qs);\n\n";
											 
											 String backpage = ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).Alias.getAlias() + "_" + ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).Alias.getAlias();
											 
											 backpage = backpage.replace(" ", "_");
											 
											 back = "\n\t\t\t\t\t\techo \"<br><br><br><br><a href='" + backpage  + ".php'>Volver</a>\";";
											 
											 page += qs + tablerow + row + back + "\n\t\t\t\t\t\t$conn->close();\n\t\t\t\t\t}\n";
											 
										 }										 
									 }
									 
								 	
								}
							
							}
						}
									
						  
						 
						 //
						 function = "";
						 System.out.println("\t" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre());
						 
						 function = ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getNombre() + "(";
						 for(int x4=0; x4 < ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.ElemAgrup.size(); x4++ ){	 
						 	//System.out.println( "\t\t\t" + ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia() );
							 ElemRefInferencia = ((ElemAgrupM) ((PPresentacionM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getPPresentacionM(x3)).HerPPresentacion.getElemAgrup(x4)).Alias.getRefInferencia();
							 
							 for(int s1=0; s1 < ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).Argumento.size(); s1++){								 
									
								 if( ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getId().equals(ElemRefInferencia) ){
										Alias = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).Alias.getAlias();
										Atributo = ((ArgumentoM) ((ServicioM) ((ClaseM) Proyecto.getClaseM(x1)).getServicioM(x2)).getArgumentoM(s1)).getNombre();
										
										//System.out.println("\t\t\t" + Atributo + "   " + Alias);
										function += Atributo + ", ";
									}								 
							 } 
						 }		
						 
						 page += "\t\t\t\t\t\telse if(isset($_POST['" + service + "'])){\n";
						 
						 page += "\n\t\t\t\t\t\t$" + Clase + " = new " + Clase + ";\n\t\t\t\t\t\t$" + Clase + "->connect();\n";
						 
						 function = service;
						 //hay que hacer bien el modelo con el orden!!
						 if(((String) Clase).equals("vendedor")){
							 function += "($motivo, $_POST['" + id_attribute + "'], 1, 1);";
						 }
						 //+ "($_POST['" + id_attribute + "']);";
						 
						 page += "\t\t\t\t\t\t$" + Clase + "->" + function + "\n";
						 page += "\t\t\t\t\t\t$" + Clase + "->close();\n\t\t\t\t\t" + back + "\n\t\t\t\t\t}\n\t\t\t\t\t?>\n";
						 
						 
						 
						 return page;
						 
					 }
					 
				 }			 
			 }
		 }
		
		if(!find){ //Clase.PPresentacion
			
			int derived = 0;
			int namederived = 0;
			AliasAtributos.clear();
			FormulaAtributos.clear();
			
			List ElemAcOfertadas = new ArrayList<String>();
			String Javascript = "\t\t\t\t\t<script language='Javascript'>\n";
			String input = "\t\t\t\t\t\techo \"<form method='POST' name='Form'>\";\n";
			
			
			
			for(int c1=0; c1 < Proyecto.Clase.size(); c1++){
				
				for(int c2=0; c2 < ((ClaseM) Proyecto.getClaseM(c1)).PPresentacion.size(); c2++){
					if( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getId().equals(RefInferencia) ){
						/*System.out.println("\t_Id: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getId());
						System.out.println("\tNombre: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getNombre());
						System.out.println("\n\tRef.NavegaOfertadaM: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefNavegaOfertadaM());
						System.out.println("\n\tRef.AccionesOfertaM: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefAccionesOfertaM());
						System.out.println("\n\tRef.ConjVisualM: " + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefConjVisualM());*/
						
						id_attribute = ((AtributoM) ((ClaseM) Proyecto.getClaseM(c1)).getAtributoM(0)).getNombre();
						
						for(int s=0; s < ((ClaseM) Proyecto.getClaseM(c1)).Servicios.size(); s++){
							if( !((ServicioM) ((ClaseM) Proyecto.getClaseM(c1)).getServicioM(s)).getDont().equals("1") ){
								//Javascript to change form
								Javascript += "\t\t\t\t\t\tfunction " + ((ServicioM) ((ClaseM) Proyecto.getClaseM(c1)).getServicioM(s)).getNombre() + "(){\n";
								Javascript += "\t\t\t\t\t\t\tdocument.Form.action='" + ((ClaseM) Proyecto.getClaseM(c1)).Alias.getAlias().replace(" ", "_") + "_" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(c1)).getServicioM(s)).Alias.getAlias().replace(" ", "_") + ".php'\n";
								Javascript += "\t\t\t\t\t\t\tdocument.Form.submit(); return true;}\n";
								
								//input submit to send with post
								input += "\t\t\t\t\t\techo \"<input type='submit' value='" + ((ServicioM) ((ClaseM) Proyecto.getClaseM(c1)).getServicioM(s)).getNombre() + "' name='buscar' onclick='return " + ((ServicioM) ((ClaseM) Proyecto.getClaseM(c1)).getServicioM(s)).getNombre() + "();'/>\";\n\t\t";
							}
						}
						
						input +="\t\t\t\t\t\techo \"<br><br>\";\n";
						Javascript += "\t\t\t\t\t</script>\n\n";
																		
						
						page += Javascript;						
						page += "\n\n\t\t\t\t\t<?php \n\t\t\t\t\t\t$conn = new postgresql;\n\t\t\t\t\t\t$conn->connect();\n";
						page += "\t\t\t\t\t\techo \"<div id='page-title'><span class='title'>" + ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).Alias.getAlias() + "</span></div>\";\n\n";
						
						
						for(int x1=0; x1 < Proyecto.Clase.size(); x1++){
							 for(int x2=0; x2 < ((ClaseM) Proyecto.getClaseM(x1)).ConjVisual.size(); x2++){
								 
								 dominio = ((ClaseM) Proyecto.getClaseM(x1)).getNombreEnBD();
								 strfrom = dominio;
								 strwhere = " WHERE ";
								 
								 if( ((PPresentacionM) ((ClaseM) Proyecto.getClaseM(c1)).getPPresentacionM(c2)).getRefConjVisualM().equals( ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getId() ) ){
								 	System.out.println("\tConjVisual: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getId());
									 System.out.println("\tNombre: " + ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getNombre());
									 
									 for(int x3=0; x3 < ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).ElemConjVisual.size(); x3++){
										 
										 //System.out.println("\t\t\t" + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Alias.getAlias() + " | " + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString());
										 
										 String attributeforcheck = check_attribute(derived, ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Alias.getRefInferencia(), Proyecto );
										 
										 System.out.println("CONJUNTOS ATRIBUTOS: " + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Alias.getAlias());
										 if(attributeforcheck.length() > 0){
											 AliasAtributos.add(((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Alias.getAlias());
											 
											 
											 if(attributeforcheck.contains(" as funcion") || ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString().contains(".")){
												 
												 if(attributeforcheck.contains(" as funcion")){
													 attributeforcheck = attributeforcheck.replace(" funcion", "");
													 attributeforcheck = attributeforcheck.substring(0, attributeforcheck.length()-1);
													 attributeforcheck += " " + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString();
												 }
												 else{
													 attributeforcheck = ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString();
												 }
											 }
											 Atributos.add( attributeforcheck );
											 System.out.println(attributeforcheck + " o " + ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString());
											 FormulaAtributos.add(((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString());
										 }
										 
										 String fk = "";
										 String M_Clase = "";
										 
										 M_Clase = ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getM_Clase();
										 
										 
										 if(attributeforcheck.contains("solicitud_venta."))
											 M_Clase = "solicitud_venta";
										 
										 if(M_Clase.length() == 0 && ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString().contains("."))
											 M_Clase = ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString().substring(0, ((ElemConjVisual) ((ConjVisualM) ((ClaseM) Proyecto.getClaseM(x1)).getConjVisualM(x2)).getElemConjVisual(x3)).Formula.getFormulaString().indexOf(".") );
										 
										 if ( M_Clase.length() > 0 && !strfrom.contains(M_Clase)){
											 strfrom += ", " + M_Clase;
											 System.out.println("strfrom: " + strfrom);
											 
											 if(countAND > 0)
												 strwhere += " AND ";
											 
											 countAND ++;
											 
											 for(int f1=0; f1< Proyecto.Clase.size(); f1++){
												 if( ((ClaseM) Proyecto.getClaseM(f1)).getNombreEnBD().equals(M_Clase) ){
													 
													 for(int f2=0; f2< ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.size(); f2++ ){
														 if( ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).contains(dominio) ){
															 fk = ((String) ((ClaseM) Proyecto.getClaseM(f1)).ForeignKeys.get(f2)).replace(" INT NULL", "");
														 }
													 }
													 
													 
													 
												 }
											 }
											 
											 
											 //IF THE RELATIONSHIP IS IN RELATIONDB
											 //GET THE ID EQUIAL IN FK OR ID 
											 for(int sqlw=0; sqlw< Proyecto.RelationDB.size(); sqlw++){
												 if( ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(dominio+M_Clase) || ((RelationDB) Proyecto.getRelation(sqlw)).getTables().equals(M_Clase+dominio)){
												 		strwhere += ((RelationDB) Proyecto.getRelation(sqlw)).getRelation();
												 		//System.out.println("strwhere: " + strwhere);
												 }
											 }
										 }
										 
									 }
									 
									 //RECORRER ATRIBUTOS
									 String tablerow = "\t\t\t\t\t\techo \"<table><tr><td></td>";
									 String row = "\t\t\t\t\t\twhile ($row = pg_fetch_array($result)){\n\t\t\t\t\t\t\t ";
									 
									 String wtablerow = "\t\t\t\t\t\techo \"<table><tr>";
									 String wrow = "\t\t\t\t\t\twhile ($row = pg_fetch_array($result)){\n\t\t\t\t\t\t\t ";
									 
									 for(int a1=0; a1< Atributos.size(); a1++){
										tablerow += "<td>" + ((String) AliasAtributos.get(a1)) + "</td>";
										wtablerow += "<td>" + ((String) AliasAtributos.get(a1)) + "</td>";
										
										
										if(a1==0){
											row += "echo \"<td><input type='radio' name='" + id_attribute + "' value=\".$row[\"" + id_attribute + "\"].\"></td>\";\n\t\t\t\t\t\t\t";
											wrow += "\n\t\t\t\t\t\t\t";
										}
										
										if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){											
											row += "echo \"<td>\".$row[\""+ ((String) Atributos.get(a1)).substring(((String) Atributos.get(a1)).indexOf(".")+1, ((String) Atributos.get(a1)).length()) + "\"].\"</td>\";\n\t\t\t\t\t\t\t";
											wrow += "echo \"<td>\".$row[\""+ ((String) Atributos.get(a1)).substring(((String) Atributos.get(a1)).indexOf(".")+1, ((String) Atributos.get(a1)).length()) + "\"].\"</td>\";\n\t\t\t\t\t\t\t";
											//row += "echo \"<td>\".$row[\""+ ((String) Atributos.get(a1)) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
										}
										else{
											if(((String) Atributos.get(a1)).contains(" as ") ){
												
												row += "echo \"<td>\".$row[\"funcion" + namederived + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												wrow += "echo \"<td>\".$row[\"funcion" + namederived + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
												namederived ++;
												derived --;
											}
											else{
												
												if(((String) Atributos.get(a1)).contains("precio_compra*")){
													row += "echo \"<td>\".$row[\"precio_venta\"].\"</td>\";\n \t\t\t\t\t\t\t";
													wrow += "echo \"<td>\".$row[\"precio_venta\"].\"</td>\";\n \t\t\t\t\t\t\t";
													
												}
												else
													row += "echo \"<td>\".$row[\""+ ((String) FormulaAtributos.get(a1)) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
													wrow += "echo \"<td>\".$row[\""+ ((String) FormulaAtributos.get(a1)) + "\"].\"</td>\";\n \t\t\t\t\t\t\t";
											}
										}
									 
										if(((String) Atributos.get(a1)).length() > 0){
											//dominio.atributo
											if( ((String) Atributos.get(a1)).indexOf(".") > 0 ){
												qs += Atributos.get(a1) + ", ";
											}
											else{
												qs += dominio + "." + Atributos.get(a1) + ", ";	
											}
										}
										
											
									 }
									 
									 row += "echo \"</tr>\";\n\t\t\t\t\t\t }\n \t\t\t\t\t\techo \"</tr></table></form>\";\n";
									 wrow += "echo \"</tr>\";\n\t\t\t\t\t\t }\n \t\t\t\t\t\techo \"</tr></table>\";\n";
									 tablerow += "</tr><tr>\";\n";
									 wtablerow += "</tr><tr>\";\n";
									 
									 if(countAND == 0)
										 strwhere = "";
									 
									 if(!qs.contains(id_attribute)){
										 qs += id_attribute + "  ";
									 }
									 
									 System.out.println(qs + " FROM " + strfrom);
									 //strwhere.replace("vehiculo.id_vehiculo=local.fk_local_1", "vehiculo.fk_local_1=local.id_local");
									 
									 
									 qs = "\t\t\t\t\t\t$qs = " + qs.substring(0, qs.length()-2) + " FROM " + strfrom 
											 				   + strwhere + "\";\n\t\t\t\t\t\t$result = pg_query($conn->DB, $qs);\n\n";
									 
									 
									 //WITHOUT CHECKBOX!!!
									 ((ClaseM) Proyecto.getClaseM(c1)).SQLPOB = qs + wtablerow + wrow;
									 
									 //TROUBLE SEQUENCE EVC SEARCH
									 create_evcsearch("p_evc" + ((ClaseM) Proyecto.getClaseM(c1)).getNombreEnBD(), Proyecto);
									 
									 //WITH CHECKBOX!!!
									 page += qs + input + tablerow + row + "\n\t\t\t\t\t\t$conn->close();\n\t\t\t\t\t?>\n";
									 
									 //System.out.println(page);
							 	}
							 }
						 }
					}
				}
			}
		}
		
		
		System.out.println("\n\n");
		
		
		return page;
	}
	
	public static String check_attribute(int derived, String ref, ProyectoM Proyecto){
		
		String nombre = "";
		for(int a=0; a< Proyecto.Clase.size(); a++){
			
			for(int i=0; i< ((ClaseM) Proyecto.getClaseM(a)).Atributos.size(); i++){
				if( ref.equals( ((AtributoM) ((ClaseM) Proyecto.getClaseM(a)).getAtributoM(i)).getId() ) ){
					if( !( ((AtributoM) ((ClaseM) Proyecto.getClaseM(a)).getAtributoM(i)).getTipo().equals("D") ) ){
						//nombre = Clase.getNombreEnBD() + "." + ((AtributoM) Clase.getAtributoM(i)).getNombre();
						nombre = ((AtributoM) ((ClaseM) Proyecto.getClaseM(a)).getAtributoM(i)).getNombre();
						return nombre;
						
					}
					
					if( ( ((AtributoM) ((ClaseM) Proyecto.getClaseM(a)).getAtributoM(i)).getTipo().equals("D") ) ){
						nombre = ((String) ((AtributoM) ((ClaseM) Proyecto.getClaseM(a)).getAtributoM(i)).getDerivaciones(0));		
						
						nombre = getDerivacionM(nombre, ((ClaseM) Proyecto.getClaseM(a))) + derived;
						
						derived ++;
					}
					
				}
				
			}
		}
		
		return nombre;
	}	
	
	public static String getDerivacionM(String nombre, ClaseM Clase){
		
		for(int i=0; i < Clase.Derivaciones.size(); i++){
			if( nombre.equals( ((DerivacionM) Clase.getDerivacionM(i)).getId() ) ){
				
				if ( ((DerivacionM) Clase.getDerivacionM(i)).Formula.getFormulaString().contains( "daysDifferenceFromDate" ) ){
					nombre = "current_date - fecha as funcion"; //current_date in postgresql return the date YY-MM-DD
					return nombre;
				}
				else{				
					return ((DerivacionM) Clase.getDerivacionM(i)).Formula.getFormulaString()+" as funcion";
				}
			}
		}
		
		return nombre;				
	}
	
	public static void create_search(ArgumentoM Argumento, ProyectoM Proyecto){
		
		String atributo = Argumento.getNombre();
		String page = "<?php include('lib/class.php'); ?>\n\n<html>\n<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1' />"
				+ "<link rel='stylesheet' href='css/style.css' type='text/css' media='screen' />\n<link rel='stylesheet' href='css/social-icons.css' type='text/css' media='screen' />"
				+ "\n<head>\n\n<script language='javascript'>\nfunction post_value(){\n"
				+ "\topener.document.f1." + Argumento.getNombre() + ".value = document.frm.c_name.value;\n\tself.close();}\n</script>"
				+ "\n\n<title></title>\n</head>\n\n<body>\n\n<form name='frm' method=post action=''><table border=0 cellpadding=0 cellspacing=0 width=250>"
				+ "<tr><td align='center'> Id: <input type='number' min=0 name='c_name' size='6' value='0' > <input type=button value='Submit' onclick='post_value();'></td></tr></table></form><br><br><br><br>";
		
		page +="\n\n<?php\n\t$conn= new postgresql;\n\t$conn->connect();\n\n";
		
		
		atributo = atributo.replace("p_this", "");
		atributo = atributo.replace("p_evc", "");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			
			if( ((ClaseM)Proyecto.getClaseM(i)).getNombreEnBD().equals(atributo) ){
				page += ((ClaseM)Proyecto.getClaseM(i)).SQLPOB.replace("</form>", "");
				
				
				
			}			
		}
	
		
		
		page += "\t$conn->close();\n?>\n\n</body>\n</html>";
		
		
		

		try {
			  File file = new File(Proyecto.getDirectorio()+"\\buscar" + Argumento.getNombre() + ".php");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(page);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
	
	
	}
	
	
	public static void create_evcsearch(String Argumento, ProyectoM Proyecto){
		
		String atributo = Argumento;
		String page = "<?php include('lib/class.php'); ?>\n\n<html>\n<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1' />"
				+ "<link rel='stylesheet' href='css/style.css' type='text/css' media='screen' />\n<link rel='stylesheet' href='css/social-icons.css' type='text/css' media='screen' />"
				+ "\n<head>\n\n<script language='javascript'>\nfunction post_value(){\n"
				+ "\topener.document.f1." + Argumento + ".value = document.frm.c_name.value;\n\tself.close();}\n</script>"
				+ "\n\n<title></title>\n</head>\n\n<body>\n\n<form name='frm' method=post action=''><table border=0 cellpadding=0 cellspacing=0 width=250>"
				+ "<tr><td align='center'> Id: <input type='number' min=0 name='c_name' size='6' value='0' > <input type=button value='Submit' onclick='post_value();'></td></tr></table></form><br><br><br><br>";
		
		page +="\n\n<?php\n\t$conn= new postgresql;\n\t$conn->connect();\n\n";
		
		
		atributo = atributo.replace("p_this", "");
		atributo = atributo.replace("p_evc", "");
		
		for(int i=0; i< Proyecto.Clase.size(); i++){
			
			if( ((ClaseM)Proyecto.getClaseM(i)).getNombreEnBD().equals(atributo) ){
				page += ((ClaseM)Proyecto.getClaseM(i)).SQLPOB.replace("</form>", "");
				
				
				
			}			
		}
	
		
		
		page += "\t$conn->close();\n?>\n\n</body>\n</html>";
		
		
		

		try {
			  File file = new File(Proyecto.getDirectorio()+"\\buscar" + Argumento + ".php");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(page);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
	
	
	}
	
	
}
