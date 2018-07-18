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

import model.Cliente;
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
public class ClienteDAO {

    public void salvar(Cliente cliente) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(cliente);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar
    
    public void excluir(Cliente cliente) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(cliente);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir

    public Cliente consultarPorCodigo(int cdCliente) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Cliente cliente = (Cliente) sessao.get(Cliente.class, cdCliente);
        sessao.close();
        
        return cliente;
    } // Fim do método consultarPorCodigo

    public List<Cliente> listar() throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Cliente.class);
        criterio.addOrder(Order.asc("nome"));
        List<Cliente> clientes = criterio.list();
        sessao.close();
        
        return clientes;
    } // Fim do código listar

    public List<Cliente> consultarPorLike(String propertyName, String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Cliente.class);
        criterio.add(Restrictions.like(propertyName, value + "%"));
        criterio.addOrder(Order.asc(propertyName));
        criterio.addOrder(Order.asc("nome"));
        List<Cliente> clientes = criterio.list();
        sessao.close();
        
        return clientes;
    } // Fim do método consultarPorLike

    public Cliente consultarPorEqUk(String propertyName, String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Cliente.class);
        criterio.add(Restrictions.eq(propertyName, value));
        Cliente cliente = (Cliente) criterio.uniqueResult();
        sessao.close();
        
        return cliente;
    } // Fim do método consultarPorCpfUk
    
    public boolean possuiRelacionamento(Integer cdCliente) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(Cliente.class);
            criterio.createAlias("cliente", "c");
            criterio.add(Restrictions.eq("c.cdCliente", cdCliente));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.out.println("Error: possuiRelacionamento: " + ex);
        }
        
        return true;
    } // Fim do método possuiRelacionamento

} // Fim da classe ClienteDAO
