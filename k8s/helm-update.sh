#!/usr/bin/env bash
set -x
cd "$(dirname "$0")"
export env=${1:-stage}

helm upgrade --install $env prime/ --namespace prime-$env -f prime/$env.yaml
