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

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Aluno
 */
@Entity
public class Pagamento implements Serializable {
    
    // atributos da entidade Pagamento
    @Id
    @GeneratedValue
    private Integer cdPagamento;
    
    @Column(nullable = false)
    private Float vlPagamento;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtPagamento;
    
    @ManyToOne
    @JoinColumn(name = "fkCompra", nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Pagamento_Compra"))
    private Compra compra;
    
    @ManyToOne
    @JoinColumn(name = "fkTipoMovimentacao", nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Pagamento_TipoMovimentacao"))
    private TipoMovimentacao tipoMovimentacao;

    public Integer getCdPagamento() {
        return cdPagamento;
    } // Fim do método getCdPagamento

    public void setCdPagamento(Integer cdPagamento) {
        this.cdPagamento = cdPagamento;
    } // Fim do método setCdPagamento

    public Float getVlPagamento() {
        return vlPagamento;
    } // Fim do método getVlPagamento

    public void setVlPagamento(Float vlPagamento) {
        this.vlPagamento = vlPagamento;
    } // Fim do método setVlPagamento

    public Date getDtPagamento() {
        return dtPagamento;
    } // Fim do método getDtPagamento

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    } // Fim do método setDtPagamento

    public Compra getCompra() {
        return compra;
    } // Fim do método getCompra

    public void setCompra(Compra compra) {
        this.compra = compra;
    } // Fim do método setCompra

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    } // Fim do método getTipoMovimentacao

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    } // Fim do método setTipoMovimentacao

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cdPagamento);
        hash = 59 * hash + Objects.hashCode(this.vlPagamento);
        hash = 59 * hash + Objects.hashCode(this.dtPagamento);
        hash = 59 * hash + Objects.hashCode(this.compra);
        hash = 59 * hash + Objects.hashCode(this.tipoMovimentacao);
        
        return hash;
    } // Fim do método hashCode

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagamento other = (Pagamento) obj;
        if (!Objects.equals(this.cdPagamento, other.cdPagamento)) {
            return false;
        }
        if (!Objects.equals(this.vlPagamento, other.vlPagamento)) {
            return false;
        }
        if (!Objects.equals(this.dtPagamento, other.dtPagamento)) {
            return false;
        }
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        
        return Objects.equals(this.tipoMovimentacao, other.tipoMovimentacao);
    } // Fim do método equals
    
} // Fim da classe Pagamento
