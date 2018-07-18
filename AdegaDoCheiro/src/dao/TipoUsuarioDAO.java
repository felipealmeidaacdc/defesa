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

import model.Usuario;
import model.TipoUsuario;
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
public class TipoUsuarioDAO {
    
    public void salvar(TipoUsuario tipoUsuario) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(tipoUsuario);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar
    
    public void excluir(TipoUsuario tipoUsuario) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(tipoUsuario);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir

    public TipoUsuario consultarPorCodigo(int cdTipoUsuario) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        TipoUsuario tipoUsuario = (TipoUsuario) sessao.get(TipoUsuario.class, cdTipoUsuario);
        sessao.close();
        
        return tipoUsuario;
    } // Fim do método consultarPorCodigo

    public List<TipoUsuario> listar() throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoUsuario.class);
        List<TipoUsuario> tiposUsuario = criterio.list();
        sessao.close();
        
        return tiposUsuario;
    } // Fim do método listar

     public List<TipoUsuario> consultarPorTipo(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoUsuario.class);
        criterio.add(Restrictions.like("tipo", value + "%"));
        criterio.addOrder(Order.asc("tipo"));
        List<TipoUsuario> tiposUsuario = criterio.list();
        sessao.close();
        
        return tiposUsuario;
    } // Fim do método consultarPorTipo

    public TipoUsuario consultarPorTipoUk(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoUsuario.class);
        criterio.add(Restrictions.eq("tipo", value));
        TipoUsuario tiposUsuario = (TipoUsuario) criterio.uniqueResult();
        sessao.close();
        
        return tiposUsuario;
    } // Fim do método consultarPotTipoUk
    
    public boolean possuiRelacionamento(Integer cdTipoUsuario) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(Usuario.class);
            criterio.createAlias("tipoUsuario", "tu");
            criterio.add(Restrictions.eq("tu.cdTipoUsuario", cdTipoUsuario));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.out.println("Error: possuiRelacionamento: " + ex);
        }
        
        return true;
    } // Fim do método possuiRelacionamento
    
} // Fim da classe TipoUsuarioDAO
