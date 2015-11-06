/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedeComunicacao;

import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import batalhanaval.BatalhaNaval;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author renato.souza
 */
public class AtorComunicacao implements OuvidorProxy{
    
    private BatalhaNaval batalhaNaval;
    private Proxy proxy;
    
    public AtorComunicacao(BatalhaNaval bn){
        super();
        this.batalhaNaval = bn;
        proxy = Proxy.getInstance();
    }

    /**
    * Executa a conexão do jogo com o servidor NetGames
    * @param nome
    * @param servidor
    */
    public void conectar(String nome, String servidor){
        try {
            proxy.conectar(servidor, nome);
        } catch (JahConectadoException ex) {
            JOptionPane.showMessageDialog(this.batalhaNaval.getFrame(), ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(AtorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoPossivelConectarException ex) {
            JOptionPane.showMessageDialog(this.batalhaNaval.getFrame(), ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(AtorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArquivoMultiplayerException ex) {
            JOptionPane.showMessageDialog(this.batalhaNaval.getFrame(), ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(AtorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** 
    * Inicia uma partida entre cliente e servidor.
    * Com essa partida iniciada, os jogadores trocarão mensagens entre si.
    */
    public void iniciarPartidaRede(){
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(this.batalhaNaval.getFrame(), ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(AtorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void iniciarNovaPartida(Integer posicao) {
       batalhaNaval.iniciarPartidaRede();
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberJogada(Jogada jogada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tratarConexaoPerdida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
