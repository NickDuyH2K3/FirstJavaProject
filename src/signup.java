import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.Statement;

import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class signup extends JFrame {
	private int mouseX,mouseY;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JTextField txtEmail;
	private JTextField txtPhoneNumber;
	private JComboBox optionsBox = new JComboBox();
	/**
	 * Launch the application.
	 */
	
	
	public void insertSignUpDetails()
	{
		String name = txtUsername.getText();
		String password = pwdPassword.getText();
		String email = txtEmail.getText();
		String pnumber = txtPhoneNumber.getText();
		String options = optionsBox.getSelectedItem().toString();
		if(options.equalsIgnoreCase("Admin"))
		{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			java.sql.Statement sm = con.createStatement();
            String sql = "insert into adminAcc(name,password,email,contact) values (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setString(4, pnumber);
            
            int rows = pst.executeUpdate();
    
            if (rows>0) {
            	JOptionPane.showMessageDialog(null, "SUCCESSFUL SIGNUP!");
            	Login log = new Login();
            	log.setLocationRelativeTo(null);
            	log.setVisible(true);
            	dispose();
            }
            else {
            	JOptionPane.showMessageDialog(null,"Failed!");
            };
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else if(options.equalsIgnoreCase("Client"))
		{
			try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			java.sql.Statement sm = con.createStatement();
            String sql = "insert into clientAcc(name,password,email,contact) values (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setString(4, pnumber);
            
            int rows = pst.executeUpdate();
    
            if (rows>0) {
            	JOptionPane.showMessageDialog(null, "Successful Signup!");
            	Login log = new Login();
            	log.setLocationRelativeTo(null);
            	log.setVisible(true);
            	dispose();
            }
            else {
            	JOptionPane.showMessageDialog(null,"Failed!");
            };
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
	}
	public boolean checkSignUp()
	{
		String name = txtUsername.getText();
		String password = pwdPassword.getText();
		String email = txtEmail.getText();
		String pnumber = txtPhoneNumber.getText();
		String options = optionsBox.getSelectedItem().toString();
		
		if (name.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter Username!");
			return false;
		}
		if (password.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter Password!");
			return false;
		}
		if (email.equals("")|| !email.matches("^.+@.+\\..+$"))
		{
			JOptionPane.showMessageDialog(null,"Please enter correct Email!");
			return false;
		}
		if (pnumber.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter Phone Number!");
			return false;
		}
		if(options.equals("Select"))
		{
			JOptionPane.showMessageDialog(null,"Please choose a type of register!");
			return false;
		}
		return true;
	}
	
	public boolean checkDupUser()
	{
		String name = txtUsername.getText();
		boolean check = false;
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			PreparedStatement pst = con.prepareStatement("select * from users where name=?");
			pst.setString(1, name);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				check = true;
			}
			else
			{
				check=false;
			}
	} catch (Exception e) {
			
		}
		return check;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public signup() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 828);
		ImageIcon image = new ImageIcon("icon.png");
		this.setIconImage(image.getImage());
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 990, 830);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Duy Hoang");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 162, 41);
		panel.add(lblNewLabel);
		
		JLabel lblWelcome = new JLabel("Welcome To");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Sitka Display", Font.PLAIN, 35));
		lblWelcome.setBounds(319, 63, 209, 41);
		panel.add(lblWelcome);
		
		JLabel lblCarManagementSystem = new JLabel("Car Management System");
		lblCarManagementSystem.setForeground(Color.RED);
		lblCarManagementSystem.setFont(new Font("Sitka Display", Font.BOLD, 45));
		lblCarManagementSystem.setBounds(200, 98, 503, 70);
		panel.add(lblCarManagementSystem);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\DNC\\Downloads\\ahmed-hasan-bugatti-bolide-ahmed-hasan-1-1 (1).jpg"));
		lblNewLabel_1.setBounds(0, 0, 990, 830);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(102, 102, 255));
		panel_1.setBounds(991, 0, 532, 828);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLogin = new JLabel("Signup Page");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		lblLogin.setBounds(209, 29, 170, 41);
		panel_1.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsername.setBounds(72, 113, 170, 41);
		panel_1.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(Color.WHITE);
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(checkDupUser()==true)
				{
					JOptionPane.showMessageDialog(null, "This Username Already Exist!");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				txtUsername.setText("");
			}
		});
		txtUsername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtUsername.setAlignmentY(Component.TOP_ALIGNMENT);
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtUsername.setBackground(new Color(102, 102, 255));
		txtUsername.setBounds(72, 170, 260, 40);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsernameIcon = new JLabel("");
		lblUsernameIcon.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\icons\\icons8_Account_50px.png"));
		lblUsernameIcon.setForeground(Color.WHITE);
		lblUsernameIcon.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon.setBounds(10, 160, 64, 50);
		panel_1.add(lblUsernameIcon);
		
		JLabel lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\icons\\icons8_Secure_50px.png"));
		lblPasswordIcon.setForeground(Color.WHITE);
		lblPasswordIcon.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPasswordIcon.setBounds(10, 268, 64, 50);
		panel_1.add(lblPasswordIcon);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPassword.setBounds(72, 221, 170, 41);
		panel_1.add(lblPassword);
		
		JButton btnSignup = new JButton("SIGNUP");
		btnSignup.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnSignup.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSignup.setFocusable(false);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkSignUp()==true)
				{
					if(checkDupUser()==false)
					{
						insertSignUpDetails();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "This Username Already Exist!");
					}
				}
			}
		});
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setBackground(Color.RED);
		btnSignup.setBounds(245, 721, 156, 29);
		panel_1.add(btnSignup);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setForeground(Color.WHITE);
		pwdPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		pwdPassword.setOpaque(false);
		pwdPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		pwdPassword.setBounds(72, 305, 260, 29);
		panel_1.add(pwdPassword);
		
		JLabel exitButton = new JLabel("X");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		exitButton.setHorizontalAlignment(SwingConstants.CENTER);
		exitButton.setForeground(Color.WHITE);
		exitButton.setBounds(460, 11, 52, 29);
		panel_1.add(exitButton);
		
		JLabel lblNewLabel_3 = new JLabel("Create New Account");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(187, 73, 192, 29);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblEmail.setBounds(72, 371, 170, 41);
		panel_1.add(lblEmail);
		
		JLabel lblEmailIcon = new JLabel("");
		lblEmailIcon.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\icons\\icons8_Secured_Letter_50px.png"));
		lblEmailIcon.setForeground(Color.WHITE);
		lblEmailIcon.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblEmailIcon.setBounds(10, 418, 64, 50);
		panel_1.add(lblEmailIcon);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPhoneNumber.setBounds(72, 534, 170, 41);
		panel_1.add(lblPhoneNumber);
		
		JLabel lblPasswordIcon_1_1 = new JLabel("");
		lblPasswordIcon_1_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\icons\\icons8_Google_Mobile_50px.png"));
		lblPasswordIcon_1_1.setForeground(Color.WHITE);
		lblPasswordIcon_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPasswordIcon_1_1.setBounds(10, 581, 64, 50);
		panel_1.add(lblPasswordIcon_1_1);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLogin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login log = new Login();
				log.setLocationRelativeTo(null);
				log.setVisible(true);
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(51, 51, 255));
		btnLogin.setBounds(245, 776, 156, 29);
		panel_1.add(btnLogin);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtEmail.setBackground(new Color(102, 102, 255));
		txtEmail.setAlignmentY(0.0f);
		txtEmail.setAlignmentX(0.0f);
		txtEmail.setBounds(72, 423, 260, 40);
		panel_1.add(txtEmail);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setForeground(Color.WHITE);
		txtPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
		txtPhoneNumber.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtPhoneNumber.setBackground(new Color(102, 102, 255));
		txtPhoneNumber.setAlignmentY(0.0f);
		txtPhoneNumber.setAlignmentX(0.0f);
		txtPhoneNumber.setBounds(72, 603, 260, 40);
		panel_1.add(txtPhoneNumber);
		
		JLabel lblNewLabel_2 = new JLabel("Register As:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(270, 672, 145, 22);
		panel_1.add(lblNewLabel_2);
		
		optionsBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		optionsBox.setBackground(Color.WHITE);
		optionsBox.setForeground(Color.BLACK);
		optionsBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		optionsBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Client"}));
		optionsBox.setBounds(404, 672, 108, 29);
		panel_1.add(optionsBox);
		ImageIcon background = new ImageIcon(this.getClass().getResource("back.jpg"));
		ImageIcon userIcon = new ImageIcon(this.getClass().getResource("user.png"));
		ImageIcon passIcon = new ImageIcon(this.getClass().getResource("pass.png"));
		ImageIcon adminIcon = new ImageIcon(this.getClass().getResource("admin.png"));
	}
	}
