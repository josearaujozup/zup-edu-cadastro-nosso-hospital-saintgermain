package br.com.zup.edu.saintgermain.leito;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Leito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOcupacao status;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDateTime atualizadoEm;

    public Leito(String titulo) {
        this.titulo = titulo;
        this.status = StatusOcupacao.LIVRE;
    }

    /**
     * @deprecated construtor de uso exclusivo para o Hibernate
     */
    @Deprecated
    public Leito() {
    }

    public Long getId() {
        return id;
    }
    
    private boolean isLivre() {
		return this.status == StatusOcupacao.LIVRE;
	}
    
	public void reservar() {
		
		if(!isLivre()) {
    		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Leito não está livre para reservar");
    	}
		
		this.atualizadoEm = LocalDateTime.now();
		this.status = StatusOcupacao.OCUPADO;
	}
}
