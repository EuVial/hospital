USE `hospital`;

--
-- Dumping data for table `patient`
--
INSERT INTO `patient`
(id, first_name,  last_name,  ward)
VALUES
  (1, 'Eddard',   'Stark',      1),
  (2, 'Jaime',    'Lannister',  2),
  (3, 'Myrcelle', 'Baratheon',  2),
  (4, 'Jorah',    'Mormont',    1),
  (5, 'Aerys',    'Targaryen',  3),
  (6, 'Bran',     'Stark',      1),
  (7, 'Hodor',    'HODOR',      1),
  (8, 'Tyrion',   'Lannister',  2);

--
-- Dumping data for table `diagnosis`
--
INSERT INTO `diagnosis`
(id, title)
VALUES
  (1, 'Migraine'),
  (2, 'Poisoning'),
  (3, 'Diarrhea'),
  (4, 'Greyscale'),
  (5, 'Hand gangrene'),
  (6, 'Pharyngitis'),
  (7, 'Broken hand'),
  (8, 'Madness'),
  (9, 'Schizophrenia'),
  (10,'Fracture of the spine'),
  (11,'Dwarfism'),
  (12,'Dyslexia'),
  (13,'Lackwit'),
  (14, 'Wound in the shoulder'),
  (15, 'Head fell off');

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
  (1,1,14,2,'2010-01-20 16:00:00'),
  (2,2,7,2, '2009-01-20 16:00:00'),
  (3,4,4,3, '2009-01-20 15:00:00'),
  (4,3,2,3, '2009-01-20 11:00:00'),
  (5,5,8,2, '2007-01-20 16:00:00'),
  (6,6,9,2, '2009-01-30 16:00:00'),
  (7,6,10,3, '2013-01-20 16:00:00'),
  (8,7,13,3, '2015-01-20 16:00:00'),
  (9,2,12,3, '2011-01-20 16:00:00'),
  (10,8,11,3, '2016-01-20 16:00:00'),
  (11,1,15,2, '2016-02-21 09:20:12');

--
-- Dumping data for table `treatment`
--
INSERT INTO `treatment`
(id, title, patient_diagnosis_id, type_id, performer_id, done)
VALUES
  (1,'Milk of the poppy',1,0,5,1),
  (2,'Stop the bleeding',1,1,2,1),
  (3,'Placing a herbal plaster',1,1,5,1),
  (4,'Mixture of herbs',1,0,5,1),
  (5,'Milk of the poppy',2,0,5,1),
  (6,'Bone repositioning',2,2,NULL,0),
  (7,'Put a splint',2,2,NULL,0),
  (8,'Plaster cast on',2,1,NULL,0),
  (9,'Pump stomach',4,1,6,1),
  (10,'Mixture of tansy',4,0,6,1),
  (11,'Pump stomach',4,1,6,1),
  (12,'Essence of Nightshade',4,1,6,1),
  (13,'Pump stomach',4,1,6,1),
  (14,'Milk of the poppy',3,0,5,1),
  (15,'Essence of Nightshade',3,0,5,1),
  (16,'Cutting off infected skin',3,2,3,1),
  (17,'Placing a herbal plaster',3,1,NULL,0),
  (18,'Essence of Nightshade',5,0,3,1),
  (19,'Essence of Nightshade',5,0,3,1),
  (20,'Essence of Nightshade',5,0,3,1),
  (21,'Mixture of tansy',5,0,3,1),
  (22,'Mixture of herbs',5,0,3,1),
  (23,'Essence of Nightshade',5,0,3,1),
  (24,'Essence of Nightshade',6,0,2,1),
  (25,'Mixture of tansy',6,0,2,1),
  (26,'Mixture of herbs',6,0,2,1),
  (27,'Essence of Nightshade',6,0,2,1),
  (28,'Milk of the poppy',7,0,2,1),
  (29,'Skeleton extending',7,2,2,1),
  (30,'Mixture of pennyroyal',7,0,2,1),
  (31,'Skeleton extending',7,2,2,1),
  (32,'Essence of Nightshade',7,0,NULL,0),
  (33,'Essence of Nightshade',8,0,6,1),
  (34,'Mixture of tansy',8,0,6,1),
  (35,'Essence of Nightshade',8,0,6,1),
  (36,'Mixture of herbs',8,0,6,1),
  (37,'Essence of Nightshade',8,0,NULL,0),
  (38,'Essence of Nightshade',9,0,2,1),
  (39,'Mixture of tansy',9,0,2,1),
  (40,'Blueberries tea',9,0,2,1),
  (41,'Mixture of herbs',9,0,2,1),
  (42,'Blueberries tea',9,0,2,1),
  (43,'Essence of Nightshade',9,0,2,1),
  (44,'Blueberries tea',9,0,2,1),
  (45,'Milk of the poppy',10,0,2,1),
  (46,'Bone straightening',10,2,2,1),
  (47,'Milk of the poppy',10,0,2,1),
  (48,'Bone straightening',10,2,2,1),
  (49,'Milk of the poppy',10,0,2,1),
  (50,'Bone straightening',10,2,2,1),
  (51,'Milk of the poppy',10,0,NULL,0),
  (52,'Bone straightening',10,2,NULL,0),
  (53,'Stop the bleeding',11,1,5,1),
  (54,'Milk of the poppy',11,0,5,1),
  (55,'Put head on shoulders',11,2,2,1),
  (56,'Skeleton extending',11,2,2,1),
  (57,'Stitch head on shoulders',11,2,2,1),
  (58,'Placing a herbal plaster',1,1,NULL,0),
  (59,'Mixture of herbs',1,0,NULL,0);









