// FilePattern.groovy

import java.io.File
import java.io.File
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.StandardCopyOption

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

    def backUpFile(String oldFileName){
        def sourceFilePath = "app/src/main/resources/samplefiles/" + oldFileName
        // Specify the path to the destination folder
        def destinationFolderPath = "app/src/main/resources/backup/"
        // Create Path objects for the source file and destination folder
        def sourcePath = Paths.get(sourceFilePath)
        def destinationPath = Paths.get(destinationFolderPath)

        // Copy the file to the destination folder
        Files.copy(sourcePath, destinationPath.resolve(sourcePath.fileName), StandardCopyOption.REPLACE_EXISTING)
        printf("File %s has been backed up in %s \n", oldFileName, destinationFolderPath+oldFileName)
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

            backUpFile(matchingElements[0])


            } else {
            println("The specified path is not a directory.")
        }
    }
}

// Create an instance of the main class and call the start method
def mainInstance = new Main()
mainInstance.start()