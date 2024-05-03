import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static Map<String, TreeSet<String>> phoneBook = new HashMap<>();
    static String namePattern = "[А-Яа-я]+";
    static String numberPattern = "^(\\+7|8)?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$";
    static String stringList = "LIST";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("exit")) {
            System.out.print("Введите номер, имя или команду: ");
            input = scanner.nextLine();
            if (input.matches(namePattern)) {
                addName(input, scanner);
            }
            else if (input.matches(numberPattern)) {
                addNumber(input, scanner);
            }
            else if (input.equals(stringList)) {
                phoneBook.forEach((k, v) -> System.out.println(k + "-" + v));
            }
            else{
                System.out.println("Неверный формат ввода");
            }
        }
        scanner.close();
    }

    public static void addName(String name, Scanner scanner) {
        if (!phoneBook.containsKey(name)) {
            System.out.println("Такого имени в телефонной книге нет.");
            System.out.printf("Введите номер телефона для абонента %s: ", name);
            String numberUser = scanner.nextLine();
            while (!numberUser.matches(numberPattern) || isExistsNumber(numberUser)) {
                System.out.println("Неверный формат ввода или такой номер уже существует.");
                System.out.printf("Введите номер телефона для абонента %s: ", name);
                numberUser = scanner.nextLine();
            }

            TreeSet<String> numbers = new TreeSet<>();
            numbers.add(numberUser);
            phoneBook.put(name, numbers);
            System.out.println("Контакт сохранен!");
        }
        else{
            System.out.println("Такое имя уже существует");
        }

    }

    public static void addNumber(String number, Scanner scanner) {

        if (!isExistsNumber(number)) {
            System.out.println("Такого номера в телефонной книге нет.");
            System.out.printf("Введите имя абонента для номера “%s”: ", number);
            String nameUser = scanner.nextLine();
            while (!nameUser.matches(nameUser)){
                System.out.println("Неверный формат ввода");
                nameUser = scanner.nextLine();
            }

            TreeSet<String> numbers = phoneBook.get(nameUser);
            if(numbers == null){
                numbers = new TreeSet<>();
                numbers.add(number);
                phoneBook.put(nameUser, numbers);
            }
            else{
                numbers.add(number);
            }
            System.out.println("Контакт сохранен!");
        }
        else{
            System.out.println("Такой телефонный номер уже существует");
        }
    }

    private static boolean isExistsNumber(String number) {
        for (String nameUser : phoneBook.keySet()) {
            TreeSet<String> numbers = phoneBook.get(nameUser);
            if(numbers.contains(number)){
                return true;
            }
        }
        return false;
    }

}


