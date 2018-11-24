# drug-trace-system
基于区块链的药品溯源系统

## 主要功能模块
1.用户的注册

2.对某一次的药品的物流进行溯源操作

## 使用说明
### 运行环境
* 本机系统Ubuntu16.04
* 区块链Fabric版本:1.0.0
* go version 1.8.3
* docker version 17.03.2-ce
* docker-compose version 1.8.0
### 搭建环境
* 首先必须要搭建好完整的Fabric运行环境
* 并跑通官方测试用例,以下是参考的博客
    * [初学Hyperledger-Fabric网络搭建](https://blog.csdn.net/Box_clf/article/details/82534469)
    * [fabric-java-sdk测试用例调试](https://blog.csdn.net/Box_clf/article/details/82683417)
    * [相关搭链问题](https://blog.csdn.net/Box_clf/article/details/82588062)
* 上述步骤是保证demo有完整的运行环境,如果早已搭建完成可以跳过阅读
* 主要步骤:
    * 创建home目录下创建工程项目文件夹:` mkdir -p /go/src/github.com/hyperledger/`
    * clone后需要更改对应的GOPATH路径: `export GOPATH=$HOME/go/`
    * 在hyperledger文件夹下clone该demo已经有的fabric网络:`git clone https://github.com/kvenLin/fabric1.0.git`
        * 使用IDEA打开项目,查看文件imocc下的memo.md文件可以看到所有运行当前网络的命令
        * Fabric1.0.0的学习文档也在imocc文件夹下,搭链学习参考,文档中会有详细讲解如何搭建整个网络的
    * 运行docker-compose.yaml:
        * `cd imocc/deploy/`
        * `export COMPOSE_HTTP_TIMEOUT=12000`,设置环境变量
        * `docker-compose up`,这里使用的开发者模式,所以不需要后台运行,详细参考学习文档
    * 网络运行后依次在运行下面的命令(在deploy文件夹所在路径下),参考memo.md和学习文档
        * `rm -rf config/*`
        * `rm -rf crypto-config/*`
        * `cryptogen generate --config=./crypto-config.yaml`
        * `configtxgen -profile OneOrgOrdererGenesis -outputBlock ./config/genesis.block`
        * `configtxgen -profile TwoOrgChannel -outputCreateChannelTx ./config/mychannel.tx -channelID mychannel`
        * `configtxgen -profile TwoOrgChannel -outputAnchorPeersUpdate ./config/Org0MSPanchors.tx -channelID mychannel -asOrg Org0MSP`
        * `configtxgen -profile TwoOrgChannel -outputAnchorPeersUpdate ./config/Org1MSPanchors.tx -channelID mychannel -asOrg Org1MSP`
    * 进入docker: `docker exec -it -cli bash`
        *  `peer channel create -o orderer.imocc.com:7050 -c mychannel -f /etc/hyperledger/config/mychannel.tx`
        * `peer channel join -b mychannel.block`
        * `peer channel update -o orderer.imocc.com:7050 -c mychannel -f /etc/hyperledger/config/Org1MSPanchors.tx`
        * `peer chaincode install -n register -v 1.0.0 -l golang -p github.com/chaincode/register`
        * `peer chaincode install -n drugTrace -v 1.0.0 -l golang -p github.com/chaincode/drugTrace`
        * `peer chaincode instantiate -o orderer.imocc.com:7050 -C mychannel -n register -l golang -v 1.0.0 -c '{"Args":["init"]}'`
        * `peer chaincode instantiate -o orderer.imocc.com:7050 -C mychannel -n drugTrace -l golang -v 1.0.0 -c '{"Args":["init"]}'`
    * 退出docker容器在chaincode路径下运行链码:
        * 打开一个终端,进入register链码路径: `cd imooc/chaincode/register/`
        * 运行register链码: `CORE_CHAINCODE_ID_NAME=register:1.0.0 CORE_PEER_ADDRESS=0.0.0.0:27051 CORE_CHAINCODE_LOGGING_LEVEL=DEBUG go run -tags=nopkcs11 register.go`
        * 打开另个终端,进入drugTrace链码路径: `cd imooc/chaincode/drugTrace/`
        * 运行drugTrace链码: `CORE_CHAINCODE_ID_NAME=drugTrace:1.0.0 CORE_PEER_ADDRESS=0.0.0.0:27051 CORE_CHAINCODE_LOGGING_LEVEL=DEBUG go run -tags=nopkcs11 drugTrace.go`
### 运行后台项目
* 上述本机Fabric网络环境搭建好之后就可以运行整个后台了
* 测试界面首页在 resources/本地测试/index.html
* demo项目只提供接口测试数据的展示
### demo展示
* 用户管理
![用户管理](https://raw.githubusercontent.com/kvenLin/drug-trace-system/system-for-local-demo/src/main/resources/images/001.png)

* 药品管理
![药品管理](https://raw.githubusercontent.com/kvenLin/drug-trace-system/system-for-local-demo/src/main/resources/images/002.png)