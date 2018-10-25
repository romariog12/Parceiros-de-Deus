package br.com.romariodev.module.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.romariodev.module.base.entity.Estagio;

public interface EstagioRepository extends JpaRepository<Estagio, Long>{

	public List<Estagio> findByAlunoIdAluno(int id);
}
