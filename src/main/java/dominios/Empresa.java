package dominios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_EMPRESA")
    @SequenceGenerator(name = "GEN_EMPRESA", sequenceName = "GEN_EMPRESA", allocationSize = 1, initialValue = 1)
    @Column(name = "EMP_ID")
    private Integer empId;
    @NotNull
    @Size(max = 100)
    @Column(name = "EMP_RAZAO_SOCIAL")
    private String empRazaoSocial;
    @Size(max = 200)
    @Column(name = "EMP_NOME_FANTASIA")
    private String empNomeFantasia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 14, max = 14)
    @Column(name = "EMP_CNPJ")
    private String empCnpj;
    @Size(max = 150)
    @Column(name = "EMP_ENDERECO")
    private String empEndereco;
    @Column(name = "EMP_NUMERO")
    private Integer empNumero;
    @Size(max = 100)
    @Column(name = "EMP_CIDADE")
    private String empCidade;
    @Size(max = 150)
    @Column(name = "EMP_BAIRRO")
    private String empBairro;
    @Size(max = 2)
    @Column(name = "EMP_UF")
    private String empUf;

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

    public String getEmpEndereco() {
        return empEndereco;
    }

    public void setEmpEndereco(String empEndereco) {
        this.empEndereco = empEndereco;
    }

    public Integer getEmpNumero() {
        return empNumero;
    }

    public void setEmpNumero(Integer empNumero) {
        this.empNumero = empNumero;
    }

    public String getEmpCidade() {
        return empCidade;
    }

    public void setEmpCidade(String empCidade) {
        this.empCidade = empCidade;
    }

    public String getEmpBairro() {
        return empBairro;
    }

    public void setEmpBairro(String empBairro) {
        this.empBairro = empBairro;
    }

    public String getEmpUf() {
        return empUf;
    }

    public void setEmpUf(String empUf) {
        this.empUf = empUf;
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
