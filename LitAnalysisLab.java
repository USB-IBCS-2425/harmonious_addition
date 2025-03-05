import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

class LitAnalysisLab {
    public JFrame mainframe;
    public JPanel output;
    public JButton readB;
    public JButton avgB;
    public JButton writeB;
    public JButton mostCommonB;
    public JButton leastCommonB;
    public JButton cleanB;
    public JButton longB;
    public JButton shortB;
    public JButton mVowelB;
    public JButton lVowelB;
    public JButton avgSentenceB;
    public JButton strangeB;

    public static JTextField toRead;
    public static JTextArea resultT;
    public static ArrayList<String> textTokens;
    public static ArrayList<String> allwords;
    public static HashMap<String, Integer> wordmap;
    public static ArrayList<String> sentences = new ArrayList<>();

    public LitAnalysisLab() {
        textTokens = new ArrayList<String>();
        allwords = new ArrayList<String>();
        wordmap = new HashMap<>();

        mainframe = new JFrame("Literature Analysis");
        mainframe.setSize(900, 750);

        mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        toRead = new JTextField("book.txt");
        toRead.setBounds(300, 50, 200, 40);
        mainframe.setLayout(null);
        mainframe.add(toRead);

        output = new JPanel();
        output.setBounds(50, 150, 100, 40);
        mainframe.add(output);

        readB = new JButton("Read File");
        readB.setActionCommand("READ");
        readB.addActionListener(new ButtonClickListener());
        readB.setBounds(50, 150, 100, 100);
        mainframe.add(readB);

        avgB = new JButton("Average Word");
        avgB.setActionCommand("AVG");
        avgB.addActionListener(new ButtonClickListener());
        avgB.setBounds(200, 150, 100, 100);
        mainframe.add(avgB);

        writeB = new JButton("Write File");
        writeB.setActionCommand("WRITE");
        writeB.addActionListener(new ButtonClickListener());
        writeB.setBounds(350, 150, 100, 100);
        mainframe.add(writeB);

        mostCommonB = new JButton("MCommon");
        mostCommonB.setActionCommand("MOST");
        mostCommonB.addActionListener(new ButtonClickListener());
        mostCommonB.setBounds(500, 150, 100, 100);
        mainframe.add(mostCommonB);

        leastCommonB = new JButton("LCommon");
        leastCommonB.setActionCommand("LEAST");
        leastCommonB.addActionListener(new ButtonClickListener());
        leastCommonB.setBounds(650, 150, 100, 100);
        mainframe.add(leastCommonB);

        cleanB = new JButton("Clean");
        cleanB.setActionCommand("CLEAN");
        cleanB.addActionListener(new ButtonClickListener());
        cleanB.setBounds(50, 270, 100, 100);
        mainframe.add(cleanB);

        longB = new JButton("Long");
        longB.setActionCommand("LONG");
        longB.addActionListener(new ButtonClickListener());
        longB.setBounds(200, 270, 100, 100);
        mainframe.add(longB);

        shortB = new JButton("Short");
        shortB.setActionCommand("SHORT");
        shortB.addActionListener(new ButtonClickListener());
        shortB.setBounds(350, 270, 100, 100);
        mainframe.add(shortB);

        mVowelB = new JButton("MVowel");
        mVowelB.setActionCommand("MVOWEL");
        mVowelB.addActionListener(new ButtonClickListener());
        mVowelB.setBounds(500, 270, 100, 100);
        mainframe.add(mVowelB);

        lVowelB = new JButton("LVowel");
        lVowelB.setActionCommand("LVOWEL");
        lVowelB.addActionListener(new ButtonClickListener());
        lVowelB.setBounds(650, 270, 100, 100);
        mainframe.add(lVowelB);

        avgSentenceB = new JButton("AvgS");
        avgSentenceB.setActionCommand("AVGS");
        avgSentenceB.addActionListener(new ButtonClickListener());
        avgSentenceB.setBounds(50, 390, 100, 100);
        mainframe.add(avgSentenceB);

        strangeB = new JButton("Strange");
        strangeB.setActionCommand("STRANGE");
        strangeB.addActionListener(new ButtonClickListener());
        strangeB.setBounds(200, 390, 100, 100);
        mainframe.add(strangeB);

        resultT = new JTextArea("");
        resultT.setBounds(500, 450, 800, 250);
        mainframe.add(resultT);

        mainframe.setVisible(true);
    }

