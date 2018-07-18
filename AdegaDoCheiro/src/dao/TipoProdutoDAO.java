/*
 * Copyright 2018 Aluno.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;

import model.Produto;
import model.TipoProduto;
import util.HibernateUtils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Aluno
 */
public class TipoProdutoDAO {

    public void salvar(TipoProduto tipoProduto) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(tipoProduto);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar
    
    public void excluir(TipoProduto tipoProduto) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(tipoProduto);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir

    public TipoProduto consultarPorCodigo(int cdTipoProduto) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        TipoProduto tipoProduto = (TipoProduto) sessao.get(TipoProduto.class, cdTipoProduto);
        sessao.close();
        
        return tipoProduto;
    } // Fim do método consultarPorCodigo

    public List<TipoProduto> listar() throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoProduto.class);
        List<TipoProduto> tiposProduto = criterio.list();
        sessao.close();
        
        return tiposProduto;
    } // Fim do método listar

     public List<TipoProduto> consultarPorTipo(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoProduto.class);
        criterio.add(Restrictions.like("tipo", value + "%"));
        criterio.addOrder(Order.asc("tipo"));
        List<TipoProduto> tiposProduto = criterio.list();
        sessao.close();
        
        return tiposProduto;
    } // Fim do método consultarPorTipo

    public TipoProduto consultarPorTipoUk(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoProduto.class);
        criterio.add(Restrictions.eq("tipo", value));
        TipoProduto tipoProduto = (TipoProduto) criterio.uniqueResult();
        sessao.close();
        
        return tipoProduto;
    } // Fim do método consultarProTipoUk
    
    public boolean possuiRelacionamento(Integer cdTipoProduto) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(Produto.class);
            criterio.createAlias("tipoProduto", "tp");
            criterio.add(Restrictions.eq("tp.cdTipoProduto", cdTipoProduto));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.out.println("Error: possuiRelacionamento: " + ex);
        }
        
        return true;
    } // Fim do método possuiRelacionamento
    
} // Fim da classe TipoProdutoDAO
