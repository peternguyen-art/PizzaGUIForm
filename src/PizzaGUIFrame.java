import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

public class PizzaGUIFrame extends JFrame {
    JPanel mainPanel;

    JPanel titlePanel;
    JLabel titleLbl;
    JPanel optionPanel;
    GroupLayout groupLayout;

    JPanel centerPanel;

    TitledBorder crustBorder;
    JPanel crustPanel;
    JRadioButton thinBtn;
    JRadioButton regBtn;
    JRadioButton deepDishBtn;
    ButtonGroup radioButtonGroup;

    TitledBorder sizesBorder;
    JPanel sizesPanel;
    String[] sizes = {};
    JComboBox<String> sizeCombo;

    TitledBorder toppingBorder;
    JPanel toppingPanel;
    JCheckBox cheeseCheckBox;
    JCheckBox fingersCheckBox;
    JCheckBox pineappleCheckBox;
    JCheckBox toesCheckBox;
    JCheckBox snailsCheckBox;
    JCheckBox wormsCheckBox;

    JPanel receiptPanel;
    JTextArea receiptArea;
    JScrollPane scrollPane;

    JPanel buttonPanel;
    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;

    double basePrice;
    double toppingPrice;
    double subTotalPrice;
    double tax;
    double totalPrice;

    public PizzaGUIFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        createCenterPnl();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(700, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createTitlePanel()
    {
        titlePanel = new JPanel();
        titleLbl = new JLabel("Peter's Pizzeria Order Form");
        titleLbl.setFont(new Font("", Font.BOLD,18));
        titlePanel.add(titleLbl);
    }

    private void createCenterPnl()
    {
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,1));

        createOptionPnl();
        centerPanel.add(optionPanel);

