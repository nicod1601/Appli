package appli.ihm;

import appli.Controleur;
import appli.metier.Fond;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class PanelOptionParametre extends JPanel implements ActionListener, ItemListener, MouseListener
{
    private JTabbedPane tabbedPane;
    private JPanel panelChangerFond;
    private JPanel panelSecurite;
    private JPanel panelOptionFond;

    private JButton btnQuitter;

    /* Partie Compte */
    private JPanel panelCompte;
    private JLabel lblProfil;

    private JTextField txtNom;
    private JTextField txtMDP;

    private JPanel panelBoutonCompte;
    private JButton btnImporter;

    private JRadioButton[] tabRadio;
    private ButtonGroup groupe;

    /* Partie Fond */
    private Fond fond;
    private JButton[][] tabButtonFond;

    private FrameAppli frame;
    private Controleur ctrl;

    public PanelOptionParametre(FrameAppli frame, Controleur ctrl)
    {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(new Color(30, 30, 30));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        this.frame = frame;
        this.ctrl  = ctrl;

        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setBackground(new Color(40, 40, 40));
        this.tabbedPane.setForeground(Color.YELLOW);
        this.tabbedPane.setFont(new Font("Arial", Font.BOLD, 13));

        // Bouton Fermer en haut
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTop.setBackground(new Color(30, 30, 30));
        this.btnQuitter = new JButton("✕ Fermer les Paramètres");
        this.styleButton(this.btnQuitter);
        panelTop.add(this.btnQuitter);

        /* ============= PANEL COMPTE ============= */
        this.panelCompte = new JPanel();
        this.panelCompte.setLayout(new BorderLayout(20, 20));
        this.panelCompte.setBackground(new Color(40, 40, 40));
        this.panelCompte.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Panel central pour le compte
        JPanel panelCentreCompte = new JPanel();
        panelCentreCompte.setLayout(new BoxLayout(panelCentreCompte, BoxLayout.Y_AXIS));
        panelCentreCompte.setBackground(new Color(40, 40, 40));

        // Photo de profil
        JPanel panelPhotoContainer = new JPanel();
        panelPhotoContainer.setLayout(new BoxLayout(panelPhotoContainer, BoxLayout.Y_AXIS));
        panelPhotoContainer.setBackground(new Color(40, 40, 40));
        panelPhotoContainer.setMaximumSize(new Dimension(800, 250));
        
        JLabel lblTitrePhoto = new JLabel("Photo de profil");
        lblTitrePhoto.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitrePhoto.setForeground(Color.YELLOW);
        lblTitrePhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.lblProfil = new JLabel();
        this.lblProfil.setPreferredSize(new Dimension(150, 150));
        this.lblProfil.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 3),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        this.lblProfil.setHorizontalAlignment(JLabel.CENTER);
        this.lblProfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.lblProfil.setBackground(new Color(50, 50, 50));
        this.lblProfil.setOpaque(true);
        
        this.btnImporter = new JButton("📁 Importer une photo");
        this.styleButton(this.btnImporter);
        this.btnImporter.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.btnImporter.setPreferredSize(new Dimension(200, 35));

        panelPhotoContainer.add(lblTitrePhoto);
        panelPhotoContainer.add(Box.createVerticalStrut(15));
        panelPhotoContainer.add(this.lblProfil);
        panelPhotoContainer.add(Box.createVerticalStrut(15));
        panelPhotoContainer.add(this.btnImporter);

        // Informations du compte
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS));
        panelInfos.setBackground(new Color(40, 40, 40));
        panelInfos.setMaximumSize(new Dimension(600, 200));

        JLabel lblTitreProfil = new JLabel("Nom de profil");
        JLabel lblTitreMDP = new JLabel("Mot de passe");
        this.styleLabel(lblTitreProfil);
        this.styleLabel(lblTitreMDP);

        this.txtNom = new JTextField(25);
        this.txtMDP = new JTextField(25);
        this.styleTextField(this.txtNom);
        this.styleTextField(this.txtMDP);

        JPanel panelNomField = createFieldPanel(lblTitreProfil, this.txtNom);
        JPanel panelMDPField = createFieldPanel(lblTitreMDP, this.txtMDP);

        panelInfos.add(panelNomField);
        panelInfos.add(Box.createVerticalStrut(15));
        panelInfos.add(panelMDPField);

        panelCentreCompte.add(panelPhotoContainer);
        panelCentreCompte.add(Box.createVerticalStrut(30));
        panelCentreCompte.add(panelInfos);

        this.panelCompte.add(panelCentreCompte, BorderLayout.CENTER);

        /* ============= PANEL CHANGER FOND ============= */
        this.panelChangerFond = new JPanel(new BorderLayout(15, 15));
        this.panelChangerFond.setBackground(new Color(40, 40, 40));
        this.panelChangerFond.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Titre pour le panel fond
        JLabel lblTitreFond = new JLabel("Sélectionnez une couleur");
        lblTitreFond.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitreFond.setForeground(Color.YELLOW);
        lblTitreFond.setHorizontalAlignment(JLabel.CENTER);
        this.panelChangerFond.add(lblTitreFond, BorderLayout.NORTH);

        // Grille de couleurs
        JPanel panelGrilleCouleurs = new JPanel();
        this.fond = new Fond();
        panelGrilleCouleurs.setLayout(new GridLayout(this.fond.getLigne(), this.fond.getColonne(), 8, 8));
        panelGrilleCouleurs.setBackground(new Color(40, 40, 40));
        this.tabButtonFond = new JButton[this.fond.getLigne()][this.fond.getColonne()];

        for(int cpt = 0; cpt < this.fond.getLigne(); cpt++)
        {
            for(int cpt2 = 0; cpt2 < this.fond.getColonne(); cpt2++)
            {
                this.tabButtonFond[cpt][cpt2] = new JButton();
                this.tabButtonFond[cpt][cpt2].setBackground(this.fond.getCouleur(cpt, cpt2));
                this.tabButtonFond[cpt][cpt2].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                this.tabButtonFond[cpt][cpt2].setPreferredSize(new Dimension(60, 60));
                this.tabButtonFond[cpt][cpt2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                this.tabButtonFond[cpt][cpt2].setFocusPainted(false);
                panelGrilleCouleurs.add(this.tabButtonFond[cpt][cpt2]);
            }
        }

        this.panelChangerFond.add(panelGrilleCouleurs, BorderLayout.CENTER);

        // Options de fond (Radio buttons)
        this.panelOptionFond = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        this.panelOptionFond.setBackground(new Color(30, 30, 30));
        this.panelOptionFond.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 2),
            new EmptyBorder(10, 20, 10, 20)
        ));

        JLabel lblAppliquerA = new JLabel("Appliquer à : ");
        lblAppliquerA.setFont(new Font("Arial", Font.BOLD, 14));
        lblAppliquerA.setForeground(Color.YELLOW);

        this.tabRadio = new JRadioButton[3];
        this.tabRadio[0] = new JRadioButton("Fond d'écran");
        this.tabRadio[1] = new JRadioButton("Bordure des boutons");
        this.tabRadio[2] = new JRadioButton("Texte des boutons");
        
        this.groupe = new ButtonGroup();
        for(int i = 0; i < this.tabRadio.length; i++)
        {
            this.styleRadioButton(this.tabRadio[i]);
            this.groupe.add(this.tabRadio[i]);
        }

        this.panelOptionFond.add(lblAppliquerA);
        this.panelOptionFond.add(this.tabRadio[0]);
        this.panelOptionFond.add(this.tabRadio[1]);
        this.panelOptionFond.add(this.tabRadio[2]);
        this.panelOptionFond.setVisible(false);

        /* ============= PANEL SÉCURITÉ ============= */
        this.panelSecurite = new JPanel();
        this.panelSecurite.setLayout(new BorderLayout());
        this.panelSecurite.setBackground(new Color(40, 40, 40));
        this.panelSecurite.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel lblSecurite = new JLabel("Section Sécurité - À venir", JLabel.CENTER);
        lblSecurite.setFont(new Font("Arial", Font.BOLD, 18));
        lblSecurite.setForeground(Color.YELLOW);
        this.panelSecurite.add(lblSecurite, BorderLayout.CENTER);

        /* Ajout des onglets avec icônes */
        this.tabbedPane.addTab("👤 Compte", this.panelCompte);
        this.tabbedPane.addTab("🎨 Apparence", this.panelChangerFond);
        this.tabbedPane.addTab("🔒 Sécurité", this.panelSecurite);

        // Listener pour afficher/masquer les options de fond
        this.tabbedPane.addChangeListener(e -> {
            this.panelOptionFond.setVisible(
                this.tabbedPane.getSelectedComponent() == this.panelChangerFond
            );
        });

        /*--------------------------*/
        /* Position des composants  */
        /*--------------------------*/
        this.add(panelTop, BorderLayout.NORTH);
        this.add(this.tabbedPane, BorderLayout.CENTER);
        this.add(this.panelOptionFond, BorderLayout.SOUTH);

        /*--------------------------*/
        /* Activation des composants*/
        /*--------------------------*/
        this.btnQuitter.addActionListener(this);
        this.btnImporter.addActionListener(this);
        this.txtNom.addActionListener(this);
        this.txtMDP.addActionListener(this);

        for(int cpt = 0; cpt < this.fond.getLigne(); cpt++)
        {
            for(int cpt2 = 0; cpt2 < this.fond.getColonne(); cpt2++)
            {
                this.tabButtonFond[cpt][cpt2].addActionListener(this);
            }
        }

        for(int cpt = 0; cpt < this.tabRadio.length; cpt++)
        {
            this.tabRadio[cpt].addItemListener(this);
        }

        this.btnImporter.addMouseListener(this);
        this.btnQuitter.addMouseListener(this);
        this.txtNom.addMouseListener(this);
        this.txtMDP.addMouseListener(this);
    }

    private JPanel createFieldPanel(JLabel label, JTextField field)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(40, 40, 40));
        panel.setMaximumSize(new Dimension(600, 70));
        
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(600, 35));
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(8));
        panel.add(field);
        
        return panel;
    }

    public void itemStateChanged(ItemEvent e)
    {
        // Les méthodes sont déjà appelées par les listeners
    }

    public boolean changerFond()
    {
        return this.tabRadio[0].isSelected();
    }

    public boolean changerFondBouton()
    {
        return this.tabRadio[1].isSelected();
    }

    public boolean changerEcritureBouton()
    {
        return this.tabRadio[2].isSelected();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnQuitter) 
        {
            this.frame.parametre(false);
        }

        for(int cpt = 0; cpt < this.fond.getLigne(); cpt++)
        {
            for(int cpt2 = 0; cpt2 < this.fond.getColonne(); cpt2++)
            {
                if(e.getSource() == this.tabButtonFond[cpt][cpt2])
                {
                    if(this.changerFond())
                    {
                        this.frame.setFond(this.tabButtonFond[cpt][cpt2].getBackground());
                    }
                    
                    if(this.changerFondBouton())
                    {
                        this.frame.setFondBouton(this.tabButtonFond[cpt][cpt2].getBackground());
                    }

                    if(this.changerEcritureBouton())
                    {
                        this.frame.setEcritureBouton(this.tabButtonFond[cpt][cpt2].getBackground());
                    }
                }
            }
        }

        if(e.getSource() == this.btnImporter)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setDialogTitle("Sélectionner une photo de profil");
            int returnValue = fileChooser.showOpenDialog(this);
            
            if (returnValue == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                try 
                {
                    BufferedImage image = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(140, 140, Image.SCALE_SMOOTH));
                    this.lblProfil.setIcon(icon);
                    this.lblProfil.setText("");
                } 
                catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(this, 
                        "Impossible de charger l'image", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
    public void mouseEntered(MouseEvent e)
    {
        if(e.getSource() == this.btnImporter || e.getSource() == this.btnQuitter)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JButton btn = (JButton) e.getSource();
            btn.setBackground(new Color(0, 150, 0));
        }

        if(e.getSource() == this.txtNom || e.getSource() == this.txtMDP)
        {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        }
    }
    
    public void mouseExited(MouseEvent e)
    {
        if(e.getSource() == this.btnImporter || e.getSource() == this.btnQuitter)
        {
            this.setCursor(Cursor.getDefaultCursor());
            JButton btn = (JButton) e.getSource();
            btn.setBackground(new Color(70, 70, 70));
        }

        if(e.getSource() == this.txtNom || e.getSource() == this.txtMDP)
        {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void setProfile(String nom, String prenom)
    {
        this.txtNom.setText(nom);
        this.txtNom.setEnabled(false);
        this.txtMDP.setText(prenom);
        this.txtMDP.setEnabled(false);
    }

    public void setInformation(String pseudo, String mdp)
    {
        this.txtNom.setText(pseudo);
        this.txtNom.setEnabled(false);
        this.txtMDP.setText(mdp);
        this.txtMDP.setEnabled(false);
    }

    private void styleLabel(JLabel label) 
    {
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void styleButton(JButton button)
    {
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.YELLOW);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void styleRadioButton(JRadioButton radio)
    {
        radio.setBackground(new Color(50, 50, 50));
        radio.setForeground(Color.YELLOW);
        radio.setFocusPainted(false);
        radio.setFont(new Font("Arial", Font.BOLD, 12));
        radio.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        radio.setOpaque(true);
        radio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void styleTextField(JTextField field)
    {
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.YELLOW);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }
}