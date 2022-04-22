import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
public class ViewRecord extends JFrame {

	private JPanel contentPane;
	private JComboBox dayCB = new JComboBox();
	private JComboBox MonthCB = new JComboBox();
	private JComboBox YearCB = new JComboBox();
	private JComboBox dayCB_1 = new JComboBox();
	private JComboBox MonthCB_1 = new JComboBox();
	private JComboBox YearCB_1 = new JComboBox();
	DefaultTableModel model;
	private JTable tableVehicleRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRecord frame = new ViewRecord();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void insertRecordDetail()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			Statement st =con.createStatement();
			ResultSet rs = st.executeQuery("select * from vehicleRegister");
			
			while(rs.next())
			{
				String vname = rs.getString("vname");
				String from = rs.getString("froms");
				String to = rs.getString("tos");
				String vplate = rs.getString("vplate");
				
				Object[] obj = {vname,from,to,vplate};
				model = (DefaultTableModel) tableVehicleRegister.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearDataTable()
	{
		DefaultTableModel model = (DefaultTableModel) tableVehicleRegister.getModel();
		model.setRowCount(0);
	}
	
	public void searchRecord()
	{
		int day = Integer.parseInt(dayCB.getSelectedItem().toString());
		int month = Integer.parseInt(MonthCB.getSelectedItem().toString());
		int year = Integer.parseInt(YearCB.getSelectedItem().toString());
		int day2 = Integer.parseInt(dayCB_1.getSelectedItem().toString());
		int month2 = Integer.parseInt(MonthCB_1.getSelectedItem().toString());
		int year2 = Integer.parseInt(YearCB_1.getSelectedItem().toString());
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://Duy;databaseName=DemoDB","sa","duybroso");
			String sql = "select * from vehicleRegister\r\n"
					+ "where day(froms) between ? and ? and month(froms) between ? and ? and year(froms) between ? and ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, day);
			pst.setInt(2, day2);
			pst.setInt(3, month);
			pst.setInt(4, month2);
			pst.setInt(5, year);
			pst.setInt(6, year2);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
				{
					String vname = rs.getString("vname");
					String from = rs.getString("froms");
					String to = rs.getString("tos");
					String vplate = rs.getString("vplate");
					
					Object[] obj = {vname,from,to,vplate};
					model = (DefaultTableModel) tableVehicleRegister.getModel();
					model.addRow(obj);
				}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public ViewRecord() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1480, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102,102,255));
		panel.setBounds(0, 0, 1480, 280);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblViewRecord = new JLabel("View Record");
		lblViewRecord.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_log_50px.png"));
		lblViewRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewRecord.setForeground(Color.WHITE);
		lblViewRecord.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblViewRecord.setBounds(602, 25, 300, 50);
		panel.add(lblViewRecord);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(612, 83, 300, 5);
		panel.add(panel_2_1);
		
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
		lblBack.setBounds(10, 0, 85, 50);
		panel.add(lblBack);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		lblNewLabel_1.setBounds(1417, 0, 63, 41);
		panel.add(lblNewLabel_1);
		
		dayCB.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		dayCB.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayCB.setBounds(476, 146, 85, 22);
		panel.add(dayCB);
		
		MonthCB.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		MonthCB.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		MonthCB.setBounds(704, 146, 85, 22);
		panel.add(MonthCB);
		
		YearCB.setModel(new DefaultComboBoxModel(new String[] {"2021", "2022"}));
		YearCB.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		YearCB.setBounds(885, 146, 85, 22);
		panel.add(YearCB);
		
