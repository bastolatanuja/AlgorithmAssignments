package week9AndFollowing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.xml.transform.Source;

import week9AndFollowing.Algo.Location;

import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {
	
	//public Image menuImg = new ImageIcon(HomePage.class.getResource("Img/menu.jpg")).getImage().getScaledInstance(90,90,20);

	private JPanel contentPane;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JTextField textFieldDistance;
	private JButton btnNewButton_1;
	private JPanel panelAdd;
	private JPanel panelAll;
	private JPanel panelShortest;
	private JPanel panel_1;
	private	JPanel panel;
	private JComboBox comboBox_3;
	public  static String[] allFrom;
	public  static String[] allTo;
	public static String[] allPosiblePaths;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	public String  distance="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//delete from csv
	public void delete(String place,String from) throws IOException {
		try {
			int flag=0;
			
			ArrayList<Location> list = getAllPlaces();
			ArrayList<Location> newList = new ArrayList<Location>();
			ListIterator<Location> litr = list.listIterator();
			while(litr.hasNext()) {
				Location temp = litr.next();
				if( ( (temp.from.equals(from)&&temp.place.equals(place))  || (temp.from.equals(place)&&temp.place.equals(from))   ) ) {
					flag=1;
				}
				else if(!  ( (temp.from.equals(from)&&temp.place.equals(place))  || (temp.from.equals(place)&&temp.place.equals(from))   )  ) {
					newList.add(temp);
				}
				
			}
			if(flag==0) {
				JOptionPane.showMessageDialog(contentPane, "No such link between selected places");
				throw new IOException();
			}
			else {
				
				try {	
					FileWriter fstream = new FileWriter("C:\\Users\\HP\\eclipse-workspace\\algorithmAssignment\\src\\week9AndFollowing\\location_details.csv");
					BufferedWriter out = new BufferedWriter(fstream);
					
					out.write("");
					out.close();
				}catch (FileNotFoundException e) {
				      System.out.println(e.getMessage());
			    }
				
				ListIterator<Location> itr = newList.listIterator();
				while(itr.hasNext()) {
					itr.next();
					Location temp = itr.next();
					insert(temp.from, ""+temp.distance, temp.place);
				}
				
			}
		}catch (Exception e) {
			throw new IOException("Not deleted");
		}
		
	}
	
	//insert into csv
	public void insert(String place,String distance,String from) throws IOException {
		
		try {		
						
			 
			StringBuilder sb1 = new StringBuilder();
		      sb1.append(place);
		      sb1.append(",");
		      sb1.append(distance);
		      sb1.append(",");
		      sb1.append(from);
		    StringBuilder sb2 = new StringBuilder();
		      sb2.append(from);
		      sb2.append(",");
		      sb2.append(distance);
		      sb2.append(",");
		      sb2.append(place);
		      
	 
			FileWriter fstream = new FileWriter("C:\\Users\\HP\\eclipse-workspace\\algorithmAssignment\\src\\week9AndFollowing\\location_details.csv", true);
			BufferedWriter out = new BufferedWriter(fstream);
	 
			
			
			out.write(sb1.toString());
			out.newLine();
			out.write(sb2.toString());
			out.newLine();
	 
			//close buffer writer
			out.close();
		     
		    } catch (FileNotFoundException e) {
		    	JOptionPane.showMessageDialog(contentPane, "Error Occured while inserting : " + e);
		    }
	}
	//to string array
		public static String[] toStringArray(ArrayList<String> list) {
			String [] stringArray = new String[list.size()];
			for(int i=0;i<list.size();i++) {
				stringArray[i] = list.get(i);
				
			}
			return stringArray;
		}
	//check function
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	//driver code for algorithm
	public static ArrayList<Location> getAllPlaces() throws IOException {
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		File csvFile = new File("C:\\Users\\HP\\eclipse-workspace\\algorithmAssignment\\src\\week9AndFollowing\\location_details.csv");
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String line = "";
		try {
		
			while((line = br.readLine())!=null) {
				String[] eachCellData = line.split(",");
				Location somewhere = new Location(eachCellData[0],Integer.parseInt(eachCellData[1]), eachCellData[2]);
				locationList.add(somewhere);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(contentPane,"Deleted Route");
		}
		
		
		return locationList;
	
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 490);
		
		
		//*****
		ArrayList<String> tempStoragefrom = new ArrayList<String>();
		ArrayList<String> tempStorageto = new ArrayList<String>();
		
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		try {
			locationList= getAllPlaces();}
		catch (Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(contentPane, "Cannot Get Routes :" + e);
		}
		ListIterator<Location> litr= locationList.listIterator();
		Location temp;
		while(litr.hasNext()){
	    	   temp = litr.next();
	    	  if(!tempStoragefrom.contains(temp.from)) {
	    		  tempStoragefrom.add(temp.from);
	    	  }
	    	  if(!tempStorageto.contains(temp.place)) {
	    		  tempStorageto.add(temp.place);
	    	  }	  
		
		}
		String[] allfrom = new String[tempStoragefrom.size()];
		for(int i=0;i<tempStoragefrom.size();i++) {
			allfrom[i] = tempStoragefrom.get(i);
		}
		
		String[] allto = new String[tempStoragefrom.size()];
		for(int i=0;i<tempStorageto.size();i++) {
			allto[i] = tempStorageto.get(i);
		}
		allPosiblePaths = new String[tempStoragefrom.size()];
		
		allFrom = allfrom;
		allTo = allto;
		//****
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setFont(new Font("Cambria Math", panelMenu.getFont().getStyle() | Font.BOLD | Font.ITALIC, 19));
		panelMenu.setForeground(new Color(240, 255, 240));
		panelMenu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelMenu.setBackground(new Color(0, 128, 128));
		panelMenu.setBounds(0, 0, 280, 490);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIconTextGap(0);
		lblNewLabel.setBounds(0, 0, 280, 199);
		panelMenu.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\menuIcon 1.png"));
		
		JLabel lblNewLabel_1 = new JLabel("Menu");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(57, 157, 150, 54);
		panelMenu.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("Add Route");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelShortest.setVisible(false);
				panelAdd.setVisible(true);
				comboBox_3.setVisible(false);
				panelAll.setVisible(false);
				panel_1.setVisible(false);
				panel.setVisible(false);
				
				try {
				ArrayList<Location>	allPlaces = getAllPlaces();
				}
				catch (Exception x) {
					JOptionPane.showMessageDialog(contentPane, "Cannot Find CSV file");
				}
			}
		});
		btnNewButton_1.setBounds(10, 236, 260, 40);
		panelMenu.add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(240, 255, 240));
		btnNewButton_1.setFont(new Font("Cambria Math", btnNewButton_1.getFont().getStyle() | Font.BOLD | Font.ITALIC, 19));
		btnNewButton_1.setBackground(new Color(0, 128, 128));
		
		JButton btnNewButton_1_1 = new JButton("All Routes");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelShortest.setVisible(false);
				panelAdd.setVisible(false);
				panelAll.setVisible(true);
				comboBox_3.setVisible(false);
				panel_1.setVisible(false);
				panel.setVisible(false);
				
				

				comboBox_3.removeAllItems();
			}
		});
		btnNewButton_1_1.setFont(new Font("Cambria Math", btnNewButton_1_1.getFont().getStyle() | Font.BOLD | Font.ITALIC, 19));
		btnNewButton_1_1.setForeground(new Color(240, 255, 240));
		btnNewButton_1_1.setBackground(new Color(0, 128, 128));
		btnNewButton_1_1.setBounds(10, 388, 260, 40);
		panelMenu.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Shortest Route");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelShortest.setVisible(true);
				panelAdd.setVisible(false);
				panelAll.setVisible(false);
				comboBox_3.setVisible(false);
				panel_1.setVisible(false);
				panel.setVisible(false);


				comboBox_3.removeAllItems();
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Cambria Math", btnNewButton_1_1_1.getFont().getStyle() | Font.BOLD | Font.ITALIC, 19));
		btnNewButton_1_1_1.setForeground(new Color(240, 255, 240));
		btnNewButton_1_1_1.setBackground(new Color(0, 128, 128));
		btnNewButton_1_1_1.setBounds(10, 439, 260, 40);
		panelMenu.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Delete Routes");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelShortest.setVisible(false);
				panelAdd.setVisible(false);
				panelAll.setVisible(false);
				comboBox_3.setVisible(false);
				panel_1.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnNewButton_1_1_1_1.setFont(new Font("Cambria Math", btnNewButton_1_1_1_1.getFont().getStyle() | Font.BOLD | Font.ITALIC, 19));
		btnNewButton_1_1_1_1.setForeground(new Color(240, 255, 240));
		btnNewButton_1_1_1_1.setBackground(new Color(0, 128, 128));
		btnNewButton_1_1_1_1.setBounds(10, 338, 260, 40);
		panelMenu.add(btnNewButton_1_1_1_1);
		
		panel = new JPanel();
		panel.setVisible(false);
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(300, 233, 552, 143);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox_6 = new JComboBox(allFrom);
		comboBox_6.setBounds(10, 11, 150, 22);
		panel.add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox(allTo);
		comboBox_7.setBounds(392, 11, 150, 22);
		panel.add(comboBox_7);
		
		textField = new JTextField();
		textField.setBounds(10, 44, 150, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(392, 44, 150, 27);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(230, 51, 96, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		 
		JButton btnNewButton_1_1_1_1_1 = new JButton("Edit Routes");
		btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelShortest.setVisible(false);
				panelAdd.setVisible(false);
				panelAll.setVisible(false);
				comboBox_3.setVisible(false);
				panel_1.setVisible(false);
				panel.setVisible(true);
				
			}
		});
		btnNewButton_1_1_1_1_1.setFont(new Font("Cambria Math", btnNewButton_1_1_1_1_1.getFont().getStyle() | Font.BOLD | Font.ITALIC, 19));
		btnNewButton_1_1_1_1_1.setForeground(new Color(240, 255, 240));
		btnNewButton_1_1_1_1_1.setBackground(new Color(0, 128, 128));
		btnNewButton_1_1_1_1_1.setBounds(10, 287, 260, 40);
		panelMenu.add(btnNewButton_1_1_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\menuIcon 2.png"));
		lblNewLabel_3.setBounds(290, 0, 178, 230);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\menuIcon 3.png"));
		lblNewLabel_5.setBounds(639, 11, 178, 200);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\menuIcon 4.png"));
		lblNewLabel_6.setBounds(478, 56, 220, 143);
		contentPane.add(lblNewLabel_6);
		
		panelAdd = new JPanel();
		panelAdd.setVisible(false);
		panelAdd.setBackground(new Color(0, 128, 128));
		panelAdd.setBounds(300, 233, 552, 92);
		contentPane.add(panelAdd);
		panelAdd.setLayout(null);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(10, 11, 150, 28);
		panelAdd.add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		textFieldTo = new JTextField();
		textFieldTo.setColumns(10);
		textFieldTo.setBounds(392, 11, 150, 28);
		panelAdd.add(textFieldTo);
		
		textFieldDistance = new JTextField();
		textFieldDistance.setBounds(228, 15, 96, 20);
		panelAdd.add(textFieldDistance);
		textFieldDistance.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean clear = true;
				String fromPlace = textFieldFrom.getText().toLowerCase();
				String toPlace = textFieldTo.getText().toLowerCase();
				String distanceBetween = textFieldDistance.getText();
				//System.out.println(fromPlace+"---->"+toPlace);
				ArrayList<Location> allPlaces;
				try {
				allPlaces = getAllPlaces();
				ListIterator<Location> litr = allPlaces.listIterator();
				while(litr.hasNext()) {
					
					Location temp = litr.next();
					
					if((temp.from.equals(fromPlace)&&(temp.place.equals(toPlace))) || (temp.place.equals(fromPlace)&&(temp.from.equals(toPlace)))) {
						JOptionPane.showMessageDialog(contentPane, "Route Already Exists");
						
						clear = false;
						break;
					}
				}
				if(clear) {
					if(!isNumeric(distanceBetween)) {
						JOptionPane.showMessageDialog(contentPane, "Enter distance in numbers between 0 to "+Integer.MAX_VALUE+" not in decimals.");
					}
					else {
						insert(toPlace, distanceBetween, fromPlace);
						JOptionPane.showMessageDialog(contentPane, "New route SUCCESSFULLY added");
					}
				}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, "CSV file Empty.");
					
				}
				new HomePage().setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(10, 50, 532, 32);
		panelAdd.add(btnNewButton);
		
		JLabel lblNewLabel_4_2 = new JLabel("Distance");
		lblNewLabel_4_2.setForeground(new Color(176, 224, 230));
		lblNewLabel_4_2.setBounds(255, -13, 83, 44);
		panelAdd.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4 = new JLabel("From");
		lblNewLabel_4.setBounds(353, 198, 83, 44);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setFont(lblNewLabel_4.getFont().deriveFont(lblNewLabel_4.getFont().getStyle() | Font.BOLD | Font.ITALIC, 20f));
		lblNewLabel_4.setForeground(new Color(176, 224, 230));
		
		JLabel lblNewLabel_4_1 = new JLabel("To");
		lblNewLabel_4_1.setBounds(751, 198, 83, 44);
		contentPane.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setFont(new Font("Cambria Math", lblNewLabel_4_1.getFont().getStyle() | Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_4_1.setForeground(new Color(176, 224, 230));
		
		panelShortest = new JPanel();
		panelShortest.setBounds(300, 233, 552, 92);
		contentPane.add(panelShortest);
		panelShortest.setVisible(false);
		panelShortest.setLayout(null);
		panelShortest.setBackground(new Color(0, 128, 128));
		
		JComboBox comboBox_2 = new JComboBox(allFrom);
		comboBox_2.setBounds(10, 11, 148, 22);
		panelShortest.add(comboBox_2);
		
		JComboBox comboBox_1_1 = new JComboBox(allTo);
		comboBox_1_1.setBounds(394, 11, 148, 22);
		panelShortest.add(comboBox_1_1);
		
		JButton btnFindAllPaths_1 = new JButton("Find Shortest Paths");
		btnFindAllPaths_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox_2.getSelectedItem()==null || comboBox_1_1.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(contentPane, "Nothing selected to find ");
				}
				else {
					comboBox_3.removeAllItems();
					
					String source = comboBox_2.getSelectedItem().toString();
					String destination = comboBox_1_1.getSelectedItem().toString();
					
					ArrayList<Location> locationListLocal = new ArrayList<Location>();
					try {
						locationListLocal= getAllPlaces();}
					catch (Exception s) {
						JOptionPane.showMessageDialog(contentPane, "Cannot get routes :" +s);
					}
					String res = Algo.dijikstra(locationListLocal, source, destination);
					comboBox_3.addItem(res);
				}
				
				

				comboBox_3.setVisible(true);
			}
		});
		btnFindAllPaths_1.setBounds(10, 44, 532, 32);
		panelShortest.add(btnFindAllPaths_1);
		
		panelAll = new JPanel();
		panelAll.setBounds(300, 233, 552, 92);
		contentPane.add(panelAll);
		panelAll.setVisible(false);
		panelAll.setBackground(new Color(0, 128, 128));
		panelAll.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(allFrom));
		comboBox.setBounds(10, 11, 148, 22);
		panelAll.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(allTo));
		comboBox_1.setBounds(394, 11, 148, 22);
		panelAll.add(comboBox_1);
		
		JButton btnFindAllPaths = new JButton("Find All Paths");
		btnFindAllPaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem()==null || comboBox_1.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(contentPane, "Nothing selected to find ");
				}
				else {
					comboBox_3.removeAllItems();
					String source = comboBox.getSelectedItem().toString();
					String destination = comboBox_1.getSelectedItem().toString();
					
					ArrayList<Location> locationListLocal = new ArrayList<Location>();
					try {
						locationListLocal= getAllPlaces();}
					catch (Exception s) {
						JOptionPane.showMessageDialog(contentPane, "Cannot get routes :" +s);
					}
					ArrayList<String> allPaths = Algo.bfs(locationListLocal,source,destination);
					allPosiblePaths = toStringArray(allPaths);
					if(allPosiblePaths.length==0) {
						comboBox_3.addItem("No routes between selected places are available");
					}else {
						for(int i=0;i<allPosiblePaths.length;i++) {
							comboBox_3.addItem(allPosiblePaths[i]);
						}
					}
					
				}
				
				

				comboBox_3.setVisible(true);
			}
		});
		btnFindAllPaths.setBounds(10, 44, 532, 32);
		panelAll.add(btnFindAllPaths);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setVisible(false);
		comboBox_3.setBounds(300, 353, 552, 35);
		contentPane.add(comboBox_3);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(300, 233, 552, 92);
		contentPane.add(panel_1);
		
		JComboBox comboBox_4 = new JComboBox(allFrom);
		comboBox_4.setBounds(10, 11, 148, 22);
		panel_1.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox(allTo);
		comboBox_5.setBounds(394, 11, 148, 22);
		panel_1.add(comboBox_5);
		
		JButton btnDeletePath = new JButton("Delete Path");
		btnDeletePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_4.getSelectedItem()==null || comboBox_5.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(contentPane, "Nothing selected to Delete ");
				}
				else {
					String source = comboBox_4.getSelectedItem().toString();
					String destination = comboBox_5.getSelectedItem().toString();
					try {
					delete(source, destination);
					JOptionPane.showMessageDialog(contentPane, "Route Deleted");
					}catch (Exception z) {
						JOptionPane.showMessageDialog(contentPane, "Error in deleting route :" +z);
					}
					new HomePage().setVisible(true);
					dispose();
				}
				
				
				
				
				
				
			}
			
			
		});
		btnDeletePath.setBounds(10, 49, 532, 32);
		panel_1.add(btnDeletePath);
		
		
		
		JButton btnNewButton_2 = new JButton("Update Route");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_6.getSelectedItem()==null || comboBox_7.getSelectedItem()==null ) {
					JOptionPane.showMessageDialog(contentPane, "Nothing selected to update ");
				}
				else {
					String prevfrom = comboBox_6.getSelectedItem().toString();
					String prevto = comboBox_7.getSelectedItem().toString();
					String newfrom = textField.getText();
					String newto = textField_1.getText();
					String newDistance = textField_3.getText();
					if(newfrom==""  || newto=="" ||  !isNumeric(newDistance)) {
						JOptionPane.showMessageDialog(contentPane, "Invalid Fields Error : Places must be string and distance must be number");
					}
					else {
						try {
							delete(prevto,prevfrom);
							insert(newfrom,newDistance,newto);
							}
							catch(Exception b){
								JOptionPane.showMessageDialog(contentPane, "Cannot update routes :" +b);
							}
						}
						
						new HomePage().setVisible(true);
						dispose();
					}
			}
		});
		btnNewButton_2.setBounds(10, 82, 532, 37);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Select");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int flag=0;
					ArrayList<Location> list= getAllPlaces();
					ListIterator<Location> litr = list.listIterator();
					while(litr.hasNext()) {
						Location temp = litr.next();
						if(temp.from.equals(comboBox_6.getSelectedItem().toString()) && temp.place.equals(comboBox_7.getSelectedItem().toString())) {
							textField.setText(temp.from);
							textField_1.setText(temp.place);
							textField_3.setText(""+temp.distance);
							flag=1;
						}
					}
					
					if(flag==0) {
						JOptionPane.showMessageDialog(contentPane, "No such route exists");
					}
					}catch (Exception y) {
						JOptionPane.showMessageDialog(contentPane ,"No route Available : " +y);
					}
			}
		});
		btnNewButton_3.setBounds(230, 11, 89, 23);
		panel.add(btnNewButton_3);
		
		
	}
}
