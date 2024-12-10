package com.delivery.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.delivery.app.model.Pedidos;
import com.delivery.app.repository.PedidoRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public ResponseEntity<List<Pedidos>> getAll() {
		return ResponseEntity.ok(pedidoRepository.findAll());
	}
	
	 @GetMapping("/usuario/{nome}")
		public ResponseEntity<List<Pedidos>> getBy(@PathVariable String nome){
		    return ResponseEntity.ok(pedidoRepository.findAllByUsuarioNomeContainingIgnoreCase(nome));
		}
	
	@PostMapping
	public ResponseEntity<Pedidos> post(@Valid @RequestBody Pedidos pedido){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pedidoRepository.save(pedido));
	}
	
	@PutMapping
	public ResponseEntity<Pedidos> put(@Valid @RequestBody Pedidos pedido){
		return pedidoRepository.findById(pedido.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(pedidoRepository.save(pedido)))
						.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	 @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        Optional<Pedidos> produto = pedidoRepository.findById(id);

	        if (produto.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

	        pedidoRepository.deleteById(id);
	    }
}
