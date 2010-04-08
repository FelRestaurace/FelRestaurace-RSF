/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import hibernate.Uzaverka;
import java.util.Comparator;

/**
 *
 * @author Lukas Camra
 */
public class UzaverkaComparator implements Comparator<Uzaverka> {

    @Override
    public int compare(Uzaverka uz1, Uzaverka uz2 ){
       return uz2.getDate().compareTo(uz1.getDate());
    }
}
