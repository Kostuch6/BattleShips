/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

/**
 *
 * @author Lukas
 */
public class ClientReader extends Thread
{
    private final InputStream in;
    private final GUI gui;
    
    // sprawdzam
    public ClientReader(GUI gui, InputStream in) 
    {
        this.gui=gui;
        this.in=in;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            StringBuffer sb = new StringBuffer();
            try 
            {
                System.out.println("reading");
                //Czytanie danych ze strumienia wejsciowego (od serwera)
                int k;
                
                while ((k = in.read()) != -1 && k != '\n')// czytanie po jednym bajcie
                {
                    sb.append((char) k);	// zamiana bajtu na char i wstawianie do bufora 
                }
                check(sb.toString());
            }
            catch (IOException e)
            {
                System.err.println("blad "+e);
            }
            System.out.println(sb);
        }
    }
    
    private void check(String str)
    {
        StringTokenizer strTok=new StringTokenizer(str,":");
        System.out.println("checking");
        switch (strTok.nextToken()) 
        {
            case "HIT":
            {
                gui.setHit(Integer.parseInt(strTok.nextToken()),Integer.parseInt(strTok.nextToken()));
                gui.turn.change();
            }
            break;
            
            case "HITOPONENT":
            {
                gui.setHitOponent(Integer.parseInt(strTok.nextToken()),Integer.parseInt(strTok.nextToken()));
                gui.turn.change();
            }
            break;
                
            case "MISS":
            {
                gui.setMiss(Integer.parseInt(strTok.nextToken()),Integer.parseInt(strTok.nextToken()));
                gui.turn.change();
            }
            break;
                
            case "MISSOPONENT":
            {
                gui.setMissOponent(Integer.parseInt(strTok.nextToken()),Integer.parseInt(strTok.nextToken()));
                gui.turn.change();
            }
            break;
                
            case "DESTROY":
            {
                gui.setMiss(Integer.parseInt(strTok.nextToken()),Integer.parseInt(strTok.nextToken()));
            }
            break;
                
            case "DESTROYOP":
            {
                gui.setMissOponent(Integer.parseInt(strTok.nextToken()),Integer.parseInt(strTok.nextToken()));
            }
            break;
                
            case "NOTDESTROYED":
            {
                gui.setNotDestroyed(Integer.parseInt(strTok.nextToken()),Integer.parseInt(strTok.nextToken()));
            }
            break;
                
            case "SETCLIENTLIST":
            {
                int i=0;
                String[] players=new String[strTok.countTokens()];
                while(strTok.hasMoreTokens())
                {
                    players[i]=strTok.nextToken();
                    i++;
                }
                gui.setPlayersList(players);
            }
            break;
                
            case "PLAYQUERY":
            {
                String oponent=strTok.nextToken();
                gui.showQuery("Gracz "+oponent+" chce z Toba zagrac. Przyjmujesz zaproszenie?",oponent);
            }
            break;
                
            case "PLAYANSWER":
            {
                if(strTok.nextToken().equals("YES"))
                {
                    gui.play();
                }
                else
                {
                    gui.cancelPlay();
                }
            }
            break;

            case "DECIDETURN":
            {
                if(strTok.nextToken().equals("YOUR"))
                {
                    gui.turn.change();
                }
            }
            break;
            
            case "START":
            {
                gui.showGrids();
            }
            break;
            
            case "POPUP":
            {
                gui.showPopup(strTok.nextToken());
            }
            break;
                
            case "WINPOPUP":
            {
                gui.showPopup(strTok.nextToken());
                gui.block();
            }
            break;
              
            case "DCPOPUP":
            {
                gui.showPopup(strTok.nextToken());
                gui.disconnect();
                //System.out.println(strTok.nextToken());
            }
            break;

            case "INVALIDNAME":
            {
                gui.showPopup("Ta nazwa uzytkownika jest juz zajeta.");
            }
            break;
            
            case "VALIDNAME":
            {
                gui.showPlayers();
            }
            break;

            case "SERVERMSG":
            {
                String msg=strTok.nextToken();
                if(!msg.startsWith(gui.getLogin()))
                {
                    gui.addServerMsg(msg);
                }
            }
            break;
            
            case "ROOMMSG":
            {
                gui.addRoomMsg(strTok.nextToken());
            }
            break;

            default:
                throw new AssertionError();
        }
    }
}