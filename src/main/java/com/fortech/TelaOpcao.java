package com.fortech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import com.fortech.model.Conteudos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class TelaOpcao extends JFrame {
    private TelaMenu telaMenu;

    public TelaOpcao(String titulo, TelaMenu telaMenu) {
        super(titulo);
        this.telaMenu = telaMenu;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        JPanel painel = new JPanel(new BorderLayout());
        
        JLabel labelTitulo = new JLabel(titulo);
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(100, 30));
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaMenu();
            }
        });
        
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSuperior.add(labelTitulo);
        painelSuperior.add(botaoVoltar);
        
        painel.add(painelSuperior, BorderLayout.NORTH);
        
        String modulo = titulo =="Guia de Investimento" ? "Investimento" : (titulo == "Cursos" ? "Cursos" : "Empregos");
        
        List<Conteudos> list = PesquisaConteudoBD(modulo);
        
        JPanel painelAccordions = new JPanel();
        painelAccordions.setLayout(new BoxLayout(painelAccordions, BoxLayout.Y_AXIS));
        
        for (Conteudos conteudo : list) {
            AccordionItem a = new AccordionItem(conteudo.getTitulo(), conteudo.getConteudo());
            painelAccordions.add(a);
        }
        
        JScrollPane scrollPane = new JScrollPane(painelAccordions);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        setContentPane(painel);
        setVisible(true);
    }

    private void voltarTelaMenu() {
        dispose();
        telaMenu.setVisible(true);
    }

    private List<Conteudos> PesquisaConteudoBD(String modulo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fortech-jpa");
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT * from conteudos where Módulo = :modulo";

        Query query = em.createNativeQuery(sql, Conteudos.class);
        query.setParameter("modulo", modulo);
        try {
            List<Conteudos> resultados = query.getResultList();
            em.close();
            return resultados;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            em.close();
            return null;
        }
    } 
    private static class AccordionItem extends JPanel {
        private JLabel titleLabel;
        private JLabel detailsLabel;
        private boolean expanded;

        public AccordionItem(String title, String details) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            setBackground(Color.WHITE);

            titleLabel = new JLabel(title);
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
            titleLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toggleExpanded();
                }
            });

            detailsLabel = new JLabel(details);
            detailsLabel.setVisible(false);

            add(titleLabel);
            add(detailsLabel);
        }

        private void toggleExpanded() {
            expanded = !expanded;
            detailsLabel.setVisible(expanded);
        }
    }
}
