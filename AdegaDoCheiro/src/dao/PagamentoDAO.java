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

import model.Pagamento;
import util.HibernateUtils;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Aluno
 */
public class PagamentoDAO {

    public void salvar(Pagamento pagamento) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(pagamento);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar

    public Pagamento consultarPagamentoPorCompra(int cdCompra, boolean pago) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Pagamento.class);
        criterio.createAlias("compra", "c");
        criterio.add(Restrictions.eq("c.cdCompra", cdCompra));
        criterio.add(Restrictions.eq("c.pago", pago));
        Pagamento pagamento = (Pagamento) criterio.uniqueResult();
        sessao.close();
        
        return pagamento;
    } // Fim do método consultarPagamentoPorCompra

} // Fim da classe PagamentoDAO
