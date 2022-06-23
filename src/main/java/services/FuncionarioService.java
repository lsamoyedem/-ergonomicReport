/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dominios.Funcionario;
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
public class FuncionarioService {

    @EJB
    private BaseService bs;

    public List<Funcionario> buscaFuncionario(List<Filtros> filtros) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT F.* FROM FUNCIONARIO F ");
        query.append("LEFT JOIN PESSOA P ON P.PES_ID = F.FUN_COD_PESSOA ");
        query.append(bs.montaFiltros(filtros));
        return bs.executeNativeQuery(query.toString(), Funcionario.class);
    }
}
