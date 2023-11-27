
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

    def searchFile(List<String> listOfAllFiles, String textPattern) {

        def fileName = textPattern

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

    def renameFile(List<String> listOfAllFiles, String sourceDirectoryPath, String textPattern, String replacerTextPattern){
        def logDirectory = "app/src/main/resources/logs"
        def logFileName = "rename_logs.txt"
        def logFilePath = "${logDirectory}/${logFileName}"

        def matchedFiles = searchFile(listOfAllFiles, textPattern)

        def updatedFileNames = matchedFiles.collect { fileName ->
            fileName.replaceAll(textPattern, replacerTextPattern)
        }

        // Log the process flow to a text file
        def logFile = new File(logFilePath)
        logFile.text = "Renaming process started at ${new Date()}\n"

        // Backup and rename files
        for (int i = 0; i < matchedFiles.size(); i++) {
            def fileName = matchedFiles[i]
            def updatedFileName = updatedFileNames[i]

            try {
                // Backup the file before renaming
                backUpFile(fileName,sourceDirectoryPath)

                def oldFilePath = sourceDirectoryPath + "/" + fileName
                println("old file path is ${oldFilePath}")

                // Log the renaming action
                logFile.append("Renaming file: $fileName to $updatedFileName\n")

                // Create a File object for the existing file
                def oldFile = new File(oldFilePath)

                // Create a File object for the new file (with the new name)
                def newFile = new File(oldFile.parent, updatedFileName)

                // Rename the file
                if (oldFile.renameTo(newFile)) {
                    println("File successfully renamed.")
                } else {
                    println("Failed to rename the file.")
                }

                // Log pattern found information
                if (fileName.contains(replacerTextPattern)) {
                    logFile.append("Pattern 'text' found in file: $fileName\n")
                }
            } catch (Exception e) {
                // Log errors
                logFile.append("Error processing file $fileName: ${e.message}\n")
                println("Error processing file $fileName: ${e.message}")
            }

        }
        logFile.append("Renaming process completed at ${new Date()}\n")

    }
    

    def mainMenu(List<String> listOfAllFiles, String sourceDirectoryPath, String textPattern, String replacerTextPattern) {
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
            for (file in searchFile(listOfAllFiles, textPattern)) {
                printf("file number %d: %s%n", (counter + 1), file)
                counter++
            }
        } else if (choice.toInteger() == 3) {
            println("--Rename--")
            renameFile(listOfAllFiles, sourceDirectoryPath)
        } else if (choice.toInteger() == 4) {
            println("--Exit--")
            println("Program done.")
            control = false
        } else {
            println("Invalid choice")
        }
        return true
        
    }

    def start(args) {
        def control = true
        String directoryPath

        if (args.size() >= 3) {

            // Access command line arguments directly
            directoryPath = args[0]
            String textPattern = args[1]
            String replacerTextPattern = args[2]

            // Rest of your function code using directoryPath, textPattern, and replacerTextPattern
            println "Directory Path: $directoryPath"
            println "Text Pattern: $textPattern"
            println "Replacer Text Pattern: $replacerTextPattern"

            // Replace backslashes with forward slashes
            def convertedDirectoryPath = (directoryPath.replace("\\", "/"))

            // Create a File object based on the directory path
            def directory = new File(directoryPath)

            // Check if the specified path exists
            if (directory.exists()) {
                println("Directory exists.")
                def fileList = directory.listFiles()
                // Get the list of file names in the directory that end with '.txt'
                def txtFiles = directory.listFiles { file ->
                    file.isFile() && file.name.endsWith('.txt')
                }.collect { file ->
                    file.name
                }

                renameFile(txtFiles,convertedDirectoryPath,textPattern,replacerTextPattern)
            } else {
                println("Directory does not exist.")
                return
            }
        } else {
            println "Not enough command line arguments provided. Usage: script.groovy <directoryPath> <textPattern> <replacerTextPattern>"
        }    

    }
}

// Create an instance of the main class and call the start method
def mainInstance = new Main()
mainInstance.start(args)
