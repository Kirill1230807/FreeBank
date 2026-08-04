# Створення екрану логіну та навігації

Цей план передбачає створення екрану логіну, екрану-заглушки (Home) та налаштування навігації для тестування процесу авторизації та збереження токенів.

## План змін

### Presentation Layer

#### [NEW] [LoginViewModel.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/screens/LoginViewModel.kt)
Створення ViewModel для обробки логіки логіну. Вона буде використовувати `AuthRepository` для виклику API та керувати станом екрану (завантаження, помилка, успіх).

#### [NEW] [LoginScreen.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/screens/LoginScreen.kt)
Створення UI екрану логіну з полями Email, Password та кнопкою Login.

#### [NEW] [HomeScreen.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/screens/HomeScreen.kt)
Створення простого екрану-заглушки, на який користувач потрапить після успішного логіну.

#### [NEW] [Screen.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/navigation/Screen.kt)
Визначення маршрутів для навігації.

#### [NEW] [NavGraph.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/navigation/NavGraph.kt)
Налаштування `NavHost` з маршрутами до Login та Home екранів.

### Main Activity

#### [MODIFY] [MainActivity.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/MainActivity.kt)
Встановлення `NavGraph` як основного контенту застосунку.

## План верифікації

### Ручна перевірка
1. Запустити застосунок.
2. Ввести email та password на екрані логіну.
3. Натиснути кнопку "Login".
4. Перевірити, чи відбувається перехід на HomeScreen при успішному логіні (якщо API поверне 200).
5. Оскільки я не маю доступу до реального бекенду під час розробки, я можу додати логування в `AuthRepositoryImpl` або `LoginViewModel`, щоб переконатися, що токени зберігаються в DataStore.
