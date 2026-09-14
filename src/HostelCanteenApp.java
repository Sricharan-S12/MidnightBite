import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * 🌙 HOSTEL NIGHT CANTEEN FOOD ORDERING SYSTEM (STANDALONE SINGLE-FILE GUI)
 * 
 * How to Run:
 * 1. Save this file as: HostelCanteenApp.java
 * 2. Open Terminal / Command Prompt in the folder.
 * 3. Compile:  javac HostelCanteenApp.java
 * 4. Run:      java HostelCanteenApp
 */
public class HostelCanteenApp {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   🌙 HOSTEL NIGHT CANTEEN FOOD ORDER SYSTEM   ");
        System.out.println("=================================================");
        System.out.println("Starting application GUI...");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}

        // Apply custom dark theme colors to override system defaults
        applyDarkTheme();

        DataStore.getInstance();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
            System.out.println("GUI Interface Launched Successfully!");
            // Show login dialog after frame is visible
            frame.promptLoginDialog();
        });
    }

    private static void applyDarkTheme() {
        Color black = new Color(0, 0, 0);
        Color white = new Color(255, 255, 255);
        Color darkGray = new Color(50, 50, 50);
        Color mediumGray = new Color(150, 150, 150);

        // Global defaults - STRICT BLACK & WHITE
        UIManager.put("Panel.background", black);
        UIManager.put("Panel.foreground", white);
        UIManager.put("Label.foreground", white);
        UIManager.put("Label.background", black);
        UIManager.put("TextField.background", darkGray);
        UIManager.put("TextField.foreground", white);
        UIManager.put("TextField.caretForeground", white);
        UIManager.put("PasswordField.background", darkGray);
        UIManager.put("PasswordField.foreground", white);
        UIManager.put("PasswordField.caretForeground", white);
        UIManager.put("TextArea.background", darkGray);
        UIManager.put("TextArea.foreground", white);
        UIManager.put("TextArea.caretForeground", white);
        
        // Button styling
        UIManager.put("Button.background", white);
        UIManager.put("Button.foreground", black);
        UIManager.put("Button.focus", white);
        UIManager.put("Button.select", white);
        
        // TabbedPane styling
        UIManager.put("TabbedPane.background", black);
        UIManager.put("TabbedPane.foreground", white);
        UIManager.put("TabbedPane.tabBackground", darkGray);
        UIManager.put("TabbedPane.tabForeground", white);
        UIManager.put("TabbedPane.contentAreaColor", black);
        UIManager.put("TabbedPane.selected", black);
        UIManager.put("TabbedPane.selectedForeground", white);
        UIManager.put("TabbedPane.focus", white);
        UIManager.put("TabbedPane.highlight", mediumGray);
        
        // ComboBox styling
        UIManager.put("ComboBox.background", darkGray);
        UIManager.put("ComboBox.foreground", white);
        UIManager.put("ComboBox.selectionBackground", white);
        UIManager.put("ComboBox.selectionForeground", black);
        
        // Table styling
        UIManager.put("Table.background", darkGray);
        UIManager.put("Table.foreground", white);
        UIManager.put("Table.selectionBackground", white);
        UIManager.put("Table.selectionForeground", black);
        UIManager.put("Table.gridColor", black);
        UIManager.put("TableHeader.background", black);
        UIManager.put("TableHeader.foreground", white);
        
        // ScrollPane styling
        UIManager.put("ScrollPane.background", black);
        UIManager.put("ScrollPane.foreground", white);
        UIManager.put("ScrollBar.background", darkGray);
        UIManager.put("ScrollBar.foreground", mediumGray);
        UIManager.put("ScrollBar.thumb", mediumGray);
        UIManager.put("ScrollBar.thumbHighlight", white);
        
        // Dialog and window styling
        UIManager.put("Dialog.background", black);
        UIManager.put("Dialog.foreground", white);
        UIManager.put("Window.background", black);
        UIManager.put("Window.foreground", white);
        
        // Menu styling
        UIManager.put("Menu.foreground", white);
        UIManager.put("Menu.background", darkGray);
        UIManager.put("MenuItem.foreground", white);
        UIManager.put("MenuItem.background", darkGray);
        UIManager.put("MenuItem.selectionBackground", white);
        UIManager.put("MenuItem.selectionForeground", black);
        
        // CheckBox and RadioButton
        UIManager.put("CheckBox.foreground", white);
        UIManager.put("CheckBox.background", black);
        UIManager.put("RadioButton.foreground", white);
        UIManager.put("RadioButton.background", black);
    }
}

// =========================================================
// 1. DATA MODEL ENTITIES
// =========================================================

class User {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String rollNo;
    private String hostelBlock;
    private String roomNo;
    private String phone;
    private String role; // "STUDENT" or "ADMIN"

    public User() {}

    public User(String id, String username, String password, String fullName, String rollNo, 
                String hostelBlock, String roomNo, String phone, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.rollNo = rollNo;
        this.hostelBlock = hostelBlock;
        this.roomNo = roomNo;
        this.phone = phone;
        this.role = role;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public String getHostelBlock() { return hostelBlock; }
    public void setHostelBlock(String hostelBlock) { this.hostelBlock = hostelBlock; }
    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }
}

class MenuItem {
    private String id;
    private String name;
    private String category;
    private double price;
    private String description;
    private boolean isVegetarian;
    private boolean isAvailable;
    private String emoji;

    public MenuItem() {}

    public MenuItem(String id, String name, String category, double price, String description, 
                    boolean isVegetarian, boolean isAvailable, String emoji) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.isAvailable = isAvailable;
        this.emoji = emoji;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isVegetarian() { return isVegetarian; }
    public void setVegetarian(boolean vegetarian) { isVegetarian = vegetarian; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public String getEmoji() { return emoji != null ? emoji : "🍔"; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
}

class OrderItem {
    private String menuItemId;
    private String itemName;
    private double unitPrice;
    private int quantity;
    private String specialNotes;

    public OrderItem() {}

    public OrderItem(String menuItemId, String itemName, double unitPrice, int quantity, String specialNotes) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.specialNotes = specialNotes != null ? specialNotes : "";
    }

    public String getMenuItemId() { return menuItemId; }
    public void setMenuItemId(String menuItemId) { this.menuItemId = menuItemId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getSpecialNotes() { return specialNotes; }
    public void setSpecialNotes(String specialNotes) { this.specialNotes = specialNotes; }

    public double getTotalPrice() { return unitPrice * quantity; }
}

class Order {
    private String orderId;
    private String studentId;
    private String studentName;
    private String rollNo;
    private String hostelBlock;
    private String roomNo;
    private String phone;
    private List<OrderItem> items = new ArrayList<>();
    private double totalAmount;
    private String paymentMethod;
    private String status;
    private String orderTime;

    public Order() {}

    public Order(String orderId, String studentId, String studentName, String rollNo, 
                 String hostelBlock, String roomNo, String phone, List<OrderItem> items, 
                 double totalAmount, String paymentMethod, String status, String orderTime) {
        this.orderId = orderId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.hostelBlock = hostelBlock;
        this.roomNo = roomNo;
        this.phone = phone;
        this.items = items != null ? items : new ArrayList<>();
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.orderTime = orderTime;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public String getHostelBlock() { return hostelBlock; }
    public void setHostelBlock(String hostelBlock) { this.hostelBlock = hostelBlock; }
    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getOrderTime() { return orderTime; }
    public void setOrderTime(String orderTime) { this.orderTime = orderTime; }

    public String getDeliveryAddress() { return hostelBlock + " - Room " + roomNo; }
}

// =========================================================
// 2. JSON UTILITY & FILE PERSISTENCE
// =========================================================

class SimpleJson {
    public static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }

    public static String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\\"", "\"").replace("\\n", "\n").replace("\\r", "\r").replace("\\\\", "\\");
    }

    public static String userToJson(User u) {
        return String.format(
            "{\"id\":\"%s\",\"username\":\"%s\",\"password\":\"%s\",\"fullName\":\"%s\",\"rollNo\":\"%s\",\"hostelBlock\":\"%s\",\"roomNo\":\"%s\",\"phone\":\"%s\",\"role\":\"%s\"}",
            escape(u.getId()), escape(u.getUsername()), escape(u.getPassword()), escape(u.getFullName()),
            escape(u.getRollNo()), escape(u.getHostelBlock()), escape(u.getRoomNo()), escape(u.getPhone()), escape(u.getRole())
        );
    }

