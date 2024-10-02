# Jeopardy Game with GUI
This project was created during my final year of high school for the Aerospace Club, which I was a co-founder of. The purpose was to create a custom program that could would only require the user to import text files containing a series of questions and answers they wanted to use. This would solve the problem of replayability that many online Jeopardy games suffer from. Additionally, programming the game myself would allow for a greater degree of customization.

## How it Works
Three main files control the entire program.
- Main runs the overall logic and calls the other classes
- GUI creates the user interface and is in charge of buttons, panels, windows, colouring, and teams
- QandA reads text files containing the questions and answers and assigns them to the hashmap

As for data structure, there are two boards, question and answer. Each contain a 2-d arraylist with a hashmap that contains the point value and the corresponding question/answer. 

