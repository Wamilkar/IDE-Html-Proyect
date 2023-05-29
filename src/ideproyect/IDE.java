/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ideproyect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.JOptionPane;

/**
 *
 * @author Sistemas
 */
public class IDE extends javax.swing.JFrame {

      NumeroLinea numerolinea;
      Directorio dir;
      
      
      
    /**
     * Creates new form Editor
     */
    public IDE() {
        initComponents();
        inicializar();
        colors();
    }
    
    //METODO PARA ENCONTRAR LAS ULTIMAS CADENAS
    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            //  \\W = [A-Za-Z0-9]
            if (String.valueOf(text.charAt(index)).matches("[^a-zA-Z0-9<>]|>")) {
                break;
            }
        }
        return index;
    }

    //METODO PARA ENCONTRAR LAS PRIMERAS CADENAS 
    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("[^a-zA-Z0-9<>]|>")) {
                break;
            }
            index++;
        }
        return index;
    }

    //METODO PARA PINTAS LAS PALABRAS RESEVADAS
    private void colors() {

        final StyleContext cont = StyleContext.getDefaultStyleContext();

        //COLORES 
        final AttributeSet attblue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.decode("#FF0000"));
        final AttributeSet attblack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,Color.decode("#00000"));

        //STYLO 
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("[^a-zA-Z0-9<>]|>")) {
                       if (text.substring(wordL, wordR)
                               .matches("(\\W)*(html|head|title|body|p|br|hr|a|img|ul|ol|li|dl|dt|dd|"
                                       + "h[1-6]|div|span|form|input|button|label|select|option|textarea|"
                                       + "table|tr|td|th|caption|style|script|<|>|/)(\\W)*")){
                                setCharacterAttributes(wordL, wordR - wordL, attblue, false);
                        } else {
                            setCharacterAttributes(wordL, wordR - wordL, attblack, false);
                        }
                        wordL = wordR;

                    }
                    wordR++;
                }
            }

            public void romeve(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
            }
        };

        JTextPane txt = new JTextPane(doc);
        String temp = jtpCode.getText();
        jtpCode.setStyledDocument(txt.getStyledDocument());
        jtpCode.setText(temp);

    }

    
    public static void print(JTextPane textPane) {
        // Obtener el PrinterJob
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        
        // Establecer el contenido a imprimir
        printerJob.setPrintable(textPane.getPrintable(null, null));
        
        // Mostrar el diálogo de impresión
        if (printerJob.printDialog()) {
            try {
                // Imprimir el contenido
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaCompile = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuevo = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        mguardar = new javax.swing.JMenu();
        mgcomo = new javax.swing.JMenu();
        mimpr = new javax.swing.JMenu();
        mbuscar = new javax.swing.JMenu();
        mrempl = new javax.swing.JMenu();
        mir = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtpCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtpCode.setName("jtpCode"); // NOI18N
        jtpCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtpCodeKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtpCode);

        jtaCompile.setColumns(20);
        jtaCompile.setRows(5);
        jScrollPane2.setViewportView(jtaCompile);

        jMenu1.setText("ARCHIVO");

        mnuevo.setText("Nuevo");
        mnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuevoMouseClicked(evt);
            }
        });
        jMenu1.add(mnuevo);

        jMenu2.setText("Abrir");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu2);

        mguardar.setText("Guardar");
        mguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mguardarMouseClicked(evt);
            }
        });
        jMenu1.add(mguardar);

        mgcomo.setText("Guardar como");
        mgcomo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mgcomoMouseClicked(evt);
            }
        });
        jMenu1.add(mgcomo);

        mimpr.setText("Imprimir");
        mimpr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mimprMouseClicked(evt);
            }
        });
        jMenu1.add(mimpr);

        jMenuBar1.add(jMenu1);

        mbuscar.setText("Buscar");
        jMenuBar1.add(mbuscar);

        mrempl.setText("Remplazar");
        jMenuBar1.add(mrempl);

        mir.setText("Ir a");
        mir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mirMouseClicked(evt);
            }
        });
        jMenuBar1.add(mir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtpCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtpCodeKeyReleased
        int keyCode = evt.getKeyCode();
        if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 48 && keyCode <= 57)
                || (keyCode >= 97 && keyCode <= 122) || (keyCode != 27 && !(keyCode >= 37
                && keyCode <= 40) && !(keyCode >= 16
                && keyCode <= 18) && keyCode != 524
                && keyCode != 20)) {

            if (!getTitle().contains("*")) {
                setTitle(getTitle() + "*");
            }
        }        
// TODO add your handling code here:
    }//GEN-LAST:event_jtpCodeKeyReleased

    private void mguardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseClicked
         dir.Guardar(this);       
// TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseClicked

    private void mgcomoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mgcomoMouseClicked
       dir.guardarC(this);
        // TODO add your handling code here:
    }//GEN-LAST:event_mgcomoMouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        dir.Abrir(this);
        clearAllComp();
// TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void mnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuevoMouseClicked
        dir.Nuevo(this);
    // TODO add your handling code here:
    }//GEN-LAST:event_mnuevoMouseClicked


    private void mimprMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mimprMouseClicked

     print(jtpCode);
    }//GEN-LAST:event_mimprMouseClicked


    private void mirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mirMouseClicked
    // Mostrar el cuadro de diálogo de entrada de número de línea
    String input = JOptionPane.showInputDialog(this, "Ingrese el número de línea:");

    if (input != null && !input.isEmpty()) {
        try {
            // Obtener el número de línea ingresado
            int lineNumber = Integer.parseInt(input);

            // Obtener el componente de texto del JScrollPane
            JTextPane textPane = (JTextPane) jScrollPane1.getViewport().getView();
            int lineCount = textPane.getDocument().getDefaultRootElement().getElementCount();

            if (lineNumber >= 1 && lineNumber <= lineCount) {
                // Obtener la posición de inicio y fin de la línea
                int startOffset = textPane.getDocument().getDefaultRootElement()
                        .getElement(lineNumber - 1).getStartOffset();
                int endOffset = textPane.getDocument().getDefaultRootElement()
                        .getElement(lineNumber - 1).getEndOffset();

                // Desplazarse a la línea deseada
                textPane.setCaretPosition(startOffset);
                textPane.moveCaretPosition(endOffset);
            } else {
                JOptionPane.showMessageDialog(this, "Número de línea inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número de línea inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }    // TODO add your handling code here:
    }//GEN-LAST:event_mirMouseClicked


    /**
     * @param args the command line arguments
     */
   
    private void inicializar(){
        
         dir = new Directorio();
        
         setTitle("#HTML");
         String[] options = new String[]{"Guardar y continuar", "Descargar"};
         
         //Numero de Linea
         numerolinea = new NumeroLinea(jtpCode);
         jScrollPane1.setRowHeaderView(numerolinea);
          
    } 
    
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IDE().setVisible(true);
            }
        });
            /* Create and display the form */
        
    }
    
    public void clearAllComp() {
        jtaCompile.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jtaCompile;
    public static javax.swing.JTextPane jtpCode;
    private javax.swing.JMenu mbuscar;
    private javax.swing.JMenu mgcomo;
    private javax.swing.JMenu mguardar;
    private javax.swing.JMenu mimpr;
    private javax.swing.JMenu mir;
    private javax.swing.JMenu mnuevo;
    private javax.swing.JMenu mrempl;
    // End of variables declaration//GEN-END:variables

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex == 0) {
            Graphics2D graphics2d = (Graphics2D) graphics;
            graphics2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            printAll(graphics2d);
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        } 
    }
}