package Motomaticas.RecursosCustomizados;

import java.awt.Dialog;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import Motomaticas.ObjetosLogicos.motorMatematico.funciones.FuncionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.ValoresDefault.Constantes;

import java.awt.event.ActionEvent;

public class JDialog_CrearFuncionMatematica extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private UnidadMatematica FuncionMatematicaRespuesta;
    private JFrame padre;
    private HashMap<String, JPanel> categoriasContenido = new HashMap<String, JPanel>();// Contenidos
    private HashMap<String, JPanel> categoriasSeccionCompleta = new HashMap<String, JPanel>();// panel con contenido y
                                                                                              // titulos y bordes
    private GridBagConstraints gridBagLayourPanelCategorias = new GridBagConstraints();

    private void inicializarOpcionesUnidadesMatematicas() {

        for (FuncionMatematica funcionMatematica : FuncionMatematica.TotalFuncionesMatematicas) {
            JPanel FuncionMatematicaJP = new JPanel();
            FuncionMatematicaJP.setBackground(Constantes.PrincipalColor);
            FuncionMatematicaJP.setLayout(new BorderLayout());

            BotonAritmetico nuevaFuncion = new BotonAritmetico(funcionMatematica);
            nuevaFuncion.setPreferredSize(new Dimension(nuevaFuncion.getMaximumSize().width, 100));

            nuevaFuncion.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    FuncionMatematicaRespuesta = funcionMatematica.llamarFuncionMatematica(padre);
                    dispose();
                }
            });

            FuncionMatematicaJP.add(nuevaFuncion, BorderLayout.CENTER);
            FuncionMatematicaJP.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
            FuncionMatematicaJP.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
            FuncionMatematicaJP.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
            FuncionMatematicaJP.add(Box.createHorizontalStrut(5), BorderLayout.WEST);

            if (!categoriasSeccionCompleta.containsKey(funcionMatematica.getCategoriaMatematica())) {
                // Crea panel de categoria con sus borders
                JPanel nuevaCategoria = new JPanel(new BorderLayout());

                nuevaCategoria.setBackground(Constantes.PrincipalColor);

                // panel de titulo de categoria con sus borders
                JPanel TituloCategoria = new JPanel(new BorderLayout());
                TituloCategoria.setBackground(Constantes.PrincipalColor);
                JLabel JLabelTitulo = new JLabel(funcionMatematica.getCategoriaMatematica());
                JLabelTitulo.setFont(Constantes.botones);
                JLabelTitulo.setForeground(Constantes.TerciarioColor);
                TituloCategoria.add(JLabelTitulo, BorderLayout.CENTER);
                TituloCategoria.add(Box.createVerticalStrut(3), BorderLayout.SOUTH);
                TituloCategoria.add(Box.createVerticalStrut(3), BorderLayout.NORTH);
                TituloCategoria.add(Box.createHorizontalStrut(3), BorderLayout.EAST);
                TituloCategoria.add(Box.createHorizontalStrut(3), BorderLayout.WEST);

                // panel de los elementos el cual sera insertado en el hashmap

                JPanel CategoriaElementos = new JPanel(new GridLayout(0, 3));

                CategoriaElementos.setBackground(Constantes.PrincipalColor);
                categoriasContenido.put(funcionMatematica.getCategoriaMatematica(), CategoriaElementos);

                // añadir todo al panel general
                nuevaCategoria.add(TituloCategoria, BorderLayout.NORTH);
                nuevaCategoria.add(CategoriaElementos, BorderLayout.CENTER);
                nuevaCategoria.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
                categoriasSeccionCompleta.put(funcionMatematica.getCategoriaMatematica(), nuevaCategoria);
            }

            ((JPanel) categoriasContenido.get(funcionMatematica.getCategoriaMatematica()))
                    .add(FuncionMatematicaJP);

            contentPanel.removeAll();
            for (Object categoria : categoriasSeccionCompleta.values().toArray()) {
                contentPanel.add((JPanel) categoria, gridBagLayourPanelCategorias);
            }

        }

    }

    public JDialog_CrearFuncionMatematica(JFrame Padre, String Mensaje) {
        super(Padre, "Confirmar", Dialog.ModalityType.TOOLKIT_MODAL);
        this.padre = Padre;
        gridBagLayourPanelCategorias.gridx = 0;
        gridBagLayourPanelCategorias.gridy = GridBagConstraints.RELATIVE;
        gridBagLayourPanelCategorias.fill = GridBagConstraints.HORIZONTAL;
        gridBagLayourPanelCategorias.weightx = 1;
     
        setBounds(100, 100, Constantes.PantallaOrdenadorX / 3, Constantes.PantallaOrdenadorY / 3);
        setTitle("Crear Unidad Matematica");
        setResizable(false);

        contentPanel.setBackground(Constantes.PrincipalColor);
        contentPanel.setLayout(new GridBagLayout());

        JScrollPane Operaciones = new JScrollPane();
        Operaciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Operaciones.setBackground(Constantes.PrincipalColor);
        Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Operaciones.setViewportView(contentPanel);
        inicializarOpcionesUnidadesMatematicas();

        getContentPane().add(Operaciones, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public UnidadMatematica getUnidadMatematicaResultadoFuncion() {
        return FuncionMatematicaRespuesta;
    }

}
