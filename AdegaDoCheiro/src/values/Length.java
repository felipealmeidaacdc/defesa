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

package values;

/**
 *
 * @author Aluno
 */
public class Length {
    
    public static class Usuario {
        public static final int NOME = 50;
        public static final int LOGIN = 16;
        public static final int SENHA = 16;
        public static final int SENHA_MD5 = 32;
    } // Fim da subclasse Usuario
    
    public static class TipoUsuario {
        public static final int TIPO = 50;
        public static final int PERMISSAO = 13;
    } // Fim da subclasse TipoUsuario
    
    public static class Produto {
        public static final int NOME = 100;
        public static final int NU_PRODUTO = 50;
    } // Fim da subclasse Produto
    
    public static class TipoProduto {
        public static final int TIPO = 50;
    } // Fim da subclasse TipoProduto
    
    public static class Cliente {
        public static final int NOME = 50;
        public static final int NU_CPF = 11;
        public static final int ENDERECO = 50;
        public static final int NUM = 8;
        public static final int BAIRRO = 50;
        public static final int COMPLEMENTO = 50;
        public static final int CIDADE = 50;
        public static final int ESTADO = 2;
        public static final int CEP = 8;
        public static final int NU_TELEFONE = 11;
        public static final int EMAIL = 50;
    } // Fim da subclasse Cliente
    
    public static class Fornecedor {
        public static final int NOME = 50;
        public static final int NU_CNPJ = 14;
        public static final int ENDERECO = 50;
        public static final int NUM = 8;
        public static final int BAIRRO = 50;
        public static final int COMPLEMENTO = 50;
        public static final int CIDADE = 50;
        public static final int ESTADO = 2;
        public static final int CEP = 8;
        public static final int NU_TELEFONE = 11;
        public static final int EMAIL = 50;
    } // Fim da subclasse Fornecedor
    
    public static class TipoMovimentacao {
        public static final int TIPO = 50;
    } // Fim da subclasse TipoMovimentacao
    
} // Fim da classe Length
