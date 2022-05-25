package services;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.Filtros;

/**
 *
 * @author Lucas.S
 */
@Stateless
@LocalBean
public class BaseService extends BaseManager {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "sysdb_oficial")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public String montaFiltros(List<Filtros> filtros) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (filtros != null && !filtros.isEmpty()) {
            for (Filtros filtro : filtros) {
                if (filtro.possuiFiltro()) {
                    if (count > 0) {
                        sb.append(" AND ");
                    }
                    sb.append(filtro.getClausula());
                    count++;
                }
            }
        }
        if (count > 0) {
            sb.insert(0, " WHERE ");
        }
        return sb.toString();
    }
}
