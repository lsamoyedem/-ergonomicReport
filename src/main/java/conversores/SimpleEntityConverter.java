package conversores;

import beans.ConverterStorageBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import utils.JsfUtil;

@FacesConverter(value = "sec")
public class SimpleEntityConverter implements Converter {

    public SimpleEntityConverter() {
    }

    /**
     * @return Retorna somente os componentes para quais este conversor deve
     * funcionar. Para qualquer outro componente que não esteja nessa lista, o
     * método getAsString() deste conversor retornará a saída do método
     * toString() da instância que está sendo convertida.<br/>
     * Os conversores descendentes poderão acrescentar componentes a essa lista
     * sobrecrevendo este método.
     */
    public List<Class> getCompatibleUIComponents() {
        List<Class> l = new ArrayList<>();
        l.add(UISelectOne.class);
        l.add(UISelectMany.class);
        l.add(PickList.class);
        //l.add(SelectOneMenu.class);
        return l;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String index) {
        if (index == null || index.trim().length() == 0) {
            return null;
        }
        try {
            return getConverterStorageBean().getEntity(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            return index;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        /*
         * Se o componente a partir do qual este conversor estiver sido chamado
         * não constar da lista de componentes compatíveis, então retorna apenas
         * a saída do método toString() da instância que está sendo convertida.
         */
        if (!checkCompatibility(component)) {
            return value.toString();
        }
        return getConverterStorageBean().addEntity(value);
    }

    private ConverterStorageBean getConverterStorageBean() {
        return JsfUtil.getBean(ConverterStorageBean.class, "#{converterStorageBean}");
    }

    private boolean checkCompatibility(UIComponent component) {
        for (Class compatibleUIComp : getCompatibleUIComponents()) {
            if (compatibleUIComp.isInstance(component)) {
                return true;
            }
        }
        return false;
    }
}
