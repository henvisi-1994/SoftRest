/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.vistas;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Berty
 */
public class Validaciones {

    //pinta color normal de fondo y borde     
    public static void pinta_text(JTextField txt)
    {
        txt.setBorder(BorderFactory.createLineBorder(Color.gray));
        txt.setBackground(Color.white);
    }
    public static void pinta_text(JTextArea txt)
    {
        txt.setBorder(BorderFactory.createLineBorder(Color.gray));
        txt.setBackground(Color.white);
    }
    
    //verifica si es requerido
    public static boolean esRequerido(JTextField txt)
    {
        boolean ok=true;
        if (txt.getText().trim().equals(""))
        {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok=false;                    
        }
        return ok;        
    }   
    
    public static boolean esRequerido(JTextArea txt)
    {
        boolean ok=true;
        if (txt.getText().trim().equals(""))
        {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok=false;                    
        }
        return ok;        
    } 
    
    //verifica flotantes    
    public static boolean esFlotante(JTextField txt)
    {
        boolean ok=true;
        if (!txt.getText().trim().matches("[0-9]{1,10}[.]{0,1}[0-9]{0,}"))
        {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok=false;                    
        }
        return ok;   
        
         /*
        try{
            Double d=Double.parseDouble(txt.getText());
            return true;
        }catch(Exception ex){
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
        }
        return false; 
        */
    }
    
    //verifica si es email
    public static boolean esEmail(JTextField txt)
    {
        boolean ok=true;
        //if (!txt.getText().trim().matches("[a-z]{1,}[@]{1}[a-z]{1,}[.]{1}[a-z]{1,}[.]{0,1}[a-z]{0,2}"))
        Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(txt.getText().trim());
        if(!mat.find()){
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok=false;                    
        }
        return ok; 
    }   
    
    public static boolean esTelefono(JTextField txt)
    {
        boolean ok=true;
        if (!txt.getText().trim().matches("[0-9]{7,13}"))
        {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok=false;                    
        }
        return ok;  
    }
    
    public static boolean esCedula(JTextField txt)
    {
        boolean ok=true;
        if(!((txt.getText().trim().length()==10)||(txt.getText().trim().length()==13))){
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok=false;                    
        }        
        return ok;  
    }
    
    public static boolean esLetras(JTextField txt)
    {
        boolean ok=true;
        if (!txt.getText().trim().matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]{2,}"))
        {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok=false;                    
        }        
        return ok;  
    }

    public static boolean cedula(JTextField cedula) {
        int suma = 0;
        boolean correcto = false;
        try {
            String x = cedula.getText();
            if (x.length() == 9) {
                cedula.setBorder(BorderFactory.createLineBorder(Color.red));
                cedula.setBackground(Color.pink);
                correcto = false;
            } else {
                int a[] = new int[x.length() / 2];
                int b[] = new int[(x.length() / 2)];
                int c = 0;
                int d = 1;
                for (int i = 0; i < x.length() / 2; i++) {
                    a[i] = Integer.parseInt(String.valueOf(x.charAt(c)));
                    c = c + 2;
                    if (i < (x.length() / 2) - 1) {
                        b[i] = Integer.parseInt(String.valueOf(x.charAt(d)));
                        d = d + 2;
                    }
                }

                for (int i = 0; i < a.length; i++) {
                    a[i] = a[i] * 2;
                    if (a[i] > 9) {
                        a[i] = a[i] - 9;
                    }
                    suma = suma + a[i] + b[i];
                }
                int aux = suma / 10;
                int dec = (aux + 1) * 10;
                if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length() - 1)))) {
                    correcto = true;
                } else if (suma % 10 == 0 && x.charAt(x.length() - 1) == '0') {
                    correcto = true;
                } else {
                    correcto = false;
                    cedula.setBorder(BorderFactory.createLineBorder(Color.red));
                    cedula.setBackground(Color.pink);
                }
            }
        } catch (Exception ex) {
            cedula.setBorder(BorderFactory.createLineBorder(Color.red));
            cedula.setBackground(Color.pink);
        }
        return correcto;
    }

    public static boolean esNumero(JTextField txt) {
        try {
            Integer.parseInt(txt.getText());
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void validaJtxtNumeros(char c, KeyEvent evt) {
        c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }

    }

    public static void validaJtxtLetras(char c, KeyEvent evt) {
        c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            Toolkit.getDefaultToolkit().beep();

            evt.consume();
        }
    }

    public static void limitarCajaTexto(JTextField txt, int limite, KeyEvent e) {
        if (txt.getText().length() == limite) {
            e.consume();
        }
    }

}
