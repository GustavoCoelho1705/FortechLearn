package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fortech.model.Usuario;

public class TelaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaLogin() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    String imagePath = getClass().getClassLoader().getResource("img/Wallpaper.jpg").getPath();
                    Image image = new ImageIcon(imagePath).getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        painel.setLayout(new FlowLayout(FlowLayout.CENTER));

        Color corTexto = Color.BLACK; // Cor das escritas

        JLabel labelUsuario = new JLabel("Email:");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        labelUsuario.setForeground(corTexto);
        campoUsuario = new JTextField(20);
        campoUsuario.setForeground(Color.BLACK);
        campoUsuario.setBackground(new Color(255, 255, 255, 150)); // Cor do campo de texto (Branco com transparência)
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.BOLD, 18));
        labelSenha.setForeground(corTexto);
        campoSenha = new JPasswordField(20);
        campoSenha.setForeground(Color.BLACK);
        campoSenha.setBackground(new Color(255, 255, 255, 150)); // Cor do campo de texto (Branco com transparência)
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBackground(campoSenha.getBackground()); // Mesma cor da caixa de senha
        botaoLogin.setForeground(corTexto);
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 18));
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());
                
                Usuario usuario = PesquisaUsuarioBD(email, senha);
                if(usuario != null) {
                    if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");

                        // Abre a tela de menu após o login ser bem-sucedido
                        abrirTelaMenu();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                }
            }
        });

        JButton botaoCadastrar = new JButton("Cadastro");
        botaoCadastrar.setBackground(campoSenha.getBackground()); // Mesma cor da caixa de senha
        botaoCadastrar.setForeground(corTexto);
        botaoCadastrar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrindo tela de cadastro...");
                abrirTelaCadastro(); // Método para abrir a tela de cadastro
            }
        });

        painel.add(labelUsuario);
        painel.add(campoUsuario);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(botaoLogin);
        painel.add(botaoCadastrar);

        setContentPane(painel);
        setVisible(true);
    }

    private void abrirTelaMenu() {
        dispose(); // Fecha a tela de login
        new TelaMenu(this); // Abre a tela de menu
    }

    private void abrirTelaCadastro() {
        dispose(); // Fecha a tela de login
        new TelaCadastro(this); // Abre a tela de cadastro
    }
    private Usuario PesquisaUsuarioBD(String email, String senha)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fortech-jpa");
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT * FROM usuarios WHERE senha = :senha AND email = :email";

        Query query = em.createNativeQuery(sql, Usuario.class);
        query.setParameter("senha", senha);
        query.setParameter("email", email);
        try {
            Usuario resultados = (Usuario)query.getSingleResult();
            em.close();
            return resultados;
        }
        catch(Exception ex) {
            em.close();
            return null;
        }
        
    }
}