    public static String usersListToJson(List<User> users) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < users.size(); i++) {
            sb.append("  ").append(userToJson(users.get(i)));
            if (i < users.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    public static List<User> jsonToUsersList(String json) {
        List<User> list = new ArrayList<>();
        if (json == null || json.trim().isEmpty()) return list;
        List<Map<String, String>> objects = parseJsonArrayOfObjects(json);
        for (Map<String, String> m : objects) {
            list.add(new User(
                m.getOrDefault("id", ""), m.getOrDefault("username", ""), m.getOrDefault("password", ""),
                m.getOrDefault("fullName", ""), m.getOrDefault("rollNo", ""), m.getOrDefault("hostelBlock", ""),
                m.getOrDefault("roomNo", ""), m.getOrDefault("phone", ""), m.getOrDefault("role", "STUDENT")
            ));
        }
        return list;
    }

    public static String menuItemToJson(MenuItem item) {
        return String.format(
            "{\"id\":\"%s\",\"name\":\"%s\",\"category\":\"%s\",\"price\":%.2f,\"description\":\"%s\",\"isVegetarian\":%b,\"isAvailable\":%b,\"emoji\":\"%s\"}",
            escape(item.getId()), escape(item.getName()), escape(item.getCategory()), item.getPrice(),
            escape(item.getDescription()), item.isVegetarian(), item.isAvailable(), escape(item.getEmoji())
        );
    }

    public static String menuListToJson(List<MenuItem> items) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append("  ").append(menuItemToJson(items.get(i)));
            if (i < items.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    public static List<MenuItem> jsonToMenuList(String json) {
        List<MenuItem> list = new ArrayList<>();
        if (json == null || json.trim().isEmpty()) return list;
        List<Map<String, String>> objects = parseJsonArrayOfObjects(json);
        for (Map<String, String> m : objects) {
            list.add(new MenuItem(
                m.getOrDefault("id", ""), m.getOrDefault("name", ""), m.getOrDefault("category", "Snacks"),
                parseDouble(m.get("price")), m.getOrDefault("description", ""),
                parseBool(m.get("isVegetarian")), parseBool(m.get("isAvailable")), m.getOrDefault("emoji", "🍔")
            ));
        }
        return list;
    }

    public static String orderItemToJson(OrderItem item) {
        return String.format(
            "{\"menuItemId\":\"%s\",\"itemName\":\"%s\",\"unitPrice\":%.2f,\"quantity\":%d,\"specialNotes\":\"%s\"}",
            escape(item.getMenuItemId()), escape(item.getItemName()), item.getUnitPrice(), item.getQuantity(), escape(item.getSpecialNotes())
        );
    }

    public static String orderToJson(Order o) {
        StringBuilder itemsJson = new StringBuilder("[");
        for (int i = 0; i < o.getItems().size(); i++) {
            itemsJson.append(orderItemToJson(o.getItems().get(i)));
            if (i < o.getItems().size() - 1) itemsJson.append(",");
        }
        itemsJson.append("]");

        return String.format(
            "{\"orderId\":\"%s\",\"studentId\":\"%s\",\"studentName\":\"%s\",\"rollNo\":\"%s\",\"hostelBlock\":\"%s\",\"roomNo\":\"%s\",\"phone\":\"%s\",\"items\":%s,\"totalAmount\":%.2f,\"paymentMethod\":\"%s\",\"status\":\"%s\",\"orderTime\":\"%s\"}",
            escape(o.getOrderId()), escape(o.getStudentId()), escape(o.getStudentName()), escape(o.getRollNo()),
            escape(o.getHostelBlock()), escape(o.getRoomNo()), escape(o.getPhone()), itemsJson.toString(),
            o.getTotalAmount(), escape(o.getPaymentMethod()), escape(o.getStatus()), escape(o.getOrderTime())
        );
    }

    public static String ordersListToJson(List<Order> orders) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < orders.size(); i++) {
            sb.append("  ").append(orderToJson(orders.get(i)));
            if (i < orders.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    public static List<Order> jsonToOrdersList(String json) {
        List<Order> list = new ArrayList<>();
        if (json == null || json.trim().isEmpty()) return list;

        List<String> rawObjects = extractTopLevelObjects(json);
        for (String raw : rawObjects) {
            Map<String, String> map = parseSingleObjectFlat(raw);
            String itemsArrayStr = extractArrayContent(raw, "items");
            List<OrderItem> items = new ArrayList<>();
            if (itemsArrayStr != null && !itemsArrayStr.isEmpty()) {
                List<Map<String, String>> itemMaps = parseJsonArrayOfObjects(itemsArrayStr);
                for (Map<String, String> im : itemMaps) {
                    items.add(new OrderItem(
                        im.getOrDefault("menuItemId", ""), im.getOrDefault("itemName", ""),
                        parseDouble(im.get("unitPrice")), parseInt(im.get("quantity")), im.getOrDefault("specialNotes", "")
                    ));
                }
            }

            list.add(new Order(
                map.getOrDefault("orderId", ""), map.getOrDefault("studentId", ""), map.getOrDefault("studentName", ""),
                map.getOrDefault("rollNo", ""), map.getOrDefault("hostelBlock", ""), map.getOrDefault("roomNo", ""),
                map.getOrDefault("phone", ""), items, parseDouble(map.get("totalAmount")),
                map.getOrDefault("paymentMethod", "UPI"), map.getOrDefault("status", "PLACED"), map.getOrDefault("orderTime", "")
            ));
        }
        return list;
    }

    private static List<Map<String, String>> parseJsonArrayOfObjects(String json) {
        List<Map<String, String>> list = new ArrayList<>();
        List<String> objectStrs = extractTopLevelObjects(json);
        for (String objStr : objectStrs) {
            list.add(parseSingleObjectFlat(objStr));
        }
        return list;
    }

    private static List<String> extractTopLevelObjects(String json) {
        List<String> results = new ArrayList<>();
        if (json == null) return results;
        int depth = 0, start = -1;
        boolean inString = false, escapeNext = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escapeNext) { escapeNext = false; continue; }
            if (c == '\\') { escapeNext = true; continue; }
            if (c == '"') { inString = !inString; continue; }
            if (!inString) {
                if (c == '{') {
                    if (depth == 0) start = i;
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0 && start != -1) {
                        results.add(json.substring(start, i + 1));
                        start = -1;
                    }
                }
            }
        }
        return results;
    }

    private static Map<String, String> parseSingleObjectFlat(String json) {
        Map<String, String> map = new HashMap<>();
        if (json == null) return map;
        String trimmed = json.trim();
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            trimmed = trimmed.substring(1, trimmed.length() - 1);
        }

        boolean inString = false, escapeNext = false, buildingKey = true, insideValue = false;
        StringBuilder keySb = new StringBuilder(), valueSb = new StringBuilder();
        int len = trimmed.length(), i = 0;

        while (i < len) {
            char c = trimmed.charAt(i);
            if (escapeNext) {
                if (buildingKey) keySb.append(c); else valueSb.append(c);
                escapeNext = false; i++; continue;
            }
            if (c == '\\') { escapeNext = true; i++; continue; }
            if (c == '"') { inString = !inString; i++; continue; }

            if (!inString) {
                if (c == ':') { buildingKey = false; insideValue = true; i++; continue; }
                else if (c == ',' || i == len - 1) {
                    if (i == len - 1 && c != '}' && c != ']') {
                        if (!buildingKey && insideValue) valueSb.append(c);
                    }
                    String key = keySb.toString().trim();
                    String val = valueSb.toString().trim();
                    if (!key.isEmpty()) map.put(key, unescape(val));
                    keySb.setLength(0); valueSb.setLength(0);
                    buildingKey = true; insideValue = false; i++; continue;
                } else if (c == '[' || c == '{') {
                    int subDepth = 1; char open = c, close = (c == '[') ? ']' : '}';
                    int startSub = i; i++;
                    while (i < len && subDepth > 0) {
                        char subC = trimmed.charAt(i);
                        if (subC == open) subDepth++;
                        else if (subC == close) subDepth--;
                        i++;
                    }
                    if (!buildingKey) valueSb.append(trimmed, startSub, i);
                    continue;
                }
            }
            if (buildingKey) keySb.append(c); else valueSb.append(c);
            i++;
        }
        if (keySb.length() > 0) map.put(keySb.toString().trim(), unescape(valueSb.toString().trim()));
        return map;
    }

    private static String extractArrayContent(String rawObjectJson, String key) {
        String search = "\"" + key + "\":";
        int idx = rawObjectJson.indexOf(search);
        if (idx == -1) return null;
        int startArr = rawObjectJson.indexOf('[', idx);
        if (startArr == -1) return null;
        int depth = 0;
        for (int i = startArr; i < rawObjectJson.length(); i++) {
            char c = rawObjectJson.charAt(i);
            if (c == '[') depth++;
            else if (c == ']') {
                depth--;
                if (depth == 0) return rawObjectJson.substring(startArr, i + 1);
            }
        }
        return null;
    }

    private static double parseDouble(String str) {
        try { return str != null ? Double.parseDouble(str) : 0.0; } catch (NumberFormatException e) { return 0.0; }
    }
    private static int parseInt(String str) {
        try { return str != null ? Integer.parseInt(str) : 0; } catch (NumberFormatException e) { return 0; }
    }
    private static boolean parseBool(String str) {
        return str != null && "true".equalsIgnoreCase(str.trim());
    }
}

class DataStore {
    private static DataStore instance;
    private final File dataDir;
    private final File usersFile, menuFile, ordersFile;

    private List<User> users = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    private DataStore() {
        dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdirs();
        usersFile = new File(dataDir, "users.json");
        menuFile = new File(dataDir, "menu.json");
        ordersFile = new File(dataDir, "orders.json");
        this.loadData();
    }
    
    private void loadData() {
        loadAllData();
    }

