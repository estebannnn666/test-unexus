package com.unexus.minegocio.connector;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unexus.minegocio.entity.DireccionesEntity;

public interface IDireccionConector extends JpaRepository<DireccionesEntity, Long>{

}
