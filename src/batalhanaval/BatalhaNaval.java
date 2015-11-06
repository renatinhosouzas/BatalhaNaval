package batalhanaval;

import RedeComunicacao.AtorComunicacao;
import java.awt.Component;

/**
 *
 * @author renato.souza
 */
public class BatalhaNaval {

    private AtorComunicacao atorComunicacao;
    
    public BatalhaNaval(){
        super();
        // Uma instancia de AtorComunicação recebe uma 
        // referência para BatalhaNaval em sua construção.
        atorComunicacao = new AtorComunicacao(this);
    }
    
    public void go(){
        this.atorComunicacao.conectar("Renato", "localhost");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Teste 8");
    }

    public void iniciarPartidaRede() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Component getFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void receberMensagemRede(String mensagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
