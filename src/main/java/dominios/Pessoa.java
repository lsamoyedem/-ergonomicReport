package dominios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_PESSOA")
    @SequenceGenerator(name = "GEN_PESSOA", sequenceName = "GEN_PESSOA", allocationSize = 1, initialValue = 1)
    @NotNull
    @Column(name = "PES_ID")
    private Integer pesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PES_NOME")
    private String pesNome;
    @Column(name = "PES_DATA_NASC")
    @Temporal(TemporalType.DATE)
    private Date pesDataNasc;
    @Size(max = 11)
    @Column(name = "PES_CPF")
    private String pesCpf;
    @Size(max = 150)
    @Column(name = "PES_ENDERECO")
    private String pesEndereco;
    @Column(name = "PES_NUMERO")
    private Integer pesNumero;
    @Size(max = 100)
    @Column(name = "PES_CIDADE")
    private String pesCidade;
    @Size(max = 150)
    @Column(name = "PES_BAIRRO")
    private String pesBairro;
    @Size(max = 2)
    @Column(name = "PES_UF")
    private String pesUf;

    public Pessoa() {
    }

    public Pessoa(Integer pesId) {
        this.pesId = pesId;
    }

    public Pessoa(Integer pesId, String pesNome) {
        this.pesId = pesId;
        this.pesNome = pesNome;
    }

    public Integer getPesId() {
        return pesId;
    }

    public void setPesId(Integer pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public Date getPesDataNasc() {
        return pesDataNasc;
    }

    public void setPesDataNasc(Date pesDataNasc) {
        this.pesDataNasc = pesDataNasc;
    }

    public String getPesCpf() {
        return pesCpf;
    }

    public void setPesCpf(String pesCpf) {
        this.pesCpf = pesCpf;
    }

    public String getPesEndereco() {
        return pesEndereco;
    }

    public void setPesEndereco(String pesEndereco) {
        this.pesEndereco = pesEndereco;
    }

    public Integer getPesNumero() {
        return pesNumero;
    }

    public void setPesNumero(Integer pesNumero) {
        this.pesNumero = pesNumero;
    }

    public String getPesCidade() {
        return pesCidade;
    }

    public void setPesCidade(String pesCidade) {
        this.pesCidade = pesCidade;
    }

    public String getPesBairro() {
        return pesBairro;
    }

    public void setPesBairro(String pesBairro) {
        this.pesBairro = pesBairro;
    }

    public String getPesUf() {
        return pesUf;
    }

    public void setPesUf(String pesUf) {
        this.pesUf = pesUf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pesId != null ? pesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        return !((this.pesId == null && other.pesId != null) || (this.pesId != null && !this.pesId.equals(other.pesId)));
    }
}
