Day 35
- add on endpoint for angular
    1. /games-ng, with optional query params
        List games with name and GID
    - allow cross-origin using @CrossOrigin(origins="*") notation for endpoint
    **run server, then run front end (frontend folder) -> ng serve
    
<img width="452" alt="Screenshot_20230222_030801" src="https://user-images.githubusercontent.com/86675075/220550129-4eee772a-adc1-4486-965a-262eac6b8337.png">


Day 26
- Boardgames database on localhost, with games and comments collections
    Endpoints (REST):
    1. /games, with optional query params limit & offset
        List games with name and GID
    2. /games/rank, with optional query params limit & offset
        List games by ranking (ascending)
    3. /game/{gid}
        List details of game with gid

