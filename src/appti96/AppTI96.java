/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appti96;

import appti96.controller.PelangganJpaController;
import appti96.models.Pelanggan;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ASUS
 */
public class AppTI96 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("appTI96PU");
        Pelanggan pelanggan=new Pelanggan();
        PelangganJpaController pelangganctController=new PelangganJpaController(emf);
        
        pelanggan.setKodepelanggan("18630294");
        pelanggan.setNamapelanggan("M.Supriyadi");
        pelanggan.setEmail("pb.supriyadi1996@gmail.com");
        
        try {
            pelangganctController.create(pelanggan);
        } catch (Exception ex) {
            Logger.getLogger(AppTI96.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
