package com.thingtek.view.frame.shell;

import com.thingtek.config.SystemConfig;
import com.thingtek.factory.Factorys;
import com.thingtek.tools.TitleButton;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class Shell extends JFrame {

    private Shell() {

    }

    @Resource
    private Factorys factorys;

    @Resource
    private SystemConfig systemConfig;

    private JPanel titlePanel;

    private JPanel centerPanel;

    private CardLayout cardLayout;

    public void init() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(systemConfig.getProgrameName());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));

        cardLayout = new CardLayout();
        centerPanel = new JPanel();
        centerPanel.setLayout(cardLayout);
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(titlePanel, BorderLayout.NORTH);

        container.add(centerPanel, BorderLayout.CENTER);

    }


    public void addItems(Container c, final String name) {

        final TitleButton jButton = new TitleButton(name, factorys.getIconFactory().getIcon(name));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (java.awt.Component component : titlePanel.getComponents()) {
                    JButton button = (JButton) component;
                    if (e.getSource() == component) {
                        button.setSelected(true);
                    } else {
                        button.setSelected(false);
                    }
                }
                cardLayout.show(centerPanel, name);
            }
        });
        titlePanel.add(jButton);
        centerPanel.add(c, name);

    }


}
