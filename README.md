## Day 35 workshop: getting boardgames names from mongo
    - Grabs boardgame names from mongodb
    - Use ngOnChanges to listen to changes on input for View Per Page, to change pagination
    


## Day 36 workshop: Accessing Open Weather API from Angular
- View 1: Add cities to list, store in LocalStorage
    - Add on ngDestroy():moving away from Component & @HostListener('window:beforeunload'): when closing tab
- View 2: Show weather details of chosen city
    - Calling endpoint with HttpClient: apiKey and city in Params


## Day 37 excercise: Playing around with ngx-webcam



## Day 37 workshop: Posting Pic with comments to SQL
- Use FormData() to send image & comments to backend
- Server: consumes multipart_form_data_value, stores image/comments to SQL 
- image: throw inputStream to SQL to store as blob


## Day 38 Excercise: Excercise on IndexDB
- Save records to IndexDB
- Sync with server side: clear IndexDB, save to MongoDB
- Fetch all from Mongo


## csf assessment: Pizza factory
### View 1
- Form for getting orders
    - Use of form group, with form array for getting checkbox values
    - Some form validations: Email format, minimum one topping. All fields required except comments
    - send to endpoint: /api/order, save order to SQL
    - run proxy for front-end

### View 2
- View all orders for certain email
    - get endpoint: /api/order/{email}/all
    - grab RowSet from SQL, convert to order summary list, send back to frontend for display
    - calculate cost with pre-provided cost sheet