#!/usr/bin/env bash
set -x
cd "$(dirname "$0")"
export env=${1:-stage}

helm upgrade --install $env lms/ --namespace lms-$env -f lms/$env.yaml
