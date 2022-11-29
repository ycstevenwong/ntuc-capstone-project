-- Add this two in application.properties

--spring.jpa.defer-datasource-initialization=true
--spring.sql.init.mode=always

Delete
from project.dormant_accounts
where 1;
Delete
from project.transactions
where 1;
Delete
from project.transactions
where 1;
Delete
from project.employee
where 1;
Delete
from project.accounts
where 1;
Delete
from project.account_types
where 1;
Delete
from project.customer
where 1;
-- Insert customer table

INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('1', 'Singapore Block A', '18', '2004-01-01', 'steven@test.com', 'MALE', 'Steven', 'Gabriel', 'S54321',
        'S12345', '11111111', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('2', 'Singapore Block B', '18', '2004-02-02', 'gabriel@test.com', 'MALE', 'Gabriel', 'Steven', 'S12345',
        'S54321', '22222222', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('3', 'Singapore Block C', '18', '2004-03-03', 'jiayi@test.com', 'FEMALE', 'Jia Yi', 'Ming Ming', 'S09876',
        'S67890', '33333333', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('4', 'Singapore Block D', '18', '2004-04-04', 'ming2@test.com', 'FEMALE', 'Ming Ming', 'Gabriel', 'S67890',
        'S09876', '44444444', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('5', 'Singapore Block E', '18', '2004-05-05', 'qz@test.com', 'MALE', 'Qi Zhi', 'Si Qi', 'T54321', 'T12345',
        '55555555', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('6', 'Singapore Block F', '18', '2004-06-06', 'sq@test.com', 'FEMALE', 'Si Qi', 'Qi Zhi', 'T12345', 'T54321',
        '66666666', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('7', 'Singapore Block G', '18', '2004-07-07', 'fusheng@test.com', 'MALE', 'Fu Sheng', 'Keith', 'T09876',
        'T67890', '77777777', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('8', 'Singapore Block H', '18', '2004-08-08', 'keith@test.com', 'MALE', 'Keith', 'Fu Sheng', 'T67890', 'T09876',
        '88888888', current_timestamp());
INSERT INTO `project`.`customer` (`customer_id`, `address`, `age`, `birth_date`, `email_address`, `gender`, `name`,
                                  `nominee_name`, `nominee_nric`, `nric`, `phone`, `register_time`)
VALUES ('9', 'Singapore Block I', '18', '2004-09-09', 'bowen@test.com', 'MALE', 'Bowen', 'Steven', 'S12345', 'T11111',
        '99999999', current_timestamp());

-- Insert Employee Table
INSERT INTO `project`.`employee` (`employee_id`, `pwd`, `register_time`,`email`)
VALUES ('1', 'password', current_timestamp(), 'test1@uob.com');
INSERT INTO `project`.`employee` (`employee_id`, `pwd`, `register_time`,`email`)
VALUES ('2', 'password', current_timestamp(), 'test2@uob.com');
INSERT INTO `project`.`employee` (`employee_id`, `pwd`, `register_time`,`email`)
VALUES ('3', 'password', current_timestamp(), 'test3@uob.com');


-- Insert Account Types Table
INSERT INTO `project`.`account_types` (`account_type_id`, `interest_rate`, `name`)
VALUES ('1', '0.07', 'Fixed Deposit');
INSERT INTO `project`.`account_types` (`account_type_id`, `interest_rate`, `name`)
VALUES ('2', '0.10', 'Recurring Deposit');


-- Insert Accounts Table
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('1', '5000', 'OPEN', '1', '1');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('2', '15000', 'OPEN', '1', '2');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('3', '25000', 'OPEN', '1', '3');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('4', '9000', 'OPEN', '1', '4');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('5', '0', 'CLOSED', '1', '5');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('6', '6000', 'OPEN', '1', '6');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('7', '7000', 'OPEN', '1', '7');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('8', '8000', 'CLOSED', '1', '8');
INSERT INTO `project`.`accounts` (`account_number`, `balance`, `status`, `account_type_id`, `customer_id`)
VALUES ('9', '9000', 'OPEN', '1', '9');


-- Insert Dormant Accounts Table
INSERT INTO `project`.`dormant_accounts` (`dormant_account_number`, `customer_id`, `status`)
VALUES ('1', '5', 'CLOSED');
INSERT INTO `project`.`dormant_accounts` (`dormant_account_number`, `customer_id`, `status`)
VALUES ('2', '8', 'CLOSED');

-- Insert Transactions Table
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`,
                                      `account_id`)
VALUES ('1', '500', 'education', current_timestamp(), '1');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`,
                                      `account_id`)
VALUES ('2', '-500', 'xio shuan fei', current_timestamp(), '2');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`,
                                      `account_id`)
VALUES ('3', '1500', 'household', current_timestamp(), '3');
INSERT INTO `project`.`transactions` (`transaction_id`, `transaction_amount`, `description`, `transaction_time`,
                                      `account_id`)
VALUES ('4', '-400', 'expensive food', current_timestamp(), '4');

-- Insert Employee Table
INSERT INTO `project`.`employee` (`employee_id`, `pwd`, `register_time`,`email`)
VALUES (4, '$2a$12$e9xo1Pii3XtqyyLJZMMgtOfVlF68HtzyAij3e602jbAz81.fTERz6', '2022-11-28 17:20:56', 'test@uob.com');