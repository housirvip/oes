#!/usr/bin/env bash

function docker_pull(){
	docker pull registry.cn-shanghai.aliyuncs.com/housirvip/oes-$2:$1
    if [[ $? -eq 0 ]]; then
        echo "$2 $1 docker image pull successful";
        return 0
    else
        echo "$2 $1 docker image pull failed"
        return -1
    fi
}

if [[ ! $2 ]]
then
    array=(config user exam store support)
    for((i=0;i<${#array[@]};i++))
    do
        docker_pull $1 ${array[i]}
    done
else
    docker_pull $1 $2
fi
