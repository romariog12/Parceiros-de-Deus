package br.com.romariodev.module.base.model;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import br.com.romariodev.module.base.entity.Empresa;
import br.com.romariodev.module.base.entity.Instituicao;
import br.com.romariodev.module.base.repository.AlunoRepository;
import br.com.romariodev.module.base.repository.ContratoRepository;
import br.com.romariodev.module.base.repository.EmpresaRepository;
import br.com.romariodev.module.base.repository.EstagioRepository;
import br.com.romariodev.module.base.repository.InstituicaoRepository;
import br.com.romariodev.module.base.repository.UsuarioRepository;
/**
 * 
 * @author Romário Macêdo Portela <romariomacedo18@gmail.com>
 *
 */
@Service
@RestController
public class BaseService extends AbstractService{

	protected BaseService(EmpresaRepository empresa, UsuarioRepository usuario, EstagioRepository estagio,
			AlunoRepository aluno, InstituicaoRepository instituicao, ContratoRepository contrato) {
		super(empresa, usuario, estagio, aluno, instituicao, contrato);
	}
	public Iterable<Empresa> empresas(){
		Iterable<Empresa> empresas = this.getEmpresaRepository().findAll();
		return empresas;
	}
	public Iterable<Instituicao> instituicoes(){
		Iterable<Instituicao> instituicoes = this.getInstituicaoRepository().findAll();
		return instituicoes;
	}
}
