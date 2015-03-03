//WHERE --- AND --- WITH TABLEA.FOREIGN_KEY AND TABLEB.ID
package clases;

public class RelationDB {
	private String tables; //tableAtableB
	private String relation; // tableA.fk = tableB=id | tableA.id = tableB.fk
	private String tableFK;
	private String InsB; //A generate in the first InsClass
	
	public RelationDB(String tables, String relation, String tableFK){
		this.setTables(tables);
		this.setRelation(relation);
		this.setTableFK(tableFK);
	}

	public String getTables() {
		return tables;
	}

	public void setTables(String tables) {
		this.tables = tables;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getTableFK() {
		return tableFK;
	}

	public void setTableFK(String tableFK) {
		this.tableFK = tableFK;
	}

	public String getIns() {
		return InsB;
	}

	public void setIns(String insB) {
		this.InsB = insB;
	}
}
