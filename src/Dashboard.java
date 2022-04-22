import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import org.jdatepicker.JDatePicker;



public class Dashboard extends JFrame {

	/**
	 * 
	 */
	Color enter = new Color(0,0,0);
	Color exit = Color.DARK_GRAY;
	private JPanel contentPane;
	private JTable tableEmployeeDetail;
	private JTable tableVehicleDetail;
	private JLabel lblTotalVehicle = new JLabel("10");
	private JLabel lblTotalEmployee = new JLabel("10");
	private JLabel lblTotalClients = new JLabel("10");
	DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void insertEmployeeDetail()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			Statement st =con.createStatement();
			ResultSet rs = st.executeQuery("select * from employee");
			
			while(rs.next())
			{
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pnumber = rs.getString("pnumber");
				
				Object[] obj = {id,name,email,pnumber};
				model = (DefaultTableModel) tableEmployeeDetail.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertVehicleDetail()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			Statement st =con.createStatement();
			ResultSet rs = st.executeQuery("select * from vehicle");
			
			while(rs.next())
			{
				String vid = rs.getString("vid");
				String name = rs.getString("name");
				String vmodel = rs.getString("model");
				String plate = rs.getString("plate");
				
				Object[] obj = {vid,name,vmodel,plate};
				model = (DefaultTableModel) tableVehicleDetail.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void totalVehicle()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			Statement st =con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select count(*) as 'rowcount' from vehicle");
			rs.next();
			int count = rs.getInt("rowcount");
			rs.close();
			lblTotalVehicle.setText(Integer.toString(count));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void totalEmployee()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			Statement st =con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select count(*) as 'rowcount' from employee");
			rs.next();
			int count = rs.getInt("rowcount");
			rs.close();
			lblTotalEmployee.setText(Integer.toString(count));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void totalClient()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			Statement st =con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select count(*) as 'rowcount' from client");
			rs.next();
			int count = rs.getInt("rowcount");
			rs.close();
			lblTotalClients.setText(Integer.toString(count));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Dashboard() {
		setResizable(false);
		setUndecorated(true);
		totalVehicle();
		totalEmployee();
		totalClient();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1905, 1023);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 255));
		panel.setBounds(0, 0, 1900, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMenuIcon = new JLabel("");
		lblMenuIcon.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_menu_48px_1.png"));
		lblMenuIcon.setBounds(10, 11, 48, 48);
		panel.add(lblMenuIcon);
		
		JPanel addOn = new JPanel();
		addOn.setBackground(new Color(51, 51, 51));
		addOn.setBounds(64, 9, 5, 50);
		panel.add(addOn);
		
		JLabel lblName = new JLabel("Car Management System");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblName.setBounds(79, 20, 272, 27);
		panel.add(lblName);
		
		JLabel lblWelcome = new JLabel("Welcome,");
		lblWelcome.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\male_user_50px.png"));
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblWelcome.setBounds(1583, 11, 242, 48);
		panel.add(lblWelcome);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(1844, 11, 46, 33);
		panel.add(lblClose);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(new Color(51, 51, 51));
		MainPanel.setBounds(0, 70, 340, 960);
		contentPane.add(MainPanel);
		MainPanel.setLayout(null);
		
		JPanel HomePanel = new JPanel();
		HomePanel.setBackground(Color.RED);
		HomePanel.setBounds(0, 50, 340, 60);
		MainPanel.add(HomePanel);
		HomePanel.setLayout(null);
		
		JLabel lblHomePage = new JLabel("    Home Page");
		lblHomePage.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\adminIcons\\icons8_Home_26px_2.png"));
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblHomePage.setBounds(64, 11, 184, 38);
		HomePanel.add(lblHomePage);
		
		JPanel DashboardPanel = new JPanel();
		DashboardPanel.setLayout(null);
		DashboardPanel.setBackground(Color.DARK_GRAY);
		DashboardPanel.setBounds(0, 138, 340, 60);
		MainPanel.add(DashboardPanel);
		
		JLabel lblNewLabel_3_1 = new JLabel("    Dashboard");
		lblNewLabel_3_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\adminIcons\\icons8_Library_26px_1.png"));
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(64, 11, 184, 38);
		DashboardPanel.add(lblNewLabel_3_1);
		
		JLabel lblFeatures = new JLabel("Features");
		lblFeatures.setForeground(Color.WHITE);
		lblFeatures.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblFeatures.setBounds(10, 209, 184, 38);
		MainPanel.add(lblFeatures);
		
		JPanel EmployeePanel = new JPanel();
		EmployeePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageEmployee me = new ManageEmployee();
				me.setLocationRelativeTo(null);
				me.setVisible(true);
				dispose();
			}
		});
		EmployeePanel.setLayout(null);
		EmployeePanel.setBackground(Color.DARK_GRAY);
		EmployeePanel.setBounds(0, 262, 340, 60);
		MainPanel.add(EmployeePanel);
		
		JLabel lblEmployee = new JLabel("    Manage Employee");
		lblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				EmployeePanel.setBackground(enter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				EmployeePanel.setBackground(exit);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageEmployee me = new ManageEmployee();
				me.setLocationRelativeTo(null);
				me.setVisible(true);
				dispose();
			}
		});
		lblEmployee.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\adminIcons\\icons8_People_50px.png"));
		lblEmployee.setForeground(Color.WHITE);
		lblEmployee.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblEmployee.setBounds(64, 11, 224, 38);
		EmployeePanel.add(lblEmployee);
		
		JPanel VehiclesPanel = new JPanel();
		VehiclesPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageVehicles mv = new ManageVehicles();
				mv.setLocationRelativeTo(null);
				mv.setVisible(true);
				dispose();
			}
		});
		VehiclesPanel.setLayout(null);
		VehiclesPanel.setBackground(Color.DARK_GRAY);
		VehiclesPanel.setBounds(0, 376, 340, 60);
		MainPanel.add(VehiclesPanel);
		
		JLabel lblVehicles = new JLabel("    Manage Vehicles");
		lblVehicles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				VehiclesPanel.setBackground(enter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				VehiclesPanel.setBackground(exit);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageVehicles mv = new ManageVehicles();
				mv.setLocationRelativeTo(null);
				mv.setVisible(true);
				dispose();
			}
		});
		lblVehicles.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_garage_50px_1.png"));
		lblVehicles.setForeground(Color.WHITE);
		lblVehicles.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblVehicles.setBounds(64, 7, 224, 49);
		VehiclesPanel.add(lblVehicles);
		
		JPanel ClientsPanel = new JPanel();
		ClientsPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ClientsPanel.setBackground(enter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ClientsPanel.setBackground(exit);
			}
			public void mouseClicked(MouseEvent e) {
				ManageClient mc = new ManageClient();
				mc.setLocationRelativeTo(null);
				mc.setVisible(true);
				dispose();
			}
		});
		ClientsPanel.setLayout(null);
		ClientsPanel.setBackground(Color.DARK_GRAY);
		ClientsPanel.setBounds(0, 493, 340, 60);
		MainPanel.add(ClientsPanel);
		
		JLabel lblClients = new JLabel("    Manage Clients");
		lblClients.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\adminIcons\\icons8_Read_Online_26px.png"));
		lblClients.setForeground(Color.WHITE);
		lblClients.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblClients.setBounds(64, 11, 218, 38);
		ClientsPanel.add(lblClients);
		
		JPanel RecordPanel = new JPanel();
		RecordPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewRecord vr = new ViewRecord();
				vr.setLocationRelativeTo(null);
				vr.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				RecordPanel.setBackground(enter);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				RecordPanel.setBackground(exit);
			}
		});
		RecordPanel.setLayout(null);
		RecordPanel.setBackground(Color.DARK_GRAY);
		RecordPanel.setBounds(0, 605, 340, 60);
		MainPanel.add(RecordPanel);
		
		JLabel lblViewRecords = new JLabel("    View Records");
		lblViewRecords.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\adminIcons\\icons8_View_Details_26px.png"));
		lblViewRecords.setForeground(Color.WHITE);
		lblViewRecords.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblViewRecords.setBounds(64, 11, 184, 38);
		RecordPanel.add(lblViewRecords);
		
		JPanel LogOutPanel = new JPanel();
		LogOutPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login log = new Login();
				log.setLocationRelativeTo(null);
				log.setVisible(true);
				dispose();
			}
		});
		LogOutPanel.setLayout(null);
		LogOutPanel.setBackground(new Color(102, 102, 255));
		LogOutPanel.setBounds(0, 710, 340, 60);
		MainPanel.add(LogOutPanel);
		
		JLabel lblLogOut = new JLabel("    Log Out");
		lblLogOut.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\adminIcons\\icons8_Exit_26px_2.png"));
		lblLogOut.setForeground(Color.WHITE);
		lblLogOut.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblLogOut.setBounds(64, 11, 184, 38);
		LogOutPanel.add(lblLogOut);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(340, 70, 1560, 953);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.RED));
		panel_1.setBounds(98, 102, 260, 140);
		panel_4.add(panel_1);
		panel_1.setLayout(null);
		
		lblTotalVehicle.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_50px.png"));
		lblTotalVehicle.setForeground(new Color(102, 102, 102));
		lblTotalVehicle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblTotalVehicle.setBounds(90, 36, 160, 51);
		panel_1.add(lblTotalVehicle);
		
		JLabel lblNewLabel = new JLabel("Total Vehicles");
		lblNewLabel.setForeground(new Color(102, 102, 102));
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblNewLabel.setBounds(98, 71, 189, 27);
		panel_4.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(102, 102, 255) ));
		panel_1_1.setBounds(541, 102, 260, 140);
		panel_4.add(panel_1_1);
		
		lblTotalEmployee.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_identification_documents_50px.png"));
		lblTotalEmployee.setForeground(new Color(102, 102, 102));
		lblTotalEmployee.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblTotalEmployee.setBounds(90, 36, 160, 51);
		panel_1_1.add(lblTotalEmployee);
		
		JLabel lblNewLabel_2 = new JLabel("Total Employee");
		lblNewLabel_2.setForeground(new Color(102, 102, 102));
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblNewLabel_2.setBounds(541, 71, 189, 27);
		panel_4.add(lblNewLabel_2);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(Color.LIGHT_GRAY);
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.RED));
		panel_1_2.setBounds(1022, 102, 260, 140);
		panel_4.add(panel_1_2);
		
		lblTotalClients.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_matthew_accounting_50px.png"));
		lblTotalClients.setForeground(new Color(102, 102, 102));
		lblTotalClients.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 50));
		lblTotalClients.setBounds(90, 36, 160, 51);
		panel_1_2.add(lblTotalClients);
		
		JLabel lblTotalClient = new JLabel("Total Client");
		lblTotalClient.setForeground(new Color(102, 102, 102));
		lblTotalClient.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblTotalClient.setBounds(1022, 71, 189, 27);
		panel_4.add(lblTotalClient);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 282, 687, 279);
		panel_4.add(scrollPane);
		
		tableEmployeeDetail = new JTable();
		tableEmployeeDetail.setSelectionBackground(Color.WHITE);
		tableEmployeeDetail.setFocusTraversalKeysEnabled(false);
		tableEmployeeDetail.setGridColor(new Color(102, 102, 255));
		tableEmployeeDetail.setForeground(new Color(0, 112, 192));
		tableEmployeeDetail.getTableHeader().setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        tableEmployeeDetail.getTableHeader().setBackground(new Color (102,102,255));
        tableEmployeeDetail.getTableHeader().setForeground(new Color (250,250,250));
		tableEmployeeDetail.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		
		scrollPane.setViewportView(tableEmployeeDetail);
		tableEmployeeDetail.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Email", "Phone Number"
			}
		));
		insertEmployeeDetail();
		tableEmployeeDetail.setFocusable(false);
		tableEmployeeDetail.setIntercellSpacing(new java.awt.Dimension(0, 0));
		tableEmployeeDetail.setRowHeight(40);				
		
		JLabel lblEmployeeDetail = new JLabel("Employee Details");
		lblEmployeeDetail.setForeground(new Color(102, 102, 102));
		lblEmployeeDetail.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblEmployeeDetail.setBounds(32, 244, 189, 27);
		panel_4.add(lblEmployeeDetail);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 631, 687, 279);
		panel_4.add(scrollPane_1);
		
		tableVehicleDetail = new JTable();
		tableVehicleDetail.setSelectionBackground(Color.WHITE);
		tableVehicleDetail.getTableHeader().setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        tableVehicleDetail.getTableHeader().setBackground(new Color (102,102,255));
        tableVehicleDetail.getTableHeader().setForeground(new Color (250,250,250));
		tableVehicleDetail.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Model", "Plate"
			}
		));
		insertVehicleDetail();
		tableVehicleDetail.setRowHeight(40);
		tableVehicleDetail.setGridColor(new Color(102, 102, 255));
		tableVehicleDetail.setIntercellSpacing(new Dimension(0, 0));
		tableVehicleDetail.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		tableVehicleDetail.setFocusable(false);
		tableVehicleDetail.setRowHeight(40);				
		scrollPane_1.setViewportView(tableVehicleDetail);
		JLabel lblUserDetail_1 = new JLabel("Vehicle Details");
		lblUserDetail_1.setForeground(new Color(102, 102, 102));
		lblUserDetail_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblUserDetail_1.setBounds(32, 593, 189, 27);
		panel_4.add(lblUserDetail_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\logoVKU2.png"));
		lblNewLabel_1.setBounds(928, 339, 580, 416);
		panel_4.add(lblNewLabel_1);
		tableEmployeeDetail.getTableHeader().setReorderingAllowed(false);
		tableEmployeeDetail.getTableHeader().setSize(120,120);
	}
}
