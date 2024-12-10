package com.delivery.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.delivery.app.model.Pedidos;

public interface PedidoRepository extends JpaRepository<Pedidos, Long> {

	public List<Pedidos> findAll();
	
	public List<Pedidos>findAllByUsuarioNomeContainingIgnoreCase(@Param("nome") String nome);
}
