import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPasswordField;



public class Client extends JFrame {

	/**
	 * 
	 */
	Color enter = new Color(0,0,0);
	Color exit = Color.DARK_GRAY;
	String vname,vplate,from,to;
	String username,oldpass,newpass,newpasscon;
	private JPanel contentPane;
	DefaultTableModel model;
	private JTextField txtVName;
	private JTextField txtFrom;
	private JTextField txtTo;
	private JTextField txtVPlate;
	private JPasswordField txtOldPass;
	private JPasswordField txtNewPass;
	private JTextField txtUsername;
	private JPanel panelRegister = new JPanel();
	private JPanel panelChangePassword = new JPanel();
	private JTextField txtName;
	private JTextField txtPlate;
	private JLabel lblVName = new JLabel("");
	private JLabel lblDayTo = new JLabel("");
	private JLabel lblDayFrom = new JLabel("");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean checkRegister()
	{
		boolean check = true;
		vname = txtVName.getText();
		from = txtFrom.getText();
		to = txtTo.getText();
		vplate = txtVPlate.getText();
		if(vname.equals(""))
		{
			check = false;
		}
		if(from.equals(""))
		{
			check = false;
		}
		if(to.equals(""))
		{
			check = false;
		}
		if(vplate.equals(""))
		{
			check = false;
		}
		return check;
	}
	
