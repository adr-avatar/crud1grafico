/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud1visual;

/**
 *
 * @author adr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiAplicacion extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;  // Mover a variable de instancia para usarla en otros métodos

    public MiAplicacion() {
        // Configuración del JFrame
        setTitle("Mi Aplicación");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usar CardLayout para cambiar entre paneles
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Crear el primer panel con 4 opciones
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(4, 1));
        String[] opciones = { "Opción 1", "Opción 2", "Opción 3", "Opción 4" };

        for (String opcion : opciones) {
            JButton boton = new JButton(opcion);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Cerrar el panel de opciones y abrir el formulario
                    JPanel formularioPanel = crearFormularioPanel();
                    panelPrincipal.add(formularioPanel, "Formulario");
                    cardLayout.show(panelPrincipal, "Formulario");
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                }
            });
            panelOpciones.add(boton);
        }

        // Agregar el panel de opciones al panel principal
        panelPrincipal.add(panelOpciones, "Opciones");

        // Agregar el panel principal al JFrame
        add(panelPrincipal);

        // Hacer visible el primer panel
        cardLayout.show(panelPrincipal, "Opciones");
    }

    private JPanel crearFormularioPanel() {
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(4, 2));  // Aumentamos a 4 filas para agregar el botón Cancelar

        // Campos del formulario
        formularioPanel.add(new JLabel("Campo 1:"));
        formularioPanel.add(new JTextField(15));

        formularioPanel.add(new JLabel("Campo 2:"));
        formularioPanel.add(new JTextField(15));

        // Botón para enviar
        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MiAplicacion.this, "Formulario enviado.");
            }
        });
        formularioPanel.add(enviarButton);

        // Botón para cancelar y regresar al panel de opciones
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver al panel de opciones
                cardLayout.show(panelPrincipal, "Opciones");
            }
        });
        formularioPanel.add(cancelarButton);

        return formularioPanel;
    }

    
}