    public static synchronized DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }

    public synchronized void loadAllData() {
        if (!usersFile.exists()) { users = createDefaultUsers(); saveUsers(); }
        else { users = SimpleJson.jsonToUsersList(readFile(usersFile)); if (users.isEmpty()) { users = createDefaultUsers(); saveUsers(); } }

        if (!menuFile.exists()) { menuItems = createDefaultMenu(); saveMenu(); }
        else { menuItems = SimpleJson.jsonToMenuList(readFile(menuFile)); if (menuItems.isEmpty()) { menuItems = createDefaultMenu(); saveMenu(); } }

        if (!ordersFile.exists()) { orders = new ArrayList<>(); saveOrders(); }
        else { orders = SimpleJson.jsonToOrdersList(readFile(ordersFile)); }
    }

    public synchronized void saveUsers() { writeFile(usersFile, SimpleJson.usersListToJson(users)); }
    public synchronized void saveMenu() { writeFile(menuFile, SimpleJson.menuListToJson(menuItems)); }
    public synchronized void saveOrders() { writeFile(ordersFile, SimpleJson.ordersListToJson(orders)); }

    public synchronized List<User> getUsers() { return new ArrayList<>(users); }
    public synchronized List<MenuItem> getMenuItems() { return new ArrayList<>(menuItems); }
    public synchronized List<Order> getOrders() { return new ArrayList<>(orders); }

    public synchronized void addUser(User u) { users.add(u); saveUsers(); }
    public synchronized void addMenuItem(MenuItem mi) { menuItems.add(mi); saveMenu(); }
    public synchronized void updateMenuItem(MenuItem mi) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getId().equals(mi.getId())) { menuItems.set(i, mi); break; }
        }
        saveMenu();
    }
    public synchronized void deleteMenuItem(String id) { menuItems.removeIf(m -> m.getId().equals(id)); saveMenu(); }
    public synchronized void addOrder(Order o) { orders.add(0, o); saveOrders(); }
    public synchronized void updateOrder(Order o) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(o.getOrderId())) { orders.set(i, o); break; }
        }
        saveOrders();
    }

    private List<User> createDefaultUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User("U-100", "admin", "admin123", "Canteen Manager", "STAFF-01", "Canteen Office", "G-01", "9876543210", "ADMIN"));
        list.add(new User("U-101", "student", "student123", "Rahul Sharma", "21CS045", "Block A", "304", "9812345678", "STUDENT"));
        list.add(new User("U-102", "priya", "priya123", "Priya Verma", "21EC089", "Block C", "108", "9898765432", "STUDENT"));
        return list;
    }

    private List<MenuItem> createDefaultMenu() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem("M-01", "Butter Cheese Maggi", "Maggi & Noodles", 70.0, "Classic 2-min Maggi loaded with Amul butter & melted cheese", true, true, "🍜"));
        list.add(new MenuItem("M-02", "Schezwan Egg Noodles", "Maggi & Noodles", 90.0, "Spicy wok-fried noodles with farm eggs & Schezwan sauce", false, true, "🍝"));
        list.add(new MenuItem("M-03", "Paneer Tikka Grilled Sandwich", "Sandwiches & Rolls", 110.0, "Triple layer toasted sandwich stuffed with spiced cottage cheese", true, true, "🥪"));
        list.add(new MenuItem("M-04", "Chicken Roll Special", "Sandwiches & Rolls", 120.0, "Juicy grilled chicken wrapped in crispy katha paratha", false, true, "🌯"));
        list.add(new MenuItem("M-05", "Midnight Combo Deal", "Late Night Specials", 160.0, "1x Butter Maggi + 1x Cold Coffee + 1x Veg Sandwich", true, true, "🍱"));
        list.add(new MenuItem("M-06", "Aloo Paratha (2 Pcs)", "Snacks", 80.0, "Served hot with curd, butter cube & spicy pickle", true, true, "🫓"));
        list.add(new MenuItem("M-07", "Thick Cold Coffee w/ Ice Cream", "Beverages", 75.0, "Creamy blended cold coffee topped with vanilla scoop", true, true, "🧋"));
        list.add(new MenuItem("M-08", "Hot Chocolate Fudge", "Desserts", 95.0, "Rich hot chocolate syrup poured over scoops of vanilla ice cream", true, true, "🍨"));
        list.add(new MenuItem("M-09", "Chai (Kulhad Special)", "Beverages", 25.0, "Steaming hot ginger cardamom tea served in traditional mud cup", true, true, "☕"));
        list.add(new MenuItem("M-10", "French Fries (Peri Peri)", "Snacks", 85.0, "Crispy golden french fries tossed in spicy Peri-Peri seasoning", true, true, "🍟"));
        return list;
    }

    private String readFile(File f) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            String l; while ((l = br.readLine()) != null) sb.append(l).append("\n");
        } catch (IOException ignored) {}
        return sb.toString();
    }

    private void writeFile(File f, String c) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8))) {
            bw.write(c);
        } catch (IOException ignored) {}
    }
}

// =========================================================
// 3. SERVICES
// =========================================================

class AuthService {
    private static AuthService instance;
    private final DataStore dataStore = DataStore.getInstance();
    private User currentUser;

    public static synchronized AuthService getInstance() {
        if (instance == null) instance = new AuthService();
        return instance;
    }
    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User u) { this.currentUser = u; }

    public User login(String username, String password) {
        if (username == null || password == null) return null;
        for (User u : dataStore.getUsers()) {
            if (u.getUsername().equalsIgnoreCase(username.trim()) && u.getPassword().equals(password)) {
                this.currentUser = u;
                return u;
            }
        }
        return null;
    }

    public User registerStudent(String username, String password, String fullName, 
                                String rollNo, String hostelBlock, String roomNo, String phone) throws Exception {
        if (username == null || username.trim().isEmpty()) throw new Exception("Username cannot be empty");
        if (password == null || password.trim().isEmpty()) throw new Exception("Password cannot be empty");

        for (User u : dataStore.getUsers()) {
            if (u.getUsername().equalsIgnoreCase(username.trim())) {
                throw new Exception("Username already taken! Choose another.");
            }
        }

        String id = "U-" + UUID.randomUUID().toString().substring(0, 8);
        User u = new User(id, username.trim(), password, fullName, rollNo, hostelBlock, roomNo, phone, "STUDENT");
        dataStore.addUser(u);
        this.currentUser = u;
        return u;
    }
}

class MenuService {
    private static MenuService instance;
    private final DataStore dataStore = DataStore.getInstance();

    public static synchronized MenuService getInstance() {
        if (instance == null) instance = new MenuService();
        return instance;
    }

    public List<MenuItem> getAllMenuItems() { return dataStore.getMenuItems(); }

    public List<MenuItem> filterMenuItems(String category, String searchQuery, Boolean vegOnly) {
        List<MenuItem> all = dataStore.getMenuItems();
        List<MenuItem> filtered = new ArrayList<>();
        for (MenuItem item : all) {
            if (category != null && !category.equalsIgnoreCase("ALL") && !category.equalsIgnoreCase("All Categories")) {
                String itemCat = item.getCategory();
                if (itemCat == null || !itemCat.equalsIgnoreCase(category)) continue;
            }
            if (vegOnly != null && vegOnly && !item.isVegetarian()) continue;
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String q = searchQuery.toLowerCase().trim();
                boolean m1 = item.getName() != null && item.getName().toLowerCase().contains(q);
                boolean m2 = item.getDescription() != null && item.getDescription().toLowerCase().contains(q);
                if (!m1 && !m2) continue;
            }
            filtered.add(item);
        }
        return filtered;
    }

    public MenuItem getMenuItemById(String id) {
        for (MenuItem mi : dataStore.getMenuItems()) if (mi.getId().equals(id)) return mi;
        return null;
    }

    public void addMenuItem(String name, String category, double price, String description, 
                            boolean isVegetarian, boolean isAvailable, String emoji) throws Exception {
        if (name == null || name.trim().isEmpty()) throw new Exception("Item name cannot be empty");
        if (price <= 0) throw new Exception("Price must be greater than 0");

        String id = "M-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        dataStore.addMenuItem(new MenuItem(id, name.trim(), category, price, description, isVegetarian, isAvailable, emoji));
    }

    public void toggleAvailability(String itemId) {
        MenuItem item = getMenuItemById(itemId);
        if (item != null) {
            item.setAvailable(!item.isAvailable());
            dataStore.updateMenuItem(item);
        }
    }

    public void deleteMenuItem(String itemId) {
        dataStore.deleteMenuItem(itemId);
    }
}

class OrderService {
    private static OrderService instance;
    private final DataStore dataStore = DataStore.getInstance();

    public static synchronized OrderService getInstance() {
        if (instance == null) instance = new OrderService();
        return instance;
    }

    public Order createOrder(User student, List<OrderItem> items, String paymentMethod, String overrideBlock, String overrideRoom) throws Exception {
        if (student == null) throw new Exception("Please log in to place an order");
        if (items == null || items.isEmpty()) throw new Exception("Cart is empty!");

        double total = 0.0;
        for (OrderItem item : items) total += item.getTotalPrice();

        String block = (overrideBlock != null && !overrideBlock.trim().isEmpty()) ? overrideBlock : student.getHostelBlock();
        String room = (overrideRoom != null && !overrideRoom.trim().isEmpty()) ? overrideRoom : student.getRoomNo();

        String orderId = "ORD-" + (1000 + new Random().nextInt(9000));
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a"));

        Order order = new Order(orderId, student.getId(), student.getFullName(), student.getRollNo(), block, room, student.getPhone(), items, total, paymentMethod, "PLACED", timeStr);
        dataStore.addOrder(order);
        return order;
    }

    public List<Order> getOrdersForStudent(String studentId) {
        List<Order> result = new ArrayList<>();
        for (Order o : dataStore.getOrders()) if (o.getStudentId().equalsIgnoreCase(studentId)) result.add(o);
        return result;
    }

    public List<Order> getAllOrders() { return dataStore.getOrders(); }

    public void updateOrderStatus(String orderId, String newStatus) {
        for (Order o : dataStore.getOrders()) {
            if (o.getOrderId().equals(orderId)) {
                o.setStatus(newStatus);
                dataStore.updateOrder(o);
                break;
            }
        }
    }

    public double getTotalRevenue() {
        double r = 0;
        for (Order o : dataStore.getOrders()) if (!"CANCELLED".equalsIgnoreCase(o.getStatus())) r += o.getTotalAmount();
        return r;
    }

    public int getActiveOrdersCount() {
        int c = 0;
        for (Order o : dataStore.getOrders()) {
            if ("PLACED".equalsIgnoreCase(o.getStatus()) || "PREPARING".equalsIgnoreCase(o.getStatus()) || "OUT_FOR_DELIVERY".equalsIgnoreCase(o.getStatus())) c++;
        }
        return c;
    }
}

// =========================================================
// 4. SWING UI THEME & COMPONENTS
// =========================================================

class UITheme {
    // STRICT BLACK & WHITE THEME
    public static final Color BG_DARK = new Color(0, 0, 0);           // Pure black
    public static final Color BG_CARD = new Color(30, 30, 30);        // Dark gray
    public static final Color BG_INPUT = new Color(50, 50, 50);       // Darker gray
    public static final Color BG_HEADER = new Color(0, 0, 0);         // Pure black

