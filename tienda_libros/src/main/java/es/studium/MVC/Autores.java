package es.studium.MVC;

public class Autores
{
	private String autor;
	private int idAutor;
	
	public Autores(int idAutor, String nombreAutor)
	{
		nombreAutor = "";
		idAutor = 0;
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
