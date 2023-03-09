package es.studium.MVC;

public class Autores
{
	private String autor;
	private int idAutor;
	//constructor vacío para mostrar la información
	public Autores() {
		idAutor = 0;
		autor = "";
	}
	public Autores(int idAutores, String nombreAutor)
	{	
		idAutor = idAutores;
		autor = nombreAutor;
	
	}
	public String getAutor()
	{
		return autor;
	}
	public void setAutor(String autor)
	{
		this.autor = autor;
	}
	public int getIdAutor()
	{
		return idAutor;
	}
	public void setIdAutor(int idAutor)
	{
		this.idAutor = idAutor;
	}

	
}
