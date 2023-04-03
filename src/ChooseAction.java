import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ChooseAction implements ActionListener{
    private final JFrame ChooseAction = new JFrame("Choose Option");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JButton CreateAcc = new JButton("Create Account");
    private final JButton DeleteAcc = new JButton("Delete Account");
    private final JButton ShowInfo = new JButton("Display Information");
    private final JButton UpdateAcc = new JButton("Update Details");
    private final JButton Transactions = new JButton("Transaction");
    private final JButton Exit = new JButton("Exit");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));

    public void design(){

        // Adding AnD setting frame
        ChooseAction.setSize(500,550);
        ChooseAction.getContentPane().setBackground(new Color(238, 182, 146, 255));
        ChooseAction.setLayout(null);
        ChooseAction.setResizable(false);
        ChooseAction.setLocationRelativeTo(null);
        ChooseAction.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\choose.png").getImage());
        ChooseAction.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ChooseAction.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        ChooseAction.add(bankLogo);
        // Create account
        CreateAcc.setBounds(100,110,300,50);
        CreateAcc.setForeground(new Color(0, 0, 0));
        CreateAcc.setBackground(new Color(190, 188, 188));
        CreateAcc.setBorder(BorderFactory.createRaisedBevelBorder());
        CreateAcc.setFont(new Font("Serif", Font.BOLD, 16));
        CreateAcc.addActionListener(this);
        ChooseAction.add(CreateAcc);

        // Delete account
        DeleteAcc.setBounds(100,170,300,50);
        DeleteAcc.setForeground(new Color(0, 0, 0));
        DeleteAcc.setBackground(new Color(190, 188, 188));
        DeleteAcc.setBorder(BorderFactory.createRaisedBevelBorder());
        DeleteAcc.setFont(new Font("Serif", Font.BOLD, 16));
        DeleteAcc.addActionListener(this);
        ChooseAction.add(DeleteAcc);

        // Show Infromation
        ShowInfo.setBounds(100,230,300,50);
        ShowInfo.setForeground(new Color(0, 0, 0));
        ShowInfo.setBackground(new Color(190, 188, 188));
        ShowInfo.setBorder(BorderFactory.createRaisedBevelBorder());
        ShowInfo.addActionListener(this);
        ShowInfo.setFont(new Font("Serif", Font.BOLD, 16));
        ChooseAction.add(ShowInfo);

        // Update account
        UpdateAcc.setBounds(100,290,300,50);
        UpdateAcc.setForeground(new Color(0, 0, 0));
        UpdateAcc.setBackground(new Color(190, 188, 188));
        UpdateAcc.setBorder(BorderFactory.createRaisedBevelBorder());
        UpdateAcc.addActionListener(this);
        UpdateAcc.setFont(new Font("Serif", Font.BOLD, 16));
        ChooseAction.add(UpdateAcc);

        // Transaction
        Transactions.setBounds(100,350,300,50);
        Transactions.setForeground(new Color(0, 0, 0));
        Transactions.setBackground(new Color(190, 188, 188));
        Transactions.setBorder(BorderFactory.createRaisedBevelBorder());
        Transactions.addActionListener(this);
        Transactions.setFont(new Font("Serif", Font.BOLD, 16));
        ChooseAction.add(Transactions);

        //BackButton
        Back.setBounds(0,450,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        ChooseAction.add(Back);

        //HomeButton
        Home.setBounds(430,450,50,50);
        Home.addActionListener(this);
        ChooseAction.add(Home);


        // Exit
        Exit.setBounds(100,410,300,50);
        Exit.setForeground(new Color(0, 0, 0));
        Exit.setBackground(new Color(190, 188, 188));
        Exit.setBorder(BorderFactory.createRaisedBevelBorder());
        Exit.addActionListener(this);
        Exit.setFont(new Font("Serif", Font.BOLD, 16));
        ChooseAction.add(Exit);

    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        ChooseAction.setVisible(false);
        if(Choice.getSource() == Back){
            LoginPage obj = new LoginPage();
            obj.design();
            return;
        }
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
        }
        else if(Choice.getSource() == CreateAcc){
            ChooseAccountType obj = new ChooseAccountType();
            obj.design();
        }
        else if (Choice.getSource() == DeleteAcc) {
            DeleteAccount obj = new DeleteAccount();
            obj.design();
        }
        else if (Choice.getSource() == UpdateAcc){
            UpdateInformation_Login obj = new UpdateInformation_Login();
            obj.design();
        }
        else if (Choice.getSource() == Transactions) {
            new Transaction_Type().design();
        }
        else if (Choice.getSource() == ShowInfo) {
            DisplayInformation_Login obj = new DisplayInformation_Login();
            obj.design();
        }
        else if(Choice.getSource() == Exit){
            System.exit(0);
        }


    }

    public static void main(String[] args) {
        ChooseAction obj = new ChooseAction();
        obj.design();
    }
}
