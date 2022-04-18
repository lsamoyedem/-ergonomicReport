package dominios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ENDERECO")
    @SequenceGenerator(name = "GEN_ENDERECO", sequenceName = "GEN_ENDERECO", allocationSize = 1, initialValue = 1)
    @Column(name = "END_ID")
    private Integer endId;
    @Column(name = "END_NUMERO")
    private Short endNumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "END_UF")
    private String endUf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "END_NOME_CIDADE")
    private String endNomeCidade;
    @Size(max = 8)
    @Column(name = "END_CEP")
    private String endCep;
    @Size(max = 100)
    @Column(name = "END_RUA")
    private String endRua;
    @Size(max = 100)
    @Column(name = "END_COMPLEMENTO")
    private String endComplemento;

    public Endereco() {
    }

    public Endereco(Integer endId) {
        this.endId = endId;
    }

    public Endereco(Integer endId, String endUf, String endNomeCidade) {
        this.endId = endId;
        this.endUf = endUf;
        this.endNomeCidade = endNomeCidade;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public String getEndUf() {
        return endUf;
    }

    public void setEndUf(String endUf) {
        this.endUf = endUf;
    }

    public String getEndNomeCidade() {
        return endNomeCidade;
    }

    public void setEndNomeCidade(String endNomeCidade) {
        this.endNomeCidade = endNomeCidade;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public Short getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(Short endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndComplemento() {
        return endComplemento;
    }

    public void setEndComplemento(String endComplemento) {
        this.endComplemento = endComplemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (endId != null ? endId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        return !((this.endId == null && other.endId != null) || (this.endId != null && !this.endId.equals(other.endId)));
    }
}
