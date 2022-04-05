pipeline {
    agent none
    stages {
        // git pull 받아오는 상태
        stage('git pull') {
            agent any
            steps {
                echo "Pulling"
            }
        }

        stage('Docker build') {
            agent any
            steps {
                sh 'docker build -t backend:latest ./BE '
                sh 'docker build -t frontend:latest ./FE '

                // --no-run-if-empty : docker ps -f name=frontend -q 의 결과가 0이면 실행 안함
                sh 'docker ps -f name=frontend -q | xargs --no-run-if-empty docker container stop'
                sh 'docker ps -f name=backend -q | xargs --no-run-if-empty docker container stop'
        
                sh 'docker container ls -a -f name=frontend -q | xargs -r docker container rm'
                sh 'docker container ls -a -f name=backend -q | xargs -r docker container rm'

                sh 'docker images -f dangling=true -q | xargs --no-run-if-empty docker rmi -f' 
            }
        }

        stage('Docker run') {
            agent any
            steps {
                sh 'docker run -d \
                    --name backend \
                    --network SENAGI \
                    -p 8080:8080 \
                    -v /var/backend/keystore:/keystore \
                    -v /var/backend/imgs:/imgs \
                    -v /var/backend/logs:/logs \
                    backend:latest'
                
                sh 'docker run -d \
                    --name frontend \
                    --network SENAGI \
                    -p 80:80 \
                    -p 443:443 \
                    -v /etc/letsencrypt:/etc/letsencrypt \
                    frontend:latest' 
            }
        }
    }
}