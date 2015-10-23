/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Lukas
 */
public class Field extends JButton
{
    private final Integer x;
    private final Integer y;
    private boolean occupied;
    
    public Field(int x, int y, Icon icon) 
    {
        super(icon);
        this.x = x;
        this.y = y;
        occupied=false;        
    }
    
    public Integer getCoordX()
    {
        return x;
    }
    
    public Integer getCoordY()
    {
        return y;
    }

    public boolean isOccupied()
    {
        return occupied;
    }

    public void setOccupied(boolean occupied)
    {
        this.occupied=occupied;
    }
}