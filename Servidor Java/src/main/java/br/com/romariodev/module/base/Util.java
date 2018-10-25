package br.com.romariodev.module.base;

public enum Util { 
	
	//Url's abstratas
	ADMINISTRADOR_URL("/administrador"),
	INSTITUICAO_URL("/instituicao"),
	ALUNO_URL("/aluno"),
	EMPRESA_URL("/empresa"),
	//url's ações
	NOVO_ALUNO("/novoAluno"),
	ALUNOS("/alunos"),
	NOVA_INSTITUICAO("/novaInstituicao"),
	INSTITUICOES("/instituicoes"),
	NOVO_CONTRATO("/novoContrato"),
	CONTRATOS("/contratos"),
	CONTRATO_VENCENDO("/contratoVencendo"),
	CONTRATO_VENCIDO("/contratoVencido"),
	NOVA_EMPRESA("novaEmpresa"),
	EMPRESAS("/empresas"),
	NOVO_ESTAGIO("/novoEstagio"),
	ESTAGIOS("/estagios"),
	
	
	
	//Respostas CRUD
	CADASTRO_SUCESSO("Cadastro realizado com sucesso!"),
	CADASTRO_ERRO("Cadastro não realizado!"),
	CADASTRO_EXISTENTE("Cadastro existente"),
	EXCLUSAO_SUCESSO("Registro apagado!"),
	EXCLUSAO_ERRO("Registro não apagado!"),
	SAUDACAO("Bem vindo"),
	UPDATE_SUCESSO("Dados editados com sucesso!"),
	EXCECAO_TITULO("\n Exceção: \n"),
	NULL("Requisição nula!");
	
   private String valor;
   
   Util(String v) {
	   valor = v;
   }
	public String valor() {
		return valor;
	}

   
}
