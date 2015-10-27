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
			int cont_page=0;//CONTADOR DE PAGINAS
			int cont_menu=0;
			String tabla = null;//NOMBRE DE LA TABLA
		
			//LECTURA
			while((line = br.readLine()) != null) {
				
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
				int x_view_atributo=line.indexOf("metawebdesign:ViewAttribute");
				int x_view_component_atributte=line.indexOf("getAttribute=");
				int x_view_component_type_presentation=line.indexOf("typePresentation=");
				int x_view_component_x=line.indexOf("positionHorizontal=");
				int x_view_component_y=line.indexOf("postionVertical=");
				
				//MENUS
				int x_view_menu=line.indexOf("metawebdesign:NavegationMenu");
				int x_view_menu_name=line.indexOf("name=");
				int x_view_menu_typeMenu=line.indexOf("typeMenu=");
				int x_view_link_crud=line.indexOf("metawebdesign:LinkViewCRUD");
				int x_view_link_crud_name=line.indexOf("name=");
				int x_view_link_crud_service=line.indexOf("service=");
				int x_view_link_crud_class=line.indexOf("fromClass=");
				
				
				//BUSQUEDA TABLA
				if(x_class != -1){
					//AGREGO LA TABLA IDENTIFICADA ANTERIORMENTE
					if(cont_tabla !=0){
						this.sql.addTabla(t);//AGREGO LA PRIMERA TABLA INDETIFICADA
					}
					
					//NUEVA TABLA
					t = new Tabla();
					r = new Roles();
			        String substr = line.substring(x_class+12, line.length());
			        int stop=substr.indexOf("\"");//CRITERIO DE PARADA PARA EXTRACCION DEL DATO
			        tabla=substr.substring(0, stop);
			        t.setNombre(tabla);//AGREGO EL NOMBRE A LA TABLA
			        
			        //ROLES -ACCESO A LOS SERVICIOS
			        if(x_roles_fcreate != -1){//CREATE
			        	String substr_fcreate=line.substring(x_roles_fcreate+16	, line.length());
			        	int stop_fcreate=substr_fcreate.indexOf("\"");
			        	r.setFCreate(substr_fcreate.substring(0, stop_fcreate));
			        	cont_tabla++;
			        }
			        
			        if(x_roles_fupdate != -1){//UPDATE
			        	String substr_fupdate=line.substring(x_roles_fupdate+16	, line.length());
			        	int stop_fupdate=substr_fupdate.indexOf("\"");
			        	r.setFUpdate(substr_fupdate.substring(0, stop_fupdate));
			        	cont_tabla++;
			        }
			        
			        if(x_roles_fdelete != -1){//DELETE
			        	String substr_fdelete=line.substring(x_roles_fdelete+16	, line.length());
			        	int stop_fdelete=substr_fdelete.indexOf("\"");
			        	r.setFDelete(substr_fdelete.substring(0, stop_fdelete));
			        	cont_tabla++;
			        }
			        
			        if(x_roles_findex != -1){//INDEX
			        	String substr_findex=line.substring(x_roles_findex+15	, line.length());
			        	int stop_findex=substr_findex.indexOf("\"");
			        	r.setFIndex(substr_findex.substring(0, stop_findex));
			        	cont_tabla++;
			        }
			        
			        if(x_roles_fview != -1){//VIEW
			        	String substr_fview=line.substring(x_roles_fview+14	, line.length());
			        	int stop_fview=substr_fview.indexOf("\"");
			        	r.setFView(substr_fview.substring(0, stop_fview));
			        	cont_tabla++;
			        }
			        
			        
			        t.setRoles(r); //AGREGO LA CONF DE LOS ROLES ENCONTRADOS
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
			        }
			        if(x_attribute_required !=-1){
			        	requiered=false;
			        }
			        
			        String atributo_nombre=substr_nombre.substring(0, stop_nombre);
			        String atributo_type_model=substr_type.substring(0, stop_type);
			        
			        String atributo_type=typeAdaptAtribute(atributo_type_model);//Adapta el dataType del modelo a uno aceptado por la BDD;
			        
			        Atributo a = new Atributo(atributo_nombre, pk, false, atributo_type, atributo_type_model, requiered);
			        t.addAtributo(a);//AGREGO ATRIBUTOS A LA TABLA 
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
			        View v = new View(atributo_nombre, atributo_formula, tabla, atributo_type);
			        this.sql.addView(v);
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
				     
				     ForeignKey f=new ForeignKey(relation_name, numClaseDestino, numAtributoDestino);
				     t.addForeignKey(f);
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
					p.setTitle(substr_title.substring(0, title_stop));
					p.setContentHTML(substr_html.substring(0,html_stop));
					p.setRol(substr_rol.substring(0, rol_stop));
					cont_page++;
				}
				
				//VIEW ATTRIBUTE - PAGE
				if(x_view_atributo!=-1){
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
					
				    
					int int_clase= Integer.parseInt(substr_clase.substring(0, clase_stop-1));
					int int_atributo= Integer.parseInt(substr_atributo.substring(0, atributo_stop));				    
					String typePresentation=substr_presentation.substring(0, presentation_stop);
				    String pos_x=substr_x.substring(0, stop_x);
				    int pos_y=Integer.parseInt(substr_y.substring(0, stop_y));
				   //+1 ya que el ID del modelo parte de cero y el de la BDD parte de uno
					ViewAttribute viewAttribute=new ViewAttribute(int_clase+1, int_atributo+1, typePresentation, pos_x, pos_y);
					p.setAtributo(viewAttribute);
				}
				
				//MENU
				
				if(x_view_menu != -1){
					if(cont_menu!=0){
						this.menus.add(m);	
					}
					m = new Menu();
					String substr_menu_name=line.substring(x_view_menu_name+6, line.length());
					String substr_typeMenu=line.substring( x_view_menu_typeMenu+10, line.length());
					
					int stop_menu_name=substr_menu_name.indexOf("\"");
					int stop_menu_type=substr_typeMenu.indexOf("\"");
					
					m.setName(substr_menu_name.substring(0, stop_menu_name));
					m.setTypeMenu(substr_typeMenu.substring(0, stop_menu_type));
					m.setIdView(cont_page+4);
				}
				
				//LINKS CRUD
				
				if(x_view_link_crud !=-1){
					String substr_crud_name=line.substring(x_view_link_crud_name+6, line.length());
					String substr_crud_service=line.substring( x_view_link_crud_service+9, line.length());
					String substr_crud_clase=line.substring( x_view_link_crud_class+20, line.length());
					
					int stop_crud_name=substr_crud_name.indexOf("\"");
					int stop_crud_service=substr_crud_service.indexOf("\"");
					int stop_crud_clase=substr_crud_clase.indexOf("\"");
					
					String linkc_name=substr_crud_name.substring(0, stop_crud_name);
					String linkc_service=substr_crud_service.substring(0, stop_crud_service);
					int linkc_clase=Integer.parseInt(substr_crud_clase.substring(0, stop_crud_clase));
					LinkCRUD link_crud = new LinkCRUD(linkc_name, linkc_service, linkc_clase);
					m.addLinkCRUD(link_crud);
				}
				
			}
			fr.close();
			this.sql.addTabla(t);//AGREGO LA ULTIMA TABLA DEL MODELO
			this.pages.add(p);
			System.out.println("Guardo Page "+cont_page);
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
	
}