package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import dao.UserDao;

public class User_register extends JFrame implements ActionListener{
	JLabel lbl_tel,lbl_pwd,lbl_conpwd,lbl_user,lbl_idtype,lbl_id,lbl_usertype;
	JComboBox cmb_idtype,cmb_usertype;
	JTextField txt_tel,txt_user,txt_id;
	JPasswordField txt_pwd,txt_conpwd;
	JRadioButton rbn_read;
	JButton btn_confirm,btn_cancel;
	JPanel jp_bn,jp_rbn,jp_btn;
	
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new User_register();
	}
	
	User_register(){
		lbl_tel=new JLabel("手机号码：");
		lbl_pwd=new JLabel("设置密码：");
		lbl_conpwd=new JLabel("确认密码：");
		lbl_user=new JLabel("真实姓名：");
		lbl_idtype=new JLabel("证件类型：");
		lbl_id=new JLabel("证件号码：");
		lbl_usertype=new JLabel("乘客类型：");
		
		txt_tel=new JTextField(15);
		txt_pwd=new JPasswordField(15);
		txt_conpwd=new JPasswordField(15);
		txt_user=new JTextField(15);
		txt_id=new JTextField(25);
		
		cmb_idtype=new JComboBox();
		cmb_idtype.addItem("中国居民身份证");
		cmb_idtype.addItem("港澳居民来往内地通行证");
		cmb_idtype.addItem("台湾居民来往大陆通行证");
		cmb_idtype.addItem("护照");
		
		cmb_usertype=new JComboBox();
		cmb_usertype.addItem("成人");
		cmb_usertype.addItem("学生");
		
		rbn_read=new JRadioButton("我已阅读并同意遵守《中国铁路客户服务中心网站服务条款》、《隐私权政策》");
		
		btn_confirm=new JButton("确认");
		btn_cancel=new JButton("取消");
		
		btn_confirm.addActionListener(this);
		btn_cancel.addActionListener(this);
		
		Box basebox,box1,box2;
		
		box1=Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(4));
		box1.add(lbl_tel);
		box1.add(Box.createVerticalStrut(17));
		box1.add(lbl_pwd);
		box1.add(Box.createVerticalStrut(17));
		box1.add(lbl_conpwd);
		box1.add(Box.createVerticalStrut(17));
		box1.add(lbl_user);
		box1.add(Box.createVerticalStrut(17));
		box1.add(lbl_idtype);
		box1.add(Box.createVerticalStrut(17));
		box1.add(lbl_id);
		box1.add(Box.createVerticalStrut(17));
		box1.add(lbl_usertype);
		
		box2=Box.createVerticalBox();
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_tel);
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_pwd);
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_conpwd);
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_user);
		box2.add(Box.createVerticalStrut(8));
		box2.add(cmb_idtype);
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_id);
		box2.add(Box.createVerticalStrut(8));
		box2.add(cmb_usertype);
		
		basebox=Box.createHorizontalBox();
		basebox.add(Box.createHorizontalStrut(10));
		basebox.add(box1);
		basebox.add(Box.createHorizontalStrut(20));
		basebox.add(box2);
		basebox.add(Box.createHorizontalStrut(10));
		
		jp_rbn=new JPanel();
		jp_rbn.add(rbn_read);
		
		jp_btn=new JPanel();
		jp_btn.add(btn_confirm);
		jp_btn.add(btn_cancel);
		
		jp_bn=new JPanel();
		jp_bn.setLayout(new BorderLayout());
		jp_bn.add(jp_rbn,BorderLayout.NORTH);
		jp_bn.add(jp_btn,BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(basebox,BorderLayout.CENTER);
		add(jp_bn,BorderLayout.SOUTH);
		
		setTitle("用户注册");
		setBounds(300,400,500,350);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_confirm) {
			Btn_Confirm();
		}
		else if(e.getSource()==btn_cancel) {
			Btn_Cancel();
		}
	}
	
	private void Btn_Confirm(){
		String pwd=String.valueOf(txt_pwd.getPassword());
		String conpwd=String.valueOf(txt_conpwd.getPassword());
		String tel=txt_tel.getText().trim();
		String user=txt_user.getText().trim();
		String id=txt_id.getText().trim();
		String idtype=(String)cmb_idtype.getSelectedItem();
		String usertype=(String)cmb_usertype.getSelectedItem();
		
		if(tel.equals("") || pwd.equals("") || conpwd.equals("") || user.equals("") || id.equals("")) {
				JOptionPane.showMessageDialog(this, "信息不能为空！","Warning",JOptionPane.WARNING_MESSAGE);
		}
		else {
			if(rbn_read.isSelected()) {
				if(pwd.equals(conpwd)) {
					int k=UserDao.userInsert(tel,pwd,user,idtype,id,usertype);
					if(k==1) {	
						JOptionPane.showMessageDialog(this, "注册成功！");
						dispose();
					}
				}
				
				else {
					JOptionPane.showMessageDialog(this, "两次密码不一致！请重新输入","Warning",JOptionPane.WARNING_MESSAGE);
					txt_pwd.setText(null);		
					txt_conpwd.setText(null);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "需先阅读并同意遵守《中国铁路客户服务中心网站服务条款》、《隐私权政策》！","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private void Btn_Cancel() {
		dispose();	
	}
	
}
