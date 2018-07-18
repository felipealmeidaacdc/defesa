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
package util;

/**
 *
 * @author Aluno
 */
public class Validator {
    
    private static final int[] PESO_CPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESO_CNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        
        soma = 11 - soma % 11;
        
        return soma > 9 ? 0 : soma;
    } // Fim do método calcularDigito
    
    public static boolean isValidCPF(String cpf) {
        if ((cpf==null) || (cpf.length()!=11)) return false;
        
        Integer digito1 = calcularDigito(cpf.substring(0,9), PESO_CPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, PESO_CPF);
        
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    } // Fim do método isValidCPF
    
    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj==null)||(cnpj.length()!=14)) return false;
        
        Integer digito1 = calcularDigito(cnpj.substring(0,12), PESO_CNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, PESO_CNPJ);
        
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    } // Fim do método isValidCNPJ
    
} // Fim da classe Validator
