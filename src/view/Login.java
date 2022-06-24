package view;

import common.CLogin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Login extends JFrame implements ActionListener{
	JButton btn_user,btn_manager;
	Border titleBorder;
	JLabel lbl_title;
	JPanel pl_all,pl_btn;
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Login();
	}
	
	public Login(){
		btn_user=new JButton("用户");
		btn_manager=new JButton("管理员");
		lbl_title=new JLabel("请选择您的身份：",JLabel.CENTER);
		pl_btn=new JPanel();
		pl_all=new JPanel();
		
		//个性化设置
		
		Font f_title=new Font("楷体",Font.BOLD,20);
		Font f_btn=new Font("楷体",Font.BOLD,22);
		titleBorder=BorderFactory.createTitledBorder(null,"欢迎使用航空订票管理系统！",TitledBorder.CENTER,TitledBorder.TOP,f_title);
		lbl_title.setFont(f_title);
		
		btn_user.setFont(f_btn);
		btn_user.setPreferredSize(new Dimension(150, 200));
		btn_user.setBorderPainted(false);
		
		btn_manager.setFont(f_btn);
		btn_manager.setPreferredSize(new Dimension(150, 200));
		btn_manager.setBorderPainted(false);
		
		pl_btn.setBackground(Color.white);
		pl_all.setBackground(Color.white);
		
		//布局
		pl_btn.add(btn_user);
		pl_btn.add(btn_manager);
		
		pl_all.setLayout(new BorderLayout());
		pl_all.setBorder(titleBorder);
		pl_all.add(lbl_title,BorderLayout.NORTH);
		pl_all.add(pl_btn,BorderLayout.CENTER);
		add(pl_all);
		
		setTitle("航空订票管理系统");
		setBounds(100,100,350,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//操作
		btn_user.addActionListener(this);
		btn_manager.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_user) {
			btn_user.setForeground(Color.blue);
			btn_manager.setForeground(Color.black);
			int confirm=JOptionPane.showConfirmDialog(null,"是否选择用户登录？",null,JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(confirm==JOptionPane.YES_OPTION) {
				new CLogin("user登录",1);
				dispose();
			}
			else btn_user.setForeground(Color.black);
		}
		else{
			btn_manager.setForeground(Color.blue);
			btn_user.setForeground(Color.black);
			int confirm=JOptionPane.showConfirmDialog(null,"是否选择管理员登录？",null,JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(confirm==JOptionPane.YES_OPTION) {
				new CLogin("manager登录",0);
				dispose();
			}
			else btn_manager.setForeground(Color.black);
		}
			
	}
	
}

