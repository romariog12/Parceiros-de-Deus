package br.com.romariodev.estagio.module.base.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.263-0300")
@StaticMetamodel(Orientador.class)
public class Orientador_ {
	public static volatile SingularAttribute<Orientador, Integer> idOrientador;
	public static volatile SingularAttribute<Orientador, String> matricula;
	public static volatile ListAttribute<Orientador, Acompanhamento> acompanhamentos;
	public static volatile SingularAttribute<Orientador, Instituicao> instituicao;
	public static volatile SingularAttribute<Orientador, Usuario> usuario;
}
