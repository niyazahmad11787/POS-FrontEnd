pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/your/repo.git', branch: 'main'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/SanitySuite.xml'
            }
        }
        stage('Publish Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: 'reports',
                             reportFiles: 'index.html',
                             reportName: 'Extent Report'])
            }
        }
    }
}
