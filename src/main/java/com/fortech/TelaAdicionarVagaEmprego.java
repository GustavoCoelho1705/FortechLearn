package com.fortech;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;

import com.fortech.model.Conteudos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicionarVagaEmprego extends JFrame {
    public TelaAdicionarVagaEmprego() {
        super("Adicionar Vaga de Emprego");
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

                Boolean retorno = InserirEmprego(titulo, descricao);

                if(retorno) {

                    JOptionPane.showMessageDialog(null, "Emprego Salvo!:\n\nTítulo: " + titulo + "\nDescrição: " + descricao);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir emprego: " + titulo + "\n");
                }
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

    private Boolean InserirEmprego(String titulo, String descricao) 
    {
        Conteudos c = new Conteudos();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fortech-jpa");
        EntityManager em = emf.createEntityManager();

        c.setTitulo(titulo);
        c.setConteudo(descricao);
        c.setModulo("Empregos");

        em.getTransaction().begin();
        
        try {
            em.persist(c);
            em.getTransaction().commit();
            return true;
        }
        catch(Exception ex) {
            em.getTransaction().commit();
            
            return false;
        }
    }
}
