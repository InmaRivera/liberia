package es.studium.MVC;

import java.util.Date;

public class Pedidos
{
	private int idPedido;
	private Date fechaPedido;
	private Date fechaEnviado;
	private int idClienteFK;
	boolean enviado;
	private static final int ENVIADO = 1;
	//constructor vacío 
		public Pedidos()
		{	
			idPedido = 0;
			idClienteFK = 0 ;
			enviado = false;
			
		}

	// Constructor con argumentos
	public Pedidos(int idPedido, Date fechaPedido, Date fechaEnviado, int idClienteFK, int enviado) {
		this.idPedido = idPedido;
		this.fechaPedido = fechaPedido;
		this.fechaEnviado = fechaEnviado;
		this.idClienteFK = idClienteFK;
		this.enviado = (enviado==ENVIADO);
	}
	
	public int getIdPedido()
	{
		return idPedido;
	}
	public void setIdPedido(int idPedido)
	{
		this.idPedido = idPedido;
	}
	public Date getFechaPedido()
	{
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido)
	{
		this.fechaPedido = fechaPedido;
	}
	public Date getFechaEnviado()
	{
		return fechaEnviado;
	}
	public void setFechaEnviado(Date fechaEnviado)
	{
		this.fechaEnviado = fechaEnviado;
	}
	public int getIdClienteFK()
	{
		return idClienteFK;
	}
	public void setIdClienteFK(int idClienteFK)
	{
		this.idClienteFK = idClienteFK;
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