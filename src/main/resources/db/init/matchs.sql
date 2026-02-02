TRUNCATE TABLE ratings;
TRUNCATE TABLE match CASCADE;

-- AOUT
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (1, 'LIGUE 1', to_date('2025-08-16', 'YYYY-MM-DD'), true, 'OLYMPIQUE LYONNAIS', 0,1);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (2, 'LIGUE 1', to_date('2025-08-24', 'YYYY-MM-DD'), false, 'LE HAVRE AC', 2,1);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (3, 'LIGUE 1', to_date('2025-08-29', 'YYYY-MM-DD'), true, 'STADE BRESTOIS 29', 3,1);
-- SEPTEMBRE
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (4, 'LIGUE 1', to_date('2025-09-14', 'YYYY-MM-DD'), false, 'PARIS SG', 0,2);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (5, 'LIGUE 1', to_date('2025-09-20', 'YYYY-MM-DD'), true, 'LILLE OSC', 3,0);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (6, 'LIGUE 1', to_date('2025-09-28', 'YYYY-MM-DD'), false, 'STADE RENNAIS FC', 0,0);
-- OCTOBRE
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (7, 'LIGUE 1', to_date('2025-10-04', 'YYYY-MM-DD'), false, 'AJ AUXERRE', 2,1);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (8, 'LIGUE 1', to_date('2025-10-19', 'YYYY-MM-DD'), true, 'PARIS FC', 2,1);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (9, 'LIGUE 1', to_date('2025-10-25', 'YYYY-MM-DD'), true, 'OLYMPIQUE DE MARSEILLE', 2,1);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (10, 'LIGUE 1', to_date('2025-10-29', 'YYYY-MM-DD'), false, 'FC METZ', 0,2);
-- NOVEMBRE
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (11, 'LIGUE 1', to_date('2025-11-02', 'YYYY-MM-DD'), true, 'FC LORIENT', 3,0);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (12, 'LIGUE 1', to_date('2025-11-08', 'YYYY-MM-DD'), false, 'AS MONACO FC', 4,1);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (13, 'LIGUE 1', to_date('2025-11-22', 'YYYY-MM-DD'), true, 'RC STRASBOURG ALSACE', 1,0);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (14, 'LIGUE 1', to_date('2025-11-30', 'YYYY-MM-DD'), false, 'ANGERS SCO', 2,1);
-- DECEMBRE
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (15, 'LIGUE 1', to_date('2025-12-06', 'YYYY-MM-DD'), false, 'FC NANTES', 2,1);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (16, 'LIGUE 1', to_date('2025-12-14', 'YYYY-MM-DD'), true, 'OGC NICE', 2,0);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (35, 'COUPE DE FRANCE', to_date('2025-12-19', 'YYYY-MM-DD'), true, 'ENTENTE FEIGNIES-AULNOYE FC', 3,1);
-- JANVIER
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (17, 'LIGUE 1', to_date('2026-01-02', 'YYYY-MM-DD'), false, 'TOULOUSE FC', 3,0);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (36, 'COUPE DE FRANCE', to_date('2026-01-11', 'YYYY-MM-DD'), false, 'FC SOCHAUX-MONTBELIARD', 3,0);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (18, 'LIGUE 1', to_date('2026-01-17', 'YYYY-MM-DD'), true, 'AJ AUXERRE', 1,0);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (19, 'LIGUE 1', to_date('2026-01-24', 'YYYY-MM-DD'), false, 'OLYMPIQUE DE MARSEILLE', 1,3);
INSERT INTO match (id, competition, date, home, opponent, score_lens, score_opponent) VALUES (20, 'LIGUE 1', to_date('2026-01-30', 'YYYY-MM-DD'), true, 'LE HAVRE AC', 1,0);