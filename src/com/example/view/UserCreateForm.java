package com.example.view;

import com.cinema.dao.UserDaoImpl;
import com.cinema.dao.UserRoleDao;
import com.cinema.dao.UserRoleDaoImpl;
import com.cinema.dto.UserDto;
import com.cinema.error.RegistrationException;
import com.cinema.model.User;
import com.cinema.model.UserRole;
import com.cinema.service.UserRegistrationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class UserCreateForm extends JFrame {
    private UserRegistrationService userRegistrationService;
    private UserRoleDaoImpl userRoleDao;
    private JLabel usernameLabel, emailLabel, passwordLabel, confirmPasswordLabel, userRoleLabel;
    private JTextField usernameTF, emailTF;
    private JPasswordField passwordTF, confirmPasswordTF;
    private JComboBox<UserRole> userRoleBox;
    private JButton createBtn, resetBtn;

    public UserCreateForm() {
        this.userRoleDao = new UserRoleDaoImpl();
        this.userRegistrationService = new UserRegistrationService();
        initializeComponents();
        initializeUIComponents();
        this.setLocationRelativeTo(null); // Center the form on the screen
        this.setVisible(true);
    }

    private void initializeComponents() {
        this.setSize(450, 300);
        this.setTitle("User Create Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10)); // Adds margin around the form
        this.setResizable(false); // Makes the form non-resizable
    }

    private void initializeUIComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and Text Fields
        usernameLabel = new JLabel("User Name: ");
        emailLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");
        confirmPasswordLabel = new JLabel("Confirm Password: ");
        userRoleLabel = new JLabel("User Role: ");

        usernameTF = new JTextField(20);
        emailTF = new JTextField(20);
        passwordTF = new JPasswordField(20);
        confirmPasswordTF = new JPasswordField(20);

        // User Role ComboBox with predefined roles
        List<UserRole> userRoleList = this.userRoleDao.getAll();
        userRoleBox = new JComboBox<>(userRoleList.toArray(new UserRole[0]));
        createBtn = new JButton("Create");
        resetBtn = new JButton("Reset");

        // Adding components to the form panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(userRoleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userRoleBox, gbc);

        // Buttons Panel for Create and Reset buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.add(createBtn);
        buttonPanel.add(resetBtn);
        setUpCreateButtonAction();
        // Adding panels to the main frame
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setUpCreateButtonAction(){
        this.createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBtnAction();
            }
        });
    }

    private void createBtnAction() {
        String username = this.usernameTF.getText();
        String email = this.emailTF.getText();
        String password = new String(this.passwordTF.getPassword());
        String confirmPassword = new String(this.confirmPasswordTF.getPassword());
        UserRole selectedUserRole = (UserRole) this.userRoleBox.getSelectedItem();

        UserDto userDto = new UserDto(username, email, password, confirmPassword, selectedUserRole);
       try {
           this.userRegistrationService.call(userDto);
           JOptionPane.showMessageDialog(this, "Registration Successful!!!");
           this.dispose();
           new LoginWindow();
       }catch (RegistrationException e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }

    }
}
