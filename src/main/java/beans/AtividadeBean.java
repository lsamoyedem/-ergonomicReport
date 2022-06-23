package beans;

import dominios.Atividade;
import dominios.Tarefa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import services.AtividadeService;
import services.BaseService;
import utils.Filtros;
import utils.JsfUtil;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class AtividadeBean extends BaseCrud<Atividade> implements Serializable {

    @EJB
    private AtividadeService as;
    @EJB
    private BaseService bs;

    private Integer filtroId;
    private String filtroDescricao;

    private List<Tarefa> tarefas = new ArrayList<>();

    @Override
    protected void setup() {
        tarefas = bs.findAll(Tarefa.class, "tarDesc");
    }

    @Override
    public void salvar() {
        if (getCrudObj().getTarefaxatividadeList().isEmpty()) {
            JsfUtil.warn("Adicione pelo menos uma tarefa");
            return;
        }
        super.salvar();
    }

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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
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
