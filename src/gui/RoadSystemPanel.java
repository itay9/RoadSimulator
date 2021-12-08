package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

import components.Map;
import utilities.Timer;
import components.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class RoadSystemPanel extends JPanel implements ActionListener {
   private static final long serialVersionUID = 1L;
   private Main frame;
   private JPanel p1;
   private JButton[] b_num;
   private String[] names = {"Create road system","Start","Stop","Resume","Info"};
   private JScrollPane scrollPane;
   private boolean isTableVisible;
   private Driving driving = null;
   private int colorInd = 0;
   private boolean started = false;
	
   
   public RoadSystemPanel(Main f) {
	    frame = f;
	    isTableVisible = false;
	    setBackground(new Color(255,255,255));
	    p1=new JPanel();
		p1.setLayout(new GridLayout(1,7,0,0));
		p1.setBackground(new Color(0,150,255));
		b_num=new JButton[names.length];
		
		for(int i=0;i<names.length;i++) {
		    b_num[i]=new JButton(names[i]);
		    b_num[i].addActionListener(this);
		    b_num[i].setBackground(Color.lightGray);
		    p1.add(b_num[i]);		
		}

		setLayout(new BorderLayout());
		add("South", p1);
   }	
   
   
   public void createNewRoadSystem(int junc, int cars) {
	    if (getDriving() !=null)
	    	getDriving().setStop();
		setDriving(new Driving(junc,cars));
		getDriving().setPanel(this); 
		setStarted(false);
		repaint();
   }
   

   public void paintComponent(Graphics g) {
	   	super.paintComponent(g);	
	   	
	   	if (getDriving()==null) return;
	   	int delta = 10;
	   	
	   	
//	   	Map map = getDriving().getMap();
//	   	if (map!=null) {	   	
//		   	for(Road road: map.getRoads()) 
//		   		road.drawRoads(g,delta);
//		   	
//		   	for(Road road: map.getRoads()) 
//		   		road.drawRoadGreenLight(g,delta);
//		   	
//		   	for(Junction junc: map.getJunctions())
//		   		junc.drawJunction(g,delta);
//		   	
//		   	for(Vehicle veh: getDriving().getVehicles()) 
//		   		veh.drawVehicle(g, delta, colorInd);
//	   	}
	   	if (driving.getCountryBuilder()!=null) {
		   	for(Road road: driving.getCountryBuilder().getRoads()) 
		   		road.drawRoads(g,delta);
		   	
		   	for(Road road: driving.getCountryBuilder().getRoads()) 
		   		road.drawRoadGreenLight(g,delta);
		   	
		   	for(Junction junc: driving.getCountryBuilder().getJunctions())
		   		junc.drawJunction(g,delta);
		   	
		   	for(Vehicle veh: getDriving().getVehicles()) 
		   		veh.drawVehicle(g, delta, colorInd);
	   	}
	   	
	   	if (driving.getCityBuilder()!=null) {
	   	   	for(Road road: driving.getCityBuilder().getRoads()) 
		   		road.drawRoads(g,delta);
		   	
		   	for(Road road: driving.getCityBuilder().getRoads()) 
		   		road.drawRoadGreenLight(g,delta);
		   	
		   	for(Junction junc: driving.getCityBuilder().getJunctions())
		   		junc.drawJunction(g,delta);
		   	
		   	for(Vehicle veh: getDriving().getVehicles()) 
		   		veh.drawVehicle(g, delta, colorInd);
	   	}
   }
   
   
   
   public void setColorIndex(int ind) {
	   this.colorInd = ind;
	   repaint();
   }
   
   
   public void setBackgr(int num) {
	   switch(num) {
	   case 0:
		   setBackground(new Color(255,255,255));
		   break;
	   case 1:
		   setBackground(new Color(0,150,255));
		   break;

	   }
	   repaint();
   }
   
   
   
   public void add(){
	   CreateRoadSystemlDialog dial = new CreateRoadSystemlDialog(frame,this,"Create road system");
	   dial.setVisible(true);
   }
   

   public void start() {
	   if (getDriving() == null || isStarted()) return;
	   setStarted(true);
	   Thread t = new Thread(getDriving());
	   t.start(); 
   }
   
	public void resume() {
		if (getDriving() == null) return;
		getDriving().setResume();
   }

 	public void stop() {
 		if (getDriving() == null) return;
	   getDriving().setSuspend();
   }

   
   public void info() { 
	   if (getDriving() == null) return;
	   if(isTableVisible == false) {
		 int i=0;
		  String[] columnNames = {"Vehicle #", "Type", "Location","Time on loc","Speed"};
		  ArrayList<Vehicle> vehicles = getDriving().getVehicles();
	      String [][] data = new String[vehicles.size()][columnNames.length];
	      for(Vehicle v : vehicles) {
	    	  data[i][0] = ""+v.getID();
	    	  data[i][1] = ""+v.getVehicleType();
	    	  RouteParts rp = v.getCurrentRoutePart();
	    	  if (rp instanceof Road) {
	    		  Road r = (Road) rp;
	    		  data[i][2] = "Road "+r.getStartJunction().getJunctionName()+"-"+r.getEndJunction().getJunctionName();
	    		  data[i][4] = ""+Math.min(r.getMaxSpeed(), v.getVehicleType().getAverageSpeed());
	    	  }
	    	  else {
	    		  Junction j = (Junction) rp;
	    		  data[i][2] = "Junction "+j.getJunctionName();
	    		  data[i][4] = "0";
	    	  }
	    	  
	    	  data[i][3] = ""+v.getTimeOnCurrentPart();
	    	  i++;
	      }
	      
	      JTable table = new JTable(data, columnNames);
	      scrollPane = new JScrollPane(table);
	      scrollPane.setSize(450,table.getRowHeight()*(vehicles.size())+24);
	      add( scrollPane, BorderLayout.CENTER );
	      isTableVisible = true;
	   }
	   else
		   isTableVisible = false;
	   
	   scrollPane.setVisible(isTableVisible);
       repaint();
   }

   
   public void destroy(){  	        
      System.exit(0);
   }
   
   
   public void actionPerformed(ActionEvent e) {
	if(e.getSource() == b_num[0]) 
		JOptionPane.showMessageDialog(this, "This option not available!");
//		add();
		
	else if(e.getSource() == b_num[1]) 
		start();
	else if(e.getSource() == b_num[2])  
		stop();
	else if(e.getSource() == b_num[3])  
		resume(); 
	else if(e.getSource() == b_num[4])  
		info();
   }


	public Driving getDriving() {
		return driving;
	}
	
	
	public void setDriving(Driving driving) {
		this.driving = driving;
	}
	
	
	public boolean isStarted() {
		return started;
	}
	
	
	public void setStarted(boolean started) {
		this.started = started;
	}


}