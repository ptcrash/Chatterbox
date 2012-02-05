package me.JnH.ChatterBox.Applet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class applet extends JApplet {
	public Vector<String> wholist = new Vector<String>();
	private static final long serialVersionUID = 3469083502408530144L;
	EventHandler handler = new EventHandler();
	public void chatadd(String who, String txt){
		chat.append("["+who+"] "+txt+"\n");
	}
	public void chatToApplet(){
		chat.setText(null);
		chatadd("Server","connecting... connected!");
	}
	public boolean verifyConnect(String user, String pass){
		if(user.equals("jobud9")&&pass.equals("hi")){
			return true;
		}
		else{
			return true;
		}
	}
	public void enablelogin(boolean bool){
		jTextField1.setEnabled(bool);
		jPasswordField1.setEnabled(bool);
		loginbtn.setEnabled(bool);
	}
	public Vector<String> getPlayers(){
		wholist.add("etho");
		wholist.add("notch");
		return wholist;
	}
	public void init() {
		initComponents();
		chatToApplet();

		try{
			InetAddress IP = InetAddress.getLocalHost();
			//Socket sock = Socket(IP, 7763);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void initComponents() {
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jTextField1 = new JTextField();
		jPasswordField1 = new JPasswordField();
		jPasswordField1.setColumns(5);
		loginbtn = new JButton();
		loginbtn.addActionListener(handler);
		jScrollPane1 = new JScrollPane();
		who = new JList();
		jScrollPane2 = new JScrollPane();
		chat = new JTextArea();
		jLabel3 = new JLabel();
		jTextField2 = new JTextField();
		sendbtn = new JButton();
		jLabel4 = new JLabel();
		jLabel1.setText("username: ");
		jLabel1.setToolTipText("");
		jLabel2.setText("password: ");
		loginbtn.setText("login");
		who.setVisibleRowCount(5);
		jScrollPane1.setViewportView(who);
		chat.setColumns(20);
		chat.setEditable(false);
		chat.setLineWrap(true);
		chat.setRows(5);
		jScrollPane2.setViewportView(chat);
		jLabel3.setText("Message: ");
		sendbtn.setText("send!");
		sendbtn.setEnabled(false);
		sendbtn.addActionListener(handler);
		jLabel4.setText("Users Online:");

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel1)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel2)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(loginbtn)
										.addGap(182, 182, 182)
										.addComponent(jLabel4)
										.addGap(61, 61, 61))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(jLabel3)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(sendbtn))
																.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap())))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1)
										.addComponent(jLabel2)
										.addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(loginbtn))
										.addGroup(layout.createSequentialGroup()
												.addContainerGap()
												.addComponent(jLabel4)))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																		.addComponent(sendbtn)
																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jLabel3)))))
																				.addContainerGap())
				);
	}
	private JButton loginbtn;
	private JButton sendbtn;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JPasswordField jPasswordField1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JList who;
	private JTextArea chat;
	private JTextField jTextField1;
	private JTextField jTextField2;

	public class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource().equals(loginbtn)){
				String user = jTextField1.getText();
				String pass = jPasswordField1.getPassword().toString();
				chatadd("server", "logging into server...");
				enablelogin(false);
				if(verifyConnect(user, pass)){
					chatadd("server", "connected!");
					sendbtn.setEnabled(true);
					wholist.addAll(getPlayers());
					wholist.add("etho");
				}
				else{
					chatadd("server", "incorrect username and password!");
					enablelogin(true);
				}
			}
			if(event.getSource().equals(sendbtn)){
				chatadd("jobud9",jTextField2.getText());
				jTextField2.setText(null);
			}
		}
	}
}