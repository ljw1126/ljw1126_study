

* jenkins
- 경로 /var/lib/jenkins/workspace 
- 


#### 에러 Build step 'Execute shell' marked build as failure


```
#/usr/bin/env bash
ECR_URI=263154317287.dkr.ecr.ap-northeast-2.amazonaws.com/stage-management-system/live
DOCKER_IMG=${ECR_URI}:latest
DATE=$(date '+%Y%m%d-%H%M%S')


aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin $ECR_URI

docker build -t $DOCKER_IMG --build-arg "JAVA_OPTS=-Dspring.profiles.active=live" .

# ECR PUSH IMAGE
docker push $DOCKER_IMG

# ECS RESTART
CLUSTER="data-analysis-web-cluster"
SERVICE="sms-web-service"
aws ecs update-service --cluster ${CLUSTER} --service ${SERVICE} --force-new-deployment
```


#### jenkins에서 ecs task를 blue/green 방식으로 배포하려면 shell script를 어떻게 작성해야 하는지 알려주세요  // 사용안함

1. 뭔가 중복되는 듯한 느낌이 .. LB가 스왑이 된건가?? 

```shell
#!/bin/bash

# Define the AWS region
export AWS_DEFAULT_REGION=your_aws_region

# Define the service name
SERVICE_NAME=your_service_name

# Define the task definition family name
TASK_DEFINITION_FAMILY=your_task_definition_family

# Define the new task definition revision
NEW_TASK_DEFINITION_REVISION=your_new_task_definition_revision

# Define the load balancer target group ARN for blue and green deployments
BLUE_TARGET_GROUP_ARN=your_blue_target_group_arn
GREEN_TARGET_GROUP_ARN=your_green_target_group_arn

# Define the deployment color (blue or green)
DEPLOYMENT_COLOR=blue

# Define the wait time for task deployment (in seconds)
WAIT_TIME=30

# Create a new task definition revision
echo "Creating a new task definition revision..."
NEW_TASK_DEFINITION=$(aws ecs register-task-definition \
  --family $TASK_DEFINITION_FAMILY \
  --revision $NEW_TASK_DEFINITION_REVISION \
  --task-role-arn your_task_role_arn \
  --execution-role-arn your_execution_role_arn \
  --container-definitions "$(cat container_definitions.json)")

# Update the service with the new task definition revision
echo "Updating the $SERVICE_NAME service with the new task definition revision..."
if [ "$DEPLOYMENT_COLOR" == "blue" ]; then
  aws ecs update-service \
    --cluster your_cluster \
    --service $SERVICE_NAME \
    --task-definition $NEW_TASK_DEFINITION_ARN \
    --desired-count 0 \
    --load-balancer-target-group-arns $GREEN_TARGET_GROUP_ARN
else
  aws ecs update-service \
    --cluster your_cluster \
    --service $SERVICE_NAME \
    --task-definition $NEW_TASK_DEFINITION_ARN \
    --desired-count 0 \
    --load-balancer-target-group-arns $BLUE_TARGET_GROUP_ARN
fi

# Wait for the blue/green deployment to complete
echo "Waiting for the blue/green deployment to complete..."
sleep $WAIT_TIME

# Update the service to use the new task definition revision and adjust the load balancer target group
echo "Updating the $SERVICE_NAME service to use the new task definition revision..."
if [ "$DEPLOYMENT_COLOR" == "blue" ]; then
  aws ecs update-service \
    --cluster your_cluster \
    --service $SERVICE_NAME \
    --task-definition $NEW_TASK_DEFINITION_ARN \
    --desired-count your_desired_count \
    --load-balancer-target-group-arns $BLUE_TARGET_GROUP_ARN
  DEPLOYMENT_COLOR=green
else
  aws ecs update-service \
    --cluster your_cluster \
    --service $SERVICE_NAME \
    --task-definition $NEW_TASK_DEFINITION_ARN \
    --desired-count your_desired_count \
    --load-balancer-target-group-arns $GREEN_TARGET_GROUP_ARN
  DEPLOYMENT_COLOR=blue
fi

# Print the current deployment color
echo "The current deployment color is $DEPLOYMENT_COLOR."

```


2. cli 명령어로 하는데 .. // 사용안함

```shell
#!/bin/bash

# Build Docker image
docker build -t <your-image-name>:<version> .

# Push Docker image to the registry
docker push <your-image-name>:<version>

# Create new task definition for blue deployment
ecs-cli compose --file <your-task-definition-blue-file> --project-name <your-project-name> create

# Create new service for blue deployment
ecs-cli create-service --service-name <your-service-name> --cluster <your-cluster-name> --task-definition <your-project-name> --load-balancer-name <your-load-balancer-name> --blue

# Wait for blue service to become stable
ecs-cli compose --project-name <your-project-name> service ps --cluster <your-cluster-name> --service <your-service-name> --desired-status RUNNING --health-status HEALTHY

# Create new task definition for green deployment
ecs-cli compose --file <your-task-definition-green-file> --project-name <your-project-name> create

# Update service to use green task definition
ecs-cli compose --project-name <your-project-name> service up --cluster <your-cluster-name> --service <your-service-name> --target-group-arn <your-target-group-arn> --force-deployment --timeout 10 --ecs-params <your-ecs-params-file> --blue-green

# Wait for green service to become stable
ecs-cli compose --project-name <your-project-name> service ps --cluster <your-cluster-name> --service <your-service-name> --desired-status RUNNING --health-status HEALTHY

# Delete old service for blue deployment
ecs-cli delete-service --service-name <your-service-name>-blue --cluster <your-cluster-name> --force

# Delete old task definition for blue deployment
ecs-cli compose --file <your-task-definition-blue-file> --project-name <your-project-name> delete

```
    (추가 설명)
    위 스크립트에서는 다음과 같은 단계를 거칩니다.

        - Docker 이미지를 빌드하고, 레지스트리에 푸시합니다.
        - 새로운 블루 Task Definition을 생성하고, 새로운 블루 서비스를 생성합니다.
        - 새로운 블루 서비스가 안정화될 때까지 기다립니다.
        - 새로운 그린 Task Definition을 생성하고, 새로운 그린 서비스로 업데이트합니다.
        - 새로운 그린 서비스가 안정화될 때까지 기다립니다.
        - 이전 블루 서비스 및 Task Definition을 삭제합니다.
        - 위 스크립트를 Jenkins의 Shell 스크립트로 사용하여 ECS Task를 Blue/Green 방식으로 배포할 수 있습니다. 단, 위 스크립트에 필요한 값들(<your-image-name>, <version>,          <your-task-definition-blue-file>, 등)은 프로젝트와 배포 구성에 따라 적절하게 수정해야 합니다.