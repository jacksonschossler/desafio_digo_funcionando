package br.com.gtsaude.registro.domain.service;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import br.com.agenda.categoria.domain.entity.Categoria;
import br.com.agenda.registro.domain.entity.Registro;
import br.com.agenda.registro.domain.service.RegistroService;
import br.com.gtsaude.registro.domain.AbstractIntegrationTests;
import scala.annotation.meta.setter;

public class RegistroServiceIntegrationTests extends AbstractIntegrationTests {

	@Autowired
	private RegistroService registroService;
	
	
	private Categoria categoria = new Categoria();
	
	
	@Test
	@Sql({"/dataset/registro/registros.sql"
		})
	public void inserirRegistroMustPass() {
		Registro registro = new Registro();
		registro.setCategoria(new Categoria(20L));
		registro.setData(LocalDateTime.now());
		registro.setDescricao("BLABLABLA");
		registro.setValor(15);
		registroService.insertRegistro(registro);
		Assert.notNull(registro, "não deu ");
		
		
		
	}
	
	public void inserirRegistroMustFail() {
		
		
	}
	
	public void findRegistroByIdMustPass() {
		
		
	}
	
	public void findRegistroByIdMustFail() {
		
		
	}
	
	public void removeRegistroMustPass() {
		
	}
	
	
	
	
	
}
