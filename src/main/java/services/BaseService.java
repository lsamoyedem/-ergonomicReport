package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elvis
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
}
