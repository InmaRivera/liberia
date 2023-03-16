package es.studium.MVC;

public class LibroPra
{
	private int idLibro;
	private String tituloLi;
	private int cantidadLi;
	private double precioLi;
	private String autor;
	private String editorial;
	private String nombreEditorial;
	private Autores idAutorFK = new Autores(idLibro, autor);
	private Editoriales idEditorialFK = new Editoriales(idLibro, autor);
//	Constructor vacío
	public LibroPra() {
		idLibro = 0;
		tituloLi ="";
		cantidadLi = 0;
		precioLi = 0;
		autor = "";
		editorial = "";
		nombreEditorial = "";
//		idAutorFK = 0;	
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

	public String getEditorial()
	{
		return editorial;
	}

	public void setEditorial(String editorial)
	{
		this.editorial = editorial;
	}

	public String getNombreEditorial()
	{
		return nombreEditorial;
	}

	public void setNombreEditorial(String nombreEditorial)
	{
		this.nombreEditorial = nombreEditorial;
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