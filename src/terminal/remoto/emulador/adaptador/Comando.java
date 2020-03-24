/*
 * A classe Comando permite solicitar comandos através dos métodos estáticos
 * e o comando formatado para o sistema operacional (Linux/Windwos) é devolvido
 */
package terminal.remoto.emulador.adaptador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;
import terminal.remoto.emulator.TerminalEmulator;

/**
 *
 * @author anderson
 */
public class Comando 
{
    public static final int COMANDO_SISTEMA = 0;
    public static final int COMANDO_TERMINAL = 1;
    public static final int COMANDO_ROBOT = 2;
    public static final int COMANDO_PREDEFINIDO = 3;
    public static final int COMANDO_INFO = 4;
    //
    public static final String COMANDO = "comando-";
    public static final String SISTEMA = "system-";
    public static final String ROBOT = "robot-";
    public static final String INFO = "info-";
    //
    public static final String NOME_SISTEMA = System.getProperty("os.name");
    public static final int DESLIGAR = 1;
    public static final int CANCELAR_DESLIGAMENTO = 2;
    public static final int REINICIAR = 3;
    public static final int SUSPENDER = 4;
    public static final int BLOQUEAR_TELA = 5;
    public static final int AUMENTAR_VOLUME = 6;
    public static final int DIMINUIR_VOLUME = 7;
    public static final int MUDO = 8;
    public static final int CONFIGURACAO_IP = 9;
    public static final int PWD = 11;
    public static final int DATE = 12;
    public static final int HISTORY = 13;
    public static final int LISTAR_DIRETORIO = 14;


    public static String executarComando(String comando)
    {
        String resultado = "";
        switch(checarTipoComando(comando))
        {
            case COMANDO_SISTEMA:
                resultado = executaComandoSistema(comando);
                break;
            case COMANDO_TERMINAL:
                resultado = executaComandoTerminal(comando);
                break;
            case COMANDO_ROBOT:
                break;
            case COMANDO_PREDEFINIDO:
                resultado = escolherComandoPorId(Integer.parseInt(comando));
                break;
            case COMANDO_INFO:
                resultado = executaComandoInfo(comando);
                break;
        }
        return resultado;
    }
    
    public static int checarTipoComando(String comando)
    {
        int tipoComando = -1;
        
        if(isComandoPredefinido(comando))
            tipoComando = COMANDO_PREDEFINIDO;
        else
            if(isComandoTerminal(comando))
                tipoComando = COMANDO_TERMINAL;
            else
                if(isComandoSistema(comando))
                    tipoComando = COMANDO_SISTEMA;
                else
                    if(isComandoRobot(comando))
                        tipoComando = COMANDO_ROBOT;
                    else
                        if(isComandoInfo(comando))
                            tipoComando = COMANDO_INFO;
                        
        
        return tipoComando;
    }
    
    public static boolean isComandoTerminal(String comando)
    {
        boolean comandoTerminal = false;
        if(comando.contains(COMANDO))
            comandoTerminal = true;
        return comandoTerminal;
    }
    
    public static boolean isComandoSistema(String comando)
    {
        boolean comandoSistema = false;
        if(comando.contains(SISTEMA))
            comandoSistema = true;
        return comandoSistema;
    }
    
    public static boolean isComandoRobot(String comando)
    {
        boolean comandoRobot = false;
        if(comando.contains(ROBOT))
            comandoRobot = true;
        return comandoRobot;
    }
    
    public static boolean isComandoPredefinido(String comando)
    {
        boolean comandoPredefinido = false;
        if(comando.matches("[0-9]*"))
            comandoPredefinido = true;
        return comandoPredefinido;
    }
    
    public static boolean isComandoInfo(String comando)
    {
        boolean comandoInfo = false;
        if(comando.contains(INFO))
            comandoInfo = true;
        return comandoInfo;
    }
    
    public static String executaComandoSistema(String comando)
    {
        comando = comando.replace(Comando.SISTEMA, "");
        String resultado = System.getProperty(comando);
        return resultado;
    }
    
    public static String executaComandoTerminal(String comando)
    {
        String resultado = "";
        if(comando.contains(COMANDO))
            comando = comando.replace(Comando.COMANDO, "");
        Map<String,String> result = TerminalEmulator.exec(comando);
        if (result.get("error") != null && !result.get("error").equals(""))
        {   resultado = result.get("error");    }
        else if (result.get("input") != null && !result.get("input").equals(""))
        {   resultado = result.get("input");    }
        
        return resultado;
    } 
    
