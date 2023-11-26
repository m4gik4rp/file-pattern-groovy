// FilePattern.groovy

import java.io.File

class Main {

    def userInpuTaker(String userInput) {
        // Your method implementation
    }

    def mainMenu() {
        println("Welcome to text file searcher and replacer")
    }

    def start() {
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

            // Get the list of files in the directory
            def txtFiles = directory.listFiles { file ->
                file.isFile() && file.name.endsWith('.txt')
            }

            // Print the names of the .txt files
            txtFiles.each { txtFile ->
                println(txtFile.name)
            }
            
            } else {
            println("The specified path is not a directory.")
        }
    }
}

// Create an instance of the main class and call the start method
def mainInstance = new Main()
mainInstance.start()
