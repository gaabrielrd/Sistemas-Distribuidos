package sisdis_eleicao_bully;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Processo extends Thread {

    public int pid;
    public ArrayList<Message> inbox = new ArrayList<>();
    private boolean alive = true;
    private boolean segueEmFrente = false;

    public void fazAlgo() {

    }

    public boolean requestAccess(Processo p) {
        System.err.println(p.pid + " requisitou acesso");
        if (SisDis_Eleicao_Bully.inUse) {
            SisDis_Eleicao_Bully.waitList.add(p);
            p.peraLa();
            return false;
        } else {
            SisDis_Eleicao_Bully.inUse = true;
            return true;
        }
    }

    public void actionComplete() {
        System.err.println("recurso liberado");
        if (SisDis_Eleicao_Bully.waitList.isEmpty()) {
            SisDis_Eleicao_Bully.inUse = false;
        } else {
            System.err.println("--notificando primeiro da fila--");
            SisDis_Eleicao_Bully.waitList.remove(0).dale();
        }
    }

    public void dale() {
        segueEmFrente = true;
    }

    public void peraLa() {
        segueEmFrente = false;
    }

    public void sendMessage(Processo remetente, Processo proc, String msg) {
        proc.inbox.add(new Message(remetente, msg));
    }

    public void bully() throws InterruptedException {
        SisDis_Eleicao_Bully.eleicaoRolando = true;
        System.out.println(this.pid + " invocou uma eleicao");
        for (int i = this.pid + 1; i < SisDis_Eleicao_Bully.processos.size(); i++) {
            Processo next = SisDis_Eleicao_Bully.processos.get(i);

            sendMessage(this, next, "quemmandasoueu");

        }
        sleep(1000l);
        if (inbox.isEmpty()) {
            SisDis_Eleicao_Bully.coordenador = this;
            System.err.println(this.pid + " se  tornou coordenador");
            SisDis_Eleicao_Bully.eleicaoRolando = false;
        } else {
            inbox.clear();
            SisDis_Eleicao_Bully.eleicaoRolando = false;
            System.err.println(this.pid + " desistiu da eleicao");
        }
    }

    public void run() {
        try {
            if (SisDis_Eleicao_Bully.coordenador == null) {
                SisDis_Eleicao_Bully.coordenador = this;
            }
            while (alive) {
                if (pid != SisDis_Eleicao_Bully.coordenador.pid) {
                    sendMessage(this, SisDis_Eleicao_Bully.coordenador, "eaicoordenadorbeleza?");
                    System.out.println(this.pid + " mandou mensagem para o coordenador "
                            + SisDis_Eleicao_Bully.coordenador.pid);
                    sleep(1000l);
                    if (inbox.isEmpty() && !SisDis_Eleicao_Bully.eleicaoRolando) {
                        bully();
                    } else {
                        while (!inbox.isEmpty()) {

                            Message next = inbox.remove(0);
                            if (next.mensagem.equals("quemmandasoueu")) {
                                sendMessage(this, next.remetente, "naoenao");
                            }
                        }

                    }
                    sleep((long) Math.random() * 1000);
                    if (!SisDis_Eleicao_Bully.coordenador.requestAccess(this)) {
                        System.err.println(this.pid + " esperando para usar o recurso");
                        while (!segueEmFrente);
                    }
                    System.err.println(this.pid + " USANDO SESSAO CRITICA");
                    SisDis_Eleicao_Bully.coordenador.actionComplete();

                } else {
                    if (!inbox.isEmpty()) {
                        Message m = inbox.remove(0);
                        if (m.mensagem.equals("eaicoordenadorbeleza?")) {
                            sendMessage(this, m.remetente, "tudocertofera");
                        } else if (m.mensagem.equals("quemmandasoueu")) {
                            sendMessage(this, m.remetente, "naoenao");
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    void kill() {
        alive = false;
    }
}
