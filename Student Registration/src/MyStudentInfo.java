/*** created by @digitalzia 
github_Link: https://github.com/digitalzia ***/

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
import java.awt.Font;

public class MyStudentInfo extends JFrame {

	private JPanel contentPane;
	
	JComboBox comboBoxID;
	private JComboBox comboBoxCourse;
	JComboBox comboBoxMyCourse;
	
	Connection connection =null;
	Login connectionLogin=null;
	Login login = new Login();
	
	private ButtonGroup radioGroup = new ButtonGroup();
	private JButton btnUpdate;
	private JButton btnEditCourse;
	private JButton btnLogut;
	private JButton btnAdd;
	private JButton btnDelete;

	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblPhone;
	private JLabel lblCity;
	private JLabel lblGender;
	private JLabel lblMyCourseData;
	private JLabel lblNewLabel;
	private JLabel lblGeneralCourseInformation;

	JTextField textFieldName;
	JTextField textFieldSurname;
	JTextField textFieldUsername;
	JPasswordField textFieldPassword;
	JTextField textFieldPhone;
	JTextField textFieldCity;
	
	JTable tableStudentCourse;
	private JScrollPane scrollPaneStudentCourse;
	private JScrollPane scrollPaneMyProfile;
	private JScrollPane scrollPaneGeneralCourse;


	private JLabel lblSelectCouse;
	private JLabel lblMyData;

	private JTable table2;
	JTable tableMyProfile;
	private JTable table;
	private JTable tableStudentCredit;
	

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
					MyStudentInfo frame = new MyStudentInfo();
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


			String queryCourse="select * from CourseInfo ";
			PreparedStatement pstCourse =connection.prepareStatement(queryCourse);
			ResultSet rsCourse = pstCourse.executeQuery();
			table2.setModel(DbUtils.resultSetToTableModel(rsCourse));
	
