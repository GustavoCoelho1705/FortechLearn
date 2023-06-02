package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuAdmin extends JFrame {
    public TelaMenuAdmin() {
        super("Menu Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagemFundo = new ImageIcon("caminho/para/a/imagem.jpg"); // Substitua pelo caminho da imagem desejada
                g.drawImage(imagemFundo.getImage(), 0, 0, null);
            }
        };
        painelFundo.setLayout(null);

        // Configuração dos botões
        JButton botaoCadastrarAdm = new JButton("Cadastrar Administrador");
        botaoCadastrarAdm.setBounds(50, 100, 300, 40);
        botaoCadastrarAdm.setFont(new Font("Arial", Font.BOLD, 18));

        JButton botaoAdicionarGuia = new JButton("Adicionar Guia de Investimento");
        botaoAdicionarGuia.setBounds(50, 160, 300, 40);
        botaoAdicionarGuia.setFont(new Font("Arial", Font.BOLD, 18));

        JButton botaoAdicionarVagas = new JButton("Adicionar Vagas de Emprego");
        botaoAdicionarVagas.setBounds(50, 220, 300, 40);
        botaoAdicionarVagas.setFont(new Font("Arial", Font.BOLD, 18));

        JButton botaoAdicionarCursos = new JButton("Adicionar Cursos");
        botaoAdicionarCursos.setBounds(50, 280, 300, 40);
        botaoAdicionarCursos.setFont(new Font("Arial", Font.BOLD, 18));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(50, 340, 300, 40);
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 18));

        // Adiciona os botões ao painel de fundo
        painelFundo.add(botaoCadastrarAdm);
        painelFundo.add(botaoAdicionarGuia);
        painelFundo.add(botaoAdicionarVagas);
        painelFundo.add(botaoAdicionarCursos);
        painelFundo.add(botaoVoltar);

        // Configuração dos eventos dos botões
        botaoCadastrarAdm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroAdm();
            }
        });

        botaoAdicionarGuia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionarGuiaInvestimento();
            }
        });

        botaoAdicionarVagas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionarVagasEmprego();
            }
        });

        botaoAdicionarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionarCursos();
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaLogin();
            }
        });

        // Define o painel de fundo como conteúdo do JFrame
        setContentPane(painelFundo);
        setVisible(true);
    }

    private void abrirTelaAdicionarGuiaInvestimento() {
        dispose();
        new TelaAdicionarGuiaInvestimento();
    }

    private void abrirTelaAdicionarVagasEmprego() {
        dispose();
        new TelaAdicionarVagaEmprego();
    }

    private void abrirTelaAdicionarCursos() {
        dispose();
        new TelaAdicionarCurso();
    }

    private void voltarTelaLogin() {
        dispose();
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }

    private void abrirTelaCadastroAdm() {
        dispose();
        new TelaCadastroAdm();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaMenuAdmin();
            }
        });
    }
}
