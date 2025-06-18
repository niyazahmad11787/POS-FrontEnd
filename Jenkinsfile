pipeline {
    agent any

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
                testNG(
                    reportFilenamePattern: '**/test-output/testng-results.xml',
                    escapeTestDescp: false,
                    escapeExceptionMsg: false,
                    showFailedBuilds: true
                )
            }
        }
    }
}
