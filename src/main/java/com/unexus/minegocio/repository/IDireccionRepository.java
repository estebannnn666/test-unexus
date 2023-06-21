package com.unexus.minegocio.repository;
import java.util.Collection;

import com.unexus.minegocio.entity.DireccionesEntity;
import com.unexus.minegocio.vo.DireccionResponseVO;

/**
 * Interface repository address.
 * @author Esteban G.
 *
 */
public interface IDireccionRepository{

	/**
	 * Buscar direcciones por cliente
	 * @param codigoCliente
	 * @return
	 */
	Collection<DireccionResponseVO> listaDirecciones(Long codigoCliente);
	
	/**
	 * Lista de direcciones por cliente para eliminar
	 * @param codigoCliente
	 * @return
	 */
	Collection<DireccionesEntity> listaDireccionesEliminar(Long codigoCliente);
}
