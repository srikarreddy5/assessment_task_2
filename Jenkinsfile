pipeline {
  agent any
  tools {
    maven 'Maven' // Ensure this is configured in Jenkins
  }
  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/srikarreddy5/assessment_task_2.git'
      }
    }
    stage('Build') {
      steps {
        bat 'mvn clean package' // Adjust this if using Linux/Unix, change to 'sh' if needed
      }
    }
    stage('Test') {
      steps {
        bat 'mvn test' // Adjust if needed
      }
    }
    stage('SonarQube Analysis') {
      environment {
        SONAR_HOST_URL = 'http://192.168.164.58:9000/' // Fixed extra 'http://'
      }
      steps {
        withCredentials([string(credentialsId: 'sonarqube_id', variable: 'SONAR_AUTH_TOKEN')]) {
          withSonarQubeEnv('srikar_reddy') {
            bat 'mvn sonar:sonar -Dsonar.login=%SONAR_AUTH_TOKEN%'
          }
        }
      }
    }
    stage('Warnings Analysis') {
      steps {
        recordIssues(
          tools: [java(), maven()],
          qualityGates: [[threshold: 5, unstable: true]] // Customize as per your thresholds
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
