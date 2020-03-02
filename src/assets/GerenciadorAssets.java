/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import java.net.URL;

/**
 *
 * @author anderson
 */
public class GerenciadorAssets 
{
    public static final String TERMINAL = "Terminal.png";
    
    public static URL getImagemUrl(String imagem)
    {
        URL url = GerenciadorAssets.class.getResource(imagem);
        return url;
    }    
}
