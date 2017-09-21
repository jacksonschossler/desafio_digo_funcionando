package br.com.agenda.registro.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.agenda.common.application.i18n.MessageSourceHolder;
import br.com.agenda.registro.application.restful.IRegistroResource;
import br.com.agenda.registro.domain.entity.Registro;
import br.com.agenda.registro.domain.repository.IRegistroRepository;

@Service
@RemoteProxy
@Transactional
public class RegistroService implements IRegistroResource {

	
	@Autowired
	private IRegistroRepository registroRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public Registro insertRegistro(Registro registro) {
		Assert.notNull(registro, this.messageSource.getMessage("registro.null",null,LocaleContextHolder.getLocale()));
		registro = this.registroRepository.save(registro);
		return registro;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Registro findRegistroById(Long id) {
		final Registro registro = this.registroRepository.findOne(id);
		Assert.notNull(registro, MessageSourceHolder.getMessage("repository.notFoundById", id));
		return registro;
	}

	

	@Override
	public Page<Registro> listRegistroById(Integer mes, Integer ano, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return this.registroRepository.listByFilters(mes, ano, pageRequest);
	}
	

	@Override
	public void removeRegistro(Long id) {
		Assert.notNull(id,MessageSourceHolder.getMessage("repository.notFoundIdToRemove", id));
		Registro registro = this.registroRepository.findOne(id);
		
		this.registroRepository.delete(registro);
		
	}

	@Override
	public Registro updateRegistro(Registro registro) {
		Assert.notNull( registro.getId(), this.messageSource.getMessage( "registroId.null", null, LocaleContextHolder.getLocale() ) );
		Assert.notNull( registro.getTipo(), this.messageSource.getMessage( "registroTipo.null", null, LocaleContextHolder.getLocale() ) );
		Assert.notNull( registro.getCategoria(), this.messageSource.getMessage( "registroCategoria.null", null, LocaleContextHolder.getLocale() ) );
		Assert.notNull( registro.getData(), this.messageSource.getMessage( "registroData.null", null, LocaleContextHolder.getLocale() ) );
		Assert.notNull( registro.getValor(), this.messageSource.getMessage( "registroValor.null", null, LocaleContextHolder.getLocale() ) );

		Registro registroSaved = this.registroRepository.findOne(registro.getId());
		
		registroSaved.setTipo(registro.getTipo());
		registroSaved.setCategoria(registro.getCategoria());
		registroSaved.setData(registro.getData());
		registroSaved.setValor(registro.getValor());
		registroSaved.setDescricao(registro.getDescricao());
		
		return this.registroRepository.save(registroSaved);
	}

}
