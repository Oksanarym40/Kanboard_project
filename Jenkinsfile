pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
    }
    triggers {
        cron('0 8 * * *')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'New_branch', url: 'https://github.com/Oksanarym40/Kanboard_project.git'
            }
        }
        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                // Крок для виконання тестів
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }
    }
    post {
        // Пост-операції, що виконуються після завершення всіх стадій
        // Наприклад, відправлення повідомлення або розсилка результатів
        always {
            // Прибираємо за собою: видаляємо створений директорій target
            deleteDir()
        }
        success {
            // Якщо збірка завершилася успішно, робимо щось...
            echo "Build successful! Congrats!"
        }
        failure {
            // Якщо збірка не пройшла успішно, робимо щось...
            echo "Build failed. Please check the logs."
        }
    }
}
