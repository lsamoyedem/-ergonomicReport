package beans;

import dominios.Empresa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import services.BaseService;
import services.EmpresaService;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class EmpresaBean extends BaseCrud<Empresa> implements Serializable {

    @EJB
    private BaseService bs;
    @EJB
    private EmpresaService es;

    private Integer filtroId;
    private String filtroRazaoSocial;

    @PostConstruct
    private void init() {
        limpar();
    }

    public void pesquisar() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("EMP_ID", filtroId));
        filtros.add(new Filtros("EMP_RAZAO_SOCIAL", filtroRazaoSocial, true));
        setCrudObjFiltrados(es.buscaEmpresa(filtros));
        super.pesquisar();
    }

    @Override
    public void limparPesquisa() {
        super.limparPesquisa();
        filtroId = null;
        filtroRazaoSocial = null;
    }

    public Integer getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(Integer filtroId) {
        this.filtroId = filtroId;
    }

    public String getFiltroRazaoSocial() {
        return filtroRazaoSocial;
    }

    public void setFiltroRazaoSocial(String filtroRazaoSocial) {
        this.filtroRazaoSocial = filtroRazaoSocial;
    }

    @Override
    public Empresa getCrudObj() {
        return super.getCrudObj();
    }

    @Override
    public List<Empresa> getCrudObjFiltrados() {
        return super.getCrudObjFiltrados();
    }
}
