pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure 'Maven' is installed and configured in Jenkins under Manage Jenkins > Global Tool Configuration
    }

    environment {
        // Correctly specify the environment variables
        CHROME_DRIVER_PATH = 'C:\\Users\\SRIJA\\Downloads\\chromedriver_win32_4\\chromedriver.exe' // Update this with the correct path
        SONAR_HOST_URL = 'http://192.168.164.58:9000/' // Ensure this is the correct URL for your SonarQube server
        SONAR_AUTH_TOKEN = credentials('sonarqube_id') // This fetches the SonarQube token from Jenkins Credentials
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloning the repository from GitHub, specifying the branch explicitly
                echo "Cloning repository..."
                git branch: 'main', url: 'https://github.com/srikarreddy5/assessment_task_2.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building the project with Maven..."
                bat 'mvn clean package' // For Linux/Unix systems, change to 'sh mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo "Running the tests..."
                bat 'mvn test' // Change to 'sh mvn test' for Linux/Unix if necessary
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Ensure SonarQube environment is available
                    withSonarQubeEnv('srikar_reddy') {  // Ensure 'srikar_reddy' is the name of the SonarQube server configured in Jenkins
                        // Run the Maven goal to trigger SonarQube analysis
                        echo "Running SonarQube analysis..."
                        bat 'mvn sonar:sonar -Dsonar.login=%SONAR_AUTH_TOKEN%' // Ensure SonarQube login token is handled securely
                    }
                }
            }
        }

        stage('Warnings Analysis') {
            steps {
                echo "Analyzing warnings..."
                // Record warnings and issues from Maven or Java tools
                recordIssues(
                    tools: [java(), maven()], // Ensure Java and Maven tools are set up in Jenkins
                    qualityGates: [[threshold: 5, unstable: true]] // Customize this threshold as needed
                )
            }
        }

        stage('JUnit Test Report') {
            steps {
                echo "Publishing JUnit test results..."
                junit '**/target/test-*.xml'  // Adjust path as necessary based on where Maven places the test reports
            }
        }
    }

    post {
        success {
            echo "Pipeline succeeded!"
        }
        failure {
            echo "Pipeline failed!"
        }
    }
}
