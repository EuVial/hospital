
# SELECT COUNT(*) AS 'count' FROM (SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1) AS 't';
# SELECT COUNT(*) AS 'count' FROM (SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1) AS 't';
# SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1;
# SELECT COUNT(*) AS 'count' FROM hospital.treatment WHERE performer_id = 2 LIMIT 1;
# SELECT * FROM patient, patient_diagnosis WHERE patient.id = patient_diagnosis.patient_id;
SELECT diagnosis.title, user.first_name, user.last_name, user.role_id, patient_diagnosis.consultation_date
FROM patient_diagnosis
  JOIN user ON (patient_diagnosis.doctor_id = user.id)
  JOIN diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)
WHERE patient_diagnosis.patient_id = 6;