        createReceiptPnl();
        centerPanel.add(receiptPanel);
    }

    private void createOptionPnl() {
        optionPanel = new JPanel();

        createCrustPnl();
        createSizesPnl();
        createToppingPnl();

        groupLayout = new GroupLayout(optionPanel);
        optionPanel.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(crustPanel)
                                .addComponent(sizesPanel))
                        .addComponent(toppingPanel)

        );

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(crustPanel)
                                .addComponent(sizesPanel))
                        .addComponent(toppingPanel)
        );
    }

    private void createCrustPnl() {
        crustPanel = new JPanel();
        crustPanel.setLayout(new GridLayout(3,1 ));
        crustBorder = BorderFactory.createTitledBorder("Choose your crust");
        crustBorder.setTitleFont(new Font("", Font.BOLD, 14));
        crustPanel.setBorder(crustBorder);

        thinBtn = new JRadioButton("Thin");
        regBtn = new JRadioButton("Regular");
        deepDishBtn = new JRadioButton("Deep Dish");

        radioButtonGroup = new ButtonGroup();

        crustPanel.add(thinBtn);
        crustPanel.add(regBtn);
        crustPanel.add(deepDishBtn);
    }

    private void createSizesPnl() {
        sizesPanel = new JPanel();
        sizesBorder = BorderFactory.createTitledBorder("Choose your size");
        sizesBorder.setTitleFont(new Font("", Font.BOLD, 14));
        sizesPanel.setBorder(sizesBorder);

        sizeCombo = new JComboBox<>(sizes);
        sizeCombo.addItem("Small");
        sizeCombo.addItem("Medium");
        sizeCombo.addItem("Large");
        sizeCombo.addItem("Super");

        sizeCombo.setSelectedIndex(0);
        sizeCombo.setEditable(false);

        sizesPanel.add(sizeCombo);
    }

    private void createToppingPnl() {
        toppingPanel = new JPanel();
        toppingPanel.setLayout(new GridLayout(6,1));
        toppingBorder = BorderFactory.createTitledBorder("Choose your topping: 1$ each");
        toppingBorder.setTitleFont(new Font("", Font.BOLD, 14));
        toppingPanel.setBorder(toppingBorder);

        cheeseCheckBox = new JCheckBox("Cheese");
        fingersCheckBox = new JCheckBox("Fingers");
        pineappleCheckBox = new JCheckBox("Pineapple");
        toesCheckBox = new JCheckBox("Toes");
        snailsCheckBox = new JCheckBox("Snails");
        wormsCheckBox = new JCheckBox("Worms");

        toppingPanel.add(cheeseCheckBox);
        toppingPanel.add(fingersCheckBox);
        toppingPanel.add(pineappleCheckBox);
        toppingPanel.add(toesCheckBox);
        toppingPanel.add(snailsCheckBox);
        toppingPanel.add(wormsCheckBox);
    }

    private void createReceiptPnl() {
        receiptPanel = new JPanel();

        receiptArea = new JTextArea(20,40);
        scrollPane = new JScrollPane(receiptArea);
        receiptArea.setEditable(false);

        receiptPanel.add(scrollPane);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3,20,5));

        EmptyBorder padding = new EmptyBorder(0,20,10,20);
        buttonPanel.setBorder(padding);
        orderBtn = new JButton("Order");
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");

        ActionListener orderListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String chosenCrust = "";

                if (thinBtn.isSelected()) {
                    chosenCrust = "Thin";
                } else if (regBtn.isSelected()) {
                    chosenCrust = "Regular";
                }  else if (deepDishBtn.isSelected()) {
                    chosenCrust = "Deep Dish";
                } else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Please choose either Thin or Regular, Deep Dish");
                    return;
                }

                String chosenSize = Objects.requireNonNull(sizeCombo.getSelectedItem()).toString();
                if (chosenSize.equals("Small")) {
                    basePrice = 8.00;
                }else if (chosenSize.equals("Medium")) {
                    basePrice = 12.00;
                }else if (chosenSize.equals("Large")) {
                    basePrice = 16.00;
                }else if (chosenSize.equals("Super")) {
                    basePrice = 20.00;
                }

                ArrayList<String> chosenToppings = new ArrayList<>();
                if (cheeseCheckBox.isSelected()) {
                    chosenToppings.add("Cheese");
                    toppingPrice += 1.00;
                }
                if  (fingersCheckBox.isSelected()) {
                    chosenToppings.add("Fingers");
                    toppingPrice += 1.00;
                }
                if (pineappleCheckBox.isSelected()) {
                    chosenToppings.add("Pineapple");
                    toppingPrice += 1.00;
                }
                if (toesCheckBox.isSelected()) {
                    chosenToppings.add("Toes");
                    toppingPrice += 1.00;
                }
                if (snailsCheckBox.isSelected()) {
                    chosenToppings.add("Snails");
                    toppingPrice += 1.00;
                }
                if (wormsCheckBox.isSelected()) {
                    chosenToppings.add("Worms");
                    toppingPrice += 1.00;
                }

                subTotalPrice = basePrice + toppingPrice;
                tax = 0.07;
                totalPrice = subTotalPrice - (subTotalPrice*tax);

                receiptArea.setText("");

                StringBuilder toppingsDisplay = new StringBuilder();
                for (String topping : chosenToppings){
                    toppingsDisplay.append("  + ").append(topping).append("\t\t\t\t" + toppingPrice + "\n");
                }

                receiptArea.append(
                        "=======================================================\n" +
                                "\t\t🍕 PIZZA RECEIPT 🍕\t\t\n" +
                                "=======================================================\n" +
                                "\n" +
                                chosenCrust + " (" + chosenSize + ")\t\t\t" + basePrice + "\n" +
                                "EXTRA TOPPINGS:\n" +
                                toppingsDisplay +
                                "\n" +
                                "---------------------------------------------------------------------------------------------------\n" +
                                 "Sub-total:\t\t\t\t" + subTotalPrice + "\n" +
                                 "Tax:\t\t\t\t" + tax + "\n" +
                                "---------------------------------------------------------------------------------------------------\n" +
                                "TOTAL DUE:\t\t\t\t" + totalPrice + "\n" +
                                "=======================================================\n"
                );
            }
        };

        ActionListener clearListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        };

        ActionListener exitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int formExit = JOptionPane.showConfirmDialog
                        (null,
                                "Do you want to exit the form",
                                "Exit Form",
                                JOptionPane.YES_NO_OPTION);
                if (formExit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        };

        clearBtn.addActionListener(clearListener);
        orderBtn.addActionListener(orderListener);
        quitBtn.addActionListener(exitListener);

        buttonPanel.add(orderBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(quitBtn);
    }

    public void clearForm(){
        thinBtn.setSelected(false);
        regBtn.setSelected(false);
        deepDishBtn.setSelected(false);

        cheeseCheckBox.setSelected(false);
        fingersCheckBox.setSelected(false);
        pineappleCheckBox.setSelected(false);
        toesCheckBox.setSelected(false);
        snailsCheckBox.setSelected(false);
        wormsCheckBox.setSelected(false);

        sizeCombo.setSelectedItem(null);

        receiptArea.setText("");
    }
}
