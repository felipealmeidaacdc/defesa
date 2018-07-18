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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Aluno
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "login", name = "UK_Usuario_Login")
})
public class Usuario implements Serializable {

    // atributos da entidade Usuario
    @Id
    @GeneratedValue
    private Integer cdUsuario;

    @Column(length = values.Length.Usuario.NOME, nullable = false)
    private String nome;

    @Column(length = values.Length.Usuario.LOGIN, nullable = false)
    private String login;

    @Column(length = values.Length.Usuario.SENHA_MD5, nullable = false)
    private String senha;
    
    @ManyToOne
    @JoinColumn(name = "fkTipoUsuario",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_Usuario_TipoUsuario"))
    private TipoUsuario tipoUsuario;

    public Integer getCdUsuario() {
        return cdUsuario;
    } // Fim do método getCdUsuario

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    } // Fim do método setCdUsuario

    public String getNome() {
        return nome;
    } // Fim do método getNome

    public void setNome(String nome) {
        this.nome = nome;
    } // Fim do método setNome

    public String getLogin() {
        return login;
    } // Fim do método getLogin

    public void setLogin(String login) {
        this.login = login;
    } // Fim do método setLogin

    public String getSenha() {
        return senha;
    } // Fim do método getSenha

    public void setSenha(String senha) {
        this.senha = senha;
    } // Fim do método setSenha

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    } // Fim do método getTipoUsuario

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    } // Fim do método setTipoUsuario

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.cdUsuario);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.senha);
        hash = 97 * hash + Objects.hashCode(this.tipoUsuario);
        
        return hash;
    } // Fim do método hasCode

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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.cdUsuario, other.cdUsuario)) {
            return false;
        }
        
        return Objects.equals(this.tipoUsuario, other.tipoUsuario);
    } // Fim do método equals
    
} // Fim da classe Usuario
