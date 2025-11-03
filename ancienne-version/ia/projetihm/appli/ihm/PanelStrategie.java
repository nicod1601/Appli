package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelStrategie extends JPanel implements ActionListener, MouseListener {
    private JPanel[] tabPanelStrategie;
    private JButton retourButton;

    private FrameAppli frame;

    public PanelStrategie(FrameAppli frame) {
        this.setLayout(new BorderLayout(15, 15));
        this.setBackground(new Color(30, 30, 30));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.frame = frame;

        /*--------------------------*/
        /* Création du titre        */
        /*--------------------------*/
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(new Color(30, 30, 30));
        
        JLabel titre = new JLabel("STRATAGÈMES DISPONIBLES", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setForeground(Color.YELLOW);
        titre.setBorder(new EmptyBorder(10, 0, 20, 0));
        
        panelTop.add(titre, BorderLayout.CENTER);

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        String[][] strategies = {
            {"./img/Strateges/Orbital_Napalm_Barrage.png", "Orbital Napalm Barrage", "Un bombardement orbital qui enflamme la zone ciblée avec du napalm dévastateur."},
            {"./img/Strateges/Bombe_sac.png", "Sac Bomb", "Une charge explosive puissante à usage unique capable de détruire les structures ennemies."},
            {"./img/Strateges/fusil-gaz.png", "Toxic Gas Rifle", "Un fusil qui tire des munitions toxiques infligeant des dégâts sur la durée."},
            {"./img/Strateges/stratageme.png", "Stratagem", "Un appel de soutien tactique utilisé pour divers effets sur le champ de bataille."},
            {"./img/Strateges/bouclier.png", "Shield Generator Pack", "Fournit un bouclier temporaire absorbant les dégâts entrants."},
            {"./img/Strateges/mine-gaz.png", "Gas Mine", "Déploie des mines qui libèrent un gaz toxique mortel à l'activation."},
            {"./img/Strateges/mine-chare.png", "Anti-Tank Mine", "Une mine puissante conçue pour détruire les véhicules blindés ennemis."},
            {"./img/Strateges/voiture.png", "APC", "Un véhicule blindé offrant protection et puissance de feu mobile."},
            {"./img/Strateges/cannon-lazer.png", "Laser Cannon", "Une arme lourde qui tire des faisceaux laser destructeurs concentrés."},
            {"./img/Strateges/lance-rocket.png", "Rocket Launcher", "Un lance-roquettes puissant contre les véhicules et les grandes cibles."},
            {"./img/Strateges/lance-rocket-multiple.png", "Multiple Rocket Launcher", "Tire plusieurs roquettes en succession rapide pour saturer une zone."},
            {"./img/Strateges/cannon-laser.png", "Heavy Laser Cannon", "Une arme à énergie infligeant des dégâts élevés aux ennemis blindés."}
        };
        
        // Panel principal avec grille de cartes
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 2, 20, 20));
        gridPanel.setBackground(new Color(30, 30, 30));
        gridPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        int rows = strategies.length;
        this.tabPanelStrategie = new JPanel[rows];
        
        for (int i = 0; i < rows; i++) {
            // Création d'une carte pour chaque stratagème
            this.tabPanelStrategie[i] = createStrategemCard(
                strategies[i][0], 
                strategies[i][1], 
                strategies[i][2]
            );
            gridPanel.add(this.tabPanelStrategie[i]);
        }
        
        // ScrollPane pour la grille
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(new Color(30, 30, 30));
        
        // Personnalisation de la scrollbar
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.YELLOW;
                this.trackColor = new Color(50, 50, 50);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
        
        // Panel pour le bouton de retour
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setBackground(new Color(30, 30, 30));
        panelBottom.setBorder(new EmptyBorder(15, 0, 10, 0));
        
        retourButton = new JButton("← Retour à l'accueil");
        retourButton.setFont(new Font("Arial", Font.BOLD, 16));
        retourButton.setBackground(new Color(70, 70, 70));
        retourButton.setForeground(Color.YELLOW);
        retourButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 2),
            new EmptyBorder(10, 25, 10, 25)
        ));
        retourButton.setFocusPainted(false);
        retourButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        retourButton.addActionListener(this);
        retourButton.addMouseListener(this);
        
        panelBottom.add(retourButton);
        
        // Ajout des composants
        this.add(panelTop, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);
    }

    /**
     * Crée une carte visuelle pour un stratagème
     */
    private JPanel createStrategemCard(String imagePath, String nom, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(new Color(45, 45, 45));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Panel gauche avec l'image
        JPanel panelImage = new JPanel(new BorderLayout());
        panelImage.setBackground(new Color(45, 45, 45));
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            
            JLabel lblImage = new JLabel(icon);
            lblImage.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
            lblImage.setBackground(new Color(35, 35, 35));
            lblImage.setOpaque(true);
            panelImage.add(lblImage, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel lblError = new JLabel("Image\nindisponible", SwingConstants.CENTER);
            lblError.setForeground(Color.GRAY);
            lblError.setPreferredSize(new Dimension(120, 120));
            panelImage.add(lblError, BorderLayout.CENTER);
        }
        
        // Panel droit avec nom et description
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(45, 45, 45));
        
        // Nom du stratagème
        JLabel lblNom = new JLabel(nom);
        lblNom.setFont(new Font("Arial", Font.BOLD, 16));
        lblNom.setForeground(Color.YELLOW);
        lblNom.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Séparateur
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.YELLOW);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Description
        JTextArea txtDescription = new JTextArea(description);
        txtDescription.setFont(new Font("Arial", Font.PLAIN, 13));
        txtDescription.setForeground(Color.WHITE);
        txtDescription.setBackground(new Color(45, 45, 45));
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setEditable(false);
        txtDescription.setFocusable(false);
        txtDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelInfo.add(lblNom);
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(separator);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(txtDescription);
        
        // Assemblage de la carte
        card.add(panelImage, BorderLayout.WEST);
        card.add(panelInfo, BorderLayout.CENTER);
        
        return card;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retourButton) {
            this.frame.panelStrategie(false);
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == retourButton) {
            retourButton.setBackground(new Color(0, 180, 0));
            retourButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }
    
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == retourButton) {
            retourButton.setBackground(new Color(70, 70, 70));
        }
    }
}