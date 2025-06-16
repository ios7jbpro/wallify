# wallify
despite all my rage

## !!!IGNORE THIS, THE APP FUNCTIONALITY IS BEING RESTORED!!!
> ⚠ **WARNING: This project is abandoned and no longer functional**
>
> This application has been intentionally disabled. At launch, it immediately starts a placeholder activity that informs users the project has been discontinued:
>
> ```java
> startActivity(new Intent(MainActivity.this, AppAbandoned.class));
> finish(); // Terminates MainActivity to prevent any further code from executing
> ```
>
> This safeguard is in place because the app was originally designed to fetch a JSON list of wallpapers from a remote server that **no longer exists**:
>
> ```java
> fetchcategoryjson.startRequestNetwork(
>     RequestNetworkController.GET,
>     config.getString("repo", ""), // URL was previously stored here
>     "",
>     _fetchcategoryjson_request_listener
> );
> ```
>
> **There is no exception handling around this request.**  
> If you bypass the `AppAbandoned` redirect and try to continue launching the app normally, it will **immediately crash** due to a `NullPointerException` or `NetworkOnMainThreadException`, depending on how it's triggered. The remote endpoint it expects simply isn’t there anymore.
>
> ---
>
> **If you intend to revive this project by recreating the backend:**
>
> You’ll need to **manually reverse-engineer** how the app consumes the JSON data. The format used was deliberately basic — most JSON files are **non-nested, flat structures**, so you won’t need advanced parsing. However, you will still need to:
>
> - Inspect each method that references `config.getString("repo", "")` or similar paths
> - Create mock JSON files that satisfy the app’s expected structure
> - Host them in a reachable, CORS-compliant endpoint (if testing on device)
> - Have a general understanding of how the app actually works and consumes the files, so you can properly reproduce the JSON files
>
> No backend? Then **do not** attempt to bypass the abandonment screen — the app *will not run* in its current form.
>
> ---
>
> **This repository remains available for archival and reference purposes only.**
> 
> This may change in the future where I find a backend to host, e.g GitHub.