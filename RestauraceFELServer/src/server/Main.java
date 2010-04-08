/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.ServerLogger;
import service.ConfigParser;
import service.ServiceFacadeCash;
import service.ServiceFacadeManager;
import service.ServiceFacadePDA;


/**
 *
 * @author Jarda
 */
public class Main {

    public static void main(String[] args) {        

        ServerLogger log = ServerLogger.getInstance();

        //inicializace Java RMI
        try {
            log.writeLogMessage(Level.INFO, "Server spusten");
            ServiceFacadeManager facadeManager = ServiceFacadeManager.getInstance();
            ServiceFacadeCash facadeCash = ServiceFacadeCash.getInstance();
            ServiceFacadePDA facadePDA = ServiceFacadePDA.getInstance();
            //Vsechny fasady jsou registrovany a pristupny pres port 1099 (jmenna sluzba)
            Registry reg = LocateRegistry.createRegistry(1099);
            facadeManager.initServiceFacadeRMI(reg);
            facadeCash.initServiceFacadeRMI(reg);
            facadePDA.initServiceFacadeRMI(reg);
            File file=new File("../Manager/RunManager.bat");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file.getAbsolutePath());
        } catch (RemoteException re) {            
            log.writeThrowingException("ServiceFacadeManager", "getInstance", re);
            log.writeThrowingException("ServiceFacadeCash", "getInstance", re);
            log.writeThrowingException("ServiceFacadePDA", "getInstance", re);
            log.writeThrowingException("ServiceFacadeManager", "initServiceFacadeRMI", re);
            log.writeThrowingException("ServiceFacadeCash", "initServiceFacadeRMI", re);
            log.writeThrowingException("ServiceFacadePDA", "initServiceFacadeRMI", re);
            System.exit(-1);
        } catch (UnknownHostException uhe){            
            log.writeThrowingException("ServiceFacadeManager", "getInstance", uhe);
            log.writeThrowingException("ServiceFacadeCash", "getInstance", uhe);
            log.writeThrowingException("ServiceFacadePDA", "getInstance", uhe);
            log.writeThrowingException("ServiceFacadeManager", "initServiceFacadeRMI", uhe);
            log.writeThrowingException("ServiceFacadeCash", "initServiceFacadeRMI", uhe);
            log.writeThrowingException("ServiceFacadePDA", "initServiceFacadeRMI", uhe);
            System.exit(-1);
        }
         catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("not found;");
            System.exit(-1);
        }
         catch (Exception ex){
            log.writeThrowingException("ServiceFacadeManager", "getInstance", ex);
            log.writeThrowingException("ServiceFacadeCash", "getInstance", ex);
            log.writeThrowingException("ServiceFacadePDA", "getInstance", ex);
            log.writeThrowingException("ServiceFacadeManager", "initServiceFacadeRMI", ex);
            log.writeThrowingException("ServiceFacadeCash", "initServiceFacadeRMI", ex);
            log.writeThrowingException("ServiceFacadePDA", "initServiceFacadeRMI", ex);
            ex.printStackTrace();
            System.exit(-1);
        
    }
    }
}
