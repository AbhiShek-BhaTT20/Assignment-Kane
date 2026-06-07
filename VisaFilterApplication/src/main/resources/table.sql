
--Assignement-2

create schema employeedb;
use employeedb;
drop table if exists EMPLOYEE;
CREATE TABLE EMPLOYEE (
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    salary DECIMAL(10,2) NOT NULL
);
INSERT INTO EMPLOYEE (id, name, salary) VALUES
(1, 'Abhishek', 50000),
(2, 'Ankita', 30000),
(3, 'Piyush', 40000),
(4, 'Ravi', 20000),
(5, 'Anjali', 35000);
SELECT e1.name AS FirstEmployee, e2.name AS SecondEmployee
FROM EMPLOYEE e1 JOIN EMPLOYEE e2
ON e1.salary < e2.salary
ORDER BY e1.id ASC, e2.salary ASC;



