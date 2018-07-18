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

import model.Recebimento;
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
public class RecebimentoDAO {

    public void salvar(Recebimento recebimento) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(recebimento);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar

    public Recebimento consultarRecebimentoPorVenda(int cdVenda, boolean pago) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(Recebimento.class);
        criterio.createAlias("venda", "v");
        criterio.add(Restrictions.eq("v.cdVenda", cdVenda));
        criterio.add(Restrictions.eq("v.pago", pago));
        Recebimento recebimento = (Recebimento) criterio.uniqueResult();
        sessao.close();
        
        return recebimento;
    } // Fim do método consultarRecebimentoPorVenda

} // Fim da classe RecebimentoDAO
