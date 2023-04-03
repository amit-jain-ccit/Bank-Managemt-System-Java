import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ChooseAccountType implements ActionListener{
    private final JFrame AccountType = new JFrame("Choose Account Type");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JButton SavingAccount = new JButton("Savings Account");
    private final JButton CurrentAccount = new JButton("Current Account");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));


    public void design(){


        // Adding AnD setting frame
        AccountType.setSize(500,500);
        AccountType.getContentPane().setBackground(new Color(238, 182, 146, 255));
        AccountType.setLayout(null);
        AccountType.setResizable(false);
        AccountType.setLocationRelativeTo(null);
        AccountType.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\choose.png").getImage());
        AccountType.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AccountType.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        AccountType.add(bankLogo);
        // Create account
        SavingAccount.setBounds(100,210,300,50);
        SavingAccount.setForeground(new Color(0, 0, 0));
        SavingAccount.setBackground(new Color(190, 188, 188));
        SavingAccount.setBorder(BorderFactory.createRaisedBevelBorder());
        SavingAccount.setFont(new Font("Serif", Font.BOLD, 16));
        SavingAccount.addActionListener(this);
        AccountType.add(SavingAccount);

        // Delete account
        CurrentAccount.setBounds(100,270,300,50);
        CurrentAccount.setForeground(new Color(0, 0, 0));
        CurrentAccount.setBackground(new Color(190, 188, 188));
        CurrentAccount.setBorder(BorderFactory.createRaisedBevelBorder());
        CurrentAccount.setFont(new Font("Serif", Font.BOLD, 16));
        CurrentAccount.addActionListener(this);
        AccountType.add(CurrentAccount);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        AccountType.add(Back);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        AccountType.add(Home);


    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        AccountType.setVisible(false);
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            return;
        }
        if(Choice.getSource() == Back){
            ChooseAction obj = new ChooseAction();
            obj.design();
            return;
        }
        if(Choice.getSource() == SavingAccount){
            CreateAccount obj = new CreateAccount(false);
            obj.design();
        }
        else{
            CreateAccount obj = new CreateAccount(true);
            obj.design();
        }
    }

    public static void main(String[] args) {
        ChooseAccountType obj = new ChooseAccountType();
        obj.design();
    }
}
