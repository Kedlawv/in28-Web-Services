insert into user values(101, sysdate(), 'Adam');
insert into user values(102, sysdate(), 'Ewa');
insert into user values(103, sysdate(), 'Olaf');
insert into user values(104, sysdate(), 'Waldek');

insert into post values(201, 'First test post', 104);
insert into post values(202, 'Second post just to have two posts on one user', 104);
insert into post values(203, 'Hi Im Eva and I dont know how to escape single quotes lol', 102);
