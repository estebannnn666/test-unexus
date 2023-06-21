package com.unexus.minegocio.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unexus.minegocio.services.IClienteService;
import com.unexus.minegocio.vo.BaseResponseVo;
import com.unexus.minegocio.vo.ClienteRequestVO;
import com.unexus.minegocio.vo.ClientesResponseVO;
import com.unexus.minegocio.vo.FiltrosBusquedaVO;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Class to controller clients services.
 *
 * @version 1.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/minegocio/admin/clientes")
@Lazy
@Slf4j
public class ClientesController{
    
	@Lazy
    @Autowired
    @Getter
	private IClienteService clienteService;

    /**
     * Web services for get clients list.
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
    @PostMapping(path = "/listaClientes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo>  listaClientes(@RequestBody FiltrosBusquedaVO request) throws Exception {
        log.info("Metodo listaClientes::: {}");
        try {
        	Collection<ClientesResponseVO> scopeItems = clienteService.listaClientes(request.getNumeroIdentificacion(), request.getNombres());
        	BaseResponseVo<Collection<ClientesResponseVO>> response = new BaseResponseVo<>();
        	response.setMessage("La busqueda se realizo correctamente");
        	response.setData(scopeItems);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	return ResponseEntity.ok(BaseResponseVo.builder().message(e.getMessage()).build());
        }
    }  
	
	/**
     * Web services for create new client.
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
    @PostMapping(path = "/crearCliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo>  crearCliente(@RequestBody ClienteRequestVO request) throws Exception {
        log.info("Metodo crearCliente::: {}");
        try {
        	clienteService.crearCliente(request);
        	BaseResponseVo<ClienteRequestVO> response = new BaseResponseVo<>();
        	response.setMessage("EL cliente se creo correctamente");
        	response.setData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	return ResponseEntity.ok(BaseResponseVo.builder().message(e.getMessage()).build());
        }
    } 
    
	/**
     * Web services for update client.
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
    @PostMapping(path = "/actualizarCliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo>  actualizarCliente(@RequestBody ClienteRequestVO request) throws Exception {
        log.info("Metodo actualizarCliente::: {}");
        try {
        	clienteService.actualizarCliente(request);
        	BaseResponseVo<ClienteRequestVO> response = new BaseResponseVo<>();
        	response.setMessage("EL cliente se actualizo correctamente");
        	response.setData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	return ResponseEntity.ok(BaseResponseVo.builder().message(e.getMessage()).build());
        }
    }
	
	/**
     * Web services for delete client.
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
    @PostMapping(path = "/eliminarCliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo> eliminarCliente(@RequestBody ClienteRequestVO request) throws Exception {
        log.info("Metodo eliminarCliente::: {}");
        try {
        	clienteService.eliminarCliente(request.getIdCliente());
        	BaseResponseVo<ClienteRequestVO> response = new BaseResponseVo<>();
        	response.setMessage("El cliente se elimino correctamente");
        	response.setData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	return ResponseEntity.ok(BaseResponseVo.builder().message(e.getMessage()).build());
        }
    }
}
