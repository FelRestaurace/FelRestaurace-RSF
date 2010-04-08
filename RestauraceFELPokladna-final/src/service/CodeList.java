/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

/**
 * Tato trida se stara o prevadeni mernych jednotek.
 *
 * @author Jarda
 */
public class CodeList {

    /**
     * Soukromy konstruktor tridy CodeList.
     */
    private CodeList(){
    }

    /**
     * Metoda prevadi mnozstvi suroviny (atribut quantity) z jednotky
     * s identifikatorem "fromUnitTypeId" na jednotku s identifikatorem
     * "toUnitTypeId".
     *
     * @param fromUnitTypeId identifikator vychozi jednotky
     * @param toUnitTypeId identifikator koncove jednotky
     * @param quantity mnozstvi suroviny ve vychozi jednotce
     * @return mnozstvi suroviny v koncove jednotce
     */
    public static double transfer(int fromUnitTypeId, int toUnitTypeId, double quantity){
        if (fromUnitTypeId == toUnitTypeId){
            return quantity;
        }
        if (fromUnitTypeId == 1 && toUnitTypeId == 2){
            return fromGramsToKilograms(quantity);
        }
        if (fromUnitTypeId == 2 && toUnitTypeId == 1){
            return fromKilogramsToGrams(quantity);
        }

        double litres = 0;
        if (fromUnitTypeId == 3) litres = quantity / 1000;
        if (fromUnitTypeId == 4) litres = quantity / 10;
        if (fromUnitTypeId == 5) litres = quantity;
        if (fromUnitTypeId == 6) litres = quantity * 50;
        if (fromUnitTypeId == 7) litres = quantity * 30;

        if (toUnitTypeId == 3) return (litres * 1000);
        if (toUnitTypeId == 4) return (litres * 10);
        if (toUnitTypeId == 5) return (litres);
        if (toUnitTypeId == 6) return (litres / 50);
        if (toUnitTypeId == 7) return (litres / 30);
        return -1;
    }

    /**
     * Metoda prevadi kilogramy na gramy.
     *
     * @param kilos kilogramu
     * @return gramu
     */
    public static double fromKilogramsToGrams(double kilos){
        return kilos * 1000;
    }

    /**
     * Metoda prevadi gramy na kilogramy.
     *
     * @param grams gramu
     * @return kilogramu
     */
    public static double fromGramsToKilograms(double grams){
        return grams / 1000;
    }

    /**
     * Metoda prevadi 50ti litrove sudy na litry.
     *
     * @param butts sudu
     * @return litru
     */
    public static double fromButts50ToLitres(double butts){
        return butts * 50;
    }

    /**
     * Metoda prevadi litry na 50ti litrove sudy.
     *
     * @param litres litru
     * @return sudu
     */
    public static double fromLitresToButts50(double litres){
        return litres / 50;
    }

    /**
     * Metoda prevadi 30ti litrove sudy na litry.
     *
     * @param butts sudu
     * @return litru
     */
    public static double fromButts30ToLitres(double butts){
        return butts * 30;
    }

    /**
     * Metoda prevadi litry na 30ti litrove sudy.
     *
     * @param litres litru
     * @return sudu
     */
    public static double fromLitresToButts30(double litres){
        return litres / 30;
    }

    /**
     * Metoda prevadi litry na decilitry.
     *
     * @param litres litru
     * @return decilitru
     */
    public static double fromLitresToDecilitres(double litres){
        return litres * 10;
    }

    /**
     * Metoda prevadi decilitry na litry.
     *
     * @param decilitres decilitru
     * @return litru
     */
    public static double fromDecilitresToLitres(double decilitres){
        return decilitres / 10;
    }

    /**
     * Metoda prevadi litry na mililitry.
     *
     * @param litres litru
     * @return mililitru
     */
    public static double fromLitresToMililitres(double litres){
        return litres * 1000;
    }

    /**
     * Metoda prevadi mililitry na litry
     *
     * @param mililitres mililitru
     * @return litru
     */
    public static double fromMililitresToLitres(double mililitres){
        return mililitres / 1000;
    }
}
