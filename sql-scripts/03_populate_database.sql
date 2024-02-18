USE cleaningservice;

INSERT INTO role (name)
VALUES ('ROLE_ADMIN'), ('ROLE_CUSTOMER'), ("ROLE_EMPLOYEE");

INSERT INTO users (`password`, `username`)
VALUES ('$2a$10$7hNEnIDPfxuJ/uzOcW0tVO3WkbR665NFgeMHc82njfRefPRmUEWUW', 'admin');

INSERT INTO user_roles (user_id, role) VALUES
(1,1),
(1,2)
(1,3);

INSERT INTO users (password, username) VALUES
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'username1'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'username2'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'username3'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'username4'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'username5'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'employee6'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'employee7'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'employee8'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'employee9'),
('$2a$10$nKts2eSysmakMkUV0Iq4r.GnKSn.tkHwt1cZnVQcvJmlxbc8Caf.6', 'employee10');

INSERT INTO user_roles (user_id, role) VALUES
(2,2),
(3,2),
(4,2),
(5,2),
(6,2),
(7,2),
(8,2),
(9,2),
(10,2),
(11,2);

INSERT INTO user_roles (user_id, role) VALUES
(7,3),
(8,3),
(9,3),
(10,3),
(11,3);

INSERT INTO user_info (address, date_of_birth, email, first_name, full_name, phone_number, users_id) VALUES
('123 Main St', '1997-04-12', null, 'Rodrigo', 'Rodrigo Martins', '916543213', 2),
('456 Oak St', null, '3m1rs0n310@hotmail.com', 'Emerson', null, null, 3),
(null, null, null, 'Augusto', null, null, 4),
(null, null, null, 'Rita', null, null, 5),
(null, '1975-10-30', 'janedoe@outlook.com', 'Jane', null, '917321235', 6),
('369 Cedar Ct', '1988-02-16', 'joana_martins88@gmail.com', 'Joana', 'Joana Martins', '936244123', 7),
('258 Birch Way', '1992-05-08', 'luisa92@sapo.pt', 'Luisa', 'Luisa Ribeiro', '918321239', 8),
('147 Cherry Ln', '1973-11-17', 'alicesmith@gmail.com', 'Alice' ,'Alice Smith', '932530575', 9),
('987 Walnut Blvd', '1985-09-22', 'bobjohnson@outlook.com', 'Robert', 'Robert Johnson', '91204402', 10),
('789 Pine St', '1999-03-14', 'lizlewis@hotmail.com', 'Elizabeth', 'Elizabeth Lewis', '932421055', 11);

INSERT INTO employee (active, social_security_number, started_working, years_of_experience, user_info_id) VALUES
(1, '221232123', '2019-10-10', 3, 6),
(1, '221232123', '2021-03-22', 2, 7),
(0, '221232123', null, 5, 8),
(0, '221232123', null, 1, 9),
(0, '221232123', null, 0, 10);