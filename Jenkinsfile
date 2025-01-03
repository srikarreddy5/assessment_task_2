pipeline {
  agent any

  tools {
    maven 'Maven' // Ensure that 'Maven' is installed and configured in Jenkins under Manage Jenkins > Global Tool Configuration
  }

  environment {
    // Set the path for ChromeDriver explicitly, ensure the path is accessible by Jenkins agent
    CHROME_DRIVER_PATH = 'C:\\Users\\SRIJA\\Downloads\\chromedriver_win32_4\\chromedriver.exe' // Update with the correct path where ChromeDriver is located
  }

  stages {
    stage('Checkout') {
      steps {
        // Cloning the repository from GitHub
        git branch: 'main', url: 'https://github.com/srikarreddy5/assessment_task_2.git'
      }
    }

    stage('Build') {
      steps {
        // Build the project using Maven
        bat 'mvn clean package' // Use 'sh' for Linux/Unix agents
      }
    }

    stage('Test') {
      steps {
        // Running tests
        bat 'mvn test' // Change to 'sh mvn test' for Linux/Unix
      }
    }

    stage('SonarQube Analysis') {
      environment {
        // Ensure this is the correct URL for your SonarQube server
        SONAR_HOST_URL = 'http://192.168.164.58:9000/' // Ensure the URL is correct
      }
      steps {
        // Using credentials for SonarQube login
        withCredentials([string(credentialsId: 'sonarqube_id', variable: 'SONAR_AUTH_TOKEN', sandbox: false)]) {
          withSonarQubeEnv('srikar_reddy') {
            bat 'mvn sonar:sonar -Dsonar.login=%SONAR_AUTH_TOKEN%' // Change to 'sh' if using a Linux/Unix agent
          }
        }
      }
    }

    stage('Warnings Analysis') {
      steps {
        // Record warnings and issues from Maven or Java tools
        recordIssues(
          tools: [java(), maven()], // Ensure Java and Maven tools are set up in Jenkins
          qualityGates: [[threshold: 5, unstable: true]] // Customize this threshold as needed
        )
      }
    }
  }

  post {
    success {
      // Display message upon successful pipeline run
      echo "Pipeline success"
    }
    failure {
      // Display message upon failure
      echo "Pipeline failure"
    }
  }
}
