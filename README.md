In the financial world we're operating on a term "financial instrument". You can think of it as of a collection of prices of currencies, commodities, derivatives, etc.

For the purpose of this exercise we provide you an input file with multiple time series containing prices of instruments:

-	INSTRUMENT1
-	INSTRUMENT2
-	INSTRUMENT3

File format is as follows:

<INSTRUMENT_NAME>,<DATE>,<VALUE>

For instance:

INSTRUMENT1,12-Mar-2015,12.21

TASK:

Read time series from the file provided and pass all of them to the "calculation module".

Calculation engine needs to calculate:

For INSTRUMENT1 – mean
For INSTRUMENT2 – mean for November 2014
For INSTRUMENT3 – any other statistical calculation that we can compute "on-the-fly" as we read the file (it's up to you)
For any other instrument from the input file - sum of the newest 10 elements


What needs to be done:

-	Create the maven project.
-	Model the problem using OO principles.
-	Test the solution.
-	Send the source code back to us (don't include any executables or jars !)

Remember:

-	Input data is most likely not sorted.
-	We can assume that current date is 19-Dec-2014 – so we’re not expecting any data after that date.
-	Bear in mind that your solution should also work for a file that has many gigabytes of data (with 10k instruments instead of 3); your solution should be capable of handling that much of data !
-   Assume it's vitally important to calculate the abovementioned metrics as quickly as possible; your solution should scale effectively 
-	Validate the date - it should be a business date, i.e. Monday – Friday.
- 	Provide a quick description of your solution: how to run and test it, plus some info about important design decisions etc. 

Technical requirements:

-	Use any JVM version you feel most comfortable with and not older than 1.6 :-)
-	You’re allowed to use Open Source libraries and frameworks of your choice, so you’re free to use Mockito, JUnit, Guava, Apache Commons, Spring etc.
    But please - do not try to show off and don't 'over engineere'; your solution won't be evaluated on the basis of the number of frameworks used but rather on the beauty of your design and the efficiency of the algorithms used.  
-	We must be able to build and run your program simply by using Maven; in particular:
               * we won't be able to evaluate your solution if it does depend on proprietary libraries we don't have licences for; 
			   * if you plan to use some external servers on which you'd like to host your application, then those servers must be launched and stopped as part of your implementation; you can't expect us to set up the environment externally;
	
Hints: 

-   when we say that your solution should be able to process gigabytes of data - we really mean it :-); think about some ways of making sure that you won't end up getting OutOfMemory errors any time you'll try to process a huge input file;	



ABOVE SPECIFICATION IS AND ALWAYS WILL BE USED FOR CANDIDATE SKILLS EVALUATION ONLY.