/*
 * A classe Comando permite solicitar comandos através dos métodos estáticos
 * e o comando formatado para o sistema operacional (Linux/Windwos) é devolvido
 */
package terminal.remoto.emulador.adaptador;

/**
 *
 * @author anderson
 */
public class Comando 
{
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
    

    public static String escolherComandoPorId(int id)
    {
        String comando = "";
        switch(id)
        {
            case Comando.DESLIGAR:
                comando = Comando.desligar();
                break;
            case Comando.CANCELAR_DESLIGAMENTO:
                comando = Comando.cancelarDesligamento();
                break;
            case Comando.REINICIAR:
                comando = Comando.reiniciar();
                break;
            case Comando.SUSPENDER:
                comando = Comando.suspender();
                break;
            case Comando.BLOQUEAR_TELA:
                comando = Comando.bloquearTela();
                break;
            case Comando.AUMENTAR_VOLUME:
                comando = Comando.aumentarVolume();
                break;
            case Comando.DIMINUIR_VOLUME:
                comando = Comando.diminuirVolume();
                break;
            case Comando.MUDO:
                comando = Comando.mudo();
                break;
            case Comando.LISTAR_DIRETORIO:
                comando = Comando.listaDiretorio();
                break;
            case Comando.CONFIGURACAO_IP:
                comando = Comando.configuracaoIp();
                break;
            case Comando.PWD:
                comando = Comando.pwd();
                break;
            case Comando.DATE:
                comando = Comando.date();
                break;
            case Comando.HISTORY:
                comando = Comando.history();
                break;
        }
        return comando;
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
            comando += "shutdown -h";
        else
            comando += "shutdown //s";
        return comando;
    }
    
    public static String cancelarDesligamento()
    {
        String comando = "";
        if(isLinux())
            comando += "shutdown -c";
        else
            comando += "shutdown //a";
        return comando;
    }
    
    public static String reiniciar()
    {
        String comando = "";
        if(isLinux())
            comando = "shutdown -r";
        else
            comando = "shutdown //r";
        return comando;
    }
    
    public static String suspender()
    {
        String comando = "";
        if(isLinux())
            comando = "systemctl suspend";
        return comando;
    }
    
    public static String bloquearTela()
    {
        String comando = "";
        if(isLinux())
            comando = "cinnamon-screensaver-command -l";
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
