# Client-Side Documentation for Blackjack Game

## Overview
The client-side of the Blackjack game is designed for interaction with the game server, managing user input, and displaying the game state. It comprises several key components, each with a specific role in the application.

## Components

### 1. ClientApp.java
- **Purpose**: Serves as the main entry point for the client application.
- **Key Features**:
    - Initializes all client-side components.
    - Manages the game loop, handling user commands and displaying game state.
- **Methods**:
    - `startGame()`: Starts the game loop, handling user input and server communication.
    - `generateUniqueID()`: Generates a unique ID for the player.
- **[View File](https://github.com/IvanYachUkr/Blackjack/blob/start_code/src/client/ClientApp.java)**

### 2. CommunicationManager.java
- **Purpose**: Manages communication with the server.
- **Key Features**:
    - Sends formatted messages to the server.
    - Receives responses from the server.
- **Methods**:
    - `communicate(String command)`: Sends a command to the server and returns the response.
- **[View File](https://github.com/IvanYachUkr/Blackjack/blob/start_code/src/client/CommunicationManager.java)**

### 3. ConnectionManager.java
- **Purpose**: Handles the network connection to the server.
- **Key Features**:
    - Manages the socket connection.
    - Sends and receives raw messages.
- **Methods**:
    - `openConnection()`: Opens a connection to the server.
    - `sendToServer(String message)`: Sends a message to the server.
    - `receiveFromServer()`: Receives a message from the server.
    - `closeConnection()`: Closes the network connection.
- **[View File](https://github.com/IvanYachUkr/Blackjack/blob/start_code/src/client/ConnectionManager.java)**

### 4. DisplayManager.java
- **Purpose**: Manages the display of game state and messages.
- **Key Features**:
    - Displays the current state of the game to the user.
- **Methods**:
    - `displayGameState(String gameState)`: Displays the game state.
- **[View File](https://github.com/IvanYachUkr/Blackjack/blob/start_code/src/client/DisplayManager.java)**

### 5. GameInitialiser.java
- **Purpose**: Initializes the game on the client side.
- **Key Features**:
    - Fetches game rules from the server.
    - Displays game rules to the user.
- **Methods**:
    - `initializeGame()`: Initializes the game and fetches rules from the server.
- **[View File](https://github.com/IvanYachUkr/Blackjack/blob/start_code/src/client/GameInitialiser.java)**

### 6. MessageFormatter.java
- **Purpose**: Formats messages to be sent to the server.
- **Key Features**:
    - Prepares messages with the player's ID and command.
- **Methods**:
    - `formatMessage(String command)`: Formats a command with the player's ID.
- **[View File](https://github.com/IvanYachUkr/Blackjack/blob/start_code/src/client/MessageFormatter.java)**

### 7. UserInputHandler.java
- **Purpose**: Handles user input from the console.
- **Key Features**:
    - Reads commands from the user.
- **Methods**:
    - `getUserCommand()`: Reads a command from the user.
- **[View File](https://github.com/IvanYachUkr/Blackjack/blob/start_code/src/client/UserInputHandler.java)**


## Overview
The client-side of the Blackjack game is designed for robust interaction with the server, efficient handling of user input, and dynamic display of the game state. It employs a modular architecture, ensuring each component focuses on a specific aspect of the application, facilitating maintainability and scalability.

## Architecture and Design Choices

### Modular Design
- The client application is divided into distinct modules: `ClientApp`, `CommunicationManager`, `ConnectionManager`, `DisplayManager`, `GameInitialiser`, `MessageFormatter`, and `UserInputHandler`.
- This separation of concerns allows for independent development and testing of each module.

### Network Communication
- The `ConnectionManager` and `CommunicationManager` handle all network-related activities, emphasizing a clear separation between the game logic and network operations.

### User Interface
- `DisplayManager` is dedicated to presenting information to the user, ensuring that the user interface is decoupled from the game logic.

### Data Pipeline Process
- **User Input**: User commands are captured by `UserInputHandler` and passed to `ClientApp`.
- **Command Processing**: `ClientApp` processes these commands, often involving communication with the server via `CommunicationManager`.
- **Server Communication**: `CommunicationManager` uses `ConnectionManager` to send and receive data from the server.
- **Response Handling**: Server responses are received by `CommunicationManager` and relayed back to `ClientApp`.
- **Display Updates**: `ClientApp` updates the game state and instructs `DisplayManager` to present the latest information to the user.

## Detailed Component Breakdown

### ClientApp.java
- **Functionality**: Orchestrates the overall game flow on the client side.
- **Design Choice**: Acts as a central hub, coordinating between user input, server communication, and display updates.
- **Methods**:
    - `startGame()`: Initiates the game loop.
    - `generateUniqueID()`: Creates a unique identifier for the session.

### CommunicationManager.java
- **Functionality**: Manages the sending and receiving of formatted messages to/from the server.
- **Design Choice**: Abstracts the complexity of server communication from the rest of the client application.
- **Methods**:
    - `communicate(String command)`: Facilitates communication with the server.

### ConnectionManager.java
- **Functionality**: Handles low-level network operations.
- **Design Choice**: Isolates network connectivity, allowing for flexible changes to network protocols or configurations.
- **Methods**:
    - `openConnection()`, `sendToServer(String message)`, `receiveFromServer()`, `closeConnection()`: Manage network connections and data transmission.

### DisplayManager.java
- **Functionality**: Responsible for all user interface interactions.
- **Design Choice**: Separates UI concerns, enabling potential future enhancements like GUI implementation.
- **Methods**:
    - `displayGameState(String gameState)`: Updates the display based on the current game state.

### GameInitialiser.java
- **Functionality**: Sets up the game environment on the client side.
- **Design Choice**: Centralizes game initialization tasks, simplifying the startup process.
- **Methods**:
    - `initializeGame()`: Prepares the game for the user.

### MessageFormatter.java
- **Functionality**: Formats messages for server communication.
- **Design Choice**: Ensures consistent message structure, aiding in reliable server communication.
- **Methods**:
    - `formatMessage(String command)`: Formats user commands for transmission.

### UserInputHandler.java
- **Functionality**: Captures and processes user input.
- **Design Choice**: Decouples user input handling from game logic, allowing for flexible input methods.
- **Methods**:
    - `getUserCommand()`: Retrieves commands from the user.