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

import model.Fornecedor;
import util.HibernateUtils;

import java.util.List;
import model.Compra;

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
public class FornecedorDAO {

    public void salvar(Fornecedor fornecedor) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(fornecedor);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar

    public void excluir(Fornecedor fornecedor) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(fornecedor);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir

    public Fornecedor consultarPorCodigo(int cdFornecedor) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Fornecedor fornecedor = (Fornecedor) sessao.get(Fornecedor.class, cdFornecedor);
        sessao.close();
        
        return fornecedor;
    } // Fim do método consultarPorCodigo

    public List<Fornecedor> listar() throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Fornecedor.class);
        criterio.addOrder(Order.asc("nome"));
        List<Fornecedor> fornecedores = criterio.list();
        sessao.close();
        
        return fornecedores;
    } // Fim do código listar

    public List<Fornecedor> consultarPorLike(String propertyName, String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Fornecedor.class);
        criterio.add(Restrictions.like(propertyName, value + "%"));
        criterio.addOrder(Order.asc(propertyName));
        List<Fornecedor> fornecedores = criterio.list();
        sessao.close();
        
        return fornecedores;
    } // Fim do método consultarPor

    public Fornecedor consultarPorEqUk(String propertyName, String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Fornecedor.class);
        criterio.add(Restrictions.eq(propertyName, value));
        Fornecedor fornecedor = (Fornecedor) criterio.uniqueResult();
        sessao.close();
        
        return fornecedor;
    } // Fim do método consultarPorEqUk
    
    public boolean possuiRelacionamento(Integer cdFornecedor) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(Compra.class);
            criterio.createAlias("fornecedor", "f");
            criterio.add(Restrictions.eq("f.cdFornecedor", cdFornecedor));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.out.println("Error: possuiRelacionamento: " + ex);
        }
        
        return true;
    } // Fim do método possuiRelacionamento

} // Fim da classe FornecedorDAO
