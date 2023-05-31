
  package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLoginAdmin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaLoginAdmin() {
        super("Login do Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.CENTER));

        Color corTexto = Color.BLACK;

        JLabel labelUsuario = new JLabel("Email:");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        labelUsuario.setForeground(corTexto);
        campoUsuario = new JTextField(20);
        campoUsuario.setForeground(Color.BLACK);
        campoUsuario.setBackground(new Color(255, 255, 255, 150));
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.BOLD, 18));
        labelSenha.setForeground(corTexto);
        campoSenha = new JPasswordField(20);
        campoSenha.setForeground(Color.BLACK);
        campoSenha.setBackground(new Color(255, 255, 255, 150));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBackground(campoSenha.getBackground());
        botaoLogin.setForeground(corTexto);
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 18));
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());
                
                if (email.equals("admin") && senha.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Login de administrador bem-sucedido!");

                    abrirTelaMenuAdmin();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha de administrador incorretos!");
                }
            }
        });

        painel.add(labelUsuario);
        painel.add(campoUsuario);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(botaoLogin);

        setContentPane(painel);
        setVisible(true);
    }

    private void abrirTelaMenuAdmin() {
        dispose(); // Fecha a tela de login do administrador
        new TelaMenuAdmin(); // Abre a tela de menu do administrador
    }
}


