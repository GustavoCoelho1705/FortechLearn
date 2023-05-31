
  package com.fortech;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;

import com.fortech.model.Usuario;

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
                
                Usuario usuario = PesquisaUsuarioBD(email, senha);
                if (usuario != null) {
                    if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                        JOptionPane.showMessageDialog(null, "Login de administrador bem-sucedido!");

                        // Abre a tela de menu após o login ser bem-sucedido
                        abrirTelaMenuAdmin();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha do ADM incorretos!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha do ADM incorretos!");
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

    private Usuario PesquisaUsuarioBD(String email, String senha) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fortech-jpa");
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT * FROM usuarios WHERE senha = :senha AND email = :email AND Administrador = :adm";

        Query query = em.createNativeQuery(sql, Usuario.class);
        query.setParameter("senha", senha);
        query.setParameter("email", email);
        query.setParameter("adm", true);
        try {
            Usuario resultados = (Usuario) query.getSingleResult();
            em.close();
            return resultados;
        } catch (Exception ex) {
            em.close();
            return null;
        }
    }
}


