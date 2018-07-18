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
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Aluno
 */
@Entity
public class VendaItem implements Serializable {

    // atributos da entidade VendaItem
    @Id
    @GeneratedValue
    private Integer cdVendaItem;

    @Column(nullable = false)
    private Integer qtdItem;

    @Column(nullable = false)
    private Float vlItem;

    @ManyToOne
    @JoinColumn(name = "fkProduto", nullable = false,
                foreignKey = @ForeignKey(name = "FK_VendaItem_Produto"))
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fkVenda", nullable = false,
                foreignKey = @ForeignKey(name = "FK_VendaItem_Venda"))
    private Venda venda;

    public Integer getCdVendaItem() {
        return cdVendaItem;
    } // Fim do método getCdVendaItem

    public void setCdVendaItem(Integer cdVendaItem) {
        this.cdVendaItem = cdVendaItem;
    } // Fim do método setCdVendaItem

    public Integer getQtdItem() {
        return qtdItem;
    } // Fim do método getQtdItem

    public void setQtdItem(Integer qtdItem) {
        this.qtdItem = qtdItem;
    } // Fim do método setQtdItem

    public Float getVlItem() {
        return vlItem;
    } // Fim do método getVlItem

    public void setVlItem(Float vlItem) {
        this.vlItem = vlItem;
    } // Fim do método setVlItem

    public Produto getProduto() {
        return produto;
    } // Fim do método getProduto

    public void setProduto(Produto produto) {
        this.produto = produto;
    } // Fim do método setProduto

    public Venda getVenda() {
        return venda;
    } // Fim do método getVenda

    public void setVenda(Venda venda) {
        this.venda = venda;
    } // Fim do método setVenda

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.cdVendaItem);
        hash = 29 * hash + Objects.hashCode(this.qtdItem);
        hash = 29 * hash + Objects.hashCode(this.vlItem);
        hash = 29 * hash + Objects.hashCode(this.produto);
        hash = 29 * hash + Objects.hashCode(this.venda);
        
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
        final VendaItem other = (VendaItem) obj;
        if (!Objects.equals(this.cdVendaItem, other.cdVendaItem)) {
            return false;
        }
        if (!Objects.equals(this.qtdItem, other.qtdItem)) {
            return false;
        }
        if (!Objects.equals(this.vlItem, other.vlItem)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        
        return Objects.equals(this.venda, other.venda);
    } // Fim do método equals

} // Fim da classe VendaItem
