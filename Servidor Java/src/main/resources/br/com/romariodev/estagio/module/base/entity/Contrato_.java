package br.com.romariodev.estagio.module.base.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.247-0300")
@StaticMetamodel(Contrato.class)
public class Contrato_ {
	public static volatile SingularAttribute<Contrato, Integer> idContrato;
	public static volatile SingularAttribute<Contrato, String> bolsa;
	public static volatile SingularAttribute<Contrato, String> carga;
	public static volatile SingularAttribute<Contrato, Date> fim;
	public static volatile SingularAttribute<Contrato, Date> inicio;
	public static volatile SingularAttribute<Contrato, Estagio> estagio;
}
