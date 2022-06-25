package view;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import common.CLogin;
import dao.OrderDao;
import dao.StationDao;
import dao.TickettypeDao;
import dao.UserDao;

//1.常用联系人信息管理 info 2.飞机票查询、飞机票购买 ticket 3.改签以及退票、订单查询 order 4.个人信息 per、修改密码 pwd

public class UserFrm extends JFrame implements ActionListener {
	Date now;
	SimpleDateFormat nowFormat;
	JTabbedPane p_all;
	JTable tbl_info, tbl_ticket, tbl_order;
	JPanel jp_info, jp_ticket, jp_order, jp_per, jp_pwd, jp_perpwd;

	// info
	JPanel jp_info_btn;
	JButton btn_info_revise, btn_info_delete, btn_info_add;
	Object[] cols_info = { "手机号", "姓名", "证件类型", "证件号码", "乘客类型" };
	Object[][] rows_info;

	// ticket
	JPanel jp_ticket_search, jp_ticket_btn;
	JLabel lbl_ticket_trainstart, lbl_ticket_trainend, lbl_ticket_timestart;
	JTextField txt_ticket_trainstart, txt_ticket_trainend, txt_ticket_timestart;
	JRadioButton rbn_ticket_traintype;
	JButton btn_ticket_confirm, btn_ticket_buy;
	Object[] cols_ticket = { "航班", "类型", "起点站", "终到站", "出发时间", "到达时间", "里程", "头等舱", "经济舱", "经济舱" };
	Object[][] rows_ticket;

	// order
	JPanel jp_order_detail, jp_order_btn;
	JButton btn_order_change, btn_order_cancel;
	Object[] cols_order = { "手机号", "姓名", "证件号码", "航班", "起点站", "终到站", "出发时间", "到达时间", "飞机厢号", "座位号", "座位等级", "票价",
			"购买日期" };
	Object[][] rows_order;
	// 改签
	JTable tbl_order_change;
	JButton btn_order_change_buy, btn_order_change_cancel;
	JPanel jp_order_change_btn;

	// per
	JLabel lbl_per_tel, lbl_per_user, lbl_per_idtype, lbl_per_id, lbl_per_usertype;
	JComboBox cmb_per_idtype, cmb_per_usertype;
	JTextField txt_per_tel, txt_per_user, txt_per_id;
	JButton btn_per_revise, btn_per_confirm;
	JPanel jp_per_btn;
	// pwd
	JPanel jp_pwd_setpwd, jp_pwd_conpwd, jp_pwd_btn;
	JLabel lbl_pwd_setpwd, lbl_pwd_conpwd;
	JPasswordField txt_pwd_setpwd, txt_pwd_conpwd;
	JButton btn_pwd_confirm, btn_pwd_cancel;

	String usertel, userpwd;

	String trainstart, trainend, timestart;
	int type;

	DefaultTableModel tModel_info, tModel_ticket, tModel_order;

