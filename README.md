
# Gif Search App

<![alt text](https://ibb.co/hLnCW66)  width = "650px" >


This application allows users to search for GIFs using the Giphy API. Users can enter a search query, and the app will fetch and display a list of GIFs related to the query.

## Technologies Used

- Kotlin
- MVVM Architecture
- Hilt Dependency Injection (DI)
- Compose UI
- Multiple Fragments UI
- Mockito or Junit or Mockk for Unit Testing

## Requirements

To run this application, you'll need:

- Android Studio (version XYZ or higher)
- An Android device or emulator with Android version XYZ or higher

## How to Use

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the application on an Android device or emulator.

Upon launching the app, you'll be on the home screen. Enter a search query (e.g., "cats") and press enter. The app will fetch and display a list of GIFs related to your query.

## API Key

The Giphy API key is not included in the URL but passed as a query parameter. Please ensure you have a valid API key and replace it in the appropriate location.

API Endpoint: `https://api.giphy.com/v1/gifs/search`
API Key Parameter: `api_key=YOUR_API_KEY`
Query Parameter: `q=cats`

## Unit Testing

The application includes unit tests using either Mockito, Junit, or Mockk. You can run the tests using Android Studio's test runner.

---

