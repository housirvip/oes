#!/usr/bin/env bash

function docker_run(){
	docker run -d --name oes-$2 --network oes registry.cn-shanghai.aliyuncs.com/housirvip/oes-$2:$1
    if [[ $? -eq 0 ]]; then
        echo "$2 $1 docker run successful";
        return 0
    else
        echo "$2 $1 docker run failed"
        return -1
    fi
}

if [[ ! $2 ]]; then
    array=(user exam store support)
    for((i=0;i<${#array[@]};i++))
    do
        docker_run $1 ${array[i]}
    done
else
    if [[ $2 == "gateway" ]]; then
        docker run -d --name oes-gateway --network oes -p 8080:8080 registry.cn-shanghai.aliyuncs.com/housirvip/oes-gateway:$1
    else
        docker_run $1 $2
    fi
fi

#docker run -d --name oes-config --network oes -p 8000:8000 --cpus 0.5 -m 500m registry.cn-shanghai.aliyuncs.com/housirvip/oes-config:1.1
#docker run -d --name oes-user --network oes -p 8510:8510 --cpus 0.5 -m 500m registry.cn-shanghai.aliyuncs.com/housirvip/oes-user:1.1
#docker run -d --name oes-exam --network oes -p 8520:8520 --cpus 0.6 -m 600m registry.cn-shanghai.aliyuncs.com/housirvip/oes-exam:1.1
#docker run -d --name oes-store --network oes -p 8530:8530 --cpus 0.4 -m 400m registry.cn-shanghai.aliyuncs.com/housirvip/oes-store:1.1
#docker run -d --name oes-support --network oes -p 8540:8540 --cpus 0.4 -m 400m registry.cn-shanghai.aliyuncs.com/housirvip/oes-support:1.1
#docker run -d --name oes-gateway --network oes -p 8080:8080 --cpus 0.6 -m 600m registry.cn-shanghai.aliyuncs.com/housirvip/oes-gateway:1.1