			String queryStudentCourse="SELECT CourseName FROM StudentCourseInfo where ID = '"+comboBoxID.getSelectedItem()+"' ";
			PreparedStatement pstStudentCourse = connection.prepareStatement(queryStudentCourse);
			ResultSet rsStudentCourse = pstStudentCourse.executeQuery();
			tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsStudentCourse)); 
	

			String queryMyProfile="select Name ,Surname, Username "
					+ ",Password , ID , Phone, City, Gender from StudentInfo where ID= '"+comboBoxID.getSelectedItem()+"' ";
			PreparedStatement pstMyProfile = connection.prepareStatement(queryMyProfile);
			ResultSet rsMyProfile = pstMyProfile.executeQuery();
			tableMyProfile.setModel(DbUtils.resultSetToTableModel(rsMyProfile)); 

			String queryCredit="SELECT Credit FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
			PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
			ResultSet rsCredit = pstCredit.executeQuery();
			tableStudentCredit.setModel(DbUtils.resultSetToTableModel(rsCredit));
			
		/*	String queryDeleteEmpty ="DELETE FROM StudentCourseInfo  WHERE CourseName IS NULL AND WHERE ID='"+comboBoxID.getSelectedItem()+"'";
			PreparedStatement pstDeleteEmpty =connection.prepareStatement(queryDeleteEmpty);	
			pstDeleteEmpty.execute(); */
			
			
			String queryStudentCredit="select Credit from StudentCourseInfo ";
			PreparedStatement pstStudentCredit =connection.prepareStatement(queryStudentCredit);
			ResultSet rsStudentCredit= pstStudentCredit.executeQuery();
	
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
			
			comboBoxCourse.removeAllItems();
			comboBoxCourse.revalidate(); 
			comboBoxCourse.repaint();
					
			comboBoxMyCourse.removeAllItems();
			comboBoxMyCourse.revalidate(); 
			comboBoxMyCourse.repaint();
						
				
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * 
	 */

	// filling general course information...
	
	public void fillcomboBoxCourse()
	{
		try {
			String queryCourse="select * from CourseInfo ";
			PreparedStatement pstCourse =connection.prepareStatement(queryCourse);
			ResultSet rsCourse = pstCourse.executeQuery();
		
			while(rsCourse.next())
			{
				comboBoxCourse.addItem(rsCourse.getString("CourseName"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
			comboBoxCourse.repaint();	
		}
		
	}
	
	// filling course information in comboBox for user...
	
	public void fillComboBoxMyCourse()
	{
		try {
			String query="SELECT CourseName FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
			PreparedStatement pst =connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next())
			{
				comboBoxMyCourse.addItem(rs.getString("CourseName"));
	
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
			comboBoxMyCourse.repaint();	
		}
		
	}

	/**
	 * Create the frame.
	 */
	
	// connecting sql database...
	
	public MyStudentInfo() {
		connection=sqlConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(new Color(255, 127, 80));
		rdbtnMale.setBounds(69, 201, 61, 16);
		contentPane.add(rdbtnMale);
		getContentPane().add(rdbtnMale);
		radioGroup.add(rdbtnMale);
		
		
		final JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(new Color(255, 0, 255));
		rdbtnFemale.setBounds(125, 198, 76, 23);
		contentPane.add(rdbtnFemale);
		getContentPane().add(rdbtnFemale);
		radioGroup.add(rdbtnFemale);
		
	
		
		// update button...
		
		btnUpdate = new JButton("Update ");
		btnUpdate.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					// updating information...

					
					String query="Update StudentInfo set  Name=' "+textFieldName.getText()+"',Surname='"+textFieldSurname.getText()+"' "
							+ ",Username='"+textFieldUsername.getText()+"',Password='"+textFieldPassword.getText()+"'"
									+ ",Phone='"+textFieldPhone.getText()+"' ,City='"+textFieldCity.getText()+"' "
											+ ",Gender='"+rdbtnMale.getText()+"' OR Gender='"+rdbtnFemale.getText()+"' "
													+ " where ID='"+comboBoxID.getSelectedItem()+ " ' ";
					

					if (rdbtnMale.isSelected() ) {
						query="Update StudentInfo set Gender='"+rdbtnMale.getText()+"'    where ID='"+comboBoxID.getSelectedItem()+ " ' ";
						}
					 if (rdbtnFemale.isSelected()) {
						 radioGroup.clearSelection();
						 rdbtnFemale.setSelected(true);
						 query="Update StudentInfo set Gender='"+rdbtnFemale.getText()+"'  where ID='"+comboBoxID.getSelectedItem()+ " ' ";
						}
					
					PreparedStatement pst =connection.prepareStatement(query);
					
					// checking some filed are blank...

					
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
					pst.close();
			
				} catch (Exception e) {
					e.printStackTrace(); 
				}	
				refreshComboBox();
				refreshTable();
				fillcomboBoxCourse();
			}		
		});
		
		
		btnUpdate.setBounds(29, 364, 145, 29);
		contentPane.add(btnUpdate);

		// refresh button actions....
		
		btnEditCourse = new JButton("Refresh");
		btnEditCourse.setForeground(new Color(255, 0, 0));
		btnEditCourse.setVisible(false);
		btnEditCourse.addActionListener(new ActionListener() 
		{
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				
					try {
					
						String queryCredit="SELECT Credit FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
						PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
						ResultSet rsCredit = pstCredit.executeQuery();
						tableStudentCredit.setModel(DbUtils.resultSetToTableModel(rsCredit));
					
						String queryID="SELECT CourseName FROM StudentCourseInfo where ID = '"+comboBoxID.getSelectedItem()+"' ";
						PreparedStatement pstID;
						pstID = connection.prepareStatement(queryID);
						ResultSet rsID = pstID.executeQuery(); 
						tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsID));
		
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				refreshComboBox();
				refreshTable();
				fillcomboBoxCourse();
				fillComboBoxMyCourse();	
			}		
		});
		btnEditCourse.setBounds(345, 267, 145, 29);
		contentPane.add(btnEditCourse);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(77, 26, 122, 28);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		lblName = new JLabel("Name:");
		lblName.setForeground(new Color(0, 0, 255));
		lblName.setBounds(6, 32, 61, 16);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setForeground(new Color(0, 0, 255));
		lblSurname.setBounds(6, 60, 61, 16);
		contentPane.add(lblSurname);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(0, 0, 255));
		lblUsername.setBounds(6, 89, 66, 16);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(0, 0, 255));
		lblPassword.setBounds(6, 117, 66, 16);
		contentPane.add(lblPassword);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setForeground(new Color(0, 0, 255));
		lblPhone.setBounds(6, 145, 61, 16);
		contentPane.add(lblPhone);
		
		lblCity = new JLabel("City:");
		lblCity.setForeground(new Color(0, 0, 255));
		lblCity.setBounds(6, 173, 61, 16);
		contentPane.add(lblCity);
		
		lblGender = new JLabel("Gender:");
		lblGender.setForeground(new Color(0, 0, 255));
		lblGender.setBounds(6, 201, 61, 16);
		contentPane.add(lblGender);
		
	
		textFieldSurname = new JTextField();
		textFieldSurname.setBounds(77, 54, 122, 28);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(77, 83, 122, 28);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(77, 111, 122, 28);
		contentPane.add(textFieldPassword);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(77, 139, 122, 28);
		contentPane.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		textFieldCity = new JTextField();
		textFieldCity.setBounds(77, 167, 122, 28);
		contentPane.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		comboBoxID = new JComboBox();
		comboBoxID.setVisible(false);
		comboBoxID.setBounds(345, 7, 145, 27);
		contentPane.add(comboBoxID);
		
		
		comboBoxCourse = new JComboBox();
		comboBoxCourse.setBounds(77, 296, 122, 27);
		contentPane.add(comboBoxCourse);
		
		// logout button actions.... when click this button it will be open login button again...
		
		btnLogut = new JButton("Logut");
		btnLogut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0) {
		
					dispose();
					Login login = new Login();
					login.getFrame().setVisible(true);
			
			}
		});	
		btnLogut.setBounds(703, 6, 117, 29);
		contentPane.add(btnLogut);
		
		JLabel lblMyStudentIcon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("student.png")).getImage();
		lblMyStudentIcon.setIcon(new ImageIcon(img));
		lblMyStudentIcon.setBounds(594, 0, 122, 89);
		contentPane.add(lblMyStudentIcon);
		
		scrollPaneStudentCourse = new JScrollPane();
		scrollPaneStudentCourse.setBounds(211, 299, 507, 73);
		contentPane.add(scrollPaneStudentCourse);
		
		tableStudentCourse = new JTable();
		scrollPaneStudentCourse.setViewportView(tableStudentCourse);
	
		
		lblMyCourseData = new JLabel("My Course Data:");
		lblMyCourseData.setForeground(new Color(255, 0, 0));
		lblMyCourseData.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblMyCourseData.setBounds(211, 271, 182, 16);
		contentPane.add(lblMyCourseData);
		
		lblSelectCouse = new JLabel("Courses:");
		lblSelectCouse.setForeground(new Color(255, 0, 0));
		lblSelectCouse.setBounds(6, 300, 66, 16);
		contentPane.add(lblSelectCouse);
		
		lblMyData = new JLabel("My Profile:");
		lblMyData.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblMyData.setForeground(new Color(0, 0, 255));
		lblMyData.setBounds(211, 61, 110, 16);
		contentPane.add(lblMyData);
		
		// thins button for course adding...
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String queryStudentCredit= "SELECT ((SELECT Credit FROM StudentCourseInfo"
							+ " WHERE ID= '"+comboBoxID.getSelectedItem()+"') - (SELECT CourseCredit FROM CourseInfo"
							+ " WHERE CourseName= '"+comboBoxCourse.getSelectedItem()+"')) ";
					PreparedStatement pstStudentCredit =connection.prepareStatement(queryStudentCredit);
					ResultSet rsStudentCredit = pstStudentCredit.executeQuery();
					
					
					// checking for course already added or not...	
					
					String query="insert into StudentCourseInfo (ID,CourseName,Username) values (?,?,?)";		
					PreparedStatement pst =connection.prepareStatement(query);	
					Statement statement = (Statement) connection.createStatement();
					
					ResultSet rsCourse = statement.executeQuery("SELECT CourseName from StudentCourseInfo "
							+ "where CourseName='"+comboBoxCourse.getSelectedItem()+"'"
									+ "AND ID='"+comboBoxID.getSelectedItem()+"'   ");
		
					String duplicateCourse = null;
		
					
					while(rsCourse.next()){
								
						duplicateCourse = rsCourse.getString(1);
							
						if(duplicateCourse == null){
									
						}
						
						
						else if (duplicateCourse!=rsCourse.getString(1)) {
							
							JOptionPane.showMessageDialog(null,
									" Course is already added!.\n");	
							fillcomboBoxCourse();
							pst.closeOnCompletion();

						} 
						} 
						
					// checking credit...
					
						if(rsStudentCredit.getInt(1)>=0) 
						{
						
							String queryCredit="Update StudentCourseInfo set Credit='"+rsStudentCredit.getInt(1)+"' "
									+ " where ID='"+comboBoxID.getSelectedItem()+"'  ";
		
							PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
							
							pstCredit.execute();
							pstStudentCredit.execute();
						}
						
						else 
						{
							
							JOptionPane.showMessageDialog(null, "Insufficient amount of credit!");
							pst.close();

						}

						String queryStdInfo="Select Username FROM StudentInfo WHERE ID='"+comboBoxID.getSelectedItem()+"'";
						PreparedStatement pstStdInfo =connection.prepareStatement(queryStdInfo);		
						ResultSet rsStdInfo = pstStdInfo.executeQuery();
					
						
							pst.setString(1,  (String)comboBoxID.getSelectedItem());
							pst.setString(2,  (String)comboBoxCourse.getSelectedItem());
							pst.setString(3,  rsStdInfo.getString(1));

							
							if(comboBoxMyCourse.getItemCount()>1) {
							String queryDeleteEmpty ="DELETE FROM StudentCourseInfo  WHERE CourseName IS NULL AND ID='"+comboBoxID.getSelectedItem()+"'";
							PreparedStatement pstDeleteEmpty;
							pstDeleteEmpty = connection.prepareStatement(queryDeleteEmpty);
							pstDeleteEmpty.execute();
							} 
							
							JOptionPane.showMessageDialog(null, "Course Added!");
							pst.execute();
											
			
				} catch (Exception e) {
					
					e.printStackTrace(); 
				}
				refreshComboBox();
				refreshTable();
				fillcomboBoxCourse();
				fillComboBoxMyCourse();	
			}
		});
		btnAdd.setForeground(new Color(0, 128, 128));
		btnAdd.setBounds(110, 330, 88, 29);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
				
				String queryStudentCredit= "SELECT ((SELECT Credit FROM StudentCourseInfo"
						+ " WHERE ID= '"+comboBoxID.getSelectedItem()+"') + (SELECT CourseCredit FROM CourseInfo"
						+ " WHERE CourseName= '"+comboBoxMyCourse.getSelectedItem()+"')) ";
				PreparedStatement pstStudentCredit =connection.prepareStatement(queryStudentCredit);
				ResultSet rsStudentCredit = pstStudentCredit.executeQuery();

				
				String queryCredit="Update StudentCourseInfo set Credit='"+rsStudentCredit.getInt(1)+"'"
						+ "  where ID='"+comboBoxID.getSelectedItem()+"' ";
				
				String query="DELETE FROM StudentCourseInfo WHERE CourseName='"+comboBoxMyCourse.getSelectedItem()+"' "
						+ " AND ID='"+comboBoxID.getSelectedItem()+"' ";
				
				
				PreparedStatement pst =connection.prepareStatement(query);				
	
				PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
				
				pstCredit.execute();
				pstStudentCredit.execute();
				
				
				if(comboBoxMyCourse.getItemCount()<=1) {	
					
					String queryCheckEmptyCourse= "SELECT ID,UserName FROM StudentCourseInfo WHERE ID ='"+comboBoxID.getSelectedItem()+"' ";
					PreparedStatement pstCheckEmptyCourse =connection.prepareStatement(queryCheckEmptyCourse);	
					ResultSet rsCheckEmptyCourse = pstCheckEmptyCourse.executeQuery();

					String queryCourse="insert into StudentCourseInfo (ID,Username) values (?,?)";
					PreparedStatement pstCourse =connection.prepareStatement(queryCourse);
					pstCourse.setString(1, rsCheckEmptyCourse.getString(1));
					pstCourse.setString(2, rsCheckEmptyCourse.getString(1));
					pstCourse.execute();
				}
				
				JOptionPane.showMessageDialog(null, "Course Deleted!");
				pst.execute();

			} catch (Exception e) {
				e.printStackTrace(); 
			}
					
			refreshComboBox();
			refreshTable();
			fillcomboBoxCourse();
			fillComboBoxMyCourse();
			
		}
	});
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setBounds(110, 260, 89, 29);
		contentPane.add(btnDelete);
		
		scrollPaneGeneralCourse = new JScrollPane();
		scrollPaneGeneralCourse.setBounds(211, 182, 609, 81);
		contentPane.add(scrollPaneGeneralCourse);
		
		table2 = new JTable();
		scrollPaneGeneralCourse.setViewportView(table2);
		
		scrollPaneMyProfile = new JScrollPane();
		scrollPaneMyProfile.setBounds(211, 88, 609, 73);
		contentPane.add(scrollPaneMyProfile);
		
		tableMyProfile = new JTable();
		scrollPaneMyProfile.setViewportView(tableMyProfile);
		
		lblGeneralCourseInformation = new JLabel("General Course Information");
		lblGeneralCourseInformation.setForeground(new Color(255, 0, 0));
		lblGeneralCourseInformation.setBounds(211, 164, 182, 16);
		contentPane.add(lblGeneralCourseInformation);
		
		JLabel lblEditProfileAnd = new JLabel("Edit Profile and Course:");
		lblEditProfileAnd.setBounds(6, 7, 168, 16);
		contentPane.add(lblEditProfileAnd);
		
		JLabel lblMyCourses = new JLabel("My Courses:");
		lblMyCourses.setBounds(6, 230, 93, 16);
		contentPane.add(lblMyCourses);
		
		comboBoxMyCourse = new JComboBox();
		comboBoxMyCourse.setBounds(79, 226, 120, 27);
		contentPane.add(comboBoxMyCourse);
		
		JScrollPane scrollPaneCedit = new JScrollPane();
		scrollPaneCedit.setBounds(741, 299, 76, 36);
		contentPane.add(scrollPaneCedit);
		
		tableStudentCredit = new JTable();
		scrollPaneCedit.setViewportView(tableStudentCredit);
		
		refreshTable();
		fillcomboBoxCourse();

	}
}
