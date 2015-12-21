import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;



public class check extends JFrame implements ActionListener{
	
	private TextField tfWifiPassword;
	private JButton btn;  
	private ButtonGroup group = new ButtonGroup();
	private Label password;
	
	public static void main(String[] args) throws IOException {
		check app = new check();
		app.checking();
}
	
		
	public void checking() throws IOException{
		String details = "";
		String wifiname = "";
		HashMap<String,String> hmap = new HashMap<String,String>();
		BufferedReader  bf= new BufferedReader(new FileReader("record"));
		while((details = bf.readLine())!=null){
			String[] wifi = details.split(",");
			hmap.put(wifi[0],wifi[1]);
		}
		bf.close();
		
		
		BufferedReader  bf1 = new BufferedReader(new FileReader("data"));
		while((wifiname = bf1.readLine())!=null){
			System.out.println(wifiname);
	      	wifiname = wifiname.substring(26,wifiname.length());
	      	wifiname = wifiname.replace("\"","");
	      	System.out.println("2ns wifi "+ wifiname);
			if(hmap.get(wifiname)!=null){
				FileWriter fw = new FileWriter(new File("data1"));
				fw.write(wifiname+"\n");
				fw.write(hmap.get(wifiname)+"\n");
				fw.close();
				return;
			}
				
		}
		this.setupGUI();
		bf1.close();
	}
	   

	   public void setupGUI() throws IOException{
		   getContentPane().setLayout(null);
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		   int count =0;
		   int  y=0;
		   String line = "";
		   BufferedReader br = new BufferedReader(new FileReader("data"));
		   while((line = br.readLine())!=null && count <13)
		   {
			      
	      	String name = line;
	      	name = name.substring(26,name.length());
	      	name = name.replace("\"","");
	      	JRadioButton wifiName = new JRadioButton(name,false);
	      	wifiName.setLocation(0,y);
	      	wifiName.setSize(200,30);
	      	group.add(wifiName);
	      	getContentPane().add(wifiName);
	      	count++;
	      	y+=32;
	      }  
		  br.close();
		  
	      password = new Label("Wi-fi Password");
	      password.setLocation(0,436);
	      password.setSize(107,30);
	      getContentPane().add(password);
	      
	      tfWifiPassword = new TextField("",20);
	      tfWifiPassword.setEditable(true);
	      tfWifiPassword.setLocation(0,468);
	      tfWifiPassword.setSize(200,30);
	      getContentPane().add(tfWifiPassword);
	      
	      btn = new JButton("Enter");
	      btn.setLocation(0,500);
	      btn.setSize(107,30);
	      getContentPane().add(btn);             
	      
	      btn.addActionListener(this);
	      setTitle("Wi-fi Networks ( JFrame )");
	      setSize(250, 600);
	      setResizable(true);
	      setVisible(true);  		   
	      
	      getRootPane().setDefaultButton(btn);
	   }
	
	
	@Override
	   public void actionPerformed(ActionEvent event)  
	   { 
	      if(event.getSource()== btn)  
	      {  
	         Enumeration<AbstractButton> allRadioButton=group.getElements();  
	         while(allRadioButton.hasMoreElements())  
	         {  
	            JRadioButton temp=(JRadioButton)allRadioButton.nextElement();  
	            if(temp.isSelected())  
	            {  
	            	try {
	            		FileWriter fw = new FileWriter(new File("data1"));
	    				fw.write(temp.getText()+"\n");
	    				fw.write(tfWifiPassword.getText()+"\n");
	    				fw.close();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    			setVisible(false);
	    			dispose();
	            }  
	         }            
	      }
	   }
}
