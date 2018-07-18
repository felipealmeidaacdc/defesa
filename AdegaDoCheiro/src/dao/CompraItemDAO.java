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

import model.CompraItem;
import util.HibernateUtils;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Aluno
 */
public class CompraItemDAO {

    public void salvar(CompraItem compraItem) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(compraItem);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar
    
    public void excluir(CompraItem compraItem) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(compraItem);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir

    public CompraItem consultarPorCodigo(int cdCompraItem) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        CompraItem compraItem = (CompraItem) sessao.get(CompraItem.class, cdCompraItem);
        sessao.close();
        
        return compraItem;
    } // Fim do método consultarPorCodigo

    public List<CompraItem> consultarPorCompra(int cdCompra) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(CompraItem.class);
        criterio.createAlias("compra", "c");
        criterio.add(Restrictions.eq("c.cdCompra", cdCompra));
        criterio.addOrder(Order.asc("cdCompraItem"));
        List<CompraItem> compraItens = criterio.list();
        sessao.close();
        
        return compraItens;
    } // Fim do método consultarPorCompra
    
    public List<CompraItem> consultarPorPeriodo(Date ini, Date fim) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        List<CompraItem> compraItens = sessao.createCriteria(CompraItem.class, "ci")
                .createAlias("ci.compra", "c")
                .createAlias("c.fornecedor", "f")
                .add(Restrictions.between("c.dtCompra", ini, fim))
                .addOrder(Order.asc("f.nome"))
                .addOrder(Order.desc("ci.qtdItem"))
                .list();
        sessao.close();
        
        return compraItens;
    } // Fim do método consultarPorPeriodo

} // Fim da classe CompraItem
