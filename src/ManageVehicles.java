import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManageVehicles extends JFrame {

	String name,vmodel,plate;
	int vid;
	DefaultTableModel model;
	
	
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtModel;
	private JTextField txtPlate;
	private JTable tableVehicle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageVehicles frame = new ManageVehicles();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
				model = (DefaultTableModel) tableVehicle.getModel();
				model.addRow(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean addVehicle()
	{
		boolean add = false;
		vid = Integer.parseInt(txtID.getText());
		name = txtName.getText();
		vmodel = txtModel.getText();
		plate = txtPlate.getText();
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "insert into vehicle(vid,name,model,plate) values(?,?,?,?) ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,vid);
			pst.setString(2, name);
			pst.setString(3, vmodel);
			pst.setString(4, plate);
			
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
	
	public boolean updateVehicle()
	{
		boolean update = false;
		vid = Integer.parseInt(txtID.getText());
		name = txtName.getText();
		vmodel = txtModel.getText();
		plate = txtPlate.getText();
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "update vehicle set name=?, model=?,plate=? where vid =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, vmodel);
			pst.setString(3, plate);
			pst.setInt(4,vid);
			
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
	
	public boolean deleteVehicle()
	{
		boolean delete=false;
		vid = Integer.parseInt(txtID.getText());
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "delete from vehicle where vid =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, vid);
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
	
	public void clearDataTable()
	{
		DefaultTableModel model = (DefaultTableModel) tableVehicle.getModel();
		model.setRowCount(0);
	}
	
	/**
	 * Create the frame.
	 */
	public ManageVehicles() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1724, 824);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 255));
		panel.setBounds(0, 0, 580, 830);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard db = new Dashboard();
				db.setLocationRelativeTo(null);
				db.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_back_arrow_50px_1.png"));
		lblNewLabel.setBounds(10, 11, 123, 50);
		panel.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setForeground(Color.WHITE);
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtID.setColumns(10);
		txtID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtID.setBackground(new Color(102, 102, 255));
		txtID.setAlignmentY(0.0f);
		txtID.setAlignmentX(0.0f);
		txtID.setBounds(83, 155, 260, 40);
		panel.add(txtID);
		
		JLabel lblName = new JLabel("ID");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblName.setBounds(83, 98, 170, 41);
		panel.add(lblName);
		
		JLabel lblUsernameIcon = new JLabel("");
		lblUsernameIcon.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_identification_documents_50px_4.png"));
		lblUsernameIcon.setForeground(Color.WHITE);
		lblUsernameIcon.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon.setBounds(21, 145, 64, 50);
		panel.add(lblUsernameIcon);
		
		txtName = new JTextField();
		txtName.setForeground(Color.WHITE);
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtName.setColumns(10);
		txtName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtName.setBackground(new Color(102, 102, 255));
		txtName.setAlignmentY(0.0f);
		txtName.setAlignmentX(0.0f);
		txtName.setBounds(83, 284, 260, 40);
		panel.add(txtName);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblName_1.setBounds(83, 227, 170, 41);
		panel.add(lblName_1);
		
		JLabel lblUsernameIcon_1 = new JLabel("");
		lblUsernameIcon_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_name_50px.png"));
		lblUsernameIcon_1.setForeground(Color.WHITE);
		lblUsernameIcon_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_1.setBounds(21, 274, 64, 50);
		panel.add(lblUsernameIcon_1);
		
		txtModel = new JTextField();
		txtModel.setForeground(Color.WHITE);
		txtModel.setHorizontalAlignment(SwingConstants.LEFT);
		txtModel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtModel.setColumns(10);
		txtModel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtModel.setBackground(new Color(102, 102, 255));
		txtModel.setAlignmentY(0.0f);
		txtModel.setAlignmentX(0.0f);
		txtModel.setBounds(83, 445, 260, 40);
		panel.add(txtModel);
		
		JLabel lblEmail = new JLabel("Model");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblEmail.setBounds(83, 388, 170, 41);
		panel.add(lblEmail);
		
		JLabel lblUsernameIcon_2 = new JLabel("");
		lblUsernameIcon_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_car_top_view_50px.png"));
		lblUsernameIcon_2.setForeground(Color.WHITE);
		lblUsernameIcon_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_2.setBounds(21, 435, 64, 50);
		panel.add(lblUsernameIcon_2);
		
		txtPlate = new JTextField();
		txtPlate.setForeground(Color.WHITE);
		txtPlate.setHorizontalAlignment(SwingConstants.LEFT);
		txtPlate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtPlate.setColumns(10);
		txtPlate.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtPlate.setBackground(new Color(102, 102, 255));
		txtPlate.setAlignmentY(0.0f);
		txtPlate.setAlignmentX(0.0f);
		txtPlate.setBounds(83, 610, 260, 40);
		panel.add(txtPlate);
		
		JLabel lblPhoneNumber = new JLabel("Plate");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPhoneNumber.setBounds(83, 553, 170, 41);
		panel.add(lblPhoneNumber);
		
		JLabel lblUsernameIcon_3 = new JLabel("");
		lblUsernameIcon_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_licence_plate_50px.png"));
		lblUsernameIcon_3.setForeground(Color.WHITE);
		lblUsernameIcon_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_3.setBounds(21, 600, 64, 50);
		panel.add(lblUsernameIcon_3);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnAdd.setFocusable(false);
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(addVehicle()==true)
				{
					JOptionPane.showMessageDialog(null, "Vehicle successfully added!");
					clearDataTable();
					insertVehicleDetail();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to add Vehicle!");
				}
			}
		});
		btnAdd.setBounds(31, 730, 89, 23);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnUpdate.setFocusable(false);
		btnUpdate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(updateVehicle()==true)
				{
					JOptionPane.showMessageDialog(null, "Vehicle successfully updated!");
					clearDataTable();
					insertVehicleDetail();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to update Vehicle!");
				}
			}
		});
		btnUpdate.setBounds(183, 730, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFocusable(false);
		btnDelete.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(deleteVehicle()==true)
				{
					JOptionPane.showMessageDialog(null, "Vehicle successfully deleted!");
					clearDataTable();
					insertVehicleDetail();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to delete Vehicle!");
				}
			}
		});
		btnDelete.setBounds(328, 730, 89, 23);
		panel.add(btnDelete);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFocusable(false);
		btnSearch.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnSearch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vid = Integer.parseInt(txtID.getText());
				try {
					Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
					PreparedStatement pst = con.prepareStatement("select * from vehicle where vid =? ");
					pst.setInt(1, vid);
					ResultSet rs = pst.executeQuery();
					if(rs.next())
					{
						txtID.setText(rs.getString("vid"));
						txtName.setText(rs.getString("name"));
						txtModel.setText(rs.getString("model"));
						txtPlate.setText(rs.getString("plate"));
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(465, 730, 89, 23);
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(579, 0, 1135, 824);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(1062, 11, 63, 41);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 200, 723, 335);
		panel_1.add(scrollPane);
		
		tableVehicle = new JTable();
		tableVehicle.getTableHeader().setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        tableVehicle.getTableHeader().setBackground(new Color (102,102,255));
        tableVehicle.getTableHeader().setForeground(new Color (250,250,250));
		tableVehicle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableVehicle.getSelectedRow();
				TableModel model = tableVehicle.getModel();
				
				txtID.setText(model.getValueAt(row, 0).toString());
				txtName.setText(model.getValueAt(row, 1).toString());
				txtModel.setText(model.getValueAt(row, 2).toString());
				txtPlate.setText(model.getValueAt(row, 3).toString());
			}
		});
		tableVehicle.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"VID", "Name", "Model", "Plate"
			}
		));
		insertVehicleDetail();
		tableVehicle.setSelectionBackground(Color.WHITE);
		tableVehicle.setRowHeight(40);
		tableVehicle.setIntercellSpacing(new Dimension(0, 0));
		tableVehicle.setGridColor(new Color(102, 102, 255));
		tableVehicle.setForeground(new Color(0, 112, 192));
		tableVehicle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		tableVehicle.setFocusable(false);
		tableVehicle.setFocusTraversalKeysEnabled(false);
		scrollPane.setViewportView(tableVehicle);
		
		JLabel lblNewLabel_2 = new JLabel("   Manage Vehicles");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_vehicle_insurance_50px.png"));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblNewLabel_2.setBounds(248, 95, 407, 58);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(220, 164, 460, 5);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\logoVKU2.png"));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(556, 563, 569, 237);
		panel_1.add(lblNewLabel_3);
	}
}
