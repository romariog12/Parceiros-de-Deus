package br.com.romariodev.estagio.module.base.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.231-0300")
@StaticMetamodel(Acompanhamento.class)
public class Acompanhamento_ {
	public static volatile SingularAttribute<Acompanhamento, AcompanhamentoPK> id;
	public static volatile SingularAttribute<Acompanhamento, Date> fim;
	public static volatile SingularAttribute<Acompanhamento, Date> inicio;
	public static volatile SingularAttribute<Acompanhamento, Byte> situacao;
	public static volatile SingularAttribute<Acompanhamento, Estagio> estagio;
	public static volatile SingularAttribute<Acompanhamento, Orientador> orientador;
	public static volatile SingularAttribute<Acompanhamento, Supervisor> supervisor;
	public static volatile ListAttribute<Acompanhamento, Planoatividade> planoatividades;
	public static volatile ListAttribute<Acompanhamento, Relatorio> relatorios;
}
