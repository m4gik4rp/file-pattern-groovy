// FilePattern.groovy

import java.io.File

class Main {

    def userInpuTaker(String userInput) {
        // Your method implementation
    }

    def mainMenu() {
        println("Welcome to text file searcher and replacer")
        print("Please Enter the file name You want to search: ")
        def userInput = System.console().readLine()
        return userInput
    }

    def backUpFile(){

    }

    def start() {
        // Call mainMenu and use as regex pattern
        def fileName = mainMenu()

        // Specify the path to the directory
        def directoryPath = "app/src/main/resources/samplefiles"

        // Create a File object based on the directory path
        def directory = new File(directoryPath)

        // Check if the specified path exists
        if (directory.exists()) {
            println("Directory exists.")
        } else {
            println("Directory does not exist.")
        }


        // Check if the specified path is a directory
        if (directory.isDirectory()) {
            // Get the list of files in the directory
            def fileList = directory.listFiles()

           // Get the list of file names in the directory that end with '.txt'
            def txtFiles = directory.listFiles { file ->
                file.isFile() && file.name.endsWith('.txt')
            }.collect { file ->
                file.name
            }

            println("Matching files: $txtFiles")
            // Filter and keep only the elements that contain the substring
            def matchingElements = txtFiles.findAll { element ->
                element.contains(fileName)
            }
            
            def counter = 0
            for (element in matchingElements) {
                printf("file number %d: %s%n", (counter+1), element, counter++)
            }


            } else {
            println("The specified path is not a directory.")
        }
    }
}

// Create an instance of the main class and call the start method
def mainInstance = new Main()
mainInstance.start()