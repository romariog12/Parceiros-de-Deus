package br.com.romariodev.estagio.module.base.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-17T12:03:12.250-0300")
@StaticMetamodel(Empresa.class)
public class Empresa_ {
	public static volatile SingularAttribute<Empresa, EmpresaPK> id;
	public static volatile SingularAttribute<Empresa, String> cnpj;
	public static volatile SingularAttribute<Empresa, String> endereco;
	public static volatile SingularAttribute<Empresa, Usuario> usuario;
	public static volatile ListAttribute<Empresa, Estagio> estagios;
	public static volatile ListAttribute<Empresa, Supervisor> supervisors;
}
