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
                sh '''
                  git config user.name "jenkins"
                  git config user.email "jenkins@local"
                  git add VERSION
                  git commit -m "ci: bump version to ${VERSION} [skip ci]" || echo "No changes to commit"
                  git push origin HEAD:main || echo "No push performed"
                  git tag -a "v${VERSION}" -m "Release v${VERSION}"
                  git push origin "v${VERSION}" || echo "No tag push performed"
                '''
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
