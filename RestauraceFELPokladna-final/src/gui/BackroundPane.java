/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

/**
 *
 * @author komarem
 */
public class BackroundPane extends JScrollPane{
 ImageIcon image = new ImageIcon("images/backgroundpane-bg.jpg");
//  public BackgroundPanel()
//  {
//
//  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if (image != null)
         g.drawImage(image.getImage(), 0, 0, this);
      //g.drawImage(image.getImage(), 0,0,300,500,this);
  }
}
