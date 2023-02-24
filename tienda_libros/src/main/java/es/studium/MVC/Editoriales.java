package es.studium.MVC;

public class Editoriales
{
	private int idEditorial;
	private String nombreEditorial;
	public Editoriales()
	{
		idEditorial = 0;
		nombreEditorial ="";

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
