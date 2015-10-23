/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Lukas
 */
public class BattleShips_Client 
{

    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("localhost", 2000);
            System.out.println("Connected To Server ...");
            //Pobieranie strumieni do gniazda
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            GUI gui=new GUI(out);
            javax.swing.SwingUtilities.invokeLater(new Runnable() 
            {
                @Override
                public void run(){};
            });
        
        ClientReader reader=new ClientReader(gui,in);
        reader.start();
        }
        catch(UnknownHostException uhe)
        {
            System.err.println(uhe);
        } 
        catch (IOException ioe) 
        {
            System.err.println(ioe);
        }
    }
}