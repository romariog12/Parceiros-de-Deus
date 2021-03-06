package snippet;

public class Snippet {
	<?xml version="1.0" encoding="UTF-8"?>
	  <beans xmlns="http://www.springframework.org/schema/beans"
	        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	        xmlns:tx="http://www.springframework.org/schema/tx"
	        xsi:schemaLocation="
	          http://www.springframework.org/schema/beans
	          http://www.springframework.org/schema/beans/spring-beans.xsd
	          http://www.springframework.org/schema/context
	          http://www.springframework.org/schema/context/spring-context.xsd
	          http://www.springframework.org/schema/tx
	          http://www.springframework.org/schema/tx/spring-tx.xsd">
	   
	        <!-- Contexto do DispatcherServlet: define a infraestrutura do processamento das requisições feitas ao Servlet (DispatcherServlet) -->
	   
	        <bean id="entityManagerFactory"
	              class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
	              <property name="persistenceUnitName" value="DEVMEDIA-UP" />
	              <property name="dataSource" ref="mysqlDataSource" />
	              <property name="jpaVendorAdapter">
	        <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
	              </property>
	        </bean>
	        <bean id="mysqlDataSource" class="org.apache.commons.dbcp2.BasicDataSource">
	              <property name="driverClassName" value="com.mysql.jdbc.Driver" />
	              <property name="url"
	                    value="jdbc:mysql://localhost/alunodb?createDatabaseIfNotExist=true" />
	              <property name="username" value="root" />
	              <property name="password" value="123456" />
	        </bean>
	        <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
	              <property name="entityManagerFactory" ref="entityManagerFactory" />
	        </bean>
	        <tx:annotation-driven />
	   
	        <!-- Define o pacote base para o escaneamento das anotações de contexto (@Component, @Repository, @Service, @Controller, etc) -->
	        <context:component-scan base-package="br.com.cazuzaneto" />
	        <context:annotation-config />
	   
	  </beans>
}

