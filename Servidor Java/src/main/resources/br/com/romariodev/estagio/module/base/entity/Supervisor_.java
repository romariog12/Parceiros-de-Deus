package br.com.romariodev.estagio.module.base.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.274-0300")
@StaticMetamodel(Supervisor.class)
public class Supervisor_ {
	public static volatile SingularAttribute<Supervisor, Integer> idSupervisor;
	public static volatile SingularAttribute<Supervisor, String> registro;
	public static volatile ListAttribute<Supervisor, Acompanhamento> acompanhamentos;
	public static volatile SingularAttribute<Supervisor, Empresa> empresa;
}
