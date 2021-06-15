package Agence;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.util.ArrayList;

public class Agence {
	static Connection cnx;

	protected static final Component NULL = null;
	private JFrame frame;
	private JTextField id;
	private JTextField dimension;
	private JTextField price;
	private JTextField city;
	private JTextField nb_chambre;
	private JTextField cin;
	private JTextField lname;
	private JTextField fname;
	private JTextField location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agence window = new Agence();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/agence_imm","root","");
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	public Agence() {
		initialize();
		this.connect();
		
		
	}
	ArrayList<Location> loc =new ArrayList<Location>();


	public ArrayList<Location> tab_maison() {
		try {
			 cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/agence_imm","root","");
			Statement stmt=cnx.createStatement();
			ResultSet result=stmt.executeQuery("select * from maison");
			Location l;
			while(result.next()) {
				l=new Location(
						result.getString("id_maison"),
						result.getString("prix"),
						result.getString("ville"),
						result.getString("dimension"),
						result.getString("nb_chambre"),
						result.getString("disponibilité"),
						result.getString("cin")
						
						);
				loc.add(l);

			}

		}catch(Exception ev) {ev.printStackTrace();}
		return(loc);

	}
	

	public Object[][] affichage_maison(){
		ArrayList<Location> loc1 =this.tab_maison();
		Object[][] rows=new Object[loc1.size()][7];
		for (int i = 0; i < loc1.size(); i++) {
			
			rows[i][0]=loc1.get(i).getId_maison();
			rows[i][1]=loc1.get(i).getPrix();
			rows[i][2]=loc1.get(i).getVille();
			rows[i][3]=loc1.get(i).getDimension();
			rows[i][4]=loc1.get(i).getNb_chambre();
			rows[i][5]=loc1.get(i).getDisponibilité();
			rows[i][6]=loc1.get(i).getCin();
					
		}
		
	return rows;
		
	}
	
	
	ArrayList<Client> cl =new ArrayList<Client>();
	public ArrayList<Client> tab_client() {
		try {
			cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/agence_imm","root","");
			Statement stmt=cnx.createStatement();
			ResultSet result=stmt.executeQuery("select * from client");
			Client c;
			while(result.next()) {
				c=new Client(
						result.getString("cin"),
						result.getString("nom"),
						result.getString("prenom"),
						result.getString("localisation")
					
						
						);
				cl.add(c);
			}
			
		}catch(Exception ev) {ev.printStackTrace();}
		return(cl);
	}
	
   public Object[][] affichage_client(){
		ArrayList<Client> cl1 =this.tab_client();
		Object[][] row=new Object[cl1.size()][7];
		for (int i = 0; i < cl1.size(); i++) {
			
			row[i][0]=cl1.get(i).getCin();
			row[i][1]=cl1.get(i).getNom();
			row[i][2]=cl1.get(i).getPrenom();
			row[i][3]=cl1.get(i).getLocalisation();
			
	  }	
		return row;
	}

	private JTable table;
	private JTable table_1;
	private JTextField disp;
	private JTextField loc_cin;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize( 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("BSA AGENCE");
		frame.show();
		frame.getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(10, 10));
		
