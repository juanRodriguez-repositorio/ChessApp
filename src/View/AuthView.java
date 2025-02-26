/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author kamus
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ModelView.AuthController;

public class AuthView extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JLabel outPut;
    private boolean isLoading;
    private int type;

    public AuthView() {
        setTitle("Authentication");
        setSize(600, 470);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(213, 249, 222));
        setLayout(new BorderLayout());
        
        outPut=new JLabel("<html>"+"<span style='font-size:13pt'>"+"¡A divertirse!"+"</span></html>");
        outPut.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel messagePanel=new JPanel();
        messagePanel.setBackground(new Color(213, 249, 222));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(60, 10, 0, 10));
        JLabel messageLabel = new JLabel("Bienvenido, por favor registrate o inicia sesión si ya tienes una cuenta", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        messagePanel.add(messageLabel);
        add(messagePanel, BorderLayout.NORTH);
        
        
        
                
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(213, 249, 222));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
      
        // Agregamos el output entre el título y el campo de nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(outPut, gbc);

        JLabel nameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(nameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);

        loginButton = new JButton("Log In");
        loginButton.addActionListener(e -> loginUser());
        loginButton.setPreferredSize(new Dimension(100,35));
        styleButtonLightGreen(loginButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(Box.createVerticalStrut(20),gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> registerUser());
        styleButtonWhite(registerButton);
        registerButton.setPreferredSize(new Dimension(100,35));
        gbc.gridy = 4;
        formPanel.add(registerButton, gbc);

        add(formPanel, BorderLayout.CENTER);
        
        
        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> registerUser());
        styleButtonWhite(registerButton);
        registerButton.setPreferredSize(new Dimension(100,35));
        gbc.gridy = 5;
        formPanel.add(registerButton, gbc);

        add(formPanel, BorderLayout.CENTER);
        loginButton.setFocusable(false);
        registerButton.setFocusable(false);
        AuthController.init();
    }
    private void styleButtonWhite(JButton button) {
       button.setBackground(new Color(255,255,255)); 
       button.setForeground(Color.BLACK); 
       button.setFont(new Font("Arial", Font.BOLD, 12)); // Fuente
       button.setFocusPainted(false); // Eliminar el borde de enfoque
       button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregar padding
       button.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseEntered(MouseEvent e) {
               if(button.isEnabled()==false){
                   return;
               }
               button.setBackground(new Color(230,230,230)); // Color de fondo más claro al pasar el ratón
           }

           @Override
           public void mouseExited(MouseEvent e) {
               if(button.isEnabled()==false){
                   return;
               }
               button.setBackground(new Color(255,255,255)); // Restaurar el color original
           }

           @Override
           public void mousePressed(MouseEvent e) {
               if(button.isEnabled()==false){
                   return;
               }
               UIManager.put("Button.select",new Color(220,220,220));
               button.revalidate(); // Cambiar el color cuando se hace clic
           }

           @Override
           public void mouseReleased(MouseEvent e) {
               if(button.isEnabled()==false){
                   return;
               }
               if (button.getBackground() != new Color(255,255,255)) {
                    //button.setBackground(hoverColor);
                    UIManager.put("Button.select",new Color(230,230,230));
                    } else {
                      //button.setBackground(backgroundColor);
                      UIManager.put("Button.select",new Color(255,255,255));
                    }
            
                } // Restaurar el color original al soltar el clic
            
            });
    }
    private void styleButtonLightGreen(JButton button){
        button.setBackground(new Color(219, 244, 167));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Fuente
        button.setFocusPainted(false); // Eliminar el borde de enfoque
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregar padding
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(200, 233, 143)); // Color de fondo más claro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                button.setBackground(new Color(219, 244, 167)); // Restaurar el color original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                UIManager.put("Button.select",new Color(176, 217, 119));
                button.revalidate(); // Cambiar el color cuando se hace clic
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(button.isEnabled()==false){
                    return;
                }
                if (button.getBackground() != new Color(219, 244, 167)) {
                     //button.setBackground(hoverColor);
                     UIManager.put("Button.select",new Color(200, 233, 143));
                     } else {
                       //button.setBackground(backgroundColor);
                       UIManager.put("Button.select",new Color(219, 244, 167));
                     }
            
                } // Restaurar el color original al soltar el clic
            
            });
        
    }
    private void loginUser(){
        String name=nameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        AuthController.loginUser(name, password,this);
    }
    private void registerUser(){
        String name=nameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        AuthController.registerUser(name, password,this);
    }
    public void showErrorMessage(String message){
        isLoading=false;
        showMessage("<span style='color:rgb(231, 76, 60);'>"+message+"</span>");
        styleEnabledLightGreenButton(loginButton);
        styleEnabledWhiteButton(registerButton);
    }
    public void showLoadingMessage(int type){
        isLoading = true;
        styleDisabledLightGreenButton(loginButton);
        styleDisabledWhiteButton(registerButton);
        this.type=type;
        new Thread(this::startLoadingEffect).start();
        
    }
    public void showGoodRegisting(){
        isLoading=false;
        nameField.setText("");
        passwordField.setText("");
        showMessage("<span style='color:rgb(46, 204, 113);'>"+"usuario registrado!, inicia sesión"+"</span>");
        styleEnabledLightGreenButton(loginButton);
        styleEnabledWhiteButton(registerButton);
    }
    private void styleDisabledWhiteButton(JButton button){
        button.setEnabled(false);
        button.setBackground(new Color(210,210,210));
        button.setForeground(new Color(20,20,20));
    }
    private void styleEnabledWhiteButton(JButton button){
        button.setEnabled(true);
        button.setBackground(new Color(255,255,255));
        button.setForeground(Color.BLACK);
    }
    private void styleEnabledLightGreenButton(JButton button){
         button.setEnabled(true);
         button.setBackground(new Color(219, 244, 167));
         button.setForeground(Color.BLACK);
    }
    private void styleDisabledLightGreenButton(JButton button){
         button.setEnabled(false);
         button.setBackground(new Color(187,210,139));
         button.setForeground(Color.BLACK);
    }
    private void startLoadingEffect() {
        String baseText;
        if(type==0){
            baseText = "Iniciando sesión";
        }else{
            baseText="Registrando";
        }
        
        int dots = 0;

        while (isLoading) {
            try {
                dots = (dots + 1) % 4; // Alterna entre 0, 1, 2, 3 puntos
                String text = baseText + ".".repeat(dots);
                showMessage("<span style='color:rgb(120,120,120);'>"+text+"</span>");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    private void showMessage(String outPut){
        SwingUtilities.invokeLater(()->this.outPut.setText("<html>"+"<span style='font-size:13pt'>"+outPut+"</span></html>"));
    }
    
}

