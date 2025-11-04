package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelAccueil extends JPanel implements ActionListener, MouseListener
{
    private JButton[] tabButton;
    private String[] tabImages;
    private String[] tabNom;
    private String[] tabDescriptions;

    private FrameAppli frame;

    public PanelAccueil(FrameAppli frame) {
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(30, 30, 30));
        this.setBorder(new EmptyBorder(30, 40, 30, 40));

        this.frame = frame;
        
        /*--------------------------*/
        /* Création du titre        */
        /*--------------------------*/
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        panelTop.setBackground(new Color(30, 30, 30));
        
        JLabel titre = new JLabel("HELLDIVERS II", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 36));
        titre.setForeground(Color.YELLOW);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel sousTitre = new JLabel("Centre de commandement", SwingConstants.CENTER);
        sousTitre.setFont(new Font("Arial", Font.ITALIC, 16));
        sousTitre.setForeground(new Color(200, 200, 0));
        sousTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelTop.add(titre);
        panelTop.add(Box.createVerticalStrut(5));
        panelTop.add(sousTitre);
        panelTop.add(Box.createVerticalStrut(20));
        
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.tabImages = new String[]{
            "./img/competence.png", 
            "./img/stratageme.png", 
            "./img/logoPasse.png", 
            "./img/stratageme.png"
        };
        
        this.tabNom = new String[]{
            "Compétences", 
            "Stratagèmes", 
            "Passe de Combat", 
            "Arsenal"
        };
        
        this.tabDescriptions = new String[]{
            "Gérez vos compétences et améliorations",
            "Consultez les stratagèmes disponibles",
            "Progression et récompenses",
            "Équipement et armement"
        };

        // Panel central avec grille de boutons
        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 25, 25));
        gridPanel.setBackground(new Color(30, 30, 30));
        gridPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        
        this.tabButton = new JButton[this.tabImages.length];
        
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            this.tabButton[cpt] = createMenuButton(
                this.tabImages[cpt], 
                this.tabNom[cpt],
                this.tabDescriptions[cpt]
            );
            gridPanel.add(this.tabButton[cpt]);
        }

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(panelTop, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);

        /*--------------------------- */
        /*   Action des composants    */
        /*--------------------------- */
        for (JButton button : this.tabButton) 
        {
            button.addActionListener(this);
            button.addMouseListener(this);
        }
    }

    /**
     * Crée un bouton de menu stylisé avec image, nom et description
     */
    private JButton createMenuButton(String imagePath, String nom, String description) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout(10, 10));
        button.setBackground(new Color(45, 45, 45));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 3),
            new EmptyBorder(20, 20, 20, 20)
        ));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Panel pour l'image
        JPanel panelImage = new JPanel(new BorderLayout());
        panelImage.setOpaque(false);
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            
            JLabel lblImage = new JLabel(icon, SwingConstants.CENTER);
            panelImage.add(lblImage, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel lblError = new JLabel("⚠", SwingConstants.CENTER);
            lblError.setFont(new Font("Arial", Font.BOLD, 40));
            lblError.setForeground(Color.YELLOW);
            panelImage.add(lblError, BorderLayout.CENTER);
        }
        
        // Panel pour le texte
        JPanel panelTexte = new JPanel();
        panelTexte.setLayout(new BoxLayout(panelTexte, BoxLayout.Y_AXIS));
        panelTexte.setOpaque(false);
        panelTexte.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JLabel lblNom = new JLabel(nom, SwingConstants.CENTER);
        lblNom.setFont(new Font("Arial", Font.BOLD, 18));
        lblNom.setForeground(Color.YELLOW);
        lblNom.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblDescription = new JLabel("<html><center>" + description + "</center></html>", SwingConstants.CENTER);
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDescription.setForeground(new Color(200, 200, 200));
        lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelTexte.add(lblNom);
        panelTexte.add(Box.createVerticalStrut(5));
        panelTexte.add(lblDescription);
        
        // Assemblage du bouton
        button.add(panelImage, BorderLayout.CENTER);
        button.add(panelTexte, BorderLayout.SOUTH);
        
        return button;
    }

    public void actionPerformed(ActionEvent e)
    {
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            if (e.getSource() == this.tabButton[cpt])
            {
                System.out.println("Navigation vers : " + tabNom[cpt]);

                if(this.tabNom[cpt].equals("Stratagèmes"))
                {
                    this.frame.panelStrategie(true);
                }
                // Vous pouvez ajouter d'autres conditions ici pour les autres boutons
            }
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e)
    {
        for (int cpt = 0; cpt < this.tabButton.length; cpt++)
        {
            if (e.getSource() == this.tabButton[cpt])
            {
                // Effet hover : fond plus clair et bordure plus épaisse
                this.tabButton[cpt].setBackground(new Color(60, 60, 60));
                this.tabButton[cpt].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 255, 100), 4),
                    new EmptyBorder(20, 20, 20, 20)
                ));
            }
        }
    }

    public void mouseExited(MouseEvent e)
    {
        for (int cpt = 0; cpt < this.tabButton.length; cpt++) 
        {
            if (e.getSource() == this.tabButton[cpt]) 
            {
                // Retour à l'état normal
                this.tabButton[cpt].setBackground(new Color(45, 45, 45));
                this.tabButton[cpt].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.YELLOW, 3),
                    new EmptyBorder(20, 20, 20, 20)
                ));
            }
        }
    }
}