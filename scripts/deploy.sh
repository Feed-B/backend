BUILD_JAR=$(ls /home/[EC2 경로]/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "> build 파일명: $JAR_NAME" >> /home/[EC2 경로]/deploy.log #deploy.log에 기록 남기기

echo "> build 파일 복사" >> /home[EC2경로]/deploy.log
DEPLOY_PATH=/home/[EC2 경로]/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/[EC2 경로]/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID  ]
then 
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/[EC2 경로]/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAT 배포" >> /home/[EC2 경로]/deploy.log

nohup java -jar $DEPLOY_JAR >> /home/[EC2 경로]/deploy.log 2>/home/[EC2 경로]/deploy_err.log &
