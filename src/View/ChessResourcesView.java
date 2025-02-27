/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;
import ModelView.ChessResource;
import ModelView.ChessResourcesController;
import Model.ChessResourceModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ChessResourcesView extends JFrame {

    private JPanel openingPanel;
    private JPanel middlegamePanel;
    private JPanel endgamePanel;

    public ChessResourcesView() {
        setTitle("Recursos de Ajedrez");
        setSize(900, 700);
        getContentPane().setBackground(new Color(213, 249, 222));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navegación");
        menu.setForeground(new Color(10, 10, 10));
        JMenuItem exercisesItem = new JMenuItem("Ejercicios");
        exercisesItem.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(()->new ExercisesView()); // Asegúrate de tener esta clase
        });

        JMenuItem resourcesItem = new JMenuItem("Recursos");
        resourcesItem.addActionListener(e -> {
           
        });

        menu.add(exercisesItem);
        menu.add(resourcesItem);
        menuBar.add(menu);
        menuBar.setBackground(new Color(162, 210, 187));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(182, 141, 106))); // Solo línea inferior negra de 2px
        setJMenuBar(menuBar);

        // Título general
        JLabel mainTitle = new JLabel("Recursos de Ajedrez: Apertura, Medio Juego y Final", JLabel.CENTER);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 18));
        mainTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(mainTitle, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(213, 249, 222));
        contentPanel.setLayout(new GridLayout(3, 1, 0, 10));

        openingPanel = createSection("Apertura", new String[]{});
        middlegamePanel = createSection("Medio Juego", new String[]{});
        endgamePanel = createSection("Final", new String[]{});

        openingPanel.setBackground(new Color(213, 249, 222));
        middlegamePanel.setBackground(new Color(213, 249, 222));
        endgamePanel.setBackground(new Color(213, 249, 222));
        contentPanel.add(openingPanel);
        contentPanel.add(middlegamePanel);
        contentPanel.add(endgamePanel);

        add(contentPanel, BorderLayout.CENTER);

        new ChessResourcesController(this, new ChessResourceModel());
        setVisible(true);
    }

    private JPanel createSection(String title, String[] resources) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel sectionTitle = new JLabel(title, JLabel.CENTER);
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(sectionTitle, BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String resource : resources) {
            model.addElement(resource);
        }

        JList<String> resourceList = new JList<>(model);
        resourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resourceList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = resourceList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selected = resourceList.getModel().getElementAt(index);
                        if (selected.startsWith("http")) {
                            try {
                                Desktop.getDesktop().browse(new java.net.URI(selected));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        panel.add(new JScrollPane(resourceList), BorderLayout.CENTER);
        return panel;
    }

    public void updateSection(String section, List<ChessResource> resources) {
        JPanel panel = switch (section) {
            case "Apertura" -> openingPanel;
            case "Medio Juego" -> middlegamePanel;
            case "Final" -> endgamePanel;
            default -> throw new IllegalArgumentException("Sección no válida: " + section);
        };

        DefaultListModel<String> model = new DefaultListModel<>();
        for (ChessResource resource : resources) {
            model.addElement("<html><b>" + resource.getTitle() + "</b> - <a style='color:blue;'>" + resource.getLink() + "</a></html>");
        }

        JList<String> resourceList = new JList<>(model);
        resourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resourceList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = resourceList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selected = resourceList.getModel().getElementAt(index);
                        String url = selected.substring(selected.indexOf("http"), selected.lastIndexOf("</a>"));
                        try {
                            Desktop.getDesktop().browse(new java.net.URI(url));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        panel.removeAll();
        panel.add(new JLabel(section, JLabel.CENTER), BorderLayout.NORTH);
        panel.add(new JScrollPane(resourceList), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

}
