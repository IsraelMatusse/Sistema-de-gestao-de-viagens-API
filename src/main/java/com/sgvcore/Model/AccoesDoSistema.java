package com.sgvcore.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public abstract class AccoesDoSistema {
    @CreatedBy
    @Column(updatable = false)
    private String criadoPor;
    @LastModifiedBy
    private String actualizadoPor;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String apagadoPor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataActualizacao;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataApagado;
    private Boolean activo = true;
}
