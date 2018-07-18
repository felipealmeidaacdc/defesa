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
import java.lang.reflect.Field;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Aluno
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "tipo", name = "UK_TipoUsuario_Tipo")
})
public class TipoUsuario implements Serializable {

    // Atributos da entidade TipoUsuario
    @Id
    @GeneratedValue
    private Integer cdTipoUsuario;
    
    @Column(length = values.Length.TipoUsuario.TIPO, nullable = false)
    private String tipo;
    
    @Column(nullable = false)
    private Boolean cadUsuario;
    
    @Column(nullable = false)
    private Boolean cadTipoUsuario;
    
    @Column(nullable = false)
    private Boolean cadProduto;
    
    @Column(nullable = false)
    private Boolean cadTipoProduto;
    
    @Column(nullable = false)
    private Boolean cadCliente;
    
    @Column(nullable = false)
    private Boolean cadTipoMovimentacao;
    
    @Column(nullable = false)
    private Boolean cadFornecedor;
    
    @Column(nullable = false)
    private Boolean realizarCompra;
    
    @Column(nullable = false)
    private Boolean realizarVenda;
    
    @Column(nullable = false)
    private Boolean emitirRelatorioCompras;
    
    @Column(nullable = false)
    private Boolean emitirRelatorioVendas;
    
    @Column(nullable = false)
    private Boolean fazerBackup;
    
    @Column(nullable = false)
    private Boolean fazerRestauracao;

    public Integer getCdTipoUsuario() {
        return cdTipoUsuario;
    } // Fim do método getCdTipoUsuario

    public void setCdTipoUsuario(Integer cdTipoUsuario) {
        this.cdTipoUsuario = cdTipoUsuario;
    } // Fim do método setCdTipoUsuario

    public String getTipo() {
        return tipo;
    } // Fim do método getTipo

    public void setTipo(String tipo) {
        this.tipo = tipo;
    } // Fim do método setTipo

    public Boolean getCadUsuario() {
        return cadUsuario;
    } // Fim do método getCadUsuario

    public void setCadUsuario(Boolean cadUsuario) {
        this.cadUsuario = cadUsuario;
    } // Fim do método setCadUsuario

    public Boolean getCadTipoUsuario() {
        return cadTipoUsuario;
    } // Fim do método getCadTipoUsuario

    public void setCadTipoUsuario(Boolean cadTipoUsuario) {
        this.cadTipoUsuario = cadTipoUsuario;
    } // Fim do método setCadTipoUsuario

    public Boolean getCadProduto() {
        return cadProduto;
    } // Fim do método getCadProduto

    public void setCadProduto(Boolean cadProduto) {
        this.cadProduto = cadProduto;
    } // Fim do método setCadProduto

    public Boolean getCadTipoProduto() {
        return cadTipoProduto;
    } // Fim do método getCadTipoProduto

    public void setCadTipoProduto(Boolean cadTipoProduto) {
        this.cadTipoProduto = cadTipoProduto;
    } // Fim do método setCadTipoProduto

    public Boolean getCadCliente() {
        return cadCliente;
    } // Fim do método getCadCliente

    public void setCadCliente(Boolean cadCliente) {
        this.cadCliente = cadCliente;
    } // Fim do método setCadCliente

    public Boolean getCadTipoMovimentacao() {
        return cadTipoMovimentacao;
    } // Fim do método getCadTipoMovimentacao

    public void setCadTipoMovimentacao(Boolean cadTipoMovimentacao) {
        this.cadTipoMovimentacao = cadTipoMovimentacao;
    } // Fim do método setCadTipoMovimentacao

    public Boolean getCadFornecedor() {
        return cadFornecedor;
    } // Fim do método getCadFornecedor

    public void setCadFornecedor(Boolean cadFornecedor) {
        this.cadFornecedor = cadFornecedor;
    } // Fim do método setCadFornecedor

    public Boolean getRealizarCompra() {
        return realizarCompra;
    } // Fim do método getRealizarCompra

    public void setRealizarCompra(Boolean realizarCompra) {
        this.realizarCompra = realizarCompra;
    } // Fim do método setRealizarCompra

    public Boolean getRealizarVenda() {
        return realizarVenda;
    } // Fim do método getRealizarVenda

    public void setRealizarVenda(Boolean realizarVenda) {
        this.realizarVenda = realizarVenda;
    } // Fim do método setRealizarVenda

