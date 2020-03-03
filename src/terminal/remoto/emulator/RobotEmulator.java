/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.remoto.emulator;

import java.awt.AWTException;
import java.awt.Robot;
import terminal.remoto.emulador.adaptador.Comando;

/**
 *
 * @author anderson
 */
public class RobotEmulator 
{
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
        System.out.println(partes[0]);
        return "Movido com sucesso.";
    }
    
    public void mover(int x, int y)
    {
        this.mouseX = x;
        this.mouseY = y;
        robot.mouseMove(mouseX, mouseY);
    }
}
