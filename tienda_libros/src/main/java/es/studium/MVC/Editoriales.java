package es.studium.MVC;

public class Editoriales
{
	private int idEditorial;
	private String nombreEditorial;
	
	
	//constructor vacío
	public Editoriales(){
		this.idEditorial = 0;
		this.nombreEditorial = "";
	}
//	constructor por parámetros para mostrar en el select
	public Editoriales(int idEditorial, String nombreEditorial)
	{
		this.idEditorial = idEditorial;
		this.nombreEditorial = nombreEditorial;

	}
	public int getIdEditorial()
	{
		return idEditorial;
	}
	public void setIdEditorial(int idEditorial)
	{
		this.idEditorial = idEditorial;
	}
	public String getNombreEditorial()
	{
		return nombreEditorial;
	}
	public void setNombreEditorial(String nombreEditorial)
	{
		this.nombreEditorial = nombreEditorial;
	}

}
