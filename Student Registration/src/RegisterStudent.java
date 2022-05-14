/*** created by @ahmet_Kara 
github_Link: https://github.com/ahmetQara ***/

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;


public class RegisterStudent extends JFrame {

	private JPanel contentPane;
	
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	JTextField textFieldUsername;
	JTextField textFieldID;
	private JTextField textFieldPhone;
	private JTextField textFieldCity;
	private JPasswordField textFieldPassword;
	
	ButtonGroup radioGroup = new ButtonGroup();
	boolean isValid=true;

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
					RegisterStudent frame = new RegisterStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// sql connecition and create fields name, surname etc...
	
	Connection connection =null;


	/**
	 * Create the frame.
	 */
	public RegisterStudent() {
		connection=sqlConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(new Color(0, 0, 255));
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblName.setBounds(38, 44, 87, 16);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setForeground(new Color(0, 0, 0));
		lblSurname.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblSurname.setBounds(38, 71, 87, 16);
		contentPane.add(lblSurname);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(0, 0, 255));
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUsername.setBounds(38, 99, 97, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(128, 0, 128));
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPassword.setBounds(38, 127, 97, 16);
		contentPane.add(lblPassword);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(new Color(255, 0, 0));
		lblId.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblId.setBounds(38, 155, 87, 16);
		contentPane.add(lblId);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setForeground(new Color(0, 128, 0));
		lblPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPhone.setBounds(38, 183, 87, 16);
		contentPane.add(lblPhone);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setForeground(new Color(255, 127, 80));
		lblCity.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCity.setBounds(38, 205, 87, 28);
		contentPane.add(lblCity);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblGender.setBounds(38, 239, 87, 16);
		contentPane.add(lblGender);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(147, 40, 134, 28);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setBounds(147, 67, 134, 28);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(147, 95, 134, 28);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(147, 151, 134, 28);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(147, 179, 134, 28);
		contentPane.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		textFieldCity = new JTextField();
		textFieldCity.setBounds(147, 207, 134, 28);
		contentPane.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		final JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(new Color(255, 127, 80));
		rdbtnMale.setBounds(147, 237, 61, 23);
		contentPane.add(rdbtnMale);
		
		
		final JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(new Color(255, 0, 255));
		rdbtnFemale.setBounds(205, 237, 76, 23);
		contentPane.add(rdbtnFemale);
		
		// writinh save button actions....
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(/**
		 * @author ahmetkara
		 *
		 */
		new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				
						try {
							
					// inserting student information in database...
							
					String query="insert into StudentInfo (Name,Surname,Username,Password,ID,Phone,City,Gender) values (?,?,?,?,?,?,?,?)";
					PreparedStatement pst =connection.prepareStatement(query);
					pst.setString(1, textFieldName.getText());
					pst.setString(2, textFieldSurname.getText());
					pst.setString(3, textFieldUsername.getText());
					pst.setString(4, textFieldPassword.getText());
					pst.setString(5, textFieldID.getText());
					pst.setString(6, textFieldPhone.getText());
					pst.setString(7, textFieldCity.getText());
					
					
					String queryCourse="insert into StudentCourseInfo (ID,Username) values (?,?)";
					PreparedStatement pstCourse =connection.prepareStatement(queryCourse);
					pstCourse.setString(1, textFieldID.getText());
					pstCourse.setString(2, textFieldUsername.getText());
				
					
					radioGroup.add(rdbtnMale);
					radioGroup.add(rdbtnFemale);

					if (rdbtnMale.isSelected() ) {
						pst.setString(8, rdbtnMale.getText());				
						}
					else if (rdbtnFemale.isSelected()) {
						 radioGroup.clearSelection();
						 rdbtnFemale.setSelected(true);
						pst.setString(8, rdbtnFemale.getText());	
						}
				
					// checking fields are blank or not...
					
					if(		textFieldName.getText() == null || textFieldName.getText().trim().equals("") ||
							textFieldUsername.getText() == null || textFieldUsername.getText().trim().equals("") ||
							textFieldSurname.getText() == null || textFieldSurname.getText().trim().equals("") ||
							textFieldCity.getText() == null || textFieldCity.getText().trim().equals("") ||
							textFieldPhone.getText() == null || textFieldPhone.getText().trim().equals("") ||
							textFieldID.getText() == null || textFieldID.getText().trim().equals("") ||
							textFieldPassword.getText() == null || textFieldPassword.getText().trim().equals("")) {	
						
						JOptionPane.showMessageDialog(null,"Some of the fields are blank."
								+ " Please enter all information!\n");
						pst.close();
						pstCourse.close();
					
					}
					
					// cheking ID is already used by another user or not....
					
					Statement statement = (Statement) connection.createStatement();

					ResultSet rsID = statement.executeQuery("SELECT * from StudentInfo where ID='" + textFieldID.getText() + "'");
	
					String duplicateID = null;
					
					while(rsID.next()){
						duplicateID = rsID.getString(1);
						}

						if(duplicateID == null){
						
						}
						
						else {
							
							JOptionPane.showMessageDialog(null,
									"ID is already used by another user. Please choose a different ID.\n");	
							pst.close();
							pstCourse.close();
		
						} 
						
						// cheking username is already used by another user or not....

						
						Statement statementUserName = (Statement) connection.createStatement();

						ResultSet rsUserName = statementUserName.executeQuery("SELECT * from StudentInfo where Username='" + textFieldUsername.getText() + "'");
		
						String duplicateUserName = null;
						
						while(rsUserName.next()){
							duplicateUserName = rsUserName.getString(1);
							}

							if(duplicateUserName == null){
							
							}
							
							else {
								
								JOptionPane.showMessageDialog(null,
										"Username is already used by another user. Please choose a different username.\n");	
								pst.close();
								pstCourse.close();
			
							} 
						
							// checking phone number format. It must be like "123-456-7890"	
							
						if (!textFieldPhone.getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")){
							JOptionPane.showMessageDialog(null,
									"Phone Number must be in the format: 123-456-7890\n");
							pst.close();	
							pstCourse.close();
							
						}
						if (!textFieldID.getText().matches("[0-9]{9}"))
						{
							JOptionPane.showMessageDialog(null,
									"Student ID must consist only of 9 digits, in the format:" +
									" 123456789\n");
							pst.close();
							pstCourse.close();
							}

					pstCourse.execute();
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Registration Successfull!");
					dispose();
					Login login = new Login();
					login.getFrame().setVisible(true);
			
		
				} catch (Exception e) {
					
					e.printStackTrace(); 
				}	
				
			}
		});
		
		btnSave.setBounds(164, 283, 117, 29);
		contentPane.add(btnSave);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(147, 123, 134, 28);
		contentPane.add(textFieldPassword);
		
		JLabel lblRegistration = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("registration.png")).getImage();
		lblRegistration.setIcon(new ImageIcon(img));
		lblRegistration.setBounds(436, 6, 157, 134);
		contentPane.add(lblRegistration);
		
	}	
}
