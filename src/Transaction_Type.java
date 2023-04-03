import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Transaction_Type implements ActionListener{
    private final JFrame TransactionType = new JFrame("Choose Transaction Type");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JButton Withdraw = new JButton("Withdraw");
    private final JButton Deposit = new JButton("Deposit");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));


    public void design(){


        // Adding AnD setting frame
        TransactionType.setSize(500,500);
        TransactionType.getContentPane().setBackground(new Color(238, 182, 146, 255));
        TransactionType.setLayout(null);
        TransactionType.setResizable(false);
        TransactionType.setLocationRelativeTo(null);
        TransactionType.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\choose.png").getImage());
        TransactionType.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        TransactionType.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        TransactionType.add(bankLogo);
        // Create account
        Withdraw.setBounds(100,210,300,50);
        Withdraw.setForeground(new Color(0, 0, 0));
        Withdraw.setBackground(new Color(190, 188, 188));
        Withdraw.setBorder(BorderFactory.createRaisedBevelBorder());
        Withdraw.setFont(new Font("Serif", Font.BOLD, 16));
        Withdraw.addActionListener(this);
        TransactionType.add(Withdraw);

        // Delete account
        Deposit.setBounds(100,270,300,50);
        Deposit.setForeground(new Color(0, 0, 0));
        Deposit.setBackground(new Color(190, 188, 188));
        Deposit.setBorder(BorderFactory.createRaisedBevelBorder());
        Deposit.setFont(new Font("Serif", Font.BOLD, 16));
        Deposit.addActionListener(this);
        TransactionType.add(Deposit);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        TransactionType.add(Back);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        TransactionType.add(Home);

    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Back){
            TransactionType.setVisible(false);
            ChooseAction obj = new ChooseAction();
            obj.design();
            return;
        }
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            }
        else if(Choice.getSource() == Withdraw){
            new WithdrawLogin().design();
        }
        else{
            new DepositLogin().design();
        }
        TransactionType.setVisible(false);
    }

    public static void main(String[] args) {
        Transaction_Type obj = new Transaction_Type();
        obj.design();
    }
}
