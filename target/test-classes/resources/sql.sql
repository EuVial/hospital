select * from hospital.patient;
# SELECT COUNT(*) AS 'count' FROM (SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1) AS 't';
# SELECT COUNT(*) AS 'count' FROM (SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1) AS 't';
SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1;
SELECT COUNT(*) AS 'count' FROM hospital.treatment WHERE performer_id = 2 LIMIT 1;
SHOW WARNINGS;