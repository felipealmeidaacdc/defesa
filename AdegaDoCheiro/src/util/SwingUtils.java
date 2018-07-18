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

import DOCUMENT.FixedLengthDocument;
import DOCUMENT.NumeroValidator;

import java.awt.Component;
import java.awt.Window;

import java.lang.reflect.Field;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author Aluno
 */
public class SwingUtils {
    
    // tipos de document para o TextField
    public static final int FIXED_LENGTH = 0;
    public static final int NUMERO_VALIDATOR = 1;

    /**
     * attempts to retrieve a component from a JFrame or JDialog using the name
     * of the private variable that NetBeans (or other IDE) created to refer to
     * it in code.
     *
     * @param <T> Generics allow easier casting from the calling side.
     * @param window JFrame or JDialog containing component
     * @param name name of the private field variable, case sensitive
     * @return null if no match, otherwise a component.
     */
    @SuppressWarnings("unchecked")
    static public <T extends Component> T getComponentByName(Window window, String name) {

        // loop through all of the class fields on that form
        for (Field field : window.getClass().getDeclaredFields()) {
            try {
                // let us look at private fields, please
                field.setAccessible(true);

                // compare the variable name to the name passed in
                if (name.equals(field.getName())) {

                    // get a potential match (assuming correct &lt;T&gt;ype)
                    final Object potentialMatch = field.get(window);

                    // cast and return the component
                    return (T) potentialMatch;
                }
            } catch (SecurityException | IllegalArgumentException
                    | IllegalAccessException ex) {
                // ignore exceptions
            }
        }
        
        // no match found
        return null;
    } // Fim do método getComponentByName

    /**
     * Classe para definir um limite de entrada de caracteres em um JTextField.
     * 
     * @param field JTextField a ser configurado o limite.
     * @param length O tamanho máximo de caracteres permitido no campo.
     * @param type Tipo de DocumentFilter a ser definido.
     * SwingUtils.FIXED_LENGHT: permite qualquer caractere
     * SwingUtils.NUMERO_VALIDATOR: permite apenas números
     */
    public static void setFieldLength(JTextField field, int length, int type) {
        switch (type) {
            case FIXED_LENGTH:
                ((AbstractDocument) field.getDocument()).setDocumentFilter(new FixedLengthDocument(length, field));
                break;
            case NUMERO_VALIDATOR:
                ((AbstractDocument) field.getDocument()).setDocumentFilter(new NumeroValidator(length, field));
                break;
        }
    } // Fim do método getFieldLength
    
} // Fim da classe SwingUtils
