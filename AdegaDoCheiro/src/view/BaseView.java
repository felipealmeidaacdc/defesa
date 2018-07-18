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

package view;

import values.SoftConfig;

import com.towel.bind.Binder;
import com.towel.bind.annotation.AnnotatedBinder;
import com.towel.bind.annotation.Form;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Aluno
 */
public abstract class BaseView extends javax.swing.JDialog {
    
    protected String className;
    protected Object entity;
    protected Binder binder;
    protected ResourceBundle bundle;

    public BaseView(java.awt.Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
    }

    private void initComponents() {
        className = getClass().getSimpleName();
        bundle = ResourceBundle.getBundle("res/Bundle"); // NOI18N
        init();
        binder = new AnnotatedBinder(this);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cancelar();
            }
        });
        configEventos();
        setSize(SoftConfig.WINDOW_WIDTH, getHeight());
        setLocationRelativeTo(null);
    }

    protected abstract void init();
    protected abstract void callConsulta() throws HeadlessException;
    protected abstract void novo() throws HeadlessException;
    protected abstract void incluir();
    protected abstract void consultar();
    protected abstract void alterar();
    protected abstract void excluir();
    protected abstract void selecionar();
    protected abstract void configEventos();
    protected abstract boolean validarCampos();
    
    protected boolean isFormValid() throws HeadlessException {
        requestFocus();
        boolean valid = validarCampos();
        
        if (!valid) {
            JOptionPane.showMessageDialog((Frame) getParent(), bundle.getString("preenchaOsCampos"));
        }
        
        return valid;
    } // fim do método validarCampos
    
    /**
     * Método para carregar os itens na tabela
     * @param clazz: Classe da entidade a ser carregado os itens na tabela
     * @param itens: Lista com os produtos
     * @param nenhumItem: String com o texto para exibir quando a lista de 
     * itens estiver vazia
     * @param scrollPane JScrollPane da tebela
     * @param tabela Tabela a ser carregado os itens
     * @param cols: String com as colunas da tabela para o ObjectTableModel
     */
    protected void carregarTabela(Class<?> clazz, List itens, String nenhumItem, 
            JScrollPane scrollPane, JTable tabela, String cols) {
        if (itens != null && !itens.isEmpty()) {
            scrollPane.setViewportView(tabela);
        } else {
            scrollPane.setViewportView(new JLabel(nenhumItem, javax.swing.SwingConstants.CENTER));
        }
        
        ObjectTableModel tableModel = new ObjectTableModel<>(new AnnotationResolver(clazz), cols);
        tableModel.setData(itens);
        tabela.setModel(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    } // Fim do método carregarTabela

    /**
     * Método para limpar valores dos os campos
     */
    protected void limpar() {
        binder.clearView();
        entity = null;
    } // fim do método limpar

    /**
     *
     * @throws HeadlessException
     */
    protected void cancelar() throws HeadlessException {
        if (JOptionPane.showConfirmDialog((Frame) getParent(),
                bundle.getString("confirmarCancelar"),
                bundle.getString("cancelar"), 0) == 0) {
            dispose();
        }
    } // Fim do método cancelar

} // Fim da classe BaseView
