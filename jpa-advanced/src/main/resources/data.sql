insert into course(id,name) values(1001,'JPA Advanced');
insert into course(id,name) values(1002,'Spring in 50 steps');
insert into course(id,name) values(1003,'Spring Boot in 100 steps');

insert into passport(id,number) values(4001, '12B31234');
insert into passport(id,number) values(4002, 'E3412894');
insert into passport(id,number) values(4003, 'LM393765');

insert into student(id,name,passport_id) values(2001, 'Kedlaw',4001);
insert into student(id,name,passport_id) values(2002, 'Grzegorz',4002);
insert into student(id,name,passport_id) values(2003, 'Ewa',4003);

insert into review(id,rating,description,course_id,student_id) values(5001,'5','Great Course',1001,2001);
insert into review(id,rating,description,course_id,student_id) values(5002,'4','Wonderful Course',1001,2002);
insert into review(id,rating,description,course_id,student_id) values(5003,'3','Awesome Course',1003,2001);

insert into student_course(student_id,course_id) values(2001,1001);
insert into student_course(student_id,course_id) values(2002,1001);
insert into student_course(student_id,course_id) values(2003,1001);
insert into student_course(student_id,course_id) values(2001,1003);