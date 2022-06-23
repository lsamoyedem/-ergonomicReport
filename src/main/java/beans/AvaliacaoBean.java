package beans;

import dominios.Atividade;
import dominios.Avaliacao;
import dominios.Ciclo;
import dominios.CicloDetalhe;
import dominios.Funcionario;
import dominios.Tarefa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import services.AtividadeService;
import services.AvaliacaoService;
import services.BaseService;
import services.EmpresaService;
import services.FuncionarioService;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Named
@ViewScoped
public class AvaliacaoBean extends BaseCrud<Avaliacao> implements Serializable {

    @EJB
    private FuncionarioService fs;
    @EJB
    private EmpresaService es;
    @EJB
    private AtividadeService ats;
    @EJB
    private AvaliacaoService as;
    @EJB
    private BaseService bs;

    private Integer filtroId;
    private String filtroNome;

    private List<Atividade> atividades = new ArrayList<>();
    private List<Funcionario> funcionario = new ArrayList<>();
    private List<Tarefa> tarefas = new ArrayList<>();

    private BarChartModel barModel;

    @Override
    protected void setup() {
        funcionario = fs.buscaFuncionario(null);
        atividades = ats.buscaAtividade(null);
        getCrudObj().setAvlData(new Date());
    }

    @Override
    public void criaNovoObjeto() {
        super.criaNovoObjeto();
    }

    public void pesquisar() {
        List<Filtros> filtros = new ArrayList<>();
        filtros.add(new Filtros("AVL_ID", filtroId));
        filtros.add(new Filtros("AVL_DESC", filtroNome, true));
        setCrudObjFiltrados(as.buscaAvaliacao(filtros));
        super.pesquisar();
    }

    public void selecionaFuncionario() {
        if (getCrudObj().getAvlCodFunc() == null) {
            atividades = ats.buscaAtividade(null);
        } else {
            atividades = getCrudObj().getAvlCodFunc().getAtividade(getCrudObj().getAvlData());
        }
    }

    @Override
    public void limparPesquisa() {
        super.limparPesquisa();
        filtroId = null;
        filtroNome = null;
    }

    public void createBarModel() {
        Map<Tarefa, Integer> tarefaxTempo = new HashMap<>();
        for (Ciclo ciclo : getCrudObj().getCicloList()) {
            for (CicloDetalhe cicloDetalhe : ciclo.getCicloDetalheList()) {
                Integer valor = tarefaxTempo.get(cicloDetalhe.getCdCodTarefa());
                if (valor == null) {
                    valor = 0;
                }
                tarefaxTempo.put(cicloDetalhe.getCdCodTarefa(), valor + (cicloDetalhe.getCdSegundos()));
            }
        }

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Tarefas");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (Map.Entry<Tarefa, Integer> entry : tarefaxTempo.entrySet()) {
            values.add(entry.getValue());
            labels.add(entry.getKey().getTarDesc() + " (" + entry.getKey().getTarGrauPerigo() + ")");
        }
        barDataSet.setData(values);
        data.setLabels(labels);

        barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");

        barDataSet.setBorderColor("rgb(255, 99, 132)");
        barDataSet.setBorderWidth(1);
        data.addChartDataSet(barDataSet);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Gráfico de tempo por tarefa");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(2);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    public Integer getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(Integer filtroId) {
        this.filtroId = filtroId;
    }

    public String getFiltroNome() {
        return filtroNome;
    }

    public void setFiltroNome(String filtroNome) {
        this.filtroNome = filtroNome;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(List<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    @Override
    public Avaliacao getCrudObj() {
        return super.getCrudObj();
    }

    @Override
    public List<Avaliacao> getCrudObjFiltrados() {
        return super.getCrudObjFiltrados();
    }
}
