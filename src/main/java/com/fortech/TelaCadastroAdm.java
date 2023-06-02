package com.fortech;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;

import com.fortech.model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroAdm extends JFrame {
    private JTextField campoNome;
    private JTextField campoEmail;
    private JPasswordField campoSenha;

    public TelaCadastroAdm() {
        super("Cadastro de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        JLabel labelEmail = new JLabel("Email:");
        campoEmail = new JTextField();

        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoCancelar = new JButton("Cancelar");

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelEmail);
        painel.add(campoEmail);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(botaoCadastrar);
        painel.add(botaoCancelar);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAdministrador(campoNome.getText(), campoEmail.getText(), new String(campoSenha.getPassword()));
            }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaMenuAdmin();
            }
        });

        setContentPane(painel);
        setVisible(true);
    }

    
    private void cadastrarAdministrador(String nome, String email, String senha) {
        Usuario u = new Usuario();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fortech-jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        u.setNome(nome);
        u.setAdministrador(true);
        u.setEmail(email);
        u.setSenha(senha);
        try {
            em.persist(u);
            em.getTransaction().commit();
            // Exemplo de exibição de uma mensagem após o cadastro
            JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso!");
        } catch(Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar administrador.");
        } finally {
            em.close();
            emf.close();
        }

        voltarTelaMenuAdmin();
    }

    private void voltarTelaMenuAdmin() {
        dispose();
        new TelaMenuAdmin();
    }
}