	public boolean vehicleRegister()
	{
		boolean reg = false;
		vname = txtVName.getText();
		from = txtFrom.getText();
		to = txtTo.getText();
		vplate = txtVPlate.getText();
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "insert into vehicleRegister(vname,froms,tos,vplate) values(?,?,?,?) ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,vname);
			pst.setString(2, from);
			pst.setString(3, to);
			pst.setString(4, vplate);
			
			int row = pst.executeUpdate();
			if(row>0)
			{
				reg = true;
			}
			else {
				reg=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reg;
	}
	
	public boolean changePassword()
	{
		boolean change = false;
		username = txtUsername.getText();
		oldpass = txtOldPass.getText();
		newpass = txtNewPass.getText();
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "update clientAcc set password=? where name =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, newpass);
			pst.setString(2, username);
			
			int row = pst.executeUpdate();
			if(row>0)
			{
				change = true;
			}
			else {
				change=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return change;
	}
	public boolean checkOldPassword()
	{
		boolean check = false;
		username = txtUsername.getText();
		oldpass = txtOldPass.getText();
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			PreparedStatement pst = con.prepareStatement("select password from clientAcc where name =?") ;
			pst.setString(1,username);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String checkpass = rs.getString("password");
			rs.close();
			if(checkpass.equals(oldpass))
			{
				check = true;
			}
			else
			{
				check = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	public void searchVehicle()
	{
		vplate = txtPlate.getText();
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			PreparedStatement pst = con.prepareStatement("select vname,froms,tos from vehicleRegister where vplate =?") ;
			pst.setString(1,vplate);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				lblVName.setText(rs.getString("vname"));
				lblDayTo.setText(rs.getString("tos"));
				lblDayFrom.setText(rs.getString("froms"));
				System.out.println(lblDayFrom.getText());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Vehicle Found!");
				lblVName.setText("");
				lblDayFrom.setText("");
				lblDayTo.setText("");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Client() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelChangePassword.setBackground(Color.WHITE);
		panelChangePassword.setBounds(340, 70, 1050, 719);
		contentPane.add(panelChangePassword);
		panelChangePassword.setLayout(null);
		panelChangePassword.setVisible(false);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_password_50px_1.png"));
		lblChangePassword.setForeground(Color.RED);
		lblChangePassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblChangePassword.setBounds(362, 68, 300, 50);
		panelChangePassword.add(lblChangePassword);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.RED);
		panel_2_1_1.setBounds(350, 125, 300, 5);
		panelChangePassword.add(panel_2_1_1);
		
		JLabel lblClientId_1_3 = new JLabel("Current Password");
		lblClientId_1_3.setForeground(Color.RED);
		lblClientId_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_3.setBounds(272, 249, 194, 41);
		panelChangePassword.add(lblClientId_1_3);
		
		txtOldPass = new JPasswordField();
		txtOldPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtOldPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtOldPass.setColumns(10);
		txtOldPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtOldPass.setBackground(Color.WHITE);
		txtOldPass.setAlignmentY(0.0f);
		txtOldPass.setAlignmentX(0.0f);
		txtOldPass.setBounds(272, 306, 260, 40);
		panelChangePassword.add(txtOldPass);
		
		JLabel lblUsernameIcon_4_3 = new JLabel("");
		lblUsernameIcon_4_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_forgot_password_50px.png"));
		lblUsernameIcon_4_3.setForeground(Color.WHITE);
		lblUsernameIcon_4_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_3.setBounds(210, 296, 64, 50);
		panelChangePassword.add(lblUsernameIcon_4_3);
		
		JLabel lblName_1_1 = new JLabel("New Password");
		lblName_1_1.setForeground(Color.RED);
		lblName_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblName_1_1.setBounds(272, 378, 170, 41);
		panelChangePassword.add(lblName_1_1);
		
		txtNewPass = new JPasswordField();
		txtNewPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtNewPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtNewPass.setColumns(10);
		txtNewPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtNewPass.setBackground(Color.WHITE);
		txtNewPass.setAlignmentY(0.0f);
		txtNewPass.setAlignmentX(0.0f);
		txtNewPass.setBounds(272, 435, 260, 40);
		panelChangePassword.add(txtNewPass);
		
		JLabel lblUsernameIcon_1_2_1 = new JLabel("");
		lblUsernameIcon_1_2_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_password_50px_1.png"));
		lblUsernameIcon_1_2_1.setForeground(Color.WHITE);
		lblUsernameIcon_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_1_2_1.setBounds(210, 425, 64, 50);
		panelChangePassword.add(lblUsernameIcon_1_2_1);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setAlignmentY(0.0f);
		txtUsername.setAlignmentX(0.0f);
		txtUsername.setBounds(272, 198, 260, 40);
		panelChangePassword.add(txtUsername);
		
		JLabel lblClientId_1_3_1 = new JLabel("Username");
		lblClientId_1_3_1.setForeground(Color.RED);
		lblClientId_1_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_3_1.setBounds(272, 141, 194, 41);
		panelChangePassword.add(lblClientId_1_3_1);
		
		JLabel lblUsernameIcon_4_3_1 = new JLabel("");
		lblUsernameIcon_4_3_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_registration_50px.png"));
		lblUsernameIcon_4_3_1.setForeground(Color.WHITE);
		lblUsernameIcon_4_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_3_1.setBounds(210, 188, 64, 50);
		panelChangePassword.add(lblUsernameIcon_4_3_1);
		
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnConfirm.setFocusable(false);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkOldPassword()==true)
				{
					if(changePassword()==true)
					{
						JOptionPane.showMessageDialog(null, "Successfuly change password!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Failed to change password!");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Current Password is not match!");
				}
			}
		});
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setBackground(Color.RED);
		btnConfirm.setBounds(404, 523, 211, 34);
		panelChangePassword.add(btnConfirm);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\logoVKU2.png"));
		lblNewLabel_2.setBounds(566, 214, 458, 240);
		panelChangePassword.add(lblNewLabel_2);
		
		panelRegister.setBackground(Color.WHITE);
		panelRegister.setBounds(340, 70, 1086, 719);
		contentPane.add(panelRegister);
		panelRegister.setLayout(null);
		
		JLabel lblRegisterVehicles = new JLabel("Register Vehicles");
		lblRegisterVehicles.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_registration_50px.png"));
		lblRegisterVehicles.setForeground(Color.RED);
		lblRegisterVehicles.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblRegisterVehicles.setBounds(355, 29, 300, 50);
		panelRegister.add(lblRegisterVehicles);
		
		JPanel panelRe = new JPanel();
		panelRe.setBackground(Color.RED);
		panelRe.setBounds(344, 90, 300, 5);
		panelRegister.add(panelRe);
		
		JLabel lblClientId_1 = new JLabel("Vehicle Name");
		lblClientId_1.setForeground(Color.RED);
		lblClientId_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1.setBounds(259, 106, 170, 41);
		panelRegister.add(lblClientId_1);
		
		JLabel lblUsernameIcon_4 = new JLabel("");
		lblUsernameIcon_4.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_insurance_50px_3.png"));
		lblUsernameIcon_4.setForeground(Color.WHITE);
		lblUsernameIcon_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4.setBounds(197, 153, 64, 50);
		panelRegister.add(lblUsernameIcon_4);
		
		txtVName = new JTextField();
		txtVName.setHorizontalAlignment(SwingConstants.LEFT);
		txtVName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtVName.setColumns(10);
		txtVName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtVName.setBackground(Color.WHITE);
		txtVName.setAlignmentY(0.0f);
		txtVName.setAlignmentX(0.0f);
		txtVName.setBounds(259, 163, 260, 40);
		panelRegister.add(txtVName);
		
		JLabel lblName_1 = new JLabel("From");
		lblName_1.setForeground(Color.RED);
		lblName_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblName_1.setBounds(259, 235, 64, 41);
		panelRegister.add(lblName_1);
		
		txtFrom = new JTextField();
		txtFrom.setHorizontalAlignment(SwingConstants.LEFT);
		txtFrom.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtFrom.setColumns(10);
		txtFrom.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtFrom.setBackground(Color.WHITE);
		txtFrom.setAlignmentY(0.0f);
		txtFrom.setAlignmentX(0.0f);
		txtFrom.setBounds(259, 292, 260, 40);
		panelRegister.add(txtFrom);
		
		JLabel lblUsernameIcon_1_2 = new JLabel("");
		lblUsernameIcon_1_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_pay_date_50px.png"));
		lblUsernameIcon_1_2.setForeground(Color.WHITE);
		lblUsernameIcon_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_1_2.setBounds(197, 282, 64, 50);
		panelRegister.add(lblUsernameIcon_1_2);
		
		JLabel lblClientId_1_1 = new JLabel("To");
		lblClientId_1_1.setForeground(Color.RED);
		lblClientId_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_1.setBounds(259, 385, 170, 41);
		panelRegister.add(lblClientId_1_1);
		
		txtTo = new JTextField();
		txtTo.setHorizontalAlignment(SwingConstants.LEFT);
		txtTo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtTo.setColumns(10);
		txtTo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtTo.setBackground(Color.WHITE);
		txtTo.setAlignmentY(0.0f);
		txtTo.setAlignmentX(0.0f);
		txtTo.setBounds(259, 436, 260, 40);
		panelRegister.add(txtTo);
		
		JLabel lblUsernameIcon_4_1 = new JLabel("");
		lblUsernameIcon_4_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_timeline_week_50px.png"));
		lblUsernameIcon_4_1.setForeground(Color.WHITE);
		lblUsernameIcon_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_1.setBounds(197, 418, 64, 58);
		panelRegister.add(lblUsernameIcon_4_1);
		
		JLabel lblClientId_1_2 = new JLabel("Vehicle Plate");
		lblClientId_1_2.setForeground(Color.RED);
		lblClientId_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_2.setBounds(259, 510, 246, 41);
		panelRegister.add(lblClientId_1_2);
		
		txtVPlate = new JTextField();
		txtVPlate.setHorizontalAlignment(SwingConstants.LEFT);
		txtVPlate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtVPlate.setColumns(10);
		txtVPlate.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtVPlate.setBackground(Color.WHITE);
		txtVPlate.setAlignmentY(0.0f);
		txtVPlate.setAlignmentX(0.0f);
		txtVPlate.setBounds(259, 567, 260, 40);
		panelRegister.add(txtVPlate);
		
		JLabel lblUsernameIcon_4_2 = new JLabel("");
		lblUsernameIcon_4_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_licence_plate_50px_1.png"));
		lblUsernameIcon_4_2.setForeground(Color.WHITE);
		lblUsernameIcon_4_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_2.setBounds(197, 557, 64, 50);
		panelRegister.add(lblUsernameIcon_4_2);
		
		JButton btnSignup = new JButton("REGISTER");
		btnSignup.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnSignup.setFocusable(false);
		btnSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkRegister()==true)
				{
					if(vehicleRegister()==true)
					{
					JOptionPane.showMessageDialog(null, "Successfuly register new vehicle!");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to register new vehicle!");
				}
			}
		});
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setBackground(Color.RED);
		btnSignup.setBounds(410, 637, 189, 33);
		panelRegister.add(btnSignup);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\logoVKU2.png"));
		lblNewLabel_1.setBounds(584, 235, 458, 240);
		panelRegister.add(lblNewLabel_1);
		
		JPanel panelMyVehicle = new JPanel();
		panelMyVehicle.setBounds(340, 70, 1060, 719);
		contentPane.add(panelMyVehicle);
		panelMyVehicle.setLayout(null);
		
		JLabel lblMyVehicle = new JLabel("My Vehicle");
		lblMyVehicle.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_advanced_search_50px.png"));
		lblMyVehicle.setForeground(Color.RED);
		lblMyVehicle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblMyVehicle.setBounds(503, 53, 300, 50);
		panelMyVehicle.add(lblMyVehicle);
		
		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(Color.RED);
		panel_2_1_1_1.setBounds(465, 110, 300, 5);
		panelMyVehicle.add(panel_2_1_1_1);
		
		JLabel lblClientId_1_4 = new JLabel("Vehicle Name");
		lblClientId_1_4.setForeground(Color.RED);
		lblClientId_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_4.setBounds(314, 143, 170, 41);
		panelMyVehicle.add(lblClientId_1_4);
		
		JLabel lblUsernameIcon_4_4 = new JLabel("");
		lblUsernameIcon_4_4.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_insurance_50px_3.png"));
		lblUsernameIcon_4_4.setForeground(Color.WHITE);
		lblUsernameIcon_4_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_4.setBounds(252, 190, 64, 50);
		panelMyVehicle.add(lblUsernameIcon_4_4);
		
		JLabel lblName_1_2 = new JLabel("Register From");
		lblName_1_2.setForeground(Color.RED);
		lblName_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblName_1_2.setBounds(314, 272, 170, 41);
		panelMyVehicle.add(lblName_1_2);
		
		JLabel lblUsernameIcon_1_2_2 = new JLabel("");
		lblUsernameIcon_1_2_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_pay_date_50px.png"));
		lblUsernameIcon_1_2_2.setForeground(Color.WHITE);
		lblUsernameIcon_1_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_1_2_2.setBounds(252, 319, 64, 50);
		panelMyVehicle.add(lblUsernameIcon_1_2_2);
		
		JLabel lblClientId_1_1_1 = new JLabel("To");
		lblClientId_1_1_1.setForeground(Color.RED);
		lblClientId_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_1_1.setBounds(314, 422, 170, 41);
		panelMyVehicle.add(lblClientId_1_1_1);
		
		JLabel lblUsernameIcon_4_1_1 = new JLabel("");
		lblUsernameIcon_4_1_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_timeline_week_50px.png"));
		lblUsernameIcon_4_1_1.setForeground(Color.WHITE);
		lblUsernameIcon_4_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_1_1.setBounds(252, 451, 64, 58);
		panelMyVehicle.add(lblUsernameIcon_4_1_1);
		
		JLabel lblClientId_1_1_1_1 = new JLabel("Vehicle Plate To Find :");
		lblClientId_1_1_1_1.setForeground(Color.RED);
		lblClientId_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_1_1_1.setBounds(581, 575, 222, 41);
		panelMyVehicle.add(lblClientId_1_1_1_1);
		
		JLabel lblUsernameIcon_4_1_1_1 = new JLabel("");
		lblUsernameIcon_4_1_1_1.setForeground(Color.WHITE);
		lblUsernameIcon_4_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_1_1_1.setBounds(560, 558, 64, 58);
		panelMyVehicle.add(lblUsernameIcon_4_1_1_1);
		
		txtPlate = new JTextField();
		txtPlate.setHorizontalAlignment(SwingConstants.LEFT);
		txtPlate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtPlate.setColumns(10);
		txtPlate.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtPlate.setBackground(Color.WHITE);
		txtPlate.setAlignmentY(0.0f);
		txtPlate.setAlignmentX(0.0f);
		txtPlate.setBounds(806, 575, 170, 40);
		panelMyVehicle.add(txtPlate);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnSearch.setFocusable(false);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchVehicle();
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.RED);
		btnSearch.setBounds(853, 637, 156, 29);
		panelMyVehicle.add(btnSearch);
		
		lblVName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		lblVName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblVName.setBounds(314, 195, 204, 36);
		panelMyVehicle.add(lblVName);
		
		lblDayTo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblDayTo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		lblDayTo.setBounds(314, 462, 204, 36);
		panelMyVehicle.add(lblDayTo);
		
		lblDayFrom.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblDayFrom.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		lblDayFrom.setBounds(314, 333, 204, 36);
		panelMyVehicle.add(lblDayFrom);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\logoVKU2.png"));
		lblNewLabel.setBounds(581, 223, 458, 240);
		panelMyVehicle.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 255));
		panel.setBounds(0, 0, 1426, 70);
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
		lblWelcome.setBounds(1084, 11, 242, 48);
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
		lblClose.setBounds(1345, 11, 46, 33);
		panel.add(lblClose);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(new Color(51, 51, 51));
		MainPanel.setBounds(0, 70, 340, 719);
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
		
		JLabel lblFeatures = new JLabel("Features");
		lblFeatures.setForeground(Color.WHITE);
		lblFeatures.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblFeatures.setBounds(10, 121, 184, 38);
		MainPanel.add(lblFeatures);
		
		JPanel RegisterVehPanel = new JPanel();
		RegisterVehPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRegister.setVisible(true);
				panelChangePassword.setVisible(false);
				panelMyVehicle.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				RegisterVehPanel.setBackground(enter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				RegisterVehPanel.setBackground(exit);
			}
		});
		RegisterVehPanel.setLayout(null);
		RegisterVehPanel.setBackground(Color.DARK_GRAY);
		RegisterVehPanel.setBounds(0, 174, 340, 60);
		MainPanel.add(RegisterVehPanel);
		
		JLabel lblEmployee = new JLabel("   Register Vehicle");
		lblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				RegisterVehPanel.setBackground(enter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				RegisterVehPanel.setBackground(exit);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRegister.setVisible(true);
				panelChangePassword.setVisible(false);
				panelMyVehicle.setVisible(false);
			}
		});
		lblEmployee.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_50px_2.png"));
		lblEmployee.setForeground(Color.WHITE);
		lblEmployee.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblEmployee.setBounds(64, 11, 224, 38);
		RegisterVehPanel.add(lblEmployee);
		
		JPanel VehiclesPanel = new JPanel();
		VehiclesPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRegister.setVisible(false);
				panelChangePassword.setVisible(false);
				panelMyVehicle.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				VehiclesPanel.setBackground(enter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				VehiclesPanel.setBackground(exit);
			}
		});
		VehiclesPanel.setLayout(null);
		VehiclesPanel.setBackground(Color.DARK_GRAY);
		VehiclesPanel.setBounds(0, 288, 340, 60);
		MainPanel.add(VehiclesPanel);
		
		JLabel lblVehicles = new JLabel("    My Vehicles");
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
				panelRegister.setVisible(false);
				panelChangePassword.setVisible(false);
				panelMyVehicle.setVisible(true);
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
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRegister.setVisible(false);
				panelChangePassword.setVisible(true);
				panelMyVehicle.setVisible(false);
			}
		});
		ClientsPanel.setLayout(null);
		ClientsPanel.setBackground(Color.DARK_GRAY);
		ClientsPanel.setBounds(0, 406, 340, 60);
		MainPanel.add(ClientsPanel);
		
		JLabel lblClients = new JLabel("    Change Password");
		lblClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRegister.setVisible(false);
				panelChangePassword.setVisible(true);
				panelMyVehicle.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ClientsPanel.setBackground(enter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ClientsPanel.setBackground(exit);
			}
		});
		lblClients.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_password_50px.png"));
		lblClients.setForeground(Color.WHITE);
		lblClients.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblClients.setBounds(63, 7, 225, 49);
		ClientsPanel.add(lblClients);
		
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
		LogOutPanel.setBounds(0, 539, 340, 60);
		MainPanel.add(LogOutPanel);
		
		JLabel lblLogOut = new JLabel("    Log Out");
		lblLogOut.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\adminIcons\\icons8_Exit_26px_2.png"));
		lblLogOut.setForeground(Color.WHITE);
		lblLogOut.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblLogOut.setBounds(64, 11, 184, 38);
		LogOutPanel.add(lblLogOut);
	}
}
