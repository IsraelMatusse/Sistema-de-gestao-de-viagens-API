package com.sgvcore.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Usuarios")
public class Usuario implements UserDetails {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_funcoes",
            joinColumns = {
                    @JoinColumn(name = "usuario_id", referencedColumnName = "id",
                            nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "funcao_id", referencedColumnName = "id",
                            nullable = false)
            }
    )
    private Set<FuncaoDoUsuario> funcoes = new HashSet<>();
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_permissoes",
            joinColumns = {
                    @JoinColumn(name = "usuario_id", referencedColumnName = "id",
                            nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "permissao_id", referencedColumnName = "id",
                            nullable = false)
            }
    )
    private List<PermissaoAcesso> permissoesDeAcesso = new ArrayList<>();
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (var r: this.funcoes){
            var sga = new SimpleGrantedAuthority(r.getName());
            authorities.add(sga);
        }
        return authorities;
    }
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
    @JsonIgnore

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @JsonIgnore

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore

    @Override
    public boolean isEnabled() {
        return this.active;
    }



}
