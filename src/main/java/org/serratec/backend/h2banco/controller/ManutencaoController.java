package org.serratec.backend.h2banco.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.h2banco.model.Manutencao;
import org.serratec.backend.h2banco.model.Servico;
import org.serratec.backend.h2banco.repository.ManutencaoRepository;
import org.serratec.backend.h2banco.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

	@Autowired
	private ManutencaoRepository manutencaoRepository;
	
	@Autowired
	private ServicoRepository servicoServicoRepository;

	@GetMapping
	public ResponseEntity<List<Manutencao>> listar() {
		return ResponseEntity.ok(manutencaoRepository.findAll());
	}
	
	@GetMapping("/obs")
	public ResponseEntity<List<Manutencao>> listarPorObservacao(@RequestParam String observacao) {
		List<Manutencao> manutencoes = manutencaoRepository.buscarPorObservacao(observacao);
		return ResponseEntity.ok(manutencoes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Manutencao> buscar(@PathVariable Long id) {
		Optional<Manutencao> manutencaoOpt = manutencaoRepository.findById(id);
		if (manutencaoOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(manutencaoOpt.get());
	}

	@PostMapping
	public Manutencao inserir(@RequestBody Manutencao manutencao) {
		if (!manutencao.getServicos().isEmpty()) {
			List<Servico> servicos = new ArrayList<>();
			for (Servico servico: manutencao.getServicos()) {
				Optional<Servico> servicoOpt = servicoServicoRepository.findById(servico.getId());
				if (servicoOpt.isPresent()) {
					servicos.add(servicoOpt.get());
				}
			}
			manutencao.setServicos(servicos);
		}
		return manutencaoRepository.save(manutencao);
	}

}
