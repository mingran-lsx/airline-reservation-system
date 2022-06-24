//package view;
//
//import java.awt.*;
//import javax.swing.*;
//
//import dao.ManagerDao;
//
//import java.awt.event.*;
//
//public class Manager_login extends JFrame implements ActionListener{
//	JLabel lbl_manager,lbl_pwd;
//	JTextField txt_manager;
//	JPasswordField txt_pwd;
//	JButton btn_login,btn_cancel;
//	JPanel p_btn,p_all;
//	static String managerno;
//	static String managerpwd;
//
//	public static void main(String args[]) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		new Manager_login();
//	}
//
//	Manager_login(){
//		lbl_manager=new JLabel("账号：");
//		lbl_pwd=new JLabel("密码：");
//		txt_manager=new JTextField(15);
//		txt_pwd=new JPasswordField(15);
//		btn_login=new JButton("登录");
//		btn_cancel=new JButton("取消");
//		p_btn=new JPanel();
//		p_all=new JPanel();
//
//		//布局
//		Box basebox,box1,box2;
//		box1=Box.createVerticalBox();
//		box1.add(Box.createVerticalStrut(18));
//		box1.add(lbl_manager);
//		box1.add(Box.createVerticalStrut(35));
//		box1.add(lbl_pwd);
//		box1.add(Box.createVerticalStrut(15));
//		box2=Box.createVerticalBox();
//		box2.add(Box.createVerticalStrut(13));
//		box2.add(txt_manager);
//		box2.add(Box.createVerticalStrut(15));
//		box2.add(txt_pwd);
//		basebox=Box.createHorizontalBox();
//		basebox.add(Box.createHorizontalStrut(10));
//		basebox.add(box1);
//		basebox.add(Box.createHorizontalStrut(30));
//		basebox.add(box2);
//		basebox.add(Box.createHorizontalStrut(10));
//
//		p_btn.setLayout(new GridLayout(1,2));
//		p_btn.add(btn_login);
//		p_btn.add(btn_cancel);
//
//		p_all.setLayout(new BorderLayout());
//		add(basebox,BorderLayout.NORTH);
//		add(p_btn,BorderLayout.SOUTH);
//
//		setTitle("管理员登录");
//		setBounds(100, 100, 300, 200);
//		setLocationRelativeTo(null);
//		setVisible(true);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//		//个性化设置
//		Font f=new Font("楷体",Font.BOLD,18);
//		lbl_manager.setFont(f);
//		lbl_pwd.setFont(f);
//
//		btn_login.setFont(f);
//		btn_login.setBorderPainted(false);
//		btn_login.setBackground(Color.white);
//		btn_cancel.setFont(f);
//		btn_cancel.setBorderPainted(false);
//		btn_cancel.setBackground(Color.white);
//
//		//操作
//		btn_login.addActionListener(this);
//		btn_cancel.addActionListener(this);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==btn_login) {
//			Btn_Login();
//		}
//		else if(e.getSource()==btn_cancel){
//			Btn_Cancel();
//		}
//	}
//
//	private void Btn_Login() {
//		managerno=txt_manager.getText();
//		managerpwd=String.valueOf(txt_pwd.getPassword());
//
//		int flag=ManagerDao.managerValidate(managerno, managerpwd);
//		if(flag==1) {//登录成功
//			JOptionPane.showMessageDialog(this,"欢迎登录！");
//			new ManagerFrm();
//			dispose();
//		}
//		else if(flag==2) {
//			txt_manager.setText(null);
//			txt_pwd.setText(null);
//			JOptionPane.showMessageDialog(this, "密码错误！","warning",JOptionPane.WARNING_MESSAGE);
//		}
//	    else if(flag==-1){
//			JOptionPane.showMessageDialog(this, "账号不存在！","warning",JOptionPane.WARNING_MESSAGE);
//	    }
//	}
//
//	private void Btn_Cancel() {
//		txt_manager.setText(null);
//		txt_pwd.setText(null);
//	}
//}
