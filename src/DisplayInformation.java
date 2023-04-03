import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DisplayInformation implements ActionListener {
    private final JFrame Display = new JFrame("View Your Information");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Name = new JLabel("Name");
    private final JTextField Name_TextField;
    private final JLabel Account_No = new JLabel("Account Number");
    private final JTextField AccountNo_TextField ;
    private final JLabel Cash = new JLabel("Cash");
    private final JTextField Cash_TextField;
    private final JLabel Contact = new JLabel("Contact");
    private final JTextField Contact_TextField ;
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));


    DisplayInformation(String NameValue , String AccountNumber_Value , String CashValue , String ContactValue ){
        Name_TextField = new JTextField(NameValue);
        AccountNo_TextField = new JTextField(AccountNumber_Value);
        Cash_TextField = new JTextField("Rs. " + CashValue);
        Contact_TextField = new JTextField(ContactValue);

    }
    public void design(){
        // Frame Settings
        Display.setSize(500,500);
        Display.getContentPane().setBackground(new Color(238, 182, 146, 255));
        Display.setLayout(null);
        Display.setResizable(false);
        Display.setLocationRelativeTo(null);
        Display.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Display.png").getImage());
        Display.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Display.setVisible(true);



        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        Display.add(bankLogo);

        // Name label And textFiled
        Name.setBounds(100,110,100,30);
        Name.setFont(new Font("Serif", Font.BOLD, 16));
        Display.add(Name);
        Name_TextField.setBounds(100,140,300,30);
        Name_TextField.setEditable(false);
        Name_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Name_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Display.add(Name_TextField);

        // Account NUmber label And textFiled
        Account_No.setBounds(100,180,150,30);
        Account_No.setFont(new Font("Serif", Font.BOLD, 16));
        Display.add(Account_No);
        AccountNo_TextField.setBounds(100,210,300,30);
        AccountNo_TextField.setEditable(false);
        AccountNo_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        AccountNo_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Display.add(AccountNo_TextField);

        // Cash label And textFiled
        Cash.setBounds(100,250,100,30);
        Cash.setFont(new Font("Serif", Font.BOLD, 16));
        Display.add(Cash);
        Cash_TextField.setBounds(100,280,300,30);
        Cash_TextField.setEditable(false);
        Cash_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Cash_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Display.add(Cash_TextField);

        // Contact label And textFiled
        Contact.setBounds(100,320,100,30);
        Contact.setFont(new Font("Serif", Font.BOLD, 16));
        Display.add(Contact);
        Contact_TextField.setBounds(100,350,300,30);
        Contact_TextField.setEditable(false);
        Contact_TextField.setFont(new Font("Serif", Font.ITALIC, 18));
        Contact_TextField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Display.add(Contact_TextField);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        Display.add(Home);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        Display.add(Back);
    }

    public static void main(String[] args) {
        DisplayInformation obj = new DisplayInformation("Vijay","123","2345","123456");
        obj.design();
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            Display.setVisible(false);
        }
        if(Choice.getSource() == Back){
            Display.setVisible(false);
            DisplayInformation_Login obj = new DisplayInformation_Login();
            obj.design();
        }
    }
}
