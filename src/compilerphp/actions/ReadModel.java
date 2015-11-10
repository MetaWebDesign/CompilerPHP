package compilerphp.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadModel{
	protected SQL sql = new SQL(); 
	List <Page> pages = new ArrayList<Page>();
	List <Menu> menus = new ArrayList<Menu>();
	protected boolean error_status=false;
	protected String error_text="";
	
	//public static SQL loadXML(String path, String file) throws IOException
	public void loadXML(String path, String file) throws IOException
	{
			Tabla t=  new Tabla();
			Roles r= new Roles();
			
			Page p=new Page();
			
			Menu m=new Menu();
			
			FileReader fr = new FileReader(path+"/"+file);//LECTURA DEL ARCHIVO DEL MODELO
			BufferedReader br = new BufferedReader(fr);
			String line;//LINEA DE LECTURA DEL ARCHIVO
			int cont_tabla=0;//CONTADOR DE TABLAS
			int cont_attribute=0; //CONTADOR DE ATRIBUTOS
			int cont_page=0;//CONTADOR DE PAGINAS
			int cont_menu=0;
			String tabla = null;//NOMBRE DE LA TABLA
			int cont_pk=0; //CONTADOR PRIMARY KEY, CADA CLAS DEBE TENER UNA
			int cont_menu_principal=0; //CONTADOR DE MENU PRINCIPAL, SOLO PUEDE EXISTIR AL MENOS UNO
			//LECTURA
			while((line = br.readLine()) != null && !error_status) {
				
				int x_class=line.indexOf("class name="); //IDENTIFICA UNA CLASE
				int x_attribute=line.indexOf("<hasAttributes");//IDENTIFICA UN ATRIBUTO
				int x_attribute_not_derived=line.indexOf("metawebdesign:NotDerived");//IDENTIFICA UN ATRIBUTO NO DERIVADO
				int x_attribute_derived=line.indexOf("metawebdesign:Derived");//IDENTIFICA UN ATRIBUTO DERIVADO
				int x_attribute_name=line.indexOf("name=");//POSICION DEL NOMBRE DEL ATRIBUTO
				int x_attribute_type=line.indexOf("dataType=");//IDENTIFICA EL TIPO DE ATRIBUTO
				int x_attribute_pk=line.indexOf("PrimaryKey=");//IDENTIFICA LLAVE PRIMARIA
				int x_attribute_formula=line.indexOf("formula=");//IDENTIFICA FORMULA SQL DEL ATRIBUTO DERIVADO PARA CREAR UNA VISTA
				int x_attribute_required=line.indexOf("inTheForm=\"not_required\"");
				int x_relation=line.indexOf("hasRelationClass name=");//IDENTIFICA UNA RELACION
				int x_relation_name=line.indexOf("name=");//IDENTIFICA EL NOMBRE DE UNA RELACION PARA CREAR LLAVE FORANEA
				int x_relation_destination=line.indexOf("Attribute_Destination");
				//ROLES
				int x_roles_fcreate=line.indexOf("functionCreate=");
				int x_roles_fupdate=line.indexOf("functionUpdate=");
				int x_roles_fdelete=line.indexOf("functionDelete=");
				int x_roles_findex=line.indexOf("functionIndex=");
				int x_roles_fview=line.indexOf("functionView=");

				//VISTAS
				int x_view=line.indexOf("metawebdesign:Page");
				int x_view_title=line.indexOf("title=");
				int x_view_content_html=line.indexOf("Content_HTML=");
				int x_view_rol=line.indexOf("rolView=");
				
				//VISTAS - VIEWATTRIBUTE
				int x_view_atributo=line.indexOf("metawebdesign:ViewAttribute");
				int x_view_attributo_name=line.indexOf("name=");
				int x_view_component_atributte=line.indexOf("getAttribute=");
				int x_view_component_type_presentation=line.indexOf("typePresentation=");
				int x_view_component_x=line.indexOf("positionHorizontal=");
				int x_view_component_y=line.indexOf("postionVertical=");
				
				//MENUS
				int x_view_menu=line.indexOf("metawebdesign:NavegationMenu");
				int x_view_menu_name=line.indexOf("name=");
				int x_view_menu_typeMenu=line.indexOf("typeMenu=");
				
				//LINK CRUD
				int x_view_link_crud=line.indexOf("metawebdesign:LinkViewCRUD");
				int x_view_link_crud_name=line.indexOf("name=");
				int x_view_link_crud_service=line.indexOf("service=");
				int x_view_link_crud_class=line.indexOf("fromClass=");
				
				int x_view_link_dif=line.indexOf("CRUD");
				
				//LINK VIEW
				int x_view_link_view=line.indexOf("metawebdesign:LinkView");
				int x_view_link_view_name=line.indexOf("name=");
				int x_view_link_view_id_view=line.indexOf("linkView=");
				
				//RESTRICCIONES
				
				int x_restriccion=line.indexOf("hasConstraint");
				int x_restriccion_operador=line.indexOf("operator=");
				int x_restriccion_service=line.indexOf("service=");
				int x_restriccion_nombre=line.indexOf("name=");
				int x_restriccion_value=line.indexOf("value=");
				int x_restriccion_atributo=line.indexOf("Attribute=");
				int x_restriccion_mensaje=line.indexOf("mansajeError=");
				
				//BUSQUEDA TABLA
				if(x_class != -1){
					//AGREGO LA TABLA IDENTIFICADA ANTERIORMENTE
					if(cont_tabla !=0){
						if(cont_pk == 0 || cont_pk > 1){
							this.error_text="Error hay una clase sin nombre";
				        	this.error_status=true;
						}
						else{
							this.sql.addTabla(t);//AGREGO LA PRIMERA TABLA INDETIFICADA
							cont_pk=0;
							cont_attribute=0;
						}
					}
					
					//NUEVA TABLA
					t = new Tabla();
					r = new Roles();
			        String substr = line.substring(x_class+12, line.length());
			        int stop=substr.indexOf("\"");//CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        tabla=substr.substring(0, stop);
			        
			        //Valida q la tabla (Clase) posea un nombre
			        if(tabla.length()==0){
			        	this.error_text="Error en la clase "+t.getNombre()+", un atributo no posee nombre";
			        	this.error_status=true;
			        	
			        }
			        
			        if(tabla.indexOf(" ") != -1 && !this.error_status){
			        	this.error_text="Error en la clase "+t.getNombre()+", el nombre debe ser sin espacios";
			        	this.error_status=true;
			        }
			        
			        if(sql.getIDTabla(tabla)!=cont_tabla && !this.error_status){
			        	this.error_text="Error la tabla "+tabla+" se encuentra repetida";
			        	this.error_status=true;
			        }
			        
			        //ROLES -ACCESO A LOS SERVICIOS
			        if(x_roles_fcreate != -1){//CREATE
			        	String substr_fcreate=line.substring(x_roles_fcreate+16	, line.length());
			        	int stop_fcreate=substr_fcreate.indexOf("\"");
			        	r.setFCreate(substr_fcreate.substring(0, stop_fcreate));
			        }
			        
			        if(!this.error_status){
			        	t.setNombre(tabla);//AGREGO EL NOMBRE A LA TABLA
			        	if(x_roles_fupdate != -1){//UPDATE
			        		String substr_fupdate=line.substring(x_roles_fupdate+16	, line.length());
			        		int stop_fupdate=substr_fupdate.indexOf("\"");
			        		r.setFUpdate(substr_fupdate.substring(0, stop_fupdate));
			        	}
			        
			        	if(x_roles_fdelete != -1){//DELETE
			        		String substr_fdelete=line.substring(x_roles_fdelete+16	, line.length());
			        		int stop_fdelete=substr_fdelete.indexOf("\"");
			        		r.setFDelete(substr_fdelete.substring(0, stop_fdelete));
			        	}
			        	
			        	if(x_roles_findex != -1){//INDEX
			        		String substr_findex=line.substring(x_roles_findex+15	, line.length());
			        		int stop_findex=substr_findex.indexOf("\"");
			        		r.setFIndex(substr_findex.substring(0, stop_findex));
			        	}
			        
			        	if(x_roles_fview != -1){//VIEW
			        		String substr_fview=line.substring(x_roles_fview+14	, line.length());
			        		int stop_fview=substr_fview.indexOf("\"");
			        		r.setFView(substr_fview.substring(0, stop_fview));
			        	}
			        	t.setRoles(r); //AGREGO LA CONF DE LOS ROLES ENCONTRADOS
			        }
			        cont_tabla++;
			    }
				
				//BUSQUEDA ATRIBUTO NO DERIVADO
				if(x_attribute != -1 && x_attribute_not_derived != -1){
					
					//DATOS A EXTRAER DE LOS ATRIBUTOS
			        String substr_nombre = line.substring(x_attribute_name+6, line.length());
			        String substr_type=line.substring(x_attribute_type+10, line.length());
			        
			        //CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        int stop_nombre=substr_nombre.indexOf("\"");
			        int stop_type=substr_type.indexOf("\"");
			        
			        boolean pk=false;
			        boolean requiered=true;
			        if(x_attribute_pk != -1){
			        	pk=true;
			        	cont_pk++;
			        }
			        if(x_attribute_required !=-1){
			        	requiered=false;
			        }
			        
			        String atributo_nombre=substr_nombre.substring(0, stop_nombre);
			        String atributo_type_model=substr_type.substring(0, stop_type);
			        String atributo_type=typeAdaptAtribute(atributo_type_model);//Adapta el dataType del modelo a uno aceptado por la BDD;
			        
			        if(atributo_nombre.indexOf("xsi") != -1 || atributo_nombre.length()==0){
			        	this.error_text="Error en la clase "+t.getNombre()+", un atributo no posee nombre";
			        	this.error_status=true;
			        }
			        
			        if(atributo_type_model.indexOf("xsi") != -1 && !this.error_status){
			        	this.error_text="Error en la clase "+t.getNombre()+" el atributo "+atributo_nombre+" no posee Data Type";
			        	this.error_status=true;
			        }
			        
			        if(t.getIdAttribute(atributo_nombre) != cont_attribute){
			        	this.error_text="Error en la clase "+t.getNombre()+" existe un atributo duplicado";
			        	this.error_status=true;
			        }
			        
			        if(atributo_nombre.indexOf(" ") != -1 && !this.error_status){
			        	this.error_text="Error en la clase "+t.getNombre()+", el atributo "+atributo_nombre+", debe ser sin espacios";
			        	this.error_status=true;
			        }
			        
			        if(!this.error_status){
			        	Atributo a = new Atributo(atributo_nombre, pk, false, atributo_type, atributo_type_model, requiered);
			        	t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABL
			        	cont_attribute++;
			        }
				}
				
				//BUSQUEDA ATRIBUTO DERIVADO 
				/*
				 * Este atributo genera una vista en la Base de Datos
				 */
				if(x_attribute != -1 && x_attribute_derived != -1){
					//DATOS A EXTRAER DE LOS ATRIBUTOS
					
					String substr_nombre=line.substring(x_attribute_name+6,line.length());
					String substr_formula=line.substring(x_attribute_formula+9, line.length());
					String substr_type=line.substring(x_attribute_type+10, line.length());
					
					//CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        int stop_nombre=substr_nombre.indexOf("\"");
			        int stop_formula=substr_formula.indexOf("\"");
			        int stop_type=substr_type.indexOf("\"");
			        
			        String atributo_nombre=substr_nombre.substring(0, stop_nombre);
			        String atributo_type=substr_type.substring(0, stop_type);
			        String atributo_formula=substr_formula.substring(0, stop_formula);
			        
			        if(atributo_nombre.indexOf("xsi") != -1 || atributo_nombre.length()==0){
			        	this.error_text="Error en la clase "+t.getNombre()+", un atributo derivado no posee nombre";
			        	this.error_status=true;
			        }
			        			        
			        if(atributo_type.indexOf("xsi") != -1 && !this.error_status){
			        	this.error_text="Error en la clase "+t.getNombre()+" en el atributo derivado "+atributo_nombre+", no posee Data Type";
			        	this.error_status=true;
			        }
			        
			        if(atributo_formula.indexOf("xsi") != -1 && !this.error_status){
			        	this.error_text="Error en la clase "+t.getNombre()+", en el atributo derivado "+atributo_nombre+" no posee formula";
			        	this.error_status=true;
			        }
			        
			        
			        if(atributo_nombre.indexOf(" ") != -1 && !this.error_status){
			        	this.error_text="Error en la clase "+t.getNombre()+", el atributo "+atributo_nombre+" debe ser sin espacios";
			        	this.error_status=true;
			        }
			        
			        if(!this.error_status){
			        	//CHEQUEO DE FORMULA.
			        	if(check_formula(atributo_formula, atributo_nombre)){
			        		//View v = new View(atributo_nombre, atributo_formula, tabla, atributo_type);
			        		//this.sql.addView(v);
			        		Atributo a_derived = new Atributo(atributo_nombre, atributo_formula, tabla, atributo_type);
			        		t.addAtributo(a_derived);
			        		t.setVistaEDO(true);
			        	}
			        }
				}
				
				//BUSQUEDA RELACION
				if(x_relation != -1){
					//NOMBRE EN LA RELACION
				     String substr_nombre=line.substring(x_relation_name+6, line.length());
				     String substr_claseDestino=line.substring(x_relation_destination+32, line.length());
				     int start_r_atributo=substr_claseDestino.indexOf("@");
					 String substr_atributoDestino=line.substring(x_relation_destination+32+start_r_atributo+15, line.length());

				     
				     //CRITERIO DE PARADA PARA EXTRACCION DEL DATO
				     int stop_nombre=substr_nombre.indexOf("\"");
				     int stop_classDestino=substr_claseDestino.indexOf("/");
				     int stop_atributoDestino=substr_atributoDestino.indexOf("\"");
				     
				     //EXTRACCION DE DATOS DE LA RELACION
				     String relation_name=substr_nombre.substring(0, stop_nombre);
				     //+1 por que modelo parte de cero y en la bdd parte de uno el ID
				     int numClaseDestino= Integer.parseInt(substr_claseDestino.substring(0, stop_classDestino));
				     int numAtributoDestino= Integer.parseInt(substr_atributoDestino.substring(0, stop_atributoDestino));
				     
				     if(relation_name.length()==0){
				        	this.error_text="Error posee una relación sin nombre";
				        	this.error_status=true;
				     }
				     
				     if(relation_name.indexOf(" ") != -1 && !this.error_status){
				       	this.error_text="Error en la realación "+relation_name+", el nombre debe ser sin espacios";
				       	this.error_status=true;
				     }
				        
				     if(!this.error_status){
				    	 ForeignKey f=new ForeignKey(relation_name, numClaseDestino, numAtributoDestino);
				    	 t.addForeignKey(f);
				     }
				}
				
				//BUSQUEDA VISTA MODELADA O PAGE
				if(x_view != -1){
					if(cont_page!=0){
						this.pages.add(p);
					}
					
					p = new Page();
					
					String substr_title=line.substring(x_view_title+7, line.length());
					String substr_html=line.substring( x_view_content_html+14, line.length());
					String substr_rol=line.substring(x_view_rol+9, line.length());
					int title_stop=substr_title.indexOf("\"");
					int html_stop=substr_html.indexOf("\"");
					int rol_stop=substr_rol.indexOf("\"");
					
					String title=substr_title.substring(0, title_stop);
					String html=substr_html.substring(0,html_stop);
					String rol=substr_rol.substring(0, rol_stop);

			        if(title.indexOf("xsi") != -1 || title.length() == 0){
			        	this.error_text="Error existe una vista sin titulo";
			        	this.error_status=true;
			        }
					
			        if(html.indexOf("type") != -1 && !this.error_status){
			        	html=" ";
			        }
			        
			        if(rol.indexOf("xsi") != -1 && !this.error_status){
			        	this.error_text="Error, la Pagina "+title+" no posee un Rol View\nVuelva a asignar uno.";
			        	this.error_status=true;
			        }
			        

			        
			        if(!this.error_status){
			        	p.setTitle(title);
			        	p.setContentHTML(html);
			        	p.setRol(rol);
			        	cont_page++;
			        }
				}
				
				//VIEW ATTRIBUTE - PAGE
				if(x_view_atributo!=-1){
					
					 if(x_view_attributo_name == -1){
				        	this.error_text="Error en la Pagina "+p.getTitle()+" existe un ViewAttribute sin nombre";
				        	this.error_status=true;
					 }
					 
					 String substr_aname=line.substring(x_view_attributo_name+6, line.length());
					 int aname_stop=substr_aname.indexOf("\"");
					 String aname=substr_aname.substring(0, aname_stop);
					 
					if(aname.length() == 0 && !this.error_status){
			        	this.error_text="Error en la Pagina "+p.getTitle()+" existe un ViewAttribute sin nombre";
			        	this.error_status=true;
					}
					
					if (x_view_component_atributte == -1 && !this.error_status){
			        	this.error_text="Error en la Pagina "+p.getTitle()+" existe un ViewAttribute sin GetAttribute";
			        	this.error_status=true;
					}
					
					if (x_view_component_type_presentation == -1 && !this.error_status){
						this.error_text="Error en la Pagina "+p.getTitle()+" existe un ViewAttribute sin Type Presentation";
			        	this.error_status=true;
					}
					
					if(x_view_component_x == -1 && !this.error_status){
						this.error_text="Error en la Pagina "+p.getTitle()+" existe un ViewAttribute sin Position Horizontal";
			        	this.error_status=true;						
					}
					
					if(x_view_component_x == -1&& !this.error_status){
						this.error_text="Error en la Pagina "+p.getTitle()+" existe un ViewAttribute sin Position Vertical";
			        	this.error_status=true;						
					}
					if(!this.error_status){
						String substr_clase=line.substring(x_view_component_atributte+23, line.length());
						int start_atributo=substr_clase.indexOf("@");
						String substr_atributo=line.substring(x_view_component_atributte+23+start_atributo+15, line.length());
						String substr_presentation=line.substring(x_view_component_type_presentation+18, line.length());
						String substr_x=line.substring(x_view_component_x+20, line.length());
						String substr_y=line.substring(x_view_component_y+17, line.length());
						
						int clase_stop=substr_clase.indexOf("@");
						int atributo_stop=substr_atributo.indexOf("\"");
						int presentation_stop=substr_presentation.indexOf("\"");
						int stop_x=substr_x.indexOf("\"");
						int stop_y=substr_y.indexOf("\"");
				    
						String clase=substr_clase.substring(0, clase_stop-1);
						String atributo=substr_atributo.substring(0, atributo_stop);
						String typePresentation=substr_presentation.substring(0, presentation_stop);
						String x=substr_x.substring(0, stop_x);
						String y=substr_y.substring(0, stop_y);
					
						int int_clase= Integer.parseInt(clase);
						int int_atributo= Integer.parseInt(atributo);
						int pos_y=Integer.parseInt(y);
					
						ViewAttribute viewAttribute=new ViewAttribute(int_clase+1, int_atributo+1, typePresentation, x, pos_y);
						p.setAtributo(viewAttribute);
					}
				}
				
				//MENU
				if(x_view_menu != -1){
					String name_menu="null";
					if(x_view_menu_name == -1){
						this.error_text="Error, un menu no posee nombre";
			        	this.error_status=true;
					}
					
					if(!this.error_status && x_view_menu_name != -1){
						String substr_menu_name=line.substring(x_view_menu_name+6, line.length());
						int stop_menu_name=substr_menu_name.indexOf("\"");
						name_menu=substr_menu_name.substring(0, stop_menu_name);
					}
					
					if(name_menu.length() == 0){
						this.error_text="Error, un menu no posee nombre";
			        	this.error_status=true;
					}
					
					if(x_view_menu_typeMenu == -1 && !error_status){
						this.error_text="Error, el menu "+name_menu+" no posee typeMenu";
			        	this.error_status=true;
					}
					
					if(!this.error_status && x_view_menu_typeMenu != -1){
					
						if(cont_menu!=0){
							this.menus.add(m);
						}
						m = new Menu();
						String substr_typeMenu=line.substring( x_view_menu_typeMenu+10, line.length());
						int stop_menu_type=substr_typeMenu.indexOf("\"");
						String type_menu=substr_typeMenu.substring(0, stop_menu_type);

						m.setName(name_menu);
						m.setTypeMenu(type_menu);
						
						if(type_menu.equals("principal")){
							m.setIdView(-1);
							cont_menu_principal++;
							if(cont_menu_principal >=2){
								this.error_text="Error, solo puede tener UN menu type = \"Principal\"";
					        	this.error_status=true;
							}
						}
						else{
							m.setIdView(cont_page+4);
						}
						cont_menu++;
					}
				}
				
				//LINKS CRUD
				if(x_view_link_crud !=-1 && x_view_link_dif != -1){
					String linkc_name="null";
					
					if(x_view_link_crud_name == -1){
						this.error_text="Error, en el menu "+m.getName()+" existe un link sin nombre";
			        	this.error_status=true;
					}
					if(!this.error_status && x_view_link_crud_name != -1 ){
						String substr_crud_name=line.substring(x_view_link_crud_name+6, line.length());
						int stop_crud_name=substr_crud_name.indexOf("\"");
						linkc_name=substr_crud_name.substring(0, stop_crud_name);
					}
					if(x_view_link_crud_service == -1 && !error_status){
						this.error_text="Error, en el menu "+m.getName()+" el link "+linkc_name+" no posee referencia a un servicio";
			        	this.error_status=true;
					}
					
					if (x_view_link_crud_class == -1 && !error_status){
						this.error_text="Error, en el menu "+m.getName()+" el link "+linkc_name+" no posee refencia a una clase";
			        	this.error_status=true;						
					}
					if(!this.error_status){
						String substr_crud_service=line.substring( x_view_link_crud_service+9, line.length());
						String substr_crud_clase=line.substring( x_view_link_crud_class+20, line.length());
					
						int stop_crud_service=substr_crud_service.indexOf("\"");
						int stop_crud_clase=substr_crud_clase.indexOf("\"");
						
						String linkc_service=substr_crud_service.substring(0, stop_crud_service);
						int linkc_clase=Integer.parseInt(substr_crud_clase.substring(0, stop_crud_clase));
						LinkCRUD link_crud = new LinkCRUD(linkc_name, linkc_service, linkc_clase);
						m.addLinkCRUD(link_crud);
					}
				}
				
				//LINKS VIEW
				if(x_view_link_view != -1 && x_view_link_dif == -1){
					String linkv_name="null";
					if(x_view_link_view_name == -1){
						this.error_text="Error, en el menu "+m.getName()+" existe un link sin nombre";
			        	this.error_status=true;
					}
					if(!this.error_status && x_view_link_view_name != -1 && !error_status){
						String substr_link_name=line.substring(x_view_link_view_name+6, line.length());
						int stop_link_view_name=substr_link_name.indexOf("\"");
						linkv_name=substr_link_name.substring(0, stop_link_view_name);
					}
					if(x_view_link_view_id_view == -1 && !error_status){
						this.error_text="Error, en el menu "+m.getName()+" el atributo "+linkv_name+" no posee LinkView";
			        	this.error_status=true;						
					}

					if(!this.error_status){
						String substr_link_id_view=line.substring(x_view_link_view_id_view+19, line.length());
					
						int stop_link_view_id_view=substr_link_id_view.indexOf("\"");
						
						int linkv_view=Integer.parseInt(substr_link_id_view.substring(0, stop_link_view_id_view));

						LinkView link_view= new LinkView(linkv_name, linkv_view+5);
						m.addLinkView(link_view);
					}
				}
				
				
				//RESTRICCIONES
				if(x_restriccion != -1){
					String substr_operador=line.substring( x_restriccion_operador+10, line.length());
					String substr_service=line.substring( x_restriccion_service+9, line.length());
					String substr_nombre=line.substring( x_restriccion_nombre+6, line.length());
					String substr_value=line.substring( x_restriccion_value+7, line.length());
					String substr_atributo_clase=line.substring( x_restriccion_atributo+20, line.length());
					String substr_mensaje=line.substring(x_restriccion_mensaje+14, line.length());
					
					int stop_operador=substr_operador.indexOf("\"");
					int stop_service=substr_service.indexOf("\"");
					int stop_nombre=substr_nombre.indexOf("\"");
					int stop_value=substr_value.indexOf("\"");
					int stop_clase=substr_atributo_clase.indexOf("/");
					int stop_mensaje=substr_mensaje.indexOf("\"");
					
					
					String nombre=substr_nombre.substring(0, stop_nombre);
					String operador=substr_operador.substring(0, stop_operador);
					String servicio=substr_service.substring(0, stop_service);
					String valor=substr_value.substring(0, stop_value);
					String clase=substr_atributo_clase.substring(0, stop_clase);
					String msj=substr_mensaje.substring(0, stop_mensaje);
					
					String substr_atributo=line.substring( x_restriccion_atributo+20+clase.length()+16, line.length());
					
					int stop_atributo=substr_atributo.indexOf("\"");
					String atributo = substr_atributo.substring(0, stop_atributo);			
					
					Restriccion restriccion = new Restriccion(msj, nombre, operador, servicio, valor, Integer.parseInt(clase), Integer.parseInt(atributo));
					t.addRestriccion(restriccion);
					t.setRestriccionEDO(true);
				}
				
			}
			fr.close();
			this.sql.addTabla(t);//AGREGO LA ULTIMA TABLA DEL MODELO
			this.pages.add(p);
			this.menus.add(m);
	}
	
	//public static String typeAtributeVarChar(String DataType){
	public static String typeAdaptAtribute(String DataTypeModel){
		if(DataTypeModel.equals("varchar10")){
			return "varchar(10)";
		}
		if(DataTypeModel.equals("varchar30")){
			return "varchar(30)";
		}
		if(DataTypeModel.equals("varchar50")){
			return "varchar(50)";
		}
		if(DataTypeModel.equals("img")){
			return "varchar(100)";
		}
		if(DataTypeModel.equals("date_time")){
			return "datetime";
		}
		else{
			return DataTypeModel;
		}
	}
	
	public SQL getSQL(){
		return this.sql;
	}
	
	public List<Page> getPages(){
		return this.pages;
	}
	
	
	public List<Menu> getMenus(){
		return this.menus;
	}	
	
	public String getErrorText(){
		return this.error_text;
	}
	
	public boolean getErrorStatus(){
		return this.error_status;
	}
	
	public boolean check_formula(String formula, String nombre){
		boolean edo=true;
		int pk=formula.indexOf(" pk");
		//int
		if(pk == -1){
			edo=false;
			this.error_text="Error, en el atributo derivado "+nombre+", falta la refencia \"as pk\"\n\n"
							+"La sintaxis debiera ser:\n"
							+"SELECT tabla.foreingKey AS pk, atributo_derivado AS nombre_columna FROM tabla\n\n"
							+"tabla = clase del dato a extraer \n"
							+"foreingKey = llave foranea que une la clase del dato a extraer con la clase en donde se encuentra la llave foranea\n"
							+"atributo_derivado = Columna del dato, puede ser COUNT, AVG, SUM, etc";
							
			this.error_status=true;
		}
		return edo;
	}
}