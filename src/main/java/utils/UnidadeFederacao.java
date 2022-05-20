package utils;

/**
 *
 * @author Lucas Samoyedem
 */
public enum UnidadeFederacao {

    RO("RO", "Rondônia"),
    AC("AC", "Acre"),
    AM("AM", "Amazonas"),
    RR("RR", "Roraima"),
    PA("PA", "Pará"),
    AP("AP", "Amapá"),
    TO("TO", "Tocantins"),
    MA("MA", "Maranhão"),
    PI("PI", "Piauí"),
    CE("CE", "Ceará"),
    RN("RN", "Rio Grande do Norte"),
    PB("PB", "Paraíba"),
    PE("PE", "Pernambuco"),
    AL("AL", "Alagoas"),
    SE("SE", "Sergipe"),
    BA("BA", "Bahia"),
    MG("MG", "Minas Gerais"),
    ES("ES", "Espírito Santo"),
    RJ("RJ", "Rio de Janeiro"),
    SP("SP", "São Paulo"),
    PR("PR", "Paraná"),
    SC("SC", "Santa Catarina"),
    RS("RS", "Rio Grande do Sul"),
    MS("MS", "Mato Grosso do Sul"),
    MT("MT", "Mato Grosso"),
    GO("GO", "Goiás"),
    DF("DF", "Distrito Federal");

    private final String cigla;
    private final String nome;

    private UnidadeFederacao(String cigla, String nome) {
        this.cigla = cigla;
        this.nome = nome;
    }

    public String getCigla() {
        return cigla;
    }

    public String getNome() {
        return nome;
    }
}
