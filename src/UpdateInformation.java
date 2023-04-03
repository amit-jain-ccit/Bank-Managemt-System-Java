import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateInformation implements ActionListener {
    private final JFrame UpdateLogin = new JFrame("Update Your Information");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Name = new JLabel("Name");
    private final JTextField Name_TextField;
    private final JLabel Account_No = new JLabel("Account Number");
    private final JTextField AccountNo_TextField ;
    private final JLabel Cash = new JLabel("Cash");
    private final JTextField Cash_TextField;
    private final JLabel Contact = new JLabel("Contact");
    private final JTextField Contact_TextField ;
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));

    private final JButton Submit = new JButton("Submit");

    private final  JLabel Message = new JLabel("Details Updated");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));


    UpdateInformation(String NameValue , String AccountNumber_Value , String CashValue , String ContactValue ){
        Name_TextField = new JTextField(NameValue);
        AccountNo_TextField = new JTextField(AccountNumber_Value);
        Cash_TextField = new JTextField("Rs. " + CashValue);
        Contact_TextField = new JTextField(ContactValue);

    }
    public void design(){
        // Frame Settings
        UpdateLogin.setSize(500,500);
        UpdateLogin.getContentPane().setBackground(new Color(238, 182, 146, 255));
        UpdateLogin.setLayout(null);
        UpdateLogin.setResizable(false);
        UpdateLogin.setLocationRelativeTo(null);
        UpdateLogin.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\update.png").getImage());
        UpdateLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UpdateLogin.setVisible(true);



        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        UpdateLogin.add(bankLogo);

        // Name label And textFiled
        Name.setBounds(100,110,100,30);
        Name.setFont(new Font("Serif", Font.BOLD, 16));
        UpdateLogin.add(Name);
        Name_TextField.setBounds(100,140,300,30);
        Name_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Name_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        UpdateLogin.add(Name_TextField);

        // Account NUmber label And textFiled
        Account_No.setBounds(100,180,150,30);
        Account_No.setFont(new Font("Serif", Font.BOLD, 16));
        UpdateLogin.add(Account_No);
        AccountNo_TextField.setBounds(100,210,300,30);
        AccountNo_TextField.setEditable(false);
        AccountNo_TextField.setToolTipText("Not Editable");
        AccountNo_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        AccountNo_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        UpdateLogin.add(AccountNo_TextField);

        // Cash label And textFiled
        Cash.setBounds(100,250,100,30);
        Cash.setFont(new Font("Serif", Font.BOLD, 16));
        UpdateLogin.add(Cash);
        Cash_TextField.setBounds(100,280,300,30);
        Cash_TextField.setEditable(false);
        Cash_TextField.setToolTipText("Not Editable");
        Cash_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Cash_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        UpdateLogin.add(Cash_TextField);

        // Contact label And textFiled
        Contact.setBounds(100,320,100,30);
        Contact.setFont(new Font("Serif", Font.BOLD, 16));
        UpdateLogin.add(Contact);
        Contact_TextField.setBounds(100,350,300,30);
        Contact_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Contact_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        UpdateLogin.add(Contact_TextField);

        //Submit Button
        Submit.setBounds(100,400,100,40);
        Submit.setForeground(new Color(0, 0, 0));
        Submit.setBackground(new Color(155, 153, 153));
        Submit.setFont(new Font("Serif", Font.BOLD, 16));
        Submit.setBorder(BorderFactory.createRaisedBevelBorder());
        Submit.addActionListener(this);
        UpdateLogin.add(Submit);

        // Message
        Message.setBounds(210,400,190,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        UpdateLogin.add(Message);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        UpdateLogin.add(Back);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        UpdateLogin.add(Home);
    }

    public static void main(String[] args) {
        UpdateInformation obj = new UpdateInformation("Vijay","123","2345","123456");
        obj.design();
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            UpdateLogin.setVisible(false);
            return;
        }
        if(Choice.getSource() == Back){
            UpdateLogin.setVisible(false);
            UpdateInformation_Login obj = new UpdateInformation_Login();
            obj.design();
            return;
        }
        String name = Name_TextField.getText();
        String contact = Contact_TextField.getText();
        try{
        if (contact.length() != 10) {
            throw new Exception();
        }
    } catch (Exception e) {
        Message.setText("Invalid Contact Number");
        Message.setVisible(true);
        return;
    }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE Savings SET Name = ? , Contact = ? WHERE AccountNumber = ?");
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2, Long.parseLong(contact));
            preparedStatement.setInt(3,Integer.parseInt(AccountNo_TextField.getText()));
            preparedStatement.execute();
            Message.setText("Details Updated");
            Message.setVisible(true);

        }
        catch (Exception e){
            Message.setText("Database Connection Number");
            Message.setVisible(true);
        }
        }
}
