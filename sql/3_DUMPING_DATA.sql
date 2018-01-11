USE `hospital`;

--
-- Dumping data for table `patient`
--
INSERT INTO `patient`
(id, first_name, last_name, ward)
VALUES
  (1,'Eddard','Stark',1),
  (2, 'Jaime', 'Lannister', 2),
  (3, 'Myrcelle', 'Baratheon', 2),
  (4, 'Jorah', 'Mormont', 1),
  (5, 'Aerys', 'Targaryen', 3),
  (6, 'Bran', 'Stark', 1);

--
-- Dumping data for table `diagnosis`
--
INSERT INTO `diagnosis`
(id, title)
VALUES
  (1,'migraine'),
  (2,'poisoning'),
  (3,'diarrhea'),
  (4,'greyscale'),
  (5,'hand gangrene'),
  (6,'pharyngitis'),
  (7,'broken hand'),
  (8,'madness'),
  (9,'schizophrenia'),
  (10,'fracture of the spine');

--
-- Dumping data for table `role`
--
INSERT INTO `role`
(id, title)
VALUES
  (0,'admin'),
  (1,'doctor'),
  (2,'nurse');

--
-- Dumping data for table `treatment_type`
--
INSERT INTO `treatment_type`
(id, title)
VALUES
  (0,'medicament'),
  (1,'procedure'),
  (2,'surgery');

--
-- Dumping data for table `role_treatment_type`
--
INSERT INTO `role_treatment_type`
(id, role_id, treatment_type_id, allow)
VALUES
  (1,1,0,'PERFORM'),
  (2,1,1,'PERFORM'),
  (3,1,2,'PERFORM'),
  (4,1,0,'ASSIGN'),
  (5,1,1,'ASSIGN'),
  (6,1,2,'ASSIGN'),
  (7,2,0,'PERFORM'),
  (8,2,1,'PERFORM');

--
-- Dumping data for table `user`
--
INSERT INTO `user`
(id, login, password, first_name, last_name, role_id)
VALUES
  (1,'admin','pass','George','Martin',0),
  (2,'pycelle','pycelle','Pycelle','Gr.Maester',1),
  (3,'aemon','aemon','Aemon','Targaryen',1),
  (4,'luwin','luwin','Luwin','Maester',1),
  (5,'tarly','tarly','Samwell','Tarly',2),
  (6,'craster','craster','Gilly','Craster',2),
  (24,'testLogin','testPass','testFN','test',1),
  (26,'q','12345','q','q',0);

--
-- Dumping data for table `patient_diagnosis`
--
INSERT INTO `patient_diagnosis`
(id, patient_id, diagnosis_id, doctor_id, consultation_date)
VALUES
  (1,1,1,2,'2010-01-20 16:00:00'),
  (2,2,7,2, '2009-01-20 16:00:00'),
  (3,4,4,3, '2009-01-20 15:00:00'),
  (4,3,2,3, '2009-01-20 11:00:00'),
  (5,5,8,2, '2007-01-20 16:00:00'),
  (6,6,9,2, '2009-01-30 16:00:00'),
  (7,6,10,3, '2013-01-20 16:00:00');

--
-- Dumping data for table `treatment`
--
INSERT INTO `treatment`
(id, title, patient_diagnosis_id, type_id, performer_id, done)
VALUES
  (1,'Drugs',1,1,1,0);