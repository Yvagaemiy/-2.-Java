
// Задание 2. Магазин игрушек (Java)
// Информация о проекте
// Необходимо написать проект, для розыгрыша в магазине игрушек. Функционал
// должен содержать добавление новых игрушек и задания веса для выпадения
// игрушек.
// В программе должен быть минимум один класс со следующими свойствами:

//  id игрушки,
//  имя игрушки,
//  количество игрушек,
//  частота выпадения игрушки (вес в % от 100)


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Main {
    public static void main(String[] args) {
        ToyStore toy_store = new ToyStore();

        Toy toy_1 = new Toy(1, "конструктор", 9, 40);
        toy_store.addToy(toy_1);

        Toy toy_2 = new Toy(2, "робот", 13, 35);
        toy_store.addToy(toy_2);

        Toy toy_3 = new Toy(3, "кукла", 5, 25);
        toy_store.addToy(toy_3);

        toy_store.showAvailableToys();

        toy_store.playToys();

        toy_store.showAvailableToys();
    }
}
// Класс Toy представляет игрушку с определенными свойствами, такими как ID, название, количество и вес.

class Toy {
    int id;
    String name;
    int quantity;
    double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Quantity: " + quantity + ", Weight: " + weight + "%";
    }
    
    public int getId() {
        return id;
      }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void decreaseQuantity() {
        quantity--;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
//  Класс ToyStore содержит список доступных игрушек и список призовых игрушек,
//   а также реализует методы добавления игрушек, обновления веса игрушки,
//   проведения розыгрыша игрушек и вывода доступных игрушек.
 
class ToyStore {
    private List<Toy> toys;
    private List<Toy> prize_toys;

    public ToyStore() {
        toys = new ArrayList<>();
        prize_toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int toy_id, double new_weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toy_id) {
                toy.setWeight(new_weight);
                break;
            }
        }
    }

    public void playToys() {
        for (Toy toy : toys) {
            int num_prizes = (int) ((toy.getWeight() / 100) * toy.getQuantity());
            for (int i = 0; i < num_prizes; i++) {
                prize_toys.add(toy);
            }
        }

        if (prize_toys.isEmpty()) {
            System.out.println("Нет игрушек для розыграша!");
            return;
        }

        Random random = new Random();
        Toy prize_toy = prize_toys.remove(random.nextInt(prize_toys.size()));
        prize_toy.decreaseQuantity();

        try {
            FileWriter writer = new FileWriter("Shop_list_toys.txt", true);
            writer.write(prize_toy.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("В этот раз призовая игрушка это: " + prize_toy.toString());
    }

    public void showAvailableToys() {
        if (toys.isEmpty()) {
            System.out.println("Игрушек для розыграша нет");
            return;
        }

        for (Toy toy : toys) {
            System.out.println(toy.toString());
        }
    }
}





