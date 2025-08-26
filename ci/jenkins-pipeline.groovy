pipeline {
    agent any
    environment {
        REGISTRY_URL = "your.registry.local:5000"
        IMAGE_NAME = "custom-jenkins"
        VERSION = readFile('VERSION').trim()
    }
    stages {
        stage('Version Check') {
            steps {
                sh './scripts/version-check.sh'
            }
        }
        stage('Build') {
            steps {
                sh './scripts/build.sh'
            }
        }
        stage('Push') {
            steps {
                sh './scripts/push.sh'
            }
        }
    }
}
