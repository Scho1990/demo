pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK JAVA'
    }

    parameters {
        choice(
            name: 'TESTNG_FILE',
            choices: ['testng.xml'],
            description: 'Select TestNG suite'
        )
        string(
            name: 'ENV',
            defaultValue: 'qa',
            description: 'Execution environment'
        )
    }

    triggers {
        cron('H 1 * * *')
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {

        stage('Verify Tools') {
            steps {
                bat '''
                java -version
                mvn -version
                '''
            }
        }

        stage('Build & Execute Tests') {
            steps {
                bat """
                mvn clean test ^
                -DsuiteXmlFile=${params.TESTNG_FILE} ^
                -Denv=${params.ENV}
                """
            }
        }

        stage('Publish HTML Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'target',
                    reportFiles: '**/*.html',
                    reportName: 'Automation Test Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: true
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*.html', fingerprint: true
        }

        success {
            emailext(
                subject: "SUCCESS - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Automation execution completed successfully.",
                to: "it.santosh68@gmail.com",
                mimeType: 'text/html'
            )
        }

        failure {
            emailext(
                subject: "FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Automation execution failed. Check Jenkins console.",
                to: "it.santosh68@gmail.com",
                mimeType: 'text/html'
            )
        }
    }
}
