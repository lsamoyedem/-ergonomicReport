package dominios;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import utils.JsfUtil;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "CICLO_DETALHE")
public class CicloDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_CICLO_DETALHE")
    @SequenceGenerator(name = "GEN_CICLO_DETALHE", sequenceName = "GEN_CICLO_DETALHE", allocationSize = 1, initialValue = 1)
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

    @Transient
    private Date horaIni;
    @Transient
    private Date horaFim;

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

    public Date getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(Date horaIni) {
        this.horaIni = horaIni;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public void atualizaSegundos() {
        if (horaIni != null && horaFim != null) {
            if (horaFim.compareTo(horaIni) < 0) {
                cdSegundos = 0;
                JsfUtil.warn("A hora final não pode ser menor que a hora inicial");
                return;
            }
            this.cdSegundos = Integer.valueOf(((Long) ((horaFim.getTime() - horaIni.getTime()) / 1000)).toString());
        }
    }

    public Integer segundosxPerigo() {
        if (cdCodTarefa != null && cdSegundos != null) {
            return cdCodTarefa.getTarGrauPerigo() * cdSegundos;
        }
        return 0;
    }

    public int getGrauDePericulosidade() {
        if (cdCodTarefa != null) {
            return cdCodTarefa.getTarGrauPerigo();
        }
        return 0;
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
