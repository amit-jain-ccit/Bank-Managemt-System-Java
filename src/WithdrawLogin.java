import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class WithdrawLogin implements ActionListener{
    private final JFrame WithdrawLogin = new JFrame("Login And Withdraw Money");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Account_Number = new JLabel("Account Number");
    private final JTextField Account_NumberTextField = new JTextField();
    private final JButton Show = new JButton("Submit");
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));

    private final JLabel Message = new JLabel("Invalid Account Number!!!");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));


    public void design(){

        // Adding AnD setting frame
        WithdrawLogin.setSize(500,500);
        WithdrawLogin.getContentPane().setBackground(new Color(238, 182, 146, 255));
        WithdrawLogin.setLayout(null);
        WithdrawLogin.setResizable(false);
        WithdrawLogin.setLocationRelativeTo(null);
        WithdrawLogin.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\LoginIcon.png").getImage());
        WithdrawLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        WithdrawLogin.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        WithdrawLogin.add(bankLogo);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        WithdrawLogin.add(Back);

        //Account Number Label And TextField
        Account_Number.setBounds(100,200,150,30);
        Account_Number.setFont(new Font("Serif", Font.BOLD, 16));
        WithdrawLogin.add(Account_Number);
        Account_NumberTextField.setBounds(100,230,300,30);
        WithdrawLogin.add(Account_NumberTextField);

        // Delete account Button
        Show.setBounds(100,300,300,50);
        Show.setForeground(new Color(0, 0, 0));
        Show.setBackground(new Color(190, 188, 188));
        Show.setBorder(BorderFactory.createRaisedBevelBorder());
        Show.setFont(new Font("Serif", Font.BOLD, 16));
        Show.addActionListener(this);
        WithdrawLogin.add(Show);

        // Message
        Message.setBounds(150,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        WithdrawLogin.add(Message);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        WithdrawLogin.add(Home);
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            WithdrawLogin.setVisible(false);
            return;
        }
        if(Choice.getSource() == Back){

            Transaction_Type obj = new Transaction_Type();
            obj.design();
            WithdrawLogin.setVisible(false);
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
                    Withdraw obj = new Withdraw(String.valueOf(rs.getFloat(3)), AccNo);
                    WithdrawLogin.setVisible(false);
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
        WithdrawLogin obj = new WithdrawLogin();
        obj.design();
    }
}

