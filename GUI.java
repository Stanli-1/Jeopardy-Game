import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.util.Arrays;

public class GUI implements ActionListener {
    private int mainFrameWidth = 1600;
    private int mainFrameHeight = 900;
    private JFrame mainFrame = new JFrame(); // window

    // panel with all the Qs
    private JPanel jeopardyQuestions = new JPanel(); 
    private int jeopardyQuestionsWidth = mainFrameWidth;
    private int jeopardyQuestionsHeight = (mainFrameHeight/5)*4;

    // panel with team names and points
    private JPanel teamInfo = new JPanel(); 
    private int teamInfoWidth = mainFrameWidth;
    private int teamInfoHeight = mainFrameHeight/5;

    // TextFields for the 6 topics 
    private JLabel topic1 = new JLabel(); private JLabel topic2 = new JLabel();
    private JLabel topic3 = new JLabel(); private JLabel topic4 = new JLabel();
    private JLabel topic5 = new JLabel(); private JLabel topic6 = new JLabel();

    // Button for each question (naming convention = Topic 1 Button for 100 points)
    private JButton T1Button200 = new JButton("$200"); private JButton T2Button200 = new JButton("$200");
    private JButton T3Button200 = new JButton("$200"); private JButton T4Button200 = new JButton("$200");
    private JButton T5Button200 = new JButton("$200"); private JButton T6Button200 = new JButton("$200");
    //400pt
    private JButton T1Button400 = new JButton("$400"); private JButton T2Button400 = new JButton("$400");
    private JButton T3Button400 = new JButton("$400"); private JButton T4Button400 = new JButton("$400");
    private JButton T5Button400 = new JButton("$400"); private JButton T6Button400 = new JButton("$400");
    //600pt
    private JButton T1Button600 = new JButton("$600"); private JButton T2Button600 = new JButton("$600");
    private JButton T3Button600 = new JButton("$600"); private JButton T4Button600 = new JButton("$600");
    private JButton T5Button600 = new JButton("$600"); private JButton T6Button600 = new JButton("$600");
    //800pt
    private JButton T1Button800 = new JButton("$800"); private JButton T2Button800 = new JButton("$800");
    private JButton T3Button800 = new JButton("$800"); private JButton T4Button800 = new JButton("$800");
    private JButton T5Button800 = new JButton("$800"); private JButton T6Button800 = new JButton("$800");
    //1000pt
    private JButton T1Button1000 = new JButton("$1000"); private JButton T2Button1000 = new JButton("$1000");
    private JButton T3Button1000 = new JButton("$1000"); private JButton T4Button1000 = new JButton("$1000");
    private JButton T5Button1000 = new JButton("$1000"); private JButton T6Button1000 = new JButton("$1000");

    //team names
    private JTextField team1 = new JTextField(); private JTextField team2 = new JTextField();
    private JTextField team3 = new JTextField(); private JTextField team4 = new JTextField();
    private JTextField team5 = new JTextField(); private JTextField team6 = new JTextField();
    //Team points
    private JLabel team1Points = new JLabel(); private JLabel team2Points = new JLabel();
    private JLabel team3Points = new JLabel(); private JLabel team4Points = new JLabel();
    private JLabel team5Points = new JLabel(); private JLabel team6Points = new JLabel();
    private int[] teamPoints = {0,0,0,0,0,0}; 
    // # of quesitons asked (will be used to check if game is finished)
    private int questionsCompleted = 0;

    // info imported from main
    private static String[][] questionBoard = new String[6][5];
    private static String[][] answerBoard = new String[6][5];
    private static String[] topicNames = new String[6];

    // secondary frame that pops up when question is clicked
    private int questionFrameWidth = 1600;
    private int questionFrameHeight = 820;
    private JFrame questionFrame = new JFrame(); 
    // panel in secondary frame to display choosen question 
    private JPanel questionPanel = new JPanel(); 
    private int questionPanelWidth = questionFrameWidth;
    private int questionPanelHeight = (questionFrameHeight/5)*4;
    // This button is used to display the answer when clicked 
    private JButton currentQuestion = new JButton();
    private int questionRow;
    private int questionCol;

    // panel in secondary frame to pick which team got Q right
    private JPanel addTeamPoints = new JPanel(); 
    private int addTeamPointsWidth = questionFrameWidth;
    private int addTeamPointsHeight = (questionFrameHeight/5);
    // Buttons to add points to team
    private int questionValue;
    private JButton team1Correct = new JButton();
    private JButton team2Correct = new JButton();
    private JButton team3Correct = new JButton();
    private JButton team4Correct = new JButton();
    private JButton team5Correct = new JButton();
    private JButton team6Correct = new JButton();


