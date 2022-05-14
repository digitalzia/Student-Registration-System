/*** created by @ahmet_Kara 
github_Link: https://github.com/ahmetQara ***/

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;
import java.awt.Color;

public class StudentInfo extends JFrame {

	Connection connection =null;
	
	private JFrame frame;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox comboBoxID;
	
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnEditCourse;
	private ButtonGroup radioGroup = new ButtonGroup();
	private JButton btnLogut;
		
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblId;
	private JLabel lblPhone;
	private JLabel lblCity;
	private JLabel lblGender;
	
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldUsername;
	private JPasswordField textFieldPassword;
	private JTextField textFieldPhone;
	private JTextField textFieldCity;


	/**
	 * Launch the application.
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfo frame = new StudentInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * 
	 */
	
	// refreshing table when click add course delete course or click course information button....
	
	public void refreshTable()
	{
		try {
			
			String query="select Name,Surname,Username,Password,Phone,City,ID,Gender from StudentInfo ";
			PreparedStatement pst =connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}		
	}
	/**
	 * 
	 */
	
	// refreshing comboBox when click add course delete course or click course information button....

	public void refreshComboBox()
	{

		try {
			
			comboBoxID.removeAllItems();
			comboBoxID.revalidate(); 
			comboBoxID.repaint();
			String query="select Name,Surname,Username,Password,Phone,City,ID,Gender from StudentInfo";
			PreparedStatement pst =connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();	
			table.setModel(DbUtils.resultSetToTableModel(rs));
				
				
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * 
	 */
	
	// filling ID in comboBox ...
	
	public void fillomboBox()
	{
		try {
			String query="select * from StudentInfo ";
			PreparedStatement pst =connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next())
			{
				comboBoxID.addItem(rs.getString("ID"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
			comboBoxID.repaint();	
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	
	// connection sql...
	
	public StudentInfo() {
		connection=sqlConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 76, 596, 318);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		final JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(new Color(205, 133, 63));
		rdbtnMale.setBounds(55, 279, 61, 16);
		contentPane.add(rdbtnMale);
		getContentPane().add(rdbtnMale);
		radioGroup.add(rdbtnMale);
		
		
		final JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(new Color(255, 0, 255));
		rdbtnFemale.setBounds(122, 275, 76, 23);
		contentPane.add(rdbtnFemale);
		getContentPane().add(rdbtnFemale);
		radioGroup.add(rdbtnFemale);
		
		// update button actions...
		
		btnUpdate = new JButton("Update ");
		btnUpdate.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				try {
						
					// updating information...
					
					String query="Update StudentInfo set  Name=' "+textFieldName.getText()+"',Surname='"+textFieldSurname.getText()+"'"
							+ " ,Username='"+textFieldUsername.getText()+"',Password='"+textFieldPassword.getText()+"',"
									+ "Phone='"+textFieldPhone.getText()+"' ,City='"+textFieldCity.getText()+"' ,"
											+ "Gender='"+rdbtnMale.getText()+"' OR Gender='"+rdbtnFemale.getText()+"' "
													+ " where ID='"+comboBoxID.getSelectedItem()+ " ' ";		
					
				/*	if(comboBoxID.isShowing())
					{
							
						String queryStudentInfo="SELECT * FROM StudentInfo WHERE  ID= '"+comboBoxID.getSelectedItem()+"'   ";
						PreparedStatement pstStudentInfo = connection.prepareStatement(queryStudentInfo);
						ResultSet rsStudentInfo = pstStudentInfo.executeQuery();		
						textFieldName.setText(rsStudentInfo.getString("Name"));
						textFieldSurname.setText(rsStudentInfo.getString("Surname"));
						textFieldUsername.setText(rsStudentInfo.getString("Username"));
						textFieldPassword.setText(rsStudentInfo.getString("Password"));
						textFieldPhone.setText(rsStudentInfo.getString("Phone"));
						textFieldCity.setText(rsStudentInfo.getString("City"));
						textFieldName.setText(rsStudentInfo.getString("Name")); 
						
					} */

					if (rdbtnMale.isSelected() ) {
						query="Update StudentInfo set Gender='"+rdbtnMale.getText()+"'    where ID='"+comboBoxID.getSelectedItem()+ " ' ";
						}
					if (rdbtnFemale.isSelected()) {
						 radioGroup.clearSelection();
						 rdbtnFemale.setSelected(true);
						 query="Update StudentInfo set Gender='"+rdbtnFemale.getText()+"'  where ID='"+comboBoxID.getSelectedItem()+ " ' ";
						}
					
				
					PreparedStatement pst =connection.prepareStatement(query);
					
					// checking some filed are blank or not...
							
					if(		textFieldName.getText() == null || textFieldName.getText().trim().equals("") ||
							textFieldSurname.getText() == null || textFieldSurname.getText().trim().equals("") ||
							textFieldUsername.getText() == null || textFieldUsername.getText().trim().equals("") ||
							textFieldCity.getText() == null || textFieldCity.getText().trim().equals("") ||
							textFieldPhone.getText() == null || textFieldPhone.getText().trim().equals("") ||
							textFieldPassword.getText() == null || textFieldPassword.getText().trim().equals("")) {	
						
						JOptionPane.showMessageDialog(null,"Some of the fields are blank."
								+ " Please enter all information!\n");
						pst.close();						

					}
					
					// checking username is already used by another user or not....
					
					String queryUserNameCheck="SELECT  Username FROM StudentInfo WHERE ID='"+comboBoxID.getSelectedItem()+"' ";
					PreparedStatement pstUserNameCheck =connection.prepareStatement(queryUserNameCheck);
					ResultSet rsUserNameCheck = pstUserNameCheck.executeQuery();
		
					Statement statementUserName = (Statement) connection.createStatement();

					ResultSet rsUserName = statementUserName.executeQuery("SELECT * from StudentInfo where Username='" + textFieldUsername.getText() + "'");
	
					String duplicateUserName = null;
					
					while(rsUserName.next() && rsUserNameCheck.next()){
						duplicateUserName = rsUserName.getString(1);
						}

						if(duplicateUserName == null || rsUserNameCheck.getString(1)!=textFieldUsername.getText()){
						
						}
						
						else {
							
							JOptionPane.showMessageDialog(null,
									"Username is already used by another user. Please choose a different username.\n");	
							pst.close();
							
						} 
						
						// checking phone number format. It must be like "123-456-7890"	

						
					if (!textFieldPhone.getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")){
						JOptionPane.showMessageDialog(null,
								"Phone Number must be in the format: 123-456-7890\n");
						pst.close();	
						
					}
						
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Updated!");
				
					
				} catch (Exception e) {
					e.printStackTrace(); 
				}	
				refreshComboBox();
				refreshTable();
				fillomboBox();	
			}
			
		});
		btnUpdate.setBounds(29, 307, 145, 29);
		contentPane.add(btnUpdate);
		
		// deleting student completely...
		
		btnDelete = new JButton("Delete Student Registration");
		btnDelete.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String query="delete from StudentInfo where ID='"+comboBoxID.getSelectedItem()+" ' ";
					PreparedStatement pst = connection.prepareStatement(query);
					String queryCourse="delete from StudentCourseInfo where ID='"+comboBoxID.getSelectedItem()+" ' ";
					PreparedStatement pstCourse = connection.prepareStatement(queryCourse);
					pstCourse.execute();
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted!");
					
		
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshComboBox();
				refreshTable();
				fillomboBox();
			}
		});
		btnDelete.setBounds(150, 14, 190, 29);
		contentPane.add(btnDelete);
	
