package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuAdmin extends JFrame {
    public TelaMenuAdmin() {
        super("Menu Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton botaoCadastrarAdm = new JButton("Cadastrar Administrador");        
        JButton botaoAdicionarGuia = new JButton("Adicionar Guia de Investimento");
        JButton botaoAdicionarVagas = new JButton("Adicionar Vagas de Emprego");
        JButton botaoAdicionarCursos = new JButton("Adicionar Cursos");
        JButton botaoVoltar = new JButton("Voltar");

        botaoCadastrarAdm.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoAdicionarGuia.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoAdicionarVagas.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoAdicionarCursos.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 18));

        painel.add(botaoAdicionarGuia);
        painel.add(botaoAdicionarVagas);
        painel.add(botaoAdicionarCursos);
        painel.add(botaoVoltar);
        painel.add(botaoCadastrarAdm);

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

        setContentPane(painel);
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
    
}
