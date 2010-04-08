/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.File;
import java.io.FileNotFoundException;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servisni trida. Slouzi pro nacitani konfiguracnich dat z XML souroru.
 *
 * @author Jarda
 */
public class ConfigParser {

    private File file = null;
    private DocumentBuilder db = null;
    private Document doc = null;
    private String configFile = "";

    /**
     * Konstruktor tridy ConfigParser. Cesta ke konfiguracnimu souboru je urcena
     * ve staticke promenne "configFile".
     *
     * @param path cesta ke konfiguracnimu souboru
     * @throws FileNotFoundException
     */
    public ConfigParser(String path) throws FileNotFoundException {
        this.configFile = path;
        file = new File(configFile);
        if (!file.exists()) {
            throw new FileNotFoundException("Soubor " + file.getPath() + " nebyl nalezen.");
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metoda navraci relativni cestu ke konfiguracnimu souboru.
     *
     * @return relativni cesta ke konfiguracnimu souboru
     */
    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Metoda navraci cestu ke konfiguracnimu souboru.
     *
     * @return cesta ke konfiguracnimu souboru
     */
    public String getAbsoluteConfigFilePath() {
        return file.getAbsolutePath();
    }

    /**
     * Metoda cte element se jmenem uvedenem jako parametr teto metody.
     *
     * @param tagName nazev elementu XML souboru
     * @return Obsah daneho elementu.
     */
    private String getValue(String tagName) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("Soubor " + file.getPath() + " nebyl nalezen.");
        }
        try {
            doc = db.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NodeList elems = doc.getElementsByTagName(tagName);
        Node node = elems.item(0);
        return node.getTextContent();
    }  

    /**
     * Metoda navraci IP adresu na ktere server pobezi serveru.
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public String getServerIP() throws FileNotFoundException {
        return getValue("serverIP");
    }
    
}
