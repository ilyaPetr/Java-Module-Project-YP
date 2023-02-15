import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ваш код начнется здесь
        // вы не должны ограничиваться только классом Main и можете создавать свои классы по необходимости

        int numberOfFriends;//количество друзей
        String stopInput;//завершение ввода
        String foodName;//название товара
        float foodPrice;//цена товара

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("на скольких человек необходимо разделить счёт?");

        while (true) {
            if (scanner.hasNextInt()) {
                numberOfFriends = scanner.nextInt();
                if (numberOfFriends > 1) {
                    break;
                } else {
                    System.out.println("Друзей должно быть больше 1! Введите количество друзей!");
                }
            } else {
                System.out.println("Друзей должно быть больше 1!Введите количество друзей!");
                scanner.next();
            }

        }

        while (true) {
            System.out.println( "Введите название  товара!"); //название может быть любым в том числе и числовым
            foodName = scanner.next();
            calculator.AddTovarName(foodName);

            while (true) {
                System.out.println(" Введите цену за " + foodName + ". Стоимость должна быть в формате рубли.копейки, например 10.45 или 11.40.");//цена может быть только числом больше 0

                if (scanner.hasNextFloat())//это число
                {
                    foodPrice = scanner.nextFloat();
                    if (foodPrice > 0) //это число больше нуля
                    {
                        break;
                    }
                    else {
                        System.out.println("Цена должна быть любым числом больше 0!");
                    }
                } else {
                    System.out.println("Цена должна быть любым числом больше 0!");
                    scanner.next();
                }
            }
            calculator.AddTovarPrice(foodPrice);
            System.out.println("Товар добавлен!");
            System.out.println( " Введите что угодно если хотите продолжить или \"завершить\" если вы добавили все товары!");
            stopInput = scanner.next();

            if (stopInput.equalsIgnoreCase("завершить")) {
                break;
            }
        }

        calculator.totalOutput(numberOfFriends);
    }
}
class Calculator //получает на вход название и цену товара, сохраняет названия и общую сумму товаров, выводит в нужном формате

{

    private String tovarName = "Добавленные товары:";
    private float totalPrice = 0f;

    public void AddTovarName(String foodName)//добавить товар
    {
        tovarName = "\n" + tovarName + "\n" + foodName;
    }

    public void AddTovarPrice(float foodPrice)//добавить цену на товар
    {
        totalPrice = totalPrice + foodPrice;
    }

    public void totalOutput(int numberOfFriends)//вывод результата
    {

        System.out.println(tovarName + "\n"); //выводим список всех товаров

        System.out.println("Общая сумма к оплате: " + String.format("%.2f", totalPrice) + rubFormat(Math.floor(totalPrice)));
        System.out.println("Каждый друг должен " + String.format("%.2f", (totalPrice / numberOfFriends)) + rubFormat(Math.floor(totalPrice / numberOfFriends)));

    }

    private String rubFormat(double totalSum) {

        String rubleView;

        int totalIntBaseSum = (int) totalSum;//базовое число, не меняется
        int totalIntSum = totalIntBaseSum;

        if (totalIntBaseSum >= 100) {
            totalIntSum = totalIntBaseSum % 100;
            if (totalIntSum > 20) {
                totalIntSum = totalIntSum % 10;
            }
        } else if (totalIntBaseSum > 20) {
            totalIntSum = totalIntBaseSum % 10;

        }

        if (totalIntSum < 1) {
            rubleView = " рублей!";
        } else if (totalIntSum == 1) {
            rubleView = " рубль!";
        } else if (totalIntSum < 5) {
            rubleView = " рубля!";
        } else {
            rubleView = " рублей!";
        }

        return (rubleView);

    }
}