    public static final Color ACCENT_AMBER = new Color(255, 255, 255);      // White
    public static final Color ACCENT_ORANGE = new Color(255, 255, 255);     // White

    public static final Color TEXT_PRIMARY = new Color(255, 255, 255);      // Pure white
    public static final Color TEXT_SECONDARY = new Color(200, 200, 200);    // Light gray
    public static final Color TEXT_MUTED = new Color(150, 150, 150);        // Medium gray

    public static final Color STATUS_PLACED = new Color(255, 255, 0);       // Yellow
    public static final Color STATUS_PREPARING = new Color(0, 150, 255);    // Blue
    public static final Color STATUS_DELIVERY = new Color(255, 150, 0);     // Orange
    public static final Color STATUS_DELIVERED = new Color(0, 255, 0);      // Green
    public static final Color STATUS_CANCELLED = new Color(255, 0, 0);      // Red

    public static final Font FONT_HEADER_LARGE = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_HEADER_MEDIUM = new Font("Segoe UI", Font.BOLD, 17);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_BODY_BOLD = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);

    public static JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_BODY_BOLD);
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setBorderPainted(true);
        btn.setBorder(new EmptyBorder(8, 16, 8, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static JButton createSecondaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_BODY_BOLD);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(50, 50, 50));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setBorderPainted(true);
        btn.setBorder(new EmptyBorder(8, 16, 8, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static JTextField createTextField(int columns) {
        JTextField tf = new JTextField(columns);
        tf.setFont(FONT_BODY);
        tf.setForeground(TEXT_PRIMARY);
        tf.setBackground(BG_INPUT);
        tf.setCaretColor(TEXT_PRIMARY);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(71, 85, 105), 1),
            new EmptyBorder(6, 10, 6, 10)
        ));
        return tf;
    }

    public static JPasswordField createPasswordField(int columns) {
        JPasswordField pf = new JPasswordField(columns);
        pf.setFont(FONT_BODY);
        pf.setForeground(TEXT_PRIMARY);
        pf.setBackground(BG_INPUT);
        pf.setCaretColor(TEXT_PRIMARY);
        pf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(71, 85, 105), 1),
            new EmptyBorder(6, 10, 6, 10)
        ));
        return pf;
    }

    public static JLabel createLabel(String text, Font font, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(color);
        return lbl;
    }

    public static JPanel createCardPanel() {
        JPanel p = new JPanel();
        p.setBackground(BG_CARD);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 65, 85), 1),
            new EmptyBorder(12, 12, 12, 12)
        ));
        return p;
    }
}

class StatusBadge extends JLabel {
    public StatusBadge(String status) {
        super(formatText(status), SwingConstants.CENTER);
        setFont(UITheme.FONT_BODY_BOLD);
        setOpaque(true);
        setBorder(new EmptyBorder(4, 10, 4, 10));
        this.applyStatus(status);
    }
    
    private void applyStatus(String status) {
        setStatus(status);
    }

    private static String formatText(String status) {
        return status != null ? status.replace("_", " ").toUpperCase() : "UNKNOWN";
    }

    public void setStatus(String status) {
        setText(formatText(status));
        if (status == null) return;
        switch (status.toUpperCase()) {
            case "PLACED" -> { setBackground(new Color(234, 179, 8, 40)); setForeground(UITheme.STATUS_PLACED); }
            case "PREPARING" -> { setBackground(new Color(59, 130, 246, 40)); setForeground(UITheme.STATUS_PREPARING); }
            case "OUT_FOR_DELIVERY", "OUT FOR DELIVERY" -> { setBackground(new Color(249, 115, 22, 40)); setForeground(UITheme.STATUS_DELIVERY); }
            case "DELIVERED" -> { setBackground(new Color(16, 185, 129, 40)); setForeground(UITheme.STATUS_DELIVERED); }
            case "CANCELLED" -> { setBackground(new Color(239, 68, 68, 40)); setForeground(UITheme.STATUS_CANCELLED); }
            default -> { setBackground(UITheme.BG_INPUT); setForeground(UITheme.TEXT_SECONDARY); }
        }
    }
}

class FoodCard extends JPanel {
    public interface OnCartQuantityChangeListener {
        void onQuantityChanged(MenuItem item, int newQuantity);
    }

    private final MenuItem item;
    private int currentQuantity = 0;
    private final OnCartQuantityChangeListener listener;
    private final JLabel qtyLabel;
    private final JPanel actionContainer;

    public FoodCard(MenuItem item, int initialQty, OnCartQuantityChangeListener listener) {
        this.item = item;
        this.currentQuantity = initialQty;
        this.listener = listener;
        
        // Explicitly reference fields to satisfy IDE analysis
        MenuItem itemRef = this.item;
        OnCartQuantityChangeListener listenerRef = this.listener;

        setLayout(new BorderLayout(10, 10));
        setBackground(UITheme.BG_CARD);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 65, 85), 1),
            new EmptyBorder(12, 12, 12, 12)
        ));
        setPreferredSize(new Dimension(260, 210));

        JPanel topPanel = new JPanel(new BorderLayout(8, 0));
        topPanel.setOpaque(false);

        JLabel emojiLbl = new JLabel(itemRef.getEmoji());
        emojiLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));

        JPanel titleBox = new JPanel(new GridLayout(2, 1, 0, 2));
        titleBox.setOpaque(false);

        JLabel nameLbl = new JLabel(itemRef.getName());
        nameLbl.setFont(UITheme.FONT_SUBTITLE);
        nameLbl.setForeground(UITheme.TEXT_PRIMARY);

        JLabel catLbl = new JLabel(itemRef.getCategory());
        catLbl.setFont(UITheme.FONT_SMALL);
        catLbl.setForeground(UITheme.TEXT_MUTED);

        titleBox.add(nameLbl); titleBox.add(catLbl);

        JLabel vegBadge = new JLabel(itemRef.isVegetarian() ? "🟢 VEG" : "🔴 NON-VEG");
        vegBadge.setFont(UITheme.FONT_SMALL);
        vegBadge.setForeground(itemRef.isVegetarian() ? UITheme.STATUS_DELIVERED : UITheme.STATUS_CANCELLED);

        topPanel.add(emojiLbl, BorderLayout.WEST);
        topPanel.add(titleBox, BorderLayout.CENTER);
        topPanel.add(vegBadge, BorderLayout.EAST);

        JTextArea descArea = new JTextArea(itemRef.getDescription());
        descArea.setFont(UITheme.FONT_BODY);
        descArea.setForeground(UITheme.TEXT_SECONDARY);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setOpaque(false);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JLabel priceLbl = new JLabel(String.format("₹%.0f", itemRef.getPrice()));
        priceLbl.setFont(UITheme.FONT_HEADER_MEDIUM);
        priceLbl.setForeground(UITheme.ACCENT_AMBER);

        actionContainer = new JPanel(new CardLayout());
        actionContainer.setOpaque(false);

        JButton addBtn = UITheme.createPrimaryButton("+ Add");
        addBtn.addActionListener(e -> {
            if (!itemRef.isAvailable()) {
                JOptionPane.showMessageDialog(this, "Item is out of stock!", "Out of Stock", JOptionPane.WARNING_MESSAGE);
                return;
            }
            currentQuantity = 1;
            updateActionState();
            if (listenerRef != null) listenerRef.onQuantityChanged(itemRef, currentQuantity);
        });

        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        qtyPanel.setOpaque(false);

        JButton minusBtn = new JButton("-");
        minusBtn.setFont(UITheme.FONT_BODY_BOLD);
        minusBtn.setForeground(Color.WHITE);
        minusBtn.setBackground(UITheme.BG_INPUT);
        minusBtn.setFocusPainted(false);

        qtyLabel = new JLabel(String.valueOf(currentQuantity), SwingConstants.CENTER);
        qtyLabel.setFont(UITheme.FONT_BODY_BOLD);
        qtyLabel.setForeground(UITheme.TEXT_PRIMARY);
        qtyLabel.setPreferredSize(new Dimension(24, 24));

        JButton plusBtn = new JButton("+");
        plusBtn.setFont(UITheme.FONT_BODY_BOLD);
        plusBtn.setForeground(Color.WHITE);
        plusBtn.setBackground(UITheme.ACCENT_ORANGE);
        plusBtn.setFocusPainted(false);

        minusBtn.addActionListener(e -> {
            if (currentQuantity > 0) {
                currentQuantity--;
                updateActionState();
                if (listenerRef != null) listenerRef.onQuantityChanged(itemRef, currentQuantity);
            }
        });

        plusBtn.addActionListener(e -> {
            currentQuantity++;
            updateActionState();
            if (listenerRef != null) listenerRef.onQuantityChanged(itemRef, currentQuantity);
        });

        qtyPanel.add(minusBtn); qtyPanel.add(qtyLabel); qtyPanel.add(plusBtn);

        actionContainer.add(addBtn, "ADD");
        actionContainer.add(qtyPanel, "QTY");

        bottomPanel.add(priceLbl, BorderLayout.WEST);
        bottomPanel.add(actionContainer, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(descArea, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        if (!itemRef.isAvailable()) {
            addBtn.setText("Sold Out");
            addBtn.setEnabled(false);
            addBtn.setBackground(UITheme.BG_INPUT);
        } else {
            updateActionState();
        }
    }

    private void updateActionState() {
        CardLayout cl = (CardLayout) actionContainer.getLayout();
        if (currentQuantity > 0) {
            qtyLabel.setText(String.valueOf(currentQuantity));
            cl.show(actionContainer, "QTY");
        } else {
            cl.show(actionContainer, "ADD");
        }
        revalidate(); repaint();
    }
}

