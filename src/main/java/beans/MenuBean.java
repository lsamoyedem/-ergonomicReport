package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuColumn;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class MenuBean implements Serializable {

    private MenuModel model;

    @PostConstruct
    public void init() {
//        model = new DefaultMenuModel();
//        DefaultSubMenu voidSubmenu = new DefaultSubMenu();
//        voidSubmenu.setLabel("Cadastros");
//
//        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().style("display: none;").build();
//        DefaultMenuColumn column = new DefaultMenuColumn();
//        DefaultMenuItem item = DefaultMenuItem.builder()
//                 .value("Empresa")
//                .outcome("/cadastros/empresa.xhtml")
//                 .icon("ui-icon-home")
//                 .title("cadastro2").build(); 
//        firstSubmenu.getElements().add(item);
//
//        column.getElements().add(firstSubmenu);
//
//        voidSubmenu.getElements().add(column);
//
//        model.getElements().add(voidSubmenu);
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

}
