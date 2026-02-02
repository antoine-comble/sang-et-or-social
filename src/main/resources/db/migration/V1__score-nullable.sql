-- Permettre aux scores d'être NULL pour les matchs qui ne se sont pas encore déroulés
ALTER TABLE match ALTER COLUMN score_lens DROP NOT NULL;
ALTER TABLE match ALTER COLUMN score_opponent DROP NOT NULL;