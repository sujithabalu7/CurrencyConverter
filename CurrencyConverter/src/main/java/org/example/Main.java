package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JComboBox<String> fromCurrencyCombo;
    private JComboBox<String> toCurrencyCombo;
    private JTextField amountField;
    private JTextArea resultArea;

    public Main() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title with Lucida Console font only
        JLabel titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Lucida Console", Font.BOLD, 45));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); // smaller bottom padding
        mainPanel.add(titleLabel);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setMaximumSize(new Dimension(500, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.LINE_END;

        // From Currency Label (default font)
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setFont(fromLabel.getFont().deriveFont(Font.BOLD, 16f));
        formPanel.add(fromLabel, gbc);

        // From Currency Combo Box
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        fromCurrencyCombo = new JComboBox<>(new String[] {
                "USD - US Dollar", "EUR - Euro", "INR - Indian Rupee", "GBP - British Pound", "JPY - Japanese Yen"
        });
        fromCurrencyCombo.setPreferredSize(new Dimension(250, 30));  // Bigger size
        formPanel.add(fromCurrencyCombo, gbc);

        // To Currency Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setFont(toLabel.getFont().deriveFont(Font.BOLD, 16f));
        formPanel.add(toLabel, gbc);

        // To Currency Combo Box
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        toCurrencyCombo = new JComboBox<>(new String[] {
                "USD - US Dollar", "EUR - Euro", "INR - Indian Rupee", "GBP - British Pound", "JPY - Japanese Yen"
        });
        toCurrencyCombo.setPreferredSize(new Dimension(250, 30));  // Bigger size
        formPanel.add(toCurrencyCombo, gbc);

        // Amount Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(amountLabel.getFont().deriveFont(Font.BOLD, 16f));
        formPanel.add(amountLabel, gbc);

        // Amount TextField
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(250, 30));
        formPanel.add(amountField, gbc);

        // Convert Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 10, 15, 10);

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(convertButton.getFont().deriveFont(Font.BOLD, 18f));
        convertButton.setPreferredSize(new Dimension(250, 35));
        formPanel.add(convertButton, gbc);

        mainPanel.add(formPanel);

        // Converted Amount Label
        JLabel resultLabel = new JLabel("Converted Amount:");
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 18f));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
        mainPanel.add(resultLabel);

        // Result Area in ScrollPane
        resultArea = new JTextArea(8, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(550, 180));
        mainPanel.add(scrollPane);

        add(mainPanel);

        // Convert button action
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        try {
            String fromCurrency = (String) fromCurrencyCombo.getSelectedItem();
            String toCurrency = (String) toCurrencyCombo.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            // Dummy conversion rates for demonstration
            double rate = getConversionRate(fromCurrency, toCurrency);

            double convertedAmount = amount * rate;

            resultArea.setText(String.format("%.2f %s = %.2f %s",
                    amount, fromCurrency, convertedAmount, toCurrency));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for amount.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double getConversionRate(String from, String to) {
        // Simple hardcoded example rates relative to USD
        double usdRateFrom = 1.0, usdRateTo = 1.0;

        switch (from.split(" - ")[0]) {
            case "USD": usdRateFrom = 1.0; break;
            case "EUR": usdRateFrom = 1.1; break;
            case "INR": usdRateFrom = 0.013; break;
            case "GBP": usdRateFrom = 1.3; break;
            case "JPY": usdRateFrom = 0.007; break;
        }

        switch (to.split(" - ")[0]) {
            case "USD": usdRateTo = 1.0; break;
            case "EUR": usdRateTo = 1.1; break;
            case "INR": usdRateTo = 0.013; break;
            case "GBP": usdRateTo = 1.3; break;
            case "JPY": usdRateTo = 0.007; break;
        }

        return usdRateTo / usdRateFrom;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
