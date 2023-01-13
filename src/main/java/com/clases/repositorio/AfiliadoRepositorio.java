package com.clases.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clases.modelo.Afiliado;
@Repository
public interface AfiliadoRepositorio extends JpaRepository<Afiliado, Long> {
	
	@Query("SELECT a FROM Afiliado a WHERE a.numeroDocumento LIKE %?1%")
	public List<Afiliado> findByNumeroDocumento(String numeroDocumento);

}
