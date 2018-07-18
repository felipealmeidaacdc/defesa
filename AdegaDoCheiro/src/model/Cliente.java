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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Aluno
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "nuCpf", name = "UK_Cliente_Cpf")
})
public class Cliente implements Serializable {

    // atributos da entidade Cliente
    @Id
    @GeneratedValue
    private Integer cdCliente;

    @Column(length = values.Length.Cliente.NOME, nullable = false)
    private String nome;

    @Column(length = values.Length.Cliente.NU_CPF, nullable = false)
    private String nuCpf;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;

    @Column(length = values.Length.Cliente.ENDERECO)
    private String endereco;

    @Column(length = values.Length.Cliente.NUM)
    private String num;

    @Column(length = values.Length.Cliente.BAIRRO)
    private String bairro;

    @Column(length = values.Length.Cliente.COMPLEMENTO)
    private String complemento;

    @Column(length = values.Length.Cliente.CIDADE)
    private String cidade;

    @Column(length = values.Length.Cliente.ESTADO)
    private String estado;

    @Column(length = values.Length.Cliente.CEP)
    private String cep;

    @Column(length = values.Length.Cliente.NU_TELEFONE)
    private String nuTelefone;

    @Column(length = values.Length.Cliente.EMAIL)
    private String email;

    public Integer getCdCliente() {
        return cdCliente;
    } // Fim do método getCdCliente

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    } // Fim do método setCdCliente
    
    public String getNome() {
        return nome;
    } // Fim do método getNome

    public void setNome(String nome) {
        this.nome = nome;
    } // Fim do método setNome
    
    public String getNuCpf() {
        return nuCpf;
    } // Fim do método getNuCpf

    public void setNuCpf(String nuCpf) {
        this.nuCpf = nuCpf;
    } // Fim do método setNuCpf

    public Date getDtNascimento() {
        return dtNascimento;
    } // Fim do método getDtNascimento

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    } // Fim do método setDtNascimento

    public String getEndereco() {
        return endereco;
    } // Fim do método getEndereco

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    } // Fim do método setEndereco

    public String getNum() {
        return num;
    } // Fim do método getNum

    public void setNum(String num) {
        this.num = num;
    } // Fim do método setNum

    public String getBairro() {
        return bairro;
    } // Fim do método getBairro

    public void setBairro(String bairro) {
        this.bairro = bairro;
    } // Fim do método setBairro

    public String getComplemento() {
        return complemento;
    } // Fim do método getComplemento

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    } // Fim do método setComplemento

    public String getCidade() {
        return cidade;
    } // Fim do método getCidade

    public void setCidade(String cidade) {
        this.cidade = cidade;
    } // Fim do método setCidade

    public String getEstado() {
        return estado;
    } // Fim do método getEstado

    public void setEstado(String estado) {
        this.estado = estado;
    } // Fim do método setEstado

    public String getCep() {
        return cep;
    } // Fim do método getCep

    public void setCep(String cep) {
        this.cep = cep;
    } // Fim do método setCep

    public String getNuTelefone() {
        return nuTelefone;
    } // Fim do método getNuTelefone

    public void setNuTelefone(String nuTelefone) {
        this.nuTelefone = nuTelefone;
    } // Fim do método setNuTelefone

    public String getEmail() {
        return email;
    } // Fim do método getEmail

    public void setEmail(String email) {
        this.email = email;
    } // Fim do método setEmail

    @Override
    public String toString() {
        return nome;
    } // Fim do método toString

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.cdCliente);
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.nuCpf);
        hash = 29 * hash + Objects.hashCode(this.dtNascimento);
        hash = 29 * hash + Objects.hashCode(this.endereco);
        hash = 29 * hash + Objects.hashCode(this.num);
        hash = 29 * hash + Objects.hashCode(this.bairro);
        hash = 29 * hash + Objects.hashCode(this.complemento);
        hash = 29 * hash + Objects.hashCode(this.cidade);
        hash = 29 * hash + Objects.hashCode(this.estado);
        hash = 29 * hash + Objects.hashCode(this.cep);
        hash = 29 * hash + Objects.hashCode(this.nuTelefone);
        hash = 29 * hash + Objects.hashCode(this.email);
        
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.nuCpf, other.nuCpf)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.num, other.num)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.nuTelefone, other.nuTelefone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.cdCliente, other.cdCliente)) {
            return false;
        }
        
        return Objects.equals(this.dtNascimento, other.dtNascimento);
    } // Fim do código equals
    
} // Fim da classe Cliente
