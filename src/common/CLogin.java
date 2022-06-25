package common;

import dao.ManagerDao;
import dao.UserDao;
import view.ManagerFrm;
import view.UserFrm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CLogin extends JFrame implements ActionListener {
    JLabel lbl_account, lbl_pwd;
    JTextField txt_account;
    JPasswordField txt_pwd;
    JButton btn_login, btn_cancel;
    JPanel p_btn, p_all;
    public static String accountno;
    public static String accountpwd;
    int type=0;//0为管理员，1为普通用户

//        public static void main(String args[]) {
//            JFrame.setDefaultLookAndFeelDecorated(true);
//            new CLogin("管理员登录",0);
//        }

    public CLogin(String account,int type) {
            this.type=type;

        lbl_account = new JLabel("账号：");
        lbl_pwd = new JLabel("密码：");
        txt_account = new JTextField(15);
        txt_pwd = new JPasswordField(15);
        btn_login = new JButton("登录");
        btn_cancel = new JButton("取消");
        p_btn = new JPanel();
        p_all = new JPanel();

        //布局
        Box basebox, box1, box2;
        box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(18));
        box1.add(lbl_account);
        box1.add(Box.createVerticalStrut(35));
        box1.add(lbl_pwd);
        box1.add(Box.createVerticalStrut(15));
        box2 = Box.createVerticalBox();
        box2.add(Box.createVerticalStrut(13));
        box2.add(txt_account);
        box2.add(Box.createVerticalStrut(15));
        box2.add(txt_pwd);
        basebox = Box.createHorizontalBox();
        basebox.add(Box.createHorizontalStrut(10));
        basebox.add(box1);
        basebox.add(Box.createHorizontalStrut(30));
        basebox.add(box2);
        basebox.add(Box.createHorizontalStrut(10));

        p_btn.setLayout(new GridLayout(1, 2));
        p_btn.add(btn_login);
        p_btn.add(btn_cancel);

        p_all.setLayout(new BorderLayout());
        add(basebox, BorderLayout.NORTH);
        add(p_btn, BorderLayout.SOUTH);

        setTitle(account);
        setBounds(100, 100, 300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //个性化设置
        Font f = new Font("楷体", Font.BOLD, 18);
        lbl_account.setFont(f);
        lbl_pwd.setFont(f);

        btn_login.setFont(f);
        btn_login.setBorderPainted(false);
        btn_login.setBackground(Color.white);
        btn_cancel.setFont(f);
        btn_cancel.setBorderPainted(false);
        btn_cancel.setBackground(Color.white);

        //操作
        btn_login.addActionListener(this);
        btn_cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_login) {
            Btn_Login();
        } else if (e.getSource() == btn_cancel) {
            Btn_Cancel();
        }
    }

    private void Btn_Login() {
        accountno = txt_account.getText();
        accountpwd = String.valueOf(txt_pwd.getPassword());

        int flag = 0;
        if(this.type==0){
            flag = ManagerDao.managerValidate(accountno, accountpwd);
        }else{
            flag = UserDao.userValidate(accountno, accountpwd);
        }
//        int flag = ManagerDao.accountValidate(accountno, accountpwd);
        if (flag == 1&&this.type==0) {//登录成功
            JOptionPane.showMessageDialog(this, "欢迎登录！");
            new ManagerFrm();
            dispose();
        }else if(flag==1&&this.type==1) {
            JOptionPane.showMessageDialog(this, "欢迎登录！");
            new UserFrm();
            dispose();
        }else if (flag == 2) {
            txt_account.setText(null);
            txt_pwd.setText(null);
            JOptionPane.showMessageDialog(this, "密码错误！", "warning", JOptionPane.WARNING_MESSAGE);
        } else if (flag == -1) {
            JOptionPane.showMessageDialog(this, "账号不存在！", "warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void Btn_Cancel() {
        txt_account.setText(null);
        txt_pwd.setText(null);
    }
}

