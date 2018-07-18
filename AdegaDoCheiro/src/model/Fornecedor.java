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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Aluno
 */
@Entity
public class Fornecedor implements Serializable {

    // atributos da entidade Fornecedor
    @Id
    @GeneratedValue
    private Integer cdFornecedor;

    @Column(length = values.Length.Fornecedor.NOME, nullable = false)
    private String nome;

    @Column(length = values.Length.Fornecedor.NU_CNPJ, nullable = false)
    private String nuCnpj;
    
    @Column(length = values.Length.Fornecedor.ENDERECO)
    private String endereco;

    @Column(length = values.Length.Fornecedor.NUM)
    private String num;

    @Column(length = values.Length.Fornecedor.BAIRRO)
    private String bairro;

    @Column(length = values.Length.Fornecedor.COMPLEMENTO)
    private String complemento;

    @Column(length = values.Length.Fornecedor.CIDADE)
    private String cidade;

    @Column(length = values.Length.Fornecedor.ESTADO)
    private String estado;

    @Column(length = values.Length.Fornecedor.CEP)
    private String cep;

    @Column(length = values.Length.Fornecedor.NU_TELEFONE)
    private String nuTelefone;

    @Column(length = values.Length.Fornecedor.EMAIL)
    private String email;

    public Integer getCdFornecedor() {
        return cdFornecedor;
    } // Fim do método getCdFornecedor

    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    } // Fim do método setCdFornecedor

    public String getNome() {
        return nome;
    } // Fim do método getNome

    public void setNome(String nome) {
        this.nome = nome;
    } // Fim do método setNome

    public String getNuCnpj() {
        return nuCnpj;
    } // Fim do método getNuCnpj

    public void setNuCnpj(String nuCnpj) {
        this.nuCnpj = nuCnpj;
    } // Fim do método setNuCnpj

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
    }
    
} // Fim da classe Fornecedor
