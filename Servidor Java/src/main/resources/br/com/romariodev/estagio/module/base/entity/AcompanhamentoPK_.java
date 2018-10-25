package br.com.romariodev.estagio.module.base.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.234-0300")
@StaticMetamodel(AcompanhamentoPK.class)
public class AcompanhamentoPK_ {
	public static volatile SingularAttribute<AcompanhamentoPK, Integer> idAcompanhamento;
	public static volatile SingularAttribute<AcompanhamentoPK, Integer> estagio_idEstagio;
	public static volatile SingularAttribute<AcompanhamentoPK, Integer> orientador_idOrientador;
	public static volatile SingularAttribute<AcompanhamentoPK, Integer> supervisor_idSupervisor;
}
