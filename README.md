# SampleApp

## Setup App Development Environment
1) Download and install Android Studio
2) Unzip contents to any folder and open this in Android Studio
3) Hit run after successful build

## App flavors - different name of the app, package-name, base url
- Simpsons Character Viewer
- The Wire Character Viewer

## Technology Stack
- Architecture: Single activity, MVVM
- Network: Retrofit
- Local database: in memory database
- Downloading images: Glide
- Observing data: LiveData
- Asynchronous: Coroutines
- UI: Xml
- Others: SlidingPaneLayout, Fragments, RecyclerView, Gson

## Some notes
- 1 MainActivity, 2 Fragments (ListFragment - data is displayed as a text, DetailsFragment - shows the details of the selected character)
- Data is stored with in-memory cache which impelemented in CharactersRepository
- Simple error handling
- Implemented search functionality that filters the character list
- Image in the details page gets downloaded by Glide. It shows placeholder when downloading and error cases
