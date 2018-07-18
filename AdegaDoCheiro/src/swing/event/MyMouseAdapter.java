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

package swing.event;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;

/**
 *
 * @author Aluno
 */
public class MyMouseAdapter {

    private JButton button;
    
    public MyMouseAdapter() {
    } // Fim do método construtor default
    
    public MyMouseAdapter(JButton button) {
        this.button = button;
    } // Fim do método construtor MyMouseAdapter
    
    public void setAction(RootPaneContainer rpc, int keyCode, int modifiers, 
            MyAbstractAction action) {
        AbstractAction aa = new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (button.isEnabled()) {
                    rpc.getRootPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    
                    if (actionEvent.getActionCommand() == null) {
                        button.doClick();
                    } else {
                        if (action != null) {
                            action.actionPerformed();
                        }
                    }
                    
                    rpc.getRootPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        };
        
        button.addActionListener(aa);
        setAtalho(rpc, keyCode, modifiers, button.getText(), aa);
    } // Fim do metodo setAction
    
    public void setAtalho(RootPaneContainer rpc, int keyCode, int modifiers, 
            String actionMapKey, AbstractAction action) {
        InputMap inputMap = rpc.getRootPane().getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(keyCode, modifiers), actionMapKey);
        rpc.getRootPane().setInputMap(JButton.WHEN_IN_FOCUSED_WINDOW, inputMap);
        rpc.getRootPane().getActionMap().put(actionMapKey, action);
    }
    
    public interface MyAbstractAction {
        public void actionPerformed();
    } // Fim da interface MyAbstractAction
    
} // Fim da classe MyMouseAdapter
