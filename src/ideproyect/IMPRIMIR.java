/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ideproyect;

import static ideproyect.IDE.jtpCode;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.DocPrintJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.print.Pageable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class IMPRIMIR {
    
   
    public static void main(String[] args) {
        
    }

    public void imprime()
    {
    // Crear un JTextPane de ejemplo
        JTextPane textPane = new JTextPane();
        textPane.setText(jtpCode.toString());

        // Obtener el documento del JTextPane
        Document document = textPane.getDocument();

        // Crear una instancia de PrinterJob
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Establecer el documento en PrinterJob
        printerJob.setPageable((Pageable) document);
        
        // Mostrar el diálogo de impresión
        if (printerJob.printDialog()) {
            try {
                // Iniciar la impresión
                printerJob.print();
            } catch (PrinterException ex) {
                System.out.print(ex);
            }
        }
    }
}
    