    public GUI() { //constructor

        Main Main = new Main();
        questionBoard = Main.getBoard("Q");
        answerBoard = Main.getBoard("A");
        topicNames = Main.getTopics();
        
        // Main frame
        mainFrame.setSize(mainFrameWidth, mainFrameHeight); // width and height (in pixels)
        mainFrame.setTitle("Astrophysics Jeopardy"); // title of window
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE); // closes frame

        // Question Frame
        questionFrame.setSize(questionFrameWidth, questionFrameHeight); 
        mainFrame.setTitle("Astrophysics Jeopardy"); 
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE); 

        
        //------------------------------------------------------------ Topic and Questions (main frame) -------------------------------------------------

        
        // Panel with topics and questions
        jeopardyQuestions.setLayout(new GridLayout(6, 6)); // creates grid (rows, columns, horizontal gap, vertical gap)
        jeopardyQuestions.setPreferredSize(new Dimension(jeopardyQuestionsWidth, jeopardyQuestionsHeight)); //(width, height)
        jeopardyQuestions.setBackground(Color.decode("#010a78")); // background color
        jeopardyQuestions.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // add padding
        mainFrame.add(jeopardyQuestions, BorderLayout.CENTER);

        // Configure the question topics
        topic1.setText(topicNames[0]);
        topic1.setForeground(Color.white); // make text white
        topic1.setFont(new Font("Arial", Font.BOLD, 20)); //font and size
        topic1.setHorizontalAlignment(JLabel.CENTER);
        //
        topic2.setText(topicNames[1]);
        topic2.setForeground(Color.white);
        topic2.setFont(new Font("Arial", Font.BOLD, 20));
        topic2.setHorizontalAlignment(JLabel.CENTER);
        //
        topic3.setText(topicNames[2]);
        topic3.setForeground(Color.white);
        topic3.setFont(new Font("Arial", Font.BOLD, 20));
        topic3.setHorizontalAlignment(JLabel.CENTER);
        //
        topic4.setText(topicNames[3]);
        topic4.setForeground(Color.white);
        topic4.setFont(new Font("Arial", Font.BOLD, 20));
        topic4.setHorizontalAlignment(JLabel.CENTER);
        //
        topic5.setText(topicNames[4]);
        topic5.setForeground(Color.white);
        topic5.setFont(new Font("Arial", Font.BOLD, 20));
        topic5.setHorizontalAlignment(JLabel.CENTER);
        //
        topic6.setText(topicNames[5]);
        topic6.setForeground(Color.white);
        topic6.setFont(new Font("Arial", Font.BOLD, 20));
        topic6.setHorizontalAlignment(JLabel.CENTER);

        // Configure the Buttons (questions)
        T1Button200.addActionListener(this); T1Button200.setActionCommand("T1_200"); //custom action command
        T2Button200.addActionListener(this); T2Button200.setActionCommand("T2_200");
        T3Button200.addActionListener(this); T3Button200.setActionCommand("T3_200");
        T4Button200.addActionListener(this); T4Button200.setActionCommand("T4_200");
        T5Button200.addActionListener(this); T5Button200.setActionCommand("T5_200");
        T6Button200.addActionListener(this); T6Button200.setActionCommand("T6_200");
        T1Button200.setBackground(Color.decode("#010a78")); T1Button200.setForeground(Color.decode("#d7a04b")); // background and text color
        T2Button200.setBackground(Color.decode("#010a78")); T2Button200.setForeground(Color.decode("#d7a04b"));
        T3Button200.setBackground(Color.decode("#010a78")); T3Button200.setForeground(Color.decode("#d7a04b"));
        T4Button200.setBackground(Color.decode("#010a78")); T4Button200.setForeground(Color.decode("#d7a04b"));
        T5Button200.setBackground(Color.decode("#010a78")); T5Button200.setForeground(Color.decode("#d7a04b"));
        T6Button200.setBackground(Color.decode("#010a78")); T6Button200.setForeground(Color.decode("#d7a04b"));
        T1Button200.setFont(new Font("Arial", Font.BOLD,36)); T2Button200.setFont(new Font("Arial", Font.BOLD,36)); // font
        T3Button200.setFont(new Font("Arial", Font.BOLD,36)); T4Button200.setFont(new Font("Arial", Font.BOLD,36));
        T5Button200.setFont(new Font("Arial", Font.BOLD,36)); T6Button200.setFont(new Font("Arial", Font.BOLD,36));
        //400pt
        T1Button400.addActionListener(this); T1Button400.setActionCommand("T1_400");
        T2Button400.addActionListener(this); T2Button400.setActionCommand("T2_400");
        T3Button400.addActionListener(this); T3Button400.setActionCommand("T3_400");
        T4Button400.addActionListener(this); T4Button400.setActionCommand("T4_400");
        T5Button400.addActionListener(this); T5Button400.setActionCommand("T5_400");
        T6Button400.addActionListener(this); T6Button400.setActionCommand("T6_400");
        T1Button400.setBackground(Color.decode("#010a78")); T1Button400.setForeground(Color.decode("#d7a04b")); 
        T2Button400.setBackground(Color.decode("#010a78")); T2Button400.setForeground(Color.decode("#d7a04b"));
        T3Button400.setBackground(Color.decode("#010a78")); T3Button400.setForeground(Color.decode("#d7a04b"));
        T4Button400.setBackground(Color.decode("#010a78")); T4Button400.setForeground(Color.decode("#d7a04b"));
        T5Button400.setBackground(Color.decode("#010a78")); T5Button400.setForeground(Color.decode("#d7a04b"));
        T6Button400.setBackground(Color.decode("#010a78")); T6Button400.setForeground(Color.decode("#d7a04b"));
        T1Button400.setFont(new Font("Arial", Font.BOLD,36)); T2Button400.setFont(new Font("Arial", Font.BOLD,36)); 
        T3Button400.setFont(new Font("Arial", Font.BOLD,36)); T4Button400.setFont(new Font("Arial", Font.BOLD,36));
        T5Button400.setFont(new Font("Arial", Font.BOLD,36)); T6Button400.setFont(new Font("Arial", Font.BOLD,36));
        // 600pt
        T1Button600.addActionListener(this); T1Button600.setActionCommand("T1_600");
        T2Button600.addActionListener(this); T2Button600.setActionCommand("T2_600");
        T3Button600.addActionListener(this); T3Button600.setActionCommand("T3_600");
        T4Button600.addActionListener(this); T4Button600.setActionCommand("T4_600");
        T5Button600.addActionListener(this); T5Button600.setActionCommand("T5_600");
        T6Button600.addActionListener(this); T6Button600.setActionCommand("T6_600");
        T1Button600.setBackground(Color.decode("#010a78")); T1Button600.setForeground(Color.decode("#d7a04b")); 
        T2Button600.setBackground(Color.decode("#010a78")); T2Button600.setForeground(Color.decode("#d7a04b"));
        T3Button600.setBackground(Color.decode("#010a78")); T3Button600.setForeground(Color.decode("#d7a04b"));
        T4Button600.setBackground(Color.decode("#010a78")); T4Button600.setForeground(Color.decode("#d7a04b"));
        T5Button600.setBackground(Color.decode("#010a78")); T5Button600.setForeground(Color.decode("#d7a04b"));
        T6Button600.setBackground(Color.decode("#010a78")); T6Button600.setForeground(Color.decode("#d7a04b"));
        T1Button600.setFont(new Font("Arial", Font.BOLD,36)); T2Button600.setFont(new Font("Arial", Font.BOLD,36)); 
        T3Button600.setFont(new Font("Arial", Font.BOLD,36)); T4Button600.setFont(new Font("Arial", Font.BOLD,36));
        T5Button600.setFont(new Font("Arial", Font.BOLD,36)); T6Button600.setFont(new Font("Arial", Font.BOLD,36));
        // 800pt
        T1Button800.addActionListener(this); T1Button800.setActionCommand("T1_800");
        T2Button800.addActionListener(this); T2Button800.setActionCommand("T2_800");
        T3Button800.addActionListener(this); T3Button800.setActionCommand("T3_800");
        T4Button800.addActionListener(this); T4Button800.setActionCommand("T4_800");
        T5Button800.addActionListener(this); T5Button800.setActionCommand("T5_800");
        T6Button800.addActionListener(this); T6Button800.setActionCommand("T6_800");
        T1Button800.setBackground(Color.decode("#010a78")); T1Button800.setForeground(Color.decode("#d7a04b")); 
        T2Button800.setBackground(Color.decode("#010a78")); T2Button800.setForeground(Color.decode("#d7a04b"));
        T3Button800.setBackground(Color.decode("#010a78")); T3Button800.setForeground(Color.decode("#d7a04b"));
        T4Button800.setBackground(Color.decode("#010a78")); T4Button800.setForeground(Color.decode("#d7a04b"));
        T5Button800.setBackground(Color.decode("#010a78")); T5Button800.setForeground(Color.decode("#d7a04b"));
        T6Button800.setBackground(Color.decode("#010a78")); T6Button800.setForeground(Color.decode("#d7a04b"));
        T1Button800.setFont(new Font("Arial", Font.BOLD,36)); T2Button800.setFont(new Font("Arial", Font.BOLD,36)); 
        T3Button800.setFont(new Font("Arial", Font.BOLD,36)); T4Button800.setFont(new Font("Arial", Font.BOLD,36));
        T5Button800.setFont(new Font("Arial", Font.BOLD,36)); T6Button800.setFont(new Font("Arial", Font.BOLD,36));
        // 1000pt
        T1Button1000.addActionListener(this); T1Button1000.setActionCommand("T1_1000");
        T2Button1000.addActionListener(this); T2Button1000.setActionCommand("T2_1000");
        T3Button1000.addActionListener(this); T3Button1000.setActionCommand("T3_1000");
        T4Button1000.addActionListener(this); T4Button1000.setActionCommand("T4_1000");
        T5Button1000.addActionListener(this); T5Button1000.setActionCommand("T5_1000");
        T6Button1000.addActionListener(this); T6Button1000.setActionCommand("T6_1000");
        T1Button1000.setBackground(Color.decode("#010a78")); T1Button1000.setForeground(Color.decode("#d7a04b")); 
        T2Button1000.setBackground(Color.decode("#010a78")); T2Button1000.setForeground(Color.decode("#d7a04b"));
        T3Button1000.setBackground(Color.decode("#010a78")); T3Button1000.setForeground(Color.decode("#d7a04b"));
        T4Button1000.setBackground(Color.decode("#010a78")); T4Button1000.setForeground(Color.decode("#d7a04b"));
        T5Button1000.setBackground(Color.decode("#010a78")); T5Button1000.setForeground(Color.decode("#d7a04b"));
        T6Button1000.setBackground(Color.decode("#010a78")); T6Button1000.setForeground(Color.decode("#d7a04b"));
        T1Button1000.setFont(new Font("Arial", Font.BOLD,36)); T2Button1000.setFont(new Font("Arial", Font.BOLD,36));
        T3Button1000.setFont(new Font("Arial", Font.BOLD,36)); T4Button1000.setFont(new Font("Arial", Font.BOLD,36));
        T5Button1000.setFont(new Font("Arial", Font.BOLD,36)); T6Button1000.setFont(new Font("Arial", Font.BOLD,36));

        // Add topics and questions (adds top down starting from left)
        jeopardyQuestions.add(topic1); jeopardyQuestions.add(topic2); jeopardyQuestions.add(topic3); //row1
        jeopardyQuestions.add(topic4); jeopardyQuestions.add(topic5); jeopardyQuestions.add(topic6); 
        jeopardyQuestions.add(T1Button200); jeopardyQuestions.add(T2Button200); jeopardyQuestions.add(T3Button200); //row2
        jeopardyQuestions.add(T4Button200); jeopardyQuestions.add(T5Button200); jeopardyQuestions.add(T6Button200);
        jeopardyQuestions.add(T1Button400); jeopardyQuestions.add(T2Button400); jeopardyQuestions.add(T3Button400); //row3
        jeopardyQuestions.add(T4Button400); jeopardyQuestions.add(T5Button400); jeopardyQuestions.add(T6Button400);
        jeopardyQuestions.add(T1Button600); jeopardyQuestions.add(T2Button600); jeopardyQuestions.add(T3Button600); //row4
        jeopardyQuestions.add(T4Button600); jeopardyQuestions.add(T5Button600); jeopardyQuestions.add(T6Button600);
        jeopardyQuestions.add(T1Button800); jeopardyQuestions.add(T2Button800); jeopardyQuestions.add(T3Button800); //row5
        jeopardyQuestions.add(T4Button800); jeopardyQuestions.add(T5Button800); jeopardyQuestions.add(T6Button800);
        jeopardyQuestions.add(T1Button1000); jeopardyQuestions.add(T2Button1000); jeopardyQuestions.add(T3Button1000); //row6
        jeopardyQuestions.add(T4Button1000); jeopardyQuestions.add(T5Button1000); jeopardyQuestions.add(T6Button1000);

        
        //----------------------------------------------------------- Team Names (main frame) -----------------------------------------------------------
        
        
        // Configure panel with team names
        teamInfo.setLayout(new GridLayout(2, 6, 15, 15)); // creates grid (rows, columns, horizontal gap, vertical gap)
        teamInfo.setPreferredSize(new Dimension(teamInfoWidth, teamInfoHeight)); //(width, height)
        teamInfo.setBackground(Color.decode("#010a78"));
        teamInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // add padding
        mainFrame.add(teamInfo, BorderLayout.SOUTH);

        // Add team textfields for user to input
        teamInfo.add(team1); teamInfo.add(team2);
        teamInfo.add(team3); teamInfo.add(team4);
        teamInfo.add(team5); teamInfo.add(team6);

        // Configure textboxes for team names
        team1.setHorizontalAlignment(JTextField.CENTER); // center text
        team1.setFont(new Font("Arial", Font.BOLD, 16)); //font and size
        //
        team2.setHorizontalAlignment(JTextField.CENTER);
        team2.setFont(new Font("Arial", Font.BOLD, 16)); 
        //
        team3.setHorizontalAlignment(JTextField.CENTER);
        team3.setFont(new Font("Arial", Font.BOLD, 16)); 
        //
        team4.setHorizontalAlignment(JTextField.CENTER);
        team4.setFont(new Font("Arial", Font.BOLD, 16));
        //
        team5.setHorizontalAlignment(JTextField.CENTER);
        team5.setFont(new Font("Arial", Font.BOLD, 16));
        //
        team6.setHorizontalAlignment(JTextField.CENTER);
        team6.setFont(new Font("Arial", Font.BOLD, 16));

        // Configure the team point displays
        team1Points.setText("$" + Integer.toString(teamPoints[0]));
        team1Points.setForeground(Color.white); // make text white
        team1Points.setFont(new Font("Arial", Font.BOLD, 25)); //font and size
        team1Points.setHorizontalAlignment(JLabel.CENTER);
        //
        team2Points.setText("$" + Integer.toString(teamPoints[1]));
        team2Points.setForeground(Color.white); // make text white
        team2Points.setFont(new Font("Arial", Font.BOLD, 25)); //font and size
        team2Points.setHorizontalAlignment(JLabel.CENTER);
        //
        team3Points.setText("$" + Integer.toString(teamPoints[2]));
        team3Points.setForeground(Color.white); // make text white
        team3Points.setFont(new Font("Arial", Font.BOLD, 25)); //font and size
        team3Points.setHorizontalAlignment(JLabel.CENTER);
        //
        team4Points.setText("$" + Integer.toString(teamPoints[3]));
        team4Points.setForeground(Color.white); // make text white
        team4Points.setFont(new Font("Arial", Font.BOLD, 25)); //font and size
        team4Points.setHorizontalAlignment(JLabel.CENTER);
        //
        team5Points.setText("$" + Integer.toString(teamPoints[4]));
        team5Points.setForeground(Color.white); // make text white
        team5Points.setFont(new Font("Arial", Font.BOLD, 25)); //font and size
        team5Points.setHorizontalAlignment(JLabel.CENTER);
        //
        team6Points.setText("$" + Integer.toString(teamPoints[5]));
        team6Points.setForeground(Color.white); // make text white
        team6Points.setFont(new Font("Arial", Font.BOLD, 25)); //font and size
        team6Points.setHorizontalAlignment(JLabel.CENTER);

        // add point labels for each team
        teamInfo.add(team1Points); teamInfo.add(team2Points);
        teamInfo.add(team3Points); teamInfo.add(team4Points);
        teamInfo.add(team5Points); teamInfo.add(team6Points);

        mainFrame.setVisible(true); // display frame

        //----------------------------------------------------------- Question (question frame) -----------------------------------------------------------
        
        questionPanel.setLayout(null); 
        questionPanel.setPreferredSize(new Dimension(questionPanelWidth, questionPanelHeight)); //(width, height)
        questionPanel.setBackground(Color.decode("#010a78")); // background color
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // add padding
        questionFrame.add(questionPanel, BorderLayout.CENTER);

        // This button will display the question and qhen clicked, will show the answer
        currentQuestion.addActionListener(this); 
        currentQuestion.setActionCommand("seeAnswer"); 
        currentQuestion.setBackground(Color.decode("#010a78")); 
        currentQuestion.setForeground(Color.decode("#d7a04b")); 
        currentQuestion.setFont(new Font("Arial", Font.BOLD,50)); 
        currentQuestion.setBounds(0, 0, questionFrameWidth, (questionFrameHeight/4)*3); // (new x, new y, width, height)
        currentQuestion.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // add padding
        currentQuestion.setHorizontalTextPosition(SwingConstants.CENTER);
        questionPanel.add(currentQuestion, BorderLayout.NORTH);

        // Configure panel with team names
        addTeamPoints.setLayout(new GridLayout(1, 6, 15, 15)); // creates grid (rows, columns, horizontal gap, vertical gap)
        addTeamPoints.setPreferredSize(new Dimension(addTeamPointsWidth, addTeamPointsHeight)); //(width, height)
        addTeamPoints.setBackground(Color.decode("#010a78"));
        addTeamPoints.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // add padding
        questionFrame.add(addTeamPoints, BorderLayout.SOUTH);

        // Configure buttons that add points to teams
        addTeamPoints.add(team1Correct); addTeamPoints.add(team2Correct);
        addTeamPoints.add(team3Correct); addTeamPoints.add(team4Correct);
        addTeamPoints.add(team5Correct); addTeamPoints.add(team6Correct);
        //team1
        team1Correct.addActionListener(this); 
        team1Correct.setActionCommand("team1Correct"); 
        team1Correct.setBackground(Color.decode("#010a78")); 
        team1Correct.setForeground(Color.decode("#d7a04b")); 
        team1Correct.setFont(new Font("Arial", Font.BOLD,25)); 
        //team2
        team2Correct.addActionListener(this); 
        team2Correct.setActionCommand("team2Correct"); 
        team2Correct.setBackground(Color.decode("#010a78")); 
        team2Correct.setForeground(Color.decode("#d7a04b")); 
        team2Correct.setFont(new Font("Arial", Font.BOLD,25)); 
        //team3
        team3Correct.addActionListener(this); 
        team3Correct.setActionCommand("team3Correct"); 
        team3Correct.setBackground(Color.decode("#010a78")); 
        team3Correct.setForeground(Color.decode("#d7a04b")); 
        team3Correct.setFont(new Font("Arial", Font.BOLD,25)); 
        //team4
        team4Correct.addActionListener(this); 
        team4Correct.setActionCommand("team4Correct"); 
        team4Correct.setBackground(Color.decode("#010a78")); 
        team4Correct.setForeground(Color.decode("#d7a04b")); 
        team4Correct.setFont(new Font("Arial", Font.BOLD,25)); 
        //team5
        team5Correct.addActionListener(this); 
        team5Correct.setActionCommand("team5Correct"); 
        team5Correct.setBackground(Color.decode("#010a78")); 
        team5Correct.setForeground(Color.decode("#d7a04b")); 
        team5Correct.setFont(new Font("Arial", Font.BOLD,25)); 
        //team6
        team6Correct.addActionListener(this); 
        team6Correct.setActionCommand("team6Correct"); 
        team6Correct.setBackground(Color.decode("#010a78")); 
        team6Correct.setForeground(Color.decode("#d7a04b")); 
        team6Correct.setFont(new Font("Arial", Font.BOLD,25)); 
        
    }//GUI Contructor

    
    // This method is called when the game is finished
    public void gameComplete() {
        JDialog winningScreen = new JDialog(); //create dialog box
        winningScreen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //close dialog when X is clicked
        winningScreen.setSize(mainFrameWidth-100, mainFrameHeight-100); //set size
        winningScreen.setLocation(50, 50); // move to center
        JLabel winMessage = new JLabel();
        winMessage.setOpaque(true);
        winMessage.setForeground(Color.decode("#d7a04b"));
        winMessage.setBackground(Color.decode("#010a78"));
        winMessage.setFont(new Font("Arial", Font.BOLD, 25));
        winMessage.setHorizontalAlignment(JLabel.CENTER);
        winningScreen.add(winMessage);

        int[] originalTeamPoints = new int [6]; //creates original copy of team points
        for (int i = 0; i < teamPoints.length; i ++)
            originalTeamPoints[i] = teamPoints[i];
        Arrays.sort(teamPoints); //sorts other copy

        if (teamPoints[5] == originalTeamPoints[0]) //team1 wins
            winMessage.setText(team1.getText() + " Wins!");
        if (teamPoints[5] == originalTeamPoints[1]) //team2 wins
            winMessage.setText(team2.getText() + " Wins!");
        if (teamPoints[5] == originalTeamPoints[2]) //team3 wins
            winMessage.setText(team3.getText() + " Wins!");
        if (teamPoints[5] == originalTeamPoints[3]) //team4 wins
            winMessage.setText(team4.getText() + " Wins!");
        if (teamPoints[5] == originalTeamPoints[4]) //team5 wins
            winMessage.setText(team5.getText() + " Wins!");
        if (teamPoints[5] == originalTeamPoints[5]) //team6 wins
            winMessage.setText(team6.getText() + " Wins!");

        winningScreen.setVisible(true);
    }


