import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;

public class ManageClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtCID;
	private JTextField txtVID;
	private JTextField txtCName;
	private JTextField txtCPN;
	int cid,vid;
	String cname , cpn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageClient frame = new ManageClient();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean checkAddClient()
	{
		boolean check = true;
		String cid2 = txtCID.getText();
		cname = txtCName.getText();
		String vid2 = txtVID.getText();
		cpn = txtCPN.getText();
		if(cid2.equals(""))
		{
			check = false;
		}
		if(cname.equals(""))
		{
			check = false;
		}
		if(vid2.equals(""))
		{
			check = false;
		}
		if(cpn.equals(""))
		{
			check = false;
		}
		return check;
	}
	
	public boolean addClient()
	{
		boolean add = false;
		cid = Integer.parseInt(txtCID.getText());
		cname = txtCName.getText();
		vid = Integer.parseInt(txtVID.getText());
		cpn = txtCPN.getText();
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "insert into client values(?,?,?,?) ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,cid);
			pst.setString(2, cname);
			pst.setString(3, cpn);
			pst.setInt(4, vid);
			
			int row = pst.executeUpdate();
			if(row>0)
			{
				add = true;
			}
			else {
				add=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return add;
	}
	
	
	public boolean updateClient()
	{
		boolean update = false;
		cid = Integer.parseInt(txtCID.getText());
		cname = txtCName.getText();
		vid = Integer.parseInt(txtVID.getText());
		cpn = txtCPN.getText();
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "update client set name=?, pnumber=?,vid=? where cid =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cname);
			pst.setString(2, cpn);
			pst.setInt(3, vid);
			pst.setInt(4,cid);
			
			int row = pst.executeUpdate();
			if(row>0)
			{
				update = true;
			}
			else {
				update=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}
	
	public boolean deleteClient()
	{
		boolean delete=false;
		cid = Integer.parseInt(txtCID.getText());
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "delete from client where cid =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cid);
			int row = pst.executeUpdate();
			if(row>0)
			{
				delete=true;
			}
			else
			{
				delete=false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return delete;
	}
	
	/**
	 * Create the frame.
	 */
	public ManageClient() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1400, 800);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(0, 0, 420, 810);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard db = new Dashboard();
				db.setLocationRelativeTo(null);
				db.setVisible(true);
				dispose();
			}
		});
		lblBack.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_back_arrow_50px_1.png"));
		lblBack.setBounds(0, 0, 85, 50);
		panel_1.add(lblBack);
		
		JLabel lblClientDetail = new JLabel("   Client Detail");
		lblClientDetail.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_name_50px.png"));
		lblClientDetail.setForeground(Color.WHITE);
		lblClientDetail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblClientDetail.setBounds(71, 80, 266, 50);
		panel_1.add(lblClientDetail);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(54, 129, 300, 5);
		panel_1.add(panel_2);
		
		JLabel lblClientId = new JLabel("Client ID");
		lblClientId.setForeground(Color.WHITE);
		lblClientId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId.setBounds(72, 174, 170, 41);
		panel_1.add(lblClientId);
		
		JLabel lblUsernameIcon = new JLabel("");
		lblUsernameIcon.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_identification_documents_50px_4.png"));
		lblUsernameIcon.setForeground(Color.WHITE);
		lblUsernameIcon.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon.setBounds(10, 221, 64, 50);
		panel_1.add(lblUsernameIcon);
		
		JLabel lblID = new JLabel("");
		lblID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblID.setBounds(87, 226, 230, 38);
		panel_1.add(lblID);
		
		JLabel lblClientN = new JLabel("");
		lblClientN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblClientN.setBounds(87, 358, 230, 38);
		panel_1.add(lblClientN);
		
		JLabel lblUsernameIcon_1 = new JLabel("");
		lblUsernameIcon_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\male_user_50px.png"));
		lblUsernameIcon_1.setForeground(Color.WHITE);
		lblUsernameIcon_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_1.setBounds(10, 353, 64, 50);
		panel_1.add(lblUsernameIcon_1);
		
		JLabel lblClientName = new JLabel("Client Name");
		lblClientName.setForeground(Color.WHITE);
		lblClientName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientName.setBounds(72, 306, 170, 41);
		panel_1.add(lblClientName);
		
		JLabel lblClientPN = new JLabel("");
		lblClientPN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblClientPN.setBounds(87, 512, 230, 38);
		panel_1.add(lblClientPN);
		
		JLabel lblUsernameIcon_2 = new JLabel("");
		lblUsernameIcon_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_phone_50px_1.png"));
		lblUsernameIcon_2.setForeground(Color.WHITE);
		lblUsernameIcon_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_2.setBounds(10, 507, 64, 50);
		panel_1.add(lblUsernameIcon_2);
		
		JLabel lblClientPhoneNumber = new JLabel("Client Phone Number");
		lblClientPhoneNumber.setForeground(Color.WHITE);
		lblClientPhoneNumber.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientPhoneNumber.setBounds(72, 460, 245, 41);
		panel_1.add(lblClientPhoneNumber);
		
		JLabel lblClientVID = new JLabel("");
		lblClientVID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblClientVID.setBounds(87, 673, 230, 38);
		panel_1.add(lblClientVID);
		
		JLabel lblUsernameIcon_2_1 = new JLabel("");
		lblUsernameIcon_2_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_loyalty_card_50px.png"));
		lblUsernameIcon_2_1.setForeground(Color.WHITE);
		lblUsernameIcon_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_2_1.setBounds(10, 668, 64, 50);
		panel_1.add(lblUsernameIcon_2_1);
		
		JLabel lblClientVehicleId = new JLabel("Client Vehicle ID");
		lblClientVehicleId.setForeground(Color.WHITE);
		lblClientVehicleId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientVehicleId.setBounds(72, 621, 245, 41);
		panel_1.add(lblClientVehicleId);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(Color.WHITE);
		panel_2_2.setBounds(80, 260, 250, 5);
		panel_1.add(panel_2_2);
		
		JPanel panel_2_2_1 = new JPanel();
		panel_2_2_1.setBackground(Color.WHITE);
		panel_2_2_1.setBounds(80, 391, 250, 5);
		panel_1.add(panel_2_2_1);
		
		JPanel panel_2_2_2 = new JPanel();
		panel_2_2_2.setBackground(Color.WHITE);
		panel_2_2_2.setBounds(80, 550, 250, 5);
		panel_1.add(panel_2_2_2);
		
		JPanel panel_2_2_3 = new JPanel();
		panel_2_2_3.setBackground(Color.WHITE);
		panel_2_2_3.setBounds(80, 706, 250, 5);
		panel_1.add(panel_2_2_3);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(102,102,255));
		panel_1_1.setBounds(475, 0, 420, 810);
		panel.add(panel_1_1);
		
		JLabel lblClientVehicleDetail = new JLabel("Client Vehicle Detail");
		lblClientVehicleDetail.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_insurance_50px_2.png"));
		lblClientVehicleDetail.setForeground(Color.WHITE);
		lblClientVehicleDetail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblClientVehicleDetail.setBounds(54, 80, 300, 50);
		panel_1_1.add(lblClientVehicleDetail);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(54, 129, 300, 5);
		panel_1_1.add(panel_2_1);
		
		JLabel lblVehicleId = new JLabel("Vehicle ID");
		lblVehicleId.setForeground(Color.WHITE);
		lblVehicleId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblVehicleId.setBounds(72, 174, 170, 41);
		panel_1_1.add(lblVehicleId);
		
		JLabel lblUsernameIcon_3 = new JLabel("");
		lblUsernameIcon_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_identification_documents_50px_4.png"));
		lblUsernameIcon_3.setForeground(Color.WHITE);
		lblUsernameIcon_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_3.setBounds(10, 221, 64, 50);
		panel_1_1.add(lblUsernameIcon_3);
		
		JLabel lblVID = new JLabel("");
		lblVID.setForeground(Color.WHITE);
		lblVID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblVID.setBounds(87, 226, 230, 38);
		panel_1_1.add(lblVID);
		
		JLabel lblVName = new JLabel("");
		lblVName.setForeground(Color.WHITE);
		lblVName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblVName.setBounds(87, 358, 230, 38);
		panel_1_1.add(lblVName);
		
		JLabel lblUsernameIcon_1_1 = new JLabel("");
		lblUsernameIcon_1_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_name_tag_50px_2.png"));
		lblUsernameIcon_1_1.setForeground(Color.WHITE);
		lblUsernameIcon_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_1_1.setBounds(10, 353, 64, 50);
		panel_1_1.add(lblUsernameIcon_1_1);
		
		JLabel lblVehicleName = new JLabel("Vehicle Name");
		lblVehicleName.setForeground(Color.WHITE);
		lblVehicleName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblVehicleName.setBounds(72, 306, 170, 41);
		panel_1_1.add(lblVehicleName);
		
		JLabel lblVModel = new JLabel("");
		lblVModel.setForeground(Color.WHITE);
		lblVModel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblVModel.setBounds(87, 512, 230, 38);
		panel_1_1.add(lblVModel);
		
		JLabel lblUsernameIcon_2_2 = new JLabel("");
		lblUsernameIcon_2_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_top_view_50px.png"));
		lblUsernameIcon_2_2.setForeground(Color.WHITE);
		lblUsernameIcon_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_2_2.setBounds(10, 507, 64, 50);
		panel_1_1.add(lblUsernameIcon_2_2);
		
		JLabel lblVehicleModel = new JLabel("Vehicle Model");
		lblVehicleModel.setForeground(Color.WHITE);
		lblVehicleModel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblVehicleModel.setBounds(72, 460, 245, 41);
		panel_1_1.add(lblVehicleModel);
		
		JLabel lblVPlate = new JLabel("");
		lblVPlate.setForeground(Color.WHITE);
		lblVPlate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblVPlate.setBounds(87, 673, 230, 38);
		panel_1_1.add(lblVPlate);
		
		JLabel lblUsernameIcon_2_1_1 = new JLabel("");
		lblUsernameIcon_2_1_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_licence_plate_50px.png"));
		lblUsernameIcon_2_1_1.setForeground(Color.WHITE);
		lblUsernameIcon_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_2_1_1.setBounds(10, 668, 64, 50);
		panel_1_1.add(lblUsernameIcon_2_1_1);
		
		JLabel lblVehiclePlate = new JLabel("Vehicle Plate");
		lblVehiclePlate.setForeground(Color.WHITE);
		lblVehiclePlate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblVehiclePlate.setBounds(72, 621, 245, 41);
		panel_1_1.add(lblVehiclePlate);
		
		JPanel panel_2_2_4 = new JPanel();
		panel_2_2_4.setBackground(Color.WHITE);
		panel_2_2_4.setBounds(72, 260, 250, 5);
		panel_1_1.add(panel_2_2_4);
		
		JPanel panel_2_2_1_1 = new JPanel();
		panel_2_2_1_1.setBackground(Color.WHITE);
		panel_2_2_1_1.setBounds(72, 391, 250, 5);
		panel_1_1.add(panel_2_2_1_1);
		
		JPanel panel_2_2_2_1 = new JPanel();
		panel_2_2_2_1.setBackground(Color.WHITE);
		panel_2_2_2_1.setBounds(72, 550, 250, 5);
		panel_1_1.add(panel_2_2_2_1);
		
		JPanel panel_2_2_3_1 = new JPanel();
		panel_2_2_3_1.setBackground(Color.WHITE);
		panel_2_2_3_1.setBounds(72, 706, 250, 5);
		panel_1_1.add(panel_2_2_3_1);
		
		JLabel lblManageClient = new JLabel("Manage Client");
		lblManageClient.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_search_client_50px_1.png"));
		lblManageClient.setForeground(Color.RED);
		lblManageClient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblManageClient.setBounds(1045, 85, 300, 50);
		panel.add(lblManageClient);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.RED);
		panel_2_1_1.setBounds(1018, 142, 300, 5);
		panel.add(panel_2_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		lblNewLabel_1.setBounds(1337, 0, 63, 41);
		panel.add(lblNewLabel_1);
		
		JLabel lblClientId_1 = new JLabel("Client ID");
		lblClientId_1.setForeground(Color.RED);
		lblClientId_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1.setBounds(1009, 210, 170, 41);
		panel.add(lblClientId_1);
		
		JLabel lblUsernameIcon_4 = new JLabel("");
		lblUsernameIcon_4.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_loyalty_card_50px_1.png"));
		lblUsernameIcon_4.setForeground(Color.WHITE);
		lblUsernameIcon_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4.setBounds(947, 257, 64, 50);
		panel.add(lblUsernameIcon_4);
		
		txtCID = new JTextField();
		txtCID.setHorizontalAlignment(SwingConstants.LEFT);
		txtCID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtCID.setColumns(10);
		txtCID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtCID.setBackground(Color.WHITE);
		txtCID.setAlignmentY(0.0f);
		txtCID.setAlignmentX(0.0f);
		txtCID.setBounds(1009, 267, 260, 40);
		panel.add(txtCID);
		
		JLabel lblName_1 = new JLabel("Vehicle ID");
		lblName_1.setForeground(Color.RED);
		lblName_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblName_1.setBounds(1009, 339, 170, 41);
		panel.add(lblName_1);
		
		txtVID = new JTextField();
		txtVID.setHorizontalAlignment(SwingConstants.LEFT);
		txtVID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtVID.setColumns(10);
		txtVID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtVID.setBackground(Color.WHITE);
		txtVID.setAlignmentY(0.0f);
		txtVID.setAlignmentX(0.0f);
		txtVID.setBounds(1009, 396, 260, 40);
		panel.add(txtVID);
		
		JLabel lblUsernameIcon_1_2 = new JLabel("");
		lblUsernameIcon_1_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_insurance_50px_3.png"));
		lblUsernameIcon_1_2.setForeground(Color.WHITE);
		lblUsernameIcon_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_1_2.setBounds(947, 386, 64, 50);
		panel.add(lblUsernameIcon_1_2);
		
		JLabel lblClientId_1_1 = new JLabel("Client Name");
		lblClientId_1_1.setForeground(Color.RED);
		lblClientId_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_1.setBounds(1009, 489, 170, 41);
		panel.add(lblClientId_1_1);
		
		JLabel lblUsernameIcon_4_1 = new JLabel("");
		lblUsernameIcon_4_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_management_70px.png"));
		lblUsernameIcon_4_1.setForeground(Color.WHITE);
		lblUsernameIcon_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_1.setBounds(935, 518, 64, 58);
		panel.add(lblUsernameIcon_4_1);
		
		txtCName = new JTextField();
		txtCName.setHorizontalAlignment(SwingConstants.LEFT);
		txtCName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtCName.setColumns(10);
		txtCName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtCName.setBackground(Color.WHITE);
		txtCName.setAlignmentY(0.0f);
		txtCName.setAlignmentX(0.0f);
		txtCName.setBounds(1009, 540, 260, 40);
		panel.add(txtCName);
		
		JLabel lblClientId_1_2 = new JLabel("Client Phone Number");
		lblClientId_1_2.setForeground(Color.RED);
		lblClientId_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblClientId_1_2.setBounds(1009, 614, 246, 41);
		panel.add(lblClientId_1_2);
		
		JLabel lblUsernameIcon_4_2 = new JLabel("");
		lblUsernameIcon_4_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_cell_phone_50px.png"));
		lblUsernameIcon_4_2.setForeground(Color.WHITE);
		lblUsernameIcon_4_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_4_2.setBounds(947, 661, 64, 50);
		panel.add(lblUsernameIcon_4_2);
		
		txtCPN = new JTextField();
		txtCPN.setHorizontalAlignment(SwingConstants.LEFT);
		txtCPN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtCPN.setColumns(10);
		txtCPN.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
		txtCPN.setBackground(Color.WHITE);
		txtCPN.setAlignmentY(0.0f);
		txtCPN.setAlignmentX(0.0f);
		txtCPN.setBounds(1009, 671, 260, 40);
		panel.add(txtCPN);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnAdd.setFocusable(false);
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkAddClient()==true)
				{
					if(addClient()==true)
					{
					JOptionPane.showMessageDialog(null, "Successfully added client!");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to add client!");
				}
			}
		});
		btnAdd.setBounds(919, 750, 89, 23);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnUpdate.setFocusable(false);
		btnUpdate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(updateClient()==true)
				{
					JOptionPane.showMessageDialog(null, "Successfully updated client!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to update client!");
				}
			}
		});
		btnUpdate.setBounds(1045, 750, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFocusable(false);
		btnDelete.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(deleteClient()==true)
				{
					JOptionPane.showMessageDialog(null, "Successfully deleted client!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to delete client!");
				}
			}
		});
		btnDelete.setBounds(1180, 750, 89, 23);
		panel.add(btnDelete);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cid = Integer.parseInt(txtCID.getText());
				try {
					Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
					PreparedStatement pst = con.prepareStatement("select * from client where cid =? ");
					PreparedStatement pst2 = con.prepareStatement("select vehicle.vid,vehicle.name,model,plate from client inner join vehicle on client.cid = vehicle.cid where client.cid = ?");
					pst.setInt(1, cid);
					pst2.setInt(1, cid);
					ResultSet rs = pst.executeQuery();
					ResultSet rs2 = pst2.executeQuery();
					if(rs.next())
					{
						lblID.setText(rs.getString("cid"));
						lblClientN.setText(rs.getString("name"));
						lblClientPN.setText(rs.getString("pnumber"));
						lblClientVID.setText(rs.getString("vid"));
					}
					if(rs2.next())
					{
						lblVID.setText(rs2.getString("vid"));
						lblVName.setText(rs2.getString("name"));
						lblVModel.setText(rs2.getString("model"));
						lblVPlate.setText(rs2.getString("plate"));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Not Found!");
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(1301, 750, 89, 23);
		panel.add(btnSearch);
	}
}
