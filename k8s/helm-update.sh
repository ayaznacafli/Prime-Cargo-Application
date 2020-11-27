#!/usr/bin/env sh
set -x
cd "$(dirname "$0")"
export env=${1:-stage}

#Enable only for the first time to configure ingress controller
#helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
#helm upgrade --install ingress-$env --namespace lms-$env ingress-nginx/ingress-nginx
#kubectl patch service ingress-stage-ingress-nginx-controller -p '{"spec": {"type": "LoadBalancer", "externalIPs":["192.168.0.104"]}}'
#kubectl patch service -n istio-system istio-ingressgateway -p '{"spec": {"type": "LoadBalancer", "externalIPs":["192.168.0.104"]}}'
export tag=$(git rev-parse --short HEAD)

helm install $env prime/ --namespace prime-$env --set services.gitTag=$tag -f prime/$env.yaml  --dry-run --debug

helm upgrade --install $env prime/ --namespace prime-$env --set services.gitTag=$tag -f prime/$env.yaml

