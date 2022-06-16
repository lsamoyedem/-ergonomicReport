/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dominios.Avaliacao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import utils.Filtros;

/**
 *
 * @author Lucas Samoyedem
 */
@Stateless
@LocalBean
public class AvaliacaoService {

    @EJB
    private BaseService bs;

    public List<Avaliacao> buscaAvaliacao(List<Filtros> filtros) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT A.* FROM AVALIACAO A ");
        query.append(bs.montaFiltros(filtros));
        return bs.executeNativeQuery(query.toString(), Avaliacao.class);
    }
}
