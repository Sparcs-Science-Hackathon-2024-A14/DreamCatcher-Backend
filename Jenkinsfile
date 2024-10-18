pipeline {
    agent any
    tools {
        jdk 'java' // JDK 이름이 Jenkins에서 정의된 이름과 일치하는지 확인하세요
        gradle 'gradle' // Gradle 이름이 Jenkins에서 정의된 이름과 일치하는지 확인하세요
    }

    environment {
        DOCKER_IMAGE = "$DOCKER_USERNAME/bepo:latest" // Docker 이미지 이름 설정
    }

    stages {
        stage('Check Java Version') {
            steps {
                script {
                    sh 'java -version' // 현재 Java 버전 확인
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout scm // 소스 코드 체크아웃
            }
        }

        stage('Set Permissions') {
            steps {
                script {
                    sh 'chmod +x ./gradlew' // gradlew 실행 권한 설정
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    sh './gradlew build' // Gradle 빌드 실행
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    sh './gradlew test' // 테스트 실행
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지 빌드
                    sh 'docker build -t $DOCKER_IMAGE .' // Docker 이미지 이름을 환경 변수로 설정
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Docker Hub에 로그인
                    sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin' // 환경변수를 사용한 로그인

                    // Docker 이미지 푸시
                    sh 'docker push $DOCKER_IMAGE' // 환경 변수로 설정한 Docker 이미지 푸시
                }
            }
        }
    }
}

