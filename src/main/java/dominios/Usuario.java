package dominios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "USU_ID")
    private Integer usuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_USUARIO")
    private String usuUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "USU_SENHA")
    private String usuSenha;
    @Size(max = 100)
    @Column(name = "USU_EMAIL")
    private String usuEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_ADM")
    private Boolean usuAdm;
    @JoinColumn(name = "USU_COD_PESSOA", referencedColumnName = "PES_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa usuCodPessoa;

    public Usuario() {
    }

    public Usuario(Integer usuId) {
        this.usuId = usuId;
    }

    public Usuario(Integer usuId, String usuUsuario, String usuSenha, Boolean usuAdm) {
        this.usuId = usuId;
        this.usuUsuario = usuUsuario;
        this.usuSenha = usuSenha;
        this.usuAdm = usuAdm;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuSenha() {
        return usuSenha;
    }

    public void setUsuSenha(String usuSenha) {
        this.usuSenha = usuSenha;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public Boolean getUsuAdm() {
        return usuAdm;
    }

    public void setUsuAdm(Boolean usuAdm) {
        this.usuAdm = usuAdm;
    }

    public Pessoa getUsuCodPessoa() {
        return usuCodPessoa;
    }

    public void setUsuCodPessoa(Pessoa usuCodPessoa) {
        this.usuCodPessoa = usuCodPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId)));
    }

}
