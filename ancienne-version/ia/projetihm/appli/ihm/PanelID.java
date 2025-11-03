package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


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
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(30, 30, 30));
        this.setBorder(new EmptyBorder(30, 50, 30, 50));

        this.frame = frame;
        
        // Panel central pour le formulaire
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(new Color(30, 30, 30));
        
        // Titre
        JLabel titre = new JLabel("INSCRIPTION");
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setForeground(Color.YELLOW);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(titre);
        panelCentral.add(Box.createVerticalStrut(30));

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        JLabel lblNom    = new JLabel("Nom");
        JLabel lblPrenom = new JLabel("Prénom");
        JLabel lblPseudo = new JLabel("Pseudo");
        JLabel lblSexe   = new JLabel("Sexe");
        JLabel lblMDP    = new JLabel("Mot de passe");

        this.styleLabel(lblNom);
        this.styleLabel(lblPrenom);
        this.styleLabel(lblSexe);
        this.styleLabel(lblMDP);
        this.styleLabel(lblPseudo);

        this.txtNom    = new JTextField(20);
        this.txtPrenom = new JTextField(20);
        this.txtPseudo = new JTextField(20);
        this.txtMDP    = new JPasswordField(20);

        this.styleTextField(txtNom);
        this.styleTextField(txtPrenom);
        this.styleTextField(txtPseudo);
        this.styleTextField(txtMDP);

        // Panels pour chaque champ avec espacement vertical
        panelNom = createFieldPanel(lblNom, txtNom);
        panelPrenom = createFieldPanel(lblPrenom, txtPrenom);
        panelPseudo = createFieldPanel(lblPseudo, txtPseudo);
        panelMDP = createFieldPanel(lblMDP, txtMDP);

        // Panel sexe avec style amélioré
        this.panelSexe = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        this.panelSexe.setBackground(new Color(30, 30, 30));
        this.panelSexe.setMaximumSize(new Dimension(600, 60));
        
        JPanel sexeContainer = new JPanel();
        sexeContainer.setLayout(new BoxLayout(sexeContainer, BoxLayout.Y_AXIS));
        sexeContainer.setBackground(new Color(30, 30, 30));
        
        lblSexe.setAlignmentX(Component.LEFT_ALIGNMENT);
        sexeContainer.add(lblSexe);
        sexeContainer.add(Box.createVerticalStrut(8));
        
        this.homme     = new JRadioButton("Homme");
        this.femme     = new JRadioButton("Femme");
        this.autre     = new JRadioButton("Autre");
        this.groupe    = new ButtonGroup();

        this.styleRadioButton(homme);
        this.styleRadioButton(femme);
        this.styleRadioButton(autre);

        this.groupe.add(homme);
        this.groupe.add(femme);
        this.groupe.add(autre);

        panelSexe.add(homme);
        panelSexe.add(femme);
        panelSexe.add(autre);
        panelSexe.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        sexeContainer.add(panelSexe);

        // Boutons avec style amélioré
        panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBouton.setBackground(new Color(30, 30, 30));
        panelBouton.setMaximumSize(new Dimension(800, 60));
        
        this.btnValider = new JButton("Enregistrer");
        this.btnConnexion = new JButton("Page Connexion");
        this.btnQuitter = new JButton("Quitter");
        
        this.styleButton(btnValider);
        this.styleButton(btnConnexion);
        this.styleButton(btnQuitter);
        
        btnValider.setPreferredSize(new Dimension(150, 40));
        btnConnexion.setPreferredSize(new Dimension(150, 40));
        btnQuitter.setPreferredSize(new Dimension(120, 40));

        panelBouton.add(btnValider);
        panelBouton.add(btnConnexion);
        panelBouton.add(btnQuitter);

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        panelCentral.add(panelNom);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(panelPrenom);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(panelPseudo);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(panelMDP);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(sexeContainer);
        panelCentral.add(Box.createVerticalStrut(30));
        panelCentral.add(panelBouton);
        
        this.add(panelCentral, BorderLayout.CENTER);

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

    private JPanel createFieldPanel(JLabel label, JTextField field)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));
        panel.setMaximumSize(new Dimension(600, 70));
        
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(600, 35));
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(8));
        panel.add(field);
        
        return panel;
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
        
                        if(this.homme.isSelected())
                        {
                            System.out.println("Sexe : homme");
                        }
                        else if(this.femme.isSelected())
                        {
                            System.out.println("Sexe : femme");
                        }
                        else
                        {
                            System.out.println("Sexe : autre");
                        }
        
                        this.frame.setIdentification(this.numID, this.txtNom.getText(), this.txtPrenom.getText(), password, this.txtPseudo.getText());
                        this.frame.frameAppli();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires", "Champs manquants", JOptionPane.WARNING_MESSAGE);
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
            this.btnConnexion.setBackground(new Color(0, 180, 0));
        }

        if(e.getComponent() == this.btnQuitter)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.btnQuitter.setBackground(new Color(200, 0, 0));
        }

        if(e.getComponent() == this.btnValider)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.btnValider.setBackground(new Color(0, 180, 0));
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if(e.getComponent() == this.btnConnexion)
        {
            this.setCursor(Cursor.getDefaultCursor());
            this.btnConnexion.setBackground(new Color(70, 70, 70));
        }

        if(e.getComponent() == this.btnQuitter)
        {
            this.setCursor(Cursor.getDefaultCursor());
            this.btnQuitter.setBackground(new Color(70, 70, 70));
        }

        if(e.getComponent() == this.btnValider)
        {
            this.setCursor(Cursor.getDefaultCursor());
            this.btnValider.setBackground(new Color(70, 70, 70));
        }
    }

    private void styleLabel(JLabel label) 
    {
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void styleTextField(JTextField field)
    {
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.YELLOW);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void styleRadioButton(JRadioButton radio)
    {
        radio.setBackground(new Color(50, 50, 50));
        radio.setForeground(Color.YELLOW);
        radio.setFocusPainted(false);
        radio.setFont(new Font("Arial", Font.BOLD, 13));
        radio.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        radio.setOpaque(true);
    }

    private void styleButton(JButton button)
    {
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.YELLOW);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setEcritureBouton(Color c)
    {
        this.btnConnexion.setForeground(c);
        this.btnQuitter.setForeground(c);
        this.btnValider.setForeground(c);
    }

    public void setFondBouton(Color c)
    {
       this.btnConnexion.setBorder(BorderFactory.createLineBorder(c, 2));
       this.btnQuitter.setBorder(BorderFactory.createLineBorder(c, 2));
       this.btnValider.setBorder(BorderFactory.createLineBorder(c, 2));
    }
}