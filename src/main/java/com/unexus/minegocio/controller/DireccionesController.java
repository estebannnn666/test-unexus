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

import com.unexus.minegocio.entity.DireccionesEntity;
import com.unexus.minegocio.services.IDireccionService;
import com.unexus.minegocio.vo.BaseResponseVo;
import com.unexus.minegocio.vo.DireccionResponseVO;
import com.unexus.minegocio.vo.FiltrosBusquedaVO;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Class to controller address services.
 *
 * @version 1.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/minegocio/admin/direcciones")
@Lazy
@Slf4j
public class DireccionesController{
    
	@Lazy
    @Autowired
    @Getter
	private IDireccionService direccionService;

    /**
     * Web services for get list address.
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
    @PostMapping(path = "/listaDireccionesCliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo>  listaDireccionesCliente(@RequestBody FiltrosBusquedaVO request){
        log.info("Metodo listaDireccionesCliente::: {}");
        try {
        	Collection<DireccionResponseVO> listaDireccionesCliente = direccionService.listaDirecciones(request.getCodigoCliente());
            BaseResponseVo<Collection<DireccionResponseVO>> response = new BaseResponseVo<>();
        	response.setMessage("La busqueda se realizo correctamente");
        	response.setData(listaDireccionesCliente);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	return ResponseEntity.ok(BaseResponseVo.builder().message(e.getMessage()).build());
        }
    } 
    
    /**
     * Web services for create new address.
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
    @PostMapping(path = "/guardarDireccion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo>  guardarDireccion(@RequestBody DireccionesEntity request) throws Exception {
        log.info("Metodo guardarDireccion::: {}");
        try {
        	direccionService.crearNuevaDireccion(request);
        	BaseResponseVo<DireccionesEntity> response = new BaseResponseVo<>();
        	response.setMessage("La direccion se creo correctamente");
        	response.setData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	return ResponseEntity.ok(BaseResponseVo.builder().message(e.getMessage()).build());
        }
    }
    
    
}
