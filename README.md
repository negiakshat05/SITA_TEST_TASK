# SITA_TEST_TASK
<br>
Author: Akshat Negi
<br>
This service takes input files, process them, and move the files into different directories depending on wherther the file is valid or invalid.
<br>
If the input file consists of numerics only in diffferent lines, file is valid.
<br>
If the input file consists alphabets, alphanumerics, or special characters, file is invalid.
<br>
I've create a processFiles method and scheduling it every 5 seconds by adding a cron expression of 5 seconds.
<br>
This method will execute every 5 secs and move the valid and invalid files into different directories.
<br>
Once the files are processed, valid files will move from IN directory to OUT directory with sum of numbers present in file with .OUTPUT appended in file name and to PROCESSED directry with the same input content with .PROCESSED appended in file name.
<br>
Invalid files will move from IN directory to ERROR directory with .ERROR appended in file name.
