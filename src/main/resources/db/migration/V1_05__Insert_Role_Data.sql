INSERT INTO "role"(NAME) VALUES
       ('ADMIN'),
       ('STAFF'),
       ('USER')
       ON CONFLICT DO NOTHING;