    public static String escolherComandoPorId(int id)
    {
        String comando = "";
        switch(id)
        {
            case DESLIGAR:
                comando = Comando.desligar();
                break;
            case CANCELAR_DESLIGAMENTO:
                comando = Comando.cancelarDesligamento();
                break;
            case REINICIAR:
                comando = Comando.reiniciar();
                break;
            case SUSPENDER:
                comando = Comando.suspender();
                break;
            case BLOQUEAR_TELA:
                comando = Comando.bloquearTela();
                break;
            case AUMENTAR_VOLUME:
                comando = Comando.aumentarVolume();
                break;
            case DIMINUIR_VOLUME:
                comando = Comando.diminuirVolume();
                break;
            case MUDO:
                comando = Comando.mudo();
                break;
            case LISTAR_DIRETORIO:
                comando = Comando.listaDiretorio();
                break;
            case CONFIGURACAO_IP:
                comando = Comando.configuracaoIp();
                break;
            case PWD:
                comando = Comando.pwd();
                break;
            case DATE:
                comando = Comando.date();
                break;
            case HISTORY:
                comando = Comando.history();
                break;
        }
        comando = executaComandoTerminal(comando);
        return comando;
    }
    
    public static String executaComandoInfo(String comando)
    {
        String resultado = "";
        if(comando.contains(INFO))
        {
            Toolkit toolKit = Toolkit.getDefaultToolkit();
            Dimension dimensao = toolKit.getScreenSize();
            resultado = "info-altura-"+dimensao.getHeight()+ ";largura-" + dimensao.getWidth();
        }
        return resultado;
    }
    
    public static boolean isLinux()
    {
        boolean isLinux = false;
        if(Comando.NOME_SISTEMA.equalsIgnoreCase("Linux"))
            isLinux = true;
        return isLinux;
    }
    
    public static boolean isWindows()
    {
        boolean isWindows = !isLinux();
        return isWindows;
    }
    
    public static String desligar()
    {
        String comando = "";
        if(isLinux())
            comando += "shutdown -h now";
        else
            comando += "shutdown /s /t 0";
        return comando;
    }
    
    public static String cancelarDesligamento()
    {
        String comando = "";
        if(isLinux())
            comando += "shutdown -c";
        else
            comando += "shutdown /a";
        return comando;
    }
    
    public static String reiniciar()
    {
        String comando = "";
        if(isLinux())
            comando = "shutdown -r";
        else
            comando = "shutdown /r";
        return comando;
    }
    
    public static String suspender()
    {
        String comando = "";
        if(isLinux())
            comando = "systemctl suspend";
        else
            comando = "rundll32.exe PowrProf.dll,SetSuspendState";
        return comando;
    }
    
    public static String bloquearTela()
    {
        String comando = "";
        if(isLinux())
            comando = "cinnamon-screensaver-command -l";
        else
            comando = "Rundll32.exe User32.dll,LockWorkStation";
        return comando;
    }
    
    public static String aumentarVolume()
    {   return Comando.aumentarVolume(5); }
    
    public static String aumentarVolume(int valor)
    {
        String comando = "";
        if(isLinux())
            comando = "volume.sh + " + valor;
        return comando;
    }
    
    public static String diminuirVolume()
    {   return Comando.diminuirVolume(5); }
    
    public static String diminuirVolume(int valor)
    {
        String comando = "";
        if(isLinux())
            comando = "volume.sh - " + valor;
        return comando;
    }
    
    public static String listaDiretorio()
    {
        String comando = "";
        if(isLinux())
            comando = "ls";
        else
            comando = "dir";
        return comando;
    }
    
    public static String configuracaoIp()
    {
        String comando = "";
        if(isLinux())
            comando = "ifconfig";
        else
            comando = "ipconfig";
        return comando;
    }
    
    public static String mudo()
    {   return Comando.diminuirVolume(100); }
    
    public static String pwd()
    {
        String comando = "";
        if(isLinux())
            comando = "pwd";
        return comando;
    }
    
    public static String date()
    {
        String comando = "";
        if(isLinux())
            comando = "date";
        return comando;
    }
    
    public static String history()
    {
        String comando = "";
        if(isLinux())
            comando = "history";
        return comando;
    }
}
