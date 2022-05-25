package dominios;

import java.io.Serializable;
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
@Table(name = "TAREFA")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_TAREFA")
    @SequenceGenerator(name = "GEN_TAREFA", sequenceName = "GEN_TAREFA", allocationSize = 1, initialValue = 1)
    @Column(name = "TAR_ID")
    private Integer tarId;
    @NotNull
    @Size(max = 200)
    @Column(name = "TAR_DESC")
    private String tarDesc;
    @NotNull
    @Column(name = "TAR_GRAU_PERIGO")
    private int tarGrauPerigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "txaCodTarefa", fetch = FetchType.LAZY)
    private List<Tarefaxatividade> tarefaxatividadeList;

    public Tarefa() {
    }

    public Tarefa(Integer tarId) {
        this.tarId = tarId;
    }

    public Tarefa(Integer tarId, String tarDesc, int tarGrauPerigo) {
        this.tarId = tarId;
        this.tarDesc = tarDesc;
        this.tarGrauPerigo = tarGrauPerigo;
    }

    public Integer getTarId() {
        return tarId;
    }

    public void setTarId(Integer tarId) {
        this.tarId = tarId;
    }

    public String getTarDesc() {
        return tarDesc;
    }

    public void setTarDesc(String tarDesc) {
        this.tarDesc = tarDesc;
    }

    public int getTarGrauPerigo() {
        return tarGrauPerigo;
    }

    public void setTarGrauPerigo(int tarGrauPerigo) {
        this.tarGrauPerigo = tarGrauPerigo;
    }

    public List<Tarefaxatividade> getTarefaxatividadeList() {
        return tarefaxatividadeList;
    }

    public void setTarefaxatividadeList(List<Tarefaxatividade> tarefaxatividadeList) {
        this.tarefaxatividadeList = tarefaxatividadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarId != null ? tarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefa)) {
            return false;
        }
        Tarefa other = (Tarefa) object;
        return !((this.tarId == null && other.tarId != null) || (this.tarId != null && !this.tarId.equals(other.tarId)));
    }
}
