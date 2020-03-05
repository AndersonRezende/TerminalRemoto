/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.remoto.emulator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import terminal.remoto.emulador.adaptador.Comando;

/**
 *
 * @author anderson
 */
public class RobotEmulator 
{
    private static final int BOTAO_ESQUERDO_MOUSE = 0;
    private static final int BOTAO_DIREITO_MOUSE = 1;
    private final Robot robot;
    private int mouseX, mouseY;
    
    public RobotEmulator() throws AWTException
    {
        this.robot = new Robot();
        this.mouseX = 0;
        this.mouseY = 0;
    }
    
    public String executarComando(String comando)
    {
        comando = comando.replace(Comando.ROBOT, "");
        String partes[] = comando.split(" ");
        if(partes[0].equalsIgnoreCase("mover"))
            this.mover(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]));
        else
        {   
            if(partes[0].equalsIgnoreCase("clicar"))
                this.clicar(Integer.parseInt(partes[1]));
        }
            
            
        System.out.println(partes[0]);
        return "Comando executado com sucesso.";
    }
    
    public void mover(int x, int y)
    {
        this.mouseX = x;
        this.mouseY = y;
        robot.mouseMove(mouseX, mouseY);
    }
    
    public void clicar(int botao)
    {
        switch(botao)
        {
            case BOTAO_ESQUERDO_MOUSE:
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                break;
            case BOTAO_DIREITO_MOUSE:
                robot.mousePress(InputEvent.BUTTON3_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_MASK);
                break;
        }
    }
}
