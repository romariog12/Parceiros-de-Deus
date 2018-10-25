package br.com.romariodev.estagio.module.base.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.255-0300")
@StaticMetamodel(Estagio.class)
public class Estagio_ {
	public static volatile SingularAttribute<Estagio, Integer> idEstagio;
	public static volatile SingularAttribute<Estagio, Date> recisao;
	public static volatile ListAttribute<Estagio, Acompanhamento> acompanhamentos;
	public static volatile ListAttribute<Estagio, Contrato> contratos;
	public static volatile SingularAttribute<Estagio, Aluno> aluno;
	public static volatile SingularAttribute<Estagio, Empresa> empresa;
	public static volatile SingularAttribute<Estagio, Instituicao> instituicao;
	public static volatile ListAttribute<Estagio, Planoatividade> planoatividades;
	public static volatile ListAttribute<Estagio, Relatorio> relatorios;
}
