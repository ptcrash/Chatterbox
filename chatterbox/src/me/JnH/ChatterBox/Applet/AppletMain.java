package me.JnH.ChatterBox.Applet;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class AppletMain extends JApplet{

	    public void init() {
	        try {
	            java.awt.EventQueue.invokeAndWait(new Runnable() {

	                public void run() {
	                    initComponents();
	                }
	            });
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    private void initComponents() {

	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTextArea1 = new javax.swing.JTextArea();
	        jTextField1 = new javax.swing.JTextField();
	        jButton1 = new javax.swing.JButton();
	        
	        jTextArea1.setColumns(20);
	        jTextArea1.setRows(5);
	        jScrollPane1.setViewportView(jTextArea1);
	        jButton1.setText("Send!");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(jButton1)))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton1))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	    }
	    private javax.swing.JButton jButton1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTextArea jTextArea1;
	    private javax.swing.JTextField jTextField1;
	}