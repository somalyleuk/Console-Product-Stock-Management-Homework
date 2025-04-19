import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String[][] stock = new String[100][3]; // [name, quantity, price]
    static ArrayList<String> history = new ArrayList<>();
    static int productCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeStock();

        while (true) {
            System.out.println("\n=== Product Stock Management System ===");
            System.out.println("1. Set Up Stock with Catalogue");
            System.out.println("2. View Product in Stock");
            System.out.println("3. Insert Product to Stock Catalogue");
            System.out.println("4. Update Product in Stock Catalogue");
            System.out.println("5. Delete Product in Stock Catalogue");
            System.out.println("6. View Insertion History");
            System.out.println("7. Exit");
            System.out.print("Enter choice (1-7): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        setupStock(scanner);
                        break;
                    case 2:
                        viewStock();
                        break;
                    case 3:
                        insertProduct(scanner);
                        break;
                    case 4:
                        updateProduct(scanner);
                        break;
                    case 5:
                        deleteProduct(scanner);
                        break;
                    case 6:
                        viewHistory();
                        break;
                    case 7:
                        System.out.println("Exiting system...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    static void initializeStock() {
        // Initialize with some sample data
        stock[0] = new String[]{"Laptop", "10", "999.99"};
        stock[1] = new String[]{"Phone", "20", "599.99"};
        stock[2] = new String[]{"Tablet", "15", "299.99"};
        productCount = 3;
        history.add("Initial stock setup: Laptop, Phone, Tablet");
    }

    static void setupStock(Scanner scanner) {
        try {
            System.out.print("Enter number of products to set up: ");
            int num = scanner.nextInt();
            scanner.nextLine();

            if (num < 0) {
                System.out.println("Number of products cannot be negative!");
                return;
            }

            productCount = 0;
            for (int i = 0; i < num && productCount < stock.length; i++) {
                System.out.println("\nProduct " + (i + 1));
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                if (name.trim().isEmpty()) {
                    System.out.println("Product name cannot be empty!");
                    i--;
                    continue;
                }

                System.out.print("Enter quantity: ");
                String quantity = scanner.nextLine();
                try {
                    if (Integer.parseInt(quantity) < 0) {
                        System.out.println("Quantity cannot be negative!");
                        i--;
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity! Please enter a valid number.");
                    i--;
                    continue;
                }

                System.out.print("Enter price: ");
                String price = scanner.nextLine();
                try {
                    if (Double.parseDouble(price) < 0) {
                        System.out.println("Price cannot be negative!");
                        i--;
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price! Please enter a valid number.");
                    i--;
                    continue;
                }

                stock[productCount] = new String[]{name, quantity, price};
                history.add("Added: " + name + " (Qty: " + quantity + ", Price: " + price + ")");
                productCount++;
            }
            System.out.println("Stock setup completed!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    static void viewStock() {
        if (productCount == 0) {
            System.out.println("No products in stock!");
            return;
        }

        System.out.println("\n=== Stock Catalogue ===");
        System.out.printf("%-20s %-10s %-10s\n", "Name", "Quantity", "Price");
        System.out.println("------------------------------------");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("%-20s %-10s %-10s\n",
                    stock[i][0], stock[i][1], stock[i][2]);
        }
    }

    static void insertProduct(Scanner scanner) {
        try {
            if (productCount >= stock.length) {
                System.out.println("Stock is full!");
                return;
            }

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Product name cannot be empty!");
                return;
            }

            System.out.print("Enter quantity: ");
            String quantity = scanner.nextLine();
            try {
                if (Integer.parseInt(quantity) < 0) {
                    System.out.println("Quantity cannot be negative!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity! Please enter a valid number.");
                return;
            }

            System.out.print("Enter price: ");
            String price = scanner.nextLine();
            try {
                if (Double.parseDouble(price) < 0) {
                    System.out.println("Price cannot be negative!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price! Please enter a valid number.");
                return;
            }

            stock[productCount] = new String[]{name, quantity, price};
            history.add("Inserted: " + name + " (Qty: " + quantity + ", Price: " + price + ")");
            productCount++;
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    static void updateProduct(Scanner scanner) {
        try {
            System.out.print("Enter product name to update: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Product name cannot be empty!");
                return;
            }

            for (int i = 0; i < productCount; i++) {
                if (stock[i][0].equalsIgnoreCase(name)) {
                    System.out.print("Enter new quantity: ");
                    String quantity = scanner.nextLine();
                    try {
                        if (Integer.parseInt(quantity) < 0) {
                            System.out.println("Quantity cannot be negative!");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity! Please enter a valid number.");
                        return;
                    }

                    System.out.print("Enter new price: ");
                    String price = scanner.nextLine();
                    try {
                        if (Double.parseDouble(price) < 0) {
                            System.out.println("Price cannot be negative!");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price! Please enter a valid number.");
                        return;
                    }

                    stock[i][1] = quantity;
                    stock[i][2] = price;
                    history.add("Updated: " + name + " (Qty: " + quantity + ", Price: " + price + ")");
                    System.out.println("Product updated successfully!");
                    return;
                }
            }
            System.out.println("Product not found!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    static void deleteProduct(Scanner scanner) {
        try {
            System.out.print("Enter product name to delete: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Product name cannot be empty!");
                return;
            }

            for (int i = 0; i < productCount; i++) {
                if (stock[i][0].equalsIgnoreCase(name)) {
                    history.add("Deleted: " + stock[i][0] + " (Qty: " + stock[i][1] + ", Price: " + stock[i][2] + ")");
                    for (int j = i; j < productCount - 1; j++) {
                        stock[j] = stock[j + 1];
                    }
                    stock[productCount - 1] = null;
                    productCount--;
                    System.out.println("Product deleted successfully!");
                    return;
                }
            }
            System.out.println("Product not found!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    static void viewHistory() {
        if (history.isEmpty()) {
            System.out.println("No history available!");
            return;
        }

        System.out.println("\n=== Insertion History ===");
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
    }
}