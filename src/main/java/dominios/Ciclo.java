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

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "CICLO")
public class Ciclo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCL_ID")
    private Integer cclId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCL_COD_AVAL")
    private int cclCodAval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCL_SEQ")
    private int cclSeq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdCodCiclo", fetch = FetchType.LAZY)
    private List<CicloDetalhe> CicloDetalheList;

    public Ciclo() {
    }

    public Ciclo(Integer cclId) {
        this.cclId = cclId;
    }

    public Ciclo(Integer cclId, int cclCodAval, int cclSeq) {
        this.cclId = cclId;
        this.cclCodAval = cclCodAval;
        this.cclSeq = cclSeq;
    }

    public Integer getCclId() {
        return cclId;
    }

    public void setCclId(Integer cclId) {
        this.cclId = cclId;
    }

    public int getCclCodAval() {
        return cclCodAval;
    }

    public void setCclCodAval(int cclCodAval) {
        this.cclCodAval = cclCodAval;
    }

    public int getCclSeq() {
        return cclSeq;
    }

    public void setCclSeq(int cclSeq) {
        this.cclSeq = cclSeq;
    }

    public List<CicloDetalhe> getCicloDetalheList() {
        return CicloDetalheList;
    }

    public void setCicloDetalheList(List<CicloDetalhe> CicloDetalheList) {
        this.CicloDetalheList = CicloDetalheList;
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
