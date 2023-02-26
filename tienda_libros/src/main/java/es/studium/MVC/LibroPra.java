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
	private void setIdLibro(int idLibro)
	{
		this.idLibro = idLibro;
		
	}
	public int getIdLibro()
	{
		return idLibro;
	}

	public String getTituloLi()
	{
		return tituloLi;
	}

	public int getCantidadLi()
	{
		return cantidadLi;
	}

	public double getPrecioLi()
	{
		return precioLi;
	}

	public Autores getIdAutorFK()
	{
		return idAutorFK;
	}

	public Editoriales getIdEditorialFK()
	{
		return idEditorialFK;
	}

	
}
