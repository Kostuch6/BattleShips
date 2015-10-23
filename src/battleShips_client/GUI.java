/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleShips_client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.OutputStream;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Lukas
 */
public class GUI extends JFrame
{
    //<editor-fold defaultstate="collapsed" desc="pola">
    OutputStream out;
    JLabel logo;
    JPanel mainPanel;
    JLabel playersLabel;
    List playersList;
    JPanel playersListPanel;
    JPanel chatPanel;
    List serverChatList;
    JPanel serverChatPanel;
    JTextField serverChatTextField;
    JButton serverSendButton;
    List roomChatList;
    JPanel roomChatPanel;
    JTextField roomChatTextField;
    JButton roomSendButton;
    JPanel loginPanel;
    JButton disconnectButton;
    
    JPanel creationPanel=new JPanel();
    JPanel gridPanel1=new JPanel();
    JPanel gridPanel2=new JPanel();
    JPanel serverPanel;
    JButton newGameButton;
    Field[][] playerGrid=new Field[10][10];
    Field[][] enemyGrid=new Field[10][10];
    JLabel[] letters1=new JLabel[10];
    JLabel[] numbers1=new JLabel[10];
    JLabel[] letters2=new JLabel[11];
    JLabel[] numbers2=new JLabel[10];
    TurnLabel turn;
    
