CatDex
======

Cat breeds reference.  

Android application with information about cat breeds.
It contains a list of known breeds, and detailed information about those breeds.



**Technical choices made:**

Tried to use as little Android specific code as possible, to make a future port to Kotlin Compose Multiplatform easier.

This application does use the Android Viewmodels, but is easily swapped out for a multiplatform version.  
For networking, this application uses Retrofit, this could be swapped out for Ktor-client, if needed.

Due to time constraints, I have left out any pagination. Only the first 12 cat breeds are displayed.  
A search function would also be welcome.  


**Future improvements I have in mind:**  

- Multiplatform support (Desktop, Web, iOS)
- Better use of API Key
- Large screen (Tablet, Desktop) UI (list-detail view)
- Pagination or infinite scrolling


**Libraries used:**  

- Navigation Compose
- Material Icons 
- Retrofit
- OkHttp Logging interceptor
- Coil
- KotlinX Serialization

