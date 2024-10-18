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
    private JPanel panelPrincipal;  // Panel principal para cambiar entre formularios

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

        // Crear un botón para cada opción y vincularlo a un formulario específico
        for (int i = 0; i < opciones.length; i++) {
            String opcion = opciones[i];
            JButton boton = new JButton(opcion);
            int opcionIndex = i + 1; // Para pasar el índice de la opción (1 a 4)
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Mostrar el formulario correspondiente a la opción seleccionada
                    JPanel formularioPanel = crearFormularioPorOpcion(opcionIndex);
                    panelPrincipal.add(formularioPanel, "Formulario" + opcionIndex);
                    cardLayout.show(panelPrincipal, "Formulario" + opcionIndex);
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

    // Método que devuelve el formulario según la opción seleccionada
    private JPanel crearFormularioPorOpcion(int opcion) {
        switch (opcion) {
            case 1:
                return crearFormularioPanelOpcion1();
            case 2:
                return crearFormularioPanelOpcion2();
            case 3:
                return crearFormularioPanelOpcion3();
            case 4:
                return crearFormularioPanelOpcion4();
            default:
                return new JPanel();  // Panel vacío por defecto
        }
    }

    // Formularios para cada opción

    private JPanel crearFormularioPanelOpcion1() {
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(4, 2));  // 4 filas, 2 columnas

        formularioPanel.add(new JLabel("Campo 1 (Opción 1):"));
        formularioPanel.add(new JTextField(15));

        formularioPanel.add(new JLabel("Campo 2 (Opción 1):"));
        formularioPanel.add(new JTextField(15));

        agregarBotonesFormulario(formularioPanel, "Formulario 1");
        return formularioPanel;
    }

    private JPanel crearFormularioPanelOpcion2() {
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(4, 2));

        formularioPanel.add(new JLabel("Campo 1 (Opción 2):"));
        formularioPanel.add(new JTextField(15));

        formularioPanel.add(new JLabel("Campo 2 (Opción 2):"));
        formularioPanel.add(new JTextField(15));

        agregarBotonesFormulario(formularioPanel, "Formulario 2");
        return formularioPanel;
    }

    private JPanel crearFormularioPanelOpcion3() {
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(4, 2));

        formularioPanel.add(new JLabel("Campo 1 (Opción 3):"));
        formularioPanel.add(new JTextField(15));

        formularioPanel.add(new JLabel("Campo 2 (Opción 3):"));
        formularioPanel.add(new JTextField(15));

        agregarBotonesFormulario(formularioPanel, "Formulario 3");
        return formularioPanel;
    }

    private JPanel crearFormularioPanelOpcion4() {
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(4, 2));

        formularioPanel.add(new JLabel("Campo 1 (Opción 4):"));
        formularioPanel.add(new JTextField(15));

        formularioPanel.add(new JLabel("Campo 2 (Opción 4):"));
        formularioPanel.add(new JTextField(15));

        agregarBotonesFormulario(formularioPanel, "Formulario 4");
        return formularioPanel;
    }

    // Método para agregar los botones de "Enviar" y "Cancelar" a los formularios
    private void agregarBotonesFormulario(JPanel formularioPanel, String nombreFormulario) {
        // Botón para enviar
        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MiAplicacion.this, nombreFormulario + " enviado.");
            }
        });
        formularioPanel.add(enviarButton);

        // Botón para cancelar y regresar al panel de opciones
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Opciones");
            }
        });
        formularioPanel.add(cancelarButton);
    }

    
}
