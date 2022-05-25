/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import services.BaseService;
import utils.JsfUtil;

/**
 *
 * @author Lucas Samoyedem
 * @param <T>
 */
public abstract class BaseCrud<T> {

    @EJB
    private BaseService bs;

    private T crudObj;
    private boolean editando;

    private List<T> crudObjFiltrados;
    private T crudObjSelecionado;

    @PostConstruct
    protected void _setup() {
        criaNovoObjeto();
        setup();
    }

    protected void setup() {
    }

    public void salvar() {
        bs.save(crudObj);
        limpar();
        JsfUtil.success();
    }

    public void criaNovoObjeto() {
        editando = false;
        try {
            crudObj = (T) getCrudClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Class getCrudClass() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class) pt.getActualTypeArguments()[0];
    }

    public void excluir() {
        bs.delete(crudObj);
        limpar();
        JsfUtil.success();
    }

    public void limpar() {
        criaNovoObjeto();
    }

    public void abrirPesquisa() {
        limparPesquisa();
        JsfUtil.showDlg("dlgPesquisa");
    }

    public void pesquisar() {
        if (crudObjFiltrados == null || crudObjFiltrados.isEmpty()) {
            JsfUtil.warn("Nenhum registro encontrado");
        }
    }

    public void limparPesquisa() {
        crudObjFiltrados = new ArrayList<>();
    }

    public void confirmaPesquisa() {
        editando = true;
        if (crudObjSelecionado == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        crudObj = crudObjSelecionado;

        JsfUtil.hideDlg("dlgPesquisa");
    }

    public T getCrudObj() {
        return crudObj;
    }

    public void setCrudObj(T crudObj) {
        this.crudObj = crudObj;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public List<T> getCrudObjFiltrados() {
        return crudObjFiltrados;
    }

    public void setCrudObjFiltrados(List<T> crudObjFiltrados) {
        this.crudObjFiltrados = crudObjFiltrados;
    }

    public T getCrudObjSelecionado() {
        return crudObjSelecionado;
    }

    public void setCrudObjSelecionado(T crudObjSelecionado) {
        this.crudObjSelecionado = crudObjSelecionado;
    }
}
