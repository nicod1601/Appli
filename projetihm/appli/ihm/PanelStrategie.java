package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelStrategie extends JPanel implements ActionListener, MouseListener {
    private JLabel[][] tabLabel;
    private JButton retourButton;

    private FrameAppli frame;

    public PanelStrategie(FrameAppli frame) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        gridPanel.setBackground(new Color(30, 30, 30));

        this.frame = frame;

        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        String[][] strategies = {
            {"./img/Strateges/Orbital_Napalm_Barrage.png", "Orbital Napalm Barrage", "Un bombardement orbital qui enflamme la zone ciblée."},
            {"./img/Strateges/Bombe_sac.png"             , "Sac Bomb", "Une charge explosive puissante à usage unique."},
            {"./img/Strateges/fusil-gaz.png"             , "Toxic Gas Rifle", "Un fusil qui tire des munitions toxiques infligeant des dégâts sur la durée."},
            {"./img/Strateges/stratageme.png"            , "Stratagem", "Un appel de soutien tactique utilisé pour divers effets sur le champ de bataille."},
            {"./img/Strateges/bouclier.png"              , "Shield Generator Pack", "Fournit un bouclier temporaire absorbant les dégâts."},
            {"./img/Strateges/mine-gaz.png"              , "Gas Mine", "Déploie des mines qui libèrent un gaz toxique à l'activation."},
            {"./img/Strateges/mine-chare.png"            , "Anti-Tank Mine", "Une mine puissante conçue pour détruire les véhicules ennemis."},
            {"./img/Strateges/voiture.png"               , "APC (Armored Personnel Carrier)", "Un véhicule blindé offrant protection et puissance de feu."},
            {"./img/Strateges/cannon-lazer.png"          , "Laser Cannon", "Une arme lourde qui tire des faisceaux laser destructeurs."},
            {"./img/Strateges/lance-rocket.png"          , "Rocket Launcher", "Un lance-roquettes puissant contre les véhicules et les grandes cibles."},
            {"./img/Strateges/lance-rocket-multiple.png" , "Multiple Rocket Launcher", "Tire plusieurs roquettes en succession rapide."},
            {"./img/Strateges/cannon-laser.png"          , "Laser Cannon", "Une arme à énergie infligeant des dégâts élevés aux ennemis blindés."}
        };
        
        int rows = strategies.length;
        this.tabLabel = new JLabel[rows][2];
        
        for (int i = 0; i < rows; i++) {
            // Colonne 1 : Image + Nom
            ImageIcon icon = new ImageIcon(getClass().getResource(strategies[i][0]));
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            this.tabLabel[i][0] = new JLabel(strategies[i][1], icon, SwingConstants.LEFT);
            this.tabLabel[i][0].setForeground(Color.WHITE);
            this.tabLabel[i][0].setFont(new Font("Arial", Font.BOLD, 14));
            this.tabLabel[i][0].setOpaque(true);
            this.tabLabel[i][0].setBackground(new Color(50, 50, 50));
            gridPanel.add(this.tabLabel[i][0]);
            
            // Colonne 2 : Description
            this.tabLabel[i][1] = new JLabel("<html>" + strategies[i][2] + "</html>", SwingConstants.LEFT);
            this.tabLabel[i][1].setForeground(Color.WHITE);
            this.tabLabel[i][1].setFont(new Font("Arial", Font.PLAIN, 12));
            gridPanel.add(this.tabLabel[i][1]);
        }
        
        // Bouton de retour à l'accueil
        retourButton = new JButton("Retour à l'accueil");
        retourButton.setFont(new Font("Arial", Font.BOLD, 14));
        retourButton.setBackground(new Color(70, 70, 70));
        retourButton.setForeground(Color.YELLOW);
        retourButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        retourButton.setFocusPainted(false);
        retourButton.addActionListener(this);
        
        // Ajout des composants
        this.add(new JScrollPane(gridPanel), BorderLayout.CENTER);
        this.add(retourButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retourButton) {
            System.out.println("Retour à l'accueil...");
            this.frame.panelStrategie(false);
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
