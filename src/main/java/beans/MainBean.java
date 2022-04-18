package beans;

import dominios.Endereco;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import services.BaseService;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class MainBean implements Serializable {

    @EJB
    BaseService bs;

    public void criaEndereco() {
        Endereco end = new Endereco();
        end.setEndUf("RS");
        end.setEndNomeCidade("Erechim");
        bs.save(end);
    }
}
