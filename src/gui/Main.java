package gui;

import components.Vehicle;
import utilities.Utilities;

import javax.swing.*;

import components.CityBuilder;
import components.CountryBuilder;
import components.Driving;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Main extends JFrame implements ActionListener, Utilities {
   private static final long serialVersionUID = 1L;
   private RoadSystemPanel panel;
   private String[] names = {"Exit","Blue","None","Blue","Magenta","Orange", "Random","Help","City map","Country map","Clone a car","Reports"};
   private JMenu m1, m2, m3, m4, m5, m6, m7;
   private JMenuItem[] mi;
   private JMenuBar mb;
   private Driving driving = null;
   private boolean started = false;

   
   public static void main(String[]args) {
	   Main fr = new Main();
	   fr.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	   fr.setSize(845,715);
	   fr.setVisible(true);
   }

   
   public Main() {
	    super("Road system");
	    panel = new RoadSystemPanel(this);
	    add(panel);
	    panel.setVisible(true);


		mb = new JMenuBar();
		m1 = new JMenu("File");
		m2 = new JMenu("Background");
		m3 = new JMenu("Vehicles color");
		m4 = new JMenu("Help");
		m5 = new JMenu("Build a map");
//		m6 = new JMenu("Clone a car");
//		m7 = new JMenu("Reports");
		mi = new JMenuItem[names.length];

		for (int i = 0; i < names.length; i++) {
			mi[i] = new JMenuItem(names[i]);
			mi[i].addActionListener(this);
		}

		m1.add(mi[0]);

		m2.add(mi[1]);
		m2.addSeparator();
		m2.add(mi[2]);

		m3.add(mi[3]);
		m3.addSeparator();
		m3.add(mi[4]);
		m3.addSeparator();
		m3.add(mi[5]);
		m3.addSeparator();
		m3.add(mi[6]);

		m4.add(mi[7]);
		
		m5.add(mi[8]);
		m5.addSeparator();
		m5.add(mi[9]);
		
//		m5.addActionListener(this);
//
//		m6.addActionListener(this);
//
//		m7.addActionListener(this);


		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		mb.add(m5);
//		mb.add(m6);
//		mb.add(m7);
//		mb.add(mi[8]).setPreferredSize(getSize());
		mb.add(mi[10]).setPreferredSize(getSize());
		mb.add(mi[11]).setPreferredSize(getSize());

		setJMenuBar(mb);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mi[0])
			destroy();
		else if (e.getSource() == mi[1])
			panel.setBackgr(1);
		else if (e.getSource() == mi[2])
			panel.setBackgr(0);
		else if (e.getSource() == mi[3])
			panel.setColorIndex(0);
		else if (e.getSource() == mi[4])
			panel.setColorIndex(1);
		else if (e.getSource() == mi[5])
			panel.setColorIndex(2);
		else if (e.getSource() == mi[6])
			panel.setColorIndex(3);
		else if (e.getSource() == mi[7])
			printHelp();
		else if(e.getSource() == mi[8])
			cityMap();
		else if(e.getSource() == mi[9])
			countryMap();
		else if(e.getSource() == mi[10])
			CloneCar();
		else if(e.getSource() == mi[11])
			Reports();

	}

	
	
	private void countryMap() {
		if (panel.getDriving() !=null)
	    	panel.getDriving().setStop();
		panel.setDriving(new Driving(new CountryBuilder()));
		panel.getDriving().setPanel(panel); 
		panel.setStarted(false);
		repaint();
   }
		// TODO Auto-generated method stub
		
	


	private void cityMap() {
		if (panel.getDriving() !=null)
	    	panel.getDriving().setStop();
		panel.setDriving(new Driving(new CityBuilder()));
		panel.getDriving().setPanel(panel); 
		panel.setStarted(false);

		for (Vehicle veh : panel.getDriving().getVehicles()){
			veh.updateSpeed30(); //update speed 30%
		}
		repaint();
		// TODO Auto-generated method stub
		
	}



	public void destroy() {
		System.exit(0);
	}


	public void printHelp() {
		JOptionPane.showMessageDialog(this, "Home Work 3\nGUI @ Threads");
	}

	public void BuildMap() {
		//TODO: build a map button
	}

	public void CloneCar() {

		int index;
		String str=JOptionPane.showInputDialog("Input car ID to clone:","0");
		index=Integer.parseInt(str);
		if (index<0 || index>panel.getDriving().getVehicles().size()-1){ //make sure index is in boundaries
			System.out.println("Error! invalid index! cloning thr first car");
			index=0;
		}
		Vehicle vehicle = panel.getDriving().getVehicles().get(index).clone();
		panel.getDriving().addVehicle(vehicle); // adding to list of vehivles
		//fixing clone car not moving
		Thread thread = new Thread(vehicle);
		thread.start();

	}
	

	public void Reports() {
		Runtime load = Runtime.getRuntime();

		String fileName = "reports.txt";
		try {
			Process p = load.exec("notepad " + fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
