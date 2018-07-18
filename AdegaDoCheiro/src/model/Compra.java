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
public class Compra implements Serializable {

    // atributos da entidade Compra
    @Id
    @GeneratedValue
    private Integer cdCompra;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCompra;

    @Column(nullable = false)
    private Float vlTotalCompra;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean pago = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "fkFornecedor", nullable = false,
                foreignKey = @ForeignKey(name = "FK_Compra_Fornecedor"))
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "fkUsuario", nullable = false,
                foreignKey = @ForeignKey(name = "FK_Compra_Usuario"))
    private Usuario usuario;

    public Integer getCdCompra() {
        return cdCompra;
    } // Fim do método getCdCompra

    public void setCdCompra(Integer cdCompra) {
        this.cdCompra = cdCompra;
    } // Fim do método setCdCompra

    public Date getDtCompra() {
        return dtCompra;
    } // Fim do método getDtCompra

    public void setDtCompra(Date dtCompra) {
        this.dtCompra = dtCompra;
    } // Fim do método setDtCompra

    public Float getVlTotalCompra() {
        return vlTotalCompra;
    } // Fim do método getVlTotalCompra

    public void setVlTotalCompra(Float vlTotalCompra) {
        this.vlTotalCompra = vlTotalCompra;
    } // Fim do método setVlTotalCompra

    public Boolean getPago() {
        return pago;
    } // Fim do método getPago

    public void setPago(Boolean pago) {
        this.pago = pago;
    } // Fim do método setPago

    public Fornecedor getFornecedor() {
        return fornecedor;
    } // Fim do método getFornecedor

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    } // Fim do método setFornecedor

    public Usuario getUsuario() {
        return usuario;
    } // Fim do método getUsuario

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    } // Fim do método setUsuario

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.cdCompra);
        hash = 53 * hash + Objects.hashCode(this.dtCompra);
        hash = 53 * hash + Objects.hashCode(this.vlTotalCompra);
        hash = 53 * hash + Objects.hashCode(this.pago);
        hash = 53 * hash + Objects.hashCode(this.fornecedor);
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
        final Compra other = (Compra) obj;
        if (!Objects.equals(this.cdCompra, other.cdCompra)) {
            return false;
        }
        if (!Objects.equals(this.dtCompra, other.dtCompra)) {
            return false;
        }
        if (!Objects.equals(this.vlTotalCompra, other.vlTotalCompra)) {
            return false;
        }
        if (!Objects.equals(this.pago, other.pago)) {
            return false;
        }
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        
        return Objects.equals(this.usuario, other.usuario);
    } // Fim do método equals

} // Fim da classe Compra
