#!/usr/bin/env bash

function docker_rm(){
	docker rm oes-$1
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
    array=(gateway user exam store support config)
    for((i=0;i<${#array[@]};i++))
    do
        docker_rm ${array[i]}
    done
else
    docker_rm $1
fi
