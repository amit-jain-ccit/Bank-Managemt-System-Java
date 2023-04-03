import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Withdraw implements ActionListener{
    private final JFrame Withdraw = new JFrame("Withdraw Your Money");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Balance = new JLabel("Your Balance");
    private final JTextField BalanceTextField ;
    private final JLabel Amount = new JLabel("Enter Amount to Withdraw");
    private final JTextField AmountTextField = new JTextField() ;
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));

    private final JButton Proceed = new JButton("Withdraw");
    private final JLabel Message = new JLabel("Withdraw Successful");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));

    private  final String AccountNo;
    Withdraw(String balance, String Acc){
        BalanceTextField = new JTextField(balance);
        AccountNo = Acc;
    }
    public void design(){

        // Adding AnD setting frame
        Withdraw.setSize(500,500);
        Withdraw.getContentPane().setBackground(new Color(238, 182, 146, 255));
        Withdraw.setLayout(null);
        Withdraw.setResizable(false);
        Withdraw.setLocationRelativeTo(null);
        Withdraw.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\withdraw.png").getImage());
        Withdraw.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Withdraw.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        Withdraw.add(bankLogo);


        //BAlance Amount  Label And TextField
        Balance.setBounds(100,120,150,30);
        Balance.setFont(new Font("Serif", Font.BOLD, 16));
        Withdraw.add(Balance);
        BalanceTextField.setBounds(100,150,300,30);
        BalanceTextField.setEditable(false);
        Withdraw.add(BalanceTextField);

        // Amount to withdraw  Label And TextField
        Amount.setBounds(100,200,200,30);
        Amount.setFont(new Font("Serif", Font.BOLD, 16));
        Withdraw.add(Amount);
        AmountTextField.setBounds(100,230,300,30);
        Withdraw.add(AmountTextField);

        // Withdraw amount Button
        Proceed.setBounds(100,300,300,50);
        Proceed.setForeground(new Color(0, 0, 0));
        Proceed.setBackground(new Color(190, 188, 188));
        Proceed.setBorder(BorderFactory.createRaisedBevelBorder());
        Proceed.setFont(new Font("Serif", Font.BOLD, 16));
        Proceed.addActionListener(this);
        Withdraw.add(Proceed);

        // Message
        Message.setBounds(150,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        Withdraw.add(Message);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        Withdraw.add(Back);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        Withdraw.add(Home);
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            Withdraw.setVisible(false);
            return;
        }
        if(Choice.getSource() == Back){
            Withdraw.setVisible(false);
            WithdrawLogin obj = new WithdrawLogin();
            obj.design();
            return;
        }
       String WithdrawAmount = AmountTextField.getText();
       String ActualAmount = BalanceTextField.getText();
       if(Float.parseFloat(WithdrawAmount) > Float.parseFloat(ActualAmount)){
           Message.setText("Insufficient Balance!!!");
           Message.setVisible(true);
       }
       else{
           String remainingBalance = String.valueOf(Float.parseFloat(ActualAmount) - Float.parseFloat(WithdrawAmount));
           try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
               PreparedStatement preparedStatement = con.prepareStatement("UPDATE Savings SET Cash = ? WHERE AccountNumber = ? ");
               preparedStatement.setFloat(1,Float.parseFloat(remainingBalance));
               preparedStatement.setInt(2,Integer.parseInt(AccountNo));
               preparedStatement.execute();
               BalanceTextField.setText(remainingBalance);
               Message.setVisible(true);
               AmountTextField.setText("");
           }
           catch (Exception e){
               Message.setText("Database Connection Error!!!");
               Message.setVisible(true);
           }

       }



    }

    public static void main(String[] args) {
        Withdraw obj = new Withdraw("1234","123");
        obj.design();
    }
}

