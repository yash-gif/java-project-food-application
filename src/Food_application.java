import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Food_application extends JFrame  {

	
	private JFrame frame;
	private JTextField jtextFieldFoodName;
	private JTextField jtextFieldFoodPrice;
	private JTable table;
	ArrayList<Food> foodlist;
	String header[] =  new String[] {"Food Name","Food Price","Food Location"};
	DefaultTableModel dtm;
	int row,col;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food_application window = new Food_application();
					
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Food_application() {
		initialize();
		foodlist = new ArrayList<>();
		dtm = new DefaultTableModel(header,0);
		table.setModel(dtm);
		
		JLabel lblFoodName_1 = new JLabel("Food Name");
		lblFoodName_1.setBounds(27, 209, 78, 16);
		frame.getContentPane().add(lblFoodName_1);
		
		JLabel lblFoodPrice_1 = new JLabel("Food Price");
		lblFoodPrice_1.setBounds(281, 208, 72, 16);
		frame.getContentPane().add(lblFoodPrice_1);
		
		JLabel lblFoodLocation_1 = new JLabel("Food Location");
		lblFoodLocation_1.setBounds(532, 209, 94, 16);
		frame.getContentPane().add(lblFoodLocation_1);
		this.setLocationRelativeTo(null);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Food Application");
		frame.setBounds(100, 100, 725, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFoodName = new JLabel("Food Name");
		lblFoodName.setBounds(54, 28, 78, 16);
		frame.getContentPane().add(lblFoodName);
		
		jtextFieldFoodName = new JTextField();
		jtextFieldFoodName.setBounds(141, 25, 304, 22);
		frame.getContentPane().add(jtextFieldFoodName);
		jtextFieldFoodName.setColumns(10);
		
		JLabel lblFoodPrice = new JLabel("Food Price");
		lblFoodPrice.setBounds(54, 73, 67, 16);
		frame.getContentPane().add(lblFoodPrice);
		
		jtextFieldFoodPrice = new JTextField();
		jtextFieldFoodPrice.setBounds(141, 70, 116, 22);
		frame.getContentPane().add(jtextFieldFoodPrice);
		jtextFieldFoodPrice.setColumns(10);
		
		JLabel lblFoodLocation = new JLabel("Food Location");
		lblFoodLocation.setBounds(54, 117, 94, 16);
		frame.getContentPane().add(lblFoodLocation);
		
		JComboBox jCBFoodLocation = new JComboBox();
		jCBFoodLocation.setModel(new DefaultComboBoxModel(new String[] {"None", "Gulal Gali"}));
		jCBFoodLocation.setBounds(151, 114, 78, 22);
		frame.getContentPane().add(jCBFoodLocation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 149, 683, 2);
		frame.getContentPane().add(separator);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String foodname = jtextFieldFoodName.getText();
				int foodprice = Integer.parseInt(jtextFieldFoodPrice.getText());
				String foodloc = jCBFoodLocation.getSelectedItem().toString();
				foodlist.add(new Food(foodname,foodloc,foodprice));
				dtm.setRowCount(0);
				for(int i=0;i<foodlist.size();i++)
				{
					Object[] objs = {foodlist.get(i).foodname,foodlist.get(i).foodprice,foodlist.get(i).foodloc};
					dtm.addRow(objs);
				}
				}catch(NumberFormatException e) {
					 System.out.println("enter foodname, price and location");	
				}
				
				clearField();
				
			}

			private void clearField() {
			
				// TODO Auto-generated method stub
				jtextFieldFoodName.requestFocus();
				jtextFieldFoodName.setText("");
				jtextFieldFoodPrice.setText("");
				jCBFoodLocation.setSelectedIndex(0);
			}
		});
		btnAdd.setBounds(62, 170, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
				String updatedfoodname = jtextFieldFoodName.getText();
				int updatedfoodprice = Integer.parseInt(jtextFieldFoodPrice.getText());
				String updatedfoodloc = jCBFoodLocation.getSelectedItem().toString();
				foodlist.get(row).foodname = updatedfoodname;
				foodlist.get(row).foodprice = updatedfoodprice;
				foodlist.get(row).foodloc= updatedfoodloc;
				dtm.setRowCount(0);
                for (int i = 0 ; i < foodlist.size();i++) {
					
					Object[] objs = { foodlist.get(i).foodname,foodlist.get(i).foodprice,foodlist.get(i).foodloc};
					dtm.addRow(objs);
				}
				
			
			}catch(NumberFormatException e) {
				System.out.println("enter valid data:");
			}
			}
		});
		btnUpdate.setBounds(222, 170, 97, 25);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			int dialogButton = JOptionPane.YES_NO_OPTION;	
			int dialogResult =	JOptionPane.showConfirmDialog(frame,"Delete the Data","Delete",dialogButton);
			if (dialogResult== 0)
			{
				dtm.removeRow(row);
				foodlist.remove(row);
				dtm.setRowCount(0);
                for (int i = 0 ; i < foodlist.size();i++) {
					
					Object[] objs = { foodlist.get(i).foodname,foodlist.get(i).foodprice,foodlist.get(i).foodloc};
					dtm.addRow(objs);
					
				}
                
                clearField();
				
			}
			else {
				
			}
			
				
				
			}
			private void clearField() {
				// TODO Auto-generated method stub
				jtextFieldFoodName.requestFocus();
				jtextFieldFoodName.setText("");
				jtextFieldFoodPrice.setText("");
				jCBFoodLocation.setSelectedIndex(0);
			}
		});
		btnDelete.setBounds(385, 170, 97, 25);
		frame.getContentPane().add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog(this,"Search food Name");
				for(int i=0;i<foodlist.size();i++)
				{
					if(foodlist.get(i).foodname.equalsIgnoreCase(input)) {
						JOptionPane.showMessageDialog(frame,"Found","Search Food",2);
						jtextFieldFoodName.setText(foodlist.get(i).foodname);
						jtextFieldFoodPrice.setText(String.valueOf(foodlist.get(i).foodprice));
						String location = foodlist.get(i).foodloc;
						for(int j=0;j<(jCBFoodLocation.getItemCount());j++)
						{
							if(jCBFoodLocation.getItemAt(i).equals(location)) {
								jCBFoodLocation.setSelectedIndex(i);
							}
						}
						return ;
					}
					
				}
				JOptionPane.showMessageDialog(frame,"Not Found","Search Food",2);
			}
		});
		btnSearch.setBounds(559, 170, 97, 25);
		frame.getContentPane().add(btnSearch);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 223, 683, 2);
		frame.getContentPane().add(separator_1);
		
		table = new JTable();
		table.setBounds(12, 238, 683, 234);
		frame.getContentPane().add(table);
		
		
	}
}
