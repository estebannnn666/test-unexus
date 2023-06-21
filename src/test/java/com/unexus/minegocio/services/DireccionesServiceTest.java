package com.unexus.minegocio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.unexus.minegocio.connector.IClienteConector;
import com.unexus.minegocio.entity.ClientesEntity;
import com.unexus.minegocio.repository.impl.ClienteRepository;
import com.unexus.minegocio.services.impl.ClienteService;
import com.unexus.minegocio.vo.ClienteRequestVO;
import com.unexus.minegocio.vo.ClientesResponseVO;

@RunWith(MockitoJUnitRunner.class)
public class DireccionesServiceTest {

	@InjectMocks
	ClienteService clienteService;
	
	@InjectMocks
	IClienteConector clienteConector;

	@Mock
	ClienteRepository clienteRepository;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getListaClientesTest()
	{
		List<ClientesResponseVO> listaClientes = new ArrayList<>();
		ClientesResponseVO cliente = new ClientesResponseVO();
	    cliente.setTipoIdentificacion("CED");
	    cliente.setNumeroIdentificacion("1005844632");
	    cliente.setNombres("RICARDO ALEX ANDRADE MARIN");
	    cliente.setCorreo("ricardo@hotmail.com");
	    cliente.setTelefono("0998542177");
	    cliente.setProvincia("COTOPAXI");
	    cliente.setCiudad("AMBATO");
	    cliente.setDireccion("CALLE GUARANDA Y LOMAS");
	    listaClientes.add(cliente);
		when(clienteRepository.listaClientes(null, null)).thenReturn(listaClientes);
		//test
		Collection<ClientesResponseVO> empList = clienteService.listaClientes(null, null);
		assertEquals(1, empList.size());
		verify(clienteRepository, times(1)).listaClientes(null, null);
	}
	
	@Test
	public void createClienteTest() {
		ClienteRequestVO cliente = new ClienteRequestVO();
	    cliente.setTipoIdentificacion("CED");
	    cliente.setNumeroIdentificacion("1005844632");
	    cliente.setNombres("RICARDO ALEX ANDRADE MARIN");
	    cliente.setCorreo("ricardo@hotmail.com");
	    cliente.setTelefono("0998542177");
	    cliente.setProvincia("COTOPAXI");
	    cliente.setCiudad("AMBATO");
	    cliente.setDireccion("CALLE GUARANDA Y LOMAS");
	    clienteService.crearCliente(cliente);
	    
	    ClientesEntity clientesEntity = new ClientesEntity();
	    clientesEntity.setTipoIdentificacion("CED");
	    clientesEntity.setNumeroIdentificacion("1005844632");
	    clientesEntity.setNombreCliente("RICARDO ALEX ANDRADE MARIN");
	    clientesEntity.setCorreo("ricardo@hotmail.com");
	    clientesEntity.setCelular("0998542177");
		verify(clienteConector, times(1)).save(clientesEntity);
	}
	
	@Test
	public void actualizarClienteTest() {
		ClienteRequestVO cliente = new ClienteRequestVO();
	    cliente.setTipoIdentificacion("CED");
	    cliente.setNumeroIdentificacion("1005844632");
	    cliente.setNombres("RICARDO ALEX ANDRADE MARIN");
	    cliente.setCorreo("ricardo@hotmail.com");
	    cliente.setTelefono("0997212132");
	    clienteService.actualizarCliente(cliente);
	    
	    ClientesEntity clientesEntity = new ClientesEntity();
	    clientesEntity.setTipoIdentificacion("CED");
	    clientesEntity.setNumeroIdentificacion("1005844632");
	    clientesEntity.setNombreCliente("RICARDO ALEX ANDRADE MARIN");
	    clientesEntity.setCorreo("ricardo@hotmail.com");
	    clientesEntity.setCelular("0997212132");
		verify(clienteConector, times(1)).save(clientesEntity);
	}
	
	@Test
	public void eliminarClienteTest() {
	    clienteService.eliminarCliente(1L);
		verify(clienteConector, times(1)).findById(1L);
	}
}
