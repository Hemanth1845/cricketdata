pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Hemanth1845/cricketdata.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('BACKEND') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Deploy Backend to Tomcat') {
            steps {
                dir('BACKEND/target') {
                    // Copy WAR file to Tomcat webapps folder
                    sh 'cp CricketBackend.war /opt/tomcat/webapps/'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontendapp') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Deploy Frontend') {
            steps {
                dir('frontendapp/dist') {
                    // Copy built files to web server (example: nginx or apache)
                    sh 'cp -r * /var/www/html/'
                }
            }
        }
    }
}
