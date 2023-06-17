package com.fortech;

import javax.swing.*;

import com.fortech.model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TelaCadastro extends JFrame {
    private TelaLogin telaLogin;

    public TelaCadastro(TelaLogin telaLogin) {
        super("Cadastro");
        this.telaLogin = telaLogin;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    String imagePath = getClass().getClassLoader().getResource("com/fortech/img/Wallpaper.jpg").getPath();
                    Image image = new ImageIcon(imagePath).getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        painel.setLayout(new FlowLayout(FlowLayout.CENTER));

        Color corTexto = Color.WHITE;

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Arial", Font.BOLD, 18));
        labelNome.setForeground(corTexto);
        final JTextField campoNome = new JTextField(20);
        campoNome.setForeground(Color.BLACK);
        campoNome.setBackground(new Color(255, 255, 255));
        campoNome.setFont(new Font("Arial", Font.PLAIN, 18));
    
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Arial", Font.BOLD, 18));
        labelEmail.setForeground(corTexto);
        final JTextField campoEmail = new JTextField(20);
        campoEmail.setForeground(Color.BLACK);
        campoEmail.setBackground(new Color(255, 255, 255));
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 18));
    
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.BOLD, 18));
        labelSenha.setForeground(corTexto);
        final JPasswordField campoSenha = new JPasswordField(20);
        campoSenha.setForeground(Color.BLACK);
        campoSenha.setBackground(new Color(255, 255, 255));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));
    
        JLabel labelConfirmarSenha = new JLabel("Confirmar Senha:");
        labelConfirmarSenha.setFont(new Font("Arial", Font.BOLD, 18));
        labelConfirmarSenha.setForeground(corTexto);
        final JPasswordField campoConfirmarSenha = new JPasswordField(20);
        campoConfirmarSenha.setForeground(Color.BLACK);
        campoConfirmarSenha.setBackground(new Color(255, 255, 255));
        campoConfirmarSenha.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBackground(new Color(255, 255, 255));
        botaoCadastrar.setForeground(Color.BLACK);
        botaoCadastrar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());
                String confirmarSenha = new String(campoConfirmarSenha.getPassword());
                if (!nome.isEmpty() && !email.isEmpty())
                {
                    if(senha.equals(confirmarSenha)) {
                        Boolean cadastro = CadastrarUsuario(nome, email, confirmarSenha);

                        if(cadastro) {
                            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                            voltarTelaLogin();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Houve uma falha ao efetuar o cadastro, por favor, tente novamente");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Senhas digitadas divergem, por favor, revise-as");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                }
            }
        });

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelEmail);
        painel.add(campoEmail);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(labelConfirmarSenha);
        painel.add(campoConfirmarSenha);
        painel.add(botaoCadastrar);

        setContentPane(painel);
        setVisible(true);
    }

    private void voltarTelaLogin() {
        dispose();
        telaLogin.setVisible(true);
    }
    private Boolean CadastrarUsuario(String nome, String email, String senha) {
        Usuario u = new Usuario();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fortech-jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        u.setNome(nome);
        u.setAdministrador(false);
        u.setEmail(email);
        u.setSenha(senha);
        try {
            em.persist(u);
            em.getTransaction().commit();
            return true;
        }
        catch(Exception ex) {
            em.getTransaction().commit();
            
            return false;
        }

    }
}

