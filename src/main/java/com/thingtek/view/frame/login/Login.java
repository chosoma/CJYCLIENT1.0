package com.thingtek.view.frame.login;

import com.thingtek.config.AgreementConfig;
import com.thingtek.database.domain.UserBean;
import com.thingtek.database.service.UserService;
import com.thingtek.factory.Factorys;
import com.thingtek.view.panel.data.DataPanel;
import com.thingtek.view.panel.home.Home;
import com.thingtek.view.panel.set.SetPanel;
import com.thingtek.view.frame.shell.Shell;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@org.springframework.stereotype.Component
public class Login extends JFrame {

    private JTextField jtfusername;

    private JPasswordField jtfpassword;

    @Resource
    private Factorys factorys;
    @Resource
    private Shell shell;
    @Resource
    private AgreementConfig agreementConfig;
    @Resource
    private UserService userService;

    private JPanel jPanel;

    public void init() {

        setTitle("登录");
        jPanel = new JPanel(new BorderLayout());
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(306, 200));
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        initTitle();
        initCenter();

        setVisible(true);

    }


    private void initTitle() {
        JPanel logo = new JPanel();
        jPanel.add(logo, BorderLayout.NORTH);
    }

    private JPanel center;
    private JPanel login;
    private JPanel loading;
    private CardLayout cardLayout;

    private void initCenter() {
        cardLayout = new CardLayout();
        center = new JPanel(cardLayout);
        jPanel.add(center, BorderLayout.CENTER);
        login = new JPanel(null);
        loading = new JPanel(new BorderLayout());
        center.add(login, "login");
        center.add(loading, "load");
        initlogin();
        initloading();
    }

    private JProgressBar jpbProgress;

    private void initloading() {
        jpbProgress = new JProgressBar(0, 100);
        jpbProgress.setStringPainted(true);// 显示百分比字符
        jpbProgress.setIndeterminate(false); // 不确定的进度条
        jpbProgress.setBorderPainted(false);// 取消边框
        jpbProgress.setOpaque(false);// 设置透明背景
        loading.add(jpbProgress, BorderLayout.SOUTH);
    }

    private void initlogin() {

        int x = 50, y = 20, yheight = 40, lablewidth = 100, textwidth = 120, labelheight = 30, textheight = 20;
        JLabel jlusername = new JLabel("用户名:", JLabel.RIGHT);
        addLogin(jlusername, x - 20, y, lablewidth, labelheight);
        y += yheight;

        JLabel jlpassword = new JLabel("密　码:", JLabel.RIGHT);
        addLogin(jlpassword, x - 20, y, lablewidth, labelheight);
        y += yheight;

        JButton jblogin = new JButton("登录");
        addLogin(jblogin, x, y, lablewidth, labelheight);
        jblogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserBean userBean = getUser();
                if (userBean == null) {
                    JOptionPane.showMessageDialog(Login.this, "用户名或密码不能为空!", "错误!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                userBean = userService.checkUser(userBean);
                if (userBean == null) {
                    JOptionPane.showMessageDialog(Login.this, "密码错误或用户名不存在!", "登录失败!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cardLayout.show(center, "load");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        loading();
                    }
                }).start();
            }
        });

        x += 110;
        y = 20;

        jtfusername = new JTextField();
        addLogin(jtfusername, x - 20, y + 5, textwidth, textheight);
        y += yheight;

        jtfpassword = new JPasswordField();
        addLogin(jtfpassword, x - 20, y + 5, textwidth, textheight);
        y += yheight;

        JButton jbcancel = new JButton("取消");
        addLogin(jbcancel, x, y, lablewidth, labelheight);
        jbcancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    private void addLogin(Component component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        component.setFont(factorys.getFontFactory().getFont("login"));
        login.add(component);
    }


    @Resource
    private Home home;
    @Resource
    private DataPanel dataPanel;
    @Resource
    private SetPanel setPanel;

    private void loading() {

        shell.init();

        jpbProgress.setValue(25);
        shell.addItems(home.init(), "主页");

        jpbProgress.setValue(50);
        shell.addItems(dataPanel.init(), "数据");

        jpbProgress.setValue(75);
        shell.addItems(setPanel.init(), "设置");


        
        setVisible(false);
        shell.setVisible(true);
    }

    private UserBean getUser() {
        String username = jtfusername.getText();
        String password = new String(jtfpassword.getPassword());
        if (stringIsNUll(username) && stringIsNUll(password)) {
            return null;
        }
        UserBean userBean = new UserBean();
        userBean.setUsername(jtfusername.getText());
        userBean.setPassword(new String(jtfpassword.getPassword()));
        return userBean;
    }

    private boolean stringIsNUll(String str) {
        return str != null && str.equals("");
    }

}
