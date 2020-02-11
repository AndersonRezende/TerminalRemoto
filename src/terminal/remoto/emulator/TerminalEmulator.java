/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.remoto.emulator;

/**
 *
 * @author anderson
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalEmulator {
    
    private static List<String> commandHistory = new ArrayList<String>();
 
    public static Map<String, String> exec(String command) 
    {
        Process proc = null;
        Map<String, String> result = new HashMap<String, String>();
        try
        {
            proc = Runtime.getRuntime().exec(command);
            
            result.put("input", inputStreamToString(proc.getInputStream()));
            result.put("error", inputStreamToString(proc.getErrorStream()));
        }
        catch (IOException e)
        {   result.put("error", e.getCause().getMessage()); }
        finally
        {   commandHistory.add(command);    }
        
        return result;
    }

    private static String inputStreamToString(InputStream isr) 
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(isr));
            StringBuilder sb = new StringBuilder();
            String s = null;

            while ((s = br.readLine()) != null) 
                sb.append(s + "\n");

            return sb.toString();
        }
        catch (IOException e) 
        {   return null;    }
    }
    
    public static void clearHistory()
    {   commandHistory.clear(); }
 
    public static List<String> getCommandHistory() 
    { return commandHistory;    }
}
