package dominios;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "ATIVXFUNCAO")
public class Ativxfuncao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ATIVXFUNCAO")
    @SequenceGenerator(name = "GEN_ATIVXFUNCAO", sequenceName = "GEN_ATIVXFUNCAO", allocationSize = 1, initialValue = 1)
    @NotNull
    @Column(name = "AXF_ID")
    private Integer axfId;
    @JoinColumn(name = "AXF_COD_ATIVIDADE", referencedColumnName = "ATV_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Atividade axfCodAtividade;
    @JoinColumn(name = "AXF_COD_FUNCAO", referencedColumnName = "FNC_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcao axfCodFuncao;

    public Ativxfuncao() {
    }

    public Ativxfuncao(Funcao axfCodFuncao) {
        this.axfCodFuncao = axfCodFuncao;
    }

    public Ativxfuncao(Integer axfId) {
        this.axfId = axfId;
    }

    public Integer getAxfId() {
        return axfId;
    }

    public void setAxfId(Integer axfId) {
        this.axfId = axfId;
    }

    public Atividade getAxfCodAtividade() {
        return axfCodAtividade;
    }

    public void setAxfCodAtividade(Atividade axfCodAtividade) {
        this.axfCodAtividade = axfCodAtividade;
    }

    public Funcao getAxfCodFuncao() {
        return axfCodFuncao;
    }

    public void setAxfCodFuncao(Funcao axfCodFuncao) {
        this.axfCodFuncao = axfCodFuncao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (axfId != null ? axfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ativxfuncao)) {
            return false;
        }
        Ativxfuncao other = (Ativxfuncao) object;
        return !((this.axfId == null && other.axfId != null) || (this.axfId != null && !this.axfId.equals(other.axfId)));
    }
}
