pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6' // Make sure this matches the Maven version configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/niyazahmad11787/POS-FrontEnd',
                        credentialsId: '0b54b1ff-30be-4974-881f-172c6243cf8c'
                    ]]
                ])
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Running Maven clean and test using SanitySuite.xml...'
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/SanitySuite.xml'
            }
        }

        stage('Publish Report') {
            steps {
                echo 'Publishing Extent HTML Report...'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'index.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }

        failure {
            echo 'Pipeline failed. Please check the logs and reports for details.'
        }
    }
}
