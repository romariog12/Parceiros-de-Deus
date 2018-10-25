package br.com.romariodev.estagio.module.base.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.270-0300")
@StaticMetamodel(Relatorio.class)
public class Relatorio_ {
	public static volatile SingularAttribute<Relatorio, Integer> idRelatorio;
	public static volatile SingularAttribute<Relatorio, String> p1;
	public static volatile SingularAttribute<Relatorio, Acompanhamento> acompanhamento;
	public static volatile SingularAttribute<Relatorio, Estagio> estagio;
}
