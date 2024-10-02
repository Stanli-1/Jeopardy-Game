class Main {

    private static String[][] questionBoard = new String[6][5];
    private static String[][] answerBoard = new String[6][5];
    private static String[] topicNames = new String[6];

    public static void main(String[] args) {

        QandA QandA = new QandA(); 
        topicNames = QandA.getTopicNames(); //array that holds all the topic names
        QandA.storeQandA(); // reads all the files and stores all the Q and As
        String[] tempArray = new String[2];

        //loops through all topics
        for (int topic = 1; topic <= questionBoard.length; topic++) {
            int row = 0;
            //loops through all difficulty of questions
            for (int difficulty = 200; difficulty <= 1000; difficulty = difficulty + 200) {
                tempArray = QandA.getQandA(topic, difficulty); //get the random question and answer
                questionBoard[topic-1][row] = tempArray[0]; //assign the question
                answerBoard[topic-1][row] = tempArray[1]; //assign the answer    
                row++;   
            }
        }

        new GUI(); //calls GUI class

    } //main

    // return the boards to the gui
    public String[][] getBoard (String type) {
        if (type.equals("Q"))
            return questionBoard;
        else
            return answerBoard;
    } //getBoard

    //return the topics to the gui
    public String[] getTopics() {
        return topicNames;
    }
    
} //Main