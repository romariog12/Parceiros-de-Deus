package br.com.romariodev.estagio.module.base.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.238-0300")
@StaticMetamodel(Aluno.class)
public class Aluno_ {
	public static volatile SingularAttribute<Aluno, AlunoPK> id;
	public static volatile SingularAttribute<Aluno, String> cpf;
	public static volatile SingularAttribute<Aluno, String> curso;
	public static volatile SingularAttribute<Aluno, String> matricula;
	public static volatile SingularAttribute<Aluno, Instituicao> instituicao;
	public static volatile SingularAttribute<Aluno, Usuario> usuario;
	public static volatile ListAttribute<Aluno, Estagio> estagios;
}
