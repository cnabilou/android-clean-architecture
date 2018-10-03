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

                stage('Unit Test') {
                    steps {
                        sh './gradlew testStagingDebug'
                    }
                }
            }
        }
    }
}