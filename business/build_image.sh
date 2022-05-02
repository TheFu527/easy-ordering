set -ex
#docker pull openjdk:11.0-oracle
cd business-release/build
docker build -t fuhao527/easy-ordering-business:latest -f Dockerfile .
#docker push fuhao527/easy-ordering-business:latest