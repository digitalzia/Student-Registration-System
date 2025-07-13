/*** created by @digitalzia 
github_Link: https://github.com/digitalzia ***/

import java.awt.BorderLayout;
import java.awt.Component;
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
import java.util.List;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Color;

public class CourseInfo extends JFrame {

	Connection connection =null;
	
	private JPanel contentPane;
	
	private JComboBox comboBoxID;
	private JComboBox comboBoxCourse;
	private JComboBox comboBoxStudentCourse;
	
	
	private ButtonGroup radioGroup = new ButtonGroup();
	private JButton btnLogut;
	
	private JLabel lblId;
	private JLabel lblCourseInformation;
	private JLabel lblStudentCourseInformation;
	private JLabel lblCourseEdit;
	
	private JTable tableStudentCourse;
	private JTable tableStudentID;
	private JTable tableCourse;
	private JTable tableStudentCredit;


	private JScrollPane scrollPaneStudentCourse;
	private JScrollPane scrollPaneStudentID;
	private JScrollPane scrollPaneCourse;

	private JTable table;
	private JScrollPane scrollPaneCedit;
	
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
					CourseInfo frame = new CourseInfo();
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
	public void refreshTable()
	{
		try {
			
			String query="select CourseName ,CourseCredit, InstructorName ,InstructorRoom , ScheduledTime , ScheduledDays from CourseInfo ";
			PreparedStatement pst =connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCourse.setModel(DbUtils.resultSetToTableModel(rs));
			

			String queryCourse="SELECT CourseName FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
			PreparedStatement pstCourse = connection.prepareStatement(queryCourse);
			ResultSet rsCourse = pstCourse.executeQuery();
			tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsCourse));
			
			
			//String queryStudentCourse="select CourseName from StudentCourseInfo ";
		//	PreparedStatement pstStudentCourse =connection.prepareStatement(queryStudentCourse);
		//	ResultSet rsStudentCourse = pstStudentCourse.executeQuery();
			//tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsStudentCourse));
			
			/*String queryStudentCredi="select CourseName from StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' "; 
			PreparedStatement pstStudentCredi =connection.prepareStatement(queryStudentCredi);
			ResultSet rsStudentCredi = pstStudentCredi.executeQuery();
			tableStudentCredit.setModel(DbUtils.resultSetToTableModel(rsCourse)); */
	
			
			String queryCredit="SELECT Credit FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
			PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
			ResultSet rsCredit = pstCredit.executeQuery();
			tableStudentCredit.setModel(DbUtils.resultSetToTableModel(rsCredit));

