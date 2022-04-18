package dominios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "FUNCIONARIOXFUNCAO")
public class Funcionarioxfuncao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "FXF_ID")
    private Integer fxfId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FXF_DATA_INI")
    @Temporal(TemporalType.DATE)
    private Date fxfDataIni;
    @Column(name = "FXF_DATA_FIM")
    @Temporal(TemporalType.DATE)
    private Date fxfDataFim;
    @JoinColumn(name = "FXF_COD_FUNCAO", referencedColumnName = "FNC_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcao fxfCodFuncao;
    @JoinColumn(name = "FXF_COD_FUNC", referencedColumnName = "FUN_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario fxfCodFunc;

    public Funcionarioxfuncao() {
    }

    public Funcionarioxfuncao(Integer fxfId) {
        this.fxfId = fxfId;
    }

    public Funcionarioxfuncao(Integer fxfId, Date fxfDataIni) {
        this.fxfId = fxfId;
        this.fxfDataIni = fxfDataIni;
    }

    public Integer getFxfId() {
        return fxfId;
    }

    public void setFxfId(Integer fxfId) {
        this.fxfId = fxfId;
    }

    public Date getFxfDataIni() {
        return fxfDataIni;
    }

    public void setFxfDataIni(Date fxfDataIni) {
        this.fxfDataIni = fxfDataIni;
    }

    public Date getFxfDataFim() {
        return fxfDataFim;
    }

    public void setFxfDataFim(Date fxfDataFim) {
        this.fxfDataFim = fxfDataFim;
    }

    public Funcao getFxfCodFuncao() {
        return fxfCodFuncao;
    }

    public void setFxfCodFuncao(Funcao fxfCodFuncao) {
        this.fxfCodFuncao = fxfCodFuncao;
    }

    public Funcionario getFxfCodFunc() {
        return fxfCodFunc;
    }

    public void setFxfCodFunc(Funcionario fxfCodFunc) {
        this.fxfCodFunc = fxfCodFunc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fxfId != null ? fxfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionarioxfuncao)) {
            return false;
        }
        Funcionarioxfuncao other = (Funcionarioxfuncao) object;
        return !((this.fxfId == null && other.fxfId != null) || (this.fxfId != null && !this.fxfId.equals(other.fxfId)));
    }
}
