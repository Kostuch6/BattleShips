/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Lukas
 */
public class TurnLabel extends JLabel
{
    private final ImageIcon yourTurn;
    private final ImageIcon enemyTurn;
    private boolean turn;

    public TurnLabel(ImageIcon yourTurn, ImageIcon enemyTurn) 
    {
        super(enemyTurn);
        this.yourTurn = yourTurn;
        this.enemyTurn = enemyTurn;
        turn=false;
    }
    
    public void change()
    {
        if(turn)
        {
            setIcon(enemyTurn);
            turn=false;
        }
        else
        {
            setIcon(yourTurn);
            turn=true;
        }
    }
    
    public boolean isMyTurn()
    {
        return turn;
    }
}