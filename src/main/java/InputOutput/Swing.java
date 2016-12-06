package InputOutput;

import Entities.Hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * (╯°□°）╯︵ ┻━┻
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 * 30.11.2016
 **/

public class Swing {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    private Hospital hospital;

    public void init(Hospital hospital){
        this.hospital = hospital;
        mainFrame = new JFrame(hospital.getName());
        mainFrame.setSize(200,100);
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(controlPanel);

        JButton staffButton = new JButton("Staff");
        JButton patientsButton = new JButton("Patients");

        staffButton.setActionCommand("Staff");
        patientsButton.setActionCommand("Submit");

        staffButton.addActionListener((ActionListener) new ButtonClickListener());
        patientsButton.addActionListener((ActionListener) new ButtonClickListener());

        controlPanel.add(staffButton);
        controlPanel.add(patientsButton);

        mainFrame.setVisible(true);
    }


    public void staff(){
        String[] columns = {"Name", "Date of Birth", "Sex", "Address", "Salary", "Date of hiring", "Post"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < hospital.getAmountOfStaff(); ++i){
            model.addRow(hospital.getStaff(i).toObject());
        }

        mainFrame = new JFrame("Staff");
        mainFrame.setSize(600,200 + hospital.getAmountOfStaff()*10);
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(controlPanel);

        JTable table = new JTable(model);

        controlPanel.add(table);

        mainFrame.setVisible(true);
    }

    public void addStaff(){
        mainFrame = new JFrame("Staff");
        mainFrame.setSize(600,200 + hospital.getAmountOfStaff()*10);
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(controlPanel);

        JTable table = new JTable(model);

        controlPanel.add(table);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if( command.equals("Staff"))  {
                staff();
            }
            else if( command.equals( "Submit" ) )  {
                statusLabel.setText("Submit Button clicked.");
            }
            else  {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
}
