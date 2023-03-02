package es.studium.MVC;

public class LibroPra
{
	private int idLibro;
	private String tituloLi;
	private int cantidadLi;
	private double precioLi;
	private String autor;
	private Autores idAutorFK = new Autores();
	private Editoriales idEditorialFK = new Editoriales();
	
	public LibroPra() {
		this.idLibro = 0;
		this.tituloLi = "";
		this.cantidadLi = 0;
		this.precioLi = 0;
		this.autor = "";
	
	}
	//Crear parámetros
	public LibroPra(int idLibro, String tituloLi, String autor, int cantidadLi, double precioLi)
	{
		super();
		this.idLibro = idLibro;
		this.tituloLi = tituloLi;
		this.cantidadLi = cantidadLi;
		this.precioLi = precioLi;
		this.autor = autor;
	}
	public int getIdLibro()
	{
		return idLibro;
	}
	public void setIdLibro(int idLibro)
	{
		this.idLibro = idLibro;
	}
	public String getTituloLi()
	{
		return tituloLi;
	}
	public void setTituloLi(String tituloLi)
	{
		this.tituloLi = tituloLi;
	}
	public int getCantidadLi()
	{
		return cantidadLi;
	}
	public void setCantidadLi(int cantidadLi)
	{
		this.cantidadLi = cantidadLi;
	}
	public double getPrecioLi()
	{
		return precioLi;
	}
	public void setPrecioLi(double precioLi)
	{
		this.precioLi = precioLi;
	}
	public String getAutor()
	{
		return autor;
	}
	public void setAutor(String autor)
	{
		this.autor = autor;
	}

	public Autores getIdAutorFK()
	{
		return idAutorFK;
	}
	public void setIdAutorFK(Autores idAutorFK)
	{
		this.idAutorFK = idAutorFK;
	}
	public Editoriales getIdEditorialFK()
	{
		return idEditorialFK;
	}
	public void setIdEditorialFK(Editoriales idEditorialFK)
	{
		this.idEditorialFK = idEditorialFK;
	}
}