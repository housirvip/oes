#!/usr/bin/env bash

function docker_stop(){
	docker stop oes-$1
    if [[ $? -eq 0 ]]; then
        echo "$1 docker stop successful";
        return 0
    else
        echo "$1 docker stop failed"
        return -1
    fi
}

if [[ ! $1 ]]
then
    array=(config user exam store support)
    for((i=0;i<${#array[@]};i++))
    do
        docker_stop ${array[i]}
    done
else
    docker_stop $1
fi
