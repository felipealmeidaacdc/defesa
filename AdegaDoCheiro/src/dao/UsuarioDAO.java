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
import util.HibernateUtils;

import java.util.List;
import model.Compra;
import model.Venda;

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
public class UsuarioDAO {

    public void salvar(Usuario usuario) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(usuario);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar

    public void excluir(Usuario usuario) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(usuario);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir
    
    public Usuario consultarPorCodigo(int cdUsuario) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Usuario usuario = (Usuario) sessao.get(Usuario.class, cdUsuario);
        sessao.close();
        
        return usuario;
    } // Fim do método consultarPorCodigo

    public List<Usuario> listar() throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Usuario.class);
        criterio.addOrder(Order.asc("nome"));
        List<Usuario> usuarios = criterio.list();
        sessao.close();
        
        return usuarios;
    } // Fim do código listar

    public List<Usuario> consultarPorLike(String propertyName, String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Usuario.class);
        criterio.add(Restrictions.like(propertyName, value + "%"));
        criterio.addOrder(Order.asc(propertyName));
        criterio.addOrder(Order.asc("nome"));
        List<Usuario> usuarios = criterio.list();
        sessao.close();
        
        return usuarios;
    } // Fim do método consultarPorLike

    public List<Usuario> consultarPorTipo(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Usuario.class);
        criterio.createAlias("tipoUsuario", "tu");
        criterio.add(Restrictions.eq("tu.tipo", value));
        criterio.addOrder(Order.asc("nome"));
        List<Usuario> usuarios = criterio.list();
        sessao.close();
        
        return usuarios;
    } // Fim do método consultarPorTipo

    public Usuario consultarPorLoginUk(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Usuario.class);
        criterio.add(Restrictions.eq("login", value));
        Usuario usuario = (Usuario) criterio.uniqueResult();
        sessao.close();
        
        return usuario;
    } // Fim do método consultarPorLoginUk
    
    public Usuario validarLogin(String login, String senha) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Usuario.class);
        criterio.add(Restrictions.eq("login", login));
        criterio.add(Restrictions.eq("senha", senha));
        Usuario usuario = (Usuario) criterio.uniqueResult();
        sessao.close();
        
        return usuario;
    } // Fim do método validarLogin
    
    public boolean possuiRelacionamento(Integer cdUsuario) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(Compra.class);
            criterio.createAlias("usuario", "u");
            criterio.add(Restrictions.eq("u.cdUsuario", cdUsuario));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();
            
            criterio = sessao.createCriteria(Venda.class);
            criterio.createAlias("usuario", "u");
            criterio.add(Restrictions.eq("u.cdUsuario", cdUsuario));
            criterio.setProjection(Projections.rowCount());
            rows += (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.out.println("Error: possuiRelacionamento: " + ex);
        }
        
        return true;
    } // Fim do método possuiRelacionamento
    
} // Fim da classe UsuarioDAO