    ButtonGroup creationGroup=new ButtonGroup();
    JLabel[] creationNumbers=new JLabel[4];
    CreationButton[] creationButtons=new CreationButton[4];
    JButton creationGridButton;
    ShipList shipList=new ShipList();
    JTextField login;
    JButton loginButton;
    JLabel loginLabel;
    JLabel loginAd;
    EnemyFieldClick efal;
    JButton creditsButton;
    //</editor-fold>

    
    GUI(OutputStream out)
    {
        super("Battle Ships");
        this.out=out;
        efal=new EnemyFieldClick();
        
        logo=new JLabel(new ImageIcon("logo.png"));
        //<editor-fold defaultstate="collapsed" desc="panele">
        mainPanel=new JPanel();
        mainPanel.setLayout(null);
        gridPanel1.setLayout(new GridLayout(11,11));
        gridPanel2.setLayout(new GridLayout(11,11));
        creationPanel.setLayout(new GridBagLayout());
        loginPanel=new JPanel(new GridBagLayout());
        chatPanel=new JPanel();
        serverPanel=new JPanel();        
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="loginPanel">
        GridBagConstraints c = new GridBagConstraints();
        loginLabel=new JLabel("Podaj nazwę użytkownika");
        loginLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        login=new JTextField();
        login.setFont(new Font("SansSerif", Font.PLAIN, 25));
        login.setPreferredSize(new Dimension(200,30));
        loginButton=new JButton("Zaloguj");
        loginButton.addActionListener(new LoginClick());
        loginAd=new JLabel(new ImageIcon("ad.png"));
        loginAd.setPreferredSize(new Dimension(190,206));
        c.insets=new Insets(0,0,30,0);
        loginPanel.add(loginLabel,c);
        c.gridy=1;
        loginPanel.add(login,c);
        c.gridy=2;
        loginPanel.add(loginButton,c);
        c.gridy=3;
        loginPanel.add(loginAd,c);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="serverPanel">
        playersLabel=new JLabel("Lista graczy");
        playersLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        serverPanel=new JPanel();
        serverPanel.setLayout(new GridBagLayout());
        playersListPanel=new JPanel();
        playersListPanel.setLayout(new BoxLayout(playersListPanel, BoxLayout.Y_AXIS));
        playersList=new List();
        playersListPanel.add(playersList);

        newGameButton=new JButton("Zagraj");
        playersListPanel.setPreferredSize(new Dimension(280,300));
        newGameButton.setPreferredSize(new Dimension(100,60));
        newGameButton.addActionListener(new NewGameClick());
        
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(0,0,0,0);
        serverPanel.add(playersLabel);
        c.gridy=1;
        serverPanel.add(playersListPanel,c);
        c.gridx=1;
        c.insets=new Insets(0,30,0,0);
        serverPanel.add(newGameButton,c);
        
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="chatPanel">
        chatPanel.setLayout(new GridBagLayout());
        
        serverChatPanel=new JPanel();
        serverChatPanel.setLayout(new BoxLayout(serverChatPanel, BoxLayout.Y_AXIS));
        serverChatList=new List();
        serverChatPanel.add(serverChatList);
        serverChatTextField=new JTextField();
        serverSendButton=new JButton("Wyślij");
        serverSendButton.addActionListener(new ServerSendClick());
        serverChatTextField.addKeyListener(new ServerSendEnter());
        
        serverChatPanel.setPreferredSize(new Dimension(330,330));
        serverChatTextField.setPreferredSize(new Dimension(250,20));
        serverSendButton.setPreferredSize(new Dimension(80,20));
        
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        c.insets=new Insets(0,0,0,20);
        chatPanel.add(serverChatPanel,c);
        c.gridy=1;
        c.gridwidth=1;
        c.gridx=1;
        chatPanel.add(serverSendButton,c);
        c.gridx=0;
        c.insets=new Insets(0,0,0,0);
        chatPanel.add(serverChatTextField,c);
        
        roomChatPanel=new JPanel();
        roomChatPanel.setLayout(new BoxLayout(roomChatPanel, BoxLayout.Y_AXIS));
        roomChatList=new List();
        roomChatPanel.add(roomChatList);
        roomChatTextField=new JTextField();
        roomSendButton=new JButton("Wyślij");
        roomSendButton.addActionListener(new RoomSendClick());
        roomChatTextField.addKeyListener(new RoomSendEnter());
        
        roomChatPanel.setPreferredSize(new Dimension(330,330));
        roomChatTextField.setPreferredSize(new Dimension(250,20));
        roomSendButton.setPreferredSize(new Dimension(80,20));
        
        c.gridx=2;
        c.gridy=0;
        c.gridwidth=2;
        chatPanel.add(roomChatPanel,c);
        c.gridy=1;
        c.gridwidth=1;
        chatPanel.add(roomChatTextField,c);
        c.gridx=3;
        chatPanel.add(roomSendButton,c);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="creationPanel">
        creationGridButton=new JButton("Rozpocznij");
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(0,0,30,30);
        for (int i = 0; i < 4; i++) 
        {
            creationNumbers[i]=new JLabel(Integer.toString(4-i)+"x");
            c.gridy=i;
            creationPanel.add(creationNumbers[i],c);
        }
        
        c.gridx=1;
        for (int i = 0; i < 4; i++) 
        {
            creationButtons[i]=new CreationButton(i+1,4-i,new ImageIcon((i+1)+"maszt.png"));
            creationButtons[i].setPreferredSize(new Dimension(30*(i+1),30));
            //creationButtons[i].addMouseListener(new ButtonClick());
            c.gridy=i;
            c.gridwidth=i+1;
            creationPanel.add(creationButtons[i],c);
            creationGroup.add(creationButtons[i]);
        }
        c.gridy=4;
        creationPanel.add(creationGridButton,c);
        creationGridButton.addActionListener(new CreationGridClick());
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Grids">
        //letters1[0]=new JLabel("");
        letters2[0]=new JLabel("");
        turn=new TurnLabel(new ImageIcon("YourTurn.png"), new ImageIcon("EnemyTurn.png"));
        gridPanel1.add(turn);
        gridPanel2.add(letters2[0]);
        
        for(int i = 1; i <= 10; i++)
        {
            letters1[i-1]=new JLabel(Character.toString((char)(64+i)), SwingConstants.CENTER);
            letters2[i]=new JLabel(Character.toString((char)(64+i)), SwingConstants.CENTER);
            gridPanel1.add(letters1[i-1]);
            gridPanel2.add(letters2[i]);
        }
        for(int i = 0; i < 10; i++)
        {
            numbers1[i]=new JLabel(String.valueOf(i+1),SwingConstants.CENTER);
            numbers2[i]=new JLabel(String.valueOf(i+1),SwingConstants.CENTER);
            gridPanel1.add(numbers1[i]);
            gridPanel2.add(numbers2[i]);
            for(int j=0;j<10;j++)
            {
                playerGrid[i][j]=new Field(i,j,new ImageIcon("ramka.png"));
                playerGrid[i][j].setName(String.valueOf(i)+":"+String.valueOf(j)+":");
                playerGrid[i][j].addMouseListener(new PlayerGridClick());
                enemyGrid[i][j]=new Field(i,j,new ImageIcon("ramka.png"));
                enemyGrid[i][j].setName(String.valueOf(i)+":"+String.valueOf(j)+":");
                enemyGrid[i][j].addActionListener(efal);
                
                gridPanel1.add(playerGrid[i][j]);
                gridPanel2.add(enemyGrid[i][j]);
            }
        }
        //</editor-fold>
        
        disconnectButton=new JButton("Wyjdź z gry");
        disconnectButton.addActionListener(new DisconnectClick());
        creditsButton=new JButton(new ImageIcon("credits.png"));
        creditsButton.addActionListener(new CreditsClick());
        creditsButton.setBorderPainted(false);
        
        //<editor-fold defaultstate="collapsed" desc="ustawianie komponentow">
        gridPanel1.setBounds(20, 146, 330, 330);
        gridPanel2.setBounds(370, 146, 330, 330);
        creationPanel.setBounds(340, 180, 330, 300);
        logo.setBounds(191,20,339,106);
        loginPanel.setBounds(0, 146, 720, 600);
        chatPanel.setBounds(20, 480, 680, 350);
        serverPanel.setBounds(0, 146, 660, 330);
        disconnectButton.setBounds(550,90,100,50);
        creditsButton.setBounds(0,0,18,27);
        gridPanel1.setVisible(false);
        gridPanel2.setVisible(false);
        creationPanel.setVisible(false);
        chatPanel.setVisible(false);
        serverPanel.setVisible(false);
        disconnectButton.setVisible(false);
        roomSendButton.setEnabled(false);
        
        mainPanel.add(gridPanel1);
        mainPanel.add(gridPanel2);
        mainPanel.add(creationPanel);
        mainPanel.add(logo);
        mainPanel.add(loginPanel);
        mainPanel.add(chatPanel);
        mainPanel.add(serverPanel);
        mainPanel.add(disconnectButton);
        mainPanel.add(creditsButton);
        mainPanel.setSize(750,900);
        add(mainPanel);
        this.addWindowListener(new Window());
        setVisible(true);
        setSize(750,900);
        //</editor-fold>
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //<editor-fold defaultstate="collapsed" desc="metody">
    public void setHit(int x, int y)
    {
        playerGrid[x][y].setIcon(new ImageIcon("deckHit.png"));
    }
    
    public void setHitOponent(int x, int y)
    {
        enemyGrid[x][y].setIcon(new ImageIcon("deckHit.png"));
        enemyGrid[x][y].removeActionListener(efal);
    }
    
    public void setMiss(int x, int y)
    {
        playerGrid[x][y].setIcon(new ImageIcon("miss.png"));
    }
    
    public void setMissOponent(int x, int y)
    {
        enemyGrid[x][y].setIcon(new ImageIcon("miss.png"));
        enemyGrid[x][y].removeActionListener(efal);
    }
    
    public void setNotDestroyed(int x, int y)
    {
        enemyGrid[x][y].setIcon(new ImageIcon("1maszt.png"));
    }
    
    public void setPlayersList(String[] players)
    {
        playersList.removeAll();
        for (int i = 0; i < players.length; i++) 
        {
            if(!players[i].equals(login.getText()))
            {
                playersList.add(players[i]);
            }
        }
    }
    
    public void showPlayers()
    {
        setTitle(getTitle()+" - "+login.getText());
        loginPanel.setVisible(false);
        serverPanel.setVisible(true);
        chatPanel.setVisible(true);
    }
    
    public void play()
    {
        roomChatList.add("Polaczono. Prosze rozstawic statki.");
        serverPanel.setVisible(false);
        gridPanel1.setVisible(true);
        creationPanel.setVisible(true);
        disconnectButton.setVisible(true);
        roomSendButton.setEnabled(true);
    }
    
    public void showGrids()
    {
        roomChatList.add("Gra rozpoczeta.");
        gridPanel2.setVisible(true);
        creationPanel.setVisible(false);
    }
    
    public void addServerMsg(String msg)
    {
        serverChatList.add(msg);
        serverChatList.makeVisible(serverChatList.getItemCount()-1);
    }
    
    public void addRoomMsg(String msg)
    {
        roomChatList.add(msg);
        roomChatList.makeVisible(roomChatList.getItemCount()-1);
    }

    public void showPopup(String info)
    {
        JOptionPane.showMessageDialog(mainPanel, info);
    }
    
    public void showQuery(String info, String oponent)
    {
        int choice;
        choice=JOptionPane.showConfirmDialog(mainPanel, info, "Zaproszenie", YES_NO_OPTION);
        if(choice==YES_OPTION)
        {
            ClientSender.send("PLAYANSWER:YES:"+oponent, out);
        }
        else
        {
            ClientSender.send("PLAYANSWER:NO:"+oponent, out);
        }
    }
    
    public void cancelPlay()
    {
        roomChatList.add("Przeciwnik odrzucil zaproszenie");
    }
    
    public void disconnect()
    {
        gridPanel1.setVisible(false);
        gridPanel2.setVisible(false);
        creationPanel.setVisible(false);
        disconnectButton.setVisible(false);
        serverPanel.setVisible(true);
        roomSendButton.setEnabled(false);
        resetGrids();
    }
    
    public void resetGrids()
    {
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                playerGrid[i][j].setIcon(new ImageIcon("ramka.png"));
                playerGrid[i][j].setOccupied(false);
                enemyGrid[i][j].setIcon(new ImageIcon("ramka.png"));
                enemyGrid[i][j].removeActionListener(efal);
                enemyGrid[i][j].addActionListener(efal);
            }
        }
        if(turn.isMyTurn())
        {
            turn.change();
        }
        
