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
    @UniqueConstraint(columnNames = "tipo", name = "UK_TipoProduto_Tipo")
})
public class TipoProduto implements Serializable {

    // Atributos da entidade TipoProduto
    @Id
    @GeneratedValue
    private Integer cdTipoProduto;
    
    @Column(length = values.Length.TipoProduto.TIPO, nullable = false)
    private String tipo;
    
    public Integer getCdTipoProduto() {
        return cdTipoProduto;
    } // Fim do método getCdTipoProduto

    public void setCdTipoProduto(Integer cdTipoProduto) {
        this.cdTipoProduto = cdTipoProduto;
    } // Fim do método setCdTipoProduto

    public String getTipo() {
        return tipo;
    } // Fim do método getTipo

    public void setTipo(String tipo) {
        this.tipo = tipo;
    } // Fim do método setTipo

    @Override
    public String toString() {
        return this.tipo;
    } // Fim do método toString

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cdTipoProduto);
        hash = 31 * hash + Objects.hashCode(this.tipo);
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
        final TipoProduto other = (TipoProduto) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return Objects.equals(this.cdTipoProduto, other.cdTipoProduto);
    } // Fim do método equals
} // Fim da classe TipoProduto
