/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.musicapp;

/**
 *
 * @author adr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class MusicApp extends JFrame {
    private JTextArea textArea;
    private File file = new File("musica.txt");

    public MusicApp() {
        setTitle("Gestor de Música");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(1, 4));

        // Botón Crear
        JButton btnCrear = new JButton("Crear");
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario();
            }
        });
        panelPrincipal.add(btnCrear);

        // Botón Leer
        JButton btnLeer = new JButton("Leer");
        btnLeer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leerArchivo();
            }
        });
        panelPrincipal.add(btnLeer);

        // Botón Modificar
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarRegistro();
            }
        });
        panelPrincipal.add(btnModificar);

        // Botón Borrar
        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarRegistro();
            }
        });
        panelPrincipal.add(btnBorrar);

        add(panelPrincipal, BorderLayout.NORTH);

        // Área de texto para mostrar el contenido
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Formulario para capturar los datos de la canción
    private void abrirFormulario() {
        JFrame formFrame = new JFrame("Formulario");
        formFrame.setSize(300, 250);
        formFrame.setLayout(new GridLayout(5, 2));

        JLabel labelId = new JLabel("ID:");
        JTextField textId = new JTextField();

        JLabel labelNombre = new JLabel("Nombre de Canción:");
        JTextField textNombre = new JTextField();

        JLabel labelArtista = new JLabel("Artista:");
        JTextField textArtista = new JTextField();

        JLabel labelAnio = new JLabel("Año de Lanzamiento:");
        JTextField textAnio = new JTextField();

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarRegistro(textId.getText(), textNombre.getText(), textArtista.getText(), textAnio.getText());
                formFrame.dispose();
            }
        });

        formFrame.add(labelId);
        formFrame.add(textId);
        formFrame.add(labelNombre);
        formFrame.add(textNombre);
        formFrame.add(labelArtista);
        formFrame.add(textArtista);
        formFrame.add(labelAnio);
        formFrame.add(textAnio);
        formFrame.add(new JLabel()); // Espacio vacío
        formFrame.add(btnEnviar);

        formFrame.setVisible(true);
    }

    // Guardar los datos en el archivo de texto
    private void guardarRegistro(String id, String nombre, String artista, String anio) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(id + "," + nombre + "," + artista + "," + anio);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Registro guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer el archivo y mostrar en el JTextArea
    private void leerArchivo() {
        textArea.setText("");  // Limpiar área de texto
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                textArea.append(linea + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Modificar un registro
    private void modificarRegistro() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del registro a modificar:");
        if (id == null || id.isEmpty()) return;

        try {
            List<String> registros = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea;
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[0].equals(id)) {
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre de la canción:", partes[1]);
                    String nuevoArtista = JOptionPane.showInputDialog("Nuevo artista:", partes[2]);
                    String nuevoAnio = JOptionPane.showInputDialog("Nuevo año de lanzamiento:", partes[3]);
                    registros.add(id + "," + nuevoNombre + "," + nuevoArtista + "," + nuevoAnio);
                    encontrado = true;
                } else {
                    registros.add(linea);
                }
            }
            reader.close();

            if (encontrado) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String reg : registros) {
                    writer.write(reg);
                    writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Registro modificado.");
            } else {
                JOptionPane.showMessageDialog(this, "Registro no encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Borrar un registro por ID
    private void borrarRegistro() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del registro a borrar:");
        if (id == null || id.isEmpty()) return;

        try {
            List<String> registros = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea;
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[0].equals(id)) {
                    encontrado = true;  // No añadir este registro, es decir, lo borramos
                } else {
                    registros.add(linea);
                }
            }
            reader.close();

            if (encontrado) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String reg : registros) {
                    writer.write(reg);
                    writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Registro borrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Registro no encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MusicApp app = new MusicApp();
                app.setVisible(true);
            }
        });
    }
}
