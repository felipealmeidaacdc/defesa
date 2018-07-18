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
public class Recebimento implements Serializable {
    
    // atributos da entidade Recebimento
    @Id
    @GeneratedValue
    private Integer cdRecebimento;
    
    @Column(nullable = false)
    private Float vlRecebimento;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtRecebimento;
    
    @ManyToOne
    @JoinColumn(name = "fkVenda", nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Recebimento_Venda"))
    private Venda venda;
    
    @ManyToOne
    @JoinColumn(name = "fkTipoMovimentacao", nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Recebimento_TipoMovimentacao"))
    private TipoMovimentacao tipoMovimentacao;

    public Integer getCdRecebimento() {
        return cdRecebimento;
    } // Fim do método getCdRecebimento

    public void setCdRecebimento(Integer cdRecebimento) {
        this.cdRecebimento = cdRecebimento;
    } // Fim do método setCdRecebimento

    public Float getVlRecebimento() {
        return vlRecebimento;
    } // Fim do método getVlRecebimento

    public void setVlRecebimento(Float vlRecebimento) {
        this.vlRecebimento = vlRecebimento;
    } // Fim do método setVlRecebimento

    public Date getDtRecebimento() {
        return dtRecebimento;
    } // Fim do método getDtRecebimento

    public void setDtRecebimento(Date dtRecebimento) {
        this.dtRecebimento = dtRecebimento;
    } // Fim do método setDtRecebimento

    public Venda getVenda() {
        return venda;
    } // Fim do método getVenda

    public void setVenda(Venda venda) {
        this.venda = venda;
    } // Fim do método setVenda

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    } // Fim do método getTipoMovimentacao

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    } // Fim do método setTipoMovimentacao

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cdRecebimento);
        hash = 59 * hash + Objects.hashCode(this.vlRecebimento);
        hash = 59 * hash + Objects.hashCode(this.dtRecebimento);
        hash = 59 * hash + Objects.hashCode(this.venda);
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
        final Recebimento other = (Recebimento) obj;
        if (!Objects.equals(this.cdRecebimento, other.cdRecebimento)) {
            return false;
        }
        if (!Objects.equals(this.vlRecebimento, other.vlRecebimento)) {
            return false;
        }
        if (!Objects.equals(this.dtRecebimento, other.dtRecebimento)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        
        return Objects.equals(this.tipoMovimentacao, other.tipoMovimentacao);
    } // Fim do método equals
    
} // Fim da classe Recebimento
