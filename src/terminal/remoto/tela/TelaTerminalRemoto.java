/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.remoto.tela;

import assets.GerenciadorAssets;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import terminal.remoto.emulador.adaptador.Comando;
import terminal.remoto.emulator.RobotEmulator;

/**
 *
 * @author anderson
 */
public class TelaTerminalRemoto extends javax.swing.JFrame 
{
    private boolean autoiniciar;
    private String ip;
    private String porta;
    private String senha;
    private int mensagens = 0;
    private ServerSocket server;
    private Socket cliente;
    private DataInputStream entrada;
    private DataOutputStream saida;
    private ImageIcon iconeJanela;
    private RobotEmulator robotEmulator;
    
    /**
     * Creates new form TelaTerminalRemoto
     */
    public TelaTerminalRemoto() 
    {   this(9999, "", false);  }

    public TelaTerminalRemoto(int porta) 
    {   this(porta, "", true);  }
    
    public TelaTerminalRemoto(int porta, String senha)
    {   this(porta, senha, true);   }
    
    public TelaTerminalRemoto(int porta, String senha, boolean autoiniciar)
    {
        initComponents();
        this.autoiniciar = autoiniciar;
        this.senha = senha;
        this.porta = "" + porta;
        try 
        {   
            this.ip = InetAddress.getLocalHost().getHostAddress();  
            this.robotEmulator = new RobotEmulator();
        } 
        catch (UnknownHostException ex) 
        {   System.err.println("Não foi possível identificar o ip");   } 
        catch (AWTException ex) {
            System.err.println("Não foi possível inicializar o robot.");
        }
        
        jTextFieldIp.setText(this.ip);
        jTextFieldPorta.setText(this.porta);
        jPasswordFieldSenha.setText(this.senha);
        
        iconeJanela = new ImageIcon(GerenciadorAssets.getImagemUrl(GerenciadorAssets.TERMINAL));
        this.setIconImage(iconeJanela.getImage());
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        if (SystemTray.isSupported()) 
        {   configurarSystemTray(); } 
        else 
        {   System.err.println("Tray indisponível");    }
        
        try 
        {   
            if(this.autoiniciar)
                this.iniciar();    
        } 
        catch (IOException ex) 
        {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
    }
    
    
    private void configurarSystemTray()
    {
        String texto = "Servidor desligado.";
        if(autoiniciar)
            texto = "Servidor inicializado";
        
        tray = SystemTray.getSystemTray();
        Image image = iconeJanela.getImage();
        popupTray = new PopupMenu();
        menuItemIniciarTray = new MenuItem("Iniciar");
        menuItemIniciarTray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {   menuItemIniciarTrayActionPerformed(e);  }
        });
        menuItemPararTray = new MenuItem("Parar");
        menuItemPararTray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {   menuItemPararTrayActionPerformed(e);  }
        });
        menuItemExibirOcultarTray = new MenuItem("Exibir/Ocultar");
        menuItemExibirOcultarTray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {   menuItemExibirOcultarTrayActionPerformed(e);  }
        });
        menuItemSairTray = new MenuItem("Sair");
        menuItemSairTray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {   menuItemSairTrayActionPerformed(e);  }
        });
        menuItemPararTray.setEnabled(false);
        
        popupTray.add(menuItemIniciarTray);
        popupTray.add(menuItemPararTray);
        popupTray.add(menuItemExibirOcultarTray);
        popupTray.add(menuItemSairTray);
        
        trayIcon = new TrayIcon(image, texto, popupTray);
        trayIcon.setImageAutoSize(true);
        try 
        {   tray.add(trayIcon);   } 
        catch (AWTException e) 
        {   System.err.println("Não pode adicionar a tray");    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPorta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jButtonIniciar = new javax.swing.JButton();
        jButtonParar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelStatus = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelEndereco = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelMensagens = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEnviar = new javax.swing.JTextField();
        jButtonEnviar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButtonAtualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneComandos = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Terminal Remoto");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configurações"));

        jLabel1.setText("IP:");

        jTextFieldIp.setEditable(false);
        jTextFieldIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIpActionPerformed(evt);
            }
        });

        jLabel2.setText("Porta:");

        jTextFieldPorta.setText("9999");
        jTextFieldPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPortaActionPerformed(evt);
            }
        });

        jLabel3.setText("Senha:");

        jPasswordFieldSenha.setText("123");
        jPasswordFieldSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldSenhaActionPerformed(evt);
            }
        });

        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        jButtonParar.setText("Parar");
        jButtonParar.setEnabled(false);
        jButtonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonParar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonIniciar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonIniciar)
                    .addComponent(jButtonParar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Status"));

        jLabelStatus.setText("Desligado");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelStatus)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Informações"));

        jLabel5.setText("Nome:");

        jLabelNome.setText("Sem dados");

        jLabelEndereco.setText("Sem dados");

        jLabel8.setText("Endereço:");

        jLabelMensagens.setText("Sem dados");

        jLabel10.setText("Mensagens:");

        jLabel4.setText("Mensagem:");

        jTextFieldEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEnviarActionPerformed(evt);
            }
        });

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.setEnabled(false);
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEnviar)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelNome))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelEndereco))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMensagens))
                            .addComponent(jLabel4))
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonEnviar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelEndereco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabelMensagens))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEnviar)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Comandos"));

        jButtonAtualizar.setText("Atualizar");

        jTextPaneComandos.setEditable(false);
        jTextPaneComandos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextPaneComandos.setContentType("text/html"); // NOI18N
        jTextPaneComandos.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    <p style=\"margin-top: 0\">\n    \n    </p>\n  </body>\n</html>\n");
        jTextPaneComandos.setToolTipText("");
        jScrollPane2.setViewportView(jTextPaneComandos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 369, Short.MAX_VALUE)
                        .addComponent(jButtonAtualizar)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAtualizar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIpActionPerformed

    private void jTextFieldPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPortaActionPerformed
        try 
        {   this.iniciar();  } 
        catch (IOException ex) 
        {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
    }//GEN-LAST:event_jTextFieldPortaActionPerformed

    private void jPasswordFieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaActionPerformed
        try 
        {   this.iniciar();  } 
        catch (IOException ex) 
        {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
    }//GEN-LAST:event_jPasswordFieldSenhaActionPerformed

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        try 
        {   this.iniciar();  } 
        catch (IOException ex) 
        {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        try 
        {   this.enviar();  } 
        catch (IOException ex) 
        {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jTextFieldEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEnviarActionPerformed
        // TODO add your handling code here:
        try 
        {   this.enviar();  } 
        catch (IOException ex) 
        {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
    }//GEN-LAST:event_jTextFieldEnviarActionPerformed

    private void jButtonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPararActionPerformed
        try 
        {   
            String senha = JOptionPane.showInputDialog(jPanel1, "Informe a senha parar finalizar o servidor:", "Finalizar o servidor", JOptionPane.INFORMATION_MESSAGE);
            if(senha != null)
            {
                if(senha.equals(this.senha))
                    this.parar();   
                else
                    JOptionPane.showMessageDialog(jPanel1, "Senha incorreta.", "Erro ao finalizar a execução", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (IOException ex) 
        {   System.err.println("Não foi possível finalizar a execução do servidor.");   }
    }//GEN-LAST:event_jButtonPararActionPerformed

    private void menuItemIniciarTrayActionPerformed(java.awt.event.ActionEvent evt)
    {   
        try 
        {   this.iniciar();  } 
        catch (IOException ex) 
        {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   } 
    }
    
    private void menuItemPararTrayActionPerformed(java.awt.event.ActionEvent evt)
    {   
        try 
        {   
            String senha = JOptionPane.showInputDialog(null, "Informe a senha parar finalizar o servidor:", "Finalizar o servidor", JOptionPane.INFORMATION_MESSAGE);
            if(senha != null)
            {
                if(senha.equals(this.senha))
                    this.parar();   
                else
                    JOptionPane.showMessageDialog(null, "Senha incorreta.", "Erro ao finalizar a execução", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (IOException ex) 
        {   System.err.println("Não foi possível finalizar a execução do servidor.");   }
    }
    
    private void menuItemSairTrayActionPerformed(java.awt.event.ActionEvent evt)
    {   
        if(this.senha != "")
        {
            try 
            {   
                String senha = JOptionPane.showInputDialog(null, "Informe a senha parar finalizar o servidor:", "Finalizar o servidor", JOptionPane.INFORMATION_MESSAGE);
                if(senha != null)
                {
                    if(senha.equals(this.senha))
                    {
                        this.parar();
                        System.exit(0);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Senha incorreta.", "Erro ao finalizar a execução", JOptionPane.ERROR_MESSAGE);
                }
            } 
            catch (IOException ex) 
            {   System.err.println("Não foi possível finalizar a execução do servidor.");   }
        }
        else
        {
            try 
            {   this.parar();   } 
            catch (IOException ex) 
            {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
            System.exit(0);
        }
            
    }
    
    private void menuItemExibirOcultarTrayActionPerformed(java.awt.event.ActionEvent evt)
    {   this.setVisible(!this.isVisible()); }
    
    
    
    //---------------------------MÉTODOS AUXILIARES-----------------------------
    private void iniciar() throws IOException
    {
        this.ip = jTextFieldIp.getText();
        this.senha = new String(jPasswordFieldSenha.getPassword());
        this.porta = jTextFieldPorta.getText();
        this.mensagens = 0;
        
        server = new ServerSocket(Integer.parseInt(this.porta));
        Thread aguardaConexao = new Thread(new AguardaConexao());
        aguardaConexao.start();
        jButtonIniciar.setEnabled(false);
        jButtonParar.setEnabled(true);
        jButtonEnviar.setEnabled(true);
        jLabelStatus.setText("Ligado");
        trayIcon.setToolTip("Servidor ligado");
        
        menuItemIniciarTray.setEnabled(false);
        menuItemPararTray.setEnabled(true);
    }
    
    private void reiniciar() throws IOException
    {
        parar();
        if(autoiniciar)
            iniciar();
    }
    
    private void parar() throws IOException
    {
        jButtonIniciar.setEnabled(true);
        jButtonParar.setEnabled(false);
        jLabelStatus.setText("Desligado");
        jLabelEndereco.setText("Sem dados");
        jLabelMensagens.setText("Sem dados");
        jLabelNome.setText("Sem dados");
        jTextPaneComandos.setText("");
        trayIcon.setToolTip("Servidor parado");
        
        menuItemIniciarTray.setEnabled(true);
        menuItemPararTray.setEnabled(false);
        
        if(entrada != null)
            entrada.close();
        if(saida != null)
            saida.close();
        if(cliente != null)
            cliente.close();
        if(server != null)
            server.close();
    }
    
    private void enviar() throws IOException
    {
        if(cliente != null && cliente.isConnected())
        {   
            saida.writeUTF(jTextFieldEnviar.getText());
            jTextFieldEnviar.setText("");
        }
    }
    
    private boolean estadoServidor()
    {
        boolean ligado = false;
        if(server != null)
            ligado = true;
        return ligado;
    }
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaTerminalRemoto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaTerminalRemoto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaTerminalRemoto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaTerminalRemoto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaTerminalRemoto telaTerminalRemoto;
                
                switch(args.length)
                {
                    case 0:
                        telaTerminalRemoto = new TelaTerminalRemoto();
                        break;
                    case 1:
                        telaTerminalRemoto = new TelaTerminalRemoto(Integer.parseInt(args[0]));
                        break;
                    case 2:
                        telaTerminalRemoto = new TelaTerminalRemoto(Integer.parseInt(args[0]), args[1]);
                        break;
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButtonParar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelMensagens;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldEnviar;
    private javax.swing.JTextField jTextFieldIp;
    private javax.swing.JTextField jTextFieldPorta;
    private javax.swing.JTextPane jTextPaneComandos;
    // End of variables declaration//GEN-END:variables
    private SystemTray tray;
    private TrayIcon trayIcon;
    private PopupMenu popupTray;
    private MenuItem menuItemExibirOcultarTray;
    private MenuItem menuItemIniciarTray;
    private MenuItem menuItemPararTray;
    private MenuItem menuItemSairTray;

    
    class AguardaConexao implements Runnable
    {
        @Override
        public void run() 
        {
            try 
            {
                cliente = server.accept();
                entrada = new DataInputStream(cliente.getInputStream());
                saida = new DataOutputStream(cliente.getOutputStream());
                saida.flush();
                
                String senhaCliente = entrada.readUTF();
                if(!senha.equals(senhaCliente))
                {
                    saida.writeInt(1);
                    reiniciar();
                }
                else
                {
                    jLabelStatus.setText("Conectado");
                    jLabelNome.setText(cliente.getInetAddress().getHostName());
                    jLabelEndereco.setText(cliente.getInetAddress().getHostAddress());
                    jLabelMensagens.setText("0");
                    saida.writeInt(0);
                    Thread aguardaComando = new Thread(new AguardaComandos());
                    aguardaComando.start();
                    System.out.println("Cliente: "+cliente.getInetAddress().getHostAddress()+" conectado.");
                }
            } 
            catch (IOException ex) 
            {   System.err.println("O servidor foi interrompido!");   }
        }
    }
    
    
    class AguardaComandos implements Runnable 
    {
        @Override
        public void run() 
        {
            System.out.println("Aguardando mensagens...");
            try 
            {
                String comandosAntigos = "";
                while(true)
                {
                    String leituraExterna = entrada.readUTF();
                    String comando = leituraExterna;
                    String mensagem = cliente.getInetAddress().getHostAddress()+": " + comando;
                    
                    if(!Comando.isComandoRobot(comando))
                        comando = Comando.executarComando(comando);
                    else
                        comando = robotEmulator.executarComando(comando);
                    
                    mensagem += "\n" + comando;
                    jTextFieldEnviar.setText(comando);
                    
                    enviar();
                    comandosAntigos += transformarComandoParaHTML(mensagem);
                    jTextPaneComandos.setText(formatarResultadoHTML(comandosAntigos));
                    jTextPaneComandos.setSelectionEnd(jTextPaneComandos.getText().length());
                    
                    System.out.println(mensagem);
                    mensagens++;
                    jLabelMensagens.setText(""+mensagens);
                }
            }   
            catch (EOFException ex) 
            {   System.err.println("Conexão encerrada inesperadamente");    }
            catch (IOException ex) 
            {   Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);   }
            finally
            {
                try 
                {   reiniciar();    } 
                catch (IOException ex) 
                {    Logger.getLogger(TelaTerminalRemoto.class.getName()).log(Level.SEVERE, null, ex);  }
            }
        }
                
        public String transformarComandoParaHTML(String texto)
        {   return texto.replace("\n", "<br>") + "<hr/";    }
        
        public String formatarResultadoHTML(String resultado)
        {
            String HTMLText = "<html>\n" +
                "  <head></head>\n" +
                "  <body>\n" +
                resultado +
                "  </body>\n" +
                "</html>\n" +
                "";
            
            return HTMLText;
        }
    }
}
