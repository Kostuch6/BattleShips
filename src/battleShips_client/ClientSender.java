/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Lukas
 */
public class ClientSender 
{
    public static void send(String str, OutputStream out)
    {
        try
        {
            out.write(str.getBytes());
            out.write("\n".getBytes());// dokladanie znakow konca wiersza
            
        }// obsluga wyjatkow
        catch (IOException ioe)
        {
            System.err.println(ioe);
        }
    }
}