		JLabel lblNewLabel = new JLabel("Day :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel.setBounds(407, 140, 71, 30);
		panel.add(lblNewLabel);
		
		JLabel lblMonth = new JLabel("Month :");
		lblMonth.setForeground(Color.WHITE);
		lblMonth.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblMonth.setBounds(602, 140, 102, 30);
		panel.add(lblMonth);
		
		JLabel lblYear = new JLabel("Year :");
		lblYear.setForeground(Color.WHITE);
		lblYear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblYear.setBounds(814, 140, 71, 30);
		panel.add(lblYear);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearDataTable();
				searchRecord();
			}
		});
		btnNewButton.setBounds(1020, 117, 143, 51);
		panel.add(btnNewButton);
		
		JButton btnShowAll = new JButton("SHOW ALL");
		btnShowAll.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		btnShowAll.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnShowAll.setBackground(Color.WHITE);
		btnShowAll.setFocusable(false);
		btnShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearDataTable();
				insertRecordDetail();
			}
		});
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowAll.setBounds(1020, 205, 143, 52);
		panel.add(btnShowAll);
		
		dayCB_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayCB_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		dayCB_1.setBounds(476, 205, 85, 22);
		panel.add(dayCB_1);
		
		MonthCB_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		MonthCB_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		MonthCB_1.setBounds(704, 205, 85, 22);
		panel.add(MonthCB_1);
		
		YearCB_1.setModel(new DefaultComboBoxModel(new String[] {"2021", "2022"}));
		YearCB_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		YearCB_1.setBounds(885, 205, 85, 22);
		panel.add(YearCB_1);
		
		JLabel lblMonth_1 = new JLabel("Month :");
		lblMonth_1.setForeground(Color.WHITE);
		lblMonth_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblMonth_1.setBounds(602, 200, 102, 30);
		panel.add(lblMonth_1);
		
		JLabel lblYear_1 = new JLabel("Year :");
		lblYear_1.setForeground(Color.WHITE);
		lblYear_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblYear_1.setBounds(814, 200, 71, 30);
		panel.add(lblYear_1);
		
		JLabel lblNewLabel_2 = new JLabel("Day :");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(407, 200, 71, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblFROM = new JLabel("FROM");
		lblFROM.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.WHITE));
		lblFROM.setIcon(null);
		lblFROM.setHorizontalAlignment(SwingConstants.CENTER);
		lblFROM.setForeground(Color.WHITE);
		lblFROM.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		lblFROM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFROM.setBounds(257, 143, 85, 27);
		panel.add(lblFROM);
		
		JLabel lblTo = new JLabel("TO ");
		lblTo.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.WHITE));
		lblTo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblTo.setBounds(257, 203, 85, 27);
		panel.add(lblTo);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_date_from_50px_1.png"));
		lblNewLabel_3.setBounds(347, 127, 50, 51);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\icons8_date_from_50px_1.png"));
		lblNewLabel_3_1.setBounds(347, 192, 50, 51);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\DNC\\eclipse-workspace\\DoAnCuoiKi\\Images\\LOGOVKU.png"));
		lblNewLabel_4.setBounds(1244, 83, 203, 174);
		panel.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(263, 326, 931, 444);
		contentPane.add(scrollPane);
		
		tableVehicleRegister = new JTable();
		tableVehicleRegister.getTableHeader().setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        tableVehicleRegister.getTableHeader().setBackground(new Color (102,102,255));
        tableVehicleRegister.getTableHeader().setForeground(new Color (250,250,250));
		tableVehicleRegister.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Vehicle Name", "Day Register", "Expired Day", "Vehicle Plate"
			}
		));
		tableVehicleRegister.setSelectionBackground(Color.WHITE);
		tableVehicleRegister.setRowHeight(40);
		tableVehicleRegister.setIntercellSpacing(new Dimension(0, 0));
		tableVehicleRegister.setGridColor(new Color(102, 102, 255));
		tableVehicleRegister.setForeground(new Color(0, 112, 192));
		tableVehicleRegister.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		tableVehicleRegister.setFocusable(false);
		tableVehicleRegister.setFocusTraversalKeysEnabled(false);
		scrollPane.setViewportView(tableVehicleRegister);
		insertRecordDetail();
	}
}
