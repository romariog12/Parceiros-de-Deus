package br.com.romariodev.module.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.base.entity.Aluno;

/**
 * 
 * @author romario.portela <romariomacedo18@gmail.com>
 *
 */
@Transactional
public interface AlunoRepository extends  JpaRepository<Aluno, Long>{
	List<Aluno> findByMatricula(String matricula);
	Aluno findByIdAluno(int id);
	List<Aluno> findByCpf(String cpf);
	void deleteByIdAluno(int id);
	@Modifying
	@Query("UPDATE Aluno a SET matricula = :matricula, curso = :curso, cpf = :cpf WHERE idAluno = :id")
	void updateAluno(@Param("id") int idAluno, @Param("matricula") String matricula, @Param("curso") String curso, @Param("cpf") String cpf);
	//Procurar por nome
	@Query(value="SELECT * FROM aluno JOIN usuario ON aluno.usuario_id_usuario = Usuario.id_usuario WHERE usuario.nome LIKE %:nome%", nativeQuery = true)
	List<Aluno> findByNome(@Param("nome") String nome);
	//Procurar por telefone
	@Query(value="SELECT * FROM aluno JOIN usuario ON aluno.usuario_id_usuario = Usuario.id_usuario WHERE usuario.telefone LIKE %:telefone%", nativeQuery = true)
	List<Aluno> findByTelefone(@Param("telefone") String nome);
	
}
