/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

/**
 *
 * @author Lukas
 */
public class ShipList 
{
    private int[][] ships=new int[10][2];
    private String[] position=new String[10];
    private int i3=1;
    private int i2=3;
    private int i1=6;
    
    public void addShip(int x, int y, int shipSize, String position)
    {
        switch (shipSize) 
        {
            case 1:
                ships[i1][0]=x;
                ships[i1][1]=y;
                this.position[i1]=position;
                i1++;
                break;
                
            case 2:
                ships[i2][0]=x;
                ships[i2][1]=y;
                this.position[i2]=position;
                i2++;
                break;
                
            case 3:
                ships[i3][0]=x;
                ships[i3][1]=y;
                this.position[i3]=position;
                i3++;
                break;
            
            case 4:
                ships[0][0]=x;
                ships[0][1]=y;
                this.position[0]=position;
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public int getShipX(int i)
    {
        return ships[i][0];
    }
    
    public int getShipY(int i)
    {
        return ships[i][1];
    }

    public String getPosition(int i) 
    {
        return position[i];
    }
}