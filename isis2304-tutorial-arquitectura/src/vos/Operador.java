package vos;

import java.util.ArrayList;

public abstract class Operador 
{
	protected Integer id;
	protected String login, nombre, correo, password, cuentaBancaria, documento;
	protected Cliente clienteEquivalente;
	protected ArrayList<Habitacion> habitaciones;
	protected Operador(Integer id, String login, String nombre, String correo, String password, String cuentaBancaria,
			String documento, Cliente clienteEquivalente) 
	{
		super();
		this.id = id;
		this.login = login;
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.cuentaBancaria = cuentaBancaria;
		this.documento = documento;
		this.clienteEquivalente = clienteEquivalente;
		habitaciones = new ArrayList<Habitacion>();
	}
}
