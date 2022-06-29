package beans;

import dominios.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import services.UsuarioService;
import utils.JsfUtil;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioService ls;

    @Inject
    private MainBean mainBean;

    private String usuario;
    private String senha;

    @PostConstruct
    private void init() {
        mainBean.setUsuarioLogado(null);
    }

    public void logar() {
        Usuario user = ls.buscaUsuario(usuario, senha);
        if (user == null) {
            JsfUtil.error("Usuário ou senha incoreto");
        } else {
            try {
                mainBean.setUsuarioLogado(user);
                JsfUtil.redirectAppPath("/index.xhtml");
            } catch (IOException ex) {
            }
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
