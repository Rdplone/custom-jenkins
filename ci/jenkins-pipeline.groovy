pipeline {
    agent any
    environment {
        REGISTRY_URL = "your.registry.local:5000"
        IMAGE_NAME = "custom-jenkins"
    }
    stages {
        stage('Bump Version') {
            steps {
                sh './scripts/bump-version.sh patch'
                script {
                    env.VERSION = readFile('VERSION').trim()
                }
                sh 'git config user.name "jenkins"'
                sh 'git config user.email "jenkins@local"'
                sh 'git add VERSION'
                sh 'git commit -m "ci: bump version [skip ci]" || echo "No changes to commit"'
                sh 'git push origin HEAD:main || echo "No push performed"'
            }
        }
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
