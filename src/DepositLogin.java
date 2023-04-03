import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DepositLogin implements ActionListener{
    private final JFrame DepositLogin = new JFrame("Deposit Money");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Account_Number = new JLabel("Account Number");
    private final JTextField Account_NumberTextField = new JTextField();
    private final JButton Show = new JButton("Submit");
    private final JLabel Message = new JLabel("Invalid Account Number!!!");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));

    public void design(){

        // Adding AnD setting frame
        DepositLogin.setSize(500,500);
        DepositLogin.getContentPane().setBackground(new Color(238, 182, 146, 255));
        DepositLogin.setLayout(null);
        DepositLogin.setResizable(false);
        DepositLogin.setLocationRelativeTo(null);
        DepositLogin.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\LoginIcon.png").getImage());
        DepositLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DepositLogin.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        DepositLogin.add(bankLogo);


        //Account Number Label And TextField
        Account_Number.setBounds(100,200,150,30);
        Account_Number.setFont(new Font("Serif", Font.BOLD, 16));
        DepositLogin.add(Account_Number);
        Account_NumberTextField.setBounds(100,230,300,30);
        DepositLogin.add(Account_NumberTextField);

        // Delete account Button
        Show.setBounds(100,300,300,50);
        Show.setForeground(new Color(0, 0, 0));
        Show.setBackground(new Color(190, 188, 188));
        Show.setBorder(BorderFactory.createRaisedBevelBorder());
        Show.setFont(new Font("Serif", Font.BOLD, 16));
        Show.addActionListener(this);
        DepositLogin.add(Show);

        // Message
        Message.setBounds(150,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        DepositLogin.add(Message);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        DepositLogin.add(Back);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        DepositLogin.add(Home);
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Back){
            DepositLogin.setVisible(false);
            Transaction_Type obj = new Transaction_Type();
            obj.design();
            return;
        }
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            DepositLogin.setVisible(false);
            return;
        }
        String AccNo = Account_NumberTextField.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from Savings");
            while(rs.next()){
                if(String.valueOf(rs.getInt(2)).equals(AccNo)){
                    Deposit obj = new Deposit(String.valueOf(rs.getFloat(3)),AccNo);
                    DepositLogin.setVisible(false);
                    obj.design();
                    return;
                }
            }
            Message.setText("Account Does Not Exist!!!");
            Message.setVisible(true);
        }
        catch (Exception e){
            Message.setText("Database Connection Error!!!");
            Message.setVisible(true);
        }


    }

    public static void main(String[] args) {
        DepositLogin obj = new DepositLogin();
        obj.design();
    }
}

