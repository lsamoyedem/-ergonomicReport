package utils;

import java.io.IOException;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

public class JsfUtil {

    public static FacesContext getFC() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getEC() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getEC().getRequest();
    }

    public static String getAppPath() {
        return getEC().getRequestContextPath();
    }

    public static String getCurrentPage() {
        return getFC().getViewRoot().getViewId();
    }

    public static void showDlg(String widgetVarDlg) {
        primeFacesExecute("PF('" + widgetVarDlg + "').show();");
    }

    public static void hideDlg(String... wVars) {
        for (String wvs : wVars) {
            primeFacesExecute("PF('" + wvs + "').hide();");
        }
    }

    public static void reloadPage() {
        primeFacesExecute("location.reload(true);");
    }

    public static void primeFacesExecute(String summary) {
        PrimeFaces.current().executeScript(summary);
    }

    public static void setFocus(String idComponent) {
        primeFacesExecute(String.format("$(function(){PrimeFaces.focus('%s');})", idComponent));
    }

    public static void primeFacesUpdate(String... summary) {
        PrimeFaces.current().ajax().update(summary);
    }

    public static <T> T getBean(Class<T> c) {
        String nomeClasse = c.getSimpleName();
        String primeiraLetraMinuscula = nomeClasse.substring(0, 1).toLowerCase();
        String nomeBeanEL = String.format("#{%s}", nomeClasse.replaceAll("^.", primeiraLetraMinuscula));
        return getBean(c, nomeBeanEL);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> c, String beanName) {
        ValueExpression vex
                = getFC().getApplication().getExpressionFactory().createValueExpression(getFC().getELContext(), beanName, c);
        return (T) vex.getValue(getFC().getELContext());
    }

    public static void refresh() {//F5
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    public static boolean isPage(String nomeDaPagina) {
        return getRequest().getRequestURI().contains(nomeDaPagina);
    }

    public static void info(String summary) {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }

    public static void info(String summary, String detail) {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    public static void info(UIComponent component, String summary) {
        getFC().addMessage(component.getClientId(getFC()),
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }

    public static void warn(String summary) {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null));
    }

    public static void warn(String summary, String detail) {
        getFC().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }

    public static void warn(UIComponent component, String summary) {
        getFC().addMessage(component.getClientId(getFC()),
                new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null));
    }

    public static void success() {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação executada com sucesso", null));
    }

    public static void errorRequired(String summary) {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format("O campo %s é obrigatório", summary), null));
    }

    public static void error(String summary) {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null));
    }

    public static void error(UIComponent component, String summary) {
        getFC().addMessage(component.getClientId(getFC()),
                new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null));
    }

    public static void fatal(String summary) {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, null));
    }

    public static void fatal(String summary, String detail) {
        getFC().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail));
    }

    public static void fatal(UIComponent component, String summary) {
        getFC().addMessage(component.getClientId(getFC()),
                new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, null));
    }

    public static void redirectAppPath(String page) throws IOException {
        if (page == null) {
            page = "";
        }
        getEC().redirect(getAppPath() + page);
    }

    public static void scrollTop() {
        primeFacesExecute("$('html').animate({scrollTop:0}, 'slow');");
    }
}
