# Pizza Factory: Pizza order form

## View 1
- Form for getting orders
    - Use of form group, with form array for getting checkbox values
    - Some form validations: Email format, minimum one topping. All fields required except comments
    - send to endpoint: /api/order, save order to SQL
    - run proxy for front-end: 
    `ng serve --proxy-config proxy.config.js`
    <img width="448" alt="view1" src="https://user-images.githubusercontent.com/86675075/220512333-bab596ad-0961-4a67-90be-56d86a1428b9.png">


## View 2
- View all orders for certain email
    - get endpoint: /api/order/{email}/all
    - grab RowSet from SQL, convert to order summary list, send back to frontend for display
    - calculate cost with pre-provided cost sheet
    <img width="389" alt="view2" src="https://user-images.githubusercontent.com/86675075/220512367-facf0946-6322-4cd7-ba20-bc88fbbd4b4c.png">
