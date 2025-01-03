pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure 'Maven' is installed and configured in Jenkins under Manage Jenkins > Global Tool Configuration
    }

    environment {
        // Path to ChromeDriver if using Selenium
        CHROME_DRIVER_PATH = 'C:\\Users\\SRIJA\\Downloads\\chromedriver_win32_4\\chromedriver.exe' // Update this with the correct path
        SONAR_HOST_URL = 'http://192.168.164.58:9000/' // Ensure this is the correct URL for your SonarQube server
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloning the repository from GitHub, specifying the branch explicitly
                git branch: 'main', url: 'https://github.com/srikarreddy5/assessment_task_2.git'
            }
        }

        stage('Build') {
            steps {
                // Run Maven clean package
                bat 'mvn clean package' // For Linux/Unix systems, change to 'sh mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Run Maven test
                bat 'mvn test' // Change to 'sh mvn test' for Linux/Unix if necessary
            }
        }

        stage('SonarQube Analysis') {
            environment {
                // Ensure that 'sonarqube_id' is the correct credentialsId for SonarQube token in Jenkins credentials
            }
            steps {
                script {
                    // Ensure SonarQube environment is available
                    withSonarQubeEnv('srikar_reddy') {  // Ensure 'srikar_reddy' is the name of the SonarQube server configured in Jenkins
                        // Run the Maven goal to trigger SonarQube analysis
                        // Make sure sonar.projectKey and sonar.projectName are properly set in your Maven POM or here as properties
                        bat 'mvn sonar:sonar -Dsonar.login=%SONAR_AUTH_TOKEN%' // Ensure SonarQube login token is handled securely
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
            echo "Pipeline success"
        }
        failure {
            echo "Pipeline failure"
        }
    }
}
