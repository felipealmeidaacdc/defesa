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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Aluno
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "nuProduto", name = "UK_Produto_NuProduto")
})
public class Produto implements Serializable {

    // atributos da entidade Produto
    @Id
    @GeneratedValue
    private Integer cdProduto;
    
    @Column(length = values.Length.Produto.NOME, nullable = false)
    private String nome;
    
    @Column(length = values.Length.Produto.NU_PRODUTO, nullable = false)
    private String nuProduto;
    
    @Column(nullable = false)
    private Integer qtdProduto;

    @Column(nullable = false)
    private Float vlVenda;
    
    @Column(nullable = false)
    private Float vlCompra;

    @ManyToOne
    @JoinColumn(name = "fkTipoProduto",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_Produto_TipoProduto"))
    private TipoProduto tipoProduto;
    
//    @OneToMany(mappedBy = "produto", targetEntity = CompraItem.class, 
//            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<CompraItem> compraItens;

    public Integer getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNuProduto() {
        return nuProduto;
    }

    public void setNuProduto(String nuProduto) {
        this.nuProduto = nuProduto;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Float getVlVenda() {
        return vlVenda;
    }

    public void setVlVenda(Float vlVenda) {
        this.vlVenda = vlVenda;
    }

    public Float getVlCompra() {
        return vlCompra;
    }

    public void setVlCompra(Float vlCompra) {
        this.vlCompra = vlCompra;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    @Override
    public String toString() {
        return "nome: " + nome + ", tipo: " + tipoProduto.getTipo() + 
                ", número: " + nuProduto + ", qtd: " + qtdProduto + 
                ", vl. de venda: " + vlVenda + ", vl. de compra: " + vlCompra;
    }

} // Fim da classe Produto
