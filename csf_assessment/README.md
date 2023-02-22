# Pizza Factory: Pizza order form

## View 1
- Form for getting orders
    - Use of form group, with form array for getting checkbox values
    - Some form validations: Email format, minimum one topping. All fields required except comments
    - send to endpoint: /api/order, save order to SQL
    - run proxy for front-end: 
    `ng serve --proxy-config proxy.config.js`

## View 2
- View all orders for certain email
    - get endpoint: /api/order/{email}/all
    - grab RowSet from SQL, convert to order summary list, send back to frontend for display
    - calculate cost with pre-provided cost sheet