        for (int i = 0; i < 4; i++) 
        {
            creationNumbers[i].setText(Integer.toString(4-i)+"x");
            creationButtons[i].setAmount(4-i);
        }
        shipList=new ShipList();
        creationGridButton.setEnabled(true);
    }
    
    public void block()
    {
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                enemyGrid[i][j].removeActionListener(efal);
            }
        }
    }
    
    public String getLogin()
    {
        return login.getText();
    }
    //</editor-fold>
    
    class Window implements WindowListener
    {
        @Override
        public void windowClosing(WindowEvent we) 
        {
            ClientSender.send("EXIT:", out);
        }
        
        @Override
        public void windowOpened(WindowEvent we) {}

        @Override
        public void windowClosed(WindowEvent we) {}

        @Override
        public void windowIconified(WindowEvent we) {}

        @Override
        public void windowDeiconified(WindowEvent we) {}

        @Override
        public void windowActivated(WindowEvent we) {}

        @Override
        public void windowDeactivated(WindowEvent we) {}
    }
    
    class LoginClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if(login.getText().equals(""))
            {
                JOptionPane.showMessageDialog(mainPanel, "Prosze podac nazwe uzytkownika.");
            }
            else
            {
                ClientSender.send("SETNAME:"+login.getText(), out);
            }
        }
    }
    
    class NewGameClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if(playersList.getSelectedItem()!=null)
            {
                ClientSender.send("PLAYQUERY:"+playersList.getSelectedItem(), out);
                roomChatList.add("Zaproszenie wyslane. Oczekiwanie na odpowiedz...");
            }
        }
    }
    
    class PlayerGridClick implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent me) 
        {
            Field b=(Field)me.getSource();
            
            for (int i = 0; i < 4; i++)
            {
                if (creationButtons[i].isSelected() && creationButtons[i].getAmount()!=0)
                {
                    if(me.getButton()==MouseEvent.BUTTON1)
                    {
                        //<editor-fold defaultstate="collapsed" desc="LMB">
                        if (b.getCoordX() <= 10 - creationButtons[i].getShipSize()) 
                        {
                            boolean isOk = true;
                            for (int k = 0; k < creationButtons[i].getShipSize(); k++) 
                            {
                                if (playerGrid[b.getCoordX() + k][b.getCoordY()].isOccupied() == true) 
                                {
                                    isOk = false;
                                }
                            }

                            if (isOk == true) 
                            {
                                int j;
                                if(b.getCoordX()!=0)
                                {
                                    if(b.getCoordY()!=0)
                                    {
                                        playerGrid[b.getCoordX()-1][b.getCoordY()-1].setOccupied(true);
                                    }
                                    playerGrid[b.getCoordX()-1][b.getCoordY()].setOccupied(true);
                                    if(b.getCoordY()!=9)
                                    {
                                        playerGrid[b.getCoordX()-1][b.getCoordY()+1].setOccupied(true);
                                    }
                                }
                                playerGrid[b.getCoordX()][b.getCoordY()].setOccupied(true);
                                for (j = 0; j < creationButtons[i].getShipSize(); j++) 
                                {
                                    playerGrid[b.getCoordX() + j][b.getCoordY()].setIcon(new ImageIcon("1maszt.png"));
                                    if(b.getCoordY()!=0)
                                    {
                                        playerGrid[b.getCoordX() + j][b.getCoordY()-1].setOccupied(true);
                                    }
                                    playerGrid[b.getCoordX() + j][b.getCoordY()].setOccupied(true);
                                    if(b.getCoordY()!=9)
                                    {
                                        playerGrid[b.getCoordX() + j][b.getCoordY()+1].setOccupied(true);
                                    }
                                }
                                if(b.getCoordX()+creationButtons[i].getShipSize()!=10)
                                {
                                    if(b.getCoordY()!=0)
                                    {
                                        playerGrid[b.getCoordX() + j][b.getCoordY()-1].setOccupied(true);
                                    }
                                    playerGrid[b.getCoordX() + j][b.getCoordY()].setOccupied(true);
                                    if(b.getCoordY()!=9)
                                    {
                                        playerGrid[b.getCoordX() + j][b.getCoordY()+1].setOccupied(true);
                                    }
                                }

                                shipList.addShip(b.getCoordX(), b.getCoordY(), creationButtons[i].getShipSize(),"v");
                                creationButtons[i].decAmount();
                                creationNumbers[i].setText(creationButtons[i].getAmount()+"x");
                            }
                        }
                    //</editor-fold>
                    }
                    else if(me.getButton()==MouseEvent.BUTTON3)
                    {
                        //<editor-fold defaultstate="collapsed" desc="RMB">
                        if (b.getCoordY() <= 10 - creationButtons[i].getShipSize()) 
                        {
                            boolean isOk = true;
                            for (int k = 0; k < creationButtons[i].getShipSize(); k++) 
                            {
                                if (playerGrid[b.getCoordX()][b.getCoordY() + k].isOccupied()== true) 
                                {
                                    isOk = false;
                                }
                            }

                            if (isOk == true) 
                            {
                                int j;
                                if(b.getCoordY()!=0)
                                {
                                    if(b.getCoordX()!=0)
                                    {
                                        playerGrid[b.getCoordX()-1][b.getCoordY()-1].setOccupied(true);
                                    }
                                    playerGrid[b.getCoordX()][b.getCoordY()-1].setOccupied(true);
                                    if(b.getCoordX()!=9)
                                    {
                                        playerGrid[b.getCoordX()+1][b.getCoordY()-1].setOccupied(true);
                                    }
                                }
                                playerGrid[b.getCoordX()][b.getCoordY()].setOccupied(true);
                                for (j = 0; j < creationButtons[i].getShipSize(); j++) 
                                {
                                    playerGrid[b.getCoordX()][b.getCoordY() + j].setIcon(new ImageIcon("1maszt.png"));
                                    if(b.getCoordX()!=0)
                                    {
                                        playerGrid[b.getCoordX()-1][b.getCoordY() + j].setOccupied(true);
                                    }
                                    playerGrid[b.getCoordX()][b.getCoordY() + j].setOccupied(true);
                                    if(b.getCoordX()!=9)
                                    {
                                        playerGrid[b.getCoordX()+1][b.getCoordY() + j].setOccupied(true);
                                    }
                                }
                                if(b.getCoordY()+creationButtons[i].getShipSize()!=10)
                                {
                                    if(b.getCoordX()!=0)
                                    {
                                        playerGrid[b.getCoordX()-1][b.getCoordY() + j].setOccupied(true);
                                    }
                                    playerGrid[b.getCoordX()][b.getCoordY() + j].setOccupied(true);
                                    if(b.getCoordX()!=9)
                                    {
                                        playerGrid[b.getCoordX()+1][b.getCoordY() + j].setOccupied(true);
                                    }
                                }

                                shipList.addShip(b.getCoordX(), b.getCoordY(), creationButtons[i].getShipSize(),"h");
                                creationButtons[i].decAmount();
                                creationNumbers[i].setText(creationButtons[i].getAmount()+"x");
                            }
                        }
                    //</editor-fold>
                    }
                }
            }
        }
        @Override
        public void mousePressed(MouseEvent me) {}
        
        @Override
        public void mouseReleased(MouseEvent me) {}
        
        @Override
        public void mouseEntered(MouseEvent me) {}
        
        @Override
        public void mouseExited(MouseEvent me) {}
    }
    
    class CreationGridClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            boolean test=true;
            for (int i = 0; i < 4; i++) 
            {
                if(creationButtons[i].getAmount()!=0)
                {
                    test=false;
                }
            }
            if(test)
            {
                String s="SETGRID:";
                for (int i = 0; i < 10; i++)
                {
                    s=s.concat(shipList.getShipX(i)+":"+shipList.getShipY(i)+":"+shipList.getPosition(i)+":");
                    System.out.println(s);
                }
                roomChatList.add("Oczekiwanie na ustawienie statkow przez przeciwnika...");
                ClientSender.send(s, out);
                creationGridButton.setEnabled(false);
            }
            else
            {
                JOptionPane.showMessageDialog(mainPanel, "Prosze rozstawic wszystkie statki.");
            }
            
        }
    }

    class ServerSendClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if(!serverChatTextField.getText().equals(""))
            {
                String msg=login.getText()+" -> "+serverChatTextField.getText();
                serverChatList.add(msg);
                serverChatTextField.setText(null);
                ClientSender.send("SERVERMSG:"+msg, out);
                serverChatList.makeVisible(serverChatList.getItemCount()-1);
            }
        }
    }
    
    class ServerSendEnter implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent ke) 
        {
            int c = ke.getKeyCode();
            if(c == KeyEvent.VK_ENTER)
            {
                serverSendButton.doClick();
            }
        }
        
        @Override
        public void keyTyped(KeyEvent ke) {}
        
        @Override
        public void keyReleased(KeyEvent ke) {}
    }
    
    class RoomSendClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if(!roomChatTextField.getText().equals(""))
            {
                String msg=login.getText()+" -> "+roomChatTextField.getText();
                roomChatList.add(msg);
                roomChatTextField.setText(null);
                ClientSender.send("ROOMMSG:"+msg, out);
                roomChatList.makeVisible(roomChatList.getItemCount()-1);
            }
        }
    }
    
    class RoomSendEnter implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent ke) 
        {
            int c = ke.getKeyCode();
            if(c == KeyEvent.VK_ENTER)
            {
                roomSendButton.doClick();
            }
        }
        
        @Override
        public void keyTyped(KeyEvent ke) {}
        
        @Override
        public void keyReleased(KeyEvent ke) {}
    }
    
    class EnemyFieldClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            Field b=(Field)ae.getSource();
            ClientSender.send("SHOT:"+b.getCoordX()+":"+b.getCoordY(), out);
        }        
    }
    
    class DisconnectClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            ClientSender.send("DISCONNECT:", out);
            disconnect();
        }
    }
    
    class CreditsClick implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
                JFrame credits=new JFrame();
                JLabel author=new JLabel("AUTOR");
                author.setFont(new Font("SansSerif", Font.BOLD, 15));
                JLabel authorName=new JLabel("Łukasz Marchel");
                JLabel graphics=new JLabel("GRAFIKA");
                graphics.setFont(new Font("SansSerif", Font.BOLD, 15));
                JLabel graphicsName1=new JLabel("Łukasz Marchel");
                JLabel graphicsName2=new JLabel("Arkadiusz Kulma");
                JLabel code=new JLabel("KOD PROGRAMU");
                code.setFont(new Font("SansSerif", Font.BOLD, 15));
                JLabel codeName=new JLabel("Łukasz Marchel");
                JLabel support=new JLabel("WSPARCIE DUCHOWE");
                support.setFont(new Font("SansSerif", Font.BOLD, 15));
                JLabel supportName1=new JLabel("Arkadiusz Kulma");
                JLabel supportName2=new JLabel("Jakub Mańkowski");
                JLabel supportName3=new JLabel("Tomasz Kucharczyk");
                
                credits.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridx=0;
                c.gridy=0;
                c.insets=new Insets(0,0,5,0);
                credits.add(author,c);
                c.gridy=1;
                c.insets=new Insets(0,0,5,0);
                credits.add(authorName,c);
                c.gridy=2;
                c.insets=new Insets(10,0,5,0);
                credits.add(graphics,c);
                c.gridy=3;
                c.insets=new Insets(0,0,5,0);
                credits.add(graphicsName1,c);
                c.gridy=4;
                credits.add(graphicsName2,c);
                c.gridy=5;
                c.insets=new Insets(10,0,5,0);
                credits.add(code,c);
                c.gridy=6;
                c.insets=new Insets(0,0,5,0);
                credits.add(codeName,c);
                c.gridy=7;
                c.insets=new Insets(10,0,5,0);
                credits.add(support,c);
                c.gridy=8;
                c.insets=new Insets(0,0,5,0);
                credits.add(supportName1,c);
                c.gridy=9;
                credits.add(supportName2,c);
                c.gridy=10;
                credits.add(supportName3,c);
                
                credits.setVisible(true);
                credits.setSize(250,340);
                credits.setLocationRelativeTo(mainPanel);
                credits.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        
    }
}