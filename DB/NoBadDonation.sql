CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `credential` varchar(255) NOT NULL,
  `name` varchar(60) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `principal` varchar(60) NOT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_id` varchar(255) DEFAULT NULL,
  `regist_date`  timestamp,
  `last_modified_date` timestamp,  
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `authority` (
  `authority_id` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`authority_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `campaign` (
  `campaign_id` bigint NOT NULL AUTO_INCREMENT,  
  `account` varchar(40) DEFAULT NULL,
  `block_hash` varchar(66) DEFAULT NULL,
  `content_image_url` varchar(255) NOT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `is_end` tinyint(1) NOT NULL,
  `target_donation` bigint DEFAULT NULL,
  `thumbnail_image_url` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `view_count` bigint DEFAULT NULL,
  `regist_date`  timestamp,
  `last_modified_date` timestamp,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`campaign_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `campaign_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `wallet` (
  `wallet_id` bigint NOT NULL AUTO_INCREMENT,
  `account` bigint NOT NULL,
  `balance` decimal(19,2) NOT NULL,
  `key_file_name` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`wallet_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `token_log` (
  `token_log_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint NOT NULL,
  `is_process` tinyint(1) NOT NULL,
  `process_date` timestamp NULL DEFAULT NULL,
  `regist_date` timestamp NOT NULL,
  `tx_hash` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`token_log_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `token_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hashtag` (
  `hashtag_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`hashtag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `support_log` (
  `support_log_id` bigint NOT NULL AUTO_INCREMENT,
  `donate_date` timestamp NOT NULL,
  `amount` bigint NOT NULL,
  `tx_hash` varchar(66) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `campaign_id` bigint DEFAULT NULL,
  PRIMARY KEY (`support_log_id`),
  KEY `user_id` (`user_id`),
  KEY `campaign_id` (`campaign_id`),
  CONSTRAINT `support_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `support_log_ibfk_2` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `campaign_hashtag` (
  `campaign_hashtag_id` bigint NOT NULL AUTO_INCREMENT,
  `campaign_id` bigint DEFAULT NULL,
  `hashtag_id` bigint DEFAULT NULL,
  PRIMARY KEY (`campaign_hashtag_id`),
  KEY `hashtag_id` (`hashtag_id`),
  KEY `campaign_id` (`campaign_id`),
  CONSTRAINT `campaign_hashtag_ibfk_1` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`hashtag_id`),
  CONSTRAINT `campaign_hashtag_ibfk_2` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `receipt` (
	`receipt_id` bigint NOT NULL AUTO_INCREMENT,
    `receipt_image_url` VARCHAR(255) NOT NULL,
    `item` VARCHAR(255) NOT NULL,
    `amount` bigint NOT NULL,    
    `campaign_id` bigint ,
    `regist_date`  timestamp,
    `last_modified_date` timestamp,
	PRIMARY KEY (`receipt_id`),
    KEY `campaign_id` (`campaign_id`),
	CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`campaign_id`)
) 	ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaction` (
  `hash` varchar(255) NOT NULL,
  `block_hash` varchar(255) DEFAULT NULL,
  `block_number` decimal(19,2) DEFAULT NULL,
  `creates` varchar(255) DEFAULT NULL,
  `from_account` varchar(255) DEFAULT NULL,
  `gas` decimal(19,2) DEFAULT NULL,
  `gas_price` decimal(19,2) DEFAULT NULL,
  `input_value` BLOB DEFAULT NULL,
  `max_fee_per_gas` varchar(255) DEFAULT NULL,
  `max_priority_fee_per_gas` varchar(255) DEFAULT NULL,
  `nonce` varchar(255) DEFAULT NULL,
  `public_key` varchar(255) DEFAULT NULL,
  `r` varchar(255) DEFAULT NULL,
  `raw` varchar(255) DEFAULT NULL,
  `s` varchar(255) DEFAULT NULL,
  `to_account` varchar(255) DEFAULT NULL,
  `transaction_index` decimal(19,2) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `v` bigint NOT NULL,
  `value` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci