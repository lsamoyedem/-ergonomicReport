package beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import services.BaseService;
import utils.UnidadeFederacao;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@SessionScoped
public class MainBean implements Serializable {

    @EJB
    BaseService bs;

    private List<UnidadeFederacao> unidadeFederacao = Arrays.asList(UnidadeFederacao.values());

    public List<UnidadeFederacao> getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(List<UnidadeFederacao> unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }
}
