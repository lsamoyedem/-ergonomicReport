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
@Table(name = "TAREFAXATIVIDADE")
public class Tarefaxatividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TXA_ID")
    private Integer txaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TXA_SITU")
    private Character txaSitu;
    @JoinColumn(name = "TXA_COD_ATIIDADE", referencedColumnName = "ATV_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Atividade txaCodAtiidade;
    @JoinColumn(name = "TXA_COD_TAREFA", referencedColumnName = "TAR_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tarefa txaCodTarefa;

    public Tarefaxatividade() {
    }

    public Tarefaxatividade(Integer txaId) {
        this.txaId = txaId;
    }

    public Tarefaxatividade(Integer txaId, Character txaSitu) {
        this.txaId = txaId;
        this.txaSitu = txaSitu;
    }

    public Integer getTxaId() {
        return txaId;
    }

    public void setTxaId(Integer txaId) {
        this.txaId = txaId;
    }

    public Character getTxaSitu() {
        return txaSitu;
    }

    public void setTxaSitu(Character txaSitu) {
        this.txaSitu = txaSitu;
    }

    public Atividade getTxaCodAtiidade() {
        return txaCodAtiidade;
    }

    public void setTxaCodAtiidade(Atividade txaCodAtiidade) {
        this.txaCodAtiidade = txaCodAtiidade;
    }

    public Tarefa getTxaCodTarefa() {
        return txaCodTarefa;
    }

    public void setTxaCodTarefa(Tarefa txaCodTarefa) {
        this.txaCodTarefa = txaCodTarefa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (txaId != null ? txaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefaxatividade)) {
            return false;
        }
        Tarefaxatividade other = (Tarefaxatividade) object;
        return !((this.txaId == null && other.txaId != null) || (this.txaId != null && !this.txaId.equals(other.txaId)));
    }
}