		// editing course information...
		
		btnEditCourse = new JButton("Edit Course");
		btnEditCourse.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				
				CourseInfo CourseInfo = new CourseInfo();
				CourseInfo.fillComboBoxCourse();
				CourseInfo.refreshComboBox();
				
				dispose();
				CourseInfo course = new CourseInfo();
				course.setVisible(true);
		
			}
		});
		btnEditCourse.setBounds(29, 348, 145, 29);
		contentPane.add(btnEditCourse);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(76, 105, 122, 28);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		lblName = new JLabel("Name:");
		lblName.setForeground(Color.BLUE);
		lblName.setBounds(6, 111, 61, 16);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setForeground(Color.BLUE);
		lblSurname.setBounds(6, 139, 61, 16);
		contentPane.add(lblSurname);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setBounds(6, 167, 66, 16);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setBounds(6, 195, 66, 16);
		contentPane.add(lblPassword);
		
		lblId = new JLabel("ID:");
		lblId.setForeground(new Color(255, 0, 0));
		lblId.setBounds(6, 18, 61, 16);
		contentPane.add(lblId);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setForeground(Color.BLUE);
		lblPhone.setBounds(6, 223, 61, 16);
		contentPane.add(lblPhone);
		
		lblCity = new JLabel("City:");
		lblCity.setForeground(Color.BLUE);
		lblCity.setBounds(6, 251, 61, 16);
		contentPane.add(lblCity);
		
		lblGender = new JLabel("Gender:");
		lblGender.setForeground(Color.BLUE);
		lblGender.setBounds(6, 279, 61, 16);
		contentPane.add(lblGender);
		
	
		textFieldSurname = new JTextField();
		textFieldSurname.setBounds(76, 133, 122, 28);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(76, 161, 122, 28);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(76, 189, 122, 28);
		contentPane.add(textFieldPassword);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(77, 217, 122, 28);
		contentPane.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		textFieldCity = new JTextField();
		textFieldCity.setBounds(77, 245, 122, 28);
		contentPane.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		comboBoxID = new JComboBox();
		comboBoxID.setBounds(28, 15, 117, 27);
		contentPane.add(comboBoxID);
		
		btnLogut = new JButton("Logut");
		btnLogut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0) {
		
					dispose();
					Login login = new Login();
					login.getFrame().setVisible(true);
			
			}
		});		
		
		btnLogut.setBounds(697, 6, 117, 29);
		contentPane.add(btnLogut);
		
		JLabel lbladminIcon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("admin.png")).getImage();
		lbladminIcon.setIcon(new ImageIcon(img));
		lbladminIcon.setBounds(600, 0, 101, 82);
		contentPane.add(lbladminIcon);
		refreshTable();
		fillomboBox();
			
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
