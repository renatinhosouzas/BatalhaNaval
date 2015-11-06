/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedeComunicacao;

import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author renato.souza
 */
public class MensagensRede implements Jogada{
    
    private String mensagem;
    
    public MensagensRede(String mensagem){
        super();
        this.mensagem = mensagem;
    }
    
    public String getMensagem(){
        return mensagem;
    }
}
