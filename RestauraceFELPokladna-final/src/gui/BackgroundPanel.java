/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author komarem
 */

class BackgroundPanel extends JPanel
{
  ImageIcon image = new ImageIcon("images/bill-bg.png");
//  public BackgroundPanel()
//  {
//
//  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if (image != null)
      g.drawImage(image.getImage(), 0,0,300,500,this);
  }
}
