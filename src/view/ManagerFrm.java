package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import common.CLogin;
import dao.ManagerDao;
import dao.OrderDao;
import dao.PriceDao;
import dao.StationDao;
import dao.PlaneDao;
import entity.Price;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ManagerFrm extends JFrame implements ActionListener {
    //1. 路线管理 road 2. 票价管理 price 3. 站点管理 station 4. 优惠活动 discount 5. 订单查询 order 6.  密码修改 password
    JTabbedPane p_all; // 全部面板
    JPanel jp_road, jp_price, jp_station, jp_discount, jp_order, jp_sale, jp_pwd;   // 单个面板

    //road
    JTable tbl_road;    // 路线表格
    JLabel lbl_road_planeno, lbl_road_planetype, lbl_road_planestart, lbl_road_planeend, lbl_road_timestart, lbl_road_timereach, lbl_road_timemove; // 路线信息标签
    JTextField txt_road_planeno, txt_road_planestart, txt_road_planeend, txt_road_timestart, txt_road_timereach, txt_road_timemove; // 路线信息文本框
    JComboBox cmb_road_planetype;   // 路线信息下拉框
    JButton btn_road_addplane, btn_road_addstation, btn_road_revise, btn_road_confirm, btn_road_delete; // 路线信息按钮
    JPanel jp_road_detail, jp_road_btn; // 路线信息面板
    Object[] cols_road = {"航班", "类型", "起点站", "终到站", "出发时间", "到达时间", "运行时间"};    // 路线表格列名
    Object[][] rows_road;   // 路线表格行数据

    //price
    JTable tbl_price;
    JPanel jp_price_detail, jp_price_revise;
    JLabel lbl_price_station1, lbl_price_station2, lbl_price_planeno;
    JTextField txt_price_station1, txt_price_station2, txt_price_planeno;
    JButton btn_price_search;
    JLabel lbl_price_high, lbl_price_one, lbl_price_two;
    JTextField txt_price_high, txt_price_one, txt_price_two;
    JButton btn_price_revise;
    Object[] cols_price = {"航班", "站点序号", "站点信息", "到达时间", "起飞时间", "里程"};
    Object[][] rows_price;

    //station
    JTable tbl_station;
    JLabel lbl_station_planeno, lbl_station_stationno, lbl_station_stationinfo, lbl_station_timereach, lbl_station_timestart, lbl_station_stationmile;
    JTextField txt_station_planeno, txt_station_stationno, txt_station_stationinfo, txt_station_timereach, txt_station_timestart, txt_station_stationmile;
    JButton btn_station_add, btn_station_revise, btn_station_confirm, btn_station_delete;
    JPanel jp_station_detail, jp_station_btn;
    Object[] cols_station = {"航班", "站点序号", "站点信息", "到达时间", "起飞时间", "里程"};
    Object[][] rows_station;

    //discount
    JTable tbl_discount;
    JLabel lbl_discount_now, lbl_discount_timestart;
    Date now;
    SimpleDateFormat nowFormat;
    JTextField txt_discount_timestart;
    JButton btn_discount_search, btn_discount_open, btn_discount_cancel;
    JPanel jp_discount_operate;
    Object[] cols_discount = {"航班", "类型", "起点站", "终到站", "出发时间", "到达时间", "经历时间","机票折扣","里程数","积分","头等舱余票","公务舱余票", "经济舱余票","折扣活动开放情况"};
    Object[][] rows_discount;

    //order
    JTable tbl_order;
    JLabel lbl_order_detail;
    JTextField txt_order_detail;
    JButton btn_order_search;
    JPanel jp_order_search;
    Object[] cols_order = {"手机号", "姓名", "证件号码", "航班", "起点站", "终到站", "起飞时间", "到达时间", "飞机厢号", "座位号", "座位等级", "票价", "购买日期"};
    Object[][] rows_order;

    //password
    JPanel jp_pwd_setpwd, jp_pwd_conpwd, jp_pwd_btn;
    JLabel lbl_pwd_setpwd, lbl_pwd_conpwd;
    JPasswordField txt_pwd_setpwd, txt_pwd_conpwd;
    JButton btn_pwd_confirm, btn_pwd_cancel;

    String managerno, managerpwd;

    DefaultTableModel tModel_road, tModel_price, tModel_station, tModel_discount, tModel_order;// 表格模型

    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new ManagerFrm();
    }


    public ManagerFrm() {
        managerno = CLogin.accountno;
        managerpwd = CLogin.accountpwd;

        jp_road = new JPanel();
        jp_price = new JPanel();
        jp_station = new JPanel();
        jp_discount = new JPanel();
        jp_order = new JPanel();
        jp_sale = new JPanel();
        jp_pwd = new JPanel();

        //road

        rows_road = PlaneDao.planeinfo();

        tModel_road = new DefaultTableModel(rows_road, cols_road);
        tbl_road = new JTable(tModel_road);

        lbl_road_planeno = new JLabel("        航班：");
        lbl_road_planetype = new JLabel("        类型：");
        lbl_road_planestart = new JLabel("        起点站：");
        lbl_road_planeend = new JLabel("        终到站：");
        lbl_road_timestart = new JLabel("        出发时间：");
        lbl_road_timereach = new JLabel("        到达时间：");
        lbl_road_timemove = new JLabel("        运行时间：");

        txt_road_planeno = new JTextField(15);
        txt_road_planestart = new JTextField(20);
        txt_road_planeend = new JTextField(20);
        txt_road_timestart = new JTextField(20);
        txt_road_timereach = new JTextField(20);
        txt_road_timemove = new JTextField(20);

        cmb_road_planetype = new JComboBox();
        cmb_road_planetype.addItem("A类飞机");
        cmb_road_planetype.addItem("B类飞机");
        cmb_road_planetype.addItem("C类飞机");

        jp_road_detail = new JPanel();
        jp_road_btn = new JPanel();

        btn_road_addplane = new JButton("录入航班");
        btn_road_addstation = new JButton("录入站点");
        btn_road_revise = new JButton("修改路线");
        btn_road_confirm = new JButton("确认修改");
        btn_road_delete = new JButton("删除航班");

        btn_road_confirm.setEnabled(false);

        btn_road_addplane.addActionListener(this);
        btn_road_addstation.addActionListener(this);
        btn_road_revise.addActionListener(this);
        btn_road_confirm.addActionListener(this);
        btn_road_delete.addActionListener(this);

        jp_road_detail.setLayout(new GridLayout(2, 8));
        jp_road_detail.add(lbl_road_planeno);
        jp_road_detail.add(txt_road_planeno);
        jp_road_detail.add(lbl_road_planetype);
        jp_road_detail.add(cmb_road_planetype);
        jp_road_detail.add(lbl_road_planestart);
        jp_road_detail.add(txt_road_planestart);
        jp_road_detail.add(lbl_road_planeend);
        jp_road_detail.add(txt_road_planeend);
        jp_road_detail.add(lbl_road_timestart);
        jp_road_detail.add(txt_road_timestart);
        jp_road_detail.add(lbl_road_timereach);
        jp_road_detail.add(txt_road_timereach);
        jp_road_detail.add(lbl_road_timemove);
        jp_road_detail.add(txt_road_timemove);
        jp_road_detail.add(btn_road_addplane);
        jp_road_detail.add(btn_road_addstation);

        jp_road_btn.setBorder(new TitledBorder("*修改路线：选中航班后点击“修改路线”，修改完毕点击“确认修改”（不可修改航班）"));
        jp_road_btn.add(btn_road_revise);
        jp_road_btn.add(btn_road_confirm);
        jp_road_btn.add(btn_road_delete);

        jp_road.setLayout(new BorderLayout());
        jp_road.add(new JScrollPane(tbl_road), BorderLayout.NORTH);
        jp_road.add(jp_road_detail, BorderLayout.CENTER);
        jp_road.add(jp_road_btn, BorderLayout.SOUTH);


        //price

        lbl_price_planeno = new JLabel("航班：");
        lbl_price_station1 = new JLabel("起点站：");
        lbl_price_station2 = new JLabel("到站：");

        txt_price_planeno = new JTextField(20);
        txt_price_station1 = new JTextField(20);
        txt_price_station2 = new JTextField(20);

        btn_price_search = new JButton("查找");

        btn_price_search.addActionListener(this);

        jp_price_detail = new JPanel();
        jp_price_detail.add(lbl_price_planeno);
        jp_price_detail.add(txt_price_planeno);
        jp_price_detail.add(lbl_price_station1);
        jp_price_detail.add(txt_price_station1);
        jp_price_detail.add(lbl_price_station2);
        jp_price_detail.add(txt_price_station2);
        jp_price_detail.add(btn_price_search);

        lbl_price_high = new JLabel("头等舱：");
        lbl_price_one = new JLabel("公务舱：");
        lbl_price_two = new JLabel("经济舱：");

        txt_price_high = new JTextField(10);
        txt_price_one = new JTextField(10);
        txt_price_two = new JTextField(10);

        btn_price_revise = new JButton("修改票价");
        btn_price_revise.addActionListener(this);

        jp_price_revise = new JPanel();
        jp_price_revise.add(lbl_price_high);
        jp_price_revise.add(txt_price_high);
        jp_price_revise.add(lbl_price_one);
        jp_price_revise.add(txt_price_one);
        jp_price_revise.add(lbl_price_two);
        jp_price_revise.add(txt_price_two);
        jp_price_revise.add(btn_price_revise);

        rows_price = null;

        tModel_price = new DefaultTableModel(rows_price, cols_price);
        tbl_price = new JTable(tModel_price);

        jp_price.setLayout(new BorderLayout());
        jp_price.setBorder(new TitledBorder("票价查询及修改"));
        jp_price.add(jp_price_detail, BorderLayout.NORTH);
        jp_price.add(new JScrollPane(tbl_price), BorderLayout.CENTER);
        jp_price.add(jp_price_revise, BorderLayout.SOUTH);


        //station

        rows_station = StationDao.stationQuery();

        tModel_station = new DefaultTableModel(rows_station, cols_station);
        tbl_station = new JTable(tModel_station);

        lbl_station_planeno = new JLabel("        航班：");
        lbl_station_stationno = new JLabel("        站点序号：");
        lbl_station_stationinfo = new JLabel("        站点信息：");
        lbl_station_timereach = new JLabel("        到达时间：");
        lbl_station_timestart = new JLabel("        起飞时间：");
        lbl_station_stationmile = new JLabel("        里程：");

        txt_station_planeno = new JTextField(10);
        txt_station_stationno = new JTextField(20);
        txt_station_stationinfo = new JTextField(20);
        txt_station_timereach = new JTextField(20);
        txt_station_timestart = new JTextField(20);
        txt_station_stationmile = new JTextField(20);

        jp_station_detail = new JPanel();
        jp_station_btn = new JPanel();

        btn_station_add = new JButton("增加站点");
        btn_station_revise = new JButton("修改站点");
        btn_station_confirm = new JButton("确认修改");
        btn_station_delete = new JButton("删除站点");

        btn_station_confirm.setEnabled(false);

        btn_station_add.addActionListener(this);
        btn_station_revise.addActionListener(this);
        btn_station_confirm.addActionListener(this);
        btn_station_delete.addActionListener(this);

        jp_station_detail.setLayout(new GridLayout(2, 8));
        jp_station_detail.add(lbl_station_planeno);
        jp_station_detail.add(txt_station_planeno);
        jp_station_detail.add(lbl_station_stationno);
        jp_station_detail.add(txt_station_stationno);
        jp_station_detail.add(lbl_station_stationinfo);
        jp_station_detail.add(txt_station_stationinfo);
        jp_station_detail.add(lbl_station_timereach);
        jp_station_detail.add(txt_station_timereach);
        jp_station_detail.add(lbl_station_timestart);
        jp_station_detail.add(txt_station_timestart);
        jp_station_detail.add(lbl_station_stationmile);
        jp_station_detail.add(txt_station_stationmile);

        jp_station_btn.setBorder(new TitledBorder("*修改站点：选中站点后点击“修改站点”，修改完毕点击“确认修改”（不可修改航班、站点序号）"));
        jp_station_btn.add(btn_station_add);
        jp_station_btn.add(btn_station_revise);
        jp_station_btn.add(btn_station_confirm);
        jp_station_btn.add(btn_station_delete);

        jp_station.setLayout(new BorderLayout());
        jp_station.add(new JScrollPane(tbl_station), BorderLayout.NORTH);
        jp_station.add(jp_station_detail, BorderLayout.CENTER);
        jp_station.add(jp_station_btn, BorderLayout.SOUTH);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Discount
        now = new Date();
        nowFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        lbl_discount_now = new JLabel("当前时间：" + nowFormat.format(now));
        lbl_discount_timestart = new JLabel("         查找起飞日期：");
        txt_discount_timestart = new JTextField(20);//

        btn_discount_search = new JButton("查找航班(起飞日期)");
        btn_discount_open = new JButton("开放折扣活动");
        btn_discount_cancel = new JButton("停止折扣活动");

        btn_discount_search.addActionListener(this);//.addActionListener(this); 是用来添加监听器的，而不是用来添加事件的，所以不能用addActionListener(this)
        btn_discount_open.addActionListener(this);
        btn_discount_cancel.addActionListener(this);

        jp_discount_operate = new JPanel();
        jp_discount_operate.add(lbl_discount_now);
        jp_discount_operate.add(lbl_discount_timestart);
        jp_discount_operate.add(txt_discount_timestart);
        jp_discount_operate.add(btn_discount_search);
        jp_discount_operate.add(btn_discount_open);
        jp_discount_operate.add(btn_discount_cancel);


        rows_discount = null;
        tModel_discount = new DefaultTableModel(rows_discount, cols_discount);
        tbl_discount = new JTable(tModel_discount);
        //  以下是 分页控件
        jp_discount.setBorder(new TitledBorder("*机票积分,优惠设置*"));
        jp_discount.setLayout(new BorderLayout());
        jp_discount.add(jp_discount_operate, BorderLayout.NORTH);//BorderLayout.NORTH  是把jp_discount_operate放在上面
        jp_discount.add(new JScrollPane(tbl_discount), BorderLayout.CENTER);//BorderLayout.CENTER 是把tbl_discount放在中间
        ////////////////////////////////////////////////////////////////////////////////////////////////


        //order

        lbl_order_detail = new JLabel("查找订单（手机号搜索）：");
        txt_order_detail = new JTextField(20);
        btn_order_search = new JButton("确认");

        btn_order_search.addActionListener(this);

        jp_order_search = new JPanel();
        jp_order_search.add(lbl_order_detail);
        jp_order_search.add(txt_order_detail);
        jp_order_search.add(btn_order_search);


        rows_order = OrderDao.orderQuery();

        tbl_order = new JTable(rows_order, cols_order);

        jp_order.setLayout(new BorderLayout());
        jp_order.add(jp_order_search, BorderLayout.NORTH);
        jp_order.add(new JScrollPane(tbl_order), BorderLayout.CENTER);


        //password
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

        jp_pwd.setLayout(new BorderLayout());
        jp_pwd.add(jp_pwd_setpwd, BorderLayout.NORTH);
        jp_pwd.add(jp_pwd_conpwd, BorderLayout.CENTER);
        jp_pwd.add(jp_pwd_btn, BorderLayout.SOUTH);


        p_all = new JTabbedPane(JTabbedPane.LEFT);
        p_all.add("路线管理", jp_road);
        p_all.add("票价管理", jp_price);
        p_all.add("路线管理", jp_station);
        p_all.add("机票销售,折扣,积分", jp_discount);
        p_all.add("订单查询", jp_order);
        p_all.add("密码修改", jp_pwd);


        setLayout(new BorderLayout());
        add(p_all, BorderLayout.CENTER);

        setTitle("管理员");
        setBounds(100, 100, 1500, 580);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_road_addplane) {
            new Btn_Road_Addplane();
        } else if (e.getSource() == btn_road_addstation) {
            new Btn_Road_Addstation();
        } else if (e.getSource() == btn_road_revise) {
            Btn_Road_Revise();
        } else if (e.getSource() == btn_road_confirm) {
            Btn_Road_Confirm();
        } else if (e.getSource() == btn_road_delete) {
            Btn_Road_Delete();
        } else if (e.getSource() == btn_price_search) {
            Btn_Price_Search();
        } else if (e.getSource() == btn_price_revise) {
            Btn_Price_Revise();
        } else if (e.getSource() == btn_station_add) {
            Btn_Station_Add();
        } else if (e.getSource() == btn_station_revise) {
            Btn_Station_Revise();
        } else if (e.getSource() == btn_station_confirm) {
            Btn_Station_Confirm();
        } else if (e.getSource() == btn_station_delete) {
            Btn_Station_Delete(); //删除站点
        } else if (e.getSource() == btn_discount_search) {
            Btn_discount_Search();//    查询优惠活动
        } else if (e.getSource() == btn_discount_open) {
            Btn_discount_Open();    //开启优惠活动
        } else if (e.getSource() == btn_discount_cancel) {
            Btn_discount_Cancel();  //取消优惠活动
        } else if (e.getSource() == btn_order_search) {
            Btn_Order_Search();   //查询订单
        } else if (e.getSource() == btn_pwd_confirm) {
            Btn_Pwd_Confirm();  //确认修改密码
        } else if (e.getSource() == btn_pwd_cancel) {
            Btn_Pwd_Cancel();   //取消修改密码
        }
    }

    class Btn_Road_Addplane extends JFrame implements ActionListener {
        JLabel lbl_no, lbl_typeno, lbl_planestart, lbl_planeend, lbl_timestart, lbl_timereach, lbl_timemove, lbl_ticket;
        JTextField txt_no, txt_typeno, txt_planestart, txt_planeend, txt_timestart, txt_timereach, txt_timemove, txt_ticket;
        JButton btn_confirm;
        JPanel jp_btn;

        Btn_Road_Addplane() {
            lbl_no = new JLabel("航班：");
            lbl_typeno = new JLabel("类型：（0:A类飞机 1:B类飞机 2:C类飞机）");
            lbl_planestart = new JLabel("起点站：");
            lbl_planeend = new JLabel("终点站：");
            lbl_timestart = new JLabel("起飞时间：");
            lbl_timereach = new JLabel("到达时间：");
            lbl_timemove = new JLabel("运行时间：");
            lbl_ticket = new JLabel("飞机票数量：");

            txt_no = new JTextField(15);
            txt_typeno = new JTextField(10);
            txt_planestart = new JTextField(20);
            txt_planeend = new JTextField(20);
            txt_timestart = new JTextField(20);
            txt_timereach = new JTextField(20);
            txt_timemove = new JTextField(20);
            txt_ticket = new JTextField(10);

            btn_confirm = new JButton("确认");
            btn_confirm.addActionListener(this);

            Box basebox, box1, box2;

            box1 = Box.createVerticalBox();
            box1.add(Box.createVerticalStrut(4));
            box1.add(lbl_no);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_typeno);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_planestart);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_planeend);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_timestart);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_timereach);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_timemove);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_ticket);

            box2 = Box.createVerticalBox();
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_no);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_typeno);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_planestart);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_planeend);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_timestart);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_timereach);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_timemove);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_ticket);

            basebox = Box.createHorizontalBox();
            basebox.add(Box.createHorizontalStrut(10));
            basebox.add(box1);
            basebox.add(Box.createHorizontalStrut(10));
            basebox.add(box2);
            basebox.add(Box.createHorizontalStrut(10));

            jp_btn = new JPanel();
            jp_btn.add(btn_confirm);

            setLayout(new BorderLayout());
            add(basebox, BorderLayout.CENTER);
            add(jp_btn, BorderLayout.SOUTH);

            setTitle("录入航班");
            setBounds(300, 400, 500, 325);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        public void actionPerformed(ActionEvent e) {
            String planeno = txt_no.getText().trim();
            int planetypeno = Integer.parseInt(txt_typeno.getText().trim());
            String planestart = txt_planestart.getText().trim();
            String planeend = txt_planeend.getText().trim();
            String timestart = txt_timestart.getText();
            String timereach = txt_timereach.getText();
            String timemove = txt_timemove.getText().trim();
            int ticket = Integer.parseInt(txt_ticket.getText().trim());


            int k = PlaneDao.planeInsert(planeno, planetypeno, planestart, planeend, timestart, timereach, timemove, ticket);
            if (k == 3) {
                JOptionPane.showMessageDialog(null, "路线录入成功！");
                rows_road = PlaneDao.planeinfo();

                tModel_road.setDataVector(rows_road, cols_road);
                dispose();
            }
        }
    }

    class Btn_Road_Addstation extends JFrame implements ActionListener {
        JLabel lbl_trano, lbl_stano, lbl_stainfo, lbl_timereach, lbl_timestart, lbl_mile, lbl_high, lbl_one, lbl_two;
        JTextField txt_trano, txt_stano, txt_stainfo, txt_timereach, txt_timestart, txt_mile, txt_high, txt_one, txt_two;
        JButton btn_confirm;
        JPanel jp_btn;

        Btn_Road_Addstation() {
            //航班、站点序号、站点信息、到达时间、起飞时间、里程
            lbl_trano = new JLabel("航班：");
            lbl_stano = new JLabel("站点序号：");
            lbl_stainfo = new JLabel("站点信息：");
            lbl_timereach = new JLabel("到达时间：");
            lbl_timestart = new JLabel("起飞时间：");
            lbl_mile = new JLabel("里程：");
            lbl_high = new JLabel("【价格】头等舱：");
            lbl_one = new JLabel("【价格】公务舱：");
            lbl_two = new JLabel("【价格】经济舱：");

            txt_trano = new JTextField(15);
            txt_stano = new JTextField(15);
            txt_stainfo = new JTextField(15);
            txt_timereach = new JTextField(20);
            txt_timestart = new JTextField(20);
            txt_mile = new JTextField(10);
            txt_high = new JTextField(10);
            txt_one = new JTextField(10);
            txt_two = new JTextField(10);

            btn_confirm = new JButton("确认");
            btn_confirm.addActionListener(this);

            Box basebox, box1, box2;

            box1 = Box.createVerticalBox();
            box1.add(Box.createVerticalStrut(5));
            box1.add(lbl_trano);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_stano);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_stainfo);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_timereach);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_timestart);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_mile);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_high);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_one);
            box1.add(Box.createVerticalStrut(15));
            box1.add(lbl_two);

            box2 = Box.createVerticalBox();
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_trano);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_stano);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_stainfo);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_timestart);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_timereach);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_mile);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_high);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_one);
            box2.add(Box.createVerticalStrut(8));
            box2.add(txt_two);

            basebox = Box.createHorizontalBox();
            basebox.add(Box.createHorizontalStrut(10));
            basebox.add(box1);
            basebox.add(Box.createHorizontalStrut(10));
            basebox.add(box2);
            basebox.add(Box.createHorizontalStrut(10));

            jp_btn = new JPanel();
            jp_btn.add(btn_confirm);

            setLayout(new BorderLayout());
            add(basebox, BorderLayout.CENTER);
            add(jp_btn, BorderLayout.SOUTH);

            setTitle("录入站点");
            setBounds(300, 400, 500, 350);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        public void actionPerformed(ActionEvent e) {
            String planeno = txt_trano.getText().trim();
            String stationno = txt_stano.getText().trim();
            String stationinfo = txt_stainfo.getText().trim();
            String timereach = txt_timereach.getText();
            String timestart = txt_timestart.getText();
            String stationmile = txt_mile.getText().trim();
            int high = Integer.parseInt(txt_high.getText().trim());
            int one = Integer.parseInt(txt_one.getText().trim());
            int two = Integer.parseInt(txt_two.getText().trim());

            int k = 0, k1 = 0, k2 = 0;
            k1 = StationDao.stationInsert(planeno, stationno, stationinfo, timereach, timestart, stationmile);
            k2 = PriceDao.priceInsert(stationno, high, one, two);
            k = k1 + k2;

            if (k == 2) {
                JOptionPane.showMessageDialog(null, "站点录入成功！");

                rows_station = StationDao.stationQuery();
                tModel_station.setDataVector(rows_station, cols_station);
                dispose();
            }
        }
    }

    public void Btn_Road_Revise() {
        int count = tbl_road.getSelectedRow();
        if (count == -1) {
            JOptionPane.showMessageDialog(this, "当前未选中航班", "Infomation", JOptionPane.WARNING_MESSAGE);
        } else {
            txt_road_planeno.setText(tbl_road.getValueAt(count, 0).toString());
            String type = tbl_road.getValueAt(count, 1).toString();
            txt_road_planestart.setText(tbl_road.getValueAt(count, 2).toString());
            txt_road_planeend.setText(tbl_road.getValueAt(count, 3).toString());
            txt_road_timestart.setText(tbl_road.getValueAt(count, 4).toString());
            txt_road_timereach.setText(tbl_road.getValueAt(count, 5).toString());
            txt_road_timemove.setText(tbl_road.getValueAt(count, 6).toString());

            cmb_road_planetype.setSelectedItem(type);

            txt_road_planeno.setEditable(false);
            btn_road_confirm.setEnabled(true);
        }
    }

    public void Btn_Road_Confirm() {
        String planeno = txt_road_planeno.getText().trim();
        String planetype = cmb_road_planetype.getSelectedItem().toString();
        String planestart = txt_road_planestart.getText();
        String planeend = txt_road_planeend.getText().trim();
        String timestart = txt_road_timestart.getText();
        String timereach = txt_road_timereach.getText();
        String timemove = txt_road_timemove.getText().trim();

        int k = PlaneDao.planeUpdate(planeno, planetype, planestart, planeend, timestart, timereach, timemove);
        if (k == 1) {
            JOptionPane.showMessageDialog(this, "路线更新成功！");

            cmb_road_planetype.setSelectedIndex(0);
            txt_road_planeno.setEditable(false);
            btn_road_confirm.setEnabled(true);

            rows_road = PlaneDao.planeinfo();
            tModel_road.setDataVector(rows_road, cols_road);
        }
    }

    public void Btn_Road_Delete() {
        int count = tbl_road.getSelectedRow();
        if (count == -1) {
            JOptionPane.showMessageDialog(this, "当前未选中航班", "Infomation", JOptionPane.WARNING_MESSAGE);
        } else {
            String planeno = tbl_road.getValueAt(count, 0).toString();
            int k = PlaneDao.planeDelete(planeno);
            if (k == 2) {
                JOptionPane.showMessageDialog(null, "删除成功！");

                rows_road = PlaneDao.planeinfo();
                tModel_road.setDataVector(rows_road, cols_road);
            }
        }
    }

    public void Btn_Price_Search() {
        String planeno = txt_price_planeno.getText().trim();
        String planestart = txt_price_station1.getText().trim();
        String stationinfo = txt_price_station2.getText().trim();
        if (planeno.equals("") || planestart.equals("") || stationinfo.equals("")) {
            JOptionPane.showMessageDialog(this, "航班、起点站、到站均不能为空！");
        } else {
            rows_price = StationDao.planeno_stationQuery(planeno, planestart, stationinfo);//-1

            if (rows_price == null) JOptionPane.showMessageDialog(this, "无符合条件航班！");

            else {
                tModel_price.setDataVector(rows_price, cols_price);

                ArrayList<Price> price = PriceDao.priceQuery(planeno, stationinfo);
                txt_price_high.setText(String.valueOf(price.get(0).getPricehigh()));
                txt_price_one.setText(String.valueOf(price.get(0).getPriceone()));
                txt_price_two.setText(String.valueOf(price.get(0).getPricetwo()));
            }

        }
    }

    public void Btn_Price_Revise() {
        int high = Integer.parseInt(txt_price_high.getText().trim());
        int one = Integer.parseInt(txt_price_one.getText().trim());
        int two = Integer.parseInt(txt_price_two.getText().trim());

        String planeno = txt_price_planeno.getText().trim();
        String stationinfo = txt_price_station2.getText().trim();
        String stationno = StationDao.stationnoQuery(planeno, stationinfo);

        int k = PriceDao.priceUpdate(high, one, two, stationno);
        if (k == 1) {
            JOptionPane.showMessageDialog(null, "票价修改成功！");
        }
    }

    public void Btn_Station_Add() {
        new Btn_Road_Addstation();
    }

    public void Btn_Station_Revise() {
        int count = tbl_station.getSelectedRow();
        if (count == -1) {
            JOptionPane.showMessageDialog(this, "当前未选中站点", "Infomation", JOptionPane.WARNING_MESSAGE);
        } else {
            txt_station_planeno.setText(tbl_station.getValueAt(count, 0).toString());
            txt_station_stationno.setText(tbl_station.getValueAt(count, 1).toString());
            txt_station_stationinfo.setText(tbl_station.getValueAt(count, 2).toString());
            txt_station_timereach.setText(tbl_station.getValueAt(count, 3).toString());
            txt_station_timestart.setText(tbl_station.getValueAt(count, 4).toString());
            txt_station_stationmile.setText(tbl_station.getValueAt(count, 5).toString());

            txt_station_planeno.setEditable(false);
            txt_station_stationno.setEditable(false);
            btn_station_confirm.setEnabled(true);
        }
    }

    public void Btn_Station_Confirm() {
        String planeno = txt_station_planeno.getText().trim();
        String stationno = txt_station_stationno.getText().trim();
        String stationinfo = txt_station_stationinfo.getText().trim();
        String timereach = txt_station_timereach.getText();
        String timestart = txt_station_timestart.getText();
        String stationmile = txt_station_stationmile.getText().trim();

        int k = StationDao.stationUpdate(stationno, stationinfo, timereach, timestart, stationmile);
        if (k == 1) {
            JOptionPane.showMessageDialog(this, "站点更新成功！");
            txt_station_planeno.setEditable(true);
            txt_station_stationno.setEditable(true);
            btn_station_confirm.setEnabled(false);

            rows_station = StationDao.stationQuery();
            tModel_station.setDataVector(rows_station, cols_station);
        }
    }

    public void Btn_Station_Delete() {
        int count = tbl_station.getSelectedRow();
        String stationno = tbl_station.getValueAt(count, 1).toString();
        int k1 = StationDao.stationDelete(stationno);
        int k2 = PriceDao.priceDelete(stationno);
        int k = k1 + k2;
        if (k == 2) {
            JOptionPane.showMessageDialog(null, "站点删除成功！");
        }
        rows_station = StationDao.stationQuery();
        tModel_station.setDataVector(rows_station, cols_station);
    }

    public void Btn_discount_Search() {
        now = new Date();
        nowFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lbl_discount_now.setText("当前时间：" + nowFormat.format(now));//设置当前时间

        String timestart = txt_discount_timestart.getText();//获取开始时间

        rows_discount = PlaneDao.plane_ticketQuery(timestart);//查询符合条件的车次
//        System.out.println(Arrays.deepToString(rows_discount));
        if (rows_discount == null) {
            JOptionPane.showMessageDialog(null, "无符合当前条件的航班！");
        } else {
            tModel_discount.setDataVector(rows_discount, cols_discount);//将查询结果显示在表格中
        }
    }

    public void Btn_discount_Open() {
        int confirm = JOptionPane.showConfirmDialog(this, "是否要开放折扣活动？", "Question", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String timestart = txt_discount_timestart.getText();
            boolean k = PlaneDao.plane_ticketOnsale(timestart);
            if (k) {
                JOptionPane.showMessageDialog(null, "折扣活动开放成功！");
            }
            rows_discount = PlaneDao.plane_ticketQuery(timestart);
            tModel_discount.setDataVector(rows_discount, cols_discount);
        }
    }

    public void Btn_discount_Cancel() {
        int confirm = JOptionPane.showConfirmDialog(this, "是否要停止折扣活动？", "Question", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String timestart = txt_discount_timestart.getText();
            int k = PlaneDao.plane_ticketStopsale(timestart);
            if (k == 0) {
                JOptionPane.showMessageDialog(null, "航班还未开放折扣活动！", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "已停止折扣活动！");
            }
            rows_discount = PlaneDao.plane_ticketQuery(timestart);
            tModel_discount.setDataVector(rows_discount, cols_discount);
        }
    }

    public void Btn_Order_Search() {
        //订单查询
        String usertel = txt_order_detail.getText().trim();//用户手机号
        rows_order = OrderDao.order_telQuery(usertel);//查询订单
        System.out.println(Arrays.deepToString(rows_order));//
        if (rows_order == null || rows_order.length == 0) {
            JOptionPane.showMessageDialog(this, "此用户未购买飞机票！", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            tModel_order.setDataVector(rows_order, cols_order);//显示订单

        }

    }

    public void Btn_Pwd_Confirm() {
        String pwd = String.valueOf(txt_pwd_setpwd.getPassword());
        String conpwd = String.valueOf(txt_pwd_conpwd.getPassword());
        System.out.println(pwd + " + " + conpwd);
        if (pwd.equals("") || conpwd.equals("")) {
            JOptionPane.showMessageDialog(this, "密码不能为空！", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            System.out.println(pwd + " + " + conpwd + "892");
            if (pwd.equals(conpwd)) {
                System.out.println(pwd + " + " + conpwd + "894");
                if (pwd.equals(managerpwd)) {
                    System.out.println(pwd + " + " + conpwd + "896");
                    JOptionPane.showMessageDialog(this, "新密码不能和原密码相同！", "Information", JOptionPane.WARNING_MESSAGE);
                } else {
                    System.out.println(pwd + " + " + conpwd + "899");
                    int k = ManagerDao.ManpwdUpdate(managerno, pwd);//更新管理员密码   返回值为1表示更新成功
                    if (k == 1) {
                        System.out.println(pwd + " + " + conpwd + "901");
                        JOptionPane.showMessageDialog(null, "密码更新成功！请重新登录！");
                        new CLogin("管理员登录", 0);
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
