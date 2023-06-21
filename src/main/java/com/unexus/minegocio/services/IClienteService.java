package com.unexus.minegocio.services;
import java.util.Collection;

import com.unexus.minegocio.vo.ClienteRequestVO;
import com.unexus.minegocio.vo.ClientesResponseVO;

public interface IClienteService{
	
	/**
	 * Buscar clientes por cedula o nombre.
	 * @param cedula
	 * @param nombre
	 * @return
	 */
	Collection<ClientesResponseVO> listaClientes(String cedula, String nombre);
	
	/**
	 * Crear nuevo cliente.
	 * @param cliente
	 * @return
	 */
	void crearCliente(ClienteRequestVO cliente);
	
	/**
	 * Actualizar datos de cliente
	 * @param cliente
	 */
	void actualizarCliente(ClienteRequestVO cliente);
	
	/**
	 * Eliminar datos de cliente por codigo
	 * @param codigoCliente
	 */
	void eliminarCliente(Long codigoCliente);
}
