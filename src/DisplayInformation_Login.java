import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class DisplayInformation_Login implements ActionListener{
    private final JFrame ShowINfo = new JFrame("Show Information");
    private final JLabel bankLogo = new JLabel(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\Logo.png"));
    private final JLabel Account_Number = new JLabel("Account Number");
    private final JTextField Account_NumberTextField = new JTextField();
    private final JButton Show = new JButton("Show Information");
    private final JLabel Message = new JLabel("Invalid Account Number!!!");
    private final JButton Home = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\home.png"));
    private final JButton Back = new JButton(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\back.png"));


    public void design(){

        // Adding AnD setting frame
        ShowINfo.setSize(500,500);
        ShowINfo.getContentPane().setBackground(new Color(238, 182, 146, 255));
        ShowINfo.setLayout(null);
        ShowINfo.setResizable(false);
        ShowINfo.setLocationRelativeTo(null);
        ShowINfo.setIconImage(new ImageIcon("C:\\Users\\vijia\\OneDrive\\Desktop\\HeraPheriBank\\src\\Images\\show.png").getImage());
        ShowINfo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ShowINfo.setVisible(true);

        // Bank Logo
        bankLogo.setBounds(0,0,500,100);
        ShowINfo.add(bankLogo);


        //Account Number Label And TextField
        Account_Number.setBounds(100,200,150,30);
        Account_Number.setFont(new Font("Serif", Font.BOLD, 16));
        ShowINfo.add(Account_Number);
        Account_NumberTextField.setBounds(100,230,300,30);
        ShowINfo.add(Account_NumberTextField);

        // Delete account Button
        Show.setBounds(100,300,300,50);
        Show.setForeground(new Color(0, 0, 0));
        Show.setBackground(new Color(190, 188, 188));
        Show.setBorder(BorderFactory.createRaisedBevelBorder());
        Show.setFont(new Font("Serif", Font.BOLD, 16));
        Show.addActionListener(this);
        ShowINfo.add(Show);

        // Message
        Message.setBounds(150,400,200,30);
        Message.setFont(new Font("Serif", Font.BOLD, 16));
        Message.setBackground(Color.WHITE);
        Message.setForeground(Color.red);
        Message.setHorizontalAlignment(SwingConstants.CENTER);
        Message.setOpaque(true);
        Message.setVisible(false);
        ShowINfo.add(Message);

        //HomeButton
        Home.setBounds(430,410,50,50);
        Home.addActionListener(this);
        ShowINfo.add(Home);

        //BackButton
        Back.setBounds(0,410,50,50);
        Back.setBackground(new Color(238, 182, 146, 255));
        Back.setBorder(null);
        Back.addActionListener(this);
        ShowINfo.add(Back);


    }

    @Override
    public void actionPerformed(ActionEvent Choice) {
        if(Choice.getSource() == Back){
            ShowINfo.setVisible(false);
            ChooseAction obj = new ChooseAction();
            obj.design();
            return;
        }
        if(Choice.getSource() == Home){
            LoginPage obj = new LoginPage();
            obj.design();
            ShowINfo.setVisible(false);
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
                    DisplayInformation obj = new DisplayInformation(rs.getString(1),String.valueOf(rs.getInt(2)),String.valueOf(rs.getFloat(3)),String.valueOf(rs.getLong(4)));
                    ShowINfo.setVisible(false);
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
        DisplayInformation_Login obj = new DisplayInformation_Login();
        obj.design();
    }
}

