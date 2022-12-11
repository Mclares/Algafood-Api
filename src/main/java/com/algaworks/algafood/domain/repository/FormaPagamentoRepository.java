package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

//	@Query("from Restaurante_FormaPagamento where restaurante.id = :restaurante and formaPagamento.id = :formaPagamento")
//	Optional<FormaPagamento> find(
//			@Param("restaurante") Long restauranteId, 
//			@Param("formaPagamento") Long formaPagamentoId);	
}