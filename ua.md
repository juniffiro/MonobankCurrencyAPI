# MonoCurrency: Monobank currency API
API для отримання курсів валют за допомогою API Monobank. В майбутньому може бути додана реалізація API інших банків.

Щоб почати користуватися API, просто створіть новий екземпляр API і отримайте дані за допомогою спеціального методу.
Отримані від сервісу дані зберігаються в кеші. Існує два режими роботи з даними:
1) Дані потрібно оновлювати вручну.
2) Дані оновлюються автоматично через певний проміжок часу.

Рекомендується використовувати режим 2, оскільки дані на сервісі оновлюються кожні 5 хвилин.
## Example
Приклад створення нового екземпляру API з ручним оновленням даних.
```java
 CurrencyAPI currencyAPI = new ManuallyMonoCurrencyAPI(30L);
 // Requests ...
 currencyAPI.onUpdate();
```
Приклад створення нового екземпляра API з автоматичним оновленням. Період оновлення за замовчуванням становить 5 хвилин.
Ви можете встановити його індивідуально. У цьому прикладі період оновлення 3 хвилини.
```java
 CurrencyAPI currencyAPI = new MonoCurrencyAPI(true, 180L);
```

Тепер вам просто потрібно використовувати метод getCurrency(), щоб отримати курс обміну.
```java
 Optional<CurrencyData> eurToUah = currencyAPI.getCurrency(978, 980);
        eurToUah.ifPresent(currencyData ->
                System.out.printf("EUR to UAH exchange rate %s%n", currencyData));
```

## Open source

MonoCurrency is an open source project distributed under the Apache License 2.0 <br>

## Getting started

1. Download the latest build from releases
2. Read the FAQ and examples
3. Enjoy!

## Status

The project is in beta. Use at your own risk. <br>
