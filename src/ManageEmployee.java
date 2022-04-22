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

public class ManageEmployee extends JFrame {

	String name,email,pnumber;
	int id;
	DefaultTableModel model;
	
	
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPNumber;
	private JTable tableEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageEmployee frame = new ManageEmployee();
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
				model = (DefaultTableModel) tableEmployee.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean addEmployee()
	{
		boolean add = false;
		id = Integer.parseInt(txtID.getText());
		name = txtName.getText();
		email = txtEmail.getText();
		pnumber = txtPNumber.getText();
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "insert into employee values(?,?,?,?) ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2, name);
			pst.setString(3, email);
			pst.setString(4, pnumber);
			
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
	
	public boolean updateEmployee()
	{
		boolean update = false;
		id = Integer.parseInt(txtID.getText());
		name = txtName.getText();
		email = txtEmail.getText();
		pnumber = txtPNumber.getText();
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "update employee set name=?, email=?,pnumber=? where id =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, pnumber);
			pst.setInt(4,id);
			
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
	
	public boolean deleteEmployee()
	{
		boolean delete=false;
		id = Integer.parseInt(txtID.getText());
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "delete from employee where id =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
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
		DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
		model.setRowCount(0);
	}
	
	/**
	 * Create the frame.
	 */
	public ManageEmployee() {
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
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtEmail.setBackground(new Color(102, 102, 255));
		txtEmail.setAlignmentY(0.0f);
		txtEmail.setAlignmentX(0.0f);
		txtEmail.setBounds(83, 445, 260, 40);
		panel.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblEmail.setBounds(83, 388, 170, 41);
		panel.add(lblEmail);
		
		JLabel lblUsernameIcon_2 = new JLabel("");
		lblUsernameIcon_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_email_sign_50px_1.png"));
		lblUsernameIcon_2.setForeground(Color.WHITE);
		lblUsernameIcon_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_2.setBounds(21, 435, 64, 50);
		panel.add(lblUsernameIcon_2);
		
		txtPNumber = new JTextField();
		txtPNumber.setForeground(Color.WHITE);
		txtPNumber.setHorizontalAlignment(SwingConstants.LEFT);
		txtPNumber.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		txtPNumber.setColumns(10);
		txtPNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtPNumber.setBackground(new Color(102, 102, 255));
		txtPNumber.setAlignmentY(0.0f);
		txtPNumber.setAlignmentX(0.0f);
		txtPNumber.setBounds(83, 610, 260, 40);
		panel.add(txtPNumber);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPhoneNumber.setBounds(83, 553, 170, 41);
		panel.add(lblPhoneNumber);
		
		JLabel lblUsernameIcon_3 = new JLabel("");
		lblUsernameIcon_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_phone_50px_1.png"));
		lblUsernameIcon_3.setForeground(Color.WHITE);
		lblUsernameIcon_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblUsernameIcon_3.setBounds(21, 600, 64, 50);
		panel.add(lblUsernameIcon_3);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFocusable(false);
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(addEmployee()==true)
				{
					JOptionPane.showMessageDialog(null, "Employee successfully added!");
					clearDataTable();
					insertEmployeeDetail();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to add Employee!");
				}
			}
		});
		btnAdd.setBounds(31, 730, 89, 23);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFocusable(false);
		btnUpdate.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnUpdate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(updateEmployee()==true)
				{
					JOptionPane.showMessageDialog(null, "Employee successfully updated!");
					clearDataTable();
					insertEmployeeDetail();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to update Employee!");
				}
			}
		});
		btnUpdate.setBounds(183, 730, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		btnDelete.setFocusable(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(deleteEmployee()==true)
				{
					JOptionPane.showMessageDialog(null, "Employee successfully deleted!");
					clearDataTable();
					insertEmployeeDetail();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed to delete Employee!");
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
					id = Integer.parseInt(txtID.getText());
					try {
						Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
						PreparedStatement pst = con.prepareStatement("select * from employee where id =? ");
						pst.setInt(1, id);
						ResultSet rs = pst.executeQuery();
						if(rs.next())
						{
							txtID.setText(rs.getString("id"));
							txtName.setText(rs.getString("name"));
							txtEmail.setText(rs.getString("email"));
							txtPNumber.setText(rs.getString("pnumber"));
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
		scrollPane.setBounds(68, 241, 713, 308);
		panel_1.add(scrollPane);
		
		tableEmployee = new JTable();
		tableEmployee.getTableHeader().setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        tableEmployee.getTableHeader().setBackground(new Color (102,102,255));
        tableEmployee.getTableHeader().setForeground(new Color (250,250,250));
		tableEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableEmployee.getSelectedRow();
				TableModel model = tableEmployee.getModel();
				
				txtID.setText(model.getValueAt(row, 0).toString());
				txtName.setText(model.getValueAt(row, 1).toString());
				txtEmail.setText(model.getValueAt(row, 2).toString());
				txtPNumber.setText(model.getValueAt(row, 3).toString());
			}
		});
		tableEmployee.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Email", "Phone Number"
			}
		));
		insertEmployeeDetail();
		tableEmployee.setSelectionBackground(Color.WHITE);
		tableEmployee.setRowHeight(40);
		tableEmployee.setIntercellSpacing(new Dimension(0, 0));
		tableEmployee.setGridColor(new Color(102, 102, 255));
		tableEmployee.setForeground(new Color(0, 112, 192));
		tableEmployee.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		tableEmployee.setFocusable(false);
		tableEmployee.setFocusTraversalKeysEnabled(false);
		scrollPane.setViewportView(tableEmployee);
		
		JLabel lblNewLabel_2 = new JLabel("   Manage Employee");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_management_70px.png"));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblNewLabel_2.setBounds(248, 95, 407, 58);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(220, 164, 460, 5);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\logoVKU2.png"));
		lblNewLabel_3.setBounds(556, 560, 569, 237);
		panel_1.add(lblNewLabel_3);
	}
}
