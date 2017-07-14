/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author henvisi
 */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
 

public class ImagenFondo implements Border{
    public BufferedImage back;
 
    public ImagenFondo(String imagen){
        try {
            URL imagePath = new URL(getClass().getResource(imagen).toString());
            back = ImageIO.read(imagePath);
        }catch (NullPointerException ex)
    {
        JOptionPane.showMessageDialog(null,"No existe ruta o imagen ","Error:"+ex,JOptionPane.ERROR_MESSAGE);
    }   catch (MalformedURLException ex) {
        } catch (IOException ex) {
        }
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        try
        {
        g.drawImage(back, (x + (width - back.getWidth())/2),(y + (height - back.getHeight())/2), null);
          }catch (NullPointerException ex)
    {
        JOptionPane.showMessageDialog(null,"No existe ruta o imagen ","Error:"+ex,JOptionPane.ERROR_MESSAGE);
    }
    }
 
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
 
    public boolean isBorderOpaque() {
        return false;
    }
 
}