package dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_FUNCAO")
    @SequenceGenerator(name = "GEN_FUNCAO", sequenceName = "GEN_FUNCAO", allocationSize = 1, initialValue = 1)
    @NotNull
    @Column(name = "FNC_ID")
    private Integer fncId;
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FNC_DESC")
    private String fncDesc;
    @NotNull
    @Column(name = "FNC_SITUACAO")
    private String fncSituacao = "A";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "axfCodFuncao", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ativxfuncao> ativxfuncaoList = new ArrayList<>();

    public Funcao() {
    }

    public Funcao(Integer fncId) {
        this.fncId = fncId;
    }

    public Funcao(Integer fncId, String fncDesc, String fncSituacao) {
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

    public String getFncSituacao() {
        return fncSituacao;
    }

    public void setFncSituacao(String fncSituacao) {
        this.fncSituacao = fncSituacao;
    }

    public List<Ativxfuncao> getAtivxfuncaoList() {
        return ativxfuncaoList;
    }

    public void setAtivxfuncaoList(List<Ativxfuncao> ativxfuncaoList) {
        this.ativxfuncaoList = ativxfuncaoList;
    }

    public void addNewAtividade() {
        Ativxfuncao axF = new Ativxfuncao(this);
        ativxfuncaoList.add(axF);
    }

    public void removeAtividade(int index) {
        ativxfuncaoList.remove(index);
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
