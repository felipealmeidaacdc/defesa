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
import util.HibernateUtils;

import java.util.List;
import model.CompraItem;
import model.VendaItem;

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
public class ProdutoDAO {

    public void salvar(Produto produto) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(produto);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar
    
    public void excluir(Produto produto) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(produto);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir
    
    public Produto consultarPorCodigo(int cdProduto) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Produto produto = (Produto) sessao.get(Produto.class, cdProduto);
        sessao.close();

        return produto;
    } // Fim do método consultarPorCodigo

    public List<Produto> listar() throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Produto.class);
        criterio.addOrder(Order.asc("nome"));
        List<Produto> produtos = criterio.list();
        sessao.close();

        return produtos;
    } // Fim do código listar

    public List<Produto> consultarPorLike(String propertyName, String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Produto.class);
        criterio.add(Restrictions.like(propertyName, value + "%"));
        criterio.addOrder(Order.asc(propertyName));
        criterio.addOrder(Order.asc("nome"));
        List<Produto> produtos = criterio.list();
        sessao.close();

        return produtos;
    } // Fim do método consultarPorLike

    public List<Produto> consultarPorTipo(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Produto.class);
        criterio.createAlias("tipoProduto", "tp");
        criterio.add(Restrictions.eq("tp.tipo", value));
        criterio.addOrder(Order.asc("nome"));
        List<Produto> produtos = criterio.list();
        sessao.close();

        return produtos;
    } // Fim do método consultarPorTipo

    public Produto consultarPorEqUk(String propertyName, String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Produto.class);
        criterio.add(Restrictions.eq(propertyName, value));
        Produto produto = (Produto) criterio.uniqueResult();
        sessao.close();

        return produto;
    } // Fim do método consultarPorEqUk
    
    public boolean possuiRelacionamento(Integer cdProduto) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(CompraItem.class);
            criterio.createAlias("produto", "p");
            criterio.add(Restrictions.eq("p.cdProduto", cdProduto));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();
            
            criterio = sessao.createCriteria(VendaItem.class);
            criterio.createAlias("produto", "p");
            criterio.add(Restrictions.eq("p.cdProduto", cdProduto));
            criterio.setProjection(Projections.rowCount());
            rows += (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.err.println("Error: possuiRelacionamento: " + ex);
        }
        
        return true;
    } // Fim do método possuiRelacionamento

} // Fim da classe ProdutoDAO
