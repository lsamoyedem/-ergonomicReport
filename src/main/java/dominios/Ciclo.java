package dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "CICLO")
public class Ciclo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_CICLO")
    @SequenceGenerator(name = "GEN_CICLO", sequenceName = "GEN_CICLO", allocationSize = 1, initialValue = 1)
    @NotNull
    @Column(name = "CCL_ID")
    private Integer cclId;
    @NotNull
    @JoinColumn(name = "CCL_COD_AVAL", referencedColumnName = "AVL_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Avaliacao cclCodAval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCL_SEQ")
    private int cclSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdCodCiclo", fetch = FetchType.LAZY)
    private List<CicloDetalhe> cicloDetalheList = new ArrayList<>();

    public Ciclo() {
    }

    public Ciclo(Integer cclId) {
        this.cclId = cclId;
    }

    public Ciclo(Avaliacao cclCodAval, int cclSeq) {
        this.cclCodAval = cclCodAval;
        this.cclSeq = cclSeq;
    }

    public Integer getCclId() {
        return cclId;
    }

    public void setCclId(Integer cclId) {
        this.cclId = cclId;
    }

    public Avaliacao getCclCodAval() {
        return cclCodAval;
    }

    public void setCclCodAval(Avaliacao cclCodAval) {
        this.cclCodAval = cclCodAval;
    }

    public int getCclSeq() {
        return cclSeq;
    }

    public void setCclSeq(int cclSeq) {
        this.cclSeq = cclSeq;
    }

    public List<CicloDetalhe> getCicloDetalheList() {
        return cicloDetalheList;
    }

    public void setCicloDetalheList(List<CicloDetalhe> CicloDetalheList) {
        this.cicloDetalheList = CicloDetalheList;
    }

    public void adicionarNovoCicloDetalhe() {
        CicloDetalhe ciclo = new CicloDetalhe();
        ciclo.setCdCodCiclo(this);
        cicloDetalheList.add(ciclo);
    }

    public void removeCicloDetalhe(int index) {
        cicloDetalheList.remove(index);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cclId != null ? cclId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciclo)) {
            return false;
        }
        Ciclo other = (Ciclo) object;
        return !((this.cclId == null && other.cclId != null) || (this.cclId != null && !this.cclId.equals(other.cclId)));
    }
}
