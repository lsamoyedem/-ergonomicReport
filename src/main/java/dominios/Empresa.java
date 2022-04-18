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
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas Samoyedem
 */
@Entity
@Table(name = "EMPRESA")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "EMP_ID")
    private Integer empId;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMP_RAZAO_SOCIAL")
    private String empRazaoSocial;
    @Size(max = 200)
    @Column(name = "EMP_NOME_FANTASIA")
    private String empNomeFantasia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "EMP_CNPJ")
    private String empCnpj;
    @JoinColumn(name = "EMP_COD_ENDERECO", referencedColumnName = "END_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Endereco empCodEndereco;

    public Empresa() {
    }

    public Empresa(Integer empId) {
        this.empId = empId;
    }

    public Empresa(Integer empId, String empRazaoSocial, String empCnpj) {
        this.empId = empId;
        this.empRazaoSocial = empRazaoSocial;
        this.empCnpj = empCnpj;
    }

    public Empresa(Endereco empCodEndereco) {
        this.empCodEndereco = empCodEndereco;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpRazaoSocial() {
        return empRazaoSocial;
    }

    public void setEmpRazaoSocial(String empRazaoSocial) {
        this.empRazaoSocial = empRazaoSocial;
    }

    public String getEmpNomeFantasia() {
        return empNomeFantasia;
    }

    public void setEmpNomeFantasia(String empNomeFantasia) {
        this.empNomeFantasia = empNomeFantasia;
    }

    public String getEmpCnpj() {
        return empCnpj;
    }

    public void setEmpCnpj(String empCnpj) {
        this.empCnpj = empCnpj;
    }

    public Endereco getEmpCodEndereco() {
        return empCodEndereco;
    }

    public void setEmpCodEndereco(Endereco empCodEndereco) {
        this.empCodEndereco = empCodEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        return !((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId)));
    }
}
