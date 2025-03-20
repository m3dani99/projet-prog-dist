# restart minikube
minikube stop
minikube delete
minikube start --driver=hyperkit

#istio setup
cd istio-*/
export PATH=$PWD/bin:$PATH
istioctl install --set profile=demo -y
kubectl label namespace default istio-injection=enabled
kubectl get pods -n istio-system

# deploy services
kubectl apply -f mysql-deployment.yaml
kubectl apply -f restaurant-api-deployment.yaml
kubectl apply -f booking-api-deployment.yaml
kubectl apply -f istio-gateway.yaml
kubectl apply -f istio-virtualservice.yaml

#check running services
kubectl get services

