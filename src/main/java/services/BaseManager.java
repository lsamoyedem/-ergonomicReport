package services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class BaseManager implements Serializable {

    private static final long serialVersionUID = 1L;

    protected abstract EntityManager getEntityManager();

    public static final String ALIAS_OBJETO = "o";

    public void sendStatementsToDB() {
        getEntityManager().flush();
    }

    public <T> T save(T t) {
        t = getEntityManager().merge(t);
        sendStatementsToDB();
        return t;
    }

    public <T> T find(Class type) {
        return (T) getEntityManager().find(type, 1);
    }

    public <T> T find(Class type, Object id) {
        return (T) getEntityManager().find(type, id);
    }

    public <T> List<T> findAll(Class<T> clazz, String... orderFields) {
        String orderBy = "";
        if (orderFields.length > 0) {
            for (int i = 0; i < orderFields.length; i++) {
                orderFields[i] = String.format("%s.%s", ALIAS_OBJETO, orderFields[i]);
            }
        }
        return getEntityManager().createQuery(String.format("select %s from %s as %s %s", ALIAS_OBJETO, clazz.getSimpleName(), ALIAS_OBJETO, orderBy)).getResultList();
    }

    public int executeUpdate(String strQuery) {
        Query q = configureQuery(strQuery);
        return q.executeUpdate();
    }

    public <T> List<T> executeQuery(String EJBQLQuery) {
        Query query = configureQuery(EJBQLQuery);
        return query.getResultList();
    }

    private Query configureQuery(String EJBQLQuery) {
        Query query = getEntityManager().createQuery(EJBQLQuery);
        return query;
    }

    private Query configureNativeQuery(String sqlQuery) {
        Query query = getEntityManager().createNativeQuery(sqlQuery);
        return query;
    }

    private Query setHints(Map queryHints, Query query) {
        if (queryHints != null) {
            for (Object hintName : queryHints.keySet()) {
                String hintValue = (String) queryHints.get(hintName);
                query = query.setHint((String) hintName, hintValue);
            }
        }
        return query;
    }

    public <T> List<T> executeNativeQuery(String sqlQuery) {
        Query query = configureNativeQuery(sqlQuery);
        return query.getResultList();
    }

    public <T> List<T> executeNativeQuery(String sqlQuery, Class<T> clazz) {
        List<T> list = (List<T>) getEntityManager().createNativeQuery(sqlQuery, clazz).getResultList();
        return list;
    }

    public void executeNativeUpdate(String sqlQuery, Object... parametros) {
        Query query = configureNativeQuery(sqlQuery);
        query.executeUpdate();
        sendStatementsToDB();
    }

    public void executeStoredProcedure(String fullyQualifiedProcedureName, Object... parametros) {
        executeNativeUpdate("BEGIN " + fullyQualifiedProcedureName + "; END;", parametros);
    }

    public void delete(Object t) {
        getEntityManager().remove(getEntityManager().merge(t));
        sendStatementsToDB();
    }
}
