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
package pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

/**
 *
 * @author Aluno
 */
public class PdfTable extends PdfPTable {
    
    public PdfTable(JTable jTable) {
        if (jTable == null) {
            throw new NullPointerException("PdfTable jTable cannot be empty!");
        }
        
        setWidthPercentage(100);
        addCells(jTable);
    } // Fim do método construtor
    
    private void addCells(JTable jTable) {
        resetColumnCount(jTable.getColumnCount());
        
        addHeader(jTable);
        
        addContent(jTable);
    } // Fim do método addCells
    
    private void addHeader(JTable jTable) {
        for (int column=0; column<jTable.getColumnCount(); column++) {
            PdfPCell cell = new PdfPCell(new Phrase(
                    jTable.getColumnName(column),
                    new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            cell.setVerticalAlignment(ALIGN_MIDDLE);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            addCell(cell);
            setHeaderRows(1);
        }
    } // Fim do método addHeader

    private void addContent(JTable jTable) {
        for (int row=0; row<jTable.getRowCount(); row++) {
            for (int column=0; column<jTable.getColumnCount(); column++) {
                Object value = jTable.getValueAt(row, column);
                PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(value)));
                cell.setVerticalAlignment(ALIGN_MIDDLE);
                
                if (Integer.class.isAssignableFrom(value.getClass())) {
                    cell.setHorizontalAlignment(ALIGN_RIGHT);
                }
                
                if ((value instanceof Double) || (value instanceof Float)) {
                    cell.setPhrase(new Phrase(new DecimalFormat("#,##0.00").format(value)));
                    cell.setHorizontalAlignment(ALIGN_RIGHT);
                }
                
                if (value instanceof Date) {
                    cell.setPhrase(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(value)));
                    cell.setHorizontalAlignment(ALIGN_RIGHT);
                }
                
                addCell(cell);
            }
        }
    } // Fim do método addContent
    
} // Fim da classe PdfTable
