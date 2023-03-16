# MonoCurrency: Monobank currency API
API for getting exchange rates using Monobank API.
Implementation of other banks APIs can be added in the future.

👉 [Ukrainian version](ua.md)

To start using the API, simply create a new API instance and retrieve data using a special method.
The data received from the service is stored in the cache. There are two modes of working with data:
1) Data needs to be updated manually.
2) The data is updated automatically after a certain period of time.

It is recommended to use mode 2, because data are updated every 5 minutes on the service.
## Example
Example of creating a new API instance with manual data update.
```java
 CurrencyAPI currencyAPI = new ManuallyMonoCurrencyAPI(30L);
 // Requests ...
 currencyAPI.onUpdate();
```
Example of creating a new API instance with automatic update. The default refresh period is 5 minutes.
You can set it individually.  In this example the refresh period is set to 3 minutes.
```java
 CurrencyAPI currencyAPI = new MonoCurrencyAPI(true, 180L);
```

Now, you just need to use the getCurrency() method to get the exchange rate.
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
