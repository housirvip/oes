#!/usr/bin/env bash

docker run -d --name oes-config --network oes -p 8000:8000 --cpus 0.5 -m 500m registry.cn-shanghai.aliyuncs.com/housirvip/oes-config:1.1
docker run -d --name oes-user --network oes -p 8510:8510 --cpus 0.5 -m 500m registry.cn-shanghai.aliyuncs.com/housirvip/oes-user:1.1
docker run -d --name oes-exam --network oes -p 8520:8520 --cpus 0.6 -m 600m registry.cn-shanghai.aliyuncs.com/housirvip/oes-exam:1.1
docker run -d --name oes-store --network oes -p 8530:8530 --cpus 0.4 -m 400m registry.cn-shanghai.aliyuncs.com/housirvip/oes-store:1.1
docker run -d --name oes-support --network oes -p 8540:8540 --cpus 0.4 -m 400m registry.cn-shanghai.aliyuncs.com/housirvip/oes-support:1.1
docker run -d --name oes-gateway --network oes -p 8080:8080 --cpus 0.6 -m 600m registry.cn-shanghai.aliyuncs.com/housirvip/oes-gateway:1.1