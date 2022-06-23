package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
@SuppressWarnings("unchecked")
public class ConverterStorageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List entities = new ArrayList();

    public String addEntity(Object obj) {
        int index = entities.indexOf(obj);
        if (index == -1) { // Objeto ainda não incluído na lista
            entities.add(obj);
            index = entities.size() - 1;
        }
        return String.valueOf(index);
    }

    public Object getEntity(Integer index) {
        try {
            if (entities == null || entities.isEmpty()) {
                return null;
            }
            return entities.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
