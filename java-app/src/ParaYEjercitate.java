import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class ParaYEjercitate {
    private static class Exercise {
        String name;
        String desc;
        JCheckBox check;

        Exercise(String name, String desc) {
            this.name = name;
            this.desc = desc;
            this.check = new JCheckBox(name);
            this.check.setToolTipText(desc);
        }
    }

    private final JFrame frame = new JFrame("Para y Ejercítate");
    private final JLabel timerLabel = new JLabel("40:00", SwingConstants.CENTER);
    private final JTextField intervalField = new JTextField("40", 3);
    private final JButton saveIntervalBtn = new JButton("Guardar intervalo");
    private final JButton doneBtn = new JButton("Marcar como hecho");
    private final JButton postponeBtn = new JButton("Posponer 5 minutos");
    private final JButton addExerciseBtn = new JButton("Agregar ejercicio");
    private final JPanel exercisesPanel = new JPanel();

    private final Preferences prefs = Preferences.userNodeForPackage(ParaYEjercitate.class);

    private final List<Exercise> exercises = new ArrayList<>();
    private javax.swing.Timer timer;
    private int interval = 40; // minutes
    private int remaining = interval * 60; // seconds

    public ParaYEjercitate() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setSize(400, 500);

        timerLabel.setFont(timerLabel.getFont().deriveFont(32f));

        JPanel top = new JPanel();
        top.add(new JLabel("Intervalo (min):"));
        top.add(intervalField);
        top.add(saveIntervalBtn);

        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.add(timerLabel, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel();
        btnPanel.add(doneBtn);
        btnPanel.add(postponeBtn);
        timerPanel.add(btnPanel, BorderLayout.SOUTH);

        exercisesPanel.setLayout(new BoxLayout(exercisesPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(exercisesPanel);

        JPanel exercisesHeader = new JPanel(new BorderLayout());
        exercisesHeader.add(new JLabel("Ejercicios"), BorderLayout.WEST);
        exercisesHeader.add(addExerciseBtn, BorderLayout.EAST);

        frame.add(top, BorderLayout.NORTH);
        frame.add(timerPanel, BorderLayout.CENTER);
        frame.add(exercisesHeader, BorderLayout.SOUTH);
        frame.add(scroll, BorderLayout.EAST);
    }

    private void loadData() {
        interval = prefs.getInt("interval", 40);
        intervalField.setText(String.valueOf(interval));
        remaining = interval * 60;

        String stored = prefs.get("exercises", null);
        if (stored == null) {
            stored = String.join("\n",
                    "Estiramiento lateral de cuello|Inclinar suavemente la cabeza hacia un lado y mantener 15 segundos.",
                    "Rotación suave de cuello|Girar la cabeza de lado a lado sin forzar.",
                    "Retracción cervical|Llevar el mentón hacia atrás, mantener 5 segundos.",
                    "Movilidad de hombros|Encoger los hombros y relajarlos 10 veces.",
                    "Estiramiento de brazos|Extender los brazos hacia adelante y estirar los dedos.");
            prefs.put("exercises", stored);
        }
        for (String line : stored.split("\n")) {
            String[] parts = line.split("\\|", 2);
            String name = parts[0];
            String desc = parts.length > 1 ? parts[1] : "";
            exercises.add(new Exercise(name, desc));
        }
    }

    private void saveExercises() {
        StringBuilder sb = new StringBuilder();
        for (Exercise ex : exercises) {
            if (sb.length() > 0) sb.append('\n');
            sb.append(ex.name).append('|').append(ex.desc);
        }
        prefs.put("exercises", sb.toString());
    }

    private void renderExercises() {
        exercisesPanel.removeAll();
        for (Exercise ex : exercises) {
            exercisesPanel.add(ex.check);
            if (!ex.desc.isEmpty()) {
                JLabel descLabel = new JLabel(ex.desc);
                descLabel.setFont(descLabel.getFont().deriveFont(12f));
                exercisesPanel.add(descLabel);
            }
        }
        exercisesPanel.revalidate();
        exercisesPanel.repaint();
    }

    private void updateTimerDisplay() {
        int min = remaining / 60;
        int sec = remaining % 60;
        timerLabel.setText(String.format("%02d:%02d", min, sec));
    }

    private void restartTimer() {
        remaining = interval * 60;
        for (Exercise ex : exercises) {
            ex.check.setSelected(false);
        }
        updateTimerDisplay();
    }

    private void tick() {
        remaining -= 1;
        if (remaining <= 0) {
            notifyUser();
            restartTimer();
        }
        updateTimerDisplay();
    }

    private void notifyUser() {
        JOptionPane.showMessageDialog(frame, "¡Hora de ejercitar!", "Recordatorio", JOptionPane.INFORMATION_MESSAGE);
    }

    private void initTimer() {
        timer = new javax.swing.Timer(1000, e -> tick());
        timer.start();
    }

    private void initActions() {
        saveIntervalBtn.addActionListener(e -> {
            try {
                int val = Integer.parseInt(intervalField.getText());
                if (val > 0) {
                    interval = val;
                    prefs.putInt("interval", interval);
                    restartTimer();
                }
            } catch (NumberFormatException ex) {
                // ignore
            }
        });

        doneBtn.addActionListener(e -> restartTimer());
        postponeBtn.addActionListener(e -> {
            remaining += 300; // 5 minutes
            updateTimerDisplay();
        });

        addExerciseBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Nombre del ejercicio:");
            if (name != null && !name.isBlank()) {
                String desc = JOptionPane.showInputDialog(frame, "Descripción del ejercicio:");
                exercises.add(new Exercise(name, desc == null ? "" : desc));
                saveExercises();
                renderExercises();
            }
        });
    }

    private void createAndShow() {
        loadData();
        renderExercises();
        updateTimerDisplay();
        initTimer();
        initActions();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParaYEjercitate().createAndShow());
    }
}
