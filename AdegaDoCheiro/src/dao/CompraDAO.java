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

import model.Compra;
import model.CompraItem;
import model.Pagamento;
import util.HibernateUtils;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Aluno
 */
public class CompraDAO {

    public void salvar(Compra compra) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(compra);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar
    
    public void excluir(Compra compra) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(compra);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir
    
    public Compra consultarCompraPorFornecedor(int cdFornecedor) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Compra.class);
        criterio.createAlias("fornecedor", "f");
        criterio.add(Restrictions.eq("f.cdFornecedor", cdFornecedor));
        criterio.add(Restrictions.eq("pago", false));
        Compra compra = (Compra) criterio.uniqueResult();
        sessao.close();
        
        return compra;
    } // Fim do método consultarCompraPorFornecedor
    
    public boolean possuiRelacionamento(Integer cdCompra) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(Pagamento.class);
            criterio.createAlias("compra", "c");
            criterio.add(Restrictions.eq("c.cdCompra", cdCompra));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();
            
            criterio = sessao.createCriteria(CompraItem.class);
            criterio.createAlias("compra", "c");
            criterio.add(Restrictions.eq("c.cdCompra", cdCompra));
            criterio.setProjection(Projections.rowCount());
            rows += (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.out.println("Error: possuiRelacionamento: " + ex);
        }
        
        return true;
    } // Fim do método possuiRelacionamento

} // Fim da classe CompraDAO
