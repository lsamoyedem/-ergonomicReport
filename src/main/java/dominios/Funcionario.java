package dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_FUNCIONARIO")
    @SequenceGenerator(name = "GEN_FUNCIONARIO", sequenceName = "GEN_FUNCIONARIO", allocationSize = 1, initialValue = 1)
    @NotNull
    @Column(name = "FUN_ID")
    private Integer funId;
    @Size(max = 11)
    @Column(name = "FUN_PIS")
    private String funPis;
    @Column(name = "FUN_DATA_ADM")
    @Temporal(TemporalType.DATE)
    private Date funDataAdm;
    @Column(name = "FUN_DATA_DEMI")
    @Temporal(TemporalType.DATE)
    private Date funDataDemi;
    @JoinColumn(name = "FUN_COD_EMP", referencedColumnName = "EMP_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresa funCodEmp;
    @JoinColumn(name = "FUN_COD_PESSOA", referencedColumnName = "PES_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa funCodPessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fxfCodFunc", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Funcionarioxfuncao> funcionarioxfuncaoList = new ArrayList<>();

    public Funcionario() {
    }

    public Funcionario(Integer funId) {
        this.funId = funId;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getFunPis() {
        return funPis;
    }

    public void setFunPis(String funPis) {
        this.funPis = funPis;
    }

    public Date getFunDataAdm() {
        return funDataAdm;
    }

    public void setFunDataAdm(Date funDataAdm) {
        this.funDataAdm = funDataAdm;
    }

    public Date getFunDataDemi() {
        return funDataDemi;
    }

    public void setFunDataDemi(Date funDataDemi) {
        this.funDataDemi = funDataDemi;
    }

    public Empresa getFunCodEmp() {
        return funCodEmp;
    }

    public void setFunCodEmp(Empresa funCodEmp) {
        this.funCodEmp = funCodEmp;
    }

    public Pessoa getFunCodPessoa() {
        return funCodPessoa;
    }

    public void setFunCodPessoa(Pessoa funCodPessoa) {
        this.funCodPessoa = funCodPessoa;
    }

    public List<Funcionarioxfuncao> getFuncionarioxfuncaoList() {
        return funcionarioxfuncaoList;
    }

    public void setFuncionarioxfuncaoList(List<Funcionarioxfuncao> funcionarioxfuncaoList) {
        this.funcionarioxfuncaoList = funcionarioxfuncaoList;
    }

    public void addNewFuncao() {
        Funcionarioxfuncao fxf = new Funcionarioxfuncao(this);
        this.funcionarioxfuncaoList.add(fxf);
    }

    public void removeFuncao(int index) {
        this.funcionarioxfuncaoList.remove(index);
    }

    public List<Atividade> getAtividade(Date data) {
        for (Funcionarioxfuncao fxf : funcionarioxfuncaoList) {
            if (data.compareTo(fxf.getFxfDataIni()) >= 0 && (fxf.getFxfDataFim() == null || data.compareTo(fxf.getFxfDataFim()) <= 0)) {
                return fxf.getFxfCodFuncao().getAtivxfuncaoList().stream().map(f -> f.getAxfCodAtividade()).collect(Collectors.toList());
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funId != null ? funId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        return !((this.funId == null && other.funId != null) || (this.funId != null && !this.funId.equals(other.funId)));
    }
}
