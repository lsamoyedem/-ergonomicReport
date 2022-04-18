package beans;

import dominios.Empresa;
import dominios.Endereco;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class EmpresaBean implements Serializable {
    
    private Empresa empresa;
    
    @PostConstruct
    private void init(){
        empresa = new Empresa(new Endereco());
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
