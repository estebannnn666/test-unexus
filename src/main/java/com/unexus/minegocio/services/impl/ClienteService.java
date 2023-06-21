package com.unexus.minegocio.services.impl;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unexus.minegocio.connector.IClienteConector;
import com.unexus.minegocio.connector.IDireccionConector;
import com.unexus.minegocio.entity.ClientesEntity;
import com.unexus.minegocio.entity.DireccionesEntity;
import com.unexus.minegocio.exception.MiNegocioException;
import com.unexus.minegocio.repository.IClienteRepository;
import com.unexus.minegocio.repository.IDireccionRepository;
import com.unexus.minegocio.services.IClienteService;
import com.unexus.minegocio.vo.ClienteRequestVO;
import com.unexus.minegocio.vo.ClientesResponseVO;

@Service
public class ClienteService implements IClienteService{

	@Autowired
    IClienteConector clienteConector;
	
	@Autowired
    IDireccionConector direccionConector;
	
	@Autowired
	IClienteRepository clienteRepository;
	
	@Autowired
	IDireccionRepository direccionRepository;
	
	
	/**
     * {@inheritDoc}
     */
    @Override
	public Collection<ClientesResponseVO> listaClientes(String cedula, String nombre){
    	return this.clienteRepository.listaClientes(cedula, nombre);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void crearCliente(ClienteRequestVO cliente) {
		// Validar si ya existe un cliente con el numero de cedula 
		Collection<ClientesResponseVO> busquedaCliente = this.clienteRepository.listaClientes(cliente.getNumeroIdentificacion(), null);
		if(busquedaCliente.isEmpty()) {
			// Guardamos el cliente
			ClientesEntity clientesEntity = new ClientesEntity();
			clientesEntity.setTipoIdentificacion(cliente.getTipoIdentificacion());
			clientesEntity.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
			clientesEntity.setNombreCliente(cliente.getNombres());
			clientesEntity.setCorreo(cliente.getCorreo());
			clientesEntity.setCelular(cliente.getTelefono());
			this.clienteConector.save(clientesEntity);
			// Guardamos la direccion
			DireccionesEntity direccionesEntity = new DireccionesEntity();
			direccionesEntity.setIdCliente(clientesEntity.getIdCliente());
			direccionesEntity.setProvincia(cliente.getProvincia());
			direccionesEntity.setCiudad(cliente.getCiudad());
			direccionesEntity.setDireccion(cliente.getDireccion());
			direccionesEntity.setEsDireccionMatriz(Boolean.TRUE);			
			this.direccionConector.save(direccionesEntity);
		}else {
			throw new MiNegocioException("El cliente con numero de identificacion "+cliente.getNumeroIdentificacion()+" ya existe.");
		}
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void actualizarCliente(ClienteRequestVO cliente) {
		// Consultar datos antiguos
		ClientesEntity datosCliente = clienteConector.findById(cliente.getIdCliente()).get();
		// Validar si ya existe un cliente con el numero de cedula que sea diferente al codigo del cliente actualizado
		Collection<ClientesResponseVO> busquedaCliente = this.clienteRepository.listaClientes(cliente.getNumeroIdentificacion(), null);
		Collection<ClientesResponseVO> clientesConCedulaIgual = busquedaCliente.stream().filter(clienteActual -> clienteActual.getIdCliente().intValue() != datosCliente.getIdCliente().intValue()).collect(Collectors.toList());
		if(clientesConCedulaIgual.isEmpty()) {
			// Guardamos el cliente
			ClientesEntity clientesEntity = new ClientesEntity();
			clientesEntity.setIdCliente(cliente.getIdCliente());
			clientesEntity.setTipoIdentificacion(cliente.getTipoIdentificacion());
			clientesEntity.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
			clientesEntity.setNombreCliente(cliente.getNombres());
			clientesEntity.setCorreo(cliente.getCorreo());
			clientesEntity.setCelular(cliente.getTelefono());
			this.clienteConector.save(clientesEntity);
			
		}else {
			throw new MiNegocioException("Los datos del cliente no se puede actualizar porque existe otro cliente con numero de identificacion "+cliente.getNumeroIdentificacion()+".");
		}
	}
	
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void eliminarCliente(Long codigoCliente) {
		// Consultar datos cliente
		Optional<ClientesEntity> datosCliente = clienteConector.findById(codigoCliente);
		if(datosCliente.isEmpty()) {
			throw new MiNegocioException("El cliente no se puede eliminar porque no existe un cliente con el id "+codigoCliente+".");
		}else {
			// Consultar direcciones para eliminar del cliente
			Collection<DireccionesEntity> listaDirecciones = direccionRepository.listaDireccionesEliminar(codigoCliente);
			if(!listaDirecciones.isEmpty()) {
				listaDirecciones.stream().forEach(direccion -> {
					this.direccionConector.delete(direccion);
				});
			}
			this.clienteConector.delete(datosCliente.get());
		}
	}
}
