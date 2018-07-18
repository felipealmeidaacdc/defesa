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

import model.TipoMovimentacao;
import util.HibernateUtils;

import java.util.List;
import model.Pagamento;
import model.Recebimento;

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
public class TipoMovimentacaoDAO {

    public void salvar(TipoMovimentacao tipoMovimentacao) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(tipoMovimentacao);
        transacao.commit();
        sessao.close();
    } // Fim do método salvar

    public void excluir(TipoMovimentacao tipoMovimentacao) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.delete(tipoMovimentacao);
        transacao.commit();
        sessao.close();
    } // Fim do método excluir

    public TipoMovimentacao consultarPorCodigo(int cdTipoMovimentacao) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        TipoMovimentacao tipoMovimentacao = (TipoMovimentacao) sessao.get(TipoMovimentacao.class, cdTipoMovimentacao);
        sessao.close();

        return tipoMovimentacao;
    } // Fim do método consultarPorCodigo

    public List<TipoMovimentacao> listar() throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoMovimentacao.class);
        List<TipoMovimentacao> tiposMovimentacao = criterio.list();
        sessao.close();

        return tiposMovimentacao;
    } // Fim do método listar

    public List<TipoMovimentacao> consultarPorTipo(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoMovimentacao.class);
        criterio.add(Restrictions.like("tipo", value + "%"));
        criterio.addOrder(Order.asc("tipo"));
        List<TipoMovimentacao> tiposMovimentacao = criterio.list();
        sessao.close();

        return tiposMovimentacao;
    } // Fim do método consultarPorTipo

    public TipoMovimentacao consultarPorTipoUk(String value) throws HibernateException {
        Session sessao = HibernateUtils.getSessionFactory().openSession();
        Criteria criterio = sessao.createCriteria(TipoMovimentacao.class);
        criterio.add(Restrictions.eq("tipo", value));
        TipoMovimentacao tiposMovimentacao = (TipoMovimentacao) criterio.uniqueResult();
        sessao.close();

        return tiposMovimentacao;
    } // Fim do método consultarProTipoUk

    public boolean possuiRelacionamento(Integer cdTipoMovimentacao) {
        try {
            Session sessao = HibernateUtils.getSessionFactory().openSession();
            Criteria criterio = sessao.createCriteria(Pagamento.class);
            criterio.createAlias("tipoMovimentacao", "tm");
            criterio.add(Restrictions.eq("tm.cdTipoMovimentacao", cdTipoMovimentacao));
            criterio.setProjection(Projections.rowCount());
            Long rows = (Long) criterio.uniqueResult();

            criterio = sessao.createCriteria(Recebimento.class);
            criterio.createAlias("tipoMovimentacao", "tm");
            criterio.add(Restrictions.eq("tm.cdTipoMovimentacao", cdTipoMovimentacao));
            criterio.setProjection(Projections.rowCount());
            rows += (Long) criterio.uniqueResult();
            sessao.close();

            return rows > 0L;
        } catch (HibernateException ex) {
            System.err.println("Error: possuiRelacionamento: " + ex);
        }

        return true;
    } // Fim do método possuiRelacionamento

} // Fim da classe TipoMovimentacaoDAO