	UserFrm() {
		jp_info = new JPanel();
		jp_ticket = new JPanel();
		jp_order = new JPanel();
		jp_per = new JPanel();
		jp_pwd = new JPanel();

		usertel = CLogin.accountno;
		userpwd = CLogin.accountpwd;

		// info
		rows_info = UserDao.userpersonQuery(usertel);

		tModel_info = new DefaultTableModel(rows_info, cols_info);
		tbl_info = new JTable(tModel_info);

		btn_info_revise = new JButton("修改常用联系人信息");
		btn_info_delete = new JButton("删除常用联系人");
		btn_info_add = new JButton("添加常用联系人");

		btn_info_revise.addActionListener(this);
		btn_info_delete.addActionListener(this);
		btn_info_add.addActionListener(this);

		jp_info_btn = new JPanel();
		jp_info_btn.setBorder(new TitledBorder("*修改信息：需选中联系人后点击“修改常用联系人信息”，无法修改联系人手机号   *删除信息：需选中联系人点击“删除常用联系人”"));
		jp_info_btn.add(btn_info_revise);
		jp_info_btn.add(btn_info_delete);
		jp_info_btn.add(btn_info_add);

		jp_info.setBorder(new TitledBorder("常用联系人信息"));
		jp_info.setLayout(new BorderLayout());
		jp_info.add(new JScrollPane(tbl_info), BorderLayout.CENTER);
		jp_info.add(jp_info_btn, BorderLayout.SOUTH);

		// ticket

		lbl_ticket_trainstart = new JLabel("    起点站：");
		lbl_ticket_trainend = new JLabel("    终到站：");
		lbl_ticket_timestart = new JLabel("    出发时间：");

		txt_ticket_trainstart = new JTextField(20);
		txt_ticket_trainend = new JTextField(20);
		txt_ticket_timestart = new JTextField(20);

		rbn_ticket_traintype = new JRadioButton("只看A类飞机");
		btn_ticket_confirm = new JButton("确认");

		btn_ticket_confirm.addActionListener(this);

		jp_ticket_search = new JPanel();
		jp_ticket_search.setLayout(new GridLayout(1, 8));
		jp_ticket_search.add(lbl_ticket_trainstart);
		jp_ticket_search.add(txt_ticket_trainstart);
		jp_ticket_search.add(lbl_ticket_trainend);
		jp_ticket_search.add(txt_ticket_trainend);
		jp_ticket_search.add(lbl_ticket_timestart);
		jp_ticket_search.add(txt_ticket_timestart);
		jp_ticket_search.add(rbn_ticket_traintype);
		jp_ticket_search.add(btn_ticket_confirm);

		now = new Date();
		nowFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jp_ticket_search.setBorder(new TitledBorder("*出发时间格式：xxxx（年）-xx（月）-xx（日）"));

		trainstart = txt_ticket_trainstart.getText().trim();
		trainend = txt_ticket_trainend.getText().trim();
		timestart = txt_ticket_timestart.getText();
		type = 0;

		rows_ticket = null;

		tModel_ticket = new DefaultTableModel(rows_ticket, cols_ticket);
		tbl_ticket = new JTable(tModel_ticket);

		btn_ticket_buy = new JButton("购买");
		btn_ticket_buy.addActionListener(this);

		jp_ticket_btn = new JPanel();
		jp_ticket_btn.add(btn_ticket_buy);

		jp_ticket = new JPanel();
		jp_ticket.setBorder(new TitledBorder("当前时间：" + nowFormat.format(now) + "   航班查询："));
		jp_ticket.setLayout(new BorderLayout());
		jp_ticket.add(jp_ticket_search, BorderLayout.NORTH);
		jp_ticket.add(new JScrollPane(tbl_ticket), BorderLayout.CENTER);
		jp_ticket.add(jp_ticket_btn, BorderLayout.SOUTH);

		// order
		rows_order = OrderDao.order_telQuery(usertel);

		tModel_order = new DefaultTableModel(rows_order, cols_order);
		tbl_order = new JTable(tModel_order);

		btn_order_change = new JButton("改签");
		btn_order_cancel = new JButton("退票");

		btn_order_change.addActionListener(this);
		btn_order_cancel.addActionListener(this);

		jp_order_detail = new JPanel();
		jp_order_detail.setBorder(new TitledBorder("订单详情："));
		jp_order_detail.setLayout(new BorderLayout());
		jp_order_detail.add(new JScrollPane(tbl_order), BorderLayout.CENTER);

		jp_order_btn = new JPanel();
		jp_order_btn.add(btn_order_change);
		jp_order_btn.add(btn_order_cancel);

		jp_order.setLayout(new BorderLayout());
		jp_order.add(jp_order_detail, BorderLayout.CENTER);
		jp_order.add(jp_order_btn, BorderLayout.SOUTH);

		// per

		lbl_per_tel = new JLabel("手机号码：");
		lbl_per_user = new JLabel("真实姓名：");
		lbl_per_idtype = new JLabel("证件类型：");
		lbl_per_id = new JLabel("证件号码：");
		lbl_per_usertype = new JLabel("乘客类型：");

		txt_per_tel = new JTextField(15);
		txt_per_user = new JTextField(15);
		txt_per_id = new JTextField(25);

		cmb_per_idtype = new JComboBox();
		cmb_per_idtype.addItem("中国居民身份证");
		cmb_per_idtype.addItem("港澳居民来往内地通行证");
		cmb_per_idtype.addItem("台湾居民来往大陆通行证");
		cmb_per_idtype.addItem("护照");

		cmb_per_usertype = new JComboBox();
		cmb_per_usertype.addItem("成人");
		cmb_per_usertype.addItem("学生");
		btn_per_revise = new JButton("修改");
		btn_per_confirm = new JButton("确认");

		btn_per_revise.addActionListener(this);
		btn_per_confirm.addActionListener(this);

		Object[] per;
		per = UserDao.userInfo(usertel);
		txt_per_tel.setText((String) per[0]);
		txt_per_user.setText((String) per[1]);
		cmb_per_idtype.setSelectedItem((String) per[2]);
		txt_per_id.setText((String) per[3]);
		cmb_per_usertype.setSelectedItem((String) per[4]);

		txt_per_tel.setEditable(false);
		txt_per_user.setEditable(false);
		cmb_per_idtype.setEnabled(false);
		txt_per_id.setEditable(false);
		cmb_per_usertype.setEnabled(false);
		btn_per_confirm.setEnabled(false);

		Box basebox, box1, box2;

		box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(4));
		box1.add(lbl_per_tel);
		box1.add(Box.createVerticalStrut(14));
		box1.add(lbl_per_user);
		box1.add(Box.createVerticalStrut(14));
		box1.add(lbl_per_idtype);
		box1.add(Box.createVerticalStrut(14));
		box1.add(lbl_per_id);
		box1.add(Box.createVerticalStrut(14));
		box1.add(lbl_per_usertype);

