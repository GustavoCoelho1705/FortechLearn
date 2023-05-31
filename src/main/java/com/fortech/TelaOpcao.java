package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaOpcao extends JFrame {
    private TelaMenu telaMenu;

    public TelaOpcao(String titulo, TelaMenu telaMenu) {
        super(titulo);
        this.telaMenu = telaMenu;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
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

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(100, 30));
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaMenu();
            }
        });

        painel.add(new JLabel(titulo));
        painel.add(botaoVoltar);

        setContentPane(painel);
        setVisible(true);
    }

    private void voltarTelaMenu() {
        dispose();
        telaMenu.setVisible(true);
    }
}
