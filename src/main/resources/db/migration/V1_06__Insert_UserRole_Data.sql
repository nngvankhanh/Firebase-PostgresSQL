INSERT INTO "user_role"(USER_ID,ROLE_ID) VALUES
       (1,1),
       (2,2)
       ON CONFLICT DO NOTHING;