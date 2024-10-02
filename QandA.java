import java.util.*;
import java.io.*;
import java.util.Random;

public class QandA {

    private Random ran = new Random();
    private int random; // Random question/answer that will be picked upon each run of code
    private String[] choosenQandA = new String[2]; //question and answer that is returned to main

    private String[] topicNames = new String[6]; //array that holds all the topic names
    // arraylist of all the topic questions and answers
    private ArrayList<HashMap<Integer, ArrayList<String>>> allTopicQ = new ArrayList<>();
    private ArrayList<HashMap<Integer, ArrayList<String>>> allTopicA = new ArrayList<>();
    
    // make a hashmap for each topic, containing (point value, arraylist of potential questions)
    private HashMap<Integer, ArrayList<String>> topicOneQ = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicTwoQ = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicThreeQ = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicFourQ = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicFiveQ = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicSixQ = new HashMap<Integer, ArrayList<String>>();

    // make a hashmap for each topic, containing (point value, arraylist of corresponding answers)
    private HashMap<Integer, ArrayList<String>> topicOneA = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicTwoA = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicThreeA = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicFourA = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicFiveA = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> topicSixA = new HashMap<Integer, ArrayList<String>>();

    
    public void storeQandA() { //read file of questions and answers

        // add all the hashmaps of Q and As to the arraylist containing all topics
        allTopicQ.add(topicOneQ);
        allTopicQ.add(topicTwoQ);
        allTopicQ.add(topicThreeQ);
        allTopicQ.add(topicFourQ);
        allTopicQ.add(topicFiveQ);
        allTopicQ.add(topicSixQ);
        allTopicA.add(topicOneA); //answers
        allTopicA.add(topicTwoA); 
        allTopicA.add(topicThreeA); 
        allTopicA.add(topicFourA); 
        allTopicA.add(topicFiveA); 
        allTopicA.add(topicSixA); 
        
        // create a file for each topic
        File topicOne = new File("TopicOne.txt");
        File topicTwo = new File("TopicTwo.txt");
        File topicThree = new File("TopicThree.txt");
        File topicFour = new File("TopicFour.txt");
        File topicFive = new File("TopicFive.txt");
        File topicSix = new File("TopicSix.txt");

        readFile(topicOne, 1); // read the first file
        readFile(topicTwo, 2); 
        readFile(topicThree, 3); 
        readFile(topicFour, 4); 
        readFile(topicFive, 5); 
        readFile(topicSix, 6); 
    }//storeQandA

    
    // reads a file and stores the questions/answers
    public void readFile( File topic, int topicNumber ) {
        
        FileReader input; //reading streams of characters in the file
        BufferedReader readFile; // Bufferes files for efficiency
        String lineOfText; // Stores each line of text in the file
        int questionValue = 0; // will be used as the key when adding questions to hashmap of qs
        
        try {
            input = new FileReader(topic);
            readFile = new BufferedReader(input);
            topicNames[topicNumber-1] = readFile.readLine(); // set the first line as the topic name

            //creates an arraylist of questions for the topic
            allTopicQ.get(topicNumber-1).put(200, new ArrayList<String>());
            allTopicQ.get(topicNumber-1).put(400, new ArrayList<String>());
            allTopicQ.get(topicNumber-1).put(600, new ArrayList<String>());
            allTopicQ.get(topicNumber-1).put(800, new ArrayList<String>());
            allTopicQ.get(topicNumber-1).put(1000, new ArrayList<String>());
            //creates an arraylist of answers for the topic
            allTopicA.get(topicNumber-1).put(200, new ArrayList<String>());
            allTopicA.get(topicNumber-1).put(400, new ArrayList<String>());
            allTopicA.get(topicNumber-1).put(600, new ArrayList<String>());
            allTopicA.get(topicNumber-1).put(800, new ArrayList<String>());
            allTopicA.get(topicNumber-1).put(1000, new ArrayList<String>());

            while ((lineOfText = readFile.readLine()) != null) {
                // this switch determines which section of questions it is reading
                //    For ex. if it goes into 200, that means it is reading 200pt questions
                // Default means that the line is a question or answer
                switch(lineOfText) {
                    case "-200-":
                        questionValue = 200;
                        break;
                    case "-400-":
                        questionValue = 400;                        
                        break;
                    case "-600-":
                        questionValue = 600;
                        break;  
                    case "-800-":
                        questionValue = 800;
                        break;
                    case "-1000-":
                        questionValue = 1000;
                        break;
                    default:
                        // get the correct topic, then access the correct question (int key) and append the question (arraylist value)
                        allTopicQ.get(topicNumber-1).get(questionValue).add(lineOfText);
                        // get the correct topic, then access the correct question (int key) and append the answer (arraylist value)
                        allTopicA.get(topicNumber-1).get(questionValue).add(readFile.readLine());
                        break;
                }//switch

            }//while    
            
            readFile.close();
            input.close();
            
        }//try
            
        catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException; " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("A error has occured.");
            System.err.println("IOExcpetion; " + e.getMessage());
        }
        
    }//readfile 


    // This function returns a random question and answer as string array
    //    Requires topic number and question value
    public String[] getQandA (int topic, int value) {
        random = ran.nextInt(allTopicQ.get(topic-1).get(value).size()); // pick q/a
        choosenQandA[0] = allTopicQ.get(topic-1).get(value).get(random);
        choosenQandA[1] = allTopicA.get(topic-1).get(value).get(random);
        return choosenQandA;
    }

    public String[] getTopicNames () {
        return topicNames;
    } //getTopicNames
    

}//QandA