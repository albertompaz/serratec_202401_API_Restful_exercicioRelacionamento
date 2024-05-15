package org.serratec.backend.h2banco.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador unico do veiculo")
	private Long id;

	@NotBlank(message = "Preencha a placa")
	@Size(max = 7)
	@Column(nullable = false, length = 7)
	@Schema(description = "Placa do veiculo")
	private String placa;

	@NotBlank(message = "Preencha a marca")
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	@Schema(description = "Nome da fabricante do veiculo")
	private String marca;

	@NotBlank(message = "Preencha o modelo")
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	@Schema(description = "Modelo do veiculo")
	private String modelo;

	@Embedded
	@Schema(description = "Caracteristicas gerais do veiculo")
	private Caracteristica caracteristica;

	@OneToOne
	@JoinColumn(name = "id_proprietario")
	@JsonManagedReference
	@Schema(description = "Proprietario do veiculo")
	private Proprietario proprietario;

	@OneToMany(mappedBy = "veiculo")
	@JsonManagedReference
	@Schema(description = "Manutenções realizadas pelo veiculo")
	private List<Manutencao> manutencoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public List<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(List<Manutencao> manutencoes) {
		this.manutencoes = manutencoes;
	}

}
