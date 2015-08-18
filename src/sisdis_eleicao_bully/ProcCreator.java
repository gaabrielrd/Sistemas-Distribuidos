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
public class ProcCreator extends Thread {

    public void run() {
        while (true) {
            try {
                sleep(3000l);
            //    wait(15000l);
                Processo p = new Processo();
                p.pid = SisDis_Eleicao_Bully.processos.size();
                SisDis_Eleicao_Bully.processos.add(p);
                p.start();
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcCreator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
