package beans;

import dominios.Empresa;
import dominios.Funcao;
import dominios.Funcionario;
import dominios.Funcionarioxfuncao;
import dominios.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import services.EmpresaService;
import services.FuncaoService;
import services.FuncionarioService;
import utils.Filtros;
import utils.JsfUtil;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class FuncionarioBean extends BaseCrud<Funcionario> implements Serializable {

    @EJB
    private FuncionarioService fs;
    @EJB
    private EmpresaService es;
    @EJB
    private FuncaoService fcs;

    private Integer filtroId;
    private String filtroNome;

    private List<Empresa> empresas = new ArrayList<>();
    private List<Funcao> funcoes = new ArrayList<>();

    @Override
    protected void setup() {
        empresas = es.buscaEmpresa(null);
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("FNC_SITUACAO", "A"));
        funcoes = fcs.buscaFuncao(filtros);
    }

    @Override
    public void salvar() {
        if (getCrudObj().getFuncionarioxfuncaoList().isEmpty()) {
            JsfUtil.warn("Adicione pelo menos uma função");
            return;
        }
        for (Funcionarioxfuncao fxf : getCrudObj().getFuncionarioxfuncaoList()) {
            if (fxf.getFxfDataFim() != null && fxf.getFxfDataIni().compareTo(fxf.getFxfDataFim()) > 0) {
                JsfUtil.warn("A data inicial não pode ser maior que a data final");
                return;
            }
        }
        super.salvar();
    }

    @Override
    public void criaNovoObjeto() {
        super.criaNovoObjeto();
        getCrudObj().setFunCodPessoa(new Pessoa());
    }

    public void pesquisar() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("F.FUN_ID", filtroId));
        filtros.add(new Filtros("P.PES_NOME", filtroNome, true));
        setCrudObjFiltrados(fs.buscaFuncionario(filtros));
        super.pesquisar();
    }

    @Override
    public void limparPesquisa() {
        super.limparPesquisa();
        filtroId = null;
        filtroNome = null;
    }

    public Integer getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(Integer filtroId) {
        this.filtroId = filtroId;
    }

    public String getFiltroNome() {
        return filtroNome;
    }

    public void setFiltroNome(String filtroNome) {
        this.filtroNome = filtroNome;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<Funcao> funcoes) {
        this.funcoes = funcoes;
    }

    @Override
    public Funcionario getCrudObj() {
        return super.getCrudObj();
    }

    @Override
    public List<Funcionario> getCrudObjFiltrados() {
        return super.getCrudObjFiltrados();
    }
}
