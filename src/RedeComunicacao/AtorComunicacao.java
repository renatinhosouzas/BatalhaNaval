/**
 * Classe responsável por fazer a comunicação através da rede, 
 * além de se comunicar com a aplicação local através de BatalhaNaval,
 * funcionando como uma ponte entre aplicações.
 * 
 * A classe implementa a interface OuvidorProxy, 
 * dessa forma é possível receber todas as mensagens
 * vindas do outro jogador.
 */
package RedeComunicacao;

import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import batalhanaval.BatalhaNaval;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import javax.swing.JOptionPane;

/**
 *
 * @author renato.souza
 */
public class AtorComunicacao implements OuvidorProxy{
    
    // Referencia para o objeto da classe BatalhaNaval.
    private BatalhaNaval batalhaNaval;
    
    // Referencia para o objeto da classe Proxy, possibilitando conectar o jogador ao servidor.
    private Proxy proxy;
    
    public AtorComunicacao(BatalhaNaval bn){
        super();
        this.batalhaNaval = bn;
        // Recebe um objeto da classe através do método getInstance();
        proxy = Proxy.getInstance();
        // para receber mensagens enviadas pelo outro jogador.
        proxy.addOuvinte(this);
    }

    /**
    * Executa a conexão do jogo com o servidor NetGames
    * @param nome (nome de cada jogador, que irá identifica-ló na partida)
    * @param servidor (saturno.inf.ufsc.br)
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
    
    /**
     * Método será chamada sempre que se desejar enviar uma
     * mensagem para outro usuario na mesma partida.
     * 
     * Esse método recebe uma string que deve ser enviada para
     * outra aplicação, para que o outro usuário possa visualizar.
     */
    public void enviarJogada(String mensagem){
        MensagensRede msg = new MensagensRede(mensagem);
        try {
            proxy.enviaJogada(msg);
        } catch (NaoJogandoException ex) {
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
        MensagensRede msg = (MensagensRede) jogada;
        batalhaNaval.receberMensagemRede(msg.getMensagem());
    }
    
    /**
     * Método responsável pela desconexão 
     * do jogo com o servidor
     */
    public void desconectar(){
        try {
            proxy.desconectar();
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(this.batalhaNaval.getFrame(), ex.getMessage());
            ex.printStackTrace();
            //Logger.getLogger(AtorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
