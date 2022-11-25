-- Add this two in application.properties

--spring.jpa.defer-datasource-initialization=true
--spring.sql.init.mode=always

Delete from project.users_roles where 1;
Delete from project.transactions where 1;
Delete from project.accounts where 1;
Delete from project.account_open_request where 1;
Delete from project.account_types where 1;
Delete from project.roles where 1;
Delete from project.user where 1;



-- Insert user table
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('1', 'Singapore Block A', '18', '2004-01-01', 'steven@gmail.com', 'MALE', 'Steven', 'Gabriel', 'S12345', 'S54321', '12345678', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('2', 'Singapore Block B', '18', '2004-02-02', 'gabriel@gmail.com', 'MALE', 'Gabriel', 'Steven', 'S54321', 'S12345', '87654321', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('3', 'Singapore Block C', '18', '2004-03-03', 'mingming@gmail.com', 'FEMALE', 'Ming Ming', 'Jia Yi', 'S67890', 'S09876', '66666666', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('4', 'Singapore Block D', '18', '2004-06-06', 'jiayi@gmail.com', 'FEMALE', 'Jia Yi', 'Ming Ming', 'S09876', 'S67890', '88888888', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('5', 'Singapore Block E', '18', '2004-07-07', 'siqi@gmail.com', 'FEMALE', 'Si Qi', 'Qi Zhi', 'T12345', 'T54321', '77777777', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('6', 'Singapore Block F', '18', '2004-08-08', 'qizhi@gmail.com', 'MALE', 'Qi Zhi', 'Si Qi', 'T54321', 'T12345', '99999999', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('7', 'Singapore Block G', '18', '2004-09-09', 'fusheng@gmail.com', 'MALE', 'Fu Sheng', 'Bowen', 'T67890', 'T09876', '11111111', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('8', 'Singapore Block H', '18', '2004-10-10', 'bowen@gmail.com', 'MALE', 'Bowen', 'Fu Sheng', 'T09876', 'T67890', '22222222', 'password', current_timestamp());
INSERT INTO `project`.`user` (`user_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `pwd`, `register_time`) 
VALUES ('9', 'Singapore Block I', '18', '2004-11-11', 'keith@gmail.com', 'MALE', 'Keith', 'Steven', 'S12345', 'T11111', '33333333', 'password', current_timestamp());

-- Insert roles table
INSERT INTO `project`.`roles` (`role_id`, `name`)
VALUES ('1', 'Admin');
INSERT INTO `project`.`roles` (`role_id`, `name`)
VALUES ('2', 'User');

-- Insert account_types table
INSERT INTO `project`.`account_types` (`account_type_id`, `interest_rate`, `name`)
VALUES ('1', '0.07', 'Recurring Deposit');
INSERT INTO `project`.`account_types` (`account_type_id`, `interest_rate`, `name`)
VALUES ('2', '0.10', 'Fixed Deposit');

-- Insert user_roles table
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('1', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('2', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('3', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('4', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('5', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('6', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('7', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('8', '1');
INSERT INTO `project`.`users_roles` (`user_id`, `role_id`)
VALUES ('9', '1');

-- Insert account_open_request table
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('1', 'PENDING', 'Recurring Deposit', '1');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('2', 'REJECTED', 'Fixed Deposit', '2');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('3', 'APPROVED', 'Fixed Deposit', '3');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('4', 'APPROVED', 'Recurring Deposit', '4');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('5', 'APPROVED', 'Fixed Deposit', '5');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('6', 'APPROVED', 'Recurring Deposit', '6');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('7', 'APPROVED', 'Fixed Deposit', '7');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('8', 'APPROVED', 'Recurring Deposit', '8');
INSERT INTO `project`.`account_open_request` (`request_id`, `status`, `account_type`, `user_id`) 
VALUES ('9', 'APPROVED', 'Fixed Deposit', '9');

-- Insert accounts table
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `user_id`) 
VALUES ('010', '5000', 'OPEN', '1', '4');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `user_id`) 
VALUES ('011', '15000', 'CLOSED', '2', '3');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `user_id`) 
VALUES ('012', '6000', 'OPEN', '2', '5');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `user_id`) 
VALUES ('013', '7000', 'OPEN', '1', '8');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `user_id`) 
VALUES ('014', '10000', 'CLOSED', '1', '6');

-- Insert transactions table
INSERT INTO `project`.`transactions` (`transaction_id`, `amount`, `from_account`, `time`, `to_account`) 
VALUES ('1', '200', '010', current_timestamp(), '012');
INSERT INTO `project`.`transactions` (`transaction_id`, `amount`, `from_account`, `time`, `to_account`) 
VALUES ('2', '500', '012', current_timestamp(), '014');
INSERT INTO `project`.`transactions` (`transaction_id`, `amount`, `from_account`, `time`, `to_account`) 
VALUES ('3', '1000', '013', current_timestamp(), '012');
