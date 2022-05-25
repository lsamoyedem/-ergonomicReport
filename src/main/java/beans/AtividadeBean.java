package beans;

import dominios.Atividade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import services.AtividadeService;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class AtividadeBean extends BaseCrud<Atividade> implements Serializable {

    @EJB
    private AtividadeService as;

    private Integer filtroId;
    private String filtroDescricao;

    public void pesquisar() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("ATV_ID", filtroId));
        filtros.add(new Filtros("ATV_DESC", filtroDescricao, true));
        setCrudObjFiltrados(as.buscaAtividade(filtros));
        super.pesquisar();
    }

    @Override
    public void limparPesquisa() {
        super.limparPesquisa();
        filtroId = null;
        filtroDescricao = null;
    }

    public Integer getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(Integer filtroId) {
        this.filtroId = filtroId;
    }

    public String getFiltroDescricao() {
        return filtroDescricao;
    }

    public void setFiltroDescricao(String filtroDescricao) {
        this.filtroDescricao = filtroDescricao;
    }

    @Override
    public Atividade getCrudObj() {
        return super.getCrudObj(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public List<Atividade> getCrudObjFiltrados() {
        return super.getCrudObjFiltrados(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
