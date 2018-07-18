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

import model.VendaItem;
import util.HibernateUtils;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Aluno
 */
public class VendaItemDAO {

    public void salvar(VendaItem vendaItem) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(vendaItem);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar
    
    public void excluir(VendaItem vendaItem) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(vendaItem);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir
    
    public VendaItem consultarPorCodigo(int cdVendaItem) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        VendaItem vendaItem = (VendaItem) sessao.get(VendaItem.class, cdVendaItem);
        sessao.close();
        
        return vendaItem;
    } // Fim do método consultarPorCodigo

    public List<VendaItem> consultarPorVenda(int cdVenda) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(VendaItem.class);
        criterio.createAlias("venda", "v");
        criterio.add(Restrictions.eq("v.cdVenda", cdVenda));
        criterio.addOrder(Order.asc("cdVendaItem"));
        List<VendaItem> vendaItens = criterio.list();
        sessao.close();
        
        return vendaItens;
    } // Fim do método consultarPorVenda
    
    public List<VendaItem> consultarPorPeriodo(Date ini, Date fim) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        List<VendaItem> vendaItens = sessao.createCriteria(VendaItem.class, "vi")
                .createAlias("vi.venda", "v")
                .createAlias("v.cliente", "c")
                .add(Restrictions.between("v.dtVenda", ini, fim))
                .addOrder(Order.asc("c.nome"))
                .addOrder(Order.desc("vi.qtdItem"))
                .list();
        sessao.close();
        
        return vendaItens;
    } // Fim do método consultarPorPeriodo

} // Fim da classe VendaItem
