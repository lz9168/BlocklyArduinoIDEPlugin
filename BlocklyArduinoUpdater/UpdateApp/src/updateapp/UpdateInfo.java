package updateapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class UpdateInfo extends JFrame{

    private JEditorPane infoPane;
    private JScrollPane scp;
    private JButton ok;
    private JButton cancel;
    private JPanel pan1;
    private JPanel pan2;

    public UpdateInfo(String info) throws Exception {
        initComponents();
        infoPane.setText(info);
    }
    
    private void initComponents() throws Exception {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        if (Integer.parseInt(Updater.getLatestVersion()) > Integer.parseInt(Updater.getLocalVersion())) {
            this.setTitle("New Update Found");
        } else {
            this.setTitle("No Update Found");
        }
        
        pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());

        pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        infoPane = new JEditorPane();
        infoPane.setContentType("text/html");

        scp = new JScrollPane();
        scp.setViewportView(infoPane);

        ok = new JButton("Update");
        ok.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        /*if (Integer.parseInt(Updater.getLatestVersion()) > Integer.parseInt(Updater.getLocalVersion())) {
            ok.setEnabled(true);
        } else {
            ok.setEnabled(false);
        }*/

        cancel = new JButton("Cancel");
        cancel.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                UpdateInfo.this.dispose();
            }
        });
        pan2.add(ok);
        pan2.add(cancel);
        pan1.add(pan2, BorderLayout.SOUTH);
        pan1.add(scp, BorderLayout.CENTER);
        this.add(pan1);
        pack();
        show();
        this.setSize(300, 200);
    }
    
    private void update() {
        String[] run = {"java","-jar","Update.jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }
}