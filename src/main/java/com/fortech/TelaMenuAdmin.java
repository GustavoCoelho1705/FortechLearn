package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuAdmin extends JFrame {
    public TelaMenuAdmin() {
        super("Menu do Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.CENTER));

        Color corTexto = Color.BLACK;

        JLabel labelTitulo = new JLabel("Bem-vindo, Administrador!");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setForeground(corTexto);

        JButton botaoSair = new JButton("Sair");
        botaoSair.setBackground(Color.RED);
        botaoSair.setForeground(Color.WHITE);
        botaoSair.setFont(new Font("Arial", Font.BOLD, 18));
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de menu do administrador
                new TelaLogin(); // Volta para a tela de login principal
            }
        });

        painel.add(labelTitulo);
        painel.add(botaoSair);

        setContentPane(painel);
        setVisible(true);
    }
}

