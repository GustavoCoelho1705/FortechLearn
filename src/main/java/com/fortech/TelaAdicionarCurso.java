package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicionarCurso extends JFrame {
    public TelaAdicionarCurso() {
        super("Adicionar Curso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitulo = new JLabel("Título:");
        final JTextField campoTitulo = new JTextField(20);

        JLabel labelDescricao = new JLabel("Descrição:");
        final JTextArea areaDescricao = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(areaDescricao);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoVoltar = new JButton("Voltar");

        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridLayout(2, 2, 10, 10));
        painelFormulario.add(labelTitulo);
        painelFormulario.add(campoTitulo);
        painelFormulario.add(labelDescricao);
        painelFormulario.add(scrollPane);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoSalvar);

        painel.add(painelFormulario, BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = campoTitulo.getText();
                String descricao = areaDescricao.getText();

                // Aqui você pode fazer o tratamento dos dados (salvar no banco de dados, por exemplo)

                // Exemplo de exibição dos dados salvos em uma nova janela
                JOptionPane.showMessageDialog(null, "Curso salvo:\n\nTítulo: " + titulo + "\nDescrição: " + descricao);

                // Limpar os campos após salvar
                campoTitulo.setText("");
                areaDescricao.setText("");
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaMenuAdmin();
            }
        });

        setContentPane(painel);
        setVisible(true);
    }

    private void voltarTelaMenuAdmin() {
        dispose();
        new TelaMenuAdmin();
    }
}
