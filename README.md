# Testautothon 2018 (STeP-IN Summit 2018)
This repo contains the testautothon step-in 2018 summit challenge solution.

My team from Coviam Tech has won this hackathon.

Team Members:
- [Kumar Abhinav](https://github.com/kabhinav2608)
- [Priyal Jain](https://github.com/priyaljain73)
- [Ravi Beck]()
- [Sajeel](https://github.com/sajeelaafaq1)
- [Viswa]()

The problem is as stated below:

###Test Flow:
1. Google Search for 20 movies listed in Reference by movie name.
2. From the search results, extract the wikipedia page link for the movie.
3. Go to the wikipedia page for a given movie.
4. Extract the name of the director(s) from the page content.
5. Extract Imdb link for the movie from the wikipedia page.
6. Go to IMDB URL for the movie.
7. Extract the director(s) name from Imdb Page.
8. Assert that director information is same on Wikipedia and Imdb for a movie.

###Rules:
1. The 20 movie names should be read from a file along with the movie number. 
2. Google search step (1 & 2) for all 20 movies  should be a one-time activity before the test begins.It should populate an in -memory data structure (no persistent writing to file/DB).This data structure should be used to drive the test method/script.
3. There should be a single test implementation (one script or one method or one feature file) which takes movie names and corresponding Wikipedia URLs as data.Each movie input should be treated as a unique test.
4. Use 20 seperate thread/processes for the above flow for each movie, including the invalid movie name.
5. The test should be implemented in a way that it can either run at the GUI layer or the HTTP layer.The test code should remain same in both the cases and via a configurable option (mode = GUI or HTTP), one should be able to make this choice in case of GUI layer test atleast one thread should run on a mobile device (physical or emulator).
6. Any considerations given about how to do this at scale (e.g. what if you had 1000 threads each with unique movie name) would fetch additional scores.
7. Implement a custom HTML report which should contain a table representing these columns:
    Movie ID, Movie Name, Wikipedia URL, Snapshot of Wikipedia Page, Wikipedia Director(s), Imdb URL , Snapshot of Imdb page, Imdb Director(s), Name differences in addition to any other details you want to showcase. In case of HTTP mode, the snapshot columns should not be present. **Without this report the submission would be disqualified**.
    
###Reference:
1. Non-Existing
2. The Shawshank Redemption
3. The Godfather
4. The Dark Knight
5. Pulp Fiction
6. Schindler's List
7. The Lord of the Rings: The Return of the King
8. The Good, The Bad, The Ugly
9. 12 Angry men
10. Incpetion
11. Forrrest Gump
12. Fight Club
13. Star Wars: Episode V - The Empire Strikes Back
14. Goodfellas
15. The Matrix
16. One Flew Over the Cuckoo's Nest
17. Seven Samurai
18. Avengers: Infinity War
19. Interstellar
20. Se7en