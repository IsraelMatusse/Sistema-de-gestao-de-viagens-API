package com.sgvcore.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class FuncaoDoUsuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome da Role não pode ser nulo!")
    @Size(min = 3, message = "A Role deve ter no minimo 3 digitos")
    @Column(unique = true)
    @Getter
    private String name;
}