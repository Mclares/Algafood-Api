package com.algaworks.algafood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

	@NotNull
	private String remetente;
	private TipoEmail tipoEmail = TipoEmail.FAKE;
	private Sandbox sandbox = new Sandbox();
			
	public enum TipoEmail {
		FAKE, REAL, SANDBOX;
	}
	
	@Setter
	@Getter
	public class Sandbox {
		private String destinatario;
	}
}