			/*Credit.setText(String.valueOf(Credi));
			Credit.removeAll();
			Credit.revalidate();
			Credit.repaint(); */
		
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}	
		
	
	}
	/**
	 * 
	 */
	public void refreshComboBox()
	{

		try {
				
			comboBoxCourse.removeAllItems();
			comboBoxCourse.revalidate(); 
			comboBoxCourse.repaint();
			//String queryCourse="select CourseName from CourseInfo";
			//PreparedStatement pstCourse =connection.prepareStatement(queryCourse);
			//ResultSet rsCourse = pstCourse.executeQuery();	
			//tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsCourse));
			
			comboBoxStudentCourse.removeAllItems();
			comboBoxStudentCourse.revalidate(); 
			comboBoxStudentCourse.repaint();
			//String queryStudentCourse="select CourseName from StudentCourseInfo";
			//PreparedStatement pstStudentCourse =connection.prepareStatement(queryStudentCourse);
			//ResultSet rsStudentCourse = pstStudentCourse.executeQuery();	
			//tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsStudentCourse));
							
				
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * 
	 */
	public void fillComboBox()
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
	public void fillComboBoxCourse()
	{
		try {
			String query="select * from CourseInfo ";
			PreparedStatement pst =connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next())
			{
				comboBoxCourse.addItem(rs.getString("CourseName"));
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
			comboBoxCourse.repaint();	
		}
		
	}
	
	
	public void fillComboBoxStudentCourse()
	{
		try {
			String query="SELECT CourseName FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
			PreparedStatement pst =connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next())
			{
				comboBoxStudentCourse.addItem(rs.getString("CourseName"));
	
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
			comboBoxStudentCourse.repaint();	
		}
		
	}

	
	/**
	 * Create the frame.
	 */
	public CourseInfo() {
			
		connection=sqlConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPaneCourse = new JScrollPane();
		scrollPaneCourse.setBounds(6, 76, 825, 148);
		contentPane.add(scrollPaneCourse);
		
		tableCourse = new JTable();
		scrollPaneCourse.setViewportView(tableCourse);
		
		lblId = new JLabel("ID:");
		lblId.setForeground(new Color(255, 0, 0));
		lblId.setBounds(6, 256, 61, 16);
		contentPane.add(lblId);
		
		comboBoxID = new JComboBox();
		comboBoxID.setBounds(99, 252, 132, 27);
		contentPane.add(comboBoxID);
		
		comboBoxCourse = new JComboBox();
		comboBoxCourse.setBounds(99, 314, 219, 27);
		contentPane.add(comboBoxCourse);
		
		
		comboBoxStudentCourse = new JComboBox();
		comboBoxStudentCourse.setBounds(99, 353, 219, 27);
		contentPane.add(comboBoxStudentCourse);

		
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
		
		JButton btnAddCourse = new JButton("Add");
		btnAddCourse.setForeground(new Color(0, 128, 128));
		btnAddCourse.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				
			
					String queryStudentCredit= "SELECT ((SELECT Credit FROM StudentCourseInfo"
							+ " WHERE ID= '"+comboBoxID.getSelectedItem()+"') - (SELECT CourseCredit FROM CourseInfo"
							+ " WHERE CourseName= '"+comboBoxCourse.getSelectedItem()+"')) ";
					PreparedStatement pstStudentCredit =connection.prepareStatement(queryStudentCredit);
					ResultSet rsStudentCredit = pstStudentCredit.executeQuery();
							
					
					String queryCredit="Update StudentCourseInfo set Credit='"+rsStudentCredit.getInt(1)+"' "
							+ " where ID='"+comboBoxID.getSelectedItem()+"'  ";
					
					PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
					
				/*	String queryCourseCredi= "SELECT SUM(CourseCredit) FROM CourseInfo"
							+ " WHERE CourseName= '"+comboBoxCourse.getSelectedItem()+"'";
					PreparedStatement pstCourseCredi =connection.prepareStatement(queryCourseCredi);
					ResultSet rsCourseCredi = pstCourseCredi.executeQuery(); */
				
			
					String query="insert into StudentCourseInfo (ID,CourseName,Username) values (?,?,?)";		
					PreparedStatement pst =connection.prepareStatement(query);	
	
	
					String queryStdInfo="Select Username FROM StudentInfo WHERE ID='"+comboBoxID.getSelectedItem()+"'";
					PreparedStatement pstStdInfo =connection.prepareStatement(queryStdInfo);		
					ResultSet rsStdInfo = pstStdInfo.executeQuery();

					pst.setString(1,  (String)comboBoxID.getSelectedItem());
					pst.setString(2,  (String)comboBoxCourse.getSelectedItem());
					pst.setString(3,  rsStdInfo.getString(1));

				
					// checking duplicate course... 
					
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
							pst.closeOnCompletion();

						} 
						} 

					/*String queryStudentCredi="SELECT Credit FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
					PreparedStatement pstStudentCredi =connection.prepareStatement(queryStudentCredi);
					ResultSet rsStudentCredi = pstStudentCredi.executeQuery();
					tableStudentCredit.setModel(DbUtils.resultSetToTableModel(rsStudentCredi)); */
						
						if(rsStudentCredit.getInt(1)>=0) 
						{
													
						//	PreparedStatement pstCredi =connection.prepareStatement(queryCredi);
						//	ResultSet rsCredi = pstCredi.executeQuery();
						//	tableStudentCredit.setModel(DbUtils.resultSetToTableModel(rsCredi));
							
							
							
						/*	String queryID="UPDATE CourseName FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
							PreparedStatement pstID;
							pstID = connection.prepareStatement(queryID);
							ResultSet rsID = pstID.executeQuery(); 
							tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rsID)); */
							
					/*	String queryCredit= "UPDATE StudentCourseInfo SET Credit=3";
						
						PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
			
						ResultSet rsCredit = pstCredit.executeQuery();
						
						System.out.println(rsCredit);
						
						pstCredi.execute(); */
												
							pstCredit.execute();
							pstStudentCredit.execute();
								
						}
				
						else 
						{
							
							JOptionPane.showMessageDialog(null, "Insufficient amount of credit!");
							//pstStudentCredi.close();	
						//	pstCourseCredi.close();
							pst.close();

						}
			
						
							if(comboBoxStudentCourse.getItemCount()>1) {
							String queryDeleteEmpty ="DELETE FROM StudentCourseInfo  WHERE CourseName IS NULL AND ID='"+comboBoxID.getSelectedItem()+"'";
							PreparedStatement pstDeleteEmpty;
							pstDeleteEmpty = connection.prepareStatement(queryDeleteEmpty);
							pstDeleteEmpty.execute();
							} 
		
					/*	if(rdbtnCredi.isSelected()) {
					
						String queryStudentCrediUpdate="UPDATE StudentCourseInfo set Credit=Credit-'"+rsCourseCredi.getInt(1)+"' where ID='"+comboBoxID.getSelectedItem()+ " '";
						
						} */
			
						JOptionPane.showMessageDialog(null, "Course Added!");
						pst.execute();

				} catch (Exception e) {
					e.printStackTrace(); 
				}
				refreshComboBox();
				refreshTable();
				fillComboBoxCourse();
				fillComboBoxStudentCourse();		
			}
		});
		btnAddCourse.setBounds(328, 314, 101, 29);
		contentPane.add(btnAddCourse);
		
		JButton btnDeleteCourse = new JButton("Delete");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String queryStudentCredit= "SELECT ((SELECT Credit FROM StudentCourseInfo"
							+ " WHERE ID= '"+comboBoxID.getSelectedItem()+"') + (SELECT CourseCredit FROM CourseInfo"
							+ " WHERE CourseName= '"+comboBoxStudentCourse.getSelectedItem()+"')) ";
					PreparedStatement pstStudentCredit =connection.prepareStatement(queryStudentCredit);
					ResultSet rsStudentCredit = pstStudentCredit.executeQuery();

					
					String queryCredit="Update StudentCourseInfo set Credit='"+rsStudentCredit.getInt(1)+"'"
							+ "  where ID='"+comboBoxID.getSelectedItem()+"' ";
	
				/*	String query="SELECT CourseName FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
					PreparedStatement pst =connection.prepareStatement(query);	
					ResultSet rs = pst.executeQuery();		*/	

					
					String query="DELETE FROM StudentCourseInfo WHERE CourseName='"+comboBoxStudentCourse.getSelectedItem()+"' "
							+ " AND ID='"+comboBoxID.getSelectedItem()+"' ";
					

					PreparedStatement pst =connection.prepareStatement(query);	
		
					PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
	
					
					pstCredit.execute();
					pstStudentCredit.execute();
					
						if(comboBoxStudentCourse.getItemCount()<=1) {	
							
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
				fillComboBoxCourse();
				fillComboBoxStudentCourse();	
			}
		});
	
		btnDeleteCourse.setForeground(new Color(255, 0, 0));
		btnDeleteCourse.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnDeleteCourse.setBounds(328, 353, 101, 29);
		contentPane.add(btnDeleteCourse);
		
		JLabel lblCourses = new JLabel("Courses:");
		lblCourses.setForeground(new Color(255, 0, 255));
		lblCourses.setBounds(6, 318, 61, 16);
		contentPane.add(lblCourses);
		
		lblCourseInformation = new JLabel("General Course Profile");
		lblCourseInformation.setForeground(new Color(255, 0, 255));
		lblCourseInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCourseInformation.setBounds(328, 47, 226, 16);
		contentPane.add(lblCourseInformation);
		
		lblStudentCourseInformation = new JLabel("Student Course Profile");
		lblStudentCourseInformation.setForeground(Color.BLUE);
		lblStudentCourseInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblStudentCourseInformation.setBounds(337, 227, 305, 16);
		contentPane.add(lblStudentCourseInformation);
		
		lblCourseEdit = new JLabel("Edit Course:");
		lblCourseEdit.setBounds(6, 290, 117, 16);
		contentPane.add(lblCourseEdit);
		

		/*Credit = new JLabel("New label");
		Credit.setForeground(Color.BLUE);
		Credit.setBounds(800, 256, 37, 16);
		contentPane.add(Credit); */
		
		
		scrollPaneStudentCourse = new JScrollPane();
		scrollPaneStudentCourse.setBounds(477, 255, 265, 172);
		contentPane.add(scrollPaneStudentCourse);
		
		tableStudentCourse = new JTable();
		scrollPaneStudentCourse.setViewportView(tableStudentCourse);
		
		
		scrollPaneStudentID = new JScrollPane();
		scrollPaneStudentID.setBounds(338, 248, 137, 36);
		contentPane.add(scrollPaneStudentID);
		
		tableStudentID = new JTable();
		scrollPaneStudentID.setViewportView(tableStudentID);
		
		
		JLabel lblStudentCourse = new JLabel("Student Course:");
		lblStudentCourse.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblStudentCourse.setBounds(6, 357, 101, 16);
		contentPane.add(lblStudentCourse);
		
		JButton btnCourseProfile = new JButton("Course Info");
		btnCourseProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
	
					String query="SELECT CourseName FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableStudentCourse.setModel(DbUtils.resultSetToTableModel(rs));

					String queryID="SELECT Name,Surname FROM StudentInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
					PreparedStatement pstID =connection.prepareStatement(queryID);
					ResultSet rsID = pstID.executeQuery();
					tableStudentID.setModel(DbUtils.resultSetToTableModel(rsID));
					
					String queryCredit="SELECT Credit FROM StudentCourseInfo WHERE ID= '"+comboBoxID.getSelectedItem()+"' ";
					PreparedStatement pstCredit =connection.prepareStatement(queryCredit);
					ResultSet rsCredit = pstCredit.executeQuery();
					tableStudentCredit.setModel(DbUtils.resultSetToTableModel(rsCredit));
					
				
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			
			}
		});
		btnCourseProfile.setForeground(Color.BLUE);
		btnCourseProfile.setBounds(235, 251, 101, 29);
		contentPane.add(btnCourseProfile);

		scrollPaneCedit = new JScrollPane();
		scrollPaneCedit.setBounds(754, 255, 67, 36);
		contentPane.add(scrollPaneCedit);
		
		tableStudentCredit = new JTable();
		scrollPaneCedit.setViewportView(tableStudentCredit);
		
		refreshTable();
		refreshComboBox();
		fillComboBoxStudentCourse();
		fillComboBox();
		fillComboBoxCourse();
		fillComboBoxStudentCourse();
		
				
	}
	public JFrame getFrame() {
		return frame;
	}
}
