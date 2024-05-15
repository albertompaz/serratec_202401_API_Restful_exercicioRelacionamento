package org.serratec.backend.h2banco.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.h2banco.model.Veiculo;
import org.serratec.backend.h2banco.repository.VeiculoRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	@Operation(summary = "Lista todos os veiculos", description = "A resposta da requisição irá listar todos os dados do veiculo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					content = {@Content(schema = @Schema(implementation = Veiculo.class), mediaType = "apllication/json")}),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso")
	})
	public ResponseEntity<List<Veiculo>> listar() {
		return ResponseEntity.ok(veiculoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um veiculo", description = "A resposta é um objeto com os dados do veiculo: id, placa, marca...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna um veiculo"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
	})
	public ResponseEntity<Veiculo> buscar(@PathVariable Long id) {
		Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);
		if (veiculoOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculoOpt.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um veiculo", description = "A resposta é um objeto com os dados cadastrados do veiculo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Veiculo adicionado"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
	})
	public Veiculo inserir(@Valid @RequestBody Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Veiculo", description = "Atualiza dados de um veiculo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna um veiculo"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
	})
	public ResponseEntity<Veiculo> alterar(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculo.setId(id);
		veiculo = veiculoRepository.save(veiculo);
		return ResponseEntity.ok(veiculo);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Remover Veiculo", description = "Remove um veiculo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna um veiculo"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação")
	})
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
