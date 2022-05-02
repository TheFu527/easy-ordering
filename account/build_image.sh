set -ex
#docker pull openjdk:11.0-oracle
cd account-release/build
docker build -t fuhao527/easy-ordering-account:latest -f Dockerfile .
#docker push fuhao527/easy-ordering-account:latest