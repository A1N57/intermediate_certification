import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Laptop {
    private String brand;
    private int ram; // ОЗУ в ГБ
    private int storage; // Объем ЖД в ГБ
    private String os; // Операционная система
    private String color;

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    // Основной метод
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 8, 512, "Windows 10", "Black"));
        laptops.add(new Laptop("Apple", 16, 256, "MacOS", "Silver"));
        laptops.add(new Laptop("HP", 8, 1024, "Windows 10", "Black"));
        laptops.add(new Laptop("Lenovo", 4, 512, "Windows 10", "Gray"));
        laptops.add(new Laptop("Asus", 16, 1024, "Windows 11", "Blue"));

        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap.put(1, "ram");
        criteriaMap.put(2, "storage");
        criteriaMap.put(3, "os");
        criteriaMap.put(4, "color");

        Scanner scanner = new Scanner(System.in);
        Map<String, String> filterCriteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        while (true) {
            System.out.print("Введите критерий (или 'q' для выхода): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            int criterion = Integer.parseInt(input);
            if (criteriaMap.containsKey(criterion)) {
                System.out.print("Введите минимальное значение для " + criteriaMap.get(criterion) + ": ");
                String value = scanner.nextLine();
                filterCriteria.put(criteriaMap.get(criterion), value);
            } else {
                System.out.println("Неверный критерий, попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, filterCriteria);

        System.out.println("Ноутбуки, соответствующие заданным критериям:");
        filteredLaptops.forEach(System.out::println);
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> criteria) {
        return laptops.stream().filter(laptop -> {
            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "ram":
                        if (laptop.getRam() < Integer.parseInt(value)) return false;
                        break;
                    case "storage":
                        if (laptop.getStorage() < Integer.parseInt(value)) return false;
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase(value)) return false;
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase(value)) return false;
                        break;
                }
            }
            return true;
        }).collect(Collectors.toSet());
    }
}