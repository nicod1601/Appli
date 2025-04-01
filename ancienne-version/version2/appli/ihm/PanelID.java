package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PanelID extends JPanel implements ActionListener, MouseListener
{
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtPseudo;
    private JPasswordField txtMDP;
    private int numID;

    JPanel panelNom;
    JPanel panelPrenom;
    JPanel panelPseudo;
    JPanel panelMDP;

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
        this.setLayout(new GridLayout(6,1));
        this.setBackground(new Color(30, 30, 30));

        this.frame = frame;
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        JLabel lblNom    = new JLabel("nom :"    , JLabel.RIGHT);
        JLabel lblPrenom = new JLabel("prénom :" , JLabel.RIGHT);
        JLabel lblPseudo = new JLabel("pseudo :"    , JLabel.RIGHT);
        JLabel lblSexe   = new JLabel("sexe :"   , JLabel.RIGHT);
        JLabel lblMDP    = new JLabel("mot de passe :" , JLabel.RIGHT);

        this.styleLabel(lblNom);
        this.styleLabel(lblPrenom);
        this.styleLabel(lblSexe);
        this.styleLabel(lblMDP);
        this.styleLabel(lblPseudo);

        this.txtNom    = new JTextField(10);
        this.txtPrenom = new JTextField(10);
        this.txtPseudo = new JTextField(10);
        this.txtMDP    = new JPasswordField(10);

        this.styleTextField(txtNom);
        this.styleTextField(txtPrenom);
        this.styleTextField(txtPseudo);
        this.styleTextField(txtMDP);

        this.panelSexe = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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

        panelNom = new JPanel(new GridLayout());
        panelNom.setBackground(new Color(40, 40, 40));
        panelPrenom = new JPanel(new GridLayout());
        panelPrenom.setBackground(new Color(40, 40, 40));
        panelPseudo = new JPanel(new GridLayout());
        panelPseudo.setBackground(new Color(40, 40, 40));
        panelMDP = new JPanel(new GridLayout());
        panelMDP.setBackground(new Color(40, 40, 40));



        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        panelNom.add(lblNom);
        panelNom.add(txtNom);

        panelPrenom.add(lblPrenom);
        panelPrenom.add(txtPrenom);

        panelPseudo.add(lblPseudo);
        panelPseudo.add(txtPseudo);

        panelMDP.add(lblMDP);
        panelMDP.add(txtMDP);

        this.groupe.add(homme);
        this.groupe.add(femme);
        this.groupe.add(autre);

        panelSexe.add(homme);
        panelSexe.add(femme);
        panelSexe.add(autre);

        panelBouton.add(btnQuitter);
        panelBouton.add(btnValider);
        panelBouton.add(btnConnexion);
        
        this.add(panelNom);
        this.add(panelPrenom);
        this.add(panelPseudo);
        this.add(panelMDP);
        this.add(panelSexe);
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
                        char[] passwordChars = this.txtMDP.getPassword();
                        String password = new String(passwordChars);
                        

                        this.numID = (int)(Math.random() * 999999);
                        System.out.println("ID : " + this.numID);
                        System.out.println("Nom : " + this.txtNom.getText());
                        System.out.println("Prénom : " + this.txtPrenom.getText());
                        System.out.println("Pseudo : " + this.txtPseudo.getText());
                        System.out.println("MDP : " + password);
        
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
        
                        this.frame.setIdentification(this.numID, this.txtNom.getText(), this.txtPrenom.getText(),password, this.txtPseudo.getText());
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