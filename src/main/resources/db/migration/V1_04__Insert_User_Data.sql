INSERT INTO "user"(USERNAME,PASSWORD,CREATED_AT,ENABLED) VALUES
       ('admin','$2a$12$WrAhLuG/o7eh7bFNIwFOo.eGEr3DLBQdHC8ZkScFhP/vseEjeyaX2',CURRENT_TIMESTAMP,TRUE),
       ('staff','$2a$12$z/j5Lc2LMjMxdJfEGvbrOOvCuyQHNVyQ1TGX0Ny82Frucm3Cux.Sm',CURRENT_TIMESTAMP,TRUE)
       ON CONFLICT DO NOTHING;