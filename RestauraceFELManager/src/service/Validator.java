/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

/**
 * Trida, ktera slouzi pro kontrolovani vstupnich komponent. Jednotlive metody
 * kontroluji splneni podminek.
 *
 * Take zajistuje "cisteni" vstupnich polozek.
 *
 * @author Jarda
 */
public class Validator {

    private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Konstruktor tridy Validator.
     */
    private Validator(){
    }

    /**
     * Metoda navraci ciselnou hodnotu obsazenou v komponente JTextComponent.
     *
     * @param com instance tridy JTextComponent nebo jejich potomku
     * @return cisela hodnota
     * @throws java.lang.NumberFormatException
     */
    private static double getDouble(JTextComponent com) throws NumberFormatException {
        return Double.parseDouble(com.getText());
    }

    /**
     * Metoda testuje, zda vstupni udaje ve vstupni komponente (instance tridy
     * JTextField nebo jejich potomku) obsahuje ciselnou hodnotu (double).
     *
     * @param com instance tridy JTextComponent nebo jejich potomku
     * @return true, pokud je vstup cislo (double), false pokud nikoliv
     */
    public static boolean isDouble(JTextComponent com){
        double d;
        try {
            d = Validator.getDouble(com);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

    /**
     * Metoda testuje, zda vstupni udaje ve vstupni komponente (instance tridy
     * JTextField nebo jejich potomku) obsahuje ciselnou kladnou hodnotu (double).
     *
     * @param com instance tridy JTextComponent nebo jejich potomku
     * @return true, pokud je vstup kladne cislo (double), false pokud nikoliv
     */
    public static boolean isPositiveDouble(JTextComponent com){
        double d;
        try {
            d = getDouble(com);
        } catch (NumberFormatException nfe){
            return false;
        }
        if (d < 0) return false;
        return true;
    }

    /**
     * Metoda testuje, zda vstupni pole obsahuje nejaky text.
     *
     * @param com instance tridy JTextComponent nebo jejich potomku
     * @return true, pokud je v komponente text, false pokud nikoliv
     */
    public static boolean isText(JTextComponent com){
        if (com.getText().isEmpty()) return false;
        return true;
    }

    /**
     * Metoda kontrolujici spravnost datumu.
     *
     * @param date textovy retezec reprezentujici datum
     * @return V pripade, ze retezec reprezentuje validni datum, tak vrati objekt
     * tridy Date pro dany retezec; jinak vrati null
     */
    public static Date isValidDate(String date){
        String n = date.replace('.', ':');
        String array [] = n.split(":", 3);
        int day = 0, month = 0, year = 0;
        Date d = null;        
        try {
            day = Integer.parseInt(array[0]);
            month = Integer.parseInt(array[1]);
            year = Integer.parseInt(array[2]);
            if (day > 31 || day < 1){
                return null;
            }
            if (month > 12 || month < 1){
                return null;
            }
            d = df.parse(date);
        } catch (Exception e){
            return null;
        }
        return d;
    }

    /**
     * Metoda testuje, zda je v danem JComboBoxu vybrana nekjaka polozka.
     *
     * @param box instance tridy JComboBox
     * @return true, pokud je vybrana nejaka polozka, jinak false
     */
    public static boolean isSelectedItem(JComboBox box){
        if (box.getSelectedIndex() == -1) return false;
        return true;
    }

    /**
     * Metoda cisti textove pole pro datum - nastavuje aktualni datum.
     *
     * @param com instance tridy JTextComponent nebo jejich potomku
     */
    public static void clearDateField(JTextComponent com){
        com.setText(df.format(new Date()));
    }

    /**
     * Metoda cisti textove pole.
     *
     * @param com instance tridy JTextComponent nebo jejich potomku
     */
    public static void clearTextField(JTextComponent com){
        com.setText("");
    }

    /**
     * Metoda nastavuje jako vybranou polozku prvni item.
     *
     * @param box instance tridy JComboBox
     */
    public static void clearComboBox(JComboBox box){
        if (box.getItemCount() > 0){
            box.setSelectedIndex(0);
        }
    }

}
