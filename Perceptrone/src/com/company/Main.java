package com.company;
import Iris.Iris;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static boolean run = true;

    public static void main(String[] args) throws IOException {
//        String pathTest = "src/Iris/iris_test.txt";
//        String pathTraining = "src/Iris/iris_training.txt";
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        JFrame frame = new JFrame("KNN");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocation(200, 200);
        JPanel jPanel = new JPanel();

        jPanel.setPreferredSize(new Dimension(400, 200));
        jPanel.setLayout(new GridLayout(8, 1));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        jPanel.setBackground(Color.magenta);
        JLabel jLabel = new JLabel(" Enter Testing Data \n Enter Training data");
        JTextField PathTest = new JTextField(50);
        JTextField PathTraining = new JTextField(50);
        JTextField flower = new JTextField(50);
        JButton calcres = new JButton("Calcres");
        JButton Flower = new JButton("add your flower");
        Flower.setBackground(Color.magenta.darker());
        calcres.setBackground(Color.magenta);
        jPanel.add(jLabel);
        jPanel.add(PathTest);
        jPanel.add(PathTraining);
        jPanel.add(flower);
        jPanel.add(Flower);
        jPanel.add(calcres);


        Flower.addActionListener(e -> {
            try {
                outPut(flower(flower.getText(),PathTest.getText(),PathTraining.getText()), jPanel, frame);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        calcres.addActionListener(e -> {

            String s = null;
            try {
                s = start(PathTest.getText(), PathTraining.getText());
            } catch (Exception ex) {
                s = "Something went wrong with input data";
            }
            outPut(s, jPanel, frame);
        });


        frame.setContentPane(jPanel);
        frame.pack();
    }

    public static void outPut(String string, JPanel jPanel, JFrame jFrame) {

        jPanel.removeAll();
        jPanel.revalidate();
        jPanel.setLayout(new GridLayout(3, 1));
        JLabel jLabel = new JLabel(string);
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        jPanel.setBackground(Color.magenta);
        jPanel.add(jLabel);
        JButton jButton = new JButton("Continue");
        jButton.setBackground(Color.magenta.darker());
        jButton.addActionListener(e -> {
            jFrame.dispose();
            new Main();
        });
        JButton jButton1 = new JButton("Exit");
        jButton1.setBackground(Color.magenta.darker());
        jButton1.addActionListener(e -> {
            System.exit(0);
        });
        if (!string.equals("Something went wrong with input data"))
            jPanel.add(jButton);
        jPanel.add(jButton1);
    }

    public static String flower(String s,String pathTest,String pathTraining) throws IOException {

        String[] strings = s.split("\\s+");
        double[] doubles = new double[strings.length+1];
        doubles[0] = 1;
        for (int i = 1; i < strings.length; i++) {
            doubles[i] = Double.parseDouble(strings[i-1]);
        }
        Iris iris = new Iris(doubles, "");
        List<Iris> irises = new ArrayList<Iris>();
        irises.add(iris);
        KNN knn = new KNN();
        double[] arr = knn.training(knn.readTest(pathTest), knn.readTraining(pathTraining));
        return knn.clacOne(irises,arr);
    }

    public static String start(String pathTest, String pathTraining) {
        int correctlyClassified = 0;
        int statistics = 0;
        KNN knn = new KNN();
        try {
            double[] arr = knn.training(knn.readTest(pathTest), knn.readTraining(pathTraining));
            boolean[] booleans = knn.testingPerceptrone(knn.readTest(pathTest), arr);
            for (int i = 0; i < booleans.length; i++) {
                if (booleans[i]) {
                    correctlyClassified++;
                }
            }
            statistics = (correctlyClassified * 100) / booleans.length;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("correctlyClassified: " + correctlyClassified);
        System.out.println("percent: " + statistics + "%");
        String string = "correctlyClassified: " + correctlyClassified + " percent: " + statistics;
        return string;
    }
}
