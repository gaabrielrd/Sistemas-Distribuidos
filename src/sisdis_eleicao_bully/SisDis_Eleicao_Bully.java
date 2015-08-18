package sisdis_eleicao_bully;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class SisDis_Eleicao_Bully {

    /**
     * A cada 15s nasce um processo
     * A cada 1min morre o coordenador
     */
    public static ArrayList<Processo> processos = new ArrayList();
    public static Processo coordenador;
    public static boolean eleicaoRolando = false;
    public static ArrayList<Processo> waitList = new ArrayList<>();
    public static boolean inUse = false;
    
    public static void main(String[] args) {
        ProcCreator p = new ProcCreator();
        p.start();
        BossKill b = new BossKill();
        b.start();
        for (int i = 0; i < 10; i++) {
            processos.add(new Processo());
            processos.get(i).pid = i;
        }
        for (int i = 0; i < 10; i++) {
            processos.get(i).start();
        }
    }
    
}
