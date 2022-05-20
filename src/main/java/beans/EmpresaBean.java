package beans;

import dominios.Empresa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import utils.UnidadeFederacao;

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
        empresa = new Empresa();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
