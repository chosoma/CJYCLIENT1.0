package com.thingtek.view.frame.login;

import com.thingtek.config.AgreementConfig;
import com.thingtek.config.SystemConfig;
import com.thingtek.database.domain.UserBean;
import com.thingtek.database.service.UserService;
import com.thingtek.factory.Factorys;
import com.thingtek.view.panel.BasePanel;
import com.thingtek.view.frame.shell.Shell;

import javax.annotation.Resource;
import javax.swing.*;
import javax.swing.plaf.RootPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

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
    private SystemConfig systemConfig;

    @Resource
    private UserService userService;

    private JPanel jPanel;

    public void init() {

        setTitle("登录");

        jPanel = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                Color c1 = new Color(0xA4D7A9),
                        c2 = new Color(0xB1E6B9);
                g2.setPaint(new GradientPaint(0, 0, c1, 0, 30, c2));
                g2.fillRect(0, 0, getWidth(), 30);
                g2.setPaint(new GradientPaint(0, 30, c2, 0, (getHeight() - 44) / 2 + 30, c1));
                g2.fillRect(0, 30, getWidth(), (getHeight() - 44) / 2 + 30);
                g2.setPaint(new GradientPaint(0, (getHeight() - 44) / 2 + 30, c1, 0, getHeight() - 14, c2));
                g2.fillRect(0, (getHeight() - 44) / 2 + 30, getWidth(), getHeight() - 14);
                g2.setPaint(new GradientPaint(0, getHeight() - 14, c2, 0, getHeight(), c1));
                g2.fillRect(0, getHeight() - 14, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g2);
            }

            public boolean isOpaque() {
                return false;
            }
        };
//        jPanel.setBackground(new Color(0xDDCF22));
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(306, 230));
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        initTop();
        initCenter();
        initBottom();
        setVisible(true);

    }


    private void initTop() {
        JLabel logo = new JLabel(systemConfig.getProgrameName(), JLabel.LEFT);
        logo.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        logo.setFont(factorys.getFontFactory().getFont("logo"));
        jPanel.add(logo, BorderLayout.NORTH);
    }

    private JPanel center;
    private JPanel login;
    private JPanel loading;
    private CardLayout cardLayout;

    private void initCenter() {
        cardLayout = new CardLayout();
        center = new JPanel(cardLayout);
//        center.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
        center.setOpaque(false);
        jPanel.add(center, BorderLayout.CENTER);
        login = new JPanel(null);
        login.setOpaque(false);
        loading = new JPanel(new BorderLayout());
        loading.setOpaque(false);
        center.add(login, "login");
        center.add(loading, "load");
        initlogin();
        initloading();
    }

    private void initBottom() {
        JLabel copyright = new JLabel(systemConfig.getCopyRight(), JLabel.CENTER);
        copyright.setFont(factorys.getFontFactory().getFont("copyright"));
        jPanel.add(copyright, BorderLayout.SOUTH);
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
        jblogin.setOpaque(false);
        addLogin(jblogin, x, y + 10, lablewidth, labelheight);
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
        jbcancel.setOpaque(false);
        addLogin(jbcancel, x, y + 10, lablewidth, labelheight);
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


    /*@Resource
    private Home home;
    @Resource
    private DataPanel dataPanel;
    @Resource
    private SetPanel setPanel;*/

    public void setBases(Map<String, BasePanel> bases) {
        this.bases = bases;
    }

    private Map<String, BasePanel> bases;

    private void loading() {

        shell.init();

        int startVal = 0;
        int plus = 100 / (bases.size() != 0 ? bases.size() : 1);

        Set<Map.Entry<String, BasePanel>> entries = bases.entrySet();

        for (Map.Entry<String, BasePanel> entry : entries) {
            jpbProgress.setValue(startVal);
            shell.addItems(entry.getValue().init(), entry.getKey());
            startVal += plus;
        }


        /*jpbProgress.setValue(25);
        shell.addItems(home.init(), "主页");
        jpbProgress.setValue(50);
        shell.addItems(dataPanel.init(), "数据");
        jpbProgress.setValue(75);
        shell.addItems(setPanel.init(), "设置");*/

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

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponents(Graphics g) {

    }
}
