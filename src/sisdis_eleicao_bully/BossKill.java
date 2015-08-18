/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdis_eleicao_bully;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class BossKill extends Thread{
    public void run(){
        try {
            sleep(6000l);
            SisDis_Eleicao_Bully.coordenador.kill();
        } catch (InterruptedException ex) {
            Logger.getLogger(BossKill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
