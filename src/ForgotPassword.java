import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ForgotPassword implements ActionListener {
    private final JFrame resetPasswordPage = new JFrame("Reset Password");
    private final JLabel bankLogo  = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Username_Label  = new JLabel("Username");
    private final JLabel Password_Label  = new JLabel("New Password");
    private final JLabel Security_Code  = new JLabel("Security Code");
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));

    private final JLabel Message   = new JLabel("Invalid Security Code/Password/Username");
    private final JTextField UserName_TextField = new JTextField();
    private final JPasswordField NewPassword_ValueField = new JPasswordField();
    private final JTextField SecurityCode_TextField = new JTextField();
    final JButton Reset = new JButton("Reset");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));


    public void design(){

        // Frame Settings
        resetPasswordPage.setSize(500 ,500);
        resetPasswordPage.getContentPane().setBackground(new Color(238, 182, 146, 255));
        resetPasswordPage.setLayout(null);
        resetPasswordPage.setResizable(false);
        resetPasswordPage.setLocationRelativeTo(null);
        resetPasswordPage.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\reset.png").getImage());
        resetPasswordPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        resetPasswordPage.setVisible(true);



        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        resetPasswordPage.add(bankLogo);

        // Security Code label And textFiled
        Security_Code.setBounds(100,140,100,30);
        Security_Code.setFont(new Font("Serif", Font.BOLD, 16));
        resetPasswordPage.add(Security_Code);
        SecurityCode_TextField.setBounds(100,170,300,30);
        resetPasswordPage.add(SecurityCode_TextField);

        // UserName label And textFiled
        Username_Label.setBounds(100,210,100,30);
        Username_Label.setFont(new Font("Serif", Font.BOLD, 16));
        resetPasswordPage.add(Username_Label);
        UserName_TextField.setBounds(100,240,300,30);
        resetPasswordPage.add(UserName_TextField);

        // Password Label and Text field
        Password_Label.setBounds(100,280,100,30);
        Password_Label.setFont(new Font("Serif", Font.BOLD, 16));
        resetPasswordPage.add(Password_Label);
        NewPassword_ValueField.setBounds(100,310,300,30);
        resetPasswordPage.add(NewPassword_ValueField);


        //Reset Button
        Reset.setBounds(200,370,100,40);
        Reset.setForeground(new Color(0, 0, 0));
        Reset.setBackground(new Color(155, 153, 153));
        Reset.setFont(new Font("Serif", Font.BOLD, 16));
        Reset.setBorder(BorderFactory.createRaisedBevelBorder());
        Reset.addActionListener(this);
        resetPasswordPage.add(Reset);

        // UserName label And textFiled
        Message.setBounds(110,420,290,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        resetPasswordPage.add(Message);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        resetPasswordPage.add(Back);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        resetPasswordPage.add(Home);
    }

    public void buttonClick(){
        try {

            String username =UserName_TextField.getText();
            String password = String.valueOf(NewPassword_ValueField.getPassword());
            String security = SecurityCode_TextField.getText();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Employee");
            while (rs.next()) {
                String Actual_Username = String.valueOf(rs.getInt(1));
                if (username.equalsIgnoreCase(Actual_Username)) {
                    if (security.equalsIgnoreCase("0000")) {
                        PreparedStatement preparedStatement = con.prepareStatement("UPDATE Employee SET Password = ? WHERE Username = ?");
                        preparedStatement.setInt(1,Integer.parseInt(password));
                        preparedStatement.setInt(2,Integer.parseInt(username));
                        preparedStatement.execute();
                        Message.setText("Successful Reset!!!");
                        Message.setVisible(true);
                        return;
                    }
                }
            }
            Message.setVisible(true);
            con.close();
        } catch (Exception Error){
            Message.setText("Database Connection Error");
            Message.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Back){
            resetPasswordPage.setVisible(false);
            LoginPage obj = new LoginPage();
            obj.design();
            return;
        }
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            resetPasswordPage.setVisible(false);
            return;
        }
        buttonClick();
    }

    public static void main(String[] args) {
        ForgotPassword obj = new ForgotPassword();
        obj.design();
    }
}
