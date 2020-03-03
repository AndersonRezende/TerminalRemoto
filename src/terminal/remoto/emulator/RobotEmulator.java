/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.remoto.emulator;

import java.awt.AWTException;
import java.awt.Robot;

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
    
    public void mover(int x, int y)
    {
        this.mouseX = x;
        this.mouseY = y;
        robot.mouseMove(mouseX, mouseY);
    }
    
    public static void atalho() throws AWTException
    {
        Robot robo = new Robot();
        robo.mouseMove(1920, 1080);
    }
}
