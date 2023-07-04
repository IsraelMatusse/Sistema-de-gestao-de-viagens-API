package com.sgvcore.Model;
import com.sgvcore.DTOs.tipoLicencaDTOs.TipoLicencaCriarDTO;
import com.sgvcore.utils.GeneratePin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.NoSuchAlgorithmException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TipopLicenca extends AccoesDoSistema{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String designacao;
    private String abreviatura;
    private String codigo;
    public TipopLicenca(TipoLicencaCriarDTO dto) throws NoSuchAlgorithmException {
        this.abreviatura=dto.getAbreviatura();
        this.designacao=dto.getDesignacao();
        this.codigo= GeneratePin.generateStringPin();
    }
}
