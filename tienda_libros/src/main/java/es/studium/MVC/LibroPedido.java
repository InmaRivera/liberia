package es.studium.MVC;

public class LibroPedido
{
	private int idLibro;
	private int cantidad;
	public LibroPedido() {
		idLibro = 0;
		cantidad = 0;
	}
	public LibroPedido(int idLibro, int cantidad)
	{
		this.idLibro = idLibro;
		this.cantidad = cantidad;
	}
	public int getIdLibro()
	{
		return idLibro;
	}
	public void setIdLibro(int idLibro)
	{
		this.idLibro = idLibro;
	}
	public int getCantidad()
	{
		return cantidad;
	}
	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}
	
	public String getAutor()
	{
		return Libreria_pra.getAutor(idLibro);
	}
	public String getTitulo()
	{
		return Libreria_pra.getTitulo(idLibro);
	}
	public double getPrecio()
	{
		return Libreria_pra.getPrecio(idLibro);
	}
}
