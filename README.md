## Fakeopoly
Started: 2023-03-24
Last Edited: 2023-04-16

## Description
This program was created for our final project in our Computer Networking class at Okanagan College. 
We decided to make a game that resembles Monopoly using an RMI server in Java. We chose RMI as our method of handling calls to the server as it allows us to scale our function calls and maintain an overall concurrent state between all clients. It also excited us as we had not truelly experiemented with RMI and so it was a great learning opportunity.

Using java, hosting server RMI
All UI Designs where done in house.

## Developers & Designers:
- Freddie Banfield
- Brady McMechan

## Game
The game is a \**micro* version of Monopoly. 
Functionality in game:
- You can connect to the RMI server, providing a server address, a server port, a username, and your color for your game peice.
- You can message the other players in the game menu before readying up.
- You can ready up and notify other players to start the game.
- Once in the game, you will see all player information, a chat menu to continue the conversations from the lobby screen, and buttons to control your turn.
- You can roll the dice, to which all players will see the dice animation.
- You can get doubles, but becareful, if you get three in a row you will go to jail.
- You can go to jail to which you have to roll doubles or three times to get out.
- You can land on properties and purchase them if not owned.
- You will pay people money if you land on a property that is owned.
- You will pay tax if you land on one of the tax tiles
- You will collect $200 if you pass go
- You can click on the board properties to see all their details.
- If you run out of money, you will be out of the game.
- If you are the last player, you will win.

## How to Play
1. Run the server application
2. Run two clients
3. On both clients repeat the next steps:
  1. Hit Find Server
  2. Put "localhost" for the server address
  3. Put "9001" for the server port
  4. Type in a unique user name less than 25 characters long (don't unclude fancy characters like spaces)
  5. Pick a unique color from the color picker
  6. Click join
4. Once both clients are in, experiement with sending messages back and forth. You should see message appear live as well as new users joining in live.
5. Then ready both clients up.
6. The game will start.
7. Now roll the dice on the client to which it is their turn.
8. If you land on a property that can be purchased, you will be prompted to buy it. If you land on a chance or community chest nothing will happen (feature not implmented). If you land on tax it will take money away from you.
9. If you rolled a double roll again, otherwise you will notice the roll dice button is now disabled. You can then end you turn. Currently Manage Properties is not implemented.
10. Now roll the dice on the other client.
11. Only unique behaviour now is if you land on a property owned by the other player, you will see that the money is updated.


## Known bugs
This game is not perfect, nor do we have the time to spend the to polish it up. As such I will let you know some known bugs:
1. Due to RMI using the name of the user for a path of its client API, having spaces or special characters in the username will cause issues.
2. Currently, going greater then 4 users will cause a world of trouble, so don't, please.
3. Having users connect, leave, and then connect again is handled slightly, the game won't crash but strange things will happen.
4. Users can currently join the game in progress.
5. Having two clients run at the same time, and then opening a property info modal, will cause an error, it is handled, but you will see it in the console log.

## Footnotes
\**Micro: By micro we mean it lacks some features that Monopoly would have. The reason for the lack of features was due to time and as the project was more based on showing that we can create an working RMI server, then a fully featured game.

