/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Trida zajistujici tvorbu tiskovych sestav a samotny tiskovy vystup.
 *
 * @author Jarda
 */
public class Printer {    

    /**
     * Bezparametricky konstruktor tridy Printer.
     */
    private Printer() {
    }    
    

    /**
     * Metoda vytvarejici PDF vystup z tabulky. Vystup je vytvoren dle sestavy
     * zaznamenane v Jasper souboru.
     *
     * @param params parametry tiskove sestavy
     * @param tableModel datovy model, ze ktereho je tiskovy vystup generovan
     * @param printFile soubor s tiskovou sestavou (.jasper soubor)
     * @param destinationFileName cesta k souboru, do ktereho bude tisk zapsan
     * @throws net.sf.jasperreports.engine.JRException
     */
    public static void printFromTableToPDF(Map<String, Object> params, TableModel tableModel, File printFile, String destinationFileName) throws JRException {

        JasperReport jr = (JasperReport) JRLoader.loadObject(printFile);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRTableModelDataSource(tableModel));
        JasperExportManager.exportReportToPdfFile(jp, destinationFileName);
    }

    /**
     * Metoda vytvarejici tiskovy vystup z tabulky, ktery posila rovnou na
     * tiskarnu.
     *
     * @param params parametry tiskove sestavy
     * @param tableModel datovy model, ze ktereho je tiskovy vystup generovan
     * @param printFile soubor s tiskovou sestavou (.jasper soubor)
     * @throws net.sf.jasperreports.engine.JRException
     */
    public static void printFromTableToPrinter(Map<String, Object> params, TableModel tableModel, File printFile) throws JRException {
        JasperReport jr = (JasperReport) JRLoader.loadObject(printFile);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRTableModelDataSource(tableModel));
        JasperPrintManager.printReport(jp, true);
    }

    /**
     * Metoda vytvarejici tiskovy vystup z kolekce dat (Beanu), kterou posila
     * rovnou na tiskarnu.
     *
     * @param params parametry tiskove sestavy
     * @param collection kolekce Beanu, ktere slouzi jako datovy zdroj
     * @param printFile soubor s tiskovou sestavou (.jasper soubor)
     * @throws net.sf.jasperreports.engine.JRException
     */
    public static void printFromListToPrinter(Map<String, Object> params, Collection collection, File printFile) throws JRException {
        JasperReport jr = (JasperReport) JRLoader.loadObject(printFile);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(collection));
        JasperPrintManager.printReport(jp, true);
    }

    /**
     * Metoda vytvarejici tiskovy vystup z kolekce dat (Beanu), kterou posila
     * rovnou na tiskarnu.
     *
     * @param params parametry tiskove sestavy
     * @param collection kolekce Beanu, ktere slouzi jako datovy zdroj
     * @param printFile soubor s tiskovou sestavou (.jasper soubor)
     * @param destinationFileName cesta k souboru, do ktereho bude tisk zapsan
     * @throws net.sf.jasperreports.engine.JRException
     */
    public static void printFromListToPDF(Map<String, Object> params, Collection collection, File printFile, String destinationFileName) throws JRException {
        JasperReport jr = (JasperReport) JRLoader.loadObject(printFile);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(collection));
        JasperExportManager.exportReportToPdfFile(jp, destinationFileName);
    }

    /**
     * Kompilator tiskove sestavy ve formatu .jrxml do pozadovaneho formatu
     * .jasper.
     *
     * @param file soubor .jrxml
     * @param comFile adresa vystupniho souboru .jasper
     * @throws net.sf.jasperreports.engine.JRException
     */
    public static void compileReport(File file, String comFile) throws JRException{
        JasperCompileManager.compileReportToFile(file.getAbsolutePath(), comFile);
    }
}
