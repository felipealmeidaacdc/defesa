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
public class Venda implements Serializable {

    // atributos da entidade Venda
    @Id
    @GeneratedValue
    private Integer cdVenda;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtVenda;

    @Column(nullable = false)
    private Float vlTotalVenda;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean recebido = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "fkCliente", nullable = false,
                foreignKey = @ForeignKey(name = "FK_Venda_Cliente"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fkUsuario", nullable = false,
                foreignKey = @ForeignKey(name = "FK_Venda_Usuario"))
    private Usuario usuario;

    public Integer getCdVenda() {
        return cdVenda;
    } // Fim do método getCdVenda

    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
    } // Fim do método setCdVenda

    public Date getDtVenda() {
        return dtVenda;
    } // Fim do método getDtVenda

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    } // Fim do método setDtVenda

    public Float getVlTotalVenda() {
        return vlTotalVenda;
    } // Fim do método getVlTotalVenda

    public void setVlTotalVenda(Float vlTotalVenda) {
        this.vlTotalVenda = vlTotalVenda;
    } // Fim do método setVlTotalVenda

    public Boolean getRecebido() {
        return recebido;
    } // Fim do método getRecebido

    public void setRecebido(Boolean recebido) {
        this.recebido = recebido;
    } // Fim do método setRecebido

    public Cliente getCliente() {
        return cliente;
    } // Fim do método getCliente

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    } // Fim do método setCliente

    public Usuario getUsuario() {
        return usuario;
    } // Fim do método getUsuario

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    } // Fim do método setUsuario

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.cdVenda);
        hash = 53 * hash + Objects.hashCode(this.dtVenda);
        hash = 53 * hash + Objects.hashCode(this.vlTotalVenda);
        hash = 53 * hash + Objects.hashCode(this.recebido);
        hash = 53 * hash + Objects.hashCode(this.cliente);
        hash = 53 * hash + Objects.hashCode(this.usuario);
        
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.cdVenda, other.cdVenda)) {
            return false;
        }
        if (!Objects.equals(this.dtVenda, other.dtVenda)) {
            return false;
        }
        if (!Objects.equals(this.vlTotalVenda, other.vlTotalVenda)) {
            return false;
        }
        if (!Objects.equals(this.recebido, other.recebido)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        
        return Objects.equals(this.usuario, other.usuario);
    } // Fim do método equals

} // Fim da classe Venda
