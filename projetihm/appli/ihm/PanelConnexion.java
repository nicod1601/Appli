package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelConnexion extends JPanel implements ActionListener, MouseListener
{
    
    private JTextField txtNom;
    private JTextField txtPrenom;

    JPanel panelBouton;
    private JButton btnConnexion;
    private JButton btnCreer;

    JPanel panelNom;
    JPanel panelPrenom;
    
    private FrameConnexion frame;

    public PanelConnexion(FrameConnexion frame)
    {
        this.setLayout(new GridLayout(3,1));
        this.setBackground(new Color(30, 30, 30));

        this.frame = frame;
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        JLabel lblNom    = new JLabel("nom :"    , JLabel.CENTER);
        JLabel lblPrenom = new JLabel("prénom :" , JLabel.CENTER);

        JPanel panelNom    = new JPanel();
        panelNom.setBackground(new Color(30, 30, 30));
        JPanel panelPrenom = new JPanel();
        panelPrenom.setBackground(new Color(30, 30, 30));

        this.txtNom = new JTextField(10);
        this.txtPrenom = new JTextField(10);

        panelBouton = new JPanel();
        panelBouton.setBackground(new Color(30, 30, 30));
        this.btnConnexion = new JButton("Connexion");
        this.btnCreer = new JButton("Créer un compte");

        this.styleButton(this.btnConnexion);
        this.styleButton(this.btnCreer);
        this.styleTextField(this.txtNom);
        this.styleTextField(this.txtPrenom);
        this.styleLabel(lblNom);
        this.styleLabel(lblPrenom);
        

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        panelBouton.add(this.btnCreer);
        panelBouton.add(this.btnConnexion);


        panelNom.add(this.txtNom);

        panelPrenom.add(this.txtPrenom);

        this.add(lblNom);
        this.add(panelNom);
        this.add(lblPrenom);
        this.add(panelPrenom);
        this.add(new JLabel());
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
        if(e.getSource() == this.btnCreer)
        {
            this.frame.creerCompte();
        }


        if(! this.txtNom.getText().equals("") && ! this.txtPrenom.getText().equals(""))
        {
            if (e.getSource() == this.btnConnexion)
            {
                if(this.frame.connecter(this.txtNom.getText(), this.txtPrenom.getText()))
                {
                    System.out.println("Connexion effectuée");
                    this.frame.frameAppli();
                    this.frame.setVisible(false);
                }
                else
                {
                    System.out.println("Connexion impossible");
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
