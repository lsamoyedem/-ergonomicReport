package services;

import dominios.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Stateless
@LocalBean
public class UsuarioService implements Serializable {

    @EJB
    private BaseService bs;

    public Usuario buscaUsuario(String usuario, String senha) {
        String senhaMd5 = DigestUtils.md5Hex(senha);
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM USUARIO ");
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("USU_USUARIO", usuario));
        filtros.add(new Filtros("USU_SENHA", senhaMd5));
        query.append(bs.montaFiltros(filtros));
        List<Usuario> usuarios = bs.executeNativeQuery(query.toString(), Usuario.class);
        if (usuarios != null && !usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }

    public List<Usuario> buscaUsuarios(List<Filtros> filtros) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM USUARIO ");
        query.append(bs.montaFiltros(filtros));
        return bs.executeNativeQuery(query.toString(), Usuario.class);
    }
}
