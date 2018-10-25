package br.com.romariodev.estagio.module.aluno;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-24T12:17:36.283-0300")
@StaticMetamodel(Aluno.class)
public class Aluno_ {
	public static volatile SingularAttribute<Aluno, Integer> idAluno;
	public static volatile SingularAttribute<Aluno, String> cpf;
	public static volatile SingularAttribute<Aluno, String> curso;
	public static volatile SingularAttribute<Aluno, Integer> instituicaoIdInstituicao;
	public static volatile SingularAttribute<Aluno, String> matricula;
	public static volatile SingularAttribute<Aluno, Usuario> usuario;
}
