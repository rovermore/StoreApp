StoreApp
-----------

Summary
------
The app shows a small catalog of products fetched from network in a list that will show product name and price. 
The user can select any of this products and the selection will be saved in a cart. 
A cart is accessible to the user too in order to and modify his selection by increasing and decreasing the amount of products. 
Finally the user can complete the purchase and the cart will be cleared.

Technical Decisions
-------------------
- The language selected for the development is Kotlin, as Android development is since a many years Kotlin first
- The architecture selected is CLEAN. The app contains three layers, data layer, domain layer and presentation layer. "Vertical slicing" is chosen when organizing the project for two reasons: to easily extract any layer into modules that can be reusable in future projects, and the second reason is the advantages it offers when obfuscating the code, where it would be easier to obfuscate the domain layer compared to another architecture organization. Note: If a design system exists, an additional layer of components could be created that could also be extracted into a module for reuse in other projects or platforms.
- In regards to the presentation layer, the MVVM pattern is used since I believe it is the pattern currently used in the company, as well as Compose. For observability from the ViewModel, StateFlow was used instead of State or LiveData to try to minimize the dependencies of Android in the ViewModel.
- A Result<Success, Failure> monad is used, which is returned in all operations so that both successful operations and errors can be managed and mapped easily anywhere in the app.
- Data Store is used for data persistence due to its flexibility and simplicity. Persistence models such as an SQLite database have been discarded due to the small amount and simplicity of the data to be persisted. Non-persistent data storage has also been discarded since for an e-commerce app, the shopping cart is an important point for the user experience and must survive app closures.
- Dagger Hilt is used for dependency injection as it is the solution suggested by Google for DI in Android apps.
- Coroutines are used for asynchronous operations as they are an easy, powerful, and flexible solution.
- The domain layer is thoroughly tested with unit tests using JUnit and Mockito. It is completely isolated from the Android framework or any external library and also contains the business logic.