		JLabel lblNewLabel = new JLabel("LOCATION");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);
		
		JButton insert = new JButton("INSERT");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String i=id.getText();
				String p=price.getText();
				String c=city.getText();
				String d=dimension.getText();
				String n=nb_chambre.getText();
				
                if(i.equals("")||p.equals("")||c.equals("")||d.equals("")||n.equals("")) {
                	JOptionPane.showMessageDialog(null,"Not Added ! there is an empty column please enter necessary data");
                }
                else {
            		try {
    					Statement stmt=cnx.createStatement();
    					String Requet="INSERT INTO maison  VALUES ('"+i+"','"+p+"','"+c+"','"+d+"','"+n+"','oui',NULL) " ;
    					int result=stmt.executeUpdate(Requet);
    					if (result==1){
    						
    						JOptionPane.showMessageDialog(null,"Item added succesfuly");
    						Agence g=new Agence();
    						g.frame.setVisible(true);
    						frame.dispose();
    						}
    					}
    					catch(Exception ev) {ev.printStackTrace();}
            		id.setText("");
            		price.setText("");
            		city.setText("");
            		dimension.setText("");
            		nb_chambre.setText("");
            		disp.setText("");
            		loc_cin.setText("");
                }
			}
		});
		
		
		
		
		panel_4.add(insert);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>=0) {
					String i=id.getText();
					String p=price.getText();
					String c=city.getText();
					String d=dimension.getText();
					String nc=nb_chambre.getText();
					String dis=disp.getText();
					String l=loc_cin.getText();
					try{
					 Statement stmt=cnx.createStatement();
					 String Requet="UPDATE maison SET  prix='"+p+"',ville='"+c+"',dimension='"+d+"',nb_chambre='"+nc+"',disponibilité='"+dis+"',cin='"+l+"' where id_maison= " + table.getModel().getValueAt(table.getSelectedRow(),0) ;
					 int result=stmt.executeUpdate(Requet);
					 if (result==1){
						
						JOptionPane.showMessageDialog(null,"Item updated succesfuly");
						Agence g=new Agence();
						g.frame.setVisible(true);
						frame.dispose();
						}}
					catch(Exception ev) {ev.printStackTrace();}
					
					
				}else {
					JOptionPane.showMessageDialog(null,"No row selected ! ");
				}
				
				
			}
		});
		
		panel_4.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>=0) {
					try {
						Statement stmt=cnx.createStatement();
						String Requet="DELETE FROM maison where id_maison= " + table.getModel().getValueAt(table.getSelectedRow(),0)/*.toString()*/ ;
						int result=stmt.executeUpdate(Requet);
						JOptionPane.showMessageDialog(NULL,"Row is deleted !");
		                }
						catch(Exception ev) {ev.printStackTrace();}
					Agence g=new Agence();
					g.frame.setVisible(true);
					frame.dispose();
					}
				else {
					JOptionPane.showMessageDialog(NULL,"Please set single row for delete !");
					}
				id.setText("");
        		price.setText("");
        		city.setText("");
        		dimension.setText("");
        		nb_chambre.setText("");
        		disp.setText("");
        		loc_cin.setText("");
			}
			
		});
		
		panel_4.add(btnNewButton_2);
		
		
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID_house");
		lblNewLabel_1.setBounds(36, 23, 55, 19);
		panel_5.add(lblNewLabel_1);
		
		id = new JTextField();
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(c>='0' && c<='9'||Character.isISOControl(c)) {
					id.setEditable(true);
				}
				else {
					id.setEditable(false);
				}
			}
		});
		id.setBounds(101, 22, 86, 20);
		panel_5.add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Dimension");
		lblNewLabel_2.setBounds(36, 171, 55, 14);
		panel_5.add(lblNewLabel_2);
		
		dimension = new JTextField();
		dimension.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(c>='0' && c<='9'||Character.isISOControl(c)) {
					dimension.setEditable(true);
				}
				else {
					dimension.setEditable(false);
				}
			}
		});
		dimension.setBounds(101, 168, 86, 20);
		panel_5.add(dimension);
		dimension.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setBounds(36, 82, 46, 14);
		panel_5.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("City");
		lblNewLabel_4.setBounds(36, 124, 46, 14);
		panel_5.add(lblNewLabel_4);
		
		price = new JTextField();
		price.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(c>='0' && c<='9'||Character.isISOControl(c)) {
					price.setEditable(true);
				}
				else {
				    price.setEditable(false);
				}
			}
		});
		price.setBounds(101, 79, 86, 20);
		panel_5.add(price);
		price.setColumns(10);
		
		city = new JTextField();
		city.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)) {
					city.setEditable(true);
				}
				else {
					city.setEditable(false);
				}
			}
		});
		city.setBounds(101, 121, 86, 20);
		panel_5.add(city);
		city.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("F+");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(218, 25, 55, 14);
		panel_5.add(lblNewLabel_5);
		
		nb_chambre = new JTextField();
		nb_chambre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(c>='0' && c<='9'||Character.isISOControl(c)) {
					nb_chambre.setEditable(true);
				}
				else {
					nb_chambre.setEditable(false);
				}
			}
		});
		nb_chambre.setBounds(296, 22, 86, 20);
		panel_5.add(nb_chambre);
		nb_chambre.setColumns(10);
		
		JLabel lab = new JLabel("Disponibility");
		lab.setBounds(227, 82, 56, 14);
		panel_5.add(lab);
		
		JLabel lab1 = new JLabel("Locator_CIN");
		lab1.setBounds(227, 124, 69, 14);
		panel_5.add(lab1);
		
		disp = new JTextField();
		disp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)) {
					disp.setEditable(true);
				}
				else {
					disp.setEditable(false);
				}
			}
		});
		disp.setBounds(296, 79, 86, 20);
		panel_5.add(disp);
		disp.setColumns(10);
		
		loc_cin = new JTextField();
		loc_cin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(c>='0' && c<='9'||Character.isISOControl(c)) {
					loc_cin.setEditable(true);
				}
				else {
					loc_cin.setEditable(false);
				}
			}
		});
		loc_cin.setBounds(296, 121, 86, 20);
		panel_5.add(loc_cin);
		loc_cin.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable(this.affichage_maison(),new String[] {
				"ID", "Price", "City", "Dimension", "F+", "Disponibility", "L_CIN"
			});
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				String i=table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				String p=table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				String v=table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
				String d=table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
				String nc=table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				String dis=table.getModel().getValueAt(table.getSelectedRow(), 5).toString();
				String c;
				if(table.getModel().getValueAt(table.getSelectedRow(), 6)== NULL ) {
					c="null";
				}else {
					c=table.getModel().getValueAt(table.getSelectedRow(), 6).toString();
				}
				
				id.setText(i);
        		price.setText(p);
        		city.setText(v);
        		dimension.setText(d);
        		nb_chambre.setText(nc);
        		disp.setText(dis);
        		loc_cin.setText(c);
				
			}
			
		});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(68);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(68);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(10, 10));
		
		JLabel lblNewLabel_6 = new JLabel("CLIENT");
		lblNewLabel_6.setBackground(SystemColor.activeCaption);
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_6, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.SOUTH);
		
		JButton btnNewButton_4 = new JButton("INSERT");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c=cin.getText();
				String f=fname.getText();
				String l=lname.getText();
				String loc=location.getText();
				
                if(c.equals("")||f.equals("")||l.equals("")||loc.equals("")) {
                	JOptionPane.showMessageDialog(null,"Not Added ! there is an empty column please enter necessary data");
                }
                else {
            		try {
    					Statement stmt=cnx.createStatement();
    					String Requet="INSERT INTO client  VALUES ('"+c+"','"+f+"','"+l+"','"+loc+"') " ;
    					int result=stmt.executeUpdate(Requet);
    					if (result==1){
    						
    						JOptionPane.showMessageDialog(null,"Item added succesfuly");
    						Agence g=new Agence();
    						g.frame.setVisible(true);
    						frame.dispose();
    						}
    					}
    					catch(Exception ev) {ev.printStackTrace();}
            		cin.setText("");
            		fname.setText("");
            		lname.setText("");
            		location.setText("");
                }
			}
		});
		panel_6.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("UPDATE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow()>=0) {
					String i=cin.getText();
					String l=lname.getText();
					String f=fname.getText();
					String loc=location.getText();
					
					try{
					 Statement stmt=cnx.createStatement();
					 String Requet="UPDATE client SET  nom='"+l+"',prenom='"+f+"',localisation='"+loc+"' where cin= " + table_1.getModel().getValueAt(table_1.getSelectedRow(),0) ;
					 int result=stmt.executeUpdate(Requet);
					 if (result==1){
						
						JOptionPane.showMessageDialog(null,"Item updated succesfuly");
						Agence g=new Agence();
						g.frame.setVisible(true);
						frame.dispose();
						}}
					catch(Exception ev) {ev.printStackTrace();}
					
					
				}else {
					JOptionPane.showMessageDialog(null,"No row selected ! ");
				}
				
				
			}
		});
		panel_6.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("DELETE");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow()>=0) {
					try {
						Statement stmt=cnx.createStatement();
						String Requet="DELETE FROM client where cin= " + table_1.getModel().getValueAt(table_1.getSelectedRow(),0)/*.toString()*/ ;
						int result=stmt.executeUpdate(Requet);
						
						
						String req="UPDATE maison SET disponibilité='oui',cin='"+ NULL +"' WHERE cin="+ table_1.getModel().getValueAt(table_1.getSelectedRow(),0)/*.toString()*/;
						int r=stmt.executeUpdate(req);
						Statement stmt1=cnx.createStatement();
						String R="SELECT * FROM  maison " ;
						ResultSet up=stmt.executeQuery(R);
						JOptionPane.showMessageDialog(NULL,"Row is deleted !");
						Agence g=new Agence();
						g.frame.setVisible(true);
						frame.dispose();						
						
		                }
						catch(Exception ev) {ev.printStackTrace();}
					}
				else {
					JOptionPane.showMessageDialog(NULL,"Please select row for delete !");
					}
				cin.setText("");
        		fname.setText("");
        		lname.setText("");
        		location.setText("");
			}
		});
		panel_6.add(btnNewButton_6);
		
		
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("CIN");
		lblNewLabel_7.setBounds(35, 41, 46, 14);
		panel_7.add(lblNewLabel_7);
		
		cin = new JTextField();
		cin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(c>='0' && c<='9'||Character.isISOControl(c)) {
					cin.setEditable(true);
				}
				else {
					cin.setEditable(false);
				}
			}
		});
		cin.setBounds(91, 38, 86, 20);
		panel_7.add(cin);
		cin.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Last_Name");
		lblNewLabel_8.setBounds(24, 134, 57, 14);
		panel_7.add(lblNewLabel_8);
		
		lname = new JTextField();
		lname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)) {
					lname.setEditable(true);
				}
				else {
					lname.setEditable(false);
				}
				
			}
		});
		lname.setBounds(91, 131, 86, 20);
		panel_7.add(lname);
		lname.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("First_Name");
		lblNewLabel_9.setBounds(215, 41, 57, 14);
		panel_7.add(lblNewLabel_9);
		
		fname = new JTextField();
		fname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)) {
					fname.setEditable(true);
				}
				else {
					fname.setEditable(false);
				}
			}
		});
		fname.setBounds(293, 38, 86, 20);
		panel_7.add(fname);
		fname.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Location");
		lblNewLabel_10.setBounds(215, 134, 68, 14);
		panel_7.add(lblNewLabel_10);
		
		location = new JTextField();
		location.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c=e.getKeyChar();
				if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)) {
					location.setEditable(true);
				}
				else {
					location.setEditable(false);
				}
			}
		});
		location.setBounds(293, 131, 86, 20);
		panel_7.add(location);
		location.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable(this.affichage_client(),new String[] {"CIN", "First Name", "Last Name", "Location"});
		table_1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				String i=table_1.getModel().getValueAt(table_1.getSelectedRow(), 0).toString();
				String l=table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString();
				String f=table_1.getModel().getValueAt(table_1.getSelectedRow(), 2).toString();
				String v=table_1.getModel().getValueAt(table_1.getSelectedRow(), 3).toString();
			
				
				cin.setText(i);
        		fname.setText(f);
        		lname.setText(l);
        		location.setText(v);
        		}
			
		});
	
		scrollPane_1.setViewportView(table_1);
		
		
		
	}
}
