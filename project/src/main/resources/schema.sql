-- Insert to account_types table
INSERT INTO `project`.`account_types` (`account_type_id`, `interest_rate`, `name`)
VALUES (NULL, '0.07', 'Recurring Deposit');
INSERT INTO `project`.`account_types` (`account_type_id`, `interest_rate`, `name`)
VALUES (NULL, '0.10', 'Fixed Deposit');
-- Insert to customer table
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`) 
VALUES ('1', 'Singapore Block A', '18', '2004-01-01', 'steven@gmail.com', 'MALE', 'Steven', 'Gabriel', 'S12345', 'S54321', '12345678', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`) 
VALUES ('2', 'Singapore Block B', '18', '2004-02-02', 'gabriel@gmail.com', 'MALE', 'Gabriel', 'Steven', 'S54321', 'S12345', '87654321', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`, `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`) 
VALUES ('3', 'Singapore Block C', '18', '2004-03-03', 'mingming@gmail.com', 'FEMALE', 'Ming Ming', 'Jia Yi', 'S67890', 'S09876', '66666666', current_timestamp());
-- Insert to accounts table
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`) 
VALUES ('010', '5000', 'OPEN', '1', '1');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`) 
VALUES ('011', '15000', 'CLOSED', '2', '2');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`) 
VALUES ('012', '6000', 'OPEN', '2', '3');
-- Insert to transactions table
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`, `account_id`) 
VALUES (NULL, '200', 'DEPOSIT', current_timestamp(), '010');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`, `account_id`) 
VALUES (NULL, '500', 'DEPOSIT', current_timestamp(), '010');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`, `account_id`) 
VALUES (NULL, '1000', 'WITHDRAW', current_timestamp(), '012');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`, `account_id`) 
VALUES (NULL, '1000', 'DEPOSIT', current_timestamp(), '012');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`, `account_id`) 
VALUES (NULL, '0.01', 'WITHDRAW', current_timestamp(), '011');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`, `account_id`) 
VALUES (NULL, '10', 'WITHDRAW', current_timestamp(), '011');
