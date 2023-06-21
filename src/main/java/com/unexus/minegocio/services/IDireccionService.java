package com.unexus.minegocio.services;
import java.util.Collection;

import com.unexus.minegocio.entity.DireccionesEntity;
import com.unexus.minegocio.vo.DireccionResponseVO;

public interface IDireccionService{
	
	/**
	 * Buscar direcciones por cliente
	 * @param codigoCliente
	 * @return
	 */
	Collection<DireccionResponseVO> listaDirecciones(Long codigoCliente);
	
	/**
	 * Crear nueva direccion.
	 * @param direccion
	 * @return
	 */
	void crearNuevaDireccion(DireccionesEntity direccion);
}
