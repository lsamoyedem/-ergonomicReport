package beans;

import dominios.Atividade;
import dominios.Funcao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import services.AtividadeService;
import services.FuncaoService;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class FuncaoBean extends BaseCrud<Funcao> implements Serializable {

    @EJB
    private FuncaoService fs;
    @EJB
    private AtividadeService as;

    private Integer filtroId;
    private String filtroDescricao;

    private List<Atividade> atividades = new ArrayList<>();

    @Override
    protected void setup() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("ATV_SITU", "A"));
        atividades = as.buscaAtividade(filtros);
    }

    public void pesquisar() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("FNC_ID", filtroId));
        filtros.add(new Filtros("FNC_DESC", filtroDescricao, true));
        setCrudObjFiltrados(fs.buscaFuncao(filtros));
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

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    @Override
    public Funcao getCrudObj() {
        return super.getCrudObj(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public List<Funcao> getCrudObjFiltrados() {
        return super.getCrudObjFiltrados(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
