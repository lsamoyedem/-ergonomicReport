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
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "AVALIACAO")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "AVL_ID")
    private Integer avlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "AVL_DESC")
    private String avlDesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AVL_DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date avlData;
    @JoinColumn(name = "AVL_COD_ATIVIDADE", referencedColumnName = "ATV_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Atividade avlCodAtividade;
    @JoinColumn(name = "AVL_COD_FUNC", referencedColumnName = "FUN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcionario avlCodFunc;

    public Avaliacao() {
    }

    public Avaliacao(Integer avlId) {
        this.avlId = avlId;
    }

    public Avaliacao(Integer avlId, String avlDesc, Date avlData) {
        this.avlId = avlId;
        this.avlDesc = avlDesc;
        this.avlData = avlData;
    }

    public Integer getAvlId() {
        return avlId;
    }

    public void setAvlId(Integer avlId) {
        this.avlId = avlId;
    }

    public String getAvlDesc() {
        return avlDesc;
    }

    public void setAvlDesc(String avlDesc) {
        this.avlDesc = avlDesc;
    }

    public Date getAvlData() {
        return avlData;
    }

    public void setAvlData(Date avlData) {
        this.avlData = avlData;
    }

    public Atividade getAvlCodAtividade() {
        return avlCodAtividade;
    }

    public void setAvlCodAtividade(Atividade avlCodAtividade) {
        this.avlCodAtividade = avlCodAtividade;
    }

    public Funcionario getAvlCodFunc() {
        return avlCodFunc;
    }

    public void setAvlCodFunc(Funcionario avlCodFunc) {
        this.avlCodFunc = avlCodFunc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avlId != null ? avlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        return !((this.avlId == null && other.avlId != null) || (this.avlId != null && !this.avlId.equals(other.avlId)));
    }
}