		box2 = Box.createVerticalBox();
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_per_tel);
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_per_user);
		box2.add(Box.createVerticalStrut(8));
		box2.add(cmb_per_idtype);
		box2.add(Box.createVerticalStrut(8));
		box2.add(txt_per_id);
		box2.add(Box.createVerticalStrut(8));
		box2.add(cmb_per_usertype);

		basebox = Box.createHorizontalBox();
		basebox.add(Box.createHorizontalStrut(10));
		basebox.add(box1);
		basebox.add(Box.createHorizontalStrut(20));
		basebox.add(box2);
		basebox.add(Box.createHorizontalStrut(10));

		jp_per_btn = new JPanel();
		jp_per_btn.setBorder(new TitledBorder("*无法修改手机号"));
		jp_per_btn.add(btn_per_revise);
		jp_per_btn.add(btn_per_confirm);

		jp_per.setBorder(new TitledBorder("个人信息："));
		jp_per.setLayout(new BorderLayout());
		jp_per.add(basebox, BorderLayout.CENTER);
		jp_per.add(jp_per_btn, BorderLayout.SOUTH);

		// pwd

		lbl_pwd_setpwd = new JLabel("设置新密码：");
		lbl_pwd_conpwd = new JLabel("确认新密码：");

		txt_pwd_setpwd = new JPasswordField(15);
		txt_pwd_conpwd = new JPasswordField(15);

		btn_pwd_confirm = new JButton("确认");
		btn_pwd_cancel = new JButton("取消");

		btn_pwd_confirm.addActionListener(this);
		btn_pwd_cancel.addActionListener(this);

		jp_pwd_setpwd = new JPanel();
		jp_pwd_setpwd.add(lbl_pwd_setpwd);
		jp_pwd_setpwd.add(txt_pwd_setpwd);

		jp_pwd_conpwd = new JPanel();
		jp_pwd_conpwd.add(lbl_pwd_conpwd);
		jp_pwd_conpwd.add(txt_pwd_conpwd);

		jp_pwd_btn = new JPanel();
		jp_pwd_btn.add(btn_pwd_confirm);
		jp_pwd_btn.add(btn_pwd_cancel);

		jp_pwd.setBorder(new TitledBorder("修改密码"));
		jp_pwd.setLayout(new BorderLayout());
		jp_pwd.add(jp_pwd_setpwd, BorderLayout.NORTH);
		jp_pwd.add(jp_pwd_conpwd, BorderLayout.CENTER);
		jp_pwd.add(jp_pwd_btn, BorderLayout.SOUTH);

		// per+pwd
		jp_perpwd = new JPanel();
		jp_perpwd.setLayout(new BorderLayout());
		jp_perpwd.add(jp_per, BorderLayout.NORTH);
		jp_perpwd.add(jp_pwd, BorderLayout.CENTER);

		// all
		p_all = new JTabbedPane(JTabbedPane.LEFT);
		p_all.add("常用联系人信息管理", jp_info);
		p_all.add("航班查询及飞机票购买", jp_ticket);
		p_all.add("订单查询", jp_order);
		p_all.add("用户信息", jp_perpwd);

		setLayout(new BorderLayout());
		add(p_all, BorderLayout.CENTER);

		setTitle("用户");
		setBounds(100, 100, 1800, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_info_revise) {
			new Btn_Info_Revise();
		} else if (e.getSource() == btn_info_delete) {
			Btn_Info_Delete();
		} else if (e.getSource() == btn_info_add) {
			new Btn_Info_Add();
		} else if (e.getSource() == btn_ticket_confirm) {
			Btn_Ticket_Confirm();
		} else if (e.getSource() == btn_ticket_buy) {
			Btn_Ticket_Buy();
		} else if (e.getSource() == btn_order_change) {
			Btn_Order_Change();
		} else if (e.getSource() == btn_order_cancel) {
			Btn_Order_Cancel();
		} else if (e.getSource() == btn_per_revise) {
			Btn_Per_Revise();
		} else if (e.getSource() == btn_per_confirm) {
			Btn_Per_Confirm();
		} else if (e.getSource() == btn_pwd_confirm) {
			Btn_Pwd_Confirm();
		} else if (e.getSource() == btn_pwd_cancel) {
			Btn_Pwd_Cancel();
		}

	}

	class Btn_Info_Revise extends JFrame implements ActionListener {
		JLabel lbl_tel, lbl_pername, lbl_idtype, lbl_perid, lbl_pertype;
		JTextField txt_tel, txt_pername, txt_perid;
		JComboBox cmb_idtype, cmb_pertype;
		JButton btn_confirm;
		String pertel, pername, idtype, perid, pertype;
		int count;

		public void Btn_Info_Revise() {
			count = tbl_info.getSelectedRow();// 获取行数
			if (count == -1) {
				JOptionPane.showMessageDialog(null, "当前未选中常用联系人！", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				pertel = tbl_info.getValueAt(count, 0).toString();
				pername = tbl_info.getValueAt(count, 1).toString();
				idtype = tbl_info.getValueAt(count, 2).toString();
				perid = tbl_info.getValueAt(count, 3).toString();
				pertype = tbl_info.getValueAt(count, 4).toString();
			}
		}

		Btn_Info_Revise() {
			Btn_Info_Revise();
			if (count != -1) {
				lbl_tel = new JLabel("手机号码：");
				lbl_pername = new JLabel("真实姓名：");
				lbl_idtype = new JLabel("证件类型：");
				lbl_perid = new JLabel("证件号码：");
				lbl_pertype = new JLabel("乘客类型：");

				txt_tel = new JTextField(20);
				txt_pername = new JTextField(20);
				txt_perid = new JTextField(50);

				cmb_idtype = new JComboBox();
				cmb_idtype.addItem("中国居民身份证");
				cmb_idtype.addItem("港澳居民来往内地通行证");
				cmb_idtype.addItem("台湾居民来往大陆通行证");
				cmb_idtype.addItem("护照");

				cmb_pertype = new JComboBox();
				cmb_pertype.addItem("成人");
				cmb_pertype.addItem("学生");

				btn_confirm = new JButton("确认修改");
				btn_confirm.addActionListener(this);

				txt_tel.setText(pertel);
				txt_pername.setText(pername);
				cmb_idtype.setSelectedItem(idtype);
				txt_perid.setText(perid);
				cmb_pertype.setSelectedItem(pertype);

				txt_tel.setEditable(false);

				setLayout(new GridLayout(1, 11));
				add(lbl_tel);
				add(txt_tel);
				add(lbl_pername);
				add(txt_pername);
				add(lbl_idtype);
				add(cmb_idtype);
				add(lbl_perid);
				add(txt_perid);
				add(lbl_pertype);
				add(cmb_pertype);
				add(btn_confirm);

				setTitle("修改常用联系人信息");
				setBounds(100, 100, 1300, 80);
				setLocationRelativeTo(null);
				setVisible(true);
				setResizable(false);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		}

		public void actionPerformed(ActionEvent e) {
			String pertel = txt_tel.getText();
			String pername = txt_pername.getText().trim();
			String idtype = (String) cmb_idtype.getSelectedItem();
			String perid = txt_perid.getText().trim();
			String pertype = (String) cmb_pertype.getSelectedItem();

			int k = UserDao.perInfoUpdate(pertel, pername, idtype, perid, pertype);
			if (k == 1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				rows_info = UserDao.userpersonQuery(usertel);
				tModel_info.setDataVector(rows_info, cols_info);
				dispose();
			}
		}

	}

	public void Btn_Info_Delete() {
		int count = tbl_info.getSelectedRow();// 获取行数
		if (count == -1) {
			JOptionPane.showMessageDialog(this, "当前未选中常用联系人！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			String pertel = tbl_info.getValueAt(count, 0).toString();

			int k = UserDao.perInfoDelete(pertel);
			if (k == 1) {
				JOptionPane.showMessageDialog(this, "删除成功");
				rows_info = UserDao.userpersonQuery(usertel);
				tModel_info.setDataVector(rows_info, cols_info);
			}
		}

	}

	class Btn_Info_Add extends JFrame implements ActionListener {
		JLabel lbl_info_add_tel, lbl_info_add_user, lbl_info_add_idtype, lbl_info_add_id, lbl_info_add_usertype;
		JComboBox cmb_info_add_idtype, cmb_info_add_usertype;
		JTextField txt_info_add_tel, txt_info_add_user, txt_info_add_id;
		JButton btn_info_add_confirm, btn_info_add_cancel;
		JPanel jp_info_add_btn;

		Btn_Info_Add() {
			lbl_info_add_tel = new JLabel("手机号码：");
			lbl_info_add_user = new JLabel("真实姓名：");
			lbl_info_add_idtype = new JLabel("证件类型：");
			lbl_info_add_id = new JLabel("证件号码：");
			lbl_info_add_usertype = new JLabel("乘客类型：");

			txt_info_add_tel = new JTextField(20);
			txt_info_add_user = new JTextField(20);
			txt_info_add_id = new JTextField(50);

			cmb_info_add_idtype = new JComboBox();
			cmb_info_add_idtype.addItem("中国居民身份证");
			cmb_info_add_idtype.addItem("港澳居民来往内地通行证");
			cmb_info_add_idtype.addItem("台湾居民来往大陆通行证");
			cmb_info_add_idtype.addItem("护照");

			cmb_info_add_usertype = new JComboBox();
			cmb_info_add_usertype.addItem("成人");
			cmb_info_add_usertype.addItem("学生");

			btn_info_add_confirm = new JButton("确认添加");
			btn_info_add_cancel = new JButton("取消");

			btn_info_add_confirm.addActionListener(this);
			btn_info_add_cancel.addActionListener(this);

			Box basebox, box1, box2;

			box1 = Box.createVerticalBox();
			box1.add(Box.createVerticalStrut(4));
			box1.add(lbl_info_add_tel);
			box1.add(Box.createVerticalStrut(14));
			box1.add(lbl_info_add_user);
			box1.add(Box.createVerticalStrut(14));
			box1.add(lbl_info_add_idtype);
			box1.add(Box.createVerticalStrut(16));
			box1.add(lbl_info_add_id);
			box1.add(Box.createVerticalStrut(16));
			box1.add(lbl_info_add_usertype);

			box2 = Box.createVerticalBox();
			box2.add(Box.createVerticalStrut(8));
			box2.add(txt_info_add_tel);
			box2.add(Box.createVerticalStrut(8));
			box2.add(txt_info_add_user);
			box2.add(Box.createVerticalStrut(8));
			box2.add(cmb_info_add_idtype);
			box2.add(Box.createVerticalStrut(8));
			box2.add(txt_info_add_id);
			box2.add(Box.createVerticalStrut(8));
			box2.add(cmb_info_add_usertype);

			basebox = Box.createHorizontalBox();
			basebox.add(Box.createHorizontalStrut(8));
			basebox.add(box1);
			basebox.add(Box.createHorizontalStrut(20));
			basebox.add(box2);
			basebox.add(Box.createHorizontalStrut(8));

			jp_info_add_btn = new JPanel();
			jp_info_add_btn.add(btn_info_add_confirm);
			jp_info_add_btn.add(btn_info_add_cancel);

			setLayout(new BorderLayout());
			add(basebox, BorderLayout.CENTER);
			add(jp_info_add_btn, BorderLayout.SOUTH);

			setTitle("添加常用联系人");
			setBounds(100, 100, 350, 240);
			setLocationRelativeTo(null);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		public void actionPerformed(ActionEvent e) {
			String tel = txt_info_add_tel.getText().trim();
			String user = txt_info_add_user.getText().trim();
			String id = txt_info_add_id.getText().trim();
			String idtype = (String) cmb_info_add_idtype.getSelectedItem();
			String pertype = (String) cmb_info_add_usertype.getSelectedItem();

			if (e.getSource() == btn_info_add_confirm) {
				if (tel.equals("") || user.equals("") || id.equals("")) {
					JOptionPane.showMessageDialog(this, "信息不能为空！", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					int k = UserDao.perInsert(usertel, tel, user, idtype, id, pertype);
					if (k == 1) {
						JOptionPane.showMessageDialog(this, "添加成功！");
						rows_info = UserDao.userpersonQuery(usertel);
						tModel_info.setDataVector(rows_info, cols_info);
						dispose();
					}
				}
			} else if (e.getSource() == btn_info_add_cancel) {
				dispose();
			}

		}

	}

	public void Btn_Ticket_Confirm() {
		now = new Date();
		nowFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jp_ticket.setBorder(new TitledBorder("当前时间：" + nowFormat.format(now) + "   航班查询："));

		trainstart = txt_ticket_trainstart.getText().trim();
		trainend = txt_ticket_trainend.getText().trim();
		timestart = txt_ticket_timestart.getText().trim();

		if (trainstart.equals("") || trainend.equals("") || timestart.equals("")) {
			JOptionPane.showMessageDialog(null, "起点站、终到站、出发日期均不能为空！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			if (rbn_ticket_traintype.isSelected()) {
				type = 1;
			} else {
				type = 0;
			}
			rows_ticket = StationDao.stationinfoQuery(trainstart, trainend, timestart, type);
//			System.out.println(rows_ticket);
			if (rows_ticket == null) {
				JOptionPane.showMessageDialog(null, "无符合条件航班！");
			}

			tModel_ticket.setDataVector(rows_ticket, cols_ticket);
		}
	}

	public void Btn_Ticket_Buy() {
		int count = tbl_ticket.getSelectedRow();
		if (count == -1) {
			JOptionPane.showMessageDialog(null, "当前未选中航班！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			new Btn_Ticket_Buy();
		}
	}

	class Btn_Ticket_Buy extends JFrame implements ActionListener {
		JLabel lbl_ticket_seat, lbl_ticket_remain, lbl_ticket_person, lbl_ticket_type, lbl_ticket_price;
		JTextField txt_ticket_remain, txt_ticket_price;
		JComboBox cmb_ticket_seat, cmb_ticket_person;
		JRadioButton rbn_ticket_type_stu, rbn_ticket_type_adu;
		JButton btn_ticket_buy_confirm, btn_ticket_buy_cancel;
		JPanel jp_ticket_buy_rbn, jp_ticket_buy_btn;

		int count;
		String seat;
		String trainno, traintype, trainstart, stationinfo, timestart, timereach, stationmile;
		int pricehigh, priceone, pricetwo;

		Btn_Ticket_Buy() {
			// 读取表格选中行
			count = tbl_ticket.getSelectedRow();

			trainno = tbl_ticket.getValueAt(count, 0).toString();
			traintype = tbl_ticket.getValueAt(count, 1).toString();
			trainstart = tbl_ticket.getValueAt(count, 2).toString();
			stationinfo = tbl_ticket.getValueAt(count, 3).toString();
			timestart = tbl_ticket.getValueAt(count, 4).toString();
			timereach = tbl_ticket.getValueAt(count, 5).toString();
			stationmile = tbl_ticket.getValueAt(count, 6).toString();
			pricehigh = Integer.parseInt(tbl_ticket.getValueAt(count, 7).toString());
			priceone = Integer.parseInt(tbl_ticket.getValueAt(count, 8).toString());
			pricetwo = Integer.parseInt(tbl_ticket.getValueAt(count, 9).toString());

			// 界面

			lbl_ticket_seat = new JLabel("请选择座位等级：");
			lbl_ticket_remain = new JLabel("目前余票：");
			lbl_ticket_person = new JLabel("请选择乘客：");
			lbl_ticket_type = new JLabel("乘客类型：");
			lbl_ticket_price = new JLabel("票价：");

			cmb_ticket_seat = new JComboBox();
			cmb_ticket_seat.addItem("头等舱");
			cmb_ticket_seat.addItem("公务舱");
			cmb_ticket_seat.addItem("经济舱");

			txt_ticket_remain = new JTextField(10);
			txt_ticket_remain.setEditable(false);

			cmb_ticket_person = new JComboBox();

			rbn_ticket_type_adu = new JRadioButton("成人");
			rbn_ticket_type_stu = new JRadioButton("学生");

			ButtonGroup bg = new ButtonGroup();
			bg.add(rbn_ticket_type_adu);
			bg.add(rbn_ticket_type_stu);

			jp_ticket_buy_rbn = new JPanel();
			jp_ticket_buy_rbn.add(rbn_ticket_type_adu);
			jp_ticket_buy_rbn.add(rbn_ticket_type_stu);

			txt_ticket_price = new JTextField(10);
			txt_ticket_price.setEditable(false);

			Object[][] perinfo = UserDao.userpersonQuery(usertel);
			for (int i = 0; i < perinfo.length; i++) {
				cmb_ticket_person.addItem(perinfo[i][1]);
			}
			cmb_ticket_seat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seat = (String) cmb_ticket_seat.getSelectedItem();
					if (Objects.equals(seat, "头等舱")) {
						txt_ticket_price.setText("" + pricehigh);
						txt_ticket_remain.setText("" + TickettypeDao.tickethighQuery(trainno));
					} else if (Objects.equals(seat, "公务舱")) {
						txt_ticket_price.setText("" + priceone);
						txt_ticket_remain.setText("" + TickettypeDao.ticketoneQuery(trainno));
					} else if (Objects.equals(seat, "经济舱")) {
						txt_ticket_price.setText("" + pricetwo);
						txt_ticket_remain.setText("" + TickettypeDao.tickettwoQuery(trainno));
					}
				}
			});

			cmb_ticket_person.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String pername = (String) cmb_ticket_person.getSelectedItem();
					int k = UserDao.pertypenoQuery(usertel, pername);
					if (k == 0)
						rbn_ticket_type_adu.setSelected(true);
					else
						rbn_ticket_type_stu.setSelected(true);
				}
			});

			btn_ticket_buy_confirm = new JButton("购买");
			btn_ticket_buy_cancel = new JButton("取消");

			btn_ticket_buy_confirm.addActionListener(this);
			btn_ticket_buy_cancel.addActionListener(this);

			jp_ticket_buy_btn = new JPanel();
			jp_ticket_buy_btn.add(btn_ticket_buy_confirm);
			jp_ticket_buy_btn.add(btn_ticket_buy_cancel);

			Box basebox, box1, box2;
			basebox = Box.createHorizontalBox();
			box1 = Box.createVerticalBox();
			box2 = Box.createVerticalBox();

			box1.add(lbl_ticket_seat);
			box1.add(Box.createVerticalStrut(17));
			box1.add(lbl_ticket_remain);
			box1.add(Box.createVerticalStrut(17));
			box1.add(lbl_ticket_person);
			box1.add(Box.createVerticalStrut(20));
			box1.add(lbl_ticket_type);
			box1.add(Box.createVerticalStrut(20));
			box1.add(lbl_ticket_price);

			box2.add(Box.createVerticalStrut(3));
			box2.add(cmb_ticket_seat);
			box2.add(Box.createVerticalStrut(8));
			box2.add(txt_ticket_remain);
			box2.add(Box.createVerticalStrut(8));
			box2.add(cmb_ticket_person);
			box2.add(Box.createVerticalStrut(8));
			box2.add(jp_ticket_buy_rbn);
			box2.add(Box.createVerticalStrut(8));
			box2.add(txt_ticket_price);

			basebox.add(Box.createHorizontalStrut(10));
			basebox.add(box1);
			basebox.add(box2);
			basebox.add(Box.createHorizontalStrut(10));

			setLayout(new BorderLayout());
			add(basebox, BorderLayout.CENTER);
			add(jp_ticket_buy_btn, BorderLayout.SOUTH);

			setTitle("飞机票购买");
			setBounds(100, 100, 300, 250);
			setLocationRelativeTo(null);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("UserFrm.java 800");
			if (e.getSource() == btn_ticket_buy_confirm) {
				int k1 = 0, k2 = 0, k3 = 0;
				// get乘客信息 放入订单
				String pername = (String) cmb_ticket_person.getSelectedItem();

				// 检查 每人每次只能购买同一天同一航班飞机票一张 k1==1
				k1 = OrderDao.reorderQuery(pername, trainno, timestart);

				if (k1 == 1) {
					// k2==1
					seat = (String) cmb_ticket_seat.getSelectedItem();
					if (Objects.equals(seat, "头等舱")) {
						k2 = TickettypeDao.tickethighReduce(trainno);
					} else if (Objects.equals(seat, "公务舱")) {
						k2 = TickettypeDao.ticketoneReduce(trainno);
					} else if (Objects.equals(seat, "经济舱")) {
						k2 = TickettypeDao.tickettwoReduce(trainno);
					}

					if (k2 == 0) {
						JOptionPane.showMessageDialog(null, "目前无票！");
					} else {
						Object[] train = { trainno, trainstart, stationinfo, timestart, timereach };

						// 获取按钮点击时间
						Date day = new Date();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String buydate = df.format(day);

						// insert订单 k3==2
						k3 = OrderDao.orderInsert(usertel, pername, train, seat, buydate);

						if (k3 == 2) {
							JOptionPane.showMessageDialog(this, "购买成功！");
							dispose();
							rows_order = OrderDao.order_telQuery(usertel);
							tModel_order.setDataVector(rows_order, cols_order);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "每人只能购买同一天同一航班飞机票一张！", "Infomation",
							JOptionPane.WARNING_MESSAGE);
				}

			} else if (e.getSource() == btn_ticket_buy_cancel) {
				dispose();
			}
		}
	}

	public void Btn_Order_Change() {
		int count = tbl_order.getSelectedRow();
		if (count == -1) {
			JOptionPane.showMessageDialog(null, "当前未选中订单！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			new Btn_Order_Change();
		}
	}

	class Btn_Order_Change extends JFrame implements ActionListener {
		// 改签 同一天不同时间的票 地点不变
		int count;
		String pertel, pername, perid, trainno, trainstart, stationinfo, timestart, timereach, seattrain, seattype,
				buytime;
		int seatno, price;
		DefaultTableModel tModel_order_change;

		Object[] cols_order_change = { "航班", "类型", "起点站", "终到站", "出发时间", "到达时间", "里程", "头等舱", "公务舱", "经济舱" };
		Object[][] rows_order_change;

		Btn_Order_Change() {
			// get选中数据
			count = tbl_order.getSelectedRow();

			pertel = tbl_order.getValueAt(count, 0).toString();
			pername = tbl_order.getValueAt(count, 1).toString();
			perid = tbl_order.getValueAt(count, 2).toString();
			trainno = tbl_order.getValueAt(count, 3).toString();
			trainstart = tbl_order.getValueAt(count, 4).toString();
			stationinfo = tbl_order.getValueAt(count, 5).toString();
			timestart = tbl_order.getValueAt(count, 6).toString();
			timereach = tbl_order.getValueAt(count, 7).toString();
			seattrain = tbl_order.getValueAt(count, 8).toString();
			seatno = Integer.parseInt(tbl_order.getValueAt(count, 9).toString());
			seattype = tbl_order.getValueAt(count, 10).toString();
			price = Integer.parseInt(tbl_order.getValueAt(count, 11).toString());
			buytime = tbl_order.getValueAt(count, 12).toString();

			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:ss");
			Date date = null;
			try {
				date = ft.parse(timestart);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			SimpleDateFormat ft_2 = new SimpleDateFormat("yyyy-MM-dd ");
			String time = ft_2.format(date);

			rows_order_change = StationDao.stationinfoQuery(trainstart, stationinfo, time, 0);

			tModel_order_change = new DefaultTableModel(rows_order_change, cols_order_change);
			tbl_order_change = new JTable(tModel_order_change);

			btn_order_change_buy = new JButton("购买");
			btn_order_change_cancel = new JButton("取消");

			btn_order_change_buy.addActionListener(this);
			btn_order_change_cancel.addActionListener(this);

			jp_order_change_btn = new JPanel();
			jp_order_change_btn.add(btn_order_change_buy);
			jp_order_change_btn.add(btn_order_change_cancel);

			setLayout(new BorderLayout());
			add(new JScrollPane(tbl_order_change), BorderLayout.CENTER);
			add(jp_order_change_btn, BorderLayout.SOUTH);

			setTitle("飞机票改签");
			setBounds(100, 100, 1500, 500);
			setLocationRelativeTo(null);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn_order_change_buy) {
				new ticChange();
			} else if (e.getSource() == btn_order_change_cancel) {
				dispose();
			}
		}
	}

	public void ticChange() {
		int count = tbl_order_change.getSelectedRow();
		if (count == -1) {
			JOptionPane.showMessageDialog(null, "当前未选中订单！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			new ticChange();
		}

	}

	class ticChange extends JFrame implements ActionListener {
		JLabel lbl_seat, lbl_remain, lbl_price;
		JTextField txt_remain, txt_price;
		JComboBox cmb_seat;
		JButton btn_confirm, btn_cancel;
		JPanel jp_btn;

		int count;
		String seat;
		String trainno, traintype, trainstart, stationinfo, timestart, timereach, stationmile;
		int pricehigh, priceone, pricetwo;

		ticChange() {
			count = tbl_order_change.getSelectedRow();

			trainno = tbl_order_change.getValueAt(count, 0).toString();
			traintype = tbl_order_change.getValueAt(count, 1).toString();
			trainstart = tbl_order_change.getValueAt(count, 2).toString();
			stationinfo = tbl_order_change.getValueAt(count, 3).toString();
			timestart = tbl_order_change.getValueAt(count, 4).toString();
			timereach = tbl_order_change.getValueAt(count, 5).toString();
			stationmile = tbl_order_change.getValueAt(count, 6).toString();
			pricehigh = Integer.parseInt(tbl_order_change.getValueAt(count, 7).toString());
			priceone = Integer.parseInt(tbl_order_change.getValueAt(count, 8).toString());
			pricetwo = Integer.parseInt(tbl_order_change.getValueAt(count, 9).toString());

			// 界面

			lbl_seat = new JLabel("请选择座位等级：");
			lbl_remain = new JLabel("目前余票：");
			lbl_price = new JLabel("票价：");

			cmb_seat = new JComboBox();
			cmb_seat.addItem("头等舱");
			cmb_seat.addItem("公务舱");
			cmb_seat.addItem("经济舱");

			txt_remain = new JTextField(10);
			txt_remain.setEditable(false);

			txt_price = new JTextField(10);
			txt_price.setEditable(false);

			cmb_seat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seat = (String) cmb_seat.getSelectedItem();
					if (seat.equals("头等舱")) {
						txt_price.setText("" + pricehigh);
						txt_remain.setText("" + TickettypeDao.tickethighQuery(trainno));
					} else if (seat.equals("公务舱")) {
						txt_price.setText("" + priceone);
						txt_remain.setText("" + TickettypeDao.ticketoneQuery(trainno));
					} else if (seat.equals("经济舱")) {
						txt_price.setText("" + pricetwo);
						txt_remain.setText("" + TickettypeDao.tickettwoQuery(trainno));
					}
				}
			});

			btn_confirm = new JButton("购买");
			btn_cancel = new JButton("取消");

			btn_confirm.addActionListener(this);
			btn_cancel.addActionListener(this);

			jp_btn = new JPanel();
			jp_btn.add(btn_confirm);
			jp_btn.add(btn_cancel);

			Box basebox, box1, box2;
			basebox = Box.createHorizontalBox();
			box1 = Box.createVerticalBox();
			box2 = Box.createVerticalBox();

			box1.add(lbl_seat);
			box1.add(Box.createVerticalStrut(17));
			box1.add(lbl_remain);
			box1.add(Box.createVerticalStrut(20));
			box1.add(lbl_price);

			box2.add(Box.createVerticalStrut(3));
			box2.add(cmb_seat);
			box2.add(Box.createVerticalStrut(8));
			box2.add(txt_remain);
			box2.add(Box.createVerticalStrut(8));
			box2.add(txt_price);

			basebox.add(Box.createHorizontalStrut(10));
			basebox.add(box1);
			basebox.add(box2);
			basebox.add(Box.createHorizontalStrut(10));

			setLayout(new BorderLayout());
			add(basebox, BorderLayout.CENTER);
			add(jp_btn, BorderLayout.SOUTH);

			setTitle("选择改签信息");
			setBounds(100, 100, 300, 200);
			setLocationRelativeTo(null);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("UserFrm.java 1062");
			if (e.getSource() == btn_confirm) {
				int k1 = 0, k2 = 0, k3 = 0, k4 = 0;
				// get乘客信息 放入订单
				int count = tbl_order.getSelectedRow();
				String pername = tbl_order.getValueAt(count, 1).toString();
				String trainno = tbl_order.getValueAt(count, 3).toString();
				String timestart = tbl_order.getValueAt(count, 6).toString();
				String seattype = tbl_order.getValueAt(count, 10).toString();
				String seattrain = tbl_order.getValueAt(count, 8).toString();
				int seatno = Integer.parseInt(tbl_order.getValueAt(count, 9).toString());
				// 检查 每人每次只能购买同一天同一航班飞机票一张 k1==1
				k1 = OrderDao.reorderQuery(pername, trainno, timestart);

				if (k1 == 1) {
					// k2==1
					seat = (String) cmb_seat.getSelectedItem();
					if (seat.equals("头等舱")) {
						k2 = TickettypeDao.tickethighReduce(trainno);
					} else if (seat.equals("公务舱")) {
						k2 = TickettypeDao.ticketoneReduce(trainno);
					} else if (seat.equals("经济舱")) {
						k2 = TickettypeDao.tickettwoReduce(trainno);
					}

					if (k2 == 0) {
						JOptionPane.showMessageDialog(null, "目前无票，不可改签！");
					} else {
						Object[] train = { trainno, trainstart, stationinfo, timestart, timereach };

						// 获取按钮点击时间
						Date day = new Date();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String buydate = df.format(day);

						// insert订单 k3==2
						k3 = OrderDao.orderInsert(usertel, pername, train, seat, buydate);

						if (k3 == 2) {

							// k4 返回被删除的订单信息 k4==3
							k4 = OrderDao.orderDelete(pername, trainno, timestart, seattype, seattrain, seatno);
							if (k4 == 3) {

								JOptionPane.showMessageDialog(this, "改签成功！");

								dispose();
								rows_order = OrderDao.order_telQuery(usertel);
								tModel_order.setDataVector(rows_order, cols_order);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "每人只能购买同一天同一航班飞机票一张！", "Infomation",
							JOptionPane.WARNING_MESSAGE);
				}

			} else if (e.getSource() == btn_cancel) {
				dispose();
			}
		}
	}

	public void Btn_Order_Cancel() {
		int count = tbl_order.getSelectedRow();
		if (count == -1) {
			JOptionPane.showMessageDialog(null, "当前未选中订单！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			int confirm = JOptionPane.showConfirmDialog(this, "确定退票吗？", "Information", JOptionPane.OK_CANCEL_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				String pername = tbl_order.getValueAt(count, 1).toString();
				String trainno = tbl_order.getValueAt(count, 3).toString();
				String timestart = tbl_order.getValueAt(count, 6).toString();
				String seattype = tbl_order.getValueAt(count, 10).toString();
				String seattrain = tbl_order.getValueAt(count, 8).toString();
				int seatno = Integer.parseInt(tbl_order.getValueAt(count, 9).toString());
				int k = OrderDao.orderDelete(pername, trainno, timestart, seattype, seattrain, seatno);
				if (k == 3) {
					JOptionPane.showMessageDialog(null, "退票成功！");
					rows_order = OrderDao.order_telQuery(usertel);
					tModel_order.setDataVector(rows_order, cols_order);
				}
			}
		}
	}

	public void Btn_Per_Revise() {
		txt_per_user.setEditable(true);
		cmb_per_idtype.setEnabled(true);
		txt_per_id.setEditable(true);
		cmb_per_usertype.setEnabled(true);
		btn_per_confirm.setEnabled(true);
	}

	public void Btn_Per_Confirm() {
		String username = txt_per_user.getText().trim();
		int idtypeno = UserDao.idtypenoQuery((String) cmb_per_idtype.getSelectedItem());
		String id = txt_per_id.getText().trim();
		int usertypeno = UserDao.usertypenoQuery((String) cmb_per_usertype.getSelectedItem());

		if (username.equals("") || id.equals("")) {
			JOptionPane.showMessageDialog(this, "信息均不能为空！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			int k = UserDao.userinfoUpdate(usertel, username, idtypeno, id, usertypeno);
			if (k == 1) {
				JOptionPane.showMessageDialog(null, "个人信息更新成功！");
				txt_per_user.setEditable(false);
				cmb_per_idtype.setEnabled(false);
				txt_per_id.setEditable(false);
				cmb_per_usertype.setEnabled(false);
				btn_per_confirm.setEnabled(false);
			}
		}
	}

	public void Btn_Pwd_Confirm() {
		System.out.println("Btn_Pwd_Confirm");
		String pwd = String.valueOf(txt_pwd_setpwd.getPassword());
		String conpwd = String.valueOf(txt_pwd_conpwd.getPassword());
		System.out.println(pwd+" " +conpwd);

		if (pwd.equals("") || conpwd.equals("")) {
			JOptionPane.showMessageDialog(this, "密码不能为空！", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			System.out.println(pwd);
			if (pwd.equals(conpwd)) {
				System.out.println(pwd+" "+conpwd+"pwd , conpwd ");
				if (pwd.equals(userpwd)) {
					System.out.println(pwd+" "+userpwd+"pwd , userpwd ");
					JOptionPane.showMessageDialog(this, "新密码不能和原密码相同！", "Information", JOptionPane.WARNING_MESSAGE);
				} else {
					int k = UserDao.userpwdUpdate(usertel, pwd);
					if (k == 1) {
						JOptionPane.showMessageDialog(this, "密码修改成功！请重新登录！");
						new CLogin("manager登录",0);
						dispose();

					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "两次密码不一致！", "Warning", JOptionPane.WARNING_MESSAGE);
				txt_pwd_setpwd.setText(null);
				txt_pwd_conpwd.setText(null);
			}
		}
	}

	public void Btn_Pwd_Cancel() {
		txt_pwd_setpwd.setText(null);
		txt_pwd_conpwd.setText(null);
	}
}
