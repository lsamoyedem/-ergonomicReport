package beans;

import dominios.Tarefa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import services.TarefaService;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class TarefaBean extends BaseCrud<Tarefa> implements Serializable {

    @EJB
    private TarefaService ts;

    private Integer filtroId;
    private String filtroDescricao;

    public void pesquisar() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("TAR_ID", filtroId));
        filtros.add(new Filtros("TAR_DESC", filtroDescricao, true));
        setCrudObjFiltrados(ts.buscaTarefa(filtros));
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
    public Tarefa getCrudObj() {
        return super.getCrudObj();
    }

    @Override
    public List<Tarefa> getCrudObjFiltrados() {
        return super.getCrudObjFiltrados();
    }
}
