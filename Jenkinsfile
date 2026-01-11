pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK JAVA'
    }

    parameters {
        choice(
            name: 'TESTNG_FILE',
            choices: [
                'testng.xml'
            ],
            description: 'Select TestNG suite to execute'
        )

        string(
            name: 'ENV',
            defaultValue: 'qa',
            description: 'Execution environment'
        )
    }

    triggers {
        cron('H 1 * * *')   // Nightly execution at ~1 AM
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
        REPORT_DIR = 'target/surefire-reports'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Scho1990/demo.git'
            }
        }

        stage('Build & Execute Tests') {
            steps {
                sh """
                mvn clean test \
                -DsuiteXmlFile=${params.TESTNG_FILE} \
                -Denv=${params.ENV}
                """
            }
        }

        stage('Publish HTML Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'Automation Test Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
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
                subject: "✅ Nightly Automation SUCCESS - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <p>Automation execution completed successfully.</p>
                <p><b>Suite:</b> ${params.TESTNG_FILE}</p>
                <p><b>Environment:</b> ${params.ENV}</p>
                <p>
                    <a href="${env.BUILD_URL}HTML_20Report">
                    View HTML Report
                    </a>
                </p>
                """,
                to: "it.santosh68@gmail.com",
                mimeType: 'text/html'
            )
        }

        failure {
            emailext(
                subject: "❌ Nightly Automation FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <p>Automation execution failed.</p>
                <p><b>Suite:</b> ${params.TESTNG_FILE}</p>
                <p><b>Environment:</b> ${params.ENV}</p>
                <p>
                    <a href="${env.BUILD_URL}">
                    Check Jenkins Console & Report
                    </a>
                </p>
                """,
                to: "it.santosh68@gmail.com",
                mimeType: 'text/html'
            )
        }
    }
}
