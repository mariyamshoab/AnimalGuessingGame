# FA-Based Animal Guessing Game

A fun, interactive command-line game that guesses the animal you’re thinking of using **Finite Automata**.  
Built in **Java**, this project demonstrates how formal language theory and deterministic finite automata (DFA) can model real-world problem-solving in an explainable way.
## Demo

Think of an animal and answer the following questions with `y` or `n`:

## How It Works

1. **Questions = DFA inputs** (`Y` / `N` for each answer).
2. **Animals = DFA accepting states**, each defined by a sequence of answers (a string).
3. **User input** builds a string of `Y`/`N`.
4. **Matching string** = DFA reaches an accepting state → correct animal guessed.

This demonstrates **Finite Automata in action**:
- Alphabet: `{Y, N}`
- Each animal represents a **unique language**
- Input string accepted → correct animal identified
## Features

- Fully deterministic, rule-based guessing engine
- Simple CLI interface
- Easy to extend with more animals and questions
- Explains how answers lead to the guess (can be added in next upgrade)
## How to Run

1. Open terminal in `src` folder
2. Compile Java file:
```bash
javac AnimalGuessingGame.java
```
## Working
![Working](images/game_demo.png)




