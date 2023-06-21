package com.unexus.minegocio.services.impl;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unexus.minegocio.connector.IDireccionConector;
import com.unexus.minegocio.entity.DireccionesEntity;
import com.unexus.minegocio.exception.MiNegocioException;
import com.unexus.minegocio.repository.IDireccionRepository;
import com.unexus.minegocio.services.IDireccionService;
import com.unexus.minegocio.vo.DireccionResponseVO;

@Service
public class DireccionService implements IDireccionService{

	@Autowired
    IDireccionConector direccionConector;
	
	@Autowired
	IDireccionRepository direccionRepository;
	
	
	/**
     * {@inheritDoc}
     */
    @Override
	public Collection<DireccionResponseVO> listaDirecciones(Long codigoCliente){
    	return this.direccionRepository.listaDirecciones(codigoCliente);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void crearNuevaDireccion(DireccionesEntity direccion) {
		// Validar que no exista una direccion como matriz para el cliente
		if(direccion.getEsDireccionMatriz()) {
			Collection<DireccionResponseVO>  listaDirecciones = this.direccionRepository.listaDirecciones(direccion.getIdCliente());
			DireccionResponseVO direccionMatrizEntity = listaDirecciones.stream().filter(direccionMatriz -> direccionMatriz.getEsDireccionMatriz()).findAny().orElse(null);
			if(direccionMatrizEntity == null) {
				this.direccionConector.save(direccion);
			}else {
				throw new MiNegocioException("No se puede crear una direccion matriz porque ya existe una para el cliente con id "+direccion.getIdCliente()+".");
			}
		}else {
			this.direccionConector.save(direccion);
		}	
	}
}
