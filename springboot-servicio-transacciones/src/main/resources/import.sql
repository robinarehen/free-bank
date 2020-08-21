insert into accounts (account_iban, amount) values ('ES9820385778983000760236', 195.38);

insert into transactions (reference, account_iban, amount, date, fee, description) values ('12345A', 'ES9820385778983000760236', 193.38, '2020-08-20 16:55:42', 3.18, 'Restaurant payment');
insert into transactions (reference, account_iban, amount, date, fee, description) values ('12345B', 'ES9820385778983000760236', 195.38, '2020-08-21 16:55:42', 3.18, 'Restaurant payment');
insert into transactions (reference, account_iban, amount, date, fee, description) values ('12345C', 'ES9820385778983000760236', -193.38, '2020-08-31 16:55:42', 3.18, 'Restaurant payment');