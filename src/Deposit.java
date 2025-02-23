import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

//comment my code
public class Deposit implements ActionListener{
    private final JFrame DepositPage = new JFrame("Deposit Money");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Balance = new JLabel("Your Balance");
    private final JTextField BalanceTextField ;
    private final JLabel Amount = new JLabel("Enter Amount to Deposit");
    private final JTextField AmountTextField = new JTextField() ;
    private final JButton Proceed = new JButton("Deposit");
    private final JLabel Message = new JLabel("Deposit Successful");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));
    private final String AccountNo;
    Deposit(String balance,String Acc){
        BalanceTextField = new JTextField(balance);
        AccountNo = Acc;
    }
    public void design(){

        // Adding AnD setting frame
        DepositPage.setSize(500,500);
        DepositPage.getContentPane().setBackground(new Color(238, 182, 146, 255));
        DepositPage.setLayout(null);
        DepositPage.setResizable(false);
        DepositPage.setLocationRelativeTo(null);
        DepositPage.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Deposit.png").getImage());
        DepositPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DepositPage.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        DepositPage.add(bankLogo);


        //BAlance Amount  Label And TextField
        Balance.setBounds(100,120,150,30);
        Balance.setFont(new Font("Serif", Font.BOLD, 16));
        DepositPage.add(Balance);
        BalanceTextField.setBounds(100,150,300,30);
        BalanceTextField.setEditable(false);
        DepositPage.add(BalanceTextField);

        // Amount to withdraw  Label And TextField
        Amount.setBounds(100,200,200,30);
        Amount.setFont(new Font("Serif", Font.BOLD, 16));
        DepositPage.add(Amount);
        AmountTextField.setBounds(100,230,300,30);
        DepositPage.add(AmountTextField);

        // Withdraw amount Button
        Proceed.setBounds(100,300,300,50);
        Proceed.setForeground(new Color(0, 0, 0));
        Proceed.setBackground(new Color(190, 188, 188));
        Proceed.setBorder(BorderFactory.createRaisedBevelBorder());
        Proceed.setFont(new Font("Serif", Font.BOLD, 16));
        Proceed.addActionListener(this);
        DepositPage.add(Proceed);

        // Message
        Message.setBounds(150,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        DepositPage.add(Message);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        DepositPage.add(Back);
        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        DepositPage.add(Home);
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Back){
            DepositPage.setVisible(false);
            DepositLogin obj = new DepositLogin();
            obj.design();
            return;
        }
        if(Choice.getSource() == Home){
            DepositPage.setVisible(false);
            LoginPage obj = new LoginPage();
            obj.design();
            return;
        }
        String DepositAmount = AmountTextField.getText();
        String ActualAmount = BalanceTextField.getText();
        String TotalAmount = String.valueOf(Float.parseFloat(DepositAmount) + Float.parseFloat(ActualAmount));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE Savings SET Cash = ? WHERE AccountNumber = ? ");
            preparedStatement.setFloat(1,Float.parseFloat(TotalAmount));
            preparedStatement.setInt(2,Integer.parseInt(AccountNo));
            preparedStatement.execute();
            BalanceTextField.setText(TotalAmount);
            Message.setText("Deposit Successful!!!");
            AmountTextField.setText("");
            Message.setVisible(true);
        }
        catch (Exception e){
            Message.setText("Database Connection Error!!!");
            Message.setVisible(true);
        }




    }

    public static void main(String[] args) {
        Deposit obj = new Deposit("1234","123");
        obj.design();
    }
}

