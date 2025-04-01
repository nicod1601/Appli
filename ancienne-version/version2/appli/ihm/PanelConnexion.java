package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelConnexion extends JPanel implements ActionListener, MouseListener
{
    private JTextField txtPseudo;
    private JPasswordField txtMDP;
    private JLabel     lblMessage;

    JPanel panelBouton;
    private JButton btnConnexion;
    private JButton btnCreer;

    
    private FrameConnexion frame;

    public PanelConnexion(FrameConnexion frame)
    {
        this.setLayout(new GridLayout(3,2));
        this.setBackground(new Color(30, 30, 30));

        this.frame = frame;
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        JLabel lblPseudo    = new JLabel("pseudo :"    , JLabel.CENTER);
        JLabel lblMDP       = new JLabel("mot de passe :" , JLabel.CENTER);

        this.txtPseudo = new JTextField(10);
        this.txtMDP = new JPasswordField(10);

        panelBouton = new JPanel();
        panelBouton.setBackground(new Color(30, 30, 30));
        this.btnConnexion = new JButton("Connexion");
        this.btnCreer = new JButton("Créer un compte");

        this.lblMessage = new JLabel("");

        this.styleButton(this.btnConnexion);
        this.styleButton(this.btnCreer);
        this.styleTextField(this.txtPseudo);
        this.styleLabel(lblPseudo);
        this.styleLabel(lblMDP);
        this.styleTextField(this.txtMDP);
        

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        panelBouton.add(this.btnCreer);
        panelBouton.add(this.btnConnexion);

        this.add(lblPseudo);
        this.add(this.txtPseudo);
        this.add(lblMDP);
        this.add(this.txtMDP);
        this.add(this.lblMessage);
        this.add(panelBouton);


        /*---------------------------*/
        /* Activation des composants */
        /*---------------------------*/
        this.btnConnexion.addActionListener(this);
        this.btnCreer.addActionListener(this);

        this.btnConnexion.addMouseListener(this);
        this.btnCreer.addMouseListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {
        char[] mdp = this.txtMDP.getPassword();
        String mdpString = new String(mdp);

        if(e.getSource() == this.btnCreer)
        {
            this.frame.creerCompte();
        }


        if(! this.txtPseudo.getText().equals("") && ! mdpString.equals(""))
        {
            if (e.getSource() == this.btnConnexion)
            {
                if(this.frame.connecter(this.txtPseudo.getText(), mdpString))
                {
                    System.out.println("Connexion effectuée");
                    this.lblMessage.setText("");
                    this.txtPseudo.setText("");
                    this.txtMDP.setText("");
                    this.frame.frameAppli();
                    this.frame.setVisible(false);

                    this.frame.setInformation(this.txtPseudo.getText(), mdpString);
                    System.out.println("pseudo : " + this.txtPseudo.getText() + " mdp : " + mdpString);
                }
                else
                {
                    System.out.println("Connexion impossible");
                    this.lblMessage.setText("Connexion impossible");
                    this.lblMessage.setForeground(Color.RED);

                    System.out.println("pseudo : " + this.txtPseudo.getText() + " mdp : " + mdpString);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}


    public void mouseEntered(MouseEvent e)
    {
        if(e.getComponent() == this.btnConnexion)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.btnConnexion.setBackground(Color.GREEN);
        }

        if(e.getComponent() == this.btnCreer)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.btnCreer.setBackground(Color.GREEN);
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if(e.getComponent() == this.btnConnexion || e.getComponent() == this.btnCreer)
        {
            this.setCursor(Cursor.getDefaultCursor());
            this.btnConnexion.setBackground(new Color(30, 30, 30));
            this.btnCreer.setBackground(new Color(30, 30, 30));
        }
    }

    private void styleLabel(JLabel label) 
    {
        label.setForeground(Color.YELLOW); // Jaune militaire
        label.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void styleTextField(JTextField field)
    {
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.YELLOW);
        field.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
    }

    private void styleButton(JButton button)
    {
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.YELLOW);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        button.setFocusPainted(false);
    }
}