    public Boolean getEmitirRelatorioCompras() {
        return emitirRelatorioCompras;
    } // Fim do método getEmitirRelatorioCompras

    public void setEmitirRelatorioCompras(Boolean emitirRelatorioCompras) {
        this.emitirRelatorioCompras = emitirRelatorioCompras;
    } // Fim do método setEmitirRelatorioCompras

    public Boolean getEmitirRelatorioVendas() {
        return emitirRelatorioVendas;
    } // Fim do método getEmitirRelatorioVendas

    public void setEmitirRelatorioVendas(Boolean emitirRelatorioVendas) {
        this.emitirRelatorioVendas = emitirRelatorioVendas;
    } // Fim do método setEmitirRelatorioVendas

    public Boolean getFazerBackup() {
        return fazerBackup;
    } // Fim do método getFazerBackup

    public void setFazerBackup(Boolean fazerBackup) {
        this.fazerBackup = fazerBackup;
    } // Fim do método setFazerBackup

    public Boolean getFazerRestauracao() {
        return fazerRestauracao;
    } // Fim do método getFazerRestauracao

    public void setFazerRestauracao(Boolean fazerRestauracao) {
        this.fazerRestauracao = fazerRestauracao;
    } // Fim do método setFazerRestauracao
    
    public void setAllBooleanFields(Boolean value) {
        for (Field f : this.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                
                if (Boolean.class.isAssignableFrom(f.getType())) {
                    f.set(this, value);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                System.out.println("Error: setAdmin: " + ex);
            }
        }
    } // Fim do método setAdmin

    @Override
    public String toString() {
        return tipo;
    } // Fim do método toString

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.cdTipoUsuario);
        hash = 29 * hash + Objects.hashCode(this.tipo);
        hash = 29 * hash + Objects.hashCode(this.cadUsuario);
        hash = 29 * hash + Objects.hashCode(this.cadTipoUsuario);
        hash = 29 * hash + Objects.hashCode(this.cadProduto);
        hash = 29 * hash + Objects.hashCode(this.cadTipoProduto);
        hash = 29 * hash + Objects.hashCode(this.cadCliente);
        hash = 29 * hash + Objects.hashCode(this.cadTipoMovimentacao);
        hash = 29 * hash + Objects.hashCode(this.cadFornecedor);
        hash = 29 * hash + Objects.hashCode(this.realizarCompra);
        hash = 29 * hash + Objects.hashCode(this.realizarVenda);
        hash = 29 * hash + Objects.hashCode(this.emitirRelatorioCompras);
        hash = 29 * hash + Objects.hashCode(this.emitirRelatorioVendas);
        hash = 29 * hash + Objects.hashCode(this.fazerBackup);
        hash = 29 * hash + Objects.hashCode(this.fazerRestauracao);
        
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
        final TipoUsuario other = (TipoUsuario) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.cdTipoUsuario, other.cdTipoUsuario)) {
            return false;
        }
        if (!Objects.equals(this.cadUsuario, other.cadUsuario)) {
            return false;
        }
        if (!Objects.equals(this.cadTipoUsuario, other.cadTipoUsuario)) {
            return false;
        }
        if (!Objects.equals(this.cadProduto, other.cadProduto)) {
            return false;
        }
        if (!Objects.equals(this.cadTipoProduto, other.cadTipoProduto)) {
            return false;
        }
        if (!Objects.equals(this.cadCliente, other.cadCliente)) {
            return false;
        }
        if (!Objects.equals(this.cadTipoMovimentacao, other.cadTipoMovimentacao)) {
            return false;
        }
        if (!Objects.equals(this.cadFornecedor, other.cadFornecedor)) {
            return false;
        }
        if (!Objects.equals(this.realizarCompra, other.realizarCompra)) {
            return false;
        }
        if (!Objects.equals(this.realizarVenda, other.realizarVenda)) {
            return false;
        }
        if (!Objects.equals(this.emitirRelatorioCompras, other.emitirRelatorioCompras)) {
            return false;
        }
        if (!Objects.equals(this.emitirRelatorioVendas, other.emitirRelatorioVendas)) {
            return false;
        }
        if (!Objects.equals(this.fazerBackup, other.fazerBackup)) {
            return false;
        }
        
        return Objects.equals(this.fazerRestauracao, other.fazerRestauracao);
    } // Fim do método equals
    
} // Fim da classe TipoUsuario
