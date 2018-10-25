package br.com.romariodev.module.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.romariodev.module.base.entity.Usuario;

/**
 * 
 * @author romario.portela <romariomacedo18@gmail.com>
 *
 */
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	void deleteByIdUsuario(int id);
	
	@Modifying
    @Query("UPDATE Usuario u SET u.nome = :nome, u.email = :email, u.telefone = :telefone WHERE u.idUsuario = :id")
	void updateUsuario(
			@Param("id") int iUsuario,
			@Param("nome") String nome,
			@Param("email") String email,
			@Param("telefone") String telefone);
	Usuario findByIdUsuario(int idUsuario);
}
