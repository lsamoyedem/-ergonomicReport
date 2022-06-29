package beans;

import dominios.Pessoa;
import dominios.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;
import services.UsuarioService;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class UsuarioBean extends BaseCrud<Usuario> implements Serializable {

    @EJB
    private UsuarioService us;

    private String senha;

    private Integer filtroId;
    private String filtroUsuario;

    @PostConstruct
    private void init() {
        limpar();
    }

    public void pesquisar() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("USU_ID", filtroId));
        filtros.add(new Filtros("USU_USUARIO", filtroUsuario, true));
        setCrudObjFiltrados(us.buscaUsuarios(filtros));
        super.pesquisar();
    }

    @Override
    public void salvar() {
        String senhaMd5 = DigestUtils.md5Hex(senha);
        getCrudObj().setUsuSenha(senhaMd5);
        super.salvar();
    }

    @Override
    public void criaNovoObjeto() {
        super.criaNovoObjeto();
        getCrudObj().setUsuCodPessoa(new Pessoa());
    }

    @Override
    public void limparPesquisa() {
        super.limparPesquisa();
        filtroId = null;
        filtroUsuario = null;
    }

    public Integer getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(Integer filtroId) {
        this.filtroId = filtroId;
    }

    public String getFiltroUsuario() {
        return filtroUsuario;
    }

    public void setFiltroUsuario(String filtroUsuario) {
        this.filtroUsuario = filtroUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Usuario getCrudObj() {
        return super.getCrudObj();
    }

    @Override
    public List<Usuario> getCrudObjFiltrados() {
        return super.getCrudObjFiltrados();
    }
}
