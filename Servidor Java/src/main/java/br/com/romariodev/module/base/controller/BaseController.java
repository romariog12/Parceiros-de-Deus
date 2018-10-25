package br.com.romariodev.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.romariodev.module.base.entity.Empresa;
import br.com.romariodev.module.base.entity.Instituicao;
import br.com.romariodev.module.base.model.BaseService;

@RestController
public class BaseController extends AbstractController{
	
	private BaseService baseService;
	@Autowired
	public BaseController(BaseService base) {
		baseService = base;
	}
	@GetMapping(path="/base/instituicoes")
	public  Iterable<Instituicao> instituicoes(){
		return  baseService.instituicoes();
	}
	@GetMapping(path="/base/empresas")
	public  Iterable<Empresa> empresas(){
		return baseService.empresas();
	}

}
