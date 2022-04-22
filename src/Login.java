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

public class Login extends JFrame {
	private int mouseX,mouseY;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	/**
	 * Launch the application.
	 */
	
	
	public void login()
	{
		String name = txtUsername.getText();
		String password = pwdPassword.getText();
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			PreparedStatement pst = con.prepareStatement("select * from adminAcc where name= ? and password = ?");
			PreparedStatement pst2 = con.prepareStatement("select * from clientAcc where name= ? and password = ?");
			pst.setString(1, name);
			pst.setString(2, password);
			pst2.setString(1, name);
			pst2.setString(2, password);
			ResultSet rs = pst.executeQuery();
			ResultSet rs2 = pst2.executeQuery();
            if (rs.next()) {
            	JOptionPane.showMessageDialog(null,"LOGIN SUCCESSFUL!");
            		Dashboard db = new Dashboard();
            		db.setLocationRelativeTo(null);
            		db.setVisible(true);
            		dispose();	
            }
            else if(rs2.next())
            {
            	JOptionPane.showMessageDialog(null,"LOGIN SUCCESSFUL!");
        		Client cl = new Client();
        		cl.setLocationRelativeTo(null);
        		cl.setVisible(true);
        		dispose();
            }
            else {
            	JOptionPane.showMessageDialog(null,"Invalid username or password!");
            };
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "")
			e.printStackTrace();
		}
	}
	
	public boolean checkLogin()
	{
		String name = txtUsername.getText();
		String password = pwdPassword.getText();
		
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
		return true;
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
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
		lblNewLabel.setBounds(0, 0, 162, 41);
		panel.add(lblNewLabel);
		
		JLabel lblWelcome = new JLabel("Welcome To");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Sitka Display", Font.PLAIN, 35));
		lblWelcome.setBounds(321, 63, 207, 41);
		panel.add(lblWelcome);
		
		JLabel lblCarManagementSystem = new JLabel("Car Management System");
		lblCarManagementSystem.setForeground(Color.RED);
		lblCarManagementSystem.setFont(new Font("Sitka Display", Font.BOLD, 45));
		lblCarManagementSystem.setBounds(200, 81, 518, 101);
		panel.add(lblCarManagementSystem);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\DNC\\Downloads\\ahmed-hasan-bugatti-bolide-ahmed-hasan-1-1 (1).jpg"));
		lblNewLabel_1.setBounds(0, 0, 1001, 830);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		panel_1.setFocusable(false);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(102, 102, 255));
		panel_1.setBounds(990, 0, 533, 828);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN PAGE");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		lblLogin.setBounds(209, 61, 170, 41);
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
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		txtUsername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtUsername.setAlignmentY(Component.TOP_ALIGNMENT);
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtUsername.setBackground(new Color(102, 102, 255));
		txtUsername.setBounds(72, 170, 291, 40);
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
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFocusable(false);
		btnLogin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnLogin.setRequestFocusEnabled(false);
		btnLogin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkLogin()==true)
				{
				login();
				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.RED);
		btnLogin.setBounds(324, 367, 170, 34);
		panel_1.add(btnLogin);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setForeground(Color.WHITE);
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		pwdPassword.setOpaque(false);
		pwdPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		pwdPassword.setBounds(72, 300, 291, 34);
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
		
		JLabel lblNewLabel_2 = new JLabel("Create New Account?");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				signup su = new signup();
				su.setLocationRelativeTo(null);
				su.setVisible(true);
			}
		});
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel_2.setBounds(313, 412, 210, 29);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\admin.png"));
		lblNewLabel_3.setBounds(189, 11, 170, 51);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\logoVKU2.png"));
		lblNewLabel_4.setBounds(60, 542, 441, 229);
		panel_1.add(lblNewLabel_4);
		ImageIcon background = new ImageIcon(this.getClass().getResource("back.jpg"));
		ImageIcon userIcon = new ImageIcon(this.getClass().getResource("user.png"));
		ImageIcon passIcon = new ImageIcon(this.getClass().getResource("pass.png"));
		ImageIcon adminIcon = new ImageIcon(this.getClass().getResource("admin.png"));
	}
	}
