import java.io.File
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class Main {

    def backUpFile(String oldFileName, sourceDirectoryPath) {

        def sourceDirectory = sourceDirectoryPath+ "/" + oldFileName

        // Specify the path to the destination folder
        def destinationFolderPath = "app/src/main/resources/backup/"

        // Create Path objects for the source file and destination folder
        def sourcePath = Paths.get(sourceDirectory)
        def destinationPath = Paths.get(destinationFolderPath)

        // Copy the file to the destination folder
        Files.copy(sourcePath, destinationPath.resolve(sourcePath.fileName), StandardCopyOption.REPLACE_EXISTING)
        printf("File %s has been backed up in %s \n", oldFileName, destinationFolderPath + oldFileName)
    }

    def searchFile(List<String> listOfAllFiles) {

        print("Please Enter the file name You want to search: ")
        def fileName = System.console().readLine()

        // Filter and keep only the elements that contain the substring
        def matchingElements = listOfAllFiles.findAll { element ->
            element.contains(fileName)
        }

        if(matchingElements.isEmpty()){
            println("No matches found.")
        }
        else{
            return matchingElements
        }
        
    }

    def renameFile(List<String> listOfAllFiles, String sourceDirectoryPath){
        
        def matchedFiles = searchFile(listOfAllFiles)

        def counter = 0
            for (file in matchedFiles) {
                printf("file number %d: %s%n", (counter + 1), file)
                counter++
            }

        if(matchedFiles!= null){
            print("Enter the number of the file you want to rename: ")
            def choice = System.console().readLine()

            // Specify the new name for the file
            print("Enter the new name: ")
            def newFileName = System.console().readLine()
            // Specify the path to the existing file
            def oldFilePath = sourceDirectoryPath + "/" + matchedFiles[(choice.toInteger()-1)]

            backUpFile(matchedFiles[(choice.toInteger()-1)],sourceDirectoryPath)

                // Create a File object for the existing file
                def oldFile = new File(oldFilePath)

                // Create a File object for the new file (with the new name)
                def newFile = new File(oldFile.parent, newFileName+".txt")

                // Rename the file
                if (oldFile.renameTo(newFile)) {
                    println("File successfully renamed.")
                } else {
                    println("Failed to rename the file.")
                }
}
        
    }
    

    def mainMenu(List<String> listOfAllFiles, String sourceDirectoryPath) {
        println "Choose an option:"
        println "1. View all files in directory"
        println "2. Search files"
        println "3. Rename"
        println "4. Exit"
        print "Enter your choice: "
        def choice = System.console().readLine()

        if (choice.toInteger() == 1) {
            println("--View all files in directory--")
            println("All txt files: $listOfAllFiles")
        } else if (choice.toInteger() == 2) {
            println("--Search files--")
            def counter = 0
            for (file in searchFile(listOfAllFiles)) {
                printf("file number %d: %s%n", (counter + 1), file)
                counter++
            }
        } else if (choice.toInteger() == 3) {
            println("--Rename--")
            renameFile(listOfAllFiles, sourceDirectoryPath)
        } else if (choice.toInteger() == 4) {
            println("--Exit--")
            println("Program done.")
            return false
        } else {
            println("Invalid choice")
        }
        return true
        
    }

    def start() {
        def control = true

        println("Welcome to text file searcher and replacer!")
        println("Enter the absolute directory path you want to use: ")

        // Specify the path to the directory
        def directoryPath = System.console().readLine()

        // Replace backslashes with forward slashes
        def convertedDirectoryPath = (directoryPath.replace("\\", "/"))

        // Create a File object based on the directory path
        def directory = new File(directoryPath)

        // Check if the specified path exists
        if (directory.exists()) {
            println("Directory exists.")
        } else {
            println("Directory does not exist.")
            return
        }

        while(control){
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
                control = mainMenu(txtFiles, convertedDirectoryPath)
            }
            else {
                println("The specified path is not a directory.")
            } 

        } 
    }
}

// Create an instance of the main class and call the start method
def mainInstance = new Main()
mainInstance.start()
