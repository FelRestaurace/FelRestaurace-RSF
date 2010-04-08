/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.controllers;

import hibernate.Kontrola;
import hibernate.UsedMaterial;
import hibernate.User;
import hibernate.Uzaverka;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import service.UzaverkaComparator;

/**
 *
 * @author Lukas Camra
 */
public class UzaverkaController {

    protected static UzaverkaController instance = null;
    protected Uzaverka uzaverka;

    public void setUzaverka(Uzaverka uzaverka) {
        this.uzaverka = uzaverka;
    }

    public static UzaverkaController getInstance() {
        if (UzaverkaController.instance == null) {
            UzaverkaController.instance = new UzaverkaController();
        }
        return instance;
    }

    //vytvari novou uzaverku
    public Uzaverka createUzaverka(User user, Date date) {
        uzaverka = new Uzaverka();
        uzaverka.setUser(user);
        uzaverka.setDate(date);
        uzaverka.create();
        return uzaverka;
    }

    //vraci vsechny kontroly uzaverky
    public Object[][] getKontroly() {
        List<Kontrola> kontroly = Kontrola.findByUzaverka(uzaverka.getId());
        if (uzaverka == null || kontroly == null) {
            return null;
        }
        Object[][] result = new Object[kontroly.size()][7];
        int i = 0;
        for (Kontrola k : kontroly) {
            result[i][0] = k.getSurovina().getName();
            result[i][1] = k.getStareMnozstvi();
            result[i][2] = k.getNoveMnozstvi();
            result[i][3] = k.getProdanoVahou();
            result[i][4] = k.getProdanoPokladnou();
            result[i][5] = k.getRozdil();
            if (k.isValid()) {
                result[i][6] = "ano";
            } else {
                result[i][6] = "ne";
            }
            i++;
        }
        return result;

    }
    //pridava kontrolu

    public void addKontrola(Kontrola k) {
        k.setUzaverka(uzaverka);
//        List<Uzaverka> uzaverky = Uzaverka.findAll("Uzaverka");
//        Uzaverka previous = null;
//        if (uzaverky.size() - 2 >= 0) {
//            previous = uzaverky.get(uzaverky.size() - 2);
//        }
//        Date date = null;
//        if (previous == null) {
//            date = new Date(0);
//        } else {
//            date = previous.getDate();
//        }
//        double[] result = UsedMaterial.findQuantityOfUsedMaterialUsedAfterInOrders(date, k.getSurovina());
//        k.setProdanoPokladnou(result[0]);
        k.create();
    }

    //vraci vsechny uzaverky
    public Object[][] getAllUzaverky() {
        List<Uzaverka> uzaverky = Uzaverka.findAll("Uzaverka");
        if (uzaverky == null || uzaverky.size() == 0) {
            return new Object[0][0];
        }
        Object[][] result = new Object[uzaverky.size()][4];
        int i = 0;
        Collections.sort(uzaverky,new UzaverkaComparator());
        for (Uzaverka uzav : uzaverky) {
            result[i][0] = uzav.getId();
            SimpleDateFormat dateFormater = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
            result[i][1] = dateFormater.format(uzav.getDate());
            result[i][3] = uzav.getUser().getLastName();
            List<Kontrola> kontroly = Kontrola.findByUzaverka(uzav.getId());
            if (kontroly != null) {
                result[i][2] = kontroly.size();
            } else {
                result[i][2] = 0;
            }
            i++;
        }
        return result;
    }

    //vraci seznam kontrol uzaverky
    public List<Kontrola> getAllKontrolyList() {
        List<Kontrola> kontroly = Kontrola.findByUzaverka(uzaverka.getId());
        return kontroly;
    }

    //scita jednotlive uzaverky reprezentovany celym cislem v pole ids
    public List<Object[]> getStatistikaFromUzaverky(int[] ids) {
        List<Object[]> result = new ArrayList<Object[]>();
        for (int i : ids) {
            uzaverka = ((Uzaverka) Uzaverka.findById("Uzaverka", "id", i));
            List<Kontrola> k = getAllKontrolyList();
            if (k == null) {
                continue;
            }
            for (Kontrola temp : k) {
                if (!temp.isValid()) {
                    continue;
                }
                boolean found = false;
                for (Object[] temp2 : result) {
                    if (temp.getSurovina().getName().equals(temp2[0])) {
                        temp2[2] = ((Double) temp2[2]) + temp.getProdanoVahou();
                        found = true;
                    }
                }
                if (!found) {
                    result.add(new Object[]{temp.getSurovina().getName(), temp.getStareMnozstvi(), temp.getProdanoVahou()});
                }
            }

        }
        return result;
    }
}
