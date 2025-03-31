package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PanelID extends JPanel implements ActionListener, MouseListener
{
    private JTextField txtNom;
    private JTextField txtPrenom;
    private int numID;

    JPanel panelSexe;
    private JRadioButton homme;
    private JRadioButton femme;
    private JRadioButton autre;
    private ButtonGroup groupe;

    JPanel panelBouton;
    private JButton btnValider;
    private JButton btnQuitter;
    private JButton btnConnexion;


    private FrameID frame;


    public PanelID(FrameID frame)
    {
        this.setLayout(new GridLayout(4,1));
        this.setBackground(new Color(30, 30, 30));

        this.frame = frame;
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        JLabel lblNom    = new JLabel("nom :"    , JLabel.RIGHT);
        JLabel lblPrenom = new JLabel("prénom :" , JLabel.RIGHT);
        JLabel lblSexe   = new JLabel("sexe :"   , JLabel.RIGHT);

        this.styleLabel(lblNom);
        this.styleLabel(lblPrenom);
        this.styleLabel(lblSexe);

        this.txtNom    = new JTextField(10);
        this.txtPrenom = new JTextField(10);

        this.styleTextField(txtNom);
        this.styleTextField(txtPrenom);

        this.panelSexe = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panelSexe.setBackground(new Color(40, 40, 40));
        this.homme     = new JRadioButton("homme");
        this.femme     = new JRadioButton("femme");
        this.autre     = new JRadioButton("autre");
        this.groupe    = new ButtonGroup();

        this.styleRadioButton(homme);
        this.styleRadioButton(femme);
        this.styleRadioButton(autre);

        panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBouton.setBackground(new Color(30, 30, 30));
        this.btnValider = new JButton("Valider");
        this.btnQuitter = new JButton("Quitter");
        this.btnConnexion = new JButton("Connexion");
        
        
        this.styleButton(btnConnexion);
        this.styleButton(btnValider);
        this.styleButton(btnQuitter);



        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        panelBouton.add(btnQuitter);
        panelBouton.add(btnValider);
        panelBouton.add(btnConnexion);
        

        this.groupe.add(homme);
        this.groupe.add(femme);
        this.groupe.add(autre);

        panelSexe.add(homme);
        panelSexe.add(femme);
        panelSexe.add(autre);

        this.add(lblNom);
        this.add(txtNom);
        this.add(lblPrenom);
        this.add(txtPrenom);
        this.add(lblSexe);
        this.add(panelSexe);
        this.add(new JLabel());
        this.add(panelBouton);


        /*---------------------------*/
        /* Activation des composants */
        /*---------------------------*/
        this.btnValider.addActionListener(this);
        this.btnQuitter.addActionListener(this);
        this.btnConnexion.addActionListener(this);

        this.btnConnexion.addMouseListener(this);
        this.btnValider.addMouseListener(this);
        this.btnQuitter.addMouseListener(this);


    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnQuitter)
        {
            System.exit(0);
        }
        else
        {
            if(e.getSource() == this.btnConnexion)
            {
                this.frame.frameConnexion();
                this.frame.setVisible(false);
            }
            else
            {
                if(! this.txtNom.getText().equals("") && ! this.txtPrenom.getText().equals("") && this.groupe.getSelection() != null)
                {
                    if(e.getSource() == this.btnValider)
                    {
                        this.numID = (int)(Math.random() * 999999);
                        System.out.println("ID : " + this.numID);
                        System.out.println("Nom : " + this.txtNom.getText());
                        System.out.println("Prénom : " + this.txtPrenom.getText());
        
                        if(this.groupe.getSelection() == this.homme)
                        {
                            System.out.println("Sexe : homme");
                        }
                        else 
                        {
                            if(this.groupe.getSelection() == this.femme)
                            {
                                System.out.println("Sexe : femme");
                            }
                            else
                            {
                                System.out.println("Sexe : autre");
                            }
                        }
        
                        this.frame.setIdentification(this.numID, this.txtNom.getText(), this.txtPrenom.getText());
                    }
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
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

        if(e.getComponent() == this.btnQuitter)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.btnQuitter.setBackground(Color.RED);
        }

        if(e.getComponent() == this.btnValider)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.btnValider.setBackground(Color.GREEN);
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if(e.getComponent() == this.btnConnexion)
        {
            this.setCursor(Cursor.getDefaultCursor());
            this.btnConnexion.setBackground(new Color(30, 30, 30));
        }

        if(e.getComponent() == this.btnQuitter)
        {
            this.setCursor(Cursor.getDefaultCursor());
            this.btnQuitter.setBackground(new Color(30, 30, 30));
        }

        if(e.getComponent() == this.btnValider)
        {
            this.setCursor(Cursor.getDefaultCursor());
            this.btnValider.setBackground(new Color(30, 30, 30));
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

    private void styleRadioButton(JRadioButton radio)
    {
        radio.setBackground(new Color(50, 50, 50));
        radio.setForeground(Color.YELLOW);
        radio.setFocusPainted(false);
        radio.setFont(new Font("Arial", Font.BOLD, 12));
        radio.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
        radio.setOpaque(true);
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