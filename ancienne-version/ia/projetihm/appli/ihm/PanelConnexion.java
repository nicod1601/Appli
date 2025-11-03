package appli.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelConnexion extends JPanel implements ActionListener, MouseListener
{
    private JTextField txtPseudo;
    private JPasswordField txtMDP;
    private JLabel lblMessage;
    private JCheckBox chkShowPassword;
    
    private JPanel panelForm;
    private JPanel panelBouton;
    private JButton btnConnexion;
    private JButton btnCreer;
    private JProgressBar progressBar;
    
    private FrameConnexion frame;
    private boolean isProcessing = false;

    public PanelConnexion(FrameConnexion frame)
    {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(new Color(30, 30, 30));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.frame = frame;
        
        /*--------------------------*/
        /* Création des composants  */
        /*--------------------------*/
        
        // Titre
        JLabel lblTitre = new JLabel("CONNEXION", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(Color.YELLOW);
        lblTitre.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Formulaire
        panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(new Color(40, 40, 40));
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 2),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Pseudo
        JLabel lblPseudo = new JLabel("Nom d'utilisateur");
        styleLabel(lblPseudo);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelForm.add(lblPseudo, gbc);

        this.txtPseudo = new JTextField(20);
        styleTextField(this.txtPseudo);
        txtPseudo.setToolTipText("Entrez votre nom d'utilisateur");
        gbc.gridy = 1;
        panelForm.add(this.txtPseudo, gbc);

        // Mot de passe
        JLabel lblMDP = new JLabel("Mot de passe");
        styleLabel(lblMDP);
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        panelForm.add(lblMDP, gbc);

        this.txtMDP = new JPasswordField(20);
        styleTextField(this.txtMDP);
        txtMDP.setToolTipText("Entrez votre mot de passe");
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelForm.add(this.txtMDP, gbc);

        // Case "Afficher le mot de passe"
        this.chkShowPassword = new JCheckBox("Afficher le mot de passe");
        styleCheckBox(this.chkShowPassword);
        gbc.gridy = 4;
        panelForm.add(this.chkShowPassword, gbc);

        // Message d'erreur
        this.lblMessage = new JLabel(" ", SwingConstants.CENTER);
        this.lblMessage.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 5, 5, 5);
        panelForm.add(this.lblMessage, gbc);

        // Barre de progression
        this.progressBar = new JProgressBar();
        this.progressBar.setIndeterminate(true);
        this.progressBar.setVisible(false);
        this.progressBar.setBackground(new Color(50, 50, 50));
        this.progressBar.setForeground(Color.YELLOW);
        gbc.gridy = 6;
        panelForm.add(this.progressBar, gbc);

        // Boutons
        panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBouton.setBackground(new Color(30, 30, 30));
        
        this.btnConnexion = createStyledButton("Se connecter", new Color(34, 139, 34));
        this.btnCreer = createStyledButton("Créer un compte", new Color(30, 144, 255));

        panelBouton.add(this.btnConnexion);
        panelBouton.add(this.btnCreer);

        /*---------------------------*/
        /*   Position des composants */
        /*---------------------------*/
        this.add(lblTitre, BorderLayout.NORTH);
        this.add(panelForm, BorderLayout.CENTER);
        this.add(panelBouton, BorderLayout.SOUTH);

        /*---------------------------*/
        /* Activation des composants */
        /*---------------------------*/
        this.btnConnexion.addActionListener(this);
        this.btnCreer.addActionListener(this);
        this.txtPseudo.addActionListener(this);
        this.txtMDP.addActionListener(this);
        
        this.btnConnexion.addMouseListener(this);
        this.btnCreer.addMouseListener(this);

        this.chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                txtMDP.setEchoChar((char) 0);
            } else {
                txtMDP.setEchoChar('•');
            }
        });

        // Focus par défaut sur le champ pseudo
        SwingUtilities.invokeLater(() -> txtPseudo.requestFocusInWindow());
    }

    public void actionPerformed(ActionEvent e)
    {
        if (isProcessing) return;

        if (e.getSource() == this.btnCreer)
        {
            this.frame.creerCompte();
            return;
        }



        /*char[] mdp = this.txtMDP.getPassword();
        String mdpString = new String(mdp);


        if(! this.txtPseudo.getText().equals("") && ! mdpString.equals("") && e.getSource() == this.btnConnexion)
        {
            if (e.getSource() == this.btnConnexion)
            {
                if(this.frame.connecter(this.txtPseudo.getText(), mdpString))
                {
                    System.out.println("Connexion effectuée");
                    this.frame.setInformation(this.txtPseudo.getText(), mdpString);
                    System.out.println("pseudo : " + this.txtPseudo.getText() + " mdp : " + mdpString);

                    this.lblMessage.setText("");
                    this.txtPseudo.setText("");
                    this.txtMDP.setText("");
                    this.frame.frameAppli();
                    this.frame.setVisible(false);

                    
                }
                else
                {
                    System.out.println("Connexion impossible");
                    this.lblMessage.setText("Connexion impossible");
                    this.lblMessage.setForeground(Color.RED);

                    System.out.println("pseudo : " + this.txtPseudo.getText() + " mdp : " + mdpString);
                }
            }
        }*/

        // Connexion ou Enter dans les champs
        if (e.getSource() == this.btnConnexion || 
            e.getSource() == this.txtPseudo || 
            e.getSource() == this.txtMDP)
        {
            attemptLogin();
        }
    }


    private void attemptLogin()
    {
        String pseudo = this.txtPseudo.getText().trim();
        char[] mdpChars = this.txtMDP.getPassword();
        String mdp = new String(mdpChars);

        // Effacer le mot de passe de la mémoire
        java.util.Arrays.fill(mdpChars, '0');

        // Validation
        if (pseudo.isEmpty() || mdp.isEmpty())
        {
            showError("Veuillez remplir tous les champs");
            return;
        }

        if (pseudo.length() < 3)
        {
            showError("Le nom d'utilisateur doit contenir au moins 3 caractères");
            return;
        }

        if (mdp.length() < 4)
        {
            showError("Le mot de passe doit contenir au moins 4 caractères");
            return;
        }



        // Désactiver les contrôles pendant le traitement
        setProcessing(true);
        
        // Simuler un traitement asynchrone (dans une vraie app, utilisez SwingWorker)
        Timer timer = new Timer(500, e -> {
            if (this.frame.connecter(pseudo, mdp))
            {
                showSuccess("Connexion réussie !");
                
                // Attendre un peu avant de changer d'écran
                Timer redirectTimer = new Timer(1000, evt -> {
                    this.frame.setInformation(pseudo, mdp);
                    clearFields();
                    this.frame.frameAppli();
                    this.frame.setVisible(false);
                    setProcessing(false);
                });
                redirectTimer.setRepeats(false);
                redirectTimer.start();
            }
            else
            {
                showError("Nom d'utilisateur ou mot de passe incorrect");
                setProcessing(false);
                txtMDP.setText("");
                txtMDP.requestFocusInWindow();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void setProcessing(boolean processing)
    {
        isProcessing = processing;
        btnConnexion.setEnabled(!processing);
        btnCreer.setEnabled(!processing);
        txtPseudo.setEnabled(!processing);
        txtMDP.setEnabled(!processing);
        progressBar.setVisible(processing);
        
        if (processing) {
            lblMessage.setText("Connexion en cours...");
            lblMessage.setForeground(Color.YELLOW);
        }
    }

    private void showError(String message)
    {
        lblMessage.setText("❌ " + message);
        lblMessage.setForeground(new Color(255, 100, 100));
        
        // Animation de secousse
        Point originalLocation = panelForm.getLocation();
        Timer shakeTimer = new Timer(50, null);
        final int[] shakeCount = {0};
        
        shakeTimer.addActionListener(e -> {
            if (shakeCount[0] < 6) {
                int offset = (shakeCount[0] % 2 == 0) ? 5 : -5;
                panelForm.setLocation(originalLocation.x + offset, originalLocation.y);
                shakeCount[0]++;
            } else {
                panelForm.setLocation(originalLocation);
                ((Timer)e.getSource()).stop();
            }
        });
        shakeTimer.start();
    }

    private void showSuccess(String message)
    {
        lblMessage.setText("✓ " + message);
        lblMessage.setForeground(new Color(100, 255, 100));
    }

    private void clearFields()
    {
        txtPseudo.setText("");
        txtMDP.setText("");
        lblMessage.setText(" ");
        chkShowPassword.setSelected(false);
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e)
    {
        if (e.getComponent() == this.btnConnexion) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (!isProcessing) {
                this.btnConnexion.setBackground(new Color(50, 180, 50));
            }
        }
        if (e.getComponent() == this.btnCreer) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (!isProcessing) {
                this.btnCreer.setBackground(new Color(60, 170, 255));
            }
        }
    }

    public void mouseExited(MouseEvent e)
    {
        this.setCursor(Cursor.getDefaultCursor());
        if (e.getComponent() == this.btnConnexion && !isProcessing) {
            this.btnConnexion.setBackground(new Color(34, 139, 34));
        }
        if (e.getComponent() == this.btnCreer && !isProcessing) {
            this.btnCreer.setBackground(new Color(30, 144, 255));
        }
    }

    private void styleLabel(JLabel label) 
    {
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Arial", Font.BOLD, 13));
    }

    private void styleTextField(JTextField field)
    {
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.YELLOW);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.YELLOW, 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleCheckBox(JCheckBox checkBox)
    {
        checkBox.setBackground(new Color(40, 40, 40));
        checkBox.setForeground(Color.YELLOW);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 11));
        checkBox.setFocusPainted(false);
    }

    private JButton createStyledButton(String text, Color bgColor)
    {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void setEcritureBouton(Color c)
    {
        this.btnConnexion.setForeground(c);
        this.btnCreer.setForeground(c);
    }

    public void setFondBouton(Color c)
    {
        // Garde les couleurs spécifiques des boutons
    }
}