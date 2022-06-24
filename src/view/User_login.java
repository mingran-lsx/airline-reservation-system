//package view;
//
//import java.awt.*;
//import javax.swing.*;
//
//import dao.UserDao;
//
//import java.awt.event.*;
//import java.sql.*;
//
////public class User_login extends JFrame implements ActionListener {
////	static String usertel, userpwd;
////	JLabel lbl_user, lbl_pwd;
////	JTextField txt_user;
////	JPasswordField txt_pwd;
////	JButton btn_login, btn_register;
////	JPanel p_btn, p_all;
////
////	public static void main(String args[]) {
////		JFrame.setDefaultLookAndFeelDecorated(true);
////		new User_login();
////	}
////
////	User_login() {
////		lbl_user = new JLabel("手机号：");
////		lbl_pwd = new JLabel("密码：");
////		txt_user = new JTextField(15);
////		txt_pwd = new JPasswordField(15);
////		btn_login = new JButton("登录");
////		btn_register = new JButton("注册");
////		p_btn = new JPanel();
////		p_all = new JPanel();
////
////		// 布局
////		Box basebox, box1, box2;
////		box1 = Box.createVerticalBox();
////		box1.add(Box.createVerticalStrut(18));
////		box1.add(lbl_user);
////		box1.add(Box.createVerticalStrut(35));
////		box1.add(lbl_pwd);
////		box1.add(Box.createVerticalStrut(15));
////		box2 = Box.createVerticalBox();
////		box2.add(Box.createVerticalStrut(13));
////		box2.add(txt_user);
////		box2.add(Box.createVerticalStrut(15));
////		box2.add(txt_pwd);
////		basebox = Box.createHorizontalBox();
////		basebox.add(Box.createHorizontalStrut(10));
////		basebox.add(box1);
////		basebox.add(Box.createHorizontalStrut(30));
////		basebox.add(box2);
////		basebox.add(Box.createHorizontalStrut(10));
////
////		p_btn.setLayout(new GridLayout(1, 2));
////		p_btn.add(btn_login);
////		p_btn.add(btn_register);
////
////		p_all.setLayout(new BorderLayout());
////		add(basebox, BorderLayout.NORTH);
////		add(p_btn, BorderLayout.SOUTH);
////
////		setTitle("用户登录");
////		setBounds(100, 100, 300, 200);
////		setLocationRelativeTo(null);
////		setVisible(true);
////		setResizable(false);
////		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////
////		// 个性化设置
////		Font f = new Font("楷体", Font.BOLD, 18);
////		lbl_user.setFont(f);
////		lbl_pwd.setFont(f);
////
////		btn_login.setFont(f);
////		btn_login.setBorderPainted(false);
////		btn_login.setBackground(Color.white);
////		btn_register.setFont(f);
////		btn_register.setBorderPainted(false);
////		btn_register.setBackground(Color.white);
////
////		// 操作
////		btn_login.addActionListener(this);
////		btn_register.addActionListener(this);
////	}
////
////	public void actionPerformed(ActionEvent e) {
////		if (e.getSource() == btn_login) {
////			Btn_Login();
////		} else {
////			new User_register();
////		}
////	}
////
////	public void Btn_Login() {
////		usertel = txt_user.getText().trim();
////		userpwd = String.valueOf(txt_pwd.getPassword());
////
////		int flag = UserDao.userValidate(usertel, userpwd);
////		if (flag == 1) {
////			JOptionPane.showMessageDialog(this, "登陆成功");
////			UserFrm userfrm = new UserFrm();
////			dispose();
////		} else if (flag == -1) {
////			JOptionPane.showMessageDialog(this, "账号不存在！", "Warning", JOptionPane.WARNING_MESSAGE);
////			txt_user.setText("");
////			txt_pwd.setText("");
////		} else if (flag == 2) {
////			JOptionPane.showMessageDialog(this, "密码错误！", "Warning", JOptionPane.WARNING_MESSAGE);
////			txt_user.setText("");
////			txt_pwd.setText("");
////		}
////	}
////
////}
//public class User_login extends JFrame implements ActionListener {
//	static String usertel, userpwd;
//	JLabel lbl_user, lbl_pwd;
//	JTextField txt_user;
//	JPasswordField txt_pwd;
//	JButton btn_login, btn_register;
//	JPanel p_btn, p_all;
//
//	public static void main(String args[]) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		new User_login();
//	}
//
//	User_login() {
//		lbl_user = new JLabel("手机号：");
//		lbl_pwd = new JLabel("密码：");
//		txt_user = new JTextField(15);
//		txt_pwd = new JPasswordField(15);
//		btn_login = new JButton("登录");
//		btn_register = new JButton("注册");
//		p_btn = new JPanel();
//		p_all = new JPanel();
//
//		// 布局
//		Box basebox, box1, box2;
//		box1 = Box.createVerticalBox();
//		box1.add(Box.createVerticalStrut(18));
//		box1.add(lbl_user);
//		box1.add(Box.createVerticalStrut(35));
//		box1.add(lbl_pwd);
//		box1.add(Box.createVerticalStrut(15));
//		box2 = Box.createVerticalBox();
//		box2.add(Box.createVerticalStrut(13));
//		box2.add(txt_user);
//		box2.add(Box.createVerticalStrut(15));
//		box2.add(txt_pwd);
//		basebox = Box.createHorizontalBox();
//		basebox.add(Box.createHorizontalStrut(10));
//		basebox.add(box1);
//		basebox.add(Box.createHorizontalStrut(30));
//		basebox.add(box2);
//		basebox.add(Box.createHorizontalStrut(10));
//
//		p_btn.setLayout(new GridLayout(1, 2));
//		p_btn.add(btn_login);
//		p_btn.add(btn_register);
//
//		p_all.setLayout(new BorderLayout());
//		add(basebox, BorderLayout.NORTH);
//		add(p_btn, BorderLayout.SOUTH);
//
//		setTitle("用户登录");
//		setBounds(100, 100, 300, 200);
//		setLocationRelativeTo(null);
//		setVisible(true);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//		// 个性化设置
//		Font f = new Font("楷体", Font.BOLD, 18);
//		lbl_user.setFont(f);
//		lbl_pwd.setFont(f);
//
//		btn_login.setFont(f);
//		btn_login.setBorderPainted(false);
//		btn_login.setBackground(Color.white);
//		btn_register.setFont(f);
//		btn_register.setBorderPainted(false);
//		btn_register.setBackground(Color.white);
//
//		// 操作
//		btn_login.addActionListener(this);
//		btn_register.addActionListener(this);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == btn_login) {
//			Btn_Login();
//		} else {
//			new User_register();
//		}
//	}
//
//	public void Btn_Login() {
//		usertel = txt_user.getText().trim();
//		userpwd = String.valueOf(txt_pwd.getPassword());
//
//		int flag = UserDao.userValidate(usertel, userpwd);
//		if (flag == 1) {
//			JOptionPane.showMessageDialog(this, "登陆成功");
//			UserFrm userfrm = new UserFrm();
//			dispose();
//		} else if (flag == -1) {
//			JOptionPane.showMessageDialog(this, "账号不存在！", "Warning", JOptionPane.WARNING_MESSAGE);
//			txt_user.setText("");
//			txt_pwd.setText("");
//		} else if (flag == 2) {
//			JOptionPane.showMessageDialog(this, "密码错误！", "Warning", JOptionPane.WARNING_MESSAGE);
//			txt_user.setText("");
//			txt_pwd.setText("");
//		}
//	}
//
//}