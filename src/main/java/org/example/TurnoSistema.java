package org.example;// TurnoSistema.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

public class TurnoSistema {
    private PriorityQueue<Usuario> colaDeTurnos;
    private JTextArea areaTurnos;

    public TurnoSistema() {
        colaDeTurnos = new PriorityQueue<>(new ComparadorUsuario());

        // Configuración de la ventana principal
        JFrame frame = new JFrame("Sistema de Turnos con Prioridad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Panel para agregar usuarios
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel nombreLabel = new JLabel("Nombre del usuario:");
        JTextField nombreField = new JTextField();
        JLabel prioridadLabel = new JLabel("Prioridad (0-10):");
        JTextField prioridadField = new JTextField();

        JButton agregarUsuarioBtn = new JButton("Agregar Usuario");
        agregarUsuarioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int prioridad = Integer.parseInt(prioridadField.getText());

                Usuario usuario = new Usuario(nombre, prioridad);
                colaDeTurnos.add(usuario);
                actualizarTurnos();
            }
        });

        JButton siguienteTurnoBtn = new JButton("Atender Siguiente Turno");
        siguienteTurnoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!colaDeTurnos.isEmpty()) {
                    Usuario siguiente = colaDeTurnos.poll();
                    JOptionPane.showMessageDialog(frame, "Atendiendo a: " + siguiente);
                    actualizarTurnos();
                } else {
                    JOptionPane.showMessageDialog(frame, "No hay más usuarios en la cola.");
                }
            }
        });

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(prioridadLabel);
        panel.add(prioridadField);
        panel.add(agregarUsuarioBtn);
        panel.add(siguienteTurnoBtn);

        areaTurnos = new JTextArea(10, 30);
        areaTurnos.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTurnos);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    // Método para actualizar el área de turnos con los usuarios en cola
    private void actualizarTurnos() {
        StringBuilder builder = new StringBuilder();
        for (Usuario usuario : colaDeTurnos) {
            builder.append(usuario).append("\n");
        }
        areaTurnos.setText(builder.toString());
    }
}
