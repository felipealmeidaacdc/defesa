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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author Aluno
 */
public class PdfDocument extends Document {
    
//    private Document doc;
    private ByteArrayOutputStream baos;
    
    public PdfDocument() {
        newFile();
    } // Fim do método construtor
    
    private void newFile() {
//        doc = new Document();
        baos = new ByteArrayOutputStream();
        
        try {
            PdfWriter.getInstance(this, baos);
            open();
        } catch (DocumentException ex) {
            System.err.println("Error: PdfDocument: newDocument: " + ex);
        }
    } // Fim do método newDocument
    
    public PdfDocument addParagraph(String string) {
        if (isOpen()) {
            try {
                super.add(new Paragraph(string));
            } catch (DocumentException ex) {
                System.err.println("Error: PdfDocument: addParagraph: " + ex);
            }
        }
        
        return this;
    } // Fim do método addParagraph
    
    public PdfDocument addParagraph(String string, int alignment) {
        if (isOpen()) {
            try {
                Paragraph paragraph = new Paragraph(string);
                paragraph.setAlignment(alignment);
                super.add(paragraph);
            } catch (DocumentException ex) {
                System.err.println("Error: PdfDocument: addParagraph: " + ex);
            }
        }
        
        return this;
    } // Fim do método addParagraph
    
    public PdfDocument addTable(PdfPTable pdfPTable) {
        if (isOpen()) {
            try {
                super.add(pdfPTable);
            } catch (DocumentException ex) {
                System.err.println("Error: PdfDocument: addTable: " + ex);
            }
        }
        
        return this;
    } // Fim do método addTable
    
    public void save(String pathname) {
        if (baos != null) {
            try {
                close();
                
                try (FileOutputStream fis = new FileOutputStream(new File(pathname))) {
                    baos.writeTo(fis);
                    baos.close();
                    fis.close();
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Error: PdfDocument: save: " + ex);
            } catch (IOException ex) {
                System.err.println("Error: PdfDocument: save: " + ex);
            }
        }
    } // Fim do método save
    
    public void print(String jobname) {
        try {
            close();
            DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintRequestAttributeSet requestAttributeSet = new HashPrintRequestAttributeSet();
            requestAttributeSet.add(MediaSizeName.ISO_A4);
            requestAttributeSet.add(new JobName(jobname, Locale.getDefault()));
            
            PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, requestAttributeSet);
            
            if (services != null && services.length != 0) {
                PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                PrintService service = ServiceUI.printDialog(null, 
                        (screenSize.width/2)-503, (screenSize.height/2)-394, 
                        services, defaultService, flavor, requestAttributeSet);
                
                if (service != null) {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPrintService(service);
                    job.setJobName(jobname);
                    job.setPageable(new PDFPageable(PDDocument.load(baos.toByteArray())));
                    job.print();
                }
            }
            
            baos.close();
        } catch (IOException | PrinterException ex) {
            System.err.println("Error: PdfDocument: print: " + ex);
        }
    } // Fim do método print
    
    @Override
    public void close() {
        if (isOpen()) {
            try {
                super.close();
            } catch (Exception ex) {
                System.err.println("Error: PdfDocument: close: " + ex);
            }
        }
    } // Fim do método close
    
    @Override
    public boolean isOpen() {
        if (super.isOpen()) {
            return true;
        } else {
            System.err.println("Document not opened!");
            
            return false;
        }
    } // Fim do método isOpen
    
    /*public static void main(String[] args) {
        new PdfDocument().open().addParagraph("Hello World!").save("Teste.pdf");
        new PdfDocument().open().addParagraph("Hello World!").print("Teste");
    }*/
    
} // Fim da classe PdfDocument
