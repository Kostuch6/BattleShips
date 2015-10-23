/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

import javax.swing.Icon;
import javax.swing.JToggleButton;

/**
 *
 * @author Lukas
 */
public class CreationButton extends JToggleButton
{
    private final int shipSize;
    private int amount;

    public CreationButton(int shipSize, int amount, Icon icon) 
    {
        super(icon);
        this.shipSize = shipSize;
        this.amount = amount;
    }

    public int getShipSize() 
    {
        return shipSize;
    }

    public int getAmount() 
    {
        return amount;
    }

    public void decAmount() 
    {
        amount -=1;
    }
    
    public void setAmount(int amount)
    {
        this.amount=amount;
    }
}