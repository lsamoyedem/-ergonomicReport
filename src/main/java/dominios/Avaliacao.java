package dominios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_AVALIACAO")
    @SequenceGenerator(name = "GEN_AVALIACAO", sequenceName = "GEN_AVALIACAO", allocationSize = 1, initialValue = 1)
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
    @OrderBy(value = "cclSeq")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cclCodAval", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ciclo> cicloList = new ArrayList<>();

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

    public List<Ciclo> getCicloList() {
        return cicloList;
    }

    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }

    public String getAvlDataFormat() {
        if (avlData != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(avlData);
        }
        return "";
    }

    public int maiorSequenciaCiclo() {
        Integer seq = 0;
        for (Ciclo ciclo : cicloList) {
            if (ciclo.getCclSeq() > seq) {
                seq = ciclo.getCclSeq();
            }
        }
        return seq;
    }

    public void adicionarNovoCiclo() {
        Ciclo ciclo = new Ciclo();
        ciclo.setCclCodAval(this);
        ciclo.setCclSeq(maiorSequenciaCiclo() + 1);
        cicloList.add(ciclo);
    }

    public void replicarCiclo(int index) {
        Ciclo ciclo = new Ciclo();
        ciclo.setCclCodAval(this);
        ciclo.setCclSeq(maiorSequenciaCiclo() + 1);
        Ciclo cicloRep = cicloList.get(index);
        for (CicloDetalhe cicloDet : cicloRep.getCicloDetalheList()) {
            CicloDetalhe cicloDetalhe = new CicloDetalhe();
            cicloDetalhe.setCdCodTarefa(cicloDet.getCdCodTarefa());
            cicloDetalhe.setCdSegundos(cicloDet.getCdSegundos());
            cicloDetalhe.setCdCodCiclo(ciclo);
            ciclo.getCicloDetalheList().add(cicloDetalhe);
        }
        cicloList.add(ciclo);
    }

    public void removerCiclo(int index) {
        cicloList.remove(index);
    }

    public Integer getTotalDeSegundos() {
        Integer total = 0;
        for (Ciclo ciclo : cicloList) {
            for (CicloDetalhe cicloDetalhe : ciclo.getCicloDetalheList()) {
                if (cicloDetalhe.getCdSegundos() != null) {
                    total += cicloDetalhe.getCdSegundos();
                }
            }
        }
        return total;
    }

    public String getTotalSegundosEmDate() {
        Integer total = getTotalDeSegundos();
        if (total != null) {
            int sec = total % 60;
            int min = (total / 60) % 60;
            int hours = (total / 60) / 60;

            String strSec = (sec < 10) ? "0" + Integer.toString(sec) : Integer.toString(sec);
            String strmin = (min < 10) ? "0" + Integer.toString(min) : Integer.toString(min);
            String strHours = (hours < 10) ? "0" + Integer.toString(hours) : Integer.toString(hours);

            return String.format("%s" + ":" + "%s" + ":" + "%s", strHours, strmin, strSec);
        }
        return "00:00:00";
    }

    public Double getGrauDePericulosidadeMedia() {
        Double somaTotal = 0.0;
        for (Ciclo ciclo : cicloList) {
            for (CicloDetalhe cicloDetalhe : ciclo.getCicloDetalheList()) {
                somaTotal += cicloDetalhe.segundosxPerigo();
            }
        }
        if (somaTotal > 0 && getTotalDeSegundos() > 0) {
            Double total = somaTotal / getTotalDeSegundos();
            if (total != null) {
                return new BigDecimal(somaTotal / getTotalDeSegundos()).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
            }
        }
        return 0.0;
    }

    public String getFuncionarioAvaliado() {
        if (avlCodFunc == null) {
            return "Anônimo";
        } else {
            return avlCodFunc.getFunCodPessoa().getPesNome() + " - " + avlCodFunc.getFunId();
        }
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
