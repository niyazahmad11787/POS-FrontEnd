pipeline {
    agent any

    environment {
        CONFIG_FILE = 'src/test/resources/env_config.xml'  // Optional if you’re not using it now
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/niyazahmad11787/POS-FrontEnd.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish TestNG Report') {
            steps {
                // Publish TestNG result file, if testng-results.xml exists
                testNG(testResultsPattern: '**/test-output/testng-results.xml', escapeTestDescription: false, escapeExceptionMessages: false, showFailedBuilds: true)
            }
        }
    }
}
