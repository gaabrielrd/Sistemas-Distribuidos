/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdis_eleicao_bully;

/**
 *
 * @author Gabriel
 */
public class Message {
    public Processo remetente;
    public String mensagem;
    
    public Message(Processo remetente, String mensagem){
        this.remetente = remetente;
        this.mensagem = mensagem;
    }
}
