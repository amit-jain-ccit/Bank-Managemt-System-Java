import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class DeleteAccount implements ActionListener{
    private final JFrame DeleteAccount = new JFrame("Delete Your Account");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Account_Number = new JLabel("Account Number");
    private final JTextField Account_NumberTextField = new JTextField();
    private final JButton Delete = new JButton("Delete Account");
    private final JLabel Message = new JLabel("Invalid Account Number!!!");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));

    public void design(){


        // Adding AnD setting frame
        DeleteAccount.setSize(500,500);
        DeleteAccount.getContentPane().setBackground(new Color(238, 182, 146, 255));
        DeleteAccount.setLayout(null);
        DeleteAccount.setResizable(false);
        DeleteAccount.setLocationRelativeTo(null);
        DeleteAccount.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\delete.png").getImage());
        DeleteAccount.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DeleteAccount.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        DeleteAccount.add(bankLogo);


        //Account Number Label And TextField
        Account_Number.setBounds(100,200,150,30);
        Account_Number.setFont(new Font("Serif", Font.BOLD, 16));
        DeleteAccount.add(Account_Number);
        Account_NumberTextField.setBounds(100,230,300,30);
        DeleteAccount.add(Account_NumberTextField);

        // Delete account Button
        Delete.setBounds(100,300,300,50);
        Delete.setForeground(new Color(0, 0, 0));
        Delete.setBackground(new Color(190, 188, 188));
        Delete.setBorder(BorderFactory.createRaisedBevelBorder());
        Delete.setFont(new Font("Serif", Font.BOLD, 16));
        Delete.addActionListener(this);
        DeleteAccount.add(Delete);

        // Message
        Message.setBounds(150,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        DeleteAccount.add(Message);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        DeleteAccount.add(Back);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        DeleteAccount.add(Home);




    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Home){
            DeleteAccount.setVisible(false);
            LoginPage obj = new LoginPage();
            obj.design();
            return;
        }
        if(Choice.getSource() == Back){
            DeleteAccount.setVisible(false);
            ChooseAction obj = new ChooseAction();
            obj.design();
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
                    PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Savings WHERE AccountNumber = ?");
                    preparedStatement.setInt(1,Integer.parseInt(AccNo));
                    preparedStatement.execute();
                    Message.setText("Account Deleted!!!");
                    Message.setVisible(true);
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
        DeleteAccount obj = new DeleteAccount();
        obj.design();
    }
}
