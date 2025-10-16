# 🛒 ProductsApplication

## 📱 Overview
**ProductsApplication** is an Android app built using **Kotlin** and **XML layouts**. It follows modern Android development practices and is organized with MVVM architecture, Room database, and Navigation Components.  
The app displays and manages a list of products with data persistence and navigation between screens.

---

## 🧩 Key Features

- Product listing and detail screens using **Retrofit**  
- **Room Database** integration for local persistence  
- Remembers the **last screen visited** by the user and restores it on app launch for seamless navigation  
- **Navigation Component** for smooth screen transitions  
- **MVVM architecture** for clean separation of concerns  
- **Kotlin Coroutines** and **LiveData** for asynchronous data updates  
- **Material Design** UI components for a modern look  
- Handles **offline scenarios** and database errors gracefully  
- Stores last visited screen using **RooomDB (Local Storage)**  
- Configured **Retrofit instance** for dynamic product fetching and updates  
- **Add to Cart** and **View Cart** functionality with data stored in Room Database  
- Displays **total cart price dynamically** and allows **real-time item deletion**  
- Integrated **“No Internet” screen** to prevent app crashes and improve user experience  


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
- Add to Cart Screen
- My Favourites Screen 

---

## 👨‍💻 Author
**Varun Waddepally**  
📧 Email: varunwaddepally@gmail.com  
💼 GitHub: [https://github.com/varunwaddepally](https://github.com/varunwaddepally)
