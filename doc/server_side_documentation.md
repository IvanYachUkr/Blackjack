# Server-Side Architecture for the Game of 21

## Overview
The server-side architecture of the Game of 21 is designed to efficiently manage game logic, client communications, and session handling. This document delves into the intricate details of each component, illustrating how they collectively form a robust and scalable server system.

## Detailed Component Breakdown

### 1. ServerApp
- **Purpose**: Acts as the central hub for client connections, managing the initiation and termination of client sessions.
- **Functionality**:
    - Listens on a specified port for incoming client connections.
    - For each connection, spawns a new `ClientHandler` thread, ensuring concurrent handling of multiple client interactions.
- **Design Considerations**:
    - Implements efficient socket management to handle high volumes of simultaneous connections.
    - Utilizes thread pools to optimize resource usage and performance.

### 2. ClientHandler
- **Role**: Manages direct communication with individual clients.
- **Key Responsibilities**:
    - Establishes and maintains a socket connection with the client.
    - Utilizes `RequestHandler` to interpret and process incoming commands.
    - Employs `ResponseHandler` to format and dispatch responses back to the client.
- **Concurrency Aspects**:
    - Operates in its own thread, allowing for independent and simultaneous client interactions.
    - Ensures thread-safe operations, particularly when accessing shared resources.

### 3. RequestHandler
- **Function**: Parses and processes client requests.
- **Operations**:
    - Extracts player ID and command from incoming requests.
    - Maps player IDs to their respective `GameSessions` for state management.
    - Delegates command execution to `game.CommandProcessor`.
- **Complexity Handling**:
    - Implements robust parsing logic to handle various command formats.
    - Efficiently manages session states, ensuring consistency and reliability.

### 4. ResponseHandler
- **Purpose**: Formats and sends responses to the client.
- **Capabilities**:
    - Transforms game state updates into client-readable formats.
    - Efficiently manages network I/O for sending responses.
- **Design Focus**:
    - Prioritizes low-latency communication.
    - Ensures data integrity and accuracy in responses.

### 5. game.GameSession
- **Overview**: Represents an individual game session.
- **Components**:
    - Manages player and dealer hands.
    - Controls the game logic, including card dealing and scoring.
    - Maintains the state of the deck.
- **Session Management**:
    - Isolates each game session, preventing interference between different player sessions.
    - Tracks game progress and player actions.

### 6. game.CommandProcessor
- **Role**: Translates client commands into game actions.
- **Functionality**:
    - Interprets commands and invokes corresponding methods on `game.GameSession`.
    - Handles command validation and execution.
- **Complex Command Handling**:
    - Deals with a variety of game-related commands, ensuring accurate execution.

### 7. Game Entities (Deck, Card, Hand, Player, GameStats)
- **Deck**:
    - Manages a collection of playing cards.
    - Handles card shuffling and distribution.
- **Card**:
    - Represents individual playing cards with suit and value.
- **Hand**:
    - Manages the cards held by a player or dealer.
    - Calculates hand values and determines game outcomes.
- **Player**:
    - Encapsulates player-specific actions and decisions.
- **GameStats**:
    - Records and analyzes game statistics for performance tracking.

## Concurrency Model
- **Multi-Client Support**: Utilizes multi-threading to handle numerous client connections concurrently.
- **Thread Safety**: Employs synchronization mechanisms to ensure safe access to shared resources, particularly in `GameSessions`.

## System Interaction Flow
- **Client Connection**: Upon connection, `ServerApp` initiates a `ClientHandler`.
- **Request Processing**: `ClientHandler` receives and forwards requests to `RequestHandler`.
- **Command Execution**: `RequestHandler` processes the request, updating the `game.GameSession`.
- **Response Dispatch**: `ResponseHandler` sends the updated game state to the client.
- **Continuous Loop**: This process repeats for each client interaction, maintaining continuous communication.
