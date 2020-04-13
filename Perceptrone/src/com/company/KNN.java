package com.company;

import Iris.Iris;

import java.io.*;
import java.util.*;

public class KNN {
    public List<Iris> readTraining(String training) throws IOException {

        List<Iris> irises = new ArrayList<>();
        String s;
        File file = new File(training);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replaceAll(",", ".");
            String[] strings = s.trim().split("\\s+");
            double[] doubles = new double[strings.length];
            doubles[0] = 1;
            for (int i = 1; i < strings.length; i++) {
                doubles[i] = Double.parseDouble(strings[i - 1]);
            }
            irises.add(new Iris(doubles, strings[strings.length - 1]));
        }
        bufferedReader.close();
        return irises;
    }

    public List<Iris> readTest(String test) throws IOException {
        List<Iris> irises = new ArrayList<>();
        String s;
        File file = new File(test);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replaceAll(",", ".");
            String[] strings = s.trim().split("\\s+");
            double[] doubles = new double[strings.length];
            doubles[0] = 1;
            for (int i = 1; i < strings.length; i++) {
                doubles[i] = Double.parseDouble(strings[i - 1]);
            }
            irises.add(new Iris(doubles, strings[strings.length - 1]));

        }
        bufferedReader.close();
        return irises;
    }

    public double[] training(List<Iris> irisesTest, List<Iris> irisesTraining) {
        double[] Wi = new double[irisesTest.get(0).doubles.length];
        int idicator = 0;
        int out = -1;
        for (int i = 0; i < Wi.length; i++) {
            Wi[i] = Math.random() * 4;
        }

        boolean run = true;
        int count = 0;
        while (run) {
        run = false;
        for (int i = 0; i < irisesTraining.size(); i++) {
            idicator = 0;
            if (irisesTraining.get(i).getString().equals("Iris-setosa")) {
                idicator = 1;
            }
            out = -1;
            double dist = 0;
            double s = 0;
            System.out.println("============================");
            for (int k = 1; k < Wi.length; k++) {
                dist += Wi[k] * irisesTraining.get(i).doubles[k];
            }
            s = dist - Wi[0] * 1;
            System.out.println(s);
            if (s > 0) {
                out = 1;
            } else {
                out = 0;
            }
            while (out != idicator) {
                run = true;
                    for (int j = 0; j < Wi.length; j++) {
                        Wi[j] = Wi[j] + (idicator - out) * irisesTraining.get(i).doubles[j];
                    }
                 dist = 0;
                s = 0;
                for (int k = 1; k < Wi.length; k++) {
                    dist += Wi[k] * irisesTraining.get(i).doubles[k];
                }
                s = dist - Wi[0] * 1;
                System.out.println(s);
                if (s > 0) {
                    out = 1;
                } else {
                    out = 0;
                }
            }
        }
        count++;}
        System.out.println(Arrays.toString(Wi));
        for (int i = 0; i < irisesTraining.size(); i++) {
            System.out.println(Arrays.toString(irisesTraining.get(i).doubles));
        }
        return Wi;
    }

    public boolean[] testingPerceptrone(List<Iris> test, double[] doubles) {
        boolean[] booleans = new boolean[test.size()];
        for (int j = 0; j < test.size(); j++) {
            double s = 0;
            double dist = 0;
            for (int i = 1; i < doubles.length; i++) {
                dist += doubles[i] * test.get(j).doubles[i];
            }
            s = dist - doubles[0] * test.get(j).doubles[0];
            System.out.println(s);
            if (s > 0 && test.get(j).getString().equals("Iris-setosa")) {
                booleans[j] = true;
            } else if (s <= 0 && !test.get(j).getString().equals("Iris-setosa")) {
                booleans[j] = true;
            } else
                booleans[j] = false;
        }
        System.out.println(Arrays.toString(booleans));
        return booleans;
    }

    public String clacOne(List<Iris> test, double[] doubles) {
        double dist = 0;
        double s = 0;
        for (int i = 1; i < doubles.length; i++) {
            dist += doubles[i] * test.get(0).doubles[i];
        }
        s = dist - doubles[0] * test.get(0).doubles[0];
        if (s > 0) {
            return "Iris-setosa";
        } else if (s <= 0) {
            return "NotIris-setosa";
        } else
            return "Something went wrong";
    }
}
