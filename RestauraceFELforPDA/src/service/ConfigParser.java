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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

    private static String configFile = "config"+System.getProperty("file.separator")+"config.xml";

    /**
     * Konstruktor tridy ConfigParser. Cesta ke konfiguracnimu souboru je urcena
     * ve staticke promenne "configFile".
     *
     * @throws FileNotFoundException
     */
    public ConfigParser() throws FileNotFoundException {
        file = new File(configFile);
        if (!file.exists()) {
            throw new FileNotFoundException("Soubor "+ file.getPath() +" nebyl nalezen.");
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
    public static String getConfigFile(){
        return ConfigParser.configFile;
    }

    public static void setConfigFile(String configFile){
        ConfigParser.configFile = configFile;
    }

    /**
     * Metoda navraci cestu ke konfiguracnimu souboru.
     *
     * @return cesta ke konfiguracnimu souboru
     */
    public String getAbsoluteConfigFilePath(){
        return file.getAbsolutePath();
    }

    /**
     * Metoda cte element se jmenem uvedenem jako parametr teto metody.
     *
     * @param tagName nazev elementu XML souboru
     * @return Obsah daneho elementu.
     */
    private String getValue(String tagName) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("Soubor "+ file.getPath() +" nebyl nalezen.");
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
     * Metoda zapisuje do elementu "tagName" hodnotu "value".
     *
     * @param tagName nazev elementu
     * @param value hodnota, ktera se na zapsat do elementu
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception
     */
    private void writeValue(String tagName, String value) throws FileNotFoundException, Exception {
        //if (!file.exists()) throw new FileNotFoundException("Soubor "+ file.getPath() +" nebyl nalezen.");
        NodeList elems = doc.getElementsByTagName(tagName);
        Node node = elems.item(0);
        node.setTextContent(value);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer writer = tf.newTransformer();
        writer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        writer.transform(new DOMSource(doc), new StreamResult(configFile));
    }

    /**
     * Metoda navraci informaci o pouzivane mene v ramci klienta.
     *
     * @return String retezec udavajici pouzivanou menu.
     * @throws java.io.FileNotFoundException
     */
    public String getMoney() throws FileNotFoundException {
        return getValue("money");
    }

    /**
     * Metoda navraci IP adresu primarniho serveru.
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public String getPrimaryServerIP() throws FileNotFoundException {
        
        return getValue("primaryServerIP");
    }

    /**
     * Metoda navraci IP adresu sekundarniho serveru.
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public String getSecondaryServerIP() throws FileNotFoundException {
        
        return getValue("secondaryServerIP");
    }

    /**
     * Metoda navraci cestu k adresari s tiskovymi sestavami.
     *
     * @return cesta k adresari se sestavami
     * @throws java.io.FileNotFoundException
     */
    public String getTemplatesDir() throws FileNotFoundException {
        return getValue("printingTemplatesDir");
    }

    /**
     * Metoda zapisujici pouzivanou menu.
     *
     * @param money nazev (pripadne zkratka) pouzivane meny
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception
     */
    public void setMoney(String money) throws FileNotFoundException, Exception {
        writeValue("money", money);
    }

    /**
     * Metoda zapisujici IP adresu primarniho serveru.
     *
     * @param serverIP IP adresa primarniho serveru
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception
     */
    public void setPrimaryServerIP(String serverIP) throws FileNotFoundException, Exception {
        writeValue("primaryServerIP", serverIP);
    }

    /**
     * Metoda zapisujici IP adresu sekundarniho (zalozniho serveru).
     *
     * @param serverIP IP adresa sekundarniho serveru
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception
     */
    public void setSecondaryServerIP(String serverIP) throws FileNotFoundException, Exception {
        writeValue("secondaryServerIP", serverIP);
    }

    /**
     * Metoda zapisujici cestu k adresari obsahujicimu tiskove sestavy.
     *
     * @param dir adresar s tiskovymi sestavami
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception
     */
    public void setTemplatesDir(String dir) throws FileNotFoundException, Exception {
        writeValue("printingTemplatesDir", dir);
    }

}