//===================================================== Action Listener ====================================================================


    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the action command from button press

        switch (command) {
        // 200pt
            case "T1_200":
                currentQuestion.setText("<html><p>" + questionBoard[0][0] + " \n(Click to see answer)</p></html>"); //set text in new frame to display question
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); //update all buttons with team names
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 0; questionCol = 0; // next 2 lines are for when adding points and showing answer
                questionValue = 200;
                T1Button200.setText(""); // set text of question to nothing
                T1Button200.setEnabled(false); //disable button
                questionsCompleted++;
                break;
                
            case "T2_200":
                currentQuestion.setText("<html><p>" + questionBoard[1][0] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 1; questionCol = 0; 
                questionValue = 200;
                T2Button200.setText(""); 
                T2Button200.setEnabled(false); 
                questionsCompleted++;
                break;
            case "T3_200":
                currentQuestion.setText("<html><p>" + questionBoard[2][0] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 2; questionCol = 0; 
                questionValue = 200;
                T3Button200.setText(""); 
                T3Button200.setEnabled(false);
                questionsCompleted++;
                break;
            case "T4_200":
                currentQuestion.setText("<html><p>" + questionBoard[3][0] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 3; questionCol = 0; 
                questionValue = 200;
                T4Button200.setText(""); 
                T4Button200.setEnabled(false);
                questionsCompleted++;
                break;
            case "T5_200":
                currentQuestion.setText("<html><p>" + questionBoard[4][0] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 4; questionCol = 0; 
                questionValue = 200;
                T5Button200.setText(""); 
                T5Button200.setEnabled(false);
                questionsCompleted++;
                break;
            case "T6_200":
                currentQuestion.setText("<html><p>" + questionBoard[5][0] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 5; questionCol = 0; 
                questionValue = 200;
                T6Button200.setText(""); 
                T6Button200.setEnabled(false);
                questionsCompleted++;
                break;
        // 400pt
            case "T1_400":
                currentQuestion.setText("<html><p>" + questionBoard[0][1] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 0; questionCol = 1; 
                questionValue = 400;
                T1Button400.setText(""); 
                T1Button400.setEnabled(false);
                questionsCompleted++;
                break;
            case "T2_400":
                currentQuestion.setText("<html><p>" + questionBoard[1][1] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 1; questionCol = 1; 
                questionValue = 400;
                T2Button400.setText(""); 
                T2Button400.setEnabled(false);
                questionsCompleted++;
                break;
            case "T3_400":
                currentQuestion.setText("<html><p>" + questionBoard[2][1] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 2; questionCol = 1; 
                questionValue = 400;
                T3Button400.setText(""); 
                T3Button400.setEnabled(false);
                questionsCompleted++;
                break;
            case "T4_400":
                currentQuestion.setText("<html><p>" + questionBoard[3][1] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 3; questionCol = 1; 
                questionValue = 400;
                T4Button400.setText(""); 
                T4Button400.setEnabled(false);
                questionsCompleted++;
                break;
            case "T5_400":
                currentQuestion.setText("<html><p>" + questionBoard[4][1] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 4; questionCol = 1; 
                questionValue = 400;
                T5Button400.setText(""); 
                T5Button400.setEnabled(false);
                questionsCompleted++;
                break;
            case "T6_400":
                currentQuestion.setText("<html><p>" + questionBoard[5][1] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 5; questionCol = 1; 
                questionValue = 400;
                T6Button400.setText(""); 
                T6Button400.setEnabled(false);
                questionsCompleted++;
                break;
        // 600pt
            case "T1_600":
                currentQuestion.setText("<html><p>" + questionBoard[0][2] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 0; questionCol = 2; 
                questionValue = 600;
                T1Button600.setText(""); 
                T1Button600.setEnabled(false);
                questionsCompleted++;
                break;
            case "T2_600":
                currentQuestion.setText("<html><p>" + questionBoard[1][2] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 1; questionCol = 2; 
                questionValue = 600;
                T2Button600.setText(""); 
                T2Button600.setEnabled(false);
                questionsCompleted++;
                break;
            case "T3_600":
                currentQuestion.setText("<html><p>" + questionBoard[2][2] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 2; questionCol = 2; 
                questionValue = 600;
                T3Button600.setText(""); 
                T3Button600.setEnabled(false);
                questionsCompleted++;
                break;
            case "T4_600":
                currentQuestion.setText("<html><p>" + questionBoard[3][2] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 3; questionCol = 2; 
                questionValue = 600;
                T4Button600.setText(""); 
                T4Button600.setEnabled(false);
                questionsCompleted++;
                break;
            case "T5_600":
                currentQuestion.setText("<html><p>" + questionBoard[4][2] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 4; questionCol = 2; 
                questionValue = 600;
                T5Button600.setText(""); 
                T5Button600.setEnabled(false);
                questionsCompleted++;
                break;
            case "T6_600":
                currentQuestion.setText("<html><p>" + questionBoard[5][2] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 5; questionCol = 2; 
                questionValue = 600;
                T6Button600.setText(""); 
                T6Button600.setEnabled(false);
                questionsCompleted++;
                break;
        // 800pt
            case "T1_800":
                currentQuestion.setText("<html><p>" + questionBoard[0][3] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 0; questionCol = 3; 
                questionValue = 800;
                T1Button800.setText(""); 
                T1Button800.setEnabled(false);
                questionsCompleted++;
                break;
            case "T2_800":
                currentQuestion.setText("<html><p>" + questionBoard[1][3] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 1; questionCol = 3; 
                questionValue = 800;
                T2Button800.setText(""); 
                T2Button800.setEnabled(false);
                questionsCompleted++;
                break;
            case "T3_800":
                currentQuestion.setText("<html><p>" + questionBoard[2][3] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 2; questionCol = 3; 
                questionValue = 800;
                T3Button800.setText(""); 
                T3Button800.setEnabled(false);
                questionsCompleted++;
                break;
            case "T4_800":
                currentQuestion.setText("<html><p>" + questionBoard[3][3] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 3; questionCol = 3; 
                questionValue = 800;
                T4Button800.setText(""); 
                T4Button800.setEnabled(false);  
                questionsCompleted++;              
                break;
            case "T5_800":
                currentQuestion.setText("<html><p>" + questionBoard[4][3] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 4; questionCol = 3; 
                questionValue = 800;
                T5Button800.setText(""); 
                T5Button800.setEnabled(false);
                questionsCompleted++;
                break;
            case "T6_800":
                currentQuestion.setText("<html><p>" + questionBoard[5][3] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 5; questionCol = 3; 
                questionValue = 800;
                T6Button800.setText(""); 
                T6Button800.setEnabled(false);
                questionsCompleted++;
                break;
        // 1000pt
            case "T1_1000":
                currentQuestion.setText("<html><p>" + questionBoard[0][4] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 0; questionCol = 4; 
                questionValue = 1000;
                T1Button1000.setText(""); 
                T1Button1000.setEnabled(false);
                questionsCompleted++;
                break;
            case "T2_1000":
                currentQuestion.setText("<html><p>" + questionBoard[1][4] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 1; questionCol = 4; 
                questionValue = 1000;
                T2Button1000.setText(""); 
                T2Button1000.setEnabled(false);
                questionsCompleted++;
                break;
            case "T3_1000":
                currentQuestion.setText("<html><p>" + questionBoard[2][4] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 2; questionCol = 4; 
                questionValue = 1000;
                T3Button1000.setText(""); 
                T3Button1000.setEnabled(false);
                questionsCompleted++;
                break;
            case "T4_1000":
                currentQuestion.setText("<html><p>" + questionBoard[3][4] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 3; questionCol = 4; 
                questionValue = 1000;
                T4Button1000.setText(""); 
                T4Button1000.setEnabled(false);
                questionsCompleted++;
                break;
            case "T5_1000":
                currentQuestion.setText("<html><p>" + questionBoard[4][4] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 4; questionCol = 4; 
                questionValue = 1000;
                T5Button1000.setText(""); 
                T5Button1000.setEnabled(false);
                questionsCompleted++;
                break;
            case "T6_1000":
                currentQuestion.setText("<html><p>" + questionBoard[5][4] + " \n(Click to see answer)</p></html>");
                team1Correct.setText(team1.getText()); team2Correct.setText(team2.getText()); 
                team3Correct.setText(team3.getText()); team4Correct.setText(team4.getText());
                team5Correct.setText(team5.getText()); team6Correct.setText(team6.getText());
                questionFrame.setVisible(true);
                questionRow = 5; questionCol = 4; 
                questionValue = 1000;
                T6Button1000.setText(""); 
                T6Button1000.setEnabled(false);
                questionsCompleted++;
                break;

        // Question panel
            case "seeAnswer":
                currentQuestion.setText("<html><p>" + answerBoard[questionRow][questionCol] + "</p></html>");
                break;

            case "team1Correct": //if team 1 gets question correct
                teamPoints[0] = teamPoints[0] + questionValue; // add points
                team1Points.setText("$" + Integer.toString(teamPoints[0])); // update label
                questionFrame.dispatchEvent(new WindowEvent(questionFrame, WindowEvent.WINDOW_CLOSING)); // close frame
                //check if game is finished
                if (questionsCompleted >= 30) 
                    gameComplete();
                break;
            case "team2Correct":
                teamPoints[1] = teamPoints[1] + questionValue; 
                team2Points.setText("$" + Integer.toString(teamPoints[1])); 
                questionFrame.dispatchEvent(new WindowEvent(questionFrame, WindowEvent.WINDOW_CLOSING)); 
                if (questionsCompleted >= 30) 
                    gameComplete();
                break;
            case "team3Correct":
                teamPoints[2] = teamPoints[2] + questionValue; // add points
                team3Points.setText("$" + Integer.toString(teamPoints[2])); // update label
                questionFrame.dispatchEvent(new WindowEvent(questionFrame, WindowEvent.WINDOW_CLOSING)); // close frame
                if (questionsCompleted >= 30) 
                    gameComplete();
                break;
            case "team4Correct":
                teamPoints[3] = teamPoints[3] + questionValue; // add points
                team4Points.setText("$" + Integer.toString(teamPoints[3])); // update label
                questionFrame.dispatchEvent(new WindowEvent(questionFrame, WindowEvent.WINDOW_CLOSING)); // close frame
                if (questionsCompleted >= 30) 
                    gameComplete();
                break;
            case "team5Correct":
                teamPoints[4] = teamPoints[4] + questionValue; // add points
                team5Points.setText("$" + Integer.toString(teamPoints[4])); // update label
                questionFrame.dispatchEvent(new WindowEvent(questionFrame, WindowEvent.WINDOW_CLOSING)); // close frame
                if (questionsCompleted >= 30) 
                    gameComplete();
                break;
            case "team6Correct":
                teamPoints[5] = teamPoints[5] + questionValue; // add points
                team6Points.setText("$" + Integer.toString(teamPoints[5])); // update label
                questionFrame.dispatchEvent(new WindowEvent(questionFrame, WindowEvent.WINDOW_CLOSING)); // close frame
                if (questionsCompleted >= 30) 
                    gameComplete();
                break;

        }//switch
        
    } //actionPerformed

}//GUI class