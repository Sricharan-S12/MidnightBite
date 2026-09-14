# 🌙 Hostel Night Canteen - Food Ordering System

A modern **Java Swing GUI application** for hostel students to order late-night food directly to their rooms.

## ✨ Features

- 🔐 **User Authentication** - Student login/registration and admin panel
- 🍔 **Menu Management** - Browse food items by category with search functionality
- 🛒 **Shopping Cart** - Add/remove items with quantity management
- 📦 **Order Tracking** - Real-time order status tracking with delivery stages
- 💳 **Payment Methods** - Support for Cash, UPI, and Card payments
- 📊 **Admin Dashboard** - Manage orders, menu items, and view statistics
- 🎨 **Modern Dark UI** - Beautiful dark theme with smooth animations
- 📱 **Responsive Design** - Adapts to different screen sizes

## 🎯 Quick Start

### Prerequisites
- **Java 8 or higher** installed on your system
- Windows, Mac, or Linux

### Installation & Running

**Option 1: Using Batch Script (Windows)**
```bash
scripts/run.bat
```

**Option 2: Manual Compilation & Run**
```bash
# Navigate to project directory
cd HostelCanteenApp

# Compile
javac src/HostelCanteenApp.java

# Run
java -cp src HostelCanteenApp
```

**Option 3: Using VBS Script (Windows - Hidden Console)**
```bash
scripts/launch.vbs
```

## 📝 Demo Credentials

### Student Account
- **Username:** `student`
- **Password:** `student123`

### Admin Account
- **Username:** `admin`
- **Password:** `admin123`

## 📁 Project Structure

```
HostelCanteenApp/
├── src/
│   └── HostelCanteenApp.java     # Main application (all-in-one file)
├── data/
│   ├── menu.json                 # Food menu data
│   ├── orders.json               # Order history
│   └── users.json                # User accounts
├── scripts/
│   ├── run.bat                   # Windows batch script
│   └── launch.vbs                # Windows VBS launcher (hidden console)
├── README.md                      # This file
├── INSTALLATION.md                # Detailed installation guide
└── .gitignore                     # Git ignore rules
```

## 🏗️ Application Modules

The application is built as a single Java file with the following components:

- **User & Auth** - User profiles and authentication system
- **Menu System** - Food items, categories, and availability
- **Order Management** - Order creation, tracking, and history
- **Data Storage** - JSON-based persistent data storage
- **UI Components** - Swing-based modern dark theme interface

## 🎨 UI Screens

1. **Login/Registration Screen** - Secure authentication for students
2. **Menu Browser** - Browse food items with search and category filters
3. **Shopping Cart** - Manage items and delivery details
4. **Order Tracking** - Real-time order status with delivery timeline
5. **Admin Dashboard** - Manage orders and menu items (admin only)

## 💾 Data Files

The application stores data in JSON format:

- `menu.json` - Available food items with categories and prices
- `orders.json` - Customer order history and status
- `users.json` - Registered user accounts and credentials

All data files are created automatically on first run.

## 🔒 Security Notes

This is a demo application. For production use:
- Implement proper password hashing (bcrypt/scrypt)
- Use a backend database instead of JSON files
- Add input validation and sanitization
- Implement proper error handling

## 📌 Requirements

- **Java Runtime Environment (JRE)** 8.0 or higher
- **Operating System:** Windows, macOS, or Linux
- **RAM:** Minimum 256MB
- **Disk Space:** ~5MB for application

## 🚀 Usage

1. Run the application using one of the methods above
2. Create a new student account OR login with demo credentials
3. Browse the menu and add items to cart
4. Enter delivery location and payment method
5. Place order and track it in real-time
6. Admin can manage orders and menu from admin panel

## 🐛 Troubleshooting

**Issue: "javac not found"**
- Install Java Development Kit (JDK)
- Add Java bin folder to PATH

**Issue: "Application won't start"**
- Ensure Java is properly installed: `java -version`
- Try running from command prompt instead of GUI
- Check that data files are in correct directory

**Issue: "Text not visible"**
- Ensure your display scaling is set correctly
- Try adjusting font settings in your system

## 📜 License

This is a demo hostel management system for educational purposes.

## 👨‍💻 Author

Created as a demonstration of Java Swing GUI development.

---

**Ready to order? Run the application and start placing orders! 🍜🍕**
