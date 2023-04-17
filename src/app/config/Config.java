package app.config;

import app.model.Film;
import database.DataBase;

import java.io.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class Config<T> {

    public static String getString() {
        return new Scanner(System.in).nextLine();
    }

    public static int getInteger() {
        try {
            return Integer.parseInt(getString());
        } catch (NumberFormatException e) {
            System.out.println("Đầu vào không hợp lệ. Vui lòng nhập số !");
            return getInteger();
        }
    }

    public static Boolean getBoolean() {
        return getString().equalsIgnoreCase("true");
    }

    public List<T> readFromFile(String pathFile) {
        List<T> tList = new ArrayList<>();
        try {
            File file = new File(pathFile);
            if (!file.exists()) file.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                tList = (List<T>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Foun");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOEExceptoin");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
        return tList;
    }

    public void writeFile(String pathFile, List<T> tList) {
        try {
            File file = new File(pathFile);
            if (!file.exists()) file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IOE Exception");
        }
    }
}
