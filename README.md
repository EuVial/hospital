Система Больница.

Врач определяет диагноз, делает назначение Пациенту (процедуры, лекарства, операции).

Назначение может выполнить Медсестра (процедуры, лекарства) или Врач (любое назначение).

Пациент может быть выписан из Больницы, при этом фиксируется окончательный диагноз.

Для инициализации базы данных необходимо последовательно запустить sql-скрипты, находящиеся в директории hospital/sql/

(в качестве СУБД использован MySQL)

mysql> source

0_DROP.sql

1_CREATE_DB.sql

2_CREATE_TABLES.sql

3_DUMPING_DATA.sql
