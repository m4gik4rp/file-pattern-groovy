# File Pattern Groovy
💡 Completed with a duration of **2 hours and 10 minutes** 

💡 Used **'Trunk-based development'** 

💡 Final answer is **FilePattern.groovy**. 

## Description

Hey there! I created two versions of File Pattern Groovy: one for interactive use, called **FilePattern.groovy**, and another for command-line operation, named **CLFilePattern.groovy**. The interactive mode was my **official attempt**, but I realized I missed out on using command line arguments, so I crafted the command-line mode to address that.

Since I'm relatively new to Groovy, I sought guidance from ChatGPT for examples and syntax help. It's been a learning process, and I hope these two versions serve you well!



## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Installation

⚠️ This project used **Java JDK 17.0.2** and **Groovy 5.0.0-alpha-2**.⚠️ 

### Install Java JDK 17.0.2

#### Windows

1. Download the Java JDK 17.0.2 installer from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).
2. Run the installer and follow the on-screen instructions.

#### Linux

```bash
# Install Java JDK 17.0.2
sudo apt install openjdk-17-jdk
```


### Install Groovy 5

#### Windows

1. Download the Groovy 5.0.0-alpha-2 binary distribution from [Groovy Download Page](https://groovy.apache.org/download.html).
2. Extract the downloaded ZIP file to your preferred location.
3. Add the Groovy bin directory to your system's PATH.

#### Linux

```bash
# Install Groovy 5
sudo apt install groovy
```

## Usage
+ For Interactive **(Official attempt)**
```bash
bash runGroovy.sh
```
+ For Command Line
```bash
bash runCLGroovy.sh "<absolute path" "<file name to search>" "<new file name>"
```