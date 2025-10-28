import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PizzaGUIFrame extends JFrame {
    JPanel mainPanel;

    JPanel optionPanel;
    GroupLayout groupLayout;

    TitledBorder crustBorder;
    JPanel crustPanel;
    JRadioButton thinBtn;
    JRadioButton regBtn;
    JRadioButton deepDishBtn;
    ButtonGroup radioButtonGroup;

    TitledBorder sizesBorder;
    JPanel sizesPanel;
    JComboBox<String> sizeCombo;

    TitledBorder toppingBorder;
    JPanel toppingPanel;
    JCheckBox cheeseCheckBox;
    JCheckBox fingersCheckBox;
    JCheckBox pineappleCheckBox;
    JCheckBox toesCheckBox;
    JCheckBox snailsCheckBox;
    JCheckBox wormsCheckBox;

    JTextField receiptField;
    JScrollPane scrollPane;

    JPanel buttonPanel;
    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;

    public PizzaGUIFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createOptionPnl();
        mainPanel.add(optionPanel, BorderLayout.NORTH);

        add(mainPanel);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createOptionPnl() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(1, 2));

//        createCrustPnl();
//        createSizesPnl();
//        createToppingPnl();

        groupLayout = new GroupLayout(optionPanel);
        optionPanel.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup()
                        .addComponent(crustPanel)
                        .addComponent(sizesPanel)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(toppingPanel)));

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup().
                        addGroup(groupLayout.createSequentialGroup()
                                .addComponent(crustPanel)
                                .addComponent(sizesPanel))
                        .addComponent(toppingPanel)
        );
    }

    private void createCrustPanel() {
        crustPanel = new JPanel();
        crustBorder = BorderFactory.createTitledBorder("Choose your crust");
        crustPanel.setBorder(crustBorder);

        thinBtn = new JRadioButton("Thin");
        regBtn = new JRadioButton("Regular");
        deepDishBtn = new JRadioButton("Deep Dish");

        radioButtonGroup = new ButtonGroup();
    }
}
