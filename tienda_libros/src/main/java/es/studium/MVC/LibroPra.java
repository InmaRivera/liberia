package es.studium.MVC;

public class LibroPra
{
	private int idLibro;
	private String tituloLi;
	private int cantidadLi;
	private double precioLi;
	private Autores idAutorFK = new Autores();
	private Editoriales idEditorialFK = new Editoriales();
	public LibroPra()
	{
		setIdLibro(0);
		tituloLi = "";
		cantidadLi = 0;
		precioLi = 0.0;
	}
	public LibroPra(int i, String t, int c, double p)
	{
		setIdLibro(i);
		tituloLi = t;
		cantidadLi = c;
		precioLi = p;
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
