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

import model.Venda;
import util.HibernateUtils;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Aluno
 */
public class VendaDAO {

    public void salvar(Venda venda) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(venda);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar

    public void excluir(Venda venda) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(venda);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir

    public Venda consultarVendaPorCliente(int cdCliente) {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Venda.class);
        criterio.createAlias("cliente", "c");
        criterio.add(Restrictions.eq("c.cdCliente", cdCliente));
        criterio.add(Restrictions.eq("recebido", false));
        Venda venda = (Venda) criterio.uniqueResult();
        sessao.close();
        
        return venda;
    } // Fim do método consultarVendaPorCliente

} // Fim da classe VendaDAO