    public static void main(String[] args) {
        LitAnalysisLab fo = new LitAnalysisLab();
    }

    public static double round(double x, int places) {
        int mult = (int)Math.pow(10, places);
        int y = (int)(x*mult);
        double rounded = y / (double) mult;
        return rounded;
    }

    public static void readFile() {
        String fname = toRead.getText();
        textTokens.clear();
        wordmap.clear();

        try {
            File f = new File(fname);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String data = s.next();
                textTokens.add(data);
            }
            s.close();
        }
        catch (FileNotFoundException err) {
            System.out.println("An error occurred.");
            err.printStackTrace();
        }

        resultT.setText("File Read\nFile has " + textTokens.size() + " tokens");
    }

    public static void parseWords() {
        allwords.clear();
        for (int i = 0; i < textTokens.size(); i++) {
            String[] tempWords = textTokens.get(i).split("\\s|--");
            for (String s : tempWords) {
                s = s.replaceAll("[\\p{P}_]", "");
                s = s.toLowerCase();
                allwords.add(s);
            }
        }
    }

    public static void showAvg() {
        double totLen = 0;
        for (String w : allwords) {
            totLen = totLen + w.length();
        }
        double avgLen = totLen / allwords.size();
        avgLen = round(avgLen, 2);
        String res = "The average word length is:\n";
        res = res + avgLen + " characters";
        resultT.setText(res);
    }

    public static void buildWordMap() {
        wordmap.clear();
        for (String w : allwords) {
            wordmap.put(w, wordmap.getOrDefault(w, 0) + 1);
        }
    }

    public static void mostCommon() {
        if (wordmap.isEmpty()) {
            buildWordMap();
        }

        int maxCount = Collections.max(wordmap.values());
        ArrayList<String> mostCommonWords = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordmap.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostCommonWords.add(entry.getKey());
            }
        }

        resultT.setText("Most Common Word(s):\n" + mostCommonWords + "\nCount: " + maxCount);
    }


    public static void leastCommon() {
        if (wordmap.isEmpty()) {
            buildWordMap();
        }

        int minCount = Collections.min(wordmap.values());
        ArrayList<String> leastCommonWords = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordmap.entrySet()) {
            if (entry.getValue() == minCount) {
                leastCommonWords.add(entry.getKey());
            }
        }

        resultT.setText("Least Common Word(s):\n" + leastCommonWords + "\nCount: " + minCount);
    }

    public static void writeFile() {
        // NEED TO CHECK WITH USER FIRST!
        String fname = toRead.getText();
        String toWrite = resultT.getText();

        try {
            FileWriter w = new FileWriter(fname);
            w.write(toWrite);
            w.close();
        }
        catch (IOException er) {
            System.out.println("Error message:");
            er.printStackTrace();
        }
    }

    public static void longestWord() {

        String longestWord = "";
        int maxLength = 0;

        for (String word : allwords) {
            if (word.length() > maxLength) {
                maxLength = word.length();
                longestWord = word;
            }
        }

        resultT.setText("Longest Word: " + longestWord);
    }

    public static void shortestWord() {

        String shortestWord = "";
        int minLength = 100;

        for (String word : allwords) {
            if (word.length() < minLength) {
                minLength = word.length();
                shortestWord = word;
            }
        }

        resultT.setText("Shortest Word: " + shortestWord);
    }

    public static void cleanText() {
        String filePath = toRead.getText();
        boolean isBookContent = false;
        StringBuilder bookContent = new StringBuilder();

        try {
            File inputFile = new File(filePath);
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();


                if (line.contains("*** START OF THE PROJECT GUTENBERG EBOOK")) {
                    isBookContent = true;
                    continue;
                }

                if (line.contains("*** END OF THE PROJECT GUTENBERG EBOOK")) {
                    break;
                }

                if (isBookContent) {
                    bookContent.append(line).append("\n");
                }
            }

            scanner.close();


            FileWriter writer = new FileWriter(filePath);
            writer.write(bookContent.toString());
            writer.close();

            resultT.setText("File cleaned");
        } catch (IOException e) {
            resultT.setText("Error cleaning file.");
            e.printStackTrace();
        }
    }
    public static void mostVowel() {
        if (allwords.isEmpty()) {
            resultT.setText("No words to analyze.");
            return;
        }

        String mostVowelWord = "";
        int maxVowels = 0;

        for (String word : allwords) {
            int vowelCount = 0;
            for (char c : word.toCharArray()) {
                if ("aeiou".indexOf(c) >= 0) {
                    vowelCount++;
                }
            }
            if (vowelCount > maxVowels) {
                maxVowels = vowelCount;
                mostVowelWord = word;
            }
        }

        resultT.setText("Word with Most Vowels: " + mostVowelWord);
    }

    public static void leastVowel() {
        if (allwords.isEmpty()) {
            resultT.setText("No words to analyze.");
            return;
        }

        String leastVowelWord = "";
        int minVowels = Integer.MAX_VALUE;

        for (String word : allwords) {
            int vowelCount = 0;
            for (char c : word.toCharArray()) {
                if ("aeiou".indexOf(c) >= 0) {
                    vowelCount++;
                }
            }
            if (vowelCount < minVowels) {
                minVowels = vowelCount;
                leastVowelWord = word;
            }
        }

        resultT.setText("Word with Least Vowels: " + leastVowelWord);
    }

    public static void averageSentenceLength() {
        if (textTokens.isEmpty()) {
            resultT.setText("No sentences found.");
            return;
        }

        int totalWords = 0;
        int sentenceCount = 0;

        String[] splitSentences = String.join(" ", textTokens).split("[.!?]");

        for (String sentence : splitSentences) {
            String[] words = sentence.trim().split("\\s+");
            if (words.length > 0) {
                totalWords += words.length;
                sentenceCount++;
            }
        }


        double avgLen = (sentenceCount > 0) ? (double) totalWords / sentenceCount : 0;

        resultT.setText("Average Sentence Length: " + String.format("%.2f", avgLen) + " words.");
    }


    public static void mostUniqueSentence() {
        if (textTokens.isEmpty() || wordmap.isEmpty()) {
            resultT.setText("No sentences to analyze.");
            return;
        }

        String uniqueSentence = "";
        int minFrequency = Integer.MAX_VALUE;

        String[] splitSentences = String.join(" ", textTokens).split("[.!?]");

        for (String sentence : splitSentences) {
            String[] words = sentence.trim().split("\\s+");

            if (words.length < 8) continue;

            int sentenceScore = 0;

            for (String word : words) {
                word = word.replaceAll("[^\\p{L}\\p{Nd}'-]+", "").toLowerCase();
                sentenceScore += wordmap.getOrDefault(word, Integer.MAX_VALUE);
            }

            if (sentenceScore < minFrequency) {
                minFrequency = sentenceScore;
                uniqueSentence = sentence;
            }
        }

        resultT.setText("Most Unique Sentence:\n" + uniqueSentence);
    }



    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("WRITE")) {
                writeFile();
            }

            if (command.equals("READ")) {
                readFile();
            }

            if (command.equals("AVG")) {
                parseWords();

                showAvg();
            }
            if (command.equals("MOST")) {
                parseWords();
                mostCommon();
            }

            if (command.equals("LEAST")) {
                parseWords();
                leastCommon();
            }

            if (command.equals("CLEAN")) {
                cleanText();
            }

            if (command.equals("LONG")) {
                parseWords();
                longestWord();
            }

            if (command.equals("SHORT")) {
                parseWords();
                shortestWord();
            }

            if (command.equals("MVOWEL")) {
                parseWords();
                mostVowel();
            }

            if (command.equals("LVOWEL")) {
                parseWords();
                leastVowel();
            }

            if (command.equals("AVGS")) {
                parseWords();
                averageSentenceLength();
            }

            if (command.equals("STRANGE")) {
                parseWords();
                mostUniqueSentence();
            }
        }
    }
}