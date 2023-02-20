# App to List Items to sell

- View 1:
    - To list selling item
    - send Image with other info. using formData
    - endPoint /api/posting to send image to Digital Ocean, Send to redis when Posted, return ID
<img width="331" alt="Screenshot_20230220_050404" src="https://user-images.githubusercontent.com/86675075/220065545-f9fa06fc-342f-421b-90b3-2f637834d97a.png">


- View 2:
    - On Confirm, get Redis Object, send to SQL, delete Redis Object
    - endPoint /api/posting/{postId}
    - use alert() to get pop-up if save to SQL fails
<img width="452" alt="Screenshot_20230220_050425" src="https://user-images.githubusercontent.com/86675075/220065612-7283966b-41fe-4c6c-a639-c94619593e84.png">


- View 3:
    - Final confirmation
<img width="346" alt="Screenshot_20230220_050445" src="https://user-images.githubusercontent.com/86675075/220065661-9a42da04-aa29-4977-923f-d25ac14d7d32.png">



