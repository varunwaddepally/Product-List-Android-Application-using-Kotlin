# 🛒 ProductsApplication

## 📱 Overview
**ProductsApplication** is an Android app built using **Kotlin** and **XML layouts**. It follows modern Android development practices and is organized with MVVM architecture, Room database, and Navigation Components.  
The app displays and manages a list of products with data persistence and navigation between screens.

---

## 🧩 Key Features
- Product listing and detail screens using Retrofit 
- Room Database integration for local persistence
- Remembers the last screen visited by the user and restores it on app launch for seamless navigation.  
- Navigation Component for screen transitions  
- MVVM architecture for clean separation of concerns  
- Kotlin coroutines and LiveData for async updates  
- Material Design UI components
- Handles offline scenarios and database errors gracefully.
- Remembers the last screen visited by the user and restores it on app launch using `SharedPreferences` or `DataStore`.  
- Configured Retrofit instance for network API calls to fetch or update product data dynamically.  


---

## 🛠️ Tech Stack
| Component | Technology |
|------------|-------------|
| **Language** | Kotlin |
| **UI** | XML Layouts |
| **Architecture** | MVVM |
| **Database** | Room |
| **Networking** | Retrofit |
| **Navigation** | Android Jetpack Navigation |
| **Dependency Management** | Gradle |

---

## 🚀 How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/varunwaddepally/Product-List-Android-Application-using-Kotlin.git
   cd ProductsApplication
   ```

2. Open the project in **Android Studio**.

3. Let Gradle sync automatically (or click **Sync Project with Gradle Files**).

4. Connect your Android device or use an emulator.

5. Run the app:
   ```
   Run ▶️ → app
   ```

---

## 📂 Important Files
- `app/build.gradle` – App-level build configuration  
- `AndroidManifest.xml` – App permissions and components  
- `MainActivity.kt` – Main entry point of the app  
- `res/layout/` – Contains all XML UI designs  
- `data/`, `ui/`, `repository/` – Core MVVM layer folders  

---

## 📸 Screens (if applicable)
- Product List Screen  
- Product Detail Screen  

---

## 👨‍💻 Author
**Varun Waddepally**  
📧 Email: varunwaddepally@gmail.com  
💼 GitHub: [https://github.com/varunwaddepally](https://github.com/varunwaddepally)
