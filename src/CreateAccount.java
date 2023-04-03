import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CreateAccount implements ActionListener {
    private final JFrame Create_Account = new JFrame("Create Account");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Name = new JLabel("Name");
    private final JTextField Name_TextField = new JTextField();
    private final JLabel Account_No = new JLabel("Account Number");
    private final JTextField AccountNo_TextField = new JTextField() ;
    private final JLabel Cash = new JLabel("Cash");
    private final JTextField Cash_TextField = new JTextField();
    private final JLabel Contact = new JLabel("Contact");
    private final JTextField Contact_TextField = new JTextField() ;

    private final JButton Submit = new JButton("Submit");

    private final  JLabel Message = new JLabel("Account Created");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));
    private final Boolean AccountType;
    CreateAccount(Boolean AcType){
        AccountType = AcType;
    }
    public void design(){

        // Frame Settings
        Create_Account.setSize(500,500);
        Create_Account.getContentPane().setBackground(new Color(238, 182, 146, 255));
        Create_Account.setLayout(null);
        Create_Account.setResizable(false);
        Create_Account.setLocationRelativeTo(null);
        Create_Account.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Create.png").getImage());
        Create_Account.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Create_Account.setVisible(true);



        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        Create_Account.add(bankLogo);

        // Name label And textFiled
        Name.setBounds(100,110,100,30);
        Name.setFont(new Font("Serif", Font.BOLD, 16));
        Create_Account.add(Name);
        Name_TextField.setBounds(100,140,300,30);
        Name_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Name_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Create_Account.add(Name_TextField);

        // Account NUmber label And textFiled
        Account_No.setBounds(100,180,150,30);
        Account_No.setFont(new Font("Serif", Font.BOLD, 16));
        Create_Account.add(Account_No);
        AccountNo_TextField.setBounds(100,210,300,30);
        AccountNo_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        AccountNo_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Create_Account.add(AccountNo_TextField);

        // Cash label And textFiled
        Cash.setBounds(100,250,100,30);
        Cash.setFont(new Font("Serif", Font.BOLD, 16));
        Create_Account.add(Cash);
        Cash_TextField.setBounds(100,280,300,30);
        Cash_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Cash_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Create_Account.add(Cash_TextField);

        // Contact label And textFiled
        Contact.setBounds(100,320,100,30);
        Contact.setFont(new Font("Serif", Font.BOLD, 16));
        Create_Account.add(Contact);
        Contact_TextField.setBounds(100,350,300,30);
        Contact_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Contact_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Create_Account.add(Contact_TextField);

        //Submit Button
        Submit.setBounds(100,400,80,40);
        Submit.setForeground(new Color(0, 0, 0));
        Submit.setBackground(new Color(155, 153, 153));
        Submit.setFont(new Font("Serif", Font.BOLD, 16));
        Submit.setBorder(BorderFactory.createRaisedBevelBorder());
        Submit.addActionListener(this);
        Create_Account.add(Submit);

        // Message
        Message.setBounds(200,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        Create_Account.add(Message);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        Create_Account.add(Back);
        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        Create_Account.add(Home);
    }

    public static void main(String[] args) {
        CreateAccount obj = new CreateAccount(true);
        obj.design();
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {

        if (Choice.getSource() == Home) {
            Create_Account.setVisible(false);
            LoginPage obj = new LoginPage();
            obj.design();
        }
        if (Choice.getSource() == Back) {
            Create_Account.setVisible(false);
            ChooseAccountType obj = new ChooseAccountType();
            obj.design();
            return;
        }
        if (AccountType) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
                String name = Name_TextField.getText();
                int Acc;
                float Cash;
                long Contact;
                try {
                     Acc = Integer.parseInt(AccountNo_TextField.getText());
                } catch (Exception e) {
                    Message.setText("Invalid Account Number");
                    Message.setVisible(true);
                    return;
                }
                try {
                     Cash = Float.parseFloat(Cash_TextField.getText());
                } catch (Exception e) {
                    Message.setText("Invalid Cash Amount");
                    Message.setVisible(true);
                    return;
                }
                try {
                     Contact = Long.parseLong(Contact_TextField.getText());
                    if (Contact_TextField.getText().length() != 10) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Message.setText("Invalid Contact Number");
                    Message.setVisible(true);
                    return;
                }
                try {
                    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Current values(?,?,?,?) ");
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, Acc);
                    preparedStatement.setFloat(3, Cash);
                    preparedStatement.setLong(4, Contact);
                    preparedStatement.execute();
                }
                catch (Exception e){
                    Message.setText("Account Already Exist");
                    Message.setVisible(true);
                    return;
                }
                Message.setText("Account Created!!!");
                Message.setVisible(true);
            } catch (Exception e) {
                Message.setText("Database Connection Error");
                Message.setVisible(true);
            }
        }
        else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
                String name = Name_TextField.getText();
                int Acc;
                float Cash;
                long Contact;
                try {
                    Acc = Integer.parseInt(AccountNo_TextField.getText());
                } catch (Exception e) {
                    Message.setText("Invalid Account Number");
                    Message.setVisible(true);
                    return;
                }
                try {
                    Cash = Float.parseFloat(Cash_TextField.getText());
                } catch (Exception e) {
                    Message.setText("Invalid Cash Amount");
                    Message.setVisible(true);
                    return;
                }
                try {
                    Contact = Long.parseLong(Contact_TextField.getText());
                    if (Contact_TextField.getText().length() != 10) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Message.setText("Invalid Contact Number");
                    Message.setVisible(true);
                    return;
                }
                try {
                    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Savings values(?,?,?,?) ");
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, Acc);
                    preparedStatement.setFloat(3, Cash);
                    preparedStatement.setLong(4, Contact);
                    preparedStatement.execute();
                }
                catch (Exception e){
                    Message.setText("Account Already Exist");
                    Message.setVisible(true);
                    return;
                }
                Message.setText("Account Created!!!");
                Message.setVisible(true);
            } catch (Exception e) {
                Message.setText("Database Connection Error");
                Message.setVisible(true);
            }
        }
    }
}
