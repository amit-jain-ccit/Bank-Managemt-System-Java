import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage implements ActionListener, MouseListener {
    final JFrame loginForm = new JFrame("Login Page");
    private final JLabel bankLogo  = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Username_Label  = new JLabel("LoginID");
    private final JLabel Password_Label  = new JLabel("Password");
    private final JLabel Forgot    = new JLabel("ForgotPassword");
    final JLabel Message   = new JLabel("Invalid Password/Username");
    final JTextField UserName_TextField = new JTextField();
    final JPasswordField Password_ValueField = new JPasswordField();
    final JButton Submit = new JButton("Submit");

    public void design(){
        // Frame Settings
        loginForm.setSize(500,500);
        loginForm.getContentPane().setBackground(new Color(238, 182, 146, 255));
        loginForm.setLayout(null);
        loginForm.setResizable(false);
        loginForm.setLocationRelativeTo(null);
        loginForm.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\LoginIcon.png").getImage());
        loginForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginForm.setVisible(true);



        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        loginForm.add(bankLogo);

        // UserName label And textFiled
        Username_Label.setBounds(100,190,100,30);
        Username_Label.setFont(new Font("Serif", Font.BOLD, 16));
        loginForm.add(Username_Label);
        UserName_TextField.setBounds(100,220,300,30);
        loginForm.add(UserName_TextField);

        // Password Label and Text field
        Password_Label.setBounds(100,260,100,30);
        Password_Label.setFont(new Font("Serif", Font.BOLD, 16));
        loginForm.add(Password_Label);
        Password_ValueField.setBounds(100,290,300,30);
        loginForm.add(Password_ValueField);

        //Forgot Password
        Forgot.setBounds(300 , 315 , 100,30);
        Forgot.setFont(new Font("Serif", Font.BOLD, 14));
        Forgot.setForeground(new Color(0, 64, 255));
        Forgot.addMouseListener(this);
        loginForm.add(Forgot);

        //Submit Button
        Submit.setBounds(200,350,100,40);
        Submit.setForeground(new Color(0, 0, 0));
        Submit.setBackground(new Color(155, 153, 153));
        Submit.setFont(new Font("Serif", Font.BOLD, 16));
        Submit.setBorder(BorderFactory.createRaisedBevelBorder());
        Submit.addActionListener(this);
        loginForm.add(Submit);

        // Message
        Message.setBounds(150,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        loginForm.add(Message);
    }
    private void forgotClick(){
        loginForm.setVisible(false);
        ForgotPassword obj = new ForgotPassword();
        obj.design();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            String username =UserName_TextField.getText();
            String password = String.valueOf(Password_ValueField.getPassword());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HeraPheriBank", "root", "vijay@1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Employee");
            while (rs.next()) {
                String Actual_Username = String.valueOf(rs.getInt(1));
                String Actual_Password = String.valueOf(rs.getInt(2));
                if (username.equalsIgnoreCase(Actual_Username)) {
                    if (password.equalsIgnoreCase(Actual_Password)) {
                        Message.setText("Successful LogIn ");
                        Message.setVisible(true);
                        loginForm.setVisible(false);
                        ChooseAction obj2 = new ChooseAction();
                        obj2.design();
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


    public static void main(String[] args) {
        LoginPage obj = new LoginPage();
        obj.design();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        forgotClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
