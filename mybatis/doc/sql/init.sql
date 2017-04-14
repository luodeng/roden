DROP TABLE IF EXISTS `user_t`;

CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_t` */

insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (1,'测试','sfasgfaf',24);



CREATE TABLE teacher(
    t_id INT PRIMARY KEY AUTO_INCREMENT,
    t_name VARCHAR(20)
);
CREATE TABLE class(
    c_id INT PRIMARY KEY AUTO_INCREMENT,
    c_name VARCHAR(20),
    teacher_id INT
);
ALTER TABLE class ADD CONSTRAINT fk_teacher_id FOREIGN KEY (teacher_id) REFERENCES teacher(t_id);

INSERT INTO teacher(t_name) VALUES('teacher1');
INSERT INTO teacher(t_name) VALUES('teacher2');

INSERT INTO class(c_name, teacher_id) VALUES('class_a', 1);
INSERT INTO class(c_name, teacher_id) VALUES('class_b', 2);

CREATE TABLE student(
    s_id INT PRIMARY KEY AUTO_INCREMENT,
    s_name VARCHAR(20),
    class_id INT
);
INSERT INTO student(s_name, class_id) VALUES('student_A', 1);
INSERT INTO student(s_name, class_id) VALUES('student_B', 1);
INSERT INTO student(s_name, class_id) VALUES('student_C', 1);
INSERT INTO student(s_name, class_id) VALUES('student_D', 2);
INSERT INTO student(s_name, class_id) VALUES('student_E', 2);
INSERT INTO student(s_name, class_id) VALUES('student_F', 2);


    CREATE TABLE `t_blog` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `title` varchar(255) DEFAULT NULL,
      `content` varchar(255) DEFAULT NULL,
      `owner` varchar(50) DEFAULT NULL,
      PRIMARY KEY (`id`)
    )
