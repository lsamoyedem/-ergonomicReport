package beans;

import dominios.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import utils.JsfUtil;
import utils.UnidadeFederacao;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@SessionScoped
public class MainBean implements Serializable {

    private List<UnidadeFederacao> unidadeFederacao = Arrays.asList(UnidadeFederacao.values());
    private Usuario usuarioLogado;

    public void validaAcessoPagina() throws IOException {
        if (!JsfUtil.isPage("login") && usuarioLogado == null) {
            JsfUtil.redirectAppPath("/login.xhtml");
        }
        if (JsfUtil.isPage("usuario") && !usuarioLogado.getUsuAdm()) {
            JsfUtil.redirectAppPath("/index.xhtml");
        }
    }

    public List<UnidadeFederacao> getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(List<UnidadeFederacao> unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
