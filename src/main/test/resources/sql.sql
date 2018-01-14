
# SELECT COUNT(*) AS 'count' FROM (SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1) AS 't';
# SELECT COUNT(*) AS 'count' FROM (SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1) AS 't';
# SELECT 'id' FROM hospital.treatment WHERE 'performer_id' = 1 LIMIT 1;
# SELECT COUNT(*) AS 'count' FROM hospital.treatment WHERE performer_id = 2 LIMIT 1;
# SELECT * FROM patient, patient_diagnosis WHERE patient.id = patient_diagnosis.patient_id;

# SELECT diagnosis.title, user.first_name, user.last_name, user.role_id, patient_diagnosis.consultation_date
# FROM patient_diagnosis
#   JOIN user ON (patient_diagnosis.doctor_id = user.id)
#   JOIN diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)
# WHERE patient_diagnosis.patient_id = 6;

# SELECT diagnosis.title, user.first_name, user.last_name, user.role_id, patient_diagnosis.consultation_date
# FROM hospital.patient_diagnosis
#   JOIN hospital.user ON (patient_diagnosis.doctor_id = user.id)
#   JOIN hospital.diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)
# WHERE patient_diagnosis.id = 1;

# SELECT COUNT(*) AS 'count' FROM hospital.treatment WHERE patient_diagnosis_id = ? LIMIT 1;
# SELECT id FROM hospital.diagnosis WHERE title = ?;

# SELECT treatment.title, treatment.type_id, treatment.done, user.first_name, user.last_name, user.role_id, diagnosis.title,
# patient.id, patient.first_name, patient.last_name, patient.ward
# FROM hospital.treatment
#   JOIN hospital.patient_diagnosis ON (treatment.patient_diagnosis_id = patient_diagnosis.id)
#   JOIN hospital.user ON (treatment.performer_id = user.id)
#   JOIN hospital.diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)
#   JOIN hospital.patient ON (patient_diagnosis.patient_id = patient.id)
# WHERE treatment.id = ?;

# UPDATE hospital.treatment
# SET done = 1, performer_id = ?
# WHERE id = ?
SELECT * FROM hospital.treatment WHERE id=51;

UPDATE hospital.treatment
SET done = 1, performer_id = 2
WHERE id = 51;