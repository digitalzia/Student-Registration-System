/*** created by @digitalziaa 
github_Link: https://github.com/digitalzia ***/

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login {

	private JFrame frame;

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
					Login window = new Login();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// sql connection.......
	
	Connection connection = null;
	JTextField textFieldUserName;
	private JPasswordField textFieldPassword;
	
	/**
	 * Create the application.
	 */
	
	public Login() {
		initialize();
		connection=sqlConnection.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	// creating JFrame and other field (UserName, password etc.)...
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 500, 350);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(195, 74, 74, 16);
		getFrame().getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(195, 113, 74, 16);
		getFrame().getContentPane().add(lblNewLabel_1);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(283, 68, 124, 28);
		getFrame().getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(283, 110, 124, 22);
		getFrame().getContentPane().add(textFieldPassword);
		
		// login button action....
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(/**
		 * @author ahmetkara
		 *
		 */
		new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
					String query="SELECT * FROM StudentInfo WHERE username=? and password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUserName.getText());
					pst.setString(2, textFieldPassword.getText());
					ResultSet rs  = pst.executeQuery();
					
					String queryLogin="SELECT * FROM StudentInfo WHERE username=? and password=? ";
					PreparedStatement pstLogin = connection.prepareStatement(queryLogin);
					pstLogin.setString(1, textFieldUserName.getText());
					ResultSet rsLogin = pstLogin.executeQuery();
				
					int count=0;

					
					while(rs.next()) {
						count=count+1;
						
					}
			
					// checking fields are blank or not....
					
					if(		textFieldUserName.getText().trim().equals("") || textFieldPassword.getText().trim().equals(""))
							 {	
						
						JOptionPane.showMessageDialog(null,"Some of the fields are blank."
								+ " Please enter all information!\n");
						pst.closeOnCompletion();
					
						
					}
						
					// checking if username is admin open the admin page....
					
					if(textFieldUserName.getText().trim().equals("admin") && textFieldPassword.getText().trim().equals("12345"))
					{
						
						JOptionPane.showMessageDialog(null, "Login is successful!");
						getFrame().dispose();
						StudentInfo stdInfo = new StudentInfo();
						stdInfo.setVisible(true);	
		
					}
			
					// if username is not admin open the username page when what input username in the username text field...
					
					 else  if(count==1) 
					
					{
						 
						 // accessing MyStdInfo class and getting user information...
						 
						MyStudentInfo MyStdInfo = new MyStudentInfo();	
						
						String queryMyProfile="select * from StudentInfo where Username = '"+textFieldUserName.getText()+"' ";
						PreparedStatement pstMyProfile = connection.prepareStatement(queryMyProfile);
						ResultSet rsMyPrfile = pstMyProfile.executeQuery();
						MyStdInfo.tableMyProfile.setModel(DbUtils.resultSetToTableModel(rsMyPrfile));
						
						String queryCourseName="SELECT CourseName FROM StudentCourseInfo where Username = '"+textFieldUserName.getText()+"' ";
						PreparedStatement pstCourseName = connection.prepareStatement(queryCourseName);
						ResultSet rsCourseName = pstCourseName.executeQuery(); 
						MyStdInfo.tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsCourseName));
			
						String queryID="SELECT ID FROM StudentInfo where Username = '"+textFieldUserName.getText()+"' ";;
						PreparedStatement pstID =connection.prepareStatement(queryID);
						ResultSet rsID = pstID.executeQuery();
							
								while(rsID.next())
								{
									MyStdInfo.comboBoxID.addItem(rsID.getString("ID"));
									
								}
			
					
					/*	String queryCourse="SELECT CourseName FROM StudentCourseInfo where Username= '"+textFieldUserName.getText()+"' ";
						PreparedStatement pstCourse =connection.prepareStatement(queryCourse);
						ResultSet rsCourse = pstCourse.executeQuery();
									
							while(rsCourse.next())
									{
								MyStdInfo.comboBoxMyCourse.addItem(rsCourse.getString("CourseName"));
				
										} */
						
								
					/*	String queryStudentInfo="SELECT * FROM StudentInfo WHERE  ID= '"+MyStdInfo.comboBoxID.getSelectedItem()+"'   ";
						PreparedStatement pstStudentInfo = connection.prepareStatement(queryStudentInfo);
						ResultSet rsStudentInfo = pstStudentInfo.executeQuery();		
						MyStdInfo.textFieldName.setText(rsStudentInfo.getString("Name"));
						MyStdInfo.textFieldSurname.setText(rsStudentInfo.getString("Surname"));
						MyStdInfo.textFieldUsername.setText(rsStudentInfo.getString("Username"));
						MyStdInfo.textFieldPassword.setText(rsStudentInfo.getString("Password"));
						MyStdInfo.textFieldPhone.setText(rsStudentInfo.getString("Phone"));
						MyStdInfo.textFieldCity.setText(rsStudentInfo.getString("City"));
						MyStdInfo.textFieldName.setText(rsStudentInfo.getString("Name")); */
						
						
						JOptionPane.showMessageDialog(null, "Login is successful!");
						pst.execute();
						getFrame().dispose();
						MyStdInfo.refreshTable();
						MyStdInfo.fillComboBoxMyCourse();
						MyStdInfo.setVisible(true);	
						
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "Username or password is not correct!.. Try again.");

					}
					
					rs.close();
					pst.close();
				} catch (Exception e) { 
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setBounds(289, 169, 95, 29);
		getFrame().getContentPane().add(btnLogin);
		
		JLabel label1 =  new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		label1.setIcon(new ImageIcon(img));
		label1.setBounds(48, 74, 144, 96);
		getFrame().getContentPane().add(label1);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				getFrame().dispose();
				RegisterStudent stdInfo = new RegisterStudent();
				stdInfo.setVisible(true);
			}
		});
		btnRegister.setBounds(289, 210, 95, 29);
		getFrame().getContentPane().add(btnRegister);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome to Student Registration System!");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(78, 6, 339, 36);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Username: admin");
		lblNewLabel_4.setBounds(39, 215, 144, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password: 12345");
		lblNewLabel_5.setBounds(39, 243, 144, 16);
		frame.getContentPane().add(lblNewLabel_5);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
