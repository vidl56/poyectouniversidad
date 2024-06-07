/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package empleadogui;

/**
 *
 * @author ricardo vidal
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpleadoGUI extends JFrame {
    private JTextField nombreField, apellidoField, cedulaField, hijosField, horasField, fechaAltaField, fechaBajaField, antiguedadField;
    private JRadioButton empleadoHoraButton, empleadoTemporalButton, empleadoPlantaButton;
    private JButton guardarButton, limpiarButton;
    private JTable empleadoTable;
    private DefaultTableModel tableModel;

    public EmpleadoGUI() {
        setTitle("Gestión de Empleados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        nombreField = new JTextField();
        apellidoField = new JTextField();
        cedulaField = new JTextField();
        hijosField = new JTextField();
        horasField = new JTextField();
        fechaAltaField = new JTextField();
        fechaBajaField = new JTextField();
        antiguedadField = new JTextField();

        empleadoHoraButton = new JRadioButton("Empleado por hora");
        empleadoTemporalButton = new JRadioButton("Empleado temporal");
        empleadoPlantaButton = new JRadioButton("Empleado de planta permanente");

        ButtonGroup tipoEmpleadoGroup = new ButtonGroup();
        tipoEmpleadoGroup.add(empleadoHoraButton);
        tipoEmpleadoGroup.add(empleadoTemporalButton);
        tipoEmpleadoGroup.add(empleadoPlantaButton);

        guardarButton = new JButton("Guardar");
        limpiarButton = new JButton("Limpiar");

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "¿Desea guardar la información?", "Guardar", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    guardarEmpleado();
                    limpiarCampos();
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea limpiar todos los parámetros?", "Limpiar", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    limpiarCampos();
                }
            }
        });

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Apellido:"));
        panel.add(apellidoField);
        panel.add(new JLabel("Cédula:"));
        panel.add(cedulaField);
        panel.add(new JLabel("Cantidad de hijos:"));
        panel.add(hijosField);
        panel.add(new JLabel("Horas trabajadas:"));
        panel.add(horasField);
        panel.add(new JLabel("Fecha de alta:"));
        panel.add(fechaAltaField);
        panel.add(new JLabel("Fecha de baja:"));
        panel.add(fechaBajaField);
        panel.add(new JLabel("Antigüedad (años):"));
        panel.add(antiguedadField);
        panel.add(empleadoHoraButton);
        panel.add(empleadoTemporalButton);
        panel.add(empleadoPlantaButton);
        panel.add(guardarButton);
        panel.add(limpiarButton);

        add(panel, BorderLayout.NORTH);

        // Tabla de empleados
        tableModel = new DefaultTableModel();
        empleadoTable = new JTable(tableModel);
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("Cédula");
        tableModel.addColumn("Tipo Empleado");
        tableModel.addColumn("Sueldo");

        JScrollPane scrollPane = new JScrollPane(empleadoTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        cedulaField.setText("");
        hijosField.setText("");
        horasField.setText("");
        fechaAltaField.setText("");
        fechaBajaField.setText("");
        antiguedadField.setText("");
        empleadoHoraButton.setSelected(false);
        empleadoTemporalButton.setSelected(false);
        empleadoPlantaButton.setSelected(false);
    }

    private void guardarEmpleado() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String cedula = cedulaField.getText();
        int hijos = Integer.parseInt(hijosField.getText());
        String tipoEmpleado = "";
        String sueldo = "";

        if (empleadoHoraButton.isSelected()) {
            tipoEmpleado = "Empleado por hora";
            double horasTrabajadas = Double.parseDouble(horasField.getText());
            double sueldoPorHora = 100; // BsS
            double sueldoTotal = horasTrabajadas * sueldoPorHora;
            sueldo = String.valueOf(sueldoTotal);
        } else if (empleadoTemporalButton.isSelected()) {
            tipoEmpleado = "Empleado temporal";
            int sueldoBasico = 18000; // BsS
            int adicionalPorHijo = 1000; // BsS
            int sueldoTotal = sueldoBasico + (hijos * adicionalPorHijo);
            sueldo = String.valueOf(sueldoTotal);
        } else if (empleadoPlantaButton.isSelected()) {
            tipoEmpleado = "Empleado de planta permanente";
            int sueldoBasico = 20000; // BsS
            int adicionalPorHijo = 1000; // BsS
            int complementoAntiguedad = 1000; // BsS por año
            int antiguedad = Integer.parseInt(antiguedadField.getText());
            int sueldoTotal = sueldoBasico + (hijos * adicionalPorHijo) + (complementoAntiguedad * antiguedad);
            sueldo = String.valueOf(sueldoTotal);
        }

        Object[] rowData = {nombre, apellido, cedula, tipoEmpleado, sueldo};
        tableModel.addRow(rowData);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmpleadoGUI();
            }
        });
    }
}
