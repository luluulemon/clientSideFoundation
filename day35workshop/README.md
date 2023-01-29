Day 35
- add on endpoint for angular
    1. /games-ng, with optional query params
        List games with name and GID
    **run server, then run front end (frontend folder) -> ng serve


Day 26
- Boardgames database on localhost, with games and comments collections
    Endpoints (REST):
    1. /games, with optional query params limit & offset
        List games with name and GID
    2. /games/rank, with optional query params limit & offset
        List games by ranking (ascending)
    3. /game/{gid}
        List details of game with gid

