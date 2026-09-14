# Installation Guide - Hostel Night Canteen

Detailed instructions for setting up and running the application.

## System Requirements

- **Operating System:** Windows 7+, macOS 10.9+, or Linux (any distribution)
- **Java Version:** JDK 8.0 or JRE 8.0 or higher
- **RAM:** Minimum 512MB (recommended 1GB+)
- **Display:** Any resolution (1024x768 or higher recommended)
- **Internet:** Not required (standalone application)

## Step 1: Install Java

### On Windows
1. Download Java from [oracle.com/java/technologies/downloads](https://www.oracle.com/java/technologies/downloads/)
2. Choose **JDK** (Java Development Kit) for Windows
3. Run the installer and follow the setup wizard
4. Choose installation path (e.g., `C:\Program Files\Java\jdk-21`)
5. Click "Install"

**Verify Installation:**
```bash
java -version
javac -version
```

### On macOS
```bash
# Using Homebrew (recommended)
brew install java

# Verify
java -version
```

### On Linux
```bash
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install openjdk-21-jdk

# Fedora/RedHat
sudo dnf install java-21-openjdk-devel

# Verify
java -version
javac -version
```

## Step 2: Download the Project

### Option A: Clone from GitHub
```bash
git clone https://github.com/yourusername/HostelCanteenApp.git
cd HostelCanteenApp
```

### Option B: Download ZIP
1. Visit the GitHub repository
2. Click "Code" → "Download ZIP"
3. Extract the ZIP file to your desired location

## Step 3: Run the Application

### Windows - Easy Method (Batch Script)
1. Navigate to the project folder
2. Double-click: `scripts/run.bat`
3. Application will compile and launch automatically

### Windows - Silent Launch (VBS Script)
1. Double-click: `scripts/launch.vbs`
2. No console window will appear (application only)

### All Platforms - Manual Compilation

**In Terminal/Command Prompt:**

```bash
# Navigate to project folder
cd HostelCanteenApp

# Compile the application
javac src/HostelCanteenApp.java

# Run the application
java -cp src HostelCanteenApp
```

### macOS/Linux - Create Shell Script

Create a file `run.sh` in the project root:

```bash
#!/bin/bash
cd "$(dirname "$0")"
javac src/HostelCanteenApp.java
java -cp src HostelCanteenApp
```

Make it executable:
```bash
chmod +x run.sh
./run.sh
```

## Step 4: Use Demo Accounts

When the application starts, use these credentials to test:

**Student Account:**
- Username: `student`
- Password: `student123`

**Admin Account:**
- Username: `admin`
- Password: `admin123`

## Troubleshooting

### Problem: "javac: command not found" or "java: command not found"

**Solution:** Add Java to your system PATH

**Windows:**
1. Open Environment Variables:
   - Right-click "This PC" → Properties
   - Click "Advanced system settings"
   - Click "Environment Variables"
2. Add Java bin directory to PATH:
   - Edit "Path" variable
   - Add: `C:\Program Files\Java\jdk-21\bin` (adjust version as needed)
3. Click OK and restart your terminal

**macOS/Linux:**
```bash
# Add to ~/.bash_profile or ~/.zshrc
export JAVA_HOME=$(/usr/libexec/java_home)  # macOS
export PATH=$JAVA_HOME/bin:$PATH

# Reload shell configuration
source ~/.bash_profile  # or ~/.zshrc
```

### Problem: "Could not find or load main class HostelCanteenApp"

**Solution:** Make sure you're in the right directory and compile first:
```bash
javac src/HostelCanteenApp.java
java -cp src HostelCanteenApp
```

### Problem: "Compilation failed" or "Cannot find symbol"

**Solution:** Delete compiled files and recompile:
```bash
# Windows
del *.class

# macOS/Linux
rm *.class
```

Then recompile:
```bash
javac src/HostelCanteenApp.java
```

### Problem: Application starts but UI looks broken

**Solution:** This is usually a display scaling issue
1. Right-click the terminal window → Properties
2. Adjust font size to your preference
3. Restart the application

### Problem: "Permission denied" on Mac/Linux

**Solution:** Make scripts executable:
```bash
chmod +x scripts/run.bat
chmod +x run.sh
```

## Creating or Modifying Demo Data

The application creates JSON files automatically:
- `data/menu.json` - Food items
- `data/orders.json` - Order history
- `data/users.json` - User accounts

These are created in the data folder when you first run the app.

## Developer Setup

To modify and extend the application:

1. Install a Java IDE (Optional):
   - **Eclipse:** [eclipse.org](https://www.eclipse.org/)
   - **IntelliJ IDEA:** [jetbrains.com/idea](https://www.jetbrains.com/idea/)
   - **VS Code:** Install Extension Pack for Java

2. Open the project in your IDE
3. The source is in `src/HostelCanteenApp.java`
4. Compile and run from the IDE

## Uninstalling

To remove the application:
1. Delete the project folder
2. (Optional) Uninstall Java if not needed for other applications

---

**Still having issues?** Check the main [README.md](README.md) or create a GitHub issue.
