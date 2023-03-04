package es.studium.MVC;

public class Pedidos
{
	int idPedido;
	int idClienteFK;
	int cantidad;
	double precioLi;
	String fechaPedido;
	String fechaEnviado;
	boolean enviado;
	private static final int ENVIADO = 1;
	
	public Pedidos(int idPedido, String fechaPedido, int enviado, String fechaEnviado, int idClienteFK) 
	{
		super();
		this.idPedido = idPedido;
		this.idClienteFK = idClienteFK;
		this.fechaPedido = fechaPedido;
		this.fechaEnviado = fechaEnviado;
		this.enviado = (enviado==ENVIADO);
	}
//constructor   
	public Pedidos()
	{
		// TODO Auto-generated constructor stub
	}

	public int getIdLibro()
	{
		return idPedido;
	}

	public void setIdLibro(int idLibro)
	{
		this.idPedido = idLibro;
	}

	public int getIdClienteFK()
	{
		return idClienteFK;
	}

	public void setIdClienteFK(int idClienteFK)
	{
		this.idClienteFK = idClienteFK;
	}

	public int getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}

	public double getPrecioLi()
	{
		return precioLi;
	}

	public void setPrecioLi(double precioLi)
	{
		this.precioLi = precioLi;
	}

	public String getFechaPedido()
	{
		return fechaPedido;
	}

	public void setFechaPedido(String fechaPedido)
	{
		this.fechaPedido = fechaPedido;
	}

	public String getFechaEnviado()
	{
		return fechaEnviado;
	}

	public void setFechaEnviado(String fechaEnviado)
	{
		this.fechaEnviado = fechaEnviado;
	}

	public boolean isEnviado()
	{
		return enviado;
	}

	public void setEnviado(boolean enviado)
	{
		this.enviado = enviado;
	}

	public static int getEnviado()
	{
		return ENVIADO;
	}

}