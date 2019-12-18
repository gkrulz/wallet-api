# E-wallet api

The API to support an e-wallet solution

Create database 'WALLET' and create the following tables

```sql
CREATE TABLE `USER` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `WALLET` (
  `ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ID`,`USER_ID`),
  KEY `fk_WALLET_USER_1_idx` (`USER_ID`),
  CONSTRAINT `fk_WALLET_USER_1` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `WALLET_HISTORY` (
  `ID` varchar(36) NOT NULL,
  `WALLET_ID` varchar(36) NOT NULL,
  `DEBIT` decimal(13,4) DEFAULT NULL,
  `CREDIT` decimal(13,4) DEFAULT NULL,
  `BALANCE` decimal(13,4) DEFAULT NULL,
  `DATE_TS` bigint(14) NOT NULL,
  PRIMARY KEY (`ID`,`WALLET_ID`),
  KEY `fk_WALLET_HISTORY_1_idx` (`WALLET_ID`),
  CONSTRAINT `fk_WALLET_HISTORY_1` FOREIGN KEY (`WALLET_ID`) REFERENCES `WALLET` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

Clone the repo and build it using the following commands

```bash
mvn clean package
```

Deploy the API in a Tomcat instance


**REST API Documentation**

Create User

```bash
curl -X POST \
  http://localhost:8080/wallet-api/v1/users \
  -H 'content-type: application/json' \
  -d '{
	"id": "1111",
	"name": "Toxic"
}'
```

Create wallet for user 

```bash
curl -X POST \
  http://localhost:8080/wallet-api/v1/wallet \
  -H 'content-type: application/json' \
  -d '{
	"id": "1111",
	"user": {"id": "1111"},
	"balance": 0
}'
```

Credit/Recharge

```bash
curl -X POST \
  http://localhost:8080/wallet-api/v1/wallet/credit \
  -H 'content-type: application/json' \
  -d '{
	"userId": "1111",
	"walletId": "1111",
	"amount": 500.0,
	"promoType": "SALTSIDE"
}'
```

Debit/Withdraw

```bash
curl -X POST \
  http://localhost:8080/wallet-api/v1/wallet/debit \
  -H 'content-type: application/json' \
  -d '{
	"userId": "1111",
	"walletId": "1111",
	"amount": 60.0
}'
```

Check balance

```bash
curl -X GET http://localhost:8080/wallet-api/v1/wallet/1111
```

Check wallet history

```bash
curl -X GET http://localhost:8080/wallet-api/v1/wallet/1111/history
```
