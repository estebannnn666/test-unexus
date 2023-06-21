package com.unexus.minegocio.repository;
import java.util.Collection;

import com.unexus.minegocio.vo.ClientesResponseVO;

/**
 * Interface repository clients.
 * @author Esteban G.
 *
 */
public interface IClienteRepository{

	/**
	 * Buscar clientes por cedula o nombre
	 * @param cedula
	 * @param nombre
	 * @return
	 */
	Collection<ClientesResponseVO> listaClientes(String cedula, String nombre);
}
