package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends JFrame {
    private TelaLogin telaLogin;

    public TelaMenu(TelaLogin telaLogin) {
        super("Menu");
        this.telaLogin = telaLogin;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
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
        painel.setLayout(null);

        JButton botaoOpcao1 = new JButton("Guia de investimento");
        botaoOpcao1.setBounds(50, 50, 200, 50);
        botaoOpcao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaGuiaInvestimento();
            }
        });
        painel.add(botaoOpcao1);

        JButton botaoOpcao2 = new JButton("Empregos área TI");
        botaoOpcao2.setBounds(50, 120, 200, 50);
        botaoOpcao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaEmpregosTI();
            }
        });
        painel.add(botaoOpcao2);

        JButton botaoOpcao3 = new JButton("Cursos");
        botaoOpcao3.setBounds(50, 190, 200, 50);
        botaoOpcao3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCursos();
            }
        });
        painel.add(botaoOpcao3);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(680, 10, 100, 30);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaLogin();
            }
        });
        painel.add(botaoVoltar);

        setContentPane(painel);
        setVisible(true);
    }

    private void abrirTelaGuiaInvestimento() {
        dispose();
        new TelaOpcao("Guia de Investimento", this);
    }

    private void abrirTelaEmpregosTI() {
        dispose();
        new TelaOpcao("Empregos área TI", this);
    }

    private void abrirTelaCursos() {
        dispose();
        new TelaOpcao("Cursos", this);
    }

    private void voltarTelaLogin() {
        dispose();
        telaLogin.setVisible(true);
    }
}
