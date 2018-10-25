package br.com.romariodev.module.base.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.romariodev.module.base.entity.Estagio;
import br.com.romariodev.module.base.repository.AlunoRepository;
import br.com.romariodev.module.base.repository.ContratoRepository;
import br.com.romariodev.module.base.repository.EmpresaRepository;
import br.com.romariodev.module.base.repository.EstagioRepository;
import br.com.romariodev.module.base.repository.InstituicaoRepository;
import br.com.romariodev.module.base.repository.UsuarioRepository;

public abstract class AbstractService {
	
	private  EmpresaRepository empresaRepository;
	private  UsuarioRepository usuarioRepository;
	private  EstagioRepository estagioRepository;
	private  AlunoRepository alunoRepository;
	private InstituicaoRepository instituicaoRepository;
	private ContratoRepository contratoRepository;
	
	@Autowired
	protected AbstractService(
			EmpresaRepository empresa, 
			UsuarioRepository usuario, 
			EstagioRepository estagio, 
			AlunoRepository aluno, 
			InstituicaoRepository instituicao,
			ContratoRepository contrato){
		this.empresaRepository = empresa;
		this.estagioRepository = estagio;
		this.usuarioRepository = usuario;
		this.alunoRepository = aluno;
		this.instituicaoRepository = instituicao;
		this.contratoRepository = contrato;
	}
	
	public EmpresaRepository getEmpresaRepository() {
		return empresaRepository;
	}

	public void setEmpresaRepository(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public EstagioRepository getEstagioRepository() {
		return estagioRepository;
	}

	public void setEstagioRepository(EstagioRepository estagioRepository) {
		this.estagioRepository = estagioRepository;
	}
	public AlunoRepository getAlunoRepository() {
		return alunoRepository;
	}

	public void setAlunoRepository(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public InstituicaoRepository getInstituicaoRepository() {
		return instituicaoRepository;
	}

	public void setInstituicaoRepository(InstituicaoRepository instituicaoRepository) {
		this.instituicaoRepository = instituicaoRepository;
	}

	public ContratoRepository getContratoRepository() {
		return contratoRepository;
	}

	public void setContratoRepository(ContratoRepository contratoRepository) {
		this.contratoRepository = contratoRepository;
	}

	public ResponseEntity<Perfil> perfil(int id) {
		List<Estagio> estagio = this.getEstagioRepository().findByAlunoIdAluno(id);
		Perfil perfil = new Perfil();
		perfil.setEstagio(estagio);
		return new ResponseEntity<>(perfil, HttpStatus.FOUND);
	}
	
}
