package dominios;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "FUNCAO")
public class Funcao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FNC_ID")
    private Integer fncId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FNC_DESC")
    private String fncDesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FNC_SITUACAO")
    private Character fncSituacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "axfCodFuncao", fetch = FetchType.LAZY)
    private List<Ativxfuncao> ativxfuncaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fxfCodFuncao", fetch = FetchType.LAZY)
    private List<Funcionarioxfuncao> funcionarioxfuncaoList;

    public Funcao() {
    }

    public Funcao(Integer fncId) {
        this.fncId = fncId;
    }

    public Funcao(Integer fncId, String fncDesc, Character fncSituacao) {
        this.fncId = fncId;
        this.fncDesc = fncDesc;
        this.fncSituacao = fncSituacao;
    }

    public Integer getFncId() {
        return fncId;
    }

    public void setFncId(Integer fncId) {
        this.fncId = fncId;
    }

    public String getFncDesc() {
        return fncDesc;
    }

    public void setFncDesc(String fncDesc) {
        this.fncDesc = fncDesc;
    }

    public Character getFncSituacao() {
        return fncSituacao;
    }

    public void setFncSituacao(Character fncSituacao) {
        this.fncSituacao = fncSituacao;
    }

    public List<Ativxfuncao> getAtivxfuncaoList() {
        return ativxfuncaoList;
    }

    public void setAtivxfuncaoList(List<Ativxfuncao> ativxfuncaoList) {
        this.ativxfuncaoList = ativxfuncaoList;
    }

    public List<Funcionarioxfuncao> getFuncionarioxfuncaoList() {
        return funcionarioxfuncaoList;
    }

    public void setFuncionarioxfuncaoList(List<Funcionarioxfuncao> funcionarioxfuncaoList) {
        this.funcionarioxfuncaoList = funcionarioxfuncaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fncId != null ? fncId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcao)) {
            return false;
        }
        Funcao other = (Funcao) object;
        return !((this.fncId == null && other.fncId != null) || (this.fncId != null && !this.fncId.equals(other.fncId)));
    }
}
