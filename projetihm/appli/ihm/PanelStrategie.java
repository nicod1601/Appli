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
            /*{"orbital_gatling.jpg", "Orbital Gatling", "Une mitrailleuse orbitale offrant un tir soutenu sur une zone ciblée."},
            {"eagle_airstrike.jpg", "Eagle Airstrike", "Un bombardement aérien rapide et efficace contre les ennemis au sol."},
            {"supply_pack.jpg", "Supply Pack", "Fournit des munitions et du matériel supplémentaire aux Helldivers."},
            {"guard_dog.jpg", "Guard Dog", "Un drone de soutien qui tire automatiquement sur les ennemis à proximité."},
            {"tesla_tower.jpg", "Tesla Tower", "Une tourelle stationnaire qui électrocute les ennemis dans un rayon proche."},
            {"reinforce.jpg", "Reinforce", "Permet de rappeler un coéquipier tombé au combat."},
            {"orbital_laser.jpg", "Orbital Laser", "Un puissant laser orbital qui inflige d'importants dégâts sur une large zone."},
            {"autocannon.jpg", "Autocannon", "Une arme lourde infligeant des dégâts massifs aux ennemis blindés."},
            {"shield_generator.jpg", "Shield Generator", "Crée un champ de force temporaire protégeant des attaques ennemies."},
            {"Orbital_Napalm_Barrage.png", "Orbital Napalm Barrage", "Un bombardement aérien rapide et efficace contre les ennemis au sol."}
            {"./img/competence.png", "Orbital Napalm Barrage", "Un bombardement aérien rapide et efficace contre les ennemis au sol."}*/
        };
        
        int rows = strategies.length;
        this.tabLabel = new JLabel[rows][2];
        
        for (int i = 0; i < rows; i++) {
            // Colonne 1 : Image + Nom
            /*ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(this.tabLabel[i][0].getText()));
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);*/
            //System.out.println("strategies[i][0] : " + strategies[i][0]);
            this.tabLabel[i][0] = new JLabel(strategies[i][1], null, SwingConstants.LEFT);
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
