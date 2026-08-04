# Результати реалізації екрану логіну та навігації

Я створив екран логіну, екран-заглушку та налаштував навігацію. Тепер ви можете протестувати процес авторизації.

## Що було зроблено

### 1. Навігація
- **[Screen.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/navigation/Screen.kt)**: Визначено маршрути `Login` та `Home` за допомогою `kotlinx.serialization`.
- **[NavGraph.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/navigation/NavGraph.kt)**: Налаштовано `NavHost`, який керує переходами між екранами. Після успішного логіну відбувається перехід на `Home` з очищенням бекстеку.

### 2. Екран логіну (Login)
- **[LoginViewModel.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/screens/LoginViewModel.kt)**: Обробляє введення даних, валідацію (не порожні поля) та виклик `authRepository.login()`. Використовує `Channel` для передачі подій успішного входу в UI.
- **[LoginScreen.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/screens/LoginScreen.kt)**: Реалізовано UI з полями Email та Password, обробкою стану завантаження та відображенням помилок.

### 3. Екран успіху (Home)
- **[HomeScreen.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/presentation/screens/HomeScreen.kt)**: Проста заглушка з привітанням, що з'являється після успішного входу.

### 4. Інтеграція та Dependency Injection
- **[MainActivity.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/MainActivity.kt)**: Оновлено для використання `NavGraph` як кореневого компонента.
- **[RepositoryModule.kt](file:///D:/FreeBank/app/src/main/java/com/example/freebank/core/di/RepositoryModule.kt)**: Додано модуль Hilt для прив'язки `AuthRepository` до `AuthRepositoryImpl`, що дозволяє `LoginViewModel` отримувати необхідні залежності.

## Як протестувати

1.  **Запустіть застосунок.** Ви побачите екран логіну.
2.  **Введіть дані.** Поля мають бути заповнені.
3.  **Натисніть "Login".**
    - Якщо ваш API поверне успішну відповідь (код 200), застосунок перейде на екран "Welcome to FreeBank!".
    - У цей момент токени будуть збережені в `DataStore` через `AuthRepositoryImpl`.
    - Якщо виникне помилка (наприклад, 401 або відсутність інтернету), ви побачите відповідне повідомлення на екрані.

> [!TIP]
> Оскільки токени зберігаються в `AuthPreferences`, при наступному запуску можна додати перевірку в `MainActivity` або `NavGraph`, щоб одразу переходити на `Home`, якщо токен вже існує.
