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

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "CICLO_DETALHE")
public class CicloDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CD_ID")
    private Integer cdId;
    @Column(name = "CD_SEGUNDOS")
    private Integer cdSegundos;
    @JoinColumn(name = "CD_COD_TAREFA", referencedColumnName = "TAR_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tarefa cdCodTarefa;
    @JoinColumn(name = "CD_COD_CICLO", referencedColumnName = "CCL_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ciclo cdCodCiclo;

    public CicloDetalhe() {
    }

    public CicloDetalhe(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getCdSegundos() {
        return cdSegundos;
    }

    public void setCdSegundos(Integer cdSegundos) {
        this.cdSegundos = cdSegundos;
    }

    public Tarefa getCdCodTarefa() {
        return cdCodTarefa;
    }

    public void setCdCodTarefa(Tarefa cdCodTarefa) {
        this.cdCodTarefa = cdCodTarefa;
    }

    public Ciclo getCdCodCiclo() {
        return cdCodCiclo;
    }

    public void setCdCodCiclo(Ciclo cdCodCiclo) {
        this.cdCodCiclo = cdCodCiclo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdId != null ? cdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CicloDetalhe)) {
            return false;
        }
        CicloDetalhe other = (CicloDetalhe) object;
        return !((this.cdId == null && other.cdId != null) || (this.cdId != null && !this.cdId.equals(other.cdId)));
    }
}
