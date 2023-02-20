# App to List Items to sell

- View 1:
    - To list selling item
    - send Image with other info. using formData
    - endPoint /api/posting to send image to Digital Ocean, Send to redis when Posted, return ID


- View 2:
    - On Confirm, get Redis Object, send to SQL, delete Redis Object
    - endPoint /api/posting/{postId}
    - use alert() to get pop-up if save to SQL fails


- View 3:
    - Final confirmation




