package com.unexus.minegocio.connector;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unexus.minegocio.entity.ClientesEntity;

public interface IClienteConector extends JpaRepository<ClientesEntity, Long>{

}
