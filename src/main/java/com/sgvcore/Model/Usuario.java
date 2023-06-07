package com.sgvcore.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean deleted = false;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean active = true;
    @JoinColumn(name = "created_by")
    private String createdBy;
    @JoinColumn(name = "updated_by")
    private String updatedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "deleted_by")
    private String deletedBy;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;



}
