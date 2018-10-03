pipeline {
    agent {
        node { label 'android' }
    }

    stages {
        stage('Lint & Unit Test') {
            parallel {
                stage('checkStyle') {
                    steps {
                        sh './gradlew checkStyle'
                    }
                }
            }
        }
    }
}