// =========================================================
// 5. AUTH DIALOG & PANELS
// =========================================================

class AuthDialog extends JDialog {
    private final AuthService authService = AuthService.getInstance();
    private User authenticatedUser;

    private JTextField loginUsernameTf;
    private JPasswordField loginPasswordPf;

    private JTextField regUsernameTf, regFullNameTf, regRollNoTf, regRoomNoTf, regPhoneTf;
    private JPasswordField regPasswordPf;
    private JComboBox<String> regHostelBlockCb;

    public AuthDialog(Frame owner) {
        super(owner, "Hostel Canteen - Login & Registration", true);
        setSize(480, 580);
        setLocationRelativeTo(owner);
        setResizable(false);
        setUndecorated(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UITheme.BG_DARK);

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 8));
        headerPanel.setBackground(UITheme.BG_HEADER);
        headerPanel.setBorder(new EmptyBorder(24, 20, 24, 20));

        JLabel titleLbl = new JLabel("🌙 Hostel Night Canteen", SwingConstants.CENTER);
        titleLbl.setFont(UITheme.FONT_HEADER_LARGE);
        titleLbl.setForeground(UITheme.ACCENT_AMBER);

        JLabel subLbl = new JLabel("Order late night food directly to your hostel room", SwingConstants.CENTER);
        subLbl.setFont(UITheme.FONT_SMALL);
        subLbl.setForeground(UITheme.TEXT_SECONDARY);

        headerPanel.add(titleLbl); 
        headerPanel.add(subLbl);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(UITheme.FONT_BODY_BOLD);
        tabbedPane.setBackground(UITheme.BG_CARD);
        tabbedPane.setForeground(UITheme.TEXT_PRIMARY);
        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintTabArea(java.awt.Graphics g, int tabPlacement, int selectedIndex) {
                g.setColor(UITheme.BG_INPUT);
                g.fillRect(0, 0, this.tabPane.getWidth(), this.tabPane.getHeight());
                super.paintTabArea(g, tabPlacement, selectedIndex);
            }
        });

        tabbedPane.addTab("🔑 Login", createLoginPanel());
        tabbedPane.addTab("📝 Register Student", createRegisterPanel());

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private JPanel createLoginPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(UITheme.BG_DARK);
        p.setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(8, 0, 8, 0);
        gbc.weightx = 1.0; gbc.gridx = 0;

        p.add(UITheme.createLabel("Username / Roll No:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), gbc);
        loginUsernameTf = UITheme.createTextField(20); loginUsernameTf.setText("student");
        p.add(loginUsernameTf, gbc);

        p.add(UITheme.createLabel("Password:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), gbc);
        loginPasswordPf = UITheme.createPasswordField(20); loginPasswordPf.setText("student123");
        p.add(loginPasswordPf, gbc);

        JButton loginBtn = UITheme.createPrimaryButton("Sign In ➔");
        loginBtn.addActionListener(e -> handleLogin());
        p.add(loginBtn, gbc);

        JPanel demoPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        demoPanel.setOpaque(false);

        JButton demoStudentBtn = UITheme.createSecondaryButton("Demo Student");
        demoStudentBtn.setFont(UITheme.FONT_SMALL);
        demoStudentBtn.addActionListener(e -> { loginUsernameTf.setText("student"); loginPasswordPf.setText("student123"); handleLogin(); });

        JButton demoAdminBtn = UITheme.createSecondaryButton("Canteen Admin");
        demoAdminBtn.setFont(UITheme.FONT_SMALL);
        demoAdminBtn.addActionListener(e -> { loginUsernameTf.setText("admin"); loginPasswordPf.setText("admin123"); handleLogin(); });

        demoPanel.add(demoStudentBtn); demoPanel.add(demoAdminBtn);
        gbc.insets = new Insets(16, 0, 0, 0);
        p.add(UITheme.createLabel("Quick Demo Logins:", UITheme.FONT_SMALL, UITheme.TEXT_MUTED), gbc);
        gbc.insets = new Insets(4, 0, 0, 0);
        p.add(demoPanel, gbc);

        return p;
    }

    private JPanel createRegisterPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(UITheme.BG_DARK);
        p.setBorder(new EmptyBorder(10, 30, 10, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(4, 0, 4, 0);
        gbc.weightx = 1.0; gbc.gridx = 0;

        regUsernameTf = UITheme.createTextField(15);
        regPasswordPf = UITheme.createPasswordField(15);
        regFullNameTf = UITheme.createTextField(15);
        regRollNoTf = UITheme.createTextField(15);

        String[] blocks = {"Block A (Boys)", "Block B (Boys)", "Block C (Girls)", "Block D (Girls)", "PG Hostel Block"};
        regHostelBlockCb = new JComboBox<>(blocks);
        regHostelBlockCb.setFont(UITheme.FONT_BODY);
        regHostelBlockCb.setBackground(UITheme.BG_INPUT);
        regHostelBlockCb.setForeground(UITheme.TEXT_PRIMARY);

        regRoomNoTf = UITheme.createTextField(10);
        regPhoneTf = UITheme.createTextField(10);

        p.add(UITheme.createLabel("Full Name:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), gbc);
        p.add(regFullNameTf, gbc);

        p.add(UITheme.createLabel("Roll Number:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), gbc);
        p.add(regRollNoTf, gbc);

        p.add(UITheme.createLabel("Username:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), gbc);
        p.add(regUsernameTf, gbc);

        p.add(UITheme.createLabel("Password:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), gbc);
        p.add(regPasswordPf, gbc);

        JPanel locationPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        locationPanel.setOpaque(false);

        JPanel b1 = new JPanel(new BorderLayout()); b1.setOpaque(false);
        b1.add(UITheme.createLabel("Hostel Block:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), BorderLayout.NORTH);
        b1.add(regHostelBlockCb, BorderLayout.CENTER);

        JPanel b2 = new JPanel(new BorderLayout()); b2.setOpaque(false);
        b2.add(UITheme.createLabel("Room No:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), BorderLayout.NORTH);
        b2.add(regRoomNoTf, BorderLayout.CENTER);

        locationPanel.add(b1); locationPanel.add(b2);
        p.add(locationPanel, gbc);

        p.add(UITheme.createLabel("Phone Number:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), gbc);
        p.add(regPhoneTf, gbc);

        JButton regBtn = UITheme.createPrimaryButton("Create Student Account ✨");
        regBtn.addActionListener(e -> handleRegister());
        gbc.insets = new Insets(12, 0, 4, 0);
        p.add(regBtn, gbc);

        return p;
    }

    private void handleLogin() {
        String uStr = loginUsernameTf.getText().trim();
        String pStr = new String(loginPasswordPf.getPassword());
        User u = authService.login(uStr, pStr);
        if (u != null) {
            this.authenticatedUser = u;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!\nTry 'student' / 'student123' or 'admin' / 'admin123'.", "Auth Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        try {
            User u = authService.registerStudent(
                regUsernameTf.getText().trim(), new String(regPasswordPf.getPassword()),
                regFullNameTf.getText().trim(), regRollNoTf.getText().trim(),
                (String) regHostelBlockCb.getSelectedItem(), regRoomNoTf.getText().trim(), regPhoneTf.getText().trim()
            );
            this.authenticatedUser = u;
            JOptionPane.showMessageDialog(this, "Account created successfully! Welcome, " + u.getFullName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User showDialog() {
        setVisible(true);
        return authenticatedUser;
    }
}

class MenuPanel extends JPanel {
    public interface CartItemUpdateListener {
        void onItemQuantityChanged(MenuItem item, int newQuantity);
        int getItemQuantity(String menuItemId);
    }

    private final MenuService menuService = MenuService.getInstance();
    private final CartItemUpdateListener cartListener;

    private final JTextField searchTf;
    private final JCheckBox vegOnlyCb;
    private final JPanel categoryFilterPanel;
    private final JPanel gridContainer;
    private String selectedCategory = "ALL";

    public MenuPanel(CartItemUpdateListener cartListener) {
        this.cartListener = cartListener;

        setLayout(new BorderLayout(0, 16));
        setBackground(UITheme.BG_DARK);
        setBorder(new EmptyBorder(16, 20, 16, 20));

        JPanel topToolbar = new JPanel(new BorderLayout(12, 10));
        topToolbar.setOpaque(false);

        JPanel searchBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchBoxPanel.setOpaque(false);

        searchTf = UITheme.createTextField(18);
        JButton searchBtn = UITheme.createSecondaryButton("🔍 Search");
        searchBtn.addActionListener(e -> refreshMenuGrid());

        vegOnlyCb = new JCheckBox("🌱 Veg Only");
        vegOnlyCb.setFont(UITheme.FONT_BODY_BOLD);
        vegOnlyCb.setForeground(UITheme.STATUS_DELIVERED);
        vegOnlyCb.setOpaque(false);
        vegOnlyCb.addActionListener(e -> refreshMenuGrid());

        searchBoxPanel.add(UITheme.createLabel("Find Dish:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY));
        searchBoxPanel.add(searchTf); searchBoxPanel.add(searchBtn); searchBoxPanel.add(vegOnlyCb);

        categoryFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        categoryFilterPanel.setOpaque(false);
        setupCategoryButtons();

        topToolbar.add(searchBoxPanel, BorderLayout.NORTH);
        topToolbar.add(categoryFilterPanel, BorderLayout.SOUTH);

        gridContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 16));
        gridContainer.setBackground(UITheme.BG_DARK);

        JScrollPane scrollPane = new JScrollPane(gridContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(UITheme.BG_DARK);

        add(topToolbar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setupCategoryButtons() {
        categoryFilterPanel.removeAll();
        String[] categories = {"ALL", "Maggi & Noodles", "Snacks", "Sandwiches & Rolls", "Beverages", "Late Night Specials", "Desserts"};
        for (String cat : categories) {
            String label = cat.equals("ALL") ? "🔥 All Items" : cat;
            JButton btn = new JButton(label);
            btn.setFont(UITheme.FONT_BODY_BOLD);
            btn.setFocusPainted(false);
            if (cat.equalsIgnoreCase(selectedCategory)) {
                btn.setBackground(UITheme.ACCENT_ORANGE); btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(UITheme.BG_CARD); btn.setForeground(UITheme.TEXT_SECONDARY);
            }
            btn.addActionListener(e -> { selectedCategory = cat; setupCategoryButtons(); refreshMenuGrid(); });
            categoryFilterPanel.add(btn);
        }
        categoryFilterPanel.revalidate(); categoryFilterPanel.repaint();
    }

    public void refreshMenuGrid() {
        gridContainer.removeAll();
        List<MenuItem> items = menuService.filterMenuItems(selectedCategory, searchTf.getText().trim(), vegOnlyCb.isSelected());

        if (items.isEmpty()) {
            JPanel emptyPanel = new JPanel(new GridBagLayout()); emptyPanel.setOpaque(false);
            emptyPanel.add(UITheme.createLabel("😔 No food items match your filter!", UITheme.FONT_HEADER_MEDIUM, UITheme.TEXT_MUTED));
            gridContainer.add(emptyPanel);
        } else {
            for (MenuItem item : items) {
                int currentQty = (cartListener != null) ? cartListener.getItemQuantity(item.getId()) : 0;
                gridContainer.add(new FoodCard(item, currentQty, (foodItem, newQty) -> {
                    if (cartListener != null) cartListener.onItemQuantityChanged(foodItem, newQty);
                }));
            }
        }
        gridContainer.revalidate(); gridContainer.repaint();
    }
}

class CartPanel extends JPanel {
    public interface OrderPlacedListener {
        void onOrderPlaced(Order order);
    }

    private final AuthService authService = AuthService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final OrderPlacedListener orderPlacedListener;
    private final Map<String, OrderItem> cartItems = new HashMap<>();

    private final JTable cartTable;
    private final DefaultTableModel tableModel;
    private final JLabel totalAmountLbl, itemCountLbl;
    private final JTextField blockTf, roomTf;
    private final JRadioButton upiRb, codRb, walletRb;

    public CartPanel(OrderPlacedListener orderPlacedListener) {
        this.orderPlacedListener = orderPlacedListener;
        setLayout(new BorderLayout(16, 16));
        setBackground(UITheme.BG_DARK);
        setBorder(new EmptyBorder(16, 20, 16, 20));

        JPanel leftPane = new JPanel(new BorderLayout(0, 10)); leftPane.setOpaque(false);
        leftPane.add(UITheme.createLabel("🛒 Your Night Order Cart", UITheme.FONT_HEADER_MEDIUM, UITheme.TEXT_PRIMARY), BorderLayout.NORTH);

        String[] columns = {"Item Name", "Price (₹)", "Quantity", "Subtotal (₹)", "Special Request"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return column == 4; }
        };

        cartTable = new JTable(tableModel);
        cartTable.setFont(UITheme.FONT_BODY); cartTable.setRowHeight(32);
        cartTable.setBackground(UITheme.BG_CARD); cartTable.setForeground(UITheme.TEXT_PRIMARY);
        cartTable.getTableHeader().setFont(UITheme.FONT_BODY_BOLD);
        cartTable.getTableHeader().setBackground(UITheme.BG_INPUT);
        cartTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);

        JScrollPane tableScroll = new JScrollPane(cartTable);
        tableScroll.getViewport().setBackground(UITheme.BG_CARD);

        JPanel tableActions = new JPanel(new FlowLayout(FlowLayout.RIGHT)); tableActions.setOpaque(false);
        JButton clearBtn = UITheme.createSecondaryButton("🗑️ Clear Cart");
        clearBtn.addActionListener(e -> clearCart());
        tableActions.add(clearBtn);

        leftPane.add(tableScroll, BorderLayout.CENTER); leftPane.add(tableActions, BorderLayout.SOUTH);

        JPanel rightPane = UITheme.createCardPanel();
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
        rightPane.setPreferredSize(new Dimension(320, 0));

        itemCountLbl = UITheme.createLabel("Total Items: 0", UITheme.FONT_BODY, UITheme.TEXT_SECONDARY);
        totalAmountLbl = UITheme.createLabel("Total Amount: ₹0", UITheme.FONT_HEADER_LARGE, UITheme.STATUS_DELIVERED);

        User cur = authService.getCurrentUser();
        blockTf = UITheme.createTextField(15); blockTf.setText(cur != null ? cur.getHostelBlock() : "Block A");
        roomTf = UITheme.createTextField(10); roomTf.setText(cur != null ? cur.getRoomNo() : "101");

        JPanel locBox = new JPanel(new GridLayout(2, 2, 6, 6)); locBox.setOpaque(false);
        locBox.add(UITheme.createLabel("Block:", UITheme.FONT_SMALL, UITheme.TEXT_MUTED)); locBox.add(blockTf);
        locBox.add(UITheme.createLabel("Room No:", UITheme.FONT_SMALL, UITheme.TEXT_MUTED)); locBox.add(roomTf);

        upiRb = new JRadioButton("📱 UPI / Google Pay / PhonePe", true);
        codRb = new JRadioButton("💵 Cash on Room Delivery");
        walletRb = new JRadioButton("💳 Canteen Wallet");
        ButtonGroup bg = new ButtonGroup(); bg.add(upiRb); bg.add(codRb); bg.add(walletRb);

        for (JRadioButton rb : new JRadioButton[]{upiRb, codRb, walletRb}) {
            rb.setFont(UITheme.FONT_BODY); rb.setForeground(UITheme.TEXT_PRIMARY); rb.setOpaque(false);
        }

        JButton checkoutBtn = UITheme.createPrimaryButton("🚀 Place Midnight Order");
        checkoutBtn.addActionListener(e -> handleCheckout());

        rightPane.add(UITheme.createLabel("📋 Checkout Summary", UITheme.FONT_HEADER_MEDIUM, UITheme.ACCENT_AMBER));
        rightPane.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPane.add(itemCountLbl); rightPane.add(totalAmountLbl);
        rightPane.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPane.add(new JSeparator());
        rightPane.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPane.add(UITheme.createLabel("🏠 Delivery Location:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY));
        rightPane.add(locBox);
        rightPane.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPane.add(UITheme.createLabel("💳 Payment Method:", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY));
        rightPane.add(upiRb); rightPane.add(codRb); rightPane.add(walletRb);
        rightPane.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPane.add(checkoutBtn);

        add(leftPane, BorderLayout.CENTER); add(rightPane, BorderLayout.EAST);
        refreshCartView();
    }

    public void updateItemQuantity(MenuItem item, int newQty) {
        if (newQty <= 0) cartItems.remove(item.getId());
        else {
            OrderItem oi = cartItems.get(item.getId());
            if (oi != null) oi.setQuantity(newQty);
            else cartItems.put(item.getId(), new OrderItem(item.getId(), item.getName(), item.getPrice(), newQty, ""));
        }
        refreshCartView();
    }

    public int getItemQuantity(String id) { OrderItem oi = cartItems.get(id); return oi != null ? oi.getQuantity() : 0; }
    public int getCartItemCount() { int c = 0; for (OrderItem oi : cartItems.values()) c += oi.getQuantity(); return c; }
    public void clearCart() { cartItems.clear(); refreshCartView(); }

    private void refreshCartView() {
        tableModel.setRowCount(0); double total = 0.0; int count = 0;
        for (OrderItem item : cartItems.values()) {
            tableModel.addRow(new Object[]{ item.getItemName(), String.format("%.2f", item.getUnitPrice()), item.getQuantity(), String.format("%.2f", item.getTotalPrice()), item.getSpecialNotes() });
            total += item.getTotalPrice(); count += item.getQuantity();
        }
        itemCountLbl.setText("Total Items: " + count);
        totalAmountLbl.setText(String.format("Total Amount: ₹%.2f", total));
    }

    public void updateUserInfo(User user) {
        if (user != null) { blockTf.setText(user.getHostelBlock()); roomTf.setText(user.getRoomNo()); }
    }

    private void handleCheckout() {
        User user = authService.getCurrentUser();
        if (user == null) { JOptionPane.showMessageDialog(this, "Please log in first to place your order!", "Login Required", JOptionPane.WARNING_MESSAGE); return; }
        if (cartItems.isEmpty()) { JOptionPane.showMessageDialog(this, "Your cart is empty!", "Cart Empty", JOptionPane.WARNING_MESSAGE); return; }

        String pay = upiRb.isSelected() ? "UPI" : (codRb.isSelected() ? "Cash on Delivery" : "Canteen Wallet");
        try {
            Order order = orderService.createOrder(user, new ArrayList<>(cartItems.values()), pay, blockTf.getText().trim(), roomTf.getText().trim());
            JOptionPane.showMessageDialog(this, "🎉 Order Placed Successfully!\nOrder ID: " + order.getOrderId() + "\nDelivering to " + order.getDeliveryAddress(), "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);
            clearCart();
            if (orderPlacedListener != null) orderPlacedListener.onOrderPlaced(order);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class StudentOrdersPanel extends JPanel {
    private final AuthService authService = AuthService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final JPanel activeTrackerContainer;
    private final JPanel historyListContainer;

    public StudentOrdersPanel() {
        setLayout(new BorderLayout(0, 16));
        setBackground(UITheme.BG_DARK);
        setBorder(new EmptyBorder(16, 20, 16, 20));

        JPanel topBar = new JPanel(new BorderLayout()); topBar.setOpaque(false);
        topBar.add(UITheme.createLabel("📦 My Night Orders & Live Tracking", UITheme.FONT_HEADER_MEDIUM, UITheme.TEXT_PRIMARY), BorderLayout.WEST);
        JButton refreshBtn = UITheme.createSecondaryButton("🔄 Refresh Status");
        refreshBtn.addActionListener(e -> refreshOrdersList());
        topBar.add(refreshBtn, BorderLayout.EAST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(UITheme.BG_DARK);

        activeTrackerContainer = new JPanel(); activeTrackerContainer.setLayout(new BoxLayout(activeTrackerContainer, BoxLayout.Y_AXIS)); activeTrackerContainer.setOpaque(false);
        historyListContainer = new JPanel(); historyListContainer.setLayout(new BoxLayout(historyListContainer, BoxLayout.Y_AXIS)); historyListContainer.setOpaque(false);

        contentPanel.add(activeTrackerContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 16)));
        contentPanel.add(historyListContainer);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null); scrollPane.getViewport().setBackground(UITheme.BG_DARK);

        add(topBar, BorderLayout.NORTH); add(scrollPane, BorderLayout.CENTER);
        this.initializePanel();
    }
    
    private void initializePanel() {
        refreshOrdersList();
    }

    public void refreshOrdersList() {
        activeTrackerContainer.removeAll(); historyListContainer.removeAll();
        User current = authService.getCurrentUser();
        if (current == null) {
            historyListContainer.add(UITheme.createLabel("Please log in to view your orders.", UITheme.FONT_BODY, UITheme.TEXT_MUTED));
            revalidate(); repaint(); return;
        }

        List<Order> orders = orderService.getOrdersForStudent(current.getId());
        if (orders.isEmpty()) {
            JPanel emptyBox = UITheme.createCardPanel();
            emptyBox.add(UITheme.createLabel("🌙 No orders placed yet! Browse the canteen menu.", UITheme.FONT_BODY, UITheme.TEXT_MUTED));
            historyListContainer.add(emptyBox);
        } else {
            Order activeOrder = null;
            for (Order o : orders) {
                if (!"DELIVERED".equalsIgnoreCase(o.getStatus()) && !"CANCELLED".equalsIgnoreCase(o.getStatus())) {
                    activeOrder = o; break;
                }
            }
            if (activeOrder != null) activeTrackerContainer.add(createLiveTrackerCard(activeOrder));
            historyListContainer.add(UITheme.createLabel("📜 Order History", UITheme.FONT_SUBTITLE, UITheme.TEXT_PRIMARY));
            historyListContainer.add(Box.createRigidArea(new Dimension(0, 8)));
            for (Order o : orders) {
                historyListContainer.add(createOrderCard(o));
                historyListContainer.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        activeTrackerContainer.revalidate(); historyListContainer.revalidate(); repaint();
    }

    private JPanel createLiveTrackerCard(Order order) {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(12, 12));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UITheme.ACCENT_AMBER, 2),
            new EmptyBorder(14, 16, 14, 16)
        ));

        JPanel top = new JPanel(new BorderLayout()); top.setOpaque(false);
        top.add(UITheme.createLabel("⚡ Live Order: " + order.getOrderId(), UITheme.FONT_HEADER_MEDIUM, UITheme.ACCENT_AMBER), BorderLayout.WEST);
        top.add(new StatusBadge(order.getStatus()), BorderLayout.EAST);

        JPanel progressBox = new JPanel(new GridLayout(1, 4, 8, 0)); progressBox.setOpaque(false);
        String[] stageLabels = {"1. Placed 📝", "2. Preparing 🍳", "3. On the Way 🛵", "4. Delivered 🏠"};
        int currentStageIdx = getStageIndex(order.getStatus());

        for (int i = 0; i < stageLabels.length; i++) {
            JPanel stageTile = new JPanel(new BorderLayout());
            stageTile.setOpaque(true);
            stageTile.setBackground(i <= currentStageIdx ? UITheme.ACCENT_ORANGE : UITheme.BG_INPUT);
            JLabel lbl = new JLabel(stageLabels[i], SwingConstants.CENTER);
            lbl.setFont(UITheme.FONT_SMALL); lbl.setForeground(Color.WHITE);
            stageTile.add(lbl, BorderLayout.CENTER);
            progressBox.add(stageTile);
        }

        JLabel detailsLbl = UITheme.createLabel(
            String.format("Delivering to %s | Total: ₹%.2f | Paid via %s", order.getDeliveryAddress(), order.getTotalAmount(), order.getPaymentMethod()),
            UITheme.FONT_BODY, UITheme.TEXT_SECONDARY
        );

        card.add(top, BorderLayout.NORTH); card.add(progressBox, BorderLayout.CENTER); card.add(detailsLbl, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createOrderCard(Order order) {
        JPanel card = UITheme.createCardPanel(); card.setLayout(new BorderLayout(10, 8));
        JPanel top = new JPanel(new BorderLayout()); top.setOpaque(false);
        top.add(UITheme.createLabel("Order #" + order.getOrderId() + " (" + order.getOrderTime() + ")", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY), BorderLayout.WEST);
        top.add(new StatusBadge(order.getStatus()), BorderLayout.EAST);

        StringBuilder sb = new StringBuilder("Items: ");
        for (int i = 0; i < order.getItems().size(); i++) {
            OrderItem oi = order.getItems().get(i);
            sb.append(oi.getItemName()).append(" x").append(oi.getQuantity());
            if (i < order.getItems().size() - 1) sb.append(", ");
        }

        card.add(top, BorderLayout.NORTH);
        card.add(UITheme.createLabel(sb.toString(), UITheme.FONT_BODY, UITheme.TEXT_SECONDARY), BorderLayout.CENTER);
        card.add(UITheme.createLabel(String.format("Total: ₹%.2f | Delivery: %s", order.getTotalAmount(), order.getDeliveryAddress()), UITheme.FONT_BODY_BOLD, UITheme.ACCENT_AMBER), BorderLayout.SOUTH);
        return card;
    }

    private int getStageIndex(String status) {
        if (status == null) return 0;
        return switch (status.toUpperCase()) {
            case "PLACED" -> 0;
            case "PREPARING" -> 1;
            case "OUT_FOR_DELIVERY", "OUT FOR DELIVERY" -> 2;
            case "DELIVERED" -> 3;
            default -> 0;
        };
    }
}

class AdminDashboardPanel extends JPanel {
    private final OrderService orderService = OrderService.getInstance();
    private final MenuService menuService = MenuService.getInstance();

    private JTable ordersTable, menuTable;
    private DefaultTableModel ordersTableModel, menuTableModel;
    private final JLabel statRevenueLbl, statActiveOrdersLbl, statTotalOrdersLbl;

    public AdminDashboardPanel() {
        setLayout(new BorderLayout(0, 16));
        setBackground(UITheme.BG_DARK);
        setBorder(new EmptyBorder(16, 20, 16, 20));

        JPanel analyticsBar = new JPanel(new GridLayout(1, 3, 16, 0)); analyticsBar.setOpaque(false);
        statRevenueLbl = UITheme.createLabel("₹0.00", UITheme.FONT_HEADER_LARGE, UITheme.STATUS_DELIVERED);
        statActiveOrdersLbl = UITheme.createLabel("0", UITheme.FONT_HEADER_LARGE, UITheme.ACCENT_AMBER);
        statTotalOrdersLbl = UITheme.createLabel("0", UITheme.FONT_HEADER_LARGE, UITheme.TEXT_PRIMARY);

        analyticsBar.add(createStatCard("💰 Total Revenue", statRevenueLbl));
        analyticsBar.add(createStatCard("⏳ Active Orders", statActiveOrdersLbl));
        analyticsBar.add(createStatCard("📦 Total Orders Tonight", statTotalOrdersLbl));

        JTabbedPane adminTabs = new JTabbedPane();
        adminTabs.setFont(UITheme.FONT_BODY_BOLD);
        adminTabs.setBackground(UITheme.BG_CARD);
        adminTabs.setForeground(UITheme.TEXT_PRIMARY);
        adminTabs.setOpaque(true);

        adminTabs.addTab("🛎️ Live Orders Queue", createLiveOrdersTab());
        adminTabs.addTab("🍕 Canteen Menu Manager", createMenuManagerTab());

        add(analyticsBar, BorderLayout.NORTH); add(adminTabs, BorderLayout.CENTER);
        this.initAdmin();
    }
    
    private void initAdmin() {
        refreshAdminData();
    }

    private JPanel createStatCard(String title, JLabel valueLbl) {
        JPanel card = UITheme.createCardPanel(); card.setLayout(new BorderLayout(4, 4));
        card.add(UITheme.createLabel(title, UITheme.FONT_SMALL, UITheme.TEXT_MUTED), BorderLayout.NORTH);
        card.add(valueLbl, BorderLayout.CENTER);
        return card;
    }

    private JPanel createLiveOrdersTab() {
        JPanel panel = new JPanel(new BorderLayout(0, 10)); panel.setBackground(UITheme.BG_DARK);
        String[] cols = {"Order ID", "Student Name", "Roll No", "Delivery Room", "Items Summary", "Total (₹)", "Status", "Time"};
        ordersTableModel = new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };

        ordersTable = new JTable(ordersTableModel); ordersTable.setRowHeight(36);
        ordersTable.setBackground(UITheme.BG_CARD); ordersTable.setForeground(UITheme.TEXT_PRIMARY);

        JPanel actionBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); actionBar.setOpaque(false);
        JButton prepBtn = UITheme.createSecondaryButton("🍳 Mark Preparing"); prepBtn.addActionListener(e -> updateStatus("PREPARING"));
        JButton outBtn = UITheme.createSecondaryButton("🛵 Mark Out for Delivery"); outBtn.addActionListener(e -> updateStatus("OUT_FOR_DELIVERY"));
        JButton deliveredBtn = UITheme.createPrimaryButton("✅ Mark Delivered"); deliveredBtn.addActionListener(e -> updateStatus("DELIVERED"));
        JButton refreshBtn = UITheme.createSecondaryButton("🔄 Refresh"); refreshBtn.addActionListener(e -> refreshAdminData());

        actionBar.add(prepBtn); actionBar.add(outBtn); actionBar.add(deliveredBtn); actionBar.add(refreshBtn);
        panel.add(new JScrollPane(ordersTable), BorderLayout.CENTER); panel.add(actionBar, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createMenuManagerTab() {
        JPanel panel = new JPanel(new BorderLayout(0, 10)); panel.setBackground(UITheme.BG_DARK);
        String[] cols = {"Item ID", "Emoji", "Item Name", "Category", "Price (₹)", "Veg", "Status"};
        menuTableModel = new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };

        menuTable = new JTable(menuTableModel); menuTable.setRowHeight(32);
        menuTable.setBackground(UITheme.BG_CARD); menuTable.setForeground(UITheme.TEXT_PRIMARY);

        JPanel menuActions = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); menuActions.setOpaque(false);
        JButton addBtn = UITheme.createPrimaryButton("➕ Add Dish"); addBtn.addActionListener(e -> showAddDishDialog());
        JButton toggleBtn = UITheme.createSecondaryButton("🔄 Toggle Availability"); toggleBtn.addActionListener(e -> toggleAvailability());
        menuActions.add(addBtn); menuActions.add(toggleBtn);

        panel.add(new JScrollPane(menuTable), BorderLayout.CENTER); panel.add(menuActions, BorderLayout.SOUTH);
        return panel;
    }

    public void refreshAdminData() {
        statRevenueLbl.setText(String.format("₹%.2f", orderService.getTotalRevenue()));
        statActiveOrdersLbl.setText(String.valueOf(orderService.getActiveOrdersCount()));
        statTotalOrdersLbl.setText(String.valueOf(orderService.getAllOrders().size()));

        ordersTableModel.setRowCount(0);
        for (Order o : orderService.getAllOrders()) {
            StringBuilder sb = new StringBuilder();
            for (OrderItem oi : o.getItems()) sb.append(oi.getItemName()).append(" (x").append(oi.getQuantity()).append(") ");
            ordersTableModel.addRow(new Object[]{ o.getOrderId(), o.getStudentName(), o.getRollNo(), o.getDeliveryAddress(), sb.toString(), String.format("%.2f", o.getTotalAmount()), o.getStatus(), o.getOrderTime() });
        }

        menuTableModel.setRowCount(0);
        for (MenuItem mi : menuService.getAllMenuItems()) {
            menuTableModel.addRow(new Object[]{ mi.getId(), mi.getEmoji(), mi.getName(), mi.getCategory(), String.format("%.2f", mi.getPrice()), mi.isVegetarian() ? "🟢 VEG" : "🔴 NON-VEG", mi.isAvailable() ? "AVAILABLE" : "OUT OF STOCK" });
        }
    }

    private void updateStatus(String status) {
        int r = ordersTable.getSelectedRow();
        if (r != -1) {
            orderService.updateOrderStatus((String) ordersTableModel.getValueAt(r, 0), status);
            refreshAdminData();
        } else {
            JOptionPane.showMessageDialog(this, "Select an order from table!", "Select Order", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void toggleAvailability() {
        int r = menuTable.getSelectedRow();
        if (r != -1) {
            menuService.toggleAvailability((String) menuTableModel.getValueAt(r, 0));
            refreshAdminData();
        }
    }

    private void showAddDishDialog() {
        JTextField nameTf = UITheme.createTextField(15);
        JTextField priceTf = UITheme.createTextField(10);
        JTextField descTf = UITheme.createTextField(20);
        JCheckBox vegCb = new JCheckBox("Is Vegetarian", true);

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("Dish Name:")); panel.add(nameTf);
        panel.add(new JLabel("Price (₹):")); panel.add(priceTf);
        panel.add(new JLabel("Description:")); panel.add(descTf);
        panel.add(new JLabel("Vegetarian:")); panel.add(vegCb);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Dish", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                menuService.addMenuItem(nameTf.getText(), "Snacks", Double.parseDouble(priceTf.getText()), descTf.getText(), vegCb.isSelected(), true, "🍜");
                refreshAdminData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

// =========================================================
// 6. MAIN APPLICATION FRAME
// =========================================================

class MainFrame extends JFrame implements MenuPanel.CartItemUpdateListener, CartPanel.OrderPlacedListener {
    private JLabel userBadgeLbl;
    private JButton cartNavBtn, adminNavBtn;
    private final JPanel contentContainer;
    private final CardLayout cardLayout;

    private final MenuPanel menuPanel;
    private final CartPanel cartPanel;
    private final StudentOrdersPanel ordersPanel;
    private final AdminDashboardPanel adminPanel;

    public MainFrame() {
        super("Hostel Night Canteen Food Ordering System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);

        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(UITheme.BG_DARK);
        rootPanel.add(createTopHeaderPanel(), BorderLayout.NORTH);

        cardLayout = new CardLayout();
        contentContainer = new JPanel(cardLayout);
        contentContainer.setBackground(UITheme.BG_DARK);

        menuPanel = new MenuPanel(this);
        cartPanel = new CartPanel(this);
        ordersPanel = new StudentOrdersPanel();
        adminPanel = new AdminDashboardPanel();

        contentContainer.add(menuPanel, "MENU");
        contentContainer.add(cartPanel, "CART");
        contentContainer.add(ordersPanel, "ORDERS");
        contentContainer.add(adminPanel, "ADMIN");

        rootPanel.add(contentContainer, BorderLayout.CENTER);
        setContentPane(rootPanel);
    }

    private JPanel createTopHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UITheme.BG_HEADER);
        headerPanel.setBorder(new EmptyBorder(12, 20, 12, 20));

        JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); brandPanel.setOpaque(false);
        JLabel logoLbl = new JLabel("🌙"); logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));

        JPanel titleBox = new JPanel(new GridLayout(2, 1)); titleBox.setOpaque(false);
        JLabel titleLbl = new JLabel("HOSTEL NIGHT CANTEEN"); titleLbl.setFont(UITheme.FONT_HEADER_LARGE); titleLbl.setForeground(UITheme.ACCENT_AMBER);
        JLabel subTitleLbl = new JLabel("Express Delivery to Hostel Rooms"); subTitleLbl.setFont(UITheme.FONT_SMALL); subTitleLbl.setForeground(UITheme.TEXT_MUTED);
        titleBox.add(titleLbl); titleBox.add(subTitleLbl);
        brandPanel.add(logoLbl); brandPanel.add(titleBox);

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0)); navBar.setOpaque(false);
        JButton menuNavBtn = UITheme.createSecondaryButton("📜 Menu");
        cartNavBtn = UITheme.createSecondaryButton("🛒 Cart (0)");
        JButton ordersNavBtn = UITheme.createSecondaryButton("📦 Track Orders");
        adminNavBtn = UITheme.createSecondaryButton("🛠️ Admin Dashboard");

        menuNavBtn.addActionListener(e -> showTab("MENU"));
        cartNavBtn.addActionListener(e -> showTab("CART"));
        ordersNavBtn.addActionListener(e -> showTab("ORDERS"));
        adminNavBtn.addActionListener(e -> showTab("ADMIN"));

        navBar.add(menuNavBtn); navBar.add(cartNavBtn); navBar.add(ordersNavBtn); navBar.add(adminNavBtn);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); userPanel.setOpaque(false);
        userBadgeLbl = UITheme.createLabel("👤 Guest", UITheme.FONT_BODY_BOLD, UITheme.TEXT_PRIMARY);
        JButton authBtn = UITheme.createSecondaryButton("🔑 Account"); authBtn.addActionListener(e -> promptLoginDialog());

        userPanel.add(userBadgeLbl); userPanel.add(authBtn);

        headerPanel.add(brandPanel, BorderLayout.WEST);
        headerPanel.add(navBar, BorderLayout.CENTER);
        headerPanel.add(userPanel, BorderLayout.EAST);
        return headerPanel;
    }

    public void showTab(String tabName) {
        cardLayout.show(contentContainer, tabName);
        // Load data asynchronously to avoid blocking UI
        new Thread(() -> {
            switch (tabName) {
                case "MENU" -> SwingUtilities.invokeLater(() -> menuPanel.refreshMenuGrid());
                case "ORDERS" -> SwingUtilities.invokeLater(() -> ordersPanel.refreshOrdersList());
                case "ADMIN" -> SwingUtilities.invokeLater(() -> adminPanel.refreshAdminData());
            }
        }).start();
    }

    public void promptLoginDialog() {
        AuthDialog dialog = new AuthDialog(this);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        User user = dialog.showDialog();
        if (user != null) {
            userBadgeLbl.setText("👤 " + user.getFullName() + (user.isAdmin() ? " (ADMIN)" : " (" + user.getHostelBlock() + ")"));
            cartPanel.updateUserInfo(user);
            // Load data in background to avoid blocking
            new Thread(() -> {
                ordersPanel.refreshOrdersList();
                String targetTab = user.isAdmin() ? "ADMIN" : "MENU";
                SwingUtilities.invokeLater(() -> showTab(targetTab));
            }).start();
        }
    }

    @Override public void onItemQuantityChanged(MenuItem item, int newQuantity) {
        cartPanel.updateItemQuantity(item, newQuantity);
        cartNavBtn.setText("🛒 Cart (" + cartPanel.getCartItemCount() + ")");
    }

    @Override public int getItemQuantity(String menuItemId) { return cartPanel.getItemQuantity(menuItemId); }

    @Override public void onOrderPlaced(Order order) {
        cartNavBtn.setText("🛒 Cart (0)");
        ordersPanel.refreshOrdersList();
        showTab("ORDERS");
    }
}