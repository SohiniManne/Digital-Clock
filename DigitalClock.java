import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {
    private JLabel timeLabel;
    private JLabel dateLabel;
    private Timer timer;
    
    public DigitalClock() {
        initializeComponents();
        setupTimer();
        setVisible(true);
    }
    
    private void initializeComponents() {
        // Set up the main frame
        setTitle("Digital Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Create gradient background
                GradientPaint gradient = new GradientPaint(0, 0, new Color(25, 25, 112), 
                                                         getWidth(), getHeight(), new Color(0, 0, 139));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Create time label
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create date label
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setForeground(Color.LIGHT_GRAY);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create a panel for labels
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setOpaque(false);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        labelPanel.add(timeLabel, BorderLayout.CENTER);
        labelPanel.add(dateLabel, BorderLayout.SOUTH);
        
        mainPanel.add(labelPanel, BorderLayout.CENTER);
        
        // Add digital clock border effect
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createLoweredBevelBorder()
        ));
        
        add(mainPanel);
    }
    
    private void setupTimer() {
        // Update time every second (1000 milliseconds)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        
        // Update time immediately when starting
        updateTime();
        
        // Start the timer
        timer.start();
    }
    
    private void updateTime() {
        Date now = new Date();
        
        // Format time (HH:mm:ss)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String timeString = timeFormat.format(now);
        timeLabel.setText(timeString);
        
        // Format date (EEEE, MMMM dd, yyyy)
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        String dateString = dateFormat.format(now);
        dateLabel.setText(dateString);
    }
    
    // Stop the timer when the window is closed
    @Override
    public void dispose() {
        if (timer != null) {
            timer.stop();
        }
        super.dispose();
    }
    
    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new DigitalClock();
        }
    });
}
}